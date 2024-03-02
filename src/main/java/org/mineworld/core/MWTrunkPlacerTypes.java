package org.mineworld.core;

import com.google.common.base.Suppliers;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.world.worldgen.tree.trunkplacers.PalmTrunkPlacer;

import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link TrunkPlacerType Trunk Placer Types}
 */
public final class MWTrunkPlacerTypes {

    //#region Registry

    /**
     * The {@link DeferredRegister<TrunkPlacerType> Trunk Placer Types Registry}
     */
    private static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER_TYPES = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, MineWorld.MOD_ID);

    //#endregion

    //#region Trunk Placer Types

    public static final RegistryObject<TrunkPlacerType<PalmTrunkPlacer>> PALM_TRUNK_PLACER = registerTrunkPlacerType("palm", Suppliers.memoize(() -> new TrunkPlacerType<>(PalmTrunkPlacer.CODEC)));

    //#endregion

    //#region Methods

    /**
     * Register a {@link TrunkPlacerType Trunk Placer Type}
     *
     * @param name {@link String The Trunk Placer Type name}
     * @param trunkPlacerSupplier {@link Supplier<TrunkPlacerType> The Trunk Placer Type Supplier}
     * @return {@link RegistryObject <TrunkPlacerType> The registered Trunk Placer Type}
     * @param <TP> {@link TP The Trunk Placer Type}
     */
    private static <TP extends TrunkPlacer> RegistryObject<TrunkPlacerType<TP>> registerTrunkPlacerType(final String name, final Supplier<? extends TrunkPlacerType<TP>> trunkPlacerSupplier) {
        return TRUNK_PLACER_TYPES.register(name + "_trunk_placer", trunkPlacerSupplier);
    }

    //#endregion

    //#region Bus Register

    /**
     * Register all {@link TrunkPlacerType Trunk Placer Types}
     *
     * @param eventBus {@link IEventBus The mod event bus}
     */
    public static void register(final IEventBus eventBus) {
        TRUNK_PLACER_TYPES.register(eventBus);
    }

    //#endregion

}