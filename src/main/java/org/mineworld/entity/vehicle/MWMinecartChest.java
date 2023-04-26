package org.mineworld.entity.vehicle;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.MinecartChest;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.jetbrains.annotations.NotNull;
import org.mineworld.core.MWBlocks;
import org.mineworld.core.MWEntityTypes;
import org.mineworld.core.MWItems;

/**
 * Implementation class for a {@link MinecartChest MineWorld chest minecart}
 */
public class MWMinecartChest extends MinecartChest {

    /**
     * {@link WoodType The chest wood type}
     */
    private WoodType woodType;
    /**
     * {@link String The chest wood type NBT tag key}
     */
    private final String woodTypeNBTTagKey = "WoodType";
    /**
     * {@link EntityDataAccessor <String> The chest wood type data value}
     */
    private static final EntityDataAccessor<String> DATA_TYPE = SynchedEntityData.defineId(MWMinecartChest.class, EntityDataSerializers.STRING);

    /**
     * Constructor. Set the {@link EntityType entity type}
     *
     * @param entityType {@link EntityType The entity type for this primed tnt}
     * @param level {@link Level The world reference}
     */
    public MWMinecartChest(final EntityType<? extends MinecartChest> entityType, final Level level) {
        super(entityType, level);
    }

    /**
     * Full constructor. Set the {@link BlockPos minecart pos} and {@link WoodType the chest wood type}
     *
     * @param level {@link Level The world reference}
     * @param posX {@link Double The minecart X coordinate}
     * @param posY {@link Double The minecart Y coordinate}
     * @param posZ {@link Double The minecart Z coordinate}
     * @param woodType {@link WoodType The chest wood type}
     */
    public MWMinecartChest(final Level level, final double posX, final double posY, final double posZ, final WoodType woodType) {
        super(MWEntityTypes.CHEST_MINECART.get(), level);
        this.woodType = woodType;
        this.setType(woodType);
        setPos(posX, posY, posZ);
    }

    /**
     * Set the {@link WoodType chest wood type}
     *
     * @param type {@link WoodType The chest wood type}
     */
    public void setType(WoodType type) {
        this.entityData.set(DATA_TYPE, type.name());
    }

    /**
     * Get the {@link WoodType chest wood type}
     *
     * @return {@link WoodType The chest wood type}
     */
    public WoodType getWoodType() {
        return getWoodType(this.entityData.get(DATA_TYPE));
    }

    /**
     * Get the {@link WoodType chest wood type}
     *
     * @param value {@link String The chest wood type name}
     * @return {@link WoodType The chest wood type}
     */
    private WoodType getWoodType(final String value) {
        return WoodType.values().filter(wood -> wood.name().equalsIgnoreCase(value)).findFirst().orElse(WoodType.OAK);
    }

    /**
     * Get the {@link Item dropped item}
     *
     * @return {@link Item The dropped item}
     */
    @Override
    protected @NotNull Item getDropItem() {
        final WoodType type = this.getWoodType();
        if(type.equals(WoodType.SPRUCE)) {
            return MWItems.SPRUCE_CHEST_MINECART.get();
        }
        return Items.CHEST_MINECART;
    }

    /**
     * Get the {@link BlockState block state} to display inside the minecart
     * based on the {@link WoodType chest wood type}
     *
     * @return {@link BlockState The block state to display}
     */
    @Override
    public @NotNull BlockState getDefaultDisplayBlockState() {
        final WoodType type = this.getWoodType();
        if(type.equals(WoodType.SPRUCE)) {
            return MWBlocks.SPRUCE_CHEST.get().defaultBlockState();
        }
        return Blocks.CHEST.defaultBlockState();
    }

    /**
     * Define the chest minecart data
     */
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_TYPE, WoodType.SPRUCE.name());
    }

    /**
     * Read the {@link WoodType chest wood type} from the {@link CompoundTag entity nbt data}
     *
     * @param nbt {@link CompoundTag The entity nbt data}
     */
    protected void readAdditionalSaveData(final @NotNull CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        if (nbt.contains(this.woodTypeNBTTagKey, 8)) {
            this.woodType = this.getWoodType(nbt.getString(this.woodTypeNBTTagKey));
        }
    }

    /**
     * Save the {@link WoodType chest wood type} to the {@link CompoundTag entity nbt data}
     *
     * @param nbt {@link CompoundTag The entity nbt data}
     */
    protected void addAdditionalSaveData(final @NotNull CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putString(this.woodTypeNBTTagKey, this.woodType.name());
    }

}