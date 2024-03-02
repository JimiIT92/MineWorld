package org.mineworld.entity.block.chest;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.TrappedChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlockEntityTypes;

/**
 * {@link MineWorld MineWorld} {@link TrappedChestBlockEntity Cherry Trapped Chest Block Entity}
 */
public class CherryTrappedChestBlockEntity extends MWTrappedChestBlockEntity {

    /**
     * Constructor. Set the Entity properties
     *
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     */
    public CherryTrappedChestBlockEntity(final BlockPos blockPos, final BlockState blockState) {
        super(MWBlockEntityTypes.CHERRY_TRAPPED_CHEST.get(), blockPos, blockState);
    }

    /**
     * Get the {@link WoodType Chest Wood Type}
     *
     * @return {@link WoodType#CHERRY The Cherry Wood Type}
     */
    @Override
    public WoodType getWoodType() {
        return WoodType.CHERRY;
    }

}