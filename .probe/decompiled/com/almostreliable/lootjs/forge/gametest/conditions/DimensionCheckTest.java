package com.almostreliable.lootjs.forge.gametest.conditions;

import com.almostreliable.lootjs.forge.gametest.GameTestUtils;
import com.almostreliable.lootjs.loot.condition.AnyDimension;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.gametest.GameTestHolder;
import net.minecraftforge.gametest.PrefixGameTestTemplate;

@GameTestHolder("lootjs")
@PrefixGameTestTemplate(false)
public class DimensionCheckTest {

    private static final Vec3 TEST_POS = new Vec3(1.0, 0.0, 1.0);

    @GameTest(m_177046_ = "empty_test_structure")
    public void AnyDimension_match(GameTestHelper helper) {
        LootContext ctx = GameTestUtils.unknownContext(helper.getLevel(), TEST_POS);
        AnyDimension owDim = new AnyDimension(new ResourceLocation[] { new ResourceLocation("overworld") });
        helper.succeedIf(() -> GameTestUtils.assertTrue(helper, owDim.test(ctx), "Is in overworld check should pass"));
    }

    @GameTest(m_177046_ = "empty_test_structure")
    public void AnyDimension_fail(GameTestHelper helper) {
        LootContext ctx = GameTestUtils.unknownContext(helper.getLevel(), TEST_POS);
        AnyDimension owDim = new AnyDimension(new ResourceLocation[] { new ResourceLocation("nether") });
        helper.succeedIf(() -> GameTestUtils.assertFalse(helper, owDim.test(ctx), "Is in overworld check should fail"));
    }
}