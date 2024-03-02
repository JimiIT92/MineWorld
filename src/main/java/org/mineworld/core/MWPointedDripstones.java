package org.mineworld.core;

import com.google.common.base.Suppliers;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.block.IcePointedDripstoneBlock;
import org.mineworld.block.MWPointedDripstoneBlock;

import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link PointedDripstoneBlock Pointed Dripstone Blocks}
 */
public final class MWPointedDripstones {

    //#region Pointed Dripstones

    public static final RegistryObject<Block> STONE_POINTED_DRIPSTONE = registerPointedDripstone("stone", () -> Blocks.STONE);
    public static final RegistryObject<Block> SANDSTONE_POINTED_DRIPSTONE = registerPointedDripstone("sandstone", () -> Blocks.SANDSTONE);
    public static final RegistryObject<Block> RED_SANDSTONE_POINTED_DRIPSTONE = registerPointedDripstone("red_sandstone", () -> Blocks.RED_SANDSTONE);
    public static final RegistryObject<Block> ICE_POINTED_DRIPSTONE = registerIcePointedDripstone("ice", () -> Blocks.ICE);
    public static final RegistryObject<Block> PACKED_ICE_POINTED_DRIPSTONE = registerIcePointedDripstone("packed_ice", () -> Blocks.PACKED_ICE);
    public static final RegistryObject<Block> BLUE_ICE_POINTED_DRIPSTONE = registerIcePointedDripstone("blue_ice", () -> Blocks.BLUE_ICE);
    public static final RegistryObject<Block> DEEPSLATE_POINTED_DRIPSTONE = registerPointedDripstone("deepslate", () -> Blocks.DEEPSLATE);
    public static final RegistryObject<Block> GRANITE_POINTED_DRIPSTONE = registerPointedDripstone("granite", () -> Blocks.GRANITE);
    public static final RegistryObject<Block> DIORITE_POINTED_DRIPSTONE = registerPointedDripstone("diorite", () -> Blocks.DIORITE);
    public static final RegistryObject<Block> ANDESITE_POINTED_DRIPSTONE = registerPointedDripstone("andesite", () -> Blocks.ANDESITE);
    public static final RegistryObject<Block> CALCITE_POINTED_DRIPSTONE = registerPointedDripstone("calcite", () -> Blocks.CALCITE);
    public static final RegistryObject<Block> TUFF_POINTED_DRIPSTONE = registerPointedDripstone("tuff", () -> Blocks.TUFF);
    public static final RegistryObject<Block> PRISMARINE_POINTED_DRIPSTONE = registerPointedDripstone("prismarine", () -> Blocks.PRISMARINE);
    public static final RegistryObject<Block> NETHERRACK_POINTED_DRIPSTONE = registerPointedDripstone("netherrack", () -> Blocks.NETHERRACK);
    public static final RegistryObject<Block> CRIMSON_NYLIUM_POINTED_DRIPSTONE = registerPointedDripstone("crimson_nylium", () -> Blocks.CRIMSON_NYLIUM);
    public static final RegistryObject<Block> WARPED_NYLIUM_POINTED_DRIPSTONE = registerPointedDripstone("warped_nylium", () -> Blocks.WARPED_NYLIUM);
    public static final RegistryObject<Block> SOUL_SOIL_POINTED_DRIPSTONE = registerPointedDripstone("soul_soil", () -> Blocks.SOUL_SOIL);
    public static final RegistryObject<Block> BLACKSTONE_POINTED_DRIPSTONE = registerPointedDripstone("blackstone", () -> Blocks.BLACKSTONE);
    public static final RegistryObject<Block> BASALT_POINTED_DRIPSTONE = registerPointedDripstone("basalt", () -> Blocks.BASALT);
    public static final RegistryObject<Block> END_STONE_POINTED_DRIPSTONE = registerPointedDripstone("end_stone", () -> Blocks.END_STONE);
    public static final RegistryObject<Block> PERENNIAL_ICE_POINTED_DRIPSTONE = registerIcePointedDripstone("perennial_ice", Suppliers.memoize(() -> MWBlocks.PERENNIAL_ICE.get()));
    public static final RegistryObject<Block> MARBLE_POINTED_DRIPSTONE = registerPointedDripstone("marble", Suppliers.memoize(() -> MWBlocks.MARBLE.get()));

    //#endregion

    //#region Methods

    /**
     * Register a {@link MWPointedDripstoneBlock Pointed Dripstone Block}
     *
     * @param materialName {@link String The Pointed Dripstone material name}
     * @param blockSupplier {@link Supplier The Pointed Dripstone source Block Supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerPointedDripstone(final String materialName, final Supplier<Block> blockSupplier, final FeatureFlag... featureFlags) {
        return registerDripstone(materialName, Suppliers.memoize(() -> new MWPointedDripstoneBlock(blockSupplier, featureFlags)));
    }

    /**
     * Register an {@link IcePointedDripstoneBlock Ice Pointed Dripstone Block}
     *
     * @param materialName {@link String The Pointed Dripstone material name}
     * @param blockSupplier {@link Supplier The Pointed Dripstone source Block Supplier}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Block to work}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerIcePointedDripstone(final String materialName, final Supplier<Block> blockSupplier, final FeatureFlag... featureFlags) {
        return registerDripstone(materialName, Suppliers.memoize(() -> new IcePointedDripstoneBlock(blockSupplier, featureFlags)));
    }

    /**
     * Register a {@link MWPointedDripstoneBlock Dripstone Block}
     *
     * @param materialName {@link String The Pointed Dripstone material name}
     * @param blockSupplier {@link Supplier The Pointed Dripstone source Block Supplier}
     * @return {@link RegistryObject<Block> The registered Block}
     */
    private static RegistryObject<Block> registerDripstone(final String materialName, final Supplier<Block> blockSupplier) {
        return MWBlocks.registerBlock(materialName + "_pointed_dripstone", blockSupplier);
    }

    //#endregion

    //#region Bus register

    /**
     * Register all {@link PointedDripstoneBlock Pointed Dripstone Blocks}
     */
    public static void register() { }

    //#endregion

}