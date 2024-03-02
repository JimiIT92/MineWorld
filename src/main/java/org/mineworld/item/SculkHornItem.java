package org.mineworld.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.warden.SonicBoom;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.helper.ItemHelper;
import org.mineworld.helper.PropertyHelper;

import java.util.HashSet;

/**
 * {@link MineWorld MineWorld} {@link Item Sculk Horn Item}
 */
public class SculkHornItem extends Item {

    /**
     * Constructor. Set the {@link Properties Item Properties}
     */
    public SculkHornItem() {
        super(PropertyHelper.item().stacksTo(1).durability(64).rarity(Rarity.EPIC));
    }

    /**
     * Get the {@link UseAnim Item Use Animation}
     *
     * @param itemStack {@link ItemStack The current Item Stack}
     * @return {@link UseAnim#TOOT_HORN The Toot Horn Animation}
     */
    public @NotNull UseAnim getUseAnimation(final @NotNull ItemStack itemStack) {
        return UseAnim.TOOT_HORN;
    }

    /**
     * Get the {@link Integer Item Use Duration}
     *
     * @param itemStack {@link ItemStack The current Item Stack}
     * @return {@link Integer 100}
     */
    public int getUseDuration(final @NotNull ItemStack itemStack) {
        return 100;
    }

    /**
     * Use the {@link Item Sculk Horn}
     *
     * @param level {@link Level The level reference}
     * @param player {@link Player The player that is shooting the grenade}
     * @param hand {@link InteractionHand The hand the player is shooting with}
     * @return {@link InteractionResultHolder<ItemStack> The interaction result}
     */
    public @NotNull InteractionResultHolder<ItemStack> use(final @NotNull Level level, final Player player, final @NotNull InteractionHand hand) {
        final ItemStack itemStack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        sonicBoom(level, player);
        player.playSound(SoundEvents.WARDEN_SONIC_BOOM);
        ItemHelper.hurt(itemStack, player);
        ItemHelper.setCooldown(player, this, getUseDuration(itemStack));
        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.consume(itemStack);
    }

    /**
     * Make the {@link Player Player} use the {@link SonicBoom Sonic Boom attack}
     *
     * @param level {@link ServerLevel The level reference}
     * @param player {@link Player The player using the Sculk Horn}
     */
    private void sonicBoom(final Level level, final Player player) {
        if(!level.isClientSide) {
            final Vec3 playerPos = player.position();
            final Vec3 source = playerPos.add(0.0,1.6f,0.0);
            final Vec3 offset = playerPos.add(player.getLookAngle().scale(16)).subtract(source);
            final Vec3 normalizedOffset = offset.normalize();

            final HashSet<LivingEntity> targets = new HashSet<>();
            for(int i = 1; i < Mth.floor(offset.length()) + 7; ++i){
                final Vec3 particlePos = source.add(normalizedOffset.scale(i));
                ((ServerLevel) level).sendParticles(ParticleTypes.SONIC_BOOM, particlePos.x, particlePos.y, particlePos.z, 1,0.0, 0.0, 0.0, 0.0);
                targets.addAll(level.getEntitiesOfClass(LivingEntity.class, new AABB(new BlockPos((int) particlePos.x, (int) particlePos.y, (int) particlePos.z)).inflate(2), LivingEntity::attackable).stream().toList());
            }

            targets.stream().filter(entity -> !entity.equals(player))
                    .forEach(entity -> {
                        final double knockbackResistanceY = 0.5D * (1.0D - entity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
                        final double knockbackResistanceXZ = 2.5D * (1.0D - entity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
                        entity.push(normalizedOffset.x() * knockbackResistanceXZ, normalizedOffset.y() * knockbackResistanceY, normalizedOffset.z() * knockbackResistanceXZ);
                    }
            );
        }
    }

}