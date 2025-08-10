package com.snowyhill.appletreemod.datagen;


import com.snowyhill.appletreemod.AppleTreeMod;
import com.snowyhill.appletreemod.datagen.client.*;
import com.snowyhill.appletreemod.datagen.server.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = AppleTreeMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)

public class ModDataGenerator {
        @SubscribeEvent
        public static void gatherData(GatherDataEvent event) {
            DataGenerator generator = event.getGenerator();
            PackOutput packOutput = generator.getPackOutput();
            ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
            CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

            // アイテムモデル
            generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, existingFileHelper));
            // ブロックモデル
            generator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput, existingFileHelper));
            // 言語ファイル
            generator.addProvider(event.includeClient(), new JAJPLanguageProvider(packOutput));
            generator.addProvider(event.includeClient(), new ENUSLanguageProvider(packOutput));
            // サウンド
            generator.addProvider(event.includeClient(), new ModSoundProvider(packOutput, existingFileHelper));

            // ブロックタグ
            ModBlockTagsProvider blockTagsProvider = generator.addProvider(event.includeServer(),
                    new ModBlockTagsProvider(packOutput, lookupProvider, existingFileHelper));
            // アイテムタグ
            generator.addProvider(event.includeServer(),
                    new ModItemTagsProvider(packOutput, lookupProvider,
                            blockTagsProvider.contentsGetter(), existingFileHelper));
            // ルートテーブル
            generator.addProvider(event.includeServer(), ModLootTables.create(packOutput, lookupProvider));
            // レシピ
            generator.addProvider(event.includeServer(), new ModRecipeProvider(packOutput, lookupProvider));
            // 進捗
            generator.addProvider(event.includeServer(), new ModAdvancementsProvider(packOutput, lookupProvider, existingFileHelper));
            // データパック
            generator.addProvider(event.includeServer(), new ModDatapacksProvider(packOutput, lookupProvider));
            // GlobalLootModifier
            generator.addProvider(event.includeServer(),
                    new ModGlobalLootModifierProvider(packOutput, lookupProvider));
        }
    }

