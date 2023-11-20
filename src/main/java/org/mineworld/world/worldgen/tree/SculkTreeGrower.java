package org.mineworld.world.worldgen.tree;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineworld.core.MWConfiguredFeatures;

/**
 * {@link AbstractTreeGrower Tree grower for a sculk tree}
 */
public class SculkTreeGrower extends AbstractTreeGrower {

    /**
     * Get the {@link ConfiguredFeature sculk tree configured feature}
     *
     * @param random {@link RandomSource The random reference}
     * @param withBeeNest {@link Boolean If the tree should have a bee nest attached}
     * @return {@link ConfiguredFeature The sculk tree configured feature}
     */
    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(final @NotNull RandomSource random, final boolean withBeeNest) {
        return MWConfiguredFeatures.SCULK_TREE;
    }

}