package org.mineworld.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.decoration.LeashFenceKnotEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.LeadItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.helper.PlayerHelper;

/**
 * Handle the {@link Player player} right clickcing a {@link Block block}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MODID)
public final class RightClickBlockEventListener {

    /**
     * Attach a {@link LeadItem lead} to a {@link FenceBlock fence} when the {@link Player player} right clicks on it
     * and it doesn't have a leashed entity
     *
     * @param event {@link PlayerInteractEvent.RightClickBlock Player right click block event}
     */
    @SubscribeEvent
    public static void onRightClickBlock(final PlayerInteractEvent.RightClickBlock event) {
        final Player player = event.getEntity();
        final Level level = event.getLevel();
        final BlockPos clickedPos = event.getPos();
        if(event.getItemStack().is(Items.LEAD) && PlayerHelper.getLeashedEntities(player, level, clickedPos).isEmpty()) {
            event.setCanceled(true);
            LeashFenceKnotEntity leashfenceknotentity = LeashFenceKnotEntity.getOrCreateKnot(level, clickedPos);
            leashfenceknotentity.playPlacementSound();
            level.gameEvent(GameEvent.BLOCK_ATTACH, clickedPos, GameEvent.Context.of(player));
        }
    }

}