package org.mineworld.client.renderer.vehicle;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MinecartRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.mineworld.entity.vehicle.MWMinecartChest;

/**
 * Renderer class for a {@link MWMinecartChest MineWorld chest minecart}
 */
@OnlyIn(Dist.CLIENT)
public class MWChestMinecartRenderer extends MinecartRenderer<MWMinecartChest> {

    /**
     * Constructor. Set the {@link BlockRenderDispatcher block renderer reference}
     *
     * @param context {@link EntityRendererProvider.Context The entity render provider context}
     */
    public MWChestMinecartRenderer(EntityRendererProvider.Context context) {
        super(context, ModelLayers.CHEST_MINECART);
    }

}