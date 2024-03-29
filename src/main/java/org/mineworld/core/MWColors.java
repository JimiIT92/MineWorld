package org.mineworld.core;

import net.minecraft.network.chat.TextColor;
import net.minecraft.world.level.material.MapColor;
import org.mineworld.MineWorld;

/**
 * {@link MineWorld MineWorld} {@link MapColor colors}
 */
public enum MWColors {

    GRASS(0x00AA00, MapColor.GRASS),
    DIRT(0x7B553D, MapColor.DIRT),
    SAND(0xFFFF55, MapColor.SAND),
    RED_SAND(0xB8602C, MapColor.COLOR_ORANGE),
    STONE(0xAAAAAA, MapColor.STONE),
    CAKE(0xF6E8CB, MapColor.TERRACOTTA_WHITE),
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
    APPLE(0xBE7D67, MapColor.TERRACOTTA_RED),
    PALM(0xBF8F40, MapColor.TERRACOTTA_YELLOW),
    DEAD(0x747474, MapColor.COLOR_GRAY);

    /**
     * {@link Integer The RGB color}
     */
    private final int color;
    /**
     * {@link MapColor The Map color}
     */
    private final MapColor mapColor;

    /**
     * Constructor. Set the {@link Integer RGB color}
     * and the {@link MapColor Map color}
     *
     * @param color {@link Integer The RGB color}
     * @param mapColor {@link MapColor The Map color}
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