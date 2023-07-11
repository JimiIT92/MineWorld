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
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
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
import org.mineworld.block.ForgingTableBlock;
import org.mineworld.core.MWBlockEntityTypes;
import org.mineworld.core.MWRecipeTypes;
import org.mineworld.helper.ItemHelper;
import org.mineworld.helper.KeyHelper;
import org.mineworld.inventory.ForgingTableMenu;
import org.mineworld.recipe.ForgingRecipe;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Implementation class for the forging table block entity
 */
public class ForgingTableBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer, RecipeHolder, StackedContentsCompatible {

    /**
     * {@link Integer The base ingredient input slot id}
     */
    private static int SLOT_INPUT_BASE = 0;
    /**
     * {@link Integer The addition ingredient input slot id}
     */
    private static int SLOT_INPUT_ADDITION = 1;
    /**
     * {@link Integer The fuel input slot id}
     */
    private static int SLOT_INPUT_FUEL = 2;
    /**
     * {@link Integer The result output slot id}
     */
    private static int SLOT_OUTPUT_RESULT = 3;
    /**
     * {@link Integer The interactable slots for the upper face}
     */
    private static int[] SLOTS_FOR_UP = new int[]{SLOT_INPUT_BASE, SLOT_INPUT_ADDITION};
    /**
     * {@link Integer The interactable slots for the bottom face}
     */
    private static int[] SLOTS_FOR_DOWN = new int[]{SLOT_INPUT_FUEL, SLOT_OUTPUT_RESULT};
    /**
     * {@link Integer The interactable slots for the side face}
     */
    private static int[] SLOTS_FOR_SIDES = new int[]{SLOT_INPUT_FUEL};
    /**
     * {@link String The lit time nbt key}
     */
    private String LIT_TIME_NBT_KEY = "LitTime";
    /**
     * {@link String The forging progress nbt key}
     */
    private String FORGING_PROGRESS_NBT_KEY = "ForgingProgress";
    /**
     * {@link String The forging total time nbt key}
     */
    private String FORGING_TOTAL_TIME_NBT_KEY = "ForgingTotalTime";
    /**
     * {@link String The recipes used nbt key}
     */
    private String RECIPES_USED_NBT_KEY = "RecipesUsed";
    /**
     * {@link Integer The forging table container size}
     */
    private int CONTAINER_SIZE = 4;
    /**
     * {@link RecipeType<ForgingRecipe> The recipe types}
     */
    private RecipeType<? extends ForgingRecipe> recipeType;
    /**
     * {@link NonNullList<ItemStack> The forging table items}
     */
    private NonNullList<ItemStack> items = NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY);
    /**
     * {@link Integer The forging table lit time}
     */
    private int litTime;
    /**
     * {@link Integer The forging table lit duration}
     */
    private int litDuration;
    /**
     * {@link Integer The forging table current forging progress}
     */
    private int forgingProgress;
    /**
     * {@link Integer The forging table total forging time}
     */
    private int forgingTotalTime;
    /**
     * {@link ContainerData The container data access}
     */
    private ContainerData dataAccess = new ContainerData() {

        /**
         * Get a data value from the container
         *
         * @param index {@link Integer The data value index}
         * @return {@link Integer The data value}
         */
        public int get(int index) {
            return switch (index) {
                case 0 -> ForgingTableBlockEntity.this.litTime;
                case 1 -> ForgingTableBlockEntity.this.litDuration;
                case 2 -> ForgingTableBlockEntity.this.forgingProgress;
                case 3 -> ForgingTableBlockEntity.this.forgingTotalTime;
                default -> 0;
            };
        }

        /**
         * Set a data value on the container
         *
         * @param index {@link Integer The data value index}
         * @param value {@link Integer The data value to set}
         */
        public void set(int index, int value) {
            switch (index) {
                case 0 -> ForgingTableBlockEntity.this.litTime = value;
                case 1 -> ForgingTableBlockEntity.this.litDuration = value;
                case 2 -> ForgingTableBlockEntity.this.forgingProgress = value;
                case 3 -> ForgingTableBlockEntity.this.forgingTotalTime = value;
            }
        }

        /**
         * Get the number of data values
         *
         * @return {@link Integer 4}
         */
        public int getCount() {
            return 4;
        }
    };
    /**
     * {@link Object2IntOpenHashMap<ResourceLocation> The used recipes map}
     */
    private Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();
    /**
     * {@link RecipeManager.CachedCheck The quick recipe check}
     */
    private RecipeManager.CachedCheck<Container, ? extends ForgingRecipe> quickCheck;
    /**
     * {@link IItemHandler The forging table sided inventory item handlers}
     */
    private LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

    /**
     * Constructor. Set the block entity properties
     *
     * @param blockPos {@link BlockPos The current block pos}
     * @param blockState {@link BlockState The current block state}
     */
    public ForgingTableBlockEntity(BlockPos blockPos, BlockState blockState) {
        this(blockPos, blockState, MWRecipeTypes.FORGING.get());
    }

    /**
     * Constructor. Set the block entity properties and {@link RecipeType recipe type}
     *
     * @param blockPos {@link BlockPos The current block pos}
     * @param blockState {@link BlockState The current block state}
     * @param recipeType {@link RecipeType The forging table recipe type}
     */
    public ForgingTableBlockEntity(BlockPos blockPos, BlockState blockState, RecipeType<? extends ForgingRecipe> recipeType) {
        super(MWBlockEntityTypes.FORGING_TABLE.get(), blockPos, blockState);
        this.quickCheck = RecipeManager.createCheck(recipeType);
        this.recipeType = recipeType;
    }

    /**
     * Load the forging table properties from the {@link CompoundTag nbt data}
     *
     * @param nbt {@link CompoundTag The nbt data}
     */
    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(nbt, this.items);
        this.litTime = nbt.getInt(LIT_TIME_NBT_KEY);
        this.forgingProgress = nbt.getInt(FORGING_PROGRESS_NBT_KEY);
        this.forgingTotalTime = nbt.getInt(FORGING_TOTAL_TIME_NBT_KEY);
        this.litDuration = this.getBurnDuration(this.items.get(1));
        CompoundTag recipesUsedNbt = nbt.getCompound(RECIPES_USED_NBT_KEY);
        recipesUsedNbt.getAllKeys().forEach(recipe -> this.recipesUsed.put(KeyHelper.parseLocation(recipe), recipesUsedNbt.getInt(recipe)));
    }

    /**
     * Save the forging table properties to the {@link CompoundTag nbt data}
     *
     * @param nbt {@link CompoundTag The nbt data}
     */
    @Override
    protected void saveAdditional(@NotNull CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.putInt(LIT_TIME_NBT_KEY, this.litTime);
        nbt.putInt(FORGING_PROGRESS_NBT_KEY, this.forgingProgress);
        nbt.putInt(FORGING_TOTAL_TIME_NBT_KEY, this.forgingTotalTime);
        ContainerHelper.saveAllItems(nbt, this.items);
        CompoundTag recipesUsed = new CompoundTag();
        this.recipesUsed.forEach((location, count) -> recipesUsed.putInt(location.toString(), count));
        nbt.put(RECIPES_USED_NBT_KEY, recipesUsed);
    }

    /**
     * Get the {@link Component forging table container name}
     *
     * @return {@link Component The forging table container name}
     */
    @Override
    protected @NotNull Component getDefaultName() {
        return ForgingTableBlock.CONTAINER_TITLE;
    }

    /**
     * Get the {@link AbstractContainerMenu forging table menu}
     *
     * @param id {@link Integer The forging table menu id}
     * @param inventory {@link Inventory The forging table inventory}
     * @return {@link AbstractContainerMenu The forging table menu}
     */
    @Override
    protected @NotNull AbstractContainerMenu createMenu(int id, @NotNull Inventory inventory) {
        return new ForgingTableMenu(id, inventory, this, this.dataAccess);
    }

    /**
     * Check if an {@link ItemStack item} is a forging table fuel
     *
     * @param itemStack {@link ItemStack The item stack to check}
     * @return {@link Boolean True if is a lava bucket}
     */
    public static boolean isFuel(ItemStack itemStack) {
        return itemStack.is(Items.LAVA_BUCKET);
    }

    /**
     * Get the {@link ItemStack item burn duration}
     *
     * @param itemStack {@link ItemStack The item stack to get the burn duration from}
     * @return {@link Integer 20000 if is a forging table fuel, 0 otherwise or if the item stack is empty}
     */
    private int getBurnDuration(ItemStack itemStack) {
        return itemStack.isEmpty() || !isFuel(itemStack) ? 0 : 20000;
    }

    /**
     * Check if the forging table is lit
     *
     * @return {@link Boolean True if the lit time is greater than 0}
     */
    private boolean isLit() {
        return this.litTime > 0;
    }

    /**
     * Make the forging table tick
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param blockState {@link BlockState The current block state}
     * @param blockEntity {@link ForgingTableBlockEntity The forging table block entity}
     */
    public static void serverTick(Level level, BlockPos blockPos, BlockState blockState, ForgingTableBlockEntity blockEntity) {
        boolean isLit = blockEntity.isLit();
        if (blockEntity.isLit()) {
            --blockEntity.litTime;
        }
        boolean hasChanges = false;
        ItemStack fuel = blockEntity.items.get(SLOT_INPUT_FUEL);
        boolean hasFuel = !fuel.isEmpty();
        boolean hasIngredients = hasIngredients(blockEntity.items);
        if (isLit || hasFuel && hasIngredients) {
            Recipe<?> recipe = hasIngredients ? blockEntity.quickCheck.getRecipeFor(blockEntity, level).orElse(null) : null;
            int maxStackSize = blockEntity.getMaxStackSize();
            boolean canBurn = blockEntity.canBurn(level.registryAccess(), recipe, blockEntity.items, maxStackSize);
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
                    if (blockEntity.forge(level.registryAccess(), recipe, blockEntity.items, maxStackSize)) {
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
     * Check if there are ingredients
     *
     * @param items {@link NonNullList<ItemStack> The items to check}
     * @return {@link Boolean True if the first 2 items are not empty}
     */
    private static boolean hasIngredients(NonNullList<ItemStack> items) {
        return items.size() >= 2 && !items.get(SLOT_INPUT_BASE).isEmpty() && !items.get(SLOT_INPUT_ADDITION).isEmpty();
    }

    /**
     * Check if a recipe can be processed
     *
     * @param registryAccess {@link RegistryAccess The registry access}
     * @param recipe {@link Recipe The recipe to check}
     * @param items {@link NonNullList<ItemStack> The recipe ingredients}
     * @param count {@link Integer The recipe result count}
     * @return {@link Boolean True if the recipe can be processed}
     */
    private boolean canBurn(RegistryAccess registryAccess, @Nullable Recipe<?> recipe, NonNullList<ItemStack> items, int count) {
        if (hasIngredients(items) && recipe != null) {
            ItemStack result = ((Recipe<WorldlyContainer>) recipe).assemble(this, registryAccess);
            if (result.isEmpty()) {
                return false;
            }
            ItemStack currentResult = items.get(SLOT_OUTPUT_RESULT);
            if (currentResult.isEmpty() || (currentResult.getCount() + result.getCount() <= count && currentResult.getCount() + result.getCount() <= currentResult.getMaxStackSize())) {
                return true;
            }
            if (!currentResult.sameItem(result)) {
                return false;
            }
            return currentResult.getCount() + result.getCount() <= result.getMaxStackSize();
        }
        return false;
    }

    /**
     * Forge an item
     *
     * @param registryAccess {@link RegistryAccess The registry access}
     * @param recipe {@link Recipe The recipe to process}
     * @param items {@link NonNullList<ItemStack> The recipe ingredients}
     * @param count {@link Integer The recipe result count}
     * @return {@link Boolean True if the items has been forge successfully}
     */
    private boolean forge(RegistryAccess registryAccess, @Nullable Recipe<?> recipe, NonNullList<ItemStack> items, int count) {
        if (recipe != null && this.canBurn(registryAccess, recipe, items, count)) {
            ItemStack base = items.get(SLOT_INPUT_BASE);
            ItemStack addition = items.get(SLOT_INPUT_ADDITION);
            ItemStack result = ((Recipe<WorldlyContainer>) recipe).assemble(this, registryAccess);
            ItemStack currentResult = items.get(SLOT_OUTPUT_RESULT);
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
     * Get the {@link Integer total forging time}
     *
     * @param level {@link Level The level reference}
     * @param blockEntity {@link BlockEntity The forging table block entity}
     * @return {@link Integer 400}
     */
    private static int getTotalForgingTime(Level level, ForgingTableBlockEntity blockEntity) {
        return blockEntity.quickCheck.getRecipeFor(blockEntity, level).map(ForgingRecipe::forgingTime).orElse(400);
    }

    /**
     * Get the {@link Integer slots} based on the {@link Direction face}
     * for interaction with hoppers
     *
     * @param direction {@link Direction The face the hopper is interacting from}
     * @return {@link Integer The interactable slots}
     */
    @Override
    public int @NotNull [] getSlotsForFace(@NotNull Direction direction) {
        return switch (direction) {
            case DOWN -> SLOTS_FOR_DOWN;
            case UP -> SLOTS_FOR_UP;
            default -> SLOTS_FOR_SIDES;
        };
    }

    /**
     * Check if an item can be placed by a hopper from a face
     *
     * @param slotId {@link Integer The slot id}
     * @param itemStack {@link ItemStack The item stack to place}
     * @param direction {@link Direction The face the hopper is interacting from}
     * @return {@link Boolean True if the item can be placed}
     */
    @Override
    public boolean canPlaceItemThroughFace(int slotId, @NotNull ItemStack itemStack, @Nullable Direction direction) {
        return this.canPlaceItem(slotId, itemStack);
    }

    /**
     * Check if an item can be taken through a face by a hopper
     *
     * @param slotId {@link Integer The slot id}
     * @param itemStack {@link ItemStack The item stack to take}
     * @param direction {@link Direction The face the hopper is interacting from}
     * @return {@link Boolean True if the item can be taken}
     */
    @Override
    public boolean canTakeItemThroughFace(int slotId, @NotNull ItemStack itemStack, @NotNull Direction direction) {
        return true;
    }

    /**
     * Get the {@link Integer container size}
     *
     * @return {@link #CONTAINER_SIZE 4}
     */
    @Override
    public int getContainerSize() {
        return this.items.size();
    }

    /**
     * Check if the container is empty
     *
     * @return {@link Boolean True if all items are empty}
     */
    @Override
    public boolean isEmpty() {
        return this.items.stream().allMatch(item -> item.isEmpty());
    }

    /**
     * Get an {@link ItemStack item} from the container
     *
     * @param index {@link Integer The container index}
     * @return {@link ItemStack The item stack}
     */
    @Override
    public @NotNull ItemStack getItem(int index) {
        return this.items.get(index);
    }

    /**
     * Remove an item from the container
     *
     * @param index {@link Integer The container index}
     * @param count {@link Integer The amount of items to take}
     * @return {@link ItemStack The removed item stack}
     */
    @Override
    public @NotNull ItemStack removeItem(int index, int count) {
        return ContainerHelper.removeItem(this.items, index, count);
    }

    /**
     * Remove an item from the container without causing updates
     *
     * @param index {@link Integer The container index}
     * @return {@link ItemStack The removed item stack}
     */
    @Override
    public @NotNull ItemStack removeItemNoUpdate(int index) {
        return ContainerHelper.takeItem(this.items, index);
    }

    /**
     * Set an item inside the container
     *
     * @param slotId {@link Integer The container slot id}
     * @param itemStack {@link ItemStack The item stack to set}
     */
    @Override
    public void setItem(int slotId, ItemStack itemStack) {
        ItemStack content = this.items.get(slotId);
        boolean isSameItem = !itemStack.isEmpty() && itemStack.sameItem(content) && ItemStack.tagMatches(itemStack, content);
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
     * Check if the container is still valid
     *
     * @param player {@link Player The player interacting with the container}
     * @return {@link Boolean True if is still valid}
     */
    @Override
    public boolean stillValid(@NotNull Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    /**
     * Check if an item can be placed inside the container
     *
     * @param slotId {@link Integer The container slot id}
     * @param itemStack {@link ItemStack The item stack to place}
     * @return {@link Boolean True if the item can be placed}
     */
    @Override
    public boolean canPlaceItem(int slotId, @NotNull ItemStack itemStack) {
        return switch (slotId) {
            case SLOT_OUTPUT_RESULT -> false;
            case SLOT_INPUT_FUEL -> isFuel(this.items.get(SLOT_INPUT_FUEL));
            default -> true;
        };
    }

    /**
     * Clear the container content
     */
    @Override
    public void clearContent() {
        this.items.clear();
    }

    /**
     * Mark a {@link Recipe recipe} as used
     *
     * @param recipe {@link Recipe The recipe}
     */
    @Override
    public void setRecipeUsed(@Nullable Recipe<?> recipe) {
        if (recipe != null) {
            this.recipesUsed.addTo(recipe.getId(), 1);
        }
    }

    /**
     * Get the {@link Recipe used recipe}
     *
     * @return {@link Recipe Null}
     */
    @Override
    @Nullable
    public Recipe<?> getRecipeUsed() {
        return null;
    }

    /**
     * Award the {@link Player player} with the used recipe
     *
     * @param player {@link Player The player to award}
     */
    @Override
    public void awardUsedRecipes(@NotNull Player player) {

    }

    /**
     * Award the {@link ServerPlayer player} with the used recipe and experience
     *
     * @param player {@link ServerPlayer The player to award}
     */
    public void awardUsedRecipesAndPopExperience(ServerPlayer player) {
        player.awardRecipes(this.getRecipesToAwardAndPopExperience(player.getLevel(), player.position()));
        this.recipesUsed.clear();
    }

    /**
     * Get the {@link List<Recipe> recipes to award} to the {@link ServerPlayer player}
     *
     * @param player {@link Player The player to award}
     * @param position {@link Vec3 The player position}
     * @return {@link List<Recipe> The recipe list}
     */
    public List<Recipe<?>> getRecipesToAwardAndPopExperience(ServerLevel player, Vec3 position) {
        List<Recipe<?>> recipes = Lists.newArrayList();
        for(Object2IntMap.Entry<ResourceLocation> entry : this.recipesUsed.object2IntEntrySet()) {
            player.getRecipeManager().byKey(entry.getKey()).ifPresent(recipe -> {
                recipes.add(recipe);
                createExperience(player, position, entry.getIntValue(), ((ForgingRecipe)recipe).experience());
            });
        }
        return recipes;
    }

    /**
     * Create some experience orbs
     *
     * @param level {@link ServerLevel The level reference}
     * @param position {@link Vec3 The current position}
     * @param count {@link Integer How may orbs to create}
     * @param amount {@link Float The amount of experience to get}
     */
    private static void createExperience(ServerLevel level, Vec3 position, int count, float amount) {
        int orbCount = Mth.floor((float)count * amount);
        float extraOrbChance = Mth.frac((float)count * amount);
        if (extraOrbChance != 0.0F && Math.random() < (double)extraOrbChance) {
            ++orbCount;
        }
        ExperienceOrb.award(level, position, orbCount);
    }

    /**
     * Fill the {@link StackedContents stacked contents}
     *
     * @param stackedContents {@link StackedContents The stacked contents}
     */
    public void fillStackedContents(@NotNull StackedContents stackedContents) {
        this.items.forEach(item -> stackedContents.accountStack(item));
    }

    /**
     * Get the {@link Capability forging table capability} based on the {@link Direction direction}
     *
     * @param capability {@link Capability The forging table capability}
     * @param direction {@link Direction The direction to get the capability from}
     * @return {@link LazyOptional<T> The forging table capability}
     * @param <T> {@link T The capability type}
     */
    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction direction) {
        if (!this.remove && direction != null && ForgeCapabilities.ITEM_HANDLER.equals(capability)) {
            return switch (direction) {
                case UP -> handlers[0].cast();
                case DOWN -> handlers[1].cast();
                default -> handlers[2].cast();
            };
        }
        return super.getCapability(capability, direction);
    }

    /**
     * Invalidate the forging table capabilities
     */
    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        for (LazyOptional<? extends IItemHandler> handler : handlers) handler.invalidate();
    }

    /**
     * Revive the forging table capabilities
     */
    @Override
    public void reviveCaps() {
        super.reviveCaps();
        this.handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);
    }

}