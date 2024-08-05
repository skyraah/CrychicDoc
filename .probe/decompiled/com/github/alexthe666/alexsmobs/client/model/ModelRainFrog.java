package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityRainFrog;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;

public class ModelRainFrog extends AdvancedEntityModel<EntityRainFrog> {

    private final AdvancedModelBox root;

    private final AdvancedModelBox body;

    private final AdvancedModelBox tongue;

    private final AdvancedModelBox left_arm;

    private final AdvancedModelBox right_arm;

    private final AdvancedModelBox left_leg;

    private final AdvancedModelBox right_leg;

    private final AdvancedModelBox left_eye;

    private final AdvancedModelBox right_eye;

    public ModelRainFrog() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.root = new AdvancedModelBox(this, "root");
        this.root.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.body = new AdvancedModelBox(this, "body");
        this.body.setRotationPoint(0.0F, -3.0F, -0.5F);
        this.root.addChild(this.body);
        this.body.setTextureOffset(0, 0).addBox(-3.5F, -3.0F, -4.5F, 7.0F, 5.0F, 9.0F, 0.0F, false);
        this.tongue = new AdvancedModelBox(this, "tongue");
        this.tongue.setRotationPoint(0.0F, -1.0F, -4.5F);
        this.body.addChild(this.tongue);
        this.tongue.setTextureOffset(0, 0).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 0.0F, 2.0F, 0.0F, false);
        this.left_arm = new AdvancedModelBox(this, "left_arm");
        this.left_arm.setRotationPoint(3.5F, 0.0F, -3.0F);
        this.body.addChild(this.left_arm);
        this.left_arm.setTextureOffset(0, 15).addBox(-3.0F, -0.01F, -2.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);
        this.right_arm = new AdvancedModelBox(this, "right_arm");
        this.right_arm.setRotationPoint(-3.5F, 0.0F, -3.0F);
        this.body.addChild(this.right_arm);
        this.right_arm.setTextureOffset(0, 15).addBox(-1.0F, -0.01F, -2.0F, 4.0F, 3.0F, 4.0F, 0.0F, true);
        this.left_leg = new AdvancedModelBox(this, "left_leg");
        this.left_leg.setRotationPoint(2.5F, 1.25F, 2.25F);
        this.body.addChild(this.left_leg);
        this.left_leg.setTextureOffset(15, 21).addBox(-0.5F, -1.25F, -0.25F, 2.0F, 3.0F, 2.0F, 0.0F, false);
        this.left_leg.setTextureOffset(17, 15).addBox(-0.5F, 1.749F, -2.25F, 4.0F, 0.0F, 4.0F, 0.0F, false);
        this.right_leg = new AdvancedModelBox(this, "right_leg");
        this.right_leg.setRotationPoint(-2.5F, 1.25F, 2.25F);
        this.body.addChild(this.right_leg);
        this.right_leg.setTextureOffset(15, 21).addBox(-1.5F, -1.25F, -0.25F, 2.0F, 3.0F, 2.0F, 0.0F, true);
        this.right_leg.setTextureOffset(17, 15).addBox(-3.5F, 1.749F, -2.25F, 4.0F, 0.0F, 4.0F, 0.0F, true);
        this.left_eye = new AdvancedModelBox(this, "left_eye");
        this.left_eye.setRotationPoint(1.5F, -3.0F, -2.5F);
        this.body.addChild(this.left_eye);
        this.left_eye.setTextureOffset(0, 23).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 1.0F, 3.0F, 0.0F, false);
        this.right_eye = new AdvancedModelBox(this, "right_eye");
        this.right_eye.setRotationPoint(-1.5F, -3.0F, -2.5F);
        this.body.addChild(this.right_eye);
        this.right_eye.setTextureOffset(0, 23).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 1.0F, 3.0F, 0.0F, true);
        this.updateDefaultPose();
    }

    @Override
    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of(this.root);
    }

    @Override
    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of(this.root, this.body, this.right_eye, this.left_eye, this.right_arm, this.left_arm, this.right_leg, this.left_leg, this.tongue);
    }

    @Override
    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.f_102610_) {
            float f = 1.25F;
            this.right_eye.setScale(f, f, f);
            this.left_eye.setScale(f, f, f);
            this.right_eye.setShouldScaleChildren(true);
            this.left_eye.setShouldScaleChildren(true);
            matrixStackIn.pushPose();
            matrixStackIn.scale(0.5F, 0.5F, 0.5F);
            matrixStackIn.translate(0.0, 1.5, 0.125);
            this.parts().forEach(p_228292_8_ -> p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.popPose();
            this.right_eye.setScale(1.0F, 1.0F, 1.0F);
            this.left_eye.setScale(1.0F, 1.0F, 1.0F);
        } else {
            matrixStackIn.pushPose();
            this.parts().forEach(p_228290_8_ -> p_228290_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
            matrixStackIn.popPose();
        }
    }

    public void setupAnim(EntityRainFrog entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        float walkSpeed = this.f_102610_ ? 1.0F : 2.3F;
        float walkDegree = 1.0F;
        float digSpeed = 0.8F;
        float digDegree = 0.5F;
        float danceSpeed = 0.8F;
        float danceDegree = 0.5F;
        float partialTick = ageInTicks - (float) entity.f_19797_;
        float burrowProgress = entity.prevBurrowProgress + (entity.burrowProgress - entity.prevBurrowProgress) * partialTick;
        float danceProgress = entity.prevDanceProgress + (entity.danceProgress - entity.prevDanceProgress) * partialTick;
        float attackProgress = entity.prevAttackProgress + (entity.attackProgress - entity.prevAttackProgress) * partialTick;
        float stanceProgress = entity.prevStanceProgress + (entity.stanceProgress - entity.prevStanceProgress) * partialTick;
        float blinkAmount = Math.max(0.0F, ((float) Math.sin((double) (ageInTicks * 0.1F)) - 0.5F) * 2.0F) * (5.0F - stanceProgress) * 0.2F;
        float digAmount = Mth.clamp((float) Math.sin((double) burrowProgress * Math.PI / 5.0), 0.0F, 1.0F);
        this.progressPositionPrev(this.right_eye, blinkAmount, 0.0F, 0.9F, 0.1F, 1.0F);
        this.progressPositionPrev(this.left_eye, blinkAmount, 0.0F, 0.9F, 0.1F, 1.0F);
        this.progressPositionPrev(this.body, danceProgress, 0.0F, -2.0F, 0.0F, 5.0F);
        this.progressPositionPrev(this.right_leg, danceProgress, 0.0F, 0.7F, 0.0F, 5.0F);
        this.progressPositionPrev(this.left_leg, danceProgress, 0.0F, 0.7F, 0.0F, 5.0F);
        this.progressPositionPrev(this.body, burrowProgress, 0.0F, 5.0F, 0.0F, 5.0F);
        this.progressPositionPrev(this.tongue, 5.0F - attackProgress, 0.0F, 0.0F, 3.0F, 5.0F);
        this.progressPositionPrev(this.body, attackProgress, 0.0F, 0.0F, -2.0F, 5.0F);
        this.progressRotationPrev(this.body, attackProgress, Maths.rad(-10.0), 0.0F, 0.0F, 5.0F);
        this.progressRotationPrev(this.right_leg, attackProgress, Maths.rad(10.0), 0.0F, 0.0F, 5.0F);
        this.progressRotationPrev(this.left_leg, attackProgress, Maths.rad(10.0), 0.0F, 0.0F, 5.0F);
        this.progressRotationPrev(this.left_arm, attackProgress, Maths.rad(10.0), 0.0F, 0.0F, 5.0F);
        this.progressRotationPrev(this.right_arm, attackProgress, Maths.rad(10.0), 0.0F, 0.0F, 5.0F);
        this.progressPositionPrev(this.left_leg, attackProgress, 0.0F, -1.0F, 0.0F, 5.0F);
        this.progressPositionPrev(this.right_leg, attackProgress, 0.0F, -1.0F, 0.0F, 5.0F);
        this.progressPositionPrev(this.body, stanceProgress, 0.0F, -2.0F, 0.0F, 5.0F);
        this.progressPositionPrev(this.left_leg, stanceProgress, 0.0F, 2.0F, 0.0F, 5.0F);
        this.progressPositionPrev(this.right_leg, stanceProgress, 0.0F, 2.0F, 0.0F, 5.0F);
        this.progressPositionPrev(this.left_arm, stanceProgress, 0.0F, 2.0F, 0.0F, 5.0F);
        this.progressPositionPrev(this.right_arm, stanceProgress, 0.0F, 2.0F, 0.0F, 5.0F);
        this.progressPositionPrev(this.left_eye, stanceProgress, 0.0F, -1.0F, 0.0F, 5.0F);
        this.progressPositionPrev(this.right_eye, stanceProgress, 0.0F, -1.0F, 0.0F, 5.0F);
        this.body.setScale(1.0F + stanceProgress * 0.025F, 1.0F + stanceProgress * 0.075F, 1.0F + stanceProgress * 0.025F);
        this.swing(this.body, digSpeed, digDegree * 0.5F, false, 3.0F, 0.0F, ageInTicks, digAmount);
        this.walk(this.right_arm, digSpeed, digDegree, false, -1.5F, -0.2F, ageInTicks, digAmount);
        this.walk(this.left_arm, digSpeed, digDegree, false, -1.5F, -0.2F, ageInTicks, digAmount);
        this.walk(this.right_leg, digSpeed, digDegree, false, -1.5F, 0.2F, ageInTicks, digAmount);
        this.walk(this.left_leg, digSpeed, digDegree, false, -1.5F, 0.2F, ageInTicks, digAmount);
        this.flap(this.body, walkSpeed, walkDegree * 0.35F, false, 0.0F, 0.0F, limbSwing, limbSwingAmount);
        this.swing(this.body, walkSpeed, walkDegree * 0.35F, false, 1.0F, 0.0F, limbSwing, limbSwingAmount);
        this.walk(this.left_arm, walkSpeed, walkDegree * 1.2F, false, -2.5F, -0.2F, limbSwing, limbSwingAmount);
        this.walk(this.right_arm, walkSpeed, walkDegree * 1.2F, true, -2.5F, 0.2F, limbSwing, limbSwingAmount);
        this.walk(this.right_leg, walkSpeed, walkDegree, false, -2.5F, 0.3F, limbSwing, limbSwingAmount);
        this.walk(this.left_leg, walkSpeed, walkDegree, true, -2.5F, -0.3F, limbSwing, limbSwingAmount);
        float leftLegS = (float) (Math.sin((double) (limbSwing * walkSpeed) - 2.5) * (double) limbSwingAmount * (double) walkDegree - (double) (limbSwingAmount * walkDegree));
        float rightLegS = (float) (Math.sin(-((double) (limbSwing * walkSpeed)) + 2.5) * (double) limbSwingAmount * (double) walkDegree - (double) (limbSwingAmount * walkDegree));
        this.left_leg.rotationPointY += 1.5F * leftLegS;
        this.right_leg.rotationPointY += 1.5F * rightLegS;
        this.left_leg.rotationPointZ -= 2.0F * leftLegS;
        this.right_leg.rotationPointZ -= 2.0F * rightLegS;
        this.right_arm.rotationPointY += 1.5F * leftLegS;
        this.left_arm.rotationPointY += 1.5F * rightLegS;
        this.right_arm.rotationPointZ += 1.0F * leftLegS;
        this.left_arm.rotationPointZ += 1.0F * rightLegS;
        this.swing(this.body, danceSpeed, danceDegree * 0.5F, false, 1.0F, 0.0F, ageInTicks, danceProgress * 0.2F);
        this.walk(this.body, danceSpeed, danceDegree * 0.5F, false, 3.0F, -0.4F, ageInTicks, danceProgress * 0.2F);
        this.flap(this.right_arm, danceSpeed, danceDegree, false, 0.0F, 0.3F, ageInTicks, danceProgress * 0.2F);
        this.flap(this.left_arm, danceSpeed, danceDegree, true, 0.0F, 0.3F, ageInTicks, danceProgress * 0.2F);
        this.left_leg.rotateAngleX = this.left_leg.rotateAngleX - 1.0F * this.body.rotateAngleX;
        this.left_leg.rotateAngleY = this.left_leg.rotateAngleY - 1.0F * this.body.rotateAngleY;
        this.left_leg.rotateAngleZ = this.left_leg.rotateAngleZ - 1.0F * this.body.rotateAngleZ;
        this.right_leg.rotateAngleX = this.right_leg.rotateAngleX - 1.0F * this.body.rotateAngleX;
        this.right_leg.rotateAngleY = this.right_leg.rotateAngleY - 1.0F * this.body.rotateAngleY;
        this.right_leg.rotateAngleZ = this.right_leg.rotateAngleZ - 1.0F * this.body.rotateAngleZ;
    }
}