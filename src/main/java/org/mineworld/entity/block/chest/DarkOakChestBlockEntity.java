package org.mineworld.entity.block.chest;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlockEntityTypes;

/**
 * {@link MineWorld MineWorld} {@link ChestBlockEntity Dark Oak Chest Block Entity}
 */
public class DarkOakChestBlockEntity extends MWChestBlockEntity {

    /**
     * Constructor. Set the Entity properties
     *
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     */
    public DarkOakChestBlockEntity(final BlockPos blockPos, final BlockState blockState) {
        super(MWBlockEntityTypes.DARK_OAK_CHEST.get(), blockPos, blockState);
    }

    /**
     * Get the {@link WoodType Chest Wood Type}
     *
     * @return {@link WoodType#DARK_OAK The Dark Oak Wood Type}
     */
    @Override
    public WoodType getWoodType() {
        return WoodType.DARK_OAK;
    }

}