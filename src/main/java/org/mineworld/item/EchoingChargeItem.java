package org.mineworld.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.block.MWPortalBlock;
import org.mineworld.core.MWBlocks;
import org.mineworld.core.MWDimensions;
import org.mineworld.core.MWItems;
import org.mineworld.helper.ItemHelper;
import org.mineworld.helper.PropertyHelper;

/**
 * {@link MineWorld MineWorld} {@link Item Echoing Charge Item}
 */
public class EchoingChargeItem extends Item {

    /**
     * Constructor. Set the {@link Properties Item Properties}
     */
    public EchoingChargeItem() {
        super(PropertyHelper.item().rarity(Rarity.EPIC));
    }

    /**
     * Open the {@link MWDimensions#ETHEREAL Ethereal Dimension} portal when right clicking
     * on the Deep Dark portal
     *
     * @param context {@link UseOnContext The Item Use On Context}
     * @return {@link InteractionResult The Item Use Interaction Result}
     */
    public @NotNull InteractionResult useOn(final UseOnContext context) {
        final Player player = context.getPlayer();
        if(player != null) {
            final Level level = context.getLevel();
            final BlockPos clickedPos = context.getClickedPos();
            final BlockState blockState = level.getBlockState(clickedPos);
            final Direction face = context.getClickedFace();
            final ResourceKey<Level> dimension = level.dimension();
            if(player.getOffhandItem().is(MWItems.SCULK_HEART.get()) && (dimension.equals(MWDimensions.ETHEREAL) || dimension.equals(Level.OVERWORLD))) {
                for (Direction direction : Direction.Plane.VERTICAL) {
                    final BlockPos framePos = clickedPos.relative(direction);
                    if(((MWPortalBlock) MWBlocks.ETHEREAL_PORTAL.get()).trySpawnPortal(level, clickedPos.relative(face))) {
                        if(player instanceof ServerPlayer) {
                            ItemHelper.hurt(context.getItemInHand(), player);
                        }
                        return InteractionResult.sidedSuccess(level.isClientSide());
                    }
                }
            }
        }
        return super.useOn(context);
    }

    /**
     * Make the Item glint
     *
     * @param itemStack {@link ItemStack The current Item Stack}
     * @return {@link Boolean#TRUE True}
     */
    @Override
    public boolean isFoil(@NotNull ItemStack itemStack) {
        return true;
    }

}