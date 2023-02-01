package com.nikkot.worldshiptrees.events;

import com.nikkot.worldshiptrees.WorldShipTrees;
import com.nikkot.worldshiptrees.additions.WSBlocks;
import com.nikkot.worldshiptrees.additions.WSColors;
import com.nikkot.worldshiptrees.additions.WSFluids;
import com.nikkot.worldshiptrees.additions.WSItems;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = WorldShipTrees.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class WSClientBusEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        //Minecraft minecraft = Minecraft.getInstance();
        //minecraft.getBlockColors().register(WSColors.rubber_leaves, WSBlocks.BLOCK_RUBBER_WOOD_LEAVES.get());
        //minecraft.getItemColors().register(WSColors.rubber_leaves_item, WSItems.ITEM_RUBBER_WOOD_LEAVES.get());
        // Some client setup code
        //LOGGER.info("HELLO FROM CLIENT SETUP");
        //LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        ItemBlockRenderTypes.setRenderLayer(WSFluids.FLUID_TREE_SAP_SOURCE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(WSFluids.FLUID_TREE_SAP_FLOWING.get(), RenderType.translucent());
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event){
        //Minecraft.crash(new CrashReport("dfegr", new Throwable("frgtyjhtgr")));
        event.register(WSColors.block_rubber_leaves, WSBlocks.BLOCK_RUBBER_WOOD_LEAVES.get());
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event){
        event.register(WSColors.item_rubber_leaves, WSItems.ITEM_RUBBER_WOOD_LEAVES.get());
        event.register(WSColors.item_bucket, WSItems.ITEM_BUCKET_TREE_SAP.get());
    }
}
