package org.mineworld.recipe.serializer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.crafting.CraftingRecipeCodecs;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;
import org.mineworld.recipe.ForgingRecipe;

/**
 * {@link RecipeSerializer<ForgingRecipe> The forging recipe serializer}
 */
public class ForgingRecipeSerializer implements RecipeSerializer<ForgingRecipe> {

    /**
     * The {@link Codec<ForgingRecipe> recipe codec}
     */
    private static final Codec<ForgingRecipe> CODEC = RecordCodecBuilder.create(builder ->
            builder.group(Ingredient.CODEC.fieldOf("base").forGetter(recipe -> recipe.base()),
            Ingredient.CODEC.fieldOf("addition").forGetter(recipe -> recipe.addition()),
            CraftingRecipeCodecs.ITEMSTACK_OBJECT_CODEC.fieldOf("result").forGetter(recipe -> recipe.result()),
            Codec.INT.fieldOf("forging_time").forGetter(recipe -> recipe.forgingTime()),
            Codec.FLOAT.fieldOf("experience").forGetter(recipe -> recipe.experience())
            ).apply(builder, ForgingRecipe::new));

    /**
     * Deserialize a recipe from the network
     *
     * @param byteBuffer {@link FriendlyByteBuf The network byte buffer}
     */
    public ForgingRecipe fromNetwork(final @NotNull FriendlyByteBuf byteBuffer) {
        return new ForgingRecipe(
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
    public void toNetwork(final @NotNull FriendlyByteBuf byteBuffer, final ForgingRecipe recipe) {
        recipe.base().toNetwork(byteBuffer);
        recipe.addition().toNetwork(byteBuffer);
        byteBuffer.writeItem(recipe.result());
        byteBuffer.writeInt(recipe.forgingTime());
        byteBuffer.writeFloat(recipe.experience());
    }

    /**
     * Get the {@link Codec<ForgingRecipe> Forging Recipe Codec}
     *
     * @return The {@link Codec<ForgingRecipe> Forging Recipe Codec}
     */
    public @NotNull Codec<ForgingRecipe> codec() {
        return CODEC;
    }

}