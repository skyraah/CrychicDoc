package com.github.alexthe666.alexsmobs.misc;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class AMDamageTypes {

    public static final ResourceKey<DamageType> FARSEER = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("alexsmobs:farseer"));

    public static final ResourceKey<DamageType> FREDDY = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("alexsmobs:freddy"));

    public static DamageSource causeFarseerDamage(LivingEntity attacker) {
        return new AMDamageTypes.DamageSourceRandomMessages(((Registry) attacker.m_9236_().registryAccess().registry(Registries.DAMAGE_TYPE).get()).getHolderOrThrow(FARSEER), attacker);
    }

    public static DamageSource causeFreddyBearDamage(LivingEntity attacker) {
        return new DamageSource(((Registry) attacker.m_9236_().registryAccess().registry(Registries.DAMAGE_TYPE).get()).getHolderOrThrow(FREDDY), attacker);
    }

    private static class DamageSourceRandomMessages extends DamageSource {

        public DamageSourceRandomMessages(Holder<DamageType> damageTypeHolder, @Nullable Entity entity1, @Nullable Entity entity2, @Nullable Vec3 from) {
            super(damageTypeHolder, entity1, entity2, from);
        }

        public DamageSourceRandomMessages(Holder<DamageType> damageTypeHolder, @Nullable Entity entity1, @Nullable Entity entity2) {
            super(damageTypeHolder, entity1, entity2);
        }

        public DamageSourceRandomMessages(Holder<DamageType> damageTypeHolder, Vec3 from) {
            super(damageTypeHolder, from);
        }

        public DamageSourceRandomMessages(Holder<DamageType> damageTypeHolder, @Nullable Entity entity) {
            super(damageTypeHolder, entity);
        }

        public DamageSourceRandomMessages(Holder<DamageType> holderDamageType0) {
            super(holderDamageType0);
        }

        @Override
        public Component getLocalizedDeathMessage(LivingEntity attacked) {
            int type = attacked.getRandom().nextInt(3);
            LivingEntity livingentity = attacked.getKillCredit();
            String s = "death.attack." + this.m_19385_() + "_" + type;
            String s1 = s + ".player";
            return livingentity != null ? Component.translatable(s1, attacked.m_5446_(), livingentity.m_5446_()) : Component.translatable(s, attacked.m_5446_());
        }
    }
}