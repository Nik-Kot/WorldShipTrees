package com.nikkot.worldshiptrees.additions.custom;

import com.nikkot.worldshiptrees.additions.WSBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class WSInfestedLog extends WSInfestedPillarBlock {


    public static final int MAX_AGE = BlockStateProperties.MAX_AGE_7;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_7;

    public WSInfestedLog(Supplier<? extends Block> hostBlock, Supplier<? extends Block> hollowBlock, Supplier<? extends EntityType<? extends Mob>> entitySupplier, Properties properties) {
        super(hostBlock, entitySupplier, properties);
        registerHollow(this, hostBlock, hollowBlock);
        this.registerDefaultState(defaultBlockState().setValue(AGE, Integer.valueOf(0)));
    }

    @SuppressWarnings("deprecation")
    @Override
    public void randomTick(@NotNull BlockState blockState, @NotNull ServerLevel level, @NotNull BlockPos blockPos, @NotNull RandomSource randomSource) {
        int age = blockState.getValue(AGE);

        if (age >= 4) {
            infestFoliage(level, blockPos, randomSource);
        }

        if (age < 6) {
            if (age < 4 || randomSource.nextBoolean()) {
                level.setBlock(blockPos, blockState.setValue(AGE, age + 1), 3);
            }
        } else if (age == 6) {
            moveToNextLog(level, blockPos, blockState, randomSource.nextInt(3));
        }
    }

    @Override
    public boolean isRandomlyTicking(@NotNull BlockState blockState) {
        return true;
    }

    public boolean infestFoliage (ServerLevel level, BlockPos blockPos, RandomSource randomSource) {
        if (hasFoliage(level, blockPos)) {
            infestNearest(level, blockPos, randomSource, 0);
            return true;
        }
        return false;
    }

    public boolean hasFoliage(ServerLevel level, BlockPos blockPos) {
        return checkForLeavesInfested(level.getBlockState(blockPos.above()))
                || checkForLeavesInfested(level.getBlockState(blockPos.north()))
                || checkForLeavesInfested(level.getBlockState(blockPos.east()))
                || checkForLeavesInfested(level.getBlockState(blockPos.south()))
                || checkForLeavesInfested(level.getBlockState(blockPos.west()))
                || checkForLeavesInfested(level.getBlockState(blockPos.below()));
    }

    public boolean infestNearest (ServerLevel level, BlockPos blockPos, RandomSource randomSource, int distance) {


        if (distance < 7) {
            BlockPos current = switch (randomSource.nextInt(6)) {
                case 0 -> blockPos.above();
                case 1 -> blockPos.north();
                case 2 -> blockPos.east();
                case 3 -> blockPos.south();
                case 4 -> blockPos.west();
                case 5 -> blockPos.below();
                default -> blockPos.above();
            };
            BlockState blockState = level.getBlockState(current);
            if (infestLeaves(level, current, blockState)) {
                return true;
            } else {
                if (checkForLeavesInfested(blockState)) {
                    return infestNearest(level, current, randomSource,distance + 1);
                }
            }
        }
        return false;
    }

    public boolean moveToNextLog(ServerLevel level, BlockPos blockPos, BlockState blockState, int direction) {
        boolean result = switch (direction) {
            case 0 -> infestLog(level, blockPos.above());
            case 1 -> {
                if (infestLog(level, blockPos.north())) {
                    yield true;
                }
                if (infestLog(level, blockPos.east())) {
                    yield true;
                }
                if (infestLog(level, blockPos.south())) {
                    yield true;
                }
                yield infestLog(level, blockPos.west());
            }
            case 2 -> infestLog(level, blockPos.below());
            default -> infestLog(level, blockPos.above());
        };

        if (result) {
            makeHollow(level, blockPos, blockState);
            return true;
        }
        return false;
    }

    public boolean checkForLeaves(BlockState blockState) {
        return blockState.is(BlockTags.LEAVES) && !blockState.is(WSBlocks.TAG_INFESTED_LEAVES) && blockState.hasProperty(BlockStateProperties.PERSISTENT) && !blockState.getValue(BlockStateProperties.PERSISTENT);
    }

    public boolean checkForLeavesInfested(BlockState blockState) {
        return blockState.is(BlockTags.LEAVES) && blockState.hasProperty(BlockStateProperties.PERSISTENT) && !blockState.getValue(BlockStateProperties.PERSISTENT);
    }

    public boolean checkForLog(BlockState blockState) {
        return blockState.is(BlockTags.LOGS) && !blockState.is(WSBlocks.TAG_INFESTED_LOGS) && !blockState.is(WSBlocks.TAG_HOLLOW_LOGS);
    }

    public boolean checkForInfested(BlockState blockState) {
        return blockState.is(WSBlocks.TAG_INFESTED_LOGS);
    }

    public boolean infestLog(ServerLevel level, BlockPos blockPos) {
        BlockState blockState = level.getBlockState(blockPos);
        if (checkForLog(blockState)) {
            level.setBlock(blockPos, WSInfestation.infestedStateByHost(blockState), 3);
            return true;
        } else {
            return false;
        }
    }

    public boolean infestLeaves(ServerLevel level, BlockPos blockPos) {
        BlockState blockState = level.getBlockState(blockPos);
        if (checkForLeaves(blockState)) {
            level.setBlock(blockPos, WSInfestation.infestedStateByHost(blockState), 3);
            return true;
        } else {
            return false;
        }
    }

    public boolean infestLeaves(ServerLevel level, BlockPos blockPos, BlockState blockState) {
        if (checkForLeaves(blockState)) {
            level.setBlock(blockPos, WSInfestation.infestedStateByHost(blockState), 3);
            return true;
        } else {
            return false;
        }
    }

    public boolean makeHollow (ServerLevel level, BlockPos blockPos) {
        BlockState blockState = level.getBlockState(blockPos);
        if (checkForInfested(blockState)) {
            level.setBlock(blockPos, WSInfestation.hollowStateByInfested(blockState), 3);
            return true;
        } else {
            return false;
        }
    }

    public boolean makeHollow (ServerLevel level, BlockPos blockPos, BlockState blockState) {
        if (checkForInfested(blockState)) {
            level.setBlock(blockPos, WSInfestation.hollowStateByInfested(blockState), 3);
            return true;
        } else {
            return false;
        }
    }




    @Override
    protected void createBlockStateDefinition(@NotNull StateDefinition.Builder<Block, BlockState> stateBuilder) {
        super.createBlockStateDefinition(stateBuilder);
        stateBuilder.add(AGE);
    }


}
