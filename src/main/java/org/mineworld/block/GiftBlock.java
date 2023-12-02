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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineworld.entity.block.ForgingTableBlockEntity;
import org.mineworld.entity.block.GiftBlockEntity;
import org.mineworld.helper.PropertyHelper;

public class GiftBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {

    /**
     * {@link DirectionProperty The facing property}
     */
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    /**
     * {@link BooleanProperty The waterlogged property}
     */
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    /**
     * Constructor. Set the block properties
     */
    public GiftBlock() {
        super(PropertyHelper.basicBlockProperties(MapColor.COLOR_CYAN, 0.5F, 0.5F, false, SoundType.WOOL).noLootTable());
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.FALSE));
    }

    /**
     * Rotate the {@link BlockState block state} based on the {@link Rotation current rotation}
     *
     * @param blockState {@link BlockState The current block state}
     * @param rotation {@link Rotation The current rotation}
     * @return {@link Rotation The rotated block state}
     */
    @Override
    public @NotNull BlockState rotate(final BlockState blockState, final Rotation rotation) {
        return blockState.setValue(FACING, rotation.rotate(blockState.getValue(FACING)));
    }

    /**
     * Mirror the {@link BlockState block state}
     *
     * @param blockState {@link BlockState The current block state}
     * @param mirror {@link Mirror The current mirroring}
     * @return {@link BlockState The mirrored block state}
     */
    @Override
    public @NotNull BlockState mirror(final BlockState blockState, final Mirror mirror) {
        return blockState.rotate(mirror.getRotation(blockState.getValue(FACING)));
    }

    /**
     * Get the {@link StateDefinition block state definition}
     *
     * @param stateBuilder {@link StateDefinition.Builder The block state definition builder}
     */
    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(FACING, WATERLOGGED);
    }

    /**
     * Get the {@link RenderShape block render shape}
     *
     * @param blockState {@link BlockState The current block state}
     * @return {@link RenderShape The block render shape}
     */
    @Override
    public @NotNull RenderShape getRenderShape(final @NotNull BlockState blockState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    /**
     * Updated the {@link BlockState BlockState} if the
     * {@link DoublePlantBlock Cattail} is waterlogged
     *
     * @param state {@link BlockState The current BlockState}
     * @param facing {@link Direction The update Direction}
     * @param neighborState {@link BlockState The neighbor BlockState}
     * @param level {@link LevelAccessor The Level reference}
     * @param pos {@link BlockPos The current BlockPos}
     * @param neighborPos {@link BlockPos The neighbor BlockPos}
     * @return Placed {@link BlockState The updated BlockState}
     */
    @Override
    public @NotNull BlockState updateShape(final BlockState state, final @NotNull Direction facing, final @NotNull BlockState neighborState, final @NotNull LevelAccessor level, final @NotNull BlockPos pos, final @NotNull BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        return super.updateShape(state, facing, neighborState, level, pos, neighborPos);
    }

    /**
     * Get the {@link VoxelShape Block Shape}
     *
     * @param state {@link BlockState The current BlockState}
     * @param level {@link BlockGetter The Level reference}
     * @param pos {@link BlockPos The current BlockPos}
     * @param context {@link CollisionContext The Collision Context}
     * @return {@link VoxelShape The Block Shape}
     */
    @Override
    public @NotNull VoxelShape getShape(final @NotNull BlockState state, final @NotNull BlockGetter level, final @NotNull BlockPos pos, final @NotNull CollisionContext context) {
        return Block.box(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);
    }

    /**
     * Get the {@link FluidState Fluid State}
     *
     * @param state {@link BlockState The current BlockState}
     * @return {@link FluidState The Fluid State}
     */
    @Override
    public @NotNull FluidState getFluidState(final BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    /**
     * Get the {@link BlockState BlockState} when the
     * {@link DoublePlantBlock Cattail} is placed
     *
     * @param context {@link BlockPlaceContext The Place Context}
     * @return Placed {@link BlockState The placed BlockState}
     */
    @Nullable
    @Override
    public BlockState getStateForPlacement(final @NotNull BlockPlaceContext context) {
        final Direction direction = context.getHorizontalDirection().getOpposite();
        final FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(FACING, direction).setValue(WATERLOGGED, fluidstate.is(Fluids.WATER));
    }

    /**
     * Place the gift and set the content
     *
     * @param level {@link Level The level reference}
     * @param pos {@link BlockPos The current BlockPos}
     * @param state {@link BlockState The placed BlockPlace}
     * @param entity {@link LivingEntity The entity that placed the gift}
     * @param itemStack {@link ItemStack The gift item stack}
     */
    @Override
    public void setPlacedBy(final @NotNull Level level, final @NotNull BlockPos pos, final @NotNull BlockState state, final LivingEntity entity, final ItemStack itemStack) {
        if(!itemStack.hasTag()) {
            return;
        }
        /*final CompoundTag items = itemStack.getTag();
        if(items != null && !items.isEmpty()) {
            final NonNullList<ItemStack> content = NonNullList.create();
            ContainerHelper.loadAllItems(items, content);
            final BlockEntity blockentity = level.getBlockEntity(pos);
            if (blockentity instanceof GiftBlockEntity gift) {
                gift.setItems(content);
            }
        }*/
        final BlockEntity blockentity = level.getBlockEntity(pos);
        if (blockentity instanceof GiftBlockEntity gift) {
            gift.load(itemStack.getTag());
        }
    }

    /**
     * Drop the gift contents on block removed
     *
     * @param state {@link BlockState The current block state}
     * @param level {@link Level The level reference}
     * @param pos {@link BlockPos The current block pos}
     * @param newState {@link BlockState The updated block state}
     * @param isMoving {@link Boolean If the block entity is moving}
     */
    @Override
    public void onRemove(final BlockState state, final @NotNull Level level, final @NotNull BlockPos pos, final BlockState newState, final boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            BlockEntity blockentity = level.getBlockEntity(pos);
            if (blockentity instanceof Container) {
                Containers.dropContents(level, pos, (Container)blockentity);
                level.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    /**
     * Check if the block is pathfindable
     *
     * @param state {@link BlockState The current BlockState}
     * @param level {@link BlockGetter The level reference}
     * @param pos {@link BlockPos The current block pos}
     * @param pathComputationType {@link PathComputationType The path computation type}
     * @return {@link Boolean False}
     */
    public boolean isPathfindable(final @NotNull BlockState state, final @NotNull BlockGetter level, final @NotNull BlockPos pos, final @NotNull PathComputationType pathComputationType) {
        return false;
    }

    /**
     * Get the {@link BlockEntity forging table block entity}
     *
     * @param blockPos {@link BlockPos The current block pos}
     * @param blockState {@link BlockState The current block state}
     * @return {@link ForgingTableBlockEntity The forging table block entity}
     */
    @Nullable
    @Override
    public BlockEntity newBlockEntity(final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        return new GiftBlockEntity(blockPos, blockState);
    }

}