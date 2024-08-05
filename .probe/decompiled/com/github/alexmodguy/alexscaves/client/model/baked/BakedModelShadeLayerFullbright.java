package com.github.alexmodguy.alexscaves.client.model.baked;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.BakedModelWrapper;
import net.minecraftforge.client.model.data.ModelData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BakedModelShadeLayerFullbright extends BakedModelWrapper {

    public BakedModelShadeLayerFullbright(BakedModel originalModel) {
        super(originalModel);
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @NotNull RandomSource rand, @NotNull ModelData extraData, @Nullable RenderType renderType) {
        return state == null ? this.originalModel.getQuads(state, side, rand, extraData, renderType) : transformUnshadedQuad(this.originalModel.getQuads(state, side, rand, extraData, renderType));
    }

    private static List<BakedQuad> transformUnshadedQuad(List<BakedQuad> oldQuads) {
        List<BakedQuad> quads = new ArrayList(oldQuads);
        if (!quads.isEmpty()) {
            quads.replaceAll(quad -> quad.isShade() ? quad : setFullbright(quad));
        }
        return quads;
    }

    private static BakedQuad setFullbright(BakedQuad quad) {
        int[] vertexData = (int[]) quad.getVertices().clone();
        int step = vertexData.length / 4;
        vertexData[6] = 15728880;
        vertexData[6 + step] = 15728880;
        vertexData[6 + 2 * step] = 15728880;
        vertexData[6 + 3 * step] = 15728880;
        return new BakedQuad(vertexData, quad.getTintIndex(), quad.getDirection(), quad.getSprite(), quad.isShade());
    }
}