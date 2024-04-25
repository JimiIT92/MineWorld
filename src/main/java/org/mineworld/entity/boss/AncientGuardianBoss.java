package org.mineworld.entity.boss;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWEntityTypes;
import org.mineworld.core.MWSounds;
import org.mineworld.entity.Reaper;
import org.mineworld.entity.projectile.DarknessCharge;
import org.mineworld.helper.RandomHelper;
import org.mineworld.helper.SonicBoomHelper;

import javax.annotation.Nullable;
import java.util.EnumSet;

/**
 * {@link MineWorld MineWorld} {@link AncientGuardianBoss Ancient Guardian Boss}
 */
public class AncientGuardianBoss extends Monster implements PowerableMob, RangedAttackMob {

    /**
     * {@link Integer Invulnerability Ticks} {@link EntityDataAccessor Entity Data Accessor}
     */
    private static final EntityDataAccessor<Integer> DATA_ID_INV = SynchedEntityData.defineId(AncientGuardianBoss.class, EntityDataSerializers.INT);
    /**
     * {@link Float Entity invulnerable max ticks}
     */
    private static final int INVULNERABLE_TICKS = 110;
    /**
     * {@link ServerBossEvent The Entity Boss Bar}
     */
    private final ServerBossEvent BOSS_BAR = (ServerBossEvent)(new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.BLUE, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);

    /**
     * Constructor. Set the {@link EntityType Entity Type}
     *
     * @param entityType {@link EntityType The Entity Type}
     * @param level {@link Level The level reference}
     */
    public AncientGuardianBoss(final EntityType<? extends AncientGuardianBoss> entityType, final Level level) {
        super(entityType, level);
        this.moveControl = new FlyingMoveControl(this, 10, false);
        this.setHealth(this.getMaxHealth());
        this.setNoGravity(true);
        this.getNavigation().setCanFloat(true);
        this.xpReward = 100;
    }

    /**
     * Create the {@link PathNavigation entity navigation}
     *
     * @param level {@link Level The level reference}
     * @return {@link PathNavigation The entity navigation}
     */
    protected @NotNull PathNavigation createNavigation(final @NotNull Level level) {
        final FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, level);
        flyingpathnavigation.setCanOpenDoors(false);
        flyingpathnavigation.setCanFloat(true);
        flyingpathnavigation.setCanPassDoors(true);
        return flyingpathnavigation;
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
     * Get the {@link Float Entity Standing Eye height}
     *
     * @param pose {@link Pose The Entity Pose}
     * @param size {@link EntityDimensions The Entity dimensions}
     * @return {@link Float 1.1F}
     */
    @Override
    protected float getStandingEyeHeight(final @NotNull Pose pose, final @NotNull EntityDimensions size) {
        return 1.1F;
    }

    /**
     * Register the {@link Goal Entity Goals}
     */
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new AncientGuardianDoNothingGoal());
        this.goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0D, 10, 20.0F));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomFlyingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, false, false));
    }

    /**
     * Define the {@link Entity Entity} Data
     */
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_INV, 0);
    }

    /**
     * Save the {@link CompoundTag Entity NBT Data}
     *
     * @param nbt {@link CompoundTag The Entity NBT Data}
     */
    @Override
    public void addAdditionalSaveData(final @NotNull CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("Invul", this.getInvulnerableTicks());
    }

    /**
     * Read the {@link CompoundTag Entity NBT Data}
     *
     * @param nbt {@link CompoundTag The Entity NBT Data}
     */
    @Override
    public void readAdditionalSaveData(final @NotNull CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.setInvulnerableTicks(nbt.getInt("Invul"));
        if (this.hasCustomName()) {
            this.BOSS_BAR.setName(this.getDisplayName());
        }
    }

    /**
     * Set the {@link Component entity custom name}
     *
     * @param name {@link Component The entity custom name}
     */
    @Override
    public void setCustomName(final @Nullable Component name) {
        super.setCustomName(name);
        this.BOSS_BAR.setName(this.getDisplayName());
    }

    /**
     * Get the {@link SoundEvent Entity Ambient Sound}
     *
     * @return {@link MWSounds#ANCIENT_GUARDIAN_AMBIENT The Ancient Guardian Ambient Sound}
     */
    @Override
    protected SoundEvent getAmbientSound() {
        return MWSounds.ANCIENT_GUARDIAN_AMBIENT.get();
    }

    /**
     * Get the {@link SoundEvent Entity Ambient Sound}
     *
     * @param damageSource {@link DamageSource The damage source}
     * @return {@link MWSounds#ANCIENT_GUARDIAN_HURT The Ancient Guardian Hurt Sound}
     */
    @Override
    protected SoundEvent getHurtSound(final @NotNull DamageSource damageSource) {
        return MWSounds.ANCIENT_GUARDIAN_HURT.get();
    }

    /**
     * Get the {@link SoundEvent Entity Ambient Sound}
     *
     * @return {@link MWSounds#ANCIENT_GUARDIAN_DEATH The Ancient Guardian Death Sound}
     */
    @Override
    protected SoundEvent getDeathSound() {
        return MWSounds.ANCIENT_GUARDIAN_DEATH.get();
    }

    /**
     * Make the {@link Goal Entity Goals} tick
     */
    @Override
    public void aiStep() {
        final Vec3 movementVec = this.getDeltaMovement().multiply(1.0D, 0.6D, 1.0D);
        this.setDeltaMovement(movementVec);
        if (movementVec.horizontalDistanceSqr() > 0.05D) {
            this.setYRot((float) Mth.atan2(movementVec.z, movementVec.x) * (180F / (float)Math.PI) - 90.0F);
        }
        super.aiStep();
    }

    /**
     * Add a custom AI to the {@link Entity entity}
     */
    @Override
    protected void customServerAiStep() {
        int invulnerableTicks = this.getInvulnerableTicks();
        if (invulnerableTicks > 0) {
            if(invulnerableTicks == INVULNERABLE_TICKS) {
                this.level().playSound(null, this.blockPosition(), MWSounds.ANCIENT_GUARDIAN_CHARGING.get(), SoundSource.HOSTILE, 1.0F, 1.0F);
            }
            invulnerableTicks--;
            this.BOSS_BAR.setProgress(1.0F - (float)invulnerableTicks / INVULNERABLE_TICKS);

            if (invulnerableTicks <= 0) {
                this.level().explode(this, this.getX(), this.getEyeY(), this.getZ(), 8.0F, false, Level.ExplosionInteraction.MOB);
                this.setHealth(this.getMaxHealth());
            }

            this.setInvulnerableTicks(invulnerableTicks);
            if (this.tickCount % 5 == 0) {
                this.heal(5.0F);
            }
        } else {
            super.customServerAiStep();

            if (this.tickCount % 20 == 0) {
                this.heal(1.0F);
            }

            this.BOSS_BAR.setProgress(this.getHealth() / this.getMaxHealth());
        }
    }

    /**
     * Make the {@link Entity entity} invulnerable
     */
    public void makeInvulnerable() {
        this.setInvulnerableTicks(INVULNERABLE_TICKS);
        this.BOSS_BAR.setProgress(0.0F);
        this.setHealth(this.getMaxHealth() / 3.0F);
    }

    /**
     * Show the {@link #BOSS_BAR boss bar} to a {@link ServerPlayer player}
     *
     * @param player {@link ServerPlayer The player}
     */
    public void startSeenByPlayer(final @NotNull ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.BOSS_BAR.addPlayer(player);
    }

    /**
     * Hide the {@link #BOSS_BAR boss bar} from a {@link ServerPlayer player}
     *
     * @param player {@link ServerPlayer The player}
     */
    public void stopSeenByPlayer(final @NotNull ServerPlayer player) {
        super.stopSeenByPlayer(player);
        this.BOSS_BAR.removePlayer(player);
    }

    /**
     * Shoot a {@link WitherSkull projectile} to a target
     *
     * @param entity {@link LivingEntity The target entity}
     * @param distance {@link Float The distance}
     */
    public void performRangedAttack(final @NotNull LivingEntity entity, final float distance) {
        if(RandomHelper.nextInt(10)) {
            final double x = entity.getX();
            final double y = entity.getY() + (double)entity.getEyeHeight() * 0.5D;
            final double z = entity.getZ();
            if (!this.isSilent()) {
                this.playSound(MWSounds.DARKNESS_CHARGE_SHOOT.get());
            }

            final float angle = Mth.cos(this.yBodyRot * ((float)Math.PI / 180F));
            final double projectileX = this.getX() + (double)angle * 1.3D;
            final double projectileY = this.getY() + 2.2D;
            final double projectileZ = this.getZ() + (double)angle * 1.3D;
            final double targetX = x - projectileX;
            final double targetY = y - projectileY;
            final double targetZ = z - projectileZ;

            final DarknessCharge darknessCharge = new DarknessCharge(this.level(), this, targetX, targetY, targetZ);
            darknessCharge.setOwner(this);
            darknessCharge.setPosRaw(projectileX, projectileY, projectileZ);
            this.level().addFreshEntity(darknessCharge);
        }
    }

    /**
     * Check if the {@link Boolean entity} is invulnerable to a {@link DamageSource damage source}
     *
     * @param damageSource {@link DamageSource The damage source}
     * @return {@link Boolean True if the entity is invulnerable to the provided damage source}
     */
    @Override
    public boolean isInvulnerableTo(final @NotNull DamageSource damageSource) {
        return damageSource.is(DamageTypeTags.IS_EXPLOSION) || damageSource.getEntity() instanceof Warden || super.isInvulnerableTo(damageSource);
    }

    /**
     * Hurt the {@link Entity entity}
     *
     * @param damageSource {@link DamageSource The damage source}
     * @param amount {@link Float The damage amount}
     * @return {@link Boolean True if the entity has been damaged}
     */
    public boolean hurt(final @NotNull DamageSource damageSource, final float amount) {
        if (this.isInvulnerableTo(damageSource)) {
            return false;
        }
        if (!(damageSource.getEntity() instanceof AncientGuardianBoss)) {
            if (this.getInvulnerableTicks() > 0 && !damageSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
                return false;
            }
            if (damageSource.getDirectEntity() instanceof AbstractArrow arrow) {
                arrow.discard();
                return false;
            }

            final Entity attacker = damageSource.getEntity();
            if (!(attacker instanceof Player) && attacker instanceof LivingEntity && ((LivingEntity) attacker).getMobType().equals(this.getMobType())) {
                return false;
            }

            if(attacker instanceof Player player) {
                final int chance = switch (this.level().getDifficulty()) {
                    case NORMAL -> 10;
                    case HARD -> 30;
                    default -> 0;
                };
                if(chance > 0 && RandomHelper.getRandom().nextInt(100) <= chance) {
                    for (int i = 0; i < 1 + RandomHelper.getRandom().nextInt(3); i++) {
                        final Reaper reaper = new Reaper(MWEntityTypes.REAPER.get(), this.level());
                        reaper.setPos(attacker.position());
                        reaper.setTarget(player);
                        reaper.setCharging(true);
                        this.level().addFreshEntity(reaper);
                    }
                }

                if(this.isPowered() && RandomHelper.nextInt(10)) {
                    if(RandomHelper.nextBoolean()) {
                        final int seconds = switch (this.level().getDifficulty()) {
                            case NORMAL -> 5;
                            case HARD -> 15;
                            default -> 0;
                        };
                        if(seconds > 0) {
                            player.setSecondsOnFire(seconds);
                        }
                    } else {
                        if(RandomHelper.nextBoolean()) {
                            SonicBoomHelper.fire(level(), this, 15, this.level().getDifficulty().equals(Difficulty.HARD) ? 7F : 5F);
                        } else {
                            final LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, this.level());
                            lightningBolt.setPos(player.position());
                            lightningBolt.setVisualOnly(false);
                            this.level().addFreshEntity(lightningBolt);
                        }
                    }
                }
            }

            return super.hurt(damageSource, amount);
        }
        return false;
    }

    /**
     * Check if the {@link Entity entity} should despawn
     */
    public void checkDespawn() {
        if (this.level().getDifficulty().equals(Difficulty.PEACEFUL) && this.shouldDespawnInPeaceful()) {
            this.discard();
        } else {
            this.noActionTime = 0;
        }
    }

    /**
     * Make the {@link AncientGuardianBoss Ancient Guardian} immune to potion effects
     *
     * @param effect {@link MobEffectInstance The effect to apply}
     * @param attacker {@link Entity The entity that is applying the effect}
     * @return {@link Boolean#FALSE False}
     */
    public boolean addEffect(final @NotNull MobEffectInstance effect, final @Nullable Entity attacker) {
        return false;
    }

    /**
     * Get the {@link AttributeSupplier.Builder Entity Attributes Builder}
     *
     * @return {@link AttributeSupplier.Builder The Entity Attributes Builder}
     */
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 250.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.5F)
                .add(Attributes.FLYING_SPEED, 0.5F)
                .add(Attributes.FOLLOW_RANGE, 40.0D)
                .add(Attributes.ARMOR, 12.0D);
    }

    /**
     * Get the {@link Integer remaining invulnerability ticks}
     *
     * @return {@link Integer The remaining invulnerability ticks}
     */
    public int getInvulnerableTicks() {
        return this.entityData.get(DATA_ID_INV);
    }

    /**
     * Set the {@link Integer invulnerability ticks}
     *
     * @param ticks {@link Integer The invulnerability ticks}
     */
    public void setInvulnerableTicks(final int ticks) {
        this.entityData.set(DATA_ID_INV, ticks);
    }

    /**
     * Check if the {@link Entity entity} is powered
     *
     * @return {@link Boolean True if the entity is powered}
     */
    public boolean isPowered() {
        return this.getHealth() <= this.getMaxHealth() / 2F;
    }

    /**
     * Get the {@link MobType entity mob type}
     *
     * @return {@link MobType#UNDEAD Undead Mob Type}
     */
    public @NotNull MobType getMobType() {
        return MobType.UNDEFINED;
    }

    /**
     * Check if the {@link Entity entity} can ride another one
     *
     * @param entity {@link Entity The entity to ride}
     * @return {@link Boolean#FALSE False}
     */
    protected boolean canRide(final @NotNull Entity entity) {
        return false;
    }

    /**
     * Check if the {@link Entity entity} can change dimension
     *
     * @return {@link Boolean#FALSE False}
     */
    public boolean canChangeDimensions() {
        return false;
    }

    /**
     * Check if the {@link Entity entity} can be affected  by a {@link MobEffectInstance potion effect}
     *
     * @param effect {@link MobEffectInstance The potion effect}
     * @return {@link Boolean#FALSE False}
     */
    public boolean canBeAffected(final @NotNull MobEffectInstance effect) {
        return false;
    }

    /**
     * {@link MineWorld MineWorld} {@link AncientGuardianBoss Ancient Guardian} {@link Goal do nothing Goal}
     */
    class AncientGuardianDoNothingGoal extends Goal {

        /**
         * Constructor. Set the Goal properties
         */
        public AncientGuardianDoNothingGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP, Goal.Flag.LOOK));
        }

        /**
         * Check if the {@link AncientGuardianBoss Ancient Guardian} should do nothing
         *
         * @return {@link Boolean True if the Ancient Guardian is invulnerable}
         */
        @Override
        public boolean canUse() {
            return AncientGuardianBoss.this.getInvulnerableTicks() > 0;
        }

    }

}