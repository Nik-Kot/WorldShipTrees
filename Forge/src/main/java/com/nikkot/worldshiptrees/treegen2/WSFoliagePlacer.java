package com.nikkot.worldshiptrees.treegen2;

import com.mojang.datafixers.Products;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;

public class WSFoliagePlacer extends FoliagePlacer {
   public static final Codec<WSFoliagePlacer> CODEC = RecordCodecBuilder.create((p_68427_) -> {
      return blobParts(p_68427_).apply(p_68427_, WSFoliagePlacer::new);
   });
   protected final int height;

   protected static <P extends WSFoliagePlacer> Products.P3<RecordCodecBuilder.Mu<P>, IntProvider, IntProvider, Integer> blobParts(RecordCodecBuilder.Instance<P> p_68414_) {
      return foliagePlacerParts(p_68414_).and(Codec.intRange(0, 16).fieldOf("height").forGetter((p_68412_) -> {
         return p_68412_.height;
      }));
   }

   public WSFoliagePlacer(IntProvider p_161356_, IntProvider p_161357_, int p_161358_) {
      super(p_161356_, p_161357_);
      this.height = p_161358_;
   }

   @Override
   @NotNull
   protected FoliagePlacerType<?> type() {
      return FoliagePlacerType.BLOB_FOLIAGE_PLACER;
   }

   public void createFoliage(@NotNull LevelSimulatedReader p_225605_, @NotNull BiConsumer<BlockPos, BlockState> p_225606_, @NotNull RandomSource p_225607_, @NotNull WSTreeConfiguration p_225608_, int p_225609_, @NotNull FoliagePlacer.FoliageAttachment p_225610_, int p_225611_, int p_225612_) {
      this.createFoliage(p_225605_, p_225606_, p_225607_, p_225608_, p_225609_, p_225610_, p_225611_, p_225612_, this.offset(p_225607_));
   }

   private int offset(RandomSource p_225592_) {
      return this.offset.sample(p_225592_);
   }
   protected void createFoliage(@NotNull LevelSimulatedReader p_225520_, @NotNull BiConsumer<BlockPos, BlockState> p_225521_, @NotNull RandomSource p_225522_, @NotNull TreeConfiguration p_225523_, int p_225524_, @NotNull FoliageAttachment p_225525_, int p_225526_, int p_225527_, int p_225528_) {
      for(int i = p_225528_; i >= p_225528_ - p_225526_; --i) {
         int j = Math.max(p_225527_ + p_225525_.radiusOffset() - 1 - i / 2, 0);
         this.placeLeavesRow(p_225520_, p_225521_, p_225522_, p_225523_, p_225525_.pos(), j, i, p_225525_.doubleTrunk());
      }
   }

   protected void createFoliage(@NotNull LevelSimulatedReader p_225520_, @NotNull BiConsumer<BlockPos, BlockState> p_225521_, @NotNull RandomSource p_225522_, @NotNull WSTreeConfiguration p_225523_, int p_225524_, @NotNull FoliageAttachment p_225525_, int p_225526_, int p_225527_, int p_225528_) {
      for(int i = p_225528_; i >= p_225528_ - p_225526_; --i) {
         int j = Math.max(p_225527_ + p_225525_.radiusOffset() - 1 - i / 2, 0);
         this.placeLeavesRow(p_225520_, p_225521_, p_225522_, p_225523_, p_225525_.pos(), j, i, p_225525_.doubleTrunk());
      }
   }

   protected void placeLeavesRow(@NotNull LevelSimulatedReader p_225629_, @NotNull BiConsumer<BlockPos, BlockState> p_225630_, @NotNull RandomSource p_225631_, @NotNull WSTreeConfiguration p_225632_, @NotNull BlockPos p_225633_, int p_225634_, int p_225635_, boolean p_225636_) {
      int i = p_225636_ ? 1 : 0;
      BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

      for(int j = -p_225634_; j <= p_225634_ + i; ++j) {
         for(int k = -p_225634_; k <= p_225634_ + i; ++k) {
            if (!this.shouldSkipLocationSigned(p_225631_, j, p_225635_, k, p_225634_, p_225636_)) {
               blockpos$mutableblockpos.setWithOffset(p_225633_, j, p_225635_, k);
               tryPlaceLeaf(p_225629_, p_225630_, p_225631_, p_225632_, blockpos$mutableblockpos);
            }
         }
      }
   }

   protected static void tryPlaceLeaf(LevelSimulatedReader p_225623_, @NotNull BiConsumer<BlockPos, BlockState> p_225624_, @NotNull RandomSource p_225625_, @NotNull WSTreeConfiguration p_225626_, @NotNull BlockPos p_225627_) {
      if (TreeFeature.validTreePos(p_225623_, p_225627_)) {
         BlockState blockstate = p_225626_.foliageProvider.getState(p_225625_, p_225627_);
         if (blockstate.hasProperty(BlockStateProperties.WATERLOGGED)) {
            blockstate = blockstate.setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(p_225623_.isFluidAtPosition(p_225627_, (p_225638_) -> {
               return p_225638_.isSourceOfType(Fluids.WATER);
            })));
         }

         p_225624_.accept(p_225627_, blockstate);
      }
   }

   public int foliageHeight(@NotNull RandomSource p_225516_, int p_225517_, @NotNull TreeConfiguration p_225518_) {
      return this.height;
   }

   public int foliageHeight(@NotNull RandomSource p_225516_, int p_225517_, @NotNull WSTreeConfiguration p_225518_) {
      return this.height;
   }

   protected boolean shouldSkipLocation(@NotNull RandomSource p_225509_, int p_225510_, int p_225511_, int p_225512_, int p_225513_, boolean p_225514_) {
      return p_225510_ == p_225513_ && p_225512_ == p_225513_ && (p_225509_.nextInt(2) == 0 || p_225511_ == 0);
   }
}