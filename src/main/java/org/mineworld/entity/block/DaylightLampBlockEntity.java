package org.mineworld.entity.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.mineworld.MineWorld;
import org.mineworld.block.DaylightLampBlock;
import org.mineworld.core.MWBlockEntityTypes;

/**
 * {@link MineWorld MineWorld} {@link DaylightLampBlock Daylight Lamp} {@link BlockEntity Block Entity}
 */
public class DaylightLampBlockEntity extends BlockEntity {

    /**
     * Constructor. Set the Block Entity properties
     *
     * @param blockPos {@link BlockPos The Block Entity Block POs}
     * @param blockState {@link BlockState The Block State for the Block Entity}
     */
    public DaylightLampBlockEntity(final BlockPos blockPos, final BlockState blockState) {
        super(MWBlockEntityTypes.DAYLIGHT_LAMP.get(), blockPos, blockState);
    }

}