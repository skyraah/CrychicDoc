package com.yungnickyoung.minecraft.yungsapi.module;

import com.yungnickyoung.minecraft.yungsapi.api.autoregister.AutoRegisterBlockEntityType;
import com.yungnickyoung.minecraft.yungsapi.autoregister.AutoRegisterField;
import com.yungnickyoung.minecraft.yungsapi.autoregister.AutoRegistrationManager;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityTypeModuleForge {

    private static final Map<String, DeferredRegister<BlockEntityType<?>>> registersByModId = new HashMap();

    public static void processEntries() {
        AutoRegistrationManager.BLOCK_ENTITY_TYPES.stream().filter(data -> !data.processed()).forEach(BlockEntityTypeModuleForge::register);
    }

    private static void register(AutoRegisterField data) {
        String modId = data.name().getNamespace();
        if (!registersByModId.containsKey(modId)) {
            DeferredRegister<BlockEntityType<?>> deferredRegister = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, modId);
            deferredRegister.register(FMLJavaModLoadingContext.get().getModEventBus());
            registersByModId.put(modId, deferredRegister);
        }
        AutoRegisterBlockEntityType autoRegisterBlockEntityType = (AutoRegisterBlockEntityType) data.object();
        Supplier<BlockEntityType<?>> blockEntityTypeSupplier = autoRegisterBlockEntityType.getSupplier();
        DeferredRegister<BlockEntityType<?>> deferredRegister = (DeferredRegister<BlockEntityType<?>>) registersByModId.get(modId);
        RegistryObject<BlockEntityType<?>> registryObject = deferredRegister.register(data.name().getPath(), blockEntityTypeSupplier);
        autoRegisterBlockEntityType.setSupplier(registryObject);
        data.markProcessed();
    }
}