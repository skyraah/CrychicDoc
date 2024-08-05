package com.github.alexmodguy.alexscaves.client.model;

import com.github.alexmodguy.alexscaves.server.entity.item.TephraEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;

public class TephraModel extends AdvancedEntityModel<TephraEntity> {

    private final AdvancedModelBox main;

    public TephraModel() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.main = new AdvancedModelBox(this);
        this.main.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.main.setTextureOffset(0, 0).addBox(-7.0F, -7.0F, -7.0F, 14.0F, 14.0F, 14.0F, 0.0F, false);
        this.updateDefaultPose();
    }

    @Override
    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of(this.main);
    }

    public void setupAnim(TephraEntity tephraEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.resetToDefaultPose();
        this.main.rotateAngleZ += ageInTicks * 0.2F;
        this.main.rotateAngleX += ageInTicks * 0.4F;
    }

    @Override
    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of(this.main);
    }
}