package org.mineworld.core;

import com.google.common.base.Suppliers;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.block.weathering.*;

import java.util.Locale;
import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link IMWChangeOverTimeBlock Copper Blocks}
 */
public final class MWCopperBlocks {

    //#region Copper Blocks

    //#region Stairs

    public static final RegistryObject<Block> OXIDIZED_COPPER_STAIRS = registerStair("copper", WeatheringCopper.WeatherState.OXIDIZED, false);
    public static final RegistryObject<Block> WEATHERED_COPPER_STAIRS = registerStair("copper", WeatheringCopper.WeatherState.WEATHERED, false);
    public static final RegistryObject<Block> EXPOSED_COPPER_STAIRS = registerStair("copper", WeatheringCopper.WeatherState.EXPOSED, false);
    public static final RegistryObject<Block> COPPER_STAIRS = registerStair("copper", WeatheringCopper.WeatherState.UNAFFECTED, false);
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_STAIRS = registerStair("copper", WeatheringCopper.WeatherState.OXIDIZED, true);
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_STAIRS = registerStair("copper", WeatheringCopper.WeatherState.WEATHERED, true);
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_STAIRS = registerStair("copper", WeatheringCopper.WeatherState.EXPOSED, true);
    public static final RegistryObject<Block> WAXED_COPPER_STAIRS = registerStair("copper", WeatheringCopper.WeatherState.UNAFFECTED, true);

    //#endregion

    //#region Slabs

    public static final RegistryObject<Block> OXIDIZED_COPPER_SLAB = registerSlab("copper", WeatheringCopper.WeatherState.OXIDIZED, false);
    public static final RegistryObject<Block> WEATHERED_COPPER_SLAB = registerSlab("copper", WeatheringCopper.WeatherState.WEATHERED, false);
    public static final RegistryObject<Block> EXPOSED_COPPER_SLAB = registerSlab("copper", WeatheringCopper.WeatherState.EXPOSED, false);
    public static final RegistryObject<Block> COPPER_SLAB = registerSlab("copper", WeatheringCopper.WeatherState.UNAFFECTED, false);
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_SLAB = registerSlab("copper", WeatheringCopper.WeatherState.OXIDIZED, true);
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_SLAB = registerSlab("copper", WeatheringCopper.WeatherState.WEATHERED, true);
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_SLAB = registerSlab("copper", WeatheringCopper.WeatherState.EXPOSED, true);
    public static final RegistryObject<Block> WAXED_COPPER_SLAB = registerSlab("copper", WeatheringCopper.WeatherState.UNAFFECTED, true);

    //#endregion

    //#region Doors and Trapdoors

    public static final RegistryObject<Block> COPPER_DOOR = MWBlocks.registerDoor("copper", true, MWBlockSetTypes.COPPER);
    public static final RegistryObject<Block> OXIDIZED_COPPER_TRAPDOOR = registerTrapdoor("copper", WeatheringCopper.WeatherState.OXIDIZED, false);
    public static final RegistryObject<Block> WEATHERED_COPPER_TRAPDOOR = registerTrapdoor("copper", WeatheringCopper.WeatherState.WEATHERED, false);
    public static final RegistryObject<Block> EXPOSED_COPPER_TRAPDOOR = registerTrapdoor("copper", WeatheringCopper.WeatherState.EXPOSED, false);
    public static final RegistryObject<Block> COPPER_TRAPDOOR = registerTrapdoor("copper", WeatheringCopper.WeatherState.UNAFFECTED, false);
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_TRAPDOOR = registerTrapdoor("copper", WeatheringCopper.WeatherState.OXIDIZED, true);
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_TRAPDOOR = registerTrapdoor("copper", WeatheringCopper.WeatherState.WEATHERED, true);
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_TRAPDOOR = registerTrapdoor("copper", WeatheringCopper.WeatherState.EXPOSED, true);
    public static final RegistryObject<Block> WAXED_COPPER_TRAPDOOR = registerTrapdoor("copper", WeatheringCopper.WeatherState.UNAFFECTED, true);

    //#endregion

    //#region Pressure Plates

    public static final RegistryObject<Block> OXIDIZED_COPPER_PRESSURE_PLATE = registerPressurePlate("copper", WeatheringCopper.WeatherState.OXIDIZED, false);
    public static final RegistryObject<Block> WEATHERED_COPPER_PRESSURE_PLATE = registerPressurePlate("copper", WeatheringCopper.WeatherState.WEATHERED, false);
    public static final RegistryObject<Block> EXPOSED_COPPER_PRESSURE_PLATE = registerPressurePlate("copper", WeatheringCopper.WeatherState.EXPOSED, false);
    public static final RegistryObject<Block> COPPER_PRESSURE_PLATE = registerPressurePlate("copper", WeatheringCopper.WeatherState.UNAFFECTED, false);
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_PRESSURE_PLATE = registerPressurePlate("copper", WeatheringCopper.WeatherState.OXIDIZED, true);
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_PRESSURE_PLATE = registerPressurePlate("copper", WeatheringCopper.WeatherState.WEATHERED, true);
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_PRESSURE_PLATE = registerPressurePlate("copper", WeatheringCopper.WeatherState.EXPOSED, true);
    public static final RegistryObject<Block> WAXED_COPPER_PRESSURE_PLATE = registerPressurePlate("copper", WeatheringCopper.WeatherState.UNAFFECTED, true);

    public static final RegistryObject<Block> OXIDIZED_CUT_COPPER_PRESSURE_PLATE = registerPressurePlate("cut_copper", WeatheringCopper.WeatherState.OXIDIZED, false);
    public static final RegistryObject<Block> WEATHERED_CUT_COPPER_PRESSURE_PLATE = registerPressurePlate("cut_copper", WeatheringCopper.WeatherState.WEATHERED, false);
    public static final RegistryObject<Block> EXPOSED_CUT_COPPER_PRESSURE_PLATE = registerPressurePlate("cut_copper", WeatheringCopper.WeatherState.EXPOSED, false);
    public static final RegistryObject<Block> CUT_COPPER_PRESSURE_PLATE = registerPressurePlate("cut_copper", WeatheringCopper.WeatherState.UNAFFECTED, false);
    public static final RegistryObject<Block> WAXED_OXIDIZED_CUT_COPPER_PRESSURE_PLATE = registerPressurePlate("cut_copper", WeatheringCopper.WeatherState.OXIDIZED, true);
    public static final RegistryObject<Block> WAXED_WEATHERED_CUT_COPPER_PRESSURE_PLATE = registerPressurePlate("cut_copper", WeatheringCopper.WeatherState.WEATHERED, true);
    public static final RegistryObject<Block> WAXED_EXPOSED_CUT_COPPER_PRESSURE_PLATE = registerPressurePlate("cut_copper", WeatheringCopper.WeatherState.EXPOSED, true);
    public static final RegistryObject<Block> WAXED_CUT_COPPER_PRESSURE_PLATE = registerPressurePlate("cut_copper", WeatheringCopper.WeatherState.UNAFFECTED, true);

    //#endregion

    //#region Chains

    public static final RegistryObject<Block> OXIDIZED_COPPER_CHAIN = registerChain("copper", WeatheringCopper.WeatherState.OXIDIZED, false);
    public static final RegistryObject<Block> WEATHERED_COPPER_CHAIN = registerChain("copper", WeatheringCopper.WeatherState.WEATHERED, false);
    public static final RegistryObject<Block> EXPOSED_COPPER_CHAIN = registerChain("copper", WeatheringCopper.WeatherState.EXPOSED, false);
    public static final RegistryObject<Block> COPPER_CHAIN = registerChain("copper", WeatheringCopper.WeatherState.UNAFFECTED, false);
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_CHAIN = registerChain("copper", WeatheringCopper.WeatherState.OXIDIZED, true);
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_CHAIN = registerChain("copper", WeatheringCopper.WeatherState.WEATHERED, true);
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_CHAIN = registerChain("copper", WeatheringCopper.WeatherState.EXPOSED, true);
    public static final RegistryObject<Block> WAXED_COPPER_CHAIN = registerChain("copper", WeatheringCopper.WeatherState.UNAFFECTED, true);

    //#endregion

    //#region Lanterns

    public static final RegistryObject<Block> OXIDIZED_COPPER_LANTERN = registerLantern("copper", WeatheringCopper.WeatherState.OXIDIZED, false, false);
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_LANTERN = registerLantern("copper", WeatheringCopper.WeatherState.OXIDIZED, true, false);
    public static final RegistryObject<Block> OXIDIZED_COPPER_SOUL_LANTERN = registerLantern("copper", WeatheringCopper.WeatherState.OXIDIZED, false, true);
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_SOUL_LANTERN = registerLantern("copper", WeatheringCopper.WeatherState.OXIDIZED, true, true);
    public static final RegistryObject<Block> WALL_HANGING_OXIDIZED_COPPER_LANTERN = registerWallHangingLantern(WeatheringCopper.WeatherState.OXIDIZED, false, false, Suppliers.memoize(() -> OXIDIZED_COPPER_LANTERN.get().defaultBlockState()));
    public static final RegistryObject<Block> WALL_HANGING_OXIDIZED_COPPER_SOUL_LANTERN = registerWallHangingLantern(WeatheringCopper.WeatherState.OXIDIZED, false, true, Suppliers.memoize(() -> OXIDIZED_COPPER_SOUL_LANTERN.get().defaultBlockState()));
    public static final RegistryObject<Block> WALL_HANGING_WAXED_OXIDIZED_COPPER_LANTERN = registerWallHangingLantern(WeatheringCopper.WeatherState.OXIDIZED, true, false, Suppliers.memoize(() -> WAXED_OXIDIZED_COPPER_LANTERN.get().defaultBlockState()));
    public static final RegistryObject<Block> WALL_HANGING_WAXED_OXIDIZED_COPPER_SOUL_LANTERN = registerWallHangingLantern(WeatheringCopper.WeatherState.OXIDIZED, true, true, Suppliers.memoize(() -> WAXED_OXIDIZED_COPPER_SOUL_LANTERN.get().defaultBlockState()));
    public static final RegistryObject<Block> WEATHERED_COPPER_LANTERN = registerLantern("copper", WeatheringCopper.WeatherState.WEATHERED, false, false);
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_LANTERN = registerLantern("copper", WeatheringCopper.WeatherState.WEATHERED, true, false);
    public static final RegistryObject<Block> WEATHERED_COPPER_SOUL_LANTERN = registerLantern("copper", WeatheringCopper.WeatherState.WEATHERED, false, true);
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_SOUL_LANTERN = registerLantern("copper", WeatheringCopper.WeatherState.WEATHERED, true, true);
    public static final RegistryObject<Block> WALL_HANGING_WEATHERED_COPPER_LANTERN = registerWallHangingLantern(WeatheringCopper.WeatherState.WEATHERED, false, false, Suppliers.memoize(() -> WEATHERED_COPPER_LANTERN.get().defaultBlockState()));
    public static final RegistryObject<Block> WALL_HANGING_WEATHERED_COPPER_SOUL_LANTERN = registerWallHangingLantern(WeatheringCopper.WeatherState.WEATHERED, false, true, Suppliers.memoize(() -> WEATHERED_COPPER_SOUL_LANTERN.get().defaultBlockState()));
    public static final RegistryObject<Block> WALL_HANGING_WAXED_WEATHERED_COPPER_LANTERN = registerWallHangingLantern(WeatheringCopper.WeatherState.WEATHERED, true, false, Suppliers.memoize(() -> WAXED_WEATHERED_COPPER_LANTERN.get().defaultBlockState()));
    public static final RegistryObject<Block> WALL_HANGING_WAXED_WEATHERED_COPPER_SOUL_LANTERN = registerWallHangingLantern(WeatheringCopper.WeatherState.WEATHERED, true, true, Suppliers.memoize(() -> WAXED_WEATHERED_COPPER_SOUL_LANTERN.get().defaultBlockState()));
    public static final RegistryObject<Block> EXPOSED_COPPER_LANTERN = registerLantern("copper", WeatheringCopper.WeatherState.EXPOSED, false, false);
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_LANTERN = registerLantern("copper", WeatheringCopper.WeatherState.EXPOSED, true, false);
    public static final RegistryObject<Block> EXPOSED_COPPER_SOUL_LANTERN = registerLantern("copper", WeatheringCopper.WeatherState.EXPOSED, false, true);
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_SOUL_LANTERN = registerLantern("copper", WeatheringCopper.WeatherState.EXPOSED, true, true);
    public static final RegistryObject<Block> WALL_HANGING_EXPOSED_COPPER_LANTERN = registerWallHangingLantern(WeatheringCopper.WeatherState.EXPOSED, false, false, Suppliers.memoize(() -> EXPOSED_COPPER_LANTERN.get().defaultBlockState()));
    public static final RegistryObject<Block> WALL_HANGING_EXPOSED_COPPER_SOUL_LANTERN = registerWallHangingLantern(WeatheringCopper.WeatherState.EXPOSED, false, true, Suppliers.memoize(() -> EXPOSED_COPPER_SOUL_LANTERN.get().defaultBlockState()));
    public static final RegistryObject<Block> WALL_HANGING_WAXED_EXPOSED_COPPER_LANTERN = registerWallHangingLantern(WeatheringCopper.WeatherState.EXPOSED, true, false, Suppliers.memoize(() -> WAXED_EXPOSED_COPPER_LANTERN.get().defaultBlockState()));
    public static final RegistryObject<Block> WALL_HANGING_WAXED_EXPOSED_COPPER_SOUL_LANTERN = registerWallHangingLantern(WeatheringCopper.WeatherState.EXPOSED, true, true, Suppliers.memoize(() -> WAXED_EXPOSED_COPPER_SOUL_LANTERN.get().defaultBlockState()));
    public static final RegistryObject<Block> COPPER_LANTERN = registerLantern("copper", WeatheringCopper.WeatherState.UNAFFECTED, false, false);
    public static final RegistryObject<Block> WAXED_COPPER_LANTERN = registerLantern("copper", WeatheringCopper.WeatherState.UNAFFECTED, true, false);
    public static final RegistryObject<Block> COPPER_SOUL_LANTERN = registerLantern("copper", WeatheringCopper.WeatherState.UNAFFECTED, false, true);
    public static final RegistryObject<Block> WAXED_COPPER_SOUL_LANTERN = registerLantern("copper", WeatheringCopper.WeatherState.UNAFFECTED, true, true);
    public static final RegistryObject<Block> WALL_HANGING_COPPER_LANTERN = registerWallHangingLantern(WeatheringCopper.WeatherState.UNAFFECTED, false, false, Suppliers.memoize(() -> COPPER_LANTERN.get().defaultBlockState()));
    public static final RegistryObject<Block> WALL_HANGING_COPPER_SOUL_LANTERN = registerWallHangingLantern(WeatheringCopper.WeatherState.UNAFFECTED, false, true, Suppliers.memoize(() -> COPPER_SOUL_LANTERN.get().defaultBlockState()));
    public static final RegistryObject<Block> WALL_HANGING_WAXED_COPPER_LANTERN = registerWallHangingLantern(WeatheringCopper.WeatherState.UNAFFECTED, true, false, Suppliers.memoize(() -> WAXED_COPPER_LANTERN.get().defaultBlockState()));
    public static final RegistryObject<Block> WALL_HANGING_WAXED_COPPER_SOUL_LANTERN = registerWallHangingLantern(WeatheringCopper.WeatherState.UNAFFECTED, true, true, Suppliers.memoize(() -> WAXED_COPPER_SOUL_LANTERN.get().defaultBlockState()));


    //#endregion

    //#region Bars

    public static final RegistryObject<Block> OXIDIZED_COPPER_BARS = registerBars("copper", WeatheringCopper.WeatherState.OXIDIZED, false);
    public static final RegistryObject<Block> WEATHERED_COPPER_BARS = registerBars("copper", WeatheringCopper.WeatherState.WEATHERED, false);
    public static final RegistryObject<Block> EXPOSED_COPPER_BARS = registerBars("copper", WeatheringCopper.WeatherState.EXPOSED, false);
    public static final RegistryObject<Block> COPPER_BARS = registerBars("copper", WeatheringCopper.WeatherState.UNAFFECTED, false);
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_BARS = registerBars("copper", WeatheringCopper.WeatherState.OXIDIZED, true);
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_BARS = registerBars("copper", WeatheringCopper.WeatherState.WEATHERED, true);
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_BARS = registerBars("copper", WeatheringCopper.WeatherState.EXPOSED, true);
    public static final RegistryObject<Block> WAXED_COPPER_BARS = registerBars("copper", WeatheringCopper.WeatherState.UNAFFECTED, true);

    //#endregion

    //#region Cages

    public static final RegistryObject<Block> OXIDIZED_COPPER_CAGE = registerCage("copper", WeatheringCopper.WeatherState.OXIDIZED, false);
    public static final RegistryObject<Block> WEATHERED_COPPER_CAGE = registerCage("copper", WeatheringCopper.WeatherState.WEATHERED, false);
    public static final RegistryObject<Block> EXPOSED_COPPER_CAGE = registerCage("copper", WeatheringCopper.WeatherState.EXPOSED, false);
    public static final RegistryObject<Block> COPPER_CAGE = registerCage("copper", WeatheringCopper.WeatherState.UNAFFECTED, false);
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_CAGE = registerCage("copper", WeatheringCopper.WeatherState.OXIDIZED, true);
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_CAGE = registerCage("copper", WeatheringCopper.WeatherState.WEATHERED, true);
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_CAGE = registerCage("copper", WeatheringCopper.WeatherState.EXPOSED, true);
    public static final RegistryObject<Block> WAXED_COPPER_CAGE = registerCage("copper", WeatheringCopper.WeatherState.UNAFFECTED, true);

    //#endregion

    //#region Grates

    public static final RegistryObject<Block> OXIDIZED_COPPER_GRATE = registerGrate("copper", WeatheringCopper.WeatherState.OXIDIZED, Suppliers.memoize(() -> OXIDIZED_COPPER_BARS.get().defaultBlockState()), false);
    public static final RegistryObject<Block> WEATHERED_COPPER_GRATE = registerGrate("copper", WeatheringCopper.WeatherState.WEATHERED, Suppliers.memoize(() -> WEATHERED_COPPER_BARS.get().defaultBlockState()), false);
    public static final RegistryObject<Block> EXPOSED_COPPER_GRATE = registerGrate("copper", WeatheringCopper.WeatherState.EXPOSED, Suppliers.memoize(() -> EXPOSED_COPPER_BARS.get().defaultBlockState()), false);
    public static final RegistryObject<Block> COPPER_GRATE = registerGrate("copper", WeatheringCopper.WeatherState.UNAFFECTED, Suppliers.memoize(() -> COPPER_BARS.get().defaultBlockState()), false);
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_GRATE = registerGrate("copper", WeatheringCopper.WeatherState.OXIDIZED, Suppliers.memoize(() -> WAXED_OXIDIZED_COPPER_BARS.get().defaultBlockState()), true);
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_GRATE = registerGrate("copper", WeatheringCopper.WeatherState.WEATHERED, Suppliers.memoize(() -> WAXED_WEATHERED_COPPER_BARS.get().defaultBlockState()), true);
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_GRATE = registerGrate("copper", WeatheringCopper.WeatherState.EXPOSED, Suppliers.memoize(() -> WAXED_EXPOSED_COPPER_BARS.get().defaultBlockState()), true);
    public static final RegistryObject<Block> WAXED_COPPER_GRATE = registerGrate("copper", WeatheringCopper.WeatherState.UNAFFECTED, Suppliers.memoize(() -> WAXED_COPPER_BARS.get().defaultBlockState()), true);

    //#endregion

    //#endregion

    //#region Methods

    /**
     * Register a {@link MWWeatheringCopperStairBlock Copper Stair}
     *
     * @param materialName {@link String The Stair material name}
     * @param weatherState {@link WeatheringCopper.WeatherState The Weather State}
     * @param isWaxed {@link Boolean If the Block is waxed}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerStair(final String materialName, final WeatheringCopper.WeatherState weatherState, final boolean isWaxed, final FeatureFlag... featureFlags) {
        return registerCopperBlock(materialName, "stairs", weatherState, isWaxed, Suppliers.memoize(() -> new MWWeatheringCopperStairBlock(weatherState, getBlockStateSupplierFromWeatherState(weatherState, isWaxed), featureFlags)));
    }

    /**
     * Register a {@link MWWeatheringCopperSlabBlock Copper Slab}
     *
     * @param materialName {@link String The Slab material name}
     * @param weatherState {@link WeatheringCopper.WeatherState The Weather State}
     * @param isWaxed {@link Boolean If the Block is waxed}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerSlab(final String materialName, final WeatheringCopper.WeatherState weatherState, final boolean isWaxed, final FeatureFlag... featureFlags) {
        return registerCopperBlock(materialName, "slab", weatherState, isWaxed, Suppliers.memoize(() -> new MWWeatheringCopperSlabBlock(weatherState, getBlockStateSupplierFromWeatherState(weatherState, isWaxed), featureFlags)));
    }

    /**
     * Register a {@link WeatheringCopperTrapdoorBlock Copper Trapdoor}
     *
     * @param materialName {@link String The Trapdoor material name}
     * @param weatherState {@link WeatheringCopper.WeatherState The Weather State}
     * @param isWaxed {@link Boolean If the Block is waxed}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerTrapdoor(final String materialName, final WeatheringCopper.WeatherState weatherState, final boolean isWaxed, final FeatureFlag... featureFlags) {
        return registerCopperBlock(materialName, "trapdoor", weatherState, isWaxed, Suppliers.memoize(() -> new WeatheringCopperTrapdoorBlock(weatherState, featureFlags)));
    }

    /**
     * Register a {@link WeatheringCopperPressurePlateBlock Copper Pressure Plate}
     *
     * @param materialName {@link String The Pressure Plate material name}
     * @param weatherState {@link WeatheringCopper.WeatherState The Weather State}
     * @param isWaxed {@link Boolean If the Block is waxed}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerPressurePlate(final String materialName, final WeatheringCopper.WeatherState weatherState, final boolean isWaxed, final FeatureFlag... featureFlags) {
        return registerCopperBlock(materialName, "pressure_plate", weatherState, isWaxed, Suppliers.memoize(() -> new WeatheringCopperPressurePlateBlock(weatherState, featureFlags)));
    }

    /**
     * Register a {@link WeatheringCopperChainBlock Copper Chain}
     *
     * @param materialName {@link String The Chain material name}
     * @param weatherState {@link WeatheringCopper.WeatherState The Weather State}
     * @param isWaxed {@link Boolean If the Block is waxed}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerChain(final String materialName, final WeatheringCopper.WeatherState weatherState, final boolean isWaxed, final FeatureFlag... featureFlags) {
        return registerCopperBlock(materialName, "chain", weatherState, isWaxed, Suppliers.memoize(() -> new WeatheringCopperChainBlock(weatherState, featureFlags)));
    }

    /**
     * Register a {@link WeatheringCopperLanternBlock Copper Lantern}
     *
     * @param materialName {@link String The Lantern material name}
     * @param weatherState {@link WeatheringCopper.WeatherState The Weather State}
     * @param isWaxed {@link Boolean If the Block is waxed}
     * @param isSoulLantern {@link Boolean If the Lantern is a Soul Lantern}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerLantern(final String materialName, final WeatheringCopper.WeatherState weatherState, final boolean isWaxed, final boolean isSoulLantern, final FeatureFlag... featureFlags) {
        return registerCopperBlock(materialName, (isSoulLantern ? "soul_lantern" : "lantern"), weatherState, isWaxed, Suppliers.memoize(() -> new WeatheringCopperLanternBlock(weatherState, isSoulLantern ? 10 : 15, featureFlags)));
    }

    /**
     * Register a {@link WeatheringCopperLanternBlock Copper Lantern}
     *
     * @param weatherState {@link WeatheringCopper.WeatherState The Weather State}
     * @param isWaxed {@link Boolean If the Block is waxed}
     * @param isSoulLantern {@link Boolean If the Lantern is a Soul Lantern}
     * @param blockStateSupplier {@link Supplier<BlockState> The Supplier for the Block State this Lantern is based on}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerWallHangingLantern(final WeatheringCopper.WeatherState weatherState, final boolean isWaxed, final boolean isSoulLantern, final Supplier<BlockState> blockStateSupplier, final FeatureFlag... featureFlags) {
        return MWBlocks.registerBlockWithoutBlockItem("wall_hanging_" + getCopperBlockName("copper", weatherState, isWaxed, (isSoulLantern ? "soul_lantern" : "lantern")), Suppliers.memoize(() -> new WeatheringWallHangingLanternBlock(weatherState, blockStateSupplier, featureFlags)));
    }

    /**
     * Register a {@link WeatheringCopperBarsBlock Copper Bars}
     *
     * @param materialName {@link String The Bars material name}
     * @param weatherState {@link WeatheringCopper.WeatherState The Weather State}
     * @param isWaxed {@link Boolean If the Block is waxed}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerBars(final String materialName, final WeatheringCopper.WeatherState weatherState, final boolean isWaxed, final FeatureFlag... featureFlags) {
        return registerCopperBlock(materialName, "bars", weatherState, isWaxed, Suppliers.memoize(() -> new WeatheringCopperBarsBlock(weatherState, featureFlags)));
    }

    /**
     * Register a {@link WeatheringCopperCageBlock Copper Cage}
     *
     * @param materialName {@link String The Cage material name}
     * @param weatherState {@link WeatheringCopper.WeatherState The Weather State}
     * @param isWaxed {@link Boolean If the Block is waxed}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerCage(final String materialName, final WeatheringCopper.WeatherState weatherState, final boolean isWaxed, final FeatureFlag... featureFlags) {
        return registerCopperBlock(materialName, "cage", weatherState, isWaxed, Suppliers.memoize(() -> new WeatheringCopperCageBlock(weatherState, featureFlags)));
    }

    /**
     * Register a {@link WeatheringHorizontalPaneBlock Copper Grate}
     *
     * @param materialName {@link String The Grate material name}
     * @param weatherState {@link WeatheringCopper.WeatherState The Weather State}
     * @param isWaxed {@link Boolean If the Block is waxed}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerGrate(final String materialName, final WeatheringCopper.WeatherState weatherState, final Supplier<BlockState> blockStateSupplier, final boolean isWaxed, final FeatureFlag... featureFlags) {
        return MWBlocks.registerBlockWithoutBlockItem(getCopperBlockName(materialName, weatherState, isWaxed, "grate"), Suppliers.memoize(() -> new WeatheringHorizontalPaneBlock(WeatheringCopper.WeatherState.OXIDIZED, blockStateSupplier, featureFlags)));
    }

    /**
     * Register a {@link Block Copper Block}
     *
     * @param materialName {@link String The Chain material name}
     * @param suffix {@link String The Block name suffix}
     * @param weatherState {@link WeatheringCopper.WeatherState The Weather State}
     * @param isWaxed {@link Boolean If the Block is waxed}
     * @param blockSupplier {@link Supplier<Block> The Block supplier}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerCopperBlock(final String materialName, final String suffix, final WeatheringCopper.WeatherState weatherState, final boolean isWaxed, final Supplier<Block> blockSupplier) {
        return MWBlocks.registerBlock(getCopperBlockName(materialName, weatherState, isWaxed, suffix), blockSupplier);
    }

    /**
     * Get the {@link String Weather State name}
     *
     * @param weatherState {@link WeatheringCopper.WeatherState The Weather State}
     * @return {@link String The Weather State name}
     */
    private static String getWeatherStateName(final WeatheringCopper.WeatherState weatherState) {
        return weatherState.equals(WeatheringCopper.WeatherState.UNAFFECTED) ? "" : weatherState.name().toLowerCase(Locale.ROOT) + "_";
    }

    /**
     * Get a {@link Supplier<BlockState> Block State supplier} based on the {@link WeatheringCopper.WeatherState Weather State}
     *
     * @param weatherState {@link WeatheringCopper.WeatherState The Weather State}
     * @param isWaxed {@link Boolean If the Block is waxed}
     * @return {@link Supplier<BlockState> The Block State supplier}
     */
    private static Supplier<BlockState> getBlockStateSupplierFromWeatherState(final WeatheringCopper.WeatherState weatherState, final boolean isWaxed) {
        return switch (weatherState) {
            case UNAFFECTED -> (isWaxed ? Blocks.WAXED_COPPER_BLOCK : Blocks.COPPER_BLOCK)::defaultBlockState;
            case EXPOSED -> (isWaxed ? Blocks.WAXED_EXPOSED_COPPER : Blocks.EXPOSED_COPPER)::defaultBlockState;
            case WEATHERED -> (isWaxed ? Blocks.WAXED_WEATHERED_COPPER : Blocks.WEATHERED_COPPER)::defaultBlockState;
            case OXIDIZED -> (isWaxed ? Blocks.WAXED_OXIDIZED_COPPER : Blocks.OXIDIZED_COPPER)::defaultBlockState;
        };
    }

    /**
     * Get a {@link String Copper Block name}
     *
     * @param materialName {@link String The Copper Block material name}
     * @param weatherState {@link WeatheringCopper.WeatherState The Weather State}
     * @param isWaxed {@link Boolean If the Block is waxed}
     * @param suffix {@link String The Block name suffix}
     * @return {@link String The Copper Block name}
     */
    private static String getCopperBlockName(final String materialName, final WeatheringCopper.WeatherState weatherState, final boolean isWaxed, final String suffix) {
        return (isWaxed ? "waxed_" : "") + getWeatherStateName(weatherState) + materialName + "_" + suffix;
    }

    //#endregion

    //#region Bus register

    /**
     * Register all {@link IMWChangeOverTimeBlock Copper Blocks}
     */
    public static void register() { }

    //#endregion

}