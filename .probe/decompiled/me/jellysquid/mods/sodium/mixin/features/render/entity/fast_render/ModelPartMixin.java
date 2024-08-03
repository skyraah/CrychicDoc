package me.jellysquid.mods.sodium.mixin.features.render.entity.fast_render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import java.util.List;
import java.util.Map;
import me.jellysquid.mods.sodium.client.model.ModelCuboidAccessor;
import me.jellysquid.mods.sodium.client.render.immediate.model.EntityRenderer;
import me.jellysquid.mods.sodium.client.render.immediate.model.ModelCuboid;
import me.jellysquid.mods.sodium.client.render.immediate.model.ModelPartData;
import net.caffeinemc.mods.sodium.api.math.MatrixHelper;
import net.caffeinemc.mods.sodium.api.util.ColorABGR;
import net.caffeinemc.mods.sodium.api.vertex.buffer.VertexBufferWriter;
import net.minecraft.client.model.geom.ModelPart;
import org.embeddedt.embeddium.render.matrix_stack.CachingPoseStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = { ModelPart.class }, priority = 1500)
public class ModelPartMixin implements ModelPartData {

    @Shadow
    public float x;

    @Shadow
    public float y;

    @Shadow
    public float z;

    @Shadow
    public float xScale;

    @Shadow
    public float yScale;

    @Shadow
    public float zScale;

    @Shadow
    public float yRot;

    @Shadow
    public float xRot;

    @Shadow
    public float zRot;

    @Shadow
    public boolean visible;

    @Shadow
    public boolean skipDraw;

    @Mutable
    @Shadow
    @Final
    private List<ModelPart.Cube> cubes;

    @Unique
    private ModelPart[] sodium$children;

    @Unique
    private ModelCuboid[] sodium$cuboids;

    @Inject(method = { "<init>" }, at = { @At("RETURN") })
    private void onInit(List<ModelPart.Cube> cuboids, Map<String, ModelPart> children, CallbackInfo ci) {
        ModelCuboid[] copies = new ModelCuboid[cuboids.size()];
        for (int i = 0; i < cuboids.size(); i++) {
            ModelCuboidAccessor accessor = (ModelCuboidAccessor) cuboids.get(i);
            copies[i] = accessor.sodium$copy();
        }
        this.sodium$cuboids = copies;
        this.sodium$children = (ModelPart[]) children.values().toArray(ModelPart[]::new);
    }

    @Redirect(method = { "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;IIFFFF)V" }, at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;pushPose()V"))
    private void enableCachingBeforePush(PoseStack stack) {
        ((CachingPoseStack) stack).embeddium$setCachingEnabled(true);
        stack.pushPose();
    }

    @Redirect(method = { "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;IIFFFF)V" }, at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;popPose()V"))
    private void disableCachingAfterPop(PoseStack stack) {
        stack.popPose();
        ((CachingPoseStack) stack).embeddium$setCachingEnabled(false);
    }

    @Inject(method = { "compile" }, at = { @At("HEAD") }, cancellable = true)
    private void onRender(PoseStack.Pose matrixPose, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha, CallbackInfo ci) {
        VertexBufferWriter writer = VertexBufferWriter.tryOf(vertices);
        if (writer != null) {
            ci.cancel();
            EntityRenderer.prepareNormals(matrixPose);
            List<ModelPart.Cube> cubes = this.cubes;
            int packedColor = ColorABGR.pack(red, green, blue, alpha);
            for (int i = 0; i < cubes.size(); i++) {
                ModelPart.Cube cube = (ModelPart.Cube) cubes.get(i);
                ModelCuboid simpleCuboid = ((ModelCuboidAccessor) cube).embeddium$getSimpleCuboid();
                if (simpleCuboid != null) {
                    EntityRenderer.renderCuboidFast(matrixPose, writer, simpleCuboid, light, overlay, packedColor);
                } else {
                    cube.compile(matrixPose, vertices, light, overlay, red, green, blue, alpha);
                }
            }
        }
    }

    @Overwrite
    public void translateAndRotate(PoseStack matrixStack) {
        if (this.x != 0.0F || this.y != 0.0F || this.z != 0.0F) {
            matrixStack.translate(this.x * 0.0625F, this.y * 0.0625F, this.z * 0.0625F);
        }
        if (this.xRot != 0.0F || this.yRot != 0.0F || this.zRot != 0.0F) {
            MatrixHelper.rotateZYX(matrixStack.last(), this.zRot, this.yRot, this.xRot);
        }
        if (this.xScale != 1.0F || this.yScale != 1.0F || this.zScale != 1.0F) {
            matrixStack.scale(this.xScale, this.yScale, this.zScale);
        }
    }

    @Override
    public ModelCuboid[] getCuboids() {
        return this.sodium$cuboids;
    }

    @Override
    public boolean isVisible() {
        return this.visible;
    }

    @Override
    public boolean isHidden() {
        return this.skipDraw;
    }

    @Override
    public ModelPart[] getChildren() {
        return this.sodium$children;
    }
}