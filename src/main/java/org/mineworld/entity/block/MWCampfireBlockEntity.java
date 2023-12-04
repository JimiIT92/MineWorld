package org.mineworld.entity.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.mineworld.core.MWBlockEntityTypes;

public class MWCampfireBlockEntity extends CampfireBlockEntity {

    /**
     * Constructor. Set the block entity properties
     *
     * @param pos {@link BlockPos The current BlockPos}
     * @param state {@link BlockState The current BlockState}
     */
    public MWCampfireBlockEntity(final BlockPos pos, final BlockState state) {
        super(pos, state);
    }

    /**
     * Get the {@link BlockEntityType campfire block entity type}
     * @return {@link MWBlockEntityTypes#CAMPFIRE The campfire block entity type}
     */
    @Override
    public @NotNull BlockEntityType<?> getType() {
        return MWBlockEntityTypes.CAMPFIRE.get();
    }
}
