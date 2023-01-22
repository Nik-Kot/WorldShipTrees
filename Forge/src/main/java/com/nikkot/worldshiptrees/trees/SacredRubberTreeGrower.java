package com.nikkot.worldshiptrees.trees;

import com.nikkot.worldshiptrees.objects.WSFeatures;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.NotNull;

public class SacredRubberTreeGrower extends AbstractTreeGrower {

    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(@NotNull RandomSource randomSource, boolean beeHive) {
        /*if (randomSource.nextInt(10) == 0) {
            return beeHive ? TreeFeatures.FANCY_OAK_BEES_005 : TreeFeatures.FANCY_OAK;
        } else {
            return beeHive ? TreeFeatures.OAK_BEES_005 : TreeFeatures.OAK;
        }*/
        return WSFeatures.CONF_FEATURE_TREE_SACRED_RUBBER.getHolder().orElseThrow();
    }
}
