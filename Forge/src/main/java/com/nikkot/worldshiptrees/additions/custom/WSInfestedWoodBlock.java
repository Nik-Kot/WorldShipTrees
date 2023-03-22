package com.nikkot.worldshiptrees.additions.custom;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class WSInfestedWoodBlock extends WSInfestedPillarBlock {
    public WSInfestedWoodBlock(Block hostBlock, Supplier<? extends EntityType<? extends Mob>> entitySupplier, Properties properties) {
        super(hostBlock, entitySupplier, properties);
    }
}
