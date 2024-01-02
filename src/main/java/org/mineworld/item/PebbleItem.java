package org.mineworld.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.mineworld.entity.projectile.ThrownPebble;
import org.mineworld.helper.ItemHelper;
import org.mineworld.helper.PropertyHelper;

import java.util.function.Supplier;

public class PebbleItem extends ItemNameBlockItem {

    /**
     * Constructor. Set the Item properties
     *
     * @param pebbleBlockSupplier {@link Supplier<Block> The Pebble Block that will be placed from this Item}
     * @param featureFlags {@link FeatureFlag The Feature Flags that must be enabled for the Item to work}
     */
    public PebbleItem(final Supplier<Block> pebbleBlockSupplier, final FeatureFlag... featureFlags) {
        super(pebbleBlockSupplier.get(), PropertyHelper.item(featureFlags));
    }

    /**
     * Shoot the pebble on right click
     *
     * @param level {@link Level The level reference}
     * @param player {@link Player The player that is shooting the pebble}
     * @param hand {@link InteractionHand The hand the player is shooting with}
     * @return {@link InteractionResultHolder<ItemStack> The interaction result}
     */
    public @NotNull InteractionResultHolder<ItemStack> use(final Level level, final Player player, final @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!level.isClientSide) {
            ThrownPebble pebble = new ThrownPebble(level, player);
            pebble.setItem(itemstack);
            pebble.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(pebble);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        ItemHelper.hurt(itemstack, player);

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }

}
