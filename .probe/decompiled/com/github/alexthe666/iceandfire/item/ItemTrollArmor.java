package com.github.alexthe666.iceandfire.item;

import com.github.alexthe666.citadel.server.item.CustomArmorMaterial;
import com.github.alexthe666.iceandfire.client.model.armor.ModelTrollArmor;
import com.github.alexthe666.iceandfire.enums.EnumTroll;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;

public class ItemTrollArmor extends ArmorItem {

    public EnumTroll troll;

    public ItemTrollArmor(EnumTroll troll, CustomArmorMaterial material, ArmorItem.Type slot) {
        super(material, slot, new Item.Properties());
        this.troll = troll;
    }

    public static String getName(EnumTroll troll, EquipmentSlot slot) {
        return "%s_troll_leather_%s".formatted(troll.name().toLowerCase(Locale.ROOT), getArmorPart(slot));
    }

    @NotNull
    @Override
    public ArmorMaterial getMaterial() {
        return this.troll.material;
    }

    private static String getArmorPart(EquipmentSlot slot) {
        return switch(slot) {
            case HEAD ->
                "helmet";
            case CHEST ->
                "chestplate";
            case LEGS ->
                "leggings";
            case FEET ->
                "boots";
            default ->
                "";
        };
    }

    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {

            @NotNull
            @Override
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity LivingEntity, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
                return new ModelTrollArmor(armorSlot == EquipmentSlot.LEGS || armorSlot == EquipmentSlot.HEAD);
            }
        });
    }

    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return "iceandfire:textures/models/armor/armor_troll_" + this.troll.name().toLowerCase(Locale.ROOT) + (slot == EquipmentSlot.LEGS ? "_legs.png" : ".png");
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        tooltip.add(Component.translatable("item.iceandfire.troll_leather_armor_" + getArmorPart(this.f_265916_.getSlot()) + ".desc").withStyle(ChatFormatting.GREEN));
    }
}