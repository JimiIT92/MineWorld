package org.mineworld.entity.vehicle;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlocks;
import org.mineworld.core.MWEntityTypes;
import org.mineworld.core.MWItems;
import org.mineworld.core.MWWoodTypes;
import org.mineworld.entity.block.MWPrimedTnt;
import org.mineworld.helper.ResourceHelper;

import java.util.function.IntFunction;
import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} {@link Boat Boat Entity}
 */
public class MWBoat extends Boat {

    /**
     * {@link String The Boat Type NBT tag key}
     */
    private final String boatTypeNBTTagKey = "Type";
    /**
     * {@link EntityDataAccessor<Integer> The Boat Type data value}
     */
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(MWBoat.class, EntityDataSerializers.INT);

    /**
     * Constructor. Set the {@link EntityType Entity Type}
     *
     * @param entityType {@link EntityType The Entity Type}
     * @param level {@link Level The level reference}
     */
    public MWBoat(final EntityType<? extends Boat> entityType, final Level level) {
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
    public MWBoat(final Level level, final double posX, final double posY, final double posZ) {
        this(MWEntityTypes.BOAT.get(), level);
        this.setPos(posX, posY, posZ);
        this.xo = posX;
        this.yo = posY;
        this.zo = posZ;
    }

    /**
     * Define the {@link Entity Entity} Data
     */
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE, Type.CRIMSON.ordinal());
    }

    /**
     * Set the {@link MWBoat.Type Boat Type}
     *
     * @param type {@link MWBoat.Type The Boat Type}
     */
    public void setBoatType(final MWBoat.Type type) {
        this.entityData.set(DATA_ID_TYPE, type.ordinal());
    }

    /**
     * Get the {@link MWBoat.Type Boat Type}
     *
     * @return {@link MWBoat.Type The Boat Type}
     */
    public MWBoat.Type getBoatType() {
        return MWBoat.Type.byId(this.entityData.get(DATA_ID_TYPE));
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
            case CRIMSON -> MWItems.CRIMSON_BOAT.get();
            case WARPED -> MWItems.WARPED_BOAT.get();
            case APPLE -> MWItems.APPLE_BOAT.get();
            case PALM -> MWItems.PALM_RAFT.get();
            case DEAD -> MWItems.DEAD_BOAT.get();
            case SCULK -> MWItems.SCULK_BOAT.get();
        };
    }

    /**
     * Save the {@link CompoundTag Entity NBT Data}
     *
     * @param nbt {@link CompoundTag The Entity NBT Data}
     */
    protected void addAdditionalSaveData(final CompoundTag nbt) {
        nbt.putString(this.boatTypeNBTTagKey, this.getBoatType().getSerializedName());
    }

    /**
     * Read the {@link CompoundTag Entity NBT Data}
     *
     * @param nbt {@link CompoundTag The Entity NBT Data}
     */
    protected void readAdditionalSaveData(final CompoundTag nbt) {
        if (nbt.contains(this.boatTypeNBTTagKey, 8)) {
            this.setBoatType(MWBoat.Type.byName(nbt.getString(this.boatTypeNBTTagKey)));
        }
    }

    /**
     * Check if the Boat is a Raft
     *
     * @return {@link Boolean True if is a Raft}
     */
    private boolean isRaft() {
        return Type.PALM.equals(this.getBoatType());
    }

    /**
     * Get the {@link Vector3f Boat Passenger attachment point}
     *
     * @return {@link Vector3f The Boat Passenger attachment point}
     */
    @Override
    protected @NotNull Vector3f getPassengerAttachmentPoint(final @NotNull Entity entity, final @NotNull EntityDimensions size, final float par3) {
        final Vector3f attachmentPoint = super.getPassengerAttachmentPoint(entity, size, par3);
        return this.isRaft() ? new Vector3f(attachmentPoint.x, size.height * 0.8888889F, attachmentPoint.z) : attachmentPoint;
    }

    /**
     * {@link MineWorld MineWorld} Boat Types
     */
    public enum Type implements StringRepresentable {
        CRIMSON(() -> Blocks.CRIMSON_PLANKS, ResourceHelper.woodName(WoodType.CRIMSON)),
        WARPED(() -> Blocks.WARPED_PLANKS, ResourceHelper.woodName(WoodType.WARPED)),
        APPLE(MWBlocks.APPLE_PLANKS, MWWoodTypes.MWWoodTypeNames.APPLE),
        PALM(MWBlocks.PALM_PLANKS, MWWoodTypes.MWWoodTypeNames.PALM),
        DEAD(MWBlocks.DEAD_PLANKS, MWWoodTypes.MWWoodTypeNames.DEAD),
        SCULK(MWBlocks.SCULK_PLANKS, MWWoodTypes.MWWoodTypeNames.SCULK);

        /**
         * {@link String The Boat Type name}
         */
        private final String name;
        /**
         * {@link Supplier<Block> The Supplier for the Planks corresponding to this Boat Type}
         */
        private final Supplier<Block> planksSupplier;
        /**
         * {@link StringRepresentable.EnumCodec The Boat Type Codec}
         */
        public static final StringRepresentable.EnumCodec<MWBoat.Type> CODEC = StringRepresentable.fromEnum(MWBoat.Type::values);
        /**
         * {@link IntFunction The Boat Type by ID function}
         */
        private static final IntFunction<MWBoat.Type> BY_ID = ByIdMap.continuous(Enum::ordinal, values(), ByIdMap.OutOfBoundsStrategy.ZERO);

        /**
         * Constructor. Set the Boat Type Properties
         *
         * @param planksSupplier {@link Supplier<Block> The Supplier for the Planks corresponding to this Boat Type}
         * @param woodName {@link String The Boat Wood name}
         */
        Type(final Supplier<Block> planksSupplier, final String woodName) {
            this.name = woodName;
            this.planksSupplier = planksSupplier;
        }

        /**
         * Get the {@link String Boat Serialized name}
         *
         * @return {@link String The Boat Serialized name}
         */
        public @NotNull String getSerializedName() {
            return this.name;
        }

        /**
         * Get the {@link String Boat name}
         *
         * @return {@link String The Boat name}
         */
        public String getName() {
            return this.name;
        }

        /**
         * Get the {@link Block Planks} dropped when the Boat is destroyed
         *
         * @return {@link Block The Boat Planks}
         */
        public Block getPlanksSupplier() {
            return this.planksSupplier.get();
        }

        /**
         * Get the {@link String Boat name}
         *
         * @return {@link String The Boat name}
         */
        public String toString() {
            return this.name;
        }

        /**
         * Get the {@link MWBoat.Type Boat Type} given its {@link Integer ID}
         *
         * @param id {@link Integer The Boat Type ID}
         * @return {@link MWBoat.Type The Boat Type}
         */
        public static MWBoat.Type byId(final int id) {
            return BY_ID.apply(id);
        }

        /**
         * Get the {@link MWBoat.Type Boat Type} given its {@link String name}
         *
         * @param name {@link String The Boat Type name}
         * @return {@link MWBoat.Type The Boat type}
         */
        public static MWBoat.Type byName(final String name) {
            return CODEC.byName(name, CRIMSON);
        }
    }

}