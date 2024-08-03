package dev.latvian.mods.kubejs.item.forge;

import dev.latvian.mods.kubejs.player.PlayerEventJS;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import org.jetbrains.annotations.Nullable;

public class ItemDestroyedEventJS extends PlayerEventJS {

    private final PlayerDestroyItemEvent event;

    public ItemDestroyedEventJS(PlayerDestroyItemEvent e) {
        this.event = e;
    }

    @Override
    public Player getEntity() {
        return this.event.getEntity();
    }

    @Nullable
    public InteractionHand getHand() {
        return this.event.getHand();
    }

    public ItemStack getItem() {
        return this.event.getOriginal();
    }
}