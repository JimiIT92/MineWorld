package org.mineworld.world.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NetherForestVegetationConfig;
import org.mineworld.core.MWBlocks;

/**
 * Implementation class for the ethereal forest vegetation placements
 */
public class EtherealForestVegetationFeature extends Feature<NetherForestVegetationConfig> {

    /**
     * Constructor. Set the feature configuration
     *
     * @param config {@link Codec<NetherForestVegetationConfig> The feature codec}
     */
    public EtherealForestVegetationFeature(Codec<NetherForestVegetationConfig> config) {
        super(config);
    }

    /**
     * Place the feature
     *
     * @param context {@link FeaturePlaceContext<NetherForestVegetationConfig> The feature place context}
     * @return {@link Boolean True if the feature has been placed correctly}
     */
    public boolean place(FeaturePlaceContext<NetherForestVegetationConfig> context) {
        WorldGenLevel worldgenlevel = context.level();
        BlockPos blockpos = context.origin();
        BlockState blockstate = worldgenlevel.getBlockState(blockpos.below());
        NetherForestVegetationConfig netherforestvegetationconfig = context.config();
        RandomSource randomsource = context.random();
        if (!blockstate.is(Blocks.SCULK) && !blockstate.is(MWBlocks.SCULK_SOIL.get())) {
            return false;
        } else {
            int i = blockpos.getY();
            if (i >= worldgenlevel.getMinBuildHeight() + 1 && i + 1 < worldgenlevel.getMaxBuildHeight()) {
                int j = 0;

                for(int k = 0; k < netherforestvegetationconfig.spreadWidth * netherforestvegetationconfig.spreadWidth; ++k) {
                    BlockPos blockpos1 = blockpos.offset(randomsource.nextInt(netherforestvegetationconfig.spreadWidth) - randomsource.nextInt(netherforestvegetationconfig.spreadWidth), randomsource.nextInt(netherforestvegetationconfig.spreadHeight) - randomsource.nextInt(netherforestvegetationconfig.spreadHeight), randomsource.nextInt(netherforestvegetationconfig.spreadWidth) - randomsource.nextInt(netherforestvegetationconfig.spreadWidth));
                    BlockState blockstate1 = netherforestvegetationconfig.stateProvider.getState(randomsource, blockpos1);
                    if (worldgenlevel.isEmptyBlock(blockpos1) && blockpos1.getY() > worldgenlevel.getMinBuildHeight() && blockstate1.canSurvive(worldgenlevel, blockpos1)) {
                        worldgenlevel.setBlock(blockpos1, blockstate1, 2);
                        ++j;
                    }
                }

                return j > 0;
            } else {
                return false;
            }
        }
    }
}