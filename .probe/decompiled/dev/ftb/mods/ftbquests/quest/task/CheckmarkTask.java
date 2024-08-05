package dev.ftb.mods.ftbquests.quest.task;

import dev.ftb.mods.ftbquests.quest.Quest;
import dev.ftb.mods.ftbquests.quest.TeamData;
import dev.ftb.mods.ftbquests.quest.theme.property.ThemeProperties;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CheckmarkTask extends AbstractBooleanTask {

    public CheckmarkTask(long id, Quest quest) {
        super(id, quest);
    }

    @Override
    public TaskType getType() {
        return TaskTypes.CHECKMARK;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void drawGUI(TeamData teamData, GuiGraphics graphics, int x, int y, int w, int h) {
        (teamData.isCompleted(this) ? ThemeProperties.CHECKMARK_TASK_ACTIVE.get(this) : ThemeProperties.CHECKMARK_TASK_INACTIVE.get(this)).draw(graphics, x, y, w, h);
    }

    @Override
    public boolean canSubmit(TeamData teamData, ServerPlayer player) {
        return true;
    }

    @Override
    public boolean checkOnLogin() {
        return false;
    }

    @Override
    protected boolean hasIconConfig() {
        return false;
    }
}