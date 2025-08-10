package com.snowyhill.appletreemod.datagen.server;

import com.snowyhill.appletreemod.registry.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModLootTables {
    public static LootTableProvider create(PackOutput packOutput,
                                           CompletableFuture<HolderLookup.Provider> lookUpProvider) {
        return new LootTableProvider(packOutput, Set.of(), List.of(



        ), lookUpProvider);
    }
}