package com.github.alexthe666.alexsmobs.entity.ai;

import com.github.alexthe666.alexsmobs.entity.ISemiAquatic;
import com.github.alexthe666.alexsmobs.misc.AMBlockPos;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;

public class SemiAquaticAIRandomSwimming extends RandomStrollGoal {

    public SemiAquaticAIRandomSwimming(Animal creature, double speed, int chance) {
        super(creature, speed, chance, false);
    }

    @Override
    public boolean canUse() {
        if (!this.f_25725_.m_20160_() && !((ISemiAquatic) this.f_25725_).shouldStopMoving() && this.f_25725_.m_5448_() == null && (this.f_25725_.m_20069_() || this.f_25725_.m_20077_() || !(this.f_25725_ instanceof ISemiAquatic) || ((ISemiAquatic) this.f_25725_).shouldEnterWater())) {
            if (!this.f_25731_ && this.f_25725_.m_217043_().nextInt(this.f_25730_) != 0) {
                return false;
            } else {
                Vec3 vector3d = this.getPosition();
                if (vector3d == null) {
                    return false;
                } else {
                    this.f_25726_ = vector3d.x;
                    this.f_25727_ = vector3d.y;
                    this.f_25728_ = vector3d.z;
                    this.f_25731_ = false;
                    return true;
                }
            }
        } else {
            return false;
        }
    }

    @Nullable
    @Override
    protected Vec3 getPosition() {
        if (this.f_25725_.m_21536_() && this.f_25725_.m_20238_(Vec3.atCenterOf(this.f_25725_.m_21534_())) > (double) (this.f_25725_.m_21535_() * this.f_25725_.m_21535_())) {
            return DefaultRandomPos.getPosTowards(this.f_25725_, 7, 3, Vec3.atBottomCenterOf(this.f_25725_.m_21534_()), 1.0);
        } else {
            if (this.f_25725_.m_217043_().nextFloat() < 0.3F) {
                Vec3 vector3d = this.findSurfaceTarget(this.f_25725_, 15, 7);
                if (vector3d != null) {
                    return vector3d;
                }
            }
            Vec3 vector3d = DefaultRandomPos.getPos(this.f_25725_, 7, 3);
            int i = 0;
            while (vector3d != null && !this.f_25725_.m_9236_().getFluidState(AMBlockPos.fromVec3(vector3d)).is(FluidTags.LAVA) && !this.f_25725_.m_9236_().getBlockState(AMBlockPos.fromVec3(vector3d)).m_60647_(this.f_25725_.m_9236_(), AMBlockPos.fromVec3(vector3d), PathComputationType.WATER) && i++ < 15) {
                vector3d = DefaultRandomPos.getPos(this.f_25725_, 10, 7);
            }
            return vector3d;
        }
    }

    private boolean canJumpTo(BlockPos pos, int dx, int dz, int scale) {
        BlockPos blockpos = pos.offset(dx * scale, 0, dz * scale);
        return this.f_25725_.m_9236_().getFluidState(blockpos).is(FluidTags.LAVA) || this.f_25725_.m_9236_().getFluidState(blockpos).is(FluidTags.WATER) && !this.f_25725_.m_9236_().getBlockState(blockpos).m_280555_();
    }

    private boolean isAirAbove(BlockPos pos, int dx, int dz, int scale) {
        return this.f_25725_.m_9236_().getBlockState(pos.offset(dx * scale, 1, dz * scale)).m_60795_() && this.f_25725_.m_9236_().getBlockState(pos.offset(dx * scale, 2, dz * scale)).m_60795_();
    }

    protected Vec3 findSurfaceTarget(PathfinderMob creature, int i, int i1) {
        BlockPos upPos = creature.m_20183_();
        while (creature.m_9236_().getFluidState(upPos).is(FluidTags.WATER) || creature.m_9236_().getFluidState(upPos).is(FluidTags.LAVA)) {
            upPos = upPos.above();
        }
        return this.isAirAbove(upPos.below(), 0, 0, 0) && this.canJumpTo(upPos.below(), 0, 0, 0) ? new Vec3((double) ((float) upPos.m_123341_() + 0.5F), (double) ((float) upPos.m_123342_() - 1.0F), (double) ((float) upPos.m_123343_() + 0.5F)) : null;
    }
}