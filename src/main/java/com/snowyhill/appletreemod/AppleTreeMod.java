package com.snowyhill.appletreemod;

import com.mojang.logging.LogUtils;
import com.snowyhill.appletreemod.registry.*;
import com.snowyhill.appletreemod.worldgen.features.decolator.ModTreeDecorators;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(AppleTreeMod.MOD_ID)
public class AppleTreeMod {
    public static final String MOD_ID = "appletreemod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public AppleTreeMod(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();
//1.20.6ではIEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();だった
        modEventBus.addListener(this::commonSetup);

        // レジストリをイベントバスに登録
        // 1. Blocks (EntitiesやItemsが参照するため最初)
        ModBlocks.register(modEventBus);
        // 2. Entities (Itemsがボートアイテムを作る際に参照するため)
        ModEntities.register(modEventBus);
        // 3. Items
        ModItems.register(modEventBus);
        // 4. その他
        ModBlockEntities.register(modEventBus);
        ModTabs.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
        ModTreeDecorators.DECORATORS.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
     //ModFlammablesから可燃性を参照
        event.enqueueWork(ModFlammables::register);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }
}