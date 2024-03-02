package org.mineworld.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.block.weathering.IMWWaxableBlock;

/**
 * Handle all events for the {@link HoneycombItem Honeycomb}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID)
public final class HoneycombEvents {

    /**
     * Wax a {@link Block Block}
     *
     * @param event {@link PlayerInteractEvent.RightClickBlock The Player Right Click Block Event}
     */
    @SubscribeEvent
    public static void onRightClickBlock(final PlayerInteractEvent.RightClickBlock event) {
        final ItemStack itemStack = event.getItemStack();
        if(!event.isCanceled() && itemStack.getItem() instanceof HoneycombItem honeyComb) {
            final Level level = event.getLevel();
            final BlockPos blockPos = event.getPos();
            final BlockState blockState = level.getBlockState(blockPos);
            if(blockState.getBlock() instanceof IMWWaxableBlock) {
                event.setCancellationResult(IMWWaxableBlock.applyWax(blockState, itemStack, event.getEntity(), blockPos, event.getHand(), level));
            }
        }
    }

}