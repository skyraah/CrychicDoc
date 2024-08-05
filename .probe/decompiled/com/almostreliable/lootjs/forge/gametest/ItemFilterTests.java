package com.almostreliable.lootjs.forge.gametest;

import com.almostreliable.lootjs.filters.ItemFilter;
import com.almostreliable.lootjs.filters.ResourceLocationFilter;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.gametest.GameTestHolder;
import net.minecraftforge.gametest.PrefixGameTestTemplate;

@GameTestHolder("lootjs")
@PrefixGameTestTemplate(false)
public class ItemFilterTests {

    @GameTest(m_177046_ = "empty_test_structure")
    public void simpleTest(GameTestHelper helper) {
        helper.succeedIf(() -> {
            ItemFilterTests.ExampleLoot loot = new ItemFilterTests.ExampleLoot();
            ItemFilter filter = ItemFilter.hasEnchantment(rl -> rl.equals(EnchantmentHelper.getEnchantmentId(Enchantments.PROJECTILE_PROTECTION)));
            GameTestUtils.assertTrue(helper, filter.test(loot.helmet), "Helmet should pass filter");
            GameTestUtils.assertTrue(helper, filter.test(loot.chestPlate), "Chestplate should pass filter");
            GameTestUtils.assertFalse(helper, filter.test(loot.sword), "Sword should not pass filter");
            GameTestUtils.assertFalse(helper, filter.test(loot.book), "Book should not pass filter");
        });
    }

    @GameTest(m_177046_ = "empty_test_structure")
    public void enchantmentFilterWithOr(GameTestHelper helper) {
        helper.succeedIf(() -> {
            ItemFilterTests.ExampleLoot loot = new ItemFilterTests.ExampleLoot();
            ItemFilter filter = ItemFilter.hasEnchantment(rl -> rl.equals(EnchantmentHelper.getEnchantmentId(Enchantments.PROJECTILE_PROTECTION))).or(ItemFilter.SWORD);
            GameTestUtils.assertTrue(helper, filter.test(loot.helmet), "Helmet should pass filter");
            GameTestUtils.assertTrue(helper, filter.test(loot.chestPlate), "Chestplate should pass filter");
            GameTestUtils.assertTrue(helper, filter.test(loot.sword), "Sword should pass filter");
            GameTestUtils.assertFalse(helper, filter.test(loot.book), "Book should not pass filter");
        });
    }

    @GameTest(m_177046_ = "empty_test_structure")
    public void enchantmentFilterWithAnd(GameTestHelper helper) {
        helper.succeedIf(() -> {
            ItemFilterTests.ExampleLoot loot = new ItemFilterTests.ExampleLoot();
            ItemFilter filter = ItemFilter.hasEnchantment(rl -> rl.equals(EnchantmentHelper.getEnchantmentId(Enchantments.SHARPNESS))).and(ItemFilter.SWORD);
            GameTestUtils.assertFalse(helper, filter.test(loot.helmet), "Helmet should not pass filter");
            GameTestUtils.assertFalse(helper, filter.test(loot.chestPlate), "Chestplate not should pass filter");
            GameTestUtils.assertTrue(helper, filter.test(loot.sword), "Sword should pass filter");
            GameTestUtils.assertFalse(helper, filter.test(loot.book), "Book should not pass filter");
        });
    }

    @GameTest(m_177046_ = "empty_test_structure")
    public void enchantmentFilterWithAndFails(GameTestHelper helper) {
        helper.succeedIf(() -> {
            ItemFilterTests.ExampleLoot loot = new ItemFilterTests.ExampleLoot();
            ItemFilter filter = ItemFilter.hasEnchantment(rl -> rl.equals(EnchantmentHelper.getEnchantmentId(Enchantments.MOB_LOOTING))).and(ItemFilter.SWORD);
            GameTestUtils.assertFalse(helper, filter.test(loot.helmet), "Helmet should not pass filter");
            GameTestUtils.assertFalse(helper, filter.test(loot.chestPlate), "Chestplate not should pass filter");
            GameTestUtils.assertFalse(helper, filter.test(loot.sword), "Sword should not pass filter");
            GameTestUtils.assertFalse(helper, filter.test(loot.book), "Book should not pass filter");
        });
    }

    @GameTest(m_177046_ = "empty_test_structure")
    public void enchantmentRegex(GameTestHelper helper) {
        helper.succeedIf(() -> {
            ItemFilterTests.ExampleLoot loot = new ItemFilterTests.ExampleLoot();
            ItemFilter filter = ItemFilter.hasEnchantment(new ResourceLocationFilter.ByPattern(Pattern.compile(".*sharpness.*")));
            GameTestUtils.assertFalse(helper, filter.test(loot.helmet), "Helmet should not pass filter");
            GameTestUtils.assertFalse(helper, filter.test(loot.chestPlate), "Chestplate should not pass filter");
            GameTestUtils.assertTrue(helper, filter.test(loot.sword), "Sword should pass filter");
            GameTestUtils.assertFalse(helper, filter.test(loot.book), "Book should not pass filter");
        });
    }

    @GameTest(m_177046_ = "empty_test_structure")
    public void enchantmentRegexOrLocation(GameTestHelper helper) {
        helper.succeedIf(() -> {
            ItemFilterTests.ExampleLoot loot = new ItemFilterTests.ExampleLoot();
            ResourceLocationFilter rlf = new ResourceLocationFilter.Or(List.of(new ResourceLocationFilter.ByPattern(Pattern.compile(".*sharpness.*")), new ResourceLocationFilter.ByLocation((ResourceLocation) Objects.requireNonNull(EnchantmentHelper.getEnchantmentId(Enchantments.INFINITY_ARROWS)))));
            ItemFilter filter = ItemFilter.hasEnchantment(rlf);
            GameTestUtils.assertFalse(helper, filter.test(loot.helmet), "Helmet should not pass filter");
            GameTestUtils.assertFalse(helper, filter.test(loot.chestPlate), "Chestplate should not pass filter");
            GameTestUtils.assertTrue(helper, filter.test(loot.sword), "Sword should pass filter");
            GameTestUtils.assertTrue(helper, filter.test(loot.book), "Book should pass filter");
        });
    }

    @GameTest(m_177046_ = "empty_test_structure")
    public void enchantmentMinInclusive(GameTestHelper helper) {
        helper.succeedIf(() -> {
            EnchantmentInstance e = new EnchantmentInstance(Enchantments.PROJECTILE_PROTECTION, 2);
            ItemFilter filter = ItemFilter.hasEnchantment(rl -> rl.equals(EnchantmentHelper.getEnchantmentId(Enchantments.PROJECTILE_PROTECTION)), 2, 5);
            GameTestUtils.assertTrue(helper, filter.test(EnchantedBookItem.createForEnchantment(e)), "Enchantment in range");
        });
    }

    @GameTest(m_177046_ = "empty_test_structure")
    public void enchantmentMaxInclusive(GameTestHelper helper) {
        helper.succeedIf(() -> {
            EnchantmentInstance e = new EnchantmentInstance(Enchantments.PROJECTILE_PROTECTION, 5);
            ItemFilter filter = ItemFilter.hasEnchantment(rl -> rl.equals(EnchantmentHelper.getEnchantmentId(Enchantments.PROJECTILE_PROTECTION)), 2, 5);
            GameTestUtils.assertTrue(helper, filter.test(EnchantedBookItem.createForEnchantment(e)), "Enchantment in range");
        });
    }

    private static class ExampleLoot {

        private final ItemStack helmet = new ItemStack(Items.IRON_HELMET);

        private final ItemStack sword;

        private final ItemStack book;

        private final ItemStack chestPlate;

        public ExampleLoot() {
            this.helmet.enchant(Enchantments.PROJECTILE_PROTECTION, 4);
            this.chestPlate = new ItemStack(Items.IRON_HELMET);
            this.chestPlate.enchant(Enchantments.PROJECTILE_PROTECTION, 4);
            this.sword = new ItemStack(Items.IRON_SWORD);
            this.sword.enchant(Enchantments.SHARPNESS, 4);
            this.book = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(Enchantments.INFINITY_ARROWS, 3));
        }
    }
}