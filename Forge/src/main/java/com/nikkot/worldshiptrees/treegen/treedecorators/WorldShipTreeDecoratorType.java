package com.nikkot.worldshiptrees.treegen.treedecorators;

import com.mojang.serialization.Codec;
import net.minecraftforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;

public class WorldShipTreeDecoratorType<P extends WorldShipTreeDecorator> {
   public static final WorldShipTreeDecoratorType<TrunkVineDecorator> TRUNK_VINE = register("trunk_vine", TrunkVineDecorator.CODEC);
   public static final WorldShipTreeDecoratorType<LeaveVineDecorator> LEAVE_VINE = register("leave_vine", LeaveVineDecorator.CODEC);
   public static final WorldShipTreeDecoratorType<CocoaDecorator> COCOA = register("cocoa", CocoaDecorator.CODEC);
   public static final WorldShipTreeDecoratorType<BeehiveDecorator> BEEHIVE = register("beehive", BeehiveDecorator.CODEC);
   public static final WorldShipTreeDecoratorType<AlterGroundDecorator> ALTER_GROUND = register("alter_ground", AlterGroundDecorator.CODEC);
   public static final WorldShipTreeDecoratorType<AttachedToLeavesDecorator> ATTACHED_TO_LEAVES = register("attached_to_leaves", AttachedToLeavesDecorator.CODEC);
   private final Codec<P> codec;

   public static final List<WorldShipTreeDecoratorType<? extends WorldShipTreeDecorator>> treeDecorators = new ArrayList<>();


   private static <P extends WorldShipTreeDecorator> WorldShipTreeDecoratorType<P> register(String p_70053_, Codec<P> p_70054_) {
      //return Registry.register(Registry.TREE_DECORATOR_TYPES, p_70053_, new WorldShipTreeDecoratorType<>(p_70054_));
      return null;//WSRegisters.treeDecoratorRegister.register(p_70053_, () ->  new WorldShipTreeDecoratorType<>(p_70054_)).get();
   }

   public static List<WorldShipTreeDecoratorType<? extends WorldShipTreeDecorator>> registerTreeDecorators(DeferredRegister<WorldShipTreeDecoratorType<?>> treeDecoratorRegister) {
      treeDecorators.add(TRUNK_VINE);
      treeDecorators.add(LEAVE_VINE);
      treeDecorators.add(COCOA);
      treeDecorators.add(BEEHIVE);
      treeDecorators.add(ALTER_GROUND);
      treeDecorators.add(ATTACHED_TO_LEAVES);

      return treeDecorators;
   }
   public WorldShipTreeDecoratorType(Codec<P> p_70050_) {
      this.codec = p_70050_;
   }

   public Codec<P> codec() {
      return this.codec;
   }
}