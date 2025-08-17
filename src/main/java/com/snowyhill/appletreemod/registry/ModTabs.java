package com.snowyhill.appletreemod.registry;

import com.snowyhill.appletreemod.AppleTreeMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;

public class ModTabs {
    // レジストリを作成
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AppleTreeMod.MOD_ID);
    // タブを作成
    public static final RegistryObject<CreativeModeTab> APPLETREEMOD_TAB =
            TABS.register("appletreemod_tab", () -> CreativeModeTab.builder()
                    // タブのタイトルを設定
                    .title(Component.translatable("creativetabs.appletreemod_tab"))
                    // タブのアイコンを設定
                    .icon(ModItems.APPLE_SAPLING_ITEM.get()::getDefaultInstance)
                    // タブにアイテムを追加（必須）
                    .displayItems((pParameters, pOutput) -> {


                        pOutput.accept(ModItems.APPLE_SAPLING_ITEM.get());
                        pOutput.accept(ModItems.APPLE_LOG_ITEM.get());
                        pOutput.accept(ModItems.STRIPPED_APPLE_LOG_ITEM.get());
                        pOutput.accept(ModItems.APPLE_WOOD_ITEM.get());
                        pOutput.accept(ModItems.STRIPPED_APPLE_WOOD_ITEM.get());
                        pOutput.accept(ModItems.APPLE_LEAVES_ITEM.get());
                        pOutput.accept(ModItems.ORNAMENTAL_APPLE_FLOWER_LEAVES_ITEM.get());
                        pOutput.accept(ModItems.ORNAMENTAL_APPLE_UNRIPE_LEAVES_ITEM.get());
                        pOutput.accept(ModItems.ORNAMENTAL_APPLE_FRUIT_LEAVES_ITEM.get());

                        pOutput.accept(ModItems.APPLE_PLANKS_ITEM.get());
                        pOutput.accept(ModItems.APPLE_SLAB_ITEM.get());
                        pOutput.accept(ModItems.APPLE_STAIRS_ITEM.get());
                        pOutput.accept(ModItems.APPLE_FENCE_ITEM.get());
                        pOutput.accept(ModItems.APPLE_FENCE_GATE_ITEM.get());
                        pOutput.accept(ModItems.APPLE_DOOR_ITEM.get());
                        pOutput.accept(ModItems.APPLE_TRAPDOOR_ITEM.get());
                        pOutput.accept(ModItems.APPLE_BUTTON_ITEM.get());
                        pOutput.accept(ModItems.APPLE_PRESSURE_PLATE_ITEM.get());

                        pOutput.accept(ModItems.APPLE_PIE_ITEM.get());




                    })
                    // 検索バーを追加
                    //.withSearchBar()
                    // 背景画像を設定
                    //.backgroundTexture(ResourceLocation.fromNamespaceAndPath(
                    //        AppleTreeMod.MOD_ID, "textures/gui/Modtab_bg.png"))
                    // ラベルの色を指定
                    //.withLabelColor(Color.WHITE.getRGB())
                    // カーソルを当てたときのスロットの色を指定
                    //.withSlotColor(new Color(0, 2, 116, 55).getRGB())
                    .build());

//    // ダミーのタブ
//    public static final RegistryObject<CreativeModeTab> DUMMY =
//            TABS.register("dummy", () -> CreativeModeTab.builder()
//                    .icon(Items.ARMOR_STAND::getDefaultInstance)
//                    .displayItems((pParameters, pOutput) -> {
//                        pOutput.accept(ModItems.SALT.get());
//                        pOutput.accept(ModItems.BUTTER.get());
//                    })
//                    // タブの順番を指定
//                    .withTabsAfter(CreativeModeTabs.BUILDING_BLOCKS)
//                    .build());

    public static void register(IEventBus eventBus) {
        TABS.register(eventBus);
    }

}
