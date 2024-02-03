package org.mineworld.core;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.advancements.critereon.PlayerTrigger;
import org.mineworld.MineWorld;
import org.mineworld.helper.ResourceHelper;

/**
 * {@link MineWorld MineWorld} {@link CriterionTrigger Criteria Triggers}
 */
public class MWCriteriaTriggers {

    //#region Criteria Triggers

    public static PlayerTrigger USE_MAGIC_MIRROR = registerPlayerTrigger("use_magic_mirror");
    public static PlayerTrigger EQUIP_INVISIBILITY_CLOAK = registerPlayerTrigger("equip_invisibility_cloak");
    public static PlayerTrigger IGNITE_CAKE_TNT = registerPlayerTrigger("ignite_cake_tnt");

    //#endregion

    //#region Methods

    /**
     * Register a {@link PlayerTrigger Player Trigger}
     *
     * @param name {@link String The Player Trigger name}
     * @return {@link PlayerTrigger The registered Player Trigger}
     */
    private static PlayerTrigger registerPlayerTrigger(final String name) {
        return CriteriaTriggers.register(ResourceHelper.stringLocation(name), new PlayerTrigger());
    }

    //#endregion

    //#region Register

    /**
     * Register all {@link MineWorld MineWorld} {@link CriterionTrigger Critera Triggers}
     */
    public static void register() { }

    //#endregion

}