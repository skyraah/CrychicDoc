package dev.latvian.mods.kubejs.block.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;

public interface BlockEntityAttachment {

    BlockEntityAttachment[] EMPTY_ARRAY = new BlockEntityAttachment[0];

    default CompoundTag writeAttachment() {
        return new CompoundTag();
    }

    default void readAttachment(CompoundTag tag) {
    }

    default void onRemove(BlockState newState) {
    }

    public interface Factory {

        BlockEntityAttachment create(BlockEntityJS var1);
    }
}