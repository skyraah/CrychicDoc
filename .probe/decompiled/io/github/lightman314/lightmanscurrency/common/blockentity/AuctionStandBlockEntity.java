package io.github.lightman314.lightmanscurrency.common.blockentity;

import com.google.common.collect.ImmutableList;
import io.github.lightman314.lightmanscurrency.api.misc.blockentity.EasyBlockEntity;
import io.github.lightman314.lightmanscurrency.common.core.ModBlockEntities;
import io.github.lightman314.lightmanscurrency.common.core.ModItems;
import io.github.lightman314.lightmanscurrency.common.traders.TraderSaveData;
import io.github.lightman314.lightmanscurrency.common.traders.auction.AuctionHouseTrader;
import io.github.lightman314.lightmanscurrency.common.traders.auction.tradedata.AuctionTradeData;
import io.github.lightman314.lightmanscurrency.network.message.auction.SPacketSyncAuctionStandDisplay;
import io.github.lightman314.lightmanscurrency.util.InventoryUtil;
import java.util.List;
import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class AuctionStandBlockEntity extends EasyBlockEntity {

    public boolean dropItem = true;

    private static ImmutableList<ItemStack> displayItems = ImmutableList.of(ItemStack.EMPTY);

    private static boolean randomizeNextOpportunity = false;

    public AuctionStandBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.AUCTION_STAND.get(), pos, state);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void serverStart(ServerStartedEvent event) {
        if (AuctionHouseTrader.isEnabled()) {
            RandomizeDisplayItems();
        }
    }

    @SubscribeEvent
    public static void playerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (AuctionHouseTrader.isEnabled() && event.getEntity() instanceof ServerPlayer sp) {
            new SPacketSyncAuctionStandDisplay(displayItems).sendTo(sp);
        }
    }

    @SubscribeEvent
    public static void serverTick(TickEvent.ServerTickEvent event) {
        if (AuctionHouseTrader.isEnabled() && event.getServer().getTickCount() % 1200 == 0) {
            if (event.haveTime()) {
                RandomizeDisplayItems();
            } else {
                randomizeNextOpportunity = true;
            }
        } else if (event.haveTime() && randomizeNextOpportunity) {
            randomizeNextOpportunity = false;
            RandomizeDisplayItems();
        }
    }

    private static void RandomizeDisplayItems() {
        if (TraderSaveData.GetAuctionHouse(false) instanceof AuctionHouseTrader ah && ah.getTradeCount() > 0) {
            AuctionTradeData trade = ah.getTrade(new Random().nextInt(ah.getTradeCount()));
            if (trade != null) {
                setDisplayItems(trade.getAuctionItems());
                return;
            }
        }
        setDefaultDisplayItem();
    }

    public static ImmutableList<ItemStack> getDisplayItems() {
        return displayItems;
    }

    private static void setDefaultDisplayItem() {
        setDisplayItems(ImmutableList.of(new ItemStack(ModItems.TRADING_CORE.get())));
    }

    private static void setDisplayItems(List<ItemStack> items) {
        displayItems = ImmutableList.copyOf(InventoryUtil.copyList(items));
        new SPacketSyncAuctionStandDisplay(displayItems).sendToAll();
    }

    public static void syncItemsFromServer(List<ItemStack> items) {
        displayItems = ImmutableList.copyOf(items);
    }
}