package fr.lucreeper74.createmetallurgy.content.casting.basin;

import com.simibubi.create.content.equipment.goggles.IHaveGoggleInformation;
import com.simibubi.create.content.kinetics.belt.behaviour.DirectBeltInputBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import com.simibubi.create.foundation.utility.Components;
import com.simibubi.create.foundation.utility.LangBuilder;
import fr.lucreeper74.createmetallurgy.content.casting.CastingBlockEntity;
import fr.lucreeper74.createmetallurgy.registries.CMRecipeTypes;
import fr.lucreeper74.createmetallurgy.utils.CMLang;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

public class CastingBasinBlockEntity extends CastingBlockEntity implements IHaveGoggleInformation {

    private static final Object CastingInBasinRecipesKey = new Object();

    public CastingBasinBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        behaviours.add(new DirectBeltInputBehaviour(this));
        this.inputTank = new SmartFluidTankBehaviour(SmartFluidTankBehaviour.INPUT, this, 1, 810, true);
        behaviours.add(this.inputTank);
    }

    @Override
    protected <C extends Container> boolean matchStaticFilters(Recipe<C> r) {
        return r.getType() == CMRecipeTypes.CASTING_IN_BASIN.getType();
    }

    @Override
    protected Object getRecipeCacheKey() {
        return CastingInBasinRecipesKey;
    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        CMLang.translate("gui.goggles.castingbasin_contents").forGoggles(tooltip);
        IItemHandlerModifiable items = this.itemCapability.orElse(new ItemStackHandler());
        IFluidHandler fluids = this.getFluidTank();
        boolean isEmpty = true;
        for (int i = 0; i < items.getSlots(); i++) {
            ItemStack stackInSlot = items.getStackInSlot(i);
            if (!stackInSlot.isEmpty()) {
                CMLang.text("").add(Components.translatable(stackInSlot.getDescriptionId()).withStyle(ChatFormatting.GRAY)).add(CMLang.text(" x" + stackInSlot.getCount()).style(ChatFormatting.GREEN)).forGoggles(tooltip, 1);
                isEmpty = false;
            }
        }
        LangBuilder mb = CMLang.translate("generic.unit.millibuckets");
        for (int ix = 0; ix < fluids.getTanks(); ix++) {
            FluidStack fluidStack = fluids.getFluidInTank(ix);
            if (!fluidStack.isEmpty()) {
                CMLang.text("").add(CMLang.fluidName(fluidStack).add(CMLang.text(" ")).style(ChatFormatting.GRAY).add(CMLang.number((double) fluidStack.getAmount()).add(mb).style(ChatFormatting.BLUE))).forGoggles(tooltip, 1);
                isEmpty = false;
            }
        }
        if (isEmpty) {
            tooltip.remove(0);
        }
        return true;
    }
}