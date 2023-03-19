package com.nikkot.worldshiptrees.events;

import com.nikkot.worldshiptrees.WorldShipTrees;
import com.nikkot.worldshiptrees.additions.WSEntities;
import com.nikkot.worldshiptrees.additions.custom.WSEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WorldShipTrees.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WSBusEvents {
    @SubscribeEvent
    public static void entityAttributes(EntityAttributeCreationEvent event) {
        event.put(WSEntities.ENTITY_WS_ENTITY.get(), WSEntity.createAttributes().build());
    }
}
