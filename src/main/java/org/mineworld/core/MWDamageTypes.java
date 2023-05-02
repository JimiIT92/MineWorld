package org.mineworld.core;

import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import org.mineworld.MineWorld;
import org.mineworld.helper.KeyHelper;

/**
 * {@link MineWorld MineWorld} {@link DamageType damage types}
 */
public final class MWDamageTypes {

    public static final ResourceKey<DamageType> BLUEBERRY_BUSH = KeyHelper.registerDamageType("blueberry_bush");

    /**
     * Register the {@link DamageType damage types}
     *
     * @param context {@link BootstapContext<DamageType> The bootstrap context}
     */
    public static void bootstrap(BootstapContext<DamageType> context) {
        context.register(BLUEBERRY_BUSH, new DamageType("blueberryBush", 0.1F));
    }

}