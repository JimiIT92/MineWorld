package org.mineworld.entity.block.chest;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.mineworld.core.MWBlockEntityTypes;

/**
 * Implementation class for a {@link ChestBlockEntity bamboo trapped chest block entity}
 */
public class BambooTrappedChestBlockEntity extends AbstractTrappedChestBlockEntity {

    /**
     * Constructor. Set the chest properties
     *
     * @param blockPos {@link BlockPos The current block pos}
     * @param blockState {@link BlockState The current block state}
     */
    public BambooTrappedChestBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(MWBlockEntityTypes.BAMBOO_TRAPPED_CHEST.get(), blockPos, blockState);
    }

}