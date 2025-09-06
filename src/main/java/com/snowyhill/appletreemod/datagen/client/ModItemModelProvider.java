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
     //ここにブロックじゃないアイテムを登録
        //ここにアイテムを追加して自動生成させる

        itemWithBlock(ModBlocks.APPLE_SLAB);
        itemWithBlock(ModBlocks.APPLE_STAIRS);
        itemWithBlock(ModBlocks.APPLE_FENCE_GATE);
        itemWithBlock(ModBlocks.APPLE_PRESSURE_PLATE);
        basicItem(ModBlocks.APPLE_DOOR.get().asItem());
        trapdoor(ModBlocks.APPLE_TRAPDOOR);
        fence(ModBlocks.APPLE_FENCE,
                ModBlocks.APPLE_PLANKS);
        button(ModBlocks.APPLE_BUTTON,
                ModBlocks.APPLE_PLANKS);

        sapling(ModBlocks.APPLE_SAPLING);
        sapling(ModBlocks.DARK_APPLE_SAPLING);
        itemWithBlock(ModBlocks.DARK_APPLE_SLAB);
        itemWithBlock(ModBlocks.DARK_APPLE_STAIRS);
        itemWithBlock(ModBlocks.DARK_APPLE_FENCE_GATE);
        itemWithBlock(ModBlocks.DARK_APPLE_PRESSURE_PLATE);
        basicItem(ModBlocks.DARK_APPLE_DOOR.get().asItem());
        trapdoor(ModBlocks.DARK_APPLE_TRAPDOOR);
        fence(ModBlocks.DARK_APPLE_FENCE,
                ModBlocks.DARK_APPLE_PLANKS);
        button(ModBlocks.DARK_APPLE_BUTTON,
                ModBlocks.DARK_APPLE_PLANKS);



        basicItem(ModItems.DARK_APPLE.get());




    }

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
    public void fence(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(AppleTreeMod.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }
    public void button(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(AppleTreeMod.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }
    private void sapling(RegistryObject<Block> sapling) {
        String name = ForgeRegistries.BLOCKS.getKey(sapling.get()).getPath();
        // 親は "minecraft:item/generated"
        withExistingParent(name, mcLoc("item/generated"))
                // 苗木の見た目は block テクスチャ1枚
                .texture("layer0", modLoc("block/" + name));
    }


}