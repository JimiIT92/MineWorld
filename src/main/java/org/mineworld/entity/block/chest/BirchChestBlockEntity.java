package org.mineworld.entity.block.chest;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlockEntityTypes;

/**
 * {@link MineWorld MineWorld} {@link ChestBlockEntity Birch Chest Block Entity}
 */
public class BirchChestBlockEntity extends MWChestBlockEntity {

    /**
     * Constructor. Set the Entity properties
     *
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     */
    public BirchChestBlockEntity(final BlockPos blockPos, final BlockState blockState) {
        super(MWBlockEntityTypes.BIRCH_CHEST.get(), blockPos, blockState);
    }

    /**
     * Get the {@link WoodType Chest Wood Type}
     *
     * @return {@link WoodType#BIRCH The Birch Wood Type}
     */
    @Override
    public WoodType getWoodType() {
        return WoodType.BIRCH;
    }

}