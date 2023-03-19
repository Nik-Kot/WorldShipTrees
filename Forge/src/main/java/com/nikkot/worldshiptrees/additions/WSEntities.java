package com.nikkot.worldshiptrees.additions;

import com.nikkot.worldshiptrees.WorldShipTrees;
import com.nikkot.worldshiptrees.additions.custom.WSEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class WSEntities {

    public static RegistryObject<EntityType<WSEntity>> ENTITY_WS_ENTITY = WSRegisters
            .entityTypeRegister.register("ws_entity", () ->
                    EntityType.Builder
                            .of(WSEntity::new, MobCategory.MONSTER)
                            .build(WorldShipTrees.MODID + ":ws_entity"));


    public static List<RegistryObject<? extends EntityType<?>>> entities = new ArrayList<>();
    public static List<RegistryObject<? extends EntityType<?>>> registerEntities (DeferredRegister<EntityType<?>> entityTypeRegister) {
        entities.add(ENTITY_WS_ENTITY);
        return entities;
    }

}
