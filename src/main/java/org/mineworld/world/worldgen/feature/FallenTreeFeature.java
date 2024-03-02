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
import org.mineworld.MineWorld;
import org.mineworld.helper.RandomHelper;
import org.mineworld.world.worldgen.feature.configurations.FallenTreeConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * {@link MineWorld MineWorld} {@link Feature Fallen Tree Feature}
 */
public class FallenTreeFeature extends Feature<FallenTreeConfiguration> {

    /**
     * Constructor. Set the {@link Feature Feature} {@link Codec Codec}
     *
     * @param codec {@link Codec<FallenTreeConfiguration> The Feature Codec}
     */
    public FallenTreeFeature(final Codec<FallenTreeConfiguration> codec) {
        super(codec);
    }

    /**
     * Place the {@link Feature Feature}
     *
     * @param context {@link FeaturePlaceContext<FallenTreeConfiguration> The Feature Place Context}
     * @return {@link Boolean True if the Feature has been correctly placed}
     */
    public boolean place(FeaturePlaceContext<FallenTreeConfiguration> context) {
        final FallenTreeConfiguration config = context.config();
        final RandomSource random = context.random();
        final WorldGenLevel level = context.level();
        final BlockPos blockPos = context.origin();
        final Block logBlock = config.logProvider().getState(random, blockPos).getBlock();
        final Block hollowLogBlock = config.hollowLogProvider().getState(random, blockPos).getBlock();
        final boolean ignoreMoss = config.ignoreMoss();
        final Direction direction = RandomHelper.randomValue(Arrays.stream(Direction.values()).filter(dir -> dir.getAxis().isHorizontal()).collect(Collectors.toCollection(ArrayList::new))).orElse(Direction.NORTH);
        final int size = random.nextInt(3) + 2;

        for (int i = 0; i < size + 2; i++) {
            final BlockPos relativeBlockPos = blockPos.relative(direction, i);
            final BlockState belowBlockState = level.getBlockState(relativeBlockPos.below());
            final BlockState blockState = level.getBlockState(relativeBlockPos);
            if(belowBlockState.isAir() || !belowBlockState.isSolidRender(level, relativeBlockPos) || !blockState.isAir()) {
                return false;
            }
        }

        final BlockState hollowLogState = hollowLogBlock.defaultBlockState().setValue(RotatedPillarBlock.AXIS, direction.getAxis());
        level.setBlock(blockPos, logBlock.defaultBlockState().setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y), 2);

        for (int i = 0; i < size; i++) {
            final BlockPos hollowLogPos = blockPos.relative(direction, 2 + i);
            level.setBlock(hollowLogPos, hollowLogState, 2);
            if(!ignoreMoss && random.nextBoolean()) {
                level.setBlock(hollowLogPos.above(), Blocks.MOSS_CARPET.defaultBlockState(), 2);
            }
        }

        return true;
    }

}