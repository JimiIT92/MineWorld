package org.mineworld.entity.block.chest;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.mineworld.core.MWBlockEntityTypes;

/**
 * Implementation class for an {@link ChestBlockEntity ice chest block entity}
 */
public class IceChestBlockEntity extends ChestBlockEntity {

    /**
     * Constructor. Set the block entity properties
     *
     * @param blockPos {@link BlockPos The current block pos}
     * @param blockState {@link BlockState The current block state}
     */
    public IceChestBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(MWBlockEntityTypes.ICE_CHEST.get(), blockPos, blockState);
    }

}