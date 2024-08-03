package yesman.epicfight.world.capabilities.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.mojang.datafixers.util.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.Enchantments;
import yesman.epicfight.api.animation.LivingMotion;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.collider.Collider;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.network.EpicFightNetworkManager;
import yesman.epicfight.network.server.SPChangeSkill;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.particle.HitParticleType;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.skill.guard.GuardSkill;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.entity.ai.attribute.EpicFightAttributes;

public class CapabilityItem {

    public static CapabilityItem EMPTY = builder().build();

    protected static List<StaticAnimation> commonAutoAttackMotion = new ArrayList();

    protected final WeaponCategory weaponCategory;

    protected Map<Style, Map<Attribute, AttributeModifier>> attributeMap;

    public static List<StaticAnimation> getBasicAutoAttackMotion() {
        return commonAutoAttackMotion;
    }

    protected CapabilityItem(CapabilityItem.Builder builder) {
        this.weaponCategory = builder.category;
        this.attributeMap = builder.attributeMap;
    }

    public void modifyItemTooltip(ItemStack itemstack, List<Component> itemTooltip, LivingEntityPatch<?> entitypatch) {
        if (!this.getStyle(entitypatch).canUseOffhand()) {
            itemTooltip.add(1, Component.literal(" ").append(Component.translatable("attribute.name.epicfight.twohanded").withStyle(ChatFormatting.DARK_GRAY)));
        }
        Map<Attribute, AttributeModifier> attribute = this.getDamageAttributesInCondition(this.getStyle(entitypatch));
        int index = 0;
        boolean modifyIn = false;
        for (int i = 0; i < itemTooltip.size(); i++) {
            Component textComp = (Component) itemTooltip.get(i);
            index = i;
            if (this.findComponentArgument(textComp, Attributes.ATTACK_SPEED.getDescriptionId()) != null) {
                modifyIn = true;
                break;
            }
        }
        index++;
        if (attribute != null) {
            if (!modifyIn) {
                itemTooltip.add(index, Component.literal(""));
                itemTooltip.add(++index, Component.translatable("epicfight.gui.attribute").withStyle(ChatFormatting.GRAY));
                index++;
            }
            Attribute armorNegation = EpicFightAttributes.ARMOR_NEGATION.get();
            Attribute impact = EpicFightAttributes.IMPACT.get();
            Attribute maxStrikes = EpicFightAttributes.MAX_STRIKES.get();
            if (attribute.containsKey(armorNegation)) {
                double value = ((AttributeModifier) attribute.get(armorNegation)).getAmount() + entitypatch.getOriginal().getAttribute(armorNegation).getBaseValue();
                if (value > 0.0) {
                    itemTooltip.add(index, Component.literal(" ").append(Component.translatable(armorNegation.getDescriptionId(), ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(value))));
                }
            }
            if (attribute.containsKey(impact)) {
                double value = ((AttributeModifier) attribute.get(impact)).getAmount() + entitypatch.getOriginal().getAttribute(impact).getBaseValue();
                if (value > 0.0) {
                    int ix = itemstack.getEnchantmentLevel(Enchantments.KNOCKBACK);
                    value *= (double) (1.0F + (float) ix * 0.12F);
                    itemTooltip.add(index++, Component.literal(" ").append(Component.translatable(impact.getDescriptionId(), ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(value))));
                }
            }
            if (attribute.containsKey(maxStrikes)) {
                double value = ((AttributeModifier) attribute.get(maxStrikes)).getAmount() + entitypatch.getOriginal().getAttribute(maxStrikes).getBaseValue();
                if (value > 0.0) {
                    itemTooltip.add(index++, Component.literal(" ").append(Component.translatable(maxStrikes.getDescriptionId(), ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(value))));
                }
            } else {
                itemTooltip.add(index++, Component.literal(" ").append(Component.translatable(maxStrikes.getDescriptionId(), ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(maxStrikes.getDefaultValue()))));
            }
        }
    }

    private Object findComponentArgument(Component component, String key) {
        if (component.getContents() instanceof TranslatableContents contents) {
            if (contents.getKey().equals(key)) {
                return component;
            }
            for (Object arg : contents.getArgs()) {
                if (arg instanceof Component argComponent) {
                    Object ret = this.findComponentArgument(argComponent, key);
                    if (ret != null) {
                        return ret;
                    }
                }
            }
        }
        for (Component siblingComponent : component.getSiblings()) {
            Object ret = this.findComponentArgument(siblingComponent, key);
            if (ret != null) {
                return ret;
            }
        }
        return null;
    }

    public List<StaticAnimation> getAutoAttckMotion(PlayerPatch<?> playerpatch) {
        return getBasicAutoAttackMotion();
    }

    public List<StaticAnimation> getMountAttackMotion() {
        return null;
    }

    @Nullable
    public Skill getInnateSkill(PlayerPatch<?> playerpatch, ItemStack itemstack) {
        return null;
    }

    @Nullable
    public Skill getPassiveSkill() {
        return null;
    }

    public WeaponCategory getWeaponCategory() {
        return this.weaponCategory;
    }

    public void changeWeaponInnateSkill(PlayerPatch<?> playerpatch, ItemStack itemstack) {
        Skill weaponInnateSkill = this.getInnateSkill(playerpatch, itemstack);
        String skillName = "";
        SPChangeSkill.State state = SPChangeSkill.State.ENABLE;
        SkillContainer weaponInnateSkillContainer = playerpatch.getSkill(SkillSlots.WEAPON_INNATE);
        if (weaponInnateSkill != null) {
            if (weaponInnateSkillContainer.getSkill() != weaponInnateSkill) {
                weaponInnateSkillContainer.setSkill(weaponInnateSkill);
            }
            skillName = weaponInnateSkill.toString();
        } else {
            state = SPChangeSkill.State.DISABLE;
        }
        weaponInnateSkillContainer.setDisabled(weaponInnateSkill == null);
        EpicFightNetworkManager.sendToPlayer(new SPChangeSkill(SkillSlots.WEAPON_INNATE, skillName, state), (ServerPlayer) playerpatch.getOriginal());
        Skill skill = this.getPassiveSkill();
        SkillContainer passiveSkillContainer = playerpatch.getSkill(SkillSlots.WEAPON_PASSIVE);
        if (skill != null) {
            if (passiveSkillContainer.getSkill() != skill) {
                passiveSkillContainer.setSkill(skill);
                EpicFightNetworkManager.sendToPlayer(new SPChangeSkill(SkillSlots.WEAPON_PASSIVE, skill.toString(), SPChangeSkill.State.ENABLE), (ServerPlayer) playerpatch.getOriginal());
            }
        } else {
            passiveSkillContainer.setSkill(null);
            EpicFightNetworkManager.sendToPlayer(new SPChangeSkill(SkillSlots.WEAPON_PASSIVE, "empty", SPChangeSkill.State.ENABLE), (ServerPlayer) playerpatch.getOriginal());
        }
    }

    public SoundEvent getSmashingSound() {
        return EpicFightSounds.WHOOSH.get();
    }

    public SoundEvent getHitSound() {
        return EpicFightSounds.BLUNT_HIT.get();
    }

    public Collider getWeaponCollider() {
        return ColliderPreset.FIST;
    }

    public HitParticleType getHitParticle() {
        return EpicFightParticles.HIT_BLUNT.get();
    }

    public void addStyleAttibutes(Style style, Pair<Attribute, AttributeModifier> attributePair) {
        Map<Attribute, AttributeModifier> map = (Map<Attribute, AttributeModifier>) this.attributeMap.computeIfAbsent(style, key -> Maps.newHashMap());
        map.put((Attribute) attributePair.getFirst(), (AttributeModifier) attributePair.getSecond());
    }

    public void addStyleAttributes(Style style, double armorNegation, double impact, int maxStrikes) {
        if (Double.compare(armorNegation, 0.0) != 0) {
            this.addStyleAttibutes(style, Pair.of(EpicFightAttributes.ARMOR_NEGATION.get(), EpicFightAttributes.getArmorNegationModifier(armorNegation)));
        }
        if (Double.compare(impact, 0.0) != 0) {
            this.addStyleAttibutes(style, Pair.of(EpicFightAttributes.IMPACT.get(), EpicFightAttributes.getImpactModifier(impact)));
        }
        if (Double.compare((double) maxStrikes, 0.0) != 0) {
            this.addStyleAttibutes(style, Pair.of(EpicFightAttributes.MAX_STRIKES.get(), EpicFightAttributes.getMaxStrikesModifier(maxStrikes)));
        }
    }

    public final Map<Attribute, AttributeModifier> getDamageAttributesInCondition(Style style) {
        Map<Attribute, AttributeModifier> attributes = (Map<Attribute, AttributeModifier>) this.attributeMap.getOrDefault(style, Maps.newHashMap());
        ((Map) this.attributeMap.getOrDefault(CapabilityItem.Styles.COMMON, Maps.newHashMap())).forEach(attributes::putIfAbsent);
        return attributes;
    }

    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot equipmentSlot, @Nullable LivingEntityPatch<?> entitypatch) {
        Multimap<Attribute, AttributeModifier> map = HashMultimap.create();
        if (entitypatch != null) {
            Map<Attribute, AttributeModifier> modifierMap = this.getDamageAttributesInCondition(this.getStyle(entitypatch));
            if (modifierMap != null) {
                for (Entry<Attribute, AttributeModifier> entry : modifierMap.entrySet()) {
                    map.put((Attribute) entry.getKey(), (AttributeModifier) entry.getValue());
                }
            }
        }
        return map;
    }

    public Multimap<Attribute, AttributeModifier> getAllAttributeModifiers(EquipmentSlot equipmentSlot) {
        Multimap<Attribute, AttributeModifier> map = HashMultimap.create();
        for (Map<Attribute, AttributeModifier> attrMap : this.attributeMap.values()) {
            for (Entry<Attribute, AttributeModifier> entry : attrMap.entrySet()) {
                map.put((Attribute) entry.getKey(), (AttributeModifier) entry.getValue());
            }
        }
        return map;
    }

    public Map<LivingMotion, StaticAnimation> getLivingMotionModifier(LivingEntityPatch<?> playerpatch, InteractionHand hand) {
        return Maps.newHashMap();
    }

    public Style getStyle(LivingEntityPatch<?> entitypatch) {
        return this.canBePlacedOffhand() ? CapabilityItem.Styles.ONE_HAND : CapabilityItem.Styles.TWO_HAND;
    }

    public StaticAnimation getGuardMotion(GuardSkill skill, GuardSkill.BlockType blockType, PlayerPatch<?> playerpatch) {
        return null;
    }

    public boolean canBePlacedOffhand() {
        return true;
    }

    public boolean shouldCancelCombo(LivingEntityPatch<?> entitypatch) {
        return true;
    }

    public boolean isEmpty() {
        return this == EMPTY;
    }

    public CapabilityItem getResult(ItemStack item) {
        return this;
    }

    public boolean availableOnHorse() {
        return true;
    }

    public void setConfigFileAttribute(double armorNegation1, double impact1, int maxStrikes1, double armorNegation2, double impact2, int maxStrikes2) {
        this.addStyleAttributes(CapabilityItem.Styles.ONE_HAND, armorNegation1, impact1, maxStrikes1);
        this.addStyleAttributes(CapabilityItem.Styles.TWO_HAND, armorNegation2, impact2, maxStrikes2);
    }

    public boolean checkOffhandValid(LivingEntityPatch<?> entitypatch) {
        return this.getStyle(entitypatch).canUseOffhand() && EpicFightCapabilities.getItemStackCapability(entitypatch.getOriginal().getOffhandItem()).canHoldInOffhandAlone();
    }

    public boolean canHoldInOffhandAlone() {
        return true;
    }

    public UseAnim getUseAnimation(LivingEntityPatch<?> entitypatch) {
        return UseAnim.NONE;
    }

    public static CapabilityItem.Builder builder() {
        return new CapabilityItem.Builder();
    }

    static {
        commonAutoAttackMotion.add(Animations.FIST_AUTO1);
        commonAutoAttackMotion.add(Animations.FIST_AUTO2);
        commonAutoAttackMotion.add(Animations.FIST_AUTO3);
        commonAutoAttackMotion.add(Animations.FIST_DASH);
        commonAutoAttackMotion.add(Animations.FIST_AIR_SLASH);
    }

    public static class Builder {

        Function<CapabilityItem.Builder, CapabilityItem> constructor = CapabilityItem::new;

        WeaponCategory category = CapabilityItem.WeaponCategories.FIST;

        Map<Style, Map<Attribute, AttributeModifier>> attributeMap = Maps.newHashMap();

        protected Builder() {
        }

        public CapabilityItem.Builder constructor(Function<CapabilityItem.Builder, CapabilityItem> constructor) {
            this.constructor = constructor;
            return this;
        }

        public CapabilityItem.Builder category(WeaponCategory category) {
            this.category = category;
            return this;
        }

        public CapabilityItem.Builder addStyleAttibutes(Style style, Pair<Attribute, AttributeModifier> attributePair) {
            Map<Attribute, AttributeModifier> map = (Map<Attribute, AttributeModifier>) this.attributeMap.computeIfAbsent(style, key -> Maps.newHashMap());
            map.put((Attribute) attributePair.getFirst(), (AttributeModifier) attributePair.getSecond());
            return this;
        }

        public final CapabilityItem build() {
            return (CapabilityItem) this.constructor.apply(this);
        }
    }

    public static enum Styles implements Style {

        COMMON(true),
        ONE_HAND(true),
        TWO_HAND(false),
        MOUNT(true),
        RANGED(false),
        SHEATH(false),
        OCHS(false);

        final boolean canUseOffhand;

        final int id = Style.ENUM_MANAGER.assign(this);

        private Styles(boolean canUseOffhand) {
            this.canUseOffhand = canUseOffhand;
        }

        @Override
        public int universalOrdinal() {
            return this.id;
        }

        @Override
        public boolean canUseOffhand() {
            return this.canUseOffhand;
        }
    }

    public static enum WeaponCategories implements WeaponCategory {

        NOT_WEAPON,
        AXE,
        FIST,
        GREATSWORD,
        HOE,
        PICKAXE,
        SHOVEL,
        SWORD,
        UCHIGATANA,
        SPEAR,
        TACHI,
        TRIDENT,
        LONGSWORD,
        DAGGER,
        SHIELD,
        RANGED;

        final int id = WeaponCategory.ENUM_MANAGER.assign(this);

        @Override
        public int universalOrdinal() {
            return this.id;
        }
    }
}