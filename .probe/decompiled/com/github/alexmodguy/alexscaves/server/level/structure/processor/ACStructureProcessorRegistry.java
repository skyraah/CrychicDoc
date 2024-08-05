package com.github.alexmodguy.alexscaves.server.level.structure.processor;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ACStructureProcessorRegistry {

    public static final DeferredRegister<StructureProcessorType<?>> DEF_REG = DeferredRegister.create(Registries.STRUCTURE_PROCESSOR, "alexscaves");

    public static final RegistryObject<StructureProcessorType<UndergroundCabinProcessor>> UNDERGROUND_CABIN = DEF_REG.register("underground_cabin", () -> () -> UndergroundCabinProcessor.CODEC);

    public static final RegistryObject<StructureProcessorType<WhalefallProcessor>> WHALEFALL = DEF_REG.register("whalefall", () -> () -> WhalefallProcessor.CODEC);

    public static final RegistryObject<StructureProcessorType<WhalefallProcessor>> WHALEFALL_SKULL = DEF_REG.register("whalefall_skull", () -> () -> WhalefallProcessor.CODEC_SKULL);
}