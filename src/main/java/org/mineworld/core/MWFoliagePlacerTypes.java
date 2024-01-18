package org.mineworld.core;

import com.google.common.base.Suppliers;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.world.worldgen.tree.foliageplacers.PalmFoliagePlacer;

import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link FoliagePlacerType Foliage Placer Types}
 */
public final class MWFoliagePlacerTypes {

    //#region Registry

    /**
     * The {@link DeferredRegister<FoliagePlacerType> Foliage Placer Types Registry}
     */
    private static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER_TYPES = DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, MineWorld.MOD_ID);

    //#endregion

    //#region Foliage Placer Types

    public static final RegistryObject<FoliagePlacerType<PalmFoliagePlacer>> PALM_FOLIAGE_PLACER = registerFoliagePlacerType("palm", Suppliers.memoize(() -> new FoliagePlacerType<>(PalmFoliagePlacer.CODEC)));

    //#endregion

    //#region Methods

    /**
     * Register a {@link FoliagePlacerType Foliage Placer Type}
     *
     * @param name {@link String The Foliage Placer Type name}
     * @param foliagePlacerSupplier {@link Supplier<FoliagePlacerType> The Foliage Placer Type Supplier}
     * @return {@link RegistryObject <FoliagePlacerType> The registered Foliage Placer Type}
     * @param <FP> {@link FP The Foliage Placer Type}
     */
    private static <FP extends FoliagePlacer> RegistryObject<FoliagePlacerType<FP>> registerFoliagePlacerType(final String name, final Supplier<? extends FoliagePlacerType<FP>> foliagePlacerSupplier) {
        return FOLIAGE_PLACER_TYPES.register(name + "_foliage_placer", foliagePlacerSupplier);
    }

    //#endregion

    //#region Bus Register

    /**
     * Register all {@link FoliagePlacerType Foliage Placer Types}
     *
     * @param eventBus {@link IEventBus The mod event bus}
     */
    public static void register(final IEventBus eventBus) {
        FOLIAGE_PLACER_TYPES.register(eventBus);
    }

    //#endregion

}