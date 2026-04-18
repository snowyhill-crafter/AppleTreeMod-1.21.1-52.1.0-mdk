package com.snowyhill.appletreemod.item;

import com.snowyhill.appletreemod.entity.ModBoatEntity;
import com.snowyhill.appletreemod.entity.ModChestBoatEntity;
import com.snowyhill.appletreemod.registry.ModEntities;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.function.Predicate;

public class ModBoatItem extends Item {
    private static final Predicate<Entity> ENTITY_PREDICATE = EntitySelector.NO_SPECTATORS.and(Entity::isPickable);
    private final boolean isChest;
    private final ModBoatEntity.Type type;

    // コンストラクタを (boolean, Type, Properties) に変更
    public ModBoatItem(boolean isChest, ModBoatEntity.Type type, Item.Properties properties) {
        super(properties);
        this.isChest = isChest;
        this.type = type;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        HitResult hitresult = getPlayerPOVHitResult(pLevel, pPlayer, net.minecraft.world.level.ClipContext.Fluid.ANY);

        if (hitresult.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(itemstack);
        } else {
            Vec3 vec3 = pPlayer.getViewVector(1.0F);
            List<Entity> list = pLevel.getEntities(pPlayer, pPlayer.getBoundingBox().expandTowards(vec3.scale(5.0D)).inflate(1.0D), ENTITY_PREDICATE);
            if (!list.isEmpty()) {
                Vec3 vec31 = pPlayer.getEyePosition();
                for(Entity entity : list) {
                    AABB aabb = entity.getBoundingBox().inflate((double)entity.getPickRadius());
                    if (aabb.contains(vec31)) {
                        return InteractionResultHolder.pass(itemstack);
                    }
                }
            }

            if (hitresult.getType() == HitResult.Type.BLOCK) {
                // ★ここがポイント：使用される瞬間に ModEntities.xxx.get() を呼ぶ
                Boat boat = this.getBoat(pLevel, hitresult);

                // ボートの種類（ apple / dark_apple ）を設定
                if (boat instanceof ModBoatEntity modBoat) {
                    modBoat.setVariant(this.type);
                } else if (boat instanceof ModChestBoatEntity modChestBoat) {
                    modChestBoat.setVariant(this.type);
                }

                boat.setYRot(pPlayer.getYRot());
                if (!pLevel.noCollision(boat, boat.getBoundingBox())) {
                    return InteractionResultHolder.fail(itemstack);
                } else {
                    if (!pLevel.isClientSide) {
                        pLevel.addFreshEntity(boat);
                        pLevel.gameEvent(pPlayer, GameEvent.ENTITY_PLACE, hitresult.getLocation());
                        itemstack.consume(1, pPlayer);
                    }

                    pPlayer.awardStat(Stats.ITEM_USED.get(this));
                    return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
                }
            } else {
                return InteractionResultHolder.pass(itemstack);
            }
        }
    }

    // isChest のフラグに応じて生成するエンティティを切り替えるメソッド
    private Boat getBoat(Level level, HitResult hitResult) {
        Boat boat = this.isChest ?
                new ModChestBoatEntity(ModEntities.MOD_CHEST_BOAT.get(), level) :
                new ModBoatEntity(ModEntities.MOD_BOAT.get(), level);

        boat.setPos(hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z);
        return boat;
    }
}