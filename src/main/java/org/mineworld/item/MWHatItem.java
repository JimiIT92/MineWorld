package org.mineworld.item;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.mineworld.MineWorld;

import java.util.function.Consumer;

/**
 * Base class for a {@link MineWorld MineWorld} hat item
 */
public abstract class MWHatItem extends ArmorItem {

    /**
     * Constructor. Set the {@link ArmorMaterial hat material}
     *
     * @param material {@link ArmorMaterial The hat material}
     * @param properties {@link Item.Properties The item properties}
     */
    public MWHatItem(final ArmorMaterial material, final Item.Properties properties) {
        super(material, Type.HELMET, properties);
    }

    /**
     * Initialize the client renderer
     *
     * @param consumer {@link Consumer<IClientItemExtensions> The client consumer}
     */
    @Override
    public void initializeClient(final Consumer<IClientItemExtensions> consumer) {
        consumer.accept(getArmorRenderer());
    }

    /**
     * Get the {@link IClientItemExtensions client armor renderer}
     *
     * @return The {@link IClientItemExtensions client armor renderer}
     */
    public abstract IClientItemExtensions getArmorRenderer();

}