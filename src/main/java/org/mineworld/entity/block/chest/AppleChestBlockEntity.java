package org.mineworld.entity.block.chest;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlockEntityTypes;
import org.mineworld.core.MWWoodTypes;

/**
 * {@link MineWorld MineWorld} {@link ChestBlockEntity Apple Chest Block Entity}
 */
public class AppleChestBlockEntity extends MWChestBlockEntity {

    /**
     * Constructor. Set the Entity properties
     *
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     */
    public AppleChestBlockEntity(final BlockPos blockPos, final BlockState blockState) {
        super(MWBlockEntityTypes.APPLE_CHEST.get(), blockPos, blockState);
    }

    /**
     * Get the {@link WoodType Chest Wood Type}
     *
     * @return {@link MWWoodTypes#APPLE The Apple Wood Type}
     */
    @Override
    public WoodType getWoodType() {
        return MWWoodTypes.APPLE.get();
    }

}