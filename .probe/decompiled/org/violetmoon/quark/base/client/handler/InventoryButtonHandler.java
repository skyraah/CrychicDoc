package org.violetmoon.quark.base.client.handler;

import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.Slot;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import org.jetbrains.annotations.NotNull;
import org.violetmoon.quark.addons.oddities.client.screen.BackpackInventoryScreen;
import org.violetmoon.quark.api.IQuarkButtonAllowed;
import org.violetmoon.quark.base.Quark;
import org.violetmoon.quark.base.config.QuarkGeneralConfig;
import org.violetmoon.quark.base.handler.InventoryTransferHandler;
import org.violetmoon.quark.content.management.client.screen.widgets.MiniInventoryButton;
import org.violetmoon.zeta.client.event.load.ZKeyMapping;
import org.violetmoon.zeta.client.event.play.ZScreen;
import org.violetmoon.zeta.event.bus.PlayEvent;
import org.violetmoon.zeta.module.ZetaModule;

public final class InventoryButtonHandler {

    private static final Multimap<InventoryButtonHandler.ButtonTargetType, InventoryButtonHandler.ButtonProviderHolder> providers = Multimaps.newSetMultimap(new HashMap(), TreeSet::new);

    private static final Multimap<InventoryButtonHandler.ButtonTargetType, Button> currentButtons = Multimaps.newSetMultimap(new HashMap(), LinkedHashSet::new);

    @PlayEvent
    public static void initGui(ZScreen.Init.Post event) {
        Minecraft mc = Minecraft.getInstance();
        Screen screen = event.getScreen();
        if (QuarkGeneralConfig.printScreenClassnames) {
            String print = I18n.get("quark.misc.opened_screen", ChatFormatting.AQUA + screen.getClass().getName());
            Quark.LOG.info(print);
            if (mc.player != null) {
                mc.player.sendSystemMessage(Component.literal(print));
            }
        }
        currentButtons.clear();
        boolean apiAllowed = screen instanceof IQuarkButtonAllowed;
        if (screen instanceof AbstractContainerScreen<?> containerScreen && (apiAllowed || QuarkGeneralConfig.isScreenAllowed(screen))) {
            if (containerScreen instanceof InventoryScreen || containerScreen.getClass().getName().contains("CuriosScreen")) {
                applyProviders(event, InventoryButtonHandler.ButtonTargetType.PLAYER_INVENTORY, containerScreen, s -> s.container == mc.player.m_150109_() && s.getSlotIndex() == 17);
            } else if (apiAllowed || InventoryTransferHandler.accepts(containerScreen.getMenu(), mc.player)) {
                applyProviders(event, InventoryButtonHandler.ButtonTargetType.CONTAINER_INVENTORY, containerScreen, s -> s.container != mc.player.m_150109_() && s.getSlotIndex() == 8);
                applyProviders(event, InventoryButtonHandler.ButtonTargetType.CONTAINER_PLAYER_INVENTORY, containerScreen, s -> s.container == mc.player.m_150109_() && s.getSlotIndex() == 17);
            }
        }
    }

    private static Collection<InventoryButtonHandler.ButtonProviderHolder> forGui(Screen gui) {
        Set<InventoryButtonHandler.ButtonProviderHolder> holders = new HashSet();
        if (gui instanceof AbstractContainerScreen<?> screen) {
            if (gui instanceof InventoryScreen) {
                holders.addAll(providers.get(InventoryButtonHandler.ButtonTargetType.PLAYER_INVENTORY));
            } else {
                Minecraft mc = Minecraft.getInstance();
                if (InventoryTransferHandler.accepts(screen.getMenu(), mc.player)) {
                    holders.addAll(providers.get(InventoryButtonHandler.ButtonTargetType.CONTAINER_INVENTORY));
                    holders.addAll(providers.get(InventoryButtonHandler.ButtonTargetType.CONTAINER_PLAYER_INVENTORY));
                }
            }
        }
        return holders;
    }

    @PlayEvent
    public static void mouseInputEvent(ZScreen.MouseButtonPressed.Pre pressed) {
        if (pressed.getScreen() instanceof AbstractContainerScreen<?> screen) {
            if (!QuarkGeneralConfig.isScreenAllowed(screen)) {
                return;
            }
            for (InventoryButtonHandler.ButtonProviderHolder holder : forGui(screen)) {
                if (holder.keybind != null && holder.keybind.matchesMouse(pressed.getButton()) && (holder.keybind.getKeyModifier() == KeyModifier.NONE || holder.keybind.getKeyModifier().isActive(KeyConflictContext.GUI))) {
                    holder.pressed.accept(screen);
                    pressed.setCanceled(true);
                }
            }
        }
    }

    @PlayEvent
    public static void keyboardInputEvent(ZScreen.KeyPressed.Pre pressed) {
        if (pressed.getScreen() instanceof AbstractContainerScreen<?> screen) {
            if (!QuarkGeneralConfig.isScreenAllowed(screen)) {
                return;
            }
            for (InventoryButtonHandler.ButtonProviderHolder holder : forGui(screen)) {
                if (holder.keybind != null && holder.keybind.matches(pressed.getKeyCode(), pressed.getScanCode()) && (holder.keybind.getKeyModifier() == KeyModifier.NONE || holder.keybind.getKeyModifier().isActive(KeyConflictContext.GUI))) {
                    holder.pressed.accept(screen);
                    pressed.setCanceled(true);
                }
            }
        }
    }

    private static void applyProviders(ZScreen.Init.Post event, InventoryButtonHandler.ButtonTargetType type, AbstractContainerScreen<?> screen, Predicate<Slot> slotPred) {
        Collection<InventoryButtonHandler.ButtonProviderHolder> holders = providers.get(type);
        if (!holders.isEmpty()) {
            for (Slot slot : screen.getMenu().slots) {
                if (slotPred.test(slot)) {
                    int x = slot.x + 6;
                    int y = slot.y - 13;
                    if (screen instanceof BackpackInventoryScreen) {
                        y -= 60;
                    }
                    for (InventoryButtonHandler.ButtonProviderHolder holder : holders) {
                        Button button = holder.getButton(screen, x, y);
                        if (button != null) {
                            event.addListener(button);
                            currentButtons.put(type, button);
                            x -= 12;
                        }
                    }
                    return;
                }
            }
        }
    }

    public static Collection<Button> getActiveButtons(InventoryButtonHandler.ButtonTargetType type) {
        return currentButtons.get(type);
    }

    public static void addButtonProvider(ZetaModule module, InventoryButtonHandler.ButtonTargetType type, int priority, KeyMapping binding, Consumer<AbstractContainerScreen<?>> onKeybind, InventoryButtonHandler.ButtonProvider provider, BooleanSupplier enableCond) {
        providers.put(type, new InventoryButtonHandler.ButtonProviderHolder(module, priority, type, provider, binding, onKeybind, enableCond));
    }

    public static void addButtonProvider(ZKeyMapping event, ZetaModule module, InventoryButtonHandler.ButtonTargetType type, int priority, String keybindName, Consumer<AbstractContainerScreen<?>> onKeybind, InventoryButtonHandler.ButtonProvider provider, BooleanSupplier enableCond) {
        KeyMapping keybind = event.init(keybindName, null, "quark.gui.keygroup.inv");
        keybind.setKeyConflictContext(KeyConflictContext.GUI);
        addButtonProvider(module, type, priority, keybind, onKeybind, provider, enableCond);
    }

    public static void addButtonProvider(ZetaModule module, InventoryButtonHandler.ButtonTargetType type, int priority, InventoryButtonHandler.ButtonProvider provider, BooleanSupplier enableCond) {
        providers.put(type, new InventoryButtonHandler.ButtonProviderHolder(module, priority, type, provider, enableCond));
    }

    public interface ButtonProvider {

        MiniInventoryButton provide(AbstractContainerScreen<?> var1, int var2, int var3);
    }

    private static class ButtonProviderHolder implements Comparable<InventoryButtonHandler.ButtonProviderHolder> {

        private final int priority;

        private final ZetaModule module;

        private final InventoryButtonHandler.ButtonTargetType type;

        private final InventoryButtonHandler.ButtonProvider provider;

        private final KeyMapping keybind;

        private final Consumer<AbstractContainerScreen<?>> pressed;

        private final BooleanSupplier enableCond;

        public ButtonProviderHolder(ZetaModule module, int priority, InventoryButtonHandler.ButtonTargetType type, InventoryButtonHandler.ButtonProvider provider, KeyMapping keybind, Consumer<AbstractContainerScreen<?>> onPressed, BooleanSupplier enableCond) {
            this.module = module;
            this.priority = priority;
            this.type = type;
            this.provider = provider;
            this.keybind = keybind;
            this.pressed = onPressed;
            this.enableCond = enableCond;
        }

        public ButtonProviderHolder(ZetaModule module, int priority, InventoryButtonHandler.ButtonTargetType type, InventoryButtonHandler.ButtonProvider provider, BooleanSupplier enableCond) {
            this(module, priority, type, provider, null, screen -> {
            }, enableCond);
        }

        public int compareTo(@NotNull InventoryButtonHandler.ButtonProviderHolder o) {
            return this.priority - o.priority;
        }

        public MiniInventoryButton getButton(AbstractContainerScreen<?> parent, int x, int y) {
            MiniInventoryButton b = !this.module.enabled || this.enableCond != null && !this.enableCond.getAsBoolean() ? null : this.provider.provide(parent, x, y);
            if (b != null) {
                b.setType(this.type);
            }
            return b;
        }
    }

    public static enum ButtonTargetType {

        PLAYER_INVENTORY(() -> QuarkGeneralConfig.chestButtonOffsets.playerX, () -> QuarkGeneralConfig.chestButtonOffsets.playerY), CONTAINER_INVENTORY(() -> QuarkGeneralConfig.chestButtonOffsets.topX, () -> QuarkGeneralConfig.chestButtonOffsets.topY), CONTAINER_PLAYER_INVENTORY(() -> QuarkGeneralConfig.chestButtonOffsets.middleX, () -> QuarkGeneralConfig.chestButtonOffsets.middleY);

        public final Supplier<Integer> offX;

        public final Supplier<Integer> offY;

        private ButtonTargetType(Supplier<Integer> offX, Supplier<Integer> offY) {
            this.offX = offX;
            this.offY = offY;
        }
    }
}