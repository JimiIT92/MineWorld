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
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlockEntityTypes;

/**
 * {@link MineWorld MineWorld} {@link RandomizableContainerBlockEntity Gift Block Entity}
 */
public class GiftBlockEntity extends RandomizableContainerBlockEntity implements LidBlockEntity {

    /**
     * {@link NonNullList<ItemStack> The Gift Content}
     */
    private NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);
    /**
     * {@link ChestLidController The Gift Lid Controller}
     */
    private final ChestLidController giftLidController = new ChestLidController();
    /**
     * {@link IItemHandlerModifiable The Gift Item Handler}
     */
    private LazyOptional<IItemHandlerModifiable> giftHandler;

    /**
     * Constructor. Set the Block Entity properties
     *
     * @param blockPos {@link BlockPos The Block Entity Block POs}
     * @param blockState {@link BlockState The Block State for the Block Entity}
     */
    public GiftBlockEntity(final BlockPos blockPos, final BlockState blockState) {
        super(MWBlockEntityTypes.GIFT.get(), blockPos, blockState);
    }

    /**
     * Get the {@link Integer Container Size}
     *
     * @return {@link Integer 1}
     */
    @Override
    public int getContainerSize() {
        return 1;
    }

    /**
     * Get the {@link Component Container default name}
     *
     * @return {@link Component#empty() Empty Component}
     */
    @Override
    protected @NotNull Component getDefaultName() {
        return Component.empty();
    }

    /**
     * Create the {@link AbstractContainerMenu Container Menu}
     *
     * @param id {@link Integer The Menu Id}
     * @param inventory {@link Inventory The Menu Inventory}
     * @return {@link AbstractContainerMenu Null}
     */
    @Override
    protected @NotNull AbstractContainerMenu createMenu(final int id, final @NotNull Inventory inventory) {
        return null;
    }

    /**
     * Load the Container content from the {@link CompoundTag NBT Tags}
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
     * Save the Container content to the {@link CompoundTag NBT Tags}
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
     * Get the {@link NonNullList<ItemStack> Container content}
     *
     * @return {@link NonNullList<ItemStack> The Container content}
     */
    protected @NotNull NonNullList<ItemStack> getItems() {
        return this.items;
    }

    /**
     * Set the {@link NonNullList<ItemStack> Container content}
     *
     * @param items {@link NonNullList<ItemStack> The Container content}
     */
    public void setItems(final @NotNull NonNullList<ItemStack> items) {
        this.items = items;
    }

    /**
     * Swap the content of two {@link GiftBlockEntity Gifts}
     *
     * @param source {@link GiftBlockEntity The source Gift}
     * @param destination {@link GiftBlockEntity The destination Gift}
     */
    public static void swapContents(final GiftBlockEntity source, final GiftBlockEntity destination) {
        final NonNullList<ItemStack> nonnulllist = source.getItems();
        source.setItems(destination.getItems());
        destination.setItems(nonnulllist);
    }

    /**
     * Set the {@link BlockState Gift Block State}
     *
     * @param state The {@link BlockState Gift Block State}
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
     * Get the {@link T Container Capability}
     *
     * @param capability {@link Capability<T> The Capability to check}
     * @param side {@link Direction The Side to check from}
     * @return {@link LazyOptional<T> The Container Capability}
     * @param <T> The Capability type
     */
    @Override
    public <T> @NotNull LazyOptional<T> getCapability(final @NotNull Capability<T> capability, final Direction side) {
        if (!this.remove && capability == ForgeCapabilities.ITEM_HANDLER) {
            if (this.giftHandler == null)
                this.giftHandler = LazyOptional.of(this::createHandler);
            return this.giftHandler.cast();
        }
        return super.getCapability(capability, side);
    }

    /**
     * Get the {@link IItemHandlerModifiable Container Item Handler}
     *
     * @return The {@link IItemHandlerModifiable Container Item Handler}
     */
    private IItemHandlerModifiable createHandler() {
        return new InvWrapper(this);
    }

    /**
     * Invalidate the Container Capabilities
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
     * Get the {@link Float Gift Lid openness}
     *
     * @param angle {@link Float The Lid angle}
     * @return {@link Float 0F}
     */
    @Override
    public float getOpenNess(final float angle) {
        return 0F;
    }

}