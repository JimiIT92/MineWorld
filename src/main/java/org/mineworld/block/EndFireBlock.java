package org.mineworld.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;

/**
 * {@link MineWorld MineWorld} {@link MWFireBlock End Fire Block}
 */
public class EndFireBlock extends MWFireBlock {

    /**
     * {@link MapCodec The Block Codec}
     */
    public static final MapCodec<EndFireBlock> CODEC = simpleCodec(EndFireBlock::new);

    /**
     * Constructor. Set the {@link BlockBehaviour.Properties Block Properties}
     *
     * @param properties The {@link BlockBehaviour.Properties Block Properties}
     */
    public EndFireBlock(final BlockBehaviour.Properties properties) {
        super(MWFireType.END, properties);
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
