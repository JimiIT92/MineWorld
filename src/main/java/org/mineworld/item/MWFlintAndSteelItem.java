package org.mineworld.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.block.MWPortalBlock;
import org.mineworld.helper.ItemHelper;

/**
 * Base class for a {@link MineWorld MineWorld} flint and steel item
 */
public abstract class MWFlintAndSteelItem extends FlintAndSteelItem {

    /**
     * Constructor. Set the item properties
     */
    public MWFlintAndSteelItem() {
        super(new Item.Properties().durability(64));
    }

    /**
     * Get the {@link ResourceKey<Level> portal dimension key} that can be lit by this flint and steel
     *
     * @return {@link ResourceKey<Level> The portal dimension key}
     */
    public abstract ResourceKey<Level> getDimension();

    /**
     * Get the {@link RegistryObject<Block> portal block}
     *
     * @return {@link RegistryObject<Block> The portal block}
     */
    public abstract RegistryObject<Block> getPortalBlock();

    /**
     * Get the {@link SoundEvent portal trigger sound}
     *
     * @return {@link SoundEvent The portal trigger sound}
     */
    public SoundEvent getPortalTriggerSound() {
        return SoundEvents.PORTAL_TRIGGER;
    }

    /**
     * Get the {@link BlockState fire block state}
     *
     * @param level {@link Level The level reference}
     * @param pos {@link BlockPos The current block pos}
     * @return {@link BlockState The fire block state}
     */
    public BlockState getFireBlockState(final Level level, final BlockPos pos) {
        return BaseFireBlock.getState(level, pos);
    }

    /**
     * Check if a fire block can be placed at the current location
     *
     * @param level {@link Level The level reference}
     * @param pos {@link BlockPos The current block pos}
     * @param direction {@link Direction The clicked direction}
     * @return {@link Boolean True if a fire block can be placed}
     */
    public boolean canPlaceFire(final Level level, final BlockPos pos, final Direction direction) {
        return BaseFireBlock.canBePlacedAt(level, pos, direction);
    }

    /**
     * Place the fire or light a portal on block right click
     *
     * @param context {@link UseOnContext The item use context}
     * @return {@link InteractionResult The item use interaction result}
     */
    public @NotNull InteractionResult useOn(final UseOnContext context) {
        final Player player = context.getPlayer();
        final Level level = context.getLevel();
        final BlockPos clickedPos = context.getClickedPos();
        final BlockState blockState = level.getBlockState(clickedPos);
        final Direction face = context.getClickedFace();
        final ResourceKey<Level> dimension = level.dimension();
        if(dimension.equals(getDimension()) || dimension.equals(Level.OVERWORLD)) {
            for (Direction direction : Direction.Plane.VERTICAL) {
                final BlockPos framePos = clickedPos.relative(direction);
                if(((MWPortalBlock)getPortalBlock().get()).trySpawnPortal(level, clickedPos.relative(face))) {
                    level.playSound(player, framePos, getPortalTriggerSound(), SoundSource.BLOCKS, 0.5F, 0.5F);
                    level.playSound(player, clickedPos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
                    if(player instanceof ServerPlayer) {
                        ItemHelper.hurt(context.getItemInHand(), player);
                    }
                    return InteractionResult.sidedSuccess(level.isClientSide());
                }
            }
        }
        if (!CampfireBlock.canLight(blockState) && !CandleBlock.canLight(blockState) && !CandleCakeBlock.canLight(blockState)) {
            final BlockPos relativePos = clickedPos.relative(face);
            if (canPlaceFire(level, relativePos, context.getHorizontalDirection())) {
                level.playSound(player, relativePos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
                level.setBlock(relativePos, getFireBlockState(level, relativePos), 11);
                level.gameEvent(player, GameEvent.BLOCK_PLACE, clickedPos);
                ItemStack itemstack = context.getItemInHand();
                if (player instanceof ServerPlayer) {
                    CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)player, relativePos, itemstack);
                    ItemHelper.hurt(itemstack, player);
                }

                return InteractionResult.sidedSuccess(level.isClientSide());
            } else {
                return InteractionResult.FAIL;
            }
        }

        return super.useOn(context);
    }
}