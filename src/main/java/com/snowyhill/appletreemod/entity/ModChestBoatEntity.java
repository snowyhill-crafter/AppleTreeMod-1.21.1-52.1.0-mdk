package com.snowyhill.appletreemod.entity;

import com.snowyhill.appletreemod.registry.ModEntities;
import com.snowyhill.appletreemod.registry.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class ModChestBoatEntity extends ChestBoat {
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(ModChestBoatEntity.class, EntityDataSerializers.INT);

    public ModChestBoatEntity(EntityType<? extends ChestBoat> type, Level level) {
        super(type, level);
    }

    // 基本的に ModBoatItem 側で EntityType.create を使うため、このコンストラクタは補助用です
    public ModChestBoatEntity(Level level, double x, double y, double z) {
        this(ModEntities.MOD_CHEST_BOAT.get(), level);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    @Override
    public Item getDropItem() {
        return switch (getModVariant()) {
            case APPLE -> ModItems.APPLE_CHEST_BOAT.get();
            case DARK_APPLE -> ModItems.DARK_APPLE_CHEST_BOAT.get();
        };
    }

    public void setVariant(ModBoatEntity.Type type) {
        this.entityData.set(DATA_ID_TYPE, type.ordinal());
    }

    public ModBoatEntity.Type getModVariant() {
        return ModBoatEntity.Type.byId(this.entityData.get(DATA_ID_TYPE));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_ID_TYPE, ModBoatEntity.Type.APPLE.ordinal());
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putString("Type", getModVariant().getName());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("Type", 8)) {
            this.setVariant(ModBoatEntity.Type.byName(tag.getString("Type")));
        }
    }
}