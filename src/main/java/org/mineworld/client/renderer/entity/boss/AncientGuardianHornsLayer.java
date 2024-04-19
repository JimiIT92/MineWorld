package org.mineworld.client.renderer.entity.boss;


import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.mineworld.client.model.entity.boss.AncientGuardianModel;
import org.mineworld.entity.boss.AncientGuardianBoss;
import org.mineworld.helper.TextureHelper;

/**
 * Render layer for the {@link AncientGuardianBoss Ancient Guardian} horns
 */
@OnlyIn(Dist.CLIENT)
public class AncientGuardianHornsLayer extends EyesLayer<AncientGuardianBoss, AncientGuardianModel> {

    /**
     * {@link RenderType Ancient Guardian Horns Render Type}
     */
    private static final RenderType ANCIENT_GUARDIAN_HORNS = RenderType.entityCutout(TextureHelper.entity("ancient_guardian/ancient_guardian_horns"));

    /**
     * Layer constructor
     *
     * @param parent {@link RenderLayerParent The parent Renderer}
     */
    public AncientGuardianHornsLayer(final RenderLayerParent<AncientGuardianBoss, AncientGuardianModel> parent) {
        super(parent);
    }

    /**
     * Get the {@link RenderType Render Type}
     *
     * @return {@link #ANCIENT_GUARDIAN_HORNS The Ancient Guardian Horns Render Type}
     */
    public @NotNull RenderType renderType() {
        return ANCIENT_GUARDIAN_HORNS;
    }

}