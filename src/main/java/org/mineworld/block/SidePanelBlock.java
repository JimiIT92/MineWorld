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
import org.jetbrains.annotations.Nullable;
import org.mineworld.MineWorld;

import java.util.Arrays;
import java.util.Map;

/**
 * {@link MineWorld MineWorld} {@link Block Side Panel Block}
 */
public class SidePanelBlock extends Block {

    /**
     * {@link DirectionProperty The facing direction property}
     */
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    /**
     * {@link BooleanProperty The Block Waterlogged property}
     */
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    /**
     * {@link VoxelShape The Block Shape based on direction}
     */
    private static final Map<Direction, VoxelShape> AABBS = Maps.newEnumMap(
            ImmutableMap.of(
                    Direction.NORTH, Block.box(0.0D, 8.0D, 15.0D, 16.0D, 16.0D, 16.0D),
                    Direction.SOUTH, Block.box(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 1.0D),
                    Direction.EAST, Block.box(0.0D, 8.0D, 0.0D, 1.0D, 16.0D, 16.0D),
                    Direction.WEST, Block.box(15.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D)
            )
    );

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     *
     * @param properties {@link BlockBehaviour.Properties The Block Properties}
     */
    public SidePanelBlock(final Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.FALSE));
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
        return AABBS.get(blockState.getValue(FACING));
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
    public boolean canSurvive(final BlockState blockState, final LevelReader levelReader, final BlockPos blockPos) {
        return levelReader.getBlockState(blockPos.relative(blockState.getValue(FACING).getOpposite())).isSolid();
    }

    /**
     * Get the {@link BlockState Block State} after the block has been placed
     *
     * @param placeContext {@link BlockPlaceContext The block place context}
     * @return {@link BlockState The placed Block State}
     */
    @Override
    @Nullable
    public BlockState getStateForPlacement(final BlockPlaceContext placeContext) {
        BlockState blockState = this.defaultBlockState();
        final FluidState fluidState = placeContext.getLevel().getFluidState(placeContext.getClickedPos());
        final LevelReader level = placeContext.getLevel();
        final BlockPos pos = placeContext.getClickedPos();

        for(Direction direction : Arrays.stream(placeContext.getNearestLookingDirections()).filter(direction -> direction.getAxis().isHorizontal()).toList()) {
            blockState = blockState.setValue(FACING, direction.getOpposite());
            if (blockState.canSurvive(level, pos)) {
                return blockState.setValue(WATERLOGGED, fluidState.is(Fluids.WATER));
            }
        }

        return null;
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
    public @NotNull BlockState updateShape(final BlockState blockState, final Direction direction, final @NotNull BlockState neighborBlockState, final @NotNull LevelAccessor levelAccessor, final @NotNull BlockPos blockPos, final @NotNull BlockPos neighborBlockPos) {
        return direction.getOpposite().equals(blockState.getValue(FACING)) && !blockState.canSurvive(levelAccessor, blockPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(blockState, direction, neighborBlockState, levelAccessor, blockPos, neighborBlockPos);
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

}