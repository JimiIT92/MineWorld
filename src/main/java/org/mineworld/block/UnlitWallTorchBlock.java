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
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.mineworld.core.MWBlocks;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Implementation class for an {@link UnlitTorchBlock unlit wall torch block}
 */
public class UnlitWallTorchBlock extends UnlitTorchBlock {

    /**
     * The {@link DirectionProperty facing direction property}
     */
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    /**
     * {@link VoxelShape The block bounding boxes}
     */
    private static final Map<Direction, VoxelShape> AABBS = Maps.newEnumMap(ImmutableMap.of(
            Direction.NORTH, Block.box(5.5D, 3.0D, 11.0D, 10.5D, 13.0D, 16.0D),
            Direction.SOUTH, Block.box(5.5D, 3.0D, 0.0D, 10.5D, 13.0D, 5.0D),
            Direction.WEST, Block.box(11.0D, 3.0D, 5.5D, 16.0D, 13.0D, 10.5D),
            Direction.EAST, Block.box(0.0D, 3.0D, 5.5D, 5.0D, 13.0D, 10.5D))
    );

    /**
     * Constructor. Set the block properties
     *
     * @param isSoulTorch {@link Boolean If the torch is a soul torch}
     */
    public UnlitWallTorchBlock(boolean isSoulTorch) {
        this(() -> isSoulTorch ? MWBlocks.UNLIT_SOUL_TORCH.get() : MWBlocks.UNLIT_TORCH.get());
    }

    /**
     * Constructor. Set the block properties
     *
     * @param torchBlockSupplier {@link Supplier<Block> The torch block supplier}
     */
    public UnlitWallTorchBlock(final Supplier<Block> torchBlockSupplier) {
        super(BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.WOOD).dropsLike(torchBlockSupplier.get()).pushReaction(PushReaction.DESTROY));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    /**
     * Get the {@link String Block Description}
     *
     * @return {@link String The Block Description}
     */
    @Override
    public @NotNull String getDescriptionId() {
        return this.asItem().getDescriptionId();
    }

    /**
     * Get the {@link VoxelShape Block Bounding Box}
     *
     * @param state {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The block getter reference}
     * @param pos {@link BlockPos The current Block Pos}
     * @param context {@link CollisionContext The collision context}
     * @return {@link VoxelShape The Block Bounding Box}
     */
    @Override
    public @NotNull VoxelShape getShape(final @NotNull BlockState state, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos pos, final @NotNull CollisionContext context) {
        return getShape(state);
    }

    /**
     * Get the {@link VoxelShape Block Bounding Box}
     *
     * @param state {@link BlockState The current Block State}
     * @return {@link VoxelShape The Block Bounding Box}
     */
    public static VoxelShape getShape(final BlockState state) {
        return AABBS.get(state.getValue(FACING));
    }

    /**
     * Check if the Block can survive at the given {@link BlockPos location}
     *
     * @param state {@link BlockState The current Block State}
     * @param level {@link LevelReader The level reference}
     * @param pos {@link BlockPos The current Block Pos}
     * @return {@link Boolean True if the Block can survive}
     */
    public boolean canSurvive(final @NotNull BlockState state, final @NotNull LevelReader level, final BlockPos pos) {
        Direction direction = state.getValue(FACING);
        BlockPos blockpos = pos.relative(direction.getOpposite());
        BlockState blockstate = level.getBlockState(blockpos);
        return blockstate.isFaceSturdy(level, blockpos, direction);
    }

    /**
     * Get the {@link BlockState placed Block State}
     *
     * @param context {@link BlockPlaceContext The block place context}
     * @return {@link BlockState The placed Block State}
     */
    @Nullable
    @Override
    public BlockState getStateForPlacement(final BlockPlaceContext context) {
        BlockState blockstate = this.defaultBlockState();
        LevelReader levelreader = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        Direction[] adirection = context.getNearestLookingDirections();

        for(Direction direction : adirection) {
            if (direction.getAxis().isHorizontal()) {
                Direction direction1 = direction.getOpposite();
                blockstate = blockstate.setValue(FACING, direction1);
                if (blockstate.canSurvive(levelreader, blockpos)) {
                    return blockstate;
                }
            }
        }

        return null;
    }

    /**
     * Update the {@link BlockState Block State} based on neighbor updates
     *
     * @param state {@link BlockState The current Block State}
     * @param direction {@link Direction The update direction}
     * @param neighborBlockState {@link BlockState The neighbor Block State}
     * @param level {@link LevelAccessor The level reference}
     * @param pos {@link BlockPos The current Block Pos}
     * @param neighborPos {@link BlockPos The neighbor Block Pos}
     * @return {@link BlockState The updated Block State}
     */
    @Override
    public @NotNull BlockState updateShape(final @NotNull BlockState state, final @NotNull Direction direction, final @NotNull BlockState neighborBlockState, final @NotNull LevelAccessor level, final @NotNull BlockPos pos, final @NotNull BlockPos neighborPos) {
        return direction.getOpposite() == state.getValue(FACING) && !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : state;
    }

    /**
     * Rotate the {@link BlockState block state} based on the {@link Rotation current rotation}
     *
     * @param state {@link BlockState The current block state}
     * @param rotation {@link Rotation The current rotation}
     * @return {@link Rotation The rotated block state}
     */
    @Override
    public @NotNull BlockState rotate(final BlockState state, final Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    /**
     * Mirror the {@link BlockState block state}
     *
     * @param state {@link BlockState The current block state}
     * @param mirror {@link Mirror The current mirroring}
     * @return {@link BlockState The mirrored block state}
     */
    @Override
    public @NotNull BlockState mirror(final BlockState state, final Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    /**
     * Create the {@link StateDefinition block state definition}
     *
     * @param stateBuilder {@link StateDefinition.Builder The block state builder}
     */
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(FACING);
    }

}