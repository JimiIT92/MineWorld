package org.mineworld.entity.block;

import com.google.common.base.Suppliers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWColors;
import org.mineworld.core.MWEntityTypes;

import javax.annotation.Nullable;
import java.util.Locale;
import java.util.function.Supplier;

/**
 * {@link MineWorld MineWorld} class for a {@link PrimedTnt Primed TNT entity}
 */
public class MWPrimedTnt extends PrimedTnt {

    /**
     * {@link EntityDataAccessor<Integer> The TNT fuse time data value}
     */
    private static final EntityDataAccessor<Integer> DATA_FUSE_ID = SynchedEntityData.defineId(MWPrimedTnt.class, EntityDataSerializers.INT);
    /**
     * {@link EntityDataAccessor<String> The TNT type data value}
     */
    private static final EntityDataAccessor<String> DATA_TYPE = SynchedEntityData.defineId(MWPrimedTnt.class, EntityDataSerializers.STRING);
    /**
     * {@link String The TNT type NBT tag key}
     */
    private final String tntTypeNBTTagKey = "TNTType";
    /**
     * {@link LivingEntity The TNT igniter}
     */
    @Nullable
    private LivingEntity owner;

    /**
     * Constructor. Set the {@link EntityType Entity Type}
     *
     * @param entityType {@link EntityType The Entity Type}
     * @param level {@link Level The level reference}
     */
    public MWPrimedTnt(final EntityType<? extends PrimedTnt> entityType, final Level level) {
        super(entityType, level);
    }

    /**
     * Constructor. Set the {@link BlockPos TNT Block Pos} and {@link Float explosion power}
     *
     * @param level {@link Level The level reference}
     * @param posX {@link Double The TNT X coordinate}
     * @param posY {@link Double The TNT Y coordinate}
     * @param posZ {@link Double The TNT Z coordinate}
     * @param owner {@link LivingEntity The entity who ignited the TNT}
     * @param type {@link Type The TNT type}
     */
    public MWPrimedTnt(final Level level, final double posX, final double posY, final double posZ, final @Nullable LivingEntity owner, final Type type) {
        super(MWEntityTypes.MW_PRIMED_TNT.get(), level);
        this.setPos(posX, posY, posZ);
        final double delta = level.random.nextDouble() * (double)((float)Math.PI * 2F);
        this.setDeltaMovement(-Math.sin(delta) * 0.02D, 0.2F, -Math.cos(delta) * 0.02D);
        this.setFuse(type.fuseTime);
        this.setType(type);
        this.xo = posX;
        this.yo = posY;
        this.zo = posZ;
        this.owner = owner;
    }

    /**
     * Define the TNT data
     */
    @Override
    protected void defineSynchedData() {
        this.entityData.define(DATA_TYPE, Type.MEGA.name());
        this.entityData.define(DATA_FUSE_ID, 80);
    }

    /**
     * Set the {@link Integer TNT fuse time}
     *
     * @param fuseTime {@link Integer The TNT fuse time}
     */
    @Override
    public void setFuse(int fuseTime) {
        this.entityData.set(DATA_FUSE_ID, fuseTime);
    }

    /**
     * Get the {@link Integer TNT fuse time}
     *
     * @return {@link Integer The TNT fuse time}
     */
    @Override
    public int getFuse() {
        return this.entityData.get(DATA_FUSE_ID);
    }

    /**
     * Set the {@link Type TNT Type}
     *
     * @param type {@link Type The TNT Type}
     */
    public void setType(Type type) {
        this.entityData.set(DATA_TYPE, type.name());
    }

    /**
     * Get the {@link Type TNT Type}
     *
     * @return {@link Type The TNT Type}
     */
    public Type getTntType() {
        return getTntType(this.entityData.get(DATA_TYPE));
    }

    /**
     * Get the {@link Type TNT type}
     *
     * @param type {@link String The TNT type name}
     * @return {@link Type The TNT type}
     */
    public Type getTntType(final String type) {
        return Type.valueOf(type.toUpperCase(Locale.ROOT));
    }

    /**
     * Make the TNT explode
     */
    @Override
    protected void explode() {
        this.level().explode(this, this.getX(), this.getY(0.0625D), this.getZ(), this.getTntType().explosionPower, Level.ExplosionInteraction.TNT);
    }

    /**
     * Get the {@link LivingEntity TNT owner}
     *
     * @return {@link LivingEntity The TNT owner}
     */
    @Override
    @Nullable
    public LivingEntity getOwner() {
        return owner;
    }

    /**
     * Read the {@link CompoundTag Entity NBT Data}
     *
     * @param nbt {@link CompoundTag The Entity NBT Data}
     */
    protected void readAdditionalSaveData(final @NotNull CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        if (nbt.contains(this.tntTypeNBTTagKey, 8)) {
            this.setType(this.getTntType(nbt.getString(this.tntTypeNBTTagKey)));
        }
    }

    /**
     * Save the {@link CompoundTag Entity NBT Data}
     *
     * @param nbt {@link CompoundTag The Entity NBT Data}
     */
    protected void addAdditionalSaveData(final @NotNull CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putString(this.tntTypeNBTTagKey, this.getTntType().name());
    }

    /**
     * {@link MineWorld MineWorld} TNT Types
     */
    public enum Type {
        MEGA(120, 8),
        SUPER(160, 16),
        HYPER(200, 32),
        DISGUISED_GRASS(MWColors.GRASS.toText(), Suppliers.memoize(() -> Blocks.GRASS_BLOCK)),
        DISGUISED_DIRT(MWColors.DIRT.toText(), Suppliers.memoize(() -> Blocks.DIRT)),
        DISGUISED_SAND(MWColors.SAND.toText(), Suppliers.memoize(() -> Blocks.SAND)),
        DISGUISED_RED_SAND(MWColors.RED_SAND.toText(), Suppliers.memoize(() -> Blocks.RED_SAND)),
        DISGUISED_STONE(MWColors.STONE.toText(), Suppliers.memoize(() -> Blocks.STONE)),
        DISGUISED_CAKE(MWColors.CAKE.toText(), Suppliers.memoize(() -> Blocks.CAKE));

        /**
         * {@link Boolean If the TNT Type represents a disguised one}
         */
        private final boolean isDisguised;
        /**
         * {@link Integer The TNT fuse time}
         */
        private final int fuseTime;
        /**
         * {@link Integer The TNT explosion power}
         */
        private final int explosionPower;
        /**
         * {@link TextColor The Tooltips text color}
         */
        private final TextColor textColor;
        /**
         * {@link Supplier<Block> The disguised Block Supplier}
         */
        private final Supplier<Block> disguisedBlockSupplier;

        /**
         * Default Constructor. Set a non-disguised TNT properties
         *
         * @param fuseTime {@link Integer The TNT fuse time}
         * @param explosionPower {@link Integer The TNT explosion power}
         */
        Type(final int fuseTime, final int explosionPower) {
            this(false, fuseTime, explosionPower, null, Suppliers.memoize(() -> Blocks.AIR));
        }

        /**
         * Disguised TNT Constructor. Set a disguised TNT properties
         *
         * @param textColor {@link TextColor The Tooltips Text color}
         * @param disguisedBlockSupplier {@link Supplier<Block> The disguised Block Supplier}
         */
        Type(final TextColor textColor, final Supplier<Block> disguisedBlockSupplier) {
            this(true, 40, 2, textColor, disguisedBlockSupplier);
        }

        /**
         * Constructor. Set the TNT properties
         *
         * @param isDisguised {@link Boolean If the TNT Type is disguised}
         * @param fuseTime {@link Integer The TNT fuse time}
         * @param explosionPower {@link Integer The TNT explosion power}
         * @param textColor {@link TextColor The Tooltips Text color}
         * @param disguisedBlockSupplier {@link Supplier<Block> The disguised Block Supplier}
         */
        Type(final boolean isDisguised, final int fuseTime, final int explosionPower, final TextColor textColor, final Supplier<Block> disguisedBlockSupplier) {
            this.isDisguised = isDisguised;
            this.fuseTime = fuseTime;
            this.explosionPower = explosionPower;
            this.textColor = textColor;
            this.disguisedBlockSupplier = disguisedBlockSupplier;
        }

        /**
         * Get if the TNT Type is disguised
         *
         * @return {@link Boolean True if the TNT Type is disguised}
         */
        public boolean isDisguised() {
            return this.isDisguised;
        }

        /**
         * Get the {@link Integer TNT explosion power}
         *
         * @return {@link Integer The TNT explosion power}
         */
        public int getExplosionPower() {
            return this.explosionPower;
        }

        /**
         * Get the {@link TextColor Tooltips Text color}
         * @return {@link TextColor The Tooltips Text color}
         */
        public TextColor getTextColor() {
            return this.textColor;
        }

        /**
         * Get the {@link MutableComponent disguised Block name}
         *
         * @return {@link MutableComponent The disguised Block name}
         */
        public MutableComponent getDisguisedBlockName() {
            return this.disguisedBlockSupplier.get().getName();
        }

    }

}