package org.mineworld.client.listener;

import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlocks;
import org.mineworld.core.MWFlowerPots;

/**
 * Handles the {@link Block block coloring} on the client side
 */
@Mod.EventBusSubscriber(modid = MineWorld.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ColorHandlerListener {

    /**
     * Register block coloring for the client side
     *
     * @param event {@link RegisterColorHandlersEvent.Block Block register color handlers event}
     */
    @SubscribeEvent
    public static void onRegisterBlockColorHandlers(final RegisterColorHandlersEvent.Block event) {
        registerFlowerPotsColorHandlers(event);
        event.register((blockState, tintGetter, blockPos, tintIndex) ->
                        tintGetter != null && blockPos != null ? BiomeColors.getAverageGrassColor(tintGetter, blockPos) : GrassColor.getDefaultColor(),
                MWBlocks.DISGUISED_GRASS_TNT.get());
    }

    /**
     * Register item coloring for the client side
     *
     * @param event {@link RegisterColorHandlersEvent.Item Item register color handlers event}
     */
    @SubscribeEvent
    public static void onRegisterItemColorHandlers(final RegisterColorHandlersEvent.Item event) {
        event.register((itemStack, tintIndex) -> {
            BlockState blockstate = ((BlockItem)itemStack.getItem()).getBlock().defaultBlockState();
            return event.getBlockColors().getColor(blockstate, null, null, tintIndex);
        }, MWBlocks.DISGUISED_GRASS_TNT.get());
    }

    /**
     * Register the flower pots coloring
     *
     * @param event {@link RegisterColorHandlersEvent.Block Block register color handlers event}
     */
    private static void registerFlowerPotsColorHandlers(final RegisterColorHandlersEvent.Block event) {
        event.register((blockState, tintGetter, blockPos, tintIndex) ->
                        FoliageColor.getDefaultColor(),
                MWFlowerPots.POTTED_OAK_LEAVES.get(),
                MWFlowerPots.POTTED_JUNGLE_LEAVES.get(),
                MWFlowerPots.POTTED_ACACIA_LEAVES.get(),
                MWFlowerPots.POTTED_DARK_OAK_LEAVES.get(),
                MWFlowerPots.POTTED_MANGROVE_LEAVES.get());
        event.register((blockState, tintGetter, blockPos, tintIndex) ->
                        FoliageColor.getEvergreenColor(),
                MWFlowerPots.POTTED_SPRUCE_LEAVES.get());
        event.register((blockState, tintGetter, blockPos, tintIndex) ->
                        FoliageColor.getBirchColor(),
                MWFlowerPots.POTTED_BIRCH_LEAVES.get());
        event.register((blockState, tintGetter, blockPos, tintIndex) ->
                        GrassColor.getDefaultColor(),
                MWFlowerPots.POTTED_GRASS.get(),
                MWFlowerPots.POTTED_TALL_GRASS.get(),
                MWFlowerPots.POTTED_LARGE_FERN.get());
        event.register((blockState, tintGetter, blockPos, tintIndex) ->
                        tintGetter != null && blockPos != null ? BiomeColors.getAverageWaterColor(tintGetter, blockPos) : -1,
                MWFlowerPots.POTTED_TUBE_CORAL.get(),
                MWFlowerPots.POTTED_BRAIN_CORAL.get(),
                MWFlowerPots.POTTED_BUBBLE_CORAL.get(),
                MWFlowerPots.POTTED_FIRE_CORAL.get(),
                MWFlowerPots.POTTED_HORN_CORAL.get(),
                MWFlowerPots.POTTED_TUBE_CORAL_FAN.get(),
                MWFlowerPots.POTTED_BRAIN_CORAL_FAN.get(),
                MWFlowerPots.POTTED_BUBBLE_CORAL_FAN.get(),
                MWFlowerPots.POTTED_FIRE_CORAL_FAN.get(),
                MWFlowerPots.POTTED_HORN_CORAL_FAN.get());
    }
}
