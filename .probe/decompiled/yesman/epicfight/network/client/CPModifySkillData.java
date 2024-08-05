package yesman.epicfight.network.client;

import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import yesman.epicfight.skill.SkillDataKey;
import yesman.epicfight.skill.SkillDataManager;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;

public class CPModifySkillData {

    private Object value;

    private int slot;

    private int keyId;

    public CPModifySkillData() {
        this.value = null;
    }

    public CPModifySkillData(SkillDataKey<?> key, int slot, Object value) {
        this.keyId = key.getId();
        this.slot = slot;
        this.value = value;
    }

    public static CPModifySkillData fromBytes(FriendlyByteBuf buf) {
        int id = buf.readInt();
        int slot = buf.readInt();
        Object value = SkillDataKey.byId(id).readFromBuffer(buf);
        return new CPModifySkillData(SkillDataKey.byId(id), slot, value);
    }

    public static void toBytes(CPModifySkillData msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.keyId);
        buf.writeInt(msg.slot);
        SkillDataKey.byId(msg.keyId).writeToBuffer(buf, msg.value);
    }

    public static void handle(CPModifySkillData msg, Supplier<NetworkEvent.Context> ctx) {
        ((NetworkEvent.Context) ctx.get()).enqueueWork(() -> {
            ServerPlayer player = ((NetworkEvent.Context) ctx.get()).getSender();
            if (player.getCapability(EpicFightCapabilities.CAPABILITY_ENTITY).orElse(null) instanceof PlayerPatch<?> playerpatch) {
                SkillDataManager dataManager = playerpatch.getSkill(msg.slot).getDataManager();
                SkillDataKey<?> dataKey = SkillDataKey.byId(msg.keyId);
                dataManager.setDataRawtype(dataKey, msg.value);
            }
        });
        ((NetworkEvent.Context) ctx.get()).setPacketHandled(true);
    }
}