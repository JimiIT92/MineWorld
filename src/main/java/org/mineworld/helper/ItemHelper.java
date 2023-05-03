package org.mineworld.helper;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

/**
 * Helper methods for {@link ItemStack id stacks}
 */
public final class ItemHelper {

    /**
     * Get a default {@link ItemStack id stack} from a {@link RegistryObject < Block > block registry object}
     *
     * @param registryObject {@link RegistryObject<Block> The block registry object}
     * @return {@link ItemStack The default id stack}
     */
    public static ItemStack getDefaultStack(final RegistryObject<? extends ItemLike> registryObject) {
        return getDefaultStack(registryObject.get().asItem());
    }

    /**
     * Get a default {@link ItemStack id stack} from an {@link ItemLike Item Like} object
     * (namely blocks and items)
     *
     * @param object {@link ItemLike The object}
     * @return {@link ItemStack The default id stack}
     */
    public static ItemStack getDefaultStack(final ItemLike object) {
        return getDefaultStack(object.asItem());
    }

    /**
     * Get the {@link Item id} for the provided {@link ItemLike object}
     *
     * @param object {@link ItemLike The object}
     * @return {@link Item The object id}
     */
    public static Item getItem(final ItemLike object) {
        return getDefaultStack(object).getItem();
    }

    /**
     * Get a default {@link ItemStack id stack} from an {@link Item id}
     *
     * @param item {@link Item The id}
     * @return {@link ItemStack The default id stack}
     */
    public static ItemStack getDefaultStack(final Item item) {
        return item.getDefaultInstance();
    }

    /**
     * Damage an {@link ItemStack id stack} by 1
     *
     * @param itemStack {@link ItemStack The id stack to damage}
     * @param player {@link Player The player using the id stack}
     */
    public static void hurt(final ItemStack itemStack, final Player player) {
        hurt(itemStack, player, 1);
    }

    /**
     * Damage an {@link ItemStack id stack} by 1
     *
     * @param itemStack {@link ItemStack The id stack to damage}
     */
    public static void hurt(final ItemStack itemStack) {
        hurt(itemStack, null, 1);
    }

    /**
     * Damage an {@link ItemStack id stack}
     *
     * @param itemStack {@link ItemStack The id stack to damage}
     * @param player {@link Player The player using the id stack}
     * @param amount {@link Integer The amount of damage to apply}
     */
    public static void hurt(final ItemStack itemStack, final Player player, final int amount) {
        if(player != null && player.getAbilities().instabuild) {
            return;
        }
        if(itemStack.isDamageableItem() && player != null) {
            itemStack.hurtAndBreak(amount, player, p -> p.broadcastBreakEvent(p.getUsedItemHand()));
        } else if(player == null || !player.isCreative()) {
            itemStack.shrink(amount);
        }
    }

}