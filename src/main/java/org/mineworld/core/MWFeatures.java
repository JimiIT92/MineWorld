package org.mineworld.core;

import com.google.common.base.Suppliers;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NetherForestVegetationConfig;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.helper.RegistryHelper;
import org.mineworld.world.worldgen.feature.BoneSpikeFeature;
import org.mineworld.world.worldgen.feature.EtherealForestVegetationFeature;
import org.mineworld.world.worldgen.feature.FallenTreeFeature;
import org.mineworld.world.worldgen.feature.configurations.FallenTreeConfiguration;

import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link Feature Features}
 */
public final class MWFeatures {

    //#region Registry

    /**
     * The {@link DeferredRegister<Feature> Features Registry}
     */
    private static final DeferredRegister<Feature<?>> FEATURES = RegistryHelper.registry(Registries.FEATURE);

    //#endregion

    //#region Features

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> BONE_SPIKE = registerFeature("bone_spike", Suppliers.memoize(() -> new BoneSpikeFeature()));
    public static final RegistryObject<Feature<FallenTreeConfiguration>> FALLEN_TREE = registerFeature("fallen_tree", Suppliers.memoize(() -> new FallenTreeFeature(FallenTreeConfiguration.CODEC)));
    public static final RegistryObject<Feature<NetherForestVegetationConfig>> ETHEREAL_FOREST_VEGETATION = registerFeature("ethereal_forest_vegetation", Suppliers.memoize(() -> new EtherealForestVegetationFeature(NetherForestVegetationConfig.CODEC)));

    //#endregion

    //#region Methods

    /**
     * Register a {@link Feature Feature}
     *
     * @param name {@link String The Feature Name}
     * @param featureSupplier {@link Supplier<Feature> The Feature Supplier}
     * @return {@link RegistryObject<Feature> The registered Feature}
     * @param <FC> {@link FC The Feature Configuration}
     */
    public static <FC extends FeatureConfiguration> RegistryObject<Feature<FC>> registerFeature(final String name, final Supplier<? extends Feature<FC>> featureSupplier) {
        return FEATURES.register(name, featureSupplier);
    }

    //#endregion

    //#region Bus Register

    /**
     * Register all {@link Feature Features}
     *
     * @param eventBus {@link IEventBus The mod event bus}
     */
    public static void register(final IEventBus eventBus) {
        FEATURES.register(eventBus);
    }

    //#endregion

}