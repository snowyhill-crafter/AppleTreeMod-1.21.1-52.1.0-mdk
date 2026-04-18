package com.snowyhill.appletreemod.client;

import com.snowyhill.appletreemod.AppleTreeMod;
import com.snowyhill.appletreemod.client.renderer.ModBoatRenderer;
import com.snowyhill.appletreemod.registry.ModBlockEntities;
import com.snowyhill.appletreemod.registry.ModEntities;
import com.snowyhill.appletreemod.client.ModWoodTypes;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(
        modid = AppleTreeMod.MOD_ID,
        value = Dist.CLIENT,
        bus = Mod.EventBusSubscriber.Bus.MOD
)
public final class ClientSetup {

    @SubscribeEvent
    public static void onClientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            // 3. ここで ModWoodTypes.APPLE と書けているか
            Sheets.addWoodType(ModWoodTypes.APPLE);
            Sheets.addWoodType(ModWoodTypes.DARK_APPLE);
        });
        // BlockEntity (看板) のレンダラー登録
        BlockEntityRenderers.register(ModBlockEntities.SIGN.get(), SignRenderer::new);
        // HangingSignRenderer は context を渡す形式に修正
        BlockEntityRenderers.register(ModBlockEntities.HANGING_SIGN.get(), HangingSignRenderer::new);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        // ボート（エンティティ）のレンダラー登録
        // 第2引数で ModBoatRenderer の第2引数 (isChestBoat) を切り替えます
        event.registerEntityRenderer(ModEntities.MOD_BOAT.get(), context -> new ModBoatRenderer(context, false));
        event.registerEntityRenderer(ModEntities.MOD_CHEST_BOAT.get(), context -> new ModBoatRenderer(context, true));
    }
}