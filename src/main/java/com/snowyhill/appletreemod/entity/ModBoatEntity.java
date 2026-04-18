package com.snowyhill.appletreemod.entity;

import com.snowyhill.appletreemod.registry.ModBlocks;
import com.snowyhill.appletreemod.registry.ModEntities;
import com.snowyhill.appletreemod.registry.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.ByIdMap;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.function.IntFunction;

public class ModBoatEntity extends Boat {
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(ModBoatEntity.class, EntityDataSerializers.INT);

    public ModBoatEntity(EntityType<? extends Boat> type, Level level) {
        super(type, level);
    }

    @Override
    public Item getDropItem() {
        return switch (getModVariant()) {
            case APPLE -> ModItems.APPLE_BOAT.get();
            case DARK_APPLE -> ModItems.DARK_APPLE_BOAT.get();
        };
    }

    public void setVariant(Type type) {
        this.entityData.set(DATA_ID_TYPE, type.ordinal());
    }

    public Type getModVariant() {
        return Type.byId(this.entityData.get(DATA_ID_TYPE));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_ID_TYPE, Type.APPLE.ordinal());
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putString("Type", getModVariant().getName());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        if (tag.contains("Type", 8)) {
            this.setVariant(Type.byName(tag.getString("Type")));
        }
    }

    public enum Type {
        APPLE("apple"),
        DARK_APPLE("dark_apple");

        private final String name;
        public static final IntFunction<Type> BY_ID = ByIdMap.continuous(Type::ordinal, values(), ByIdMap.OutOfBoundsStrategy.ZERO);

        Type(String name) {
            this.name = name;
        }

        public String getName() { return this.name; }

        // ここで .get() を呼ぶことで、ブロック登録完了後にアクセスすることを保証する
        public Block getPlanks() {
            return (this == DARK_APPLE) ? ModBlocks.DARK_APPLE_PLANKS.get() : ModBlocks.APPLE_PLANKS.get();
        }

        public static Type byId(int id) { return BY_ID.apply(id); }

        public static Type byName(String name) {
            for (Type type : values()) {
                if (type.name.equals(name)) return type;
            }
            return APPLE;
        }
    }
}