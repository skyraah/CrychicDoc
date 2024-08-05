package net.mehvahdjukaar.supplementaries.client.renderers.entities.funny;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.mehvahdjukaar.supplementaries.reg.ClientRegistry;
import net.mehvahdjukaar.supplementaries.reg.ModTextures;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.ArrowLayer;
import net.minecraft.client.renderer.entity.layers.BeeStingerLayer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.PlayerItemInHandLayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.Score;
import net.minecraft.world.scores.Scoreboard;

public class PickleRenderer extends LivingEntityRenderer<AbstractClientPlayer, PickleModel<AbstractClientPlayer>> {

    public static PickleRenderer INSTANCE = null;

    protected float axisFacing = 0.0F;

    protected boolean wasCrouching = false;

    public PickleRenderer(EntityRendererProvider.Context context) {
        super(context, new PickleModel<>(context.bakeLayer(ClientRegistry.PICKLE_MODEL)), 0.0125F);
        this.f_114478_ = 0.0F;
        this.f_114477_ = 0.0F;
        this.m_115326_(new PlayerItemInHandLayer<>(this, context.getItemInHandRenderer()));
        this.m_115326_(new PickleModel.PickleArmor<>(this, new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), context.getModelManager()));
        this.m_115326_(new ArrowLayer<>(context, this));
        this.m_115326_(new PickleModel.PickleElytra<>(this, context.getModelSet()));
        this.m_115326_(new BeeStingerLayer<>(this));
        this.m_115326_(new CustomHeadLayer<>(this, context.getModelSet(), context.getItemInHandRenderer()));
    }

    public ResourceLocation getTextureLocation(AbstractClientPlayer player) {
        return ModTextures.SEA_PICKLE_RICK;
    }

    protected boolean shouldShowName(AbstractClientPlayer player) {
        return !player.m_6047_() && super.shouldShowName(player);
    }

    protected void scale(AbstractClientPlayer player, PoseStack stack, float partialTickTime) {
        stack.scale(0.5F, 0.5F, 0.5F);
    }

    public void render(AbstractClientPlayer player, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int packedLight) {
        this.setModelProperties(player);
        if (this.wasCrouching) {
            float f = (Mth.rotLerp(partialTicks, player.f_20884_, player.f_20883_) + this.axisFacing) % 360.0F;
            matrixStack.mulPose(Axis.YP.rotationDegrees(f));
        }
        super.render(player, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }

    public Vec3 getRenderOffset(AbstractClientPlayer player, float partialTicks) {
        return new Vec3(0.0, -0.25, 0.0);
    }

    private void setModelProperties(AbstractClientPlayer player) {
        PlayerModel<AbstractClientPlayer> pickleModel = (PlayerModel<AbstractClientPlayer>) this.m_7200_();
        pickleModel.setAllVisible(false);
        boolean c = player.m_6047_();
        pickleModel.f_102810_.visible = true;
        pickleModel.f_102812_.visible = !c;
        pickleModel.f_102811_.visible = !c;
        pickleModel.f_102814_.visible = !c;
        pickleModel.f_102813_.visible = !c;
        pickleModel.f_102808_.visible = !c;
        pickleModel.f_102809_.visible = !c;
        if (this.wasCrouching != c && c) {
            this.axisFacing = -player.m_6350_().toYRot();
        }
        this.wasCrouching = c;
        HumanoidModel.ArmPose poseRightArm = getArmPose(player, InteractionHand.MAIN_HAND);
        HumanoidModel.ArmPose poseLeftArm = getArmPose(player, InteractionHand.OFF_HAND);
        if (poseRightArm.isTwoHanded()) {
            poseLeftArm = player.m_21206_().isEmpty() ? HumanoidModel.ArmPose.EMPTY : HumanoidModel.ArmPose.ITEM;
        }
        if (player.m_5737_() == HumanoidArm.RIGHT) {
            pickleModel.f_102816_ = poseRightArm;
            pickleModel.f_102815_ = poseLeftArm;
        } else {
            pickleModel.f_102816_ = poseLeftArm;
            pickleModel.f_102815_ = poseRightArm;
        }
    }

    protected static HumanoidModel.ArmPose getArmPose(AbstractClientPlayer player, InteractionHand hand) {
        ItemStack itemstack = player.m_21120_(hand);
        if (itemstack.isEmpty()) {
            return HumanoidModel.ArmPose.EMPTY;
        } else {
            if (player.m_7655_() == hand && player.m_21212_() > 0) {
                UseAnim useAnimation = itemstack.getUseAnimation();
                if (useAnimation == UseAnim.BLOCK) {
                    return HumanoidModel.ArmPose.BLOCK;
                }
                if (useAnimation == UseAnim.BOW) {
                    return HumanoidModel.ArmPose.BOW_AND_ARROW;
                }
                if (useAnimation == UseAnim.SPEAR) {
                    return HumanoidModel.ArmPose.THROW_SPEAR;
                }
                if (useAnimation == UseAnim.CROSSBOW && hand == player.m_7655_()) {
                    return HumanoidModel.ArmPose.CROSSBOW_CHARGE;
                }
                if (useAnimation == UseAnim.SPYGLASS) {
                    return HumanoidModel.ArmPose.SPYGLASS;
                }
            } else if (!player.f_20911_ && itemstack.getItem() == Items.CROSSBOW && CrossbowItem.isCharged(itemstack)) {
                return HumanoidModel.ArmPose.CROSSBOW_HOLD;
            }
            return HumanoidModel.ArmPose.ITEM;
        }
    }

    protected void renderNameTag(AbstractClientPlayer player, Component name, PoseStack matrixStack, MultiBufferSource buffer, int p_225629_5_) {
        double d0 = this.f_114476_.distanceToSqr(player);
        matrixStack.pushPose();
        if (d0 < 100.0) {
            Scoreboard scoreboard = player.m_36329_();
            Objective objective = scoreboard.getDisplayObjective(2);
            if (objective != null) {
                Score score = scoreboard.getOrCreatePlayerScore(player.m_6302_(), objective);
                super.m_7649_(player, Component.literal(Integer.toString(score.getScore())).append(" ").append(objective.getDisplayName()), matrixStack, buffer, p_225629_5_);
                matrixStack.translate(0.0, 0.25875F, 0.0);
            }
        }
        super.m_7649_(player, name, matrixStack, buffer, p_225629_5_);
        matrixStack.popPose();
    }

    protected void setupRotations(AbstractClientPlayer player, PoseStack matrixStack, float p_225621_3_, float p_225621_4_, float partialTicks) {
        float f = player.m_20998_(partialTicks);
        if (player.m_21255_()) {
            super.setupRotations(player, matrixStack, p_225621_3_, p_225621_4_, partialTicks);
            float f1 = (float) player.m_21256_() + partialTicks;
            float inclination = Mth.clamp(f1 * f1 / 100.0F, 0.0F, 1.0F);
            if (!player.m_21209_()) {
                matrixStack.mulPose(Axis.XP.rotationDegrees(inclination * (-90.0F - player.m_146909_())));
            }
            Vec3 vector3d = player.m_20252_(partialTicks);
            Vec3 vector3d1 = player.m_20184_();
            double d0 = vector3d1.horizontalDistanceSqr();
            double d1 = vector3d.horizontalDistanceSqr();
            if (d0 > 0.0 && d1 > 0.0) {
                double d2 = (vector3d1.x * vector3d.x + vector3d1.z * vector3d.z) / Math.sqrt(d0 * d1);
                double d3 = vector3d1.x * vector3d.z - vector3d1.z * vector3d.x;
                matrixStack.mulPose(Axis.YP.rotation((float) (Math.signum(d3) * Math.acos(d2))));
            }
        } else if (f > 0.0F) {
            super.setupRotations(player, matrixStack, p_225621_3_, p_225621_4_, partialTicks);
            float f3 = player.m_20069_() ? -90.0F - player.m_146909_() : -90.0F;
            float f4 = Mth.lerp(f, 0.0F, f3);
            matrixStack.mulPose(Axis.XP.rotationDegrees(f4));
            if (player.m_6067_()) {
                matrixStack.translate(0.0, -0.25, 0.25);
            }
        } else {
            super.setupRotations(player, matrixStack, p_225621_3_, p_225621_4_, partialTicks);
        }
    }
}