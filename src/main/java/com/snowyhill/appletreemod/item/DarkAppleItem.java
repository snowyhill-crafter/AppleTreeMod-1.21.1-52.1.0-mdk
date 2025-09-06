package com.snowyhill.appletreemod.item;

import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class DarkAppleItem extends Item {
    /** バフ／デバフを半々で引きたいときのフラグ */
    private static final boolean FIFTY_FIFTY = true;

    /** バフ候補（重み付き） */
    private static final List<WeightedEffect> POSITIVE = new ArrayList<>();

    /** デバフ候補（重み付き） */
    private static final List<WeightedEffect> NEGATIVE = new ArrayList<>();

    static {
        // === バフ側（効果, 期間tick, レベル, 重み） ===
        // 例: 20tick = 1秒
        POSITIVE.add(new WeightedEffect(() -> new MobEffectInstance(MobEffects.REGENERATION,   20 * 8,  1),  8));
        POSITIVE.add(new WeightedEffect(() -> new MobEffectInstance(MobEffects.ABSORPTION,     20 * 60, 1), 7));
        POSITIVE.add(new WeightedEffect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20 * 90, 1), 6));
        POSITIVE.add(new WeightedEffect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST,   20 * 45, 1), 6));
        POSITIVE.add(new WeightedEffect(() -> new MobEffectInstance(MobEffects.JUMP,           20 * 90, 1), 4));
        POSITIVE.add(new WeightedEffect(() -> new MobEffectInstance(MobEffects.LUCK,           20 * 120,0), 3));
        POSITIVE.add(new WeightedEffect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20 * 30, 0), 5));

        // === デバフ側（効果, 期間tick, レベル, 重み） ===
        NEGATIVE.add(new WeightedEffect(() -> new MobEffectInstance(MobEffects.POISON,         20 * 7,  1),  8));
        NEGATIVE.add(new WeightedEffect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20 * 45, 1), 6));
        NEGATIVE.add(new WeightedEffect(() -> new MobEffectInstance(MobEffects.WEAKNESS,       20 * 60, 0), 6));
        NEGATIVE.add(new WeightedEffect(() -> new MobEffectInstance(MobEffects.DIG_SLOWDOWN,   20 * 60, 1), 5));
        NEGATIVE.add(new WeightedEffect(() -> new MobEffectInstance(MobEffects.CONFUSION,      20 * 20, 0), 5)); // 画面ぐにゃ
        NEGATIVE.add(new WeightedEffect(() -> new MobEffectInstance(MobEffects.UNLUCK,         20 * 120,0), 3));
        NEGATIVE.add(new WeightedEffect(() -> new MobEffectInstance(MobEffects.BLINDNESS,      20 * 12, 0), 4));
        // WITHER は強すぎると感じたらコメントアウトのままで
        // NEGATIVE.add(new WeightedEffect(() -> new MobEffectInstance(MobEffects.WITHER,      20 * 5,  0), 2));
    }

    public DarkAppleItem(Properties props) {
        super(props);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        // まず通常の空腹回復や食べ終わり処理を実行
        ItemStack result = super.finishUsingItem(stack, level, entity);

        if (!level.isClientSide) {
            RandomSource rand = level.getRandom();

            // 50/50 抽選（trueで半々、falseなら単純に全候補から重み抽選に切替も可）
            boolean choosePositive = FIFTY_FIFTY ? rand.nextBoolean() : true;

            List<WeightedEffect> pool = choosePositive ? POSITIVE : NEGATIVE;
            MobEffectInstance picked = pickWeighted(pool, rand);

            if (picked != null) {
                entity.addEffect(picked);
            }
        }
        return result;
    }

    @Nullable
    private static MobEffectInstance pickWeighted(List<WeightedEffect> list, RandomSource rand) {
        if (list.isEmpty()) return null;
        int total = 0;
        for (WeightedEffect e : list) total += Math.max(0, e.weight);
        if (total <= 0) return null;

        int r = rand.nextInt(total);
        int acc = 0;
        for (WeightedEffect e : list) {
            acc += Math.max(0, e.weight);
            if (r < acc) {
                return e.newInstance();
            }
        }
        return list.get(list.size() - 1).newInstance();
    }



    /** ラムダで毎回新しい MobEffectInstance を作るためのラッパ */
    private static class WeightedEffect {
        final EffectFactory factory;
        final int weight;
        WeightedEffect(EffectFactory factory, int weight) {
            this.factory = factory;
            this.weight = weight;
        }
        MobEffectInstance newInstance() { return factory.create(); }
    }

    @FunctionalInterface
    private interface EffectFactory {
        MobEffectInstance create();
    }

    //効果を登録しケーキに引用させる
    public static void applyRandomDarkAppleEffect(LivingEntity entity, Level level) {
        if (level.isClientSide) return;
        RandomSource rand = level.getRandom();

        // 50/50 抽選（好みで調整OK）
        boolean choosePositive = rand.nextBoolean();
        List<WeightedEffect> pool = choosePositive ? POSITIVE : NEGATIVE;

        MobEffectInstance picked = pickWeighted(pool, rand); // 既存のメソッドを public static にするか、ここに複製
        if (picked != null) {
            entity.addEffect(picked);
        }
    }
}
