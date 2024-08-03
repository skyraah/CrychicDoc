package net.minecraft.world.level.levelgen.feature.configurations;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class RandomFeatureConfiguration implements FeatureConfiguration {

    public static final Codec<RandomFeatureConfiguration> CODEC = RecordCodecBuilder.create(p_67898_ -> p_67898_.apply2(RandomFeatureConfiguration::new, WeightedPlacedFeature.CODEC.listOf().fieldOf("features").forGetter(p_161053_ -> p_161053_.features), PlacedFeature.CODEC.fieldOf("default").forGetter(p_204816_ -> p_204816_.defaultFeature)));

    public final List<WeightedPlacedFeature> features;

    public final Holder<PlacedFeature> defaultFeature;

    public RandomFeatureConfiguration(List<WeightedPlacedFeature> listWeightedPlacedFeature0, Holder<PlacedFeature> holderPlacedFeature1) {
        this.features = listWeightedPlacedFeature0;
        this.defaultFeature = holderPlacedFeature1;
    }

    @Override
    public Stream<ConfiguredFeature<?, ?>> getFeatures() {
        return Stream.concat(this.features.stream().flatMap(p_204814_ -> p_204814_.feature.value().getFeatures()), this.defaultFeature.value().getFeatures());
    }
}