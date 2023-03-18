package com.nikkot.worldshiptrees.additions.custom.client;

import com.nikkot.worldshiptrees.WorldShipTrees;
import com.nikkot.worldshiptrees.additions.custom.WSEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class WSEntityRenderer extends MobRenderer<WSEntity, WSEntityModel<WSEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(WorldShipTrees.MODID, "textures/entity/ws_entity.png");

    public WSEntityRenderer(EntityRendererProvider.Context context, WSEntityModel<WSEntity> entityModel, float shadowSize) {
        super(context, entityModel, shadowSize);
    }

    @Override
    @NotNull
    public ResourceLocation getTextureLocation(@NotNull WSEntity wsEntity) {
        return TEXTURE;
    }
}
