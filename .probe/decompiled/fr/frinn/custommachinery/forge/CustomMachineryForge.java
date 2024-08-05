package fr.frinn.custommachinery.forge;

import dev.architectury.platform.forge.EventBuses;
import fr.frinn.custommachinery.CustomMachinery;
import fr.frinn.custommachinery.common.util.LootTableHelper;
import fr.frinn.custommachinery.forge.client.ClientHandler;
import fr.frinn.custommachinery.forge.integration.theoneprobe.TOPInfoProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.OnDatapackSyncEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("custommachinery")
public class CustomMachineryForge {

    public CustomMachineryForge() {
        IEventBus MOD_BUS = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus("custommachinery", MOD_BUS);
        MOD_BUS.addListener(this::sendIMCMessages);
        CustomMachinery.init();
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientHandler::setupConfig);
        IEventBus FORGE_BUS = MinecraftForge.EVENT_BUS;
        FORGE_BUS.addListener(EventPriority.HIGH, this::syncDatapacks);
    }

    private void sendIMCMessages(InterModEnqueueEvent event) {
        if (ModList.get().isLoaded("theoneprobe")) {
            InterModComms.sendTo("theoneprobe", "getTheOneProbe", TOPInfoProvider::new);
        }
    }

    private void syncDatapacks(OnDatapackSyncEvent event) {
        if (event.getPlayer() != null) {
            CustomMachinery.syncData(event.getPlayer());
        } else {
            LootTableHelper.generate(event.getPlayerList().getServer());
            event.getPlayerList().getPlayers().forEach(CustomMachinery::syncData);
        }
    }
}