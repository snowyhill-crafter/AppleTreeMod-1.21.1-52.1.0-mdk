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

        logBlock((RotatedPillarBlock) ModBlocks.APPLE_LOG.get());
        item(ModBlocks.APPLE_LOG);//ログブロックとアクシズブロックはアイテムモデルを自動生成しないし作っても消される。

        logBlock((RotatedPillarBlock) ModBlocks.STRIPPED_APPLE_LOG.get());
        item(ModBlocks.STRIPPED_APPLE_LOG);


        axisBlock((RotatedPillarBlock) ModBlocks.APPLE_WOOD.get(),
                blockTexture(ModBlocks.APPLE_LOG.get()),
                blockTexture(ModBlocks.APPLE_LOG.get()));
        item(ModBlocks.APPLE_WOOD);

        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_APPLE_WOOD.get(),
                blockTexture(ModBlocks.STRIPPED_APPLE_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_APPLE_LOG.get()));
        item(ModBlocks.STRIPPED_APPLE_WOOD);

        simpleBlockWithItem(ModBlocks.APPLE_PLANKS);
        slabBlock((SlabBlock) ModBlocks.APPLE_SLAB.get(),
                // 二つ重ねたときのテクスチャ
                blockTexture(ModBlocks.APPLE_PLANKS.get()),
                // 単体のテクスチャ
                blockTexture(ModBlocks.APPLE_PLANKS.get()));
        stairsBlock((StairBlock) ModBlocks.APPLE_STAIRS.get(),
                blockTexture(ModBlocks.APPLE_PLANKS.get()));
        fenceBlock((FenceBlock) ModBlocks.APPLE_FENCE.get(),
                blockTexture(ModBlocks.APPLE_PLANKS.get()));
        fenceGateBlock((FenceGateBlock) ModBlocks.APPLE_FENCE_GATE.get(),
                blockTexture(ModBlocks.APPLE_PLANKS.get()));
        doorBlockWithRenderType((DoorBlock) ModBlocks.APPLE_DOOR.get(),
                modLoc("block/apple_door_bottom"),
                modLoc("block/apple_door_top"),
                "cutout");
        trapdoorBlockWithRenderType((TrapDoorBlock)
                        ModBlocks.APPLE_TRAPDOOR.get(),
                modLoc("block/apple_trapdoor"), true,
                "cutout");
        buttonBlock((ButtonBlock) ModBlocks.APPLE_BUTTON.get(),
                blockTexture(ModBlocks.APPLE_PLANKS.get()));
        pressurePlateBlock((PressurePlateBlock)
                        ModBlocks.APPLE_PRESSURE_PLATE.get(),
                blockTexture(ModBlocks.APPLE_PLANKS.get()));

        simpleLeaves(ModBlocks.APPLE_LEAVES);
        simpleLeaves(ModBlocks.APPLE_FLOWER_LEAVES);

        // --- 1) 苗木: cross + cutout（1回だけ） ---
        ModelFile saplingModel = models()
                .cross(regPath(ModBlocks.APPLE_SAPLING.get()), modLoc("block/apple_sapling"))
                .renderType("cutout");
        simpleBlock(ModBlocks.APPLE_SAPLING.get(), saplingModel);

        // --- 2) 鉢植え苗木: flower_pot_cross 親 + plant テクスチャ（1回だけ） ---
        ModelFile pottedModel = models()
                .withExistingParent(regPath(ModBlocks.POTTED_APPLE_SAPLING.get()), mcLoc("block/flower_pot_cross"))
                .texture("plant", modLoc("block/apple_sapling"))
                .renderType("cutout");
        simpleBlock(ModBlocks.POTTED_APPLE_SAPLING.get(), pottedModel);

        // --- 1) 苗木 ---
        ModelFile DarkAppleSaplingModel = models()
                .cross(regPath(ModBlocks.DARK_APPLE_SAPLING.get()), modLoc("block/dark_apple_sapling"))
                .renderType("cutout");
        simpleBlock(ModBlocks.DARK_APPLE_SAPLING.get(), DarkAppleSaplingModel);

// --- 2) 鉢植え苗木 ---
        ModelFile DarkApplePottedModel = models()
                .withExistingParent(regPath(ModBlocks.POTTED_DARK_APPLE_SAPLING.get()), mcLoc("block/flower_pot_cross"))
                .texture("plant", modLoc("block/dark_apple_sapling"))
                .renderType("cutout");
        simpleBlock(ModBlocks.POTTED_DARK_APPLE_SAPLING.get(), DarkApplePottedModel);

        logBlock((RotatedPillarBlock) ModBlocks.DARK_APPLE_LOG.get());
        item(ModBlocks.DARK_APPLE_LOG);//ログブロックとアクシズブロックはアイテムモデルを自動生成しないし作っても消される。

        logBlock((RotatedPillarBlock) ModBlocks.STRIPPED_DARK_APPLE_LOG.get());
        item(ModBlocks.STRIPPED_DARK_APPLE_LOG);


        axisBlock((RotatedPillarBlock) ModBlocks.DARK_APPLE_WOOD.get(),
                blockTexture(ModBlocks.DARK_APPLE_LOG.get()),
                blockTexture(ModBlocks.DARK_APPLE_LOG.get()));
        item(ModBlocks.DARK_APPLE_WOOD);

        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_DARK_APPLE_WOOD.get(),
                blockTexture(ModBlocks.STRIPPED_DARK_APPLE_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_DARK_APPLE_LOG.get()));
        item(ModBlocks.STRIPPED_DARK_APPLE_WOOD);

        simpleBlockWithItem(ModBlocks.DARK_APPLE_PLANKS);
        slabBlock((SlabBlock) ModBlocks.DARK_APPLE_SLAB.get(),
                // 二つ重ねたときのテクスチャ
                blockTexture(ModBlocks.DARK_APPLE_PLANKS.get()),
                // 単体のテクスチャ
                blockTexture(ModBlocks.DARK_APPLE_PLANKS.get()));
        stairsBlock((StairBlock) ModBlocks.DARK_APPLE_STAIRS.get(),
                blockTexture(ModBlocks.DARK_APPLE_PLANKS.get()));
        fenceBlock((FenceBlock) ModBlocks.DARK_APPLE_FENCE.get(),
                blockTexture(ModBlocks.DARK_APPLE_PLANKS.get()));
        fenceGateBlock((FenceGateBlock) ModBlocks.DARK_APPLE_FENCE_GATE.get(),
                blockTexture(ModBlocks.DARK_APPLE_PLANKS.get()));
        doorBlockWithRenderType((DoorBlock) ModBlocks.DARK_APPLE_DOOR.get(),
                modLoc("block/dark_apple_door_bottom"),
                modLoc("block/dark_apple_door_top"),
                "cutout");
        trapdoorBlockWithRenderType((TrapDoorBlock)
                        ModBlocks.DARK_APPLE_TRAPDOOR.get(),
                modLoc("block/dark_apple_trapdoor"), true,
                "cutout");
        buttonBlock((ButtonBlock) ModBlocks.DARK_APPLE_BUTTON.get(),
                blockTexture(ModBlocks.DARK_APPLE_PLANKS.get()));
        pressurePlateBlock((PressurePlateBlock)
                        ModBlocks.DARK_APPLE_PRESSURE_PLATE.get(),
                blockTexture(ModBlocks.DARK_APPLE_PLANKS.get()));

        simpleLeaves(ModBlocks.DARK_APPLE_LEAVES);
        simpleLeaves(ModBlocks.DARK_APPLE_FLOWER_LEAVES);



    }

    private void simpleBlockWithItem(RegistryObject<Block> block) {
        simpleBlockWithItem(block.get(), cubeAll(block.get()));
    }

    // ブロック用のアイテムモデルを作成
    private void item(RegistryObject<Block> block) {
        simpleBlockItem(block.get(), new ModelFile.UncheckedModelFile(
                AppleTreeMod.MOD_ID + ":block/" +
                        ForgeRegistries.BLOCKS.getKey(block.get()).getPath()
        ));
    }

    /** 登録パス（apple_sapling など） */
    private static String regPath(Block b) {
        return ForgeRegistries.BLOCKS.getKey(b).getPath();
    }

    // 普通の葉ブロック
    private void simpleLeaves(RegistryObject<Block> block) {
        simpleBlockWithItem(block.get(), models().singleTexture(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                ResourceLocation.fromNamespaceAndPath("minecraft", "block/leaves"),
                "all", blockTexture(block.get())).renderType("cutout"));
    }

    private void sapling(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),
                        blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }




    }

