package org.mineworld.core;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableSet;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.helper.RegistryHelper;
import org.mineworld.helper.ResourceHelper;

import java.util.Arrays;
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
    public static final Supplier<PoiType> FISHERMAN = getPoiType(
            Suppliers.memoize(() -> Blocks.BARREL),
            Suppliers.memoize(() -> MWBlocks.OAK_BARREL.get()),
            Suppliers.memoize(() -> MWBlocks.BIRCH_BARREL.get()),
            Suppliers.memoize(() -> MWBlocks.JUNGLE_BARREL.get()),
            Suppliers.memoize(() -> MWBlocks.ACACIA_BARREL.get()),
            Suppliers.memoize(() -> MWBlocks.DARK_OAK_BARREL.get()),
            Suppliers.memoize(() -> MWBlocks.MANGROVE_BARREL.get()),
            Suppliers.memoize(() -> MWBlocks.CHERRY_BARREL.get()),
            Suppliers.memoize(() -> MWBlocks.BAMBOO_BARREL.get()),
            Suppliers.memoize(() -> MWBlocks.CRIMSON_BARREL.get()),
            Suppliers.memoize(() -> MWBlocks.WARPED_BARREL.get()),
            Suppliers.memoize(() -> MWBlocks.APPLE_BARREL.get()),
            Suppliers.memoize(() -> MWBlocks.PALM_BARREL.get()),
            Suppliers.memoize(() -> MWBlocks.DEAD_BARREL.get()),
            Suppliers.memoize(() -> MWBlocks.SCULK_BARREL.get())
    );
    public static final Supplier<PoiType> LIBRARIAN = getPoiType(
            Suppliers.memoize(() -> Blocks.LECTERN),
            Suppliers.memoize(() -> MWBlocks.SPRUCE_LECTERN.get()),
            Suppliers.memoize(() -> MWBlocks.BIRCH_LECTERN.get()),
            Suppliers.memoize(() -> MWBlocks.JUNGLE_LECTERN.get()),
            Suppliers.memoize(() -> MWBlocks.ACACIA_LECTERN.get()),
            Suppliers.memoize(() -> MWBlocks.DARK_OAK_LECTERN.get()),
            Suppliers.memoize(() -> MWBlocks.MANGROVE_LECTERN.get()),
            Suppliers.memoize(() -> MWBlocks.CHERRY_LECTERN.get()),
            Suppliers.memoize(() -> MWBlocks.BAMBOO_LECTERN.get()),
            Suppliers.memoize(() -> MWBlocks.CRIMSON_LECTERN.get()),
            Suppliers.memoize(() -> MWBlocks.WARPED_LECTERN.get()),
            Suppliers.memoize(() -> MWBlocks.APPLE_LECTERN.get()),
            Suppliers.memoize(() -> MWBlocks.PALM_LECTERN.get()),
            Suppliers.memoize(() -> MWBlocks.DEAD_LECTERN.get()),
            Suppliers.memoize(() -> MWBlocks.SCULK_LECTERN.get())
    );

    //#endregion

    //#region Methods

    /**
     * Register a {@link PoiType POI Type}
     *
     * @param name {@link String The POI Type name}
     * @param blockSuppliers {@link Supplier<Block> The Supplier for the Blocks this POI Type is referring to}
     * @return {@link RegistryObject<PoiType> The registered POI Type}
     */
    @SafeVarargs
    private static RegistryObject<PoiType> registerPoiType(final String name, final Supplier<Block>... blockSuppliers) {
        return POI_TYPES.register(name, getPoiType(blockSuppliers));
    }

    /**
     * Get a {@link PoiType POI Type}
     *
     * @param blockSuppliers {@link Supplier<Block> The Supplier for the Blocks this POI Type is referring to}
     * @return {@link RegistryObject<PoiType> The registered POI Type}
     */
    @SafeVarargs
    private static Supplier<PoiType> getPoiType(final Supplier<Block>... blockSuppliers) {
        return Suppliers.memoize(() -> new PoiType(
                Arrays.stream(blockSuppliers).map(x -> x.get())
                        .flatMap((block) -> block.getStateDefinition().getPossibleStates().stream())
                        .collect(ImmutableSet.toImmutableSet()), 1, 1));
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