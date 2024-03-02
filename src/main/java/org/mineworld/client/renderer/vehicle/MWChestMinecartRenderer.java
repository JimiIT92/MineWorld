package org.mineworld.client.renderer.vehicle;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MinecartRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.mineworld.MineWorld;
import org.mineworld.entity.vehicle.MWMinecartChest;

/**
 * Renderer class for a {@link MineWorld MineWorld} {@link MWMinecartChest Chest Minecart}
 */
@OnlyIn(Dist.CLIENT)
public class MWChestMinecartRenderer extends MinecartRenderer<MWMinecartChest> {

    /**
     * Constructor. Set the renderer properties
     *
     * @param context {@link EntityRendererProvider.Context The Render Context}
     */
    public MWChestMinecartRenderer(final EntityRendererProvider.Context context) {
        super(context, ModelLayers.CHEST_MINECART);
    }

}