package io.github.lightman314.lightmanscurrency.client.gui.screen.inventory.traderstorage.settings.core;

import io.github.lightman314.lightmanscurrency.LCText;
import io.github.lightman314.lightmanscurrency.api.misc.EasyText;
import io.github.lightman314.lightmanscurrency.api.misc.client.rendering.EasyGuiGraphics;
import io.github.lightman314.lightmanscurrency.api.misc.player.OwnerData;
import io.github.lightman314.lightmanscurrency.api.network.LazyPacketData;
import io.github.lightman314.lightmanscurrency.api.ownership.Owner;
import io.github.lightman314.lightmanscurrency.api.traders.TraderData;
import io.github.lightman314.lightmanscurrency.client.gui.screen.inventory.traderstorage.settings.SettingsSubTab;
import io.github.lightman314.lightmanscurrency.client.gui.screen.inventory.traderstorage.settings.TraderSettingsClientTab;
import io.github.lightman314.lightmanscurrency.client.gui.widget.OwnerSelectionWidget;
import io.github.lightman314.lightmanscurrency.client.gui.widget.button.icon.IconButton;
import io.github.lightman314.lightmanscurrency.client.gui.widget.button.icon.IconData;
import io.github.lightman314.lightmanscurrency.client.gui.widget.easy.EasyAddonHelper;
import io.github.lightman314.lightmanscurrency.client.gui.widget.easy.EasyButton;
import io.github.lightman314.lightmanscurrency.client.gui.widget.easy.EasyTextButton;
import io.github.lightman314.lightmanscurrency.client.util.IconAndButtonUtil;
import io.github.lightman314.lightmanscurrency.client.util.ScreenArea;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Items;

public class OwnershipTab extends SettingsSubTab {

    OwnerSelectionWidget ownerSelectionWidget;

    private boolean manualSelectionMode = false;

    EditBox playerOwnerInput;

    EasyButton setPlayerButton;

    EasyButton buttonToggleInputMode;

    public OwnershipTab(@Nonnull TraderSettingsClientTab parent) {
        super(parent);
    }

    @Nonnull
    @Override
    public IconData getIcon() {
        return IconAndButtonUtil.ICON_ALEX_HEAD;
    }

    public MutableComponent getTooltip() {
        return LCText.TOOLTIP_SETTINGS_OWNER.get();
    }

    @Override
    public boolean canOpen() {
        return this.menu.hasPermission("transferOwnership");
    }

    @Override
    public void initialize(ScreenArea screenArea, boolean firstOpen) {
        this.playerOwnerInput = this.addChild(new EditBox(this.getFont(), screenArea.x + 20, screenArea.y + 50, 160, 20, EasyText.empty()));
        this.playerOwnerInput.setMaxLength(16);
        this.setPlayerButton = this.addChild(new EasyTextButton(screenArea.pos.offset(20, 80), 160, 20, LCText.BUTTON_OWNER_SET_PLAYER.get(), this::setPlayerOwner).withAddons(EasyAddonHelper.tooltip(LCText.TOOLTIP_WARNING_CANT_BE_UNDONE.getWithStyle(ChatFormatting.YELLOW, ChatFormatting.BOLD))));
        this.ownerSelectionWidget = this.addChild(new OwnerSelectionWidget(screenArea.pos.offset(20, 27), 160, 5, this::getCurrentOwner, this::setOwner, this.ownerSelectionWidget));
        this.buttonToggleInputMode = this.addChild(new IconButton(screenArea.pos.offset(screenArea.width - 25, 5), this::toggleInputMode, this::getModeIcon).withAddons(EasyAddonHelper.tooltip(this::getModeTooltip)));
        this.buttonToggleInputMode = this.addChild(new IconButton(screenArea.pos.offset(screenArea.width - 25, 5), this::toggleInputMode, this::getModeIcon).withAddons(EasyAddonHelper.tooltip(this::getModeTooltip)));
        this.updateMode();
    }

    @Nullable
    protected OwnerData getCurrentOwner() {
        TraderData trader = this.menu.getTrader();
        return trader != null ? trader.getOwner() : null;
    }

    protected void setOwner(@Nonnull Owner newOwner) {
        this.sendMessage(LazyPacketData.simpleTag("ChangeOwner", newOwner.save()));
    }

    @Override
    public void renderBG(@Nonnull EasyGuiGraphics gui) {
        TraderData trader = this.menu.getTrader();
        if (trader != null) {
            gui.drawString(LCText.GUI_OWNER_CURRENT.get(trader.getOwner().getName()), 20, 10, 4210752);
        }
    }

    @Override
    public void tick() {
        if (this.manualSelectionMode) {
            this.setPlayerButton.f_93623_ = !this.playerOwnerInput.getValue().isBlank();
        }
    }

    private void setPlayerOwner(EasyButton button) {
        if (!this.playerOwnerInput.getValue().isBlank()) {
            this.sendMessage(LazyPacketData.simpleString("ChangePlayerOwner", this.playerOwnerInput.getValue()));
            this.playerOwnerInput.setValue("");
        }
    }

    private void toggleInputMode(EasyButton button) {
        this.manualSelectionMode = !this.manualSelectionMode;
        this.updateMode();
    }

    private void updateMode() {
        this.playerOwnerInput.f_93624_ = this.setPlayerButton.f_93624_ = this.manualSelectionMode;
        if (this.manualSelectionMode) {
            this.setPlayerButton.f_93623_ = !this.playerOwnerInput.getValue().isBlank();
        }
        this.ownerSelectionWidget.setVisible(!this.manualSelectionMode);
    }

    private IconData getModeIcon() {
        return this.manualSelectionMode ? IconData.of(Items.COMMAND_BLOCK) : IconAndButtonUtil.ICON_ALEX_HEAD;
    }

    private Component getModeTooltip() {
        return this.manualSelectionMode ? LCText.TOOLTIP_OWNERSHIP_MODE_SELECTION.get() : LCText.TOOLTIP_OWNERSHIP_MODE_MANUAL.get();
    }
}