package org.mineworld.helper;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

/**
 * Helper methods for {@link ItemStack item stacks}
 */
public final class ItemHelper {

    /**
     * Get a default {@link ItemStack Item Stack} from a {@link RegistryObject < Block > block registry object}
     *
     * @param registryObject {@link RegistryObject<Block> The block registry object}
     * @return {@link ItemStack The default Item Stack}
     */
    public static ItemStack getDefaultStack(final RegistryObject<? extends ItemLike> registryObject) {
        return getDefaultStack(registryObject.get().asItem());
    }

    /**
     * Get a default {@link ItemStack Item Stack} from an {@link ItemLike Item Like} object
     * (namely blocks and items)
     *
     * @param object {@link ItemLike The object}
     * @return {@link ItemStack The default Item Stack}
     */
    public static ItemStack getDefaultStack(final ItemLike object) {
        return getDefaultStack(object.asItem());
    }

    /**
     * Get a default {@link ItemStack Item Stack} from an {@link Item item}
     *
     * @param item {@link Item The item}
     * @return {@link ItemStack The default Item Stack}
     */
    public static ItemStack getDefaultStack(final Item item) {
        return item.getDefaultInstance();
    }

}