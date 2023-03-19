package com.nikkot.worldshiptrees.additions.custom;

import com.google.common.collect.Maps;
import com.nikkot.worldshiptrees.additions.WSEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.function.Supplier;

public class WSInfestedWoodBlock extends RotatedPillarBlock {
    private final RotatedPillarBlock hostBlock;
    private static final Map<Block, Block> BLOCK_BY_HOST_BLOCK = Maps.newIdentityHashMap();
    private static final Map<BlockState, BlockState> HOST_TO_INFESTED_STATES = Maps.newIdentityHashMap();
    private static final Map<BlockState, BlockState> INFESTED_TO_HOST_STATES = Maps.newIdentityHashMap();

    public WSInfestedWoodBlock(RotatedPillarBlock block, BlockBehaviour.Properties properties) {
        super(properties.destroyTime(block.defaultDestroyTime() / 2.0F).explosionResistance(0.75F));
        this.hostBlock = block;
        BLOCK_BY_HOST_BLOCK.put(block, this);
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED) ? 0 : 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED) ? 0 : 5;
    }

    public Block getHostBlock() {
        return this.hostBlock;
    }

    public static boolean isCompatibleHostBlock(BlockState blockState) {
        return BLOCK_BY_HOST_BLOCK.containsKey(blockState.getBlock());
    }

    private void spawnInfestation(ServerLevel level, BlockPos blockPos) {
        WSEntity wsEntity = WSEntities.ENTITY_WS_ENTITY.get().create(level);
        if (wsEntity != null) {
            wsEntity.moveTo((double) blockPos.getX() + 0.5d, (double) blockPos.getY(), (double) blockPos.getZ() + 0.5d, 0.0f, 0.0f);
            level.addFreshEntity(wsEntity);
            wsEntity.spawnAnim();
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void spawnAfterBreak(@NotNull BlockState blockState, @NotNull ServerLevel level, @NotNull BlockPos blockPos, @NotNull ItemStack itemStack, boolean condition) {
        super.spawnAfterBreak(blockState, level, blockPos, itemStack, condition);
        if (level.getGameRules().getBoolean(GameRules.RULE_DOBLOCKDROPS) && itemStack.getEnchantmentLevel(Enchantments.SILK_TOUCH) == 0) {
            this.spawnInfestation(level, blockPos);
        }

    }

    public static BlockState infestedStateByHost(BlockState blockState) {
        return getNewStateWithProperties(HOST_TO_INFESTED_STATES, blockState, () ->
                BLOCK_BY_HOST_BLOCK.get(blockState.getBlock()).defaultBlockState());
    }

    public BlockState hostStateByInfested(BlockState blockState) {
        return getNewStateWithProperties(INFESTED_TO_HOST_STATES, blockState, () ->
                this.getHostBlock().defaultBlockState());
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
