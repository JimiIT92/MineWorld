package org.mineworld.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Map;

/**
 * {@link MineWorld MineWorld} class for a side panel block
 */
public class SidePanelBlock extends Block {

    /**
     * {@link DirectionProperty The block facing property}
     */
    public static DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    /**
     * {@link BooleanProperty The block waterlogged property}
     */
    public static BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    /**
     * {@link VoxelShape The block shape based on direction}
     */
    private static Map<Direction, VoxelShape> AABBS = Maps.newEnumMap(
            ImmutableMap.of(
                    Direction.NORTH, Block.box(0.0D, 8.0D, 15.0D, 16.0D, 16.0D, 16.0D),
                    Direction.SOUTH, Block.box(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 1.0D),
                    Direction.EAST, Block.box(0.0D, 8.0D, 0.0D, 1.0D, 16.0D, 16.0D),
                    Direction.WEST, Block.box(15.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D)
            )
    );

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties block properties}
     *
     * @param properties {@link BlockBehaviour.Properties The block properties}
     */
    public SidePanelBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.FALSE));
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
    public @NotNull VoxelShape getShape(@NotNull BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, @NotNull CollisionContext collisionContext) {
        return AABBS.get(blockState.getValue(FACING));
    }

    /**
     * Check if the block can survive at the current location
     *
     * @param state {@link BlockState The current block state}
     * @param level {@link LevelReader The level reader reference}
     * @param pos {@link BlockPos The current block pos}
     * @return {@link Boolean True if the block can survive}
     */
    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.relative(state.getValue(FACING).getOpposite())).getMaterial().isSolid();
    }

    /**
     * Get the {@link BlockState placed state} based on neighbors
     *
     * @param context {@link BlockPlaceContext The block place context}
     * @return {@link BlockState The placed state}
     */
    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockState = this.defaultBlockState();
        FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
        LevelReader level = context.getLevel();
        BlockPos pos = context.getClickedPos();

        for(Direction direction : Arrays.stream(context.getNearestLookingDirections()).filter(direction -> direction.getAxis().isHorizontal()).toList()) {
            blockState = blockState.setValue(FACING, direction.getOpposite());
            if (blockState.canSurvive(level, pos)) {
                return blockState.setValue(WATERLOGGED, fluidState.is(Fluids.WATER));
            }
        }

        return null;
    }

    /**
     * Update the block based on neighbor updates
     *
     * @param state {@link BlockState The current block state}
     * @param direction {@link Direction The update direction}
     * @param neighborState {@link BlockState The neighbor block state}
     * @param level {@link LevelAccessor The world reference}
     * @param pos {@link BlockPos The current block pos}
     * @param neighborPos {@link BlockPos The neighbor block pos}
     * @return {@link BlockState The updated block state}
     */
    @Override
    public @NotNull BlockState updateShape(BlockState state, Direction direction, @NotNull BlockState neighborState, @NotNull LevelAccessor level, @NotNull BlockPos pos, @NotNull BlockPos neighborPos) {
        return direction.getOpposite().equals(state.getValue(FACING)) && !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    /**
     * Set the panel rotation when placed
     *
     * @param state {@link BlockState The current block state}
     * @param rotation {@link Rotation The direction to rotate}
     * @return {@link BlockState The rotated block state}
     */
    @Override
    public @NotNull BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    /**
     * Mirror the panel when placed
     *
     * @param state {@link BlockState The current block state}
     * @param mirror {@link Mirror The mirror direction}
     * @return {@link BlockState The mirrored block state}
     */
    @Override
    public @NotNull BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    /**
     * Create the {@link StateDefinition block state definition}
     *
     * @param stateBuilder {@link StateDefinition.Builder The block state builder}
     */
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(FACING, WATERLOGGED);
    }

}
