package org.mineworld.core;


import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.helper.RegisterHelper;
import org.mineworld.recipe.ForgingRecipe;
import org.mineworld.recipe.WoodcutterRecipe;
import org.mineworld.recipe.serializer.MWSingleItemRecipeSerializer;

/**
 * {@link MineWorld MineWorld} {@link RecipeSerializer recipe serializers}
 */
public final class MWRecipeSerializers {

    public static final RegistryObject<RecipeSerializer<WoodcutterRecipe>> WOODCUTTER = RegisterHelper.registerRecipeSerializer("woodcutting", () -> new MWSingleItemRecipeSerializer<>(WoodcutterRecipe::new));
    public static final RegistryObject<RecipeSerializer<ForgingRecipe>> FORGING = RegisterHelper.registerRecipeSerializer("forging", ForgingRecipe.ForgingRecipeSerialzier::new);

    /**
     * Register the {@link MineWorld MineWorld} {@link RecipeSerializer recipe serializers}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void register(final IEventBus eventBus) {
        RegisterHelper.registerRecipeSerializers(eventBus);
    }

}