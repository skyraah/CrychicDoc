package net.minecraft.world.level.levelgen.feature.configurations;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class ProbabilityFeatureConfiguration implements FeatureConfiguration {

    public static final Codec<ProbabilityFeatureConfiguration> CODEC = RecordCodecBuilder.create(p_67866_ -> p_67866_.group(Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter(p_161045_ -> p_161045_.probability)).apply(p_67866_, ProbabilityFeatureConfiguration::new));

    public final float probability;

    public ProbabilityFeatureConfiguration(float float0) {
        this.probability = float0;
    }
}