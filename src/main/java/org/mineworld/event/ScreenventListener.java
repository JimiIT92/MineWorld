package org.mineworld.event;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.helper.SplashScreenHelper;

/**
 * Listener for a {@link ScreenEvent screen event}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID, value = Dist.CLIENT)
public final class ScreenventListener {

    /**
     * Show custom splash screen on main screen opening
     *
     * @param event {@link ScreenEvent.Opening The screen opening event}
     */
    @SubscribeEvent
    public static void onScreenOpen(final ScreenEvent.Opening event) {
        final Screen screen = event.getScreen();
        if(screen instanceof TitleScreen titleScreen) {
            SplashScreenHelper.getSplashText().ifPresent(text -> titleScreen.splash = text);
        }
    }
}