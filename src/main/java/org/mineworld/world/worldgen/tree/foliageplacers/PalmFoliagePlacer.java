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
 * Implementation class for a {@link BlobFoliagePlacer Palm Foliage Placer}
 */
public class PalmFoliagePlacer extends BlobFoliagePlacer {

    /**
     * {@link Codec<PalmFoliagePlacer> The Palm Foliage Placer Codec}
     */
    public static final Codec<PalmFoliagePlacer> CODEC = RecordCodecBuilder.create(builder -> blobParts(builder).apply(builder, PalmFoliagePlacer::new));

    /**
     * Constructor. Set the {@link FoliagePlacer Foliage Placer} Properties
     *
     * @param radius {@link IntProvider The Leaves radius}
     * @param offset {@link IntProvider The Leaves offset}
     * @param height {@link Integer The Leaves height}
     */
    public PalmFoliagePlacer(final IntProvider radius, final IntProvider offset, final int height) {
        super(radius, offset, height);
    }

    /**
     * Get the {@link FoliagePlacerType Foliage Placer Type}
     *
     * @return {@link MWFoliagePlacerTypes#PALM_FOLIAGE_PLACER The Palm Foliage Placer Type}
     */
    @Override
    protected @NotNull FoliagePlacerType<?> type() {
        return MWFoliagePlacerTypes.PALM_FOLIAGE_PLACER.get();
    }

    /**
     * Place the Foliage
     *
     * @param levelSimulatedReader {@link LevelSimulatedReader The Level reference}
     * @param foliageSetter {@link FoliagePlacer.FoliageSetter The Foliage Setter}
     * @param random {@link RandomSource The Random reference}
     * @param treeConfiguration {@link TreeConfiguration The Tree Configuration}
     * @param treeHeight {@link Integer The Trunk height}
     * @param height {@link Integer The Tree Foliage height}
     * @param radius {@link Integer The Tree Foliage radius}
     * @param offset {@link Integer The Tree Foliage offset}
     */
    @Override
    protected void createFoliage(final @NotNull LevelSimulatedReader levelSimulatedReader, final FoliagePlacer.@NotNull FoliageSetter foliageSetter, final @NotNull RandomSource random, final @NotNull TreeConfiguration treeConfiguration, final int treeHeight, final FoliagePlacer.@NotNull FoliageAttachment foliageAttachment, final int height, final int radius, final int offset) {
        for(int i = offset; i >= offset - height; --i) {
            final int j = Math.max(radius + foliageAttachment.radiusOffset() - 1 - i / 2, 0);
            this.placeLeavesRowWithHangingLeavesBelow(levelSimulatedReader, foliageSetter, random, treeConfiguration, foliageAttachment.pos(), j, i, foliageAttachment.doubleTrunk(),0.25F, 0.25F);
        }
    }

}