package org.mineworld.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.mineworld.client.model.AncientGuardianModel;
import org.mineworld.entity.AncientGuardian;
import org.mineworld.helper.KeyHelper;

/**
 * Renderer class for a {@link AncientGuardian Ancient Guardian entity}
 */
@OnlyIn(Dist.CLIENT)
public class AncientGuardianRenderer extends MobRenderer<AncientGuardian, AncientGuardianModel> {

    /**
     * Constructor. Set the renderer context
     *
     * @param context {@link EntityRendererProvider.Context The renderer context}
     */
    public AncientGuardianRenderer(final EntityRendererProvider.Context context) {
        super(context, new AncientGuardianModel(context.bakeLayer(AncientGuardianModel.LAYER_LOCATION)), 0F);
    }

    /**
     * Get the {@link ResourceLocation entity texture location}
     *
     * @param ancientGuardian {@link AncientGuardian The Ancient Guardian entity}
     * @return {@link ResourceLocation The entity texture location}
     */
    @Override
    public @NotNull ResourceLocation getTextureLocation(final @NotNull AncientGuardian ancientGuardian) {
        final String texture = ancientGuardian.isAttacking() ? "ancient_guardian_attacking" : "ancient_guardian";
        return KeyHelper.entityTexture("ancient_guardian/" + texture);
    }

}