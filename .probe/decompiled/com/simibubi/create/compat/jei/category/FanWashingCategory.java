package com.simibubi.create.compat.jei.category;

import com.simibubi.create.compat.jei.category.animations.AnimatedKinetics;
import com.simibubi.create.content.kinetics.fan.processing.SplashingRecipe;
import com.simibubi.create.foundation.gui.element.GuiGameElement;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.level.material.Fluids;

public class FanWashingCategory extends ProcessingViaFanCategory.MultiOutput<SplashingRecipe> {

    public FanWashingCategory(CreateRecipeCategory.Info<SplashingRecipe> info) {
        super(info);
    }

    @Override
    protected void renderAttachedBlock(GuiGraphics graphics) {
        GuiGameElement.of(Fluids.WATER).scale(24.0).atLocal(0.0, 0.0, 2.0).lighting(AnimatedKinetics.DEFAULT_LIGHTING).render(graphics);
    }
}