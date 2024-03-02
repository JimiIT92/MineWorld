package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlockEntityTypes;
import org.mineworld.core.MWStats;
import org.mineworld.entity.block.ForgingTableBlockEntity;
import org.mineworld.helper.ComponentHelper;
import org.mineworld.helper.PropertyHelper;
import org.mineworld.inventory.ForgingTableMenu;

import static net.minecraft.world.level.block.Blocks.SMITHING_TABLE;

/**
 * {@link MineWorld MineWorld} {@link Block Forging Table Block}
 */
public class ForgingTableBlock extends BaseEntityBlock {

    /**
     * {@link Component The Container Title}
     */
    public static final Component CONTAINER_TITLE = ComponentHelper.container("forging_table");
    /**
     * {@link DirectionProperty The facing direction property}
     */
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    /**
     * {@link Boolean The Block Lit property}
     */
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     */
    public ForgingTableBlock() {
        super(PropertyHelper.copy(SMITHING_TABLE));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIT, Boolean.FALSE));
    }

    /**
     * Get the {@link BlockState Block State} after the block has been placed
     *
     * @param placeContext {@link BlockPlaceContext The block place context}
     * @return {@link BlockState The placed Block State}
     */
    @Override
    public BlockState getStateForPlacement(final BlockPlaceContext placeContext) {
        return this.defaultBlockState().setValue(FACING, placeContext.getHorizontalDirection().getOpposite());
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
        stateBuilder.add(FACING, LIT);
    }

    /**
     * Get the {@link RenderShape render shape} for this block
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link RenderShape#MODEL Model render shape}
     */
    @Override
    public @NotNull RenderShape getRenderShape(final @NotNull BlockState blockState) {
        return RenderShape.MODEL;
    }

    /**
     * Drop the {@link Block Block} content when its destroyed
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param newBlockState {@link BlockState The updated Block State}
     * @param isMoving {@link Boolean If the block entity is moving}
     */
    @Override
    public void onRemove(final BlockState blockState, final @NotNull Level level, final @NotNull BlockPos blockPos, final BlockState newBlockState, final boolean isMoving) {
        if (!blockState.is(newBlockState.getBlock())) {
            if (level.getBlockEntity(blockPos) instanceof ForgingTableBlockEntity blockEntity) {
                if (level instanceof ServerLevel) {
                    Containers.dropContents(level, blockPos, blockEntity);
                    blockEntity.getRecipesToAwardAndPopExperience((ServerLevel)level, Vec3.atCenterOf(blockPos));
                }
                level.updateNeighbourForOutputSignal(blockPos, this);
            }
            super.onRemove(blockState, level, blockPos, newBlockState, isMoving);
        }
    }

    /**
     * Create the {@link ForgingTableBlockEntity Forging Table Block Entity}
     *
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     * @return {@link BlockEntity The Forging Table Block Entity}
     */
    @Nullable
    @Override
    public BlockEntity newBlockEntity(final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        return new ForgingTableBlockEntity(blockPos, blockState);
    }

    /**
     * Interact with the Block
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link ServerLevel The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param player {@link Player The player who interacted with the Block}
     * @param hand {@link InteractionHand The hand the player has interacted with}
     * @param hitResult {@link BlockHitResult The hit result for the block interaction}
     * @return {@link InteractionResult The interaction result based on the Player's held Item}
     */
    @Override
    public @NotNull InteractionResult use(final @NotNull BlockState blockState, final Level level, final @NotNull BlockPos blockPos, final @NotNull Player player, final @NotNull InteractionHand hand, final @NotNull BlockHitResult hitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }
        if (level.getBlockEntity(blockPos) instanceof ForgingTableBlockEntity blockEntity) {
            player.openMenu(blockEntity);
            player.awardStat(MWStats.INTERACT_WITH_FORGING_TABLE.get());
        }
        return InteractionResult.CONSUME;
    }

    /**
     * Place the {@link DoubleBlockHalf#UPPER Upper variant} when a {@link DoublePlantBlock Cattail} is placed
     *
     * @param level {@link Level The Level reference}
     * @param blockPos {@link BlockPos The current BlockPos}
     * @param blockState {@link BlockState The current BlockState}
     * @param entity {@link LivingEntity The Entity} who placed the {@link DoublePlantBlock Cattail}
     * @param itemStack {@link ItemStack The ItemStack}
     */
    public void setPlacedBy(final @NotNull Level level, final @NotNull BlockPos blockPos, final @NotNull BlockState blockState, final LivingEntity entity, final ItemStack itemStack) {
        if (itemStack.hasCustomHoverName()) {
            BlockEntity blockentity = level.getBlockEntity(blockPos);
            if (blockentity instanceof ForgingTableBlockEntity blockEntity) {
                blockEntity.setCustomName(itemStack.getHoverName());
            }
        }
    }

    /**
     * Get the related ticker for this block
     *
     * @param level {@link Level The level reference}
     * @param blockState {@link BlockState The current Block State}
     * @param blockEntityType {@link BlockEntityType The Block Entity Type}
     * @return {@link BlockEntityTicker The Block Entity Ticker}
     * @param <T> {@link T The Block Entity Type}
     */
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(final @NotNull Level level, final @NotNull BlockState blockState, final @NotNull BlockEntityType<T> blockEntityType) {
        return level.isClientSide ? null : createTickerHelper(blockEntityType, MWBlockEntityTypes.FORGING_TABLE.get(), ForgingTableBlockEntity::serverTick);
    }

    /**
     * Get the {@link MenuProvider Block Menu Provider}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link MenuProvider The Woodcutter Menu Provider}
     */
    @Nullable
    @Override
    public MenuProvider getMenuProvider(final @NotNull BlockState blockState, final @NotNull Level level, final @NotNull BlockPos blockPos) {
        return new SimpleMenuProvider((id, inventory, player) -> new ForgingTableMenu(id, inventory), CONTAINER_TITLE);
    }

    /**
     * Check if the {@link Block Block} has a Comparator Signal
     *
     * @param blockState {@link BlockState The current Block State}
     * @return {@link Boolean#TRUE True}
     */
    public boolean hasAnalogOutputSignal(final @NotNull BlockState blockState) {
        return true;
    }

    /**
     * Get the {@link Integer Comparator Signal}
     *
     * @param blockState {@link BlockState The current Block State}
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link Integer The Comparator Signal}
     */
    public int getAnalogOutputSignal(final @NotNull BlockState blockState, final Level level, final @NotNull BlockPos blockPos) {
        return ForgingTableMenu.getRedstoneSignalFromBlockEntity(level.getBlockEntity(blockPos));
    }

}