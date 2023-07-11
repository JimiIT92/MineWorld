package org.mineworld.recipe;

import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeHooks;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlocks;
import org.mineworld.core.MWRecipeSerializers;
import org.mineworld.core.MWRecipeTypes;

import java.util.stream.Stream;

/**
 * Record class for a {@link MineWorld MineWorld} forging recipe
 */
public record ForgingRecipe(ResourceLocation id, Ingredient base, Ingredient addition, ItemStack result, int forgingTime, float experience) implements SmithingRecipe {

    /**
     * Check if some ingredients matches a recipe
     *
     * @param container {@link Container The container with the ingredients}
     * @param level {@link Level The level reference}
     * @return {@link Boolean True if the ingredients matches some recipe}
     */
    @Override
    public boolean matches(Container container, @NotNull Level level) {
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
    public @NotNull ItemStack assemble(Container container, @NotNull RegistryAccess registryAccess) {
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
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    /**
     * Get the {@link ItemStack recipe result}
     *
     * @param registryAccess {@link RegistryAccess The registry access}
     * @return {@link ItemStack The recipe result}
     */
    @Override
    public @NotNull ItemStack getResultItem(@NotNull RegistryAccess registryAccess) {
        return this.result();
    }

    /**
     * Check if an ingredient is a smithing template
     *
     * @param itemStack {@link ItemStack The ingredient item stack}
     * @return {@link Boolean False}
     */
    @Override
    public boolean isTemplateIngredient(@NotNull ItemStack itemStack) {
        return false;
    }

    /**
     * Check if an {@link ItemStack ingredient} corresponds to a base ingredient
     *
     * @param itemStack {@link ItemStack The ingredient}
     * @return {@link Boolean True if the item stack corresponds to a base or addition ingredient}
     */
    @Override
    public boolean isBaseIngredient(@NotNull ItemStack itemStack) {
        return isIngredient(itemStack);
    }

    /**
     * Check if an {@link ItemStack ingredient} corresponds to an addition ingredient
     *
     * @param itemStack {@link ItemStack The ingredient}
     * @return {@link Boolean True if the item stack corresponds to a base or addition ingredient}
     */
    @Override
    public boolean isAdditionIngredient(@NotNull ItemStack itemStack) {
        return isIngredient(itemStack);
    }

    /**
     * Check if an {@link ItemStack ingredient} corresponds to an ingredient
     *
     * @param itemStack {@link ItemStack The ingredient}
     * @return {@link Boolean True if the item stack corresponds to a base or addition ingredient}
     */
    private boolean isIngredient(ItemStack itemStack) {
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

}