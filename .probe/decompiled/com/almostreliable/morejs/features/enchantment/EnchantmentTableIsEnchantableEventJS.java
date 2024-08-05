package com.almostreliable.morejs.features.enchantment;

import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EnchantmentTableIsEnchantableEventJS extends EnchantmentTableServerEventJS {

    @Nullable
    private Boolean isEnchantable;

    public EnchantmentTableIsEnchantableEventJS(ItemStack item, ItemStack secondItem, Level level, BlockPos pos, EnchantmentMenuProcess state) {
        super(item, secondItem, level, pos, state.getPlayer(), state);
    }

    public void setIsEnchantable(boolean flag) {
        this.isEnchantable = flag;
    }

    @Nullable
    public Boolean getIsEnchantable() {
        return this.isEnchantable;
    }
}