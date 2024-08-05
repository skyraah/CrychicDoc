package yesman.epicfight.world.entity;

import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import yesman.epicfight.world.entity.ai.attribute.EpicFightAttributes;

public class WitherGhostClone extends FlyingMob {

    public WitherGhostClone(EntityType<? extends FlyingMob> entityType, Level level) {
        super(entityType, level);
        this.m_20242_(true);
        this.f_19794_ = true;
    }

    public WitherGhostClone(ServerLevel level, Vec3 position, LivingEntity target) {
        this(EpicFightEntities.WITHER_GHOST_CLONE.get(), level);
        this.m_146884_(position);
        this.m_7618_(EntityAnchorArgument.Anchor.FEET, target.m_20182_());
        this.m_6710_(target);
    }

    @Override
    public boolean hurt(DamageSource damagesource, float damage) {
        return !damagesource.is(DamageTypeTags.BYPASSES_INVULNERABILITY) ? false : super.m_6469_(damagesource, damage);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(EpicFightAttributes.WEIGHT.get()).add(EpicFightAttributes.ARMOR_NEGATION.get()).add(EpicFightAttributes.IMPACT.get()).add(EpicFightAttributes.MAX_STRIKES.get()).add(Attributes.ATTACK_DAMAGE);
    }

    @Override
    public void customServerAiStep() {
        if (this.f_19797_ >= 40) {
            this.m_142687_(Entity.RemovalReason.DISCARDED);
        }
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEAD;
    }
}