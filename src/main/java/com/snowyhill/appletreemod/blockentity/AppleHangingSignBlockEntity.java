package com.snowyhill.appletreemod.blockentity;

import com.snowyhill.appletreemod.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class AppleHangingSignBlockEntity extends HangingSignBlockEntity {
    public AppleHangingSignBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.HANGING_SIGN.get(), pos, state);
    }

    @Override
    public BlockEntityType<?> getType() {
        return ModBlockEntities.HANGING_SIGN.get();
    }
}