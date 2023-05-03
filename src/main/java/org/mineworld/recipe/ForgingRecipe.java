package org.mineworld.recipe;

import com.google.gson.JsonObject;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeHooks;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlocks;
import org.mineworld.core.MWRecipeSerializers;
import org.mineworld.core.MWRecipeTypes;

import java.util.stream.Stream;

/**
 * Implementation class for a {@link MineWorld MineWorld} forging recipe
 */
public class ForgingRecipe implements SmithingRecipe {

    /**
     * {@link Ingredient The recipe base ingredient}
     */
    private final Ingredient base;
    /**
     * {@link Ingredient The recipe addition ingredient}
     */
    private final Ingredient addition;
    /**
     * {@link ItemStack The recipe result}
     */
    private final ItemStack result;
    /**
     * {@link ResourceLocation The recipe id}
     */
    private final ResourceLocation id;

    /**
     * Constructor. Set the recipe properties
     *
     * @param id {@link ResourceLocation The recipe id}
     * @param base {@link Ingredient The recipe base ingredient}
     * @param addition {@link Ingredient The recipe addition ingredient}
     * @param result {@link ItemStack The recipe result}
     */
    public ForgingRecipe(final ResourceLocation id, final Ingredient base, final Ingredient addition, final ItemStack result) {
        this.id = id;
        this.base = base;
        this.addition = addition;
        this.result = result;
    }

    /**
     * Check if some ingredients matches a recipe
     *
     * @param container {@link Container The container with the ingredients}
     * @param level {@link Level The level reference}
     * @return {@link Boolean True if the ingredients matches some recipe}
     */
    @Override
    public boolean matches(final Container container, final @NotNull Level level) {
        return isIngredient(container.getItem(0)) || isIngredient(container.getItem(1));
    }

    /**
     * Get the {@link ItemStack recipe result} based on the matched recipe
     *
     * @param container {@link Container The container for the recipe}
     * @param registryAccess {@link RegistryAccess The registry access}
     * @return {@link ItemStack The recipe result}
     */
    @Override
    public @NotNull ItemStack assemble(final Container container, final @NotNull RegistryAccess registryAccess) {
        ItemStack itemstack = this.result.copy();
        CompoundTag compoundtag = container.getItem(0).getTag();
        if (compoundtag != null) {
            itemstack.setTag(compoundtag.copy());
        }
        return itemstack;
    }

    /**
     * Check if the crafting container is of the correct size
     *
     * @param width {@link Integer The crafting container width}
     * @param height {@link Integer The crafting container height}
     * @return {@link Boolean True if the crafting container is consistent of 2 slots}
     */
    @Override
    public boolean canCraftInDimensions(final int width, final int height) {
        return width * height >= 2;
    }

    /**
     * Get the {@link ItemStack recipe result}
     *
     * @param registryAccess {@link RegistryAccess The registry access}
     * @return {@link ItemStack The recipe result}
     */
    @Override
    public @NotNull ItemStack getResultItem(final @NotNull RegistryAccess registryAccess) {
        return this.getResult();
    }

    /**
     * Get the {@link Ingredient recipe base ingredient}
     *
     * @return {@link Ingredient The recipe base ingredient}
     */
    public Ingredient getBase() {
        return this.base;
    }

    /**
     * Get the {@link Ingredient recipe addition ingredient}
     *
     * @return {@link Ingredient The recipe addition ingredient}
     */
    public Ingredient getAddition() {
        return this.addition;
    }

    /**
     * Get the {@link ItemStack recipe result}
     *
     * @return {@link ItemStack The recipe result}
     */
    public ItemStack getResult() {
        return this.result;
    }

    /**
     * Check if an ingredient is a smithing template
     *
     * @param itemStack {@link ItemStack The ingredient item stack}
     * @return {@link Boolean False}
     */
    @Override
    public boolean isTemplateIngredient(final @NotNull ItemStack itemStack) {
        return false;
    }

    /**
     * Check if an {@link ItemStack ingredient} corresponds to a base ingredient
     *
     * @param itemStack {@link ItemStack The ingredient}
     * @return {@link Boolean True if the item stack corresponds to a base or addition ingredient}
     */
    @Override
    public boolean isBaseIngredient(final @NotNull ItemStack itemStack) {
        return isIngredient(itemStack);
    }

    /**
     * Check if an {@link ItemStack ingredient} corresponds to an addition ingredient
     *
     * @param itemStack {@link ItemStack The ingredient}
     * @return {@link Boolean True if the item stack corresponds to a base or addition ingredient}
     */
    @Override
    public boolean isAdditionIngredient(final @NotNull ItemStack itemStack) {
        return isIngredient(itemStack);
    }

    /**
     * Check if an {@link ItemStack ingredient} corresponds to an ingredient
     *
     * @param itemStack {@link ItemStack The ingredient}
     * @return {@link Boolean True if the item stack corresponds to a base or addition ingredient}
     */
    private boolean isIngredient(final ItemStack itemStack) {
        return this.base.test(itemStack) || this.addition.test(itemStack);
    }

    /**
     * Get the {@link ResourceLocation recipe id}
     *
     * @return {@link ResourceLocation The recipe id}
     */
    @Override
    public @NotNull ResourceLocation getId() {
        return this.id;
    }

    /**
     * Get the {@link RecipeSerializer recipe serializer}
     *
     * @return {@link RecipeSerializer The recipe serializer}
     */
    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return MWRecipeSerializers.FORGING.get();
    }

    /**
     * Get the {@link ItemStack item stack to show on recipe unlocks}
     *
     * @return {@link MWBlocks#FORGING_TABLE The forging table item stack}
     */
    @Override
    public @NotNull ItemStack getToastSymbol() {
        return new ItemStack(MWBlocks.FORGING_TABLE.get());
    }

    /**
     * Check if the recipe is incomplete
     *
     * @return {@link Boolean True if there is a missing ingredient}
     */
    @Override
    public boolean isIncomplete() {
        return Stream.of(this.base, this.addition).anyMatch(ingredient -> ForgeHooks.hasNoElements(ingredient));
    }

    /**
     * Get the {@link RecipeType recipe type}
     *
     * @return {@link MWRecipeTypes#FORGING The forging table recipe type}
     */
    @Override
    public @NotNull RecipeType<?> getType() {
        return MWRecipeTypes.FORGING.get();
    }

    /**
     * {@link RecipeSerializer<ForgingRecipe> The forging recipe serializer}
     */
    public static class ForgingRecipeSerialzier implements RecipeSerializer<ForgingRecipe> {

        /**
         * Deserialize a recipe from a JSON file
         *
         * @param recipeId {@link ResourceLocation The recipe resource location}
         * @param json {@link JsonObject The recipe json object}
         */
        public @NotNull ForgingRecipe fromJson(final @NotNull ResourceLocation recipeId, final @NotNull JsonObject json) {
            return new ForgingRecipe(recipeId, getIngredient("base", json), getIngredient("addition", json), ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result")));
        }

        /**
         * Get an {@link Ingredient ingredient} from the {@link JsonObject serialized recipe json}
         *
         * @param key {@link String The ingredient key}
         * @param json {@link JsonObject The serialized recipe json}
         * @return {@link Ingredient The ingredient}
         */
        private Ingredient getIngredient(final String key, final JsonObject json) {
            return Ingredient.fromJson(GsonHelper.isArrayNode(json, key) ? GsonHelper.getAsJsonArray(json, key) : GsonHelper.getAsJsonObject(json, key));
        }

        /**
         * Deserialize a recipe from the network
         *
         * @param recipeId {@link ResourceLocation The recipe resource location}
         * @param byteBuffer {@link FriendlyByteBuf The network byte buffer}
         */
        public ForgingRecipe fromNetwork(final @NotNull ResourceLocation recipeId, final @NotNull FriendlyByteBuf byteBuffer) {
            return new ForgingRecipe(recipeId, Ingredient.fromNetwork(byteBuffer), Ingredient.fromNetwork(byteBuffer), byteBuffer.readItem());
        }

        /**
         * Serialize a recipe to the network
         *
         * @param byteBuffer {@link FriendlyByteBuf The network byte buffer}
         * @param recipe {@link ForgingRecipe The recipe to serialize}
         */
        public void toNetwork(final @NotNull FriendlyByteBuf byteBuffer, final ForgingRecipe recipe) {
            recipe.base.toNetwork(byteBuffer);
            recipe.addition.toNetwork(byteBuffer);
            byteBuffer.writeItem(recipe.result);
        }

    }

}