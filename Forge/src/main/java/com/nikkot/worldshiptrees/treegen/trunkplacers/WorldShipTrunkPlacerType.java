package com.nikkot.worldshiptrees.treegen.trunkplacers;

import com.mojang.serialization.Codec;
import net.minecraftforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;

public class WorldShipTrunkPlacerType<P extends WorldShipTrunkPlacer> {
   public static final WorldShipTrunkPlacerType<StraightTrunkPlacer> STRAIGHT_TRUNK_PLACER = register("straight_trunk_placer", StraightTrunkPlacer.CODEC);
   public static final WorldShipTrunkPlacerType<ForkingTrunkPlacer> FORKING_TRUNK_PLACER = register("forking_trunk_placer", ForkingTrunkPlacer.CODEC);
   public static final WorldShipTrunkPlacerType<GiantTrunkPlacer> GIANT_TRUNK_PLACER = register("giant_trunk_placer", GiantTrunkPlacer.CODEC);
   public static final WorldShipTrunkPlacerType<MegaJungleTrunkPlacer> MEGA_JUNGLE_TRUNK_PLACER = register("mega_jungle_trunk_placer", MegaJungleTrunkPlacer.CODEC);
   public static final WorldShipTrunkPlacerType<DarkOakTrunkPlacer> DARK_OAK_TRUNK_PLACER = register("dark_oak_trunk_placer", DarkOakTrunkPlacer.CODEC);
   public static final WorldShipTrunkPlacerType<FancyTrunkPlacer> FANCY_TRUNK_PLACER = register("fancy_trunk_placer", FancyTrunkPlacer.CODEC);
   public static final WorldShipTrunkPlacerType<BendingTrunkPlacer> BENDING_TRUNK_PLACER = register("bending_trunk_placer", BendingTrunkPlacer.CODEC);
   public static final WorldShipTrunkPlacerType<UpwardsBranchingTrunkPlacer> UPWARDS_BRANCHING_TRUNK_PLACER = register("upwards_branching_trunk_placer", UpwardsBranchingTrunkPlacer.CODEC);
   private final Codec<P> codec;

   public static final List<WorldShipTrunkPlacerType<? extends WorldShipTrunkPlacer>> trunkTypes = new ArrayList<>();

   private static <P extends WorldShipTrunkPlacer> WorldShipTrunkPlacerType<P> register(String p_70327_, Codec<P> p_70328_) {
      //return Registry.register(Registry.TRUNK_PLACER_TYPES, p_70327_, new WorldShipTrunkPlacerType<>(p_70328_));
      return null;//WSRegisters.trunkPlacerRegister.register(p_70327_, () ->  new WorldShipTrunkPlacerType<>(p_70328_)).get();
   }


   public static List<WorldShipTrunkPlacerType<? extends WorldShipTrunkPlacer>> registerTrunkTypes(DeferredRegister<WorldShipTrunkPlacerType<?>> trunkPlacerRegister) {
      trunkTypes.add(STRAIGHT_TRUNK_PLACER);
      trunkTypes.add(FORKING_TRUNK_PLACER);
      trunkTypes.add(GIANT_TRUNK_PLACER);
      trunkTypes.add(MEGA_JUNGLE_TRUNK_PLACER);
      trunkTypes.add(DARK_OAK_TRUNK_PLACER);
      trunkTypes.add(FANCY_TRUNK_PLACER);
      trunkTypes.add(BENDING_TRUNK_PLACER);
      trunkTypes.add(UPWARDS_BRANCHING_TRUNK_PLACER);

      return trunkTypes;
   }

   public WorldShipTrunkPlacerType(Codec<P> p_70324_) {
      this.codec = p_70324_;
   }

   public Codec<P> codec() {
      return this.codec;
   }
}