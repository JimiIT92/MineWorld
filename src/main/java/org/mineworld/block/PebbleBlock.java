package org.mineworld.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
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
import org.mineworld.item.PebbleItem;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} class for a {@link PebbleItem Pebble Block}
 */
public class PebbleBlock extends BushBlock implements SimpleWaterloggedBlock {

    /**
     * {@link MapCodec The Block Codec}
     */
    public static final MapCodec<PebbleBlock> CODEC = simpleCodec(PebbleBlock::new);
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
     * {@link BooleanProperty The Block Waterlogged property}
     */
    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     *
     * @param blockSupplier {@link Block The supplier for the Block the Pebble is based on}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     */
    public PebbleBlock(final Supplier<Block> blockSupplier, final FeatureFlag... featureFlags) {
        this(PropertyHelper.copy(blockSupplier.get(), featureFlags).noCollission().requiresCorrectToolForDrops().pushReaction(PushReaction.DESTROY));
    }

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     *
     * @param properties The {@link BlockBehaviour.Properties Block Properties}
     */
    public PebbleBlock(final BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, Boolean.FALSE));
    }

    /**
     * Set the Block rotation when placed
     *
     * @param blockState {@link BlockState The current Block State}
     * @param rotation {@link Rotation The direction to rotate}
     * @return {@link BlockState The rotated Block State}
     */
    @Override
    public @NotNull BlockState rotate(final BlockState blockState, final Rotation rotation) {
        return blockState.setValue(FACING, rotation.rotate(blockState.getValue(FACING)));
    }

    /**
     * Mirror the Block when placed
     *
     * @param blockState {@link BlockState The current Block State}
     * @param mirror {@link Mirror The mirror direction}
     * @return {@link BlockState The mirrored Block State}
     */
    @Override
    public @NotNull BlockState mirror(final @NotNull BlockState blockState, final @NotNull Mirror mirror) {
        return blockState.rotate(mirror.getRotation(blockState.getValue(FACING)));
    }

    /**
     * Check if the Block can be replaced
     *
     * @param blockState {@link BlockState The current Block State}
     * @param context {@link BlockPlaceContext The block place context}
     * @return {@link Boolean True if the Block can be replaced}
     */
    @Override
    public boolean canBeReplaced(final @NotNull BlockState blockState, final BlockPlaceContext context) {
        return !context.isSecondaryUseActive() && context.getItemInHand().is(this.asItem()) && blockState.getValue(AMOUNT) < MAX_AMOUNT || super.canBeReplaced(blockState, context);
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
    public @NotNull VoxelShape getShape(final @NotNull BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos, final @NotNull CollisionContext collisionContext) {
        return Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
    }

    /**
     * Get the {@link BlockState Block State} after the block has been placed
     *
     * @param placeContext {@link BlockPlaceContext The block place context}
     * @return {@link BlockState The placed Block State}
     */
    @Override
    public BlockState getStateForPlacement(final BlockPlaceContext placeContext) {
        final BlockState blockstate = placeContext.getLevel().getBlockState(placeContext.getClickedPos());
        if (blockstate.is(this)) {
            return blockstate.setValue(AMOUNT, Math.min(MAX_AMOUNT, blockstate.getValue(AMOUNT) + 1));
        } else {
            return Objects.requireNonNull(super.getStateForPlacement(placeContext)).setValue(WATERLOGGED, placeContext.getLevel().getFluidState(placeContext.getClickedPos()).is(Fluids.WATER));
        }
    }

    /**
     * Create the {@link StateDefinition Block State definition}
     *
     * @param stateBuilder {@link StateDefinition.Builder The Block State builder}
     */
    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(FACING, AMOUNT, WATERLOGGED);
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
        final BlockPos belowPos = blockPos.below();
        return this.mayPlaceOn(levelReader.getBlockState(belowPos), levelReader, belowPos);
    }

    /**
     * Check if the Block can be placed at the given {@link BlockPos location}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link Boolean True if the Block can be placed}
     */
    protected boolean mayPlaceOn(final BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos) {
        return !blockState.getCollisionShape(blockGetter, blockPos).getFaceShape(Direction.UP).isEmpty() || blockState.isFaceSturdy(blockGetter, blockPos, Direction.UP);
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
        if (blockState.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }
        return super.updateShape(blockState, direction, neighborBlockState, levelAccessor, blockPos, neighborBlockPos);
    }

    /**
     * Get the {@link FluidState Block Fluid State}
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Fluids#WATER Water if is Waterlogged}
     */
    public @NotNull FluidState getFluidState(final BlockState blockState) {
        return blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    /**
     * Get the {@link MapCodec Block Codec}
     *
     * @return {@link MapCodec The Block Codec}
     */
    @Override
    protected @NotNull MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }
    
}