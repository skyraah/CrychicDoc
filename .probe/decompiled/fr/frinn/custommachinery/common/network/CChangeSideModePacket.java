package fr.frinn.custommachinery.common.network;

import dev.architectury.networking.NetworkManager;
import dev.architectury.networking.simple.BaseC2SMessage;
import dev.architectury.networking.simple.MessageType;
import dev.architectury.utils.Env;
import fr.frinn.custommachinery.api.component.ISideConfigComponent;
import fr.frinn.custommachinery.common.init.CustomMachineContainer;
import fr.frinn.custommachinery.impl.component.config.RelativeSide;
import fr.frinn.custommachinery.impl.component.config.SideConfig;
import java.util.Optional;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;

public class CChangeSideModePacket extends BaseC2SMessage {

    private final int containerID;

    private final String id;

    private final byte side;

    private final boolean next;

    public CChangeSideModePacket(int containerID, String id, byte side, boolean next) {
        this.containerID = containerID;
        this.id = id;
        this.side = side;
        this.next = next;
    }

    @Override
    public MessageType getType() {
        return PacketManager.CHANGE_SIDE_MODE;
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeVarInt(this.containerID);
        buf.writeUtf(this.id);
        buf.writeByte(this.side);
        buf.writeBoolean(this.next);
    }

    public static CChangeSideModePacket read(FriendlyByteBuf buf) {
        return new CChangeSideModePacket(buf.readVarInt(), buf.readUtf(), buf.readByte(), buf.readBoolean());
    }

    @Override
    public void handle(NetworkManager.PacketContext context) {
        if (context.getEnvironment() == Env.SERVER) {
            context.queue(() -> {
                Player player = context.getPlayer();
                if (player != null && player.containerMenu.containerId == this.containerID && player.containerMenu instanceof CustomMachineContainer container) {
                    Optional<ISideConfigComponent> component = container.getTile().getComponentManager().getConfigComponentById(this.id);
                    if (component.isPresent()) {
                        SideConfig config = ((ISideConfigComponent) component.get()).getConfig();
                        switch(this.side) {
                            case 6:
                                config.setAutoInput(!config.isAutoInput());
                                break;
                            case 7:
                                config.setAutoOutput(!config.isAutoOutput());
                                break;
                            default:
                                RelativeSide side = RelativeSide.values()[this.side];
                                if (this.next) {
                                    config.setSideMode(side, config.getSideMode(side).next());
                                } else {
                                    config.setSideMode(side, config.getSideMode(side).previous());
                                }
                        }
                    }
                }
            });
        }
    }
}