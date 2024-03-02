package org.mineworld.entity.block.chest;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.mineworld.MineWorld;

/**
 * {@link MineWorld MineWorld} {@link ChestBlockEntity Chest Block Entity}
 */
public abstract class MWChestBlockEntity extends ChestBlockEntity {

    /**
     * Constructor. Set the Entity properties
     *
     * @param blockEntityType {@link BlockEntityType<ChestBlockEntity> The Block Entity Type Supplier}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     */
    public MWChestBlockEntity(final BlockEntityType<? extends ChestBlockEntity> blockEntityType, final BlockPos blockPos, final BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    /**
     * Get the {@link WoodType Chest Wood Type}
     *
     * @return {@link WoodType The Chest Wood Type}
     */
    public abstract WoodType getWoodType();

}