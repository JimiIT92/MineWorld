package org.mineworld.entity.block.chest;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;

/**
 * Base class for a {@link MineWorld MineWorld} trapped chest
 */
abstract class AbstractTrappedChestBlockEntity extends ChestBlockEntity {

    /**
     * Constructor. Set the chest properties
     * 
     * @param blockEntityType {@link BlockEntityType The chest block entity type}
     * @param blockPos {@link BlockPos The current block pos}
     * @param blockState {@link BlockState The current block state}
     */
    public AbstractTrappedChestBlockEntity(final BlockEntityType<?> blockEntityType, final BlockPos blockPos, final BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    /**
     * Update the neighbor blocks on chest opening
     * 
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param blockState {@link BlockState The current block state}
     * @param par4 {@link Integer An integer parameter}
     * @param par5 {@link Integer An integer parameter}
     */
    @Override
    protected void signalOpenCount(final @NotNull Level level, final @NotNull BlockPos blockPos, final @NotNull BlockState blockState, final int par4, final int par5) {
        super.signalOpenCount(level, blockPos, blockState, par4, par5);
        if (par4 != par5) {
            Block block = blockState.getBlock();
            level.updateNeighborsAt(blockPos, block);
            level.updateNeighborsAt(blockPos.below(), block);
        }
    }
}
