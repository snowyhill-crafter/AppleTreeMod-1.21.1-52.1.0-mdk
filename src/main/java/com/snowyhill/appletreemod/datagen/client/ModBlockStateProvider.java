package com.snowyhill.appletreemod.datagen.client;

import com.snowyhill.appletreemod.AppleTreeMod;
import com.snowyhill.appletreemod.registry.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, AppleTreeMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // --- APPLE ---
        logBlock((RotatedPillarBlock) ModBlocks.APPLE_LOG.get());
        item(ModBlocks.APPLE_LOG);

        logBlock((RotatedPillarBlock) ModBlocks.STRIPPED_APPLE_LOG.get());
        item(ModBlocks.STRIPPED_APPLE_LOG);

        axisBlock((RotatedPillarBlock) ModBlocks.APPLE_WOOD.get(),
                blockTexture(ModBlocks.APPLE_LOG.get()), blockTexture(ModBlocks.APPLE_LOG.get()));
        item(ModBlocks.APPLE_WOOD);

        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_APPLE_WOOD.get(),
                blockTexture(ModBlocks.STRIPPED_APPLE_LOG.get()), blockTexture(ModBlocks.STRIPPED_APPLE_LOG.get()));
        item(ModBlocks.STRIPPED_APPLE_WOOD);

        simpleBlockWithItem(ModBlocks.APPLE_PLANKS);
        sapling(ModBlocks.APPLE_SAPLING);

        stairsBlock((StairBlock) ModBlocks.APPLE_STAIRS.get(), blockTexture(ModBlocks.APPLE_PLANKS.get()));


        slabBlock((SlabBlock) ModBlocks.APPLE_SLAB.get(),
                // 二つ重ねたときのテクスチャ
                blockTexture(ModBlocks.APPLE_PLANKS.get()),
                // 単体のテクスチャ
                blockTexture(ModBlocks.APPLE_PLANKS.get()));

        fenceBlock((FenceBlock) ModBlocks.APPLE_FENCE.get(), blockTexture(ModBlocks.APPLE_PLANKS.get()));
        fenceGateBlock((FenceGateBlock) ModBlocks.APPLE_FENCE_GATE.get(), blockTexture(ModBlocks.APPLE_PLANKS.get()));
        pressurePlateBlock((PressurePlateBlock) ModBlocks.APPLE_PRESSURE_PLATE.get(), blockTexture(ModBlocks.APPLE_PLANKS.get()));
        buttonBlock((ButtonBlock) ModBlocks.APPLE_BUTTON.get(), blockTexture(ModBlocks.APPLE_PLANKS.get()));

        doorBlockWithRenderType((DoorBlock) ModBlocks.APPLE_DOOR.get(),
                modLoc("block/apple_door_bottom"), modLoc("block/apple_door_top"), "cutout");
        trapdoorBlockWithRenderType((TrapDoorBlock)
                        ModBlocks.APPLE_TRAPDOOR.get(),
                modLoc("block/apple_trapdoor"), true,
                "cutout");

        signBlock((StandingSignBlock) ModBlocks.APPLE_SIGN.get(), (WallSignBlock) ModBlocks.APPLE_WALL_SIGN.get(),
                blockTexture(ModBlocks.APPLE_PLANKS.get()));

        hangingSignBlock(ModBlocks.APPLE_HANGING_SIGN.get(), ModBlocks.APPLE_WALL_HANGING_SIGN.get(),
                blockTexture(ModBlocks.APPLE_PLANKS.get()));

        simpleLeaves(ModBlocks.APPLE_LEAVES);

        // --- DARK APPLE ---
        logBlock((RotatedPillarBlock) ModBlocks.DARK_APPLE_LOG.get());
        item(ModBlocks.DARK_APPLE_LOG);

        logBlock((RotatedPillarBlock) ModBlocks.STRIPPED_DARK_APPLE_LOG.get());
        item(ModBlocks.STRIPPED_DARK_APPLE_LOG);

        axisBlock((RotatedPillarBlock) ModBlocks.DARK_APPLE_WOOD.get(),
                blockTexture(ModBlocks.DARK_APPLE_LOG.get()), blockTexture(ModBlocks.DARK_APPLE_LOG.get()));
        item(ModBlocks.DARK_APPLE_WOOD);

        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_DARK_APPLE_WOOD.get(),
                blockTexture(ModBlocks.STRIPPED_DARK_APPLE_LOG.get()), blockTexture(ModBlocks.STRIPPED_DARK_APPLE_LOG.get()));
        item(ModBlocks.STRIPPED_DARK_APPLE_WOOD);

        simpleBlockWithItem(ModBlocks.DARK_APPLE_PLANKS);
        sapling(ModBlocks.DARK_APPLE_SAPLING);

        stairsBlock((StairBlock) ModBlocks.DARK_APPLE_STAIRS.get(), blockTexture(ModBlocks.DARK_APPLE_PLANKS.get()));
        slabBlock((SlabBlock) ModBlocks.DARK_APPLE_SLAB.get(),
                // 二つ重ねたときのテクスチャ
                blockTexture(ModBlocks.DARK_APPLE_PLANKS.get()),
                // 単体のテクスチャ
                blockTexture(ModBlocks.DARK_APPLE_PLANKS.get()));
        fenceBlock((FenceBlock) ModBlocks.DARK_APPLE_FENCE.get(), blockTexture(ModBlocks.DARK_APPLE_PLANKS.get()));
        fenceGateBlock((FenceGateBlock) ModBlocks.DARK_APPLE_FENCE_GATE.get(), blockTexture(ModBlocks.DARK_APPLE_PLANKS.get()));
        pressurePlateBlock((PressurePlateBlock) ModBlocks.DARK_APPLE_PRESSURE_PLATE.get(), blockTexture(ModBlocks.DARK_APPLE_PLANKS.get()));
        buttonBlock((ButtonBlock) ModBlocks.DARK_APPLE_BUTTON.get(), blockTexture(ModBlocks.DARK_APPLE_PLANKS.get()));

        doorBlockWithRenderType((DoorBlock) ModBlocks.DARK_APPLE_DOOR.get(),
                modLoc("block/dark_apple_door_bottom"), modLoc("block/dark_apple_door_top"), "cutout");
        trapdoorBlockWithRenderType((TrapDoorBlock)
                        ModBlocks.DARK_APPLE_TRAPDOOR.get(),
                modLoc("block/dark_apple_trapdoor"), true,
                "cutout");

        signBlock((StandingSignBlock) ModBlocks.DARK_APPLE_SIGN.get(), (WallSignBlock) ModBlocks.DARK_APPLE_WALL_SIGN.get(),
                blockTexture(ModBlocks.DARK_APPLE_PLANKS.get()));

        hangingSignBlock(ModBlocks.DARK_APPLE_HANGING_SIGN.get(), ModBlocks.DARK_APPLE_WALL_HANGING_SIGN.get(),
                blockTexture(ModBlocks.DARK_APPLE_PLANKS.get()));

        simpleLeaves(ModBlocks.DARK_APPLE_LEAVES);
    }


    // --- 吊り下げ看板用のカスタムメソッド ---
    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ResourceLocation texture) {
        ModelFile sign = models().sign(name(signBlock), texture);
        simpleBlock(signBlock, sign);
        simpleBlock(wallSignBlock, sign);
    }

    private String name(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block).getPath();
    }

    private void simpleBlockWithItem(RegistryObject<Block> block) {
        simpleBlockWithItem(block.get(), cubeAll(block.get()));
    }

    private void item(RegistryObject<Block> block) {
        String path = ForgeRegistries.BLOCKS.getKey(block.get()).getPath();
        simpleBlockItem(block.get(), new ModelFile.UncheckedModelFile(
                ResourceLocation.fromNamespaceAndPath(AppleTreeMod.MOD_ID, "block/" + path)));
    }


    private void simpleLeaves(RegistryObject<Block> block) {
        simpleBlockWithItem(block.get(), models().singleTexture(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                ResourceLocation.withDefaultNamespace("block/leaves"),
                "all", blockTexture(block.get())).renderType("cutout"));
    }

    private void sapling(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),
                        blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }
}