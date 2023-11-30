package org.mineworld.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.mineworld.entity.ThrownGrenade;
import org.mineworld.helper.ItemHelper;
import org.mineworld.helper.PropertyHelper;

/**
 * Implementation class for a grenade
 */
public class GrenadeItem extends Item {

    /**
     * Constructor. Set the item properties
     */
    public GrenadeItem() {
        super(PropertyHelper.basicItemProperties());
    }

    /**
     * Throw the grenade
     *
     * @param level {@link Level The level reference}
     * @param player {@link Player The player throwing the grenade}
     * @param hand {@link InteractionHand The hand the player is interacting with}
     * @return {@link InteractionResultHolder<ItemStack> The interaction result}
     */
    public @NotNull InteractionResultHolder<ItemStack> use(final @NotNull Level level, final Player player, final @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.FLINTANDSTEEL_USE, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
        ItemHelper.setCooldown(player, this, 60);
        if (!level.isClientSide) {
            ThrownGrenade grenade = new ThrownGrenade(level, player);
            grenade.setItem(itemstack);
            grenade.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(grenade);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        ItemHelper.hurt(itemstack, player);

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }

}