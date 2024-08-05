package snownee.jade.mixin;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.font.glyphs.BakedGlyph;
import net.minecraft.util.Mth;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import snownee.jade.gui.JadeFont;

@Mixin({ Font.class })
public class FontMixin implements JadeFont {

    private float glint1 = Float.NaN;

    private float glint2 = Float.NaN;

    private float glint1Strength = 1.0F;

    private float glint2Strength = 1.0F;

    @Inject(method = { "renderChar" }, at = { @At("HEAD") }, cancellable = true)
    private void jade$renderChar(BakedGlyph bakedGlyph, boolean bl, boolean bl2, float f, float x, float y, Matrix4f matrix4f, VertexConsumer vertexConsumer, float r, float g, float b, float a, int m, CallbackInfo ci) {
        if (!Float.isNaN(this.glint1)) {
            float dist = Math.abs(x - this.glint1);
            float localGlint1 = 0.65F + Mth.clamp(1.0F - dist / 20.0F, 0.0F, 1.0F) * 0.35F * this.glint1Strength;
            dist = Math.abs(x - this.glint2);
            float localGlint2 = 0.65F + Mth.clamp(1.0F - dist / 20.0F, 0.0F, 1.0F) * 0.35F * this.glint2Strength;
            a = Math.max(localGlint1, localGlint2) * a;
            bakedGlyph.render(bl2, x, y, matrix4f, vertexConsumer, r, g, b, a, m);
            if (bl) {
                bakedGlyph.render(bl2, x + f, y, matrix4f, vertexConsumer, r, g, b, a, m);
            }
            ci.cancel();
        }
    }

    @Override
    public void jade$setGlint(float glint1, float glint2) {
        this.glint1 = glint1;
        this.glint2 = glint2;
    }

    @Override
    public void jade$setGlintStrength(float glint1Strength, float glint2Strength) {
        this.glint1Strength = glint1Strength;
        this.glint2Strength = glint2Strength;
    }
}