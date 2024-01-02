package org.mineworld.core;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.helper.RegistryHelper;
import org.mineworld.helper.ResourceHelper;

/**
 * {@link MineWorld MineWorld} {@link ResourceLocation Statistics}
 */
public final class MWStats {

    //#region Registry

    /**
     * The {@link DeferredRegister<ResourceLocation> Statistics Registry}
     */
    private static final DeferredRegister<ResourceLocation> STATISTICS = RegistryHelper.registry(BuiltInRegistries.CUSTOM_STAT.key());

    //#endregion

    //#region Statistics

    public static final RegistryObject<ResourceLocation> INTERACT_WITH_WOODCUTTER = registerStatistic("interacted_with_woodcutter");
    public static final RegistryObject<ResourceLocation> INTERACT_WITH_FORGING_TABLE = registerStatistic("interacted_with_forging_table");

    //#endregion

    //#region Methods

    /**
     * Register a {@link ResourceLocation statistic}
     *
     * @param name {@link String The statistic name}
     * @return {@link RegistryObject<ResourceLocation> The registered statistic}
     */
    private static RegistryObject<ResourceLocation> registerStatistic(final String name) {
        return STATISTICS.register(name, () -> ResourceHelper.resourceLocation(name));
    }

    //#endregion

    //#region Bus register

    /**
     * Register all {@link ResourceLocation statistics}
     *
     * @param eventBus {@link IEventBus The mod event bus}
     */
    public static void register(final IEventBus eventBus) {
        STATISTICS.register(eventBus);
    }

    //#endregion

}