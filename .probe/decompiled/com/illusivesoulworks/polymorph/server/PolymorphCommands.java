package com.illusivesoulworks.polymorph.server;

import com.illusivesoulworks.polymorph.PolymorphConstants;
import com.illusivesoulworks.polymorph.platform.Services;
import com.illusivesoulworks.polymorph.server.wrapper.CraftingRecipeWrapper;
import com.illusivesoulworks.polymorph.server.wrapper.RecipeWrapper;
import com.illusivesoulworks.polymorph.server.wrapper.SmithingRecipeWrapper;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import java.util.TreeSet;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;

public class PolymorphCommands {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        int opPermissionLevel = 2;
        LiteralArgumentBuilder<CommandSourceStack> command = (LiteralArgumentBuilder<CommandSourceStack>) Commands.literal("polymorph").requires(player -> player.hasPermission(2));
        command.then(Commands.literal("conflicts").executes(context -> findConflicts((CommandSourceStack) context.getSource())));
        dispatcher.register(command);
    }

    private static int findConflicts(CommandSourceStack source) {
        CompletableFuture.runAsync(() -> {
            source.sendSuccess(() -> Component.translatable("commands.polymorph.conflicts.starting"), true);
            ServerLevel world = source.getLevel();
            RecipeManager recipeManager = world.getRecipeManager();
            List<String> output = new ArrayList();
            int count = 0;
            count += scanRecipes(RecipeType.CRAFTING, output, recipeManager, CraftingRecipeWrapper::new);
            count += scanRecipes(RecipeType.SMELTING, output, recipeManager, RecipeWrapper::new);
            count += scanRecipes(RecipeType.BLASTING, output, recipeManager, RecipeWrapper::new);
            count += scanRecipes(RecipeType.SMOKING, output, recipeManager, RecipeWrapper::new);
            count += scanRecipes(RecipeType.SMITHING, output, recipeManager, SmithingRecipeWrapper::new);
            if (count > 0) {
                try {
                    Files.write(Paths.get(Services.PLATFORM.getGameDir() + "/logs/conflicts.log"), output, StandardCharsets.UTF_8);
                } catch (IOException var6) {
                    PolymorphConstants.LOG.error("Whoops! Something went wrong writing down your conflicts :(");
                    var6.printStackTrace();
                }
            }
            source.sendSuccess(() -> Component.translatable("commands.polymorph.conflicts.success", count), true);
        });
        return 1;
    }

    private static <C extends Container, T extends Recipe<C>> int scanRecipes(RecipeType<T> pType, List<String> pOutput, RecipeManager pRecipeManager, Function<Recipe<?>, RecipeWrapper> pFactory) {
        Collection<RecipeWrapper> recipes = pRecipeManager.getAllRecipesFor(pType).stream().map(pFactory).toList();
        List<Set<ResourceLocation>> conflicts = new ArrayList();
        Set<ResourceLocation> skipped = new TreeSet();
        Set<ResourceLocation> processed = new HashSet();
        for (RecipeWrapper recipe : recipes) {
            ResourceLocation id = recipe.getId();
            if (!processed.contains(id)) {
                processed.add(id);
                if (recipe.getRecipe() instanceof CustomRecipe) {
                    skipped.add(id);
                } else {
                    Set<ResourceLocation> currentGroup = new TreeSet();
                    for (RecipeWrapper otherRecipe : recipes) {
                        if (!processed.contains(otherRecipe.getId()) && otherRecipe.conflicts(recipe)) {
                            currentGroup.add(id);
                            currentGroup.add(otherRecipe.getId());
                            processed.add(otherRecipe.getId());
                        }
                    }
                    if (!currentGroup.isEmpty()) {
                        conflicts.add(currentGroup);
                    }
                }
            }
        }
        pOutput.add("===================================================================");
        pOutput.add(BuiltInRegistries.RECIPE_TYPE.getKey(pType) + " recipe conflicts (" + conflicts.size() + ")");
        pOutput.add("===================================================================");
        pOutput.add("");
        int count = 1;
        for (Set<ResourceLocation> conflict : conflicts) {
            StringJoiner joiner = new StringJoiner(", ");
            conflict.stream().map(ResourceLocation::toString).forEach(joiner::add);
            pOutput.add(count + ": " + joiner);
            pOutput.add("");
            count++;
        }
        if (skipped.size() > 0) {
            pOutput.add("Skipped special recipes: ");
            for (ResourceLocation resourceLocation : skipped) {
                pOutput.add(resourceLocation.toString());
            }
            pOutput.add("");
        }
        return conflicts.size();
    }
}