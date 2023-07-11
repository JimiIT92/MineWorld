package org.mineworld.core;

import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.helper.RegisterHelper;
import org.mineworld.world.worldgen.tree.foliageplacers.PalmFoliagePlacer;

/**
 * {@link MineWorld MineWorld} {@link FoliagePlacerType trunk placer types}
 */
public class MWFoliagePlacerTypes {

    public static RegistryObject<FoliagePlacerType<PalmFoliagePlacer>> PALM_FOLIAGE_PLACER = RegisterHelper.registerFoliagePlacerType("palm", () -> new FoliagePlacerType<>(PalmFoliagePlacer.CODEC));

    /**
     * Register the {@link MineWorld MineWorld} {@link FoliagePlacerType trunk placer types}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void register(IEventBus eventBus) {
        RegisterHelper.registerFoliagePlacerTypes(eventBus);
    }

}
