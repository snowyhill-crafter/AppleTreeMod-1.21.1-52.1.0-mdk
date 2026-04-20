package com.snowyhill.appletreemod.setup;

import com.snowyhill.appletreemod.AppleTreeMod;
import com.snowyhill.appletreemod.registry.ModBlocks;
import com.snowyhill.appletreemod.registry.ModItems;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = AppleTreeMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonSetup {

    @SubscribeEvent
    public static void onCommonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            FlowerPotBlock pot = (FlowerPotBlock) Blocks.FLOWER_POT;

            pot.addPlant(ModBlocks.APPLE_SAPLING.getId(), ModBlocks.POTTED_APPLE_SAPLING);
            pot.addPlant(ModBlocks.DARK_APPLE_SAPLING.getId(), ModBlocks.POTTED_DARK_APPLE_SAPLING);

            ComposterBlock.COMPOSTABLES.put(ModItems.APPLE_PIE_ITEM.get(), 1.0F);
            ComposterBlock.COMPOSTABLES.put(ModItems.DARK_APPLE_PIE_ITEM.get(), 1.0F);
            ComposterBlock.COMPOSTABLES.put(ModItems.DARK_APPLE.get(), 0.65F);
        });
    }
}