package org.mineworld.event;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.VersionChecker;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.helper.ComponentHelper;

/**
 * Handle all events for the {@link PlayerEvent.PlayerLoggedInEvent Player Login}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID)
public final class PlayerLoginEvents {

    /**
     * Show a message to the {@link Player Player} if a new mod version is available
     *
     * @param event {@link PlayerEvent.PlayerLoggedInEvent The Player Logged In Event}
     */
    @SubscribeEvent
    public static void onPlayerLogin(final PlayerEvent.PlayerLoggedInEvent event) {
        if(!event.isCanceled()) {
            ModList.get().getModContainerById(MineWorld.MOD_ID).ifPresent(modContainer -> {
                final VersionChecker.CheckResult versionCheckResult = VersionChecker.getResult(modContainer.getModInfo());
                if(versionCheckResult != null && versionCheckResult.status().equals(VersionChecker.Status.OUTDATED) && versionCheckResult.target() != null) {
                    final Player player = event.getEntity();
                    player.playNotifySound(SoundEvents.NOTE_BLOCK_HAT.get(), SoundSource.MASTER, 1.0F, 1.0F);
                    final Component notifyMessage = ComponentHelper.updateMessage("outdated", versionCheckResult.target());
                    final Component downloadMessage = ComponentHelper.updateMessage("download").setStyle(
                            Style.EMPTY.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, versionCheckResult.url()))
                                    .withUnderlined(true)
                                    .withColor(ChatFormatting.GOLD)
                    );
                    player.sendSystemMessage(notifyMessage);
                    player.sendSystemMessage(downloadMessage);
                }
            });
        }
    }

}