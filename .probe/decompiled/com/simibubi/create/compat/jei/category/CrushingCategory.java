package com.simibubi.create.compat.jei.category;

import com.simibubi.create.compat.jei.category.animations.AnimatedCrushingWheels;
import com.simibubi.create.content.kinetics.crusher.AbstractCrushingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingOutput;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.foundation.gui.AllGuiTextures;
import com.simibubi.create.foundation.ponder.ui.LayoutHelper;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.crafting.Ingredient;

@ParametersAreNonnullByDefault
public class CrushingCategory extends CreateRecipeCategory<AbstractCrushingRecipe> {

    private final AnimatedCrushingWheels crushingWheels = new AnimatedCrushingWheels();

    public CrushingCategory(CreateRecipeCategory.Info<AbstractCrushingRecipe> info) {
        super(info);
    }

    public void setRecipe(IRecipeLayoutBuilder builder, AbstractCrushingRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 51, 3).setBackground(getRenderedSlot(), -1, -1).addIngredients((Ingredient) recipe.m_7527_().get(0));
        int xOffset = this.getBackground().getWidth() / 2;
        int yOffset = 86;
        this.layoutOutput(recipe).forEach(layoutEntry -> builder.addSlot(RecipeIngredientRole.OUTPUT, xOffset + layoutEntry.posX() + 1, yOffset + layoutEntry.posY() + 1).setBackground(getRenderedSlot(layoutEntry.output()), -1, -1).addItemStack(layoutEntry.output().getStack()).addTooltipCallback(addStochasticTooltip(layoutEntry.output())));
    }

    private List<CrushingCategory.LayoutEntry> layoutOutput(ProcessingRecipe<?> recipe) {
        int size = recipe.getRollableResults().size();
        List<CrushingCategory.LayoutEntry> positions = new ArrayList(size);
        LayoutHelper layout = LayoutHelper.centeredHorizontal(size, 1, 18, 18, 1);
        for (ProcessingOutput result : recipe.getRollableResults()) {
            positions.add(new CrushingCategory.LayoutEntry(result, layout.getX(), layout.getY()));
            layout.next();
        }
        return positions;
    }

    public void draw(AbstractCrushingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics graphics, double mouseX, double mouseY) {
        AllGuiTextures.JEI_DOWN_ARROW.render(graphics, 72, 7);
        this.crushingWheels.draw(graphics, 62, 59);
    }

    private static record LayoutEntry(ProcessingOutput output, int posX, int posY) {
    }
}