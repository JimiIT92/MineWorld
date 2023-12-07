package org.mineworld.event;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.decoration.LeashFenceKnotEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.block.HollowBlock;
import org.mineworld.block.UnlitTorchBlock;
import org.mineworld.block.WallHangingLanternBlock;
import org.mineworld.core.MWBlocks;
import org.mineworld.core.MWEntityTypes;
import org.mineworld.core.MWTags;
import org.mineworld.helper.ItemHelper;
import org.mineworld.helper.LevelHelper;
import org.mineworld.helper.PlayerHelper;

import java.util.Objects;
import java.util.Optional;

/**
 * Handle the {@link Player player} right clickcing a {@link Block block}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID)
public final class RightClickBlockEventListener {

    /**
     * Attach a {@link LeadItem lead} to a {@link FenceBlock fence} when the {@link Player player}
     * right clicks on it, and it doesn't have a leashed entity
     *
     * @param event {@link PlayerInteractEvent.RightClickBlock Player right click block event}
     */
    @SubscribeEvent
    public static void onRightClickBlock(final PlayerInteractEvent.RightClickBlock event) {
        if(!event.isCanceled()) {
            final Player player = event.getEntity();
            final Level level = event.getLevel();
            final BlockPos clickedPos = event.getPos();
            final BlockState blockState = level.getBlockState(clickedPos);
            final ItemStack itemStack = event.getItemStack();
            final boolean hasLeashedEntities = !PlayerHelper.getLeashedEntities(player, level, clickedPos).isEmpty();
            if(itemStack.isEmpty() && blockState.getBlock() instanceof TorchBlock) {
                handleUnlitTorchPlacement(event, level, clickedPos, player, itemStack, blockState);
                return;
            }
            if(itemStack.is(Items.BONE)) {
                handleRodPlacement(event, level, clickedPos, player, itemStack, MWBlocks.BONE_ROD_BLOCK.get());
                return;
            }
            if(itemStack.is(Items.BLAZE_ROD)) {
                handleRodPlacement(event, level, clickedPos, player, itemStack, MWBlocks.BLAZE_ROD_BLOCK.get());
                return;
            }
            if(itemStack.is(Items.STICK)) {
                handleRodPlacement(event, level, clickedPos, player, itemStack, MWBlocks.STICK_ROD_BLOCK.get());
                return;
            }
            if(itemStack.is(Items.LEAD) || hasLeashedEntities) {
                handleLeashKnot(event, level, clickedPos, player, hasLeashedEntities);
                return;
            }
            if(player.isShiftKeyDown() && itemStack.getItem() instanceof AxeItem) {
                handleHollowLog(event, level, clickedPos, player, itemStack);
                return;
            }
            if(itemStack.is(Items.LANTERN) || itemStack.is(Items.SOUL_LANTERN) || itemStack.is(MWTags.Items.LANTERNS)) {
                handleWallHangingLantern(event, level, clickedPos, player, itemStack);
                return;
            }
            if(itemStack.is(Items.BROWN_MUSHROOM)) {
                handleMushroomPlacement(event, level, clickedPos, player, itemStack, blockState, MWBlocks.BROWN_MUSHROOM_WALL_FAN.get());
                return;
            }
            if(itemStack.is(Items.RED_MUSHROOM)) {
                handleMushroomPlacement(event, level, clickedPos, player, itemStack, blockState, MWBlocks.RED_MUSHROOM_WALL_FAN.get());
                return;
            }
            if(itemStack.getItem() instanceof SwordItem && blockState.getBlock() instanceof CampfireBlock) {
                handleLitCampfirePlacement(event, level, clickedPos, player, itemStack, blockState);
            }
        }
    }

    /**
     * Handle the interaction on a block with an id
     *
     * @param event {@link PlayerInteractEvent.EntityInteractSpecific.RightClickBlock The entity interact right click block event }
     */
    @SubscribeEvent
    public static void onBlockInteract(final PlayerInteractEvent.EntityInteractSpecific.RightClickBlock event) {
        final Player player = event.getEntity();
        final Level level = event.getLevel();
        final BlockPos clickedPos = event.getPos();
        final BlockState blockState = level.getBlockState(clickedPos);
        final ItemStack itemStack = event.getItemStack();
        if(blockState.is(MWTags.Blocks.LECTERNS) && itemStack.is(ItemTags.LECTERN_BOOKS)) {
            event.setCanceled(true);
            InteractionResult result = LecternBlock.tryPlaceBook(player, level, clickedPos, blockState, itemStack) ?
                    InteractionResult.sidedSuccess(level.isClientSide) : InteractionResult.PASS;
            event.setCancellationResult(result);
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
     * Handle interaction with the {@link LeashFenceKnotEntity leash knot}
     *
     * @param event {@link PlayerInteractEvent.RightClickBlock The player right click block event}
     * @param level {@link Level The level reference}
     * @param clickedPos {@link BlockPos The clicked block pos}
     * @param player {@link Player The player interacting with the block}
     * @param hasLeashedEntities {@link Boolean If the player has some leashed entities}
     */
    private static void handleLeashKnot(final PlayerInteractEvent.RightClickBlock event, final Level level, final BlockPos clickedPos, final Player player, final boolean hasLeashedEntities) {
        event.setCanceled(true);
        final InteractionResult result = bindPlayerMobs(player, level, clickedPos, hasLeashedEntities);
        event.setCancellationResult(result);
        if(!hasLeashedEntities && !level.isClientSide && result.equals(InteractionResult.SUCCESS) && !player.isCreative()) {
            ItemHelper.hurt(event.getItemStack(), player);
        }
    }

    /**
     * Handle interaction with an {@link HollowBlock hollow block}
     *
     * @param event {@link PlayerInteractEvent.RightClickBlock The player right click block event}
     * @param level {@link Level The level reference}
     * @param clickedPos {@link BlockPos The clicked block pos}
     * @param player {@link Player The player interacting with the block}
     * @param itemStack {@link ItemStack The id stack used to interact with the block}
     */
    private static void handleHollowLog(final PlayerInteractEvent.RightClickBlock event, final Level level, final BlockPos clickedPos, final Player player, final ItemStack itemStack) {
        if(player.isShiftKeyDown()) {
            HollowBlock.getHollow(level.getBlockState(clickedPos)).ifPresent(hollowState -> {
                level.setBlockAndUpdate(clickedPos, hollowState.setValue(HollowBlock.WATERLOGGED, LevelHelper.isUnderwater(level, clickedPos)));
                ItemHelper.hurt(itemStack, player);
                if(player instanceof ServerPlayer) {
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, clickedPos, itemStack);
                }
                event.setUseItem(Event.Result.DENY);
                if(level.isClientSide()) {
                    player.swing(event.getHand());
                    player.playSound(SoundEvents.AXE_STRIP);
                }
            });
        }
    }

    /**
     * Handle interaction with a {@link WallHangingLanternBlock wall hanging lantern block}
     *
     * @param event {@link PlayerInteractEvent.RightClickBlock The player right click block event}
     * @param level {@link Level The level reference}
     * @param clickedPos {@link BlockPos The clicked block pos}
     * @param player {@link Player The player interacting with the block}
     * @param itemStack {@link ItemStack The id stack used to interact with the block}
     */
    private static void handleWallHangingLantern(final PlayerInteractEvent.RightClickBlock event, final Level level, final BlockPos clickedPos, final Player player, final ItemStack itemStack) {
        final Direction direction = event.getFace();
        if(Objects.requireNonNull(direction).getAxis().isHorizontal() && LevelHelper.isFaceSolid(level, clickedPos, direction)) {
            WallHangingLanternBlock.getWallHangingLantern(itemStack, direction).ifPresent(hangingLantern -> {
                final BlockPos offsetPos = LevelHelper.offset(clickedPos, direction);
                final BlockState blockState = hangingLantern.setValue(HollowBlock.WATERLOGGED, LevelHelper.isUnderwater(level, offsetPos));
                level.setBlockAndUpdate(offsetPos, blockState);
                ItemHelper.hurt(itemStack, player);
                if(player instanceof ServerPlayer) {
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, offsetPos, itemStack);
                }
                event.setUseItem(Event.Result.DENY);
                if(level.isClientSide()) {
                    player.swing(event.getHand());
                    PlayerHelper.playSound(player, SoundEvents.LANTERN_PLACE);
                }
            });
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

    /**
     * Handle interaction with a {@link RodBlock rod block}
     *
     * @param event {@link PlayerInteractEvent.RightClickBlock The player right click block event}
     * @param level {@link Level The level reference}
     * @param clickedPos {@link BlockPos The clicked block pos}
     * @param player {@link Player The player interacting with the block}
     * @param itemStack {@link ItemStack The id stack used to interact with the block}
     * @param rodBlock {@link Block The rod block to place}
     */
    private static void handleRodPlacement(final PlayerInteractEvent.RightClickBlock event, final Level level, final BlockPos clickedPos, final Player player, final ItemStack itemStack, final Block rodBlock) {
        if(player.isShiftKeyDown()) {
            final Direction face = event.getFace();
            final BlockPos rodPos = clickedPos.offset(Objects.requireNonNull(event.getFace()).getNormal());
            if(level.getBlockState(rodPos).isAir()) {
                final BlockState rodState = rodBlock.defaultBlockState().setValue(RodBlock.FACING, Objects.requireNonNull(face).getOpposite());
                level.setBlock(rodPos, rodState, 2);
                player.playSound(rodBlock.getSoundType(rodState, level, rodPos, player).getPlaceSound());
                event.setCanceled(true);
                ItemHelper.hurt(event.getItemStack(), player);
            }
        }
    }

    /**
     * Handle interaction with a {@link TorchBlock torch block}
     *
     * @param event {@link PlayerInteractEvent.RightClickBlock The player right click block event}
     * @param level {@link Level The level reference}
     * @param clickedPos {@link BlockPos The clicked block pos}
     * @param player {@link Player The player interacting with the block}
     * @param itemStack {@link ItemStack The id stack used to interact with the block}
     * @param torchState {@link BlockState The torch Block State}
     */
    private static void handleUnlitTorchPlacement(final PlayerInteractEvent.RightClickBlock event, final Level level, final BlockPos clickedPos, final Player player, final ItemStack itemStack, final BlockState torchState) {
        final Block unlitTorchBlock = UnlitTorchBlock.UNLIT_TORCHES.get().get(torchState.getBlock());
        if(unlitTorchBlock != null) {
            level.setBlock(clickedPos, unlitTorchBlock.withPropertiesOf(torchState), 2);
            player.playSound(SoundEvents.CANDLE_EXTINGUISH);
            player.swing(event.getHand());
            event.setCanceled(true);
        }
    }

    /**
     * Handle interaction with a {@link WallHangingLanternBlock wall hanging lantern block}
     *
     * @param event {@link PlayerInteractEvent.RightClickBlock The player right click block event}
     * @param level {@link Level The level reference}
     * @param clickedPos {@link BlockPos The clicked block pos}
     * @param player {@link Player The player interacting with the block}
     * @param itemStack {@link ItemStack The id stack used to interact with the block}
     * @param clickedBlockState {@link BlockState The clicked BlockState}
     * @param mushroomBlock {@link Block The mushroom block}
     */
    private static void handleMushroomPlacement(final PlayerInteractEvent.RightClickBlock event, final Level level, final BlockPos clickedPos, final Player player, final ItemStack itemStack, final BlockState clickedBlockState, final Block mushroomBlock) {
        final Direction face = event.getFace();
        final BlockPos placePos = clickedPos.offset(face.getNormal());
        boolean isValidFace = face != Direction.DOWN && face != Direction.UP && clickedBlockState.isFaceSturdy(level, placePos, face) && level.getBlockState(placePos).canBeReplaced();
        if(isValidFace) {
            if(!level.isClientSide()) {
                level.setBlockAndUpdate(placePos, mushroomBlock.defaultBlockState()
                        .setValue(BaseCoralPlantTypeBlock.WATERLOGGED, level.getFluidState(placePos).is(Fluids.WATER))
                        .setValue(BaseCoralWallFanBlock.FACING, face)
                );
                ItemHelper.hurt(itemStack, player);
            } else {
                player.swing(event.getHand());
                player.playSound(SoundEvents.GRASS_PLACE, 1.0F, 1.0F);
            }
        }
    }

    /**
     * Handle interaction with a {@link CampfireBlock campfire block}
     *
     * @param event {@link PlayerInteractEvent.RightClickBlock The player right click block event}
     * @param level {@link Level The level reference}
     * @param clickedPos {@link BlockPos The clicked block pos}
     * @param player {@link Player The player interacting with the block}
     * @param itemStack {@link ItemStack The id stack used to interact with the block}
     * @param clickedBlockState {@link BlockState The clicked BlockState}
     */
    private static void handleLitCampfirePlacement(final PlayerInteractEvent.RightClickBlock event, final Level level, final BlockPos clickedPos, final Player player, final ItemStack itemStack, final BlockState clickedBlockState) {
        if(itemStack.isEnchanted() && itemStack.getAllEnchantments().containsKey(Enchantments.FIRE_ASPECT) && !clickedBlockState.getValue(CampfireBlock.LIT)) {
            if(!level.isClientSide()) {
                level.setBlockAndUpdate(clickedPos, clickedBlockState.setValue(CampfireBlock.LIT, true));
                ItemHelper.hurt(itemStack, player);
            } else {
                player.swing(event.getHand());
                player.playSound(SoundEvents.FLINTANDSTEEL_USE);
            }
        }
    }

}