package org.mineworld.core;

import com.google.common.base.Suppliers;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mineworld.MineWorld;
import org.mineworld.helper.RegistryHelper;
import org.mineworld.recipe.ForgingRecipe;
import org.mineworld.recipe.GiftRecipe;
import org.mineworld.recipe.WoodcutterRecipe;
import org.mineworld.recipe.serializer.ForgingRecipeSerializer;
import org.mineworld.recipe.serializer.MWSingleItemRecipeSerializer;

import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link RecipeSerializer Recipe Serializers}
 */
public final class MWRecipeSerializers {

    //#region Registry

    /**
     * The {@link DeferredRegister<RecipeSerializer> Recipe Serializers Registry}
     */
    private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = RegistryHelper.registry(ForgeRegistries.RECIPE_SERIALIZERS);

    //#endregion

    //#region Recipe Serializers

    public static final RegistryObject<RecipeSerializer<WoodcutterRecipe>> WOODCUTTER = registerRecipeSerializer("woodcutting", () -> new MWSingleItemRecipeSerializer<>(WoodcutterRecipe::new));
    public static final RegistryObject<RecipeSerializer<ForgingRecipe>> FORGING = registerRecipeSerializer("forging", ForgingRecipeSerializer::new);
    public static final RegistryObject<RecipeSerializer<GiftRecipe>> GIFT = registerSimpleRecipeSerializer("gift", GiftRecipe::new);

    //#endregion

    //#region Methods

    /**
     * Register a {@link RecipeSerializer Recipe Serializer}
     *
     * @param name {@link String The Recipe Serializer name}
     * @param recipeFactory {@link SimpleCraftingRecipeSerializer.Factory<T> The Recipe Factory}
     * @return {@link RegistryObject<RecipeSerializer> The registered Recipe Serializer}
     * @param <T> {@link T The Recipe Type}
     */
    private static <T extends CraftingRecipe> RegistryObject<RecipeSerializer<T>> registerSimpleRecipeSerializer(final String name, final SimpleCraftingRecipeSerializer.Factory<T> recipeFactory) {
        return registerRecipeSerializer(name, Suppliers.memoize(() -> new SimpleCraftingRecipeSerializer<>(recipeFactory)));
    }

    /**
     * Register a {@link RecipeSerializer Recipe Serializer}
     *
     * @param name {@link String The Recipe Serializer name}
     * @param recipeSerializerSupplier {@link Supplier<RecipeSerializer> The Recipe Serializer Supplier}
     * @return {@link RegistryObject<RecipeSerializer> The registered Recipe Serializer}
     * @param <T> {@link T The Recipe Type}
     */
    private static <T extends Recipe<?>> RegistryObject<RecipeSerializer<T>> registerRecipeSerializer(final String name, final Supplier<RecipeSerializer<T>> recipeSerializerSupplier) {
        return RECIPE_SERIALIZERS.register(name, recipeSerializerSupplier);
    }

    //#endregion

    //#region Bus Register

    /**
     * Register all {@link RecipeSerializer Recipe Serializers}
     *
     * @param eventBus {@link IEventBus The mod event bus}
     */
    public static void register(final IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);
    }

    //#endregion

}