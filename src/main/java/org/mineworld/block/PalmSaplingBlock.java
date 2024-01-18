package org.mineworld.block;

import com.google.common.base.Suppliers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.common.PlantType;
import org.mineworld.core.MWConfiguredFeatures;
import org.mineworld.helper.PropertyHelper;
import org.mineworld.world.worldgen.tree.MWTreeGrower;

/**
 * Implementation class for a {@link SaplingBlock Palm Sapling Block}
 */
public class PalmSaplingBlock extends SaplingBlock {

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     */
    public PalmSaplingBlock() {
        super(new MWTreeGrower(Suppliers.memoize(() -> MWConfiguredFeatures.PALM_TREE)), PropertyHelper.copy(Blocks.JUNGLE_SAPLING));
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