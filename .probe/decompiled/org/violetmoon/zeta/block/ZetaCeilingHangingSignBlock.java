package org.violetmoon.zeta.block;

import java.util.function.BooleanSupplier;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.jetbrains.annotations.Nullable;
import org.violetmoon.zeta.module.ZetaModule;
import org.violetmoon.zeta.util.BooleanSuppliers;

public class ZetaCeilingHangingSignBlock extends CeilingHangingSignBlock implements IZetaBlock {

    @Nullable
    private final ZetaModule module;

    private BooleanSupplier enabledSupplier = BooleanSuppliers.TRUE;

    public ZetaCeilingHangingSignBlock(String regname, @Nullable ZetaModule module, WoodType type, BlockBehaviour.Properties properties) {
        super(properties, type);
        this.module = module;
        if (module != null) {
            module.zeta.registry.registerBlock(this, regname, false);
        }
    }

    public ZetaCeilingHangingSignBlock setCondition(BooleanSupplier enabledSupplier) {
        this.enabledSupplier = enabledSupplier;
        return this;
    }

    @Override
    public boolean doesConditionApply() {
        return this.enabledSupplier.getAsBoolean();
    }

    @Nullable
    @Override
    public ZetaModule getModule() {
        return this.module;
    }
}