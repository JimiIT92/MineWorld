package org.mineworld.world.worldgen.tree;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineworld.core.MWConfiguredFeatures;

/**
 * {@link AbstractTreeGrower Tree grower for an apple tree}
 */
public class AppleTreeGrower extends AbstractTreeGrower {

    /**
     * Get the {@link ConfiguredFeature apple tree configured feature}
     *
     * @param random {@link RandomSource The random reference}
     * @param withBeeNest {@link Boolean If the tree should have a bee nest attached}
     * @return {@link ConfiguredFeature The apple tree configured feature}
     */
    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(final @NotNull RandomSource random, final boolean withBeeNest) {
        return random.nextInt(10) == 0 ? (
                withBeeNest ? MWConfiguredFeatures.FANCY_APPLE_BEES_TREE : MWConfiguredFeatures.FANCY_APPLE_TREE) :
                withBeeNest ? MWConfiguredFeatures.APPLE_BEES_TREE : MWConfiguredFeatures.APPLE_TREE;
    }

}