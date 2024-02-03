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
import org.mineworld.MineWorld;

/**
 * {@link MineWorld MineWorld} {@link Feature Bone Spike Feature}
 */
public class BoneSpikeFeature extends Feature<NoneFeatureConfiguration> {

    /**
     * Constructor. Set the {@link Feature Feature} {@link Codec Codec}
     */
    public BoneSpikeFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    /**
     * Place the {@link Feature Feature}
     *
     * @param context {@link FeaturePlaceContext<NoneFeatureConfiguration> The Feature Place Context}
     * @return {@link Boolean True if the Feature has been correctly placed}
     */
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos origin = context.origin();
        final RandomSource random = context.random();
        final WorldGenLevel level = context.level();
        while (level.isEmptyBlock(origin) && origin.getY() > level.getMinBuildHeight() + 2) {
            origin = origin.below();
        }

        if (!level.getBlockState(origin).is(Blocks.NETHERRACK)) {
            return false;
        }

        origin = origin.above(random.nextInt(4));
        final int i = random.nextInt(4) + 7;
        final int j = i / 4 + random.nextInt(2);
        if (j > 1 && random.nextInt(60) == 0) {
            origin = origin.above(10 + random.nextInt(30));
        }

        for(int k = 0; k < i; ++k) {
            final float scale = (1.0F - (float)k / (float)i) * (float)j;
            final int height = Mth.ceil(scale);

            for(int i1 = -height; i1 <= height; ++i1) {
                final float f1 = (float)Mth.abs(i1) - 0.25F;

                for(int j1 = -height; j1 <= height; ++j1) {
                    final float f2 = (float)Mth.abs(j1) - 0.25F;
                    if ((i1 == 0 && j1 == 0 || !(f1 * f1 + f2 * f2 > scale * scale)) && (i1 != -height && i1 != height && j1 != -height && j1 != height || !(random.nextFloat() > 0.75F))) {
                        BlockState blockState = level.getBlockState(origin.offset(i1, k, j1));
                        if (blockState.isAir() || isDirt(blockState) || blockState.is(Blocks.NETHERRACK)) {
                            this.setBlock(level, origin.offset(i1, k, j1), Blocks.BONE_BLOCK.defaultBlockState());
                        }

                        if (k != 0 && height > 1) {
                            blockState = level.getBlockState(origin.offset(i1, -k, j1));
                            if (blockState.isAir() || isDirt(blockState) || blockState.is(Blocks.NETHERRACK)) {
                                this.setBlock(level, origin.offset(i1, -k, j1), Blocks.BONE_BLOCK.defaultBlockState());
                            }
                        }
                    }
                }
            }
        }

        int k1 = Math.min(Math.max(j -1 , 0), 1);

        for(int l1 = -k1; l1 <= k1; ++l1) {
            for(int i2 = -k1; i2 <= k1; ++i2) {
                BlockPos blockPos = origin.offset(l1, -1, i2);
                int j2 = 50;
                if (Math.abs(l1) == 1 && Math.abs(i2) == 1) {
                    j2 = random.nextInt(5);
                }

                while(blockPos.getY() > 50) {
                    final BlockState blockState = level.getBlockState(blockPos);
                    if (!blockState.isAir() && !isDirt(blockState) && !blockState.is(Blocks.NETHERRACK) && !blockState.is(Blocks.BONE_BLOCK)) {
                        break;
                    }

                    this.setBlock(level, blockPos, Blocks.BONE_BLOCK.defaultBlockState());
                    blockPos = blockPos.below();
                    --j2;
                    if (j2 <= 0) {
                        blockPos = blockPos.below(random.nextInt(5) + 1);
                        j2 = random.nextInt(5);
                    }
                }
            }
        }

        return true;

    }

}