package io.github.lightman314.lightmanscurrency.common.crafting;

import com.google.gson.JsonObject;
import io.github.lightman314.lightmanscurrency.common.core.ModRecipes;
import io.github.lightman314.lightmanscurrency.common.items.TicketItem;
import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.common.crafting.CraftingHelper;
import org.jetbrains.annotations.Nullable;

public class TicketRecipe implements TicketStationRecipe {

    private final ResourceLocation id;

    private final Ingredient masterIngredient;

    private final Ingredient ingredient;

    private final Item ticketResult;

    public TicketRecipe(@Nonnull ResourceLocation id, @Nonnull Ingredient masterIngredient, @Nonnull Ingredient ingredient, @Nonnull Item result) {
        this.id = id;
        this.masterIngredient = masterIngredient;
        this.ingredient = ingredient;
        this.ticketResult = result;
    }

    @Nonnull
    @Override
    public List<ItemStack> jeiModifierList() {
        return TicketStationRecipe.exampleTicketList(this.masterIngredient);
    }

    @Nonnull
    @Override
    public Ingredient getIngredient() {
        return this.ingredient;
    }

    @Nonnull
    @Override
    public ItemStack exampleResult() {
        return new ItemStack(this.ticketResult);
    }

    @Override
    public boolean consumeModifier() {
        return false;
    }

    @Override
    public boolean validModifier(@Nonnull ItemStack stack) {
        return this.masterIngredient.test(stack);
    }

    @Override
    public boolean validIngredient(@Nonnull ItemStack stack) {
        return this.ingredient.test(stack);
    }

    @Nonnull
    @Override
    public ItemStack assemble(@Nonnull Container container, @Nonnull RegistryAccess registryAccess) {
        return TicketItem.CraftTicket(container.getItem(0), this.ticketResult);
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Nonnull
    @Override
    public ItemStack getResultItem(@Nonnull RegistryAccess registryAccess) {
        return this.masterIngredient.getItems().length == 0 ? ItemStack.EMPTY : TicketItem.CraftTicket(TicketItem.CreateTicket(this.masterIngredient.getItems()[0].getItem(), -1L), this.ticketResult);
    }

    @Nonnull
    @Override
    public ItemStack peekAtResult(@Nonnull Container container) {
        return TicketItem.CraftTicket(container.getItem(0), this.ticketResult);
    }

    @Nonnull
    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Nonnull
    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.TICKET.get();
    }

    public static class Serializer implements RecipeSerializer<TicketRecipe> {

        @Nonnull
        public TicketRecipe fromJson(@Nonnull ResourceLocation id, @Nonnull JsonObject json) {
            Ingredient masterIngredient = Ingredient.fromJson(json.get("masterTicket"));
            Ingredient ingredient = Ingredient.fromJson(json.get("ingredient"));
            Item item = CraftingHelper.getItem(GsonHelper.getAsString(json, "result"), true);
            return new TicketRecipe(id, masterIngredient, ingredient, item);
        }

        @Nullable
        public TicketRecipe fromNetwork(@Nonnull ResourceLocation id, @Nonnull FriendlyByteBuf buffer) {
            return new TicketRecipe(id, Ingredient.fromNetwork(buffer), Ingredient.fromNetwork(buffer), buffer.readItem().getItem());
        }

        public void toNetwork(@Nonnull FriendlyByteBuf buffer, @Nonnull TicketRecipe recipe) {
            recipe.masterIngredient.toNetwork(buffer);
            recipe.ingredient.toNetwork(buffer);
            buffer.writeItem(new ItemStack(recipe.ticketResult));
        }
    }
}