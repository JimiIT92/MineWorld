package org.mineworld.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.entity.AncientGuardian;

/**
 * Model class for a {@link AncientGuardian Ancient Guardian} entity
 */
@OnlyIn(Dist.CLIENT)
public class AncientGuardianModel extends HierarchicalModel<AncientGuardian> {

    /**
     * The {@link ModelLayerLocation Model Layer Location}
     */
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MineWorld.MOD_ID, "ancient_guardian"), "main");

    /**
     * The {@link ModelPart root model part}
     */
    private final ModelPart root;

    /**
     * Constructor. Set the entity {@link ModelPart model parts}
     *
     * @param root {@link ModelPart The root model part}
     */
    public AncientGuardianModel(final ModelPart root) {
        this.root = root.getChild("root");
    }

    /**
     * Create the entity body layer
     *
     * @return {@link LayerDefinition The entity layer definition}
     */
    public static LayerDefinition createBodyLayer() {
        final MeshDefinition meshDefinition = new MeshDefinition();
        final PartDefinition root = meshDefinition.getRoot().addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 32)
                .addBox(-8.0F, -26.0F, -5.0F, 16.0F, 16.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -34.0F, -1.0F));
        PartDefinition torso = root.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 0)
                .addBox(-9.0F, -23.0F, -4.0F, 18.0F, 21.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -21.0F, -1.0F));
        PartDefinition leftArm = torso.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 58)
                .addBox(-4.0F, -10.0F, -4.0F, 8.0F, 28.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(13.0F, -13.0F, 1.0F));
        PartDefinition rightArm = torso.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(44, 50)
                .addBox(-4.0F, -10.0F, -4.0F, 8.0F, 28.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-13.0F, -13.0F, 1.0F));
        PartDefinition leftLeg = torso.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(76, 76)
                .addBox(-2.9F, -10.0F, -3.0F, 6.0F, 23.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 8.0F, 0.0F));
        PartDefinition rightLeg = torso.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(76, 47)
                .addBox(-3.1F, -10.0F, -3.0F, 6.0F, 23.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 8.0F, 0.0F));
        PartDefinition leftWing = torso.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(0, 96)
                .addBox(-32.0F, -53.0F, 6.0F, 32.0F, 16.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 1.0F));
        PartDefinition rightWing = torso.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(0, 112)
                .addBox(0.0F, -53.0F, 6.0F, 32.0F, 16.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 1.0F));

        return LayerDefinition.create(meshDefinition, 128, 128);
    }

    /**
     * Setup the {@link AncientGuardian Ancient Guardian} animations
     *
     * @param ancientGuardian {@link AncientGuardian The Ancient Guardian entity}
     * @param limbSwing {@link Float The entity limb swing angle}
     * @param limbSwingAmount {@link Float The entity limb swing amount}
     * @param ageInTicks {@link Float The entity age in ticks}
     * @param netHeadYaw {@link Float The entity head yaw value}
     * @param headPitch {@link Float The entity head pitch value}
     */
    @Override
    public void setupAnim(final @NotNull AncientGuardian ancientGuardian, final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHeadYaw, final float headPitch) {

    }

    /**
     * Render the model
     *
     * @param poseStack {@link PoseStack The entity Pose Stack}
     * @param vertexConsumer {@link VertexConsumer The vertex consumer reference}
     * @param packedLight {@link Integer The client packed light}
     * @param packedOverlay {@link Integer The client packed overlay value}
     * @param red {@link Float The rendering red value}
     * @param green {@link Float The rendering green value}
     * @param blue {@link Float The rendering blue value}
     * @param alpha {@link Float The rendering alpha value}
     */
    @Override
    public void renderToBuffer(final @NotNull PoseStack poseStack, final @NotNull VertexConsumer vertexConsumer, final int packedLight, final int packedOverlay, final float red, final float green, final float blue, final float alpha) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    /**
     * Get the {@link ModelPart root Model Part}
     *
     * @return The {@link #root root Model Part}
     */
    @Override
    public @NotNull ModelPart root() {
        return this.root;
    }

    /**
     * Get the {@link AnimationDefinition entity idle animation}
     *
     * @return {@link AnimationDefinition The entity idle animation}
     */
    private AnimationDefinition getIdleAnimation() {
        return AnimationDefinition.Builder.withLength(2f).looping()
                .addAnimation("left_arm",
                        new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                new Keyframe(0f, KeyframeAnimations.degreeVec(-5f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(1f, KeyframeAnimations.degreeVec(5f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(2f, KeyframeAnimations.degreeVec(-5f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR)))
                .addAnimation("right_arm",
                        new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                new Keyframe(0f, KeyframeAnimations.degreeVec(5f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(1f, KeyframeAnimations.degreeVec(-5f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(2f, KeyframeAnimations.degreeVec(5f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR)))
                .addAnimation("left_wing",
                        new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 45f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(2f, KeyframeAnimations.degreeVec(0f, 45f, 0f),
                                        AnimationChannel.Interpolations.LINEAR)))
                .addAnimation("right_wing",
                        new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                new Keyframe(0f, KeyframeAnimations.degreeVec(0f, -45f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(2f, KeyframeAnimations.degreeVec(0f, -45f, 0f),
                                        AnimationChannel.Interpolations.LINEAR)))
                .build();
    }

    /**
     * Get the {@link AnimationDefinition entity walk animation}
     *
     * @return {@link AnimationDefinition The entity walk animation}
     */
    private AnimationDefinition getWalkAnimation() {
        return AnimationDefinition.Builder.withLength(2f).looping()
                .addAnimation("head",
                        new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                new Keyframe(0f, KeyframeAnimations.degreeVec(5f, 5f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(1f, KeyframeAnimations.degreeVec(-5f, -5f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(2f, KeyframeAnimations.degreeVec(5f, 5f, 0f),
                                        AnimationChannel.Interpolations.LINEAR)))
                .addAnimation("left_arm",
                        new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                new Keyframe(0f, KeyframeAnimations.degreeVec(-15f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(1f, KeyframeAnimations.degreeVec(15f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(2f, KeyframeAnimations.degreeVec(-15f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR)))
                .addAnimation("right_arm",
                        new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                new Keyframe(0f, KeyframeAnimations.degreeVec(15f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(1f, KeyframeAnimations.degreeVec(-15f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(2f, KeyframeAnimations.degreeVec(15f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR)))
                .addAnimation("left_leg",
                        new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                new Keyframe(0f, KeyframeAnimations.degreeVec(10f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(1f, KeyframeAnimations.degreeVec(-10f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(2f, KeyframeAnimations.degreeVec(10f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR)))
                .addAnimation("right_leg",
                        new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                new Keyframe(0f, KeyframeAnimations.degreeVec(-10f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(1f, KeyframeAnimations.degreeVec(10f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(2f, KeyframeAnimations.degreeVec(-10f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR)))
                .addAnimation("left_wing",
                        new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 45f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(2f, KeyframeAnimations.degreeVec(0f, 45f, 0f),
                                        AnimationChannel.Interpolations.LINEAR)))
                .addAnimation("right_wing",
                        new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                new Keyframe(0f, KeyframeAnimations.degreeVec(0f, -45f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(2f, KeyframeAnimations.degreeVec(0f, -45f, 0f),
                                        AnimationChannel.Interpolations.LINEAR)))
                .build();
    }

    /**
     * Get the {@link AnimationDefinition entity summon attack animation}
     *
     * @return {@link AnimationDefinition The entity summon attack animation}
     */
    private AnimationDefinition getSummonAttackAnimation() {
        return AnimationDefinition.Builder.withLength(2f)
                .addAnimation("head",
                        new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                new Keyframe(0f, KeyframeAnimations.degreeVec(-30f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR)))
                .addAnimation("left_arm",
                        new AnimationChannel(AnimationChannel.Targets.POSITION,
                                new Keyframe(0f, KeyframeAnimations.posVec(0f, 9f, 0f),
                                        AnimationChannel.Interpolations.LINEAR)))
                .addAnimation("left_arm",
                        new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                new Keyframe(0f, KeyframeAnimations.degreeVec(5f, 0f, -135f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(0.5f, KeyframeAnimations.degreeVec(-5f, 0f, -135f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(1f, KeyframeAnimations.degreeVec(5f, 0f, -135f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(1.5f, KeyframeAnimations.degreeVec(-5f, 0f, -135f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(2f, KeyframeAnimations.degreeVec(5f, 0f, -135f),
                                        AnimationChannel.Interpolations.LINEAR)))
                .addAnimation("right_arm",
                        new AnimationChannel(AnimationChannel.Targets.POSITION,
                                new Keyframe(0f, KeyframeAnimations.posVec(0f, 9f, 0f),
                                        AnimationChannel.Interpolations.LINEAR)))
                .addAnimation("right_arm",
                        new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                new Keyframe(0f, KeyframeAnimations.degreeVec(5f, 0f, 135f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(0.5f, KeyframeAnimations.degreeVec(-5f, 0f, 135f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(1f, KeyframeAnimations.degreeVec(5f, 0f, 135f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(1.5f, KeyframeAnimations.degreeVec(-5F, 0f, 135f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(2f, KeyframeAnimations.degreeVec(5f, 0f, 135f),
                                        AnimationChannel.Interpolations.LINEAR)))
                .build();
    }

    /**
     * Get the {@link AnimationDefinition entity flying attack animation}
     *
     * @return {@link AnimationDefinition The entity flying attack animation}
     */
    private AnimationDefinition getFlyingAttackAnimation() {
        return AnimationDefinition.Builder.withLength(4f)
                .addAnimation("head",
                        new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                new Keyframe(0f, KeyframeAnimations.degreeVec(-5f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(1f, KeyframeAnimations.degreeVec(-15f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(3.5f, KeyframeAnimations.degreeVec(-15f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(4f, KeyframeAnimations.degreeVec(-5f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR)))
                .addAnimation("left_arm",
                        new AnimationChannel(AnimationChannel.Targets.POSITION,
                                new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(1f, KeyframeAnimations.posVec(0f, 9f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(3.5f, KeyframeAnimations.posVec(0f, 9f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(4f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR)))
                .addAnimation("left_arm",
                        new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                new Keyframe(0f, KeyframeAnimations.degreeVec(15f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, -135f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(3.5f, KeyframeAnimations.degreeVec(0f, 0f, -135f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(4f, KeyframeAnimations.degreeVec(-15f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR)))
                .addAnimation("right_arm",
                        new AnimationChannel(AnimationChannel.Targets.POSITION,
                                new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(1f, KeyframeAnimations.posVec(0f, 9f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(3.5f, KeyframeAnimations.posVec(0f, 9f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(4f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR)))
                .addAnimation("right_arm",
                        new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                new Keyframe(0f, KeyframeAnimations.degreeVec(15f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 135f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(3.5f, KeyframeAnimations.degreeVec(0f, 0f, 135f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(4f, KeyframeAnimations.degreeVec(15f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR)))
                .addAnimation("left_leg",
                        new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                new Keyframe(0f, KeyframeAnimations.degreeVec(10f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR)))
                .addAnimation("right_leg",
                        new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                new Keyframe(0f, KeyframeAnimations.degreeVec(10f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR)))
                .addAnimation("left_wing",
                        new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 15f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 45f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(2f, KeyframeAnimations.degreeVec(0f, 15f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(2.5f, KeyframeAnimations.degreeVec(0f, 30f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(3f, KeyframeAnimations.degreeVec(0f, 15f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(3.5f, KeyframeAnimations.degreeVec(0f, 30f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(4f, KeyframeAnimations.degreeVec(0f, 15f, 0f),
                                        AnimationChannel.Interpolations.LINEAR)))
                .addAnimation("right_wing",
                        new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                new Keyframe(0f, KeyframeAnimations.degreeVec(0f, -15f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(1f, KeyframeAnimations.degreeVec(0f, -45f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(2f, KeyframeAnimations.degreeVec(0f, -15f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(2.5f, KeyframeAnimations.degreeVec(0f, -30f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(3f, KeyframeAnimations.degreeVec(0f, -15f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(3.5f, KeyframeAnimations.degreeVec(0f, -30f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(4f, KeyframeAnimations.degreeVec(0f, -15f, 0f),
                                        AnimationChannel.Interpolations.LINEAR)))
                .addAnimation("root",
                        new AnimationChannel(AnimationChannel.Targets.POSITION,
                                new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(0.5f, KeyframeAnimations.posVec(0f, 5f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(1f, KeyframeAnimations.posVec(0f, 10f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(1.5f, KeyframeAnimations.posVec(0f, 12f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(2f, KeyframeAnimations.posVec(0f, 10f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(2.5f, KeyframeAnimations.posVec(0f, 12f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(3f, KeyframeAnimations.posVec(0f, 10f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(3.5f, KeyframeAnimations.posVec(0f, 12f, 0f),
                                        AnimationChannel.Interpolations.LINEAR),
                                new Keyframe(4f, KeyframeAnimations.posVec(0f, 0f, 0f),
                                        AnimationChannel.Interpolations.LINEAR)))
                .build();
    }

}