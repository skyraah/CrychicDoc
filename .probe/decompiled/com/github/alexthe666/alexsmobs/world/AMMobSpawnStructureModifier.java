package com.github.alexthe666.alexsmobs.world;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraftforge.common.world.ModifiableStructureInfo;
import net.minecraftforge.common.world.StructureModifier;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AMMobSpawnStructureModifier implements StructureModifier {

    private static final RegistryObject<Codec<? extends StructureModifier>> SERIALIZER = RegistryObject.create(new ResourceLocation("alexsmobs", "am_structure_spawns"), ForgeRegistries.Keys.STRUCTURE_MODIFIER_SERIALIZERS, "alexsmobs");

    @Override
    public void modify(Holder<Structure> structure, StructureModifier.Phase phase, ModifiableStructureInfo.StructureInfo.Builder builder) {
        if (phase == StructureModifier.Phase.ADD) {
            AMWorldRegistry.modifyStructure(structure, builder);
        }
    }

    @Override
    public Codec<? extends StructureModifier> codec() {
        return SERIALIZER.get();
    }

    public static Codec<AMMobSpawnStructureModifier> makeCodec() {
        return Codec.unit(AMMobSpawnStructureModifier::new);
    }
}