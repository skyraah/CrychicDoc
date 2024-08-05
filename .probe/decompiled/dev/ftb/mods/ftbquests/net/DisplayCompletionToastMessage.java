package dev.ftb.mods.ftbquests.net;

import dev.architectury.networking.NetworkManager;
import dev.architectury.networking.simple.BaseS2CMessage;
import dev.architectury.networking.simple.MessageType;
import dev.ftb.mods.ftbquests.client.FTBQuestsNetClient;
import net.minecraft.network.FriendlyByteBuf;

public class DisplayCompletionToastMessage extends BaseS2CMessage {

    private final long id;

    DisplayCompletionToastMessage(FriendlyByteBuf buffer) {
        this.id = buffer.readLong();
    }

    public DisplayCompletionToastMessage(long i) {
        this.id = i;
    }

    @Override
    public MessageType getType() {
        return FTBQuestsNetHandler.DISPLAY_COMPLETION_TOAST;
    }

    @Override
    public void write(FriendlyByteBuf buffer) {
        buffer.writeLong(this.id);
    }

    @Override
    public void handle(NetworkManager.PacketContext context) {
        FTBQuestsNetClient.displayCompletionToast(this.id);
    }
}