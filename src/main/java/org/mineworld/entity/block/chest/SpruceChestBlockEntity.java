package org.mineworld.entity.block.chest;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.mineworld.core.MWBlockEntityTypes;

/**
 * Implementation class for a {@link ChestBlockEntity spruce chest block entity}
 */
public class SpruceChestBlockEntity extends ChestBlockEntity {

    /**
     * Constructor. Set the block entity properties
     *
     * @param blockPos {@link BlockPos The current block pos}
     * @param blockState {@link BlockState The current block state}
     */
    public SpruceChestBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(MWBlockEntityTypes.SPRUCE_CHEST.get(), blockPos, blockState);
    }

}