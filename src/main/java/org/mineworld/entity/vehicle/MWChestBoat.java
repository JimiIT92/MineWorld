package org.mineworld.entity.vehicle;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.HasCustomInventoryScreen;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.entity.vehicle.ContainerEntity;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWEntityTypes;
import org.mineworld.core.MWItems;

import javax.annotation.Nullable;

/**
 * Implementation class for a {@link MineWorld MineWorld} {@link ChestBoat chest boat}
 */
public class MWChestBoat extends MWBoat implements HasCustomInventoryScreen, ContainerEntity {

    /**
     * {@link Integer The chest boat container size}
     */
    private static final int CONTAINER_SIZE = 27;
    /**
     * {@link NonNullList<ItemStack> The chest boat content}
     */
    private NonNullList<ItemStack> itemStacks = NonNullList.withSize(27, ItemStack.EMPTY);
    /**
     * {@link ResourceLocation The chest boat loot table}
     */
    @Nullable
    private ResourceLocation lootTable;
    /**
     * {@link Long The chest boat loot table seed}
     */
    private long lootTableSeed;
    /**
     * {@link LazyOptional<InvWrapper> The chest id handler}
     */
    private LazyOptional<?> itemHandler = LazyOptional.of(() -> new InvWrapper(this));

    /**
     * Constructor. Set the {@link EntityType boat entity type}
     *
     * @param entityType {@link EntityType The boat entity type}
     * @param level {@link Level The level reference}
     */
    public MWChestBoat(final EntityType<? extends Boat> entityType, final Level level) {
        super(entityType, level);
    }

    /**
     * Full constructor. Set the {@link BlockPos boat pos}
     *
     * @param level {@link Level The level reference}
     * @param posX {@link Double The boat X coordinate}
     * @param posY {@link Double The boat Y coordinate}
     * @param posZ {@link Double The boat Z coordinate}
     */
    public MWChestBoat(final Level level, final double posX, final double posY, final double posZ) {
        this(MWEntityTypes.CHEST_BOAT.get(), level);
        this.setPos(posX, posY, posZ);
        this.xo = posX;
        this.yo = posY;
        this.zo = posZ;
    }

    /**
     * Get the {@link Float passenger X coordinate offset}
     *
     * @return {@link Float 0.15}
     */
    protected float getSinglePassengerXOffset() {
        return 0.15F;
    }

    /**
     * {@link Integer Get the maximum amount of passegners}
     *
     * @return {@link Integer 1}
     */
    protected int getMaxPassengers() {
        return 1;
    }

    /**
     * Save the chest content on the {@link CompoundTag boat nbt data}
     *
     * @param nbt {@link CompoundTag The boat nbt data}
     */
    protected void addAdditionalSaveData(final CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        this.addChestVehicleSaveData(nbt);
    }

    /**
     * Read the chest content from the {@link CompoundTag boat nbt data}
     *
     * @param nbt {@link CompoundTag The boat nbt data}
     */
    protected void readAdditionalSaveData(final CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.readChestVehicleSaveData(nbt);
    }

    /**
     * Drop the chest content on boat destroyed
     *
     * @param damageSource {@link DamageSource The damage source that destroyed the boat}
     */
    public void destroy(final @NotNull DamageSource damageSource) {
        super.destroy(damageSource);
        this.chestVehicleDestroyed(damageSource, this.level(), this);
    }

    /**
     * Remove the chest boat from the {@link Level level}
     *
     * @param removalReason {@link Entity.RemovalReason The reason why the entity has been removed}
     */
    public void remove(final Entity.@NotNull RemovalReason removalReason) {
        if (!this.level().isClientSide && removalReason.shouldDestroy()) {
            Containers.dropContents(this.level(), this, this);
        }
        super.remove(removalReason);
    }

    /**
     * Open the chest on {@link Player player} interaction
     *
     * @param player {@link Player The player interacting with the boat}
     * @param interactionHand {@link InteractionHand The hand the player is interacting with}
     * @return {@link InteractionResult The interaction result}
     */
    public @NotNull InteractionResult interact(final @NotNull Player player, final @NotNull InteractionHand interactionHand) {
        if (this.canAddPassenger(player) && !player.isSecondaryUseActive()) {
            return super.interact(player, interactionHand);
        }
        final InteractionResult interactionresult = this.interactWithContainerVehicle(player);
        if (interactionresult.consumesAction()) {
            this.gameEvent(GameEvent.CONTAINER_OPEN, player);
            PiglinAi.angerNearbyPiglins(player, true);
        }
        return interactionresult;
    }

    /**
     * Open the chest GUI
     *
     * @param player {@link Player The player opening the chest}
     */
    public void openCustomInventoryScreen(Player player) {
        player.openMenu(this);
        if (!player.level().isClientSide) {
            this.gameEvent(GameEvent.CONTAINER_OPEN, player);
            PiglinAi.angerNearbyPiglins(player, true);
        }
    }

    /**
     * Get the {@link Item dropped id} when the boat is broken
     *
     * @return {@link Item The boat dropped id}
     */
    public @NotNull Item getDropItem() {
        return switch (this.getBoatType()) {
            case CRIMSON -> MWItems.CRIMSON_CHEST_BOAT.get();
            case WARPED -> MWItems.WARPED_CHEST_BOAT.get();
            case APPLE -> MWItems.APPLE_CHEST_BOAT.get();
            case PALM -> MWItems.PALM_CHEST_RAFT.get();
            case DEAD -> MWItems.DEAD_CHEST_BOAT.get();
            case SCULK -> MWItems.SCULK_CHEST_BOAT.get();
        };
    }

    /**
     * Clear the chest content
     */
    public void clearContent() {
        this.clearChestVehicleContent();
    }

    /**
     * Get the {@link Integer chest content size}
     *
     * @return {@link Integer 27}
     */
    public int getContainerSize() {
        return 27;
    }

    /**
     * Get an id from the chest
     *
     * @param slotId {@link Integer The chest slot id}
     * @return {@link ItemStack The id stack}
     */
    public @NotNull ItemStack getItem(final int slotId) {
        return this.getChestVehicleItem(slotId);
    }

    /**
     * Remove an id from the chest
     *
     * @param slotId {@link Integer The chest slot id}
     * @param amount {@link Integer The amount of items to take}
     * @return {@link ItemStack The removed id stack}
     */
    public @NotNull ItemStack removeItem(final int slotId, final int amount) {
        return this.removeChestVehicleItem(slotId, amount);
    }

    /**
     * Remove an id from the chest without updating
     *
     * @param slotId {@link Integer The chest slot id}
     * @return {@link ItemStack The removed id stack}
     */
    public @NotNull ItemStack removeItemNoUpdate(final int slotId) {
        return this.removeChestVehicleItemNoUpdate(slotId);
    }

    /**
     * Set an id inside the chest
     *
     * @param slotId {@link Integer The chest slot id}
     * @param itemStack {@link ItemStack The id stack to set}
     */
    public void setItem(final int slotId, final @NotNull ItemStack itemStack) {
        this.setChestVehicleItem(slotId, itemStack);
    }

    /**
     * Get a {@link SlotAccess chest slot}
     *
     * @param slotId {@link Integer The chest slot id}
     * @return {@link SlotAccess The chest slot}
     */
    public @NotNull SlotAccess getSlot(final int slotId) {
        return this.getChestVehicleSlot(slotId);
    }

    /**
     * Mark boat changes
     */
    public void setChanged() { }

    /**
     * Check if the boat is still valid
     *
     * @param player {@link Player The player interacting with the boat}
     * @return {@link Boolean True if the boat is still valid}
     */
    public boolean stillValid(final @NotNull Player player) {
        return this.isChestVehicleStillValid(player);
    }

    /**
     * Create the chest menu
     *
     * @param size {@link Integer The menu size}
     * @param inventory {@link Inventory The menu inventory}
     * @param player {@link Player The player to show the menu}
     * @return {@link AbstractContainerMenu The chest menu}
     */
    @Nullable
    public AbstractContainerMenu createMenu(final int size, final @NotNull Inventory inventory, final @NotNull Player player) {
        if (this.lootTable != null && player.isSpectator()) {
            return null;
        }
        this.unpackLootTable(inventory.player);
        return ChestMenu.threeRows(size, inventory, this);
    }

    /**
     * Fill the chest with the {@link LootTable loot table}
     *
     * @param player {@link Player The player opening the chest}
     */
    public void unpackLootTable(final @Nullable Player player) {
        this.unpackChestVehicleLootTable(player);
    }

    /**
     * Get the {@link ResourceLocation chest loot table}
     *
     * @return {@link ResourceLocation The chest loot table}
     */
    @Nullable
    public ResourceLocation getLootTable() {
        return this.lootTable;
    }

    /**
     * Set the {@link ResourceLocation chest loot table}
     *
     * @param lootTable {@link ResourceLocation The chest loot table}
     */
    public void setLootTable(final @Nullable ResourceLocation lootTable) {
        this.lootTable = lootTable;
    }

    /**
     * Get the {@link Long loot table seed}
     *
     * @return {@link Long The loot table seed}
     */
    public long getLootTableSeed() {
        return this.lootTableSeed;
    }

    /**
     * Set the {@link Long loot table seed}
     *
     * @param seed {@link Long The loot table seed}
     */
    public void setLootTableSeed(final long seed) {
        this.lootTableSeed = seed;
    }

    /**
     * Get the {@link NonNullList<ItemStack> chest content}
     *
     * @return {@link NonNullList<ItemStack> The chest content}
     */
    public @NotNull NonNullList<ItemStack> getItemStacks() {
        return this.itemStacks;
    }

    /**
     * Clear the chest content
     */
    public void clearItemStacks() {
        this.itemStacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
    }

    /**
     * Get the {@link LazyOptional<T> chest boat capability}
     *
     * @param capability {@link Capability<T> The capability to check}
     * @param facing {@link Direction The direction to check}
     * @return {@link LazyOptional<T> The chest boat capability, if any}
     * @param <T> {@link T The capability type}
     */
    @Override
    public <T> @NotNull LazyOptional<T> getCapability(final @NotNull Capability<T> capability, final @Nullable Direction facing) {
        return this.isAlive() && ForgeCapabilities.ITEM_HANDLER.equals(capability) ? itemHandler.cast() : super.getCapability(capability, facing);
    }

    /**
     * Invalidate the chest boat capabilities
     */
    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        itemHandler.invalidate();
    }

    /**
     * Initialize the chest boat capabilities
     */
    @Override
    public void reviveCaps() {
        super.reviveCaps();
        itemHandler = LazyOptional.of(() -> new InvWrapper(this));
    }

    /**
     * Close the chest
     *
     * @param player {@link Player The player closing the chest}
     */
    public void stopOpen(final @NotNull Player player) {
        this.level().gameEvent(GameEvent.CONTAINER_CLOSE, this.position(), GameEvent.Context.of(player));
    }

}