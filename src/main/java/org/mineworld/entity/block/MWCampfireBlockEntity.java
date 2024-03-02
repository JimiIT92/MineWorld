package org.mineworld.entity.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlockEntityTypes;

/**
 * {@link MineWorld MineWorld} {@link CampfireBlockEntity Campfire Block Entity}
 */
public class MWCampfireBlockEntity extends CampfireBlockEntity {

    /**
     * Constructor. Set the Block Entity properties
     *
     * @param blockPos {@link BlockPos The Block Entity Block POs}
     * @param blockState {@link BlockState The Block State for the Block Entity}
     */
    public MWCampfireBlockEntity(final BlockPos blockPos, final BlockState blockState) {
        super(blockPos, blockState);
    }

    /**
     * Get the {@link BlockEntityType Block Entity Type}
     *
     * @return {@link MWBlockEntityTypes#CAMPFIRE The Campfire Block Entity Type}
     */
    @Override
    public @NotNull BlockEntityType<?> getType() {
        return MWBlockEntityTypes.CAMPFIRE.get();
    }

}
