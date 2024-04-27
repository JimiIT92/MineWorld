package org.mineworld.client.event;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Pose;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.core.MWKeyMappings;
import org.mineworld.helper.PlayerHelper;

/**
 * Handle all events for {@link MineWorld MineWorld} {@link KeyMapping KeyMappings}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID, value = Dist.CLIENT)
public final class KeyMappingEvents {

    /**
     * Register all {@link KeyMapping Key Mappings}
     *
     * @param event {@link RegisterKeyMappingsEvent The Key Mappings register event}
     */
    @SubscribeEvent
    public static void onRegisterKeyMappings(final RegisterKeyMappingsEvent event) {
        event.register(MWKeyMappings.CRAWL.get());
    }

    /**
     * Handle the {@link KeyMapping Key Mappings}
     *
     * @param event {@link TickEvent.ClientTickEvent The Client Tick event}
     */
    @SubscribeEvent
    public static void onClientTick(final TickEvent.ClientTickEvent event) {
        if(!event.isCanceled() && event.phase == TickEvent.Phase.END) {
            final LocalPlayer player = Minecraft.getInstance().player;
            while(MWKeyMappings.CRAWL.get().consumeClick()) {
                PlayerHelper.togglePose(player, Pose.SWIMMING);
            }
        }
    }

}