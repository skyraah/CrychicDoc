package dev.latvian.mods.kubejs.block;

import dev.latvian.mods.kubejs.level.BlockContainerJS;
import dev.latvian.mods.kubejs.player.PlayerEventJS;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

@Info("Invoked when a player left clicks on a block.\n")
public class BlockLeftClickedEventJS extends PlayerEventJS {

    private final Player player;

    private final InteractionHand hand;

    private final BlockPos pos;

    private final Direction direction;

    public BlockLeftClickedEventJS(Player player, InteractionHand hand, BlockPos pos, Direction direction) {
        this.player = player;
        this.hand = hand;
        this.pos = pos;
        this.direction = direction;
    }

    @Info("The player that left clicked the block.")
    @Override
    public Player getEntity() {
        return this.player;
    }

    @Info("The block that was left clicked.")
    public BlockContainerJS getBlock() {
        return new BlockContainerJS(this.player.m_9236_(), this.pos);
    }

    @Info("The item that was used to left click the block.")
    public ItemStack getItem() {
        return this.player.m_21120_(this.hand);
    }

    @Info("The face of the block that was left clicked.")
    @Nullable
    public Direction getFacing() {
        return this.direction;
    }
}