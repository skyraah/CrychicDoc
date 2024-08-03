package dev.latvian.mods.kubejs.player;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;

@Info("Invoked when a player opens or closes a container.\n")
public class InventoryEventJS extends PlayerEventJS {

    private final Player player;

    private final AbstractContainerMenu menu;

    public InventoryEventJS(Player player, AbstractContainerMenu menu) {
        this.player = player;
        this.menu = menu;
    }

    @Info("Gets the player that opened or closed the container.")
    @Override
    public Player getEntity() {
        return this.player;
    }

    @Info("Gets the container that was opened or closed.")
    public AbstractContainerMenu getInventoryContainer() {
        return this.menu;
    }
}