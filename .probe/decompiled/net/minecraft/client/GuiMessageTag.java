package net.minecraft.client;

import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public record GuiMessageTag(int f_240386_, @Nullable GuiMessageTag.Icon f_240355_, @Nullable Component f_240381_, @Nullable String f_240342_) {

    private final int indicatorColor;

    @Nullable
    private final GuiMessageTag.Icon icon;

    @Nullable
    private final Component text;

    @Nullable
    private final String logTag;

    private static final Component SYSTEM_TEXT = Component.translatable("chat.tag.system");

    private static final Component SYSTEM_TEXT_SINGLE_PLAYER = Component.translatable("chat.tag.system_single_player");

    private static final Component CHAT_NOT_SECURE_TEXT = Component.translatable("chat.tag.not_secure");

    private static final Component CHAT_MODIFIED_TEXT = Component.translatable("chat.tag.modified");

    private static final int CHAT_NOT_SECURE_INDICATOR_COLOR = 13684944;

    private static final int CHAT_MODIFIED_INDICATOR_COLOR = 6316128;

    private static final GuiMessageTag SYSTEM = new GuiMessageTag(13684944, null, SYSTEM_TEXT, "System");

    private static final GuiMessageTag SYSTEM_SINGLE_PLAYER = new GuiMessageTag(13684944, null, SYSTEM_TEXT_SINGLE_PLAYER, "System");

    private static final GuiMessageTag CHAT_NOT_SECURE = new GuiMessageTag(13684944, null, CHAT_NOT_SECURE_TEXT, "Not Secure");

    static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation("textures/gui/chat_tags.png");

    public GuiMessageTag(int f_240386_, @Nullable GuiMessageTag.Icon f_240355_, @Nullable Component f_240381_, @Nullable String f_240342_) {
        this.indicatorColor = f_240386_;
        this.icon = f_240355_;
        this.text = f_240381_;
        this.logTag = f_240342_;
    }

    public static GuiMessageTag system() {
        return SYSTEM;
    }

    public static GuiMessageTag systemSinglePlayer() {
        return SYSTEM_SINGLE_PLAYER;
    }

    public static GuiMessageTag chatNotSecure() {
        return CHAT_NOT_SECURE;
    }

    public static GuiMessageTag chatModified(String p_242878_) {
        Component $$1 = Component.literal(p_242878_).withStyle(ChatFormatting.GRAY);
        Component $$2 = Component.empty().append(CHAT_MODIFIED_TEXT).append(CommonComponents.NEW_LINE).append($$1);
        return new GuiMessageTag(6316128, GuiMessageTag.Icon.CHAT_MODIFIED, $$2, "Modified");
    }

    public static enum Icon {

        CHAT_MODIFIED(0, 0, 9, 9);

        public final int u;

        public final int v;

        public final int width;

        public final int height;

        private Icon(int p_240599_, int p_240544_, int p_240607_, int p_240531_) {
            this.u = p_240599_;
            this.v = p_240544_;
            this.width = p_240607_;
            this.height = p_240531_;
        }

        public void draw(GuiGraphics p_282284_, int p_282597_, int p_283579_) {
            p_282284_.blit(GuiMessageTag.TEXTURE_LOCATION, p_282597_, p_283579_, (float) this.u, (float) this.v, this.width, this.height, 32, 32);
        }
    }
}