package org.mineworld.recipe.serializer;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import org.mineworld.recipe.ForgingRecipe;

/**
 * {@link RecipeSerializer<ForgingRecipe> The forging recipe serializer}
 */
public class ForgingRecipeSerializer implements RecipeSerializer<ForgingRecipe> {

    /**
     * Deserialize a recipe from a JSON file
     *
     * @param recipeId {@link ResourceLocation The recipe resource location}
     * @param json {@link JsonObject The recipe json object}
     */
    public @NotNull ForgingRecipe fromJson(@NotNull ResourceLocation recipeId, @NotNull JsonObject json) {
        return new ForgingRecipe(recipeId,
                getIngredient("base", json),
                getIngredient("addition", json),
                ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result")),
                GsonHelper.getAsInt(json, "forging_time"),
                GsonHelper.getAsFloat(json, "experience"));
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
     */
    public ForgingRecipe fromNetwork(@NotNull ResourceLocation recipeId, @NotNull FriendlyByteBuf byteBuffer) {
        return new ForgingRecipe(recipeId,
                Ingredient.fromNetwork(byteBuffer),
                Ingredient.fromNetwork(byteBuffer),
                byteBuffer.readItem(),
                byteBuffer.readInt(),
                byteBuffer.readFloat());
    }

    /**
     * Serialize a recipe to the network
     *
     * @param byteBuffer {@link FriendlyByteBuf The network byte buffer}
     * @param recipe {@link ForgingRecipe The recipe to serialize}
     */
    public void toNetwork(@NotNull FriendlyByteBuf byteBuffer, ForgingRecipe recipe) {
        recipe.base().toNetwork(byteBuffer);
        recipe.addition().toNetwork(byteBuffer);
        byteBuffer.writeItem(recipe.result());
        byteBuffer.writeInt(recipe.forgingTime());
        byteBuffer.writeFloat(recipe.experience());
    }

}