package com.nikkot.worldshiptrees.treegen.foliageplacers;

import com.mojang.serialization.Codec;
import net.minecraftforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;

public class WorldShipFoliagePlacerType<P extends WorldShipFoliagePlacer> {
   public static final WorldShipFoliagePlacerType<BlobFoliagePlacer> BLOB_FOLIAGE_PLACER = register("blob_foliage_placer", BlobFoliagePlacer.CODEC);
   public static final WorldShipFoliagePlacerType<SpruceFoliagePlacer> SPRUCE_FOLIAGE_PLACER = register("spruce_foliage_placer", SpruceFoliagePlacer.CODEC);
   public static final WorldShipFoliagePlacerType<PineFoliagePlacer> PINE_FOLIAGE_PLACER = register("pine_foliage_placer", PineFoliagePlacer.CODEC);
   public static final WorldShipFoliagePlacerType<AcaciaFoliagePlacer> ACACIA_FOLIAGE_PLACER = register("acacia_foliage_placer", AcaciaFoliagePlacer.CODEC);
   public static final WorldShipFoliagePlacerType<BushFoliagePlacer> BUSH_FOLIAGE_PLACER = register("bush_foliage_placer", BushFoliagePlacer.CODEC);
   public static final WorldShipFoliagePlacerType<FancyFoliagePlacer> FANCY_FOLIAGE_PLACER = register("fancy_foliage_placer", FancyFoliagePlacer.CODEC);
   public static final WorldShipFoliagePlacerType<MegaJungleFoliagePlacer> MEGA_JUNGLE_FOLIAGE_PLACER = register("jungle_foliage_placer", MegaJungleFoliagePlacer.CODEC);
   public static final WorldShipFoliagePlacerType<MegaPineFoliagePlacer> MEGA_PINE_FOLIAGE_PLACER = register("mega_pine_foliage_placer", MegaPineFoliagePlacer.CODEC);
   public static final WorldShipFoliagePlacerType<DarkOakFoliagePlacer> DARK_OAK_FOLIAGE_PLACER = register("dark_oak_foliage_placer", DarkOakFoliagePlacer.CODEC);
   public static final WorldShipFoliagePlacerType<RandomSpreadFoliagePlacer> RANDOM_SPREAD_FOLIAGE_PLACER = register("random_spread_foliage_placer", RandomSpreadFoliagePlacer.CODEC);
   private final Codec<P> codec;

   public static final List<WorldShipFoliagePlacerType<? extends WorldShipFoliagePlacer>> foliageTypes = new ArrayList<>();


   private static <P extends WorldShipFoliagePlacer> WorldShipFoliagePlacerType<P> register(String p_68606_, Codec<P> p_68607_) {
      return null;//WSRegisters.foliagePlacerRegister.register(p_68606_, () ->  new WorldShipFoliagePlacerType<>(p_68607_)).get();
   }

   public static List<WorldShipFoliagePlacerType<? extends WorldShipFoliagePlacer>> registerFoliageTypes(DeferredRegister<WorldShipFoliagePlacerType<?>> foliagePlacerRegister) {
      foliageTypes.add(BLOB_FOLIAGE_PLACER);
      foliageTypes.add(SPRUCE_FOLIAGE_PLACER);
      foliageTypes.add(PINE_FOLIAGE_PLACER);
      foliageTypes.add(ACACIA_FOLIAGE_PLACER);
      foliageTypes.add(BUSH_FOLIAGE_PLACER);
      foliageTypes.add(FANCY_FOLIAGE_PLACER);
      foliageTypes.add(MEGA_JUNGLE_FOLIAGE_PLACER);
      foliageTypes.add(MEGA_PINE_FOLIAGE_PLACER);
      foliageTypes.add(DARK_OAK_FOLIAGE_PLACER);
      foliageTypes.add(RANDOM_SPREAD_FOLIAGE_PLACER);

      return foliageTypes;
   }

   public WorldShipFoliagePlacerType(Codec<P> p_68603_) {
      this.codec = p_68603_;
   }

   public Codec<P> codec() {
      return this.codec;
   }
}