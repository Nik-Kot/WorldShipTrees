package com.nikkot.worldshiptrees.additions;

import com.nikkot.worldshiptrees.WorldShipTrees;
import com.nikkot.worldshiptrees.additions.custom.*;
import com.nikkot.worldshiptrees.trees.growers.RubberTreeGrower;
import com.nikkot.worldshiptrees.trees.growers.SacredRubberTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class WSBlocks {

    public static final TagKey<Block> TAG_INFESTED_WOOD = BlockTags.create(new ResourceLocation(WorldShipTrees.MODID, "infested_logs"));

    public static final IntegerProperty STATE_INFEST_DISTANCE = IntegerProperty.create("infest_distance", 1, 7);

    public static final RegistryObject<RotatedPillarBlock> BLOCK_RUBBER_WOOD_LOG = WSRegisters
            .blockRegister.register("rubber_wood_log", () ->
                    new RotatedPillarBlock(BlockBehaviour.Properties
                            .of(Material.WOOD, (blockState) ->
                                    blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MaterialColor.WOOD : MaterialColor.PODZOL)
                            .sound(SoundType.WOOD)
                            .strength(2.0f))
                    {
                        @Override
                        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                            return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED) ? 0 : 5;
                        }

                        @Override
                        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                            return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED) ? 0 : 5;
                        }
                    });

    public static final RegistryObject<WSHollowPillarBlock> BLOCK_HOLLOW_RUBBER_WOOD_LOG = WSRegisters
            .blockRegister.register("hollow_rubber_wood_log", () ->
                    new WSHollowPillarBlock(BlockBehaviour.Properties
                            .of(Material.WOOD, (blockState) ->
                                    blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MaterialColor.WOOD : MaterialColor.PODZOL)
                            .sound(SoundType.WOOD)
                            .strength(1.0f))
                    {
                        @Override
                        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                            return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED) ? 0 : 5;
                        }

                        @Override
                        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                            return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED) ? 0 : 5;
                        }
                    });
    public static final RegistryObject<WSLeavesBlock> BLOCK_RUBBER_WOOD_LEAVES = WSRegisters
            .blockRegister.register("rubber_wood_leaves", () ->
                    new WSLeavesBlock(BlockBehaviour.Properties
                            .of(Material.LEAVES)
                            .sound(SoundType.GRASS)
                            .strength(0.2F)
                            .randomTicks()
                            .noOcclusion()
                            .isValidSpawn(WSBlocks::ocelotOrParrot)
                            .isSuffocating(WSBlocks::never)
                            .isViewBlocking(WSBlocks::never))
                    {
                        @Override
                        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                            return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED) ? 0 : 30;
                        }

                        @Override
                        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                            return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED) ? 0 : 60;
                        }
                    });

    public static final RegistryObject<SaplingBlock> BLOCK_RUBBER_WOOD_SAPLING = WSRegisters
            .blockRegister.register("rubber_wood_sapling", () ->
                    new SaplingBlock(
                            new RubberTreeGrower(),
                            BlockBehaviour.Properties
                                    .of(Material.PLANT)
                                    .sound(SoundType.GRASS)
                                    .noCollission()
                                    .randomTicks()
                                    .instabreak())
                    {
                        @Override
                        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                            return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED) ? 0 : 60;
                        }

                        @Override
                        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                            return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED) ? 0 : 100;
                        }
                    });
    public static final RegistryObject<SaplingBlock> BLOCK_SACRED_RUBBER_WOOD_SAPLING = WSRegisters
            .blockRegister.register("sacred_rubber_wood_sapling", () ->
                    new SaplingBlock(
                            new SacredRubberTreeGrower(),
                            BlockBehaviour.Properties
                                    .of(Material.PLANT)
                                    .sound(SoundType.GRASS)
                                    .noCollission()
                                    .randomTicks()
                                    .instabreak())
                    {
                        @Override
                        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                            return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED) ? 0 : 60;
                        }

                        @Override
                        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                            return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED) ? 0 : 100;
                        }
                    });

    public static final RegistryObject<LiquidBlock> BLOCK_LIQUID_TREE_SAP = WSRegisters
            .blockRegister.register("tree_sap", () ->
                    new LiquidBlock(
                            WSFluids.FLUID_TREE_SAP_SOURCE,
                            BlockBehaviour.Properties
                                    .of(Material.WATER, MaterialColor.WOOD)
                                    .noCollission()
                                    .strength(100.0f)
                                    .noLootTable()));



    public static final RegistryObject<WSInfestedPillarBlock> BLOCK_INFESTED_RUBBER_WOOD_LOG = WSRegisters
            .blockRegister.register("infested_rubber_wood_log", () ->
                    new WSInfestedPillarBlock(BLOCK_RUBBER_WOOD_LOG.get(), WSEntities.ENTITY_WS_ENTITY, BlockBehaviour.Properties
                            .of(Material.WOOD, (blockState) ->
                                    blockState.getValue(WSInfestedPillarBlock.AXIS) == Direction.Axis.Y ? MaterialColor.WOOD : MaterialColor.PODZOL)
                            .sound(SoundType.WOOD))
                    {
                        @Override
                        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                            return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED) ? 0 : 5;
                        }

                        @Override
                        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                            return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED) ? 0 : 5;
                        }
                    });


    public static final RegistryObject<WSInfestedHollowPillarBlock> BLOCK_INFESTED_HOLLOW_RUBBER_WOOD_LOG = WSRegisters
            .blockRegister.register("infested_hollow_rubber_wood_log", () ->
                    new WSInfestedHollowPillarBlock(BLOCK_HOLLOW_RUBBER_WOOD_LOG.get(), WSEntities.ENTITY_WS_ENTITY, BlockBehaviour.Properties
                            .of(Material.WOOD, (blockState) ->
                                    blockState.getValue(WSInfestedHollowPillarBlock.AXIS) == Direction.Axis.Y ? MaterialColor.WOOD : MaterialColor.PODZOL)
                            .sound(SoundType.WOOD))
                    {
                        @Override
                        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                            return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED) ? 0 : 5;
                        }

                        @Override
                        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                            return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED) ? 0 : 5;
                        }
                    });

    public static final RegistryObject<WSInfestedLeavesBlock> BLOCK_INFESTED_RUBBER_WOOD_LEAVES = WSRegisters
            .blockRegister.register("infested_rubber_wood_leaves", () ->
                    new WSInfestedLeavesBlock(BLOCK_RUBBER_WOOD_LEAVES.get(), WSEntities.ENTITY_WS_ENTITY, BlockBehaviour.Properties
                            .of(Material.LEAVES)
                            .sound(SoundType.GRASS)
                            .strength(0.2F)
                            .randomTicks()
                            .noOcclusion()
                            .isValidSpawn(WSBlocks::ocelotOrParrot)
                            .isSuffocating(WSBlocks::never)
                            .isViewBlocking(WSBlocks::never))
                    {
                        @Override
                        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                            return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED) ? 0 : 30;
                        }

                        @Override
                        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                            return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED) ? 0 : 60;
                        }
                    });


    public static final List<RegistryObject<? extends Block>> blocks = new ArrayList<>();


    public static List<RegistryObject<? extends Block>> registerBlocks (DeferredRegister<Block> blockRegister) {

        blocks.add(BLOCK_RUBBER_WOOD_LOG);
        blocks.add(BLOCK_HOLLOW_RUBBER_WOOD_LOG);
        blocks.add(BLOCK_RUBBER_WOOD_LEAVES);
        blocks.add(BLOCK_INFESTED_RUBBER_WOOD_LEAVES);
        blocks.add(BLOCK_LIQUID_TREE_SAP);

        return blocks;
    }

    public static List<RegistryObject<? extends Block>> registerBlocksSecondary (DeferredRegister<Block> blockRegister) {

        blocks.add(BLOCK_RUBBER_WOOD_SAPLING);
        blocks.add(BLOCK_SACRED_RUBBER_WOOD_SAPLING);
        blocks.add(BLOCK_INFESTED_RUBBER_WOOD_LOG);
        blocks.add(BLOCK_INFESTED_HOLLOW_RUBBER_WOOD_LOG);

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
