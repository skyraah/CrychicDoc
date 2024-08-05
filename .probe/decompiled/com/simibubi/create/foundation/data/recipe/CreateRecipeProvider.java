package com.simibubi.create.foundation.data.recipe;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.AllTags;
import com.simibubi.create.Create;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;

public abstract class CreateRecipeProvider extends RecipeProvider {

    protected final List<CreateRecipeProvider.GeneratedRecipe> all = new ArrayList();

    public CreateRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> p_200404_1_) {
        this.all.forEach(c -> c.register(p_200404_1_));
        Create.LOGGER.info(this.m_6055_() + " registered " + this.all.size() + " recipe" + (this.all.size() == 1 ? "" : "s"));
    }

    protected CreateRecipeProvider.GeneratedRecipe register(CreateRecipeProvider.GeneratedRecipe recipe) {
        this.all.add(recipe);
        return recipe;
    }

    @FunctionalInterface
    public interface GeneratedRecipe {

        void register(Consumer<FinishedRecipe> var1);
    }

    protected static class I {

        static TagKey<Item> redstone() {
            return Tags.Items.DUSTS_REDSTONE;
        }

        static TagKey<Item> planks() {
            return ItemTags.PLANKS;
        }

        static TagKey<Item> woodSlab() {
            return ItemTags.WOODEN_SLABS;
        }

        static TagKey<Item> gold() {
            return AllTags.forgeItemTag("ingots/gold");
        }

        static TagKey<Item> goldSheet() {
            return AllTags.forgeItemTag("plates/gold");
        }

        static TagKey<Item> stone() {
            return Tags.Items.STONE;
        }

        static ItemLike andesite() {
            return (ItemLike) AllItems.ANDESITE_ALLOY.get();
        }

        static ItemLike shaft() {
            return (ItemLike) AllBlocks.SHAFT.get();
        }

        static ItemLike cog() {
            return (ItemLike) AllBlocks.COGWHEEL.get();
        }

        static ItemLike largeCog() {
            return (ItemLike) AllBlocks.LARGE_COGWHEEL.get();
        }

        static ItemLike andesiteCasing() {
            return (ItemLike) AllBlocks.ANDESITE_CASING.get();
        }

        static TagKey<Item> brass() {
            return AllTags.forgeItemTag("ingots/brass");
        }

        static TagKey<Item> brassSheet() {
            return AllTags.forgeItemTag("plates/brass");
        }

        static TagKey<Item> iron() {
            return Tags.Items.INGOTS_IRON;
        }

        static TagKey<Item> ironNugget() {
            return AllTags.forgeItemTag("nuggets/iron");
        }

        static TagKey<Item> zinc() {
            return AllTags.forgeItemTag("ingots/zinc");
        }

        static TagKey<Item> ironSheet() {
            return AllTags.forgeItemTag("plates/iron");
        }

        static TagKey<Item> sturdySheet() {
            return AllTags.forgeItemTag("plates/obsidian");
        }

        static ItemLike brassCasing() {
            return (ItemLike) AllBlocks.BRASS_CASING.get();
        }

        static ItemLike railwayCasing() {
            return (ItemLike) AllBlocks.RAILWAY_CASING.get();
        }

        static ItemLike electronTube() {
            return (ItemLike) AllItems.ELECTRON_TUBE.get();
        }

        static ItemLike precisionMechanism() {
            return (ItemLike) AllItems.PRECISION_MECHANISM.get();
        }

        static ItemLike copperBlock() {
            return Items.COPPER_BLOCK;
        }

        static TagKey<Item> brassBlock() {
            return AllTags.forgeItemTag("storage_blocks/brass");
        }

        static TagKey<Item> zincBlock() {
            return AllTags.forgeItemTag("storage_blocks/zinc");
        }

        static TagKey<Item> wheatFlour() {
            return AllTags.forgeItemTag("flour/wheat");
        }

        static ItemLike copper() {
            return Items.COPPER_INGOT;
        }

        static TagKey<Item> copperSheet() {
            return AllTags.forgeItemTag("plates/copper");
        }

        static TagKey<Item> copperNugget() {
            return AllTags.forgeItemTag("nuggets/copper");
        }

        static TagKey<Item> brassNugget() {
            return AllTags.forgeItemTag("nuggets/brass");
        }

        static TagKey<Item> zincNugget() {
            return AllTags.forgeItemTag("nuggets/zinc");
        }

        static ItemLike copperCasing() {
            return (ItemLike) AllBlocks.COPPER_CASING.get();
        }

        static ItemLike refinedRadiance() {
            return (ItemLike) AllItems.REFINED_RADIANCE.get();
        }

        static ItemLike shadowSteel() {
            return (ItemLike) AllItems.SHADOW_STEEL.get();
        }

        static Ingredient netherite() {
            return Ingredient.of(AllTags.forgeItemTag("ingots/netherite"));
        }
    }

    protected static class Marker {
    }
}