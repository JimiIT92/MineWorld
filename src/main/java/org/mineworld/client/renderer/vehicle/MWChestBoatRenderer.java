package org.mineworld.client.renderer.vehicle;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.mineworld.MineWorld;
import org.mineworld.entity.vehicle.MWChestBoat;

/**
 * Renderer class for a {@link MineWorld MineWorld} {@link MWChestBoat Chest Boat}
 */
@OnlyIn(Dist.CLIENT)
public class MWChestBoatRenderer extends MWBoatRenderer {

    /**
     * Constructor. Set the renderer properties
     *
     * @param context {@link EntityRendererProvider.Context The Render Context}
     */
    public MWChestBoatRenderer(final EntityRendererProvider.Context context) {
        super(context, true);
    }

}