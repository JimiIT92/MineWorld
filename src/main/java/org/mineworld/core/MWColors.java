package org.mineworld.core;

import net.minecraft.network.chat.TextColor;
import org.mineworld.MineWorld;

/**
 * {@link MineWorld MineWorld} colors
 */
public enum MWColors {

    PRISMARINE(0x49C4AF),
    ECHO_SHARD(0x0A2330),
    RUBY(0x9F3535),
    SAPPHIRE(0x35559F),
    SILVER(0xC5C5C5),
    ALUMINUM(0x666666),
    BRONZE(0x755848);

    /**
     * {@link Integer RGB color}
     */
    private final int color;

    /**
     * Constructor. Set the {@link Integer RGB color}
     *
     * @param color {@link Integer The RGB color}
     */
    MWColors(final int color) {
        this.color = color;
    }

    /**
     * Get a {@link TextColor text color} from the {@link Integer RGB color value}
     *
     * @return {@link TextColor The text color}
     */
    public TextColor toText() {
        return TextColor.fromRgb(color);
    }
}
