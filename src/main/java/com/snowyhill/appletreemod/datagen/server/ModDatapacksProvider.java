package com.snowyhill.appletreemod.datagen.server;

import com.snowyhill.appletreemod.AppleTreeMod;
import com.snowyhill.appletreemod.worldgen.biome.ModBiomeModifiers;
import com.snowyhill.appletreemod.worldgen.features.ModFeatures;
import com.snowyhill.appletreemod.worldgen.placement.ModPlacement;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDatapacksProvider extends DatapackBuiltinEntriesProvider {

    public ModDatapacksProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(
                output,
                lookup,
                // ★ ここで bootstrap を紐づける（これが無いと何も生成されない）
                new RegistrySetBuilder()
                        .add(Registries.CONFIGURED_FEATURE, ModFeatures::bootstrap)
                        .add(Registries.PLACED_FEATURE,     ModPlacement::bootstrap)
                        .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap),
                Set.of(AppleTreeMod.MOD_ID)
        );
    }
}