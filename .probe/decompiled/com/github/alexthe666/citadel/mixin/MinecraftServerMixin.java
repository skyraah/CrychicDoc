package com.github.alexthe666.citadel.mixin;

import com.github.alexthe666.citadel.server.world.ModifiableTickRateServer;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ MinecraftServer.class })
public abstract class MinecraftServerMixin implements ModifiableTickRateServer {

    private long modifiedMsPerTick = -1L;

    private long masterMs;

    @Inject(method = { "Lnet/minecraft/server/MinecraftServer;runServer()V" }, remap = true, at = { @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;startMetricsRecordingTick()V", shift = Shift.BEFORE) })
    protected void citadel_beforeServerTick(CallbackInfo ci) {
        this.masterTick();
    }

    private void masterTick() {
        this.masterMs += 50L;
    }

    @ModifyConstant(method = { "Lnet/minecraft/server/MinecraftServer;runServer()V" }, constant = { @Constant(longValue = 50L) }, expect = 4)
    private long citadel_serverMsPerTick(long value) {
        return this.modifiedMsPerTick == -1L ? value : this.modifiedMsPerTick;
    }

    @Override
    public void setGlobalTickLengthMs(long msPerTick) {
        this.modifiedMsPerTick = msPerTick;
    }

    @Override
    public long getMasterMs() {
        return this.masterMs;
    }
}