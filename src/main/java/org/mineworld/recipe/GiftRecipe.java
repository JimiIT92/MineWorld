package org.mineworld.recipe;

import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlocks;
import org.mineworld.core.MWItems;
import org.mineworld.core.MWRecipeSerializers;
import org.mineworld.helper.ItemHelper;

/**
 * Implementation class for a {@link MineWorld MineWorld} gift recipe
 */
public class GiftRecipe extends CustomRecipe {

    /**
     * Constructor. Set the {@link CraftingBookCategory crafting book category}
     *
     * @param category {@link CraftingBookCategory The crafting book category}
     */
    public GiftRecipe(final CraftingBookCategory category) {
        super(category);
    }

    /**
     * Check if some ingredients matches a recipe
     *
     * @param container {@link Container The container with the ingredients}
     * @param level {@link Level The level reference}
     * @return {@link Boolean True if the ingredients matches some recipe}
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
     * Get the {@link ItemStack recipe result} based on the matched recipe
     *
     * @param container {@link CraftingContainer The crafting container for the recipe}
     * @param registryAccess {@link RegistryAccess The registry access}
     * @return {@link ItemStack The recipe result}
     */
    @Override
    public @NotNull ItemStack assemble(final @NotNull CraftingContainer container, final @NotNull RegistryAccess registryAccess) {
        final ItemStack gift = ItemHelper.getDefaultStack(MWBlocks.GIFT.get());
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

    public @NotNull NonNullList<ItemStack> getRemainingItems(final CraftingContainer container) {
        container.setItem(4, ItemStack.EMPTY);
        NonNullList<ItemStack> nonnulllist = NonNullList.withSize(container.getContainerSize(), ItemStack.EMPTY);

        for(int i = 0; i < nonnulllist.size(); ++i) {
            ItemStack itemstack = container.getItem(i);
            if (itemstack.hasCraftingRemainingItem()) {
                nonnulllist.set(i, itemstack.getCraftingRemainingItem());
            }
        }

        return nonnulllist;
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
        return width >= 3 && height >= 3;
    }

    /**
     * Get the {@link RecipeSerializer recipe serializer}
     *
     * @return {@link RecipeSerializer The recipe serializer}
     */
    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return MWRecipeSerializers.GIFT.get();
    }

}