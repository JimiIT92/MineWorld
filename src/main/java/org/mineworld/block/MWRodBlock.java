package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RodBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.helper.PropertyHelper;

/**
 * Implementation class for a {@link MineWorld MineWorld} {@link RodBlock rod block}
 */
public class MWRodBlock extends RodBlock {

    /**
     * {@link BooleanProperty The block waterlogged property}
     */
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    /**
     * Constructor. Set the {@link Properties Block Properties}
     *
     * @param color {@link MapColor The block color on maps}
     * @param sound {@link SoundType The block sound}
     */
    public MWRodBlock(final MapColor color, final SoundType sound) {
        super(PropertyHelper.basicBlockProperties(color, 1.5F, 3.0F, false, sound).noOcclusion().forceSolidOn());
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP).setValue(WATERLOGGED, Boolean.FALSE));
    }

    /**
     * Get the placed {@link BlockState BlockState}
     *
     * @param context {@link BlockPlaceContext The block place context}
     * @return {@link BlockState The placed BlockState}
     */
    public BlockState getStateForPlacement(final BlockPlaceContext context) {
        Direction direction = context.getClickedFace();
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().relative(direction.getOpposite()));
        final boolean isWaterlogged = Fluids.WATER.equals(context.getLevel().getFluidState(context.getClickedPos()).getType());
        return blockstate.is(this) && blockstate.getValue(FACING) == direction ?
                this.defaultBlockState().setValue(FACING, direction.getOpposite()).setValue(WATERLOGGED, isWaterlogged) :
                this.defaultBlockState().setValue(FACING, direction).setValue(WATERLOGGED, isWaterlogged);
    }

    /**
     * Updated the {@link BlockState block state} on neighbor updates
     *
     * @param blockState {@link BlockState The current block state}
     * @param direction {@link Direction The update direction}
     * @param neighborState {@link BlockState The neighbor block state}
     * @param levelAccessor {@link LevelAccessor The level accessor reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param neighborPos {@link BlockPos The neighbor block pos}
     * @return {@link BlockState The updated block state}
     */
    public @NotNull BlockState updateShape(final BlockState blockState, final @NotNull Direction direction, final @NotNull BlockState neighborState, final @NotNull LevelAccessor levelAccessor, final @NotNull BlockPos blockPos, final @NotNull BlockPos neighborPos) {
        if (blockState.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }
        return super.updateShape(blockState, direction, neighborState, levelAccessor, blockPos, neighborPos);
    }

    /**
     * Get the {@link FluidState block fluid state}
     *
     * @param blockState {@link BlockState The current block state}
     * @return {@link FluidState The block fluid state}
     */
    public @NotNull FluidState getFluidState(final BlockState blockState) {
        return blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    /**
     * Create the {@link BlockState BlockState definition}
     *
     * @param stateBuilder {@link StateDefinition.Builder The state builder}
     */
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(FACING).add(WATERLOGGED);
    }

}