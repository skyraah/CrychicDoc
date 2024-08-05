package mezz.jei.library.recipes;

import java.util.EnumMap;
import java.util.List;
import java.util.stream.Stream;
import mezz.jei.api.ingredients.IIngredientHelper;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.ingredients.ITypedIngredient;
import mezz.jei.api.ingredients.subtypes.UidContext;
import mezz.jei.api.recipe.IFocus;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.advanced.IRecipeManagerPlugin;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.runtime.IIngredientManager;
import mezz.jei.library.focus.Focus;
import mezz.jei.library.recipes.collect.RecipeMap;
import mezz.jei.library.recipes.collect.RecipeTypeData;
import mezz.jei.library.recipes.collect.RecipeTypeDataMap;

public class InternalRecipeManagerPlugin implements IRecipeManagerPlugin {

    private final IIngredientManager ingredientManager;

    private final RecipeTypeDataMap recipeCategoriesMap;

    private final EnumMap<RecipeIngredientRole, RecipeMap> recipeMaps;

    public InternalRecipeManagerPlugin(IIngredientManager ingredientManager, RecipeTypeDataMap recipeCategoriesMap, EnumMap<RecipeIngredientRole, RecipeMap> recipeMaps) {
        this.ingredientManager = ingredientManager;
        this.recipeCategoriesMap = recipeCategoriesMap;
        this.recipeMaps = recipeMaps;
    }

    @Override
    public <V> List<RecipeType<?>> getRecipeTypes(IFocus<V> focus) {
        IFocus<V> var8 = Focus.checkOne(focus, this.ingredientManager);
        ITypedIngredient<V> ingredient = var8.getTypedValue();
        RecipeIngredientRole role = var8.getRole();
        RecipeMap recipeMap = (RecipeMap) this.recipeMaps.get(role);
        IIngredientType<V> ingredientType = ingredient.getType();
        IIngredientHelper<V> ingredientHelper = this.ingredientManager.getIngredientHelper(ingredientType);
        String ingredientUid = ingredientHelper.getUniqueId(ingredient.getIngredient(), UidContext.Recipe);
        return recipeMap.getRecipeTypes(ingredientUid).toList();
    }

    @Override
    public <T, V> List<T> getRecipes(IRecipeCategory<T> recipeCategory, IFocus<V> focus) {
        IFocus<V> var11 = Focus.checkOne(focus, this.ingredientManager);
        ITypedIngredient<V> ingredient = var11.getTypedValue();
        RecipeIngredientRole role = var11.getRole();
        IIngredientHelper<V> ingredientHelper = this.ingredientManager.getIngredientHelper(ingredient.getType());
        String ingredientUid = ingredientHelper.getUniqueId(ingredient.getIngredient(), UidContext.Recipe);
        RecipeMap recipeMap = (RecipeMap) this.recipeMaps.get(role);
        RecipeType<T> recipeType = recipeCategory.getRecipeType();
        List<T> recipes = recipeMap.getRecipes(recipeType, ingredientUid);
        if (recipeMap.isCatalystForRecipeCategory(recipeType, ingredientUid)) {
            List<T> recipesForCategory = this.getRecipes(recipeCategory);
            return Stream.concat(recipes.stream(), recipesForCategory.stream()).distinct().toList();
        } else {
            return recipes;
        }
    }

    @Override
    public <T> List<T> getRecipes(IRecipeCategory<T> recipeCategory) {
        RecipeType<T> recipeType = recipeCategory.getRecipeType();
        RecipeTypeData<T> recipeTypeData = this.recipeCategoriesMap.get(recipeType);
        return recipeTypeData.getRecipes();
    }
}