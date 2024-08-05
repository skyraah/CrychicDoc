package dev.shadowsoffire.placebo.util;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;

public class ChestBuilder {

    protected ChestBlockEntity chest;

    protected boolean isValid;

    protected BlockPos position;

    protected LevelAccessor iWorld;

    private ChestBuilder(LevelAccessor world, BlockPos pos) {
        BlockEntity tileEntity = world.m_7702_(pos);
        if (tileEntity instanceof ChestBlockEntity) {
            this.chest = (ChestBlockEntity) tileEntity;
            this.isValid = true;
            this.position = pos;
            this.iWorld = world;
        }
    }

    public void fill(ResourceLocation loot) {
        RandomizableContainerBlockEntity.setLootTable(this.iWorld, this.iWorld.getRandom(), this.position, loot);
    }

    public static void place(LevelAccessor world, BlockPos pos, ResourceLocation loot) {
        world.m_7731_(pos, Blocks.CHEST.defaultBlockState(), 2);
        ChestBuilder chest = new ChestBuilder(world, pos);
        if (chest.isValid) {
            chest.fill(loot);
        }
    }

    public static void placeTrapped(LevelAccessor world, BlockPos pos, ResourceLocation loot) {
        world.m_7731_(pos, Blocks.TRAPPED_CHEST.defaultBlockState(), 2);
        ChestBuilder chest = new ChestBuilder(world, pos);
        if (chest.isValid) {
            chest.fill(loot);
        }
    }
}