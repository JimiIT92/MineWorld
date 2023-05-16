package org.mineworld.core;

import net.minecraft.network.chat.TextColor;
import net.minecraft.world.level.material.MaterialColor;
import org.mineworld.MineWorld;

/**
 * {@link MineWorld MineWorld} colors
 */
public enum MWColors {

    PRISMARINE(0x49C4AF, MaterialColor.COLOR_CYAN),
    ECHO_SHARD(0x0A2330, MaterialColor.COLOR_BLACK),
    RUBY(0x9F3535, MaterialColor.FIRE),
    SAPPHIRE(0x35559F, MaterialColor.COLOR_BLUE),
    RAW_SILVER(0xC5C5C5, MaterialColor.RAW_IRON),
    SILVER(0xC5C5C5, MaterialColor.COLOR_LIGHT_GRAY),
    ALUMINUM(0x666666, MaterialColor.COLOR_GRAY),
    RAW_ALUMINUM(0xC5C5C5, MaterialColor.RAW_IRON),
    RAW_BRONZE(0xC5C5C5, MaterialColor.TERRACOTTA_BROWN),
    BRONZE(0x755848, MaterialColor.COLOR_BROWN),
    PYRITE(0x847C50, MaterialColor.TERRACOTTA_YELLOW),
    CHARCOAL(0x755848, MaterialColor.COLOR_BLACK),
    MARBLE(0x9E9A86, MaterialColor.TERRACOTTA_GRAY),
    WHITE_MARBLE(0xF0F0EB, MaterialColor.TERRACOTTA_WHITE),
    APPLE(0x0000, MaterialColor.TERRACOTTA_RED),
    PALM(0x000, MaterialColor.TERRACOTTA_YELLOW),
    DEAD(0x000, MaterialColor.COLOR_GRAY);

    /**
     * {@link Integer RGB color}
     */
    private final int color;
    /**
     * {@link MaterialColor The material color}
     */
    private final MaterialColor materialColor;

    /**
     * Constructor. Set the {@link Integer RGB color}
     *
     * @param color {@link Integer The RGB color}
     * @param materialColor {@link MaterialColor The material color}
     */
    MWColors(final int color, final MaterialColor materialColor) {
        this.color = color;
        this.materialColor = materialColor;
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
     * Get the {@link MaterialColor material color}
     *
     * @return {@link MaterialColor The material color}
     */
    public MaterialColor toMaterialColor() {
        return this.materialColor;
    }

}