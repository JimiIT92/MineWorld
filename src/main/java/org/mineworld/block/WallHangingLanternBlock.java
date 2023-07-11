package org.mineworld.block;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.mineworld.core.MWBlocks;
import org.mineworld.helper.ItemHelper;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Implementation class for a {@link LanternBlock wall hanging lantern block}
 */
public class WallHangingLanternBlock extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock {

    /**
     * {@link Supplier<Map> Wall hanging lanterns}
     */
    public static Supplier<BiMap<Item, Block>> HANGING_BY_LANTERNS = Suppliers.memoize(() -> ImmutableBiMap.<Item, Block>builder()
            .put(Items.LANTERN, MWBlocks.WALL_HANGING_LANTERN.get())
            .put(Items.SOUL_LANTERN, MWBlocks.WALL_HANGING_SOUL_LANTERN.get())
            .put(ItemHelper.getItem(MWBlocks.GOLDEN_LANTERN.get()), MWBlocks.WALL_HANGING_GOLDEN_LANTERN.get())
            .put(ItemHelper.getItem(MWBlocks.GOLDEN_SOUL_LANTERN.get()), MWBlocks.WALL_HANGING_GOLDEN_SOUL_LANTERN.get())
            .put(ItemHelper.getItem(MWBlocks.NETHERITE_LANTERN.get()), MWBlocks.WALL_HANGING_NETHERITE_LANTERN.get())
            .put(ItemHelper.getItem(MWBlocks.NETHERITE_SOUL_LANTERN.get()), MWBlocks.WALL_HANGING_NETHERITE_SOUL_LANTERN.get())
            .put(ItemHelper.getItem(MWBlocks.ALUMINUM_LANTERN.get()), MWBlocks.WALL_HANGING_ALUMINUM_LANTERN.get())
            .put(ItemHelper.getItem(MWBlocks.ALUMINUM_SOUL_LANTERN.get()), MWBlocks.WALL_HANGING_ALUMINUM_SOUL_LANTERN.get())
            .put(ItemHelper.getItem(MWBlocks.SILVER_LANTERN.get()), MWBlocks.WALL_HANGING_SILVER_LANTERN.get())
            .put(ItemHelper.getItem(MWBlocks.SILVER_SOUL_LANTERN.get()), MWBlocks.WALL_HANGING_SILVER_SOUL_LANTERN.get())
            .put(ItemHelper.getItem(MWBlocks.BRONZE_LANTERN.get()), MWBlocks.WALL_HANGING_BRONZE_LANTERN.get())
            .put(ItemHelper.getItem(MWBlocks.BRONZE_SOUL_LANTERN.get()), MWBlocks.WALL_HANGING_BRONZE_SOUL_LANTERN.get())
            .put(ItemHelper.getItem(MWBlocks.COPPER_LANTERN.get()), MWBlocks.WALL_HANGING_COPPER_LANTERN.get())
            .put(ItemHelper.getItem(MWBlocks.COPPER_SOUL_LANTERN.get()), MWBlocks.WALL_HANGING_COPPER_SOUL_LANTERN.get())
            .put(ItemHelper.getItem(MWBlocks.EXPOSED_COPPER_LANTERN.get()), MWBlocks.WALL_HANGING_EXPOSED_COPPER_LANTERN.get())
            .put(ItemHelper.getItem(MWBlocks.EXPOSED_COPPER_SOUL_LANTERN.get()), MWBlocks.WALL_HANGING_EXPOSED_COPPER_SOUL_LANTERN.get())
            .put(ItemHelper.getItem(MWBlocks.WEATHERED_COPPER_LANTERN.get()), MWBlocks.WALL_HANGING_WEATHERED_COPPER_LANTERN.get())
            .put(ItemHelper.getItem(MWBlocks.WEATHERED_COPPER_SOUL_LANTERN.get()), MWBlocks.WALL_HANGING_WEATHERED_COPPER_SOUL_LANTERN.get())
            .put(ItemHelper.getItem(MWBlocks.OXIDIZED_COPPER_LANTERN.get()), MWBlocks.WALL_HANGING_OXIDIZED_COPPER_LANTERN.get())
            .put(ItemHelper.getItem(MWBlocks.OXIDIZED_COPPER_SOUL_LANTERN.get()), MWBlocks.WALL_HANGING_OXIDIZED_COPPER_SOUL_LANTERN.get())
            .put(ItemHelper.getItem(MWBlocks.WAXED_COPPER_LANTERN.get()), MWBlocks.WALL_HANGING_WAXED_COPPER_LANTERN.get())
            .put(ItemHelper.getItem(MWBlocks.WAXED_COPPER_SOUL_LANTERN.get()), MWBlocks.WALL_HANGING_WAXED_COPPER_SOUL_LANTERN.get())
            .put(ItemHelper.getItem(MWBlocks.WAXED_EXPOSED_COPPER_LANTERN.get()), MWBlocks.WALL_HANGING_WAXED_EXPOSED_COPPER_LANTERN.get())
            .put(ItemHelper.getItem(MWBlocks.WAXED_EXPOSED_COPPER_SOUL_LANTERN.get()), MWBlocks.WALL_HANGING_WAXED_EXPOSED_COPPER_SOUL_LANTERN.get())
            .put(ItemHelper.getItem(MWBlocks.WAXED_WEATHERED_COPPER_LANTERN.get()), MWBlocks.WALL_HANGING_WAXED_WEATHERED_COPPER_LANTERN.get())
            .put(ItemHelper.getItem(MWBlocks.WAXED_WEATHERED_COPPER_SOUL_LANTERN.get()), MWBlocks.WALL_HANGING_WAXED_WEATHERED_COPPER_SOUL_LANTERN.get())
            .put(ItemHelper.getItem(MWBlocks.WAXED_OXIDIZED_COPPER_LANTERN.get()), MWBlocks.WALL_HANGING_WAXED_OXIDIZED_COPPER_LANTERN.get())
            .put(ItemHelper.getItem(MWBlocks.WAXED_OXIDIZED_COPPER_SOUL_LANTERN.get()), MWBlocks.WALL_HANGING_WAXED_OXIDIZED_COPPER_SOUL_LANTERN.get())
        .build());
    /**
     * {@link Supplier<BiMap> Lantern by wall hanging lanterns}
     */
    private static Supplier<BiMap<Block, Item>> LANTERN_BY_WALL_HANGING_LANTERNS = Suppliers.memoize(() -> HANGING_BY_LANTERNS.get().inverse());
    /**
     * {@link BooleanProperty The wall hanging lantern waterlogged property}
     */
    public static BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    /**
     * {@link Map The wall hanging lantern block shapes}
     */
    private static Map<Direction, VoxelShape> SHAPES = Maps.newEnumMap(
            ImmutableMap.of(
                    Direction.NORTH, Block.box(5.0D, 1.0D, 0.0D, 11.0D, 16.0D, 11.0D),
                    Direction.SOUTH, Block.box(5.0D, 1.0D, 5.0D, 11.0D, 16.0D, 16.0D),
                    Direction.EAST, Block.box(5.0D, 1.0D, 5.0D, 16.0D, 16.0D, 11.0D),
                    Direction.WEST, Block.box(0.0D, 1.0D, 5.0D, 11.0D, 16.0D, 11.0D)
            )
    );

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties block properties}
     *
     * @param properties {@link BlockBehaviour.Properties The block properties}
     */
    public WallHangingLanternBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.FALSE));
    }

    /**
     * Get the {@link BlockState wall hanging lantern block state}
     * based on the {@link BlockState current block state}
     *
     * @param itemStack {@link BlockState The current block state}
     * @return {@link Optional <BlockState> The wall hanging lantern state, if any}
     */
    public static Optional<BlockState> getWallHangingLantern(ItemStack itemStack, Direction face) {
        return Optional.ofNullable(HANGING_BY_LANTERNS.get().get(itemStack.getItem())).map(block -> block.defaultBlockState().setValue(FACING, face.getOpposite()));
    }

    /**
     * Get the {@link ItemStack id stack} for the inventory when the {@link Player player} middle mouse click the block
     *
     * @param blockState {@link BlockState The current block state}
     * @param hitResult {@link HitResult The hit result}
     * @param blockGetter {@link BlockGetter The block getter reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param player {@link Player The player who is middle mouse clicking}
     * @return {@link ItemStack The block id stack}
     */
    @Override
    public ItemStack getCloneItemStack(BlockState blockState, HitResult hitResult, BlockGetter blockGetter, BlockPos blockPos, Player player) {
        return Optional.ofNullable(LANTERN_BY_WALL_HANGING_LANTERNS.get().get(blockState.getBlock())).map(ItemHelper::getDefaultStack).orElse(ItemStack.EMPTY);
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
    public @NotNull VoxelShape getShape(BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, @NotNull CollisionContext collisionContext) {
        return SHAPES.get(blockState.getValue(FACING));
    }

    /**
     * Check if the block can survive at current location
     *
     * @param blockState {@link BlockState The current block state}
     * @param levelReader {@link LevelReader The level reader reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @return {@link Boolean True if the block can survive}
     */
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        return levelReader.getBlockState(blockPos.relative(blockState.getValue(FACING).getOpposite())).getMaterial().isSolid();
    }

    /**
     * Get the {@link BlockState block state} for the block when is placed
     *
     * @param blockPlaceContext {@link BlockPlaceContext The block place context}
     * @return {@link BlockState The placed block state}
     */
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        BlockState blockState = this.defaultBlockState();
        LevelReader level = blockPlaceContext.getLevel();
        BlockPos blockPos = blockPlaceContext.getClickedPos();
        for(Direction direction : blockPlaceContext.getNearestLookingDirections()) {
            if (direction.getAxis().isHorizontal()) {
                blockState = blockState.setValue(FACING, direction.getOpposite());
                if (blockState.canSurvive(level, blockPos)) {
                    return blockState.setValue(WATERLOGGED, level.getFluidState(blockPos).is(Fluids.WATER));
                }
            }
        }
        return null;
    }

    /**
     * Get the {@link PushReaction push reaction} when this block is pushed by pistons
     *
     * @param blockState {@link BlockState The current block state}
     * @return {@link PushReaction#DESTROY Destroy push reaction}
     */
    public @NotNull PushReaction getPistonPushReaction(@NotNull BlockState blockState) {
        return PushReaction.DESTROY;
    }

    /**
     * Update the {@link BlockState block state} based on neighbor updates
     *
     * @param blockState {@link BlockState The current block state}
     * @param direction {@link Direction The update direction}
     * @param neighborState {@link BlockState The neighbor block state}
     * @param levelAccessor {@link LevelAccessor The level accessor reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param neighborPos {@link BlockPos The neighbor block pos}
     * @return {@link BlockState The updated block state}
     */
    public @NotNull BlockState updateShape(BlockState blockState, @NotNull Direction direction, @NotNull BlockState neighborState, @NotNull LevelAccessor levelAccessor, @NotNull BlockPos blockPos, @NotNull BlockPos neighborPos) {
        if (blockState.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }
        return blockState.getValue(FACING).equals(direction) && !blockState.canSurvive(levelAccessor, blockPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(blockState, direction, neighborState, levelAccessor, blockPos, neighborPos);
    }

    /**
     * Get the {@link FluidState block fluid state}
     *
     * @param blockState {@link BlockState The current block state}
     * @return {@link FluidState The block fluid state}
     */
    public @NotNull FluidState getFluidState(BlockState blockState) {
        return blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    /**
     * Check if the block is pathfindable by mobs
     *
     * @param blockState {@link BlockState The current block state}
     * @param blockGetter {@link BlockGetter The block getter reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param pathComputationType {@link PathComputationType The path computation type}
     * @return {@link Boolean False}
     */
    public boolean isPathfindable(@NotNull BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, @NotNull PathComputationType pathComputationType) {
        return false;
    }

    /**
     * Create the {@link StateDefinition block state definition}
     *
     * @param stateBuilder {@link StateDefinition.Builder The block state definition builder}
     */
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(FACING, WATERLOGGED);
    }

}