package org.mineworld.item;

import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.helper.ItemHelper;
import org.mineworld.helper.PropertyHelper;
import org.mineworld.helper.SonicBoomHelper;

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
     * @param player {@link Player The player that is using the item}
     * @param hand {@link InteractionHand The hand the player is interacting with}
     * @return {@link InteractionResultHolder<ItemStack> The interaction result}
     */
    public @NotNull InteractionResultHolder<ItemStack> use(final @NotNull Level level, final Player player, final @NotNull InteractionHand hand) {
        final ItemStack itemStack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        SonicBoomHelper.fire(level, player, 7, 0);
        ItemHelper.hurt(itemStack, player);
        ItemHelper.setCooldown(player, this, getUseDuration(itemStack));
        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.consume(itemStack);
    }

}