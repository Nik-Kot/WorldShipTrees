package com.nikkot.worldshiptrees.additions.custom.client;

import com.nikkot.worldshiptrees.WorldShipTrees;
import com.nikkot.worldshiptrees.additions.custom.WSEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class WSEntityRenderer extends MobRenderer<WSEntity, WSEntityModel<WSEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(WorldShipTrees.MODID, "textures/entity/ws_entity.png");

    public static final float shadowSize = 0.5f;


    public WSEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new WSEntityModel<>(context.bakeLayer(WSEntityModel.LAYER_LOCATION)), shadowSize);
    }

    @Override
    @NotNull
    public ResourceLocation getTextureLocation(@NotNull WSEntity entity) {
        return TEXTURE;
    }
}
