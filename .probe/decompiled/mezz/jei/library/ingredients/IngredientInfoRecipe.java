package mezz.jei.library.ingredients;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.ingredients.ITypedIngredient;
import mezz.jei.api.recipe.vanilla.IJeiIngredientInfoRecipe;
import mezz.jei.api.runtime.IIngredientManager;
import mezz.jei.common.util.MathUtil;
import mezz.jei.common.util.StringUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;

public class IngredientInfoRecipe implements IJeiIngredientInfoRecipe {

    public static final int recipeWidth = 160;

    public static final int recipeHeight = 125;

    public static final int lineSpacing = 2;

    private final List<FormattedText> description;

    private final List<ITypedIngredient<?>> ingredients;

    public static <T> List<IJeiIngredientInfoRecipe> create(IIngredientManager ingredientManager, List<T> ingredients, IIngredientType<T> ingredientType, Component... descriptionComponents) {
        List<ITypedIngredient<?>> typedIngredients = ingredients.stream().map(ix -> TypedIngredient.createAndFilterInvalid(ingredientManager, ingredientType, (T) ix)).flatMap(Optional::stream).toList();
        List<IJeiIngredientInfoRecipe> recipes = new ArrayList();
        List<FormattedText> descriptionLines = StringUtil.expandNewlines(descriptionComponents);
        descriptionLines = StringUtil.splitLines(descriptionLines, 160);
        int lineCount = descriptionLines.size();
        Minecraft minecraft = Minecraft.getInstance();
        int maxLinesPerPage = 105 / (9 + 2);
        int pageCount = MathUtil.divideCeil(lineCount, maxLinesPerPage);
        for (int i = 0; i < pageCount; i++) {
            int startLine = i * maxLinesPerPage;
            int endLine = Math.min((i + 1) * maxLinesPerPage, lineCount);
            List<FormattedText> description = descriptionLines.subList(startLine, endLine);
            IngredientInfoRecipe recipe = new IngredientInfoRecipe(typedIngredients, description);
            recipes.add(recipe);
        }
        return recipes;
    }

    private IngredientInfoRecipe(List<ITypedIngredient<?>> ingredients, List<FormattedText> description) {
        this.description = description;
        this.ingredients = ingredients;
    }

    @Override
    public List<FormattedText> getDescription() {
        return this.description;
    }

    @Override
    public List<ITypedIngredient<?>> getIngredients() {
        return this.ingredients;
    }
}