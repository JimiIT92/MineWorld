package org.mineworld.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.enchantment.FieryTouchEnchantment;
import org.mineworld.helper.RegistryHelper;

import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link Enchantment Enchantments}
 */
public final class MWEnchantments {

    //#region Registry

    /**
     * The {@link DeferredRegister<Enchantment> Enchantments Registry}
     */
    private static final DeferredRegister<Enchantment> ENCHANTMENTS = RegistryHelper.registry(Registries.ENCHANTMENT);

    //#endregion

    //#region Enchantments

    public static final RegistryObject<Enchantment> FIERY_TOUCH = registerEnchantment("fiery_touch", FieryTouchEnchantment::new);

    //#endregion

    //#region Methods

    /**
     * Register an {@link Enchantment Enchantment}
     *
     * @param name {@link String The Enchantment name}
     * @param enchantmentSupplier {@link Supplier The Enchantment supplier}
     * @return {@link RegistryObject<Enchantment> The registered Enchantment}
     */
    private static RegistryObject<Enchantment> registerEnchantment(final String name, final Supplier<Enchantment> enchantmentSupplier) {
        return ENCHANTMENTS.register(name, enchantmentSupplier);
    }

    //#endregion

    //#region Bus register

    /**
     * Register all {@link Enchantment Enchantments}
     *
     * @param eventBus {@link IEventBus The mod event bus}
     */
    public static void register(final IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }

    //#endregion

}