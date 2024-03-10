package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.helper.PropertyHelper;

import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link CropBlock Two Blocks Tall Crop}
 */
public class TallCropBlock extends CropBlock {

    /**
     * {@link Integer The maximum age per part for this crop}
     */
    public static final int MAX_AGE = 3;
    /**
     * {@link IntegerProperty The crop age property}
     */
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    /**
     * {@link EnumProperty <DoubleBlockHalf> The block half property}
     */
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    /**
     * {@link VoxelShape Crop shapes by age}
     */
    private final VoxelShape[] SHAPE_BY_AGE;
    /**
     * {@link Supplier<ItemLike> The Crop Seed supplier}
     */
    private final Supplier<Item> SEED_SUPPLIER;

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     *
     * @param seedSupplier {@link Supplier<Item> The Crop Seed supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     */
    public TallCropBlock(final Supplier<Item> seedSupplier, final FeatureFlag... featureFlags) {
        super(PropertyHelper.copy(Blocks.WHEAT, featureFlags));
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0).setValue(HALF, DoubleBlockHalf.LOWER));
        SHAPE_BY_AGE = getShapes();
        SEED_SUPPLIER = seedSupplier;
    }

    /**
     * Get the {@link Integer Crop max age}
     *
     * @return {@link #MAX_AGE The crop max age}
     */
    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    /**
     * Get the {@link #AGE Crop age property}
     *
     * @return {@link #AGE The Crop age property}
     */
    @Override
    public @NotNull IntegerProperty getAgeProperty() {
        return AGE;
    }

    /**
     * Check if the Block can be bonemealed
     *
     * @param levelReader {@link LevelReader The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Boolean True if the Block can be bonemealed}
     */
    @Override
    public boolean isValidBonemealTarget(final @NotNull LevelReader levelReader, final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        if(isLower(blockState)) {
            final BlockState aboveBlockState = levelReader.getBlockState(blockPos.above());
            return (isSameCrop(aboveBlockState) && !isFullyGrown(aboveBlockState)) || aboveBlockState.isAir();
        }
        return super.isValidBonemealTarget(levelReader, blockPos, blockState);
    }

    /**
     * Update the {@link BlockState Block State} on neighbor changes
     *
     * @param blockState {@link BlockState The current Block State}
     * @param direction {@link Direction The direction the changes are coming}
     * @param neighborBlockState {@link BlockState The neighbor Block State}
     * @param levelAccessor {@link LevelAccessor The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param neighborBlockPos {@link BlockPos The neighbor Block Pos}
     * @return {@link BlockState The updated Block State}
     */
    @Override
    public @NotNull BlockState updateShape(final @NotNull BlockState blockState, final @NotNull Direction direction, final @NotNull BlockState neighborBlockState, final @NotNull LevelAccessor levelAccessor, final @NotNull BlockPos blockPos, final @NotNull BlockPos neighborBlockPos) {
        final boolean isLower = isLower(blockState);
        if (direction.getAxis() != Direction.Axis.Y || isLower != (direction == Direction.UP) || neighborBlockState.is(this) && neighborBlockState.getValue(HALF) != blockState.getValue(HALF)) {
            return isLower && direction == Direction.DOWN && !blockState.canSurvive(levelAccessor, blockPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(blockState, direction, neighborBlockState, levelAccessor, blockPos, neighborBlockPos);
        }
        return Blocks.AIR.defaultBlockState();
    }

    /**
     * Try to grow the Crop from {@link Items#BONE_MEAL Bone Meal}
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     */
    @Override
    public void growCrops(final @NotNull Level level, final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        final int newAge = Math.min(blockState.getValue(AGE) + this.getBonemealAgeIncrease(level), MAX_AGE * 2);
        final boolean isLower = isLower(blockState);
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
     * Try to grow the other half of the Crop when one reaches the max age
     *
     * @param level {@link Level The level reference}
     * @param blockState {@link BlockState The current Block State}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param age {@link Integer The age to grow the other half}
     */
    private void tryGrowOtherHalf(final Level level, final BlockState blockState, final BlockPos blockPos, final int age) {
        final DoubleBlockHalf half = isLower(blockState) ? DoubleBlockHalf.UPPER : DoubleBlockHalf.LOWER;
        final BlockPos otherPos = half.equals(DoubleBlockHalf.UPPER) ? blockPos.above() : blockPos.below();
        final BlockState otherBlockState = level.getBlockState(otherPos);
        level.setBlock(otherPos, this.defaultBlockState().setValue(AGE, Math.max(otherBlockState.isAir() ? 0 : otherBlockState.getValue(AGE), age - MAX_AGE)).setValue(HALF, half), 3);
    }

    /**
     * Check if the Crop can grow at the given location
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link Boolean True if there is air or the same Crop at the given location}
     */
    private boolean canGrowAt(final Level level, final BlockPos blockPos) {
        final BlockState blockState = level.getBlockState(blockPos);
        return blockState.isAir() || isSameCrop(blockState);
    }

    /**
     * Check if Crop should drop something when broken
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     * @param player {@link Player The player who broke the Crop}
     */
    @Override
    public @NotNull BlockState playerWillDestroy(final @NotNull Level level, final @NotNull BlockPos blockPos, final @NotNull BlockState blockState, final @NotNull Player player) {
        if(!level.isClientSide) {
            if(player.isCreative()) {
                preventCreativeDropsFromBottomPart(level, blockPos, blockState, player);
            } else {
                dropResources(blockState, level, blockPos, null, player, player.getMainHandItem());
            }
        }
        return super.playerWillDestroy(level, blockPos, blockState, player);
    }

    /**
     * Prevent the drops from the bottom part when the Crop is broken in Creative Mode
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     * @param player {@link Player The player who broke the Crop}
     */
    private void preventCreativeDropsFromBottomPart(final Level level, final BlockPos blockPos, final BlockState blockState, final Player player) {
        if (isLower(blockState)) {
            final BlockPos aboveBlockPos = blockPos.above();
            final BlockState aboveBlockState = level.getBlockState(aboveBlockPos);
            if (isSameCrop(aboveBlockState) && !isLower(aboveBlockState)) {
                level.setBlock(aboveBlockPos, Blocks.AIR.defaultBlockState(),35);
                level.levelEvent(player, 2001, aboveBlockPos, Block.getId(aboveBlockState));
            }
        }
    }

    /**
     * Check if the Block can stay at the given {@link BlockPos location}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param levelReader {@link LevelReader The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link Boolean True if the block can survive}
     */
    @Override
    public boolean canSurvive(final @NotNull BlockState blockState, final @NotNull LevelReader levelReader, final @NotNull BlockPos blockPos) {
        if (isLower(blockState) || !isSameCrop(blockState)) {
            return super.canSurvive(blockState, levelReader, blockPos);
        }
        final BlockState belowBlockState = levelReader.getBlockState(blockPos.below());
        return isSameCrop(belowBlockState) && isLower(belowBlockState);
    }

    /**
     * Check if a Crop part is fully grown
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Boolean True if the provided state reached the maximum age}
     */
    private boolean isFullyGrown(final BlockState blockState) {
        return blockState.getValue(AGE) >= MAX_AGE;
    }

    /**
     * Check if a block is part of the same Crop
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Boolean True if is the same Crop}
     */
    private boolean isSameCrop(final BlockState blockState) {
        return blockState.is(this);
    }

    /**
     * Check if the Crop part is the lower one
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Boolean True if is the lower part}
     */
    private boolean isLower(final BlockState blockState) {
        return blockState.getValue(HALF).equals(DoubleBlockHalf.LOWER);
    }

    /**
     * Check if the Block should randomly ticking
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Boolean True if is the Crop hasn't fully grown}
     */
    @Override
    public boolean isRandomlyTicking(final @NotNull BlockState blockState) {
        return !isFullyGrown(blockState);
    }

    /**
     * Randomly ticks the Block
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link ServerLevel The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param randomSource {@link RandomSource The random reference}
     */
    @Override
    public void randomTick(final @NotNull BlockState blockState, final @NotNull ServerLevel level, final @NotNull BlockPos blockPos, final @NotNull RandomSource randomSource) {
        if (!level.isAreaLoaded(blockPos, 1)) {
            return;
        }
        if (level.getRawBrightness(blockPos, 0) >= 9) {
            if (!isFullyGrown(blockState)) {
                final int age = this.getAge(blockState);
                final float growthSpeed = getGrowthSpeed(this, level, blockPos);
                if (ForgeHooks.onCropsGrowPre(level, blockPos, blockState, randomSource.nextInt((int)(25.0F / growthSpeed) + 1) == 0)) {
                    level.setBlock(blockPos, blockState.setValue(getAgeProperty(), age + 1), 2);
                    if(isLower(blockState)) {
                        final BlockPos aboveBlockPos = blockPos.above();
                        if(age == MAX_AGE - 1 && canGrowAt(level, aboveBlockPos)) {
                            tryGrowOtherHalf(level, blockState, blockPos, 0);
                        }
                    }
                    ForgeHooks.onCropsGrowPost(level, blockPos, blockState);
                }
            }
        }
    }

    /**
     * Create the {@link StateDefinition Block State definition}
     *
     * @param stateBuilder {@link StateDefinition.Builder The Block State builder}
     */
    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(AGE).add(HALF);
    }

    /**
     * Get the {@link VoxelShape Block Shapes}
     *
     * @return {@link VoxelShape The Block Shapes}
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
     * Get the {@link VoxelShape Block Shape}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param collisionContext {@link CollisionContext The collision context}
     * @return {@link VoxelShape The Block Shape}
     */
    @Override
    public @NotNull VoxelShape getShape(final BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos, final @NotNull CollisionContext collisionContext) {
        return SHAPE_BY_AGE[Math.min(SHAPE_BY_AGE.length, blockState.getValue(this.getAgeProperty()))];
    }

    /**
     * Get the {@link ItemLike Crop Seed}
     *
     * @return {@link #SEED_SUPPLIER The Crop Seed}
     */
    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return SEED_SUPPLIER.get();
    }

}