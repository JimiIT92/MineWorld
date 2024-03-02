package org.mineworld.entity.projectile;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWEntityTypes;
import org.mineworld.core.MWItems;
import org.mineworld.item.GrenadeItem;

/**
 * {@link MineWorld MineWorld} class for a thrown {@link GrenadeItem Grenade}
 */
public class ThrownGrenade extends ThrowableItemProjectile {

    /**
     * Constructor. Set the {@link EntityType Entity Type}
     *
     * @param entityType {@link EntityType The Entity Type}
     * @param level {@link Level The level reference}
     */
    public ThrownGrenade(final EntityType<? extends ThrowableItemProjectile> entityType, final Level level) {
        super(entityType, level);
    }

    /**
     * Constructor. Set the {@link LivingEntity Shooter}
     *
     * @param level {@link Level The level reference}
     * @param shooter {@link LivingEntity The entity that shoot the pebble}
     */
    public ThrownGrenade(final Level level, final LivingEntity shooter) {
        super(MWEntityTypes.GRENADE.get(), shooter, level);
    }

    /**
     * Constructor. Set the {@link BlockPos entity position}
     *
     * @param level {@link Level The level reference}
     * @param x {@link Double The entity X coordinate}
     * @param y {@link Double The entity Y coordinate}
     * @param z {@link Double The entity Z coordinate}
     */
    public ThrownGrenade(final Level level, final double x, final double y, final double z) {
        super(MWEntityTypes.GRENADE.get(), x, y, z, level);
    }

    /**
     * Get the {@link Item default Item} if not set
     *
     * @return {@link MWItems#GRENADE The Grenade Item}
     */
    @Override
    protected @NotNull Item getDefaultItem() {
        return MWItems.GRENADE.get();
    }

    /**
     * Get the {@link ParticleOptions entity particle to spawn on entity collide}
     *
     * @return {@link ParticleOptions Item particles}
     */
    private ParticleOptions getParticle() {
        final ItemStack itemstack = this.getItemRaw();
        return new ItemParticleOption(ParticleTypes.ITEM, itemstack.isEmpty() ? getDefaultItem().getDefaultInstance() : itemstack);
    }

    /**
     * Spawn the {@link ParticleOptions entity particles on entity collide}
     *
     * @param event {@link Byte The entity event byte}
     */
    public void handleEntityEvent(final byte event) {
        if (event == 3) {
            final ParticleOptions particleoptions = this.getParticle();
            for(int i = 0; i < 8; ++i) {
                this.level().addParticle(particleoptions, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    /**
     * Damage an entity on hit
     *
     * @param entityHitResult {@link EntityHitResult The entity hit result}
     */
    protected void onHitEntity(final @NotNull EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        entityHitResult.getEntity().hurt(this.damageSources().thrown(this, this.getOwner()), 1.25F);
    }

    /**
     * Spawn the {@link ParticleOptions entity particles on entity collide}
     *
     * @param hitResult {@link HitResult The hit result}
     */
    protected void onHit(final @NotNull HitResult hitResult) {
        super.onHit(hitResult);
        final Level level = this.level();
        if (!level.isClientSide) {
            level.broadcastEntityEvent(this, (byte)3);
            final Vec3 pos = this.position();
            level.explode(this, pos.x, pos.y, pos.z, 2.5F, Level.ExplosionInteraction.TNT);
            this.discard();
        }
    }

}