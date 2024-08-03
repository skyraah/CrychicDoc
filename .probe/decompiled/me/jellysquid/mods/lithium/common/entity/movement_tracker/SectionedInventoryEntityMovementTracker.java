package me.jellysquid.mods.lithium.common.entity.movement_tracker;

import java.util.ArrayList;
import java.util.List;
import me.jellysquid.mods.lithium.common.util.tuples.WorldSectionBox;
import me.jellysquid.mods.lithium.mixin.block.hopper.EntityTrackingSectionAccessor;
import me.jellysquid.mods.lithium.mixin.util.entity_movement_tracking.ServerEntityManagerAccessor;
import me.jellysquid.mods.lithium.mixin.util.entity_movement_tracking.ServerWorldAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ClassInstanceMultiMap;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.AABB;

public class SectionedInventoryEntityMovementTracker<S> extends SectionedEntityMovementTracker<Entity, S> {

    public SectionedInventoryEntityMovementTracker(WorldSectionBox entityAccessBox, Class<S> clazz) {
        super(entityAccessBox, clazz);
    }

    public static <S> SectionedInventoryEntityMovementTracker<S> registerAt(ServerLevel world, AABB interactionArea, Class<S> clazz) {
        MovementTrackerCache cache = (MovementTrackerCache) ((ServerEntityManagerAccessor) ((ServerWorldAccessor) world).getEntityManager()).getCache();
        WorldSectionBox worldSectionBox = WorldSectionBox.entityAccessBox(world, interactionArea);
        SectionedInventoryEntityMovementTracker<S> tracker = new SectionedInventoryEntityMovementTracker<>(worldSectionBox, clazz);
        tracker = cache.deduplicate(tracker);
        tracker.register(world);
        return tracker;
    }

    public List<S> getEntities(AABB box) {
        ArrayList<S> entities = new ArrayList();
        for (int i = 0; i < this.sortedSections.size(); i++) {
            if (this.sectionVisible[i]) {
                ClassInstanceMultiMap<S> collection = ((EntityTrackingSectionAccessor) this.sortedSections.get(i)).getCollection();
                for (S entity : collection.find(this.clazz)) {
                    Entity inventoryEntity = (Entity) entity;
                    if (inventoryEntity.isAlive() && inventoryEntity.getBoundingBox().intersects(box)) {
                        entities.add(entity);
                    }
                }
            }
        }
        return entities;
    }
}