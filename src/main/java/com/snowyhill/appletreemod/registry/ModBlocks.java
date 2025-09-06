package com.snowyhill.appletreemod.registry;

import com.snowyhill.appletreemod.AppleTreeMod;
import com.snowyhill.appletreemod.block.*;
import com.snowyhill.appletreemod.worldgen.tree.ModTreeGrowers;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    // レジストリを作成
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, AppleTreeMod.MOD_ID);
    // ブロックを作成＆レジストリに登録

    public static final RegistryObject<Block> APPLE_SAPLING = BLOCKS.register(
            "apple_sapling",
            () -> new SaplingBlock(ModTreeGrowers.APPLE_TREE,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    public static final RegistryObject<Block> POTTED_APPLE_SAPLING = BLOCKS.register(
            "potted_apple_sapling",
            () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    ModBlocks.APPLE_SAPLING,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OAK_SAPLING)));


    public static final RegistryObject<Block> DARK_APPLE_SAPLING = BLOCKS.register(
            "dark_apple_sapling",
            () -> new SaplingBlock(ModTreeGrowers.DARK_APPLE_TREE,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    public static final RegistryObject<Block> POTTED_DARK_APPLE_SAPLING = BLOCKS.register(
            "potted_dark_apple_sapling",
            () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    ModBlocks.DARK_APPLE_SAPLING,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OAK_SAPLING)));












    public static final RegistryObject<Block> APPLE_LOG = BLOCKS.register("apple_log",
            () -> new ModStrippableLogBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG),
                    () -> ModBlocks.STRIPPED_APPLE_LOG.get()
            )
    );
    // 樹皮はがし後（stripped）原木
    public static final RegistryObject<Block> STRIPPED_APPLE_LOG = BLOCKS.register("stripped_apple_log",
            () -> new ModLogBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG))
    );

    public static final RegistryObject<Block> APPLE_WOOD = BLOCKS.register("apple_wood",
            () -> new ModStrippableLogBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD),
                    () -> ModBlocks.STRIPPED_APPLE_WOOD.get()
            )
    );
    public static final RegistryObject<Block> STRIPPED_APPLE_WOOD = BLOCKS.register("stripped_apple_wood",
            () -> new ModLogBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD))
    );


    public static final RegistryObject<Block> APPLE_LEAVES = BLOCKS.register(
            "apple_leaves",
            () -> new ModLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).randomTicks()));

    public static final RegistryObject<Block> APPLE_FLOWER_LEAVES = BLOCKS.register(
            "apple_flower_leaves",
            () -> new AppleFlowerLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).randomTicks()));

    public static final RegistryObject<Block> ORNAMENTAL_APPLE_FLOWER_LEAVES = BLOCKS.register(
            "ornamental_apple_flower_leaves",
            () -> new ModLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));

    public static final RegistryObject<Block> ORNAMENTAL_APPLE_UNRIPE_LEAVES = BLOCKS.register(
            "ornamental_apple_unripe_leaves",
            () -> new ModLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));

    public static final RegistryObject<Block> ORNAMENTAL_APPLE_FRUIT_LEAVES = BLOCKS.register(
            "ornamental_apple_fruit_leaves",
            () -> new ModLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));

    //クラフト
// 板材
    public static final RegistryObject<Block> APPLE_PLANKS = BLOCKS.register(
            "apple_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    // ハーフブロック
    public static final RegistryObject<Block> APPLE_SLAB = BLOCKS.register(
            "apple_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    // 階段
    public static final RegistryObject<Block> APPLE_STAIRS = BLOCKS.register(
            "apple_stairs",
            () -> new StairBlock(ModBlocks.APPLE_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    // フェンス
    public static final RegistryObject<Block> APPLE_FENCE = BLOCKS.register(
            "apple_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    // フェンスゲート
    public static final RegistryObject<Block> APPLE_FENCE_GATE = BLOCKS.register(
            "apple_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    // ドア
    public static final RegistryObject<Block> APPLE_DOOR = BLOCKS.register(
            "apple_door",
            () -> new DoorBlock(BlockSetType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    // トラップドア
    public static final RegistryObject<Block> APPLE_TRAPDOOR = BLOCKS.register(
            "apple_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noOcclusion()
            ));
    // ボタン
    public static final RegistryObject<Block> APPLE_BUTTON = BLOCKS.register(
            "apple_button",
            () -> new ButtonBlock(BlockSetType.OAK, 30, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
            ));
    // 感圧板
    public static final RegistryObject<Block> APPLE_PRESSURE_PLATE = BLOCKS.register(
            "apple_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));


    public static final RegistryObject<Block> DARK_APPLE_LOG = BLOCKS.register("dark_apple_log",
            () -> new ModStrippableLogBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG),
                    () -> ModBlocks.STRIPPED_DARK_APPLE_LOG.get()
            )
    );
    // 樹皮はがし後（stripped）原木
    public static final RegistryObject<Block> STRIPPED_DARK_APPLE_LOG = BLOCKS.register("stripped_dark_apple_log",
            () -> new ModLogBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_DARK_OAK_LOG))
    );
    public static final RegistryObject<Block> DARK_APPLE_WOOD = BLOCKS.register("dark_apple_wood",
            () -> new ModStrippableLogBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD),
                    () -> ModBlocks.STRIPPED_DARK_APPLE_WOOD.get()
            )
    );
    public static final RegistryObject<Block> STRIPPED_DARK_APPLE_WOOD = BLOCKS.register("stripped_dark_apple_wood",
            () -> new ModLogBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD))
    );



    public static final RegistryObject<Block> DARK_APPLE_LEAVES = BLOCKS.register(
            "dark_apple_leaves",
            () -> new ModLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).randomTicks()));


    public static final RegistryObject<Block> DARK_APPLE_FLOWER_LEAVES = BLOCKS.register(
            "dark_apple_flower_leaves",
            () -> new DarkAppleFlowerLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).randomTicks()));


    public static final RegistryObject<Block> ORNAMENTAL_DARK_APPLE_FLOWER_LEAVES = BLOCKS.register(
            "ornamental_dark_apple_flower_leaves",
            () -> new ModLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).randomTicks()));


    public static final RegistryObject<Block> ORNAMENTAL_DARK_APPLE_UNRIPE_LEAVES = BLOCKS.register(
            "ornamental_dark_apple_unripe_leaves",
            () -> new ModLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).randomTicks()));

    public static final RegistryObject<Block> ORNAMENTAL_DARK_APPLE_FRUIT_LEAVES = BLOCKS.register(
            "ornamental_dark_apple_fruit_leaves",
            () -> new ModLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).randomTicks()));



    public static final RegistryObject<Block> DARK_APPLE_PLANKS = BLOCKS.register(
            "dark_apple_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    // ハーフブロック
    public static final RegistryObject<Block> DARK_APPLE_SLAB = BLOCKS.register(
            "dark_apple_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    // 階段
    public static final RegistryObject<Block> DARK_APPLE_STAIRS = BLOCKS.register(
            "dark_apple_stairs",
            () -> new StairBlock(ModBlocks.DARK_APPLE_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    // フェンス
    public static final RegistryObject<Block> DARK_APPLE_FENCE = BLOCKS.register(
            "dark_apple_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    // フェンスゲート
    public static final RegistryObject<Block> DARK_APPLE_FENCE_GATE = BLOCKS.register(
            "dark_apple_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    // ドア
    public static final RegistryObject<Block> DARK_APPLE_DOOR = BLOCKS.register(
            "dark_apple_door",
            () -> new DoorBlock(BlockSetType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    // トラップドア
    public static final RegistryObject<Block> DARK_APPLE_TRAPDOOR = BLOCKS.register(
            "dark_apple_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noOcclusion()
            ));
    // ボタン
    public static final RegistryObject<Block> DARK_APPLE_BUTTON = BLOCKS.register(
            "dark_apple_button",
            () -> new ButtonBlock(BlockSetType.OAK, 30, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
            ));
    // 感圧板
    public static final RegistryObject<Block> DARK_APPLE_PRESSURE_PLATE = BLOCKS.register(
            "dark_apple_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));



    public static final RegistryObject<Block> APPLE_PIE = BLOCKS.register("apple_pie",
            () -> new ApplePieBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE).noOcclusion()));
    public static final RegistryObject<Block> DARK_APPLE_PIE = BLOCKS.register("dark_apple_pie",
            () -> new DarkApplePieBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE).noOcclusion()));

    // ブロック＆アイテムを作成
  //  private static <T extends Block> RegistryObject<T> registerBlockWithItem(String name, Supplier<T> supplier) {
        // ブロックレジストリにブロックを登録
//        RegistryObject<T> block = BLOCKS.register(name, supplier);
//        // アイテムレジストリにBlockItemを登録
//        ModItems.ITEMS.register(name,
//                () -> new BlockItem(block.get(), new Item.Properties()));
//        return block;


    // イベントバスに登録
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
    
}
