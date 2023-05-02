package org.mineworld.client.event;

import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlocks;

/**
 * Handles the {@link Block block coloring} on the client side
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ColorHandlerListener {

    /**
     * Register block coloring for the client side
     *
     * @param event {@link RegisterColorHandlersEvent.Block Block register color handlers event}
     */
    @SubscribeEvent
    public static void onRegisterBlockColorHandlers(final RegisterColorHandlersEvent.Block event) {
        event.register((blockState, tintGetter, blockPos, tintIndex) ->
                        tintGetter != null && blockPos != null ?
                                BiomeColors.getAverageGrassColor(tintGetter, blockPos) :
                                GrassColor.getDefaultColor(),
                MWBlocks.DISGUISED_GRASS_TNT.get(),
                MWBlocks.POTTED_GRASS.get(),
                MWBlocks.POTTED_TALL_GRASS.get(),
                MWBlocks.POTTED_LARGE_FERN.get(),
                MWBlocks.GRASS_CARPET.get()
        );
        event.register((blockState, tintGetter, blockPos, tintIndex) ->
                        tintGetter != null && blockPos != null ? BiomeColors.getAverageWaterColor(tintGetter, blockPos) : -1,
                MWBlocks.POTTED_TUBE_CORAL.get(),
                MWBlocks.POTTED_BRAIN_CORAL.get(),
                MWBlocks.POTTED_BUBBLE_CORAL.get(),
                MWBlocks.POTTED_FIRE_CORAL.get(),
                MWBlocks.POTTED_HORN_CORAL.get(),
                MWBlocks.POTTED_TUBE_CORAL_FAN.get(),
                MWBlocks.POTTED_BRAIN_CORAL_FAN.get(),
                MWBlocks.POTTED_BUBBLE_CORAL_FAN.get(),
                MWBlocks.POTTED_FIRE_CORAL_FAN.get(),
                MWBlocks.POTTED_HORN_CORAL_FAN.get()
        );
        event.register((blockState, tintGetter, blockPos, tintIndex) ->
                        tintGetter != null && blockPos != null ?
                                BiomeColors.getAverageFoliageColor(tintGetter, blockPos) :
                                GrassColor.getDefaultColor(),
                MWBlocks.POTTED_OAK_LEAVES.get(),
                MWBlocks.POTTED_JUNGLE_LEAVES.get(),
                MWBlocks.POTTED_ACACIA_LEAVES.get(),
                MWBlocks.POTTED_DARK_OAK_LEAVES.get(),
                MWBlocks.POTTED_MANGROVE_LEAVES.get(),
                MWBlocks.OAK_LEAVES_CARPET.get(),
                MWBlocks.OAK_BUSH.get(),
                MWBlocks.POTTED_OAK_BUSH.get(),
                MWBlocks.JUNGLE_LEAVES_CARPET.get(),
                MWBlocks.JUNGLE_BUSH.get(),
                MWBlocks.POTTED_JUNGLE_BUSH.get(),
                MWBlocks.ACACIA_LEAVES_CARPET.get(),
                MWBlocks.ACACIA_BUSH.get(),
                MWBlocks.POTTED_ACACIA_BUSH.get(),
                MWBlocks.DARK_OAK_LEAVES_CARPET.get(),
                MWBlocks.DARK_OAK_BUSH.get(),
                MWBlocks.POTTED_DARK_OAK_BUSH.get(),
                MWBlocks.MANGROVE_LEAVES_CARPET.get(),
                MWBlocks.MANGROVE_BUSH.get(),
                MWBlocks.POTTED_MANGROVE_BUSH.get()
        );
        event.register((blockState, tintGetter, blockPos, tintIndex) ->
                        FoliageColor.getEvergreenColor(),
                MWBlocks.POTTED_SPRUCE_LEAVES.get(),
                MWBlocks.SPRUCE_LEAVES_CARPET.get(),
                MWBlocks.SPRUCE_BUSH.get(),
                MWBlocks.POTTED_SPRUCE_BUSH.get()
        );
        event.register((blockState, tintGetter, blockPos, tintIndex) ->
                        FoliageColor.getBirchColor(),
                MWBlocks.POTTED_BIRCH_LEAVES.get(),
                MWBlocks.BIRCH_LEAVES_CARPET.get(),
                MWBlocks.BIRCH_BUSH.get(),
                MWBlocks.POTTED_BIRCH_BUSH.get()
        );
    }

    /**
     * Register id coloring for the client side
     *
     * @param event {@link RegisterColorHandlersEvent.Item Item register color handlers event}
     */
    @SubscribeEvent
    public static void onRegisterItemColorHandlers(final RegisterColorHandlersEvent.Item event) {
        event.register((itemStack, tintIndex) -> event.getBlockColors().getColor(((BlockItem)itemStack.getItem()).getBlock().defaultBlockState(), null, null, tintIndex),
                MWBlocks.DISGUISED_GRASS_TNT.get(),
                MWBlocks.GRASS_CARPET.get()
        );
        event.register((itemStack, tintIndex) -> FoliageColor.getDefaultColor(),
                MWBlocks.OAK_LEAVES_CARPET.get(),
                MWBlocks.OAK_BUSH.get(),
                MWBlocks.JUNGLE_LEAVES_CARPET.get(),
                MWBlocks.JUNGLE_BUSH.get(),
                MWBlocks.ACACIA_LEAVES_CARPET.get(),
                MWBlocks.ACACIA_BUSH.get(),
                MWBlocks.DARK_OAK_LEAVES_CARPET.get(),
                MWBlocks.DARK_OAK_BUSH.get(),
                MWBlocks.MANGROVE_LEAVES_CARPET.get(),
                MWBlocks.MANGROVE_BUSH.get());
        event.register((itemStack, tintIndex) -> FoliageColor.getEvergreenColor(),
                MWBlocks.SPRUCE_LEAVES_CARPET.get(),
                MWBlocks.SPRUCE_BUSH.get());
        event.register((itemStack, tintIndex) -> FoliageColor.getBirchColor(),
                MWBlocks.BIRCH_LEAVES_CARPET.get(),
                MWBlocks.BIRCH_BUSH.get());
    }

}