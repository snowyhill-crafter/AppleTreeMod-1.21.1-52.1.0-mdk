package com.snowyhill.appletreemod.datagen.server;

import com.snowyhill.appletreemod.AppleTreeMod;
import com.snowyhill.appletreemod.tag.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, AppleTreeMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        //MODで作成したタグ
        this.copy(ModTags.Blocks.APPLE_LOG,
                ModTags.Items.APPLE_LOG);
        this.copy(ModTags.Blocks.DARK_APPLE_LOG,
                ModTags.Items.DARK_APPLE_LOG);
        //バニラタグ
        this.copy(BlockTags.LOGS, ItemTags.LOGS);
        this.copy(BlockTags.LEAVES, ItemTags.LEAVES);
        this.copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
        //作業台などの「木材」を使うレシピに適用
        this.copy(BlockTags.PLANKS, ItemTags.PLANKS);
        this.copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
    }



}