package org.mineworld.client.renderer.entity.boss;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineworld.MineWorld;
import org.mineworld.client.model.entity.boss.AncientGuardianModel;
import org.mineworld.entity.boss.AncientGuardianBoss;

/**
 * Renderer class for a {@link MineWorld MineWorld} {@link AncientGuardianBoss Ancient Guardian}
 */
@OnlyIn(Dist.CLIENT)
public class AncientGuardianRenderer extends MobRenderer<AncientGuardianBoss, AncientGuardianModel> {

    /**
     * Constructor. Set the renderer properties
     *
     * @param context {@link EntityRendererProvider.Context The Render Context}
     */
    public AncientGuardianRenderer(final EntityRendererProvider.Context context) {
        super(context, new AncientGuardianModel(context.bakeLayer(AncientGuardianModel.LAYER_LOCATION)), 0.5F);
        this.addLayer(new AncientGuardianEyeLayer(this));
        this.addLayer(new AncientGuardianHornsLayer(this));
        this.addLayer(new AncientGuardianArmorLayer(this, context.getModelSet()));
    }

    /**
     * Get the {@link Integer Entity Block Light level}
     *
     * @param entity The {@link LivingEntity Entity}
     * @param blockPos The {@link BlockPos current Block Pos}
     * @return {@link Integer 15}
     */
    @Override
    protected int getBlockLightLevel(final @NotNull AncientGuardianBoss entity, final @NotNull BlockPos blockPos) {
        return 15;
    }

    /**
     * Get the {@link ResourceLocation Entity texture}
     *
     * @param ancientGuardian {@link AncientGuardianBoss The Ancient Guardian}
     * @return {@link ResourceLocation The Entity Texture Location}
     */
    @Override
    public @NotNull ResourceLocation getTextureLocation(final @NotNull AncientGuardianBoss ancientGuardian) {
        return null;
    }

    /**
     * Get the {@link RenderType Entity Render Type}
     *
     * @param entity The {@link LivingEntity Entity}
     * @param bodyVisible {@link Boolean If the Entity body should be visible}
     * @param translucent {@link Boolean If the Entity is translucent}
     * @param glowing {@link Boolean If the Entity is glowing}
     * @return {@link RenderType#endGateway}
     */
    @Nullable
    @Override
    protected RenderType getRenderType(@NotNull AncientGuardianBoss entity, final boolean bodyVisible, final boolean translucent, final boolean glowing) {
        return RenderType.endGateway();
    }

    /**
     * Scale the {@link AncientGuardianModel Entity Model}
     *
     * @param entity {@link AncientGuardianBoss The Entity}
     * @param poseStack {@link PoseStack The Entity Pose Stack}
     * @param partialTicks {@link Float The Entity partial Ticks}
     */
    @Override
    protected void scale(final @NotNull AncientGuardianBoss entity, final PoseStack poseStack, final float partialTicks) {
        final float scale = 4F;
        poseStack.scale(scale, scale, scale);
    }

}