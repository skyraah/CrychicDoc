package com.simibubi.create.content.kinetics.saw;

import com.jozufozu.flywheel.util.transform.TransformStack;
import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.foundation.blockEntity.behaviour.ValueBoxTransform;
import com.simibubi.create.foundation.utility.VecHelper;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class SawFilterSlot extends ValueBoxTransform {

    @Override
    public Vec3 getLocalOffset(BlockState state) {
        if (state.m_61143_(SawBlock.FACING) != Direction.UP) {
            return null;
        } else {
            int offset = state.m_61143_(SawBlock.FLIPPED) ? -3 : 3;
            Vec3 x = VecHelper.voxelSpace(8.0, 12.5, (double) (8 + offset));
            Vec3 z = VecHelper.voxelSpace((double) (8 + offset), 12.5, 8.0);
            return state.m_61143_(SawBlock.AXIS_ALONG_FIRST_COORDINATE) ? z : x;
        }
    }

    @Override
    public void rotate(BlockState state, PoseStack ms) {
        int yRot = (state.m_61143_(SawBlock.AXIS_ALONG_FIRST_COORDINATE) ? 90 : 0) + (state.m_61143_(SawBlock.FLIPPED) ? 0 : 180);
        ((TransformStack) TransformStack.cast(ms).rotateY((double) yRot)).rotateX(90.0);
    }
}