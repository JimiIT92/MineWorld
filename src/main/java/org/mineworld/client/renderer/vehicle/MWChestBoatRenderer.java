package org.mineworld.client.renderer.vehicle;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.mineworld.entity.vehicle.MWChestBoat;

/**
 * Renderer class for a {@link MWChestBoat MineWorld chest boat}
 */
@OnlyIn(Dist.CLIENT)
public class MWChestBoatRenderer extends MWBoatRenderer {

    /**
     * Constructor. Set the {@link EntityRendererProvider.Context renderer context}
     *
     * @param context {@link EntityRendererProvider.Context The entity renderer provider context}
     */
    public MWChestBoatRenderer(EntityRendererProvider.Context context) {
        super(context, true);
    }

}