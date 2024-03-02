package org.mineworld.recipe;

import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
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
 * {@link MineWorld MineWorld} {@link SmithingRecipe Forging Recipe}
 */
public record ForgingRecipe(Ingredient base, Ingredient addition, ItemStack result, int forgingTime, float experience) implements SmithingRecipe {

    /**
     * Check if the {@link Container Container} {@link Ingredient Ingredients} matches a {@link Recipe Recipe}
     *
     * @param container {@link Container The Recipe Container}
     * @param level {@link Level The level reference}
     * @return {@link Boolean True if the Ingredients matches a Recipe}
     */
    @Override
    public boolean matches(final Container container, final @NotNull Level level) {
        return isIngredient(container.getItem(0)) || isIngredient(container.getItem(1));
    }

    /**
     * Get the {@link ItemStack Recipe result} based on the matched Recipe
     *
     * @param container {@link Container The container for the Recipe}
     * @param registryAccess {@link RegistryAccess The Registry access}
     * @return {@link ItemStack The Recipe result}
     */
    @Override
    public @NotNull ItemStack assemble(final Container container, final @NotNull RegistryAccess registryAccess) {
        final ItemStack itemStack = this.result.copy();
        final CompoundTag nbt = container.getItem(0).getTag();
        if (nbt != null) {
            itemStack.setTag(nbt.copy());
        }
        return itemStack;
    }

    /**
     * Check if the crafting container is of the correct size
     *
     * @param width {@link Integer The crafting container width}
     * @param height {@link Integer The crafting container height}
     * @return {@link Boolean True if the crafting container is of the correct size}
     */
    @Override
    public boolean canCraftInDimensions(final int width, final int height) {
        return width * height >= 2;
    }

    /**
     * Get the {@link ItemStack Recipe result}
     *
     * @param registryAccess {@link RegistryAccess The Registry access}
     * @return {@link ItemStack The Recipe result}
     */
    @Override
    public @NotNull ItemStack getResultItem(final @NotNull RegistryAccess registryAccess) {
        return this.result();
    }

    /**
     * Check if an {@link Ingredient Ingredient} is a Smithing Template
     *
     * @param itemStack {@link ItemStack The current Ingredient}
     * @return {@link Boolean#FALSE False}
     */
    @Override
    public boolean isTemplateIngredient(final @NotNull ItemStack itemStack) {
        return false;
    }

    /**
     * Check if an {@link ItemStack Ingredient} corresponds to a {@link Ingredient Recipe Base Ingredient}
     *
     * @param itemStack {@link ItemStack The current Ingredient}
     * @return {@link Boolean True if the Item is a Recipe Base Ingredient}
     */
    @Override
    public boolean isBaseIngredient(final @NotNull ItemStack itemStack) {
        return isIngredient(itemStack);
    }

    /**
     * Check if an {@link ItemStack Ingredient} corresponds to a {@link Ingredient Recipe Addition Ingredient}
     *
     * @param itemStack {@link ItemStack The current Ingredient}
     * @return {@link Boolean True if the Item is a Recipe Addition Ingredient}
     */
    @Override
    public boolean isAdditionIngredient(final @NotNull ItemStack itemStack) {
        return isIngredient(itemStack);
    }

    /**
     * Check if an {@link ItemStack Ingredient} corresponds to a {@link Ingredient Recipe Ingredient}
     *
     * @param itemStack {@link ItemStack The current Ingredient}
     * @return {@link Boolean True if the Item is a Recipe Ingredient}
     */
    private boolean isIngredient(final ItemStack itemStack) {
        return this.base.test(itemStack) || this.addition.test(itemStack);
    }

    /**
     * Get the {@link RecipeSerializer Recipe Serializer}
     *
     * @return {@link RecipeSerializer The Recipe Serializer}
     */
    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return MWRecipeSerializers.FORGING.get();
    }

    /**
     * Get the {@link ItemStack Item Stack} to display on Recipe Toasts Notifications
     *
     * @return {@link MWBlocks#FORGING_TABLE The Forging Table Item Stack}
     */
    @Override
    public @NotNull ItemStack getToastSymbol() {
        return new ItemStack(MWBlocks.FORGING_TABLE.get());
    }

    /**
     * Check if the {@link Recipe Recipe} is incomplete
     *
     * @return {@link Boolean True if there is a missing Ingredient}
     */
    @Override
    public boolean isIncomplete() {
        return Stream.of(this.base, this.addition).anyMatch(ingredient -> ForgeHooks.hasNoElements(ingredient));
    }

    /**
     * Get the {@link RecipeType Recipe Type}
     *
     * @return {@link MWRecipeTypes#FORGING The Forging Table Recipe Type}
     */
    @Override
    public @NotNull RecipeType<?> getType() {
        return MWRecipeTypes.FORGING.get();
    }

}