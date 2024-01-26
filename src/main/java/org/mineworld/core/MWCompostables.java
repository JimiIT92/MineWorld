package org.mineworld.core;

import com.google.common.base.Suppliers;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ComposterBlock;
import org.mineworld.MineWorld;

import java.util.Arrays;
import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} Compostable {@link ItemLike Blocks and Items}
 */
public final class MWCompostables {

    //#region Methods

    /**
     * Register some Compostable {@link ItemLike Blocks and Items}
     *
     * @param chance {@link Float The Compostable chance}
     * @param itemSuppliers {@link Supplier <Block> The Supplier for the Compostable Blocks}
     */
    @SafeVarargs
    private static void registerCompostables(final float chance, final Supplier<ItemLike>... itemSuppliers) {
        Arrays.stream(itemSuppliers).forEach(itemSupplier -> registerCompostable(itemSupplier.get().asItem(), chance));
    }

    /**
     * Add an {@link ItemLike Item} to the {@link ComposterBlock#COMPOSTABLES Compostable Items}
     *
     * @param item {@link ItemLike The Compostable Block or Item}
     * @param chance {@link Float The Compostable chance}
     */
    private static void registerCompostable(final ItemLike item, final float chance) {
        ComposterBlock.COMPOSTABLES.put(item, chance);
    }

    //#endregion

    //#region Register

    /**
     * Register all Compostable {@link ItemLike Blocks and Items}
     */
    public static void registerCompostables() {
        registerCompostables(0.1F,
                Suppliers.memoize(() -> MWBlocks.GRASS_CARPET.get()),
                Suppliers.memoize(() -> MWBlocks.OAK_LEAVES_CARPET.get()),
                Suppliers.memoize(() -> MWBlocks.SPRUCE_LEAVES_CARPET.get()),
                Suppliers.memoize(() -> MWBlocks.BIRCH_LEAVES_CARPET.get()),
                Suppliers.memoize(() -> MWBlocks.JUNGLE_LEAVES_CARPET.get()),
                Suppliers.memoize(() -> MWBlocks.ACACIA_LEAVES_CARPET.get()),
                Suppliers.memoize(() -> MWBlocks.DARK_OAK_LEAVES_CARPET.get()),
                Suppliers.memoize(() -> MWBlocks.MANGROVE_LEAVES_CARPET.get()),
                Suppliers.memoize(() -> MWBlocks.MANGROVE_ROOTS_CARPET.get()),
                Suppliers.memoize(() -> MWBlocks.CHERRY_LEAVES_CARPET.get()),
                Suppliers.memoize(() -> MWBlocks.APPLE_LEAVES_CARPET.get()),
                Suppliers.memoize(() -> MWBlocks.PALM_LEAVES_CARPET.get()),
                Suppliers.memoize(() -> MWBlocks.SCULK_LEAVES_CARPET.get())
        );
        registerCompostables(0.25F,
                Suppliers.memoize(() -> MWBlocks.AZALEA_LEAVES_CARPET.get())
        );
        registerCompostables(0.3F,
                Suppliers.memoize(() -> MWBlocks.FLOWERING_AZALEA_LEAVES_CARPET.get()),
                Suppliers.memoize(() -> MWBlocks.APPLE_LEAVES.get()),
                Suppliers.memoize(() -> MWBlocks.APPLE_SAPLING.get()),
                Suppliers.memoize(() -> MWBlocks.PALM_LEAVES.get()),
                Suppliers.memoize(() -> MWBlocks.PALM_SAPLING.get()),
                Suppliers.memoize(() -> MWBlocks.SCULK_LEAVES.get()),
                Suppliers.memoize(() -> MWBlocks.SCULK_SAPLING.get()),
                Suppliers.memoize(() -> MWBlocks.NETHER_WART_CARPET.get()),
                Suppliers.memoize(() -> MWBlocks.WARPED_WART_CARPET.get()),
                Suppliers.memoize(() -> MWItems.CORN_SEEDS.get()),
                Suppliers.memoize(() -> MWItems.BLUEBERRIES.get()),
                Suppliers.memoize(() -> MWItems.CHERRY.get())
        );
        registerCompostables(0.65F,
                Suppliers.memoize(() -> MWBlocks.OAK_BUSH.get()),
                Suppliers.memoize(() -> MWBlocks.SPRUCE_BUSH.get()),
                Suppliers.memoize(() -> MWBlocks.BIRCH_BUSH.get()),
                Suppliers.memoize(() -> MWBlocks.JUNGLE_BUSH.get()),
                Suppliers.memoize(() -> MWBlocks.ACACIA_BUSH.get()),
                Suppliers.memoize(() -> MWBlocks.DARK_OAK_BUSH.get()),
                Suppliers.memoize(() -> MWBlocks.MANGROVE_BUSH.get()),
                Suppliers.memoize(() -> MWBlocks.CHERRY_BUSH.get()),
                Suppliers.memoize(() -> MWBlocks.APPLE_BUSH.get()),
                Suppliers.memoize(() -> MWBlocks.PALM_BUSH.get()),
                Suppliers.memoize(() -> MWBlocks.SCULK_BUSH.get()),
                Suppliers.memoize(() -> MWBlocks.WARPED_WART.get()),
                Suppliers.memoize(() -> MWBlocks.BLUE_ROSE.get()),
                Suppliers.memoize(() -> MWBlocks.BLUE_ROSE_BUSH.get()),
                Suppliers.memoize(() -> MWBlocks.WHITE_ROSE.get()),
                Suppliers.memoize(() -> MWBlocks.WHITE_ROSE_BUSH.get()),
                Suppliers.memoize(() -> MWBlocks.CATTAIL.get())
        );
    }

    //#endregion

}