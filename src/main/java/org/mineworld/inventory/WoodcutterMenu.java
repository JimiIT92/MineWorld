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
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlocks;
import org.mineworld.core.MWMenuTypes;
import org.mineworld.core.MWRecipeTypes;
import org.mineworld.recipe.WoodcutterRecipe;

import java.util.List;

/**
 * {@link MineWorld MineWorld} {@link AbstractContainerMenu Woodcutter Menu}
 */
public class WoodcutterMenu extends AbstractContainerMenu {

    /**
     * {@link ContainerLevelAccess The Container Level Access reference}
     */
    private final ContainerLevelAccess containerLevelAccess;
    /**
     * {@link DataSlot The selected Recipe index}
     */
    private final DataSlot selectedRecipeIndex = DataSlot.standalone();
    /**
     * {@link Level The level reference}
     */
    private final Level level;
    /**
     * {@link List<WoodcutterRecipe> The Woodcutter Recipes}
     */
    private List<RecipeHolder<WoodcutterRecipe>> recipes = Lists.newArrayList();
    /**
     * {@link ItemStack The Input Slot Item Stack}
     */
    private ItemStack input = ItemStack.EMPTY;
    /**
     * {@link Long The last Sound Timestamp}
     */
    private long lastSoundTime;
    /**
     * {@link Slot The Input Slot}
     */
    private final Slot inputSlot;
    /**
     * {@link Slot The Output Slot}
     */
    private final Slot resultSlot;
    /**
     * {@link Runnable The Slot Update Listener}
     */
    private Runnable slotUpdateListener = () -> { };

    /**
     * {@link Container The Woodcutter Container}
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
     * {@link ResultContainer The Result Container}
     */
    private final ResultContainer resultContainer = new ResultContainer();

    /**
     * Constructor. Sets the {@link AbstractContainerMenu Menu Properties}
     *
     * @param id {@link Integer The Menu Id}
     * @param inventory {@link Inventory The Menu Inventory}
     * @param buffer {@link FriendlyByteBuf The Menu Byte Buffer}
     */
    public WoodcutterMenu(final int id, final Inventory inventory, final FriendlyByteBuf buffer) {
        this(id, inventory);
    }

    /**
     * Constructor. Sets the {@link AbstractContainerMenu Menu Properties}
     *
     * @param id {@link Integer The Menu Id}
     * @param inventory {@link Inventory The Menu Inventory}
     */
    public WoodcutterMenu(final int id, final Inventory inventory) {
        this(id, inventory, ContainerLevelAccess.NULL);
    }

    /**
     * Constructor. Sets the {@link AbstractContainerMenu Menu Properties}
     *
     * @param id {@link Integer The Menu Id}
     * @param inventory {@link Inventory The Menu Inventory}
     * @param containerLevelAccess {@link ContainerLevelAccess The Container Level Access reference}
     */
    public WoodcutterMenu(final int id, final Inventory inventory, final ContainerLevelAccess containerLevelAccess) {
        super(MWMenuTypes.WOODCUTTER.get(), id);
        this.containerLevelAccess = containerLevelAccess;
        this.level = inventory.player.level();
        this.inputSlot = this.addSlot(new Slot(this.container, 0, 20, 33));
        this.resultSlot = this.addSlot(new Slot(this.resultContainer, 1, 143, 33) {
            /**
             * Check if an {@link ItemStack Item Stack} can be placed into the Slot
             *
             * @param itemStack {@link ItemStack The Item Stack to place inside the Slot}
             * @return {@link Boolean#FALSE False}
             */
            public boolean mayPlace(final @NotNull ItemStack itemStack) {
                return false;
            }

            /**
             * Update the Menu when an {@link ItemStack Item Stack} is taken by the {@link Player Player}
             *
             * @param player {@link Player The Player taking the Item}
             * @param itemStack {@link ItemStack The Item taken}
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

            /**
             * Get the {@link ItemStack relevant Items} for the Slot
             *
             * @return {@link ItemStack The Slot relevant Items}
             */
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
     * Get the Menu {@link RecipeType Recipe Type}
     *
     * @return {@link MWRecipeTypes#WOODCUTTING The Woodcutting Recipe Type}
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
     * Get the Menu Recipes
     *
     * @return {@link List<WoodcutterRecipe> The Woodcutter Recipes}
     */
    public List<RecipeHolder<WoodcutterRecipe>> getRecipes() {
        return this.recipes;
    }

    /**
     * Get the {@link Integer number of Recipes}
     *
     * @return {@link Integer The number of Recipes}
     */
    public int getNumRecipes() {
        return this.recipes.size();
    }

    /**
     * Check if the Menu has an {@link ItemStack Input Item}
     *
     * @return {@link Boolean True if the Input Slot has an Item with a recipe associated}
     */
    public boolean hasInputItem() {
        return this.inputSlot.hasItem() && this.hasRecipes();
    }

    /**
     * Check if a {@link Player Player} can still interact with the {@link AbstractContainerMenu Menu}
     *
     * @param player {@link Player The Player interacting with the Menu}
     * @return {@link Boolean True if the Menu can still be interacted}
     */
    public boolean stillValid(final @NotNull Player player) {
        return stillValid(this.containerLevelAccess, player, MWBlocks.WOODCUTTER.get());
    }

    /**
     * Handle the click of a Menu Slot
     *
     * @param player {@link Player The Player clicking the Slot}
     * @param slotId {@link Integer The Slot Id}
     * @return {@link Boolean#TRUE True}
     */
    public boolean clickMenuButton(final @NotNull Player player, final int slotId) {
        if (this.isValidRecipeIndex(slotId)) {
            this.selectedRecipeIndex.set(slotId);
            this.setupResultSlot();
        }
        return true;
    }

    /**
     * Check if the Menu has some Recipes
     *
     * @return {@link Boolean True if the Menu has some Recipes}
     */
    private boolean hasRecipes() {
        return !this.recipes.isEmpty();
    }

    /**
     * Check if the {@link Integer Recipe index} is valid
     *
     * @param index {@link Integer The Recipe index}
     * @return {@link Boolean True if is positive and less than the Recipes number}
     */
    private boolean isValidRecipeIndex(int index) {
        return index >= 0 && index < this.getNumRecipes();
    }

    /**
     * Handle the change of a Slot inside the Menu
     *
     * @param container {@link Container The Menu container}
     */
    public void slotsChanged(final @NotNull Container container) {
        final ItemStack inputStack = this.inputSlot.getItem();
        if (!inputStack.is(this.input.getItem())) {
            this.input = inputStack.copy();
            this.setupRecipeList(container, inputStack);
        }
    }

    /**
     * Setup the Recipes related to the provided {@link ItemStack Item Stack}
     *
     * @param container {@link Container The Menu Container}
     * @param itemStack {@link ItemStack The Recipe Item Stack}
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
     * Setup the Result Slot
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
     * Get the {@link MenuType Menu Type}
     *
     * @return {@link MWMenuTypes#WOODCUTTER The Woodcutter Menu Type}
     */
    public @NotNull MenuType<?> getType() {
        return MWMenuTypes.WOODCUTTER.get();
    }

    /**
     * Register the {@link Runnable Container Update Listener}
     *
     * @param runnable {@link Runnable The Container Update Listener}
     */
    public void registerUpdateListener(final Runnable runnable) {
        this.slotUpdateListener = runnable;
    }

    /**
     * Check if an {@link ItemStack Item Stack} can be quickly taken from a {@link Slot Slot}
     *
     * @param itemStack {@link ItemStack The Item Stack to take}
     * @param slot {@link Slot The Menu Slot}
     * @return {@link Boolean True if the Item Stack can be quickly taken}
     */
    public boolean canTakeItemForPickAll(final @NotNull ItemStack itemStack, final Slot slot) {
        return slot.container != this.resultContainer && super.canTakeItemForPickAll(itemStack, slot);
    }

    /**
     * Quickly move an {@link ItemStack Item Stack} into or from a Slot
     *
     * @param player {@link Player The Player moving the Item Stack}
     * @param slotId {@link Integer The Id of the Slot to move into or from}
     * @return {@link ItemStack The moved Item Stack}
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
     * Clear the {@link Container Menu Container} when its closed
     *
     * @param player {@link Player The Player interacting with the Menu}
     */
    public void removed(final @NotNull Player player) {
        super.removed(player);
        this.resultContainer.removeItemNoUpdate(1);
        this.containerLevelAccess.execute((p_40313_, p_40314_) -> this.clearContainer(player, this.container));
    }

}