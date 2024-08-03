package com.simibubi.create.content.equipment.armor;

import java.util.Locale;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class BaseArmorItem extends ArmorItem {

    protected final ResourceLocation textureLoc;

    public BaseArmorItem(ArmorMaterial armorMaterial, ArmorItem.Type type, Item.Properties properties, ResourceLocation textureLoc) {
        super(armorMaterial, type, properties.stacksTo(1));
        this.textureLoc = textureLoc;
    }

    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return String.format(Locale.ROOT, "%s:textures/models/armor/%s_layer_%d%s.png", this.textureLoc.getNamespace(), this.textureLoc.getPath(), slot == EquipmentSlot.LEGS ? 2 : 1, type == null ? "" : String.format(Locale.ROOT, "_%s", type));
    }
}