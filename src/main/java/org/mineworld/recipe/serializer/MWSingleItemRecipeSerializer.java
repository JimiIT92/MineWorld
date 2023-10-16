package org.mineworld.recipe.serializer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipeCodecs;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;

/**
 * {@link MineWorld MineWorld} {@link SingleItemRecipe single id recipe} serializer
 * @param <T> {@link T The recipe type}
 */
public class MWSingleItemRecipeSerializer<T extends SingleItemRecipe> implements RecipeSerializer<T> {

    /**
     * {@link IMWSingleItemMaker<T> The recipe maker factory}
     */
    private final IMWSingleItemMaker<T> factory;
    /**
     * {@link Codec<T> The codec instance}
     */
    private final Codec<T> codec;

    /**
     * Constructor. Set the serializer properties
     *
     * @param factory {@link IMWSingleItemMaker<T> The recipe maker factory}
     */
    public MWSingleItemRecipeSerializer(final IMWSingleItemMaker<T> factory) {
        this.factory = factory;
        this.codec = RecordCodecBuilder.create((builder) -> builder.group(
                ExtraCodecs.strictOptionalField(Codec.STRING, "group", "").forGetter(recipe -> recipe.group),
                Ingredient.CODEC.fieldOf("ingredient").forGetter(recipe -> recipe.ingredient),
                CraftingRecipeCodecs.ITEMSTACK_OBJECT_CODEC.fieldOf("result").forGetter(recipe -> recipe.result)
        ).apply(builder, factory::create));
    }

    /**
     * Get the {@link Codec<T> Codec Instance}
     *
     * @return The {@link Codec<T> Codec Instance}
     */
    @Override
    public @NotNull Codec<T> codec() {
        return codec;
    }

    /**
     * Deserialize a recipe from the network
     *
     * @param byteBuffer {@link FriendlyByteBuf The network byte buffer}
     * @return {@link T The deserialized recipe}
     */
    public T fromNetwork(final @NotNull FriendlyByteBuf byteBuffer) {
        return this.factory.create(byteBuffer.readUtf(), Ingredient.fromNetwork(byteBuffer), byteBuffer.readItem());
    }

    /**
     * Serialize a recipe to the network
     *
     * @param byteBuffer {@link FriendlyByteBuf The network byte buffer}
     * @param recipe {@link T The recipe to serialize}
     */
    public void toNetwork(final FriendlyByteBuf byteBuffer, final T recipe) {
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
         * @param group {@link String The recipe group}
         * @param ingredient {@link Ingredient The recipe ingredient}
         * @param result {@link ItemStack The recipe result}
         * @return {@link T The created recipe}
         */
        T create(final String group, final Ingredient ingredient, final ItemStack result);
    }

}