package org.mineworld.block;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
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
import org.jetbrains.annotations.Nullable;
import org.mineworld.core.MWBlocks;
import org.mineworld.core.MWCopperBlocks;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class WallHangingLanternBlock extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock {

    /**
     * {@link MapCodec The Block Codec}
     */
    public static final MapCodec<WallHangingLanternBlock> CODEC = simpleCodec(WallHangingLanternBlock::new);

    /**
     * {@link Supplier<Map> Wall Hanging Lanterns by Lantern}
     */
    public static final Supplier<BiMap<Item, Block>> HANGING_BY_LANTERNS = Suppliers.memoize(() -> ImmutableBiMap.<Item, Block>builder()
            .put(Items.LANTERN, MWBlocks.WALL_HANGING_LANTERN.get())
            .put(Items.SOUL_LANTERN, MWBlocks.WALL_HANGING_SOUL_LANTERN.get())
            .put(MWBlocks.GOLDEN_LANTERN.get().asItem(), MWBlocks.WALL_HANGING_GOLDEN_LANTERN.get())
            .put(MWBlocks.GOLDEN_SOUL_LANTERN.get().asItem(), MWBlocks.WALL_HANGING_GOLDEN_SOUL_LANTERN.get())
            .put(MWBlocks.NETHERITE_LANTERN.get().asItem(), MWBlocks.WALL_HANGING_NETHERITE_LANTERN.get())
            .put(MWBlocks.NETHERITE_SOUL_LANTERN.get().asItem(), MWBlocks.WALL_HANGING_NETHERITE_SOUL_LANTERN.get())
            .put(MWBlocks.ALUMINUM_LANTERN.get().asItem(), MWBlocks.WALL_HANGING_ALUMINUM_LANTERN.get())
            .put(MWBlocks.ALUMINUM_SOUL_LANTERN.get().asItem(), MWBlocks.WALL_HANGING_ALUMINUM_SOUL_LANTERN.get())
            .put(MWBlocks.SILVER_LANTERN.get().asItem(), MWBlocks.WALL_HANGING_SILVER_LANTERN.get())
            .put(MWBlocks.SILVER_SOUL_LANTERN.get().asItem(), MWBlocks.WALL_HANGING_SILVER_SOUL_LANTERN.get())
            .put(MWBlocks.BRONZE_LANTERN.get().asItem(), MWBlocks.WALL_HANGING_BRONZE_LANTERN.get())
            .put(MWBlocks.BRONZE_SOUL_LANTERN.get().asItem(), MWBlocks.WALL_HANGING_BRONZE_SOUL_LANTERN.get())
            .put(MWCopperBlocks.COPPER_LANTERN.get().asItem(), MWCopperBlocks.WALL_HANGING_COPPER_LANTERN.get())
            .put(MWCopperBlocks.COPPER_SOUL_LANTERN.get().asItem(), MWCopperBlocks.WALL_HANGING_COPPER_SOUL_LANTERN.get())
            .put(MWCopperBlocks.EXPOSED_COPPER_LANTERN.get().asItem(), MWCopperBlocks.WALL_HANGING_EXPOSED_COPPER_LANTERN.get())
            .put(MWCopperBlocks.EXPOSED_COPPER_SOUL_LANTERN.get().asItem(), MWCopperBlocks.WALL_HANGING_EXPOSED_COPPER_SOUL_LANTERN.get())
            .put(MWCopperBlocks.WEATHERED_COPPER_LANTERN.get().asItem(), MWCopperBlocks.WALL_HANGING_WEATHERED_COPPER_LANTERN.get())
            .put(MWCopperBlocks.WEATHERED_COPPER_SOUL_LANTERN.get().asItem(), MWCopperBlocks.WALL_HANGING_WEATHERED_COPPER_SOUL_LANTERN.get())
            .put(MWCopperBlocks.OXIDIZED_COPPER_LANTERN.get().asItem(), MWCopperBlocks.WALL_HANGING_OXIDIZED_COPPER_LANTERN.get())
            .put(MWCopperBlocks.OXIDIZED_COPPER_SOUL_LANTERN.get().asItem(), MWCopperBlocks.WALL_HANGING_OXIDIZED_COPPER_SOUL_LANTERN.get())
            .put(MWCopperBlocks.WAXED_COPPER_LANTERN.get().asItem(), MWCopperBlocks.WALL_HANGING_WAXED_COPPER_LANTERN.get())
            .put(MWCopperBlocks.WAXED_COPPER_SOUL_LANTERN.get().asItem(), MWCopperBlocks.WALL_HANGING_WAXED_COPPER_SOUL_LANTERN.get())
            .put(MWCopperBlocks.WAXED_EXPOSED_COPPER_LANTERN.get().asItem(), MWCopperBlocks.WALL_HANGING_WAXED_EXPOSED_COPPER_LANTERN.get())
            .put(MWCopperBlocks.WAXED_EXPOSED_COPPER_SOUL_LANTERN.get().asItem(), MWCopperBlocks.WALL_HANGING_WAXED_EXPOSED_COPPER_SOUL_LANTERN.get())
            .put(MWCopperBlocks.WAXED_WEATHERED_COPPER_LANTERN.get().asItem(), MWCopperBlocks.WALL_HANGING_WAXED_WEATHERED_COPPER_LANTERN.get())
            .put(MWCopperBlocks.WAXED_WEATHERED_COPPER_SOUL_LANTERN.get().asItem(), MWCopperBlocks.WALL_HANGING_WAXED_WEATHERED_COPPER_SOUL_LANTERN.get())
            .put(MWCopperBlocks.WAXED_OXIDIZED_COPPER_LANTERN.get().asItem(), MWCopperBlocks.WALL_HANGING_WAXED_OXIDIZED_COPPER_LANTERN.get())
            .put(MWCopperBlocks.WAXED_OXIDIZED_COPPER_SOUL_LANTERN.get().asItem(), MWCopperBlocks.WALL_HANGING_WAXED_OXIDIZED_COPPER_SOUL_LANTERN.get())
            .put(MWBlocks.END_LANTERN.get().asItem(), MWBlocks.WALL_HANGING_END_LANTERN.get())
            .put(MWBlocks.GOLDEN_END_LANTERN.get().asItem(), MWBlocks.WALL_HANGING_GOLDEN_END_LANTERN.get())
            .put(MWBlocks.NETHERITE_END_LANTERN.get().asItem(), MWBlocks.WALL_HANGING_NETHERITE_END_LANTERN.get())
            .put(MWBlocks.ALUMINUM_END_LANTERN.get().asItem(), MWBlocks.WALL_HANGING_ALUMINUM_END_LANTERN.get())
            .put(MWBlocks.SILVER_END_LANTERN.get().asItem(), MWBlocks.WALL_HANGING_SILVER_END_LANTERN.get())
            .put(MWBlocks.BRONZE_END_LANTERN.get().asItem(), MWBlocks.WALL_HANGING_BRONZE_END_LANTERN.get())
            .put(MWCopperBlocks.COPPER_END_LANTERN.get().asItem(), MWCopperBlocks.WALL_HANGING_COPPER_END_LANTERN.get())
            .put(MWCopperBlocks.EXPOSED_COPPER_END_LANTERN.get().asItem(), MWCopperBlocks.WALL_HANGING_EXPOSED_COPPER_END_LANTERN.get())
            .put(MWCopperBlocks.WEATHERED_COPPER_END_LANTERN.get().asItem(), MWCopperBlocks.WALL_HANGING_WEATHERED_COPPER_END_LANTERN.get())
            .put(MWCopperBlocks.OXIDIZED_COPPER_END_LANTERN.get().asItem(), MWCopperBlocks.WALL_HANGING_OXIDIZED_COPPER_END_LANTERN.get())
            .put(MWCopperBlocks.WAXED_COPPER_END_LANTERN.get().asItem(), MWCopperBlocks.WALL_HANGING_WAXED_COPPER_END_LANTERN.get())
            .put(MWCopperBlocks.WAXED_EXPOSED_COPPER_END_LANTERN.get().asItem(), MWCopperBlocks.WALL_HANGING_WAXED_EXPOSED_COPPER_END_LANTERN.get())
            .put(MWCopperBlocks.WAXED_WEATHERED_COPPER_END_LANTERN.get().asItem(), MWCopperBlocks.WALL_HANGING_WAXED_WEATHERED_COPPER_END_LANTERN.get())
            .put(MWCopperBlocks.WAXED_OXIDIZED_COPPER_END_LANTERN.get().asItem(), MWCopperBlocks.WALL_HANGING_WAXED_OXIDIZED_COPPER_END_LANTERN.get())
            .put(MWBlocks.SCULK_LANTERN.get().asItem(), MWBlocks.WALL_HANGING_SCULK_LANTERN.get())
            .put(MWBlocks.GOLDEN_SCULK_LANTERN.get().asItem(), MWBlocks.WALL_HANGING_GOLDEN_SCULK_LANTERN.get())
            .put(MWBlocks.NETHERITE_SCULK_LANTERN.get().asItem(), MWBlocks.WALL_HANGING_NETHERITE_SCULK_LANTERN.get())
            .put(MWBlocks.ALUMINUM_SCULK_LANTERN.get().asItem(), MWBlocks.WALL_HANGING_ALUMINUM_SCULK_LANTERN.get())
            .put(MWBlocks.SILVER_SCULK_LANTERN.get().asItem(), MWBlocks.WALL_HANGING_SILVER_SCULK_LANTERN.get())
            .put(MWBlocks.BRONZE_SCULK_LANTERN.get().asItem(), MWBlocks.WALL_HANGING_BRONZE_SCULK_LANTERN.get())
            .put(MWCopperBlocks.COPPER_SCULK_LANTERN.get().asItem(), MWCopperBlocks.WALL_HANGING_COPPER_SCULK_LANTERN.get())
            .put(MWCopperBlocks.EXPOSED_COPPER_SCULK_LANTERN.get().asItem(), MWCopperBlocks.WALL_HANGING_EXPOSED_COPPER_SCULK_LANTERN.get())
            .put(MWCopperBlocks.WEATHERED_COPPER_SCULK_LANTERN.get().asItem(), MWCopperBlocks.WALL_HANGING_WEATHERED_COPPER_SCULK_LANTERN.get())
            .put(MWCopperBlocks.OXIDIZED_COPPER_SCULK_LANTERN.get().asItem(), MWCopperBlocks.WALL_HANGING_OXIDIZED_COPPER_SCULK_LANTERN.get())
            .put(MWCopperBlocks.WAXED_COPPER_SCULK_LANTERN.get().asItem(), MWCopperBlocks.WALL_HANGING_WAXED_COPPER_SCULK_LANTERN.get())
            .put(MWCopperBlocks.WAXED_EXPOSED_COPPER_SCULK_LANTERN.get().asItem(), MWCopperBlocks.WALL_HANGING_WAXED_EXPOSED_COPPER_SCULK_LANTERN.get())
            .put(MWCopperBlocks.WAXED_WEATHERED_COPPER_SCULK_LANTERN.get().asItem(), MWCopperBlocks.WALL_HANGING_WAXED_WEATHERED_COPPER_SCULK_LANTERN.get())
            .put(MWCopperBlocks.WAXED_OXIDIZED_COPPER_SCULK_LANTERN.get().asItem(), MWCopperBlocks.WALL_HANGING_WAXED_OXIDIZED_COPPER_SCULK_LANTERN.get())
    .build());

    /**
     * {@link Supplier<Map> Lanterns by Wall Hanging Lantern}
     */
    private static final Supplier<BiMap<Block, Item>> LANTERN_BY_WALL_HANGING_LANTERNS = Suppliers.memoize(() -> HANGING_BY_LANTERNS.get().inverse());
    /**
     * {@link BooleanProperty The Block Waterlogged property}
     */
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    /**
     * {@link VoxelShape The Wall Hanging Lantern Block Shapes}
     */
    private static final Map<Direction, VoxelShape> SHAPES = Maps.newEnumMap(
            ImmutableMap.of(
                    Direction.NORTH, Block.box(5.0D, 1.0D, 0.0D, 11.0D, 16.0D, 11.0D),
                    Direction.SOUTH, Block.box(5.0D, 1.0D, 5.0D, 11.0D, 16.0D, 16.0D),
                    Direction.EAST, Block.box(5.0D, 1.0D, 5.0D, 16.0D, 16.0D, 11.0D),
                    Direction.WEST, Block.box(0.0D, 1.0D, 5.0D, 11.0D, 16.0D, 11.0D)
            )
    );

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     *
     * @param properties {@link BlockBehaviour.Properties The properties this Block is based on}
     */
    public WallHangingLanternBlock(final BlockBehaviour.Properties properties) {
        super(properties.pushReaction(PushReaction.DESTROY));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.FALSE));
    }

    /**
     * Get the {@link BlockState Wall Hanging Lantern Block State} based on the {@link ItemStack used Item Stack}
     *
     * @param itemStack {@link ItemStack The used Item Stack}
     * @return {@link Optional<BlockState> The Wall Hanging Lantern Block State}, if any
     */
    public static Optional<BlockState> getWallHangingLantern(final ItemStack itemStack, final Direction direction) {
        return Optional.ofNullable(HANGING_BY_LANTERNS.get().get(itemStack.getItem())).map(block -> block.defaultBlockState().setValue(FACING, direction.getOpposite()));
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
        return Optional.ofNullable(LANTERN_BY_WALL_HANGING_LANTERNS.get().get(blockState.getBlock())).map(block -> block.asItem().getDefaultInstance()).orElse(ItemStack.EMPTY);
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
    public @NotNull VoxelShape getShape(final BlockState blockState, final @NotNull BlockGetter blockGetter, final @NotNull BlockPos blockPos, final @NotNull CollisionContext collisionContext) {
        return SHAPES.get(blockState.getValue(FACING));
    }

    /**
     * Check if the Block can stay at the given {@link BlockPos location}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param levelReader {@link LevelReader The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link Boolean True if the block can survive}
     */
    public boolean canSurvive(final BlockState blockState, final LevelReader levelReader, final BlockPos blockPos) {
        return levelReader.getBlockState(blockPos.relative(blockState.getValue(FACING).getOpposite())).isSolid();
    }

    /**
     * Get the {@link BlockState Block State} after the block has been placed
     *
     * @param placeContext {@link BlockPlaceContext The block place context}
     * @return {@link BlockState The placed Block State}
     */
    @Nullable
    public BlockState getStateForPlacement(final BlockPlaceContext placeContext) {
        BlockState blockState = this.defaultBlockState();
        final LevelReader level = placeContext.getLevel();
        final BlockPos blockPos = placeContext.getClickedPos();
        for(Direction direction : placeContext.getNearestLookingDirections()) {
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
        return blockState.getValue(FACING).equals(direction) && !blockState.canSurvive(levelAccessor, blockPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(blockState, direction, neighborBlockState, levelAccessor, blockPos, neighborBlockPos);
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
     * Create the {@link StateDefinition Block State definition}
     *
     * @param stateBuilder {@link StateDefinition.Builder The Block State builder}
     */
    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(FACING, WATERLOGGED);
    }

    /**
     * Get the {@link MapCodec Block Codec}
     *
     * @return {@link MapCodec The Block Codec}
     */
    @Override
    protected @NotNull MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }

}