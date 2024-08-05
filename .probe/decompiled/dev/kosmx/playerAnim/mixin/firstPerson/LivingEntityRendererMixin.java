package dev.kosmx.playerAnim.mixin.firstPerson;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.kosmx.playerAnim.api.firstPerson.FirstPersonMode;
import java.util.List;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.PlayerItemInHandLayer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = { LivingEntityRenderer.class }, priority = 2000)
public class LivingEntityRendererMixin {

    @Shadow
    @Final
    protected List<Object> layers;

    @Redirect(method = { "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V" }, at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/entity/LivingEntityRenderer;layers:Ljava/util/List;", opcode = 180))
    private List<Object> filterLayers(LivingEntityRenderer instance, LivingEntity entity, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        return entity instanceof LocalPlayer && FirstPersonMode.isFirstPersonPass() ? this.layers.stream().filter(layer -> layer instanceof PlayerItemInHandLayer).toList() : this.layers;
    }
}