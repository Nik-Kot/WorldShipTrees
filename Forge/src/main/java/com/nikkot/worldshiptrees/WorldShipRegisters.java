package com.nikkot.worldshiptrees;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class WorldShipRegisters {

    public static final DeferredRegister<Item> itemRegister = DeferredRegister.create(ForgeRegistries.ITEMS, WorldShipTrees.MODID);
    public static final DeferredRegister<Block> blockRegister = DeferredRegister.create(ForgeRegistries.BLOCKS, WorldShipTrees.MODID);
    public static final DeferredRegister<Fluid> fluidRegister = DeferredRegister.create(ForgeRegistries.FLUIDS, WorldShipTrees.MODID);
    public static boolean registerEverything(IEventBus eventBus) {
        //Blocks need to be registered first due to use of BlockItems
        boolean blocksRegistered =  WorldShipBlocks.registerBlocks(blockRegister, eventBus);
        //WorldShipFluids.registerFluids(fluidRegister, eventBus);

        boolean itemsRegistered = WorldShipItems.registerItems(itemRegister, blocksRegistered, eventBus);

        return blocksRegistered && itemsRegistered;
    }

    /*public static void registerFluids (IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }*/

}
