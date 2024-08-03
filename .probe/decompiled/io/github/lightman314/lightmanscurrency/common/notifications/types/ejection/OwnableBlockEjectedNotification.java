package io.github.lightman314.lightmanscurrency.common.notifications.types.ejection;

import io.github.lightman314.lightmanscurrency.LCConfig;
import io.github.lightman314.lightmanscurrency.LCText;
import io.github.lightman314.lightmanscurrency.api.misc.EasyText;
import io.github.lightman314.lightmanscurrency.api.notifications.Notification;
import io.github.lightman314.lightmanscurrency.api.notifications.NotificationCategory;
import io.github.lightman314.lightmanscurrency.api.notifications.NotificationType;
import io.github.lightman314.lightmanscurrency.common.notifications.categories.NullCategory;
import io.github.lightman314.lightmanscurrency.common.text.TextEntry;
import javax.annotation.Nonnull;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.util.NonNullSupplier;

public class OwnableBlockEjectedNotification extends Notification {

    public static final NotificationType<OwnableBlockEjectedNotification> TYPE = new NotificationType<>(new ResourceLocation("lightmanscurrency", "block_ejected"), OwnableBlockEjectedNotification::new);

    private Component name = EasyText.empty();

    private boolean ejected = false;

    private boolean anarchy = false;

    private OwnableBlockEjectedNotification() {
    }

    public OwnableBlockEjectedNotification(@Nonnull Component name) {
        this.name = name.copy();
        this.ejected = LCConfig.SERVER.safelyEjectMachineContents.get();
        this.anarchy = LCConfig.SERVER.anarchyMode.get();
    }

    @Nonnull
    public static NonNullSupplier<Notification> create(@Nonnull Component name) {
        return () -> new OwnableBlockEjectedNotification(name);
    }

    @Nonnull
    @Override
    protected NotificationType<?> getType() {
        return TYPE;
    }

    @Nonnull
    @Override
    public NotificationCategory getCategory() {
        return NullCategory.INSTANCE;
    }

    @Nonnull
    @Override
    public MutableComponent getMessage() {
        return this.getText().get(this.name);
    }

    private TextEntry getText() {
        if (this.anarchy) {
            return LCText.NOTIFICATION_EJECTION_ANARCHY;
        } else {
            return this.ejected ? LCText.NOTIFICATION_EJECTION_EJECTED : LCText.NOTIFICATION_EJECTION_DROPPED;
        }
    }

    @Override
    protected void saveAdditional(@Nonnull CompoundTag compound) {
        compound.putString("Name", Component.Serializer.toJson(this.name));
        if (this.ejected) {
            compound.putBoolean("Ejected", this.ejected);
        }
        if (this.anarchy) {
            compound.putBoolean("Anarchy", this.anarchy);
        }
    }

    @Override
    protected void loadAdditional(@Nonnull CompoundTag compound) {
        this.name = Component.Serializer.fromJson(compound.getString("Name"));
        if (compound.contains("Ejected")) {
            this.ejected = compound.getBoolean("Ejected");
        }
        if (compound.contains("Anarchy")) {
            this.anarchy = compound.getBoolean("Anarchy");
        }
    }

    @Override
    protected boolean canMerge(@Nonnull Notification other) {
        return false;
    }
}