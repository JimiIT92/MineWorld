package org.mineworld.entity.block.chest;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public abstract class MWTrappedChestBlockEntity extends MWChestBlockEntity {

    /**
     * Constructor. Set the Entity properties
     *
     * @param blockEntityType {@link Supplier<BlockEntityType> The Block Entity Type this chest refers to}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     */
    public MWTrappedChestBlockEntity(final BlockEntityType<? extends MWTrappedChestBlockEntity> blockEntityType, final BlockPos blockPos, final BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    /**
     * Update neighbor blocks signal when the Chest is opened
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     * @param eventId {@link Integer The Chest opening event ID}
     * @param eventParam {@link Integer The Chest opening event parameter}
     */
    @Override
    protected void signalOpenCount(final @NotNull Level level, final @NotNull BlockPos blockPos, final @NotNull BlockState blockState, final int eventId, int eventParam) {
        super.signalOpenCount(level, blockPos, blockState, eventId, eventParam);
        if (eventId != eventParam) {
            final Block block = blockState.getBlock();
            level.updateNeighborsAt(blockPos, block);
            level.updateNeighborsAt(blockPos.below(), block);
        }
    }

}