package me.jellysquid.mods.sodium.mixin.features.render.entity.fast_render;

import java.util.Set;
import me.jellysquid.mods.sodium.client.model.ModelCuboidAccessor;
import me.jellysquid.mods.sodium.client.render.immediate.model.ModelCuboid;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.core.Direction;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({ ModelPart.Cube.class })
public class CuboidMixin implements ModelCuboidAccessor {

    @Mutable
    @Shadow
    @Final
    private float minX;

    @Unique
    private ModelCuboid sodium$cuboid;

    @Unique
    private ModelCuboid embeddium$simpleCuboid;

    @Redirect(method = { "<init>" }, at = @At(value = "FIELD", opcode = 181, target = "Lnet/minecraft/client/model/geom/ModelPart$Cube;minX:F", ordinal = 0))
    private void onInit(ModelPart.Cube instance, float value, int u, int v, float x, float y, float z, float sizeX, float sizeY, float sizeZ, float extraX, float extraY, float extraZ, boolean mirror, float textureWidth, float textureHeight, Set<Direction> renderDirections) {
        this.sodium$cuboid = new ModelCuboid(u, v, x, y, z, sizeX, sizeY, sizeZ, extraX, extraY, extraZ, mirror, textureWidth, textureHeight, renderDirections);
        this.embeddium$simpleCuboid = this.getClass() == ModelPart.Cube.class ? this.sodium$cuboid : null;
        this.minX = value;
    }

    @Override
    public ModelCuboid sodium$copy() {
        return this.sodium$cuboid;
    }

    @Nullable
    @Override
    public ModelCuboid embeddium$getSimpleCuboid() {
        return this.embeddium$simpleCuboid;
    }
}