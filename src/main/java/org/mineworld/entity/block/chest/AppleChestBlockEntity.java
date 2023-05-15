package org.mineworld.entity.block.chest;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.mineworld.core.MWBlockEntityTypes;

/**
 * Implementation class for an {@link ChestBlockEntity apple chest block entity}
 */
public class AppleChestBlockEntity extends ChestBlockEntity {

    /**
     * Constructor. Set the block entity properties
     *
     * @param blockPos {@link BlockPos The current block pos}
     * @param blockState {@link BlockState The current block state}
     */
    public AppleChestBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(MWBlockEntityTypes.APPLE_CHEST.get(), blockPos, blockState);
    }

}