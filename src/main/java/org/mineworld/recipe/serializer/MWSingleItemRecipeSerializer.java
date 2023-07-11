package org.mineworld.recipe.serializer;

import com.google.gson.JsonObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.helper.KeyHelper;

/**
 * {@link MineWorld MineWorld} {@link SingleItemRecipe single id recipe} serializer
 * @param <T> {@link T The recipe type}
 */
public class MWSingleItemRecipeSerializer<T extends SingleItemRecipe> implements RecipeSerializer<T> {

    /**
     * {@link IMWSingleItemMaker<T> The recipe maker factory}
     */
    private IMWSingleItemMaker<T> factory;

    /**
     * Constructor. Set the serializer properties
     *
     * @param factory {@link IMWSingleItemMaker<T> The recipe maker factory}
     */
    public MWSingleItemRecipeSerializer(IMWSingleItemMaker<T> factory) {
        this.factory = factory;
    }

    /**
     * Deserialize a recipe from a JSON file
     *
     * @param recipeId {@link ResourceLocation The recipe resource location}
     * @param json {@link JsonObject The recipe json object}
     * @return {@link T The deserialized recipe}
     */
    public @NotNull T fromJson(@NotNull ResourceLocation recipeId, @NotNull JsonObject json) {
        return this.factory.create(recipeId,
                GsonHelper.getAsString(json, "group", ""),
                getIngredient("ingredient", json),
                new ItemStack(BuiltInRegistries.ITEM.get(KeyHelper.fromJson(json, "result")),
                        GsonHelper.getAsInt(json, "count", 1)
                )
        );
    }

    /**
     * Get an {@link Ingredient ingredient} from the {@link JsonObject serialized recipe json}
     *
     * @param key {@link String The ingredient key}
     * @param json {@link JsonObject The serialized recipe json}
     * @return {@link Ingredient The ingredient}
     */
    private Ingredient getIngredient(String key, JsonObject json) {
        return Ingredient.fromJson(GsonHelper.isArrayNode(json, key) ? GsonHelper.getAsJsonArray(json, key) : GsonHelper.getAsJsonObject(json, key));
    }

    /**
     * Deserialize a recipe from the network
     *
     * @param recipeId {@link ResourceLocation The recipe resource location}
     * @param byteBuffer {@link FriendlyByteBuf The network byte buffer}
     * @return {@link T The deserialized recipe}
     */
    public T fromNetwork(@NotNull ResourceLocation recipeId, FriendlyByteBuf byteBuffer) {
        return this.factory.create(recipeId, byteBuffer.readUtf(), Ingredient.fromNetwork(byteBuffer), byteBuffer.readItem());
    }

    /**
     * Serialize a recipe to the network
     *
     * @param byteBuffer {@link FriendlyByteBuf The network byte buffer}
     * @param recipe {@link T The recipe to serialize}
     */
    public void toNetwork(FriendlyByteBuf byteBuffer, T recipe) {
        byteBuffer.writeUtf(recipe.group);
        recipe.ingredient.toNetwork(byteBuffer);
        byteBuffer.writeItem(recipe.result);
    }

    /**
     * Interface for a {@link SingleItemRecipe single id recipe maker}
     *
     * @param <T> {@link T The recipe type}
     */
    public interface IMWSingleItemMaker<T extends SingleItemRecipe> {

        /**
         * Create a recipe
         *
         * @param recipeId {@link ResourceLocation The recipe resource location}
         * @param group {@link String The recipe group}
         * @param ingredient {@link Ingredient The recipe ingredient}
         * @param result {@link ItemStack The recipe result}
         * @return {@link T The created recipe}
         */
        T create(ResourceLocation recipeId, String group, Ingredient ingredient, ItemStack result);
    }

}