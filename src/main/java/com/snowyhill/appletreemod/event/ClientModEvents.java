package com.snowyhill.appletreemod.event;


import com.snowyhill.appletreemod.AppleTreeMod;
import com.snowyhill.appletreemod.registry.ModBlocks;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.FoliageColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = AppleTreeMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModEvents {

    @SubscribeEvent
    public static void onBlockColors(RegisterColorHandlersEvent.Block event) {


        // バイオームのfoliage.png（バニラ仕様）を参照する
        event.register(
                (state, world, pos, tintIndex) -> {
                    if (world != null && pos != null) {
                        // バニラの葉ブロックが使っている色（バイオーム色）
                        return BiomeColors.getAverageFoliageColor(world, pos);
                    }
                    // デフォルト（アイテムなどバイオームなしのとき）はこれ
                    return FoliageColor.getDefaultColor();
                },
                ModBlocks.APPLE_LEAVES.get(),
                ModBlocks.APPLE_FLOWER_LEAVES.get(),
                ModBlocks.ORNAMENTAL_APPLE_FLOWER_LEAVES.get(),
                ModBlocks.ORNAMENTAL_APPLE_UNRIPE_LEAVES.get(),
                ModBlocks.ORNAMENTAL_APPLE_FRUIT_LEAVES.get(),

                ModBlocks.DARK_APPLE_LEAVES.get(),
                ModBlocks.DARK_APPLE_FLOWER_LEAVES.get(),
                ModBlocks.ORNAMENTAL_DARK_APPLE_FLOWER_LEAVES.get(),
                ModBlocks.ORNAMENTAL_DARK_APPLE_UNRIPE_LEAVES.get(),
                ModBlocks.ORNAMENTAL_DARK_APPLE_FRUIT_LEAVES.get()
        );

    }

    @SubscribeEvent
    public static void onItemColors(RegisterColorHandlersEvent.Item event) {

        event.register(
                (stack, tintIndex) -> FoliageColor.getDefaultColor(),
                ModBlocks.APPLE_LEAVES.get(),
                ModBlocks.APPLE_FLOWER_LEAVES.get(),
                ModBlocks.ORNAMENTAL_APPLE_FLOWER_LEAVES.get(),
                ModBlocks.ORNAMENTAL_APPLE_UNRIPE_LEAVES.get(),
                ModBlocks.ORNAMENTAL_APPLE_FRUIT_LEAVES.get(),

                ModBlocks.DARK_APPLE_LEAVES.get(),
                ModBlocks.DARK_APPLE_FLOWER_LEAVES.get(),
                ModBlocks.ORNAMENTAL_DARK_APPLE_FLOWER_LEAVES.get(),
                ModBlocks.ORNAMENTAL_DARK_APPLE_UNRIPE_LEAVES.get(),
                ModBlocks.ORNAMENTAL_DARK_APPLE_FRUIT_LEAVES.get()



        );








    }
}
