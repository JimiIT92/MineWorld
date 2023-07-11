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
 * Handle events for handling the furnace fuel
 */
@Mod.EventBusSubscriber(modid = MineWorld.MOD_ID)
public class FurnaceFuelEventListener {

    /**
     * Makes some items burnable by the furnace
     *
     * @param event {@link FurnaceFuelBurnTimeEvent The furnace fuel burn time event}
     */
    @SubscribeEvent
    public static void onFurnaceFuel(FurnaceFuelBurnTimeEvent event) {
        if(!event.isCanceled()) {
            ItemStack itemStack = event.getItemStack();
            if(!itemStack.is(ItemTags.NON_FLAMMABLE_WOOD)) {
                Item item = itemStack.getItem();
                if(itemStack.is(Items.LEAD)) {
                    event.setBurnTime(100);
                    return;
                }
                if(item instanceof BlockItem blockItem) {
                    Block block = blockItem.getBlock();
                    if(block instanceof LecternBlock || block instanceof ChestBlock || block instanceof BarrelBlock
                        || block instanceof ChiseledBookShelfBlock || itemStack.is(MWTags.Items.BOOKSHELVES)
                        || block.equals(MWBlocks.MANGROVE_ROOTS_CARPET.get())) {
                        event.setBurnTime(300);
                        return;
                    }
                    if(block instanceof RopeBlock || block instanceof AzaleaBlock) {
                        event.setBurnTime(100);
                    }
                }
            }
        }
    }

}