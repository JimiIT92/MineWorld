package org.mineworld.client.event;

import net.minecraft.client.gui.screens.inventory.BeaconScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.client.screen.MWBeaconScreen;

/**
 * Handle all events for {@link MineWorld MineWorld} {@link BeaconScreen Beacon Screen}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID, value = Dist.CLIENT)
public final class BeaconScreenEvents {

    /**
     * Replace the vanilla {@link BeaconScreen Beacon Screen} with the {@link MWBeaconScreen MineWorld Beacon Screen}
     *
     * @param event {@link ScreenEvent.Opening The Screen Opening Event}
     */
    @SubscribeEvent
    public static void onScreenOpen(final ScreenEvent.Opening event) {
        if(!event.isCanceled()) {
            if(event.getScreen() instanceof BeaconScreen beaconScreen) {
                event.setNewScreen(new MWBeaconScreen(beaconScreen));
            }
        }
    }

}