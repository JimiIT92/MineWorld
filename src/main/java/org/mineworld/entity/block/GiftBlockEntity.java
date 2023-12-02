package org.mineworld.entity.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.ChestLidController;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.NotNull;
import org.mineworld.block.GiftBlock;
import org.mineworld.core.MWBlockEntityTypes;

/**
 * Implementation class for the {@link GiftBlock gift block entity}
 */
public class GiftBlockEntity extends RandomizableContainerBlockEntity implements LidBlockEntity {

    /**
     * {@link NonNullList<ItemStack> The gift items}
     */
    private NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);
    /**
     * {@link ChestLidController The gift lid controller}
     */
    private final ChestLidController giftLidController = new ChestLidController();
    /**
     * {@link IItemHandlerModifiable The gift handler}
     */
    private LazyOptional<IItemHandlerModifiable> giftHandler;

    /**
     * Constructor. Set the block entity type
     *
     * @param pos {@link BlockPos The block entity pos}
     * @param state {@link BlockState The block entity state}
     */
    public GiftBlockEntity(final BlockPos pos, final BlockState state) {
        super(MWBlockEntityTypes.GIFT.get(), pos, state);
    }

    /**
     * Get the {@link Integer container size}
     *
     * @return {@link Integer 1}
     */
    @Override
    public int getContainerSize() {
        return 1;
    }

    /**
     * Get the {@link Component gift container name}
     *
     * @return {@link Component#empty() Empty component}
     */
    @Override
    protected @NotNull Component getDefaultName() {
        return Component.empty();
    }

    /**
     * Create the gift menu
     *
     * @param id {@link Integer The menu ID}
     * @param inventory {@link Inventory The menu inventory}
     * @return {@link AbstractContainerMenu null}
     */
    @Override
    protected @NotNull AbstractContainerMenu createMenu(final int id, final @NotNull Inventory inventory) {
        return null;
    }

    /**
     * Load the items from {@link CompoundTag NBT Tags}
     *
     * @param nbt {@link CompoundTag The NBT Tags}
     */
    public void load(final @NotNull CompoundTag nbt) {
        super.load(nbt);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(nbt)) {
            ContainerHelper.loadAllItems(nbt, this.items);
        }

    }

    /**
     * Save items to {@link CompoundTag NBT Tags}
     *
     * @param nbt {@link CompoundTag The NBT Tags}
     */
    protected void saveAdditional(final @NotNull CompoundTag nbt) {
        super.saveAdditional(nbt);
        if (!this.trySaveLootTable(nbt)) {
            ContainerHelper.saveAllItems(nbt, this.items);
        }
    }

    /**
     * Get the {@link NonNullList<ItemStack> gift items}
     *
     * @return {@link NonNullList<ItemStack> The gift items}
     */
    protected @NotNull NonNullList<ItemStack> getItems() {
        return this.items;
    }

    /**
     * Set the {@link NonNullList<ItemStack> git items}
     *
     * @param items {@link NonNullList<ItemStack> The gift items}
     */
    protected void setItems(final @NotNull NonNullList<ItemStack> items) {
        this.items = items;
    }

    /**
     * Swap the content of two gifts
     *
     * @param source {@link GiftBlockEntity The source block entity}
     * @param destination {@link GiftBlockEntity The destination block entity}
     */
    public static void swapContents(final GiftBlockEntity source, final GiftBlockEntity destination) {
        NonNullList<ItemStack> nonnulllist = source.getItems();
        source.setItems(destination.getItems());
        destination.setItems(nonnulllist);
    }

    /**
     * Set the {@link BlockState gift BlockState}
     *
     * @param state The {@link BlockState gift BlockState}
     */
    @Override
    public void setBlockState(final @NotNull BlockState state) {
        super.setBlockState(state);
        if (this.giftHandler != null) {
            net.minecraftforge.common.util.LazyOptional<?> oldHandler = this.giftHandler;
            this.giftHandler = null;
            oldHandler.invalidate();
        }
    }

    /**
     * Get the {@link T gift capability}
     *
     * @param cap {@link Capability<T> The capability to check}
     * @param side {@link Direction The Side to check from},
     *   <strong>CAN BE NULL</strong>. Null is defined to represent 'internal' or 'self'
     * @return {@link LazyOptional<T> The gift capability}, if any
     * @param <T> The capability type
     */
    @Override
    public <T> @NotNull LazyOptional<T> getCapability(final @NotNull Capability<T> cap, final Direction side) {
        if (!this.remove && cap == ForgeCapabilities.ITEM_HANDLER) {
            if (this.giftHandler == null)
                this.giftHandler = LazyOptional.of(this::createHandler);
            return this.giftHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    /**
     * Get the {@link IItemHandlerModifiable gift handler}
     *
     * @return The {@link IItemHandlerModifiable gift handler}
     */
    private IItemHandlerModifiable createHandler() {
        return new InvWrapper(this);
    }

    /**
     * Invalidate the gift capabilities
     */
    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        if (giftHandler != null) {
            giftHandler.invalidate();
            giftHandler = null;
        }
    }

    /**
     * Get the gift lid openness
     *
     * @param angle {@link Float The lid angle}
     * @return {@link Float 0}
     */
    @Override
    public float getOpenNess(final float angle) {
        return 0;
    }

}