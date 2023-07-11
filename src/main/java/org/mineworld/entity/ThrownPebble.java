package org.mineworld.entity;

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
import org.jetbrains.annotations.NotNull;
import org.mineworld.core.MWEntityTypes;
import org.mineworld.core.MWItems;
import org.mineworld.helper.ItemHelper;

/**
 * Implementation class for a {@link ThrowableItemProjectile throwable pebble}
 */
public class ThrownPebble extends ThrowableItemProjectile {

    /**
     * Constructor. Set the {@link EntityType entity type}
     *
     * @param entityType {@link EntityType The entity type for this pebble}
     * @param level {@link Level The world reference}
     */
    public ThrownPebble(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        super(entityType, level);
    }

    /**
     * Constructor. Set the {@link LivingEntity shooter}
     *
     * @param level {@link Level The world reference}
     * @param shooter {@link LivingEntity The entity that shoot the pebble}
     */
    public ThrownPebble(Level level, LivingEntity shooter) {
        super(MWEntityTypes.PEBBLE.get(), shooter, level);
    }

    /**
     * Constructor. Set the {@link BlockPos entity position}
     *
     * @param level {@link Level The world reference}
     * @param posX {@link Double The pebble X coordinate}
     * @param posY {@link Double The pebble Y coordinate}
     * @param posZ {@link Double The pebble Z coordinate}
     */
    public ThrownPebble(Level level, double posX, double posY, double posZ) {
        super(MWEntityTypes.PEBBLE.get(), posX, posY, posZ, level);
    }

    /**
     * Get the {@link Item default id} if is not set
     *
     * @return {@link MWItems#STONE_PEBBLE The stone pebble id}
     */
    @Override
    protected @NotNull Item getDefaultItem() {
        return MWItems.STONE_PEBBLE.get();
    }

    /**
     * Get the {@link ParticleOptions entity particle to spawn on entity collide}
     *
     * @return {@link ParticleOptions Item particles}
     */
    private ParticleOptions getParticle() {
        ItemStack itemstack = this.getItemRaw();
        return new ItemParticleOption(ParticleTypes.ITEM, itemstack.isEmpty() ? ItemHelper.getDefaultStack(getDefaultItem()) : itemstack);
    }

    /**
     * Spawn the {@link ParticleOptions entity particles on entity collide}
     *
     * @param event {@link Byte The entity event byte}
     */
    public void handleEntityEvent(byte event) {
        if (event == 3) {
            ParticleOptions particleoptions = this.getParticle();
            for(int i = 0; i < 8; ++i) {
                this.level.addParticle(particleoptions, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    /**
     * Damage an entity on hit
     *
     * @param entityHitResult {@link EntityHitResult The entity hit result}
     */
    protected void onHitEntity(@NotNull EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        entityHitResult.getEntity().hurt(this.damageSources().thrown(this, this.getOwner()), 1.0F);
    }

    /**
     * Spawn the {@link ParticleOptions entity particles on entity collide}
     *
     * @param hitResult {@link HitResult The hit result}
     */
    protected void onHit(@NotNull HitResult hitResult) {
        super.onHit(hitResult);
        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }
}