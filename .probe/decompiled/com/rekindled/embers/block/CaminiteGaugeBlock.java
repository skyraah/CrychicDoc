package com.rekindled.embers.block;

import com.rekindled.embers.RegistryManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class CaminiteGaugeBlock extends Block implements SimpleWaterloggedBlock {

    public CaminiteGaugeBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
        this.m_49959_((BlockState) ((BlockState) this.f_49792_.any()).m_61124_(BlockStateProperties.WATERLOGGED, false));
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.INVISIBLE;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.m_60713_(newState.m_60734_())) {
            for (MechEdgeBlockBase.MechEdge edge : MechEdgeBlockBase.MechEdge.values()) {
                BlockPos cornerPos = pos.subtract(edge.centerPos);
                if (level.getBlockState(cornerPos).m_60734_() instanceof MechEdgeBlockBase) {
                    level.m_46961_(cornerPos, false);
                }
            }
            super.m_6810_(state, level, pos, newState, isMoving);
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        boolean clear = true;
        for (MechEdgeBlockBase.MechEdge edge : MechEdgeBlockBase.MechEdge.values()) {
            if (!pContext.m_43725_().getBlockState(pContext.getClickedPos().subtract(edge.centerPos)).m_60629_(pContext)) {
                clear = false;
            }
        }
        return clear ? (BlockState) super.getStateForPlacement(pContext).m_61124_(BlockStateProperties.WATERLOGGED, pContext.m_43725_().getFluidState(pContext.getClickedPos()).getType() == Fluids.WATER) : null;
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        for (MechEdgeBlockBase.MechEdge edge : MechEdgeBlockBase.MechEdge.values()) {
            BlockState edgeState = (BlockState) RegistryManager.CAMINITE_GAUGE_EDGE.get().defaultBlockState().m_61124_(BlockStateProperties.WATERLOGGED, level.getFluidState(pos.subtract(edge.centerPos)).getType() == Fluids.WATER);
            level.setBlock(pos.subtract(edge.centerPos), (BlockState) edgeState.m_61124_(MechEdgeBlockBase.EDGE, edge), 3);
        }
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if ((Boolean) pState.m_61143_(BlockStateProperties.WATERLOGGED)) {
            pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.m_6718_(pLevel));
        }
        return super.m_7417_(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(BlockStateProperties.WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState pState) {
        return pState.m_61143_(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.m_5888_(pState);
    }
}