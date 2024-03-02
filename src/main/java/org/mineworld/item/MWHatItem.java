package org.mineworld.item;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.mineworld.MineWorld;

import java.util.function.Consumer;

/**
 * {@link MineWorld MineWorld} {@link ArmorItem Hat Item}
 */
public abstract class MWHatItem extends ArmorItem {

    /**
     * Constructor. Set the {@link Properties Item Properties}
     *
     * @param material {@link ArmorMaterial The Armor Material}
     * @param properties {@link Item.Properties The Item Properties}
     */
    public MWHatItem(final ArmorMaterial material, final Item.Properties properties) {
        super(material, Type.HELMET, properties);
    }

    /**
     * Assign the special rendering to this {@link Item Item}
     *
     * @param consumer {@link Consumer<IClientItemExtensions> The Client Item Rendered Consumer}
     */
    @Override
    public void initializeClient(final Consumer<IClientItemExtensions> consumer) {
        consumer.accept(getArmorRenderer());
    }

    /**
     * Get the {@link IClientItemExtensions Armor Model Renderer}
     *
     * @return {@link IClientItemExtensions The Armor Model Renderer}
     */
    public abstract IClientItemExtensions getArmorRenderer();

}