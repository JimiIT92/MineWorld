package org.mineworld.event;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.block.HollowBlock;
import org.mineworld.block.WallHangingLanternBlock;
import org.mineworld.core.MWTags;
import org.mineworld.helper.ItemHelper;
import org.mineworld.helper.LevelHelper;

import java.util.Objects;

/**
 * Handle all events for a {@link LanternBlock Lantern}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID)
public final class LanternEvents {

    /**
     * Place a {@link WallHangingLanternBlock Wall Hanging Lantern Block} when right clicking on a {@link Block Block}
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
            if(itemStack.is(Items.LANTERN) || itemStack.is(Items.SOUL_LANTERN) || itemStack.is(MWTags.Items.LANTERNS)) {
                final Direction direction = event.getFace();
                if(Objects.requireNonNull(direction).getAxis().isHorizontal()) {
                    LevelHelper.getReplacingBlockPos(level, clickedPos, direction).ifPresent(lanternPos -> WallHangingLanternBlock.getWallHangingLantern(itemStack, direction).ifPresent(hangingLantern -> {
                        final BlockState blockState = hangingLantern.setValue(HollowBlock.WATERLOGGED, LevelHelper.isUnderwater(level, lanternPos));
                        level.setBlockAndUpdate(lanternPos, blockState);
                        ItemHelper.hurt(itemStack, player, level, event.getHand());
                        if(player instanceof ServerPlayer) {
                            CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, lanternPos, itemStack);
                        }
                        event.setUseItem(Event.Result.DENY);
                        player.playSound(SoundEvents.LANTERN_PLACE);
                    }));
                }
            }
        }
    }

}