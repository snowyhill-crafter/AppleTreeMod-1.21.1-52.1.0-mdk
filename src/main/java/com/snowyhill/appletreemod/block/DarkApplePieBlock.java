package com.snowyhill.appletreemod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;


public class DarkApplePieBlock extends Block{
    public static final IntegerProperty BITES = IntegerProperty.create("bites", 0, 3);
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    // ── 形状（世界座標の象限。境界は 1..8..15 で統一） ──
    private static final VoxelShape Q_NW = Block.box(1, 0, 1, 8, 8, 8);
    private static final VoxelShape Q_NE = Block.box(8, 0, 1, 15, 8, 8);
    private static final VoxelShape Q_SW = Block.box(1, 0, 8, 8, 8, 15);
    private static final VoxelShape Q_SE = Block.box(8, 0, 8, 15, 8, 15);
    // 時計回りの順（北基準で）: NE(0) → SE(1) → SW(2) → NW(3)
    private static final VoxelShape[] QUADS_CW = new VoxelShape[] { Q_NE, Q_SE, Q_SW, Q_NW };

    public DarkApplePieBlock(Properties props) {
        super(props);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(BITES, 0)
                .setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b) {
        b.add(BITES, FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        // プレイヤーが向いている方向＝12時
        return this.defaultBlockState()
                .setValue(FACING, ctx.getHorizontalDirection())
                .setValue(BITES, 0);
    }

    // 食べる
    // 空手（デフォルトのブロック操作用）
    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos,
                                               Player player, BlockHitResult hit) {
        if (!player.canEat(false)) return InteractionResult.PASS;
        if (level.isClientSide)     return InteractionResult.SUCCESS;

        eatOneBite(level, pos, state, player);
        return InteractionResult.CONSUME; // 成功（腕は振らない）
    }

    // 手に持っているアイテムで右クリックされた場合（ケーキ互換：持ち物があっても食べられる）
    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
                                              Player player, InteractionHand hand, BlockHitResult hit) {
        if (!player.canEat(false)) return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        if (level.isClientSide)     return ItemInteractionResult.SUCCESS;

        eatOneBite(level, pos, state, player);
        return ItemInteractionResult.CONSUME;
    }
    // 共通の「ひと口食べる」処理
    private void eatOneBite(Level level, BlockPos pos, BlockState state, Player player) {
        player.getFoodData().eat(4, 0.3F); // 回復量はお好みで
        level.playSound(null, pos, SoundEvents.GENERIC_EAT, SoundSource.PLAYERS, 1.0F, 1.0F);

        int bites = state.getValue(BITES);
        if (bites < 3) {
            level.setBlock(pos, state.setValue(BITES, bites + 1), Block.UPDATE_CLIENTS);
        } else {
            level.removeBlock(pos, false);
        }
        if (!level.isClientSide) { //dark_appleの高価を引き継ぐ
            com.snowyhill.appletreemod.item.DarkAppleItem.applyRandomDarkAppleEffect(player, level);
        }
    }





    // 地面の透け防止：形状ベースで遮蔽判定
    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    // 選択枠
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return shapeFor(state);
    }
    // クリック判定
    @Override
    public VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return shapeFor(state);
    }
    // 衝突判定（破壊が通らない事故防止）
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return shapeFor(state);
    }

    // ── 残っている象限を合成して形状を作る（回転は使わない） ──
    private static VoxelShape shapeFor(BlockState state) {
        int bites = state.getValue(BITES);
        Direction f = state.getValue(FACING);

        // 最初に欠ける象限のインデックス（NE=0 を基準に、向きに応じて回す）
        // NORTH: NE(0), EAST: SE(1), SOUTH: SW(2), WEST: NW(3)
        int start = switch (f) {
            case EAST -> 1;
            case SOUTH -> 2;
            case WEST -> 3;
            default -> 0; // NORTH
        };

        // bites 回だけ時計回りに欠けていく → 欠けた象限を removed[] にマーキング
        boolean[] removed = new boolean[4];
        for (int i = 0; i < bites; i++) {
            removed[(start + i) & 3] = true;
        }

        // 残っている象限を合成
        VoxelShape acc = Shapes.empty();
        for (int i = 0; i < 4; i++) {
            if (!removed[i]) acc = Shapes.or(acc, QUADS_CW[i]);
        }
        return acc;
    }
}
