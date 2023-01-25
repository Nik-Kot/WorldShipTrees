package com.nikkot.worldshiptrees.trees;

import com.nikkot.worldshiptrees.additions.WSBlocks;
import com.nikkot.worldshiptrees.treegen2.WSFeatureSize;
import com.nikkot.worldshiptrees.treegen2.WSFoliagePlacer;
import com.nikkot.worldshiptrees.treegen2.WSTreeConfiguration;
import com.nikkot.worldshiptrees.treegen2.WSTrunkPlacer;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class WSTreeConfigurations {

    public static final TreeConfiguration TREE_RUBBER = new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(WSBlocks.BLOCK_RUBBER_WOOD_LOG.get()),
            new StraightTrunkPlacer(5, 6, 3),
            BlockStateProvider.simple(WSBlocks.BLOCK_RUBBER_WOOD_LEAVES.get()),
            new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
            new TwoLayersFeatureSize(1, 0, 2)).build();

    public static final WSTreeConfiguration TREE_SACRED_RUBBER = new WSTreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(WSBlocks.BLOCK_RUBBER_WOOD_LOG.get()),
            new WSTrunkPlacer(5, 6, 3),
            BlockStateProvider.simple(WSBlocks.BLOCK_RUBBER_WOOD_LEAVES.get()),
            new WSFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
            new WSFeatureSize(1, 0, 2)).build();
}
