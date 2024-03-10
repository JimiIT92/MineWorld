package org.mineworld.recipe.serializer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.recipe.ForgingRecipe;

/**
 * {@link MineWorld MineWorld} {@link RecipeSerializer Forging Recipe Serializer}
 */
public class ForgingRecipeSerializer implements RecipeSerializer<ForgingRecipe> {

    /**
     * {@link MapCodec<ForgingRecipe> The Recipe Result Codec}
     */
    private static final Codec<ForgingRecipe> CODEC = RecordCodecBuilder.create(builder ->
            builder.group(Ingredient.CODEC.fieldOf("base").forGetter(recipe -> recipe.base()),
                    Ingredient.CODEC.fieldOf("addition").forGetter(recipe -> recipe.addition()),
                    ItemStack.ITEM_WITH_COUNT_CODEC.fieldOf("result").forGetter(recipe -> recipe.result()),
                    Codec.INT.fieldOf("forging_time").forGetter(recipe -> recipe.forgingTime()),
                    Codec.FLOAT.fieldOf("experience").forGetter(recipe -> recipe.experience())
            ).apply(builder, ForgingRecipe::new));

    /**
     * Deserialize a {@link Recipe Recipe} from the {@link FriendlyByteBuf Buffer}
     *
     * @param byteBuffer {@link FriendlyByteBuf The Network Buffer}
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
     * Serialize a {@link Recipe Recipe} to the {@link FriendlyByteBuf Buffer}
     *
     * @param byteBuffer {@link FriendlyByteBuf The Network Buffer}
     * @param recipe {@link ForgingRecipe The Recipe to serialize}
     */
    public void toNetwork(final @NotNull FriendlyByteBuf byteBuffer, final ForgingRecipe recipe) {
        recipe.base().toNetwork(byteBuffer);
        recipe.addition().toNetwork(byteBuffer);
        byteBuffer.writeItem(recipe.result());
        byteBuffer.writeInt(recipe.forgingTime());
        byteBuffer.writeFloat(recipe.experience());
    }

    /**
     * Get the {@link Codec<ForgingRecipe> Codec instance}
     *
     * @return {@link Codec<ForgingRecipe> The Codec instance}
     */
    public @NotNull Codec<ForgingRecipe> codec() {
        return CODEC;
    }

}