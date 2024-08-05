package net.minecraft.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.CaveSpider;

public class CaveSpiderRenderer extends SpiderRenderer<CaveSpider> {

    private static final ResourceLocation CAVE_SPIDER_LOCATION = new ResourceLocation("textures/entity/spider/cave_spider.png");

    private static final float SCALE = 0.7F;

    public CaveSpiderRenderer(EntityRendererProvider.Context entityRendererProviderContext0) {
        super(entityRendererProviderContext0, ModelLayers.CAVE_SPIDER);
        this.f_114477_ *= 0.7F;
    }

    protected void scale(CaveSpider caveSpider0, PoseStack poseStack1, float float2) {
        poseStack1.scale(0.7F, 0.7F, 0.7F);
    }

    public ResourceLocation getTextureLocation(CaveSpider caveSpider0) {
        return CAVE_SPIDER_LOCATION;
    }
}