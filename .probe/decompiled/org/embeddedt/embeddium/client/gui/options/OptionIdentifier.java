package org.embeddedt.embeddium.client.gui.options;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.util.Objects;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public final class OptionIdentifier<T> {

    private final String modId;

    private final String path;

    private final Class<T> clz;

    private static final ObjectOpenHashSet<OptionIdentifier<?>> IDENTIFIERS = new ObjectOpenHashSet();

    public static final OptionIdentifier<Void> EMPTY = create("", "", Void.class);

    private OptionIdentifier(String modId, String path, Class<T> clz) {
        this.modId = modId;
        this.path = path;
        this.clz = clz;
    }

    public String getModId() {
        return this.modId;
    }

    public String getPath() {
        return this.path;
    }

    public Class<T> getType() {
        return this.clz;
    }

    public static OptionIdentifier<Void> create(ResourceLocation location) {
        return create(location, void.class);
    }

    public static <T> OptionIdentifier<T> create(ResourceLocation location, Class<T> clz) {
        return create(location.getNamespace(), location.getPath(), clz);
    }

    public static OptionIdentifier<Void> create(String modId, String path) {
        return create(modId, path, void.class);
    }

    public static synchronized <T> OptionIdentifier<T> create(String modId, String path, Class<T> clz) {
        OptionIdentifier<T> ourIdentifier = new OptionIdentifier<>(modId, path, clz);
        OptionIdentifier<T> oldIdentifier = (OptionIdentifier<T>) IDENTIFIERS.addOrGet(ourIdentifier);
        if (oldIdentifier != null && oldIdentifier.clz != ourIdentifier.clz) {
            throw new IllegalArgumentException(String.format("OptionIdentifier '%s' created with differing class type %s from existing instance %s", ourIdentifier, ourIdentifier.clz, oldIdentifier.clz));
        } else {
            return oldIdentifier;
        }
    }

    public static boolean isPresent(@Nullable OptionIdentifier<?> id) {
        return id != null && id != EMPTY;
    }

    public boolean matches(OptionIdentifier<?> other) {
        return this == other;
    }

    public boolean matches(ResourceLocation other) {
        return this.modId.equals(other.getNamespace()) && this.path.equals(other.getPath());
    }

    public String toString() {
        return this.modId + ":" + this.path;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            OptionIdentifier<?> that = (OptionIdentifier<?>) o;
            return Objects.equals(this.modId, that.modId) && Objects.equals(this.path, that.path);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[] { this.modId, this.path });
    }
}