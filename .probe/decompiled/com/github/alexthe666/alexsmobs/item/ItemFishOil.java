package com.github.alexthe666.alexsmobs.item;

import com.github.alexthe666.alexsmobs.config.AMConfig;
import com.github.alexthe666.alexsmobs.effect.AMEffectRegistry;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class ItemFishOil extends Item {

    public ItemFishOil(Item.Properties p_i225737_1_) {
        super(p_i225737_1_);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack p_77654_1_, Level p_77654_2_, LivingEntity p_77654_3_) {
        super.finishUsingItem(p_77654_1_, p_77654_2_, p_77654_3_);
        if (AMConfig.fishOilMeme) {
            p_77654_3_.addEffect(new MobEffectInstance(AMEffectRegistry.OILED.get(), 1200, 0));
        }
        if (p_77654_3_ instanceof ServerPlayer lvt_4_1_) {
            CriteriaTriggers.CONSUME_ITEM.trigger(lvt_4_1_, p_77654_1_);
            lvt_4_1_.m_36246_(Stats.ITEM_USED.get(this));
        }
        if (p_77654_1_.isEmpty()) {
            return new ItemStack(Items.GLASS_BOTTLE);
        } else {
            if (p_77654_3_ instanceof Player && !((Player) p_77654_3_).getAbilities().instabuild) {
                ItemStack lvt_4_2_ = new ItemStack(Items.GLASS_BOTTLE);
                Player lvt_5_1_ = (Player) p_77654_3_;
                if (!lvt_5_1_.getInventory().add(lvt_4_2_)) {
                    lvt_5_1_.drop(lvt_4_2_, false);
                }
            }
            return p_77654_1_;
        }
    }

    @Override
    public int getUseDuration(ItemStack p_77626_1_) {
        return 40;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_77661_1_) {
        return UseAnim.DRINK;
    }

    @Override
    public SoundEvent getDrinkingSound() {
        return SoundEvents.HONEY_DRINK;
    }

    @Override
    public SoundEvent getEatingSound() {
        return SoundEvents.HONEY_DRINK;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        return ItemUtils.startUsingInstantly(level, player, interactionHand);
    }
}