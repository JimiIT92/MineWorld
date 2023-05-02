package org.mineworld.event;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.inventory.BeaconScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.client.screen.MWBeaconScreen;
import org.mineworld.helper.SplashScreenHelper;

/**
 * Listener for a {@link ScreenEvent screen event}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID, value = Dist.CLIENT)
public final class ScreenEventListener {

    /**
     * Show custom splash screen on main screen opening or
     * the {@link MWBeaconScreen custom beacon screen} on beacon opening
     *
     * @param event {@link ScreenEvent.Opening The screen opening event}
     */
    @SubscribeEvent
    public static void onScreenOpen(final ScreenEvent.Opening event) {
        if(!event.isCanceled()) {
            final Screen screen = event.getScreen();
            if(screen instanceof TitleScreen titleScreen) {
                SplashScreenHelper.getSplashText().ifPresent(text -> titleScreen.splash = text);
                return;
            }
            if(screen instanceof BeaconScreen beaconScreen) {
                event.setNewScreen(new MWBeaconScreen(beaconScreen));
            }
        }
    }
}