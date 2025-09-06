package com.snowyhill.appletreemod.worldgen.features;

import com.snowyhill.appletreemod.AppleTreeMod;
import com.snowyhill.appletreemod.registry.ModBlocks;
import com.snowyhill.appletreemod.worldgen.features.decolator.AppleOuterFlowersDecorator;
import com.snowyhill.appletreemod.worldgen.features.decolator.DarkAppleOuterFlowersDecorator;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

import java.util.List;

public class ModFeatures {


    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE_TREE_KEY =
            createKey("apple_tree");

    public static final ResourceKey<ConfiguredFeature<?, ?>> DARK_APPLE_TREE_KEY =
            createKey( "dark_apple_tree");






    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

        FeatureUtils.register(context, APPLE_TREE_KEY, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.APPLE_LOG.get()),
                        new StraightTrunkPlacer(5, 1, 0),

                        // ここは “通常葉のみ”
                        BlockStateProvider.simple(ModBlocks.APPLE_LEAVES.get()),

                        new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                        new TwoLayersFeatureSize(1, 0, 1)
                )
                        // ★外縁だけ APPLE_FLOWER_LEAVES に置換
                        .decorators(java.util.List.of(
                                new AppleOuterFlowersDecorator(2) // 2〜3が目安
                        ))
                        .ignoreVines()
                        .build()
        );


        FeatureUtils.register(context, DARK_APPLE_TREE_KEY, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        // 幹・葉はダークアップルのブロックを使用
                        BlockStateProvider.simple(ModBlocks.DARK_APPLE_LOG.get()),
                        // 大型オークと同タイプの幹
                        new FancyTrunkPlacer(
                                7,   // base height（大きなオーク相当）
                                3,  // heightRandA
                                1    // heightRandB
                        ),
                        BlockStateProvider.simple(ModBlocks.DARK_APPLE_LEAVES.get()),
                        // 大型オークと同タイプの葉
                        new FancyFoliagePlacer(
                                ConstantInt.of(2),  // foliageRadius
                                ConstantInt.of(3),  // offset
                                3                   // height
                        ),
                        // Fancy Oak 相当の層サイズ
                        new TwoLayersFeatureSize(1, 0, 1))
                        // ★ TreeDecorator の “インスタンス” を渡す
                        .decorators(List.of(
                                new DarkAppleOuterFlowersDecorator(2)
                        )).ignoreVines()
                        .build()
        );

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE,
                ResourceLocation.fromNamespaceAndPath(AppleTreeMod.MOD_ID, name));
    }
}
