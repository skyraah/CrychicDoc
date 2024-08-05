package vazkii.patchouli.common.base;

import java.util.function.BiConsumer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class PatchouliSounds {

    public static final SoundEvent BOOK_OPEN = SoundEvent.createVariableRangeEvent(new ResourceLocation("patchouli", "book_open"));

    public static final SoundEvent BOOK_FLIP = SoundEvent.createVariableRangeEvent(new ResourceLocation("patchouli", "book_flip"));

    public static void submitRegistrations(BiConsumer<ResourceLocation, SoundEvent> e) {
        e.accept(BOOK_OPEN.getLocation(), BOOK_OPEN);
        e.accept(BOOK_FLIP.getLocation(), BOOK_FLIP);
    }

    public static SoundEvent getSound(ResourceLocation key, SoundEvent fallback) {
        return (SoundEvent) BuiltInRegistries.SOUND_EVENT.getOptional(key).orElse(fallback);
    }
}