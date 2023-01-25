package com.nikkot.worldshiptrees.additions;

import com.nikkot.worldshiptrees.trees.growers.RubberTreeGrower;
import com.nikkot.worldshiptrees.trees.growers.SacredRubberTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class WSBlocks {

    public static final RegistryObject<RotatedPillarBlock> BLOCK_RUBBER_WOOD_LOG = WSRegisters.blockRegister.register("rubber_wood_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, (blockState) -> blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MaterialColor.WOOD : MaterialColor.PODZOL).sound(SoundType.WOOD).strength(2.0F)) {
        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED) ? 0 : 5;
        }
        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED) ? 0 : 5;
        }
    });
    public static final RegistryObject<LeavesBlock> BLOCK_RUBBER_WOOD_LEAVES = WSRegisters.blockRegister.register("rubber_wood_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.GRASS).strength(0.2F).randomTicks().noOcclusion().isValidSpawn(WSBlocks::ocelotOrParrot).isSuffocating(WSBlocks::never).isViewBlocking(WSBlocks::never)) {
        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED) ? 0 : 30;
        }
        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED) ? 0 : 60;
        }
    });

    public static final RegistryObject<SaplingBlock> BLOCK_RUBBER_WOOD_SAPLING = WSRegisters.blockRegister.register("rubber_wood_sapling", () -> new SaplingBlock(new RubberTreeGrower(),  BlockBehaviour.Properties.of(Material.PLANT).sound(SoundType.GRASS).noCollission().randomTicks().instabreak()) {
        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED) ? 0 : 60;
        }
        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED) ? 0 : 100;
        }
    });
    public static final RegistryObject<SaplingBlock> BLOCK_SACRED_RUBBER_WOOD_SAPLING = WSRegisters.blockRegister.register("sacred_rubber_wood_sapling", () -> new SaplingBlock(new SacredRubberTreeGrower(),  BlockBehaviour.Properties.of(Material.PLANT).sound(SoundType.GRASS).noCollission().randomTicks().instabreak()) {
        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED) ? 0 : 60;
        }
        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED) ? 0 : 100;
        }
    });

    public static final RegistryObject<LiquidBlock> BLOCK_LIQUID_TREE_SAP = WSRegisters.blockRegister.register("tree_sap", () -> new LiquidBlock(WSFluids.FLUID_TREE_SAP_SOURCE, BlockBehaviour.Properties.of(Material.WATER, MaterialColor.WOOD).noCollission().strength(100.0f).noLootTable()));


    public static final List<RegistryObject<? extends Block>> blocks = new ArrayList<>();


    public static List<RegistryObject<? extends Block>> registerBlocks (DeferredRegister<Block> blockRegister) {

        blocks.add(BLOCK_RUBBER_WOOD_LOG);
        blocks.add(BLOCK_RUBBER_WOOD_LEAVES);
        blocks.add(BLOCK_LIQUID_TREE_SAP);

        return blocks;
    }

    public static List<RegistryObject<? extends Block>> registerBlocksSecondary (DeferredRegister<Block> blockRegister) {

        blocks.add(BLOCK_RUBBER_WOOD_SAPLING);
        blocks.add(BLOCK_SACRED_RUBBER_WOOD_SAPLING);

        return blocks;
    }

    private static Boolean ocelotOrParrot(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, EntityType<?> entityType) {
        return entityType == EntityType.OCELOT || entityType == EntityType.PARROT;
    }

    private static boolean always(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return true;
    }

    private static boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return false;
    }

}
