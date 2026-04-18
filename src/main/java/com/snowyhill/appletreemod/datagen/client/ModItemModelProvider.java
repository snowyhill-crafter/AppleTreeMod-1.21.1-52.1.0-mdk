package com.snowyhill.appletreemod.datagen.client;

import com.snowyhill.appletreemod.AppleTreeMod;
import com.snowyhill.appletreemod.registry.ModBlocks;
import com.snowyhill.appletreemod.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AppleTreeMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // --- ブロックベースのアイテム ---
        itemWithBlock(ModBlocks.APPLE_SLAB);
        itemWithBlock(ModBlocks.APPLE_STAIRS);
        itemWithBlock(ModBlocks.APPLE_FENCE_GATE);
        itemWithBlock(ModBlocks.APPLE_PRESSURE_PLATE);
        basicItem(ModBlocks.APPLE_DOOR.get().asItem());
        trapdoor(ModBlocks.APPLE_TRAPDOOR);
        fence(ModBlocks.APPLE_FENCE, ModBlocks.APPLE_PLANKS);
        button(ModBlocks.APPLE_BUTTON, ModBlocks.APPLE_PLANKS);

        itemWithBlock(ModBlocks.DARK_APPLE_SLAB);
        itemWithBlock(ModBlocks.DARK_APPLE_STAIRS);
        itemWithBlock(ModBlocks.DARK_APPLE_FENCE_GATE);
        itemWithBlock(ModBlocks.DARK_APPLE_PRESSURE_PLATE);
        basicItem(ModBlocks.DARK_APPLE_DOOR.get().asItem());
        trapdoor(ModBlocks.DARK_APPLE_TRAPDOOR);
        fence(ModBlocks.DARK_APPLE_FENCE, ModBlocks.DARK_APPLE_PLANKS);
        button(ModBlocks.DARK_APPLE_BUTTON, ModBlocks.DARK_APPLE_PLANKS);

        // --- 食べ物・素材 ---
        basicItem(ModItems.DARK_APPLE.get());
        basicItem(ModItems.APPLE_PIE_ITEM.get());
        basicItem(ModItems.DARK_APPLE_PIE_ITEM.get());

        // --- 苗木 ---
        sapling(ModBlocks.APPLE_SAPLING);
        sapling(ModBlocks.DARK_APPLE_SAPLING);

        // --- ボート (1.21.1 仕様) ---
        basicItem(ModItems.APPLE_BOAT.get());
        basicItem(ModItems.APPLE_CHEST_BOAT.get());
        basicItem(ModItems.DARK_APPLE_BOAT.get());
        basicItem(ModItems.DARK_APPLE_CHEST_BOAT.get());

        // --- 看板 ---
        basicItem(ModItems.APPLE_SIGN_ITEM.get());
        hangingSignItem(ModItems.APPLE_HANGING_SIGN_ITEM);
        basicItem(ModItems.DARK_APPLE_SIGN_ITEM.get());
        hangingSignItem(ModItems.DARK_APPLE_HANGING_SIGN_ITEM);
    }

    // --- ヘルパーメソッド群 ---

    public void itemWithBlock(RegistryObject<Block> block) {
        this.getBuilder(ForgeRegistries.BLOCKS.getKey(block.get()).getPath())
                .parent(new ModelFile.UncheckedModelFile(
                        AppleTreeMod.MOD_ID + ":block/" +
                                ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }

    public void trapdoor(RegistryObject<Block> block) {
        this.getBuilder(ForgeRegistries.BLOCKS.getKey(block.get()).getPath())
                .parent(new ModelFile.UncheckedModelFile(
                        AppleTreeMod.MOD_ID + ":block/" +
                                ForgeRegistries.BLOCKS.getKey(block.get()).getPath() + "_bottom"));
    }

    private void fence(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture", ResourceLocation.fromNamespaceAndPath(AppleTreeMod.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    private void button(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture", ResourceLocation.fromNamespaceAndPath(AppleTreeMod.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    private void sapling(RegistryObject<Block> sapling) {
        String name = ForgeRegistries.BLOCKS.getKey(sapling.get()).getPath();
        withExistingParent(name, mcLoc("item/generated"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(AppleTreeMod.MOD_ID, "block/" + name));
    }

    private void hangingSignItem(RegistryObject<?> item) {
        String name = ForgeRegistries.ITEMS.getKey((net.minecraft.world.item.Item)item.get()).getPath();
        withExistingParent(name, mcLoc("item/generated"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(AppleTreeMod.MOD_ID, "item/" + name));
    }
}