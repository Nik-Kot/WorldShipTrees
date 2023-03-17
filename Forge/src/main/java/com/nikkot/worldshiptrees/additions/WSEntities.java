package com.nikkot.worldshiptrees.additions;

import com.nikkot.worldshiptrees.WorldShipTrees;
import com.nikkot.worldshiptrees.additions.custom.WSEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class WSEntities {

    public static RegistryObject<EntityType<? extends Entity>> ws_entity = WSRegisters.entityTypeRegister.register("ws_entity", () -> EntityType.Builder.of(WSEntity::new, MobCategory.MONSTER).build(WorldShipTrees.MODID + ":"));


    public static List<RegistryObject<EntityType<? extends Entity>>> entities = new ArrayList<>();
    public static List<RegistryObject<EntityType<? extends Entity>>> registerEntities (DeferredRegister<EntityType<?>> entityTypeRegister) {
        entities.add(ws_entity);
        return entities;
    }

}
