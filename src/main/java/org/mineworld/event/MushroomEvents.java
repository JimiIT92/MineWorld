package org.mineworld.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.core.MWBlocks;
import org.mineworld.helper.ItemHelper;

import java.util.Optional;

/**
 * Handle all events for a {@link MushroomBlock Mushroom}
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID)
public final class MushroomEvents {

    /**
     * Place a {@link CoralWallFanBlock Wall Mushroom Fan Block} when right clicking on a {@link Block Block}
     *
     * @param event {@link PlayerInteractEvent.RightClickBlock The Player Right Click Block Event}
     */
    @SubscribeEvent
    public static void onRightClickBlock(final PlayerInteractEvent.RightClickBlock event) {
        if(!event.isCanceled()) {
            final Player player = event.getEntity();
            final BlockPos clickedPos = event.getPos();
            final Level level = event.getLevel();
            final BlockState blockState = level.getBlockState(clickedPos);
            final ItemStack itemStack = event.getItemStack();
            Optional<Block> optionalMushroomBlock = Optional.empty();
            if(itemStack.is(Items.BROWN_MUSHROOM)) {
                optionalMushroomBlock = Optional.of(MWBlocks.BROWN_MUSHROOM_WALL_FAN.get());
            }
            else if(itemStack.is(Items.RED_MUSHROOM)) {
                optionalMushroomBlock = Optional.of(MWBlocks.RED_MUSHROOM_WALL_FAN.get());
            }
            optionalMushroomBlock.ifPresent(mushroomBlock -> {
                final Direction face = event.getFace();
                final BlockPos placePos = clickedPos.offset(face.getNormal());
                boolean isValidFace = face != Direction.DOWN && face != Direction.UP && blockState.isFaceSturdy(level, placePos, face) && level.getBlockState(placePos).canBeReplaced();
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
            });
        }
    }

}