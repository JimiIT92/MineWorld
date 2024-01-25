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
import org.jetbrains.annotations.Nullable;
import org.mineworld.MineWorld;
import org.mineworld.core.MWEntityTypes;
import org.mineworld.core.MWItems;
import org.mineworld.entity.MWPrimedTnt;

/**
 * {@link MineWorld MineWorld} {@link ChestBoat Chest Boat Entity}
 */
public class MWChestBoat extends MWBoat implements HasCustomInventoryScreen, ContainerEntity {

    /**
     * {@link Integer The Chest Boat Container size}
     */
    private static final int CONTAINER_SIZE = 27;
    /**
     * {@link NonNullList<ItemStack> The Chest Boat content}
     */
    private NonNullList<ItemStack> itemStacks = NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY);
    /**
     * {@link ResourceLocation The Chest Boat Loot Table}
     */
    @Nullable
    private ResourceLocation lootTable;
    /**
     * {@link Long The Chest Boat Loot Table seed}
     */
    private long lootTableSeed;
    /**
     * {@link LazyOptional<InvWrapper> The Chest Boat Item Handler}
     */
    private LazyOptional<?> itemHandler = LazyOptional.of(() -> new InvWrapper(this));

    /**
     * Constructor. Set the {@link EntityType Entity Type}
     *
     * @param entityType {@link EntityType The Entity Type}
     * @param level {@link Level The level reference}
     */
    public MWChestBoat(final EntityType<? extends Boat> entityType, final Level level) {
        super(entityType, level);
    }

    /**
     * Constructor. Set the {@link BlockPos Minecart Block Pos} and {@link MWPrimedTnt.Type the TNT Type}
     *
     * @param level {@link Level The level reference}
     * @param posX {@link Double The minecart X coordinate}
     * @param posY {@link Double The minecart Y coordinate}
     * @param posZ {@link Double The minecart Z coordinate}
     */
    public MWChestBoat(final Level level, final double posX, final double posY, final double posZ) {
        this(MWEntityTypes.CHEST_BOAT.get(), level);
        this.setPos(posX, posY, posZ);
        this.xo = posX;
        this.yo = posY;
        this.zo = posZ;
    }

    /**
     * Get the {@link Float Passenger X Offset}
     *
     * @return {@link Float 0.15F}
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
     * Save the {@link CompoundTag Entity NBT Data}
     *
     * @param nbt {@link CompoundTag The Entity NBT Data}
     */
    protected void addAdditionalSaveData(final CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        this.addChestVehicleSaveData(nbt);
    }

    /**
     * Read the {@link CompoundTag Entity NBT Data}
     *
     * @param nbt {@link CompoundTag The Entity NBT Data}
     */
    protected void readAdditionalSaveData(final CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.readChestVehicleSaveData(nbt);
    }

    /**
     * Drop the Chest Content when the Boat is destroyed
     *
     * @param damageSource {@link DamageSource The Damage Source that destroyed the Boat}
     */
    public void destroy(final @NotNull DamageSource damageSource) {
        super.destroy(damageSource);
        this.chestVehicleDestroyed(damageSource, this.level(), this);
    }

    /**
     * Remove the Chest Boat from the {@link Level level}
     *
     * @param removalReason {@link Entity.RemovalReason The reason why the Chest Boat has been removed}
     */
    public void remove(final Entity.@NotNull RemovalReason removalReason) {
        if (!this.level().isClientSide && removalReason.shouldDestroy()) {
            Containers.dropContents(this.level(), this, this);
        }
        super.remove(removalReason);
    }

    /**
     * Interact with the {@link Entity Entity}
     *
     * @param player {@link Player The Player interacting with the Entity}
     * @param hand {@link InteractionHand The hand the Player is interacting with}
     * @return {@link InteractionResult The interaction result}
     */
    public @NotNull InteractionResult interact(final @NotNull Player player, final @NotNull InteractionHand hand) {
        if (this.canAddPassenger(player) && !player.isSecondaryUseActive()) {
            return super.interact(player, hand);
        }
        final InteractionResult interactionresult = this.interactWithContainerVehicle(player);
        if (interactionresult.consumesAction()) {
            this.gameEvent(GameEvent.CONTAINER_OPEN, player);
            PiglinAi.angerNearbyPiglins(player, true);
        }
        return interactionresult;
    }

    /**
     * Open the Chest Screen
     *
     * @param player {@link Player The Player opening the Chest}
     */
    public void openCustomInventoryScreen(final Player player) {
        player.openMenu(this);
        if (!player.level().isClientSide) {
            this.gameEvent(GameEvent.CONTAINER_OPEN, player);
            PiglinAi.angerNearbyPiglins(player, true);
        }
    }

    /**
     * Get the {@link Item dropped Item Stack}
     *
     * @return {@link Item The dropped Item Stack}
     */
    @Override
    @NotNull
    public Item getDropItem() {
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
     * Clear the Chest Content
     */
    public void clearContent() {
        this.clearChestVehicleContent();
    }

    /**
     * Get the {@link Integer Container Size}
     *
     * @return {@link #CONTAINER_SIZE 27}
     */
    public int getContainerSize() {
        return CONTAINER_SIZE;
    }

    /**
     * Get an {@link Item Item} from the Chest
     *
     * @param slotId {@link Integer The Chest Slot Id}
     * @return {@link ItemStack The Item Stack}
     */
    public @NotNull ItemStack getItem(final int slotId) {
        return this.getChestVehicleItem(slotId);
    }

    /**
     * Remove an {@link Item Item} from the Chest
     *
     * @param slotId {@link Integer The Chest Slot Id}
     * @param amount {@link Integer The amount of Items to take}
     * @return {@link ItemStack The removed Item Stack}
     */
    public @NotNull ItemStack removeItem(final int slotId, final int amount) {
        return this.removeChestVehicleItem(slotId, amount);
    }

    /**
     * Remove an {@link Item Item} from the Chest without causing updates
     *
     * @param slotId {@link Integer The Chest Slot Id}
     * @return {@link ItemStack The removed Item Stack}
     */
    public @NotNull ItemStack removeItemNoUpdate(final int slotId) {
        return this.removeChestVehicleItemNoUpdate(slotId);
    }

    /**
     * Set an {@link Item Item} inside the Chest
     *
     * @param slotId {@link Integer The Chest Slot Id}
     * @param itemStack {@link ItemStack The Item Stack to set}
     */
    public void setItem(final int slotId, final @NotNull ItemStack itemStack) {
        this.setChestVehicleItem(slotId, itemStack);
    }

    /**
     * Get a {@link SlotAccess Chest Slot}
     *
     * @param slotId {@link Integer The Chest Slot Id}
     * @return {@link SlotAccess The Chest Slot}
     */
    public @NotNull SlotAccess getSlot(final int slotId) {
        return this.getChestVehicleSlot(slotId);
    }

    /**
     * Set the {@link Entity Entity} as changed
     */
    public void setChanged() { }

    /**
     * Check if a {@link Player Player} can still interact with the {@link Entity Entity}
     *
     * @param player {@link Player The Player interacting with the Entity}
     * @return {@link Boolean True if the Entity can still be interacted}
     */
    public boolean stillValid(final @NotNull Player player) {
        return this.isChestVehicleStillValid(player);
    }

    /**
     * Create the {@link AbstractContainerMenu Container Menu}
     *
     * @param size {@link Integer The Menu size}
     * @param inventory {@link Inventory The Menu Inventory}
     * @param player {@link Player The Player opening the Menu}
     * @return {@link ChestMenu The Chest Menu}
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
     * Fill the Chest with a {@link LootTable Loot Table}
     *
     * @param player {@link Player The Player opening the Chest}
     */
    public void unpackLootTable(final @Nullable Player player) {
        this.unpackChestVehicleLootTable(player);
    }

    /**
     * Get the {@link ResourceLocation Chest Loot Table}
     *
     * @return {@link ResourceLocation The Chest Loot Table}
     */
    @Nullable
    public ResourceLocation getLootTable() {
        return this.lootTable;
    }

    /**
     * Set the {@link ResourceLocation Chest Loot Table}
     *
     * @param lootTable {@link ResourceLocation The Chest Loot Table}
     */
    public void setLootTable(final @Nullable ResourceLocation lootTable) {
        this.lootTable = lootTable;
    }

    /**
     * Get the {@link Long Chest Loot Table Seed}
     *
     * @return {@link Long The Chest Loot Table Seed}
     */
    public long getLootTableSeed() {
        return this.lootTableSeed;
    }

    /**
     * Set the {@link Long Chest Loot Table Seed}
     *
     * @param seed {@link Long The Chest Loot Table Seed}
     */
    public void setLootTableSeed(final long seed) {
        this.lootTableSeed = seed;
    }

    /**
     * Get the {@link NonNullList<ItemStack> Chest content}
     *
     * @return {@link NonNullList<ItemStack> The Chest content}
     */
    public @NotNull NonNullList<ItemStack> getItemStacks() {
        return this.itemStacks;
    }

    /**
     * Clear the Chest content
     */
    public void clearItemStacks() {
        this.itemStacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
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
        return this.isAlive() && ForgeCapabilities.ITEM_HANDLER.equals(capability) ? itemHandler.cast() : super.getCapability(capability, side);
    }

    /**
     * Invalidate the Container Capabilities
     */
    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        itemHandler.invalidate();
    }

    /**
     * Initialize the Chest Boat Capabilities
     */
    @Override
    public void reviveCaps() {
        super.reviveCaps();
        itemHandler = LazyOptional.of(() -> new InvWrapper(this));
    }

    /**
     * Close the Chest
     *
     * @param player {@link Player The Player closing the Chest}
     */
    public void stopOpen(final @NotNull Player player) {
        this.level().gameEvent(GameEvent.CONTAINER_CLOSE, this.position(), GameEvent.Context.of(player));
    }

}