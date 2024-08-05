package mezz.jei.common.gui.elements;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import mezz.jei.common.Constants;
import mezz.jei.common.gui.textures.JeiSpriteUploader;
import mezz.jei.common.util.ImmutableRect2i;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;

public class DrawableNineSliceTexture {

    private final JeiSpriteUploader spriteUploader;

    private final ResourceLocation location;

    private final int width;

    private final int height;

    private final int sliceLeft;

    private final int sliceRight;

    private final int sliceTop;

    private final int sliceBottom;

    public DrawableNineSliceTexture(JeiSpriteUploader spriteUploader, ResourceLocation location, int width, int height, int left, int right, int top, int bottom) {
        this.spriteUploader = spriteUploader;
        this.location = location;
        this.width = width;
        this.height = height;
        this.sliceLeft = left;
        this.sliceRight = right;
        this.sliceTop = top;
        this.sliceBottom = bottom;
    }

    public void draw(GuiGraphics guiGraphics, ImmutableRect2i area) {
        this.draw(guiGraphics, area.getX(), area.getY(), area.getWidth(), area.getHeight());
    }

    public void draw(GuiGraphics guiGraphics, int xOffset, int yOffset, int width, int height) {
        TextureAtlasSprite sprite = this.spriteUploader.getSprite(this.location);
        int leftWidth = this.sliceLeft;
        int rightWidth = this.sliceRight;
        int topHeight = this.sliceTop;
        int bottomHeight = this.sliceBottom;
        int textureWidth = this.width;
        int textureHeight = this.height;
        RenderSystem.setShader(GameRenderer::m_172817_);
        RenderSystem.setShaderTexture(0, Constants.LOCATION_JEI_GUI_TEXTURE_ATLAS);
        float uMin = sprite.getU0();
        float uMax = sprite.getU1();
        float vMin = sprite.getV0();
        float vMax = sprite.getV1();
        float uSize = uMax - uMin;
        float vSize = vMax - vMin;
        float uLeft = uMin + uSize * ((float) leftWidth / (float) textureWidth);
        float uRight = uMax - uSize * ((float) rightWidth / (float) textureWidth);
        float vTop = vMin + vSize * ((float) topHeight / (float) textureHeight);
        float vBottom = vMax - vSize * ((float) bottomHeight / (float) textureHeight);
        Tesselator tessellator = Tesselator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuilder();
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        Matrix4f matrix = guiGraphics.pose().last().pose();
        draw(bufferBuilder, matrix, uMin, (double) vMin, uLeft, vTop, xOffset, yOffset, leftWidth, topHeight);
        draw(bufferBuilder, matrix, uMin, (double) vBottom, uLeft, vMax, xOffset, yOffset + height - bottomHeight, leftWidth, bottomHeight);
        draw(bufferBuilder, matrix, uRight, (double) vMin, uMax, vTop, xOffset + width - rightWidth, yOffset, rightWidth, topHeight);
        draw(bufferBuilder, matrix, uRight, (double) vBottom, uMax, vMax, xOffset + width - rightWidth, yOffset + height - bottomHeight, rightWidth, bottomHeight);
        int middleWidth = textureWidth - leftWidth - rightWidth;
        int middleHeight = textureWidth - topHeight - bottomHeight;
        int tiledMiddleWidth = width - leftWidth - rightWidth;
        int tiledMiddleHeight = height - topHeight - bottomHeight;
        if (tiledMiddleWidth > 0) {
            drawTiled(bufferBuilder, matrix, uLeft, vMin, uRight, vTop, xOffset + leftWidth, yOffset, tiledMiddleWidth, topHeight, middleWidth, topHeight);
            drawTiled(bufferBuilder, matrix, uLeft, vBottom, uRight, vMax, xOffset + leftWidth, yOffset + height - bottomHeight, tiledMiddleWidth, bottomHeight, middleWidth, bottomHeight);
        }
        if (tiledMiddleHeight > 0) {
            drawTiled(bufferBuilder, matrix, uMin, vTop, uLeft, vBottom, xOffset, yOffset + topHeight, leftWidth, tiledMiddleHeight, leftWidth, middleHeight);
            drawTiled(bufferBuilder, matrix, uRight, vTop, uMax, vBottom, xOffset + width - rightWidth, yOffset + topHeight, rightWidth, tiledMiddleHeight, rightWidth, middleHeight);
        }
        if (tiledMiddleHeight > 0 && tiledMiddleWidth > 0) {
            drawTiled(bufferBuilder, matrix, uLeft, vTop, uRight, vBottom, xOffset + leftWidth, yOffset + topHeight, tiledMiddleWidth, tiledMiddleHeight, middleWidth, middleHeight);
        }
        tessellator.end();
    }

    private static void drawTiled(BufferBuilder bufferBuilder, Matrix4f matrix, float uMin, float vMin, float uMax, float vMax, int xOffset, int yOffset, int tiledWidth, int tiledHeight, int width, int height) {
        int xTileCount = tiledWidth / width;
        int xRemainder = tiledWidth - xTileCount * width;
        int yTileCount = tiledHeight / height;
        int yRemainder = tiledHeight - yTileCount * height;
        int yStart = yOffset + tiledHeight;
        float uSize = uMax - uMin;
        float vSize = vMax - vMin;
        for (int xTile = 0; xTile <= xTileCount; xTile++) {
            for (int yTile = 0; yTile <= yTileCount; yTile++) {
                int tileWidth = xTile == xTileCount ? xRemainder : width;
                int tileHeight = yTile == yTileCount ? yRemainder : height;
                int x = xOffset + xTile * width;
                int y = yStart - (yTile + 1) * height;
                if (tileWidth > 0 && tileHeight > 0) {
                    int maskRight = width - tileWidth;
                    int maskTop = height - tileHeight;
                    float uOffset = (float) maskRight / (float) width * uSize;
                    float vOffset = (float) maskTop / (float) height * vSize;
                    draw(bufferBuilder, matrix, uMin, (double) (vMin + vOffset), uMax - uOffset, vMax, x, y + maskTop, tileWidth, tileHeight);
                }
            }
        }
    }

    private static void draw(BufferBuilder bufferBuilder, Matrix4f matrix, float minU, double minV, float maxU, float maxV, int xOffset, int yOffset, int width, int height) {
        bufferBuilder.m_252986_(matrix, (float) xOffset, (float) (yOffset + height), 0.0F).uv(minU, maxV).endVertex();
        bufferBuilder.m_252986_(matrix, (float) (xOffset + width), (float) (yOffset + height), 0.0F).uv(maxU, maxV).endVertex();
        bufferBuilder.m_252986_(matrix, (float) (xOffset + width), (float) yOffset, 0.0F).uv(maxU, (float) minV).endVertex();
        bufferBuilder.m_252986_(matrix, (float) xOffset, (float) yOffset, 0.0F).uv(minU, (float) minV).endVertex();
    }
}