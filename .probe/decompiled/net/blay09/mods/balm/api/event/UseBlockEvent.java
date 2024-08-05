package net.blay09.mods.balm.api.event;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

public class UseBlockEvent extends BalmEvent {

    private final Player player;

    private final Level level;

    private final InteractionHand hand;

    private final BlockHitResult hitResult;

    private InteractionResult result = InteractionResult.PASS;

    public UseBlockEvent(Player player, Level level, InteractionHand hand, BlockHitResult hitResult) {
        this.player = player;
        this.level = level;
        this.hand = hand;
        this.hitResult = hitResult;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Level getLevel() {
        return this.level;
    }

    public InteractionHand getHand() {
        return this.hand;
    }

    public BlockHitResult getHitResult() {
        return this.hitResult;
    }

    public InteractionResult getInteractionResult() {
        return this.result;
    }

    public void setResult(InteractionResult result) {
        this.result = result;
    }
}