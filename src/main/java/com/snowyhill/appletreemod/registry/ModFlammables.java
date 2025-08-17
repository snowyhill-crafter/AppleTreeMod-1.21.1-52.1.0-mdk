package com.snowyhill.appletreemod.registry;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;

public final class ModFlammables {
    private ModFlammables() {}

    public static void register() {
        FireBlock fire = (FireBlock) Blocks.FIRE;

        // 板材系
        fire.setFlammable(ModBlocks.APPLE_PLANKS.get(), 5, 20);
        fire.setFlammable(ModBlocks.APPLE_SLAB.get(), 5, 20);
        fire.setFlammable(ModBlocks.APPLE_STAIRS.get(), 5, 20);
        fire.setFlammable(ModBlocks.APPLE_FENCE.get(), 5, 20);
        fire.setFlammable(ModBlocks.APPLE_FENCE_GATE.get(), 5, 20);
        fire.setFlammable(ModBlocks.APPLE_BUTTON.get(), 5, 20);
        fire.setFlammable(ModBlocks.APPLE_PRESSURE_PLATE.get(), 5, 20);
        fire.setFlammable(ModBlocks.APPLE_DOOR.get(), 5, 20);
        fire.setFlammable(ModBlocks.APPLE_TRAPDOOR.get(), 5, 20);
    }
}