package org.mineworld.recipe;

import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlocks;
import org.mineworld.core.MWItems;
import org.mineworld.core.MWRecipeSerializers;

/**
 * {@link MineWorld MineWorld} {@link CustomRecipe Gift Recipe}
 */
public class GiftRecipe extends CustomRecipe {

    /**
     * Constructor. Set the {@link Recipe Recipe Properties}
     *
     * @param category {@link CraftingBookCategory The Crafting Book Category}
     */
    public GiftRecipe(final CraftingBookCategory category) {
        super(category);
    }

    /**
     * Check if the {@link Container Container} {@link Ingredient Ingredients} matches a {@link Recipe Recipe}
     *
     * @param container {@link Container The Recipe Container}
     * @param level {@link Level The level reference}
     * @return {@link Boolean True if the Ingredients matches a Recipe}
     */
    @Override
    public boolean matches(final @NotNull CraftingContainer container, final @NotNull Level level) {
        if(container.getItems().size() < 9) {
            return false;
        }
        for (int i = 0; i < 9; ++i) {
            final ItemStack item = container.getItem(i);
            if(i == 4) {
                if(item.is(MWItems.GIFT.get())) {
                    return false;
                }
            }
            else if(!(item.is(ItemTags.WOOL))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get the {@link ItemStack Recipe result} based on the matched Recipe
     *
     * @param container {@link Container The container for the Recipe}
     * @param registryAccess {@link RegistryAccess The Registry access}
     * @return {@link ItemStack The Recipe result}
     */
    @Override
    public @NotNull ItemStack assemble(final @NotNull CraftingContainer container, final @NotNull RegistryAccess registryAccess) {
        final ItemStack gift = MWBlocks.GIFT.get().asItem().getDefaultInstance();
        final ItemStack content = container.getItems().size() >= 5 ? container.getItem(4) : ItemStack.EMPTY;
        if(!content.isEmpty()) {
            CompoundTag tag = gift.getOrCreateTag();
            ListTag items = new ListTag();
            CompoundTag itemTag = new CompoundTag();
            itemTag.putByte("Slot", (byte)0);
            content.save(itemTag);
            items.add(itemTag);
            tag.put("Items", items);
            gift.setTag(tag);
        }
        return gift;
    }

    /**
     * Get the crafting {@link NonNullList<ItemStack> remaining Items}
     *
     * @param container {@link CraftingContainer The Crafting Container}
     * @return {@link NonNullList<ItemStack> The crafting remaining Items}
     */
    public @NotNull NonNullList<ItemStack> getRemainingItems(final CraftingContainer container) {
        container.setItem(4, ItemStack.EMPTY);
        final NonNullList<ItemStack> remainingItems = NonNullList.withSize(container.getContainerSize(), ItemStack.EMPTY);

        for(int i = 0; i < remainingItems.size(); ++i) {
            ItemStack itemStack = container.getItem(i);
            if (itemStack.hasCraftingRemainingItem()) {
                remainingItems.set(i, itemStack.getCraftingRemainingItem());
            }
        }

        return remainingItems;
    }

    /**
     * Check if the crafting container is of the correct size
     *
     * @param width {@link Integer The crafting container width}
     * @param height {@link Integer The crafting container height}
     * @return {@link Boolean True if the crafting container is of the correct size}
     */
    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width >= 3 && height >= 3;
    }

    /**
     * Get the {@link RecipeSerializer Recipe Serializer}
     *
     * @return {@link RecipeSerializer The Recipe Serializer}
     */
    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return MWRecipeSerializers.GIFT.get();
    }

}