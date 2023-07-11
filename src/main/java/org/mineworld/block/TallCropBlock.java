package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.mineworld.helper.PropertyHelper;

import java.util.function.Supplier;

/**
 * Implementation class for a {@link CropBlock double block crop}
 */
public class TallCropBlock extends CropBlock {

    /**
     * {@link Integer The maximum age per part for this crop}
     */
    public static int MAX_AGE = 3;
    /**
     * {@link IntegerProperty The crop age property}
     */
    public static IntegerProperty AGE = BlockStateProperties.AGE_3;
    /**
     * {@link EnumProperty<DoubleBlockHalf> The block half property}
     */
    public static EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    /**
     * {@link VoxelShape Crop shapes by age}
     */
    private VoxelShape[] SHAPE_BY_AGE;
    /**
     * {@link Supplier<ItemLike> The seed supplier}
     */
    private Supplier<Item> SEED_SUPPLIER;

    /**
     * Constructor. Set the {@link Integer crop max age}
     *
     * @param seedSupplier {@link Supplier<Item> The seed id supplier}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     */
    public TallCropBlock(Supplier<Item> seedSupplier, FeatureFlag... featureFlags) {
        super(PropertyHelper.copyFromBlock(Blocks.WHEAT, featureFlags));
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0).setValue(HALF, DoubleBlockHalf.LOWER));
        SHAPE_BY_AGE = getShapes();
        SEED_SUPPLIER = seedSupplier;
    }

    /**
     * Get the {@link Integer crop max age}
     *
     * @return {@link Integer The crop max age}
     */
    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    /**
     * Get the {@link #AGE age property}
     *
     * @return {@link #AGE The age property}
     */
    @Override
    public @NotNull IntegerProperty getAgeProperty() {
        return AGE;
    }

    /**
     * Check if the crop can be bonemealed
     *
     * @param levelReader {@link LevelReader The world reference}
     * @param blockPos {@link BlockPos The block pos}
     * @param blockState {@link BlockState The current block state}
     * @param isClient {@link Boolean If the code is running client side}
     * @return {@link Boolean True if the crop can be bonemealed}
     */
    @Override
    public boolean isValidBonemealTarget(@NotNull LevelReader levelReader, @NotNull BlockPos blockPos, @NotNull BlockState blockState, boolean isClient) {
        if(isLower(blockState)) {
            BlockState aboveBlockState = levelReader.getBlockState(blockPos.above());
            return (isSameCrop(aboveBlockState) && !isFullyGrown(aboveBlockState)) || aboveBlockState.isAir();
        }
        return super.isValidBonemealTarget(levelReader, blockPos, blockState, isClient);
    }

    /**
     * Break the crop if one of the two half is broken
     *
     * @param blockState {@link BlockState The current block state}
     * @param direction {@link Direction The update direction}
     * @param neighborState {@link BlockState The neighbor block state}
     * @param levelAccessor {@link LevelAccessor The world reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param neighborPos {@link BlockPos The neighbor block pos}
     * @return {@link BlockState The updated block state}
     */
    @Override
    public @NotNull BlockState updateShape(@NotNull BlockState blockState, @NotNull Direction direction, @NotNull BlockState neighborState, @NotNull LevelAccessor levelAccessor, @NotNull BlockPos blockPos, @NotNull BlockPos neighborPos) {
        boolean isLower = isLower(blockState);
        if (direction.getAxis() != Direction.Axis.Y || isLower != (direction == Direction.UP) || neighborState.is(this) && neighborState.getValue(HALF) != blockState.getValue(HALF)) {
            return isLower && direction == Direction.DOWN && !blockState.canSurvive(levelAccessor, blockPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(blockState, direction, neighborState, levelAccessor, blockPos, neighborPos);
        }
        return Blocks.AIR.defaultBlockState();
    }

    /**
     * Try to grow the crop from bonemeal
     *
     * @param level {@link Level The world reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param blockState {@link BlockState The current block state}
     */
    @Override
    public void growCrops(@NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        int newAge = Math.min(blockState.getValue(AGE) + this.getBonemealAgeIncrease(level), MAX_AGE * 2);
        boolean isLower = isLower(blockState);
        if(canGrowAt(level, isLower ? blockPos.above() : blockPos)) {
            if(newAge <= MAX_AGE) {
                level.setBlock(blockPos, blockState.setValue(AGE, newAge), 2);
            } else {
                if(!isFullyGrown(blockState)) {
                    level.setBlock(blockPos, blockState.setValue(AGE, MAX_AGE), 2);
                } else {
                    tryGrowOtherHalf(level, blockState, blockPos, newAge);
                }
            }
        }
    }

    /**
     * Try to grow the other half of the crop when one reaches the max age
     *
     * @param level {@link Level The world reference}
     * @param blockState {@link BlockState The current block state}
     * @param blockPos {@link BlockPos The current block pos}
     * @param age {@link Integer The age to grow the other half}
     */
    private void tryGrowOtherHalf(Level level, BlockState blockState, BlockPos blockPos, int age) {
        DoubleBlockHalf half = isLower(blockState) ? DoubleBlockHalf.UPPER : DoubleBlockHalf.LOWER;
        BlockPos otherPos = half.equals(DoubleBlockHalf.UPPER) ? blockPos.above() : blockPos.below();
        BlockState otherBlockState = level.getBlockState(otherPos);
        level.setBlock(otherPos, this.defaultBlockState().setValue(AGE, Math.max(otherBlockState.isAir() ? 0 : otherBlockState.getValue(AGE), age - MAX_AGE)).setValue(HALF, half), 3);
    }

    /**
     * Check if the crop can grow at the given location
     *
     * @param level {@link Level The world reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @return {@link Boolean True if there is air or the same crop at the given location}
     */
    private boolean canGrowAt(Level level, BlockPos blockPos) {
        BlockState blockState = level.getBlockState(blockPos);
        return blockState.isAir() || isSameCrop(blockState);
    }

    /**
     * Check if crop should drop something wehn broken
     *
     * @param level {@link Level The world reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param blockState {@link BlockState The current block state}
     * @param player {@link Player The player who broke the crop}
     */
    @Override
    public void playerWillDestroy(@NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockState, @NotNull Player player) {
        if(!level.isClientSide) {
            if(player.isCreative()) {
                preventCreativeDropsFromBottomPart(level, blockPos, blockState, player);
            } else {
                dropResources(blockState, level, blockPos, null, player, player.getMainHandItem());
            }
        }
        super.playerWillDestroy(level, blockPos, blockState, player);
    }

    /**
     * Prevent the drops from the bottom part when the crop is broken from the creative mode
     *
     * @param level {@link Level The world reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param blockState {@link BlockState The current block state}
     * @param player {@link Player The player who broke the crop}
     */
    private void preventCreativeDropsFromBottomPart(Level level, BlockPos blockPos, BlockState blockState, Player player) {
        if (isLower(blockState)) {
            BlockPos aboveBlockPos = blockPos.above();
            BlockState aboveBlockState = level.getBlockState(aboveBlockPos);
            if (isSameCrop(aboveBlockState) && !isLower(aboveBlockState)) {
                level.setBlock(aboveBlockPos, Blocks.AIR.defaultBlockState(),35);
                level.levelEvent(player, 2001, aboveBlockPos, Block.getId(aboveBlockState));
            }
        }
    }

    /**
     * Check if the crop can survive at the current location
     *
     * @param blockState {@link BlockState The current block state}
     * @param levelReader {@link LevelReader The world reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @return {@link Boolean True if the crop can survive}
     */
    @Override
    public boolean canSurvive(@NotNull BlockState blockState, @NotNull LevelReader levelReader, @NotNull BlockPos blockPos) {
        if (isLower(blockState) || !isSameCrop(blockState)) {
            return super.canSurvive(blockState, levelReader, blockPos);
        }
        BlockState belowBlockState = levelReader.getBlockState(blockPos.below());
        return isSameCrop(belowBlockState) && isLower(belowBlockState);
    }

    /**
     * Check if a crop part is fully grown
     *
     * @param state {@link BlockState The block state to check}
     * @return {@link Boolean True if the provided state reached the maximum age}
     */
    private boolean isFullyGrown(BlockState state) {
        return state.getValue(AGE) >= MAX_AGE;
    }

    /**
     * Check if a block is part of the same crop
     *
     * @param state {@link BlockState The block state to check}
     * @return {@link Boolean True if is the same crop}
     */
    private boolean isSameCrop(BlockState state) {
        return state.is(this);
    }

    /**
     * Check if the crop part is the lower one
     *
     * @param blockState {@link BlockState The crop block state to check}
     * @return {@link Boolean True if is the lower part}
     */
    private boolean isLower(BlockState blockState) {
        return blockState.getValue(HALF).equals(DoubleBlockHalf.LOWER);
    }

    /**
     * Check if the block should randomly ticking
     *
     * @param blockState {@link BlockState The current block state}
     * @return {@link Boolean True if the crop hasn't fully grow}
     */
    @Override
    public boolean isRandomlyTicking(@NotNull BlockState blockState) {
        return !isFullyGrown(blockState);
    }

    /**
     * Grow the crop naturally. If the lower part reaches the max age,
     * the initial stage of the upper part is placed
     *
     * @param blockState {@link BlockState The current block state}
     * @param level {@link ServerLevel The world reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param randomSource {@link RandomSource The random reference}
     */
    @Override
    public void randomTick(@NotNull BlockState blockState, @NotNull ServerLevel level, @NotNull BlockPos blockPos, @NotNull RandomSource randomSource) {
        if (!level.isAreaLoaded(blockPos, 1)) {
            return;
        }
        if (level.getRawBrightness(blockPos, 0) >= 9) {
            if (!isFullyGrown(blockState)) {
                int age = this.getAge(blockState);
                float growthSpeed = getGrowthSpeed(this, level, blockPos);
                if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(level, blockPos, blockState, randomSource.nextInt((int)(25.0F / growthSpeed) + 1) == 0)) {
                    level.setBlock(blockPos, blockState.setValue(getAgeProperty(), age + 1), 2);
                    if(isLower(blockState)) {
                        BlockPos aboveBlockPos = blockPos.above();
                        if(age == MAX_AGE - 1 && canGrowAt(level, aboveBlockPos)) {
                            tryGrowOtherHalf(level, blockState, blockPos, 0);
                        }
                    }
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(level, blockPos, blockState);
                }
            }
        }
    }

    /**
     * Create the {@link StateDefinition block state definition}
     *
     * @param stateBuilder {@link StateDefinition.Builder The block state builder}
     */
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(AGE).add(HALF);
    }

    /**
     * Get the crop shapes
     *
     * @return {@link VoxelShape The crop shapes}
     */
    public VoxelShape[] getShapes() {
        return new VoxelShape[]{
                Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
                Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
                Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
                Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)
        };
    }

    /**
     * Get the crop shape based on its age
     *
     * @param blockState {@link BlockState The current block state}
     * @param blockGetter {@link BlockGetter The block getter}
     * @param blockPos {@link BlockPos The current block pos}
     * @param collisionContext {@link CollisionContext The collision context}
     * @return {@link VoxelShape The crop shape}
     */
    @Override
    public @NotNull VoxelShape getShape(BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, @NotNull CollisionContext collisionContext) {
        return SHAPE_BY_AGE[Math.min(SHAPE_BY_AGE.length, blockState.getValue(this.getAgeProperty()))];
    }

    /**
     * Get the crop seed id
     *
     * @return {@link ItemLike The crop seed id}
     */
    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return SEED_SUPPLIER.get();
    }

}