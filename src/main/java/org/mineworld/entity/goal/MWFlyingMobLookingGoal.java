package org.mineworld.entity.goal;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import org.mineworld.MineWorld;

import java.util.EnumSet;

/**
 * {@link MineWorld MineWorld} goal for a flying mob looking
 */
public class MWFlyingMobLookingGoal extends Goal {

    /**
     * {@link Mob The flying mob}
     */
    private final Mob mob;

    /**
     * Constructor. Set the {@link Mob flying mob}
     *
     * @param mob {@link Mob The flying mob}
     */
    public MWFlyingMobLookingGoal(final Mob mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Goal.Flag.LOOK));
    }

    /**
     * Check if the mob can look around
     *
     * @return {@link Boolean#TRUE True}
     */
    @Override
    public boolean canUse() {
        return true;
    }

    /**
     * Check if this goal should run every tick
     *
     * @return {@link Boolean#TRUE True}
     */
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    /**
     * Make the mob look around
     */
    public void tick() {
        if (this.mob.getTarget() == null) {
            final Vec3 deltaMovement = this.mob.getDeltaMovement();
            this.mob.setYRot(-((float) Mth.atan2(deltaMovement.x, deltaMovement.z)) * (180F / (float)Math.PI));
            this.mob.yBodyRot = this.mob.getYRot();
        } else {
            LivingEntity target = this.mob.getTarget();
            if (target.distanceToSqr(this.mob) < 4096.0D) {
                final double x = target.getX() - this.mob.getX();
                final double y = target.getZ() - this.mob.getZ();
                this.mob.setYRot(-((float)Mth.atan2(x, y)) * (180F / (float)Math.PI));
                this.mob.yBodyRot = this.mob.getYRot();
            }
        }
    }

}