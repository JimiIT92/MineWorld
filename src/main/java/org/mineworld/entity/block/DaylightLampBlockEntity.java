package org.mineworld.entity.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.mineworld.block.DaylightLampBlock;
import org.mineworld.core.MWBlockEntityTypes;

/**
 * Implementation class for the {@link DaylightLampBlock daylight lamp block entity}
 */
public class DaylightLampBlockEntity extends BlockEntity {

    /**
     * Constructor. Set the {@link BlockEntity block entity properties
     * }
     * @param blockPos {@link BlockPos The block entity pos}
     * @param blockState {@link BlockState The block state for the block entity}
     */
    public DaylightLampBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(MWBlockEntityTypes.DAYLIGHT_LAMP.get(), blockPos, blockState);
    }

}