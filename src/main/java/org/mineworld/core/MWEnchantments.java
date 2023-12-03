package org.mineworld.core;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.enchantment.FieryTouchEnchantment;
import org.mineworld.helper.RegisterHelper;

/**
 * {@link MineWorld MineWorld} {@link Enchantment enchantments}
 */
public final class MWEnchantments {

    public static final RegistryObject<Enchantment> FIERY_TOUCH = RegisterHelper.registerEnchantment("fiery_touch", FieryTouchEnchantment::new);

    /**
     * Register the {@link MineWorld MineWorld} {@link Enchantment enchantments}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void register(final IEventBus eventBus) {
        RegisterHelper.registerEnchantments(eventBus);
    }

}