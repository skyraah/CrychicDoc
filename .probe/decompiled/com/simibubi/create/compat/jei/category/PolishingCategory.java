package com.simibubi.create.compat.jei.category;

import com.simibubi.create.AllItems;
import com.simibubi.create.content.equipment.sandPaper.SandPaperPolishingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingOutput;
import com.simibubi.create.foundation.gui.AllGuiTextures;
import com.simibubi.create.foundation.gui.element.GuiGameElement;
import javax.annotation.ParametersAreNonnullByDefault;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

@ParametersAreNonnullByDefault
public class PolishingCategory extends CreateRecipeCategory<SandPaperPolishingRecipe> {

    private final ItemStack renderedSandpaper = AllItems.SAND_PAPER.asStack();

    public PolishingCategory(CreateRecipeCategory.Info<SandPaperPolishingRecipe> info) {
        super(info);
    }

    public void setRecipe(IRecipeLayoutBuilder builder, SandPaperPolishingRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 27, 29).setBackground(getRenderedSlot(), -1, -1).addIngredients((Ingredient) recipe.m_7527_().get(0));
        ProcessingOutput output = (ProcessingOutput) recipe.getRollableResults().get(0);
        builder.addSlot(RecipeIngredientRole.OUTPUT, 132, 29).setBackground(getRenderedSlot(output), -1, -1).addItemStack(output.getStack()).addTooltipCallback(addStochasticTooltip(output));
    }

    public void draw(SandPaperPolishingRecipe recipe, IRecipeSlotsView iRecipeSlotsView, GuiGraphics graphics, double mouseX, double mouseY) {
        AllGuiTextures.JEI_SHADOW.render(graphics, 61, 21);
        AllGuiTextures.JEI_LONG_ARROW.render(graphics, 52, 32);
        NonNullList<Ingredient> ingredients = recipe.m_7527_();
        ItemStack[] matchingStacks = ingredients.get(0).getItems();
        if (matchingStacks.length != 0) {
            CompoundTag tag = this.renderedSandpaper.getOrCreateTag();
            tag.put("Polishing", matchingStacks[0].serializeNBT());
            tag.putBoolean("JEI", true);
            GuiGameElement.of(this.renderedSandpaper).<GuiGameElement.GuiRenderBuilder>at((float) (this.getBackground().getWidth() / 2 - 16), 0.0F, 0.0F).scale(2.0).render(graphics);
        }
    }
}