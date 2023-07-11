package org.mineworld.core;

import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.helper.RegisterHelper;
import org.mineworld.world.worldgen.tree.trunkplacers.PalmTrunkPlacer;

/**
 * {@link MineWorld MineWorld} {@link TrunkPlacerType trunk placer types}
 */
public class MWTrunkPlacerTypes {

    public static RegistryObject<TrunkPlacerType<PalmTrunkPlacer>> PALM_TRUNK_PLACER = RegisterHelper.registerTrunkPlacerType("palm", () -> new TrunkPlacerType<>(PalmTrunkPlacer.CODEC));

    /**
     * Register the {@link MineWorld MineWorld} {@link TrunkPlacerType trunk placer types}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void register(IEventBus eventBus) {
        RegisterHelper.registerTrunkPlacerTypes(eventBus);
    }

}
