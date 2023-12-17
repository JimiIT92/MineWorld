package org.mineworld.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.mineworld.core.MWEntityTypes;

import javax.annotation.Nullable;

public class AncientGuardian extends Monster {

    /**
     * The {@link EntityDataAccessor<Byte> Ancient Guardian data accessor flags}
     */
    protected static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(AncientGuardian.class, EntityDataSerializers.BYTE);
    /**
     * {@link Boolean The attacking flag value}
     */
    private static final int FLAG_IS_ATTACKING = 1;
    /**
     * The {@link ServerBossEvent boss bar}
     */
    private final ServerBossEvent bossEvent = (ServerBossEvent)(new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.BLUE, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);
    /**
     * The {@link AnimationState idle animation state}
     */
    public final AnimationState idleAnimationState = new AnimationState();
    /**
     * The {@link Integer idle animation state timeout}
     */
    public int idleAnimationStateTimeout = 0;
    /**
     * The {@link AnimationState walking animation state}
     */
    public final AnimationState walkingAnimationState = new AnimationState();
    /**
     * The {@link Integer walking animation state timeout}
     */
    public int walkingAnimationStateTimeout = 0;
    /**
     * The {@link AnimationState summonAttack animation state}
     */
    public final AnimationState summonAttackAnimationState = new AnimationState();
    /**
     * The {@link Integer summonAttack animation state timeout}
     */
    public int summonAttackAnimationStateTimeout = 0;
    /**
     * The {@link AnimationState flyingAttack animation state}
     */
    public final AnimationState flyingAttackAnimationState = new AnimationState();
    /**
     * The {@link Integer flyingAttack animation state timeout}
     */
    public int flyingAttackAnimationStateTimeout = 0;

    /**
     * Constructor. Set the entity properties
     *
     * @param entityType {@link EntityType<AncientGuardian> The Ancient Guardian entity type}
     * @param level {@link Level The level reference}
     */
    public AncientGuardian(final EntityType<AncientGuardian> entityType, final Level level) {
        super(entityType, level);
        this.xpReward = 100;
        this.setHealth(this.getMaxHealth());
        this.getNavigation().setCanFloat(true);
    }

    /**
     * Constructor. Set the entity properties
     *
     * @param level {@link Level The level reference}
     */
    public AncientGuardian(final Level level) {
        this(MWEntityTypes.ANCIENT_GUARDIAN.get(), level);
    }

    /**
     * Register the entity goals
     */
    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Mob.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    /**
     * Get the {@link AttributeSupplier.Builder attributes builder}
     *
     * @return The {@link AttributeSupplier.Builder attributes builder}
     */
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 500F)
                .add(Attributes.MOVEMENT_SPEED, 0.25F)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .add(Attributes.ATTACK_KNOCKBACK, 2.0D)
                .add(Attributes.ATTACK_DAMAGE, 30D)
                .add(Attributes.FOLLOW_RANGE, 100F);
    }

    /**
     * Check if the {@link AncientGuardian Ancient Guardian} can ride other entities
     *
     * @param entity {@link Entity The entity to ride}
     * @return {@link Boolean#FALSE False}
     */
    protected boolean canRide(final @NotNull Entity entity) {
        return false;
    }

    /**
     * Check if the {@link AncientGuardian Ancient Guardian} can disable shields
     *
     * @return {@link Boolean#TRUE True}
     */
    public boolean canDisableShield() {
        return true;
    }

    /**
     * Get the {@link Float entity sound volume}
     *
     * @return {@link Float 4.0}
     */
    @Override
    protected float getSoundVolume() {
        return 4.0F;
    }

    /**
     * Get the {@link AncientGuardian Ancient Guardian} {@link SoundEvent ambient sound}
     *
     * @return The {@link AncientGuardian Ancient Guardian} {@link SoundEvent ambient sound}
     */
    @Override
    @Nullable
    protected SoundEvent getAmbientSound() {
        return SoundEvents.WARDEN_AMBIENT;
    }

    /**
     * Get the {@link AncientGuardian Ancient Guardian} {@link SoundEvent damage sound}
     *
     * @param damageSource {@link DamageSource The damage source}
     * @return The {@link AncientGuardian Ancient Guardian} {@link SoundEvent damage sound}
     */
    @Override
    protected SoundEvent getHurtSound(final @NotNull DamageSource damageSource) {
        return SoundEvents.WARDEN_HURT;
    }

    /**
     * Get the {@link AncientGuardian Ancient Guardian} {@link SoundEvent death sound}
     *
     * @return The {@link AncientGuardian Ancient Guardian} {@link SoundEvent death sound}
     */
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.WARDEN_DEATH;
    }

    /**
     * Play the {@link AncientGuardian Ancient Guardian} {@link SoundEvent step sound}
     *
     * @param blockPos {@link BlockPos The current BlockPos}
     * @param blockState {@link BlockState The BlocKState the entity is walking on}
     */
    @Override
    protected void playStepSound(final @NotNull BlockPos blockPos, final @NotNull BlockState blockState) {
        this.playSound(SoundEvents.WARDEN_STEP, 10.0F, 1.0F);
    }

    /**
     * Check if the entity is attacking
     *
     * @return {@link Boolean True if is attacking}
     */
    public boolean isAttacking() {
        return this.getAncientGuardianFlag(FLAG_IS_ATTACKING);
    }

    /**
     * Set the entity attacking value
     *
     * @param isAttacking {@link Boolean If the entity is attacking}
     */
    public void setAttacking(final boolean isAttacking) {
        this.setAncientGuardianFlag(FLAG_IS_ATTACKING, isAttacking);
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
     * Check if a {@link AncientGuardian Ancient Guardian flag} is active
     *
     * @param flag {@link Integer The flag value}
     * @return {@link Boolean True if the flag is active}
     */
    private boolean getAncientGuardianFlag(final int flag) {
        final int value = this.entityData.get(DATA_FLAGS_ID);
        return (value & flag) != 0;
    }

    /**
     * Set a {@link AncientGuardian Ancient Guardian flag}
     *
     * @param flag {@link Integer The flag value}
     * @param isActive {@link Boolean If the flag is active}
     */
    private void setAncientGuardianFlag(final int flag, final boolean isActive) {
        int value = this.entityData.get(DATA_FLAGS_ID);
        if (isActive) {
            value |= flag;
        } else {
            value &= ~flag;
        }

        this.entityData.set(DATA_FLAGS_ID, (byte)(value & 255));
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
     * Make the entity immune to explosions
     *
     * @return {@link Boolean#TRUE True}
     */
    @Override
    public boolean ignoreExplosion() {
        return true;
    }

    /**
     * Prevent the entity from disappearing when far away
     *
     * @param distance {@link Double The distance from the entity}
     * @return {@link Boolean#FALSE False}
     */
    @Override
    public boolean removeWhenFarAway(final double distance) {
        return false;
    }

    /**
     * Read the entity name from {@link CompoundTag NBT}
     *
     * @param nbt {@link CompoundTag The entity NBT}
     */
    @Override
    public void readAdditionalSaveData(final @NotNull CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        if (this.hasCustomName()) {
            this.bossEvent.setName(this.getDisplayName());
        }

    }

    /**
     * Set a {@link Component custom entity name}
     *
     * @param name {@link Component The entity name}
     */
    @Override
    public void setCustomName(final @Nullable Component name) {
        super.setCustomName(name);
        this.bossEvent.setName(this.getDisplayName());
    }

    /**
     * Update the boss bar
     */
    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }

    /**
     * Show the boss bar when a {@link ServerPlayer player} is nearby
     *
     * @param player {@link ServerPlayer The player approaching the entity}
     */
    @Override
    public void startSeenByPlayer(final @NotNull ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.bossEvent.addPlayer(player);
    }

    /**
     * Hide the boss bar when a {@link ServerPlayer player} is away from the entity
     *
     * @param player {@link ServerPlayer The player moving away from the entity}
     */
    @Override
    public void stopSeenByPlayer(final @NotNull ServerPlayer player) {
        super.stopSeenByPlayer(player);
        this.bossEvent.removePlayer(player);
    }

    /**
     * Check if the entity can travel across dimensions
     *
     * @return {@link Boolean#FALSE False}
     */
    @Override
    public boolean canChangeDimensions() {
        return false;
    }

}