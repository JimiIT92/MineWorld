package org.mineworld.world.worldgen.tree.foliageplacers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.jetbrains.annotations.NotNull;
import org.mineworld.core.MWFoliagePlacerTypes;

/**
 * Implementation class for a {@link BlobFoliagePlacer palm foliage placer}
 */
public class PalmFoliagePlacer extends BlobFoliagePlacer {

    /**
     * {@link Codec<PalmFoliagePlacer> The palm foliage placer codec}
     */
    public static Codec<PalmFoliagePlacer> CODEC = RecordCodecBuilder.create(builder -> blobParts(builder).apply(builder, PalmFoliagePlacer::new));

    /**
     * Constructor. Set the trunk placer properties
     *
     * @param radius {@link IntProvider The leaves radius}
     * @param offset {@link IntProvider The leaves offset}
     * @param height {@link Integer The leaves height}
     */
    public PalmFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset, height);
    }

    /**
     * Get the {@link FoliagePlacerType foliage placer type}
     *
     * @return {@link MWFoliagePlacerTypes#PALM_FOLIAGE_PLACER The palm foliage placer type}
     */
    @Override
    protected @NotNull FoliagePlacerType<?> type() {
        return MWFoliagePlacerTypes.PALM_FOLIAGE_PLACER.get();
    }

    /**
     * Place the foliage inside the world
     *
     * @param levelSimulatedReader {@link LevelSimulatedReader The level simulated reader instance}
     * @param foliageSetter {@link FoliagePlacer.FoliageSetter The foliage setter}
     * @param random {@link RandomSource The random reference}
     * @param treeConfiguration {@link TreeConfiguration The tree configuration}
     * @param treeHeight {@link Integer The trunk height}
     * @param height {@link Integer The tree foliage height}
     * @param radius {@link Integer The tree foliage radius}
     * @param offset {@link Integer The tree foliage offset}
     */
    @Override
    protected void createFoliage(@NotNull LevelSimulatedReader levelSimulatedReader, FoliagePlacer.@NotNull FoliageSetter foliageSetter, @NotNull RandomSource random, @NotNull TreeConfiguration treeConfiguration, int treeHeight, FoliagePlacer.@NotNull FoliageAttachment foliageAttachment, int height, int radius, int offset) {
        for(int i = offset; i >= offset - height; --i) {
            int j = Math.max(radius + foliageAttachment.radiusOffset() - 1 - i / 2, 0);
            this.placeLeavesRowWithHangingLeavesBelow(levelSimulatedReader, foliageSetter, random, treeConfiguration, foliageAttachment.pos(), j, i, foliageAttachment.doubleTrunk(),0.25F, 0.25F);
        }
    }

}