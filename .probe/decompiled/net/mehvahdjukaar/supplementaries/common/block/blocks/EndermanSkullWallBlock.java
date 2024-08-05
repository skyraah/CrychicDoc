package net.mehvahdjukaar.supplementaries.common.block.blocks;

import com.google.common.collect.Maps;
import java.util.Map;
import net.mehvahdjukaar.moonlight.api.client.util.ParticleUtil;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.supplementaries.common.block.ModBlockProperties;
import net.mehvahdjukaar.supplementaries.common.block.tiles.EndermanSkullBlockTile;
import net.mehvahdjukaar.supplementaries.reg.ModRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class EndermanSkullWallBlock extends WallSkullBlock {

    public static final BooleanProperty WATCHED = ModBlockProperties.WATCHED;

    private static final Map<Direction, VoxelShape> AABBS_ANGERY = Maps.newEnumMap(Map.of(Direction.NORTH, Block.box(4.0, 4.0, 8.0, 12.0, 18.0, 16.0), Direction.SOUTH, Block.box(4.0, 4.0, 0.0, 12.0, 18.0, 8.0), Direction.EAST, Block.box(0.0, 4.0, 4.0, 8.0, 18.0, 12.0), Direction.WEST, Block.box(8.0, 4.0, 4.0, 16.0, 18.0, 12.0)));

    public EndermanSkullWallBlock(BlockBehaviour.Properties properties) {
        super(EndermanSkullBlock.TYPE, properties);
        this.m_49959_((BlockState) ((BlockState) this.m_49966_().m_61124_(WATCHED, false)).m_61124_(EndermanSkullBlock.POWER, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WATCHED);
        builder.add(EndermanSkullBlock.POWER);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.m_61143_(WATCHED) ? (VoxelShape) AABBS_ANGERY.get(state.m_61143_(f_58097_)) : super.getShape(state, level, pos, context);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type) {
        return Utils.getTicker(type, (BlockEntityType) ModRegistry.ENDERMAN_SKULL_TILE.get(), EndermanSkullBlockTile::tick);
    }

    @Override
    public boolean isSignalSource(@NotNull BlockState state) {
        return true;
    }

    @Override
    public int getSignal(BlockState blockState, @NotNull BlockGetter blockAccess, @NotNull BlockPos pos, @NotNull Direction side) {
        return (Integer) blockState.m_61143_(EndermanSkullBlock.POWER);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new EndermanSkullBlockTile(pos, state);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if ((Boolean) state.m_61143_(WATCHED)) {
            ParticleUtil.spawnParticleOnBlockShape(level, pos, ParticleTypes.PORTAL, UniformInt.of(1, 1 + (Integer) state.m_61143_(EndermanSkullBlock.POWER) / 2), 0.5F);
        }
    }
}