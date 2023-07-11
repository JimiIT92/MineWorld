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
import net.minecraft.world.level.block.state.properties.WoodType;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlocks;
import org.mineworld.core.MWEntityTypes;
import org.mineworld.core.MWItems;
import org.mineworld.core.MWWoodTypes;
import org.mineworld.helper.PropertyHelper;

import java.util.function.IntFunction;

/**
 * Implementation class for a {@link MineWorld MineWorld} {@link Boat boat}
 */
public class MWBoat extends Boat {

    /**
     * {@link EntityDataAccessor<Integer> The boat data type accessor}
     */
    private static EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(MWBoat.class, EntityDataSerializers.INT);

    /**
     * Constructor. Set the {@link EntityType boat entity type}
     *
     * @param entityType {@link EntityType The boat entity type}
     * @param level {@link Level The level reference}
     */
    public MWBoat(EntityType<? extends Boat> entityType, Level level) {
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
    public MWBoat(Level level, double posX, double posY, double posZ) {
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
    public void setBoatType(MWBoat.Type type) {
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
     * Get the {@link Item dropped id} when the boat is destroyed
     *
     * @return {@link Item The boat dropped id}
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
        };
    }

    /**
     * Save the {@link MWBoat.Type boat type} to the {@link CompoundTag nbt data}
     *
     * @param nbt {@link CompoundTag The boat nbt data}
     */
    protected void addAdditionalSaveData(CompoundTag nbt) {
        nbt.putString("Type", this.getBoatType().getSerializedName());
    }

    /**
     * Read the {@link MWBoat.Type boat type} from the {@link CompoundTag nbt data}
     *
     * @param nbt {@link CompoundTag The boat nbt data}
     */
    protected void readAdditionalSaveData(CompoundTag nbt) {
        if (nbt.contains("Type", 8)) {
            this.setBoatType(MWBoat.Type.byName(nbt.getString("Type")));
        }
    }

    /**
     * Check if the boat is a raft
     *
     * @return {@link Boolean True if is a raft}
     */
    private boolean isRaft() {
        return Type.PALM.equals(this.getBoatType());
    }

    /**
     * Get the {@link Double boat passegner offset}
     *
     * @return {@link Double The boat passegner offset}
     */
    @Override
    public double getPassengersRidingOffset() {
        return isRaft() ? 0.3D : super.getPassengersRidingOffset();
    }

    /**
     * Boat types
     */
    public enum Type implements StringRepresentable {
        CRIMSON(Blocks.CRIMSON_PLANKS, WoodType.CRIMSON),
        WARPED(Blocks.WARPED_PLANKS, WoodType.WARPED),
        APPLE(MWBlocks.APPLE_PLANKS.get(), MWWoodTypes.APPLE),
        PALM(MWBlocks.PALM_PLANKS.get(), MWWoodTypes.PALM),
        DEAD(MWBlocks.DEAD_PLANKS.get(), MWWoodTypes.DEAD);

        /**
         * {@link String The boat type name}
         */
        private String name;
        /**
         * {@link Block The planks representing this boat type}
         */
        private Block planks;
        /**
         * {@link StringRepresentable.EnumCodec The boat type coded}
         */
        public static StringRepresentable.EnumCodec<MWBoat.Type> CODEC = StringRepresentable.fromEnum(MWBoat.Type::values);
        /**
         * {@link IntFunction The boat type by ID function}
         */
        private static IntFunction<MWBoat.Type> BY_ID = ByIdMap.continuous(Enum::ordinal, values(), ByIdMap.OutOfBoundsStrategy.ZERO);

        /**
         * Constructor. Set the boat type properties
         *
         * @param planks {@link Block The planks representing this boat type}
         * @param woodType {@link WoodType The boat wood type}
         */
        Type(Block planks, WoodType woodType) {
            this.name = PropertyHelper.getWoodTypeName(woodType);
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
        public static MWBoat.Type byId(int id) {
            return BY_ID.apply(id);
        }

        /**
         * Get the {@link MWBoat.Type boat type} given its {@link String name}
         *
         * @param name {@link String The boat name}
         * @return {@link MWBoat.Type The boat type}
         */
        public static MWBoat.Type byName(String name) {
            return CODEC.byName(name, CRIMSON);
        }
    }

}