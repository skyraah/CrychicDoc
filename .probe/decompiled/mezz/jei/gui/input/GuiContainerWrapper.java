package mezz.jei.gui.input;

import java.util.stream.Stream;
import mezz.jei.api.ingredients.ITypedIngredient;
import mezz.jei.api.runtime.IScreenHelper;
import mezz.jei.common.input.ClickableIngredientInternal;
import mezz.jei.common.input.IClickableIngredientInternal;
import mezz.jei.common.util.ImmutableRect2i;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;

public class GuiContainerWrapper implements IRecipeFocusSource {

    private final IScreenHelper screenHelper;

    public GuiContainerWrapper(IScreenHelper screenHelper) {
        this.screenHelper = screenHelper;
    }

    @Override
    public Stream<IClickableIngredientInternal<?>> getIngredientUnderMouse(double mouseX, double mouseY) {
        Screen guiScreen = Minecraft.getInstance().screen;
        return guiScreen == null ? Stream.empty() : this.screenHelper.getClickableIngredientUnderMouse(guiScreen, mouseX, mouseY).map(clickableSlot -> {
            ITypedIngredient<?> typedIngredient = clickableSlot.getTypedIngredient();
            ImmutableRect2i area = new ImmutableRect2i(clickableSlot.getArea());
            return new ClickableIngredientInternal<>(typedIngredient, area, false, false);
        });
    }
}