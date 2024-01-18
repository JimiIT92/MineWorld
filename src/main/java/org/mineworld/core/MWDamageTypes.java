package org.mineworld.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import org.mineworld.MineWorld;
import org.mineworld.helper.RegistryHelper;

/**
 * {@link MineWorld MineWorld} {@link DamageType Damage Types}
 */
public final class MWDamageTypes {

    //#region Damage Types

    public static final ResourceKey<DamageType> BLUEBERRY_BUSH = registerDamageTypeKey("blueberry_bush");

    //#endregion

    //#region Methods

    /**
     * Register a {@link DamageType Damage Type} {@link ResourceKey<DamageType> Resource Key}
     *
     * @param name {@link String The Damage Type name}
     * @return {@link ResourceKey<DamageType> The Damage Type Resource Key}
     */
    private static ResourceKey<DamageType> registerDamageTypeKey(final String name) {
        return RegistryHelper.register(Registries.DAMAGE_TYPE, name);
    }

    //#endregion

    //#region Register

    /**
     * Register all {@link DamageType Damage Types}
     *
     * @param context {@link BootstapContext<DamageType> The Bootstrap Context}
     */
    public static void bootstrap(final BootstapContext<DamageType> context) {
        context.register(BLUEBERRY_BUSH, new DamageType("blueberryBush", 0.1F));
    }

    //#endregion

}