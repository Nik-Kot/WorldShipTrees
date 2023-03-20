package com.nikkot.worldshiptrees.additions.custom;

import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.function.Supplier;

public interface WSInfestation {
    Map<Block, Block> INFESTED_BY_HOST_BLOCK = Maps.newIdentityHashMap();
    Map<Block, Block> HOST_BY_INFESTED_BLOCK = Maps.newIdentityHashMap();
    Map<Block, Supplier<? extends EntityType<? extends Mob>>> MOB_BY_INFESTED_BLOCK = Maps.newIdentityHashMap();
    Map<BlockState, BlockState> HOST_TO_INFESTED_STATES = Maps.newIdentityHashMap();
    Map<BlockState, BlockState> INFESTED_TO_HOST_STATES = Maps.newIdentityHashMap();

    class InfestationParams{
        public Block infestedBlock;
        public Block hostBlock;
        public Supplier<? extends EntityType<? extends Mob>> entitySupplier;

        public InfestationParams(Block infestedBlock, Block hostBlock, Supplier<? extends EntityType<? extends Mob>> entitySupplier) {
            this.infestedBlock = infestedBlock;
            this.hostBlock = hostBlock;
            this.entitySupplier = entitySupplier;
        }

    }

    default void registerInfestation(InfestationParams params) {
        INFESTED_BY_HOST_BLOCK.put(params.infestedBlock, params.hostBlock);
        HOST_BY_INFESTED_BLOCK.put(params.hostBlock, params.infestedBlock);
        MOB_BY_INFESTED_BLOCK.put(params.infestedBlock, params.entitySupplier);
    }

    static boolean isCompatibleHostBlock(Block hostBlock, EntityType<?> entityType) {
        Block infestedBlock = INFESTED_BY_HOST_BLOCK.get(hostBlock);
        if(infestedBlock != null) {
            Supplier<? extends EntityType<? extends Mob>> entitySupplier =  MOB_BY_INFESTED_BLOCK.get(infestedBlock);
            if (entitySupplier != null) {
                return entityType == entitySupplier.get();
            }
        }
        return false;
    }

    static boolean isCompatibleInfestedBlock(Block infestedBlock, EntityType<?> entityType) {
        Supplier<? extends EntityType<? extends Mob>> entitySupplier =  MOB_BY_INFESTED_BLOCK.get(infestedBlock);
        if (entitySupplier != null) {
            return entityType == entitySupplier.get();
        }
        return false;
    }

    default void spawnInfestation(@NotNull BlockState blockState, @NotNull ServerLevel level, @NotNull BlockPos blockPos, @NotNull ItemStack itemStack, boolean condition) {
        EntityType<? extends Mob> infestEntity = MOB_BY_INFESTED_BLOCK.get(blockState.getBlock()).get();
        if (infestEntity != null && level.getGameRules().getBoolean(GameRules.RULE_DOBLOCKDROPS) && itemStack.getEnchantmentLevel(Enchantments.SILK_TOUCH) == 0) {
            Mob entity = infestEntity.create(level);
            if (entity != null) {
                entity.moveTo((double) blockPos.getX() + 0.5d, (double) blockPos.getY(), (double) blockPos.getZ() + 0.5d, 0.0f, 0.0f);
                level.addFreshEntity(entity);
                entity.spawnAnim();
            }
        }
    }

    static BlockState infestedStateByHost(BlockState blockState) {
        return getNewStateWithProperties(HOST_TO_INFESTED_STATES, blockState, () ->
                INFESTED_BY_HOST_BLOCK.get(blockState.getBlock()).defaultBlockState());
    }

    static BlockState hostStateByInfested(BlockState blockState) {
        return getNewStateWithProperties(INFESTED_TO_HOST_STATES, blockState, () ->
                HOST_BY_INFESTED_BLOCK.get(blockState.getBlock()).defaultBlockState());
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
