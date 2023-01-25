package com.nikkot.worldshiptrees.trees.growers;

import com.nikkot.worldshiptrees.additions.WSFeatures;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.NotNull;

public class RubberTreeGrower extends AbstractTreeGrower {

    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(@NotNull RandomSource randomSource, boolean beeHive) {
        return WSFeatures.CONF_FEATURE_TREE_RUBBER.getHolder().orElseThrow();
    }
}
