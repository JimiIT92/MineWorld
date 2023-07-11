package org.mineworld.helper;

import net.minecraft.client.Minecraft;
import org.mineworld.MineWorld;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

/**
 * Helper methods for splash screens
 */
public class SplashScreenHelper {

    /**
     * The {@link MineWorld MineWorld} {@link Set<String> splash texts}
     */
    private static Set<String> splashTexts;
    /**
     * {@link String The special splash text} shown for {@link MineWorld MineWorld} birthday on November 30
     */
    private static String birthdaySplashText;

    /**
     * Get a {@link String random splash text}
     *
     * @return {@link String A random splash text}
     */
    public static Optional<String> getSplashText() {
        if(isMineWorldBirhday()) {
            return Optional.of(birthdaySplashText);
        }
        return !RandomHelper.choose() || isVanillaSplashTextSpecialDate() ? Optional.of(Minecraft.getInstance().getSplashManager().getSplash()) : RandomHelper.randomValue(splashTexts);
    }

    /**
     * Check if the local date correspond to a special vanilla splash text date
     *
     * @return {@link Boolean True if is halloween, christmas or new year's eve}
     */
    private static boolean isVanillaSplashTextSpecialDate() {
        Calendar calendar = getCalendar();
        return (calendar.get(Calendar.MONTH) + 1 == 12 && calendar.get(Calendar.DATE) == 24) ||
                (calendar.get(Calendar.MONTH) + 1 == 1 && calendar.get(Calendar.DATE) == 1) ||
                (calendar.get(Calendar.MONTH) + 1 == 10 && calendar.get(Calendar.DATE) == 31);
    }

    /**
     * Check if is the {@link MineWorld MineWorld} birthday
     *
     * @return {@link Boolean True if is November 30}
     */
    private static boolean isMineWorldBirhday() {
        Calendar calendar = getCalendar();
        return calendar.get(Calendar.MONTH) + 1 == 11 && calendar.get(Calendar.DATE) == 30;
    }

    /**
     * Get the {@link Calendar calendar instance}
     *
     * @return {@link Calendar The calendar instance}
     */
    private static Calendar getCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar;
    }

    /**
     * Load the {@link MineWorld MineWorld} {@link Set<String> splash texts}
     *
     * @return {@link Set<String> The MineWorld splash texts}
     */
    private static Set<String> loadSplashTexts() {
        return Set.of(
                getSplashText("blocks"),
                getSplashText("logs"),
                getSplashText("christmas"),
                getSplashText("blazersmod"),
                getSplashText("bundles"),
                getSplashText("better_snowier_snow"),
                getSplashText("herobrine"),
                getSplashText("chooped_trees"),
                getSplashText("made_in_italy")
        );
    }

    /**
     * Get a {@link String translated splash text}
     *
     * @param key {@link String The splash text key}
     * @param args {@link Object The translation arguments}
     * @return {@link String The translated splash text}
     */
    private static String getSplashText(String key, Object... args) {
        return ComponentHelper.splashText(key, args).getString();
    }

    static {
        splashTexts = loadSplashTexts();
        birthdaySplashText = getSplashText("birthday",LocalDate.now().getYear() - 2013);
    }
}