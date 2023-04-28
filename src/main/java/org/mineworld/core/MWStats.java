package org.mineworld.core;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.helper.RegisterHelper;

/**
 * {@link MineWorld MineWorld} statistics
 */
public final class MWStats {

    public static final RegistryObject<ResourceLocation> INTERACT_WITH_WOODCUTTER = RegisterHelper.registerStatistic("interacted_with_woodcutter");

    /**
     * Register the {@link MineWorld MineWorld} {@link ResourceLocation statistics}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void register(final IEventBus eventBus) {
        RegisterHelper.registerStatistics(eventBus);
    }

}