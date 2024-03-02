package org.mineworld.client.event;

import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.helper.SplashScreenHelper;

/**
 * Handle all events for {@link MineWorld MineWorld} Splash Screen
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID, value = Dist.CLIENT)
public final class SplashScreenEvents {

    /**
     * Show the {@link MineWorld MineWorld} Splash texts on the main menu
     *
     * @param event {@link ScreenEvent.Opening The Screen Opening Event}
     */
    @SubscribeEvent
    public static void onScreenOpen(final ScreenEvent.Opening event) {
        if(!event.isCanceled()) {
            if(event.getScreen() instanceof TitleScreen titleScreen) {
                SplashScreenHelper.getSplashText().ifPresent(text -> titleScreen.splash = text);
            }
        }
    }

}