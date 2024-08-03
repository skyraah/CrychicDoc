package net.liopyu.entityjs.builders.modification;

import dev.latvian.mods.kubejs.event.EventJS;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import net.liopyu.entityjs.util.ContextUtils;
import net.liopyu.entityjs.util.EntityJSHelperClass;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModifyEntityBuilder extends EventJS {

    public final EntityType<?> entityType;

    public transient Boolean repositionEntityAfterLoad;

    public transient Object mainArm;

    public transient Function<ContextUtils.EPassengerEntityContext, Object> canAddPassenger;

    public transient Function<Entity, Object> setBlockJumpFactor;

    public transient Object setSwimSound;

    public transient Function<Entity, Object> isFlapping;

    public transient Object setSwimSplashSound;

    public transient Function<ContextUtils.LineOfSightContext, Object> isAlliedTo;

    public transient Consumer<ContextUtils.PositionRiderContext> positionRider;

    public transient Function<Entity, Object> isFreezing;

    public transient Function<ContextUtils.ECollidingEntityContext, Object> canCollideWith;

    public transient Function<ContextUtils.EMayInteractContext, Object> mayInteract;

    public transient Function<ContextUtils.ECanTrampleContext, Object> canTrample;

    public transient Consumer<Entity> onRemovedFromWorld;

    public transient Consumer<Entity> onLivingJump;

    public transient Consumer<ContextUtils.EThunderHitContext> thunderHit;

    public transient Function<ContextUtils.EDamageContext, Object> isInvulnerableTo;

    public transient Function<Entity, Object> dampensVibrations;

    public transient Consumer<ContextUtils.EntityPlayerContext> playerTouch;

    public transient Function<Entity, Object> showVehicleHealth;

    public transient Consumer<Entity> lavaHurt;

    public transient Consumer<Entity> onFlap;

    public transient Consumer<Entity> onAddedToWorld;

    public transient Consumer<Entity> onClientRemoval;

    public transient Consumer<ContextUtils.MobInteractContext> onInteract;

    public transient Function<Entity, Object> setMaxFallDistance;

    public transient Consumer<ContextUtils.LerpToContext> lerpTo;

    public transient Function<ContextUtils.EntitySqrDistanceContext, Object> shouldRenderAtSqrDistance;

    public transient Consumer<Entity> tick;

    public transient Consumer<ContextUtils.MovementContext> move;

    public transient Boolean isAttackable;

    public transient Function<Entity, Object> canChangeDimensions;

    public transient Function<Entity, Object> blockSpeedFactor;

    public transient boolean isPickable;

    public transient Consumer<ContextUtils.EEntityFallDamageContext> onFall;

    public transient Consumer<Entity> onSprint;

    public transient Consumer<Entity> onStopRiding;

    public transient Consumer<Entity> rideTick;

    public transient Function<Entity, Object> canFreeze;

    public transient Function<Entity, Object> isCurrentlyGlowing;

    public transient Boolean isPushable;

    public transient Function<Entity, Object> myRidingOffset;

    public transient Boolean controlledByFirstPassenger;

    public static Map<EntityType<?>, ModifyEntityBuilder> builderMap = new HashMap();

    public ModifyEntityBuilder(EntityType<?> entityType) {
        this.entityType = entityType;
    }

    @Info("Will output the entity type as well as the builder pertaining to the entity type \n \nExample usage: \n```javascript\nEntityJSEvents.modifyEntity(event => {\n    event.modify(\"minecraft:zombie\", builder => {\n        console.log(builder.builderType())\n    })\n})\n```\n")
    public String builderType() {
        return "[EntityJS]: Builder for " + this.getEntityType().toString() + ": " + this.getClass().getSimpleName();
    }

    @HideFromJS
    public static ModifyEntityBuilder getOrCreate(EntityType<?> type) {
        if (!builderMap.containsKey(type)) {
            ModifyEntityBuilder builder = new ModifyEntityBuilder(type);
            builderMap.put(type, builder);
        }
        return (ModifyEntityBuilder) builderMap.get(type);
    }

    public EntityType<?> getEntityType() {
        return this.entityType;
    }

    @Info("Boolean determining if the entity is controlled by the first passenger\n\nExample usage:\n```javascript\nmodifyBuilder.controlledByFirstPassenger(true)\n```\n")
    public ModifyEntityBuilder controlledByFirstPassenger(boolean controlledByFirstPassenger) {
        this.controlledByFirstPassenger = controlledByFirstPassenger;
        return this;
    }

    @Info("Function which sets the offset for riding on the entity.\n\n@param myRidingOffset The offset value for riding on the mob.\nDefaults to 0.0.\n\nExample usage:\n```javascript\nmodifyBuilder.myRidingOffset(entity => {\n    //Use the provided context about the entity to determine the riding offset of the passengers\n    return 5 //Some double value;\n})\n```\n")
    public ModifyEntityBuilder myRidingOffset(Function<Entity, Object> myRidingOffset) {
        this.myRidingOffset = myRidingOffset;
        return this;
    }

    @Info("Boolean determining if the part entity is pickable.\n\nExample usage:\n```javascript\nmodifyBuilder.isPickable(true)\n```\n")
    public ModifyEntityBuilder isPickable(boolean isPickable) {
        this.isPickable = isPickable;
        return this;
    }

    @Info("Function determining if the entity may collide with another entity\nusing the ContextUtils.CollidingEntityContext which has this entity and the\none colliding with this entity.\n\nExample usage:\n```javascript\nmodifyBuilder.canCollideWith(context => {\n    return true //Some Boolean value determining whether the entity may collide with another\n});\n```\n")
    public ModifyEntityBuilder canCollideWith(Function<ContextUtils.ECollidingEntityContext, Object> canCollideWith) {
        this.canCollideWith = canCollideWith;
        return this;
    }

    @Info("Defines in what condition the entity will start freezing.\n\nExample usage:\n```javascript\nmodifyBuilder.isFreezing(entity => {\n    return true;\n});\n```\n")
    public ModifyEntityBuilder isFreezing(Function<Entity, Object> isFreezing) {
        this.isFreezing = isFreezing;
        return this;
    }

    @Info("Sets the block jump factor for the entity.\n\nExample usage:\n```javascript\nmodifyBuilder.setBlockJumpFactor(entity => {\n    //Set the jump factor for the entity through context\n    return 1 //some float value;\n});\n```\n")
    public ModifyEntityBuilder setBlockJumpFactor(Function<Entity, Object> blockJumpFactor) {
        this.setBlockJumpFactor = blockJumpFactor;
        return this;
    }

    @Info("Sets whether the entity is pushable.\n\nExample usage:\n```javascript\nmodifyBuilder.isPushable(true);\n```\n")
    public ModifyEntityBuilder isPushable(boolean b) {
        this.isPushable = b;
        return this;
    }

    @Info("@param positionRider A consumer determining the position of rider/riders.\n\n    Example usage:\n    ```javascript\n    modifyBuilder.positionRider(context => {\n        const {entity, passenger, moveFunction} = context\n    });\n    ```\n")
    public ModifyEntityBuilder positionRider(Consumer<ContextUtils.PositionRiderContext> builderConsumer) {
        this.positionRider = builderConsumer;
        return this;
    }

    @Info("Sets a predicate to determine if a passenger can be added to the entity.\n\n@param predicate The predicate to check if a passenger can be added.\n\nExample usage:\n```javascript\nmodifyBuilder.canAddPassenger(context => {\n    // Custom logic to determine if a passenger can be added to the entity\n    return true;\n});\n```\n")
    public ModifyEntityBuilder canAddPassenger(Function<ContextUtils.EPassengerEntityContext, Object> predicate) {
        this.canAddPassenger = predicate;
        return this;
    }

    @Info("Sets the swim sound for the entity using a string representation.\n\nExample usage:\n```javascript\nmodifyBuilder.setSwimSound(\"minecraft:entity.generic.swim\");\n```\n")
    public ModifyEntityBuilder setSwimSound(Object sound) {
        if (sound instanceof String) {
            this.setSwimSound = new ResourceLocation((String) sound);
        } else if (sound instanceof ResourceLocation) {
            this.setSwimSound = (ResourceLocation) sound;
        } else {
            EntityJSHelperClass.logErrorMessageOnce("[EntityJS]: Invalid value for setSwimSound. Value: " + sound + ". Must be a ResourceLocation or String. Example: \"minecraft:entity.generic.swim\"");
            this.setSwimSound = new ResourceLocation("minecraft:entity.generic.swim");
        }
        return this;
    }

    @Info("Sets the swim splash sound for the entity using either a string representation or a ResourceLocation object.\n\nExample usage:\n```javascript\nmodifyBuilder.setSwimSplashSound(\"minecraft:entity.generic.splash\");\n```\n")
    public ModifyEntityBuilder setSwimSplashSound(Object sound) {
        if (sound instanceof String) {
            this.setSwimSplashSound = new ResourceLocation((String) sound);
        } else if (sound instanceof ResourceLocation) {
            this.setSwimSplashSound = (ResourceLocation) sound;
        } else {
            EntityJSHelperClass.logErrorMessageOnce("[EntityJS]: Invalid value for setSwimSplashSound. Value: " + sound + ". Must be a ResourceLocation or String. Example: \"minecraft:entity.generic.splash\"");
            this.setSwimSplashSound = new ResourceLocation("minecraft", "entity/generic/splash");
        }
        return this;
    }

    @Info("Sets a function to determine the block speed factor of the entity.\nThe provided Function accepts a {@link Entity} parameter,\nrepresenting the entity whose block speed factor is being determined.\nIt returns a Float representing the block speed factor.\n\nExample usage:\n```javascript\nmodifyBuilder.blockSpeedFactor(entity => {\n    // Define logic to calculate and return the block speed factor for the entity\n    // Use information about the Entity provided by the context.\n    return // Some Float value representing the block speed factor;\n});\n```\n")
    public ModifyEntityBuilder blockSpeedFactor(Function<Entity, Object> callback) {
        this.blockSpeedFactor = callback;
        return this;
    }

    @Info("Sets a function to determine whether the entity is currently flapping.\nThe provided Function accepts a {@link Entity} parameter,\nrepresenting the entity whose flapping status is being determined.\nIt returns a Boolean indicating whether the entity is flapping.\n\nExample usage:\n```javascript\nmodifyBuilder.isFlapping(entity => {\n    // Define logic to determine whether the entity is currently flapping\n    // Use information about the Entity provided by the context.\n    return // Some Boolean value indicating whether the entity is flapping;\n});\n```\n")
    public ModifyEntityBuilder isFlapping(Function<Entity, Object> b) {
        this.isFlapping = b;
        return this;
    }

    @Info("Sets a callback function to be executed when the entity is added to the world.\nThe provided Consumer accepts a {@link Entity} parameter,\nrepresenting the entity that is added to the world.\n\nExample usage:\n```javascript\nmodifyBuilder.onAddedToWorld(entity => {\n    // Define custom logic for handling when the entity is added to the world\n    // Use information about the Entity provided by the context.\n});\n```\n")
    public ModifyEntityBuilder onAddedToWorld(Consumer<Entity> onAddedToWorldCallback) {
        this.onAddedToWorld = onAddedToWorldCallback;
        return this;
    }

    @Info("Sets whether to reposition the entity after loading.\n\nExample usage:\n```javascript\nmodifyBuilder.repositionEntityAfterLoad(true);\n```\n")
    public ModifyEntityBuilder repositionEntityAfterLoad(boolean customRepositionEntityAfterLoad) {
        this.repositionEntityAfterLoad = customRepositionEntityAfterLoad;
        return this;
    }

    @Info("Sets a callback function to be executed when the entity starts sprinting.\nThe provided Consumer accepts a {@link Entity} parameter,\nrepresenting the entity that has started sprinting.\n\nExample usage:\n```javascript\nmodifyBuilder.onSprint(entity => {\n    // Define custom logic for handling when the entity starts sprinting\n    // Use information about the Entity provided by the context.\n});\n```\n")
    public ModifyEntityBuilder onSprint(Consumer<Entity> consumer) {
        this.onSprint = consumer;
        return this;
    }

    @Info("Sets a callback function to be executed when the entity stops riding.\nThe provided Consumer accepts a {@link Entity} parameter,\nrepresenting the entity that has stopped being ridden.\n\nExample usage:\n```javascript\nmodifyBuilder.onStopRiding(entity => {\n    // Define custom logic for handling when the entity stops being ridden\n    // Use information about the Entity provided by the context.\n});\n```\n")
    public ModifyEntityBuilder onStopRiding(Consumer<Entity> callback) {
        this.onStopRiding = callback;
        return this;
    }

    @Info("Sets a callback function to be executed during each tick when the entity is being ridden.\nThe provided Consumer accepts a {@link Entity} parameter,\nrepresenting the entity that is being ridden.\n\nExample usage:\n```javascript\nmodifyBuilder.rideTick(entity => {\n    // Define custom logic for handling each tick when the entity is being ridden\n    // Use information about the Entity provided by the context.\n});\n```\n")
    public ModifyEntityBuilder rideTick(Consumer<Entity> callback) {
        this.rideTick = callback;
        return this;
    }

    @Info("Sets a predicate function to determine whether the entity can undergo freezing.\nThe provided Predicate accepts a {@link Entity} parameter,\nrepresenting the entity that may be subjected to freezing.\n\nExample usage:\n```javascript\nmodifyBuilder.canFreeze(entity => {\n    // Define the conditions for the entity to be able to freeze\n    // Use information about the Entity provided by the context.\n    return true //someBoolean;\n});\n```\n")
    public ModifyEntityBuilder canFreeze(Function<Entity, Object> predicate) {
        this.canFreeze = predicate;
        return this;
    }

    @Info("Sets a predicate function to determine whether the entity is currently glowing.\nThe provided Predicate accepts a {@link Entity} parameter,\nrepresenting the entity that may be checked for its glowing state.\n\nExample usage:\n```javascript\nmodifyBuilder.isCurrentlyGlowing(entity => {\n    // Define the conditions to check if the entity is currently glowing\n    // Use information about the Entity provided by the context.\n    const isGlowing = // Some boolean condition to check if the entity is glowing;\n    return isGlowing;\n});\n```\n")
    public ModifyEntityBuilder isCurrentlyGlowing(Function<Entity, Object> predicate) {
        this.isCurrentlyGlowing = predicate;
        return this;
    }

    @Info("Sets the minimum fall distance for the entity before taking damage.\n\nExample usage:\n```javascript\nmodifyBuilder.setMaxFallDistance(entity => {\n    // Define custom logic to determine the maximum fall distance\n    // Use information about the Entity provided by the context.\n    return 3;\n});\n```\n")
    public ModifyEntityBuilder setMaxFallDistance(Function<Entity, Object> maxFallDistance) {
        this.setMaxFallDistance = maxFallDistance;
        return this;
    }

    @Info("Sets a callback function to be executed when the entity is removed on the client side.\nThe provided Consumer accepts a {@link Entity} parameter,\nrepresenting the entity that is being removed on the client side.\n\nExample usage:\n```javascript\nmodifyBuilder.onClientRemoval(entity => {\n    // Define custom logic for handling the removal of the entity on the client side\n    // Use information about the Entity provided by the context.\n});\n```\n")
    public ModifyEntityBuilder onClientRemoval(Consumer<Entity> consumer) {
        this.onClientRemoval = consumer;
        return this;
    }

    @Info("Sets a callback function to be executed when the entity is hurt by lava.\nThe provided Consumer accepts a {@link Entity} parameter,\nrepresenting the entity that is affected by lava.\n\nExample usage:\n```javascript\nmodifyBuilder.lavaHurt(entity => {\n    // Define custom logic for handling the entity being hurt by lava\n    // Use information about the Entity provided by the context.\n});\n```\n")
    public ModifyEntityBuilder lavaHurt(Consumer<Entity> consumer) {
        this.lavaHurt = consumer;
        return this;
    }

    @Info("Sets a callback function to be executed when the entity performs a flap action.\nThe provided Consumer accepts a {@link Entity} parameter,\nrepresenting the entity that is flapping.\n\nExample usage:\n```javascript\nmodifyBuilder.onFlap(entity => {\n    // Define custom logic for handling the entity's flap action\n    // Use information about the Entity provided by the context.\n});\n```\n")
    public ModifyEntityBuilder onFlap(Consumer<Entity> consumer) {
        this.onFlap = consumer;
        return this;
    }

    @Info("Sets a predicate to determine whether the living entity dampens vibrations.\n\n@param predicate The predicate to determine whether the living entity dampens vibrations.\n\nThe predicate should take a Entity as a parameter and return a boolean value indicating whether the living entity dampens vibrations.\n\nExample usage:\n```javascript\nbaseEntityBuilder.dampensVibrations(entity => {\n    // Determine whether the living entity dampens vibrations\n    // Return true if the entity dampens vibrations, false otherwise\n});\n```\n")
    public ModifyEntityBuilder dampensVibrations(Function<Entity, Object> predicate) {
        this.dampensVibrations = predicate;
        return this;
    }

    @Info("Sets a predicate to determine whether to show the vehicle health for the living entity.\n\n@param predicate The predicate to determine whether to show the vehicle health.\n\nThe predicate should take a Entity as a parameter and return a boolean value indicating whether to show the vehicle health.\n\nExample usage:\n```javascript\nbaseEntityBuilder.showVehicleHealth(entity => {\n    // Determine whether to show the vehicle health for the living entity\n    // Return true to show the vehicle health, false otherwise\n});\n```\n")
    public ModifyEntityBuilder showVehicleHealth(Function<Entity, Object> predicate) {
        this.showVehicleHealth = predicate;
        return this;
    }

    @Info("Sets a callback function to be executed when the entity is hit by thunder.\nThe provided Consumer accepts a {@link ContextUtils.ThunderHitContext} parameter,\nrepresenting the context of the entity being hit by thunder.\n\nExample usage:\n```javascript\nmodifyBuilder.thunderHit(context => {\n    // Define custom logic for handling the entity being hit by thunder\n    // Use information about the ThunderHitContext provided by the context.\n});\n```\n")
    public ModifyEntityBuilder thunderHit(Consumer<ContextUtils.EThunderHitContext> consumer) {
        this.thunderHit = consumer;
        return this;
    }

    @Info("Sets a predicate function to determine whether the entity is invulnerable to a specific type of damage.\nThe provided Predicate accepts a {@link ContextUtils.DamageContext} parameter,\nrepresenting the context of the damage, and returns a boolean indicating invulnerability.\n\nExample usage:\n```javascript\nmodifyBuilder.isInvulnerableTo(context => {\n    // Define conditions for the entity to be invulnerable to the specific type of damage\n    // Use information about the DamageContext provided by the context.\n    return true // Some boolean condition indicating if the entity has invulnerability to the damage type;\n});\n```\n")
    public ModifyEntityBuilder isInvulnerableTo(Function<ContextUtils.EDamageContext, Object> predicate) {
        this.isInvulnerableTo = predicate;
        return this;
    }

    @Info("Sets a predicate function to determine whether the entity can change dimensions.\nThe provided Predicate accepts a {@link Entity} parameter,\nrepresenting the entity that may attempt to change dimensions.\n\nExample usage:\n```javascript\nmodifyBuilder.canChangeDimensions(entity => {\n    // Define the conditions for the entity to be able to change dimensions\n    // Use information about the Entity provided by the context.\n    return false // Some boolean condition indicating if the entity can change dimensions;\n});\n```\n")
    public ModifyEntityBuilder canChangeDimensions(Function<Entity, Object> supplier) {
        this.canChangeDimensions = supplier;
        return this;
    }

    @Info("Sets a predicate function to determine whether the entity may interact with something.\nThe provided Predicate accepts a {@link ContextUtils.MayInteractContext} parameter,\nrepresenting the context of the potential interaction, and returns a boolean.\n\nExample usage:\n```javascript\nmodifyBuilder.mayInteract(context => {\n    // Define conditions for the entity to be allowed to interact\n    // Use information about the MayInteractContext provided by the context.\n    return false // Some boolean condition indicating if the entity may interact;\n});\n```\n")
    public ModifyEntityBuilder mayInteract(Function<ContextUtils.EMayInteractContext, Object> predicate) {
        this.mayInteract = predicate;
        return this;
    }

    @Info("Sets a predicate function to determine whether the entity can trample or step on something.\nThe provided Predicate accepts a {@link ContextUtils.CanTrampleContext} parameter,\nrepresenting the context of the potential trampling action, and returns a boolean.\n\nExample usage:\n```javascript\nmodifyBuilder.canTrample(context => {\n    // Define conditions for the entity to be allowed to trample\n    // Use information about the CanTrampleContext provided by the context.\n    return false // Some boolean condition indicating if the entity can trample;\n});\n```\n")
    public ModifyEntityBuilder canTrample(Function<ContextUtils.ECanTrampleContext, Object> predicate) {
        this.canTrample = predicate;
        return this;
    }

    @Info("Sets a callback function to be executed when the entity is removed from the world.\nThe provided Consumer accepts a {@link Entity} parameter,\nrepresenting the entity that is being removed from the world.\n\nExample usage:\n```javascript\nmodifyBuilder.onRemovedFromWorld(entity => {\n    // Define custom logic for handling the removal of the entity from the world\n    // Use information about the Entity provided by the context.\n});\n```\n")
    public ModifyEntityBuilder onRemovedFromWorld(Consumer<Entity> consumer) {
        this.onRemovedFromWorld = consumer;
        return this;
    }

    @Info("Sets a callback function to be executed when the entity falls and takes damage.\nThe provided Consumer accepts a {@link ContextUtils.EEntityFallDamageContext} parameter,\nrepresenting the context of the entity falling and taking fall damage.\n\nExample usage:\n```javascript\nmodifyBuilder.onFall(context => {\n    // Define custom logic for handling when the entity falls and takes damage\n    // Use information about the EEntityFallDamageContext provided by the context.\n});\n```\n")
    public ModifyEntityBuilder onFall(Consumer<ContextUtils.EEntityFallDamageContext> c) {
        this.onFall = c;
        return this;
    }

    @HideFromJS
    public static MobCategory stringToMobCategory(String category) {
        return switch(category) {
            case "monster" ->
                MobCategory.MONSTER;
            case "creature" ->
                MobCategory.CREATURE;
            case "ambient" ->
                MobCategory.AMBIENT;
            case "water_creature" ->
                MobCategory.WATER_CREATURE;
            case "misc" ->
                MobCategory.MISC;
            default ->
                MobCategory.MISC;
        };
    }

    @Info("Sets a consumer to handle lerping (linear interpolation) of the entity's position.\n\n@param lerpTo Consumer accepting a {@link ContextUtils.LerpToContext} parameter,\n                providing information and control over the lerping process.\n\nExample usage:\n```javascript\nmodifyBuilder.lerpTo(context => {\n    // Custom logic for lerping the entity's position\n    // Access information about the lerping process using the provided context.\n});\n```\n")
    public ModifyEntityBuilder lerpTo(Consumer<ContextUtils.LerpToContext> consumer) {
        this.lerpTo = consumer;
        return this;
    }

    @Info("Sets a function to determine whether the entity should render at a squared distance.\n\n@param shouldRenderAtSqrDistance Function accepting a {@link ContextUtils.EntitySqrDistanceContext} parameter,\n                 defining the conditions under which the entity should render.\n\nExample usage:\n```javascript\nmodifyBuilder.shouldRenderAtSqrDistance(context => {\n    // Custom logic to determine whether the entity should render\n    // Access information about the distance using the provided context.\n    return true;\n});\n```\n")
    public ModifyEntityBuilder shouldRenderAtSqrDistance(Function<ContextUtils.EntitySqrDistanceContext, Object> func) {
        this.shouldRenderAtSqrDistance = func;
        return this;
    }

    @Info("Sets whether the entity is attackable or not.\n\n@param isAttackable Boolean value indicating whether the entity is attackable.\n\nExample usage:\n```javascript\nmodifyBuilder.isAttackable(true);\n```\n")
    public ModifyEntityBuilder isAttackable(boolean b) {
        this.isAttackable = b;
        return this;
    }

    @Info("Sets a callback function to be executed when a player touches the entity.\nThe provided Consumer accepts a {@link ContextUtils.EntityPlayerContext} parameter,\nrepresenting the context of the player's interaction with the entity.\n\nExample usage:\n```javascript\nmodifyBuilder.playerTouch(context => {\n    // Custom logic to handle the player's touch interaction with the entity\n    // Access information about the interaction using the provided context.\n});\n```\n")
    public ModifyEntityBuilder playerTouch(Consumer<ContextUtils.EntityPlayerContext> consumer) {
        this.playerTouch = consumer;
        return this;
    }

    @Info("Sets a callback function to be executed when the entity performs a movement action.\nThe provided Consumer accepts a {@link ContextUtils.MovementContext} parameter,\nrepresenting the context of the entity's movement.\n\nExample usage:\n```javascript\nmodifyBuilder.move(context => {\n    // Custom logic to handle the entity's movement action\n    // Access information about the movement using the provided context.\n});\n```\n")
    public ModifyEntityBuilder move(Consumer<ContextUtils.MovementContext> consumer) {
        this.move = consumer;
        return this;
    }

    @Info("Sets a callback function to be executed on each tick for the entity.\n\n@param tick A Consumer accepting a {@link Entity} parameter, defining the behavior to be executed on each tick.\n\nExample usage:\n```javascript\nmodifyBuilder.tick(entity => {\n    // Custom logic to be executed on each tick of the entity.\n    // Access information about the entity using the provided parameter.\n});\n```\n")
    public ModifyEntityBuilder tick(Consumer<Entity> consumer) {
        this.tick = consumer;
        return this;
    }
}