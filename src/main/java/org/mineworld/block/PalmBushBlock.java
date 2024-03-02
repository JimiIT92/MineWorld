package org.mineworld.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.common.PlantType;
import org.mineworld.core.MWTreeGrowers;

public class PalmBushBlock extends TreeBushBlock {

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     */
    public PalmBushBlock() {
        super(MWTreeGrowers.PALM_TREE_GROWER);
    }

    /**
     * Get the {@link PlantType plant type}
     *
     * @param blockGetter {@link BlockGetter The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link PlantType#NETHER The Nether plant type}
     */
    @Override
    public PlantType getPlantType(final BlockGetter blockGetter, final BlockPos blockPos) {
        return PlantType.DESERT;
    }

}