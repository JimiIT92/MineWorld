package org.mineworld.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import org.mineworld.MineWorld;
import org.mineworld.core.MWEntityTypes;

import javax.annotation.Nullable;
import java.util.Locale;

/**
 * Implementation class for a {@link MineWorld MineWorld} {@link PrimedTnt primed tnt}
 */
public class MWPrimedTnt extends PrimedTnt {

    /**
     * {@link EntityDataAccessor<Integer> The tnt fuse time data value}
     */
    private static final EntityDataAccessor<Integer> DATA_FUSE_ID = SynchedEntityData.defineId(MWPrimedTnt.class, EntityDataSerializers.INT);
    /**
     * {@link EntityDataAccessor<String> The tnt type data value}
     */
    private static final EntityDataAccessor<String> DATA_TYPE = SynchedEntityData.defineId(MWPrimedTnt.class, EntityDataSerializers.STRING);
    /**
     * {@link Float Tnt explosion power}
     */
    private float explosionPower;
    /**
     * {@link LivingEntity The tnt igniter}
     */
    @Nullable
    private LivingEntity owner;

    /**
     * Constructor. Set the {@link EntityType entity type}
     *
     * @param entityType {@link EntityType The entity type for this primed tnt}
     * @param level {@link Level The world reference}
     */
    public MWPrimedTnt(final EntityType<? extends PrimedTnt> entityType, final Level level) {
        super(entityType, level);
    }

    /**
     * Full constructor. Set the {@link BlockPos tnt pos} and {@link Float explosion power}
     *
     * @param level {@link Level The world reference}
     * @param posX {@link Double The tnt X coordinate}
     * @param posY {@link Double The tnt Y coordinate}
     * @param posZ {@link Double The tnt Z coordinate}
     * @param igniter {@link LivingEntity The entity who ignited the tnt}
     * @param type {@link Type The primed tnt type}
     */
    public MWPrimedTnt(Level level, double posX, double posY, double posZ, @Nullable LivingEntity igniter, final Type type) {
        super(MWEntityTypes.MW_PRIMED_TNT.get(), level);
        this.explosionPower = type.getExplosionPower();
        this.setPos(posX, posY, posZ);
        final double delta = level.random.nextDouble() * (double)((float)Math.PI * 2F);
        this.setDeltaMovement(-Math.sin(delta) * 0.02D, 0.2F, -Math.cos(delta) * 0.02D);
        this.setFuse(type.fuseTime);
        this.setType(type);
        this.xo = posX;
        this.yo = posY;
        this.zo = posZ;
        this.owner = igniter;
    }

    /**
     * Define the tnt data
     */
    @Override
    protected void defineSynchedData() {
        this.entityData.define(DATA_TYPE, Type.MEGA.name());
        this.entityData.define(DATA_FUSE_ID, 80);
    }

    /**
     * Set the {@link Integer fuse time}
     *
     * @param fuseTime {@link Integer The fuse time}
     */
    public void setFuse(int fuseTime) {
        this.entityData.set(DATA_FUSE_ID, fuseTime);
    }

    /**
     * Set the {@link Type tnt type}
     *
     * @param type {@link Type The tnt type}
     */
    public void setType(Type type) {
        this.entityData.set(DATA_TYPE, type.name());
    }

    /**
     * Get the {@link Integer fuse time}
     *
     * @return {@link Integer The fuse time}
     */
    public int getFuse() {
        return this.entityData.get(DATA_FUSE_ID);
    }

    /**
     * Make the tnt explode
     */
    @Override
    protected void explode() {
        this.level.explode(this, this.getX(), this.getY(0.0625D), this.getZ(), this.explosionPower, Level.ExplosionInteraction.TNT);
    }

    /**
     * Get the {@link LivingEntity tnt igniter}
     *
     * @return {@link LivingEntity The tnt igniter}
     */
    @Override
    @Nullable
    public LivingEntity getOwner() {
        return owner;
    }

    /**
     * Get the {@link Type primed tnt type}
     *
     * @return {@link Type The primed tnt type}
     */
    public Type getTntType() {
        return Type.valueOf(this.entityData.get(DATA_TYPE).toUpperCase(Locale.ROOT));
    }

    /**
     * {@link MineWorld MineWorld} primed tnt types
     */
    public enum Type {
        MEGA(false, 120, 8),
        SUPER(false, 160, 16),
        HYPER(false, 200, 32),
        DISGUISED_GRASS(true, 40, 2),
        DISGUISED_DIRT(true, 40, 2),
        DISGUISED_SAND(true, 40, 2),
        DISGUISED_RED_SAND(true, 40, 2),
        DISGUISED_STONE(true, 40, 2);

        /**
         * {@link Boolean If the tnt type represents a disguised one}
         */
        private final boolean isDisguised;
        /**
         * {@link Integer The tnt fuse time}
         */
        private final int fuseTime;
        /**
         * {@link Integer The tnt explosion power}
         */
        private final int explosionPower;

        /**
         * Constructor. Set if the tnt type is disguised
         *
         * @param isDisguised {@link Boolean If the tnt type is disguised}
         * @param fuseTime {@link Integer The tnt fuse time}
         * @param explosionPower {@link Integer The tnt explosion power}
         */
        Type(final boolean isDisguised, final int fuseTime, final int explosionPower) {
            this.isDisguised = isDisguised;
            this.fuseTime = fuseTime;
            this.explosionPower = explosionPower;
        }

        /**
         * Get if the tnt type is disguised
         *
         * @return {@link Boolean True if the tnt type is disguised}
         */
        public boolean isDisguised() {
            return this.isDisguised;
        }

        /**
         * Get the {@link Integer tnt explosion power}
         *
         * @return {@link Integer The tnt explosion power}
         */
        public int getExplosionPower() {
            return this.explosionPower;
        }
    }
}
