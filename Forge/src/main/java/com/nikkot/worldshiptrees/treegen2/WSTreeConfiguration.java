package com.nikkot.worldshiptrees.treegen2;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.List;
import java.util.Optional;

public class WSTreeConfiguration implements FeatureConfiguration {
   public static final Codec<WSTreeConfiguration> CODEC = RecordCodecBuilder.create((p_225468_) -> {
      return p_225468_.group(BlockStateProvider.CODEC.fieldOf("trunk_provider").forGetter((p_161248_) -> {
         return p_161248_.trunkProvider;
      }), WSTrunkPlacer.CODEC.fieldOf("trunk_placer").forGetter((p_161246_) -> {
         return p_161246_.trunkPlacer;
      }), BlockStateProvider.CODEC.fieldOf("foliage_provider").forGetter((p_161244_) -> {
         return p_161244_.foliageProvider;
      }), WSFoliagePlacer.CODEC.fieldOf("foliage_placer").forGetter((p_191357_) -> {
         return p_191357_.foliagePlacer;
      }), WSRootPlacer.CODEC.optionalFieldOf("root_placer").forGetter((p_225478_) -> {
         return p_225478_.rootPlacer;
      }), BlockStateProvider.CODEC.fieldOf("dirt_provider").forGetter((p_225476_) -> {
         return p_225476_.dirtProvider;
      }), WSFeatureSize.CODEC.fieldOf("minimum_size").forGetter((p_225474_) -> {
         return p_225474_.minimumSize;
      }), WSTreeDecorator.CODEC.listOf().fieldOf("decorators").forGetter((p_225472_) -> {
         return p_225472_.decorators;
      }), Codec.BOOL.fieldOf("ignore_vines").orElse(false).forGetter((p_161232_) -> {
         return p_161232_.ignoreVines;
      }), Codec.BOOL.fieldOf("force_dirt").orElse(false).forGetter((p_225470_) -> {
         return p_225470_.forceDirt;
      })).apply(p_225468_, WSTreeConfiguration::new);
   });
   //TODO: Review this, see if we can hook in the sapling into the Codec
   public final BlockStateProvider trunkProvider;
   public final BlockStateProvider dirtProvider;
   public final WSTrunkPlacer trunkPlacer;
   public final BlockStateProvider foliageProvider;
   public final WSFoliagePlacer foliagePlacer;
   public final Optional<WSRootPlacer> rootPlacer;
   public final WSFeatureSize minimumSize;
   public final List<WSTreeDecorator> decorators;
   public final boolean ignoreVines;
   public final boolean forceDirt;

   protected WSTreeConfiguration(BlockStateProvider p_225457_, WSTrunkPlacer p_225458_, BlockStateProvider p_225459_, WSFoliagePlacer p_225460_, Optional<WSRootPlacer> p_225461_, BlockStateProvider p_225462_, WSFeatureSize p_225463_, List<WSTreeDecorator> p_225464_, boolean p_225465_, boolean p_225466_) {
      this.trunkProvider = p_225457_;
      this.trunkPlacer = p_225458_;
      this.foliageProvider = p_225459_;
      this.foliagePlacer = p_225460_;
      this.rootPlacer = p_225461_;
      this.dirtProvider = p_225462_;
      this.minimumSize = p_225463_;
      this.decorators = p_225464_;
      this.ignoreVines = p_225465_;
      this.forceDirt = p_225466_;
   }

   public static class TreeConfigurationBuilder {
      public final BlockStateProvider trunkProvider;
      private final WSTrunkPlacer trunkPlacer;
      public final BlockStateProvider foliageProvider;
      private final WSFoliagePlacer foliagePlacer;
      private final Optional<WSRootPlacer> rootPlacer;
      private BlockStateProvider dirtProvider;
      private final WSFeatureSize minimumSize;
      private List<WSTreeDecorator> decorators = ImmutableList.of();
      private boolean ignoreVines;
      private boolean forceDirt;

      public TreeConfigurationBuilder(BlockStateProvider p_225481_, WSTrunkPlacer p_225482_, BlockStateProvider p_225483_, WSFoliagePlacer p_225484_, Optional<WSRootPlacer> p_225485_, WSFeatureSize p_225486_) {
         this.trunkProvider = p_225481_;
         this.trunkPlacer = p_225482_;
         this.foliageProvider = p_225483_;
         this.dirtProvider = BlockStateProvider.simple(Blocks.DIRT);
         this.foliagePlacer = p_225484_;
         this.rootPlacer = p_225485_;
         this.minimumSize = p_225486_;
      }

      public TreeConfigurationBuilder(BlockStateProvider p_191359_, WSTrunkPlacer p_191360_, BlockStateProvider p_191361_, WSFoliagePlacer p_191362_, WSFeatureSize p_191363_) {
         this(p_191359_, p_191360_, p_191361_, p_191362_, Optional.empty(), p_191363_);
      }

      public TreeConfigurationBuilder dirt(BlockStateProvider p_161261_) {
         this.dirtProvider = p_161261_;
         return this;
      }

      public TreeConfigurationBuilder decorators(List<WSTreeDecorator> p_68250_) {
         this.decorators = p_68250_;
         return this;
      }

      public TreeConfigurationBuilder ignoreVines() {
         this.ignoreVines = true;
         return this;
      }

      public TreeConfigurationBuilder forceDirt() {
         this.forceDirt = true;
         return this;
      }

      public WSTreeConfiguration build() {
         return new WSTreeConfiguration(this.trunkProvider, this.trunkPlacer, this.foliageProvider, this.foliagePlacer, this.rootPlacer, this.dirtProvider, this.minimumSize, this.decorators, this.ignoreVines, this.forceDirt);
      }
   }
}
