package org.mineworld.core.vanillaexpansion;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlockSetTypes;
import org.mineworld.core.MWBlocks;
import org.mineworld.core.MWColors;

/**
 * {@link MineWorld MineWorld} {@link Block vanilla expansion blocks}
 */
public final class MWVanillaExpansionBlocks {

    //#region Blocks

    public static final RegistryObject<Block> CHARCOAL_BLOCK = MWBlocks.registerFuelBlock("charcoal_block", MWColors.CHARCOAL.toMaterialColor(), 800);
    public static final RegistryObject<Block> CUT_GOLD = MWBlocks.registerSimpleBlock("cut_gold", MWBlocks.copyFrom(Blocks.GOLD_BLOCK));
    public static final RegistryObject<Block> GOLDEN_STAIRS = MWBlocks.registerStair("golden_stairs", Blocks.GOLD_BLOCK);
    public static final RegistryObject<Block> CUT_GOLDEN_STAIRS = MWBlocks.registerStair("cut_golden_stairs", () -> CUT_GOLD.get().defaultBlockState());
    public static final RegistryObject<Block> GOLDEN_SLAB = MWBlocks.registerSlab("golden_slab", Blocks.GOLD_BLOCK);
    public static final RegistryObject<Block> CUT_GOLDEN_SLAB = MWBlocks.registerSlab("cut_golden_slab", () -> CUT_GOLD.get().defaultBlockState());
    public static final RegistryObject<Block> GOLDEN_DOOR = MWBlocks.registerDoor("golden_door", true, BlockSetType.GOLD);
    public static final RegistryObject<Block> GOLDEN_TRAPDOOR = MWBlocks.registerTrapdoor("golden_trapdoor", true, BlockSetType.GOLD);
    public static final RegistryObject<Block> CUT_GOLDEN_PRESSURE_PLATE = MWBlocks.registerWeightedPressurePlate("cut_golden_pressure_plate", 15, MaterialColor.GOLD, BlockSetType.GOLD);
    public static final RegistryObject<Block> GOLDEN_CHAIN = MWBlocks.registerChain("golden_chain");
    public static final RegistryObject<Block> GOLDEN_LANTERN = MWBlocks.registerLantern("golden_lantern");
    public static final RegistryObject<Block> GOLDEN_SOUL_LANTERN = MWBlocks.registerLantern("golden_soul_lantern", 10);
    public static final RegistryObject<Block> GOLD_BARS = MWBlocks.registerBars("gold_bars");
    public static final RegistryObject<Block> GOLDEN_CAGE = MWBlocks.registerSimpleTranslucentBlock("golden_cage", MWBlocks.copyFrom(Blocks.GOLD_BLOCK));
    public static final RegistryObject<Block> CUT_NETHERITE = MWBlocks.registerSimpleBlock("cut_netherite", MWBlocks.copyFrom(Blocks.NETHERITE_BLOCK));
    public static final RegistryObject<Block> NETHERITE_STAIRS = MWBlocks.registerStair("netherite_stairs", Blocks.NETHERITE_BLOCK);
    public static final RegistryObject<Block> CUT_NETHERITE_STAIRS = MWBlocks.registerStair("cut_netherite_stairs", () -> CUT_NETHERITE.get().defaultBlockState());
    public static final RegistryObject<Block> NETHERITE_SLAB = MWBlocks.registerSlab("netherite_slab", Blocks.NETHERITE_BLOCK);
    public static final RegistryObject<Block> CUT_NETHERITE_SLAB = MWBlocks.registerSlab("cut_netherite_slab", () -> CUT_NETHERITE.get().defaultBlockState());
    public static final RegistryObject<Block> NETHERITE_DOOR = MWBlocks.registerDoor("netherite_door", true, MWBlockSetTypes.NETHERITE);
    public static final RegistryObject<Block> NETHERITE_TRAPDOOR = MWBlocks.registerTrapdoor("netherite_trapdoor", true, MWBlockSetTypes.NETHERITE);
    public static final RegistryObject<Block> NETHERITE_PRESSURE_PLATE = MWBlocks.registerWeightedPressurePlate("netherite_pressure_plate", 100, MaterialColor.COLOR_BLACK, MWBlockSetTypes.NETHERITE);
    public static final RegistryObject<Block> CUT_NETHERITE_PRESSURE_PLATE = MWBlocks.registerWeightedPressurePlate("cut_netherite_pressure_plate", 100, MaterialColor.COLOR_BLACK, MWBlockSetTypes.NETHERITE);
    public static final RegistryObject<Block> NETHERITE_CHAIN = MWBlocks.registerChain("netherite_chain");
    public static final RegistryObject<Block> NETHERITE_LANTERN = MWBlocks.registerLantern("netherite_lantern");
    public static final RegistryObject<Block> NETHERITE_SOUL_LANTERN = MWBlocks.registerLantern("netherite_soul_lantern", 10);
    public static final RegistryObject<Block> NETHERITE_BARS = MWBlocks.registerBars("netherite_bars");
    public static final RegistryObject<Block> NETHERITE_CAGE = MWBlocks.registerSimpleTranslucentBlock("netherite_cage", MWBlocks.copyFrom(Blocks.NETHERITE_BLOCK));
    public static final RegistryObject<Block> CUT_IRON = MWBlocks.registerSimpleBlock("cut_iron", MWBlocks.copyFrom(Blocks.IRON_BLOCK));
    public static final RegistryObject<Block> CUT_IRON_PRESSURE_PLATE = MWBlocks.registerWeightedPressurePlate("cut_iron_pressure_plate", 15, MaterialColor.METAL, BlockSetType.IRON);
    public static final RegistryObject<Block> IRON_STAIRS = MWBlocks.registerStair("iron_stairs", Blocks.IRON_BLOCK);
    public static final RegistryObject<Block> CUT_IRON_STAIRS = MWBlocks.registerStair("cut_iron_stairs", () -> CUT_IRON.get().defaultBlockState());
    public static final RegistryObject<Block> IRON_SLAB = MWBlocks.registerSlab("iron_slab", Blocks.IRON_BLOCK);
    public static final RegistryObject<Block> CUT_IRON_SLAB = MWBlocks.registerSlab("cut_iron_slab", () -> CUT_IRON.get().defaultBlockState());
    public static final RegistryObject<Block> IRON_CAGE = MWBlocks.registerSimpleTranslucentBlock("iron_cage", MWBlocks.copyFrom(Blocks.IRON_BLOCK));

    //#endregion

    /**
     * Register the {@link MineWorld MineWorld} {@link Block blocks}
     *
     * @param eventBus {@link IEventBus Event Bus}
     */
    public static void register(final IEventBus eventBus) {
        MWVanillaExpansionCopperBlocks.register(eventBus);
    }
}
