package com.snowyhill.appletreemod.client;

import com.snowyhill.appletreemod.AppleTreeMod;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;


public final class ModWoodTypes {
    // 名前を "appletreemod:apple" に変更する
    public static final WoodType APPLE =
            WoodType.register(new WoodType(AppleTreeMod.MOD_ID + ":apple", BlockSetType.OAK));

    public static final WoodType DARK_APPLE =
            WoodType.register(new WoodType(AppleTreeMod.MOD_ID + ":dark_apple", BlockSetType.OAK));

}