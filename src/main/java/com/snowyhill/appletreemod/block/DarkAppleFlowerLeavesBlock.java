package com.snowyhill.appletreemod.block;

import com.mojang.serialization.MapCodec;
import com.snowyhill.appletreemod.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

// BlockではなくLeavesBlockを継承する
public class DarkAppleFlowerLeavesBlock extends LeavesBlock implements BonemealableBlock {
    public static final MapCodec<AppleFlowerLeavesBlock> CODEC = simpleCodec(AppleFlowerLeavesBlock::new);
    public static final int MAX_AGE = 2;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_2;

    private static final VoxelShape LEAVES_SHAPE = Shapes.block();

    @Override
    public MapCodec<AppleFlowerLeavesBlock> codec() {
        return CODEC;
    }

    public DarkAppleFlowerLeavesBlock(Properties p_57249_) {
        super(p_57249_);
        // LeavesBlockのデフォルト状態に加えて、AGEプロパティのデフォルトを設定
        // LeavesBlockのコンストラクタがDISTANCE, PERSISTENT, WATERLOGGEDを設定するので、AGEのみ追加
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(AGE, Integer.valueOf(0))
                .setValue(DISTANCE, Integer.valueOf(7))
                .setValue(PERSISTENT, Boolean.valueOf(false))
                .setValue(WATERLOGGED, Boolean.valueOf(false)) // WATERLOGGEDもデフォルト状態に含める
        );
    }
//クリエイティブモードのピック（中ボタン押し込み）の処理を描いているが、指定しなければブロックを返すので別に要らない。
//    @Override
//    public ItemStack getCloneItemStack(LevelReader pLevel, BlockPos pPos, BlockState pState) {
//        return new ItemStack(ModItems.DARK_APPLE.get());
//    }

    @Override
    protected VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return LEAVES_SHAPE;
    }

    @Override
    protected boolean isRandomlyTicking(BlockState pState) {
        return pState.getValue(AGE) < MAX_AGE || !pState.getValue(PERSISTENT) && pState.getValue(DISTANCE) == 7;
    }

    @Override
    protected void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        int i = pState.getValue(AGE);
        if (i < MAX_AGE && pLevel.getRawBrightness(pPos.above(), 0) >= 9 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(pLevel, pPos, pState, pRandom.nextInt(5) == 0)) {
            BlockState blockstate = pState.setValue(AGE, Integer.valueOf(i + 1));
            pLevel.setBlock(pPos, blockstate, 2);
            pLevel.gameEvent(GameEvent.BLOCK_CHANGE, pPos, GameEvent.Context.of(blockstate));
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(pLevel, pPos, pState);
        }

        super.randomTick(pState, pLevel, pPos, pRandom);
    }

    @Override
    protected ItemInteractionResult useItemOn(
            ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult
    ) {
        int i = pState.getValue(AGE);
        if (pStack.is(Items.BONE_MEAL)) {
            if (isValidBonemealTarget(pLevel, pPos, pState)) {
                if (!pLevel.isClientSide) {
                    performBonemeal((ServerLevel) pLevel, pLevel.random, pPos, pState);
                }
                return ItemInteractionResult.sidedSuccess(pLevel.isClientSide);
            }
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        return super.useItemOn(pStack, pState, pLevel, pPos, pPlayer, pHand, pHitResult);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
        int i = pState.getValue(AGE);
        if (i == MAX_AGE) {
            if (!pLevel.isClientSide) {
                popResource(pLevel, pPos, new ItemStack(ModItems.DARK_APPLE.get(), 1));
                pLevel.playSound(null, pPos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + pLevel.random.nextFloat() * 0.4F);
                BlockState blockstate = pState.setValue(AGE, Integer.valueOf(0));
                pLevel.setBlock(pPos, blockstate, 2);
                pLevel.gameEvent(GameEvent.BLOCK_CHANGE, pPos, GameEvent.Context.of(pPlayer, blockstate));
            }
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        } else {
            return super.useWithoutItem(pState, pLevel, pPos, pPlayer, pHitResult);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        // 親クラス（LeavesBlock）のcreateBlockStateDefinitionを呼び出し、
        // その後でAGEプロパティを追加する。これにより、DISTANCE, PERSISTENT, WATERLOGGEDが適切に定義される。
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(AGE);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader pLevel, BlockPos pPos, BlockState pState) {
        return pState.getValue(AGE) < MAX_AGE;
    }

    @Override
    public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        int before = pState.getValue(AGE);
        if (before < MAX_AGE) {
            int after = before + 1;
            BlockState grown = pState.setValue(AGE, Integer.valueOf(after));

            // 成長を反映
            pLevel.setBlock(pPos, grown, 2);
            pLevel.gameEvent(GameEvent.BLOCK_CHANGE, pPos, GameEvent.Context.of(grown));

            // ---- ここから演出（“成長したときだけ”出す）----

            // バニラの骨粉キラキラ（green sparkles）
            // levelEvent(2005, pos, data) は骨粉演出。data は 0 で OK
            pLevel.levelEvent(2005, pPos, 0);

            // 追加のハッピービレジャー粒子（お好み）
            double cx = pPos.getX() + 0.5D;
            double cy = pPos.getY() + 0.7D;
            double cz = pPos.getZ() + 0.5D;
            int count = 6 + pRandom.nextInt(5); // 6～10個
            pLevel.sendParticles(ParticleTypes.HAPPY_VILLAGER, cx, cy, cz, count, 0.25D, 0.25D, 0.25D, 0.0D);

            // 効果音（骨粉使用音）
            pLevel.playSound(
                    null, pPos,
                    SoundEvents.BONE_MEAL_USE,
                    SoundSource.BLOCKS,
                    0.8F,
                    0.9F + pRandom.nextFloat() * 0.2F
            );
        }
    }
    //可燃性と延焼
    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 60;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 30;
    }

}