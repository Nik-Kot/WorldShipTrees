package com.nikkot.worldshiptrees.additions.custom;

import com.google.common.collect.Maps;
import com.nikkot.worldshiptrees.additions.WSBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.function.Supplier;

public class WSLeavesBlock extends LeavesBlock {

    Map<BlockState, BlockState> HOST_TO_INFESTED_STATES = Maps.newIdentityHashMap();

    public static final IntegerProperty INFEST_DISTANCE = WSBlocks.STATE_INFEST_DISTANCE;


    public WSLeavesBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(INFEST_DISTANCE, Integer.valueOf(7)));
    }

    @Override
    public void tick(@NotNull BlockState blockState, @NotNull ServerLevel level, @NotNull BlockPos blockPos, @NotNull RandomSource randomSource) {
        BlockState newBlockState = updateInfestDistance(blockState, level, blockPos);
        level.setBlock(blockPos, newBlockState, 3);
        super.tick(newBlockState, level, blockPos, randomSource);
    }

    private static BlockState updateInfestDistance(BlockState blockState, LevelAccessor levelAccessor, BlockPos blockPos) {
        int i = 7;
        BlockPos.MutableBlockPos mutableblockpos = new BlockPos.MutableBlockPos();

        for(Direction direction : Direction.values()) {
            mutableblockpos.setWithOffset(blockPos, direction);
            i = Math.min(i, getInfestDistanceAt(levelAccessor.getBlockState(mutableblockpos)) + 1);
            if (i == 1) {
                break;
            }
        }

        return blockState.setValue(INFEST_DISTANCE, Integer.valueOf(i));
    }

    private static int getInfestDistanceAt(BlockState blockState) {
        if (blockState.is(WSBlocks.TAG_INFESTED_WOOD)) {
            return 0;
        } else {
            return blockState.getBlock() instanceof WSLeavesBlock ? blockState.getValue(INFEST_DISTANCE) : 7;
        }
    }

    @Override
    public boolean isRandomlyTicking(@NotNull BlockState blockState) {
        return super.isRandomlyTicking(blockState);
    }
    @Override
    public void randomTick(@NotNull BlockState blockState, @NotNull ServerLevel level, @NotNull BlockPos blockPos, @NotNull RandomSource randomSource) {
        super.randomTick(blockState, level, blockPos, randomSource);
        if (this.toBeInfested(blockState)) {
            WSInfestedLeavesBlock block = WSBlocks.BLOCK_INFESTED_RUBBER_WOOD_LEAVES.get();
            BlockState blockState1 = getNewStateWithProperties(HOST_TO_INFESTED_STATES, blockState, block::defaultBlockState);
            level.setBlock(blockPos, blockState1, 3);
        }

    }


    protected boolean toBeInfested(BlockState blockState) {
        return !blockState.getValue(PERSISTENT) && blockState.getValue(INFEST_DISTANCE) < 7;
    }

    @NotNull
    public BlockState updateShape(@NotNull BlockState blockState, @NotNull Direction direction, @NotNull BlockState blockState1, @NotNull LevelAccessor levelAccessor, @NotNull BlockPos blockPos, @NotNull BlockPos blockPos1) {
        BlockState blockState2 = super.updateShape(blockState, direction, blockState1, levelAccessor, blockPos, blockPos1);


        int i = getInfestDistanceAt(blockState1) + 1;
        if (i != 1 || blockState2.getValue(INFEST_DISTANCE) != i) {
            levelAccessor.scheduleTick(blockPos, this, 1);
        }

        return blockState2;
    }

    @Override
    protected void createBlockStateDefinition(@NotNull StateDefinition.Builder<Block, BlockState> stateBuilder) {
        super.createBlockStateDefinition(stateBuilder);
        stateBuilder.add(INFEST_DISTANCE);
    }

    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext placeContext) {
        return updateInfestDistance(super.getStateForPlacement(placeContext), placeContext.getLevel(), placeContext.getClickedPos());
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static BlockState getNewStateWithProperties(Map<BlockState, BlockState> stateMap, BlockState blockState, Supplier<BlockState> blockStateSupplier) {
        return stateMap.computeIfAbsent(blockState, (blockState1) -> {
            BlockState blockstate = blockStateSupplier.get();

            for(Property property : blockState1.getProperties()) {
                blockstate = blockstate.hasProperty(property) ? blockstate.setValue(property, blockState1.getValue(property)) : blockstate;
            }

            return blockstate;
        });
    }
}
