package noobanidus.mods.lootr.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.AbstractMinecartContainer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraftforge.network.NetworkHooks;
import noobanidus.mods.lootr.api.LootrAPI;
import noobanidus.mods.lootr.api.entity.ILootCart;
import noobanidus.mods.lootr.config.ConfigManager;
import noobanidus.mods.lootr.event.HandleBreak;
import noobanidus.mods.lootr.init.ModBlocks;
import noobanidus.mods.lootr.init.ModEntities;
import noobanidus.mods.lootr.network.OpenCart;
import noobanidus.mods.lootr.network.PacketHandler;
import noobanidus.mods.lootr.util.ChestUtil;
import org.jetbrains.annotations.Nullable;

public class LootrChestMinecartEntity extends AbstractMinecartContainer implements ILootCart {

    private static BlockState cartNormal = null;

    private final Set<UUID> openers = new HashSet();

    private boolean opened = false;

    public LootrChestMinecartEntity(EntityType<LootrChestMinecartEntity> type, Level world) {
        super(type, world);
    }

    public LootrChestMinecartEntity(Level worldIn, double x, double y, double z) {
        super(ModEntities.LOOTR_MINECART_ENTITY.get(), x, y, z, worldIn);
    }

    @Override
    public void unpackChestVehicleLootTable(@Nullable Player player0) {
    }

    @Override
    public Item getDropItem() {
        return Items.CHEST_MINECART;
    }

    @Override
    public Set<UUID> getOpeners() {
        return this.openers;
    }

    public void addOpener(Player player) {
        this.openers.add(player.m_20148_());
        this.m_6596_();
    }

    public boolean isOpened() {
        return this.opened;
    }

    public void setOpened() {
        this.opened = true;
    }

    public void setClosed() {
        this.opened = false;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        if (this.m_20147_() && source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            return true;
        } else if (!(source.getEntity() instanceof Player player)) {
            return true;
        } else if ((!LootrAPI.isFakePlayer(player) || !ConfigManager.ENABLE_FAKE_PLAYER_BREAK.get()) && !ConfigManager.ENABLE_BREAK.get()) {
            if (ConfigManager.DISABLE_BREAK.get()) {
                if (!player.getAbilities().instabuild) {
                    player.displayClientMessage(Component.translatable("lootr.message.cannot_break").setStyle(HandleBreak.getChatStyle()), false);
                    return true;
                } else if (!player.m_6144_()) {
                    player.displayClientMessage(Component.translatable("lootr.message.cannot_break_sneak").setStyle(HandleBreak.getChatStyle()), false);
                    return true;
                } else {
                    return false;
                }
            } else {
                if (!source.getEntity().isShiftKeyDown()) {
                    ((Player) source.getEntity()).displayClientMessage(Component.translatable("lootr.message.cart_should_sneak").setStyle(HandleBreak.getChatStyle()), false);
                    ((Player) source.getEntity()).displayClientMessage(Component.translatable("lootr.message.should_sneak2", Component.translatable("lootr.message.cart_should_sneak3").setStyle(Style.EMPTY.withBold(true))).setStyle(HandleBreak.getChatStyle()), false);
                }
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public void destroy(DamageSource source) {
        this.remove(Entity.RemovalReason.KILLED);
        if (this.m_9236_().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
            ItemStack itemstack = new ItemStack(Items.CHEST_MINECART);
            if (this.m_8077_()) {
                itemstack.setHoverName(this.m_7770_());
            }
            this.m_19983_(itemstack);
        }
    }

    @Override
    public int getContainerSize() {
        return 27;
    }

    @Override
    public AbstractMinecart.Type getMinecartType() {
        return AbstractMinecart.Type.CHEST;
    }

    @Override
    public BlockState getDefaultDisplayBlockState() {
        if (cartNormal == null) {
            cartNormal = (BlockState) ModBlocks.CHEST.get().m_49966_().m_61124_(ChestBlock.FACING, Direction.NORTH);
        }
        return cartNormal;
    }

    @Override
    public int getDefaultDisplayOffset() {
        return 8;
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInventoryIn) {
        return ChestMenu.threeRows(id, playerInventoryIn, this);
    }

    @Override
    public void remove(Entity.RemovalReason reason) {
        this.m_142467_(reason);
        if (reason == Entity.RemovalReason.KILLED) {
            this.m_146850_(GameEvent.ENTITY_DIE);
        }
        this.invalidateCaps();
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {
        if (this.f_38204_ != null) {
            compound.putString("LootTable", this.f_38204_.toString());
        }
        compound.putLong("LootTableSeed", this.f_38205_);
        ListTag list = new ListTag();
        for (UUID opener : this.openers) {
            list.add(NbtUtils.createUUID(opener));
        }
        compound.put("LootrOpeners", list);
        super.addAdditionalSaveData(compound);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {
        this.f_38204_ = new ResourceLocation(compound.getString("LootTable"));
        this.f_38205_ = compound.getLong("LootTableSeed");
        if (compound.contains("LootrOpeners", 9)) {
            ListTag openers = compound.getList("LootrOpeners", 11);
            this.openers.clear();
            for (Tag item : openers) {
                this.openers.add(NbtUtils.loadUUID(item));
            }
        }
        super.readAdditionalSaveData(compound);
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        InteractionResult ret = InteractionResult.PASS;
        if (ret.consumesAction()) {
            return ret;
        } else if (player.m_6144_()) {
            ChestUtil.handleLootCartSneak(player.m_9236_(), this, player);
            return !player.m_9236_().isClientSide ? InteractionResult.CONSUME : InteractionResult.SUCCESS;
        } else {
            ChestUtil.handleLootCart(player.m_9236_(), this, player);
            if (!player.m_9236_().isClientSide) {
                PiglinAi.angerNearbyPiglins(player, true);
                return InteractionResult.CONSUME;
            } else {
                return InteractionResult.SUCCESS;
            }
        }
    }

    public void addLoot(@javax.annotation.Nullable Player player, Container inventory, @javax.annotation.Nullable ResourceLocation overrideTable, long seed) {
        if (this.f_38204_ != null && this.m_9236_().getServer() != null) {
            LootTable loottable = this.m_9236_().getServer().getLootData().m_278676_(overrideTable != null ? overrideTable : this.f_38204_);
            if (loottable == LootTable.EMPTY) {
                LootrAPI.LOG.error("Unable to fill loot in " + this.m_9236_().dimension().location() + " at " + this.m_20182_() + " as the loot table '" + (overrideTable != null ? overrideTable : this.f_38204_) + "' couldn't be resolved! Please search the loot table in `latest.log` to see if there are errors in loading.");
                if (ConfigManager.REPORT_UNRESOLVED_TABLES.get() && player != null) {
                    player.displayClientMessage(ChestUtil.getInvalidTable(overrideTable != null ? overrideTable : this.f_38204_), false);
                }
            }
            if (player instanceof ServerPlayer) {
                CriteriaTriggers.GENERATE_LOOT.trigger((ServerPlayer) player, overrideTable != null ? overrideTable : this.f_38204_);
            }
            LootParams.Builder builder = new LootParams.Builder((ServerLevel) this.m_9236_()).withParameter(LootContextParams.ORIGIN, this.m_20182_());
            builder.withParameter(LootContextParams.KILLER_ENTITY, this);
            if (player != null) {
                builder.withLuck(player.getLuck()).withParameter(LootContextParams.THIS_ENTITY, player);
            }
            loottable.fill(inventory, builder.create(LootContextParamSets.CHEST), LootrAPI.getLootSeed(seed == Long.MIN_VALUE ? this.f_38205_ : seed));
        }
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void startOpen(Player player) {
        if (!player.isSpectator()) {
            OpenCart cart = new OpenCart(this.m_19879_());
            PacketHandler.sendToInternal(cart, (ServerPlayer) player);
        }
    }

    @Override
    public void stopOpen(Player player) {
        if (!player.isSpectator()) {
            this.addOpener(player);
        }
    }

    @Override
    public void startSeenByPlayer(ServerPlayer pPlayer) {
        super.m_6457_(pPlayer);
        if (this.getOpeners().contains(pPlayer.m_20148_())) {
            OpenCart cart = new OpenCart(this.m_19879_());
            PacketHandler.sendToInternal(cart, pPlayer);
        }
    }
}