package yesman.epicfight.client;

import com.mojang.blaze3d.vertex.PoseStack;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import yesman.epicfight.client.events.engine.ControllEngine;
import yesman.epicfight.client.events.engine.RenderEngine;
import yesman.epicfight.client.world.capabilites.entitypatch.player.LocalPlayerPatch;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;

@OnlyIn(Dist.CLIENT)
public class ClientEngine {

    private static ClientEngine instance;

    public Minecraft minecraft;

    public RenderEngine renderEngine;

    public ControllEngine controllEngine;

    private boolean armorModelDebuggingMode;

    private boolean triangleDrawingMode;

    public static ClientEngine getInstance() {
        return instance;
    }

    public ClientEngine() {
        instance = this;
        this.minecraft = Minecraft.getInstance();
        this.renderEngine = new RenderEngine();
        this.controllEngine = new ControllEngine();
    }

    public boolean switchArmorModelDebuggingMode() {
        this.armorModelDebuggingMode = !this.armorModelDebuggingMode;
        return this.armorModelDebuggingMode;
    }

    public boolean isArmorModelDebuggingMode() {
        return this.armorModelDebuggingMode;
    }

    @Nullable
    public LocalPlayerPatch getPlayerPatch() {
        return EpicFightCapabilities.getEntityPatch(this.minecraft.player, LocalPlayerPatch.class);
    }

    public boolean isBattleMode() {
        LocalPlayerPatch localPlayerPatch = EpicFightCapabilities.getEntityPatch(this.minecraft.player, LocalPlayerPatch.class);
        return localPlayerPatch == null ? false : localPlayerPatch.isBattleMode();
    }

    public void turnOnTriangleDrawingMode(PoseStack stack) {
        this.triangleDrawingMode = true;
        stack.pushPose();
    }

    public void turnOffTriangleDrawingMode(PoseStack stack) {
        this.triangleDrawingMode = false;
        stack.popPose();
    }

    public boolean isTriangleDrawingMode() {
        return this.triangleDrawingMode;
    }
}