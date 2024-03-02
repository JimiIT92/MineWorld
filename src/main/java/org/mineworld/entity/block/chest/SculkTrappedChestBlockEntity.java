package org.mineworld.entity.block.chest;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.TrappedChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlockEntityTypes;
import org.mineworld.core.MWWoodTypes;

/**
 * {@link MineWorld MineWorld} {@link TrappedChestBlockEntity Sculk Trapped Chest Block Entity}
 */
public class SculkTrappedChestBlockEntity extends MWTrappedChestBlockEntity {

    /**
     * Constructor. Set the Entity properties
     *
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     */
    public SculkTrappedChestBlockEntity(final BlockPos blockPos, final BlockState blockState) {
        super(MWBlockEntityTypes.SCULK_TRAPPED_CHEST.get(), blockPos, blockState);
    }

    /**
     * Get the {@link WoodType Chest Wood Type}
     *
     * @return {@link MWWoodTypes#SCULK The Sculk Wood Type}
     */
    @Override
    public WoodType getWoodType() {
        return MWWoodTypes.SCULK.get();
    }

}