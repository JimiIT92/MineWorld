package org.mineworld.core;


import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.helper.RegisterHelper;
import org.mineworld.recipe.ForgingRecipe;
import org.mineworld.recipe.GiftRecipe;
import org.mineworld.recipe.WoodcutterRecipe;
import org.mineworld.recipe.serializer.ForgingRecipeSerializer;
import org.mineworld.recipe.serializer.MWSingleItemRecipeSerializer;

/**
 * {@link MineWorld MineWorld} {@link RecipeSerializer recipe serializers}
 */
public final class MWRecipeSerializers {

    public static final RegistryObject<RecipeSerializer<WoodcutterRecipe>> WOODCUTTER = RegisterHelper.registerRecipeSerializer("woodcutting", () -> new MWSingleItemRecipeSerializer<>(WoodcutterRecipe::new));
    public static final RegistryObject<RecipeSerializer<ForgingRecipe>> FORGING = RegisterHelper.registerRecipeSerializer("forging", ForgingRecipeSerializer::new);
    public static final RegistryObject<RecipeSerializer<GiftRecipe>> GIFT = RegisterHelper.registerRecipeSerializer("gift", () -> new SimpleCraftingRecipeSerializer<>(GiftRecipe::new));

    /**
     * Register the {@link MineWorld MineWorld} {@link RecipeSerializer recipe serializers}
     *
     * @param eventBus {@link IEventBus The event bus}
     */
    public static void register(final IEventBus eventBus) {
        RegisterHelper.registerRecipeSerializers(eventBus);
    }

}