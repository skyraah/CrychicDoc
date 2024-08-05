package fr.frinn.custommachinery.client.element;

import fr.frinn.custommachinery.api.guielement.IMachineScreen;
import fr.frinn.custommachinery.common.guielement.ResetGuiElement;
import fr.frinn.custommachinery.impl.guielement.TexturedGuiElementWidget;
import net.minecraft.network.chat.Component;

public class ResetGuiElementWidget extends TexturedGuiElementWidget<ResetGuiElement> {

    private static final Component TITLE = Component.translatable("custommachinery.gui.element.reset.name");

    public ResetGuiElementWidget(ResetGuiElement element, IMachineScreen screen) {
        super(element, screen, TITLE);
    }
}