package com.nikkot.worldshiptrees;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.OakTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class WorldShipBlocks {

    public static final RegistryObject<Block> BLOCK_RUBBER_WOOD_LOG = WorldShipRegisters.blockRegister.register("rubber_wood_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, (blockState) -> blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MaterialColor.WOOD : MaterialColor.PODZOL).sound(SoundType.WOOD).strength(2.0F)));
    public static final RegistryObject<Block> BLOCK_RUBBER_WOOD_LEAVES = WorldShipRegisters.blockRegister.register("rubber_wood_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.GRASS).strength(0.2F).randomTicks().noOcclusion().isValidSpawn(WorldShipBlocks::ocelotOrParrot).isSuffocating(WorldShipBlocks::never).isViewBlocking(WorldShipBlocks::never)));

    public static final RegistryObject<Block> BLOCK_RUBBER_WOOD_SAPLING = WorldShipRegisters.blockRegister.register("rubber_wood_sapling", () -> new SaplingBlock(new OakTreeGrower(),  BlockBehaviour.Properties.of(Material.PLANT).sound(SoundType.GRASS).noCollission().randomTicks().instabreak()));
    public static final RegistryObject<Block> BLOCK_SACRED_RUBBER_WOOD_SAPLING = WorldShipRegisters.blockRegister.register("sacred_rubber_wood_sapling", () -> new SaplingBlock(new OakTreeGrower(),  BlockBehaviour.Properties.of(Material.PLANT).sound(SoundType.GRASS).noCollission().randomTicks().instabreak()));

    public static final List<RegistryObject<Block>> blocks = new ArrayList<>();


    public static List<RegistryObject<Block>> registerBlocks (DeferredRegister<Block> blockRegister, IEventBus eventBus) {

        blocks.add(BLOCK_RUBBER_WOOD_LOG);
        blocks.add(BLOCK_RUBBER_WOOD_LEAVES);
        blocks.add(BLOCK_RUBBER_WOOD_SAPLING);
        blocks.add(BLOCK_SACRED_RUBBER_WOOD_SAPLING);

        blockRegister.register(eventBus);

        return blocks;
    }

    private static Boolean ocelotOrParrot(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, EntityType<?> entityType) {
        return entityType == EntityType.OCELOT || entityType == EntityType.PARROT;
    }

    private static boolean always(BlockState blockState_, BlockGetter blockGetter, BlockPos blockPos) {
        return true;
    }

    private static boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return false;
    }

}
