package org.mineworld.core;

import net.minecraft.network.chat.TextColor;
import net.minecraft.world.level.material.MapColor;
import org.mineworld.MineWorld;

/**
 * {@link MineWorld MineWorld} colors
 */
public enum MWColors {

    PRISMARINE(0x49C4AF, MapColor.COLOR_CYAN),
    SCULK(0x0A2330, MapColor.COLOR_BLACK),
    RUBY(0x9F3535, MapColor.FIRE),
    SAPPHIRE(0x35559F, MapColor.COLOR_BLUE),
    RAW_SILVER(0xC5C5C5, MapColor.RAW_IRON),
    SILVER(0xC5C5C5, MapColor.COLOR_LIGHT_GRAY),
    ALUMINUM(0x666666, MapColor.COLOR_GRAY),
    RAW_ALUMINUM(0xC5C5C5, MapColor.RAW_IRON),
    RAW_BRONZE(0xC5C5C5, MapColor.TERRACOTTA_BROWN),
    BRONZE(0x755848, MapColor.COLOR_BROWN),
    PYRITE(0x847C50, MapColor.TERRACOTTA_YELLOW),
    CHARCOAL(0x755848, MapColor.COLOR_BLACK),
    MARBLE(0x9E9A86, MapColor.TERRACOTTA_GRAY),
    WHITE_MARBLE(0xF0F0EB, MapColor.TERRACOTTA_WHITE),
    APPLE(0x0000, MapColor.TERRACOTTA_RED),
    PALM(0x000, MapColor.TERRACOTTA_YELLOW),
    DEAD(0x000, MapColor.COLOR_GRAY);

    /**
     * {@link Integer RGB color}
     */
    private final int color;
    /**
     * {@link MapColor The map color}
     */
    private final MapColor mapColor;

    /**
     * Constructor. Set the {@link Integer RGB color}
     *
     * @param color {@link Integer The RGB color}
     * @param mapColor {@link MapColor The map color}
     */
    MWColors(final int color, final MapColor mapColor) {
        this.color = color;
        this.mapColor = mapColor;
    }

    /**
     * Get a {@link TextColor text color} from the {@link Integer RGB color value}
     *
     * @return {@link TextColor The text color}
     */
    public TextColor toText() {
        return TextColor.fromRgb(color);
    }

    /**
     * Get the {@link MapColor map color}
     *
     * @return {@link MapColor The map color}
     */
    public MapColor toMapColor() {
        return this.mapColor;
    }

}