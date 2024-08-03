package com.almostreliable.morejs.mixin.enchanting;

import com.almostreliable.morejs.MoreJS;
import com.almostreliable.morejs.core.Events;
import com.almostreliable.morejs.features.enchantment.EnchantmentMenuExtension;
import com.almostreliable.morejs.features.enchantment.EnchantmentMenuProcess;
import com.almostreliable.morejs.features.enchantment.EnchantmentState;
import com.almostreliable.morejs.features.enchantment.EnchantmentTableChangedJS;
import com.almostreliable.morejs.features.enchantment.EnchantmentTableIsEnchantableEventJS;
import com.almostreliable.morejs.features.enchantment.PlayerEnchantEventJS;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.EnchantmentMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = { EnchantmentMenu.class }, priority = 42)
public abstract class EnchantmentMenuMixin extends AbstractContainerMenu implements EnchantmentMenuExtension {

    @Unique
    private EnchantmentMenuProcess morejs$process;

    @Shadow
    @Final
    private Container enchantSlots;

    @Shadow
    @Final
    private ContainerLevelAccess access;

    @Shadow
    @Final
    private RandomSource random;

    protected EnchantmentMenuMixin(@Nullable MenuType<?> menuType, int i) {
        super(menuType, i);
    }

    @Override
    public EnchantmentMenuProcess getMoreJSProcess() {
        return this.morejs$process;
    }

    @Override
    public Container getMoreJsEnchantSlots() {
        return this.enchantSlots;
    }

    @Inject(method = { "<init>(ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/inventory/ContainerLevelAccess;)V" }, at = { @At("RETURN") })
    private void initializeProcess(int i, Inventory inventory, ContainerLevelAccess containerLevelAccess, CallbackInfo ci) {
        this.morejs$process = new EnchantmentMenuProcess((EnchantmentMenu) this);
        this.morejs$process.setPlayer(inventory.player);
    }

    @Inject(method = { "slotsChanged" }, at = { @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/ContainerLevelAccess;execute(Ljava/util/function/BiConsumer;)V") }, cancellable = true)
    private void slotchanged$PrepareChangeEvent(Container container, CallbackInfo ci) {
        this.access.execute((level, pos) -> {
            ItemStack item = container.getItem(0);
            if (this.morejs$process.matchesCurrentItem(item)) {
                ci.cancel();
            } else {
                this.morejs$process.prepareEvent(item);
            }
        });
    }

    @Redirect(method = { "slotsChanged" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;isEnchantable()Z"))
    private boolean slotChanged$InvokeEnchantableEvent(ItemStack itemStack, Container container) {
        Boolean[] result = new Boolean[1];
        this.access.execute((level, pos) -> {
            ItemStack secondItem = container.getItem(1);
            EnchantmentTableIsEnchantableEventJS e = new EnchantmentTableIsEnchantableEventJS(itemStack, secondItem, level, pos, this.morejs$process);
            Events.ENCHANTMENT_TABLE_IS_ENCHANTABLE.post(e);
            result[0] = e.getIsEnchantable();
        });
        return this.morejs$process.storeItemIsEnchantable(result[0], itemStack);
    }

    @Inject(method = { "slotsChanged" }, at = { @At("RETURN") })
    private void slotChanged$InvokeChangeEvent(Container container, CallbackInfo ci) {
        if (container == this.enchantSlots && this.morejs$process.isFreezeBroadcast()) {
            ItemStack item = container.getItem(0);
            this.access.execute((level, pos) -> {
                ItemStack secondItem = container.getItem(1);
                this.morejs$process.setFreezeBroadcast(false);
                this.morejs$process.setState(EnchantmentState.USE_STORED_ENCHANTMENTS);
                Events.ENCHANTMENT_TABLE_CHANGED.post(new EnchantmentTableChangedJS(item, secondItem, level, pos, this.morejs$process, this.random));
            });
            if (item.isEmpty() || !this.morejs$process.isItemEnchantable(item)) {
                this.morejs$process.clearEnchantments();
            }
        }
    }

    @Inject(method = { "getEnchantmentList" }, at = { @At("RETURN") }, cancellable = true)
    private void handleEnchantmentGetter(ItemStack itemStack, int index, int powerLevel, CallbackInfoReturnable<List<EnchantmentInstance>> cir) {
        switch(this.morejs$process.getState()) {
            case STORE_ENCHANTMENTS:
                this.morejs$process.setEnchantments(index, (List<EnchantmentInstance>) cir.getReturnValue());
                break;
            case USE_STORED_ENCHANTMENTS:
                List<EnchantmentInstance> enchantments = this.morejs$process.getEnchantments(index);
                if (enchantments == null) {
                    MoreJS.LOG.error("Enchantment list is null for index " + index + ", when in state USE_STORED_ENCHANTMENTS");
                    return;
                }
                cir.setReturnValue(enchantments);
        }
    }

    @Inject(method = { "clickMenuButton" }, at = { @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/ContainerLevelAccess;execute(Ljava/util/function/BiConsumer;)V") }, cancellable = true)
    private void clickMenuButton$InvokeEnchantEvent(Player player, int clickedBtn, CallbackInfoReturnable<Boolean> cir) {
        this.access.execute((level, pos) -> {
            if (player != this.morejs$process.getPlayer()) {
                MoreJS.LOG.warn("<{}> Player changed during clickMenuButton", this.morejs$process.getPlayer());
            } else {
                ItemStack item = this.enchantSlots.getItem(0);
                ItemStack secondItem = this.enchantSlots.getItem(1);
                PlayerEnchantEventJS e = new PlayerEnchantEventJS(clickedBtn, item, secondItem, level, pos, player, this.morejs$process);
                if (Events.ENCHANTMENT_TABLE_ENCHANT.post(e).interruptFalse()) {
                    cir.setReturnValue(false);
                }
                if (e.itemWasChanged()) {
                    cir.setReturnValue(false);
                    ItemStack newItem = e.getItem().copy();
                    this.morejs$process.abortEvent(newItem);
                    this.enchantSlots.setItem(0, newItem);
                }
            }
        });
    }

    @Inject(method = { "clickMenuButton" }, at = { @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/ContainerLevelAccess;execute(Ljava/util/function/BiConsumer;)V", shift = Shift.AFTER) })
    private void clickMenuButton$postClear(Player player, int i, CallbackInfoReturnable<Boolean> cir) {
        this.access.execute((level, pos) -> {
            ItemStack currentItem = this.morejs$process.getCurrentItem();
            ItemStack secondItem = this.enchantSlots.getItem(0);
            if (!ItemStack.matches(currentItem, secondItem)) {
                this.morejs$process.abortEvent(ItemStack.EMPTY);
            }
        });
    }

    @Override
    public void broadcastChanges() {
        if (!this.morejs$process.isFreezeBroadcast()) {
            super.broadcastChanges();
        }
    }
}