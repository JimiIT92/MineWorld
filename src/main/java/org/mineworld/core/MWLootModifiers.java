package org.mineworld.core;

import com.mojang.serialization.Codec;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.helper.RegistryHelper;
import org.mineworld.loot.AddItemsModifier;
import org.mineworld.loot.FieryTouchModifier;
import org.mineworld.loot.ReplaceItemsModifier;

import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link IGlobalLootModifier Loot modifiers}
 */
public final class MWLootModifiers {

    //#region Registry

    /**
     * The {@link DeferredRegister Loot modifiers Registry}
     */
    private static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = RegistryHelper.registry(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS);

    //#endregion

    //#region Loot modifiers

    public static final RegistryObject<Codec<AddItemsModifier>> ADD_ITEMS = registerLootModifier("add_items", AddItemsModifier.CODEC);
    public static final RegistryObject<Codec<ReplaceItemsModifier>> REPLACE_ITEMS = registerLootModifier("replace_items", ReplaceItemsModifier.CODEC);
    public static final RegistryObject<Codec<FieryTouchModifier>> FIERY_TOUCH = registerLootModifier("fiery_touch", FieryTouchModifier.CODEC);

    //#endregion

    //#region Methods

    /**
     * Register a {@link IGlobalLootModifier Loot modifier}
     *
     * @param name {@link String The Loot modifier name}
     * @param codecSupplier {@link Supplier The Loot modifier codec supplier}
     * @return {@link RegistryObject<ResourceLocation> The registered Loot modifier}
     */
    private static <T extends IGlobalLootModifier> RegistryObject<Codec<T>> registerLootModifier(final String name, final Supplier<Codec<T>> codecSupplier) {
        return LOOT_MODIFIERS.register(name, codecSupplier);
    }

    //#endregion

    //#region Bus register

    /**
     * Register all {@link IGlobalLootModifier Loot modifiers}
     *
     * @param eventBus {@link IEventBus The mod event bus}
     */
    public static void register(final IEventBus eventBus) {
        LOOT_MODIFIERS.register(eventBus);
    }

    //#endregion

}