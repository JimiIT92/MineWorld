package org.mineworld.inventory;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWMenuTypes;
import org.mineworld.core.MWRecipeTypes;
import org.mineworld.entity.block.ForgingTableBlockEntity;
import org.mineworld.recipe.ForgingRecipe;

/**
 * {@link MineWorld MineWorld} {@link AbstractContainerMenu Forging Table Menu}
 */
public class ForgingTableMenu extends AbstractContainerMenu {
    /**
     * {@link Integer The Base Ingredient Slot Id}
     */
    public static final int INGREDIENT_BASE_SLOT = 0;
    /**
     * {@link Integer The Addition Ingredient Slot Id}
     */
    public static final int INGREDIENT_ADDITION_SLOT = 1;
    /**
     * {@link Integer The Fuel Slot Id}
     */
    public static final int FUEL_SLOT = 2;
    /**
     * {@link Integer The Result Slot Id}
     */
    public static final int RESULT_SLOT = 3;
    /**
     * {@link Integer The number of slots}
     */
    public static final int SLOT_COUNT = 4;
    /**
     * {@link Integer The Data Values count}
     */
    public static final int DATA_COUNT = 4;
    /**
     * {@link Integer The Initial Inventory Slot index}
     */
    private static final int INV_SLOT_START = 4;
    /**
     * {@link Integer The Final Inventory Slot index}
     */
    private static final int INV_SLOT_END = 31;
    /**
     * {@link Integer The Initial Hotbar Slot index}
     */
    private static final int USE_ROW_SLOT_START = 31;
    /**
     * {@link Integer The Final Hotbar Slot index}
     */
    private static final int USE_ROW_SLOT_END = 40;
    /**
     * {@link Container The Menu Container}
     */
    private final Container container;
    /**
     * {@link ContainerData The Menu Container Data}
     */
    private final ContainerData containerData;
    /**
     * {@link Level The level reference}
     */
    private final Level level;
    /**
     * {@link RecipeType<ForgingRecipe> The Forging Recipe Type}
     */
    private final RecipeType<? extends ForgingRecipe> recipeType;

    /**
     * Constructor. Sets the {@link AbstractContainerMenu Menu Properties}
     *
     * @param id {@link Integer The Menu Id}
     * @param inventory {@link Inventory The Menu Inventory}
     * @param buffer {@link FriendlyByteBuf The Container buffer}
     */
    public ForgingTableMenu(final int id, final Inventory inventory, final FriendlyByteBuf buffer) {
        this(id, inventory);
    }

    /**
     * Constructor. Sets the {@link AbstractContainerMenu Menu Properties}
     *
     * @param id {@link Integer The Menu Id}
     * @param inventory {@link Inventory The Menu Inventory}
     */
    public ForgingTableMenu(final int id, final Inventory inventory) {
        this(id, inventory, new SimpleContainer(SLOT_COUNT), new SimpleContainerData(DATA_COUNT));
    }

    /**
     * Constructor. Sets the {@link AbstractContainerMenu Menu Properties}
     *
     * @param id {@link Integer The Menu Id}
     * @param inventory {@link Inventory The Menu Inventory}
     * @param container {@link Container The Menu Container}
     * @param containerData {@link ContainerData The Container Data reference}
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
             * Check if an {@link ItemStack Item} can be placed inside the {@link Slot Slot}
             *
             * @param itemStack {@link ItemStack The Item Stack to place}
             * @return {@link Boolean True if the Item can be placed inside the Slot}
             */
            @Override
            public boolean mayPlace(final @NotNull ItemStack itemStack) {
                return ForgingTableBlockEntity.isFuel(itemStack);
            }
        });
        this.addSlot(new Slot(container, RESULT_SLOT, 131, 21) {

            /**
             * {@link Integer The removed Item Count}
             */
            private int removeCount;

            /**
             * Check if an {@link ItemStack Item} can be placed inside the {@link Slot Slot}
             *
             * @param itemStack {@link ItemStack The Item Stack to place}
             * @return {@link Boolean#FALSE False}
             */
            @Override
            public boolean mayPlace(final @NotNull ItemStack itemStack) {
                return false;
            }

            /**
             * Remove an {@link ItemStack Item} from the {@link Slot Slot}
             *
             * @param amount {@link Integer The amount of Items to remove}
             * @return {@link ItemStack The removed Item Stack}
             */
            public @NotNull ItemStack remove(final int amount) {
                if (this.hasItem()) {
                    this.removeCount += Math.min(amount, this.getItem().getCount());
                }
                return super.remove(amount);
            }

            /**
             * Take an {@link ItemStack Item} from the {@link Slot Slot}
             *
             * @param player {@link Player The Player taking the Item}
             * @param itemStack {@link ItemStack The Item to take from the Slot}
             */
            public void onTake(final @NotNull Player player, final @NotNull ItemStack itemStack) {
                this.checkTakeAchievements(itemStack);
                super.onTake(player, itemStack);
            }

            /**
             * Quickly take an {@link ItemStack Item} from the {@link Slot Slot}
             *
             * @param itemStack {@link ItemStack The Item Stack to quickly take}
             * @param amount {@link Integer The amount of Items to take}
             */
            protected void onQuickCraft(final @NotNull ItemStack itemStack, final int amount) {
                this.removeCount += amount;
                this.checkTakeAchievements(itemStack);
            }

            /**
             * Check if the {@link Player Player} should be awarded with some awards
             *
             * @param itemStack {@link ItemStack The current Item Stack}
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
     * Get the {@link Integer Menu Grid width}
     *
     * @return {@link Integer 1}
     */
    public int getGridWidth() {
        return 1;
    }

    /**
     * Get the {@link Integer Menu Grid height}
     *
     * @return {@link Integer 1}
     */
    public int getGridHeight() {
        return 1;
    }

    /**
     * Get the {@link Integer Menu size}
     *
     * @return {@link #SLOT_COUNT 4}
     */
    public int getSize() {
        return SLOT_COUNT;
    }

    /**
     * Quickly move an {@link ItemStack Item Stack} into or from a Slot
     *
     * @param player {@link Player The Player moving the Item Stack}
     * @param slotId {@link Integer The Id of the Slot to move into or from}
     * @return {@link ItemStack The moved Item Stack}
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
     * Check if a {@link Player Player} can still interact with the {@link AbstractContainerMenu Menu}
     *
     * @param player {@link Player The Player interacting with the Menu}
     * @return {@link Boolean True if the Menu can still be interacted}
     */
    @Override
    public boolean stillValid(final @NotNull Player player) {
        return this.container.stillValid(player);
    }

    /**
     * Check if an {@link ItemStack Item Stack} can be forged
     *
     * @param itemStack {@link ItemStack The current Item Stack}
     * @return {@link Boolean True if the Item can be forged}
     */
    private boolean canForge(final ItemStack itemStack) {
        return this.level.getRecipeManager().getRecipeFor((RecipeType<ForgingRecipe>)this.recipeType, new SimpleContainer(itemStack), this.level).isPresent();
    }

    /**
     * Get the {@link Integer current Forging progress}
     *
     * @return {@link Integer The current Forging progress}
     */
    public int getForgingProgress() {
        final int forgingProgress = this.containerData.get(2);
        final int forgingTotalTime = this.containerData.get(3);
        return forgingTotalTime != 0 && forgingProgress != 0 ? forgingProgress * 24 / forgingTotalTime : 0;
    }

    /**
     * Get the {@link Integer current Lit progress}
     *
     * @return {@link Integer The current Lit progress}
     */
    public int getLitProgress() {
        int litProgress = this.containerData.get(1);
        if (litProgress == 0) {
            litProgress = 400;
        }
        return this.containerData.get(0) * 28 / litProgress;
    }

    /**
     * Check if the Forging Table is Lit
     *
     * @return {@link Boolean True if the Forging Table is Lit}
     */
    public boolean isLit() {
        return this.containerData.get(0) > 0;
    }

}