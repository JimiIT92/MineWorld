package org.mineworld.event;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.decoration.LeashFenceKnotEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.LeadItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.core.MWEntityTypes;
import org.mineworld.helper.PlayerHelper;

import java.util.Optional;

/**
 * Handle the {@link Player player} right clickcing a {@link Block block}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MODID)
public final class LeashKnotEventListener {

    /**
     * Attach a {@link LeadItem lead} to a {@link FenceBlock fence} when the {@link Player player} right clicks on it
     * and it doesn't have a leashed entity
     *
     * @param event {@link PlayerInteractEvent.RightClickBlock Player right click block event}
     */
    @SubscribeEvent
    public static void onRightClickBlock(final PlayerInteractEvent.RightClickBlock event) {
        final Player player = event.getEntity();
        final Level level = event.getLevel();
        final BlockPos clickedPos = event.getPos();
        final boolean hasLeashedEntities = !PlayerHelper.getLeashedEntities(player, level, clickedPos).isEmpty();
        if(event.getItemStack().is(Items.LEAD) || hasLeashedEntities) {
            event.setCanceled(true);
            final InteractionResult result = bindPlayerMobs(player, level, clickedPos, hasLeashedEntities);
            event.setCancellationResult(result);
            if(!hasLeashedEntities && !level.isClientSide && result.equals(InteractionResult.SUCCESS) && !player.isCreative()) {
                event.getItemStack().shrink(1);
            }
        }
    }

    /**
     * Destroy the {@link LeashFenceKnotEntity leash knot} when an entity attached to it dies
     *
     * @param event {@link LivingHurtEvent Living hurt event}
     */
    @SubscribeEvent
    public static void onLeashedEntityDieEvent(final LivingDeathEvent event) {
        if(event.getEntity() instanceof Mob mob && mob.getLeashHolder() != null && mob.getLeashHolder() instanceof LeashFenceKnotEntity leashKnot) {
            leashKnot.kill();
        }
    }

    /**
     * Attach a {@link LeashFenceKnotEntity leash knot} to a fence,
     * even if there are no entities attached
     *
     * @param player {@link Player The palyer attaching the leash knot}
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The fence block pos}
     * @param hasAttachedEntities {@link Boolean If the player has some attached entities}
     * @return {@link InteractionResult The interaction result}
     */
    private static InteractionResult bindPlayerMobs(final Player player, final Level level, final BlockPos blockPos, final Boolean hasAttachedEntities) {
        if (level.getBlockState(blockPos).is(BlockTags.FENCES)) {
            if (!level.isClientSide && player != null) {
                LeashFenceKnotEntity leashKnot = getOrCreateKnot(level, blockPos, hasAttachedEntities);
                if(leashKnot != null) {
                    leashKnot.playPlacementSound();
                    PlayerHelper.getLeashedEntities(player, level, blockPos).forEach(entity -> ((Mob)entity).setLeashedTo(leashKnot, true));
                    level.gameEvent(GameEvent.BLOCK_ATTACH, blockPos, GameEvent.Context.of(player));
                    return InteractionResult.SUCCESS;
                }
                return InteractionResult.PASS;
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return InteractionResult.PASS;
    }

    /**
     * Get an existing {@link LeashFenceKnotEntity leash knot} or create a new one
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @param hasAttachedEntities {@link Boolean If the player has some attached entities}
     * @return {@link LeashFenceKnotEntity The leash knot entity}
     */
    private static LeashFenceKnotEntity getOrCreateKnot(final Level level, final BlockPos blockPos, final boolean hasAttachedEntities) {
        int x = blockPos.getX();
        int y = blockPos.getY();
        int z = blockPos.getZ();
        Optional<LeashFenceKnotEntity> leashKnot = level.getEntitiesOfClass(LeashFenceKnotEntity.class, new AABB(x - 1, y - 1, z - 1, x + 1, y + 1, z + 1))
                .stream().filter(leashKnotEntity -> leashKnotEntity.getPos().equals(blockPos)).findFirst();
        if(hasAttachedEntities) {
            return leashKnot.orElseGet(() -> getLeashKnot(level, blockPos));
        }
        return leashKnot.isPresent() ? null : getLeashKnot(level, blockPos);
    }

    /**
     * Get a new {@link LeashFenceKnotEntity leash knot entity}
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current block pos}
     * @return {@link LeashFenceKnotEntity The leash knot entity}
     */
    private static LeashFenceKnotEntity getLeashKnot(final Level level, final BlockPos blockPos) {
        LeashFenceKnotEntity leashKnotEntity = new LeashFenceKnotEntity(MWEntityTypes.LEASH_KNOT.get(), level);
        leashKnotEntity.setPos(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        level.addFreshEntity(leashKnotEntity);
        return leashKnotEntity;
    }

}