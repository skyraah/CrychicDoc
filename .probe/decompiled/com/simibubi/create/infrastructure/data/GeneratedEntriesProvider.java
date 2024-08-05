package com.simibubi.create.infrastructure.data;

import com.simibubi.create.AllDamageTypes;
import com.simibubi.create.infrastructure.worldgen.AllBiomeModifiers;
import com.simibubi.create.infrastructure.worldgen.AllConfiguredFeatures;
import com.simibubi.create.infrastructure.worldgen.AllPlacedFeatures;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

public class GeneratedEntriesProvider extends DatapackBuiltinEntriesProvider {

    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder().add(Registries.DAMAGE_TYPE, AllDamageTypes::bootstrap).add(Registries.CONFIGURED_FEATURE, AllConfiguredFeatures::bootstrap).add(Registries.PLACED_FEATURE, AllPlacedFeatures::bootstrap).add(ForgeRegistries.Keys.BIOME_MODIFIERS, AllBiomeModifiers::bootstrap);

    public GeneratedEntriesProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of("create"));
    }

    @Override
    public String getName() {
        return "Create's Generated Registry Entries";
    }
}