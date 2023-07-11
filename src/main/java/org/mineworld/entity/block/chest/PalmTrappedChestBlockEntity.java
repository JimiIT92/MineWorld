package org.mineworld.entity.block.chest;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.mineworld.core.MWBlockEntityTypes;

/**
 * Implementation class for a {@link ChestBlockEntity palm trapped chest block entity}
 */
public class PalmTrappedChestBlockEntity extends AbstractTrappedChestBlockEntity {

    /**
     * Constructor. Set the chest properties
     *
     * @param blockPos {@link BlockPos The current block pos}
     * @param blockState {@link BlockState The current block state}
     */
    public PalmTrappedChestBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(MWBlockEntityTypes.PALM_TRAPPED_CHEST.get(), blockPos, blockState);
    }

}