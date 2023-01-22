package com.nikkot.worldshiptrees.treegen.trunkplacers;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.nikkot.worldshiptrees.treegen.WorldShipTreeConfiguration;
import com.nikkot.worldshiptrees.treegen.foliageplacers.WorldShipFoliagePlacer;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.function.BiConsumer;

public class StraightTrunkPlacer extends WorldShipTrunkPlacer {
   public static final Codec<StraightTrunkPlacer> CODEC = RecordCodecBuilder.create((p_70261_) -> {
      return trunkPlacerParts(p_70261_).apply(p_70261_, StraightTrunkPlacer::new);
   });

   public StraightTrunkPlacer(int p_70248_, int p_70249_, int p_70250_) {
      super(p_70248_, p_70249_, p_70250_);
   }

   protected WorldShipTrunkPlacerType<?> type() {
      return WorldShipTrunkPlacerType.STRAIGHT_TRUNK_PLACER;
   }

   public List<WorldShipFoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader p_226147_, BiConsumer<BlockPos, BlockState> p_226148_, RandomSource p_226149_, int p_226150_, BlockPos p_226151_, WorldShipTreeConfiguration p_226152_) {
      setDirtAt(p_226147_, p_226148_, p_226149_, p_226151_.below(), p_226152_);

      for(int i = 0; i < p_226150_; ++i) {
         this.placeLog(p_226147_, p_226148_, p_226149_, p_226151_.above(i), p_226152_);
      }

      return ImmutableList.of(new WorldShipFoliagePlacer.FoliageAttachment(p_226151_.above(p_226150_), 0, false));
   }
}