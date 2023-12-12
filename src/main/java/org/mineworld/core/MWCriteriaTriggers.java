package org.mineworld.core;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraftforge.eventbus.api.IEventBus;
import org.mineworld.MineWorld;

/**
 * {@link MineWorld MineWorld} advancement criteria
 */
public final class MWCriteriaTriggers {

    public static PlayerTrigger USE_MAGIC_MIRROR = register("use_magic_mirror", new PlayerTrigger());
    public static PlayerTrigger EQUIP_INVISIBILITY_CLOAK = register("equip_invisibility_cloak", new PlayerTrigger());
    public static PlayerTrigger IGNITE_CAKE_TNT = register("ignite_cake_tnt", new PlayerTrigger());

    /**
     * Register an advancement criteria
     *
     * @param name {@link String The criteria name}
     * @param trigger {@link T The criteria trigger}
     * @return {@link T The registered trigger}
     * @param <T> {@link T The trigger type}
     */
    public static <T extends CriterionTrigger<?>> T register(String name, T trigger) {
        return CriteriaTriggers.register(MineWorld.MOD_ID + ":" + name, trigger);
    }

    /**
     * Register the {@link MineWorld MineWorld} advancement criteria
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void register(final IEventBus eventBus) { }

}