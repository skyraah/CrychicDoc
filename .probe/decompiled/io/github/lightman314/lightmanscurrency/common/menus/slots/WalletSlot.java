package io.github.lightman314.lightmanscurrency.common.menus.slots;

import com.mojang.datafixers.util.Pair;
import io.github.lightman314.lightmanscurrency.common.items.WalletItem;
import javax.annotation.Nonnull;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class WalletSlot extends Slot {

    public static final ResourceLocation EMPTY_WALLET_SLOT = new ResourceLocation("lightmanscurrency", "item/empty_wallet_slot");

    public static final Pair<ResourceLocation, ResourceLocation> BACKGROUND = Pair.of(InventoryMenu.BLOCK_ATLAS, EMPTY_WALLET_SLOT);

    private final Player player;

    public WalletSlot(Player player, Container inventory, int index, int x, int y) {
        super(inventory, index, x, y);
        this.player = player;
    }

    @Override
    public boolean mayPlace(@Nonnull ItemStack stack) {
        return isValidWallet(stack);
    }

    public static boolean isValidWallet(ItemStack stack) {
        return stack.isEmpty() || stack.getItem() instanceof WalletItem;
    }

    @Override
    public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
        return BACKGROUND;
    }
}