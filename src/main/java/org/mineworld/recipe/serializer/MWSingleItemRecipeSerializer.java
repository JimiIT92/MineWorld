package org.mineworld.recipe.serializer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;

/**
 * {@link MineWorld MineWorld} {@link RecipeSerializer Single Item Recipe Serializer}
 * @param <T>
 */
public class MWSingleItemRecipeSerializer<T extends SingleItemRecipe> implements RecipeSerializer<T> {

    /**
     * {@link IMWSingleItemMaker<T> The Recipe Item Maker Factory}
     */
    private final IMWSingleItemMaker<T> factory;
    /**
     * {@link Codec<T> The Recipe Codec}
     */
    private final Codec<T> codec;
    /**
     * {@link MapCodec<ItemStack> The Recipe Result Codec}
     */
    private static final MapCodec<ItemStack> RESULT_CODEC = RecordCodecBuilder.mapCodec(builder -> builder.group(
            BuiltInRegistries.ITEM.byNameCodec().fieldOf("result").forGetter(ItemStack::getItem),
            Codec.INT.optionalFieldOf("count", 1).forGetter(ItemStack::getCount)
    ).apply(builder, ItemStack::new));

    /**
     * Constructor. Set the {@link RecipeSerializer Recipe Serializer Properties}
     *
     * @param factory {@link IMWSingleItemMaker<T> The Recipe Item Maker Factory}
     */
    public MWSingleItemRecipeSerializer(final IMWSingleItemMaker<T> factory) {
        this.factory = factory;
        this.codec = RecordCodecBuilder.create((builder) -> builder.group(
                ExtraCodecs.strictOptionalField(Codec.STRING, "group", "").forGetter(recipe -> recipe.group),
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(recipe -> recipe.ingredient),
                RESULT_CODEC.forGetter(recipe -> recipe.result)
        ).apply(builder, factory::create));
    }

    /**
     * Get the {@link Codec<T> Codec instance}
     *
     * @return {@link Codec<T> The Codec instance}
     */
    @Override
    public @NotNull Codec<T> codec() {
        return codec;
    }

    /**
     * Deserialize a {@link Recipe Recipe} from the {@link FriendlyByteBuf Buffer}
     *
     * @param byteBuffer {@link FriendlyByteBuf The Network Buffer}
     * @return {@link T The deserialized Recipe}
     */
    public T fromNetwork(final @NotNull FriendlyByteBuf byteBuffer) {
        return this.factory.create(byteBuffer.readUtf(), Ingredient.fromNetwork(byteBuffer), byteBuffer.readItem());
    }

    /**
     * Serialize a {@link Recipe Recipe} to the {@link FriendlyByteBuf Buffer}
     *
     * @param byteBuffer {@link FriendlyByteBuf The Network Buffer}
     * @param recipe {@link T The Recipe to serialize}
     */
    public void toNetwork(final FriendlyByteBuf byteBuffer, final T recipe) {
        byteBuffer.writeUtf(recipe.group);
        recipe.ingredient.toNetwork(byteBuffer);
        byteBuffer.writeItem(recipe.result);
    }

    /**
     * Interface for a {@link SingleItemRecipe Single Item Recipe Maker}
     *
     * @param <T> {@link T The Recipe Type}
     */
    public interface IMWSingleItemMaker<T extends SingleItemRecipe> {

        /**
         * Create a {@link Recipe Recipe}
         *
         * @param group {@link String The Recipe Group}
         * @param ingredient {@link Ingredient The Recipe Ingredient}
         * @param result {@link ItemStack The Recipe Result}
         * @return {@link T The Recipe}
         */
        T create(final String group, final Ingredient ingredient, final ItemStack result);

    }

}