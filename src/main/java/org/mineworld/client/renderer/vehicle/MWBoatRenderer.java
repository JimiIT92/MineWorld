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
import org.mineworld.entity.vehicle.MWBoat;
import org.mineworld.helper.KeyHelper;

import java.util.Map;
import java.util.stream.Stream;

/**
 * Renderer class for a {@link MWBoat MineWorld boat}
 */
@OnlyIn(Dist.CLIENT)
public class MWBoatRenderer extends EntityRenderer<MWBoat> {

    /**
     * {@link Map Cached boat resources}
     */
    private Map<MWBoat.Type, Pair<ResourceLocation, ListModel<Boat>>> boatResources;

    /**
     * Constructor. Set the {@link EntityRendererProvider.Context renderer context}
     *
     * @param context {@link EntityRendererProvider.Context The entity renderer provider context}
     */
    public MWBoatRenderer(EntityRendererProvider.Context context) {
        this(context, false);
    }

    /**
     * Constructor. Set the {@link EntityRendererProvider.Context renderer context}
     * and the {@link Map boat resources}
     *
     * @param context {@link EntityRendererProvider.Context The entity renderer provider context}
     * @param hasChest {@link Boolean If the boat has a chest}
     */
    public MWBoatRenderer(EntityRendererProvider.Context context, boolean hasChest) {
        super(context);
        this.shadowRadius = 0.8F;
        this.boatResources = Stream.of(MWBoat.Type.values()).collect(ImmutableMap.toImmutableMap(
                type -> type,
                type -> Pair.of(KeyHelper.entityTexture((hasChest ? "chest_" : "") + "boat/" + type.getName()), this.createBoatModel(context, type, hasChest)))
        );
    }

    /**
     * Create some {@link Boat boat model} based on the {@link MWBoat.Type boat type}
     *
     * @param context {@link EntityRendererProvider.Context The entity renderer provider context}
     * @param type {@link MWBoat.Type The boat type}
     * @param hasChest {@link Boolean If the boat has a chest}
     * @return {@link ListModel<Boat> The boat models}
     */
    private ListModel<Boat> createBoatModel(EntityRendererProvider.Context context, MWBoat.Type type, boolean hasChest) {
        boolean isRaft = type.equals(MWBoat.Type.PALM);
        Boat.Type modelBoatType = isRaft ? Boat.Type.BAMBOO : Boat.Type.OAK;
        ModelPart modelpart = context.bakeLayer(hasChest ? ModelLayers.createChestBoatModelName(modelBoatType) : ModelLayers.createBoatModelName(modelBoatType));
        return isRaft ? (hasChest ? new ChestRaftModel(modelpart) : new RaftModel(modelpart)) :
                hasChest ? new ChestBoatModel(modelpart) : new BoatModel(modelpart);
    }

    /**
     * Render the boat
     *
     * @param boat {@link MWBoat The boat to render}
     * @param partialYaw {@link Float The boat yaw value}
     * @param partialTicks {@link Float The boat partial ticks}
     * @param posStack {@link PoseStack The boat pose stack}
     * @param multiBufferSource {@link MultiBufferSource The multi buffer renderer source}
     * @param packedLight {@link Integer The client packed light}
     */
    @Override
    public void render(MWBoat boat, float partialYaw, float partialTicks, PoseStack posStack, @NotNull MultiBufferSource multiBufferSource, int packedLight) {
        posStack.pushPose();
        posStack.translate(0.0F, 0.375F, 0.0F);
        posStack.mulPose(Axis.YP.rotationDegrees(180.0F - partialYaw));
        float boatTicks = (float)boat.getHurtTime() - partialTicks;
        if (boatTicks > 0.0F) {
            posStack.mulPose(Axis.XP.rotationDegrees(Mth.sin(boatTicks) * boatTicks * Math.max(0.0F, boat.getDamage() - partialTicks) / 10.0F * (float)boat.getHurtDir()));
        }
        if (!Mth.equal( boat.getBubbleAngle(partialTicks), 0.0F)) {
            posStack.mulPose((new Quaternionf()).setAngleAxis(boat.getBubbleAngle(partialTicks) * ((float)Math.PI / 180F), 1.0F, 0.0F, 1.0F));
        }
        Pair<ResourceLocation, ListModel<Boat>> pair = getModelWithLocation(boat);
        ListModel<Boat> model = pair.getSecond();
        posStack.scale(-1.0F, -1.0F, 1.0F);
        posStack.mulPose(Axis.YP.rotationDegrees(90.0F));
        model.setupAnim(boat, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
        model.renderToBuffer(posStack, multiBufferSource.getBuffer(model.renderType(pair.getFirst())), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        if (!boat.isUnderWater() && model instanceof WaterPatchModel waterpatchmodel) {
            waterpatchmodel.waterPatch().render(posStack, multiBufferSource.getBuffer(RenderType.waterMask()), packedLight, OverlayTexture.NO_OVERLAY);
        }
        posStack.popPose();
        super.render(boat, partialYaw, partialTicks, posStack, multiBufferSource, packedLight);
    }

    /**
     * Get the {@link ResourceLocation boat resource location}
     *
     * @param boat {@link MWBoat The boat entity}
     * @return {@link ResourceLocation The boat resource location}
     */
    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull MWBoat boat) {
        return getModelWithLocation(boat).getFirst();
    }

    /**
     * Get the {@link Pair bot model and texture location}
     *
     * @param boat {@link MWBoat The boat entity}
     * @return {@link Pair The boat model and texture location}
     */
    public Pair<ResourceLocation, ListModel<Boat>> getModelWithLocation(MWBoat boat) { return this.boatResources.get(boat.getBoatType()); }

}