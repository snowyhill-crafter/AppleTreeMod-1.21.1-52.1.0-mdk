package com.snowyhill.appletreemod.datagen.server;

import com.snowyhill.appletreemod.AppleTreeMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class ModBiomeTags {

    public static final TagKey<Biome> APPLE_TREE_SPAWNABLE =
            create("apple_tree_spawnable");

    public static final TagKey<Biome> DARK_APPLE_TREE_SPAWNABLE =
            create("dark_apple_tree_spawnable");

    private static TagKey<Biome> create(String name) {
        return TagKey.create(
                Registries.BIOME,
                ResourceLocation.fromNamespaceAndPath(AppleTreeMod.MOD_ID, name)
        );
    }
}