package org.mineworld.core;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraftforge.eventbus.api.IEventBus;
import org.mineworld.MineWorld;
import org.mineworld.helper.KeyHelper;

/**
 * {@link MineWorld MineWorld} dimensions
 */
public final class MWDimensions {

    public static final ResourceKey<Level> ETHEREAL = KeyHelper.registerDimension("ethereal");
    public static final ResourceKey<DimensionType> ETHEREAL_DIMENSION_TYPE = KeyHelper.registerDimensionType(ETHEREAL);

    /**
     * Register all dimensions
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void register(final IEventBus eventBus) { }

}