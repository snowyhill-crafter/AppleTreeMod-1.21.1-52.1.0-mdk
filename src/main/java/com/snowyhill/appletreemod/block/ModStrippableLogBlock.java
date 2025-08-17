package com.snowyhill.appletreemod.block;

    //原木と木材用のクラス

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class ModStrippableLogBlock extends RotatedPillarBlock {
    private final Supplier<Block> strippedLog;

    public ModStrippableLogBlock(BlockBehaviour.Properties properties, Supplier<Block> strippedLog) {
        super(properties);
        this.strippedLog = strippedLog;
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        // 斧で右クリック／左クリックによる「樹皮を剥ぐ」ツールアクション
        if (toolAction == ToolActions.AXE_STRIP) {
            BlockState result = strippedLog.get().defaultBlockState()
                    .setValue(AXIS, state.getValue(AXIS)); // 既存の軸を引き継ぐ

            // 加工の“実行時”のみ音を鳴らす（simulate 中は鳴らさない）
            if (!simulate && context.getLevel() != null) {
                context.getLevel().playSound(
                        context.getPlayer(),                 // 聞こえ方の基準になるプレイヤー（nullでもOK）
                        context.getClickedPos(),             // 位置
                        SoundEvents.AXE_STRIP,               // 樹皮剥ぎのバニラ音
                        SoundSource.BLOCKS,
                        1.0F,                                // 音量
                        1.0F                                 // ピッチ
                );
            }
            return result;
        }
        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }
}