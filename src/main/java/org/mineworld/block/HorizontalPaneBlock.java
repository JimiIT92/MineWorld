package org.mineworld.block;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.mineworld.core.MWBlocks;
import org.mineworld.helper.ItemHelper;
import org.mineworld.helper.PropertyHelper;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Implementation class for a horizontal pane block
 */
public class HorizontalPaneBlock extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock {

    /**
     * {@link Supplier<BiMap> Horizontal panes by block}
     */
    private static final Supplier<BiMap<Block, Block>> HORIZONTAL_PANE_BY_BLOCK = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
            .put(Blocks.GLASS_PANE, MWBlocks.HORIZONTAL_GLASS_PANE.get())
            .put(Blocks.WHITE_STAINED_GLASS_PANE, MWBlocks.WHITE_STAINED_GLASS_HORIZONTAL_PANE.get())
            .put(Blocks.ORANGE_STAINED_GLASS_PANE, MWBlocks.ORANGE_STAINED_GLASS_HORIZONTAL_PANE.get())
            .put(Blocks.MAGENTA_STAINED_GLASS_PANE, MWBlocks.MAGENTA_STAINED_GLASS_HORIZONTAL_PANE.get())
            .put(Blocks.LIGHT_BLUE_STAINED_GLASS_PANE, MWBlocks.LIGHT_BLUE_STAINED_GLASS_HORIZONTAL_PANE.get())
            .put(Blocks.YELLOW_STAINED_GLASS_PANE, MWBlocks.YELLOW_STAINED_GLASS_HORIZONTAL_PANE.get())
            .put(Blocks.LIME_STAINED_GLASS_PANE, MWBlocks.LIME_STAINED_GLASS_HORIZONTAL_PANE.get())
            .put(Blocks.PINK_STAINED_GLASS_PANE, MWBlocks.PINK_STAINED_GLASS_HORIZONTAL_PANE.get())
            .put(Blocks.GRAY_STAINED_GLASS_PANE, MWBlocks.GRAY_STAINED_GLASS_HORIZONTAL_PANE.get())
            .put(Blocks.LIGHT_GRAY_STAINED_GLASS_PANE, MWBlocks.LIGHT_GRAY_STAINED_GLASS_HORIZONTAL_PANE.get())
            .put(Blocks.CYAN_STAINED_GLASS_PANE, MWBlocks.CYAN_STAINED_GLASS_HORIZONTAL_PANE.get())
            .put(Blocks.PURPLE_STAINED_GLASS_PANE, MWBlocks.PURPLE_STAINED_GLASS_HORIZONTAL_PANE.get())
            .put(Blocks.BLUE_STAINED_GLASS_PANE, MWBlocks.BLUE_STAINED_GLASS_HORIZONTAL_PANE.get())
            .put(Blocks.BROWN_STAINED_GLASS_PANE, MWBlocks.BROWN_STAINED_GLASS_HORIZONTAL_PANE.get())
            .put(Blocks.GREEN_STAINED_GLASS_PANE, MWBlocks.GREEN_STAINED_GLASS_HORIZONTAL_PANE.get())
            .put(Blocks.RED_STAINED_GLASS_PANE, MWBlocks.RED_STAINED_GLASS_HORIZONTAL_PANE.get())
            .put(Blocks.BLACK_STAINED_GLASS_PANE, MWBlocks.BLACK_STAINED_GLASS_HORIZONTAL_PANE.get())
            .put(MWBlocks.GOLD_BARS.get(), MWBlocks.GOLD_GRATE.get())
            .put(Blocks.IRON_BARS, MWBlocks.IRON_GRATE.get())
            .put(MWBlocks.OXIDIZED_COPPER_BARS.get(), MWBlocks.OXIDIZED_COPPER_GRATE.get())
            .put(MWBlocks.WEATHERED_COPPER_BARS.get(), MWBlocks.WEATHERED_COPPER_GRATE.get())
            .put(MWBlocks.EXPOSED_COPPER_BARS.get(), MWBlocks.EXPOSED_COPPER_GRATE.get())
            .put(MWBlocks.COPPER_BARS.get(), MWBlocks.COPPER_GRATE.get())
            .put(MWBlocks.WAXED_OXIDIZED_COPPER_BARS.get(), MWBlocks.WAXED_OXIDIZED_COPPER_GRATE.get())
            .put(MWBlocks.WAXED_WEATHERED_COPPER_BARS.get(), MWBlocks.WAXED_WEATHERED_COPPER_GRATE.get())
            .put(MWBlocks.WAXED_EXPOSED_COPPER_BARS.get(), MWBlocks.WAXED_EXPOSED_COPPER_GRATE.get())
            .put(MWBlocks.WAXED_COPPER_BARS.get(), MWBlocks.WAXED_COPPER_GRATE.get())
            .put(MWBlocks.NETHERITE_BARS.get(), MWBlocks.NETHERITE_GRATE.get())
            .put(MWBlocks.SILVER_BARS.get(), MWBlocks.SILVER_GRATE.get())
            .put(MWBlocks.ALUMINUM_BARS.get(), MWBlocks.ALUMINUM_GRATE.get())
            .put(MWBlocks.BRONZE_BARS.get(), MWBlocks.BRONZE_GRATE.get())
        .build());

    /**
     * {@link Supplier<BiMap> Block by horizontal panes}
     */
    private static final Supplier<BiMap<Block, Block>> BLOCK_BY_HORIZONTAL_PANE = Suppliers.memoize(() -> HORIZONTAL_PANE_BY_BLOCK.get().inverse());

    /**
     * {@link BooleanProperty The block waterlogged property}
     */
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    /**
     * {@link VoxelShape The block bottom voxel shape}
     */
    protected static final VoxelShape HORIZONTAL_PANE_AABB = Block.box(0.0D, 7.0D, 0.0D, 16.0D, 9.0D, 16.0D);

    /**
     * Constructor. Set the block properties
     *
     * @param properties {@link Properties The block properties}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     */
    public HorizontalPaneBlock(final Properties properties, final FeatureFlag... featureFlags) {
        super(PropertyHelper.translucentBlockProperties(properties, featureFlags));
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
    public @NotNull VoxelShape getShape(final @NotNull BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos, final @NotNull CollisionContext collisionContext) {
        return HORIZONTAL_PANE_AABB;
    }

    /**
     * Check if the block is pathfindable
     *
     * @param blockState {@link BlockState The current block state}
     * @param blockGetter {@link BlockGetter The block getter reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param pathComputationType {@link PathComputationType The path computation type}
     * @return {@link Boolean True if is in water and the block is waterlogged}
     */
    public boolean isPathfindable(final @NotNull BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos, final PathComputationType pathComputationType) {
        return pathComputationType.equals(PathComputationType.WATER) && blockState.getValue(WATERLOGGED);
    }

    /**
     * Get the {@link BlockState block state for the block} when is being placed
     *
     * @param blockPlaceContext {@link BlockPlaceContext The block place context}
     * @return {@link BlockState The placed block state}
     */
    public BlockState getStateForPlacement(final BlockPlaceContext blockPlaceContext) {
        BlockState blockState = this.defaultBlockState();
        final FluidState fluidstate = blockPlaceContext.getLevel().getFluidState(blockPlaceContext.getClickedPos());
        Direction direction = blockPlaceContext.getClickedFace();
        if (!blockPlaceContext.replacingClickedOnBlock() && direction.getAxis().isHorizontal()) {
            blockState = blockState.setValue(FACING, direction);
        } else {
            blockState = blockState.setValue(FACING, blockPlaceContext.getHorizontalDirection().getOpposite());
        }
        return blockState.setValue(WATERLOGGED, fluidstate.getType().isSame(Fluids.WATER));
    }

    /**
     * Create the {@link StateDefinition block state definition}
     *
     * @param stateBuilder {@link StateDefinition.Builder The block state builder}
     */
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(FACING, WATERLOGGED);
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
     * Update the {@link BlockState block state} on neighbor changes
     *
     * @param blockState {@link BlockState The current block state}
     * @param direction {@link Direction The direction the changes are coming}
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
     * Check if a {@link BlockState block state} has an {@link HorizontalPaneBlock horizontal pane}
     *
     * @param blockState {@link BlockState The current block state}
     * @return {@link Boolean True if the block state has an horizontal pane}
     */
    public static boolean hasHorizontalPane(final BlockState blockState) {
        return Optional.ofNullable(HORIZONTAL_PANE_BY_BLOCK.get().get(blockState.getBlock())).isPresent();
    }

    /**
     * Get the corresponding {@link HorizontalPaneBlock horizontal pane} from the {@link BlockState current block state}
     *
     * @param blockState {@link BlockState The current block state}
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @return {@link BlockState The horizontal pane state}
     */
    public static BlockState getStateFromGlassPane(final BlockState blockState, final Level level, final BlockPos blockPos) {
        return Optional.ofNullable(HORIZONTAL_PANE_BY_BLOCK.get().get(blockState.getBlock()))
                .map(block -> block.defaultBlockState().setValue(WATERLOGGED, level.getFluidState(blockPos).getType().isSame(Fluids.WATER)))
                .orElse(blockState);
    }

    /**
     * Get the {@link ItemStack item stack} for the inventory when the {@link Player player} middle mouse click the block
     *
     * @param blockState {@link BlockState The current block state}
     * @param hitResult {@link HitResult The hit result}
     * @param blockGetter {@link BlockGetter The block getter reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param player {@link Player The player who is middle mouse clicking}
     * @return {@link ItemStack The block item stack}
     */
    @Override
    public ItemStack getCloneItemStack(final BlockState blockState, final HitResult hitResult, final BlockGetter blockGetter, final BlockPos blockPos, final Player player) {
        return Optional.ofNullable(BLOCK_BY_HORIZONTAL_PANE.get().get(blockState.getBlock())).map(ItemHelper::getDefaultStack).orElse(ItemStack.EMPTY);
    }

    /**
     * Get the {@link VoxelShape block visual shape}
     *
     * @param blockState {@link BlockState The current block state}
     * @param blockGetter {@link BlockGetter The block getter reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param collisionContext {@link CollisionContext The collision context}
     * @return {@link Shapes#empty() Empty shape}
     */
    public @NotNull VoxelShape getVisualShape(final @NotNull BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos, final @NotNull CollisionContext collisionContext) {
        return Shapes.empty();
    }

    /**
     * Get the {@link Float block shade brightness}
     *
     * @param blockState {@link BlockState The current block state}
     * @param blockGetter {@link BlockGetter The block getter reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @return {@link Float 1.0}
     */
    public float getShadeBrightness(final @NotNull BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos) {
        return 1.0F;
    }

    /**
     * Check if the block can propagate the skylight
     *
     * @param blockState {@link BlockState The current block state}
     * @param blockGetter {@link BlockGetter The block getter reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @return {@link Boolean True}
     */
    public boolean propagatesSkylightDown(final @NotNull BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos) {
        return true;
    }

    /**
     * Determine if the face should be rendered based on the neighbor state
     *
     * @param blockState {@link BlockState The current block state}
     * @param neighborState {@link BlockState The neighbor block state}
     * @param direction {@link Direction The direction of the face to be rendered}
     * @return {@link Boolean True if the face should be rendered}
     */
    public boolean skipRendering(final @NotNull BlockState blockState, final BlockState neighborState, final @NotNull Direction direction) {
        return neighborState.is(this) || super.skipRendering(blockState, neighborState, direction);
    }

}