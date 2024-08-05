package com.github.alexmodguy.alexscaves.client.model;

import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.entity.Entity;

public class NuclearFurnaceModel extends AdvancedEntityModel<Entity> {

    private final AdvancedModelBox root;

    private final AdvancedModelBox main;

    private final AdvancedModelBox waste;

    public NuclearFurnaceModel() {
        this.texWidth = 256;
        this.texHeight = 256;
        this.root = new AdvancedModelBox(this);
        this.root.setRotationPoint(-16.0F, 24.0F, 16.0F);
        this.main = new AdvancedModelBox(this);
        this.main.setRotationPoint(0.0F, -14.5714F, 0.0F);
        this.root.addChild(this.main);
        this.main.setTextureOffset(0, 41).addBox(-16.0F, -17.4286F, -16.0F, 32.0F, 32.0F, 9.0F, 0.0F, false);
        this.main.setTextureOffset(0, 0).addBox(-16.0F, -17.4286F, 7.0F, 32.0F, 32.0F, 9.0F, 0.0F, false);
        this.main.setTextureOffset(0, 82).addBox(7.0F, -17.4286F, -7.0F, 9.0F, 32.0F, 14.0F, 0.0F, false);
        this.main.setTextureOffset(68, 68).addBox(-16.0F, -17.4286F, -7.0F, 9.0F, 32.0F, 14.0F, 0.0F, false);
        this.main.setTextureOffset(82, 0).addBox(-7.0F, 0.5714F, -7.0F, 14.0F, 14.0F, 14.0F, 0.0F, false);
        this.main.setTextureOffset(82, 17).addBox(12.0F, -8.4286F, 5.0F, 4.0F, 15.0F, 4.0F, 0.0F, false);
        this.main.setTextureOffset(82, 17).addBox(-16.0F, -8.4286F, -9.0F, 4.0F, 15.0F, 4.0F, 0.0F, true);
        this.waste = new AdvancedModelBox(this);
        this.waste.setRotationPoint(0.0F, -0.9286F, 0.0F);
        this.main.addChild(this.waste);
        this.waste.setTextureOffset(98, 17).addBox(12.0F, -7.5F, 5.0F, 3.0F, 15.0F, 4.0F, 0.0F, true);
        this.waste.setTextureOffset(98, 17).addBox(-15.0F, -7.5F, -9.0F, 3.0F, 15.0F, 4.0F, 0.0F, false);
        this.updateDefaultPose();
    }

    @Override
    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of(this.root);
    }

    @Override
    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of(this.root, this.main, this.waste);
    }

    @Override
    public void setupAnim(Entity entity, float rot, float criticality, float ageInTicks, float waste, float unused) {
        this.resetToDefaultPose();
        this.main.rotateAngleY = (float) Math.toRadians((double) rot);
        this.waste.rotationPointY += (1.0F - waste) * 8.0F;
        this.waste.scaleY = waste;
        if (criticality >= 2.0F) {
            float f = criticality >= 3.0F ? 1.0F : 0.1F;
            this.main.walk(2.0F, 0.1F, false, 3.0F, 0.0F, ageInTicks, f);
            this.main.swing(3.0F, 0.1F, false, 2.0F, 0.0F, ageInTicks, f);
            this.main.flap(4.0F, 0.1F, false, 1.0F, 0.0F, ageInTicks, f);
        }
    }
}