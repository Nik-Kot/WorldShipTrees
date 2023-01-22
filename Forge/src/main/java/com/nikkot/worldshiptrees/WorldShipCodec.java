package com.nikkot.worldshiptrees;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Optional;

public abstract class WorldShipCodec<T> {
/*

    public Codec<T> byNameCodec(IForgeRegistry<T> registry) {
        Codec<T> codec = ResourceLocation.CODEC.flatXmap((p_206084_) -> {
            return Optional.ofNullable(registry.getValue(p_206084_)).map(DataResult::success).orElseGet(() -> {
                return DataResult.error("Unknown registry key in " + registry.getRegistryKey() + ": " + p_206084_);
            });
        }, (p_206094_) -> {
            return registry.getResourceKey(p_206094_).map(ResourceKey::location).map(DataResult::success).orElseGet(() -> {
                return DataResult.error("Unknown registry element in " + registry.getRegistryKey() + ":" + p_206094_);
            });
        });
        Codec<T> codec1 = ExtraCodecs.idResolverCodec((p_235816_) -> {
            return registry.getResourceKey(p_235816_).isPresent() ? registry..getId(p_235816_) : -1;
        }, this::byId, -1);
        return ExtraCodecs.overrideLifecycle(ExtraCodecs.orCompressed(codec, codec1), this::lifecycle, (p_235810_) -> {
            return this.lifecycle;
        });
    }

*/
}
