package com.nikkot.worldshiptrees.treegen2;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.featuresize.FeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.FeatureSizeType;
import org.jetbrains.annotations.NotNull;

import java.util.OptionalInt;

public class WSFeatureSize extends FeatureSize {
   public static final Codec<WSFeatureSize> CODEC = RecordCodecBuilder.create((p_68356_) -> {
      return p_68356_.group(Codec.intRange(0, 81).fieldOf("limit").orElse(1).forGetter((p_161341_) -> {
         return p_161341_.limit;
      }), Codec.intRange(0, 16).fieldOf("lower_size").orElse(0).forGetter((p_161339_) -> {
         return p_161339_.lowerSize;
      }), Codec.intRange(0, 16).fieldOf("upper_size").orElse(1).forGetter((p_161337_) -> {
         return p_161337_.upperSize;
      }), minClippedHeightCodec()).apply(p_68356_, WSFeatureSize::new);
   });
   private final int limit;
   private final int lowerSize;
   private final int upperSize;

   public WSFeatureSize(int p_68342_, int p_68343_, int p_68344_) {
      this(p_68342_, p_68343_, p_68344_, OptionalInt.empty());
   }

   public WSFeatureSize(int p_68346_, int p_68347_, int p_68348_, OptionalInt p_68349_) {
      super(p_68349_);
      this.limit = p_68346_;
      this.lowerSize = p_68347_;
      this.upperSize = p_68348_;
   }

   @Override
   @NotNull
   protected FeatureSizeType<?> type() {
      return FeatureSizeType.TWO_LAYERS_FEATURE_SIZE;
   }

   public int getSizeAtHeight(int p_68351_, int p_68352_) {
      return p_68352_ < this.limit ? this.lowerSize : this.upperSize;
   }
}