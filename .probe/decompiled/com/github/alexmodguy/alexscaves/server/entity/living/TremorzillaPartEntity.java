package com.github.alexmodguy.alexscaves.server.entity.living;

import com.github.alexmodguy.alexscaves.AlexsCaves;
import com.github.alexmodguy.alexscaves.server.message.MultipartEntityMessage;
import com.github.alexmodguy.alexscaves.server.misc.ACDamageTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.entity.PartEntity;

public class TremorzillaPartEntity extends PartEntity<TremorzillaEntity> {

    private final Entity connectedTo;

    private EntityDimensions size;

    public float scale = 1.0F;

    public TremorzillaPartEntity(TremorzillaEntity parent, Entity connectedTo, float sizeXZ, float sizeY) {
        super(parent);
        this.f_19850_ = true;
        this.connectedTo = connectedTo;
        this.size = EntityDimensions.scalable(sizeXZ, sizeY);
        this.m_6210_();
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        TremorzillaEntity parent = this.getParent();
        return parent == null ? this.size : this.size.scale(parent.getScale());
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        TremorzillaEntity parent = this.getParent();
        if (parent == null) {
            return InteractionResult.PASS;
        } else {
            this.m_216990_(SoundEvents.ITEM_BREAK);
            if (player.m_9236_().isClientSide) {
                AlexsCaves.sendMSGToServer(new MultipartEntityMessage(parent.m_19879_(), player.m_19879_(), 0, 0.0));
            }
            return parent.m_6096_(player, hand);
        }
    }

    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        return super.m_6673_(damageSource) || damageSource.is(ACDamageTypes.ACID) || damageSource.getEntity() != null && this.getParent().m_20365_(damageSource.getEntity());
    }

    @Override
    public boolean save(CompoundTag tag) {
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        TremorzillaEntity parent = this.getParent();
        return parent != null && parent.m_5829_();
    }

    @Override
    public boolean isPickable() {
        TremorzillaEntity parent = this.getParent();
        return parent != null && parent.m_6087_();
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        TremorzillaEntity parent = this.getParent();
        if (source.is(DamageTypeTags.IS_PROJECTILE)) {
            amount *= 0.35F;
        }
        if (!this.isInvulnerableTo(source) && parent != null) {
            Entity player = source.getEntity();
            if (player != null && !parent.m_7307_(player) && player.level().isClientSide) {
                AlexsCaves.sendMSGToServer(new MultipartEntityMessage(parent.m_19879_(), player.getId(), 1, (double) amount));
            }
        }
        return false;
    }

    @Override
    public boolean is(Entity entityIn) {
        return this == entityIn || this.getParent() == entityIn;
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void defineSynchedData() {
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {
    }

    @Override
    public AABB getBoundingBoxForCulling() {
        return this.m_20191_().inflate(2.0, 0.5, 2.0);
    }

    public float calculateAnimationAngle(float partialTicks, boolean pitch) {
        TremorzillaEntity parent = this.getParent();
        float parentRot = 0.0F;
        Vec3 connection = this.connectedTo.getPosition(partialTicks).add(0.0, (double) (this.connectedTo.getBbHeight() * 0.5F), 0.0);
        if (this.connectedTo == parent && parent != null) {
            connection = connection.add(0.0, parent.isTremorzillaSwimming() ? 0.0 : (double) (-4.0F * parent.getScale() - parent.getLegSolverBodyOffset()), 0.0);
        }
        if (parent != null) {
            parentRot = -(parent.f_20884_ + (parent.f_20883_ - parent.f_20884_) * partialTicks) - 90.0F;
        }
        Vec3 center = this.centeredPosition(partialTicks);
        Vec3 offset = connection.subtract(center).normalize();
        Vec3 back = center.add(offset.scale((double) (-1.0F * this.m_20205_())));
        double d0 = connection.x - back.x;
        double d1 = connection.y - back.y;
        double d2 = connection.z - back.z;
        if (pitch) {
            double d3 = (double) Mth.sqrt((float) (d0 * d0 + d2 * d2));
            return Mth.wrapDegrees((float) (-(Mth.atan2(d1, d3) * 180.0 / (float) Math.PI)));
        } else {
            return (float) (Mth.atan2(d2, d0) * 180.0F / (float) Math.PI) + parentRot;
        }
    }

    @Override
    public boolean shouldBeSaved() {
        return false;
    }

    public void setPosCenteredY(Vec3 pos) {
        this.m_6034_(pos.x, pos.y - (double) (this.m_20206_() * 0.5F), pos.z);
    }

    public Vec3 centeredPosition() {
        return this.m_20182_().add(0.0, (double) (this.m_20206_() * 0.5F), 0.0);
    }

    public Vec3 centeredPosition(float partialTicks) {
        return this.m_20318_(partialTicks).add(0.0, (double) (this.m_20206_() * 0.5F), 0.0);
    }
}