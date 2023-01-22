package com.nikkot.worldshiptrees.treegen.trunkplacers;

import com.mojang.datafixers.Products;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.nikkot.worldshiptrees.objects.WSRegisters;
import com.nikkot.worldshiptrees.treegen.WorldShipTreeConfiguration;
import com.nikkot.worldshiptrees.treegen.WorldShipTreeFeature;
import com.nikkot.worldshiptrees.treegen.foliageplacers.WorldShipFoliagePlacer;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public abstract class WorldShipTrunkPlacer {
   public static final Codec<WorldShipTrunkPlacer> CODEC = null;//WSRegisters.trunkPlacerRegistry.get().getCodec().dispatch(WorldShipTrunkPlacer::type, WorldShipTrunkPlacerType::codec);

   /*public static final Codec<WorldShipTrunkPlacer> CODEC = RecordCodecBuilder.create((p_70261_) -> {
      return trunkPlacerParts(p_70261_).apply(p_70261_, WSTrunkPlacer::new);
   });*/

   private static final int MAX_BASE_HEIGHT = 32;
   private static final int MAX_RAND = 24;
   public static final int MAX_HEIGHT = 80;
   protected final int baseHeight;
   protected final int heightRandA;
   protected final int heightRandB;

   protected static <P extends WorldShipTrunkPlacer> Products.P3<RecordCodecBuilder.Mu<P>, Integer, Integer, Integer> trunkPlacerParts(RecordCodecBuilder.Instance<P> p_70306_) {
      return p_70306_.group(Codec.intRange(0, 32).fieldOf("base_height").forGetter((p_70314_) -> {
         return p_70314_.baseHeight;
      }), Codec.intRange(0, 24).fieldOf("height_rand_a").forGetter((p_70312_) -> {
         return p_70312_.heightRandA;
      }), Codec.intRange(0, 24).fieldOf("height_rand_b").forGetter((p_70308_) -> {
         return p_70308_.heightRandB;
      }));
   }

   public WorldShipTrunkPlacer(int p_70268_, int p_70269_, int p_70270_) {
      this.baseHeight = p_70268_;
      this.heightRandA = p_70269_;
      this.heightRandB = p_70270_;
   }

   protected abstract WorldShipTrunkPlacerType<?> type();

   public abstract List<WorldShipFoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader p_226157_, BiConsumer<BlockPos, BlockState> p_226158_, RandomSource p_226159_, int p_226160_, BlockPos p_226161_, WorldShipTreeConfiguration p_226162_);

   public int getTreeHeight(RandomSource p_226154_) {
      return this.baseHeight + p_226154_.nextInt(this.heightRandA + 1) + p_226154_.nextInt(this.heightRandB + 1);
   }

   private static boolean isDirt(LevelSimulatedReader p_70296_, BlockPos p_70297_) {
      return p_70296_.isStateAtPosition(p_70297_, (p_70304_) -> {
         return Feature.isDirt(p_70304_) && !p_70304_.is(Blocks.GRASS_BLOCK) && !p_70304_.is(Blocks.MYCELIUM);
      });
   }

   protected static void setDirtAt(LevelSimulatedReader p_226170_, BiConsumer<BlockPos, BlockState> p_226171_, RandomSource p_226172_, BlockPos p_226173_, WorldShipTreeConfiguration p_226174_) {
      if (!(onTreeGrow((LevelReader) p_226170_, p_226171_, p_226172_, p_226173_, p_226174_)) && (p_226174_.forceDirt || !isDirt(p_226170_, p_226173_))) {
         p_226171_.accept(p_226173_, p_226174_.dirtProvider.getState(p_226172_, p_226173_));
      }
   }

   public static boolean onTreeGrow(LevelReader p_226170_, BiConsumer<BlockPos, BlockState> p_226171_, RandomSource p_226172_, BlockPos p_226173_, WorldShipTreeConfiguration p_226174_) {
      return false;
      //return ((LevelReader) p_226170_).getBlockState(p_226173_).onTreeGrow((LevelReader) p_226170_, p_226171_, p_226172_, p_226173_, p_226174_);
   }

   protected boolean placeLog(LevelSimulatedReader p_226188_, BiConsumer<BlockPos, BlockState> p_226189_, RandomSource p_226190_, BlockPos p_226191_, WorldShipTreeConfiguration p_226192_) {
      return this.placeLog(p_226188_, p_226189_, p_226190_, p_226191_, p_226192_, Function.identity());
   }

   protected boolean placeLog(LevelSimulatedReader p_226176_, BiConsumer<BlockPos, BlockState> p_226177_, RandomSource p_226178_, BlockPos p_226179_, WorldShipTreeConfiguration p_226180_, Function<BlockState, BlockState> p_226181_) {
      if (this.validTreePos(p_226176_, p_226179_)) {
         p_226177_.accept(p_226179_, p_226181_.apply(p_226180_.trunkProvider.getState(p_226178_, p_226179_)));
         return true;
      } else {
         return false;
      }
   }

   protected void placeLogIfFree(LevelSimulatedReader p_226164_, BiConsumer<BlockPos, BlockState> p_226165_, RandomSource p_226166_, BlockPos.MutableBlockPos p_226167_, WorldShipTreeConfiguration p_226168_) {
      if (this.isFree(p_226164_, p_226167_)) {
         this.placeLog(p_226164_, p_226165_, p_226166_, p_226167_, p_226168_);
      }

   }

   protected boolean validTreePos(LevelSimulatedReader p_226155_, BlockPos p_226156_) {
      return WorldShipTreeFeature.validTreePos(p_226155_, p_226156_);
   }

   public boolean isFree(LevelSimulatedReader p_226185_, BlockPos p_226186_) {
      return this.validTreePos(p_226185_, p_226186_) || p_226185_.isStateAtPosition(p_226186_, (p_226183_) -> {
         return p_226183_.is(BlockTags.LOGS);
      });
   }
}
