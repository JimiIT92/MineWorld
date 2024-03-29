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
    public static final Supplier<PoiType> CARTOGRAPHER = getPoiType(
            Suppliers.memoize(() -> Blocks.CARTOGRAPHY_TABLE),
            Suppliers.memoize(() -> MWBlocks.OAK_CARTOGRAPHY_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.SPRUCE_CARTOGRAPHY_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.BIRCH_CARTOGRAPHY_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.JUNGLE_CARTOGRAPHY_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.ACACIA_CARTOGRAPHY_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.MANGROVE_CARTOGRAPHY_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.CHERRY_CARTOGRAPHY_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.BAMBOO_CARTOGRAPHY_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.CRIMSON_CARTOGRAPHY_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.WARPED_CARTOGRAPHY_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.APPLE_CARTOGRAPHY_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.PALM_CARTOGRAPHY_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.DEAD_CARTOGRAPHY_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.SCULK_CARTOGRAPHY_TABLE.get())
    );
    public static final Supplier<PoiType> FLETCHER = getPoiType(
            Suppliers.memoize(() -> Blocks.FLETCHING_TABLE),
            Suppliers.memoize(() -> MWBlocks.OAK_FLETCHING_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.SPRUCE_FLETCHING_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.JUNGLE_FLETCHING_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.ACACIA_FLETCHING_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.DARK_OAK_FLETCHING_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.MANGROVE_FLETCHING_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.CHERRY_FLETCHING_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.BAMBOO_FLETCHING_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.CRIMSON_FLETCHING_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.WARPED_FLETCHING_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.APPLE_FLETCHING_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.PALM_FLETCHING_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.DEAD_FLETCHING_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.SCULK_FLETCHING_TABLE.get())
    );
    public static final Supplier<PoiType> TOOLSMITH = getPoiType(
            Suppliers.memoize(() -> Blocks.SMITHING_TABLE),
            Suppliers.memoize(() -> MWBlocks.OAK_SMITHING_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.SPRUCE_SMITHING_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.BIRCH_SMITHING_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.JUNGLE_SMITHING_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.ACACIA_SMITHING_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.DARK_OAK_SMITHING_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.CHERRY_SMITHING_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.BAMBOO_SMITHING_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.CRIMSON_SMITHING_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.WARPED_SMITHING_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.APPLE_SMITHING_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.PALM_SMITHING_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.DEAD_SMITHING_TABLE.get()),
            Suppliers.memoize(() -> MWBlocks.SCULK_SMITHING_TABLE.get())
    );
    public static final Supplier<PoiType> SHEPHERD = getPoiType(
            Suppliers.memoize(() -> Blocks.LOOM),
            Suppliers.memoize(() -> MWBlocks.SPRUCE_LOOM.get()),
            Suppliers.memoize(() -> MWBlocks.BIRCH_LOOM.get()),
            Suppliers.memoize(() -> MWBlocks.JUNGLE_LOOM.get()),
            Suppliers.memoize(() -> MWBlocks.ACACIA_LOOM.get()),
            Suppliers.memoize(() -> MWBlocks.DARK_OAK_LOOM.get()),
            Suppliers.memoize(() -> MWBlocks.MANGROVE_LOOM.get()),
            Suppliers.memoize(() -> MWBlocks.CHERRY_LOOM.get()),
            Suppliers.memoize(() -> MWBlocks.BAMBOO_LOOM.get()),
            Suppliers.memoize(() -> MWBlocks.CRIMSON_LOOM.get()),
            Suppliers.memoize(() -> MWBlocks.WARPED_LOOM.get()),
            Suppliers.memoize(() -> MWBlocks.APPLE_LOOM.get()),
            Suppliers.memoize(() -> MWBlocks.PALM_LOOM.get()),
            Suppliers.memoize(() -> MWBlocks.DEAD_LOOM.get()),
            Suppliers.memoize(() -> MWBlocks.SCULK_LOOM.get())
    );
    public static final Supplier<PoiType> FARMER = getPoiType(
            Suppliers.memoize(() -> Blocks.COMPOSTER),
            Suppliers.memoize(() -> MWBlocks.OAK_COMPOSTER.get()),
            Suppliers.memoize(() -> MWBlocks.BIRCH_COMPOSTER.get()),
            Suppliers.memoize(() -> MWBlocks.JUNGLE_COMPOSTER.get()),
            Suppliers.memoize(() -> MWBlocks.ACACIA_COMPOSTER.get()),
            Suppliers.memoize(() -> MWBlocks.DARK_OAK_COMPOSTER.get()),
            Suppliers.memoize(() -> MWBlocks.MANGROVE_COMPOSTER.get()),
            Suppliers.memoize(() -> MWBlocks.CHERRY_COMPOSTER.get()),
            Suppliers.memoize(() -> MWBlocks.BAMBOO_COMPOSTER.get()),
            Suppliers.memoize(() -> MWBlocks.CRIMSON_COMPOSTER.get()),
            Suppliers.memoize(() -> MWBlocks.WARPED_COMPOSTER.get()),
            Suppliers.memoize(() -> MWBlocks.APPLE_COMPOSTER.get()),
            Suppliers.memoize(() -> MWBlocks.PALM_COMPOSTER.get()),
            Suppliers.memoize(() -> MWBlocks.DEAD_COMPOSTER.get()),
            Suppliers.memoize(() -> MWBlocks.SCULK_COMPOSTER.get())
    );
    public static final Supplier<PoiType> BEEHIVE = getPoiType(
            Suppliers.memoize(() -> Blocks.BEEHIVE),
            Suppliers.memoize(() -> MWBlocks.SPRUCE_BEEHIVE.get()),
            Suppliers.memoize(() -> MWBlocks.BIRCH_BEEHIVE.get()),
            Suppliers.memoize(() -> MWBlocks.JUNGLE_BEEHIVE.get()),
            Suppliers.memoize(() -> MWBlocks.ACACIA_BEEHIVE.get()),
            Suppliers.memoize(() -> MWBlocks.DARK_OAK_BEEHIVE.get()),
            Suppliers.memoize(() -> MWBlocks.MANGROVE_BEEHIVE.get()),
            Suppliers.memoize(() -> MWBlocks.CHERRY_BEEHIVE.get()),
            Suppliers.memoize(() -> MWBlocks.BAMBOO_BEEHIVE.get()),
            Suppliers.memoize(() -> MWBlocks.CRIMSON_BEEHIVE.get()),
            Suppliers.memoize(() -> MWBlocks.WARPED_BEEHIVE.get()),
            Suppliers.memoize(() -> MWBlocks.APPLE_BEEHIVE.get()),
            Suppliers.memoize(() -> MWBlocks.PALM_BEEHIVE.get()),
            Suppliers.memoize(() -> MWBlocks.DEAD_BEEHIVE.get()),
            Suppliers.memoize(() -> MWBlocks.SCULK_BEEHIVE.get())
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