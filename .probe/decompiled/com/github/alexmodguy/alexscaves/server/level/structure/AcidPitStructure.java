package com.github.alexmodguy.alexscaves.server.level.structure;

import com.github.alexmodguy.alexscaves.server.level.biome.ACBiomeRegistry;
import com.github.alexmodguy.alexscaves.server.level.structure.piece.AcidPitStructurePiece;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureType;

public class AcidPitStructure extends AbstractCaveGenerationStructure {

    private static final int BOWL_WIDTH_RADIUS = 90;

    private static final int BOWL_HEIGHT_RADIUS = 50;

    public static final Codec<AcidPitStructure> CODEC = m_226607_(settings -> new AcidPitStructure(settings));

    public AcidPitStructure(Structure.StructureSettings settings) {
        super(settings, ACBiomeRegistry.TOXIC_CAVES);
    }

    @Override
    protected StructurePiece createPiece(BlockPos offset, BlockPos center, int heightBlocks, int widthBlocks, RandomState randomState) {
        return new AcidPitStructurePiece(offset, center, heightBlocks, widthBlocks);
    }

    @Override
    public int getGenerateYHeight(WorldgenRandom random, int x, int y) {
        return random.m_188503_(30) - 25;
    }

    @Override
    public int getWidthRadius(WorldgenRandom random) {
        return 90;
    }

    @Override
    public int getHeightRadius(WorldgenRandom random, int seaLevel) {
        return 50;
    }

    @Override
    public StructureType<?> type() {
        return ACStructureRegistry.ACID_PIT.get();
    }
}