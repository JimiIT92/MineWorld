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
import org.mineworld.block.PebbleBlock;
import org.mineworld.entity.Pebble;
import org.mineworld.helper.PropertyHelper;

/**
 * Implementation class for a throwable {@link PebbleBlock pebble item}
 */
public class PebbleItem extends ItemNameBlockItem {

    /**
     * Constructor. Set the item properties
     *
     * @param pebbleBlock {@link Block The pebble block to be placed when right clicking on a block}
     * @param featureFlags {@link FeatureFlag Any feature flag that needs to be enabled for the block to be functional}
     */
    public PebbleItem(final Block pebbleBlock, final FeatureFlag... featureFlags) {
        super(pebbleBlock, PropertyHelper.basicItemProperties(featureFlags));
    }

    /**
     * Shoot the pebble on item right click
     *
     * @param level {@link Level The world reference}
     * @param player {@link Player The player that is shooting the pebble}
     * @param hand {@link InteractionHand The hand the player is shooting with}
     * @return {@link InteractionResultHolder<ItemStack> The interaction result}
     */
    public @NotNull InteractionResultHolder<ItemStack> use(final Level level, final Player player, final @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!level.isClientSide) {
            Pebble pebble = new Pebble(level, player);
            pebble.setItem(itemstack);
            pebble.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(pebble);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild) {
            itemstack.shrink(1);
        }

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }
}