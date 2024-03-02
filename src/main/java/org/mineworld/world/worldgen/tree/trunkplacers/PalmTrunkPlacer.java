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

import java.util.List;
import java.util.function.BiConsumer;

/**
 * Implementation class for a {@link TrunkPlacer Palm Trunk Placer}
 */
public class PalmTrunkPlacer extends TrunkPlacer {

    /**
     * {@link Codec<PalmTrunkPlacer> The Palm Trunk Placer Codec}
     */
    public static final Codec<PalmTrunkPlacer> CODEC = RecordCodecBuilder.create(builder -> trunkPlacerParts(builder).apply(builder, PalmTrunkPlacer::new));

    /**
     * Constructor. Set the {@link TrunkPlacer Trunk Placer} Properties
     *
     * @param minHeight {@link Integer The minimum Tree height}
     * @param additionalHeight {@link Integer The additional height the Tree can have}
     * @param extraHeight {@link Integer The additional height the Tree can have after the extra one}
     */
    public PalmTrunkPlacer(final int minHeight, final int additionalHeight, final int extraHeight) {
        super(minHeight, additionalHeight, extraHeight);
    }

    /**
     * Get the {@link TrunkPlacerType Trunk Placer Type}
     *
     * @return {@link MWTrunkPlacerTypes#PALM_TRUNK_PLACER The Palm Trunk Placer Type}
     */
    @Override
    protected @NotNull TrunkPlacerType<?> type() {
        return MWTrunkPlacerTypes.PALM_TRUNK_PLACER.get();
    }

    /**
     * Place the Trunk
     *
     * @param levelSimulatedReader {@link LevelSimulatedReader The level reference}
     * @param stateBiConsumer {@link BiConsumer The Block Pos and Block State bi-consumer}
     * @param random {@link RandomSource The Random reference}
     * @param height {@link Integer The Trunk height}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param treeConfiguration {@link TreeConfiguration The Tree Configuration}
     * @return {@link List<FoliagePlacer.FoliageAttachment> The Foliage attachment list}
     */
    @Override
    public @NotNull List<FoliagePlacer.FoliageAttachment> placeTrunk(final @NotNull LevelSimulatedReader levelSimulatedReader, final @NotNull BiConsumer<BlockPos, BlockState> stateBiConsumer, final @NotNull RandomSource random, final int height, BlockPos blockPos, final @NotNull TreeConfiguration treeConfiguration) {
        final BlockPos below = blockPos.below();
        if(!((LevelReader)levelSimulatedReader).getBlockState(below).is(BlockTags.SAND)) {
            setDirtAt(levelSimulatedReader, stateBiConsumer, random, below, treeConfiguration);
        }

        final Direction direction = LevelHelper.getRandomHorizontalDirection(random);

        for (int i = 0; i < Math.min(4, height); ++i) {
            this.placeLog(levelSimulatedReader, stateBiConsumer, random, blockPos.above(i), treeConfiguration);
        }

        int bentTrunkHeight = Math.max(0, height - 4);
        for (int i = 0; i < bentTrunkHeight; ++i) {
            if(random.nextBoolean()) {
                blockPos = blockPos.relative(direction);
            }
            this.placeLog(levelSimulatedReader, stateBiConsumer, random, blockPos.above(3 + i), treeConfiguration);
        }

        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(blockPos.above(height), 0, false));
    }

}