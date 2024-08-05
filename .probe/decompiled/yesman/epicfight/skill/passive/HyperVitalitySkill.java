package yesman.epicfight.skill.passive;

import com.mojang.blaze3d.vertex.PoseStack;
import java.util.UUID;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import yesman.epicfight.client.gui.BattleModeGui;
import yesman.epicfight.client.world.capabilites.entitypatch.player.LocalPlayerPatch;
import yesman.epicfight.network.EpicFightNetworkManager;
import yesman.epicfight.network.server.SPSkillExecutionFeedback;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillCategories;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;

public class HyperVitalitySkill extends PassiveSkill {

    private static final UUID EVENT_UUID = UUID.fromString("06fb3f66-b900-11ed-afa1-0242ac120002");

    public HyperVitalitySkill(Skill.Builder<? extends Skill> builder) {
        super(builder);
    }

    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);
        container.getExecuter().getEventListener().addEventListener(PlayerEventListener.EventType.SKILL_CONSUME_EVENT, EVENT_UUID, event -> {
            if (!container.getExecuter().getSkill(event.getSkill()).isDisabled() && event.getSkill().getCategory() == SkillCategories.WEAPON_INNATE) {
                PlayerPatch<?> playerpatch = event.getPlayerPatch();
                if (playerpatch.getSkill(SkillSlots.WEAPON_INNATE).getStack() < 1 && container.getStack() > 0 && !playerpatch.getOriginal().isCreative()) {
                    float consumption = event.getSkill().getConsumption();
                    if (playerpatch.hasStamina(consumption * 0.1F)) {
                        event.setResourceType(Skill.Resource.NONE);
                        container.setMaxResource(consumption * 0.2F);
                        if (event.shouldConsume()) {
                            container.getExecuter().consumeStamina(consumption * 0.1F);
                            container.setMaxDuration(event.getSkill().getMaxDuration());
                            container.activate();
                            EpicFightNetworkManager.sendToPlayer(SPSkillExecutionFeedback.executed(container.getSlotId()), (ServerPlayer) playerpatch.getOriginal());
                        }
                    }
                }
            }
        }, 1);
        container.getExecuter().getEventListener().addEventListener(PlayerEventListener.EventType.SKILL_CANCEL_EVENT, EVENT_UUID, event -> {
            if (!container.getExecuter().isLogicalClient() && !container.getExecuter().getOriginal().isCreative() && event.getSkillContainer().getSkill().getCategory() == SkillCategories.WEAPON_INNATE && container.isActivated()) {
                container.setResource(0.0F);
                container.deactivate();
                ServerPlayerPatch serverPlayerPatch = (ServerPlayerPatch) container.getExecuter();
                this.setStackSynchronize(serverPlayerPatch, container.getStack() - 1);
                EpicFightNetworkManager.sendToPlayer(SPSkillExecutionFeedback.executed(container.getSlotId()), serverPlayerPatch.getOriginal());
            }
        });
    }

    @Override
    public void onRemoved(SkillContainer container) {
        super.onRemoved(container);
        container.getExecuter().getEventListener().removeListener(PlayerEventListener.EventType.SKILL_CONSUME_EVENT, EVENT_UUID);
        container.getExecuter().getEventListener().removeListener(PlayerEventListener.EventType.SKILL_CANCEL_EVENT, EVENT_UUID);
    }

    @Override
    public void executeOnClient(LocalPlayerPatch executer, FriendlyByteBuf args) {
        super.executeOnClient(executer, args);
        executer.getSkill(this).activate();
    }

    @Override
    public void cancelOnClient(LocalPlayerPatch executer, FriendlyByteBuf args) {
        super.cancelOnClient(executer, args);
        executer.getSkill(this).deactivate();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public boolean shouldDraw(SkillContainer container) {
        return container.isActivated() || container.getStack() == 0;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void drawOnGui(BattleModeGui gui, SkillContainer container, GuiGraphics guiGraphics, float x, float y) {
        PoseStack poseStack = new PoseStack();
        poseStack.pushPose();
        poseStack.translate(0.0F, (float) gui.getSlidingProgression(), 0.0F);
        guiGraphics.blit(this.getSkillTexture(), (int) x, (int) y, 24, 24, 0.0F, 0.0F, 1, 1, 1, 1);
        if (!container.isActivated()) {
            String remainTime = String.format("%.0f", container.getMaxResource() - container.getResource());
            guiGraphics.drawString(gui.font, remainTime, x + 12.0F - (float) (4 * remainTime.length()), y + 6.0F, 16777215, true);
        }
        poseStack.popPose();
    }
}