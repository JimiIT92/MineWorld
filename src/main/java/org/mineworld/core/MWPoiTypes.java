package org.mineworld.core;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableSet;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.helper.RegistryHelper;
import org.mineworld.helper.ResourceHelper;

import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link PoiType Poi Types}
 */
public final class MWPoiTypes {

    //#region Registry

    /**
     * The {@link DeferredRegister<PoiType> POI Type Registry}
     */
    private static final DeferredRegister<PoiType> POI_TYPES = RegistryHelper.registry(ForgeRegistries.POI_TYPES);

    //#endregion

    //#region Poi Types

    public static final RegistryObject<PoiType> CARPENTER = registerPoiType("carpenter", Suppliers.memoize(() -> MWBlocks.WOODCUTTER.get()));
    public static final RegistryObject<PoiType> ETHEREAL_PORTAL = registerPoiType(ResourceHelper.portalName(MWDimensions.Dimensions.ETHEREAL), Suppliers.memoize(() -> MWBlocks.ETHEREAL_PORTAL.get()));

    //#endregion

    //#region Methods

    /**
     * Register a {@link PoiType POI Type}
     *
     * @param name {@link String The POI Type name}
     * @param blockSupplier {@link Supplier<Block> The Supplier for the Block this POI Type is referring to}
     * @return {@link RegistryObject<PoiType> The registered POI Type}
     */
    private static RegistryObject<PoiType> registerPoiType(final String name, final Supplier<? extends Block> blockSupplier) {
        return POI_TYPES.register(name, () -> new PoiType(ImmutableSet.copyOf(blockSupplier.get().getStateDefinition().getPossibleStates()), 1, 1));
    }

    //#endregion

    //#region Bus Register

    /**
     * Register all {@link PoiType POI Types}
     *
     * @param eventBus {@link IEventBus The mod event bus}
     */
    public static void register(final IEventBus eventBus) {
        POI_TYPES.register(eventBus);
    }

    //#endregion

}