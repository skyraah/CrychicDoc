package net.liopyu.entityjs.builders.nonliving.vanilla;

import net.liopyu.entityjs.builders.nonliving.EntityTypeBuilder;
import net.liopyu.entityjs.entities.nonliving.vanilla.BoatEntityJS;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

public class BoatJSBuilder extends BoatEntityBuilder<BoatEntityJS> {

    public BoatJSBuilder(ResourceLocation i) {
        super(i);
    }

    @Override
    public EntityType<BoatEntityJS> createObject() {
        return new EntityTypeBuilder<BoatEntityJS>(this).get();
    }

    @Override
    public EntityType.EntityFactory<BoatEntityJS> factory() {
        return (type, level) -> new BoatEntityJS(this, type, level);
    }

    @Override
    public AttributeSupplier.Builder getAttributeBuilder() {
        return null;
    }
}