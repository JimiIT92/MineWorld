package org.mineworld.world.worldgen.tree;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineworld.MineWorld;

import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link AbstractTreeGrower generic Tree grower}
 */
public class MWTreeGrower extends AbstractTreeGrower {

    /**
     * The {@link Supplier<ResourceKey> Tree Configured Feature Resource Key Supplier}
     */
    private final Supplier<ResourceKey<ConfiguredFeature<?, ?>>> treeConfiguredFeatureKeySupplier;

    /**
     * Constructor. Set the {@link Supplier<ResourceKey> Tree Configured Feature Resource Key Supplier}
     *
     * @param treeConfiguredFeatureKeySupplier The {@link Supplier<ResourceKey> Tree Configured Feature Resource Key Supplier}
     */
    public MWTreeGrower(final Supplier<ResourceKey<ConfiguredFeature<?, ?>>> treeConfiguredFeatureKeySupplier) {
        this.treeConfiguredFeatureKeySupplier = treeConfiguredFeatureKeySupplier;
    }

    /**
     * Get the {@link ConfiguredFeature Tree Configured Feature}
     *
     * @param random {@link RandomSource The Random reference}
     * @param withBeeNest {@link Boolean If the Tree should have a Bee Nest attached}
     * @return {@link ConfiguredFeature The Tree Configured Feature}
     */
    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(final @NotNull RandomSource random, final boolean withBeeNest) {
        return treeConfiguredFeatureKeySupplier.get();
    }

}