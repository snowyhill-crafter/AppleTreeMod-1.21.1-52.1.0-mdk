package com.snowyhill.appletreemod.worldgen.features.decolator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.snowyhill.appletreemod.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.HashSet;
import java.util.Set;

/**
 * 既存の APPLE_LEAVES を “外側” だけ APPLE_FLOWER_LEAVES に置換する TreeDecorator。
 * ブロックは ModBlocks.APPLE_LEAVES / APPLE_FLOWER_LEAVES を直参照。
 */
public class AppleOuterFlowersDecorator extends TreeDecorator {

    // JSONでも使えるよう、確率(outer_chance)を追加（既定=0.5）
    public static final MapCodec<AppleOuterFlowersDecorator> CODEC =
            RecordCodecBuilder.mapCodec(inst -> inst.group(
                    Codec.INT.optionalFieldOf("min_exposed_faces", 2).forGetter(d -> d.minExposedFaces),
                    Codec.FLOAT.optionalFieldOf("outer_chance", 0.5f).forGetter(d -> d.outerChance)
            ).apply(inst, AppleOuterFlowersDecorator::new));

    protected final int minExposedFaces;
    protected final float outerChance;// 外縁を花にする確率（0.0〜1.0）

    public AppleOuterFlowersDecorator(int minExposedFaces, float outerChance) {
        this.minExposedFaces = Math.max(1, minExposedFaces);
        this.outerChance = Math.max(0f, Math.min(1f, outerChance));
    }

    // 既存コード互換（デフォ確率=0.5）
    public AppleOuterFlowersDecorator(int minExposedFaces) {
        this(minExposedFaces, 0.3f);//ここで調整する
    }




    @Override
    protected TreeDecoratorType<?> type() {
        return ModTreeDecorators.APPLE_OUTER_FLOWERS.get();
    }

    @Override
    public void place(Context ctx) {
        final LevelSimulatedReader level = ctx.level();
        final Set<BlockPos> logs   = new HashSet<>(ctx.logs());
        final Set<BlockPos> leaves = new HashSet<>(ctx.leaves());

        final BlockState flower = ModBlocks.APPLE_FLOWER_LEAVES.get().defaultBlockState();

        for (BlockPos pos : leaves) {
            // 置換対象は APPLE_LEAVES のみ（既に花葉は触らない）
            boolean isAppleLeaves = level.isStateAtPosition(pos, s -> s.is(ModBlocks.APPLE_LEAVES.get()));
            if (!isAppleLeaves) continue;

            int exposed = 0;
            boolean nearLog = false;

            // 外気判定 + 原木隣接判定
            for (Direction d : Direction.values()) {
                BlockPos n = pos.relative(d);

                if (level.isStateAtPosition(n, s -> s.isAir() || !s.getFluidState().isEmpty())) {
                    exposed++;
                }
                if (logs.contains(n) || level.isStateAtPosition(n, s -> s.is(BlockTags.LOGS))) {
                    nearLog = true;
                }
            }

            // 外縁（十分露出）かつ原木に近接していない → 確率で花化
            if (!nearLog && exposed >= this.minExposedFaces) {
                // ばらけさせるため位置ベースの乱数にする（同じツリーでも自然に散る）
                RandomSource rand = RandomSource.create(pos.asLong() ^ ctx.random().nextLong());
                if (rand.nextFloat() < this.outerChance) {
                    ctx.setBlock(pos, flower);
                }
            }
        }
    }

}
