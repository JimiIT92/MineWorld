package org.mineworld.block;

import com.mojang.serialization.MapCodec;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;

/**
 * {@link MineWorld MineWorld} {@link MWFireBlock Sculk Fire Block}
 */
public class SculkFireBlock extends MWFireBlock {

    /**
     * {@link MapCodec The Block Codec}
     */
    public static final MapCodec<SculkFireBlock> CODEC = simpleCodec(SculkFireBlock::new);

    /**
     * Constructor. Set the {@link Properties Block Properties}
     *
     * @param properties The {@link Properties Block Properties}
     */
    public SculkFireBlock(final Properties properties) {
        super(MWFireType.SCULK, properties);
    }

    /**
     * Get the {@link MapCodec Block Codec}
     *
     * @return {@link MapCodec The Block Codec}
     */
    @Override
    protected @NotNull MapCodec<? extends MWFireBlock> codec() {
        return CODEC;
    }
}
