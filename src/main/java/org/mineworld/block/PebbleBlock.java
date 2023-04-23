package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.helper.PropertyHelper;

/**
 * {@link MineWorld MineWorld} class for a pebble block
 */
public class PebbleBlock extends BushBlock implements SimpleWaterloggedBlock {

    /**
     * {@link Integer The maximum amount of pebbles that can be placed on a block}
     */
    private static final int MAX_AMOUNT = 8;
    /**
     * {@link DirectionProperty The facing direction property}
     */
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    /**
     * {@link Integer The pebble amount property}
     */
    public static final IntegerProperty AMOUNT = IntegerProperty.create("pebbles", 1, MAX_AMOUNT);
    /**
     * {@link Boolean The pebble waterlogged property}
     */
    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    /**
     * Constructor. Set the block properties
     *
     * @param block {@link Block The block the pebbles are based on}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     */
    public PebbleBlock(final Block block, final FeatureFlag... featureFlags) {
        super(PropertyHelper.copyFromBlock(block, featureFlags).noCollission().requiresCorrectToolForDrops());
        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, Boolean.FALSE));
    }

    /**
     * Set the pebble rotation when placed
     *
     * @param blockState {@link BlockState The current block state}
     * @param direction {@link Rotation The direction to rotate}
     * @return {@link BlockState The rotated block state}
     */
    @Override
    public @NotNull BlockState rotate(final BlockState blockState, final Rotation direction) {
        return blockState.setValue(FACING, direction.rotate(blockState.getValue(FACING)));
    }

    /**
     * Mirror the pebble when placed
     *
     * @param blockState {@link BlockState The current block state}
     * @param mirror {@link Mirror The mirror direction}
     * @return {@link BlockState The mirrored block state}
     */
    @Override
    public @NotNull BlockState mirror(final @NotNull BlockState blockState, final @NotNull Mirror mirror) {
        return blockState.rotate(mirror.getRotation(blockState.getValue(FACING)));
    }

    /**
     * Check if the block can be replaced
     *
     * @param blockState {@link BlockState The current block state}
     * @param context {@link BlockPlaceContext The block place context}
     * @return {@link Boolean True if the player is not placing a pebble}
     */
    @Override
    public boolean canBeReplaced(final @NotNull BlockState blockState, final BlockPlaceContext context) {
        return !context.isSecondaryUseActive() && context.getItemInHand().is(this.asItem()) && blockState.getValue(AMOUNT) < MAX_AMOUNT || super.canBeReplaced(blockState, context);
    }

    /**
     * Get the {@link VoxelShape block shape}
     *
     * @param blockState {@link BlockState The current block state}
     * @param blockGetter {@link BlockGetter The block getter reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param collisionContext {@link CollisionContext The collision context}
     * @return {@link VoxelShape The block shape}
     */
    @Override
    public @NotNull VoxelShape getShape(final @NotNull BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos, final @NotNull CollisionContext collisionContext) {
        return Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
    }

    /**
     * Get the {@link BlockState block state} after the block has been placed
     *
     * @param placeContext {@link BlockPlaceContext The block place context}
     * @return {@link BlockState The placed block state}
     */
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext placeContext) {
        BlockState blockstate = placeContext.getLevel().getBlockState(placeContext.getClickedPos());
        if (blockstate.is(this)) {
            return blockstate.setValue(AMOUNT, Math.min(MAX_AMOUNT, blockstate.getValue(AMOUNT) + 1));
        } else {
            FluidState fluidstate = placeContext.getLevel().getFluidState(placeContext.getClickedPos());
            boolean flag = fluidstate.getType() == Fluids.WATER;
            return super.getStateForPlacement(placeContext).setValue(WATERLOGGED, flag);
        }
    }

    /**
     * Create the {@link StateDefinition block state definition}
     *
     * @param stateBuilder {@link StateDefinition.Builder The block state builder}
     */
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(FACING, AMOUNT, WATERLOGGED);
    }

    /**
     * Check if the pebble can stay at the given {@link BlockPos location}
     *
     * @param blockState {@link BlockState The current block state}
     * @param level {@link LevelReader The world reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @return {@link Boolean True if the block below is solid}
     */
    @Override
    public boolean canSurvive(final @NotNull BlockState blockState, final @NotNull LevelReader level, final @NotNull BlockPos blockPos) {
        BlockPos blockpos = blockPos.below();
        return this.mayPlaceOn(level.getBlockState(blockpos), level, blockpos);
    }

    /**
     * Check if the pebble can be placed at the given {@link BlockPos location}
     *
     * @param blockState {@link BlockState The current block state}
     * @param blockGetter {@link BlockGetter The block getter reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @return {@link Boolean True if the block below is solid}
     */
    protected boolean mayPlaceOn(final BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos) {
        return !blockState.getCollisionShape(blockGetter, blockPos).getFaceShape(Direction.UP).isEmpty() || blockState.isFaceSturdy(blockGetter, blockPos, Direction.UP);
    }

    /**
     * Check if the water should flow if the pebble is waterlogged
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
    public @NotNull BlockState updateShape(final @NotNull BlockState blockState, final @NotNull Direction direction, final @NotNull BlockState neighborState, final @NotNull LevelAccessor levelAccessor, final @NotNull BlockPos blockPos, final @NotNull BlockPos neighborPos) {
        if (blockState.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }
        return super.updateShape(blockState, direction, neighborState, levelAccessor, blockPos, neighborPos);
    }

    /**
     * Get the {@link FluidState pebble fluid state}
     *
     * @param blockState {@link BlockState The current block state}
     * @return {@link Fluids#WATER Water if the pebble is waterlogged}
     */
    public @NotNull FluidState getFluidState(final BlockState blockState) {
        return blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    /**
     * Get the {@link PushReaction push reaction} when this block is pushed by pistons
     *
     * @param blockState {@link BlockState The current block state}
     * @return {@link PushReaction#DESTROY Destroy push reaction}
     */
    public @NotNull PushReaction getPistonPushReaction(final @NotNull BlockState blockState) {
        return PushReaction.DESTROY;
    }

}