package dev.latvian.mods.kubejs.player;

import net.minecraft.world.entity.player.Player;

public class SimplePlayerEventJS extends PlayerEventJS {

    private final Player player;

    public SimplePlayerEventJS(Player p) {
        this.player = p;
    }

    @Override
    public Player getEntity() {
        return this.player;
    }
}