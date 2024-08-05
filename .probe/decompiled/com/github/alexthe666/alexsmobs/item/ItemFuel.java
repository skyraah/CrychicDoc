package com.github.alexthe666.alexsmobs.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemFuel extends Item {

    private final int burnTime;

    public ItemFuel(Item.Properties props, int burnTime) {
        super(props);
        this.burnTime = burnTime;
    }

    public int getBurnTime(ItemStack itemStack) {
        return this.burnTime;
    }
}