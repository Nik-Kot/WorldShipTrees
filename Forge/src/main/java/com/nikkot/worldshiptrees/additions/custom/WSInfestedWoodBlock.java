package com.nikkot.worldshiptrees.additions.custom;

import com.nikkot.worldshiptrees.additions.WSBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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

public class WSInfestedWoodBlock extends WSInfestedPillarBlock {


     public static final int MAX_AGE = BlockStateProperties.MAX_AGE_7;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_7;

    public WSInfestedWoodBlock(Block hostBlock, Supplier<? extends EntityType<? extends Mob>> entitySupplier, Properties properties) {
        super(hostBlock, entitySupplier, properties);
        this.registerDefaultState(defaultBlockState().setValue(AGE, Integer.valueOf(0)));
    }

    @SuppressWarnings("deprecation")
    @Override
    public void randomTick(@NotNull BlockState blockState, @NotNull ServerLevel level, @NotNull BlockPos blockPos, @NotNull RandomSource randomSource) {
    }

    @Override
    public boolean isRandomlyTicking(@NotNull BlockState blockState) {
        return false;
    }

    public boolean tryInfestLeaves(ServerLevel level, BlockPos blockPos) {
        blockPos.relative(Direction.DOWN);
        level.getBlockState(blockPos).is(BlockTags.LEAVES);
        return false;
    }





    @Override
    protected void createBlockStateDefinition(@NotNull StateDefinition.Builder<Block, BlockState> stateBuilder) {
        super.createBlockStateDefinition(stateBuilder);
        stateBuilder.add(AGE);
    }


}
