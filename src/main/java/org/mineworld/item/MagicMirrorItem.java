package org.mineworld.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWCriteriaTriggers;
import org.mineworld.core.MWSounds;
import org.mineworld.helper.ItemHelper;
import org.mineworld.helper.PlayerHelper;
import org.mineworld.helper.PropertyHelper;

/**
 * {@link MineWorld MineWorld} {@link Item Magic Mirror}
 */
public class MagicMirrorItem extends Item {

    /**
     * Constructor. Set the {@link Properties Item Properties}
     */
    public MagicMirrorItem() {
        super(PropertyHelper.item().rarity(Rarity.UNCOMMON).stacksTo(1));
    }

    /**
     * Teleport the {@link Player Player} on their spawn location
     *
     * @param level {@link Level The level reference}
     * @param player {@link Player The player that is using the Magic Mirror}
     * @param hand {@link InteractionHand The hand the player is shooting with}
     * @return {@link InteractionResultHolder<ItemStack> The interaction result}
     */
    public @NotNull InteractionResultHolder<ItemStack> use(final @NotNull Level level, final Player player, final @NotNull InteractionHand hand) {
        final ItemStack itemStack = player.getItemInHand(hand);
        ItemHelper.setCooldown(player, this, 100);
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            if(level.dimensionType().bedWorks() || level.dimensionType().respawnAnchorWorks()) {
                final ResourceKey<Level> dimension = level.dimension();
                BlockPos respawnPos = serverPlayer.getRespawnPosition();
                if(respawnPos == null || level.getBlockState(respawnPos).isAir()) {
                    if(dimension.equals(Level.OVERWORLD)) {
                        respawnPos = level.getSharedSpawnPos();
                    } else {
                        respawnPos = null;
                    }
                }
                if(respawnPos != null) {
                    if(dimension.equals(Level.NETHER)) {
                        respawnPos = respawnPos.above();
                    }
                    PlayerHelper.teleport(player, respawnPos);
                    level.playSound(player, respawnPos, MWSounds.MAGIC_MIRROR.get(), SoundSource.AMBIENT);
                    MWCriteriaTriggers.USE_MAGIC_MIRROR.trigger((ServerPlayer) player);
                } else {
                    showInvalidSpawnMessage(player);
                }
            } else {
                showInvalidSpawnMessage(player);
            }
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }

    /**
     * Shows the invalid spawn message
     *
     * @param player {@link Player The Player to show the message}
     */
    private void showInvalidSpawnMessage(final Player player) {
        player.displayClientMessage(Component.translatable("message.mineworld.magic_mirror_invalid_spawn").withStyle(ChatFormatting.RED), true);
    }

}