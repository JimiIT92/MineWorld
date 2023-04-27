package org.mineworld.entity.vehicle;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWEntityTypes;
import org.mineworld.core.MWItems;

import java.util.function.IntFunction;

/**
 * Implementation class for a {@link MineWorld MineWorld} {@link Boat boat}
 */
public class MWBoat extends Boat {

    /**
     * {@link EntityDataAccessor<Integer> The boat data type accessor}
     */
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(MWBoat.class, EntityDataSerializers.INT);

    /**
     * Constructor. Set the {@link EntityType boat entity type}
     *
     * @param entityType {@link EntityType The boat entity type}
     * @param level {@link Level The level reference}
     */
    public MWBoat(final EntityType<? extends Boat> entityType, final Level level) {
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
    public MWBoat(final Level level, final double posX, final double posY, final double posZ) {
        this(MWEntityTypes.BOAT.get(), level);
        this.setPos(posX, posY, posZ);
        this.xo = posX;
        this.yo = posY;
        this.zo = posZ;
    }

    /**
     * Define the entity synced data
     */
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE, Type.CRIMSON.ordinal());
    }

    /**
     * Set the {@link MWBoat.Type boat type}
     *
     * @param type {@link MWBoat.Type The boat type}
     */
    public void setBoatType(final MWBoat.Type type) {
        this.entityData.set(DATA_ID_TYPE, type.ordinal());
    }

    /**
     * Get the {@link MWBoat.Type boat type}
     *
     * @return {@link MWBoat.Type The boat type}
     */
    public MWBoat.Type getBoatType() {
        return MWBoat.Type.byId(this.entityData.get(DATA_ID_TYPE));
    }

    /**
     * Get the {@link Item dropped item} when the boat is destroyed
     *
     * @return {@link Item The boat dropped item}
     */
    @Override
    @NotNull
    public Item getDropItem() {
        return switch (this.getBoatType()) {
            case CRIMSON -> MWItems.CRIMSON_BOAT.get();
            case WARPED -> MWItems.WARPED_BOAT.get();
        };
    }

    /**
     * Save the {@link MWBoat.Type boat type} to the {@link CompoundTag nbt data}
     *
     * @param nbt {@link CompoundTag The boat nbt data}
     */
    protected void addAdditionalSaveData(final CompoundTag nbt) {
        nbt.putString("Type", this.getBoatType().getSerializedName());
    }

    /**
     * Read the {@link MWBoat.Type boat type} from the {@link CompoundTag nbt data}
     *
     * @param nbt {@link CompoundTag The boat nbt data}
     */
    protected void readAdditionalSaveData(final CompoundTag nbt) {
        if (nbt.contains("Type", 8)) {
            this.setBoatType(MWBoat.Type.byName(nbt.getString("Type")));
        }
    }

    /**
     * Boat types
     */
    public enum Type implements StringRepresentable {
        CRIMSON(Blocks.CRIMSON_PLANKS, "crimson"),
        WARPED(Blocks.WARPED_PLANKS, "warped");

        /**
         * {@link String The boat type name}
         */
        private final String name;
        /**
         * {@link Block The planks representing this boat type}
         */
        private final Block planks;
        /**
         * {@link StringRepresentable.EnumCodec The boat type coded}
         */
        public static final StringRepresentable.EnumCodec<MWBoat.Type> CODEC = StringRepresentable.fromEnum(MWBoat.Type::values);
        /**
         * {@link IntFunction The boat type by ID function}
         */
        private static final IntFunction<MWBoat.Type> BY_ID = ByIdMap.continuous(Enum::ordinal, values(), ByIdMap.OutOfBoundsStrategy.ZERO);

        /**
         * Constructor. Set the boat type properties
         *
         * @param planks {@link Block The planks representing this boat type}
         * @param name {@link String The boat type name}
         */
        Type(final Block planks, final String name) {
            this.name = name;
            this.planks = planks;
        }

        /**
         * Get the {@link String boat serialized name}
         *
         * @return {@link String The boat serialized name}
         */
        public @NotNull String getSerializedName() {
            return this.name;
        }

        /**
         * Get the {@link String boat name}
         *
         * @return {@link String The boat name}
         */
        public String getName() {
            return this.name;
        }

        /**
         * Get the {@link Block planks} dropped when the boat is destroyed
         *
         * @return {@link Block The boat planks}
         */
        public Block getPlanks() {
            return this.planks;
        }

        /**
         * Get the {@link String boat name}
         *
         * @return {@link String The boat name}
         */
        public String toString() {
            return this.name;
        }

        /**
         * Get the {@link MWBoat.Type boat type} given its {@link Integer id}
         *
         * @param id {@link Integer The boat id}
         * @return {@link MWBoat.Type The boat type}
         */
        public static MWBoat.Type byId(final int id) {
            return BY_ID.apply(id);
        }

        /**
         * Get the {@link MWBoat.Type boat type} given its {@link String name}
         *
         * @param name {@link String The boat name}
         * @return {@link MWBoat.Type The boat type}
         */
        public static MWBoat.Type byName(final String name) {
            return CODEC.byName(name, CRIMSON);
        }
    }

}