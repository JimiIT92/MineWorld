package org.mineworld.core;

import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import org.mineworld.MineWorld;

import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link ForgeSoundType sound types}
 */
public final class MWSoundTypes {

    public static final ForgeSoundType SCULK_WOOD = createSoundType(
            MWSounds.SCULK_WOOD_BREAK,
            MWSounds.SCULK_WOOD_STEP,
            MWSounds.SCULK_WOOD_PLACE,
            MWSounds.SCULK_WOOD_HIT,
            MWSounds.SCULK_WOOD_FALL
    );
    public static final ForgeSoundType SCULK_WOOD_HANGING_SIGN = createSoundType(
            MWSounds.SCULK_WOOD_HANGING_SIGN_BREAK,
            MWSounds.SCULK_WOOD_HANGING_SIGN_STEP,
            MWSounds.SCULK_WOOD_HANGING_SIGN_PLACE,
            MWSounds.SCULK_WOOD_HANGING_SIGN_HIT,
            MWSounds.SCULK_WOOD_HANGING_SIGN_FALL
    );
    public static final ForgeSoundType SCULK_SOIL = createSoundType(
            MWSounds.SCULK_SOIL_BREAK,
            MWSounds.SCULK_SOIL_STEP,
            MWSounds.SCULK_SOIL_PLACE,
            MWSounds.SCULK_SOIL_HIT,
            MWSounds.SCULK_SOIL_FALL
    );
    public static final ForgeSoundType END_SOIL = createSoundType(
            MWSounds.END_SOIL_BREAK,
            MWSounds.END_SOIL_STEP,
            MWSounds.END_SOIL_PLACE,
            MWSounds.END_SOIL_HIT,
            MWSounds.END_SOIL_FALL
    );

    /**
     * Get a {@link ForgeSoundType sound type}
     *
     * @param breakSoundSupplier {@link Supplier<SoundEvent> The break sound supplier}
     * @param stepSoundSupplier {@link Supplier<SoundEvent> The step sound supplier}
     * @param placeSoundSupplier {@link Supplier<SoundEvent> The place sound supplier}
     * @param hitSoundSupplier {@link Supplier<SoundEvent> The hit sound supplier}
     * @param fallSoundSupplier {@link Supplier<SoundEvent> The fall sound supplier}
     * @return {@link ForgeSoundType The sound type}
     */
    private static ForgeSoundType createSoundType(final Supplier<SoundEvent> breakSoundSupplier, final Supplier<SoundEvent> stepSoundSupplier, final Supplier<SoundEvent> placeSoundSupplier, final Supplier<SoundEvent> hitSoundSupplier, final Supplier<SoundEvent> fallSoundSupplier) {
        return new ForgeSoundType(1.0F, 1.0F, breakSoundSupplier, stepSoundSupplier, placeSoundSupplier, hitSoundSupplier, fallSoundSupplier);
    }

}