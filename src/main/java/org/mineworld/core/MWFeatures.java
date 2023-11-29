package org.mineworld.core;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.helper.RegisterHelper;
import org.mineworld.world.worldgen.feature.BoneSpikeFeature;

/**
 * {@link MineWorld MineWorld} {@link Feature features}
 */
public final class MWFeatures {

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> BONE_SPIKE = RegisterHelper.registerFeature("bone_spike", BoneSpikeFeature::new);

    /**
     * Register the {@link MineWorld MineWorld} {@link Feature features}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void register(final IEventBus eventBus) {
        RegisterHelper.registerFeatures(eventBus);
    }

}