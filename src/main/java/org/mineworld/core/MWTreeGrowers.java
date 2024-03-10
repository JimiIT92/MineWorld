package org.mineworld.core;

import com.google.common.base.Suppliers;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.mineworld.MineWorld;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link TreeGrower Tree Growers}
 */
public final class MWTreeGrowers {

    //#region Tree Growers

    public static final Supplier<TreeGrower> APPLE_TREE_GROWER = Suppliers.memoize(() -> new TreeGrower(
            MWWoodTypes.MWWoodTypeNames.APPLE,
            0.1F,
            Optional.empty(),
            Optional.empty(),
            Optional.of(MWConfiguredFeatures.APPLE_TREE),
            Optional.of(MWConfiguredFeatures.FANCY_APPLE_TREE),
            Optional.of(MWConfiguredFeatures.APPLE_BEES_TREE),
            Optional.of(MWConfiguredFeatures.FANCY_APPLE_BEES_TREE))
    );
    public static final Supplier<TreeGrower> PALM_TREE_GROWER = Suppliers.memoize(() -> simpleTreeGrower(MWWoodTypes.MWWoodTypeNames.PALM, MWConfiguredFeatures.PALM_TREE));
    public static final Supplier<TreeGrower> SCULK_TREE_GROWER = Suppliers.memoize(() -> simpleTreeGrower(MWWoodTypes.MWWoodTypeNames.SCULK, MWConfiguredFeatures.SCULK_TREE));

    //#endregion

    //#region Methods

    /**
     * Get a {@link TreeGrower simple Tree Grower}
     *
     * @param name {@link String The Tree Grower name}
     * @param treeConfiguredFeature {@link ConfiguredFeature The Tree Configured Feature}
     * @return {@link TreeGrower The simple Tree Grower}
     */
    private static TreeGrower simpleTreeGrower(final String name, final ResourceKey<ConfiguredFeature<?, ?>> treeConfiguredFeature) {
        return new TreeGrower(name, Optional.empty(), Optional.of(treeConfiguredFeature), Optional.empty());
    }

    //#endregion
}