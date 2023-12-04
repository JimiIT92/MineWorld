package org.mineworld.entity.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlockEntityTypes;

/**
 * Implementation class for a {@link MineWorld MineWorld} {@link HangingSignBlockEntity hanging sign block entity}
 */
public class MWSignBlockEntity extends SignBlockEntity {

    /**
     * Constructor. Set the block entity properties
     *
     * @param pos {@link BlockPos The current BlockPos}
     * @param state {@link BlockState The current BlockState}
     */
    public MWSignBlockEntity(final BlockPos pos, final BlockState state) {
        super(MWBlockEntityTypes.SIGN.get(), pos, state);
    }

    /**
     * Get the {@link BlockEntityType sign block entity type}
     * @return {@link MWBlockEntityTypes#SIGN The sign block entity type}
     */
    @Override
    public @NotNull BlockEntityType<?> getType() {
        return MWBlockEntityTypes.SIGN.get();
    }

}