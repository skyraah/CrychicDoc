package io.github.lightman314.lightmanscurrency.client.colors;

import io.github.lightman314.lightmanscurrency.common.items.TicketItem;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GoldenTicketColor implements ItemColor {

    @Override
    public int getColor(@NotNull ItemStack itemStack, int color) {
        return color == 1 ? TicketItem.GetTicketColor(itemStack) : 16777215;
    }
}