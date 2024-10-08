package io.github.lightman314.lightmanscurrency.client.gui.screen.inventory.tax_collector;

import io.github.lightman314.lightmanscurrency.LCText;
import io.github.lightman314.lightmanscurrency.api.misc.client.rendering.EasyGuiGraphics;
import io.github.lightman314.lightmanscurrency.api.notifications.Notification;
import io.github.lightman314.lightmanscurrency.client.gui.widget.ScrollListener;
import io.github.lightman314.lightmanscurrency.client.gui.widget.button.icon.IconData;
import io.github.lightman314.lightmanscurrency.client.gui.widget.notifications.NotificationDisplayWidget;
import io.github.lightman314.lightmanscurrency.client.util.IconAndButtonUtil;
import io.github.lightman314.lightmanscurrency.client.util.ScreenArea;
import io.github.lightman314.lightmanscurrency.common.menus.tax_collector.TaxCollectorClientTab;
import io.github.lightman314.lightmanscurrency.common.menus.tax_collector.tabs.LogTab;
import io.github.lightman314.lightmanscurrency.common.taxes.TaxEntry;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

public class LogClientTab extends TaxCollectorClientTab<LogTab> {

    public LogClientTab(Object screen, LogTab commonTab) {
        super(screen, commonTab);
    }

    @Nonnull
    @Override
    public IconData getIcon() {
        return IconAndButtonUtil.ICON_SHOW_LOGGER;
    }

    @Nullable
    @Override
    public Component getTooltip() {
        return LCText.TOOLTIP_TAX_COLLECTOR_LOGS.get();
    }

    @Override
    protected void initialize(ScreenArea screenArea, boolean firstOpen) {
        NotificationDisplayWidget display = this.addChild(new NotificationDisplayWidget(screenArea.pos.offset(10, 16), screenArea.width - 20, 7, this::getNotifications));
        this.addChild(new ScrollListener(display.getArea(), display));
    }

    @Override
    public void renderBG(@Nonnull EasyGuiGraphics gui) {
        gui.drawString(this.getTooltip(), 8, 6, 4210752);
    }

    private List<Notification> getNotifications() {
        TaxEntry entry = this.getEntry();
        return (List<Notification>) (entry != null ? entry.getNotifications() : new ArrayList());
    }
}