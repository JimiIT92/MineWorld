package org.mineworld.client.event;

import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CoralPlantBlock;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlocks;
import org.mineworld.core.MWFlowerPots;

import java.util.Arrays;
import java.util.function.Supplier;

/**
 * Handle all events for {@link MineWorld MineWorld} client-side coloring
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ColorHandlerEvents {

    /**
     * Register all {@link Block Block} color handlers
     *
     * @param event {@link RegisterColorHandlersEvent.Block The Block Register Color Handlers Event}
     */
    @SubscribeEvent
    public static void onRegisterBlockColorHandlers(final RegisterColorHandlersEvent.Block event) {
        registerGrassBlocks(event,
                MWBlocks.DISGUISED_GRASS_TNT,
                MWFlowerPots.POTTED_GRASS,
                MWFlowerPots.POTTED_TALL_GRASS,
                MWFlowerPots.POTTED_LARGE_FERN,
                MWBlocks.GRASS_CARPET
        );
        registerFoliageBlocks(event,
                false,
                false,
                false,
                MWFlowerPots.POTTED_OAK_LEAVES,
                MWFlowerPots.POTTED_JUNGLE_LEAVES,
                MWFlowerPots.POTTED_ACACIA_LEAVES,
                MWFlowerPots.POTTED_DARK_OAK_LEAVES,
                MWBlocks.OAK_LEAVES_CARPET,
                MWBlocks.OAK_BUSH,
                MWFlowerPots.POTTED_OAK_BUSH,
                MWBlocks.JUNGLE_LEAVES_CARPET,
                MWBlocks.JUNGLE_BUSH,
                MWFlowerPots.POTTED_JUNGLE_BUSH,
                MWBlocks.ACACIA_LEAVES_CARPET,
                MWBlocks.ACACIA_BUSH,
                MWFlowerPots.POTTED_ACACIA_BUSH,
                MWBlocks.DARK_OAK_LEAVES_CARPET,
                MWBlocks.DARK_OAK_BUSH,
                MWFlowerPots.POTTED_DARK_OAK_BUSH,
                MWBlocks.APPLE_LEAVES,
                MWFlowerPots.POTTED_APPLE_LEAVES,
                MWBlocks.APPLE_LEAVES_CARPET,
                MWBlocks.APPLE_BUSH,
                MWFlowerPots.POTTED_APPLE_BUSH
        );
        registerFoliageBlocks(event,
                true,
                false,
                false,
                MWFlowerPots.POTTED_SPRUCE_LEAVES,
                MWBlocks.SPRUCE_LEAVES_CARPET,
                MWBlocks.SPRUCE_BUSH,
                MWFlowerPots.POTTED_SPRUCE_BUSH
        );
        registerFoliageBlocks(event,
                false,
                true,
                false,
                MWFlowerPots.POTTED_BIRCH_LEAVES,
                MWBlocks.BIRCH_LEAVES_CARPET,
                MWBlocks.BIRCH_BUSH,
                MWFlowerPots.POTTED_BIRCH_BUSH
        );
        registerFoliageBlocks(event,
                false,
                false,
                true,
                MWFlowerPots.POTTED_MANGROVE_LEAVES,
                MWBlocks.MANGROVE_LEAVES_CARPET,
                MWBlocks.MANGROVE_BUSH,
                MWFlowerPots.POTTED_MANGROVE_BUSH
        );
        registerCoralBlocks(event,
                MWFlowerPots.POTTED_TUBE_CORAL,
                MWFlowerPots.POTTED_BRAIN_CORAL,
                MWFlowerPots.POTTED_BUBBLE_CORAL,
                MWFlowerPots.POTTED_FIRE_CORAL,
                MWFlowerPots.POTTED_HORN_CORAL,
                MWFlowerPots.POTTED_TUBE_CORAL_FAN,
                MWFlowerPots.POTTED_BRAIN_CORAL_FAN,
                MWFlowerPots.POTTED_BUBBLE_CORAL_FAN,
                MWFlowerPots.POTTED_FIRE_CORAL_FAN,
                MWFlowerPots.POTTED_HORN_CORAL_FAN
        );
    }

    /**
     * Register all {@link Item Item} color handlers
     *
     * @param event {@link RegisterColorHandlersEvent.Item The Item Register Color Handlers Event}
     */
    @SubscribeEvent
    public static void onRegisterItemColorHandlers(final RegisterColorHandlersEvent.Item event) {
        registerGrassItems(event,
                MWBlocks.DISGUISED_GRASS_TNT,
                MWBlocks.GRASS_CARPET
        );
        registerFoliageItems(event,
                false,
                false,
                false,
                MWBlocks.OAK_LEAVES_CARPET,
                MWBlocks.OAK_BUSH,
                MWBlocks.JUNGLE_LEAVES_CARPET,
                MWBlocks.JUNGLE_BUSH,
                MWBlocks.ACACIA_LEAVES_CARPET,
                MWBlocks.ACACIA_BUSH,
                MWBlocks.DARK_OAK_LEAVES_CARPET,
                MWBlocks.DARK_OAK_BUSH,
                MWBlocks.APPLE_LEAVES,
                MWBlocks.APPLE_LEAVES_CARPET,
                MWBlocks.APPLE_BUSH
        );
        registerFoliageItems(event,
                true,
                false,
                false,
                MWBlocks.SPRUCE_LEAVES_CARPET,
                MWBlocks.SPRUCE_BUSH
        );
        registerFoliageItems(event,
                false,
                true,
                false,
                MWBlocks.BIRCH_LEAVES_CARPET,
                MWBlocks.BIRCH_BUSH
        );
        registerFoliageItems(event,
                false,
                false,
                true,
                MWBlocks.MANGROVE_LEAVES_CARPET,
                MWBlocks.MANGROVE_BUSH
        );
    }

    /**
     * Register the {@link Block Block} coloring for some {@link GrassBlock Grass} Blocks
     *
     * @param event {@link RegisterColorHandlersEvent.Block The Block Register Color Handlers Event}
     * @param blockSuppliers {@link Supplier<Block> The Supplier for the Blocks to register}
     */
    @SafeVarargs
    private static void registerGrassBlocks(final RegisterColorHandlersEvent.Block event, final Supplier<Block>... blockSuppliers) {
        event.register((blockState, tintGetter, blockPos, tintIndex) -> tintGetter != null && blockPos != null ? BiomeColors.getAverageGrassColor(tintGetter, blockPos) : GrassColor.getDefaultColor(),
                Arrays.stream(blockSuppliers).map(blockSupplier -> blockSupplier.get()).toArray(size -> new Block[size]));
    }

    /**
     * Register the {@link Block Block} coloring for some {@link LeavesBlock Foliage} Blocks
     *
     * @param event {@link RegisterColorHandlersEvent.Block The Block Register Color Handlers Event}
     * @param isEvergreen {@link Boolean If the Foliage is an evergreen Foliage}
     * @param isBirch {@link Boolean If the Foliage is a Birch Foliage}
     * @param isMangrove {@link Boolean If the Foliage is a Mangrove Foliage}
     * @param blockSuppliers {@link Supplier<Block> The Supplier for the Blocks to register}
     */
    @SafeVarargs
    private static void registerFoliageBlocks(final RegisterColorHandlersEvent.Block event, final boolean isEvergreen, final boolean isBirch, final boolean isMangrove, final Supplier<Block>... blockSuppliers) {
        event.register((blockState, tintGetter, blockPos, tintIndex) -> isEvergreen ?
                        FoliageColor.getEvergreenColor() : isBirch ?
                        FoliageColor.getBirchColor() : isMangrove ?
                        FoliageColor.getMangroveColor() :
                        tintGetter != null && blockPos != null ? BiomeColors.getAverageFoliageColor(tintGetter, blockPos) : GrassColor.getDefaultColor(),
                Arrays.stream(blockSuppliers).map(blockSupplier -> blockSupplier.get()).toArray(size -> new Block[size]));
    }

    /**
     * Register the {@link Block Block} coloring for some {@link CoralPlantBlock Coral} Blocks
     *
     * @param event {@link RegisterColorHandlersEvent.Block The Block Register Color Handlers Event}
     * @param blockSuppliers {@link Supplier<Block> The Supplier for the Blocks to register}
     */
    @SafeVarargs
    private static void registerCoralBlocks(final RegisterColorHandlersEvent.Block event, final Supplier<Block>... blockSuppliers) {
        event.register((blockState, tintGetter, blockPos, tintIndex) -> tintGetter != null && blockPos != null ? BiomeColors.getAverageWaterColor(tintGetter, blockPos) : -1,
                Arrays.stream(blockSuppliers).map(blockSupplier -> blockSupplier.get()).toArray(size -> new Block[size]));
    }

    /**
     * Register the {@link Block Block} coloring for some {@link GrassBlock Grass} Items
     *
     * @param event {@link RegisterColorHandlersEvent.Item The Item Register Color Handlers Event}
     * @param blockSuppliers {@link Supplier<Block> The Supplier for the Blocks to register}
     */
    @SafeVarargs
    private static void registerGrassItems(final RegisterColorHandlersEvent.Item event, final Supplier<Block>... blockSuppliers) {
        event.register((itemStack, tintIndex) -> event.getBlockColors().getColor(((BlockItem)itemStack.getItem()).getBlock().defaultBlockState(), null, null, tintIndex),
                Arrays.stream(blockSuppliers).map(blockSupplier -> blockSupplier.get()).toArray(size -> new Block[size]));
    }

    /**
     * Register the {@link Block Block} coloring for some {@link LeavesBlock Foliage} Items
     *
     * @param event {@link RegisterColorHandlersEvent.Item The Item Register Color Handlers Event}
     * @param blockSuppliers {@link Supplier<Block> The Supplier for the Blocks to register}
     */
    @SafeVarargs
    private static void registerFoliageItems(final RegisterColorHandlersEvent.Item event, final boolean isEvergreen, final boolean isBirch, final boolean isMangrove,  final Supplier<Block>... blockSuppliers) {
        event.register((itemStack, tintIndex) -> isEvergreen ? FoliageColor.getEvergreenColor() : isBirch ? FoliageColor.getBirchColor() : isMangrove ? FoliageColor.getMangroveColor() : FoliageColor.getDefaultColor(),
                Arrays.stream(blockSuppliers).map(blockSupplier -> blockSupplier.get()).toArray(size -> new Block[size]));
    }

}