package org.mineworld.entity.vehicle;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.MinecartTNT;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineworld.core.MWBlocks;
import org.mineworld.core.MWEntityTypes;
import org.mineworld.entity.MWPrimedTnt;

import java.util.Locale;

/**
 * Implementation class for a {@link MinecartTNT MineWorld tnt minecart}
 */
public class MWMinecartTNT extends MinecartTNT {

    /**
     * {@link MWPrimedTnt.Type The tnt type}
     */
    private MWPrimedTnt.Type type;
    /**
     * {@link EntityDataAccessor <String> The tnt type data value}
     */
    private static final EntityDataAccessor<String> DATA_TYPE = SynchedEntityData.defineId(MWMinecartTNT.class, EntityDataSerializers.STRING);

    /**
     * Constructor. Set the {@link EntityType entity type}
     *
     * @param entityType {@link EntityType The entity type for this primed tnt}
     * @param level {@link Level The world reference}
     */
    public MWMinecartTNT(final EntityType<? extends MinecartTNT> entityType, final Level level) {
        super(entityType, level);
    }

    /**
     * Full constructor. Set the {@link BlockPos tnt pos} and {@link MWPrimedTnt.Type tnt type}
     *
     * @param level {@link Level The world reference}
     * @param posX {@link Double The tnt X coordinate}
     * @param posY {@link Double The tnt Y coordinate}
     * @param posZ {@link Double The tnt Z coordinate}
     * @param type {@link MWPrimedTnt.Type The primed tnt type}
     */
    public MWMinecartTNT(final Level level, final double posX, final double posY, final double posZ, final MWPrimedTnt.Type type) {
        super(MWEntityTypes.TNT_MINECART.get(), level);
        this.type = type;
        this.setType(type);
        setPos(posX, posY, posZ);
    }

    /**
     * Set the {@link MWPrimedTnt.Type tnt type}
     *
     * @param type {@link MWPrimedTnt.Type The tnt type}
     */
    public void setType(MWPrimedTnt.Type type) {
        this.entityData.set(DATA_TYPE, type.name());
    }

    /**
     * Get the {@link MWPrimedTnt.Type primed tnt type}
     *
     * @return {@link MWPrimedTnt.Type The primed tnt type}
     */
    public MWPrimedTnt.Type getTntType() {
        return MWPrimedTnt.Type.valueOf(this.entityData.get(DATA_TYPE).toUpperCase(Locale.ROOT));
    }

    /**
     * Get the {@link BlockState block state} to display inside the minecart
     * based on the {@link MWPrimedTnt.Type tnt type}
     *
     * @return {@link BlockState The block state to display}
     */
    @Override
    public @NotNull BlockState getDefaultDisplayBlockState() {
        return switch(this.getTntType()) {
            case MEGA -> MWBlocks.MEGA_TNT.get().defaultBlockState();
            case SUPER -> MWBlocks.SUPER_TNT.get().defaultBlockState();
            case HYPER -> MWBlocks.HYPER_TNT.get().defaultBlockState();
            case DISGUISED_GRASS -> MWBlocks.DISGUISED_GRASS_TNT.get().defaultBlockState();
            case DISGUISED_DIRT -> MWBlocks.DISGUISED_DIRT_TNT.get().defaultBlockState();
            case DISGUISED_SAND -> MWBlocks.DISGUISED_SAND_TNT.get().defaultBlockState();
            case DISGUISED_RED_SAND -> MWBlocks.DISGUISED_RED_SAND_TNT.get().defaultBlockState();
            case DISGUISED_STONE -> MWBlocks.DISGUISED_STONE_TNT.get().defaultBlockState();
            case DISGUISED_CAKE -> MWBlocks.DISGUISED_CAKE_TNT.get().defaultBlockState();
        };
    }

    /**
     * Make the {@link MinecartTNT tnt minecart} explode
     *
     * @param damageSource {@link DamageSource The damage source that causes the explosion}
     * @param fallDistance {@link Double The distance the minecart has fallen from}
     */
    @Override
    protected void explode(final @Nullable DamageSource damageSource, final double fallDistance) {
        if (!this.level.isClientSide) {
            double multiplier = Math.min(Math.sqrt(fallDistance), 5.0D);
            this.level.explode(this, damageSource, null, this.getX(), this.getY(), this.getZ(), (float)(type.getExplosionPower() + this.random.nextDouble() * 1.5D * multiplier), false, Level.ExplosionInteraction.TNT);
            this.discard();
        }
    }

    /**
     * Define the tnt minecart data
     */
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_TYPE, MWPrimedTnt.Type.MEGA.name());
    }

    /**
     * Read the {@link MWPrimedTnt.Type tnt type} from the {@link CompoundTag entity nbt data}
     *
     * @param nbt {@link CompoundTag The entity nbt data}
     */
    protected void readAdditionalSaveData(final @NotNull CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        if (nbt.contains("TNTType", 8)) {
            this.type = MWPrimedTnt.Type.valueOf(nbt.getString("TNTType").toUpperCase(Locale.ROOT));
        }

    }

    /**
     * Save the {@link MWPrimedTnt.Type tnt type} to the {@link CompoundTag entity nbt data}
     *
     * @param nbt {@link CompoundTag The entity nbt data}
     */
    protected void addAdditionalSaveData(final @NotNull CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putString("TNTType", this.type.name());
    }

}