package org.mineworld.client.model;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.mineworld.MineWorld;
import org.mineworld.core.MWArmorMaterials;
import org.mineworld.helper.ResourceHelper;

/**
 * {@link MineWorld MineWorld} {@link HatModel Straw Hat Model}
 */
@OnlyIn(Dist.CLIENT)
public class StrawHatModel extends HatModel {

    /**
     * The {@link ModelLayerLocation Model Layer Location}
     */
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MineWorld.MOD_ID, ResourceHelper.hat(MWArmorMaterials.STRAW)), "main");

    /**
     * Constructor. Set the {@link ModelPart Root Model Part}
     *
     * @param root {@link ModelPart The Root Model Part}
     */
    public StrawHatModel(final ModelPart root) {
        super(root);
    }

    /**
     * Create the {@link LayerDefinition Model Layer Definition}
     *
     * @return {@link LayerDefinition The Model Layer Definition}
     */
    public static LayerDefinition createBodyLayer() {
        final MeshDefinition meshDefinition = new MeshDefinition();
        final PartDefinition partDefinition = HatModel.getBasePartDefinition(meshDefinition);

        final PartDefinition hat = partDefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        final PartDefinition armorHead = hat.addOrReplaceChild("armorHead", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        final PartDefinition straw_hat = armorHead.addOrReplaceChild("straw_hat", CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-6.0F, -8.0F, -6.0F, 12.0F, 3.0F, 12.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 35).addBox(-5.0F, -9.0F, -5.0F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 22).addBox(-4.0F, -11.0F, -4.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 15).addBox(-3.0F, -12.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshDefinition, 64, 64);
    }

}