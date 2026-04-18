package com.snowyhill.appletreemod.registry;

import com.snowyhill.appletreemod.AppleTreeMod;
import com.snowyhill.appletreemod.blockentity.AppleHangingSignBlockEntity;
import com.snowyhill.appletreemod.blockentity.AppleSignBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, AppleTreeMod.MOD_ID);

    /* ========= 通常の看板 ========= */

    public static final RegistryObject<BlockEntityType<AppleSignBlockEntity>> SIGN =
            BLOCK_ENTITIES.register("apple_sign",
                    () -> BlockEntityType.Builder.of(
                            AppleSignBlockEntity::new,
                            ModBlocks.APPLE_SIGN.get(),
                            ModBlocks.APPLE_WALL_SIGN.get(),
                            ModBlocks.DARK_APPLE_SIGN.get(),      // ★追加
                            ModBlocks.DARK_APPLE_WALL_SIGN.get()
                    ).build(null));

    public static final RegistryObject<BlockEntityType<AppleHangingSignBlockEntity>> HANGING_SIGN =
            BLOCK_ENTITIES.register("apple_hanging_sign",
                    () -> BlockEntityType.Builder.of(
                            AppleHangingSignBlockEntity::new,
                            ModBlocks.APPLE_HANGING_SIGN.get(),
                            ModBlocks.APPLE_WALL_HANGING_SIGN.get(),
                            ModBlocks.DARK_APPLE_HANGING_SIGN.get(),
                            ModBlocks.DARK_APPLE_WALL_HANGING_SIGN.get()
                    ).build(null));




    /* ========= register ========= */

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
