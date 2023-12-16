package org.mineworld.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import org.jetbrains.annotations.NotNull;
import org.mineworld.MineWorld;
import org.mineworld.core.MWAnimations;
import org.mineworld.entity.Reaper;

/**
 * Model class for a {@link Reaper Shadow entity}
 */
public class ReaperModel extends HierarchicalModel<Reaper> implements ArmedModel {

	/**
	 * The {@link ModelLayerLocation Model Layer Location}
	 */
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MineWorld.MOD_ID, "reaper"), "main");
	/**
	 * The {@link ModelPart root entity model part}
	 */
	private final ModelPart root;
	/**
	 * The {@link ModelPart body entity model part}
	 */
	private final ModelPart body;
	/**
	 * The {@link ModelPart head entity model part}
	 */
	private final ModelPart head;
	/**
	 * The {@link ModelPart left arm entity model part}
	 */
	private final ModelPart leftArm;
	/**
	 * The {@link ModelPart right arm entity model part}
	 */
	private final ModelPart rightArm;

	/**
	 * Constructor. Set the {@link ModelPart root entity model part}
	 *
	 * @param root {@link ModelPart The root model part}
	 */
	public ReaperModel(final ModelPart root) {
		this.root = root.getChild("reaper");
		this.head = this.root.getChild("head");
		this.body = this.root.getChild("body");
		this.leftArm = this.body.getChild("left_arm");
		this.rightArm = this.body.getChild("right_arm");
	}

	/**
	 * Create the {@link LayerDefinition entity model layer definition}
	 *
	 * @return {@link LayerDefinition The entity model layer definition}
	 */
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition reaperRoot = partdefinition.addOrReplaceChild("reaper", CubeListBuilder.create(),
				PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition head = reaperRoot.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 24)
				.addBox(-2.0F, -26.75F, -4.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition body = reaperRoot.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 0)
				.addBox(-3.0F, -20.75F, -5.0F, 8.0F, 16.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 0)
				.addBox(-5.0F, -20.75F, -2.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 0)
				.addBox(5.0F, -20.75F, -2.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	/**
	 * Setup the entity animations
	 *
	 * @param entity {@link Reaper The reaper entity}
	 * @param limbSwing {@link Float The limb swing angle}
	 * @param limbSwingAmount {@link Float The limb swing amount}
	 * @param ageInTicks {@link Float The entity age in ticks}
	 * @param netHeadYaw {@link Float The entity net head yaw value}
	 * @param headPitch {@link Float The entity head pitch value}
	 */
	@Override
	public void setupAnim(final @NotNull Reaper entity, final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHeadYaw, final float headPitch) {
		this.root.getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch);

		this.animateWalk(MWAnimations.REAPER_IDLE, limbSwing, limbSwingAmount, 1F, 2F);
		this.animate(entity.idleAnimationState, MWAnimations.REAPER_IDLE, ageInTicks, 1F);
		this.animate(entity.chargeAnimationState, MWAnimations.REAPER_CHARGE, ageInTicks, 1F);
	}

	/**
	 * Rotate the head towards the player when looking at it
	 *
	 * @param netHeadYaw {@link Float The entity net head yaw value}
	 * @param headPitch {@link Float The entity head pitch value}
	 */
	private void applyHeadRotation(float netHeadYaw, float headPitch) {
		netHeadYaw = Mth.clamp(netHeadYaw, -30F, 30F);
		headPitch = Mth.clamp(headPitch, -25F, 45F);
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = headPitch * ((float)Math.PI / 180F);
	}

	/**
	 * Render the entity model
	 *
	 * @param poseStack {@link PoseStack The entity pose stack}
	 * @param vertexConsumer {@link VertexConsumer The vertex consumer reference}
	 * @param packedLight {@link Integer The client light value}
	 * @param packedOverlay {@link Integer The client light overlay value}
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
	 * Get the {@link ModelPart entity model root part}
	 *
	 * @return {@link #root The entity model root part}
	 */
	@Override
	public @NotNull ModelPart root() {
		return root;
	}

	/**
	 * Render the equipped item
	 *
	 * @param arm {@link HumanoidArm The hand with the item}
	 * @param poseStack {@link PoseStack The entity pose stack}
	 */
	@Override
	public void translateToHand(final @NotNull HumanoidArm arm, final @NotNull PoseStack poseStack) {
		final boolean useRightArm = arm == HumanoidArm.RIGHT;
		final ModelPart armPart = useRightArm ? this.rightArm : this.leftArm;
		this.root.translateAndRotate(poseStack);
		this.body.translateAndRotate(poseStack);
		armPart.translateAndRotate(poseStack);
		poseStack.translate(0.176875D * (useRightArm ? -1 : 1), -1.35625D, 0.078125D);
	}

	/**
	 * Get the {@link Reaper reaper} idle animation
	 *
	 * @return The {@link Reaper reaper} idle animation
	 */
	public static AnimationDefinition getIdleAnimation() {
		return AnimationDefinition.Builder.withLength(3f).looping()
				.addAnimation("reaper",
						new AnimationChannel(AnimationChannel.Targets.POSITION,
								new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1.5f, KeyframeAnimations.posVec(0f, 2f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(3f, KeyframeAnimations.posVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR)))
				.addAnimation("torso",
						new AnimationChannel(AnimationChannel.Targets.POSITION,
								new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 4f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1.5f, KeyframeAnimations.posVec(0f, 0f, 6f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(3f, KeyframeAnimations.posVec(0f, 0f, 4f),
										AnimationChannel.Interpolations.LINEAR)))
				.addAnimation("torso",
						new AnimationChannel(AnimationChannel.Targets.ROTATION,
								new Keyframe(0f, KeyframeAnimations.degreeVec(10f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1.5f, KeyframeAnimations.degreeVec(12.5f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(3f, KeyframeAnimations.degreeVec(10f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR)))
				.addAnimation("left_arm",
						new AnimationChannel(AnimationChannel.Targets.POSITION,
								new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, -5f),
										AnimationChannel.Interpolations.LINEAR)))
				.addAnimation("left_arm",
						new AnimationChannel(AnimationChannel.Targets.ROTATION,
								new Keyframe(0f, KeyframeAnimations.degreeVec(-17.5f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR)))
				.addAnimation("right_arm",
						new AnimationChannel(AnimationChannel.Targets.POSITION,
								new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, -5f),
										AnimationChannel.Interpolations.LINEAR)))
				.addAnimation("right_arm",
						new AnimationChannel(AnimationChannel.Targets.ROTATION,
								new Keyframe(0f, KeyframeAnimations.degreeVec(-17.5f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR))).build();
	}

	/**
	 * Get the {@link Reaper reaper} charge animation
	 *
	 * @return The {@link Reaper reaper} charge animation
	 */
	public static AnimationDefinition getChargeAnimation() {
		return AnimationDefinition.Builder.withLength(0f)
				.addAnimation("head",
						new AnimationChannel(AnimationChannel.Targets.POSITION,
								new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 2f),
										AnimationChannel.Interpolations.LINEAR)))
				.addAnimation("head",
						new AnimationChannel(AnimationChannel.Targets.ROTATION,
								new Keyframe(0f, KeyframeAnimations.degreeVec(12.5f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR)))
				.addAnimation("torso",
						new AnimationChannel(AnimationChannel.Targets.POSITION,
								new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 3f),
										AnimationChannel.Interpolations.LINEAR)))
				.addAnimation("torso",
						new AnimationChannel(AnimationChannel.Targets.ROTATION,
								new Keyframe(0f, KeyframeAnimations.degreeVec(7.5f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR)))
				.addAnimation("left_arm",
						new AnimationChannel(AnimationChannel.Targets.POSITION,
								new Keyframe(0f, KeyframeAnimations.posVec(0f, 27f, -19f),
										AnimationChannel.Interpolations.LINEAR)))
				.addAnimation("left_arm",
						new AnimationChannel(AnimationChannel.Targets.ROTATION,
								new Keyframe(0f, KeyframeAnimations.degreeVec(-117.5f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR)))
				.addAnimation("right_arm",
						new AnimationChannel(AnimationChannel.Targets.POSITION,
								new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 8f),
										AnimationChannel.Interpolations.LINEAR)))
				.addAnimation("right_arm",
						new AnimationChannel(AnimationChannel.Targets.ROTATION,
								new Keyframe(0f, KeyframeAnimations.degreeVec(22.5f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR))).build();
	}

}