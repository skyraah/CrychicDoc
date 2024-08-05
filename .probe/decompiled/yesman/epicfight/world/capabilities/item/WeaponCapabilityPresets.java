package yesman.epicfight.world.capabilities.item;

import com.mojang.datafixers.util.Pair;
import java.util.function.Function;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.Tiers;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.skill.SkillDataKeys;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.entity.ai.attribute.EpicFightAttributes;

public class WeaponCapabilityPresets {

    public static final Function<Item, CapabilityItem.Builder> AXE = item -> {
        CapabilityItem.Builder builder = WeaponCapability.builder().category(CapabilityItem.WeaponCategories.AXE).hitSound(EpicFightSounds.BLADE_HIT.get()).collider(ColliderPreset.TOOLS).newStyleCombo(CapabilityItem.Styles.ONE_HAND, Animations.AXE_AUTO1, Animations.AXE_AUTO2, Animations.AXE_DASH, Animations.AXE_AIRSLASH).newStyleCombo(CapabilityItem.Styles.MOUNT, Animations.SWORD_MOUNT_ATTACK).innateSkill(CapabilityItem.Styles.ONE_HAND, itemstack -> EpicFightSkills.GUILLOTINE_AXE).livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.BLOCK, Animations.SWORD_GUARD);
        if (item instanceof TieredItem tieredItem) {
            int harvestLevel = tieredItem.getTier().getLevel();
            if (harvestLevel != 0) {
                builder.addStyleAttibutes(CapabilityItem.Styles.COMMON, Pair.of(EpicFightAttributes.ARMOR_NEGATION.get(), EpicFightAttributes.getArmorNegationModifier(10.0 * (double) harvestLevel)));
            }
            builder.addStyleAttibutes(CapabilityItem.Styles.COMMON, Pair.of(EpicFightAttributes.IMPACT.get(), EpicFightAttributes.getImpactModifier(0.7 + 0.3 * (double) harvestLevel)));
        }
        return builder;
    };

    public static final Function<Item, CapabilityItem.Builder> HOE = item -> {
        WeaponCapability.Builder builder = WeaponCapability.builder().category(CapabilityItem.WeaponCategories.HOE).hitSound(EpicFightSounds.BLADE_HIT.get()).collider(ColliderPreset.TOOLS).newStyleCombo(CapabilityItem.Styles.ONE_HAND, Animations.TOOL_AUTO1, Animations.TOOL_AUTO2, Animations.TOOL_DASH, Animations.SWORD_AIR_SLASH).newStyleCombo(CapabilityItem.Styles.MOUNT, Animations.SWORD_MOUNT_ATTACK);
        if (item instanceof TieredItem tieredItem) {
            int harvestLevel = tieredItem.getTier().getLevel();
            builder.addStyleAttibutes(CapabilityItem.Styles.COMMON, Pair.of(EpicFightAttributes.IMPACT.get(), EpicFightAttributes.getImpactModifier(-0.4 + 0.1 * (double) harvestLevel)));
        }
        return builder;
    };

    public static final Function<Item, CapabilityItem.Builder> PICKAXE = item -> {
        WeaponCapability.Builder builder = WeaponCapability.builder().category(CapabilityItem.WeaponCategories.PICKAXE).hitSound(EpicFightSounds.BLADE_HIT.get()).collider(ColliderPreset.TOOLS).newStyleCombo(CapabilityItem.Styles.ONE_HAND, Animations.AXE_AUTO1, Animations.AXE_AUTO2, Animations.AXE_DASH, Animations.AXE_AIRSLASH).newStyleCombo(CapabilityItem.Styles.MOUNT, Animations.SWORD_MOUNT_ATTACK);
        if (item instanceof TieredItem tieredItem) {
            int harvestLevel = tieredItem.getTier().getLevel();
            if (harvestLevel != 0) {
                builder.addStyleAttibutes(CapabilityItem.Styles.COMMON, Pair.of(EpicFightAttributes.ARMOR_NEGATION.get(), EpicFightAttributes.getArmorNegationModifier(6.0 * (double) harvestLevel)));
            }
            builder.addStyleAttibutes(CapabilityItem.Styles.COMMON, Pair.of(EpicFightAttributes.IMPACT.get(), EpicFightAttributes.getImpactModifier(0.4 + 0.1 * (double) harvestLevel)));
        }
        return builder;
    };

    public static final Function<Item, CapabilityItem.Builder> SHOVEL = item -> {
        WeaponCapability.Builder builder = WeaponCapability.builder().category(CapabilityItem.WeaponCategories.SHOVEL).collider(ColliderPreset.TOOLS).newStyleCombo(CapabilityItem.Styles.ONE_HAND, Animations.AXE_AUTO1, Animations.AXE_AUTO2, Animations.AXE_DASH, Animations.AXE_AIRSLASH).newStyleCombo(CapabilityItem.Styles.MOUNT, Animations.SWORD_MOUNT_ATTACK);
        if (item instanceof TieredItem tieredItem) {
            int harvestLevel = tieredItem.getTier().getLevel();
            builder.addStyleAttibutes(CapabilityItem.Styles.COMMON, Pair.of(EpicFightAttributes.IMPACT.get(), EpicFightAttributes.getImpactModifier(0.8 + 0.4 * (double) harvestLevel)));
        }
        return builder;
    };

    public static final Function<Item, CapabilityItem.Builder> SWORD = item -> {
        WeaponCapability.Builder builder = WeaponCapability.builder().category(CapabilityItem.WeaponCategories.SWORD).styleProvider(playerpatch -> playerpatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory() == CapabilityItem.WeaponCategories.SWORD ? CapabilityItem.Styles.TWO_HAND : CapabilityItem.Styles.ONE_HAND).collider(ColliderPreset.SWORD).newStyleCombo(CapabilityItem.Styles.ONE_HAND, Animations.SWORD_AUTO1, Animations.SWORD_AUTO2, Animations.SWORD_AUTO3, Animations.SWORD_DASH, Animations.SWORD_AIR_SLASH).newStyleCombo(CapabilityItem.Styles.TWO_HAND, Animations.SWORD_DUAL_AUTO1, Animations.SWORD_DUAL_AUTO2, Animations.SWORD_DUAL_AUTO3, Animations.SWORD_DUAL_DASH, Animations.SWORD_DUAL_AIR_SLASH).newStyleCombo(CapabilityItem.Styles.MOUNT, Animations.SWORD_MOUNT_ATTACK).innateSkill(CapabilityItem.Styles.ONE_HAND, itemstack -> EpicFightSkills.SWEEPING_EDGE).innateSkill(CapabilityItem.Styles.TWO_HAND, itemstack -> EpicFightSkills.DANCING_EDGE).livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.BLOCK, Animations.SWORD_GUARD).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.SWORD_DUAL_GUARD).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, Animations.BIPED_HOLD_DUAL_WEAPON).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.KNEEL, Animations.BIPED_HOLD_DUAL_WEAPON).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, Animations.BIPED_HOLD_DUAL_WEAPON).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.CHASE, Animations.BIPED_HOLD_DUAL_WEAPON).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_DUAL).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.SNEAK, Animations.BIPED_HOLD_DUAL_WEAPON).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.SWIM, Animations.BIPED_HOLD_DUAL_WEAPON).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.FLOAT, Animations.BIPED_HOLD_DUAL_WEAPON).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.FALL, Animations.BIPED_HOLD_DUAL_WEAPON).weaponCombinationPredicator(entitypatch -> EpicFightCapabilities.getItemStackCapability(((LivingEntity) entitypatch.getOriginal()).getOffhandItem()).getWeaponCategory() == CapabilityItem.WeaponCategories.SWORD);
        if (item instanceof TieredItem tieredItem) {
            builder.hitSound(tieredItem.getTier() == Tiers.WOOD ? EpicFightSounds.BLUNT_HIT.get() : EpicFightSounds.BLADE_HIT.get());
            builder.hitParticle(tieredItem.getTier() == Tiers.WOOD ? EpicFightParticles.HIT_BLUNT.get() : EpicFightParticles.HIT_BLADE.get());
        }
        return builder;
    };

    public static final Function<Item, CapabilityItem.Builder> SPEAR = item -> WeaponCapability.builder().category(CapabilityItem.WeaponCategories.SPEAR).styleProvider(playerpatch -> playerpatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory() == CapabilityItem.WeaponCategories.SHIELD ? CapabilityItem.Styles.ONE_HAND : CapabilityItem.Styles.TWO_HAND).collider(ColliderPreset.SPEAR).hitSound(EpicFightSounds.BLADE_HIT.get()).canBePlacedOffhand(false).newStyleCombo(CapabilityItem.Styles.ONE_HAND, Animations.SPEAR_ONEHAND_AUTO, Animations.SPEAR_DASH, Animations.SPEAR_ONEHAND_AIR_SLASH).newStyleCombo(CapabilityItem.Styles.TWO_HAND, Animations.SPEAR_TWOHAND_AUTO1, Animations.SPEAR_TWOHAND_AUTO2, Animations.SPEAR_DASH, Animations.SPEAR_TWOHAND_AIR_SLASH).newStyleCombo(CapabilityItem.Styles.MOUNT, Animations.SPEAR_MOUNT_ATTACK).innateSkill(CapabilityItem.Styles.ONE_HAND, itemstack -> EpicFightSkills.HEARTPIERCER).innateSkill(CapabilityItem.Styles.TWO_HAND, itemstack -> EpicFightSkills.GRASPING_SPIRE).livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.RUN, Animations.BIPED_RUN_SPEAR).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, Animations.BIPED_HOLD_SPEAR).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, Animations.BIPED_WALK_SPEAR).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.CHASE, Animations.BIPED_WALK_SPEAR).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_SPEAR).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.SWIM, Animations.BIPED_HOLD_SPEAR).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.SPEAR_GUARD);

    public static final Function<Item, CapabilityItem.Builder> GREATSWORD = item -> WeaponCapability.builder().category(CapabilityItem.WeaponCategories.GREATSWORD).styleProvider(playerpatch -> CapabilityItem.Styles.TWO_HAND).collider(ColliderPreset.GREATSWORD).swingSound(EpicFightSounds.WHOOSH_BIG.get()).hitSound(EpicFightSounds.BLADE_HIT.get()).canBePlacedOffhand(false).newStyleCombo(CapabilityItem.Styles.TWO_HAND, Animations.GREATSWORD_AUTO1, Animations.GREATSWORD_AUTO2, Animations.GREATSWORD_DASH, Animations.GREATSWORD_AIR_SLASH).innateSkill(CapabilityItem.Styles.TWO_HAND, itemstack -> EpicFightSkills.STEEL_WHIRLWIND).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, Animations.BIPED_HOLD_GREATSWORD).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, Animations.BIPED_WALK_GREATSWORD).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.CHASE, Animations.BIPED_WALK_GREATSWORD).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_GREATSWORD).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.JUMP, Animations.BIPED_HOLD_GREATSWORD).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.KNEEL, Animations.BIPED_HOLD_GREATSWORD).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.SNEAK, Animations.BIPED_HOLD_GREATSWORD).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.SWIM, Animations.BIPED_HOLD_GREATSWORD).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.FLY, Animations.BIPED_HOLD_GREATSWORD).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.CREATIVE_FLY, Animations.BIPED_HOLD_GREATSWORD).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.CREATIVE_IDLE, Animations.BIPED_HOLD_GREATSWORD).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.GREATSWORD_GUARD);

    public static final Function<Item, CapabilityItem.Builder> UCHIGATANA = item -> WeaponCapability.builder().category(CapabilityItem.WeaponCategories.UCHIGATANA).styleProvider(entitypatch -> {
        if (entitypatch instanceof PlayerPatch<?> playerpatch && playerpatch.getSkill(SkillSlots.WEAPON_PASSIVE).getDataManager().hasData(SkillDataKeys.SHEATH.get()) && playerpatch.getSkill(SkillSlots.WEAPON_PASSIVE).getDataManager().getDataValue(SkillDataKeys.SHEATH.get())) {
            return CapabilityItem.Styles.SHEATH;
        }
        return CapabilityItem.Styles.TWO_HAND;
    }).passiveSkill(EpicFightSkills.BATTOJUTSU_PASSIVE).hitSound(EpicFightSounds.BLADE_HIT.get()).collider(ColliderPreset.UCHIGATANA).canBePlacedOffhand(false).newStyleCombo(CapabilityItem.Styles.SHEATH, Animations.UCHIGATANA_SHEATHING_AUTO, Animations.UCHIGATANA_SHEATHING_DASH, Animations.UCHIGATANA_SHEATH_AIR_SLASH).newStyleCombo(CapabilityItem.Styles.TWO_HAND, Animations.UCHIGATANA_AUTO1, Animations.UCHIGATANA_AUTO2, Animations.UCHIGATANA_AUTO3, Animations.UCHIGATANA_DASH, Animations.UCHIGATANA_AIR_SLASH).newStyleCombo(CapabilityItem.Styles.MOUNT, Animations.SWORD_MOUNT_ATTACK).innateSkill(CapabilityItem.Styles.SHEATH, itemstack -> EpicFightSkills.BATTOJUTSU).innateSkill(CapabilityItem.Styles.TWO_HAND, itemstack -> EpicFightSkills.BATTOJUTSU).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, Animations.BIPED_HOLD_UCHIGATANA).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.KNEEL, Animations.BIPED_HOLD_UCHIGATANA).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, Animations.BIPED_WALK_UCHIGATANA).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.CHASE, Animations.BIPED_WALK_UCHIGATANA).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_UCHIGATANA).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.SNEAK, Animations.BIPED_WALK_UCHIGATANA).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.SWIM, Animations.BIPED_HOLD_UCHIGATANA).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.FLOAT, Animations.BIPED_HOLD_UCHIGATANA).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.FALL, Animations.BIPED_HOLD_UCHIGATANA).livingMotionModifier(CapabilityItem.Styles.SHEATH, LivingMotions.IDLE, Animations.BIPED_HOLD_UCHIGATANA_SHEATHING).livingMotionModifier(CapabilityItem.Styles.SHEATH, LivingMotions.KNEEL, Animations.BIPED_HOLD_UCHIGATANA_SHEATHING).livingMotionModifier(CapabilityItem.Styles.SHEATH, LivingMotions.WALK, Animations.BIPED_WALK_UCHIGATANA_SHEATHING).livingMotionModifier(CapabilityItem.Styles.SHEATH, LivingMotions.CHASE, Animations.BIPED_HOLD_UCHIGATANA_SHEATHING).livingMotionModifier(CapabilityItem.Styles.SHEATH, LivingMotions.RUN, Animations.BIPED_RUN_UCHIGATANA_SHEATHING).livingMotionModifier(CapabilityItem.Styles.SHEATH, LivingMotions.SNEAK, Animations.BIPED_HOLD_UCHIGATANA_SHEATHING).livingMotionModifier(CapabilityItem.Styles.SHEATH, LivingMotions.SWIM, Animations.BIPED_HOLD_UCHIGATANA_SHEATHING).livingMotionModifier(CapabilityItem.Styles.SHEATH, LivingMotions.FLOAT, Animations.BIPED_HOLD_UCHIGATANA_SHEATHING).livingMotionModifier(CapabilityItem.Styles.SHEATH, LivingMotions.FALL, Animations.BIPED_HOLD_UCHIGATANA_SHEATHING).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.UCHIGATANA_GUARD);

    public static final Function<Item, CapabilityItem.Builder> TACHI = item -> WeaponCapability.builder().category(CapabilityItem.WeaponCategories.TACHI).styleProvider(playerpatch -> CapabilityItem.Styles.TWO_HAND).collider(ColliderPreset.TACHI).hitSound(EpicFightSounds.BLADE_HIT.get()).canBePlacedOffhand(false).newStyleCombo(CapabilityItem.Styles.TWO_HAND, Animations.TACHI_AUTO1, Animations.TACHI_AUTO2, Animations.TACHI_AUTO3, Animations.TACHI_DASH, Animations.LONGSWORD_AIR_SLASH).newStyleCombo(CapabilityItem.Styles.MOUNT, Animations.SWORD_MOUNT_ATTACK).innateSkill(CapabilityItem.Styles.TWO_HAND, itemstack -> EpicFightSkills.RUSHING_TEMPO).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, Animations.BIPED_HOLD_TACHI).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.KNEEL, Animations.BIPED_HOLD_TACHI).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, Animations.BIPED_HOLD_TACHI).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.CHASE, Animations.BIPED_HOLD_TACHI).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_HOLD_TACHI).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.SNEAK, Animations.BIPED_HOLD_TACHI).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.SWIM, Animations.BIPED_HOLD_TACHI).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.FLOAT, Animations.BIPED_HOLD_TACHI).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.FALL, Animations.BIPED_HOLD_TACHI).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.LONGSWORD_GUARD);

    public static final Function<Item, CapabilityItem.Builder> LONGSWORD = item -> WeaponCapability.builder().category(CapabilityItem.WeaponCategories.LONGSWORD).styleProvider(playerpatch -> {
        if (playerpatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory() == CapabilityItem.WeaponCategories.SHIELD) {
            return CapabilityItem.Styles.ONE_HAND;
        } else if (playerpatch instanceof PlayerPatch<?> tplayerpatch) {
            return tplayerpatch.getSkill(SkillSlots.WEAPON_INNATE).isActivated() ? CapabilityItem.Styles.OCHS : CapabilityItem.Styles.TWO_HAND;
        } else {
            return CapabilityItem.Styles.TWO_HAND;
        }
    }).hitSound(EpicFightSounds.BLADE_HIT.get()).collider(ColliderPreset.LONGSWORD).canBePlacedOffhand(false).newStyleCombo(CapabilityItem.Styles.ONE_HAND, Animations.LONGSWORD_AUTO1, Animations.LONGSWORD_AUTO2, Animations.LONGSWORD_AUTO3, Animations.LONGSWORD_DASH, Animations.LONGSWORD_AIR_SLASH).newStyleCombo(CapabilityItem.Styles.TWO_HAND, Animations.LONGSWORD_AUTO1, Animations.LONGSWORD_AUTO2, Animations.LONGSWORD_AUTO3, Animations.LONGSWORD_DASH, Animations.LONGSWORD_AIR_SLASH).newStyleCombo(CapabilityItem.Styles.OCHS, Animations.LONGSWORD_LIECHTENAUER_AUTO1, Animations.LONGSWORD_LIECHTENAUER_AUTO2, Animations.LONGSWORD_LIECHTENAUER_AUTO3, Animations.LONGSWORD_DASH, Animations.LONGSWORD_AIR_SLASH).innateSkill(CapabilityItem.Styles.ONE_HAND, itemstack -> EpicFightSkills.SHARP_STAB).innateSkill(CapabilityItem.Styles.TWO_HAND, itemstack -> EpicFightSkills.LIECHTENAUER).innateSkill(CapabilityItem.Styles.OCHS, itemstack -> EpicFightSkills.LIECHTENAUER).livingMotionModifier(CapabilityItem.Styles.COMMON, LivingMotions.IDLE, Animations.BIPED_HOLD_LONGSWORD).livingMotionModifier(CapabilityItem.Styles.COMMON, LivingMotions.WALK, Animations.BIPED_WALK_LONGSWORD).livingMotionModifier(CapabilityItem.Styles.COMMON, LivingMotions.CHASE, Animations.BIPED_WALK_LONGSWORD).livingMotionModifier(CapabilityItem.Styles.COMMON, LivingMotions.RUN, Animations.BIPED_RUN_LONGSWORD).livingMotionModifier(CapabilityItem.Styles.COMMON, LivingMotions.SNEAK, Animations.BIPED_HOLD_LONGSWORD).livingMotionModifier(CapabilityItem.Styles.COMMON, LivingMotions.KNEEL, Animations.BIPED_HOLD_LONGSWORD).livingMotionModifier(CapabilityItem.Styles.COMMON, LivingMotions.JUMP, Animations.BIPED_HOLD_LONGSWORD).livingMotionModifier(CapabilityItem.Styles.COMMON, LivingMotions.SWIM, Animations.BIPED_HOLD_LONGSWORD).livingMotionModifier(CapabilityItem.Styles.COMMON, LivingMotions.BLOCK, Animations.LONGSWORD_GUARD).livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.IDLE, Animations.BIPED_HOLD_LIECHTENAUER).livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.WALK, Animations.BIPED_WALK_LIECHTENAUER).livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.CHASE, Animations.BIPED_WALK_LIECHTENAUER).livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.RUN, Animations.BIPED_HOLD_LIECHTENAUER).livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.SNEAK, Animations.BIPED_HOLD_LIECHTENAUER).livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.KNEEL, Animations.BIPED_HOLD_LIECHTENAUER).livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.JUMP, Animations.BIPED_HOLD_LIECHTENAUER).livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.SWIM, Animations.BIPED_HOLD_LIECHTENAUER).livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.BLOCK, Animations.SWORD_GUARD).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.LONGSWORD_GUARD).livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.BLOCK, Animations.LONGSWORD_GUARD);

    public static final Function<Item, CapabilityItem.Builder> DAGGER = item -> WeaponCapability.builder().category(CapabilityItem.WeaponCategories.DAGGER).styleProvider(playerpatch -> playerpatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory() == CapabilityItem.WeaponCategories.DAGGER ? CapabilityItem.Styles.TWO_HAND : CapabilityItem.Styles.ONE_HAND).hitSound(EpicFightSounds.BLADE_HIT.get()).swingSound(EpicFightSounds.WHOOSH_SMALL.get()).collider(ColliderPreset.DAGGER).weaponCombinationPredicator(entitypatch -> EpicFightCapabilities.getItemStackCapability(((LivingEntity) entitypatch.getOriginal()).getOffhandItem()).getWeaponCategory() == CapabilityItem.WeaponCategories.DAGGER).newStyleCombo(CapabilityItem.Styles.ONE_HAND, Animations.DAGGER_AUTO1, Animations.DAGGER_AUTO2, Animations.DAGGER_AUTO3, Animations.DAGGER_DASH, Animations.DAGGER_AIR_SLASH).newStyleCombo(CapabilityItem.Styles.TWO_HAND, Animations.DAGGER_DUAL_AUTO1, Animations.DAGGER_DUAL_AUTO2, Animations.DAGGER_DUAL_AUTO3, Animations.DAGGER_DUAL_AUTO4, Animations.DAGGER_DUAL_DASH, Animations.DAGGER_DUAL_AIR_SLASH).newStyleCombo(CapabilityItem.Styles.MOUNT, Animations.SWORD_MOUNT_ATTACK).innateSkill(CapabilityItem.Styles.ONE_HAND, itemstack -> EpicFightSkills.EVISCERATE).innateSkill(CapabilityItem.Styles.TWO_HAND, itemstack -> EpicFightSkills.BLADE_RUSH).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, Animations.BIPED_HOLD_DUAL_WEAPON).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.KNEEL, Animations.BIPED_HOLD_DUAL_WEAPON).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, Animations.BIPED_HOLD_DUAL_WEAPON).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.CHASE, Animations.BIPED_HOLD_DUAL_WEAPON).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_DUAL).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.SNEAK, Animations.BIPED_HOLD_DUAL_WEAPON).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.SWIM, Animations.BIPED_HOLD_DUAL_WEAPON).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.FLOAT, Animations.BIPED_HOLD_DUAL_WEAPON).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.FALL, Animations.BIPED_HOLD_DUAL_WEAPON);

    public static final Function<Item, CapabilityItem.Builder> FIST = item -> WeaponCapability.builder().newStyleCombo(CapabilityItem.Styles.ONE_HAND, Animations.FIST_AUTO1, Animations.FIST_AUTO2, Animations.FIST_AUTO3, Animations.FIST_DASH, Animations.FIST_AIR_SLASH).innateSkill(CapabilityItem.Styles.ONE_HAND, itemstack -> EpicFightSkills.RELENTLESS_COMBO).category(CapabilityItem.WeaponCategories.FIST).constructor(GloveCapability::new);

    public static final Function<Item, CapabilityItem.Builder> BOW = item -> RangedWeaponCapability.builder().addAnimationsModifier(LivingMotions.IDLE, Animations.BIPED_IDLE).addAnimationsModifier(LivingMotions.WALK, Animations.BIPED_WALK).addAnimationsModifier(LivingMotions.AIM, Animations.BIPED_BOW_AIM).addAnimationsModifier(LivingMotions.SHOT, Animations.BIPED_BOW_SHOT);

    public static final Function<Item, CapabilityItem.Builder> CROSSBOW = item -> RangedWeaponCapability.builder().addAnimationsModifier(LivingMotions.IDLE, Animations.BIPED_HOLD_CROSSBOW).addAnimationsModifier(LivingMotions.KNEEL, Animations.BIPED_HOLD_CROSSBOW).addAnimationsModifier(LivingMotions.WALK, Animations.BIPED_HOLD_CROSSBOW).addAnimationsModifier(LivingMotions.RUN, Animations.BIPED_HOLD_CROSSBOW).addAnimationsModifier(LivingMotions.SNEAK, Animations.BIPED_HOLD_CROSSBOW).addAnimationsModifier(LivingMotions.SWIM, Animations.BIPED_HOLD_CROSSBOW).addAnimationsModifier(LivingMotions.FLOAT, Animations.BIPED_HOLD_CROSSBOW).addAnimationsModifier(LivingMotions.FALL, Animations.BIPED_HOLD_CROSSBOW).addAnimationsModifier(LivingMotions.RELOAD, Animations.BIPED_CROSSBOW_RELOAD).addAnimationsModifier(LivingMotions.AIM, Animations.BIPED_CROSSBOW_AIM).addAnimationsModifier(LivingMotions.SHOT, Animations.BIPED_CROSSBOW_SHOT);

    public static final Function<Item, CapabilityItem.Builder> TRIDENT = item -> RangedWeaponCapability.builder().addAnimationsModifier(LivingMotions.IDLE, Animations.BIPED_IDLE).addAnimationsModifier(LivingMotions.WALK, Animations.BIPED_WALK).addAnimationsModifier(LivingMotions.AIM, Animations.BIPED_JAVELIN_AIM).addAnimationsModifier(LivingMotions.SHOT, Animations.BIPED_JAVELIN_THROW).constructor(TridentCapability::new).category(CapabilityItem.WeaponCategories.TRIDENT);

    public static final Function<Item, CapabilityItem.Builder> SHIELD = item -> CapabilityItem.builder().constructor(ShieldCapability::new).category(CapabilityItem.WeaponCategories.SHIELD);
}