package me.steinborn.krypton.mixin.client.fastchunkentityaccess;

import java.util.Collection;
import me.steinborn.krypton.mod.shared.WorldEntityByChunkAccess;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.entity.TransientEntitySectionManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@OnlyIn(Dist.CLIENT)
@Mixin({ ClientLevel.class })
public class ClientWorldMixin implements WorldEntityByChunkAccess {

    @Shadow
    @Final
    private TransientEntitySectionManager<Entity> entityStorage;

    @Override
    public Collection<Entity> getEntitiesInChunk(int chunkX, int chunkZ) {
        return ((WorldEntityByChunkAccess) this.entityStorage.sectionStorage).getEntitiesInChunk(chunkX, chunkZ);
    }
}