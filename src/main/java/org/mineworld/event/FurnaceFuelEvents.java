package org.mineworld.event;

import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mineworld.MineWorld;
import org.mineworld.block.RopeBlock;
import org.mineworld.core.MWBlocks;
import org.mineworld.core.MWTags;

/**
 * Handle all events for the {@link AbstractFurnaceBlock Furnace Block} fuel
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID)
public final class FurnaceFuelEvents {

    /**
     * Make some {@link Item Items} usable as fuel by the {@link AbstractFurnaceBlock Furnace Block}
     *
     * @param event {@link FurnaceFuelBurnTimeEvent The Furnace Fuel Burn Time Event}
     */
    @SubscribeEvent
    public static void onFurnaceFuelBurn(final FurnaceFuelBurnTimeEvent event) {
        final ItemStack itemStack = event.getItemStack();
        if(!event.isCanceled() && !itemStack.is(ItemTags.NON_FLAMMABLE_WOOD)) {
            final Item item = itemStack.getItem();
            int burnTime = event.getBurnTime();
            if(itemStack.is(Items.LEAD)) {
                burnTime = 100;
            }
            else if(item instanceof BlockItem blockItem) {
                final Block block = blockItem.getBlock();
                if(block instanceof LecternBlock || block instanceof ChestBlock || block instanceof BarrelBlock
                        || block instanceof ChiseledBookShelfBlock || itemStack.is(MWTags.Items.BOOKSHELVES)
                        || block.equals(MWBlocks.MANGROVE_ROOTS_CARPET.get())) {
                    burnTime = 300;
                }
                else if(block instanceof RopeBlock || block instanceof AzaleaBlock) {
                    burnTime = 100;
                }
            }
            event.setBurnTime(burnTime);
        }
    }

}