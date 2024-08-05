package org.violetmoon.zeta.client.config.definition;

import java.util.function.Consumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.language.I18n;
import org.violetmoon.zeta.client.ZetaClient;
import org.violetmoon.zeta.client.config.screen.SectionScreen;
import org.violetmoon.zeta.client.config.widget.PencilButton;
import org.violetmoon.zeta.config.ChangeSet;
import org.violetmoon.zeta.config.SectionDefinition;

public class SectionClientDefinition implements ClientDefinitionExt<SectionDefinition> {

    public String getSubtitle(ChangeSet changes, SectionDefinition def) {
        int size = def.getSubsections().size() + def.getValues().size();
        return size == 1 ? I18n.get("quark.gui.config.onechild") : I18n.get("quark.gui.config.nchildren", size);
    }

    public void addWidgets(ZetaClient zc, Screen parent, ChangeSet changes, SectionDefinition def, Consumer<AbstractWidget> widgets) {
        widgets.accept(new PencilButton(zc, 230, 3, b -> Minecraft.getInstance().setScreen(new SectionScreen(zc, parent, changes, def))));
    }
}