package net.minecraft.client.renderer.entity;

import net.minecraft.ChatFormatting;
import net.minecraft.client.model.RabbitModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Rabbit;

public class RabbitRenderer extends MobRenderer<Rabbit, RabbitModel<Rabbit>> {

    private static final ResourceLocation RABBIT_BROWN_LOCATION = new ResourceLocation("textures/entity/rabbit/brown.png");

    private static final ResourceLocation RABBIT_WHITE_LOCATION = new ResourceLocation("textures/entity/rabbit/white.png");

    private static final ResourceLocation RABBIT_BLACK_LOCATION = new ResourceLocation("textures/entity/rabbit/black.png");

    private static final ResourceLocation RABBIT_GOLD_LOCATION = new ResourceLocation("textures/entity/rabbit/gold.png");

    private static final ResourceLocation RABBIT_SALT_LOCATION = new ResourceLocation("textures/entity/rabbit/salt.png");

    private static final ResourceLocation RABBIT_WHITE_SPLOTCHED_LOCATION = new ResourceLocation("textures/entity/rabbit/white_splotched.png");

    private static final ResourceLocation RABBIT_TOAST_LOCATION = new ResourceLocation("textures/entity/rabbit/toast.png");

    private static final ResourceLocation RABBIT_EVIL_LOCATION = new ResourceLocation("textures/entity/rabbit/caerbannog.png");

    public RabbitRenderer(EntityRendererProvider.Context entityRendererProviderContext0) {
        super(entityRendererProviderContext0, new RabbitModel<>(entityRendererProviderContext0.bakeLayer(ModelLayers.RABBIT)), 0.3F);
    }

    public ResourceLocation getTextureLocation(Rabbit rabbit0) {
        String $$1 = ChatFormatting.stripFormatting(rabbit0.m_7755_().getString());
        if ("Toast".equals($$1)) {
            return RABBIT_TOAST_LOCATION;
        } else {
            return switch(rabbit0.getVariant()) {
                case BROWN ->
                    RABBIT_BROWN_LOCATION;
                case WHITE ->
                    RABBIT_WHITE_LOCATION;
                case BLACK ->
                    RABBIT_BLACK_LOCATION;
                case GOLD ->
                    RABBIT_GOLD_LOCATION;
                case SALT ->
                    RABBIT_SALT_LOCATION;
                case WHITE_SPLOTCHED ->
                    RABBIT_WHITE_SPLOTCHED_LOCATION;
                case EVIL ->
                    RABBIT_EVIL_LOCATION;
            };
        }
    }
}