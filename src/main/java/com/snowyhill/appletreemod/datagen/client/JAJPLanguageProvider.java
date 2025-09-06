package com.snowyhill.appletreemod.datagen.client;

import com.snowyhill.appletreemod.AppleTreeMod;
import com.snowyhill.appletreemod.registry.ModBlocks;
import com.snowyhill.appletreemod.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.Locale;

public class JAJPLanguageProvider extends LanguageProvider {
    public JAJPLanguageProvider(PackOutput output) {
        super(output, AppleTreeMod.MOD_ID, Locale.JAPAN.toString().toLowerCase());
    }

    @Override
    protected void addTranslations() {
        addBlock(ModBlocks.APPLE_SAPLING, "リンゴの苗木");
        addBlock(ModBlocks.POTTED_APPLE_SAPLING, "植木鉢に植えたリンゴの苗木");
        addBlock(ModBlocks.APPLE_LOG, "リンゴの原木");
        addBlock(ModBlocks.STRIPPED_APPLE_LOG, "樹皮を剥いだリンゴの原木");
        addBlock(ModBlocks.APPLE_WOOD, "リンゴの木");
        addBlock(ModBlocks.STRIPPED_APPLE_WOOD, "樹皮を剥いだリンゴの木");
        addBlock(ModBlocks.APPLE_LEAVES, "リンゴの葉");
        addBlock(ModBlocks.APPLE_FLOWER_LEAVES, "花の咲いたリンゴの葉");
        addBlock(ModBlocks.ORNAMENTAL_APPLE_FLOWER_LEAVES, "装飾用の花の咲いたリンゴの葉");
        addBlock(ModBlocks.ORNAMENTAL_APPLE_UNRIPE_LEAVES, "装飾用の未熟なリンゴの葉");
        addBlock(ModBlocks.ORNAMENTAL_APPLE_FRUIT_LEAVES, "装飾用のリンゴの実った葉");

        addBlock(ModBlocks.APPLE_PLANKS, "リンゴの板材");
        addBlock(ModBlocks.APPLE_SLAB, "リンゴのハーフブロック");
        addBlock(ModBlocks.APPLE_STAIRS, "リンゴの階段");
        addBlock(ModBlocks.APPLE_FENCE, "リンゴのフェンス");
        addBlock(ModBlocks.APPLE_FENCE_GATE, "リンゴのフェンスゲート");
        addBlock(ModBlocks.APPLE_DOOR, "リンゴのドア");
        addBlock(ModBlocks.APPLE_TRAPDOOR, "リンゴのトラップドア");
        addBlock(ModBlocks.APPLE_BUTTON, "リンゴのボタン");
        addBlock(ModBlocks.APPLE_PRESSURE_PLATE, "リンゴの感圧板");

        addBlock(ModBlocks.APPLE_PIE, "アップルパイ");

        addBlock(ModBlocks.DARK_APPLE_SAPLING, "ダークアップルの苗木");
        addBlock(ModBlocks.POTTED_DARK_APPLE_SAPLING, "植木鉢に植えたダークアップルの苗木");
        addBlock(ModBlocks.DARK_APPLE_LOG, "ダークアップルの原木");
        addBlock(ModBlocks.STRIPPED_DARK_APPLE_LOG, "樹皮を剥いだダークアップルの原木");
        addBlock(ModBlocks.DARK_APPLE_WOOD, "ダークアップルの木");
        addBlock(ModBlocks.STRIPPED_DARK_APPLE_WOOD, "樹皮を剥いだダークアップルの木");
        addBlock(ModBlocks.DARK_APPLE_LEAVES, "ダークアップルの葉");
        addBlock(ModBlocks.DARK_APPLE_FLOWER_LEAVES, "花の咲いたダークアップルの葉");
        addBlock(ModBlocks.ORNAMENTAL_DARK_APPLE_FLOWER_LEAVES, "装飾用の花の咲いたダークアップルの葉");
        addBlock(ModBlocks.ORNAMENTAL_DARK_APPLE_UNRIPE_LEAVES, "装飾用の未熟なダークアップルの葉");
        addBlock(ModBlocks.ORNAMENTAL_DARK_APPLE_FRUIT_LEAVES, "装飾用のダークアップルの実った葉");

        addBlock(ModBlocks.DARK_APPLE_PLANKS, "ダークアップルの板材");
        addBlock(ModBlocks.DARK_APPLE_SLAB, "ダークアップルのハーフブロック");
        addBlock(ModBlocks.DARK_APPLE_STAIRS, "ダークアップルの階段");
        addBlock(ModBlocks.DARK_APPLE_FENCE, "ダークアップルのフェンス");
        addBlock(ModBlocks.DARK_APPLE_FENCE_GATE, "ダークアップルのフェンスゲート");
        addBlock(ModBlocks.DARK_APPLE_DOOR, "ダークアップルのドア");
        addBlock(ModBlocks.DARK_APPLE_TRAPDOOR, "ダークアップルのトラップドア");
        addBlock(ModBlocks.DARK_APPLE_BUTTON, "ダークアップルのボタン");
        addBlock(ModBlocks.DARK_APPLE_PRESSURE_PLATE, "ダークアップルの感圧板");
        addItem(ModItems.DARK_APPLE, "ダークアップル");
        addBlock(ModBlocks.DARK_APPLE_PIE, "ダークアップルパイ");


        add("creativetabs.Mod_tab", "リンゴの木MOD");


    }
}