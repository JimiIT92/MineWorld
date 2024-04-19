package org.mineworld.client.renderer.entity.boss;


import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EnergySwirlLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.mineworld.client.model.entity.boss.AncientGuardianModel;
import org.mineworld.entity.boss.AncientGuardianBoss;
import org.mineworld.helper.TextureHelper;

/**
 * Render layer for the {@link AncientGuardianBoss Ancient Guardian} armor
 */
@OnlyIn(Dist.CLIENT)
public class AncientGuardianArmorLayer extends EnergySwirlLayer<AncientGuardianBoss, AncientGuardianModel> {

    /**
     * The {@link AncientGuardianModel Entity model reference}
     */
    private final AncientGuardianModel model;

    /**
     * Layer constructor
     *
     * @param parent {@link RenderLayerParent The parent Renderer}
     */
    public AncientGuardianArmorLayer(final RenderLayerParent<AncientGuardianBoss, AncientGuardianModel> parent, final EntityModelSet modelSet) {
        super(parent);
        this.model = new AncientGuardianModel(modelSet.bakeLayer(AncientGuardianModel.ARMOR_LAYER_LOCATION));
    }

    /**
     * Get the {@link ResourceLocation layer texture location}
     *
     * @return {@link ResourceLocation The layer texture location}
     */
    protected @NotNull ResourceLocation getTextureLocation() {
        return TextureHelper.entity("ancient_guardian/ancient_guardian_armor");
    }

    /**
     * Get the {@link Float armor X offset}
     *
     * @param ticks The {@link Float entity ticks}
     * @return The {@link Float armor X offset}
     */
    protected float xOffset(float ticks) {
        return Mth.cos(ticks * 0.02F) * 3.0F;
    }

    /**
     * Get the {@link AncientGuardianModel Entity model}
     *
     * @return {@link AncientGuardianModel The Entity model}
     */
    protected @NotNull AncientGuardianModel model() {
        return this.model;
    }

}