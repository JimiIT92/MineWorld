package org.mineworld.client.renderer.vehicle;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Axis;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;
import org.mineworld.MineWorld;
import org.mineworld.entity.vehicle.MWBoat;
import org.mineworld.helper.TextureHelper;

import java.util.Map;
import java.util.stream.Stream;

/**
 * Renderer class for a {@link MineWorld MineWorld} {@link MWBoat Boat}
 */
@OnlyIn(Dist.CLIENT)
public class MWBoatRenderer extends EntityRenderer<MWBoat> {

    /**
     * {@link Map The Cached Boat Resources}
     */
    private final Map<MWBoat.Type, Pair<ResourceLocation, ListModel<Boat>>> boatResources;

    /**
     * Constructor. Set the renderer properties
     *
     * @param context {@link EntityRendererProvider.Context The Render Context}
     */
    public MWBoatRenderer(final EntityRendererProvider.Context context) {
        this(context, false);
    }

    /**
     * Constructor. Set the renderer properties
     *
     * @param context {@link EntityRendererProvider.Context The Render Context}
     * @param hasChest {@link Boolean If the Boat is a Chest Boat}
     */
    public MWBoatRenderer(final EntityRendererProvider.Context context, final boolean hasChest) {
        super(context);
        this.shadowRadius = 0.8F;
        this.boatResources = Stream.of(MWBoat.Type.values()).collect(ImmutableMap.toImmutableMap(
                type -> type,
                type -> Pair.of(TextureHelper.entity((hasChest ? "chest_" : "") + "boat/" + type.getName()), this.createBoatModel(context, type, hasChest)))
        );
    }

    /**
     * Create some {@link Boat Boat Models} based on the {@link MWBoat.Type Boat Type}
     *
     * @param context {@link EntityRendererProvider.Context The Render Context}
     * @param type {@link MWBoat.Type The Boat Type}
     * @param hasChest {@link Boolean If the Boat is a Chest Boat}
     * @return {@link ListModel<Boat> The Boat Models}
     */
    private ListModel<Boat> createBoatModel(final EntityRendererProvider.Context context, final MWBoat.Type type, final boolean hasChest) {
        final boolean isRaft = type.equals(MWBoat.Type.PALM);
        final Boat.Type modelBoatType = isRaft ? Boat.Type.BAMBOO : Boat.Type.OAK;
        final ModelPart modelpart = context.bakeLayer(hasChest ? ModelLayers.createChestBoatModelName(modelBoatType) : ModelLayers.createBoatModelName(modelBoatType));
        return isRaft ? (hasChest ? new ChestRaftModel(modelpart) : new RaftModel(modelpart)) :
                hasChest ? new ChestBoatModel(modelpart) : new BoatModel(modelpart);
    }

    /**
     * Render the {@link MWBoat Boat}
     *
     * @param boat {@link MWBoat The Boat}
     * @param yaw {@link Float The Entity yaw}
     * @param partialTicks {@link Float The Entity partial ticks}
     * @param poseStack {@link PoseStack The Entity pose}
     * @param buffer {@link MultiBufferSource The Entity buffer}
     * @param packedLight {@link Integer The level packed light}
     */
    @Override
    public void render(final MWBoat boat, final float yaw, final float partialTicks, final PoseStack poseStack, final @NotNull MultiBufferSource buffer, final int packedLight) {
        poseStack.pushPose();
        poseStack.translate(0.0F, 0.375F, 0.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - yaw));
        float boatTicks = (float)boat.getHurtTime() - partialTicks;
        if (boatTicks > 0.0F) {
            poseStack.mulPose(Axis.XP.rotationDegrees(Mth.sin(boatTicks) * boatTicks * Math.max(0.0F, boat.getDamage() - partialTicks) / 10.0F * (float)boat.getHurtDir()));
        }
        if (!Mth.equal( boat.getBubbleAngle(partialTicks), 0.0F)) {
            poseStack.mulPose((new Quaternionf()).setAngleAxis(boat.getBubbleAngle(partialTicks) * ((float)Math.PI / 180F), 1.0F, 0.0F, 1.0F));
        }
        final Pair<ResourceLocation, ListModel<Boat>> pair = getModelWithLocation(boat);
        final ListModel<Boat> model = pair.getSecond();
        poseStack.scale(-1.0F, -1.0F, 1.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
        model.setupAnim(boat, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
        model.renderToBuffer(poseStack, buffer.getBuffer(model.renderType(pair.getFirst())), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        if (!boat.isUnderWater() && model instanceof WaterPatchModel waterpatchmodel) {
            waterpatchmodel.waterPatch().render(poseStack, buffer.getBuffer(RenderType.waterMask()), packedLight, OverlayTexture.NO_OVERLAY);
        }
        poseStack.popPose();
        super.render(boat, yaw, partialTicks, poseStack, buffer, packedLight);
    }

    /**
     * Get the {@link ResourceLocation Entity texture}
     *
     * @param boat {@link MWBoat The Boat}
     * @return {@link ResourceLocation The Entity Texture Location}
     */
    @Override
    public @NotNull ResourceLocation getTextureLocation(final @NotNull MWBoat boat) {
        return getModelWithLocation(boat).getFirst();
    }

    /**
     * Get the {@link Pair Boat Model and Texture Location}
     *
     * @param boat {@link MWBoat The Boat}
     * @return {@link Pair The Boat Model and Texture Location}
     */
    public Pair<ResourceLocation, ListModel<Boat>> getModelWithLocation(final MWBoat boat) { return this.boatResources.get(boat.getBoatType()); }

}