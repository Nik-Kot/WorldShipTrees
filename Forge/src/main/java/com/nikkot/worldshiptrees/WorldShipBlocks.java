package com.nikkot.worldshiptrees;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
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

public class WorldShipBlocks {

    public static RegistryObject<Block> BLOCK_RUBBER_WOOD_LOG;
    public static RegistryObject<Block> BLOCK_RUBBER_WOOD_LEAVES;
    public static RegistryObject<Block> BLOCK_RUBBER_WOOD_SAPLING;
    public static RegistryObject<Block> BLOCK_SACRED_RUBBER_WOOD_SAPLING;

    public static boolean registerBlocks (DeferredRegister<Block> blockRegister, IEventBus eventBus) {

        BLOCK_RUBBER_WOOD_LOG = blockRegister.register("rubber_wood_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, (blockState) -> blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MaterialColor.WOOD : MaterialColor.PODZOL).sound(SoundType.WOOD).strength(2.0F)));

        BLOCK_RUBBER_WOOD_LEAVES = blockRegister.register("rubber_wood_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.GRASS).strength(0.2F).randomTicks().noOcclusion().isValidSpawn(WorldShipBlocks::ocelotOrParrot).isSuffocating(WorldShipBlocks::never).isViewBlocking(WorldShipBlocks::never)));

        BLOCK_RUBBER_WOOD_SAPLING = blockRegister.register("rubber_wood_sapling", () -> new SaplingBlock(new OakTreeGrower(),  BlockBehaviour.Properties.of(Material.PLANT).sound(SoundType.GRASS).noCollission().randomTicks().instabreak()));

        BLOCK_SACRED_RUBBER_WOOD_SAPLING = blockRegister.register("sacred_rubber_wood_sapling", () -> new SaplingBlock(new OakTreeGrower(),  BlockBehaviour.Properties.of(Material.PLANT).sound(SoundType.GRASS).noCollission().randomTicks().instabreak()));



        blockRegister.register(eventBus);

        return true;
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
