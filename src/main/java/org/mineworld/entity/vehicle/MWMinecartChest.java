package org.mineworld.entity.vehicle;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.MinecartChest;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWEntityTypes;
import org.mineworld.item.MWMinecartChestItem;

/**
 * {@link MineWorld MineWorld} {@link MinecartChest Chest Minecart}
 */
public class MWMinecartChest extends MinecartChest {

    /**
     * {@link String The Chest Wood Type NBT Tag Key}
     */
    private final String woodTypeNBTTagKey = "WoodType";
    /**
     * {@link EntityDataAccessor<String> The Chest Wood Type Data Value}
     */
    private static final EntityDataAccessor<String> DATA_TYPE = SynchedEntityData.defineId(MWMinecartChest.class, EntityDataSerializers.STRING);

    /**
     * Constructor. Set the {@link EntityType Entity Type}
     *
     * @param entityType {@link EntityType The Entity Type}
     * @param level {@link Level The level reference}
     */
    public MWMinecartChest(final EntityType<? extends MinecartChest> entityType, final Level level) {
        super(entityType, level);
    }

    /**
     * Constructor. Set the {@link EntityType Entity Type}
     *
     * @param level {@link Level The level reference}
     * @param posX {@link Double The entity X coordinate}
     * @param posY {@link Double The entity Y coordinate}
     * @param posZ {@link Double The entity Z coordinate}
     * @param woodType {@link WoodType The Chest Wood Type}
     */
    public MWMinecartChest(final Level level, final double posX, final double posY, final double posZ, final WoodType woodType) {
        super(MWEntityTypes.CHEST_MINECART.get(), level);
        this.setType(woodType);
        setPos(posX, posY, posZ);
    }

    /**
     * Set the {@link WoodType Chest Wood Type}
     *
     * @param type {@link WoodType The Chest Wood Type}
     */
    public void setType(final WoodType type) {
        this.entityData.set(DATA_TYPE, type.name());
    }

    /**
     * Get the {@link WoodType Chest Wood Type}
     *
     * @return {@link WoodType The Chest Wood Type}
     */
    public WoodType getWoodType() {
        return getWoodType(this.entityData.get(DATA_TYPE));
    }

    /**
     * Get the {@link WoodType Chest Wood Type}
     *
     * @param value {@link String The Chest Wood Type name}
     * @return {@link WoodType The Chest Wood Type}
     */
    private WoodType getWoodType(final String value) {
        return WoodType.values().filter(wood -> wood.name().equalsIgnoreCase(value)).findFirst().orElse(WoodType.OAK);
    }

    /**
     * Get the {@link ItemStack Item Stack} for when a {@link Player Player} middle mouse click the {@link Entity Entity}
     *
     * @return {@link ItemStack The picked Item Stack}
     */
    @Override
    public ItemStack getPickResult() {
        return MWMinecartChestItem.getChestMinecartItem(this.getWoodType()).getDefaultInstance();
    }

    /**
     * Get the {@link BlockState Chest Block State} to display inside the Minecart
     *
     * @return {@link BlockState The Chest Block State}
     */
    @Override
    public @NotNull BlockState getDefaultDisplayBlockState() {
        return MWMinecartChestItem.getChestBlock(this.getWoodType()).defaultBlockState();
    }

    /**
     * Define the {@link Entity Entity} Data
     */
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_TYPE, WoodType.SPRUCE.name());
    }

    /**
     * Read the {@link Entity Entity} Data from the  {@link CompoundTag NBT Tags}
     *
     * @param nbt {@link CompoundTag The Entity NBT Tags}
     */
    protected void readAdditionalSaveData(final @NotNull CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        if (nbt.contains(this.woodTypeNBTTagKey, 8)) {
            this.setType(this.getWoodType(nbt.getString(this.woodTypeNBTTagKey)));
        }
    }

    /**
     * Save the {@link Entity Entity} Data to the {@link CompoundTag NBT Tags}
     *
     * @param nbt {@link CompoundTag The Entity NBT Tags}
     */
    protected void addAdditionalSaveData(final @NotNull CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putString(this.woodTypeNBTTagKey, this.getWoodType().name());
    }

}