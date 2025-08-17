package com.snowyhill.appletreemod.datagen.client;

import com.snowyhill.appletreemod.AppleTreeMod;
import com.snowyhill.appletreemod.registry.ModBlocks;
import com.snowyhill.appletreemod.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.Locale;

public class ENUSLanguageProvider extends LanguageProvider {
    public ENUSLanguageProvider(PackOutput output) {
        super(output, AppleTreeMod.MOD_ID, Locale.US.toString().toLowerCase());
    }

    @Override
    protected void addTranslations() {
        addBlock(ModBlocks.APPLE_SAPLING, "Apple Sapling");
        addBlock(ModBlocks.POTTED_APPLE_SAPLING, "Potted Apple Sapling");
        addBlock(ModBlocks.APPLE_LOG, "Apple Log");
        addBlock(ModBlocks.STRIPPED_APPLE_LOG, "Stripped Apple Log");
        addBlock(ModBlocks.APPLE_WOOD, "Apple Wood");
        addBlock(ModBlocks.STRIPPED_APPLE_WOOD, "Stripped Apple Wood");
        addBlock(ModBlocks.APPLE_LEAVES, "Apple Leaves");
        addBlock(ModBlocks.APPLE_FLOWER_LEAVES, "flowering apple leaves");
        addBlock(ModBlocks.ORNAMENTAL_APPLE_FLOWER_LEAVES, "Ornamental flowering apple leaves");
        addBlock(ModBlocks.ORNAMENTAL_APPLE_UNRIPE_LEAVES, "Ornamental unripe apple leaves");
        addBlock(ModBlocks.ORNAMENTAL_APPLE_FRUIT_LEAVES, "Ornamental ripe apple leaves");

        addBlock(ModBlocks.APPLE_PLANKS, "Apple Planks");
        addBlock(ModBlocks.APPLE_SLAB, "Apple Slab");
        addBlock(ModBlocks.APPLE_STAIRS, "Apple Stairs");
        addBlock(ModBlocks.APPLE_FENCE, "Apple Fence");
        addBlock(ModBlocks.APPLE_FENCE_GATE, "Apple Fence Gate");
        addBlock(ModBlocks.APPLE_DOOR, "Apple Door");
        addBlock(ModBlocks.APPLE_TRAPDOOR, "Apple Trapdoor");
        addBlock(ModBlocks.APPLE_BUTTON, "Apple Button");
        addBlock(ModBlocks.APPLE_PRESSURE_PLATE, "Apple Pressure Plate");

        addBlock(ModBlocks.APPLE_PIE, "Apple Pie");

        add("creativetabs.Mod_tab", "appletreemod");


    }
    
    
}