package com.rekindled.embers.blockentity;

import com.rekindled.embers.RegistryManager;
import com.rekindled.embers.api.capabilities.EmbersCapabilities;
import com.rekindled.embers.upgrade.HeatInsulationUpgrade;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

public class HeatInsulationBlockEntity extends BlockEntity {

    public HeatInsulationUpgrade upgrade = new HeatInsulationUpgrade(this);

    public HeatInsulationBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(RegistryManager.HEAT_INSULATION_ENTITY.get(), pPos, pBlockState);
    }

    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return !this.f_58859_ && this.f_58857_.getBlockState(this.f_58858_).m_61138_(BlockStateProperties.FACING) && cap == EmbersCapabilities.UPGRADE_PROVIDER_CAPABILITY && side.getOpposite() == this.f_58857_.getBlockState(this.f_58858_).m_61143_(BlockStateProperties.FACING) ? this.upgrade.getCapability(cap, side) : super.getCapability(cap, side);
    }
}