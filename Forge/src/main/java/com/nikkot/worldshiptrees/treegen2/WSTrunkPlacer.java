package com.nikkot.worldshiptrees.treegen2;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.nikkot.worldshiptrees.treegen.WorldShipTreeConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class WSTrunkPlacer extends TrunkPlacer {
   public static final Codec<WSTrunkPlacer> CODEC = RecordCodecBuilder.create((p_70261_) -> {
      return trunkPlacerParts(p_70261_).apply(p_70261_, WSTrunkPlacer::new);
   });

   public WSTrunkPlacer(int p_70248_, int p_70249_, int p_70250_) {
      super(p_70248_, p_70249_, p_70250_);
   }

   @Override
   @NotNull
   protected TrunkPlacerType<?> type() {
      return TrunkPlacerType.STRAIGHT_TRUNK_PLACER;
   }

   @Override
   @NotNull
   public List<FoliagePlacer.FoliageAttachment> placeTrunk(@NotNull LevelSimulatedReader p_226147_, @NotNull BiConsumer<BlockPos, BlockState> p_226148_, @NotNull RandomSource p_226149_, int p_226150_, BlockPos p_226151_, @NotNull TreeConfiguration p_226152_) {
      setDirtAt(p_226147_, p_226148_, p_226149_, p_226151_.below(), p_226152_);

      for(int i = 0; i < p_226150_; ++i) {
         this.placeLog(p_226147_, p_226148_, p_226149_, p_226151_.above(i), p_226152_);
      }

      return ImmutableList.of(new FoliagePlacer.FoliageAttachment(p_226151_.above(p_226150_), 0, false));
   }

   @NotNull
   public List<FoliagePlacer.FoliageAttachment> placeTrunk(@NotNull LevelSimulatedReader p_226147_, @NotNull BiConsumer<BlockPos, BlockState> p_226148_, @NotNull RandomSource p_226149_, int p_226150_, BlockPos p_226151_, @NotNull WSTreeConfiguration p_226152_) {
      setDirtAt(p_226147_, p_226148_, p_226149_, p_226151_.below(), p_226152_);

      for(int i = 0; i < p_226150_; ++i) {
         this.placeLog(p_226147_, p_226148_, p_226149_, p_226151_.above(i), p_226152_);
      }

      return ImmutableList.of(new FoliagePlacer.FoliageAttachment(p_226151_.above(p_226150_), 0, false));
   }

   protected boolean placeLog(@NotNull LevelSimulatedReader p_226188_, @NotNull BiConsumer<BlockPos, BlockState> p_226189_, @NotNull RandomSource p_226190_, @NotNull BlockPos p_226191_, @NotNull WSTreeConfiguration p_226192_) {
      return this.placeLog(p_226188_, p_226189_, p_226190_, p_226191_, p_226192_, Function.identity());
   }

   protected boolean placeLog(@NotNull LevelSimulatedReader p_226176_, @NotNull BiConsumer<BlockPos, BlockState> p_226177_, @NotNull RandomSource p_226178_, @NotNull BlockPos p_226179_, @NotNull WSTreeConfiguration p_226180_, @NotNull Function<BlockState, BlockState> p_226181_) {
      if (this.validTreePos(p_226176_, p_226179_)) {
         p_226177_.accept(p_226179_, p_226181_.apply(p_226180_.trunkProvider.getState(p_226178_, p_226179_)));
         return true;
      } else {
         return false;
      }
   }

   protected static void setDirtAt(LevelSimulatedReader p_226170_, BiConsumer<BlockPos, BlockState> p_226171_, RandomSource p_226172_, BlockPos p_226173_, WSTreeConfiguration p_226174_) {
      if (!(onTreeGrow((LevelReader) p_226170_, p_226171_, p_226172_, p_226173_, p_226174_)) && (p_226174_.forceDirt || !isDirt(p_226170_, p_226173_))) {
         p_226171_.accept(p_226173_, p_226174_.dirtProvider.getState(p_226172_, p_226173_));
      }
   }

   private static boolean isDirt(LevelSimulatedReader p_70296_, BlockPos p_70297_) {
      return p_70296_.isStateAtPosition(p_70297_, (p_70304_) -> {
         return Feature.isDirt(p_70304_) && !p_70304_.is(Blocks.GRASS_BLOCK) && !p_70304_.is(Blocks.MYCELIUM);
      });
   }

   public static boolean onTreeGrow(LevelReader p_226170_, BiConsumer<BlockPos, BlockState> p_226171_, RandomSource p_226172_, BlockPos p_226173_, WSTreeConfiguration p_226174_) {
      return false;
      //return ((LevelReader) p_226170_).getBlockState(p_226173_).onTreeGrow((LevelReader) p_226170_, p_226171_, p_226172_, p_226173_, p_226174_);
   }
}