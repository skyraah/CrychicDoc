package dev.shadowsoffire.placebo.block_entity;

import dev.shadowsoffire.placebo.Placebo;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

public interface TickingEntityBlock extends EntityBlock {

    @Override
    default <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> type) {
        if (type instanceof TickingBlockEntityType tickType) {
            return tickType.getTicker(pLevel.isClientSide);
        } else {
            Placebo.LOGGER.error("##############################");
            Placebo.LOGGER.error("A Block {} with BlockEntityType {} has subscribed as a TickingEntityBlock but is not using TickingBlockEntityType!", ForgeRegistries.BLOCKS.getKey(pState.m_60734_()), BlockEntityType.getKey(type));
            Placebo.LOGGER.error("##############################");
            return null;
        }
    }
}