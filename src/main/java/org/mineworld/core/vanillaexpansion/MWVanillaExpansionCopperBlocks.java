package org.mineworld.core.vanillaexpansion;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.block.*;
import org.mineworld.core.MWBlockSetTypes;
import org.mineworld.core.MWBlocks;

/**
 * {@link MineWorld MineWorld} {@link Block vanilla expansion metal blocks}
 */
public final class MWVanillaExpansionCopperBlocks {

    //#region Blocks

    public static final RegistryObject<Block> OXIDIZED_COPPER_STAIRS = MWBlocks.registerBlock("oxidized_copper_stairs",
            () -> new MWWeatheringCopperStairBlock(WeatheringCopper.WeatherState.OXIDIZED, Blocks.OXIDIZED_COPPER.defaultBlockState(), false));
    public static final RegistryObject<Block> WEATHERED_COPPER_STAIRS = MWBlocks.registerBlock("weathered_copper_stairs",
            () -> new MWWeatheringCopperStairBlock(WeatheringCopper.WeatherState.WEATHERED, Blocks.WEATHERED_COPPER.defaultBlockState(), false));
    public static final RegistryObject<Block> EXPOSED_COPPER_STAIRS = MWBlocks.registerBlock("exposed_copper_stairs",
            () -> new MWWeatheringCopperStairBlock(WeatheringCopper.WeatherState.EXPOSED, Blocks.EXPOSED_COPPER.defaultBlockState(), false));
    public static final RegistryObject<Block> COPPER_STAIRS = MWBlocks.registerBlock("copper_stairs",
            () -> new MWWeatheringCopperStairBlock(WeatheringCopper.WeatherState.UNAFFECTED, Blocks.COPPER_BLOCK.defaultBlockState(), false));
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_STAIRS = MWBlocks.registerBlock("waxed_oxidized_copper_stairs",
            () -> new MWWeatheringCopperStairBlock(WeatheringCopper.WeatherState.OXIDIZED, Blocks.WAXED_OXIDIZED_COPPER.defaultBlockState(), true));
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_STAIRS = MWBlocks.registerBlock("waxed_weathered_copper_stairs",
            () -> new MWWeatheringCopperStairBlock(WeatheringCopper.WeatherState.WEATHERED, Blocks.WAXED_WEATHERED_COPPER.defaultBlockState(), true));
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_STAIRS = MWBlocks.registerBlock("waxed_exposed_copper_stairs",
            () -> new MWWeatheringCopperStairBlock(WeatheringCopper.WeatherState.EXPOSED, Blocks.WAXED_EXPOSED_COPPER.defaultBlockState(), true));
    public static final RegistryObject<Block> WAXED_COPPER_STAIRS = MWBlocks.registerBlock("waxed_copper_stairs",
            () -> new MWWeatheringCopperStairBlock(WeatheringCopper.WeatherState.UNAFFECTED, Blocks.WAXED_COPPER_BLOCK.defaultBlockState(), true));
    public static final RegistryObject<Block> OXIDIZED_COPPER_SLAB = MWBlocks.registerBlock("oxidized_copper_slab",
            () -> new MWWeatheringCopperSlabBlock(WeatheringCopper.WeatherState.OXIDIZED, MWBlocks.copyFrom(Blocks.OXIDIZED_CUT_COPPER), false));
    public static final RegistryObject<Block> WEATHERED_COPPER_SLAB = MWBlocks.registerBlock("weathered_copper_slab",
            () -> new MWWeatheringCopperSlabBlock(WeatheringCopper.WeatherState.WEATHERED, MWBlocks.copyFrom(Blocks.WEATHERED_CUT_COPPER), false));
    public static final RegistryObject<Block> EXPOSED_COPPER_SLAB = MWBlocks.registerBlock("exposed_copper_slab",
            () -> new MWWeatheringCopperSlabBlock(WeatheringCopper.WeatherState.EXPOSED, MWBlocks.copyFrom(Blocks.EXPOSED_CUT_COPPER), false));
    public static final RegistryObject<Block> COPPER_SLAB = MWBlocks.registerBlock("copper_slab",
            () -> new MWWeatheringCopperSlabBlock(WeatheringCopper.WeatherState.UNAFFECTED, MWBlocks.copyFrom(Blocks.CUT_COPPER_SLAB), false));
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_SLAB = MWBlocks.registerBlock("waxed_oxidized_copper_slab",
            () -> new MWWeatheringCopperSlabBlock(WeatheringCopper.WeatherState.OXIDIZED, MWBlocks.copyFrom(Blocks.WAXED_OXIDIZED_CUT_COPPER), true));
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_SLAB = MWBlocks.registerBlock("waxed_weathered_copper_slab",
            () -> new MWWeatheringCopperSlabBlock(WeatheringCopper.WeatherState.WEATHERED, MWBlocks.copyFrom(Blocks.WAXED_WEATHERED_CUT_COPPER), true));
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_SLAB = MWBlocks.registerBlock("waxed_exposed_copper_slab",
            () -> new MWWeatheringCopperSlabBlock(WeatheringCopper.WeatherState.EXPOSED, MWBlocks.copyFrom(Blocks.WAXED_EXPOSED_CUT_COPPER), true));
    public static final RegistryObject<Block> WAXED_COPPER_SLAB = MWBlocks.registerBlock("waxed_copper_slab",
            () -> new MWWeatheringCopperSlabBlock(WeatheringCopper.WeatherState.UNAFFECTED, MWBlocks.copyFrom(Blocks.WAXED_CUT_COPPER_SLAB), true));
    public static final RegistryObject<Block> OXIDIZED_COPPER_PRESSURE_PLATE = MWBlocks.registerBlock("oxidized_copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.OXIDIZED, false));
    public static final RegistryObject<Block> WEATHERED_COPPER_PRESSURE_PLATE = MWBlocks.registerBlock("weathered_copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.WEATHERED, false));
    public static final RegistryObject<Block> EXPOSED_COPPER_PRESSURE_PLATE = MWBlocks.registerBlock("exposed_copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.EXPOSED, false));
    public static final RegistryObject<Block> COPPER_PRESSURE_PLATE = MWBlocks.registerBlock("copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.UNAFFECTED, false));
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_PRESSURE_PLATE = MWBlocks.registerBlock("waxed_oxidized_copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.OXIDIZED, true));
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_PRESSURE_PLATE = MWBlocks.registerBlock("waxed_weathered_copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.WEATHERED, true));
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_PRESSURE_PLATE = MWBlocks.registerBlock("waxed_exposed_copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.EXPOSED, true));
    public static final RegistryObject<Block> WAXED_COPPER_PRESSURE_PLATE = MWBlocks.registerBlock("waxed_copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.UNAFFECTED, true));
    public static final RegistryObject<Block> OXIDIZED_CUT_COPPER_PRESSURE_PLATE = MWBlocks.registerBlock("oxidized_cut_copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.OXIDIZED, false));
    public static final RegistryObject<Block> WEATHERED_CUT_COPPER_PRESSURE_PLATE = MWBlocks.registerBlock("weathered_cut_copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.WEATHERED, false));
    public static final RegistryObject<Block> EXPOSED_CUT_COPPER_PRESSURE_PLATE = MWBlocks.registerBlock("exposed_cut_copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.EXPOSED, false));
    public static final RegistryObject<Block> CUT_COPPER_PRESSURE_PLATE = MWBlocks.registerBlock("cut_copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.UNAFFECTED, false));
    public static final RegistryObject<Block> WAXED_OXIDIZED_CUT_COPPER_PRESSURE_PLATE = MWBlocks.registerBlock("waxed_oxidized_cut_copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.OXIDIZED, true));
    public static final RegistryObject<Block> WAXED_WEATHERED_CUT_COPPER_PRESSURE_PLATE = MWBlocks.registerBlock("waxed_weathered_cut_copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.WEATHERED, true));
    public static final RegistryObject<Block> WAXED_EXPOSED_CUT_COPPER_PRESSURE_PLATE = MWBlocks.registerBlock("waxed_exposed_cut_copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.EXPOSED, true));
    public static final RegistryObject<Block> WAXED_CUT_COPPER_PRESSURE_PLATE = MWBlocks.registerBlock("waxed_cut_copper_pressure_plate",
            () -> new WeatheringCopperPressurePlateBlock(WeatheringCopper.WeatherState.UNAFFECTED, true));
    public static final RegistryObject<Block> OXIDIZED_COPPER_TRAPDOOR = MWBlocks.registerBlock("oxidized_copper_trapdoor",
            () -> new WeatheringCopperTrapdoorBlock(WeatheringCopper.WeatherState.OXIDIZED, false));
    public static final RegistryObject<Block> WEATHERED_COPPER_TRAPDOOR = MWBlocks.registerBlock("weathered_copper_trapdoor",
            () -> new WeatheringCopperTrapdoorBlock(WeatheringCopper.WeatherState.WEATHERED, false));
    public static final RegistryObject<Block> EXPOSED_COPPER_TRAPDOOR = MWBlocks.registerBlock("exposed_copper_trapdoor",
            () -> new WeatheringCopperTrapdoorBlock(WeatheringCopper.WeatherState.EXPOSED, false));
    public static final RegistryObject<Block> COPPER_TRAPDOOR = MWBlocks.registerBlock("copper_trapdoor",
            () -> new WeatheringCopperTrapdoorBlock(WeatheringCopper.WeatherState.UNAFFECTED, false));
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_TRAPDOOR = MWBlocks.registerBlock("waxed_oxidized_copper_trapdoor",
            () -> new WeatheringCopperTrapdoorBlock(WeatheringCopper.WeatherState.OXIDIZED, true));
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_TRAPDOOR = MWBlocks.registerBlock("waxed_weathered_copper_trapdoor",
            () -> new WeatheringCopperTrapdoorBlock(WeatheringCopper.WeatherState.WEATHERED, true));
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_TRAPDOOR = MWBlocks.registerBlock("waxed_exposed_copper_trapdoor",
            () -> new WeatheringCopperTrapdoorBlock(WeatheringCopper.WeatherState.EXPOSED, true));
    public static final RegistryObject<Block> WAXED_COPPER_TRAPDOOR = MWBlocks.registerBlock("waxed_copper_trapdoor",
            () -> new WeatheringCopperTrapdoorBlock(WeatheringCopper.WeatherState.UNAFFECTED, true));
    public static final RegistryObject<Block> OXIDIZED_COPPER_CHAIN = MWBlocks.registerBlock("oxidized_copper_chain",
            () -> new WeatheringCopperChainBlock(WeatheringCopper.WeatherState.OXIDIZED, false));
    public static final RegistryObject<Block> WEATHERED_COPPER_CHAIN = MWBlocks.registerBlock("weathered_copper_chain",
            () -> new WeatheringCopperChainBlock(WeatheringCopper.WeatherState.WEATHERED, false));
    public static final RegistryObject<Block> EXPOSED_COPPER_CHAIN = MWBlocks.registerBlock("exposed_copper_chain",
            () -> new WeatheringCopperChainBlock(WeatheringCopper.WeatherState.EXPOSED, false));
    public static final RegistryObject<Block> COPPER_CHAIN = MWBlocks.registerBlock("copper_chain",
            () -> new WeatheringCopperChainBlock(WeatheringCopper.WeatherState.UNAFFECTED, false));
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_CHAIN = MWBlocks.registerBlock("waxed_oxidized_copper_chain",
            () -> new WeatheringCopperChainBlock(WeatheringCopper.WeatherState.OXIDIZED, true));
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_CHAIN = MWBlocks.registerBlock("waxed_weathered_copper_chain",
            () -> new WeatheringCopperChainBlock(WeatheringCopper.WeatherState.WEATHERED, true));
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_CHAIN = MWBlocks.registerBlock("waxed_exposed_copper_chain",
            () -> new WeatheringCopperChainBlock(WeatheringCopper.WeatherState.EXPOSED, true));
    public static final RegistryObject<Block> WAXED_COPPER_CHAIN = MWBlocks.registerBlock("waxed_copper_chain",
            () -> new WeatheringCopperChainBlock(WeatheringCopper.WeatherState.UNAFFECTED, true));
    public static final RegistryObject<Block> OXIDIZED_COPPER_LANTERN = MWBlocks.registerBlock("oxidized_copper_lantern",
            () -> new WeatheringCopperLanternBlock(WeatheringCopper.WeatherState.OXIDIZED, 15, false));
    public static final RegistryObject<Block> WEATHERED_COPPER_LANTERN = MWBlocks.registerBlock("weathered_copper_lantern",
            () -> new WeatheringCopperLanternBlock(WeatheringCopper.WeatherState.WEATHERED, 15, false));
    public static final RegistryObject<Block> EXPOSED_COPPER_LANTERN = MWBlocks.registerBlock("exposed_copper_lantern",
            () -> new WeatheringCopperLanternBlock(WeatheringCopper.WeatherState.EXPOSED, 15, false));
    public static final RegistryObject<Block> COPPER_LANTERN = MWBlocks.registerBlock("copper_lantern",
            () -> new WeatheringCopperLanternBlock(WeatheringCopper.WeatherState.UNAFFECTED, 15, false));
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_LANTERN = MWBlocks.registerBlock("waxed_oxidized_copper_lantern",
            () -> new WeatheringCopperLanternBlock(WeatheringCopper.WeatherState.OXIDIZED, 15, true));
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_LANTERN = MWBlocks.registerBlock("waxed_weathered_copper_lantern",
            () -> new WeatheringCopperLanternBlock(WeatheringCopper.WeatherState.WEATHERED, 15, true));
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_LANTERN = MWBlocks.registerBlock("waxed_exposed_copper_lantern",
            () -> new WeatheringCopperLanternBlock(WeatheringCopper.WeatherState.EXPOSED, 15, true));
    public static final RegistryObject<Block> WAXED_COPPER_LANTERN = MWBlocks.registerBlock("waxed_copper_lantern",
            () -> new WeatheringCopperLanternBlock(WeatheringCopper.WeatherState.UNAFFECTED, 15, true));
    public static final RegistryObject<Block> OXIDIZED_COPPER_SOUL_LANTERN = MWBlocks.registerBlock("oxidized_copper_soul_lantern",
            () -> new WeatheringCopperLanternBlock(WeatheringCopper.WeatherState.OXIDIZED, 10, false));
    public static final RegistryObject<Block> WEATHERED_COPPER_SOUL_LANTERN = MWBlocks.registerBlock("weathered_copper_soul_lantern",
            () -> new WeatheringCopperLanternBlock(WeatheringCopper.WeatherState.WEATHERED, 10, false));
    public static final RegistryObject<Block> EXPOSED_COPPER_SOUL_LANTERN = MWBlocks.registerBlock("exposed_copper_soul_lantern",
            () -> new WeatheringCopperLanternBlock(WeatheringCopper.WeatherState.EXPOSED, 10, false));
    public static final RegistryObject<Block> COPPER_SOUL_LANTERN = MWBlocks.registerBlock("copper_soul_lantern",
            () -> new WeatheringCopperLanternBlock(WeatheringCopper.WeatherState.UNAFFECTED, 10, false));
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_SOUL_LANTERN = MWBlocks.registerBlock("waxed_oxidized_copper_soul_lantern",
            () -> new WeatheringCopperLanternBlock(WeatheringCopper.WeatherState.OXIDIZED, 10, true));
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_SOUL_LANTERN = MWBlocks.registerBlock("waxed_weathered_copper_soul_lantern",
            () -> new WeatheringCopperLanternBlock(WeatheringCopper.WeatherState.WEATHERED, 10, true));
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_SOUL_LANTERN = MWBlocks.registerBlock("waxed_exposed_copper_soul_lantern",
            () -> new WeatheringCopperLanternBlock(WeatheringCopper.WeatherState.EXPOSED, 10, true));
    public static final RegistryObject<Block> WAXED_COPPER_SOUL_LANTERN = MWBlocks.registerBlock("waxed_copper_soul_lantern",
            () -> new WeatheringCopperLanternBlock(WeatheringCopper.WeatherState.UNAFFECTED, 10, true));
    public static final RegistryObject<Block> OXIDIZED_COPPER_BARS = MWBlocks.registerBlock("oxidized_copper_bars",
            () -> new WeatheringCopperBarsBlock(WeatheringCopper.WeatherState.OXIDIZED, false));
    public static final RegistryObject<Block> WEATHERED_COPPER_BARS = MWBlocks.registerBlock("weathered_copper_bars",
            () -> new WeatheringCopperBarsBlock(WeatheringCopper.WeatherState.WEATHERED, false));
    public static final RegistryObject<Block> EXPOSED_COPPER_BARS = MWBlocks.registerBlock("exposed_copper_bars",
            () -> new WeatheringCopperBarsBlock(WeatheringCopper.WeatherState.EXPOSED, false));
    public static final RegistryObject<Block> COPPER_BARS = MWBlocks.registerBlock("copper_bars",
            () -> new WeatheringCopperBarsBlock(WeatheringCopper.WeatherState.UNAFFECTED, false));
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_BARS = MWBlocks.registerBlock("waxed_oxidized_copper_bars",
            () -> new WeatheringCopperBarsBlock(WeatheringCopper.WeatherState.OXIDIZED, true));
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_BARS = MWBlocks.registerBlock("waxed_weathered_copper_bars",
            () -> new WeatheringCopperBarsBlock(WeatheringCopper.WeatherState.WEATHERED, true));
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_BARS = MWBlocks.registerBlock("waxed_exposed_copper_bars",
            () -> new WeatheringCopperBarsBlock(WeatheringCopper.WeatherState.EXPOSED, true));
    public static final RegistryObject<Block> WAXED_COPPER_BARS = MWBlocks.registerBlock("waxed_copper_bars",
            () -> new WeatheringCopperBarsBlock(WeatheringCopper.WeatherState.UNAFFECTED, true));
    public static final RegistryObject<Block> OXIDIZED_COPPER_CAGE = MWBlocks.registerBlock("oxidized_copper_cage",
            () -> new WeatheringCopperCageBlock(WeatheringCopper.WeatherState.OXIDIZED, false));
    public static final RegistryObject<Block> WEATHERED_COPPER_CAGE = MWBlocks.registerBlock("weathered_copper_cage",
            () -> new WeatheringCopperCageBlock(WeatheringCopper.WeatherState.WEATHERED, false));
    public static final RegistryObject<Block> EXPOSED_COPPER_CAGE = MWBlocks.registerBlock("exposed_copper_cage",
            () -> new WeatheringCopperCageBlock(WeatheringCopper.WeatherState.EXPOSED, false));
    public static final RegistryObject<Block> COPPER_CAGE = MWBlocks.registerBlock("copper_cage",
            () -> new WeatheringCopperCageBlock(WeatheringCopper.WeatherState.UNAFFECTED, false));
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_CAGE = MWBlocks.registerBlock("waxed_oxidized_copper_cage",
            () -> new WeatheringCopperCageBlock(WeatheringCopper.WeatherState.OXIDIZED, true));
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_CAGE = MWBlocks.registerBlock("waxed_weathered_copper_cage",
            () -> new WeatheringCopperCageBlock(WeatheringCopper.WeatherState.WEATHERED, true));
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_CAGE = MWBlocks.registerBlock("waxed_exposed_copper_cage",
            () -> new WeatheringCopperCageBlock(WeatheringCopper.WeatherState.EXPOSED, true));
    public static final RegistryObject<Block> WAXED_COPPER_CAGE = MWBlocks.registerBlock("waxed_copper_cage",
            () -> new WeatheringCopperCageBlock(WeatheringCopper.WeatherState.UNAFFECTED, true));
    public static final RegistryObject<Block> COPPER_DOOR = MWBlocks.registerDoor("copper_door", true, MWBlockSetTypes.COPPER);

    //#endregion

    /**
     * Register the {@link MineWorld MineWorld} {@link Block blocks}
     *
     * @param eventBus {@link IEventBus Event Bus}
     */
    public static void register(final IEventBus eventBus) { }
}
