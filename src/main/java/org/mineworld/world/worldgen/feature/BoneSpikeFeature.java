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
import org.mineworld.helper.RandomHelper;

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
        final RandomSource random = context.random();
        final WorldGenLevel level = context.level();
        while (level.isEmptyBlock(blockpos) && blockpos.getY() > level.getMinBuildHeight() + 2) {
            blockpos = blockpos.below();
        }

        if (!level.getBlockState(blockpos).is(Blocks.NETHERRACK)) {
            return false;
        }

        blockpos = blockpos.above(RandomHelper.range(4));
        final int i = RandomHelper.range(4) + 7;
        final int j = i / 4 + RandomHelper.range(2);
        if (j > 1 && RandomHelper.choose(0, 60, 0)) {
            blockpos = blockpos.above(10 + RandomHelper.range(30));
        }

        for(int k = 0; k < i; ++k) {
            final float scale = (1.0F - (float)k / (float)i) * (float)j;
            final int height = Mth.ceil(scale);

            for(int i1 = -height; i1 <= height; ++i1) {
                float f1 = (float)Mth.abs(i1) - 0.25F;

                for(int j1 = -height; j1 <= height; ++j1) {
                    float f2 = (float)Mth.abs(j1) - 0.25F;
                    if ((i1 == 0 && j1 == 0 || !(f1 * f1 + f2 * f2 > scale * scale)) && (i1 != -height && i1 != height && j1 != -height && j1 != height || !(random.nextFloat() > 0.75F))) {
                        BlockState blockstate = level.getBlockState(blockpos.offset(i1, k, j1));
                        if (blockstate.isAir() || isDirt(blockstate) || blockstate.is(Blocks.NETHERRACK)) {
                            this.setBlock(level, blockpos.offset(i1, k, j1), Blocks.BONE_BLOCK.defaultBlockState());
                        }

                        if (k != 0 && height > 1) {
                            blockstate = level.getBlockState(blockpos.offset(i1, -k, j1));
                            if (blockstate.isAir() || isDirt(blockstate) || blockstate.is(Blocks.NETHERRACK)) {
                                this.setBlock(level, blockpos.offset(i1, -k, j1), Blocks.BONE_BLOCK.defaultBlockState());
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
                    j2 = random.nextInt(5);
                }

                while(blockpos1.getY() > 50) {
                    BlockState blockstate1 = level.getBlockState(blockpos1);
                    if (!blockstate1.isAir() && !isDirt(blockstate1) && !blockstate1.is(Blocks.NETHERRACK) && !blockstate1.is(Blocks.BONE_BLOCK)) {
                        break;
                    }

                    this.setBlock(level, blockpos1, Blocks.BONE_BLOCK.defaultBlockState());
                    blockpos1 = blockpos1.below();
                    --j2;
                    if (j2 <= 0) {
                        blockpos1 = blockpos1.below(random.nextInt(5) + 1);
                        j2 = random.nextInt(5);
                    }
                }
            }
        }

        return true;

    }
}