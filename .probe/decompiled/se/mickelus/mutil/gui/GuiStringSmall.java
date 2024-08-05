package se.mickelus.mutil.gui;

import net.minecraft.client.gui.GuiGraphics;
import se.mickelus.mutil.gui.animation.KeyframeAnimation;

public class GuiStringSmall extends GuiString {

    public GuiStringSmall(int x, int y, String string) {
        super(x * 2, y * 2, string);
    }

    public GuiStringSmall(int x, int y, String string, int color) {
        super(x * 2, y * 2, string, color);
    }

    public GuiStringSmall(int x, int y, String string, GuiAttachment attachment) {
        super(x * 2, y * 2, string, attachment);
    }

    public GuiStringSmall(int x, int y, String string, int color, GuiAttachment attachment) {
        super(x * 2, y * 2, string, color, attachment);
    }

    @Override
    public void setX(int x) {
        super.setX(x * 2);
    }

    @Override
    public void setY(int y) {
        super.setY(y * 2);
    }

    @Override
    public int getX() {
        return super.getX() / 2;
    }

    @Override
    public int getY() {
        return super.getY() / 2;
    }

    @Override
    public int getWidth() {
        return this.width / 2;
    }

    @Override
    public int getHeight() {
        return this.height / 2;
    }

    @Override
    public void draw(GuiGraphics graphics, int refX, int refY, int screenWidth, int screenHeight, int mouseX, int mouseY, float opacity) {
        this.activeAnimations.removeIf(keyframeAnimation -> !keyframeAnimation.isActive());
        this.activeAnimations.forEach(KeyframeAnimation::preDraw);
        graphics.pose().pushPose();
        graphics.pose().scale(0.5F, 0.5F, 0.5F);
        this.drawString(graphics, this.string, refX * 2 + this.x, refY * 2 + this.y, this.color, opacity * this.getOpacity(), this.drawShadow);
        graphics.pose().popPose();
    }
}