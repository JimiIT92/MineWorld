package org.mineworld.event;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.decoration.LeashFenceKnotEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.core.MWEntityTypes;
import org.mineworld.helper.ItemHelper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Handle all events for the {@link LeashFenceKnotEntity Leash Fence Knot}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID)
public final class LeadEvents {

    /**
     * Place the {@link LeashFenceKnotEntity Leash Fence Knot} when the {@link Player Player} is sneaking or has some leashed {@link Entity Entities}
     *
     * @param event {@link PlayerInteractEvent.RightClickBlock The Player Right Click Block Event}
     */
    @SubscribeEvent
    public static void onRightClickBlock(final PlayerInteractEvent.RightClickBlock event) {
        if(!event.isCanceled()) {
            final Player player = event.getEntity();
            final ItemStack itemStack = event.getItemStack();
            final Level level = event.getLevel();
            final BlockPos clickedPos = event.getPos();
            final boolean hasLeashedEntities = !getLeashedEntities(player, level, clickedPos).isEmpty();
            if(itemStack.is(Items.LEAD) || hasLeashedEntities) {
                event.setCanceled(true);
                final InteractionResult result = bindPlayerMobs(player, level, clickedPos, hasLeashedEntities);
                event.setCancellationResult(result);
                if(!hasLeashedEntities && !level.isClientSide && result.equals(InteractionResult.SUCCESS) && !player.isCreative()) {
                    ItemHelper.hurt(event.getItemStack(), player);
                }
            }
        }
    }

    /**
     * Destroy the {@link LeashFenceKnotEntity Leash Knot} when a {@link Entity leashed Entity} dies
     *
     * @param event {@link LivingDeathEvent The Living Death Event}
     */
    @SubscribeEvent
    public static void onLeashedEntityDieEvent(final LivingDeathEvent event) {
        if(event.getEntity() instanceof Mob mob && mob.getLeashHolder() != null && mob.getLeashHolder() instanceof LeashFenceKnotEntity leashKnot) {
            leashKnot.kill();
        }
    }

    /**
     * Get the {@link Entity Entities} leashed to a {@link Player Player}
     *
     * @param player {@link Player The Player}
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link List<Entity> The leashed Entities}
     */
    private static List<? extends Entity> getLeashedEntities(final Player player, final Level level, final BlockPos blockPos) {
        final double distance = 7.0D;
        final double x = blockPos.getX();
        final double y = blockPos.getY();
        final double z = blockPos.getZ();
        return level.getEntitiesOfClass(Mob.class, new AABB(x - distance, y - distance, z - distance, x + distance, y + distance, z + distance))
                .stream().filter(entity -> entity.getLeashHolder() != null && entity.getLeashHolder().is(player)).collect(Collectors.toList());
    }

    /**
     * Attach a {@link LeashFenceKnotEntity Leash Knot} to a fence,
     * even if there are no entities attached
     *
     * @param player {@link Player The Player}
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The Fence Block Pos}
     * @param hasAttachedEntities {@link Boolean If the Player has some leashed entities}
     * @return {@link InteractionResult The interaction result}
     */
    private static InteractionResult bindPlayerMobs(final Player player, final Level level, final BlockPos blockPos, final Boolean hasAttachedEntities) {
        if (level.getBlockState(blockPos).is(BlockTags.FENCES)) {
            if (!level.isClientSide && player != null) {
                final LeashFenceKnotEntity leashKnot = getOrCreateKnot(level, blockPos, hasAttachedEntities);
                if(leashKnot != null) {
                    leashKnot.playPlacementSound();
                    getLeashedEntities(player, level, blockPos).forEach(entity -> ((Mob)entity).setLeashedTo(leashKnot, true));
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
     * Get an existing {@link LeashFenceKnotEntity Leash Knot} or create a new one
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @param hasAttachedEntities {@link Boolean If the Player has some leashed entities}
     * @return {@link LeashFenceKnotEntity The leash knot entity}
     */
    private static LeashFenceKnotEntity getOrCreateKnot(final Level level, final BlockPos blockPos, final boolean hasAttachedEntities) {
        final int x = blockPos.getX();
        final int y = blockPos.getY();
        final int z = blockPos.getZ();
        final Optional<LeashFenceKnotEntity> leashKnot = level.getEntitiesOfClass(LeashFenceKnotEntity.class, new AABB(x - 1, y - 1, z - 1, x + 1, y + 1, z + 1))
                .stream().filter(leashKnotEntity -> leashKnotEntity.getPos().equals(blockPos)).findFirst();
        if(hasAttachedEntities) {
            return leashKnot.orElseGet(() -> getLeashKnot(level, blockPos));
        }
        return leashKnot.isPresent() ? null : getLeashKnot(level, blockPos);
    }

    /**
     * Get a new {@link LeashFenceKnotEntity Leash Knot}
     *
     * @param level {@link Level The level reference}
     * @param blockPos {@link BlockPos The current Block Pos}
     * @return {@link LeashFenceKnotEntity The Leash Knot}
     */
    private static LeashFenceKnotEntity getLeashKnot(final Level level, final BlockPos blockPos) {
        final LeashFenceKnotEntity leashKnotEntity = new LeashFenceKnotEntity(MWEntityTypes.LEASH_KNOT.get(), level);
        leashKnotEntity.setPos(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        level.addFreshEntity(leashKnotEntity);
        return leashKnotEntity;
    }

}