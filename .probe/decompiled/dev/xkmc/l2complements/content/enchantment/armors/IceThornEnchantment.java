package dev.xkmc.l2complements.content.enchantment.armors;

import dev.xkmc.l2complements.init.data.LCConfig;
import dev.xkmc.l2complements.init.registrate.LCEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class IceThornEnchantment extends AbstractThornEnchantment {

    public IceThornEnchantment(Enchantment.Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot[] pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    protected MobEffectInstance getEffect(int pLevel) {
        return new MobEffectInstance((MobEffect) LCEffects.ICE.get(), LCConfig.COMMON.iceEnchantDuration.get() << pLevel - 1);
    }
}