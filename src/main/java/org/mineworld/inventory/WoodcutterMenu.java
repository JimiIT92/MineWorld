package org.mineworld.inventory;

import com.google.common.collect.Lists;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.mineworld.block.WoodcutterBlock;
import org.mineworld.core.MWBlocks;
import org.mineworld.core.MWMenuTypes;
import org.mineworld.core.MWRecipeTypes;
import org.mineworld.recipe.WoodcutterRecipe;

import java.util.List;

/**
 * Implementation class for the {@link WoodcutterBlock woodcutter menu}
 */
public class WoodcutterMenu extends AbstractContainerMenu {

    /**
     * {@link ContainerLevelAccess The container level access reference}
     */
    private final ContainerLevelAccess containerLevelAccess;
    /**
     * {@link DataSlot The selected recipe index}
     */
    private final DataSlot selectedRecipeIndex = DataSlot.standalone();
    /**
     * {@link Level The level reference}
     */
    private final Level level;
    /**
     * {@link List<WoodcutterRecipe> The woodcutter recipes}
     */
    private List<RecipeHolder<WoodcutterRecipe>> recipes = Lists.newArrayList();
    /**
     * {@link ItemStack The input slot id stack}
     */
    private ItemStack input = ItemStack.EMPTY;
    /**
     * {@link Long The last sound timestamp}
     */
    private long lastSoundTime;
    /**
     * {@link Slot The input slot}
     */
    private final Slot inputSlot;
    /**
     * {@link Slot The output slot}
     */
    private final Slot resultSlot;
    /**
     * {@link Runnable The slot update listener}
     */
    private Runnable slotUpdateListener = () -> { };

    /**
     * {@link Container The woodcutter container}
     */
    public final Container container = new SimpleContainer(1) {

        /**
         * Update the result on changes
         */
        public void setChanged() {
            super.setChanged();
            WoodcutterMenu.this.slotsChanged(this);
            WoodcutterMenu.this.slotUpdateListener.run();
        }

    };

    /**
     * {@link ResultContainer The result container}
     */
    private final ResultContainer resultContainer = new ResultContainer();

    /**
     * Forge Constructor. Sets the screen default properties
     *
     * @param id {@link Integer The screen id}
     * @param inventory {@link Inventory The screen inventory}
     * @param buffer {@link FriendlyByteBuf The screen byte buffer}
     */
    public WoodcutterMenu(final int id, final Inventory inventory, final FriendlyByteBuf buffer) {
        this(id, inventory);
    }

    /**
     * Constructor. Set the {@link Integer screen id} and the {@link Inventory inventory}
     *
     * @param id {@link Integer The screen id}
     * @param inventory {@link Inventory The screen inventory}
     */
    public WoodcutterMenu(final int id, final Inventory inventory) {
        this(id, inventory, ContainerLevelAccess.NULL);
    }

    /**
     * Constructor. Set the screen properties
     *
     * @param id {@link Integer The screen id}
     * @param inventory {@link Inventory The screen inventory}
     * @param containerLevelAccess {@link ContainerLevelAccess The container level access reference}
     */
    public WoodcutterMenu(final int id, final Inventory inventory, final ContainerLevelAccess containerLevelAccess) {
        super(MWMenuTypes.WOODCUTTER.get(), id);
        this.containerLevelAccess = containerLevelAccess;
        this.level = inventory.player.level();
        this.inputSlot = this.addSlot(new Slot(this.container, 0, 20, 33));
        this.resultSlot = this.addSlot(new Slot(this.resultContainer, 1, 143, 33) {

            /**
             * Check if an {@link ItemStack id stack} can be placed into the slot
             *
             * @param itemStack {@link ItemStack The id stack to place inside the slot}
             * @return {@link Boolean False}
             */
            public boolean mayPlace(final @NotNull ItemStack itemStack) {
                return false;
            }

            /**
             * Update the screen on {@link ItemStack result} taken by the {@link Player player}
             *
             * @param player {@link Player The palyer taking the result}
             * @param itemStack {@link ItemStack The recipe resuòt}
             */
            public void onTake(final @NotNull Player player, final @NotNull ItemStack itemStack) {
                itemStack.onCraftedBy(player.level(), player, itemStack.getCount());
                WoodcutterMenu.this.resultContainer.awardUsedRecipes(player, this.getRelevantItems());
                final ItemStack inputStack = WoodcutterMenu.this.inputSlot.remove(1);
                if (!inputStack.isEmpty()) {
                    WoodcutterMenu.this.setupResultSlot();
                }

                containerLevelAccess.execute((level, blockPos) -> {
                    final long gameTime = level.getGameTime();
                    if (WoodcutterMenu.this.lastSoundTime != gameTime) {
                        level.playSound(null, blockPos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
                        WoodcutterMenu.this.lastSoundTime = gameTime;
                    }
                });
                super.onTake(player, itemStack);
            }

            private List<ItemStack> getRelevantItems() {
                return List.of(WoodcutterMenu.this.inputSlot.getItem());
            }

        });

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(inventory, k, 8 + k * 18, 142));
        }

        this.addDataSlot(this.selectedRecipeIndex);
    }

    /**
     * Get the screen {@link RecipeType recipe type}
     *
     * @return {@link MWRecipeTypes#WOODCUTTING The woodcutting recipe type}
     */
    public RecipeType<WoodcutterRecipe> getRecipeType() {
        return MWRecipeTypes.WOODCUTTING.get();
    }

    /**
     * Get the {@link Integer selected recipe index}
     *
     * @return {@link Integer The selected recipe index}
     */
    public int getSelectedRecipeIndex() {
        return this.selectedRecipeIndex.get();
    }

    /**
     * Get the {@link List<WoodcutterRecipe> woodcutter recipes}
     *
     * @return {@link List<WoodcutterRecipe> The woodcutter recipes}
     */
    public List<RecipeHolder<WoodcutterRecipe>> getRecipes() {
        return this.recipes;
    }

    /**
     * Get the {@link Integer number of recipes}
     *
     * @return {@link Integer The number of recipes}
     */
    public int getNumRecipes() {
        return this.recipes.size();
    }

    /**
     * Check if the screen has an input id
     *
     * @return {@link Boolean True if the input slot has an id and a recipe associated}
     */
    public boolean hasInputItem() {
        return this.inputSlot.hasItem() && this.hasRecipes();
    }

    /**
     * Check if the screen can still be displayed to the {@link Player player}
     *
     * @param player {@link Player The player using the screen}
     * @return {@link Boolean True if the screen can still be displayed}
     */
    public boolean stillValid(final @NotNull Player player) {
        return stillValid(this.containerLevelAccess, player, MWBlocks.WOODCUTTER.get());
    }

    /**
     * Handle the clicks of a button inside the screen
     *
     * @param player {@link Player The player clicking the button}
     * @param slotId {@link Integer The button id}
     * @return {@link Boolean True}
     */
    public boolean clickMenuButton(final @NotNull Player player, final int slotId) {
        if (this.isValidRecipeIndex(slotId)) {
            this.selectedRecipeIndex.set(slotId);
            this.setupResultSlot();
        }
        return true;
    }

    /**
     * Check if there are recipes
     *
     * @return {@link Boolean True if the recipe list is not empty}
     */
    private boolean hasRecipes() {
        return !this.recipes.isEmpty();
    }

    /**
     * Check if the {@link Integer index} is a valid recipe index
     *
     * @param index {@link Integer The index to check}
     * @return {@link Boolean True if is positive and less than the recipes number}
     */
    private boolean isValidRecipeIndex(int index) {
        return index >= 0 && index < this.getNumRecipes();
    }

    /**
     * Handle the change of a slot inside the screen
     *
     * @param container {@link Container The screen container}
     */
    public void slotsChanged(final @NotNull Container container) {
        final ItemStack inputStack = this.inputSlot.getItem();
        if (!inputStack.is(this.input.getItem())) {
            this.input = inputStack.copy();
            this.setupRecipeList(container, inputStack);
        }
    }

    /**
     * Setup the recipes associated with the provided {@link ItemStack id stack}
     *
     * @param container {@link Container The screen container}
     * @param itemStack {@link ItemStack The input id stack}
     */
    private void setupRecipeList(final Container container, final ItemStack itemStack) {
        this.recipes.clear();
        this.selectedRecipeIndex.set(-1);
        this.resultSlot.set(ItemStack.EMPTY);
        if (!itemStack.isEmpty()) {
            this.recipes = this.level.getRecipeManager().getRecipesFor(this.getRecipeType(), container, this.level);
        }
    }

    /**
     * Setup the result slot
     */
    void setupResultSlot() {
        if (this.hasRecipes() && this.isValidRecipeIndex(this.selectedRecipeIndex.get())) {
            final RecipeHolder<WoodcutterRecipe> recipe = this.recipes.get(this.selectedRecipeIndex.get());
            final ItemStack result = recipe.value().assemble(this.container, this.level.registryAccess());
            if (result.isItemEnabled(this.level.enabledFeatures())) {
                this.resultContainer.setRecipeUsed(recipe);
                this.resultSlot.set(result);
            } else {
                this.resultSlot.set(ItemStack.EMPTY);
            }
        } else {
            this.resultSlot.set(ItemStack.EMPTY);
        }
        this.broadcastChanges();
    }

    /**
     * Get the {@link MenuType menu type}
     *
     * @return {@link MenuType#STONECUTTER The woodcutter menu type}
     */
    public @NotNull MenuType<?> getType() {
        return MWMenuTypes.WOODCUTTER.get();
    }

    /**
     * Register the {@link Runnable update listener}
     *
     * @param runnable {@link Runnable The update listener}
     */
    public void registerUpdateListener(final Runnable runnable) {
        this.slotUpdateListener = runnable;
    }

    /**
     * Check if an {@link ItemStack id stack} can be quickly taken from a {@link Slot slot}
     *
     * @param itemStack {@link ItemStack The id stack to take}
     * @param slot {@link Slot The slot to take the id stack from}
     * @return {@link Boolean True if the id stack can be quickly taken}
     */
    public boolean canTakeItemForPickAll(final @NotNull ItemStack itemStack, final Slot slot) {
        return slot.container != this.resultContainer && super.canTakeItemForPickAll(itemStack, slot);
    }

    /**
     * Quickly move an {@link ItemStack id stack} into or from a slot
     *
     * @param player {@link Player The player moving the id stack}
     * @param slotId {@link Integer The id of the slot to move into or from}
     * @return {@link ItemStack The moved id stack}
     */
    public @NotNull ItemStack quickMoveStack(final @NotNull Player player, final int slotId) {
        ItemStack itemStack = ItemStack.EMPTY;
        final Slot slot = this.slots.get(slotId);
        if (slot.hasItem()) {
            final ItemStack result = slot.getItem();
            itemStack = result.copy();
            if (slotId == 1) {
                result.getItem().onCraftedBy(result, player.level(), player);
                if (!this.moveItemStackTo(result, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(result, itemStack);
            } else if (slotId == 0) {
                if (!this.moveItemStackTo(result, 2, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.level.getRecipeManager().getRecipeFor(this.getRecipeType(), new SimpleContainer(result), this.level).isPresent()) {
                if (!this.moveItemStackTo(result, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (slotId >= 2 && slotId < 29) {
                if (!this.moveItemStackTo(result, 29, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (slotId >= 29 && slotId < 38 && !this.moveItemStackTo(result, 2, 29, false)) {
                return ItemStack.EMPTY;
            }

            if (result.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            }

            slot.setChanged();
            if (result.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, result);
            this.broadcastChanges();
        }

        return itemStack;
    }

    /**
     * Clear the {@link Container screen container} on screen removed
     *
     * @param player {@link Player The player interacting with the screen}
     */
    public void removed(final @NotNull Player player) {
        super.removed(player);
        this.resultContainer.removeItemNoUpdate(1);
        this.containerLevelAccess.execute((p_40313_, p_40314_) -> this.clearContainer(player, this.container));
    }

}