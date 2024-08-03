package fr.frinn.custommachinery.client.screen.creation;

import fr.frinn.custommachinery.api.machine.MachineAppearanceProperty;
import fr.frinn.custommachinery.client.screen.creation.appearance.AppearancePropertyBuilderRegistry;
import fr.frinn.custommachinery.client.screen.creation.appearance.IAppearancePropertyBuilder;
import fr.frinn.custommachinery.client.screen.widget.ListWidget;
import fr.frinn.custommachinery.common.init.Registration;
import fr.frinn.custommachinery.common.machine.builder.MachineAppearanceBuilder;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.network.chat.Component;

public class AppearanceListWidget extends ListWidget<AppearanceListWidget.AppearanceEntry> {

    private final Supplier<MachineAppearanceBuilder> builder;

    private final MachineEditScreen parent;

    public AppearanceListWidget(int x, int y, int width, int height, int itemHeight, Supplier<MachineAppearanceBuilder> builder, MachineEditScreen parent) {
        super(x, y, width, height, itemHeight, Component.empty());
        this.builder = builder;
        this.parent = parent;
        this.init();
    }

    public void init() {
        this.clear();
        for (MachineAppearanceProperty<?> property : Registration.APPEARANCE_PROPERTY_REGISTRY) {
            IAppearancePropertyBuilder<?> propertyBuilder = AppearancePropertyBuilderRegistry.getBuilder(property);
            if (propertyBuilder != null) {
                this.addEntry(new AppearanceListWidget.AppearanceEntry(propertyBuilder));
            }
        }
    }

    public class AppearanceEntry extends ListWidget.Entry {

        private final IAppearancePropertyBuilder<?> builder;

        private final AbstractWidget widget;

        public AppearanceEntry(IAppearancePropertyBuilder<?> builder) {
            this.builder = builder;
            this.widget = this.createWidget(builder);
        }

        private <T> AbstractWidget createWidget(IAppearancePropertyBuilder<T> builder) {
            return builder.makeWidget(AppearanceListWidget.this.parent, 0, 0, 160, 20, () -> ((MachineAppearanceBuilder) AppearanceListWidget.this.builder.get()).getProperty(builder.getType()), property -> {
                if (!Objects.equals(property, ((MachineAppearanceBuilder) AppearanceListWidget.this.builder.get()).getProperty(builder.getType()))) {
                    ((MachineAppearanceBuilder) AppearanceListWidget.this.builder.get()).setProperty(builder.getType(), (T) property);
                    AppearanceListWidget.this.parent.setChanged();
                }
            });
        }

        @Override
        public void render(GuiGraphics graphics, int index, int x, int y, int width, int height, int mouseX, int mouseY, float partialTick) {
            graphics.drawString(Minecraft.getInstance().font, this.builder.title(), x, y + this.widget.getHeight() / 2 - 2, 0, false);
            this.widget.m_264152_(x + width - this.widget.getWidth() - 10, y);
            if (this.widget.getTooltip() != null) {
                graphics.renderTooltip(Minecraft.getInstance().font, this.widget.getTooltip().toCharSequence(Minecraft.getInstance()), mouseX, mouseY);
            }
        }

        @Override
        public List<AbstractWidget> children() {
            return Collections.singletonList(this.widget);
        }
    }
}