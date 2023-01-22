package com.nikkot.worldshiptrees;

import com.mojang.logging.LogUtils;
import com.nikkot.worldshiptrees.objects.WSBlocks;
import com.nikkot.worldshiptrees.objects.WSColors;
import com.nikkot.worldshiptrees.objects.WSItems;
import com.nikkot.worldshiptrees.objects.WSRegisters;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(WorldShipTrees.MODID)
public class WorldShipTrees
{
    public static final String MODID = "worldshiptrees";
    public static final String MOD_NAME = "Pakratt's Worldships";
    public static final String LOG_TAG = '[' + MOD_NAME + ']';
    public static final Logger LOGGER = LogUtils.getLogger();
    public static WorldShipTrees INSTANCE;
    public static String VERSION;

    public WorldShipTrees()
    {
        INSTANCE = this;

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        WSRegisters.registerEverything(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        //LOGGER.info("HELLO FROM COMMON SETUP");
        //LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        //LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            //Minecraft minecraft = Minecraft.getInstance();
            //minecraft.getBlockColors().register(WSColors.rubber_leaves, WSBlocks.BLOCK_RUBBER_WOOD_LEAVES.get());
            //minecraft.getItemColors().register(WSColors.rubber_leaves_item, WSItems.ITEM_RUBBER_WOOD_LEAVES.get());
            // Some client setup code
            //LOGGER.info("HELLO FROM CLIENT SETUP");
            //LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }

        @SubscribeEvent
        public static void registerBlockColors(RegisterColorHandlersEvent.Block event){
            //Minecraft.crash(new CrashReport("dfegr", new Throwable("frgtyjhtgr")));
            event.register(WSColors.block_rubber_leaves, WSBlocks.BLOCK_RUBBER_WOOD_LEAVES.get());
        }

        @SubscribeEvent
        public static void registerItemColors(RegisterColorHandlersEvent.Item event){
            event.register(WSColors.item_rubber_leaves, WSItems.ITEM_RUBBER_WOOD_LEAVES.get());
        }
    }
}
