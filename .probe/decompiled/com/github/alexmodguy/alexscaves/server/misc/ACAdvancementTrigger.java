package com.github.alexmodguy.alexscaves.server.misc;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.ConstructBeaconTrigger;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.SerializationContext;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

public class ACAdvancementTrigger extends SimpleCriterionTrigger<ACAdvancementTrigger.Instance> {

    public final ResourceLocation resourceLocation;

    public ACAdvancementTrigger(ResourceLocation resourceLocation) {
        this.resourceLocation = resourceLocation;
    }

    public ACAdvancementTrigger.Instance createInstance(JsonObject p_230241_1_, ContextAwarePredicate p_230241_2_, DeserializationContext p_230241_3_) {
        return new ACAdvancementTrigger.Instance(p_230241_2_, this.resourceLocation);
    }

    public void trigger(ServerPlayer p_192180_1_) {
        this.m_66234_(p_192180_1_, p_226308_1_ -> true);
    }

    @Override
    public ResourceLocation getId() {
        return this.resourceLocation;
    }

    public void triggerForEntity(Entity entity) {
        if (entity instanceof ServerPlayer serverPlayer) {
            this.trigger(serverPlayer);
        }
    }

    public static class Instance extends AbstractCriterionTriggerInstance {

        public Instance(ContextAwarePredicate p_i231507_1_, ResourceLocation res) {
            super(res, p_i231507_1_);
        }

        public static ConstructBeaconTrigger.TriggerInstance forLevel(MinMaxBounds.Ints p_203912_0_) {
            return new ConstructBeaconTrigger.TriggerInstance(ContextAwarePredicate.ANY, p_203912_0_);
        }

        @Override
        public JsonObject serializeToJson(SerializationContext p_230240_1_) {
            return super.serializeToJson(p_230240_1_);
        }
    }
}