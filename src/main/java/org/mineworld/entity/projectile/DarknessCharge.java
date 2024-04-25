package org.mineworld.entity.projectile;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.mineworld.core.MWEntityTypes;

/**
 * Projectile class for a {@link AbstractHurtingProjectile Darkness Charge}
 */
public class DarknessCharge extends AbstractHurtingProjectile {

    /**
     * Constructor. Set the entity properties
     *
     * @param entityType {@link EntityType The entity type}
     * @param level {@link Level The level reference}
     */
    public DarknessCharge(final EntityType<? extends DarknessCharge> entityType, final Level level) {
        super(entityType, level);
    }

    /**
     * Constructor. Set the entity properties and location
     *
     * @param level {@link Level The level reference}
     * @param entity {@link LivingEntity The entity reference}
     * @param x {@link Double The entity X coordinate}
     * @param y {@link Double The entity Y coordinate}
     * @param z {@link Double The entity Z coordinate}
     */
    public DarknessCharge(final Level level, final LivingEntity entity, final double x, final double y, final double z) {
        super(MWEntityTypes.DARKNESS_CHARGE.get(), entity, x, y, z, level);
    }

    @Override
    public void tick() {
        super.tick();
        final Vec3 movementVec = this.getDeltaMovement();
        final double x = this.getX() + movementVec.x;
        final double y = this.getY() + movementVec.y;
        final double z = this.getZ() + movementVec.z;
        this.level().addParticle(ParticleTypes.SCULK_SOUL, x - movementVec.x * 0.25D + this.random.nextDouble() * 0.6D - 0.3D, y - movementVec.y * 0.25D - 0.5D, z - movementVec.z * 0.25D + this.random.nextDouble() * 0.6D - 0.3D, movementVec.x, movementVec.y, movementVec.z);
    }

    /**
     * Check if the projectile is on fire
     *
     * @return {@link Boolean#FALSE False}
     */
    public boolean isOnFire() {
        return false;
    }

    /**
     * Check if the projectile can be picked up
     *
     * @return {@link Boolean#FALSE False}
     */
    public boolean isPickable() {
        return false;
    }

    /**
     * Hurt the projectile
     *
     * @param damageSource {@link DamageSource The damage source}
     * @param amount {@link Float The damage amount}
     * @return {@link Boolean#FALSE False}
     */
    public boolean hurt(final @NotNull DamageSource damageSource, final float amount) {
        return false;
    }

    /**
     * Check if the projectile should burn
     *
     * @return {@link Boolean#FALSE False}
     */
    protected boolean shouldBurn() {
        return false;
    }

    /**
     * Apply the {@link MobEffects#DARKNESS darkness effect} on entity hit
     *
     * @param entityHitResult {@link EntityHitResult The entity hit result}
     */
    protected void onHitEntity(final @NotNull EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        if (!this.level().isClientSide) {
            final Entity entityHit = entityHitResult.getEntity();
            boolean hurt;
            if (this.getOwner() instanceof LivingEntity owner) {
                hurt = entityHit.hurt( this.damageSources().mobProjectile(this, owner), 1.0F);
                if (hurt) {
                    if (entityHit.isAlive()) {
                        this.doEnchantDamageEffects(owner, entityHit);
                        owner.heal(10.0F);
                    }
                }
            } else {
                hurt = entityHit.hurt(this.damageSources().magic(), 1.0F);
            }

            if (hurt && entityHit instanceof LivingEntity target) {
                final int seconds = switch (this.level().getDifficulty()) {
                    case NORMAL -> 10;
                    case HARD -> 30;
                    default -> 0;
                };

                if (seconds > 0) {
                    target.removeAllEffects();
                    target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20 * seconds, 2), this.getEffectSource());
                    target.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 20 * seconds, 2), this.getEffectSource());
                }
            }

        }
    }

}