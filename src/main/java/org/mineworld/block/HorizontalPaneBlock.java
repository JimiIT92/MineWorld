package org.mineworld.block;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
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
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlocks;
import org.mineworld.core.MWColoredBlocks;
import org.mineworld.core.MWCopperBlocks;
import org.mineworld.helper.PropertyHelper;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} class for an {@link HorizontalDirectionalBlock horizontal Pane Block}
 */
public class HorizontalPaneBlock extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock {

    /**
     * {@link MapCodec The Block Codec}
     */
    public static final MapCodec<HorizontalPaneBlock> CODEC = simpleCodec(HorizontalPaneBlock::new);
    /**
     * {@link Supplier<BiMap> Horizontal panes by block}
     */
    private static final Supplier<BiMap<Block, Block>> HORIZONTAL_PANE_BY_BLOCK = Suppliers.memoize(() -> ImmutableBiMap.<Block, Block>builder()
            .put(Blocks.GLASS_PANE, MWBlocks.HORIZONTAL_GLASS_PANE.get())
            .put(Blocks.WHITE_STAINED_GLASS_PANE, MWColoredBlocks.WHITE_STAINED_GLASS_HORIZONTAL_PANE.get())
            .put(Blocks.ORANGE_STAINED_GLASS_PANE, MWColoredBlocks.ORANGE_STAINED_GLASS_HORIZONTAL_PANE.get())
            .put(Blocks.MAGENTA_STAINED_GLASS_PANE, MWColoredBlocks.MAGENTA_STAINED_GLASS_HORIZONTAL_PANE.get())
            .put(Blocks.LIGHT_BLUE_STAINED_GLASS_PANE, MWColoredBlocks.LIGHT_BLUE_STAINED_GLASS_HORIZONTAL_PANE.get())
            .put(Blocks.YELLOW_STAINED_GLASS_PANE, MWColoredBlocks.YELLOW_STAINED_GLASS_HORIZONTAL_PANE.get())
            .put(Blocks.LIME_STAINED_GLASS_PANE, MWColoredBlocks.LIME_STAINED_GLASS_HORIZONTAL_PANE.get())
            .put(Blocks.PINK_STAINED_GLASS_PANE, MWColoredBlocks.PINK_STAINED_GLASS_HORIZONTAL_PANE.get())
            .put(Blocks.GRAY_STAINED_GLASS_PANE, MWColoredBlocks.GRAY_STAINED_GLASS_HORIZONTAL_PANE.get())
            .put(Blocks.LIGHT_GRAY_STAINED_GLASS_PANE, MWColoredBlocks.LIGHT_GRAY_STAINED_GLASS_HORIZONTAL_PANE.get())
            .put(Blocks.CYAN_STAINED_GLASS_PANE, MWColoredBlocks.CYAN_STAINED_GLASS_HORIZONTAL_PANE.get())
            .put(Blocks.PURPLE_STAINED_GLASS_PANE, MWColoredBlocks.PURPLE_STAINED_GLASS_HORIZONTAL_PANE.get())
            .put(Blocks.BLUE_STAINED_GLASS_PANE, MWColoredBlocks.BLUE_STAINED_GLASS_HORIZONTAL_PANE.get())
            .put(Blocks.BROWN_STAINED_GLASS_PANE, MWColoredBlocks.BROWN_STAINED_GLASS_HORIZONTAL_PANE.get())
            .put(Blocks.GREEN_STAINED_GLASS_PANE, MWColoredBlocks.GREEN_STAINED_GLASS_HORIZONTAL_PANE.get())
            .put(Blocks.RED_STAINED_GLASS_PANE, MWColoredBlocks.RED_STAINED_GLASS_HORIZONTAL_PANE.get())
            .put(Blocks.BLACK_STAINED_GLASS_PANE, MWColoredBlocks.BLACK_STAINED_GLASS_HORIZONTAL_PANE.get())
            .put(MWBlocks.GOLD_BARS.get(), MWBlocks.GOLD_GRATE.get())
            .put(Blocks.IRON_BARS, MWBlocks.IRON_GRATE.get())
            .put(MWCopperBlocks.OXIDIZED_COPPER_BARS.get(), MWCopperBlocks.OXIDIZED_COPPER_GRATE.get())
            .put(MWCopperBlocks.WEATHERED_COPPER_BARS.get(), MWCopperBlocks.WEATHERED_COPPER_GRATE.get())
            .put(MWCopperBlocks.EXPOSED_COPPER_BARS.get(), MWCopperBlocks.EXPOSED_COPPER_GRATE.get())
            .put(MWCopperBlocks.COPPER_BARS.get(), MWCopperBlocks.COPPER_GRATE.get())
            .put(MWCopperBlocks.WAXED_OXIDIZED_COPPER_BARS.get(), MWCopperBlocks.WAXED_OXIDIZED_COPPER_GRATE.get())
            .put(MWCopperBlocks.WAXED_WEATHERED_COPPER_BARS.get(), MWCopperBlocks.WAXED_WEATHERED_COPPER_GRATE.get())
            .put(MWCopperBlocks.WAXED_EXPOSED_COPPER_BARS.get(), MWCopperBlocks.WAXED_EXPOSED_COPPER_GRATE.get())
            .put(MWCopperBlocks.WAXED_COPPER_BARS.get(), MWCopperBlocks.WAXED_COPPER_GRATE.get())
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
     * {@link BooleanProperty The Block Waterlogged property}
     */
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    /**
     * {@link VoxelShape The Block Shape}
     */
    protected static final VoxelShape HORIZONTAL_PANE_AABB = Block.box(0.0D, 7.0D, 0.0D, 16.0D, 9.0D, 16.0D);

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     *
     * @param properties {@link Properties The properties this Block is based on}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     */
    public HorizontalPaneBlock(final Properties properties, final FeatureFlag... featureFlags) {
        super(PropertyHelper.translucent(properties, featureFlags));
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
    public @NotNull VoxelShape getShape(final @NotNull BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos, final @NotNull CollisionContext collisionContext) {
        return HORIZONTAL_PANE_AABB;
    }

    /**
     * Check if the Block is pathfindable
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param pathComputationType {@link PathComputationType The path computation type}
     * @return {@link Boolean True if is in water and the block is waterlogged}
     */
    public boolean isPathfindable(final @NotNull BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos, final PathComputationType pathComputationType) {
        return pathComputationType.equals(PathComputationType.WATER) && blockState.getValue(WATERLOGGED);
    }

    /**
     * Get the {@link BlockState Block State} after the block has been placed
     *
     * @param placeContext {@link BlockPlaceContext The block place context}
     * @return {@link BlockState The placed Block State}
     */
    public BlockState getStateForPlacement(final BlockPlaceContext placeContext) {
        final Direction direction = placeContext.getClickedFace();
        final BlockState placedState = this.defaultBlockState().setValue(FACING, !placeContext.replacingClickedOnBlock() && direction.getAxis().isHorizontal() ? direction : placeContext.getHorizontalDirection().getOpposite());
        return placedState.setValue(WATERLOGGED, placeContext.getLevel().getFluidState(placeContext.getClickedPos()).is(Fluids.WATER));
    }

    /**
     * Create the {@link StateDefinition Block State definition}
     *
     * @param stateBuilder {@link StateDefinition.Builder The Block State builder}
     */
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(FACING, WATERLOGGED);
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
    public @NotNull BlockState updateShape(final BlockState blockState, final @NotNull Direction direction, final @NotNull BlockState neighborBlockState, final @NotNull LevelAccessor levelAccessor, final @NotNull BlockPos blockPos, final @NotNull BlockPos neighborBlockPos) {
        if (blockState.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }
        return super.updateShape(blockState, direction, neighborBlockState, levelAccessor, blockPos, neighborBlockPos);
    }

    /**
     * Check if a {@link BlockState Block State} has an {@link HorizontalPaneBlock horizontal pane}
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Boolean True if the Block State has an horizontal pane}
     */
    public static boolean hasHorizontalPane(final BlockState blockState) {
        return Optional.ofNullable(HORIZONTAL_PANE_BY_BLOCK.get().get(blockState.getBlock())).isPresent();
    }

    /**
     * Get the corresponding {@link HorizontalPaneBlock horizontal pane} from the {@link BlockState current Block State}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link BlockState The horizontal pane state}
     */
    public static BlockState getStateFromGlassPane(final BlockState blockState, final Level level, final BlockPos blockPos) {
        return Optional.ofNullable(HORIZONTAL_PANE_BY_BLOCK.get().get(blockState.getBlock()))
                .map(block -> block.defaultBlockState().setValue(WATERLOGGED, level.getFluidState(blockPos).getType().isSame(Fluids.WATER)))
                .orElse(blockState);
    }

    /**
     * Get the {@link ItemStack Item Stack} for the inventory when the {@link Player player} middle mouse click the block
     *
     * @param blockState {@link BlockState The current Block State}
     * @param hitResult {@link HitResult The hit result}
     * @param levelReader {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param player {@link Player The player who is middle mouse clicking}
     * @return {@link ItemStack The Block Item Stack}
     */
    @Override
    public ItemStack getCloneItemStack(final BlockState blockState, final HitResult hitResult, final LevelReader levelReader, final BlockPos blockPos, final Player player) {
        return Optional.ofNullable(BLOCK_BY_HORIZONTAL_PANE.get().get(blockState.getBlock())).map(block -> block.asItem().getDefaultInstance()).orElse(ItemStack.EMPTY);
    }

    /**
     * Get the {@link VoxelShape Block visual shape}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param collisionContext {@link CollisionContext The collision context}
     * @return {@link Shapes#empty Empty shape}
     */
    public @NotNull VoxelShape getVisualShape(final @NotNull BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos, final @NotNull CollisionContext collisionContext) {
        return Shapes.empty();
    }

    /**
     * Get the {@link Float Block shade brightness}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link Float 1.0}
     */
    public float getShadeBrightness(final @NotNull BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos) {
        return 1.0F;
    }

    /**
     * Check if the Block can propagate the skylight
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link Boolean#TRUE True}
     */
    public boolean propagatesSkylightDown(final @NotNull BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos) {
        return true;
    }

    /**
     * Determine if a face should be rendered based on the {@link BlockState neighbor Block State}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param neighborBlockState {@link BlockState The neighbor Block State}
     * @param direction {@link Direction The direction of the face to be rendered}
     * @return {@link Boolean True if the face should be rendered}
     */
    public boolean skipRendering(final @NotNull BlockState blockState, final BlockState neighborBlockState, final @NotNull Direction direction) {
        return neighborBlockState.is(this) || super.skipRendering(blockState, neighborBlockState, direction);
    }

    /**
     * Get the {@link MapCodec Block Codec}
     *
     * @return {@link MapCodec The Block Codec}
     */
    @Override
    protected @NotNull MapCodec<? extends HorizontalPaneBlock> codec() {
        return CODEC;
    }
}