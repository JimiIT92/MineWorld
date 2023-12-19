package org.mineworld.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;
import org.mineworld.core.MWEntityTypes;
import org.mineworld.core.MWParticleTypes;
import org.mineworld.core.MWSounds;
import org.mineworld.entity.goal.MWFlyingMobLookingGoal;

import javax.annotation.Nullable;
import java.util.EnumSet;

/**
 * Implementation class for a Reaper Entity
 */
public class Reaper extends Monster {

    /**
     * The {@link EntityDataAccessor<Byte> Reaper data accessor flags}
     */
    protected static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(Reaper.class, EntityDataSerializers.BYTE);
    /**
     * {@link Boolean The charging flag value}
     */
    private static final int FLAG_IS_CHARGING = 1;
    /**
     * The {@link AnimationState idle animation state}
     */
    public final AnimationState idleAnimationState = new AnimationState();
    /**
     * The {@link Integer idle animation state timeout}
     */
    public int idleAnimationStateTimeout = 0;
    /**
     * The {@link AnimationState charge animation state}
     */
    public final AnimationState chargeAnimationState = new AnimationState();
    /**
     * The {@link Integer charge animation state timeout}
     */
    public int chargeAnimationStateTimeout = 0;

    /**
     * Constructor. Set the entity properties
     *
     * @param entityType {@link EntityType<Reaper> The Reaper entity type}
     * @param level {@link Level The level reference}
     */
    public Reaper(final EntityType<Reaper> entityType, final Level level) {
        super(entityType, level);
        this.xpReward = 10;
        this.moveControl = new Reaper.ReaperMoveControl(this);
        this.setNoGravity(true);
    }

    /**
     * Constructor. Set the entity properties
     *
     * @param level {@link Level The level reference}
     */
    public Reaper(final Level level) {
        this(MWEntityTypes.REAPER.get(), level);
    }

    /**
     * Get the {@link Float entity eye height}
     *
     * @param pose {@link Pose The entity pose}
     * @param size {@link EntityDimensions The entity size}
     * @return {@link Float The entity eye height}
     */
    @Override
    protected float getStandingEyeHeight(final @NotNull Pose pose, final EntityDimensions size) {
        return size.height - 0.5625F;
    }

    /**
     * Register the entity goals
     */
    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new Reaper.ReaperChargeAttackGoal());
        this.goalSelector.addGoal(2, new Reaper.ReaperRandomMoveGoal());
        this.goalSelector.addGoal(3, new MWFlyingMobLookingGoal(this));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Mob.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    /**
     * Make the entity immune to fall damage
     *
     * @param par1 {@link Float Unused first parameter}
     * @param par2 {@link Float Unused second parameter}
     * @return {@link Integer 0}
     */
    @Override
    protected int calculateFallDamage(final float par1, final float par2) {
        return 0;
    }

    /**
     * Get the {@link AttributeSupplier.Builder attributes builder}
     *
     * @return The {@link AttributeSupplier.Builder attributes builder}
     */
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 10F)
                .add(Attributes.MOVEMENT_SPEED, 0.25F)
                .add(Attributes.ATTACK_DAMAGE, 3.0F)
                .add(Attributes.FOLLOW_RANGE, 100F);
    }

    /**
     * Show some smoke particles around the {@link Reaper Reaper}
     */
    @Override
    public void aiStep() {
        if (this.level().isClientSide) {
            this.level().addParticle(MWParticleTypes.SCULK_FIRE_FLAME.get(), this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
        }

        super.aiStep();
    }

    /**
     * Check if the entity is charging
     *
     * @return {@link Boolean True if is charging}
     */
    public boolean isCharging() {
        return this.getReaperFlag(FLAG_IS_CHARGING);
    }

    /**
     * Set the entity charging value
     *
     * @param isCharging {@link Boolean If the entity is charging}
     */
    public void setCharging(final boolean isCharging) {
        this.setReaperFlag(FLAG_IS_CHARGING, isCharging);
    }

    /**
     * Define the entity data
     */
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_FLAGS_ID, (byte)0);
    }

    /**
     * Check if a {@link Reaper Reaper flag} is active
     *
     * @param flag {@link Integer The flag value}
     * @return {@link Boolean True if the flag is active}
     */
    private boolean getReaperFlag(final int flag) {
        final int value = this.entityData.get(DATA_FLAGS_ID);
        return (value & flag) != 0;
    }

    /**
     * Set a {@link Reaper Reaper flag}
     *
     * @param flag {@link Integer The flag value}
     * @param isActive {@link Boolean If the flag is active}
     */
    private void setReaperFlag(final int flag, final boolean isActive) {
        int value = this.entityData.get(DATA_FLAGS_ID);
        if (isActive) {
            value |= flag;
        } else {
            value &= ~flag;
        }

        this.entityData.set(DATA_FLAGS_ID, (byte)(value & 255));
    }

    /**
     * Finalize the entity spawn
     *
     * @param level {@link ServerLevelAccessor The level reference}
     * @param difficulty {@link DifficultyInstance The difficulty reference}
     * @param spawnType {@link MobSpawnType The spawn type}
     * @param spawnGroupData {@link SpawnGroupData The spawn group data}
     * @param nbt {@link CompoundTag The entity nbt tag}
     * @return {@link SpawnGroupData The spawn group data}
     */
    @Override
    @Nullable
    public SpawnGroupData finalizeSpawn(final ServerLevelAccessor level, final @NotNull DifficultyInstance difficulty, final @NotNull MobSpawnType spawnType, final @Nullable SpawnGroupData spawnGroupData, final @Nullable CompoundTag nbt) {
        RandomSource randomsource = level.getRandom();
        this.populateDefaultEquipmentSlots(randomsource, difficulty);
        this.populateDefaultEquipmentEnchantments(randomsource, difficulty);
        return spawnGroupData;
    }

    /**
     * Equip the Reaper with a {@link Items#STONE_HOE Stone Hoe}
     *
     * @param random {@link RandomSource The random reference}
     * @param difficulty {@link DifficultyInstance The difficulty reference}
     */
    @Override
    protected void populateDefaultEquipmentSlots(final @NotNull RandomSource random, final @NotNull DifficultyInstance difficulty) {
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_HOE));
        this.setDropChance(EquipmentSlot.MAINHAND, 0.0F);
    }

    /**
     * Check if the entity should despawn in peaceful mode
     *
     * @return {@link Boolean#TRUE True}
     */
    @Override
    protected boolean shouldDespawnInPeaceful() {
        return true;
    }

    /**
     * Get the {@link Float entity riding offset}
     *
     * @param entity {@link Entity The vehicle entity}
     * @return {@link Float 0.4F}
     */
    @Override
    protected float ridingOffset(final @NotNull Entity entity) {
        return 0.04F;
    }

    /**
     * Get the passenger attachment point
     *
     * @param entity {@link Entity The entity riding the Reaper}
     * @param size {@link EntityDimensions The entity size}
     * @param scale {@link Float The entity scale}
     * @return {@link Vector3f The passenger attachment point}
     */
    @Override
    protected @NotNull Vector3f getPassengerAttachmentPoint(final @NotNull Entity entity, final EntityDimensions size, final float scale) {
        return new Vector3f(0.0F, size.height - 0.0625F * scale, 0.0F);
    }

    /**
     * Get the {@link SoundEvent Reaper ambient sound}
     *
     * @return {@link SoundEvents#VEX_AMBIENT The Reaper ambient sound}
     */
    @Override
    protected SoundEvent getAmbientSound() {
        return MWSounds.REAPER_IDLE.get();
    }

    /**
     * Get the {@link SoundEvent Reaper death sound}
     *
     * @return {@link SoundEvents#VEX_DEATH The Reaper death sound}
     */
    @Override
    protected SoundEvent getDeathSound() {
        return MWSounds.REAPER_DEATH.get();
    }

    /**
     * Get the {@link SoundEvent Reaper hurt sound}
     *
     * @param damageSource {@link DamageSource The damage source}
     * @return {@link SoundEvents#VEX_HURT The Reaper hurt sound}
     */
    @Override
    protected SoundEvent getHurtSound(final @NotNull DamageSource damageSource) {
        return MWSounds.REAPER_HURT.get();
    }

    /**
     * The {@link Reaper Reaper move control}
     */
    class ReaperMoveControl extends MoveControl {

        /**
         * Constructor. Set the {@link Reaper Reaper entity}
         *
         * @param reaper {@link Reaper The Reaper entity}
         */
        public ReaperMoveControl(final Reaper reaper) {
            super(reaper);
        }

        /**
         * Make the entity move
         */
        public void tick() {
            if (this.operation.equals(MoveControl.Operation.MOVE_TO)) {
                final Vec3 target = new Vec3(this.wantedX - Reaper.this.getX(), this.wantedY - Reaper.this.getY(), this.wantedZ - Reaper.this.getZ());
                final double distance = target.length();
                if (distance < Reaper.this.getBoundingBox().getSize()) {
                    this.operation = MoveControl.Operation.WAIT;
                    Reaper.this.setDeltaMovement(Reaper.this.getDeltaMovement().scale(0.5D));
                } else {
                    Reaper.this.setDeltaMovement(Reaper.this.getDeltaMovement().add(target.scale(this.speedModifier * 0.05D / distance)));
                    double distanceX;
                    double distanceZ;
                    if (Reaper.this.getTarget() == null) {
                        final Vec3 deltaMovement = Reaper.this.getDeltaMovement();
                        distanceX = deltaMovement.x;
                        distanceZ = deltaMovement.z;
                    } else {
                        distanceX = Reaper.this.getTarget().getX() - Reaper.this.getX();
                        distanceZ = Reaper.this.getTarget().getZ() - Reaper.this.getZ();
                    }
                    Reaper.this.setYRot(-((float) Mth.atan2(distanceX, distanceZ)) * (180F / (float)Math.PI));
                    Reaper.this.yBodyRot = Reaper.this.getYRot();
                }

            }
        }
    }

    /**
     * The {@link Reaper Reaper} charge goal
     */
    class ReaperChargeAttackGoal extends Goal {

        /**
         * Constructor. Set the entity flags
         */
        public ReaperChargeAttackGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        /**
         * Check if the entity can charge
         *
         * @return {@link Boolean True if the entity can charge}
         */
        public boolean canUse() {
            final LivingEntity target = Reaper.this.getTarget();
            if (target != null && target.isAlive() && !Reaper.this.getMoveControl().hasWanted() && Reaper.this.random.nextInt(reducedTickDelay(7)) == 0) {
                return Reaper.this.distanceToSqr(target) > 4.0D;
            }
            return false;
        }

        /**
         * Check if the entity can continue to charge
         *
         * @return {@link Boolean True if the entity can continue to charge}
         */
        public boolean canContinueToUse() {
            return Reaper.this.getMoveControl().hasWanted() && Reaper.this.isCharging() && Reaper.this.getTarget() != null && Reaper.this.getTarget().isAlive();
        }

        /**
         * Make the entity start charging
         */
        public void start() {
            final LivingEntity target = Reaper.this.getTarget();
            if (target != null) {
                charge(target);
            }
            Reaper.this.setCharging(true);
            Reaper.this.playSound(MWSounds.REAPER_CHARGE.get(), 1.0F, 1.0F);
        }

        /**
         * Make the entity stop charging
         */
        public void stop() {
            Reaper.this.setCharging(false);
        }

        /**
         * Check if the goal should run every tick
         *
         * @return {@link Boolean#TRUE True}
         */
        public boolean requiresUpdateEveryTick() {
            return true;
        }

        /**
         * Make the entity charge
         */
        public void tick() {
            final LivingEntity target = Reaper.this.getTarget();
            if (target != null) {
                if (Reaper.this.getBoundingBox().intersects(target.getBoundingBox())) {
                    Reaper.this.doHurtTarget(target);
                    Reaper.this.setCharging(false);
                } else {
                    if (Reaper.this.distanceToSqr(target) < 9.0D) {
                        charge(target);
                    }
                }
            }
        }

        /**
         * Make the entity charge its {@link LivingEntity target}
         *
         * @param target {@link LivingEntity The entity target}
         */
        private void charge(final LivingEntity target) {
            final Vec3 eyePosition = target.getEyePosition();
            Reaper.this.moveControl.setWantedPosition(eyePosition.x, eyePosition.y, eyePosition.z, 1.0D);
        }
    }

    /**
     * Make the {@link Reaper Reaper} randomly move
     */
    class ReaperRandomMoveGoal extends Goal {

        /**
         * Constructor. Set the goal flags
         */
        public ReaperRandomMoveGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        /**
         * Check if the {@link Reaper Reaper} can move
         *
         * @return {@link Boolean True if the entity can move}
         */
        public boolean canUse() {
            return !Reaper.this.getMoveControl().hasWanted() && Reaper.this.random.nextInt(reducedTickDelay(7)) == 0;
        }

        /**
         * Check if the {@link Reaper Reaper} can still move
         *
         * @return {@link Boolean#FALSE False}
         */
        public boolean canContinueToUse() {
            return false;
        }

        /**
         * Make the {@link Reaper Reaper} move
         */
        public void tick() {
            for(int i = 0; i < 3; ++i) {
                final BlockPos target =  Reaper.this.blockPosition().offset(Reaper.this.random.nextInt(15) - 7, Reaper.this.random.nextInt(11) - 5, Reaper.this.random.nextInt(15) - 7);
                if (Reaper.this.level().isEmptyBlock(target)) {
                    Reaper.this.moveControl.setWantedPosition((double)target.getX() + 0.5D, (double)target.getY() + 0.5D, (double)target.getZ() + 0.5D, 0.25D);
                    if (Reaper.this.getTarget() == null) {
                        Reaper.this.getLookControl().setLookAt((double)target.getX() + 0.5D, (double)target.getY() + 0.5D, (double)target.getZ() + 0.5D, 180.0F, 20.0F);
                    }
                    break;
                }
            }

        }
    }

}