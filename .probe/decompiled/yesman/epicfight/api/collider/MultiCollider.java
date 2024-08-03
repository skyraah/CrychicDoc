package yesman.epicfight.api.collider;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.vertex.PoseStack;
import java.util.Collections;
import java.util.List;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.entity.PartEntity;
import yesman.epicfight.api.animation.Joint;
import yesman.epicfight.api.animation.JointTransform;
import yesman.epicfight.api.animation.Pose;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.model.Armature;
import yesman.epicfight.api.utils.math.OpenMatrix4f;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

public abstract class MultiCollider<T extends Collider> extends Collider {

    protected final List<T> colliders = Lists.newArrayList();

    protected final int numberOfColliders;

    public MultiCollider(int arrayLength, double posX, double posY, double posZ, AABB outerAABB) {
        super(new Vec3(posX, posY, posZ), outerAABB);
        this.numberOfColliders = arrayLength;
    }

    @SafeVarargs
    public MultiCollider(T... colliders) {
        super(null, null);
        Collections.addAll(this.colliders, colliders);
        this.numberOfColliders = colliders.length;
    }

    public MultiCollider<T> deepCopy() {
        return null;
    }

    @Override
    public List<Entity> updateAndSelectCollideEntity(LivingEntityPatch<?> entitypatch, AttackAnimation attackAnimation, float prevElapsedTime, float elapsedTime, Joint joint, float attackSpeed) {
        int numberOf = Math.max(Math.round((float) (this.numberOfColliders + (Integer) attackAnimation.getProperty(AnimationProperty.AttackAnimationProperty.EXTRA_COLLIDERS).orElse(0)) * attackSpeed), this.numberOfColliders);
        float partialScale = 1.0F / (float) (numberOf - 1);
        float interpolation = 0.0F;
        List<Collider> colliders = Lists.newArrayList();
        LivingEntity original = entitypatch.getOriginal();
        float index = 0.0F;
        float interIndex = Math.min((float) (this.numberOfColliders - 1) / (float) (numberOf - 1), 1.0F);
        for (int i = 0; i < numberOf; i++) {
            colliders.add(((Collider) this.colliders.get((int) index)).deepCopy());
            index += interIndex;
        }
        AABB outerBox = null;
        for (Collider collider : colliders) {
            Armature armature = entitypatch.getArmature();
            int pathIndex = armature.searchPathIndex(joint.getName());
            OpenMatrix4f transformMatrix;
            if (pathIndex == -1) {
                Pose rootPose = new Pose();
                rootPose.putJointData("Root", JointTransform.empty());
                attackAnimation.modifyPose(attackAnimation, rootPose, entitypatch, elapsedTime, 1.0F);
                transformMatrix = rootPose.getOrDefaultTransform("Root").getAnimationBindedMatrix(entitypatch.getArmature().rootJoint, new OpenMatrix4f()).removeTranslation();
            } else {
                float interpolateTime = prevElapsedTime + (elapsedTime - prevElapsedTime) * interpolation;
                transformMatrix = armature.getBindedTransformByJointIndex(attackAnimation.getPoseByTime(entitypatch, interpolateTime, 1.0F), pathIndex);
            }
            double x = entitypatch.getXOld() + (original.m_20185_() - entitypatch.getXOld()) * (double) interpolation;
            double y = entitypatch.getYOld() + (original.m_20186_() - entitypatch.getYOld()) * (double) interpolation;
            double z = entitypatch.getZOld() + (original.m_20189_() - entitypatch.getZOld()) * (double) interpolation;
            OpenMatrix4f mvMatrix = OpenMatrix4f.createTranslation(-((float) x), (float) y, -((float) z));
            transformMatrix.mulFront(mvMatrix.mulBack(entitypatch.getModelMatrix(interpolation)));
            collider.transform(transformMatrix);
            interpolation += partialScale;
            if (outerBox == null) {
                outerBox = collider.getHitboxAABB();
            } else {
                outerBox.minmax(collider.getHitboxAABB());
            }
        }
        return entitypatch.getOriginal().m_9236_().getEntities(entitypatch.getOriginal(), outerBox, entity -> {
            if (entity.isSpectator()) {
                return false;
            } else if (entity instanceof PartEntity && ((PartEntity) entity).getParent().is(entitypatch.getOriginal())) {
                return false;
            } else {
                for (Collider colliderx : colliders) {
                    if (colliderx.isCollide(entity)) {
                        return true;
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void drawInternal(PoseStack matrixStackIn, MultiBufferSource buffer, OpenMatrix4f pose, boolean red) {
    }

    @Override
    public List<Entity> getCollideEntities(Entity entity) {
        List<Entity> list = Lists.newArrayList();
        for (T collider : this.colliders) {
            list.addAll(collider.getCollideEntities(entity));
        }
        return list;
    }

    @Override
    public boolean isCollide(Entity opponent) {
        return false;
    }

    @Override
    public String toString() {
        return super.toString() + " collider count: " + this.numberOfColliders;
    }

    public static enum Usage {

        INTERPOLATION, MULTI_BONES
    }
}