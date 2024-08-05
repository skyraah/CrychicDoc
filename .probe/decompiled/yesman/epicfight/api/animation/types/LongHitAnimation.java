package yesman.epicfight.api.animation.types;

import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.model.Armature;

public class LongHitAnimation extends ActionAnimation {

    public LongHitAnimation(float convertTime, String path, Armature armature) {
        super(convertTime, path, armature);
        this.addProperty(AnimationProperty.ActionAnimationProperty.STOP_MOVEMENT, Boolean.valueOf(true));
        this.stateSpectrumBlueprint.clear().newTimePair(0.0F, Float.MAX_VALUE).addState(EntityState.TURNING_LOCKED, true).addState(EntityState.MOVEMENT_LOCKED, true).addState(EntityState.UPDATE_LIVING_MOTION, false).addState(EntityState.CAN_BASIC_ATTACK, false).addState(EntityState.CAN_SKILL_EXECUTION, false).addState(EntityState.INACTION, true).addState(EntityState.HURT_LEVEL, 2);
    }
}