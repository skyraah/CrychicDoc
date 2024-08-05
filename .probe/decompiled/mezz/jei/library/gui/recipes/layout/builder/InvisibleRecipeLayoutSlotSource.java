package mezz.jei.library.gui.recipes.layout.builder;

import it.unimi.dsi.fastutil.ints.IntSet;
import java.util.List;
import java.util.stream.Stream;
import mezz.jei.api.gui.builder.IIngredientAcceptor;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.runtime.IIngredientManager;
import mezz.jei.api.runtime.IIngredientVisibility;
import mezz.jei.library.gui.ingredients.RecipeSlots;
import mezz.jei.library.ingredients.IngredientAcceptor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.material.Fluid;

public class InvisibleRecipeLayoutSlotSource implements IRecipeLayoutSlotSource, IIngredientAcceptor<InvisibleRecipeLayoutSlotSource> {

    private final IngredientAcceptor ingredients;

    private final RecipeIngredientRole role;

    public InvisibleRecipeLayoutSlotSource(IIngredientManager ingredientManager, RecipeIngredientRole role) {
        this.ingredients = new IngredientAcceptor(ingredientManager);
        this.role = role;
    }

    public InvisibleRecipeLayoutSlotSource addIngredientsUnsafe(List<?> ingredients) {
        this.ingredients.addIngredientsUnsafe(ingredients);
        return this;
    }

    public <I> InvisibleRecipeLayoutSlotSource addIngredients(IIngredientType<I> ingredientType, List<I> ingredients) {
        this.ingredients.addIngredients(ingredientType, ingredients);
        return this;
    }

    public <I> InvisibleRecipeLayoutSlotSource addIngredient(IIngredientType<I> ingredientType, I ingredient) {
        this.ingredients.addIngredient(ingredientType, ingredient);
        return this;
    }

    public InvisibleRecipeLayoutSlotSource addFluidStack(Fluid fluid, long amount) {
        this.ingredients.addFluidStack(fluid, amount);
        return this;
    }

    public InvisibleRecipeLayoutSlotSource addFluidStack(Fluid fluid, long amount, CompoundTag tag) {
        this.ingredients.addFluidStack(fluid, amount, tag);
        return this;
    }

    @Override
    public RecipeIngredientRole getRole() {
        return this.role;
    }

    @Override
    public void setRecipeSlots(RecipeSlots recipeSlots, IntSet focusMatches, IIngredientVisibility ingredientVisibility) {
    }

    @Override
    public <T> Stream<T> getIngredients(IIngredientType<T> ingredientType) {
        return this.ingredients.getIngredients(ingredientType);
    }

    @Override
    public IntSet getMatches(IFocusGroup focuses) {
        return this.ingredients.getMatches(focuses, this.role);
    }

    @Override
    public int getIngredientCount() {
        return this.ingredients.getAllIngredients().size();
    }

    @Override
    public Stream<IIngredientType<?>> getIngredientTypes() {
        return this.ingredients.getIngredientTypes();
    }
}