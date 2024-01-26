package org.mineworld.entity.block;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeCraftingHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineworld.MineWorld;
import org.mineworld.block.ForgingTableBlock;
import org.mineworld.core.MWBlockEntityTypes;
import org.mineworld.core.MWRecipeTypes;
import org.mineworld.helper.ItemHelper;
import org.mineworld.helper.ResourceHelper;
import org.mineworld.inventory.ForgingTableMenu;
import org.mineworld.recipe.ForgingRecipe;

import java.util.List;

/**
 * {@link MineWorld MineWorld} {@link BlockEntity Forging Table Block Entity}
 */
public class ForgingTableBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer, RecipeCraftingHolder, StackedContentsCompatible {

    /**
     * {@link Integer The Base Ingredient Slot Id}
     */
    private static final int SLOT_INPUT_BASE = 0;
    /**
     * {@link Integer The Addition Ingredient Slot Id}
     */
    private static final int SLOT_INPUT_ADDITION = 1;
    /**
     * {@link Integer The Fuel Slot Id}
     */
    private static final int SLOT_INPUT_FUEL = 2;
    /**
     * {@link Integer The Result Slot Id}
     */
    private static final int SLOT_OUTPUT_RESULT = 3;
    /**
     * {@link Integer The Hopper Interactable Slots for the Upper Face}
     */
    private static final int[] SLOTS_FOR_UP = new int[]{SLOT_INPUT_BASE, SLOT_INPUT_ADDITION};
    /**
     * {@link Integer The Hopper Interactable Slots for the Lower Face}
     */
    private static final int[] SLOTS_FOR_DOWN = new int[]{SLOT_INPUT_FUEL, SLOT_OUTPUT_RESULT};
    /**
     * {@link Integer The Hopper Interactable Slots for the Side Faces}
     */
    private static final int[] SLOTS_FOR_SIDES = new int[]{SLOT_INPUT_FUEL};
    /**
     * {@link String The Lit Time NBT key}
     */
    private final String LIT_TIME_NBT_KEY = "LitTime";
    /**
     * {@link String The Forging Progress NBT key}
     */
    private final String FORGING_PROGRESS_NBT_KEY = "ForgingProgress";
    /**
     * {@link String The Forging Total Time NBT key}
     */
    private final String FORGING_TOTAL_TIME_NBT_KEY = "ForgingTotalTime";
    /**
     * {@link String The Recipes Used NBT key}
     */
    private final String RECIPES_USED_NBT_KEY = "RecipesUsed";
    /**
     * {@link Integer The Container size}
     */
    private final int CONTAINER_SIZE = 4;
    /**
     * {@link NonNullList<ItemStack> The Container content}
     */
    private NonNullList<ItemStack> items = NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY);
    /**
     * {@link Integer The Forging Table Lit Time}
     */
    private int litTime;
    /**
     * {@link Integer The Forging Table Lit duration}
     */
    private int litDuration;
    /**
     * {@link Integer The Forging Table current forging progress}
     */
    private int forgingProgress;
    /**
     * {@link Integer The Forging Table total forging time}
     */
    private int forgingTotalTime;
    /**
     * {@link ContainerData The Container Data Access}
     */
    private final ContainerData dataAccess = new ContainerData() {

        /**
         * Get a Data Value from the Container
         *
         * @param index {@link Integer The Data Value index}
         * @return {@link Integer The Data Value}
         */
        public int get(final int index) {
            return switch (index) {
                case 0 -> ForgingTableBlockEntity.this.litTime;
                case 1 -> ForgingTableBlockEntity.this.litDuration;
                case 2 -> ForgingTableBlockEntity.this.forgingProgress;
                case 3 -> ForgingTableBlockEntity.this.forgingTotalTime;
                default -> 0;
            };
        }

        /**
         * Set a Data Value on the Container
         *
         * @param index {@link Integer The Data Value index}
         * @param value {@link Integer The Data Value to set}
         */
        public void set(final int index, final int value) {
            switch (index) {
                case 0 -> ForgingTableBlockEntity.this.litTime = value;
                case 1 -> ForgingTableBlockEntity.this.litDuration = value;
                case 2 -> ForgingTableBlockEntity.this.forgingProgress = value;
                case 3 -> ForgingTableBlockEntity.this.forgingTotalTime = value;
            }
        }

        /**
         * Get the number of Data Values
         *
         * @return {@link ForgingTableBlockEntity#CONTAINER_SIZE 4}
         */
        public int getCount() {
            return CONTAINER_SIZE;
        }
    };
    /**
     * {@link Object2IntOpenHashMap<ResourceLocation> The used Recipes map}
     */
    private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();
    /**
     * {@link RecipeManager.CachedCheck The Quick Recipe check manager}
     */
    private final RecipeManager.CachedCheck<Container, ? extends ForgingRecipe> quickCheck;
    /**
     * {@link IItemHandler The Container Sided Inventory Item Handlers}
     */
    private LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

    /**
     * Constructor. Set the Block Entity properties
     *
     * @param blockPos {@link BlockPos The Block Entity Block POs}
     * @param blockState {@link BlockState The Block State for the Block Entity}
     */
    public ForgingTableBlockEntity(final BlockPos blockPos, final BlockState blockState) {
        this(blockPos, blockState, MWRecipeTypes.FORGING.get());
    }

    /**
     * Constructor. Set the Block Entity properties
     *
     * @param blockPos {@link BlockPos The Block Entity Block POs}
     * @param blockState {@link BlockState The Block State for the Block Entity}
     * @param recipeType {@link RecipeType<ForgingRecipe> The Recipe Type}
     */
    public ForgingTableBlockEntity(final BlockPos blockPos, final BlockState blockState, final RecipeType<? extends ForgingRecipe> recipeType) {
        super(MWBlockEntityTypes.FORGING_TABLE.get(), blockPos, blockState);
        this.quickCheck = RecipeManager.createCheck(recipeType);
    }

    /**
     * Load the Container content from the {@link CompoundTag NBT Tags}
     *
     * @param nbt {@link CompoundTag The NBT Tags}
     */
    @Override
    public void load(final @NotNull CompoundTag nbt) {
        super.load(nbt);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(nbt, this.items);
        this.litTime = nbt.getInt(LIT_TIME_NBT_KEY);
        this.forgingProgress = nbt.getInt(FORGING_PROGRESS_NBT_KEY);
        this.forgingTotalTime = nbt.getInt(FORGING_TOTAL_TIME_NBT_KEY);
        this.litDuration = this.getBurnDuration(this.items.get(1));
        final CompoundTag recipesUsedNbt = nbt.getCompound(RECIPES_USED_NBT_KEY);
        recipesUsedNbt.getAllKeys().forEach(recipe -> this.recipesUsed.put(ResourceHelper.parse(recipe), recipesUsedNbt.getInt(recipe)));
    }

    /**
     * Save the Container content to the {@link CompoundTag NBT Tags}
     *
     * @param nbt {@link CompoundTag The NBT Tags}
     */
    @Override
    protected void saveAdditional(final @NotNull CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.putInt(LIT_TIME_NBT_KEY, this.litTime);
        nbt.putInt(FORGING_PROGRESS_NBT_KEY, this.forgingProgress);
        nbt.putInt(FORGING_TOTAL_TIME_NBT_KEY, this.forgingTotalTime);
        ContainerHelper.saveAllItems(nbt, this.items);
        final CompoundTag recipesUsed = new CompoundTag();
        this.recipesUsed.forEach((location, count) -> recipesUsed.putInt(location.toString(), count));
        nbt.put(RECIPES_USED_NBT_KEY, recipesUsed);
    }

    /**
     * Get the {@link Component Container default name}
     *
     * @return {@link ForgingTableBlock#CONTAINER_TITLE The Forging Table Container Title Component}
     */
    @Override
    protected @NotNull Component getDefaultName() {
        return ForgingTableBlock.CONTAINER_TITLE;
    }

    /**
     * Create the {@link AbstractContainerMenu Container Menu}
     *
     * @param id {@link Integer The Menu Id}
     * @param inventory {@link Inventory The Menu Inventory}
     * @return {@link ForgingTableMenu The Forging Table Menu}
     */
    @Override
    protected @NotNull AbstractContainerMenu createMenu(final int id, final @NotNull Inventory inventory) {
        return new ForgingTableMenu(id, inventory, this, this.dataAccess);
    }

    /**
     * Check if an {@link ItemStack Item Stack} is a Forging Table Fuel
     *
     * @param itemStack {@link ItemStack The Item Stack to check}
     * @return {@link Boolean True if is a Forging Table Fuel}
     */
    public static boolean isFuel(final ItemStack itemStack) {
        return itemStack.is(Items.LAVA_BUCKET);
    }

    /**
     * Get the {@link ItemStack Item burn duration}
     *
     * @param itemStack {@link ItemStack The current Item Stack}
     * @return {@link Integer 20000 if is a Forging Table Fuel}
     */
    private int getBurnDuration(final ItemStack itemStack) {
        return itemStack.isEmpty() || !isFuel(itemStack) ? 0 : 20000;
    }

    /**
     * Check if the Forging Table is Lit
     *
     * @return {@link Boolean True if is Lit}
     */
    private boolean isLit() {
        return this.litTime > 0;
    }

    /**
     * Tick the Forging Table
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param blockState {@link BlockState The current Block State}
     * @param blockEntity {@link ForgingTableBlockEntity The Forging Table Block Entity}
     */
    public static void serverTick(final Level level, final BlockPos blockPos, BlockState blockState, final ForgingTableBlockEntity blockEntity) {
        boolean isLit = blockEntity.isLit();
        if (blockEntity.isLit()) {
            --blockEntity.litTime;
        }
        boolean hasChanges = false;
        final ItemStack fuel = blockEntity.items.get(SLOT_INPUT_FUEL);
        final boolean hasFuel = !fuel.isEmpty();
        final boolean hasIngredients = hasIngredients(blockEntity.items);
        if (isLit || hasFuel && hasIngredients) {
            final RecipeHolder<?> recipe = hasIngredients ? blockEntity.quickCheck.getRecipeFor(blockEntity, level).orElse(null) : null;
            final int maxStackSize = blockEntity.getMaxStackSize();
            final boolean canBurn = recipe != null && blockEntity.canBurn(level.registryAccess(), recipe.value(), blockEntity.items, maxStackSize);
            if (!isLit && canBurn) {
                blockEntity.litTime = blockEntity.getBurnDuration(fuel);
                blockEntity.litDuration = blockEntity.litTime;
                hasChanges = true;
                if (fuel.hasCraftingRemainingItem()) {
                    blockEntity.items.set(SLOT_INPUT_FUEL, fuel.getCraftingRemainingItem());
                }
            }

            if (isLit && canBurn) {
                ++blockEntity.forgingProgress;
                if (blockEntity.forgingProgress == blockEntity.forgingTotalTime) {
                    blockEntity.forgingProgress = 0;
                    blockEntity.forgingTotalTime = getTotalForgingTime(level, blockEntity);
                    if (blockEntity.forge(level.registryAccess(), recipe.value(), blockEntity.items, maxStackSize)) {
                        blockEntity.setRecipeUsed(recipe);
                    }
                    hasChanges = true;
                }
            } else {
                blockEntity.forgingProgress = 0;
            }
        } else if (blockEntity.forgingProgress > 0) {
            blockEntity.forgingProgress = Mth.clamp(blockEntity.forgingProgress - 2, 0, blockEntity.forgingTotalTime);
        }

        if (isLit != blockEntity.isLit()) {
            hasChanges = true;
            blockState = blockState.setValue(AbstractFurnaceBlock.LIT, blockEntity.isLit());
            level.setBlock(blockPos, blockState, 3);
        }

        if (hasChanges) {
            setChanged(level, blockPos, blockState);
        }
    }

    /**
     * Check if the Forging Table has some {@link Ingredient Ingredients}
     *
     * @param items {@link NonNullList<ItemStack> The Container Items}
     * @return {@link Boolean True if the Input Slots has some Ingredients}
     */
    private static boolean hasIngredients(final NonNullList<ItemStack> items) {
        return items.size() >= 2 && !items.get(SLOT_INPUT_BASE).isEmpty() && !items.get(SLOT_INPUT_ADDITION).isEmpty();
    }

    /**
     * Check if a {@link Recipe Recipe} can be processed
     *
     * @param registryAccess {@link RegistryAccess The Registry Access reference}
     * @param recipe {@link Recipe The current Recipe}
     * @param items {@link NonNullList<ItemStack> The Recipe Ingredients}
     * @param count {@link Integer The Recipe Result count}
     * @return {@link Boolean True if the Recipe can be processed}
     */
    private boolean canBurn(final RegistryAccess registryAccess, final @Nullable Recipe<?> recipe, final NonNullList<ItemStack> items, final int count) {
        if (hasIngredients(items) && recipe != null) {
            final ItemStack result = ((Recipe<WorldlyContainer>) recipe).assemble(this, registryAccess);
            if (result.isEmpty()) {
                return false;
            }
            ItemStack currentResult = items.get(SLOT_OUTPUT_RESULT);
            if (currentResult.isEmpty() || (currentResult.getCount() + result.getCount() <= count && currentResult.getCount() + result.getCount() <= currentResult.getMaxStackSize())) {
                return true;
            }
            if (!ItemStack.isSameItem(currentResult, result)) {
                return false;
            }
            return currentResult.getCount() + result.getCount() <= result.getMaxStackSize();
        }
        return false;
    }

    /**
     * Forge an {@link ItemStack Item}
     *
     * @param registryAccess {@link RegistryAccess The Registry Access reference}
     * @param recipe {@link Recipe The current Recipe}
     * @param items {@link NonNullList<ItemStack> The Recipe Ingredients}
     * @param count {@link Integer The Recipe Result count}
     * @return {@link Boolean True if the Item has been forged successfully}
     */
    private boolean forge(final RegistryAccess registryAccess, final @Nullable Recipe<?> recipe, final NonNullList<ItemStack> items, final int count) {
        if (recipe != null && this.canBurn(registryAccess, recipe, items, count)) {
            final ItemStack base = items.get(SLOT_INPUT_BASE);
            final ItemStack addition = items.get(SLOT_INPUT_ADDITION);
            final ItemStack result = ((Recipe<WorldlyContainer>) recipe).assemble(this, registryAccess);
            final ItemStack currentResult = items.get(SLOT_OUTPUT_RESULT);
            if (currentResult.isEmpty()) {
                items.set(SLOT_OUTPUT_RESULT, result.copy());
            } else if (currentResult.is(result.getItem())) {
                currentResult.grow(result.getCount());
            }

            ItemHelper.hurt(base);
            ItemHelper.hurt(addition);
            return true;
        }
        return false;
    }

    /**
     * Get the {@link Integer total Forging time}
     *
     * @param level {@link Level The level reference}
     * @param blockEntity {@link BlockEntity The Forging Table Block Entity}
     * @return {@link Integer 400}
     */
    private static int getTotalForgingTime(final Level level, final ForgingTableBlockEntity blockEntity) {
        return blockEntity.quickCheck.getRecipeFor(blockEntity, level).map(recipe -> recipe.value().forgingTime()).orElse(400);
    }

    /**
     * Get the {@link Integer Hopper interactable Slots} based on the {@link Direction Face}
     *
     * @param direction {@link Direction The Face the Hopper is interacting from}
     * @return {@link Integer The Hopper interactable Slots}
     */
    @Override
    public int @NotNull [] getSlotsForFace(final @NotNull Direction direction) {
        return switch (direction) {
            case DOWN -> SLOTS_FOR_DOWN;
            case UP -> SLOTS_FOR_UP;
            default -> SLOTS_FOR_SIDES;
        };
    }

    /**
     * Check if an {@link ItemStack Item} can be placed by a Hopper from a Face
     *
     * @param slotId {@link Integer The Container Slot Id}
     * @param itemStack {@link ItemStack The Item Stack to place}
     * @param direction {@link Direction The Face the Hopper is interacting from}
     * @return {@link Boolean True if the Item can be placed}
     */
    @Override
    public boolean canPlaceItemThroughFace(final int slotId, final @NotNull ItemStack itemStack, final @Nullable Direction direction) {
        return this.canPlaceItem(slotId, itemStack);
    }

    /**
     * Check if an {@link ItemStack Item} can be taken by a Hopper from a Face
     *
     * @param slotId {@link Integer The Container Slot Id}
     * @param itemStack {@link ItemStack The Item Stack to take}
     * @param direction {@link Direction The Face the Hopper is interacting from}
     * @return {@link Boolean#TRUE True}
     */
    @Override
    public boolean canTakeItemThroughFace(final int slotId, final @NotNull ItemStack itemStack, final @NotNull Direction direction) {
        return true;
    }

    /**
     * Get the {@link Integer Container Size}
     *
     * @return {@link Integer 1}
     */
    @Override
    public int getContainerSize() {
        return this.items.size();
    }

    /**
     * Check if the Container is empty
     *
     * @return {@link Boolean True if there are no Items}
     */
    @Override
    public boolean isEmpty() {
        return this.items.stream().allMatch(item -> item.isEmpty());
    }

    /**
     * Get an {@link Item Item} from the Container
     *
     * @param slotId {@link Integer The Container Slot Id}
     * @return {@link ItemStack The Item Stack}
     */
    @Override
    public @NotNull ItemStack getItem(final int slotId) {
        return this.items.get(slotId);
    }

    /**
     * Remove an {@link Item Item} from the Container
     *
     * @param slotId {@link Integer The Container Slot Id}
     * @param amount {@link Integer The amount of Items to take}
     * @return {@link ItemStack The removed Item Stack}
     */
    @Override
    public @NotNull ItemStack removeItem(final int slotId, final int amount) {
        return ContainerHelper.removeItem(this.items, slotId, amount);
    }

    /**
     * Remove an {@link Item Item} from the Container without causing updates
     *
     * @param slotId {@link Integer The Container Slot Id}
     * @return {@link ItemStack The removed Item Stack}
     */
    @Override
    public @NotNull ItemStack removeItemNoUpdate(final int slotId) {
        return ContainerHelper.takeItem(this.items, slotId);
    }

    /**
     * Set an {@link Item Item} inside the Container
     *
     * @param slotId {@link Integer The Container Slot Id}
     * @param itemStack {@link ItemStack The Item Stack to set}
     */
    @Override
    public void setItem(final int slotId, final ItemStack itemStack) {
        final ItemStack content = this.items.get(slotId);
        final boolean isSameItem = !itemStack.isEmpty() && ItemStack.isSameItem(itemStack, content) && ItemStack.isSameItemSameTags(itemStack, content);
        this.items.set(slotId, itemStack);
        if (itemStack.getCount() > this.getMaxStackSize()) {
            itemStack.setCount(this.getMaxStackSize());
        }

        if ((slotId == SLOT_INPUT_BASE || slotId == SLOT_INPUT_ADDITION) && !isSameItem) {
            this.forgingTotalTime = getTotalForgingTime(this.level, this);
            this.forgingProgress = 0;
            this.setChanged();
        }
    }

    /**
     * Check if a {@link Player Player} can still interact with the {@link BlockEntity Block Entity}
     *
     * @param player {@link Player The Player interacting with the Block Entity}
     * @return {@link Boolean True if the Block Entity can still be interacted}
     */
    @Override
    public boolean stillValid(final @NotNull Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    /**
     * Check if an {@link ItemStack Item} can be placed inside the Container
     *
     * @param slotId {@link Integer The Container Slot Id}
     * @param itemStack {@link ItemStack The Item Stack to place}
     * @return {@link Boolean True if the Item can be placed}
     */
    @Override
    public boolean canPlaceItem(final int slotId, final @NotNull ItemStack itemStack) {
        return switch (slotId) {
            case SLOT_OUTPUT_RESULT -> false;
            case SLOT_INPUT_FUEL -> isFuel(this.items.get(SLOT_INPUT_FUEL));
            default -> true;
        };
    }

    /**
     * Clear the Container Content
     */
    @Override
    public void clearContent() {
        this.items.clear();
    }

    /**
     * Mark a {@link Recipe Recipe} as used
     *
     * @param recipe {@link Recipe The Recipe}
     */
    @Override
    public void setRecipeUsed(final @Nullable RecipeHolder<?> recipe) {
        if (recipe != null) {
            this.recipesUsed.addTo(recipe.id(), 1);
        }
    }

    /**
     * Get the {@link Recipe used Recipe}
     *
     * @return {@link Recipe Null}
     */
    @Override
    @Nullable
    public RecipeHolder<?> getRecipeUsed() {
        return null;
    }

    /**
     * Award the {@link Player Player} with the used {@link Recipe Recipes}
     *
     * @param player {@link Player The Player to award}
     * @param itemStacks {@link List<ItemStack> The Recipe Results}
     */
    public void awardUsedRecipes(@NotNull Player player, @NotNull List<ItemStack> itemStacks) {
    }

    /**
     * Award the {@link Player Player} with the used {@link Recipe Recipes} and experience
     *
     * @param player {@link Player The Player to award}
     */
    public void awardUsedRecipesAndPopExperience(final ServerPlayer player) {
        player.awardRecipes(this.getRecipesToAwardAndPopExperience(player.serverLevel(), player.position()));
        this.recipesUsed.clear();
    }

    /**
     * Get the {@link List<Recipe> Recipes} to award to the {@link ServerPlayer Player}
     *
     * @param player {@link Player The Player to award}
     * @param position {@link Vec3 The Player position}
     * @return {@link List<Recipe> The Recipes}
     */
    public List<RecipeHolder<?>> getRecipesToAwardAndPopExperience(final ServerLevel player, final Vec3 position) {
        final List<RecipeHolder<?>> recipes = Lists.newArrayList();
        for(Object2IntMap.Entry<ResourceLocation> entry : this.recipesUsed.object2IntEntrySet()) {
            player.getRecipeManager().byKey(entry.getKey()).ifPresent(recipe -> {
                recipes.add(recipe);
                createExperience(player, position, entry.getIntValue(), ((ForgingRecipe)recipe.value()).experience());
            });
        }
        return recipes;
    }

    /**
     * Create some experience orbs
     *
     * @param level {@link ServerLevel The level reference}
     * @param position {@link Vec3 The current Location}
     * @param count {@link Integer How may orbs to create}
     * @param amount {@link Float The amount of experience to get}
     */
    private static void createExperience(final ServerLevel level, final Vec3 position, final int count, final float amount) {
        int orbCount = Mth.floor((float)count * amount);
        final float extraOrbChance = Mth.frac((float)count * amount);
        if (extraOrbChance != 0.0F && Math.random() < (double)extraOrbChance) {
            ++orbCount;
        }
        ExperienceOrb.award(level, position, orbCount);
    }

    /**
     * Fill the {@link StackedContents Stacked Contents}
     *
     * @param stackedContents {@link StackedContents The Stacked Contents}
     */
    public void fillStackedContents(final @NotNull StackedContents stackedContents) {
        this.items.forEach(item -> stackedContents.accountStack(item));
    }

    /**
     * Get the {@link T Container Capability}
     *
     * @param capability {@link Capability<T> The Capability to check}
     * @param side {@link Direction The Side to check from}
     * @return {@link LazyOptional<T> The Container Capability}
     * @param <T> The Capability type
     */
    @Override
    public <T> @NotNull LazyOptional<T> getCapability(final @NotNull Capability<T> capability, final @Nullable Direction side) {
        if (!this.remove && side != null && ForgeCapabilities.ITEM_HANDLER.equals(capability)) {
            return switch (side) {
                case UP -> handlers[0].cast();
                case DOWN -> handlers[1].cast();
                default -> handlers[2].cast();
            };
        }
        return super.getCapability(capability, side);
    }

    /**
     * Invalidate the Container Capabilities
     */
    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        for (LazyOptional<? extends IItemHandler> handler : handlers) handler.invalidate();
    }

    /**
     * Initialize the Container Capabilities
     */
    @Override
    public void reviveCaps() {
        super.reviveCaps();
        this.handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);
    }

}