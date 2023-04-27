package org.mineworld.entity.block.chest;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.mineworld.core.MWBlockEntityTypes;

/**
 * Implementation class for a {@link ChestBlockEntity dark oak chest block entity}
 */
public class DarkOakChestBlockEntity extends ChestBlockEntity {

    /**
     * Constructor. Set the block entity properties
     *
     * @param blockPos {@link BlockPos The current block pos}
     * @param blockState {@link BlockState The current block state}
     */
    public DarkOakChestBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(MWBlockEntityTypes.DARK_OAK_CHEST.get(), blockPos, blockState);
    }

}