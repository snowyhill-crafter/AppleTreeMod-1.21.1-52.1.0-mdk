package com.snowyhill.appletreemod.datagen.server;

import com.snowyhill.appletreemod.AppleTreeMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ModBiomeTagsProvider extends TagsProvider<Biome> {

    public ModBiomeTagsProvider(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> lookupProvider,
            ExistingFileHelper existingFileHelper
    ) {
        super(output, Registries.BIOME, lookupProvider, AppleTreeMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ModBiomeTags.APPLE_TREE_SPAWNABLE)
                .add(biome("forest"))
                .add(biome("flower_forest"))
                .add(biome("birch_forest"))
                .add(biome("old_growth_birch_forest"))
                .add(biome("taiga"))
                .add(biome("snowy_taiga"));

        this.tag(ModBiomeTags.DARK_APPLE_TREE_SPAWNABLE)
                .add(biome("dark_forest"));
    }

    private static ResourceKey<Biome> biome(String name) {
        return ResourceKey.create(
                Registries.BIOME,
                ResourceLocation.withDefaultNamespace(name)
        );
    }
}