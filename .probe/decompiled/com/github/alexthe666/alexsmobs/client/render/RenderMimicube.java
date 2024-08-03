package com.github.alexthe666.alexsmobs.client.render;

import com.github.alexthe666.alexsmobs.client.model.ModelMimicube;
import com.github.alexthe666.alexsmobs.client.render.layer.LayerMimicubeHeldItem;
import com.github.alexthe666.alexsmobs.client.render.layer.LayerMimicubeHelmet;
import com.github.alexthe666.alexsmobs.client.render.layer.LayerMimicubeTexture;
import com.github.alexthe666.alexsmobs.entity.EntityMimicube;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderMimicube extends MobRenderer<EntityMimicube, ModelMimicube> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("alexsmobs:textures/entity/mimicube.png");

    public RenderMimicube(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ModelMimicube(), 0.5F);
        this.m_115326_(new LayerMimicubeHelmet(this, renderManagerIn));
        this.m_115326_(new LayerMimicubeHeldItem(this));
        this.m_115326_(new LayerMimicubeTexture(this));
    }

    protected void scale(EntityMimicube entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }

    public ResourceLocation getTextureLocation(EntityMimicube entity) {
        return TEXTURE;
    }
}