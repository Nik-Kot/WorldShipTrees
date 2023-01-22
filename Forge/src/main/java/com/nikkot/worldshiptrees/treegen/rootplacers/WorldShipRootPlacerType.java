package com.nikkot.worldshiptrees.treegen.rootplacers;

import com.mojang.serialization.Codec;
import net.minecraftforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;

public class WorldShipRootPlacerType<P extends WorldShipRootPlacer> {
   public static final WorldShipRootPlacerType<MangroveRootPlacer> MANGROVE_ROOT_PLACER = register("mangrove_root_placer", MangroveRootPlacer.CODEC);
   private final Codec<P> codec;

   public static final List<WorldShipRootPlacerType<? extends WorldShipRootPlacer>> rootTypes = new ArrayList<>();

   private static <P extends WorldShipRootPlacer> WorldShipRootPlacerType<P> register(String p_225905_, Codec<P> p_225906_) {
      return null;//WSRegisters.rootPlacerRegister.register(p_225905_, () ->  new WorldShipRootPlacerType<>(p_225906_)).get();
   }

   public static List<WorldShipRootPlacerType<? extends WorldShipRootPlacer>> registerRootTypes(DeferredRegister<WorldShipRootPlacerType<?>> rootPlacerRegister) {
      rootTypes.add(MANGROVE_ROOT_PLACER);

      return rootTypes;
   }

   public WorldShipRootPlacerType(Codec<P> p_225902_) {
      this.codec = p_225902_;
   }

   public Codec<P> codec() {
      return this.codec;
   }
}