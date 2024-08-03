package dev.xkmc.l2archery.init.data;

import dev.xkmc.l2archery.content.config.BowArrowStatConfig;
import dev.xkmc.l2archery.init.L2Archery;
import dev.xkmc.l2archery.init.registrate.ArcheryEnchantments;
import dev.xkmc.l2archery.init.registrate.ArcheryItems;
import dev.xkmc.l2complements.init.registrate.LCEffects;
import dev.xkmc.l2library.serial.config.ConfigDataProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;

public class ArcheryConfigGen extends ConfigDataProvider {

    public ArcheryConfigGen(DataGenerator generator) {
        super(generator, "Archery Config");
    }

    @Override
    public void add(ConfigDataProvider.Collector map) {
        map.add(L2Archery.STATS, new ResourceLocation("l2archery", "bows"), new BowArrowStatConfig().putBow(ArcheryItems.STARTER_BOW).end().putBow(ArcheryItems.IRON_BOW).damage(3.0).bothTimes(40.0).end().putBow(ArcheryItems.MASTER_BOW).damage(1.0).fovs(20, 0.3).end().putBow(ArcheryItems.GLOW_AIM_BOW).bothTimes(40.0).end().putBow(ArcheryItems.ENDER_AIM_BOW).end().putBow(ArcheryItems.MAGNIFY_BOW).bothTimes(40.0).fovs(60, 0.9).end().putBow(ArcheryItems.EAGLE_BOW).damage(6.0).punch(2.0).bothTimes(40.0).end().putBow(ArcheryItems.WIND_BOW).punch(1.0).bothTimes(10.0).speed(3.9).end().putBow(ArcheryItems.EXPLOSION_BOW).end().putBow(ArcheryItems.FLAME_BOW).putEffect((MobEffect) LCEffects.FLAME.get(), 100, 0).end().putBow(ArcheryItems.FROZE_BOW).putEffect((MobEffect) LCEffects.ICE.get(), 600, 0).end().putBow(ArcheryItems.STORM_BOW).end().putBow(ArcheryItems.BLACKSTONE_BOW).putEffect((MobEffect) LCEffects.STONE_CAGE.get(), 100, 0).end().putBow(ArcheryItems.WINTER_BOW).putEffect((MobEffect) LCEffects.ICE.get(), 600, 0).end().putBow(ArcheryItems.TURTLE_BOW).damage(6.0).bothTimes(40.0).speed(3.0).end().putBow(ArcheryItems.EARTH_BOW).damage(10.0).bothTimes(60.0).speed(3.0).end().putBow(ArcheryItems.GAIA_BOW).damage(16.0).bothTimes(80.0).speed(3.0).end().putBow(ArcheryItems.SUN_BOW).putEffect((MobEffect) LCEffects.FLAME.get(), 200, 1).end());
        map.add(L2Archery.STATS, new ResourceLocation("l2archery", "arrows"), new BowArrowStatConfig().putArrow(ArcheryItems.STARTER_ARROW).end().putArrow(ArcheryItems.COPPER_ARROW).damage(1.0).end().putArrow(ArcheryItems.IRON_ARROW).damage(1.0).punch(1.0).end().putArrow(ArcheryItems.OBSIDIAN_ARROW).damage(2.0).end().putArrow(ArcheryItems.GOLD_ARROW).damage(2.0).punch(3.0).end().putArrow(ArcheryItems.QUARTZ_ARROW).damage(3.0).end().putArrow(ArcheryItems.DIAMOND_ARROW).damage(4.0).end().putArrow(ArcheryItems.DESTROYER_ARROW).damage(8.0).end().putArrow(ArcheryItems.TOTEMIC_GOLD_ARROW).damage(3.0).punch(3.0).end().putArrow(ArcheryItems.POSEIDITE_ARROW).damage(4.0).punch(1.0).end().putArrow(ArcheryItems.SHULKERATE_ARROW).damage(4.0).end().putArrow(ArcheryItems.SCULKIUM_ARROW).damage(6.0).end().putArrow(ArcheryItems.ETERNIUM_ARROW).damage(4.0).end().putArrow(ArcheryItems.TEARING_ARROW).damage(5.0).end().putArrow(ArcheryItems.VOID_ARROW).end().putArrow(ArcheryItems.NO_FALL_ARROW).end().putArrow(ArcheryItems.ENDER_ARROW).end().putArrow(ArcheryItems.TNT_1_ARROW).end().putArrow(ArcheryItems.TNT_2_ARROW).end().putArrow(ArcheryItems.TNT_3_ARROW).end().putArrow(ArcheryItems.FIRE_1_ARROW).putEffect((MobEffect) LCEffects.FLAME.get(), 100, 0).end().putArrow(ArcheryItems.FIRE_2_ARROW).putEffect((MobEffect) LCEffects.FLAME.get(), 200, 1).end().putArrow(ArcheryItems.ICE_ARROW).putEffect((MobEffect) LCEffects.ICE.get(), 600, 0).end().putArrow(ArcheryItems.BLACKSTONE_ARROW).putEffect((MobEffect) LCEffects.STONE_CAGE.get(), 100, 0).end().putArrow(ArcheryItems.ACID_ARROW).putEffect((MobEffect) LCEffects.ARMOR_REDUCE.get(), 1200, 2).end().putArrow(ArcheryItems.DISPELL_ARROW).end().putArrow(ArcheryItems.WITHER_ARROW).putEffect(MobEffects.WITHER, 200, 0).end().putArrow(ArcheryItems.STORM_ARROW).end());
        map.add(L2Archery.STATS, new ResourceLocation("l2archery", "upgrades"), new BowArrowStatConfig().putUpgrade(ArcheryItems.FIRE_UP).putEffect((MobEffect) LCEffects.FLAME.get(), 100, 0).end().putUpgrade(ArcheryItems.ICE_UP).putEffect((MobEffect) LCEffects.ICE.get(), 600, 0).end().putUpgrade(ArcheryItems.BLACKSTONE_UP).putEffect((MobEffect) LCEffects.STONE_CAGE.get(), 100, 0).end().putUpgrade(ArcheryItems.HARM_UP).putEffect(MobEffects.HARM, 1, 2).end().putUpgrade(ArcheryItems.HEAL_UP).putEffect(MobEffects.HEAL, 1, 2).end().putUpgrade(ArcheryItems.SHINE_UP).putEffect(MobEffects.GLOWING, 600, 0).end().putUpgrade(ArcheryItems.LEVITATE_UP).putEffect(MobEffects.LEVITATION, 100, 0).end().putUpgrade(ArcheryItems.FLOAT_UP).putEffect(MobEffects.SLOW_FALLING, 300, 0).end().putUpgrade(ArcheryItems.SLOW_UP).putEffect(MobEffects.MOVEMENT_SLOWDOWN, 300, 5).end().putUpgrade(ArcheryItems.POISON_UP).putEffect(MobEffects.POISON, 300, 0).end().putUpgrade(ArcheryItems.WITHER_UP).putEffect(MobEffects.WITHER, 100, 2).end().putUpgrade(ArcheryItems.WEAK_UP).putEffect(MobEffects.WEAKNESS, 300, 4).end().putUpgrade(ArcheryItems.CORROSION_UP).putEffect((MobEffect) LCEffects.ARMOR_REDUCE.get(), 400, 1).end().putUpgrade(ArcheryItems.CURSE_UP).putEffect((MobEffect) LCEffects.CURSE.get(), 200, 0).end().putUpgrade(ArcheryItems.CLEANSE_UP).putEffect((MobEffect) LCEffects.CLEANSE.get(), 200, 0).end());
        map.add(L2Archery.STATS, new ResourceLocation("l2archery", "enchantments"), new BowArrowStatConfig().putEnchantment(ArcheryEnchantments.ENCH_FLOAT).putEffect(MobEffects.SLOW_FALLING, 60, 0, 60, 0).end().putEnchantment(ArcheryEnchantments.ENCH_LEVITATE).putEffect(MobEffects.LEVITATION, 20, 0, 20, 0).end().putEnchantment(ArcheryEnchantments.ENCH_SLOW).putEffect(MobEffects.MOVEMENT_SLOWDOWN, 200, 1, 0, 1).end().putEnchantment(ArcheryEnchantments.ENCH_WEAK).putEffect(MobEffects.WEAKNESS, 200, 0, 0, 1).end().putEnchantment(ArcheryEnchantments.ENCH_HEAL).putEffect(MobEffects.HEAL, 1, 0, 0, 1).end().putEnchantment(ArcheryEnchantments.ENCH_HARM).putEffect(MobEffects.HARM, 1, 0, 0, 1).end().putEnchantment(ArcheryEnchantments.ENCH_GLOW).putEffect(MobEffects.GLOWING, 600, 0, 0, 0).end().putEnchantment(ArcheryEnchantments.ENCH_POISON).putEffect(MobEffects.POISON, 100, 0, 100, 0).end().putEnchantment(ArcheryEnchantments.ENCH_WITHER).putEffect(MobEffects.WITHER, 100, 0, 0, 1).end().putEnchantment(ArcheryEnchantments.ENCH_CHAOTIC).putEffect(MobEffects.MOVEMENT_SPEED, 100, 0, 100, 0).putEffect(MobEffects.DAMAGE_BOOST, 100, 0, 100, 0).putEffect(MobEffects.JUMP, 100, 0, 100, 0).putEffect(MobEffects.HUNGER, 100, 0, 100, 0).putEffect(MobEffects.DIG_SPEED, 100, 0, 100, 0).putEffect(MobEffects.DIG_SLOWDOWN, 100, 0, 100, 0).putEffect(MobEffects.LUCK, 100, 0, 100, 0).putEffect(MobEffects.UNLUCK, 100, 0, 100, 0).putEffect(MobEffects.NIGHT_VISION, 100, 0, 100, 0).end().putEnchantment(ArcheryEnchantments.ENCH_DISTORTION).putEffect(MobEffects.CONFUSION, 100, 0, 100, 0).putEffect(MobEffects.DARKNESS, 100, 0, 100, 0).putEffect(MobEffects.BLINDNESS, 100, 0, 100, 0).end());
    }
}