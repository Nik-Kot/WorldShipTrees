package com.nikkot.worldshiptrees.additions.custom.client;
// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.nikkot.worldshiptrees.WorldShipTrees;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;
import net.minecraft.client.model.geom.builders.CubeDeformation;

public class WSEntityModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(WorldShipTrees.MODID, "ws_entity"), "main");
	private final ModelPart bodyPart_2;

	public WSEntityModel(ModelPart root) {
		this.bodyPart_2 = root.getChild("bodyPart_2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bodyPart_2 = partdefinition.addOrReplaceChild("bodyPart_2", CubeListBuilder.create().texOffs(0, 9).addBox(-3.0F, 0.0F, -1.5F, 6.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.0F, 1.0F));

		PartDefinition bodyPart_0 = bodyPart_2.addOrReplaceChild("bodyPart_0", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -4.5F));

		PartDefinition bodyPart_1 = bodyPart_2.addOrReplaceChild("bodyPart_1", CubeListBuilder.create().texOffs(0, 4).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -2.5F));

		PartDefinition bodyLayer_2 = bodyPart_1.addOrReplaceChild("bodyLayer_2", CubeListBuilder.create().texOffs(20, 18).addBox(-3.0F, 0.0F, -1.5F, 6.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

		PartDefinition bodyPart_3 = bodyPart_2.addOrReplaceChild("bodyPart_3", CubeListBuilder.create().texOffs(0, 16).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 3.0F));

		PartDefinition bodyPart_4 = bodyPart_2.addOrReplaceChild("bodyPart_4", CubeListBuilder.create().texOffs(0, 22).addBox(-1.0F, 0.0F, -1.5F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 6.0F));

		PartDefinition bodyLayer_1 = bodyPart_4.addOrReplaceChild("bodyLayer_1", CubeListBuilder.create().texOffs(20, 11).addBox(-3.0F, 0.0F, -1.5F, 6.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

		PartDefinition bodyPart_5 = bodyPart_2.addOrReplaceChild("bodyPart_5", CubeListBuilder.create().texOffs(11, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 8.5F));

		PartDefinition bodyPart_6 = bodyPart_2.addOrReplaceChild("bodyPart_6", CubeListBuilder.create().texOffs(13, 4).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 10.5F));

		PartDefinition bodyLayer_0 = bodyPart_2.addOrReplaceChild("bodyLayer_0", CubeListBuilder.create().texOffs(20, 0).addBox(-5.0F, 0.0F, -1.5F, 10.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bodyPart_2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}