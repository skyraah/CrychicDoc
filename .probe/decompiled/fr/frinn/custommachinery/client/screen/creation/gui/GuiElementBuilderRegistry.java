package fr.frinn.custommachinery.client.screen.creation.gui;

import fr.frinn.custommachinery.api.guielement.GuiElementType;
import fr.frinn.custommachinery.api.guielement.IGuiElement;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

public class GuiElementBuilderRegistry {

    private static Map<GuiElementType<?>, IGuiElementBuilder<?>> guiElementBuilders;

    public static void init() {
        RegisterGuiElementBuilderEvent event = new RegisterGuiElementBuilderEvent();
        RegisterGuiElementBuilderEvent.EVENT.invoker().registerGuiElementBuilders(event);
        guiElementBuilders = event.getBuilders();
    }

    public static <E extends IGuiElement> boolean hasBuilder(GuiElementType<E> type) {
        return guiElementBuilders.containsKey(type);
    }

    @Nullable
    public static <T extends IGuiElement> IGuiElementBuilder<T> getBuilder(GuiElementType<T> type) {
        return (IGuiElementBuilder<T>) guiElementBuilders.get(type);
    }
}