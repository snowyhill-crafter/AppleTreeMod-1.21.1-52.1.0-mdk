package com.snowyhill.appletreemod.block;

import com.snowyhill.appletreemod.blockentity.AppleSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWallSignBlock extends WallSignBlock {
    public ModWallSignBlock(Properties properties, WoodType woodType) {
        // woodType を先に、properties を後に渡すように修正
        super(woodType, properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new AppleSignBlockEntity(pos, state);
    }
}