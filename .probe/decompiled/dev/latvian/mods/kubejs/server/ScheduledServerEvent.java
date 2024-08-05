package dev.latvian.mods.kubejs.server;

import dev.latvian.mods.kubejs.util.ScheduledEvents;
import net.minecraft.server.MinecraftServer;

public class ScheduledServerEvent extends ScheduledEvents.ScheduledEvent {

    public final MinecraftServer server;

    public static ScheduledEvents make(MinecraftServer server) {
        return new ScheduledEvents(() -> new ScheduledServerEvent(server));
    }

    public ScheduledServerEvent(MinecraftServer server) {
        this.server = server;
    }
}