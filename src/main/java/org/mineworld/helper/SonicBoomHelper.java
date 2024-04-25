package org.mineworld.helper;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.warden.SonicBoom;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.HashSet;

/**
 * Helper class for firing a {@link SonicBoom Sonic Boom} attack
 */
public final class SonicBoomHelper {

    /**
     * Make the {@link LivingEntity Entity} use the {@link SonicBoom Sonic Boom attack}
     *
     * @param level {@link ServerLevel The level reference}
     * @param attacker {@link LivingEntity The entity firing the Sonic Boom}
     * @param distance {@link Integer The distance at which the Sonic Boom attack should fire}
     * @param damage {@link Float The Sonic Boom damage amount}
     */
    public static void fire(final Level level, final LivingEntity attacker, final int distance, final float damage) {
        if(!level.isClientSide) {
            final Vec3 attackerPos = attacker.position();
            final Vec3 source = attackerPos.add(0.0,1.6f,0.0);
            final Vec3 offset = attackerPos.add(attacker.getLookAngle().scale(16)).subtract(source);
            final Vec3 normalizedOffset = offset.normalize();

            final HashSet<LivingEntity> targets = new HashSet<>();
            for(int i = 1; i < Mth.floor(offset.length()) + distance; ++i) {
                final Vec3 particlePos = source.add(normalizedOffset.scale(i));
                ((ServerLevel) level).sendParticles(ParticleTypes.SONIC_BOOM, particlePos.x, particlePos.y, particlePos.z, 1,0.0, 0.0, 0.0, 0.0);
                targets.addAll(level.getEntitiesOfClass(LivingEntity.class, new AABB(new BlockPos((int) particlePos.x, (int) particlePos.y, (int) particlePos.z)).inflate(2), LivingEntity::attackable).stream().toList());
            }

            targets.stream().filter(entity -> !entity.equals(attacker)).forEach(entity -> {
                final double knockbackResistanceY = 0.5D * (1.0D - entity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
                final double knockbackResistanceXZ = 2.5D * (1.0D - entity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
                entity.push(normalizedOffset.x() * knockbackResistanceXZ, normalizedOffset.y() * knockbackResistanceY, normalizedOffset.z() * knockbackResistanceXZ);
                if(damage > 0) {
                    entity.hurt(level.damageSources().sonicBoom(attacker), damage);
                }
            });

            attacker.playSound(SoundEvents.WARDEN_SONIC_BOOM);
        }
    }
}