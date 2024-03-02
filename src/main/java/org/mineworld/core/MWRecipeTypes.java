package org.mineworld.core;

import com.google.common.base.Suppliers;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.helper.RegistryHelper;
import org.mineworld.helper.ResourceHelper;
import org.mineworld.recipe.ForgingRecipe;
import org.mineworld.recipe.WoodcutterRecipe;

/**
 * {@link MineWorld MineWorld} {@link RecipeType Recipe Types}
 */
public final class MWRecipeTypes {

    //#region Registry

    /**
     * The {@link DeferredRegister<RecipeType> Recipe Types Registry}
     */
    private static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = RegistryHelper.registry(ForgeRegistries.RECIPE_TYPES);

    //#endregion

    //#region Recipe Types

    public static final RegistryObject<RecipeType<WoodcutterRecipe>> WOODCUTTING = registerRecipeType("woodcutting");
    public static final RegistryObject<RecipeType<ForgingRecipe>> FORGING = registerRecipeType("forging");

    //#endregion

    //#region Methods

    /**
     * Register a {@link RecipeType Recipe Type}
     *
     * @param name {@link String The Recipe Type name}
     * @return {@link RegistryObject<RecipeType> The registered Recipe Type}
     * @param <T> {@link T The Recipe Type}
     */
    private static <T extends Recipe<?>> RegistryObject<RecipeType<T>> registerRecipeType(final String name) {
        return RECIPE_TYPES.register(name, Suppliers.memoize(() -> RecipeType.simple(ResourceHelper.resourceLocation(name))));
    }

    //#endregion

    //#region Bus Register

    /**
     * Register all {@link RecipeType Recipe Types}
     *
     * @param eventBus {@link IEventBus The mod event bus}
     */
    public static void register(final IEventBus eventBus) {
        RECIPE_TYPES.register(eventBus);
    }

    //#endregion

}