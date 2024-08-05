package dev.ftb.mods.ftbteams.client.gui;

import com.mojang.authlib.GameProfile;
import dev.ftb.mods.ftblibrary.icon.Color4I;
import dev.ftb.mods.ftblibrary.icon.FaceIcon;
import dev.ftb.mods.ftblibrary.ui.NordButton;
import dev.ftb.mods.ftblibrary.ui.Panel;
import dev.ftb.mods.ftblibrary.ui.Theme;
import dev.ftb.mods.ftblibrary.ui.input.MouseButton;
import dev.ftb.mods.ftblibrary.ui.misc.NordColors;
import dev.ftb.mods.ftbteams.api.client.KnownClientPlayer;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;

public class InvitedButton extends NordButton {

    public final InvitationSetup screen;

    public final KnownClientPlayer player;

    InvitedButton(Panel panel, InvitationSetup setup, KnownClientPlayer knownClientPlayer) {
        super(panel, checkbox(setup.isInvited(knownClientPlayer.profile())).append(" " + knownClientPlayer.name()), FaceIcon.getFace(knownClientPlayer.profile()));
        this.screen = setup;
        this.player = knownClientPlayer;
        if (!this.player.online()) {
            this.title = this.title.copy().withStyle(Style.EMPTY.withColor(TextColor.fromRgb(NordColors.POLAR_NIGHT_0.rgb())));
        }
    }

    private static MutableComponent checkbox(boolean checked) {
        return checked ? Component.literal("☑").withStyle(ChatFormatting.GREEN) : Component.literal("☐");
    }

    @Override
    public void drawIcon(GuiGraphics graphics, Theme theme, int x, int y, int w, int h) {
        super.drawIcon(graphics, theme, x, y, w, h);
        if (this.player.online()) {
            graphics.pose().pushPose();
            graphics.pose().translate((double) (x + w) - 1.5, (double) y - 0.5, 0.0);
            Color4I.GREEN.draw(graphics, 0, 0, 2, 2);
            graphics.pose().popPose();
        }
    }

    @Override
    public void onClicked(MouseButton button) {
        if (this.player.online()) {
            GameProfile profile = this.player.profile();
            boolean invited = this.screen.isInvited(profile);
            this.screen.setInvited(profile, !invited);
            this.title = checkbox(!invited).append(" " + this.player.name());
        }
    }
}