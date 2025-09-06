package com.snowyhill.appletreemod.worldgen.features.decolator;

import com.snowyhill.appletreemod.AppleTreeMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTreeDecorators {

    public static final DeferredRegister<TreeDecoratorType<?>> DECORATORS =
            DeferredRegister.create(Registries.TREE_DECORATOR_TYPE, AppleTreeMod.MOD_ID);

    public static final RegistryObject<TreeDecoratorType<AppleOuterFlowersDecorator>> APPLE_OUTER_FLOWERS =
            DECORATORS.register("apple_outer_flowers",
                    () -> new TreeDecoratorType<>(AppleOuterFlowersDecorator.CODEC));

    public static final RegistryObject<TreeDecoratorType<DarkAppleOuterFlowersDecorator>> DARK_APPLE_OUTER_FLOWERS =
            DECORATORS.register("dark_apple_outer_flowers",
                    () -> new TreeDecoratorType<>(DarkAppleOuterFlowersDecorator.CODEC));



    // 起動時にイベントバスへ登録するコード（メインMod側で呼ぶ）
    public static void register(net.minecraftforge.eventbus.api.IEventBus bus) {
        DECORATORS.register(bus);
    }


}
