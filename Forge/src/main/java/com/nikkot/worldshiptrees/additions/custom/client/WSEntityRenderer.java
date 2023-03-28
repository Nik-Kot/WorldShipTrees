package com.nikkot.worldshiptrees.additions.custom.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.nikkot.worldshiptrees.WorldShipTrees;
import com.nikkot.worldshiptrees.additions.custom.WSEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Silverfish;
import org.jetbrains.annotations.NotNull;

public class WSEntityRenderer extends MobRenderer<WSEntity, WSEntityModel<WSEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(WorldShipTrees.MODID, "textures/entity/ws_entity.png");

    public static final float shadowSize = 0.3f;


    public WSEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new WSEntityModel<>(context.bakeLayer(WSEntityModel.LAYER_LOCATION)), shadowSize);
    }

    @Override
    protected void scale(@NotNull WSEntity entity, @NotNull PoseStack poseStack, float fl) {
        super.scale(entity, poseStack, fl);
        if (entity.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    protected float getFlipDegrees(@NotNull WSEntity entity) {
        return 180.0f;
    }

    @Override
    @NotNull
    public ResourceLocation getTextureLocation(@NotNull WSEntity entity) {
        return TEXTURE;
    }
}
