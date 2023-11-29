package org.mineworld.world.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

/**
 * Implementation class for a Bone Spike Feature
 */
public class BoneSpikeFeature extends Feature<NoneFeatureConfiguration> {

    /**
     * Constructor. Set the feature {@link Codec codec}
     */
    public BoneSpikeFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    /**
     * Place the feature
     *
     * @param context {@link FeaturePlaceContext<NoneFeatureConfiguration> The feature place context}
     * @return {@link Boolean True if the feature has been correctly placed}
     */
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos blockpos = context.origin();
        final RandomSource randomsource = context.random();

        final WorldGenLevel worldgenlevel = context.level();
        while (worldgenlevel.isEmptyBlock(blockpos) && blockpos.getY() > worldgenlevel.getMinBuildHeight() + 2) {
            blockpos = blockpos.below();
        }

        if (!worldgenlevel.getBlockState(blockpos).is(Blocks.NETHERRACK)) {
            return false;
        }

        blockpos = blockpos.above(randomsource.nextInt(4));
        int i = randomsource.nextInt(4) + 7;
        int j = i / 4 + randomsource.nextInt(2);
        if (j > 1 && randomsource.nextInt(60) == 0) {
            blockpos = blockpos.above(10 + randomsource.nextInt(30));
        }

        for(int k = 0; k < i; ++k) {
            float f = (1.0F - (float)k / (float)i) * (float)j;
            int l = Mth.ceil(f);

            for(int i1 = -l; i1 <= l; ++i1) {
                float f1 = (float)Mth.abs(i1) - 0.25F;

                for(int j1 = -l; j1 <= l; ++j1) {
                    float f2 = (float)Mth.abs(j1) - 0.25F;
                    if ((i1 == 0 && j1 == 0 || !(f1 * f1 + f2 * f2 > f * f)) && (i1 != -l && i1 != l && j1 != -l && j1 != l || !(randomsource.nextFloat() > 0.75F))) {
                        BlockState blockstate = worldgenlevel.getBlockState(blockpos.offset(i1, k, j1));
                        if (blockstate.isAir() || isDirt(blockstate) || blockstate.is(Blocks.NETHERRACK)) {
                            this.setBlock(worldgenlevel, blockpos.offset(i1, k, j1), Blocks.BONE_BLOCK.defaultBlockState());
                        }

                        if (k != 0 && l > 1) {
                            blockstate = worldgenlevel.getBlockState(blockpos.offset(i1, -k, j1));
                            if (blockstate.isAir() || isDirt(blockstate) || blockstate.is(Blocks.NETHERRACK)) {
                                this.setBlock(worldgenlevel, blockpos.offset(i1, -k, j1), Blocks.BONE_BLOCK.defaultBlockState());
                            }
                        }
                    }
                }
            }
        }

        int k1 = j - 1;
        if (k1 < 0) {
            k1 = 0;
        } else if (k1 > 1) {
            k1 = 1;
        }

        for(int l1 = -k1; l1 <= k1; ++l1) {
            for(int i2 = -k1; i2 <= k1; ++i2) {
                BlockPos blockpos1 = blockpos.offset(l1, -1, i2);
                int j2 = 50;
                if (Math.abs(l1) == 1 && Math.abs(i2) == 1) {
                    j2 = randomsource.nextInt(5);
                }

                while(blockpos1.getY() > 50) {
                    BlockState blockstate1 = worldgenlevel.getBlockState(blockpos1);
                    if (!blockstate1.isAir() && !isDirt(blockstate1) && !blockstate1.is(Blocks.NETHERRACK) && !blockstate1.is(Blocks.BONE_BLOCK)) {
                        break;
                    }

                    this.setBlock(worldgenlevel, blockpos1, Blocks.BONE_BLOCK.defaultBlockState());
                    blockpos1 = blockpos1.below();
                    --j2;
                    if (j2 <= 0) {
                        blockpos1 = blockpos1.below(randomsource.nextInt(5) + 1);
                        j2 = randomsource.nextInt(5);
                    }
                }
            }
        }

        return true;

    }
}