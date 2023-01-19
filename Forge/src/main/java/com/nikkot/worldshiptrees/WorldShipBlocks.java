package com.nikkot.worldshiptrees;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class WorldShipBlocks {

    public static RegistryObject<Block> BLOCK_RUBBER_WOOD_LOG;


    public static RegistryObject<Block> BLOCK_RUBBER_WOOD_LEAVES;

    public static void registerBlocks (DeferredRegister<Block> blockRegister, IEventBus eventBus) {

        BLOCK_RUBBER_WOOD_LOG = blockRegister.register("rubber_wood_log", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD)));
        BLOCK_RUBBER_WOOD_LEAVES = blockRegister.register("rubber_wood_leaves", () -> new Block(BlockBehaviour.Properties.of(Material.LEAVES)));

        blockRegister.register(eventBus);
    }


}
