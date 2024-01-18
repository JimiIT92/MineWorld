package org.mineworld.block;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlocks;
import org.mineworld.helper.PropertyHelper;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Implementation class for a {@link MineWorld MineWorld} {@link RotatedPillarBlock Hollow Block}
 */
public class HollowBlock extends RotatedPillarBlock implements SimpleWaterloggedBlock {

    /**
     * {@link Supplier<Map> Hollowable Blocks}
     */
    public static final Supplier<Map<Block, Block>> HOLLOWABLES = Suppliers.memoize(() -> ImmutableMap.<Block, Block>builder()
            .put(Blocks.OAK_LOG, MWBlocks.HOLLOW_OAK_LOG.get())
            .put(Blocks.STRIPPED_OAK_LOG, MWBlocks.HOLLOW_STRIPPED_OAK_LOG.get())
            .put(MWBlocks.HOLLOW_OAK_LOG.get(), MWBlocks.HOLLOW_STRIPPED_OAK_LOG.get())
            .put(Blocks.SPRUCE_LOG, MWBlocks.HOLLOW_SPRUCE_LOG.get())
            .put(Blocks.STRIPPED_SPRUCE_LOG, MWBlocks.HOLLOW_STRIPPED_SPRUCE_LOG.get())
            .put(MWBlocks.HOLLOW_SPRUCE_LOG.get(), MWBlocks.HOLLOW_STRIPPED_SPRUCE_LOG.get())
            .put(Blocks.BIRCH_LOG, MWBlocks.HOLLOW_BIRCH_LOG.get())
            .put(Blocks.STRIPPED_BIRCH_LOG, MWBlocks.HOLLOW_STRIPPED_BIRCH_LOG.get())
            .put(MWBlocks.HOLLOW_BIRCH_LOG.get(), MWBlocks.HOLLOW_STRIPPED_BIRCH_LOG.get())
            .put(Blocks.JUNGLE_LOG, MWBlocks.HOLLOW_JUNGLE_LOG.get())
            .put(Blocks.STRIPPED_JUNGLE_LOG, MWBlocks.HOLLOW_STRIPPED_JUNGLE_LOG.get())
            .put(MWBlocks.HOLLOW_JUNGLE_LOG.get(), MWBlocks.HOLLOW_STRIPPED_JUNGLE_LOG.get())
            .put(Blocks.ACACIA_LOG, MWBlocks.HOLLOW_ACACIA_LOG.get())
            .put(Blocks.STRIPPED_ACACIA_LOG, MWBlocks.HOLLOW_STRIPPED_ACACIA_LOG.get())
            .put(MWBlocks.HOLLOW_ACACIA_LOG.get(), MWBlocks.HOLLOW_STRIPPED_ACACIA_LOG.get())
            .put(Blocks.DARK_OAK_LOG, MWBlocks.HOLLOW_DARK_OAK_LOG.get())
            .put(Blocks.STRIPPED_DARK_OAK_LOG, MWBlocks.HOLLOW_STRIPPED_DARK_OAK_LOG.get())
            .put(MWBlocks.HOLLOW_DARK_OAK_LOG.get(), MWBlocks.HOLLOW_STRIPPED_DARK_OAK_LOG.get())
            .put(Blocks.MANGROVE_LOG, MWBlocks.HOLLOW_MANGROVE_LOG.get())
            .put(Blocks.STRIPPED_MANGROVE_LOG, MWBlocks.HOLLOW_STRIPPED_MANGROVE_LOG.get())
            .put(MWBlocks.HOLLOW_MANGROVE_LOG.get(), MWBlocks.HOLLOW_STRIPPED_MANGROVE_LOG.get())
            .put(Blocks.CHERRY_LOG, MWBlocks.HOLLOW_CHERRY_LOG.get())
            .put(Blocks.STRIPPED_CHERRY_LOG, MWBlocks.HOLLOW_STRIPPED_CHERRY_LOG.get())
            .put(MWBlocks.HOLLOW_CHERRY_LOG.get(), MWBlocks.HOLLOW_STRIPPED_CHERRY_LOG.get())
            .put(Blocks.BAMBOO_BLOCK, MWBlocks.HOLLOW_BAMBOO_BLOCK.get())
            .put(Blocks.STRIPPED_BAMBOO_BLOCK, MWBlocks.HOLLOW_STRIPPED_BAMBOO_BLOCK.get())
            .put(MWBlocks.HOLLOW_BAMBOO_BLOCK.get(), MWBlocks.HOLLOW_STRIPPED_BAMBOO_BLOCK.get())
            .put(Blocks.CRIMSON_STEM, MWBlocks.HOLLOW_CRIMSON_STEM.get())
            .put(Blocks.STRIPPED_CRIMSON_STEM, MWBlocks.HOLLOW_STRIPPED_CRIMSON_STEM.get())
            .put(MWBlocks.HOLLOW_CRIMSON_STEM.get(), MWBlocks.HOLLOW_STRIPPED_CRIMSON_STEM.get())
            .put(Blocks.WARPED_STEM, MWBlocks.HOLLOW_WARPED_STEM.get())
            .put(Blocks.STRIPPED_WARPED_STEM, MWBlocks.HOLLOW_STRIPPED_WARPED_STEM.get())
            .put(MWBlocks.HOLLOW_WARPED_STEM.get(), MWBlocks.HOLLOW_STRIPPED_WARPED_STEM.get())
            /*.put(MWBlocks.APPLE_LOG.get(), MWBlocks.HOLLOW_APPLE_LOG.get())
            .put(MWBlocks.STRIPPED_APPLE_LOG.get(), MWBlocks.HOLLOW_STRIPPED_APPLE_LOG.get())
            .put(MWBlocks.HOLLOW_APPLE_LOG.get(), MWBlocks.HOLLOW_STRIPPED_APPLE_LOG.get())
            .put(MWBlocks.PALM_LOG.get(), MWBlocks.HOLLOW_PALM_LOG.get())
            .put(MWBlocks.STRIPPED_PALM_LOG.get(), MWBlocks.HOLLOW_STRIPPED_PALM_LOG.get())
            .put(MWBlocks.HOLLOW_PALM_LOG.get(), MWBlocks.HOLLOW_STRIPPED_PALM_LOG.get())
            .put(MWBlocks.DEAD_LOG.get(), MWBlocks.HOLLOW_DEAD_LOG.get())
            .put(MWBlocks.STRIPPED_DEAD_LOG.get(), MWBlocks.HOLLOW_STRIPPED_DEAD_LOG.get())
            .put(MWBlocks.HOLLOW_DEAD_LOG.get(), MWBlocks.HOLLOW_STRIPPED_DEAD_LOG.get())
            .put(MWBlocks.SCULK_LOG.get(), MWBlocks.HOLLOW_SCULK_LOG.get())
            .put(MWBlocks.STRIPPED_SCULK_LOG.get(), MWBlocks.HOLLOW_STRIPPED_SCULK_LOG.get())
            .put(MWBlocks.HOLLOW_SCULK_LOG.get(), MWBlocks.HOLLOW_STRIPPED_SCULK_LOG.get())*/
            .build());

    /**
     * {@link BooleanProperty The Block Waterlogged Property}
     */
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    /**
     * {@link Supplier<BlockState> The Supplier for the Block State this Block is based on}
     */
    private final Supplier<BlockState> blockStateSupplier;

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     *
     * @param blockStateSupplier {@link Supplier<BlockState> The Supplier for the Block State this Block is based on}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     */
    public HollowBlock(final Supplier<BlockState> blockStateSupplier, final FeatureFlag... featureFlags) {
        super(PropertyHelper.copy(blockStateSupplier.get().getBlock(), featureFlags).noOcclusion());
        this.blockStateSupplier = blockStateSupplier;
        this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.Y).setValue(WATERLOGGED, Boolean.FALSE));
    }

    /**
     * Check if the Block can catch fire
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @return {@link Boolean True if the source Block is flammable}
     */
    @Override
    public boolean isFlammable(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
        return blockStateSupplier.get().isFlammable(blockGetter, blockPos, direction);
    }

    /**
     * Get the Block {@link Integer flammability value}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @return {@link Integer 5}
     */
    @Override
    public int getFlammability(final BlockState blockState, final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
        return  5;
    }

    /**
     * Get the Block {@link Integer fire spread speed value}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param direction {@link Direction The direction the fire is coming from}
     * @return {@link Integer 5}
     */
    @Override
    public int getFireSpreadSpeed(final BlockState blockState,final BlockGetter blockGetter, final BlockPos blockPos, final Direction direction) {
        return 5;
    }

    /**
     * Get the {@link BlockState modified Block State} after interacting with a tool
     *
     * @param blockState {@link BlockState The current Block State}
     * @param context {@link UseOnContext The Item Use Context}
     * @param toolAction {@link ToolAction The tool action}
     * @param isClient {@link Boolean If the action only happened on the Client}
     * @return {@link BlockState The modified Block State}
     */
    @Nullable
    @Override
    public BlockState getToolModifiedState(final BlockState blockState, final UseOnContext context, final ToolAction toolAction, final boolean isClient) {
        ItemStack stack = context.getItemInHand();
        if(stack.getItem() instanceof AxeItem && toolAction.equals(ToolActions.AXE_STRIP)) {
            Optional<BlockState> optionalHollowState = getHollow(blockState);
            if(optionalHollowState.isPresent()) {
                return optionalHollowState.get().setValue(AXIS, blockState.getValue(AXIS)).setValue(WATERLOGGED, blockState.getValue(WATERLOGGED));
            }
        }
        return super.getToolModifiedState(blockState, context, toolAction, isClient);
    }

    /**
     * Get the {@link BlockState Block State} after the block has been placed
     *
     * @param placeContext {@link BlockPlaceContext The block place context}
     * @return {@link BlockState The placed Block State}
     */
    public BlockState getStateForPlacement(final BlockPlaceContext placeContext) {
        return this.defaultBlockState().setValue(AXIS, placeContext.getClickedFace().getAxis()).setValue(WATERLOGGED, Fluids.WATER.equals(placeContext.getLevel().getFluidState(placeContext.getClickedPos()).getType()));
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
     * Create the {@link StateDefinition Block State definition}
     *
     * @param stateBuilder {@link StateDefinition.Builder The Block State builder}
     */
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(AXIS).add(WATERLOGGED);
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
     * Get the {@link Float Block shade brightness}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link Float 1.0}
     */
    @Override
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
    @Override
    public boolean propagatesSkylightDown(final @NotNull BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos) {
        return true;
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
    public @NotNull VoxelShape getShape(final BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos, final @NotNull CollisionContext collisionContext) {
        return switch (blockState.getValue(AXIS)) {
            case X -> Shapes.or(
                    Block.box(0, 12, 4, 16, 15, 12),
                    Block.box(0, 1, 4, 16, 4, 12),
                    Block.box(0, 0, 15, 16, 16, 16),
                    Block.box(0, 0, 0, 16, 16, 1),
                    Block.box(0, 0, 1, 16, 1, 15),
                    Block.box(0, 15, 1, 16, 16, 15),
                    Block.box(0, 1, 12, 16, 15, 15),
                    Block.box(0, 1, 1, 16, 15, 4)
            );
            case Y -> Shapes.or(
                    Block.box(4, 0, 12, 12, 16, 15),
                    Block.box(4, 0, 1, 12, 16, 4),
                    Block.box(0, 0, 0, 1, 16, 16),
                    Block.box(15, 0, 0, 16, 16, 16),
                    Block.box(1, 0, 0, 15, 16, 1),
                    Block.box(1, 0, 15, 15, 16, 16),
                    Block.box(1, 0, 1, 4, 16, 15),
                    Block.box(12, 0, 1, 15, 16, 15)
            );
            case Z -> Shapes.or(
                    Block.box(4, 12, 0, 12, 15, 16),
                    Block.box(4, 1, 0, 12, 4, 16),
                    Block.box(0, 0, 0, 1, 16, 16),
                    Block.box(15, 0, 0, 16, 16, 16),
                    Block.box(1, 0, 0, 15, 1, 16),
                    Block.box(1, 15, 0, 15, 16, 16),
                    Block.box(1, 1, 0, 4, 15, 16),
                    Block.box(12, 1, 0, 15, 15, 16)
            );
        };
    }

    /**
     * Get the {@link BlockState Hollow Block State}
     * based on the {@link BlockState current Block State}
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Optional<BlockState> The Hollow Block State, if any}
     */
    public static Optional<BlockState> getHollow(final BlockState blockState) {
        return Optional.ofNullable(HOLLOWABLES.get().get(blockState.getBlock())).map(block -> block.withPropertiesOf(blockState));
    }

}