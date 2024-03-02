package org.mineworld.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
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
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import org.mineworld.MineWorld;
import org.mineworld.core.MWEntityTypes;
import org.mineworld.core.MWParticleTypes;
import org.mineworld.core.MWSounds;
import org.mineworld.entity.goal.MWFlyingMobLookingGoal;

import java.util.EnumSet;

/**
 * {@link MineWorld MineWorld} {@link Reaper Reaper}
 */
public class Reaper extends Monster {

    /**
     * {@link EntityDataAccessor<Byte> The Reaper Flags data value}
     */
    protected static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(Reaper.class, EntityDataSerializers.BYTE);
    /**
     * The {@link Boolean Charging Flag value}
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
     * Constructor. Set the {@link EntityType Entity Type}
     *
     * @param entityType {@link EntityType The Entity Type}
     * @param level {@link Level The level reference}
     */
    public Reaper(final EntityType<Reaper> entityType, final Level level) {
        super(entityType, level);
        this.xpReward = 10;
        this.moveControl = new Reaper.ReaperMoveControl(this);
        this.setNoGravity(true);
    }

    /**
     * Constructor. Set the {@link EntityType Entity Type}
     *
     * @param level {@link Level The level reference}
     */
    public Reaper(final Level level) {
        this(MWEntityTypes.REAPER.get(), level);
    }

    /**
     * Get the {@link Float Entity Standing Eye height}
     *
     * @param pose {@link Pose The Entity Pose}
     * @param size {@link EntityDimensions The Entity dimensions}
     * @return {@link Float The Entity Standing Eye height}
     */
    @Override
    protected float getStandingEyeHeight(final @NotNull Pose pose, final EntityDimensions size) {
        return size.height - 0.5625F;
    }

    /**
     * Register the {@link Goal Entity Goals}
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
     * Calculate the {@link Entity Entity} {@link Integer fall damage}
     *
     * @param distance {@link Float The fall distance}
     * @param damageMultiplier {@link Float The damage multiplier}
     * @return {@link Integer 0}
     */
    @Override
    protected int calculateFallDamage(final float distance, final float damageMultiplier) {
        return 0;
    }

    /**
     * Get the {@link AttributeSupplier.Builder Entity Attributes Builder}
     *
     * @return {@link AttributeSupplier.Builder The Entity Attributes Builder}
     */
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 10F)
                .add(Attributes.MOVEMENT_SPEED, 0.25F)
                .add(Attributes.ATTACK_DAMAGE, 3.0F)
                .add(Attributes.FOLLOW_RANGE, 100F);
    }

    /**
     * Make the {@link Goal Entity Goals} tick
     */
    @Override
    public void aiStep() {
        if (this.level().isClientSide) {
            this.level().addParticle(MWParticleTypes.SCULK_FIRE_FLAME.get(), this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
        }

        super.aiStep();
    }

    /**
     * Check if the {@link Reaper Reaper} is charging
     *
     * @return {@link Boolean True if is charging}
     */
    public boolean isCharging() {
        return this.getReaperFlag(FLAG_IS_CHARGING);
    }

    /**
     * Set the {@link Reaper Reaper} charging value
     *
     * @param isCharging {@link Boolean If the Reaper is charging}
     */
    public void setCharging(final boolean isCharging) {
        this.setReaperFlag(FLAG_IS_CHARGING, isCharging);
    }

    /**
     * Define the {@link Entity Entity} Data
     */
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_FLAGS_ID, (byte)0);
    }

    /**
     * Get a {@link Reaper Reaper} Flag value
     *
     * @param flag {@link Integer The Flag Id}
     * @return {@link Boolean True if the Flag is active}
     */
    private boolean getReaperFlag(final int flag) {
        final int value = this.entityData.get(DATA_FLAGS_ID);
        return (value & flag) != 0;
    }

    /**
     * Set a {@link Reaper Reaper} Flag
     *
     * @param flag {@link Integer The Flag Id}
     * @param isActive {@link Boolean If the Flag is active}
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
     * Finalize the {@link Entity Entity} spawn
     *
     * @param levelAccessor {@link ServerLevelAccessor The level reference}
     * @param difficulty {@link DifficultyInstance The difficulty reference}
     * @param spawnType {@link MobSpawnType The spawn type}
     * @param spawnGroupData {@link SpawnGroupData The spawn group data}
     * @param nbt {@link CompoundTag The Entity NBT Data}
     * @return {@link SpawnGroupData The spawn group data}
     */
    @Override
    @Nullable
    public SpawnGroupData finalizeSpawn(final ServerLevelAccessor levelAccessor, final @NotNull DifficultyInstance difficulty, final @NotNull MobSpawnType spawnType, final @Nullable SpawnGroupData spawnGroupData, final @Nullable CompoundTag nbt) {
        RandomSource randomsource = levelAccessor.getRandom();
        this.populateDefaultEquipmentSlots(randomsource, difficulty);
        this.populateDefaultEquipmentEnchantments(randomsource, difficulty);
        return spawnGroupData;
    }

    /**
     * Equip the {@link Entity Entity} with some items
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
     * Check if the {@link Entity Entity} should respawn in peaceful mode
     *
     * @return {@link Boolean#TRUE True}
     */
    @Override
    protected boolean shouldDespawnInPeaceful() {
        return true;
    }

    /**
     * Get the {@link Float Entity Riding Offset}
     *
     * @param entity {@link Entity The Vehicle Entity}
     * @return {@link Float 0.4F}
     */
    @Override
    protected float ridingOffset(final @NotNull Entity entity) {
        return 0.04F;
    }

    /**
     * Get the {@link Entity Passenger} Attachment Point
     *
     * @param entity {@link Entity The Passenger Entity}
     * @param size {@link EntityDimensions The Entity dimensions}
     * @param scale {@link Float The Entity scale}
     * @return {@link Vector3f The Passenger Attachment Point}
     */
    @Override
    protected @NotNull Vector3f getPassengerAttachmentPoint(final @NotNull Entity entity, final EntityDimensions size, final float scale) {
        return new Vector3f(0.0F, size.height - 0.0625F * scale, 0.0F);
    }

    /**
     * Get the {@link SoundEvent Entity Ambient Sound}
     *
     * @return {@link MWSounds#REAPER_IDLE The Reaper Ambient Sound}
     */
    @Override
    protected SoundEvent getAmbientSound() {
        return MWSounds.REAPER_IDLE.get();
    }

    /**
     * Get the {@link SoundEvent Entity Ambient Sound}
     *
     * @return {@link MWSounds#REAPER_DEATH The Reaper Death Sound}
     */
    @Override
    protected SoundEvent getDeathSound() {
        return MWSounds.REAPER_DEATH.get();
    }

    /**
     * Get the {@link SoundEvent Entity Ambient Sound}
     *
     * @return {@link MWSounds#REAPER_HURT The Reaper Hurt Sound}
     */
    @Override
    protected SoundEvent getHurtSound(final @NotNull DamageSource damageSource) {
        return MWSounds.REAPER_HURT.get();
    }

    /**
     * {@link MineWorld MineWorld} {@link Reaper Reaper} {@link MoveControl Move Control}
     */
    class ReaperMoveControl extends MoveControl {

        /**
         * Constructor. Set the Goal properties
         *
         * @param reaper {@link Reaper The Reaper}
         */
        public ReaperMoveControl(final Reaper reaper) {
            super(reaper);
        }

        /**
         * Make the {@link Reaper Reaper} move
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
     * {@link MineWorld MineWorld} {@link Reaper Reaper} {@link Goal Charge Control}
     */
    class ReaperChargeAttackGoal extends Goal {

        /**
         * Constructor. Set the Goal properties
         */
        public ReaperChargeAttackGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        /**
         * Check if the {@link Reaper Reaper} can charge
         *
         * @return {@link Boolean True if the Reaper can charge}
         */
        @Override
        public boolean canUse() {
            final LivingEntity target = Reaper.this.getTarget();
            if (target != null && target.isAlive() && !Reaper.this.getMoveControl().hasWanted() && Reaper.this.random.nextInt(reducedTickDelay(7)) == 0) {
                return Reaper.this.distanceToSqr(target) > 4.0D;
            }
            return false;
        }

        /**
         * Check if the {@link Reaper Reaper} can continue to charge
         *
         * @return {@link Boolean True if the Reaper can continue to charge}
         */
        @Override
        public boolean canContinueToUse() {
            return Reaper.this.getMoveControl().hasWanted() && Reaper.this.isCharging() && Reaper.this.getTarget() != null && Reaper.this.getTarget().isAlive();
        }

        /**
         * Make the {@link Reaper Reaper} start charging
         */
        @Override
        public void start() {
            final LivingEntity target = Reaper.this.getTarget();
            if (target != null) {
                charge(target);
            }
            Reaper.this.setCharging(true);
            Reaper.this.playSound(MWSounds.REAPER_CHARGE.get(), 1.0F, 1.0F);
        }

        /**
         * Make the {@link Reaper Reaper} stop charging
         */
        @Override
        public void stop() {
            Reaper.this.setCharging(false);
        }

        /**
         * Check if this Goal should run every tick
         *
         * @return {@link Boolean#TRUE True}
         */
        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }

        /**
         * Make the {@link Reaper Reaper} charge
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
         * Make the {@link Reaper Reaper} charge
         *
         * @param target {@link LivingEntity The charge target}
         */
        private void charge(final LivingEntity target) {
            final Vec3 eyePosition = target.getEyePosition();
            Reaper.this.moveControl.setWantedPosition(eyePosition.x, eyePosition.y, eyePosition.z, 1.0D);
        }
    }

    /**
     * {@link MineWorld MineWorld} {@link Reaper Reaper} {@link Goal Random Move Control}
     */
    class ReaperRandomMoveGoal extends Goal {

        /**
         * Constructor. Set the Goal properties
         */
        public ReaperRandomMoveGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        /**
         * Check if the {@link Reaper Reaper} can randomly move
         *
         * @return {@link Boolean True if the Reaper can randomly move}
         */
        @Override
        public boolean canUse() {
            return !Reaper.this.getMoveControl().hasWanted() && Reaper.this.random.nextInt(reducedTickDelay(7)) == 0;
        }

        /**
         * Check if the {@link Reaper Reaper} can continue to randomly move
         *
         * @return {@link Boolean True if the Reaper can continue to randomly move}
         */
        @Override
        public boolean canContinueToUse() {
            return false;
        }

        /**
         * Make the {@link Reaper Reaper} randomly move
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