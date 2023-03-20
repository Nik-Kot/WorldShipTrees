package com.nikkot.worldshiptrees.triggers;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.EntityPredicate.Composite;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

public class WSTrigger extends SimpleCriterionTrigger<WSTrigger.Instance> {

	final ResourceLocation id;

	public WSTrigger(ResourceLocation resourceLocation) {
		this.id = resourceLocation;
	}

	public void trigger(ServerPlayer serverPlayer) {
		trigger(serverPlayer, instance -> true);
	}

	@Override
	@NotNull
	public ResourceLocation getId() {
		return this.id;
	}

	@Override
	@NotNull
	protected Instance createInstance(@NotNull JsonObject jsonObject, @NotNull Composite composite, @NotNull DeserializationContext deserializationContext) {
		return new Instance(id, composite);
	}

	static class Instance extends AbstractCriterionTriggerInstance {

		public Instance(ResourceLocation resourceLocation, Composite composite) {
			super(resourceLocation, composite);
		}

	}

}
