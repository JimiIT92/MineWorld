package org.mineworld.entity.block.chest;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.mineworld.core.MWBlockEntityTypes;

/**
 * Implementation class for a {@link ChestBlockEntity jungle dark oak chest block entity}
 */
public class DarkOakTrappedChestBlockEntity extends AbstractTrappedChestBlockEntity {

    /**
     * Constructor. Set the chest properties
     *
     * @param blockPos {@link BlockPos The current block pos}
     * @param blockState {@link BlockState The current block state}
     */
    public DarkOakTrappedChestBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(MWBlockEntityTypes.DARK_OAK_TRAPPED_CHEST.get(), blockPos, blockState);
    }

}