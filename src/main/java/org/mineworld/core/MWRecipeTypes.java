package org.mineworld.core;

import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.helper.RegisterHelper;
import org.mineworld.recipe.ForgingRecipe;
import org.mineworld.recipe.WoodcutterRecipe;

/**
 * {@link MineWorld MineWorld} {@link RecipeType recipe types}
 */
public final class MWRecipeTypes {

    public static final RegistryObject<RecipeType<WoodcutterRecipe>> WOODCUTTING = RegisterHelper.registerRecipeType("woodcutting");
    public static final RegistryObject<RecipeType<ForgingRecipe>> FORGING = RegisterHelper.registerRecipeType("forging");

    /**
     * Register the {@link MineWorld MineWorld} {@link RecipeType recipe types}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void register(final IEventBus eventBus) {
        RegisterHelper.registerRecipeTypes(eventBus);
    }

}