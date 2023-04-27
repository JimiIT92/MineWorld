package org.mineworld.entity.block.chest;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.mineworld.core.MWBlockEntityTypes;

/**
 * Implementation class for an {@link ChestBlockEntity acacia trapped chest block entity}
 */
public class AcaciaTrappedChestBlockEntity extends AbstractTrappedChestBlockEntity {

    /**
     * Constructor. Set the chest properties
     *
     * @param blockPos {@link BlockPos The current block pos}
     * @param blockState {@link BlockState The current block state}
     */
    public AcaciaTrappedChestBlockEntity(final BlockPos blockPos, final BlockState blockState) {
        super(MWBlockEntityTypes.ACACIA_TRAPPED_CHEST.get(), blockPos, blockState);
    }

}