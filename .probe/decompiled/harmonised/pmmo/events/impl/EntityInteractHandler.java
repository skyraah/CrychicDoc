package harmonised.pmmo.events.impl;

import harmonised.pmmo.api.enums.EventType;
import harmonised.pmmo.api.enums.ReqType;
import harmonised.pmmo.core.Core;
import harmonised.pmmo.features.party.PartyUtils;
import harmonised.pmmo.util.Messenger;
import harmonised.pmmo.util.RegistryUtil;
import harmonised.pmmo.util.TagUtils;
import java.util.List;
import java.util.Map;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class EntityInteractHandler {

    public static void handle(PlayerInteractEvent.EntityInteract event) {
        Core core = Core.get(event.getEntity().m_9236_());
        if (!core.isActionPermitted(ReqType.ENTITY_INTERACT, event.getTarget(), event.getEntity())) {
            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.FAIL);
            Messenger.sendDenialMsg(ReqType.ENTITY_INTERACT, event.getEntity(), event.getTarget().getName());
        } else {
            boolean serverSide = !event.getEntity().m_9236_().isClientSide;
            CompoundTag eventHookOutput = new CompoundTag();
            if (serverSide) {
                eventHookOutput = core.getEventTriggerRegistry().executeEventListeners(EventType.ENTITY, event, new CompoundTag());
                if (eventHookOutput.getBoolean("is_cancelled")) {
                    event.setCanceled(true);
                    event.setCancellationResult(InteractionResult.FAIL);
                    return;
                }
            }
            eventHookOutput.putString("target", RegistryUtil.getId(event.getTarget()).toString());
            eventHookOutput.putInt("entity_id", event.getTarget().getId());
            CompoundTag perkOutput = TagUtils.mergeTags(eventHookOutput, core.getPerkRegistry().executePerk(EventType.ENTITY, event.getEntity(), eventHookOutput));
            if (serverSide) {
                Map<String, Long> xpAward = core.getExperienceAwards(EventType.ENTITY, event.getTarget(), event.getEntity(), perkOutput);
                List<ServerPlayer> partyMembersInRange = PartyUtils.getPartyMembersInRange((ServerPlayer) event.getEntity());
                core.awardXP(partyMembersInRange, xpAward);
            }
        }
    }
}