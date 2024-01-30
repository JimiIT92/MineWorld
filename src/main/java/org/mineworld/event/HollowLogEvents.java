package org.mineworld.event;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.block.HollowBlock;
import org.mineworld.block.MWLogBlock;
import org.mineworld.helper.ItemHelper;
import org.mineworld.helper.LevelHelper;

/**
 * Handle all events for the {@link HollowBlock Hollow Logs}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID)
public final class HollowLogEvents {

    /**
     * Hollow a {@link MWLogBlock Log} when the {@link Player Player} right clicks it with an {@link AxeItem Axe} while sneaking
     *
     * @param event {@link PlayerInteractEvent.RightClickBlock The Player Right Click Block Event}
     */
    @SubscribeEvent
    public static void onRightClickBlock(final PlayerInteractEvent.RightClickBlock event) {
        if(!event.isCanceled()) {
            final Player player = event.getEntity();
            final BlockPos clickedPos = event.getPos();
            final ItemStack itemStack = event.getItemStack();
            final Level level = event.getLevel();
            if(player.isShiftKeyDown() && itemStack.getItem() instanceof AxeItem) {
                HollowBlock.getHollow(level.getBlockState(clickedPos)).ifPresent(hollowState -> {
                    level.setBlockAndUpdate(clickedPos, hollowState.setValue(HollowBlock.WATERLOGGED, LevelHelper.isUnderwater(level, clickedPos)));
                    ItemHelper.hurt(itemStack, player);
                    if(player instanceof ServerPlayer) {
                        CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, clickedPos, itemStack);
                    }
                    event.setUseItem(Event.Result.DENY);
                    if(level.isClientSide()) {
                        player.swing(event.getHand());
                        player.playSound(SoundEvents.AXE_STRIP);
                    }
                });
            }
        }
    }

}