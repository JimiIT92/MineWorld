package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineworld.MineWorld;
import org.mineworld.entity.block.GiftBlockEntity;
import org.mineworld.helper.PropertyHelper;

/**
 * {@link MineWorld MineWorld} {@link BaseEntityBlock Gift Block}
 */
public class GiftBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {

    /**
     * {@link DirectionProperty The facing direction property}
     */
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    /**
     * {@link BooleanProperty The Block Waterlogged property}
     */
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     */
    public GiftBlock() {
        super(PropertyHelper.block(MapColor.COLOR_CYAN, 0.5F, 0.5F, false, SoundType.WOOL).noLootTable());
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.FALSE));
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
    public @NotNull BlockState mirror(final BlockState blockState, final Mirror mirror) {
        return blockState.rotate(mirror.getRotation(blockState.getValue(FACING)));
    }

    /**
     * Create the {@link StateDefinition Block State definition}
     *
     * @param stateBuilder {@link StateDefinition.Builder The Block State builder}
     */
    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(FACING, WATERLOGGED);
    }

    /**
     * Get the {@link RenderShape render shape} for this block
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link RenderShape#MODEL Model render shape}
     */
    @Override
    public @NotNull RenderShape getRenderShape(final @NotNull BlockState blockState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
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
    public @NotNull BlockState updateShape(final BlockState blockState, final @NotNull Direction direction, final @NotNull BlockState neighborBlockState, final @NotNull LevelAccessor levelAccessor, final @NotNull BlockPos blockPos, final @NotNull BlockPos neighborBlockPos) {
        if (blockState.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }
        return super.updateShape(blockState, direction, neighborBlockState, levelAccessor, blockPos, neighborBlockPos);
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
        return Block.box(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);
    }

    /**
     * Get the {@link FluidState Block Fluid State}
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Fluids#WATER Water if is Waterlogged}
     */
    @Override
    public @NotNull FluidState getFluidState(final BlockState blockState) {
        return blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    /**
     * Get the {@link BlockState Block State} after the block has been placed
     *
     * @param placeContext {@link BlockPlaceContext The block place context}
     * @return {@link BlockState The placed Block State}
     */
    @Nullable
    @Override
    public BlockState getStateForPlacement(final @NotNull BlockPlaceContext placeContext) {
        final Direction direction = placeContext.getHorizontalDirection().getOpposite();
        final FluidState fluidstate = placeContext.getLevel().getFluidState(placeContext.getClickedPos());
        return this.defaultBlockState().setValue(FACING, direction).setValue(WATERLOGGED, fluidstate.is(Fluids.WATER));
    }

    /**
     * Place the {@link DoubleBlockHalf#UPPER Upper variant} when a {@link DoublePlantBlock Cattail} is placed
     *
     * @param level {@link Level The Level reference}
     * @param blockPos {@link BlockPos The current BlockPos}
     * @param blockState {@link BlockState The current BlockState}
     * @param entity {@link LivingEntity The Entity} who placed the {@link DoublePlantBlock Cattail}
     * @param itemStack {@link ItemStack The ItemStack}
     */
    @Override
    public void setPlacedBy(final @NotNull Level level, final @NotNull BlockPos blockPos, final @NotNull BlockState blockState, final LivingEntity entity, final ItemStack itemStack) {
        if(!itemStack.hasTag()) {
            return;
        }
        final BlockEntity blockentity = level.getBlockEntity(blockPos);
        if (blockentity instanceof GiftBlockEntity gift) {
            gift.load(itemStack.getTag());
        }
    }

    /**
     * Drop the {@link Block Block} content when its destroyed
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param newBlockState {@link BlockState The updated Block State}
     * @param isMoving {@link Boolean If the block entity is moving}
     */
    @Override
    public void onRemove(final BlockState blockState, final @NotNull Level level, final @NotNull BlockPos blockPos, final BlockState newBlockState, final boolean isMoving) {
        if (!blockState.is(newBlockState.getBlock())) {
            BlockEntity blockentity = level.getBlockEntity(blockPos);
            if (blockentity instanceof Container) {
                Containers.dropContents(level, blockPos, (Container)blockentity);
                level.updateNeighbourForOutputSignal(blockPos, this);
            }

            super.onRemove(blockState, level, blockPos, newBlockState, isMoving);
        }
    }

    /**
     * Check if the Block is pathfindable
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param pathComputationType {@link PathComputationType The path computation type}
     * @return {@link Boolean#FALSE False}
     */
    public boolean isPathfindable(final @NotNull BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos, final @NotNull PathComputationType pathComputationType) {
        return false;
    }

    /**
     * Create the {@link GiftBlockEntity Gift Block Entity}
     *
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     * @return {@link BlockEntity The Gift Block Entity}
     */
    @Nullable
    @Override
    public BlockEntity newBlockEntity(final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        return new GiftBlockEntity(blockPos, blockState);
    }

}