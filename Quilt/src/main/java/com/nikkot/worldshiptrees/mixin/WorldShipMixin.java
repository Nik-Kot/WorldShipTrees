package com.nikkot.worldshiptrees.mixin;

import com.nikkot.worldshiptrees.WorldShipTrees;
import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class WorldShipMixin {
	@Inject(method = "init", at = @At("TAIL"))
	public void exampleMod$onInit(CallbackInfo ci) {
		WorldShipTrees.LOGGER.info("This line is printed by an example mod mixin!");
	}
}
