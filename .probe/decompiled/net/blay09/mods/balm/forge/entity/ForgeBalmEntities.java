package net.blay09.mods.balm.forge.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import net.blay09.mods.balm.api.DeferredObject;
import net.blay09.mods.balm.api.entity.BalmEntities;
import net.blay09.mods.balm.forge.DeferredRegisters;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ForgeBalmEntities implements BalmEntities {

    private final Map<String, ForgeBalmEntities.Registrations> registrations = new ConcurrentHashMap();

    @Override
    public <T extends Entity> DeferredObject<EntityType<T>> registerEntity(ResourceLocation identifier, EntityType.Builder<T> typeBuilder) {
        DeferredRegister<EntityType<?>> register = DeferredRegisters.get(ForgeRegistries.ENTITY_TYPES, identifier.getNamespace());
        RegistryObject<EntityType<T>> registryObject = register.register(identifier.getPath(), () -> typeBuilder.build(identifier.toString()));
        return new DeferredObject<>(identifier, registryObject, registryObject::isPresent);
    }

    @Override
    public <T extends LivingEntity> DeferredObject<EntityType<T>> registerEntity(ResourceLocation identifier, EntityType.Builder<T> typeBuilder, Supplier<AttributeSupplier.Builder> attributeBuilder) {
        DeferredRegister<EntityType<?>> register = DeferredRegisters.get(ForgeRegistries.ENTITY_TYPES, identifier.getNamespace());
        ForgeBalmEntities.Registrations registrations = this.getActiveRegistrations();
        RegistryObject<EntityType<T>> registryObject = register.register(identifier.getPath(), () -> {
            EntityType<T> entityType = typeBuilder.build(identifier.toString());
            registrations.attributeSuppliers.put(entityType, ((AttributeSupplier.Builder) attributeBuilder.get()).build());
            return entityType;
        });
        return new DeferredObject<>(identifier, registryObject, registryObject::isPresent);
    }

    public void register() {
        FMLJavaModLoadingContext.get().getModEventBus().register(this.getActiveRegistrations());
    }

    private ForgeBalmEntities.Registrations getActiveRegistrations() {
        return (ForgeBalmEntities.Registrations) this.registrations.computeIfAbsent(ModLoadingContext.get().getActiveNamespace(), it -> new ForgeBalmEntities.Registrations());
    }

    private static class Registrations {

        public final Map<EntityType<?>, AttributeSupplier> attributeSuppliers = new HashMap();

        @SubscribeEvent
        public void registerAttributes(EntityAttributeCreationEvent event) {
            for (Entry<EntityType<?>, AttributeSupplier> entry : this.attributeSuppliers.entrySet()) {
                event.put((EntityType<? extends LivingEntity>) entry.getKey(), (AttributeSupplier) entry.getValue());
            }
        }
    }
}