package org.mineworld.entity.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.LecternBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlockEntityTypes;

/**
 * {@link MineWorld MineWorld} {@link LecternBlockEntity Lectern Block Entity}
 */
public class MWLecternBlockEntity extends LecternBlockEntity {

    /**
     * Constructor. Set the Block Entity properties
     *
     * @param blockPos {@link BlockPos The Block Entity Block POs}
     * @param blockState {@link BlockState The Block State for the Block Entity}
     */
    public MWLecternBlockEntity(final BlockPos blockPos, final BlockState blockState) {
        super(blockPos, blockState);
    }

    /**
     * Get the {@link BlockEntityType Block Entity Type}
     *
     * @return {@link MWBlockEntityTypes#SKULL The Skull Block Entity Type}
     */
    @Override
    public @NotNull BlockEntityType<?> getType() {
        return MWBlockEntityTypes.LECTERN.get();
    }
}