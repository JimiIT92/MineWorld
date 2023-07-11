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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineworld.core.MWBlockEntityTypes;
import org.mineworld.core.MWStats;
import org.mineworld.entity.block.ForgingTableBlockEntity;
import org.mineworld.helper.ComponentHelper;
import org.mineworld.helper.PropertyHelper;
import org.mineworld.inventory.ForgingTableMenu;

/**
 * Implementation class for a {@link BaseEntityBlock forging table block}
 */
public class ForgingTableBlock extends BaseEntityBlock {

    /**
     * {@link Component The forging table screen title}
     */
    public static Component CONTAINER_TITLE = ComponentHelper.container("forging_table");
    /**
     * {@link DirectionProperty The facing property}
     */
    public static DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    /**
     * {@link BooleanProperty The lit property}
     */
    public static BooleanProperty LIT = BlockStateProperties.LIT;

    /**
     * Constructor. Set the block properties
     */
    public ForgingTableBlock() {
        super(PropertyHelper.copyFromBlock(Blocks.SMITHING_TABLE));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIT, Boolean.FALSE));
    }

    /**
     * Get the {@link BlockState block state} for when the block is placed
     *
     * @param context {@link BlockPlaceContext The block place context}
     * @return {@link BlockState The placed block state}
     */
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    /**
     * Rotate the {@link BlockState block state} based on the {@link Rotation current rotation}
     *
     * @param blockState {@link BlockState The current block state}
     * @param rotation {@link Rotation The current rotation}
     * @return {@link Rotation The rotated block state}
     */
    @Override
    public @NotNull BlockState rotate(BlockState blockState, Rotation rotation) {
        return blockState.setValue(FACING, rotation.rotate(blockState.getValue(FACING)));
    }

    /**
     * Mirror the {@link BlockState block state}
     *
     * @param blockState {@link BlockState The current block state}
     * @param mirror {@link Mirror The current mirroring}
     * @return {@link BlockState The mirrored block state}
     */
    @Override
    public @NotNull BlockState mirror(BlockState blockState, Mirror mirror) {
        return blockState.rotate(mirror.getRotation(blockState.getValue(FACING)));
    }

    /**
     * Get the {@link StateDefinition block state definition}
     *
     * @param stateBuilder {@link StateDefinition.Builder The block state definition builder}
     */
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(FACING, LIT);
    }

    /**
     * Get the {@link RenderShape block render shape}
     *
     * @param blockState {@link BlockState The current block state}
     * @return {@link RenderShape The block render shape}
     */
    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState blockState) {
        return RenderShape.MODEL;
    }

    /**
     * Drop the forging table contents on block removed
     *
     * @param blockState {@link BlockState The current block state}
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param newState {@link BlockState The updated block state}
     * @param isMoving {@link Boolean If the block entity is moving}
     */
    @Override
    public void onRemove(BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, BlockState newState, boolean isMoving) {
        if (!blockState.is(newState.getBlock())) {
            if (level.getBlockEntity(blockPos) instanceof ForgingTableBlockEntity blockEntity) {
                if (level instanceof ServerLevel) {
                    Containers.dropContents(level, blockPos, blockEntity);
                    blockEntity.getRecipesToAwardAndPopExperience((ServerLevel)level, Vec3.atCenterOf(blockPos));
                }
                level.updateNeighbourForOutputSignal(blockPos, this);
            }

            super.onRemove(blockState, level, blockPos, newState, isMoving);
        }
    }


    /**
     * Get the {@link BlockEntity forging table block entity}
     *
     * @param blockPos {@link BlockPos The current block pos}
     * @param blockState {@link BlockState The current block state}
     * @return {@link ForgingTableBlockEntity The forging table block entity}
     */
    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new ForgingTableBlockEntity(blockPos, blockState);
    }

    /**
     * Open the forging table screen on right click
     *
     * @param blockState {@link BlockState The current block state}
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param player {@link Player The player reference}
     * @param hand {@link InteractionHand The hand the player is interacting with}
     * @param blockHitResult {@link BlockHitResult The block hit result}
     * @return {@link InteractionResult The interaction result}
     */
    @Override
    public @NotNull InteractionResult use(@NotNull BlockState blockState, Level level, @NotNull BlockPos blockPos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult blockHitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }
        if (level.getBlockEntity(blockPos) instanceof ForgingTableBlockEntity blockEntity) {
            //player.openMenu(blockState.getMenuProvider(level, blockPos));
            player.openMenu(blockEntity);
            player.awardStat(MWStats.INTERACT_WITH_FORGING_TABLE.get());
        }
        return InteractionResult.CONSUME;
    }

    /**
     * Set the container custom name when the block is placed
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param blockState {@link BlockState The current block state}
     * @param placer {@link LivingEntity The entity that is placing the block}
     * @param itemStack {@link ItemStack The block item stack}
     */
    public void setPlacedBy(@NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockState, LivingEntity placer, ItemStack itemStack) {
        if (itemStack.hasCustomHoverName()) {
            BlockEntity blockentity = level.getBlockEntity(blockPos);
            if (blockentity instanceof ForgingTableBlockEntity blockEntity) {
                blockEntity.setCustomName(itemStack.getHoverName());
            }
        }
    }

    /**
     * Get the forging table ticker
     *
     * @param level {@link Level The level reference}
     * @param blockState {@link BlockState The current block state}
     * @param blockEntityType {@link BlockEntityType The block entity type}
     * @return {@link BlockEntityTicker The block entity ticker}
     * @param <T> {@link T The block entity ticker type}
     */
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState blockState, @NotNull BlockEntityType<T> blockEntityType) {
        return level.isClientSide ? null : createTickerHelper(blockEntityType, MWBlockEntityTypes.FORGING_TABLE.get(), ForgingTableBlockEntity::serverTick);
    }

    /**
     * Get the {@link MenuProvider forging table menu provider}
     *
     * @param blockState {@link BlockState The current block state}
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @return {@link MenuProvider The forging table menu provider}
     */
    @Nullable
    @Override
    public MenuProvider getMenuProvider(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos) {
        return new SimpleMenuProvider((id, inventory, player) -> new ForgingTableMenu(id, inventory), CONTAINER_TITLE);
    }

    /**
     * Check if the block has a comparator signal
     *
     * @param blockState {@link BlockState The current block state}
     * @return {@link Boolean True}
     */
    public boolean hasAnalogOutputSignal(@NotNull BlockState blockState) {
        return true;
    }

    /**
     * Get the {@link Integer comparator signal}
     *
     * @param blockState {@link BlockState The current block state}
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @return {@link Integer The comparator signal}
     */
    public int getAnalogOutputSignal(@NotNull BlockState blockState, Level level, @NotNull BlockPos blockPos) {
        return ForgingTableMenu.getRedstoneSignalFromBlockEntity(level.getBlockEntity(blockPos));
    }

}
