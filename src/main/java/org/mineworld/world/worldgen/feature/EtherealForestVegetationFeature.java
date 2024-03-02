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
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlocks;

/**
 * {@link MineWorld MineWorld} {@link Feature Ethereal Forest Vegetation Feature}
 */
public class EtherealForestVegetationFeature extends Feature<NetherForestVegetationConfig> {

    /**
     * Constructor. Set the {@link Feature Feature} {@link Codec Codec}
     *
     * @param codec {@link Codec<NetherForestVegetationConfig> The Feature Codec}
     */
    public EtherealForestVegetationFeature(final Codec<NetherForestVegetationConfig> codec) {
        super(codec);
    }

    /**
     * Place the {@link Feature Feature}
     *
     * @param context {@link FeaturePlaceContext<NetherForestVegetationConfig> The Feature Place Context}
     * @return {@link Boolean True if the Feature has been correctly placed}
     */
    public boolean place(FeaturePlaceContext<NetherForestVegetationConfig> context) {
        final WorldGenLevel level = context.level();
        final BlockPos blockPos = context.origin();
        final BlockState blockState = level.getBlockState(blockPos.below());
        final NetherForestVegetationConfig config = context.config();
        final RandomSource random = context.random();
        if (!blockState.is(Blocks.SCULK) && !blockState.is(MWBlocks.SCULK_SOIL.get())) {
            return false;
        }
        final int y = blockPos.getY();
        if (y >= level.getMinBuildHeight() + 1 && y + 1 < level.getMaxBuildHeight()) {
            int i = 0;
            for (int width = 0; width < config.spreadWidth * config.spreadWidth; ++width) {
                final BlockPos offsetBlockPos = blockPos.offset(random.nextInt(config.spreadWidth) - random.nextInt(config.spreadWidth), random.nextInt(config.spreadHeight) - random.nextInt(config.spreadHeight), random.nextInt(config.spreadWidth) - random.nextInt(config.spreadWidth));
                final BlockState offsetBlockState = config.stateProvider.getState(random, offsetBlockPos);
                if (level.isEmptyBlock(offsetBlockPos) && offsetBlockPos.getY() > level.getMinBuildHeight() && offsetBlockState.canSurvive(level, offsetBlockPos)) {
                    level.setBlock(offsetBlockPos, offsetBlockState, 2);
                    ++i;
                }
            }

            return i > 0;
        }
        return false;
    }

}