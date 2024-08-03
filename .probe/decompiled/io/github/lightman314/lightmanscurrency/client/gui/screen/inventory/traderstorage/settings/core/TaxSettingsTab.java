package io.github.lightman314.lightmanscurrency.client.gui.screen.inventory.traderstorage.settings.core;

import io.github.lightman314.lightmanscurrency.LCText;
import io.github.lightman314.lightmanscurrency.api.misc.client.rendering.EasyGuiGraphics;
import io.github.lightman314.lightmanscurrency.api.network.LazyPacketData;
import io.github.lightman314.lightmanscurrency.api.traders.TraderData;
import io.github.lightman314.lightmanscurrency.client.gui.screen.inventory.traderstorage.settings.SettingsSubTab;
import io.github.lightman314.lightmanscurrency.client.gui.screen.inventory.traderstorage.settings.TraderSettingsClientTab;
import io.github.lightman314.lightmanscurrency.client.gui.widget.button.icon.IconData;
import io.github.lightman314.lightmanscurrency.client.gui.widget.button.trade.AlertType;
import io.github.lightman314.lightmanscurrency.client.gui.widget.easy.EasyAddonHelper;
import io.github.lightman314.lightmanscurrency.client.gui.widget.easy.EasyButton;
import io.github.lightman314.lightmanscurrency.client.util.IconAndButtonUtil;
import io.github.lightman314.lightmanscurrency.client.util.ScreenArea;
import io.github.lightman314.lightmanscurrency.client.util.TextRenderUtil;
import io.github.lightman314.lightmanscurrency.common.player.LCAdminMode;
import javax.annotation.Nonnull;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.util.NonNullSupplier;
import org.jetbrains.annotations.Nullable;

public class TaxSettingsTab extends SettingsSubTab {

    public TaxSettingsTab(@Nonnull TraderSettingsClientTab parent) {
        super(parent);
    }

    @Nonnull
    @Override
    public IconData getIcon() {
        return IconAndButtonUtil.ICON_TAXES;
    }

    @Nullable
    @Override
    public Component getTooltip() {
        return LCText.TOOLTIP_TRADER_SETTINGS_TAXES.get();
    }

    @Override
    public boolean canOpen() {
        return true;
    }

    @Override
    protected void initialize(ScreenArea screenArea, boolean firstOpen) {
        this.addChild(IconAndButtonUtil.plusButton(screenArea.pos.offset(20, 30), this::increaseAcceptableTaxRate).withAddons(EasyAddonHelper.activeCheck((NonNullSupplier<Boolean>) (() -> this.getAcceptableTaxRate() < 99))));
        this.addChild(IconAndButtonUtil.minusButton(screenArea.pos.offset(20, 40), this::decreaseAcceptableTaxRate).withAddons(EasyAddonHelper.activeCheck((NonNullSupplier<Boolean>) (() -> this.getAcceptableTaxRate() > 0))));
        this.addChild(IconAndButtonUtil.checkmarkButton(screenArea.pos.offset(30, 80), this::toggleIgnoreAllTaxes, this::getIgnoreAllTaxes).withAddons(EasyAddonHelper.visibleCheck(this::isIgnoreAllTaxesVisible)));
    }

    @Override
    public void renderBG(@Nonnull EasyGuiGraphics gui) {
        TraderData trader = this.menu.getTrader();
        if (trader != null) {
            int totalRate = trader.getTotalTaxPercentage();
            int acceptableRate = trader.getAcceptableTaxRate();
            int color = totalRate > acceptableRate ? AlertType.ERROR.color : (totalRate == acceptableRate ? AlertType.WARN.color : AlertType.HELPFUL.color);
            TextRenderUtil.drawCenteredText(gui, LCText.GUI_TRADER_TAXES_TOTAL_RATE.get(totalRate), this.screen.getXSize() / 2, 16, color);
        }
        gui.drawString(LCText.GUI_TRADER_SETTINGS_TAXES_ACCEPTABLE_RATE.get(this.getAcceptableTaxRate()), 34, 37, 4210752);
        if (this.isIgnoreAllTaxesVisible()) {
            gui.drawString(LCText.GUI_TRADER_SETTINGS_TAXES_IGNORE_TAXES.get(), 40, 82, 4210752);
        }
    }

    private boolean isIgnoreAllTaxesVisible() {
        return LCAdminMode.isAdminPlayer(this.menu.getPlayer()) || this.getIgnoreAllTaxes();
    }

    private boolean getIgnoreAllTaxes() {
        TraderData trader = this.menu.getTrader();
        return trader != null && trader.ShouldIgnoreAllTaxes();
    }

    private int getAcceptableTaxRate() {
        TraderData trader = this.menu.getTrader();
        return trader != null ? trader.getAcceptableTaxRate() : 0;
    }

    private void toggleIgnoreAllTaxes(EasyButton button) {
        TraderData trader = this.menu.getTrader();
        if (trader != null) {
            this.sendMessage(LazyPacketData.simpleBoolean("ForceIgnoreAllTaxCollectors", !trader.ShouldIgnoreAllTaxes()));
        }
    }

    private void increaseAcceptableTaxRate(EasyButton button) {
        TraderData trader = this.menu.getTrader();
        if (trader != null) {
            int oldRate = trader.getAcceptableTaxRate();
            int newRate = Screen.hasShiftDown() ? oldRate + 10 : oldRate + 1;
            this.sendMessage(LazyPacketData.simpleInt("AcceptableTaxRate", newRate));
        }
    }

    private void decreaseAcceptableTaxRate(EasyButton button) {
        TraderData trader = this.menu.getTrader();
        if (trader != null) {
            int oldRate = trader.getAcceptableTaxRate();
            int newRate = Screen.hasShiftDown() ? oldRate - 10 : oldRate - 1;
            this.sendMessage(LazyPacketData.simpleInt("AcceptableTaxRate", newRate));
        }
    }
}