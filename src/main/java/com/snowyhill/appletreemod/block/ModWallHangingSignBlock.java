package com.snowyhill.appletreemod.block;

import com.snowyhill.appletreemod.blockentity.AppleHangingSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWallHangingSignBlock extends WallHangingSignBlock {
    public ModWallHangingSignBlock(Properties properties, WoodType woodType) {
        // woodType を先に、properties を後に渡すように修正
        super(woodType, properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new AppleHangingSignBlockEntity(pos, state);
    }
}