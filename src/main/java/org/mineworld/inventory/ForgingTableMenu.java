package org.mineworld.inventory;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.mineworld.block.ForgingTableBlock;
import org.mineworld.core.MWMenuTypes;
import org.mineworld.core.MWRecipeTypes;
import org.mineworld.entity.block.ForgingTableBlockEntity;
import org.mineworld.recipe.ForgingRecipe;

/**
 * Implementation class for the {@link ForgingTableBlock forging table menu}
 */
public class ForgingTableMenu extends AbstractContainerMenu {
    /**
     * {@link Integer The base ingredient slot id}
     */
    public static final int INGREDIENT_BASE_SLOT = 0;
    /**
     * {@link Integer The addition ingredient slot id}
     */
    public static final int INGREDIENT_ADDITION_SLOT = 1;
    /**
     * {@link Integer The fuel slot id}
     */
    public static final int FUEL_SLOT = 2;
    /**
     * {@link Integer The result slot id}
     */
    public static final int RESULT_SLOT = 3;
    /**
     * {@link Integer The number of slots}
     */
    public static final int SLOT_COUNT = 4;
    /**
     * {@link Integer The data values count}
     */
    public static final int DATA_COUNT = 4;
    /**
     * {@link Integer The initial inventory slot id}
     */
    private static final int INV_SLOT_START = 4;
    /**
     * {@link Integer The final inventory slot id}
     */
    private static final int INV_SLOT_END = 31;
    /**
     * {@link Integer The initial hotbar slot id}
     */
    private static final int USE_ROW_SLOT_START = 31;
    /**
     * {@link Integer The final hotbar slot id}
     */
    private static final int USE_ROW_SLOT_END = 40;
    /**
     * {@link Container The screen container}
     */
    private final Container container;
    /**
     * {@link ContainerData The screen container data}
     */
    private final ContainerData containerData;
    /**
     * {@link Level The level reference}
     */
    private final Level level;
    /**
     * {@link RecipeType<ForgingRecipe> The forging recipe type}
     */
    private final RecipeType<? extends ForgingRecipe> recipeType;

    /**
     * Forge Constructor. Sets the screen default properties
     *
     * @param id {@link Integer The screen id}
     * @param inventory {@link Inventory The screen inventory}
     * @param buffer {@link FriendlyByteBuf The screen byte buffer}
     */
    public ForgingTableMenu(final int id, final Inventory inventory, final FriendlyByteBuf buffer) {
        this(id, inventory);
    }

    /**
     * Constructor. Set the {@link Integer screen id} and the {@link Inventory inventory}
     *
     * @param id {@link Integer The screen id}
     * @param inventory {@link Inventory The screen inventory}
     */
    public ForgingTableMenu(final int id, final Inventory inventory) {
        this(id, inventory, new SimpleContainer(SLOT_COUNT), new SimpleContainerData(DATA_COUNT));
    }

    /**
     * Get the {@link Integer menu grid width}
     *
     * @return {@link Integer 1}
     */
    public int getGridWidth() {
        return 1;
    }

    /**
     * Get the {@link Integer menu grid height}
     *
     * @return {@link Integer 1}
     */
    public int getGridHeight() {
        return 1;
    }

    /**
     * Get the {@link Integer menu size}
     *
     * @return {@link #SLOT_COUNT 4}
     */
    public int getSize() {
        return SLOT_COUNT;
    }

    /**
     * Constructor. Set the screen properties
     *
     * @param id {@link Integer The screen id}
     * @param inventory {@link Inventory The screen inventory}
     * @param container {@link Container The screen container}
     * @param containerData {@link ContainerData The screen container data}
     */
    public ForgingTableMenu(final int id, final Inventory inventory, final Container container, final ContainerData containerData) {
        super(MWMenuTypes.FORGING_TABLE.get(), id);
        this.recipeType = MWRecipeTypes.FORGING.get();
        checkContainerSize(container, SLOT_COUNT);
        checkContainerDataCount(containerData, DATA_COUNT);
        this.container = container;
        this.containerData = containerData;
        this.level = inventory.player.level();
        this.addSlot(new Slot(container, INGREDIENT_BASE_SLOT, 41, 21));
        this.addSlot(new Slot(container, INGREDIENT_ADDITION_SLOT, 80, 21));
        this.addSlot(new Slot(container, FUEL_SLOT, 80, 51) {

            /**
             * Check if an item can be placed inside the slot
             *
             * @param itemStack {@link ItemStack The item stack to place}
             * @return {@link Boolean True if is a forging table fuel}
             */
            @Override
            public boolean mayPlace(final @NotNull ItemStack itemStack) {
                return ForgingTableBlockEntity.isFuel(itemStack);
            }
        });
        this.addSlot(new Slot(container, RESULT_SLOT, 131, 21) {

            /**
             * {@link Integer The removed item count}
             */
            private int removeCount;

            /**
             * Check if an item can be placed inside the slot
             *
             * @param itemStack {@link ItemStack The item stack to place}
             * @return {@link Boolean False}
             */
            @Override
            public boolean mayPlace(final @NotNull ItemStack itemStack) {
                return false;
            }

            /**
             * Remove an {@link ItemStack item} from the slot
             *
             * @param amount {@link Integer The amount of items to remove}
             * @return {@link ItemStack The removed item stack}
             */
            public @NotNull ItemStack remove(final int amount) {
                if (this.hasItem()) {
                    this.removeCount += Math.min(amount, this.getItem().getCount());
                }
                return super.remove(amount);
            }

            /**
             * Take an item from the slot
             *
             * @param player {@link Player The player taking the item}
             * @param itemStack {@link ItemStack The item to take from the slot}
             */
            public void onTake(final @NotNull Player player, final @NotNull ItemStack itemStack) {
                this.checkTakeAchievements(itemStack);
                super.onTake(player, itemStack);
            }

            /**
             * Quickly take an item into the slot
             *
             * @param itemStack {@link ItemStack The item stack to quickly take}
             * @param amount {@link Integer The amount of items to take}
             */
            protected void onQuickCraft(final @NotNull ItemStack itemStack, final int amount) {
                this.removeCount += amount;
                this.checkTakeAchievements(itemStack);
            }

            /**
             * Check if the {@link Player player} should be awarded with some awards
             *
             * @param itemStack {@link ItemStack The crafted item stack}
             */
            protected void checkTakeAchievements(final ItemStack itemStack) {
                final Player player = inventory.player;
                itemStack.onCraftedBy(player.level(), player, this.removeCount);
                if (player instanceof ServerPlayer && this.container instanceof ForgingTableBlockEntity) {
                    ((ForgingTableBlockEntity)this.container).awardUsedRecipesAndPopExperience((ServerPlayer)player);
                }
                this.removeCount = 0;
            }
        });

        for(int x = 0; x < 3; ++x) {
            for(int y = 0; y < 9; ++y) {
                this.addSlot(new Slot(inventory, y + x * 9 + 9, 8 + y * 18, 84 + x * 18));
            }
        }

        for(int x = 0; x < 9; ++x) {
            this.addSlot(new Slot(inventory, x, 8 + x * 18, 142));
        }

        this.addDataSlots(containerData);
    }

    /**
     * Quickly move an item stack
     *
     * @param player {@link Player The player interacting with the screen}
     * @param slotId {@link Integer The slot id}
     * @return {@link ItemStack The moved item stack}
     */
    @Override
    public @NotNull ItemStack quickMoveStack(final @NotNull Player player, final int slotId) {
        ItemStack result = ItemStack.EMPTY;
        final Slot slot = this.slots.get(slotId);
        if (slot.hasItem()) {
            final ItemStack slotItem = slot.getItem();
            result = slotItem.copy();
            if (slotId == RESULT_SLOT) {
                if (!this.moveItemStackTo(slotItem, INV_SLOT_START, INV_SLOT_END, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(slotItem, result);
            } else if (slotId != INGREDIENT_BASE_SLOT && slotId != INGREDIENT_ADDITION_SLOT && slotId != FUEL_SLOT) {
                if (this.canForge(slotItem)) {
                    if (!this.moveItemStackTo(slotItem, INGREDIENT_BASE_SLOT, FUEL_SLOT, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (ForgingTableBlockEntity.isFuel(slotItem)) {
                    if (!this.moveItemStackTo(slotItem, FUEL_SLOT, RESULT_SLOT, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (slotId >= INV_SLOT_START && slotId < INV_SLOT_END) {
                    if (!this.moveItemStackTo(slotItem, USE_ROW_SLOT_START, USE_ROW_SLOT_END, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (slotId >= USE_ROW_SLOT_START && slotId < USE_ROW_SLOT_END && !this.moveItemStackTo(slotItem, INV_SLOT_START, INV_SLOT_END, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(slotItem, INV_SLOT_START, USE_ROW_SLOT_END, false)) {
                return ItemStack.EMPTY;
            }

            if (slotItem.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (slotItem.getCount() == result.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, slotItem);
        }

        return result;
    }

    /**
     * Check if the screen is still valid
     *
     * @param player {@link Player The player interacting with the screen}
     * @return {@link Boolean True if the screen container is still valid}
     */
    @Override
    public boolean stillValid(final @NotNull Player player) {
        return this.container.stillValid(player);
    }

    /**
     * Check if an {@link ItemStack item stack} can be forged
     *
     * @param itemStack {@link ItemStack The item stack to check}
     * @return {@link Boolean True if the item can be forged}
     */
    private boolean canForge(final ItemStack itemStack) {
        return this.level.getRecipeManager().getRecipeFor((RecipeType<ForgingRecipe>)this.recipeType, new SimpleContainer(itemStack), this.level).isPresent();
    }

    /**
     * Get the {@link Integer forging progress}
     *
     * @return {@link Integer The forging progress}
     */
    public int getForgingProgress() {
        final int forgingProgress = this.containerData.get(2);
        final int forgingTotalTime = this.containerData.get(3);
        return forgingTotalTime != 0 && forgingProgress != 0 ? forgingProgress * 24 / forgingTotalTime : 0;
    }

    /**
     * Get the {@link Integer lit progress}
     *
     * @return {@link Integer The lit progress}
     */
    public int getLitProgress() {
        int litProgress = this.containerData.get(1);
        if (litProgress == 0) {
            litProgress = 400;
        }
        return this.containerData.get(0) * 28 / litProgress;
    }

    /**
     * Check if the forging table is lit
     *
     * @return {@link Boolean True if the forging table is lit}
     */
    public boolean isLit() {
        return this.containerData.get(0) > 0;
    }

}