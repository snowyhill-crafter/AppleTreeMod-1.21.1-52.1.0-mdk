package com.snowyhill.appletreemod.registry;

import com.snowyhill.appletreemod.AppleTreeMod;
import com.snowyhill.appletreemod.entity.ModBoatEntity;
import com.snowyhill.appletreemod.entity.ModChestBoatEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, AppleTreeMod.MOD_ID);

    // .build("") の中身を空にするのが 1.21.1 以降の安定した書き方です
    public static final RegistryObject<EntityType<ModBoatEntity>> MOD_BOAT =
            ENTITY_TYPES.register("mod_boat", () -> EntityType.Builder.<ModBoatEntity>of(ModBoatEntity::new, MobCategory.MISC)
                    .sized(1.375F, 0.5625F).build(""));

    public static final RegistryObject<EntityType<ModChestBoatEntity>> MOD_CHEST_BOAT =
            ENTITY_TYPES.register("mod_chest_boat", () -> EntityType.Builder.<ModChestBoatEntity>of(ModChestBoatEntity::new, MobCategory.MISC)
                    .sized(1.375F, 0.5625F).build(""));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}