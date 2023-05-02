package org.mineworld.core;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.helper.RegisterHelper;
import org.mineworld.loot.AddItemsModifier;

/**
 * {@link MineWorld MineWorld} {@link IGlobalLootModifier loot modifiers}
 */
public final class MWLootModifiers {

    public static final RegistryObject<Codec<AddItemsModifier>> ADD_ITEM = RegisterHelper.registerLootModifier("add_items", AddItemsModifier.CODEC);

    /**
     * Register the {@link MineWorld MineWorld} {@link IGlobalLootModifier loot modifiers}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void register(final IEventBus eventBus) {
        RegisterHelper.registerLootModifiers(eventBus);
    }

}