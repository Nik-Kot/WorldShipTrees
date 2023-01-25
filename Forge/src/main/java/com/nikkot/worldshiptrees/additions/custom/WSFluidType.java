package com.nikkot.worldshiptrees.additions.custom;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.math.Vector3f;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class WSFluidType extends FluidType {
    private ResourceLocation stillTexture = null;
    private ResourceLocation flowingTexture = null;
    private ResourceLocation overlayTexture = null;
    private ResourceLocation renderOverlayTexture = null;
    private int tintColor = 0xFFFFFFFF;
    private Vector3f fogColor = null;

    private float fogStart = 0f;
    private float fogEnd = 0f;

    public WSFluidType(final Properties properties) {
        super(properties);
    }

    public WSFluidType stillTexture(ResourceLocation stillTexture) {
        this.stillTexture = stillTexture;
        return this;
    }

    public WSFluidType flowingTexture(ResourceLocation flowingTexture) {
        this.flowingTexture = flowingTexture;
        return this;
    }

    public WSFluidType overlayTexture(ResourceLocation overlayTexture) {
        this.overlayTexture = overlayTexture;
        return this;
    }

    public WSFluidType renderOverlayTexture(ResourceLocation renderOverlayTexture) {
        this.renderOverlayTexture = renderOverlayTexture;
        return this;
    }

    public WSFluidType tintColor(int tintColor) {
        this.tintColor = tintColor;
        return this;
    }

    public WSFluidType fogColor(int color) {
        float red = (color >>> 16) & 0xFF;
        float green = (color >>> 8) & 0xFF;
        float blue = color & 0xFF;
        this.fogColor = new Vector3f(red / 255f, green / 255f, blue / 255f);
        return this;
    }

    public WSFluidType fogStart(float fogStart) {
        this.fogStart = fogStart;
        return this;
    }

    public WSFluidType fogEnd(float fogEnd) {
        this.fogEnd = fogEnd;
        return this;
    }

    @Override
    public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
        consumer.accept(new IClientFluidTypeExtensions() {
            @Override
            public ResourceLocation getStillTexture() {
                return stillTexture;
            }

            @Override
            public ResourceLocation getFlowingTexture() {
                return flowingTexture;
            }

            @Override
            @Nullable
            public ResourceLocation getOverlayTexture() {
                return overlayTexture;
            }

            @Override
            @Nullable
            public ResourceLocation getRenderOverlayTexture (Minecraft mc) {
                return renderOverlayTexture;
            }

            @Override
            public int getTintColor() {
                return tintColor;
            }

            @Override
            @NotNull
            public Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
                if (fogColor != null) {
                    return fogColor;
                } else {
                    return fluidFogColor;
                }
            }

            @Override
            public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape) {
                RenderSystem.setShaderFogStart(fogStart);
                if (fogEnd != 0f) {
                    RenderSystem.setShaderFogEnd(fogEnd);
                } else {
                    RenderSystem.setShaderFogEnd(renderDistance);
                }
            }
        });

        super.initializeClient(consumer);
    }
}
