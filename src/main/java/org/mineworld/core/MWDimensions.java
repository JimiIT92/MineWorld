package org.mineworld.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import org.mineworld.MineWorld;
import org.mineworld.helper.RegistryHelper;
import org.mineworld.helper.ResourceHelper;

/**
 * {@link MineWorld MineWorld} {@link DimensionType Dimension Types}
 */
public final class MWDimensions {

    //#region Dimension Types

    //#region Ethereal

    public static final ResourceKey<Level> ETHEREAL =  registerDimensionKey("ethereal");
    public static final ResourceKey<DimensionType> ETHEREAL_DIMENSION_TYPE = registerDimensionTypeKey(ETHEREAL);

    //#endregion

    //#endregion

    //#region Methods

    /**
     * Register a {@link Level Dimension} {@link ResourceKey<Level> Resource Key}
     *
     * @param name {@link String The Dimension name}
     * @return {@link ResourceKey<Level> The Dimension Resource Key}
     */
    private static ResourceKey<Level> registerDimensionKey(final String name) {
        return RegistryHelper.register(Registries.DIMENSION, name);
    }

    /**
     * Register a {@link DimensionType Dimension Type} {@link ResourceKey<DimensionType> Resource Key}
     *
     * @param dimensionResourceKey {@link ResourceKey<Level> The Dimension Resource Key}
     * @return {@link ResourceKey<DimensionType> The Dimension Type Resource Key}
     */
    private static ResourceKey<DimensionType> registerDimensionTypeKey(final ResourceKey<Level> dimensionResourceKey) {
        return RegistryHelper.register(Registries.DIMENSION_TYPE, ResourceHelper.path(dimensionResourceKey.registry()));
    }

    //#endregion

    //#region Bus Register

    /**
     * Register all {@link MineWorld MineWorld} {@link DimensionType Dimension Types}
     */
    public static void register() { }

    //#endregion

    //#region Names

    /**
     * The {@link MineWorld MineWorld} {@link DimensionType Dimensions}
     */
    public enum Dimensions {
        ETHEREAL
    }

    //#endregion

}