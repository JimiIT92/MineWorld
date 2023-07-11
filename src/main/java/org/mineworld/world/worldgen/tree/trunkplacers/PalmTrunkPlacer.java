package org.mineworld.world.worldgen.tree.trunkplacers;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.jetbrains.annotations.NotNull;
import org.mineworld.core.MWTrunkPlacerTypes;
import org.mineworld.helper.LevelHelper;
import org.mineworld.helper.RandomHelper;

import java.util.List;
import java.util.function.BiConsumer;

/**
 * Implementation class for a {@link TrunkPlacer palm trunk placer}
 */
public class PalmTrunkPlacer extends TrunkPlacer {

    /**
     * {@link Codec<PalmTrunkPlacer> The palm trunk placer codec}
     */
    public static Codec<PalmTrunkPlacer> CODEC = RecordCodecBuilder.create(builder -> trunkPlacerParts(builder).apply(builder, PalmTrunkPlacer::new));

    /**
     * Constructor. Set the trunk placer properties
     *
     * @param minHeight {@link Integer The minimum tree height}
     * @param heightRandA {@link Integer The additional height the tree can have}
     * @param heightRandB {@link Integer The additional height the tree can have after the extra one}
     */
    public PalmTrunkPlacer(int minHeight, int heightRandA, int heightRandB) {
        super(minHeight, heightRandA, heightRandB);
    }

    /**
     * Get the {@link TrunkPlacerType trunk placer type}
     *
     * @return {@link MWTrunkPlacerTypes#PALM_TRUNK_PLACER The palm trunk placer type}
     */
    @Override
    protected @NotNull TrunkPlacerType<?> type() {
        return MWTrunkPlacerTypes.PALM_TRUNK_PLACER.get();
    }

    /**
     * Place the trunk inside the world
     *
     * @param levelSimulatedReader {@link LevelSimulatedReader The level simulated reader instance}
     * @param stateBiConsumer {@link BiConsumer The block pos and block state bi-consumer}
     * @param random {@link RandomSource The random reference}
     * @param height {@link Integer The trunk height}
     * @param blockPos {@link BlockPos The current block pos}
     * @param treeConfiguration {@link TreeConfiguration The tree configuration}
     * @return {@link List<FoliagePlacer.FoliageAttachment> The foliage attachment list}
     */
    @Override
    public @NotNull List<FoliagePlacer.FoliageAttachment> placeTrunk(@NotNull LevelSimulatedReader levelSimulatedReader, @NotNull BiConsumer<BlockPos, BlockState> stateBiConsumer, @NotNull RandomSource random, int height, BlockPos blockPos, @NotNull TreeConfiguration treeConfiguration) {
        BlockPos below = blockPos.below();
        if(!((LevelReader)levelSimulatedReader).getBlockState(below).is(BlockTags.SAND)) {
            setDirtAt(levelSimulatedReader, stateBiConsumer, random, below, treeConfiguration);
        }

        Direction direction = LevelHelper.getRandomHorizontalDirection(random);

        for (int i = 0; i < Math.min(4, height); ++i) {
            this.placeLog(levelSimulatedReader, stateBiConsumer, random, blockPos.above(i), treeConfiguration);
        }

        int bentTrunkHeight = Math.max(0, height - 4);
        for (int i = 0; i < bentTrunkHeight; ++i) {
            if(RandomHelper.choose()) {
                blockPos = blockPos.relative(direction);
            }
            this.placeLog(levelSimulatedReader, stateBiConsumer, random, blockPos.above(3 + i), treeConfiguration);
        }

        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(blockPos.above(height), 0, false));
    }

}