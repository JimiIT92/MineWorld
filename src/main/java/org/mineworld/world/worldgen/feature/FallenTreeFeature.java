package org.mineworld.world.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.mineworld.helper.RandomHelper;
import org.mineworld.world.worldgen.feature.configuration.FallenTreeConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Implementation class for a Fallen Tree Feature
 */
public class FallenTreeFeature extends Feature<FallenTreeConfiguration> {

    /**
     * Constructor. Set the feature {@link Codec codec}
     *
     * @param codec {@link Codec<FallenTreeConfiguration> The Fallen Tree Configuration Codec}
     */
    public FallenTreeFeature(Codec<FallenTreeConfiguration> codec) {
        super(codec);
    }

    /**
     * Place the feature
     *
     * @param context {@link FeaturePlaceContext<NoneFeatureConfiguration> The feature place context}
     * @return {@link Boolean True if the feature has been correctly placed}
     */
    public boolean place(FeaturePlaceContext<FallenTreeConfiguration> context) {
        final FallenTreeConfiguration config = context.config();
        final RandomSource random = context.random();
        final BlockPos blockpos = context.origin();
        final Block logBlock = config.logProvider().getState(random, blockpos).getBlock();
        final Block hollowLogBlock = config.hollowLogProvider().getState(random, blockpos).getBlock();
        final boolean ignoreMoss = config.ignoreMoss();
        final WorldGenLevel level = context.level();
        final Direction direction = RandomHelper.randomValue(Arrays.stream(Direction.values()).filter(dir -> dir.getAxis().isHorizontal()).collect(Collectors.toCollection(ArrayList::new))).orElse(Direction.NORTH);
        final int size = RandomHelper.range(3) + 2;

        for (int i = 0; i < size + 2; i++) {
            final BlockPos relativeBlockPos = blockpos.relative(direction, i);
            final BlockState belowBlockState = level.getBlockState(relativeBlockPos.below());
            final BlockState blockState = level.getBlockState(relativeBlockPos);
            if(belowBlockState.isAir() || !belowBlockState.isSolidRender(level, relativeBlockPos) || !blockState.isAir()) {
                return false;
            }
        }

        final BlockState hollowLogState = hollowLogBlock.defaultBlockState().setValue(RotatedPillarBlock.AXIS, direction.getAxis());

        level.setBlock(blockpos, logBlock.defaultBlockState().setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y), 2);

        for (int i = 0; i < size; i++) {
            final BlockPos hollowLogPos = blockpos.relative(direction, 2 + i);
            level.setBlock(hollowLogPos, hollowLogState, 2);
            if(!ignoreMoss && RandomHelper.choose()) {
                level.setBlock(hollowLogPos.above(), Blocks.MOSS_CARPET.defaultBlockState(), 2);
            }
        }

        return true;

    }
}