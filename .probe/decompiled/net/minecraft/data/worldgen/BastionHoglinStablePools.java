package net.minecraft.data.worldgen;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;

public class BastionHoglinStablePools {

    public static void bootstrap(BootstapContext<StructureTemplatePool> bootstapContextStructureTemplatePool0) {
        HolderGetter<StructureProcessorList> $$1 = bootstapContextStructureTemplatePool0.lookup(Registries.PROCESSOR_LIST);
        Holder<StructureProcessorList> $$2 = $$1.getOrThrow(ProcessorLists.STABLE_DEGRADATION);
        Holder<StructureProcessorList> $$3 = $$1.getOrThrow(ProcessorLists.SIDE_WALL_DEGRADATION);
        HolderGetter<StructureTemplatePool> $$4 = bootstapContextStructureTemplatePool0.lookup(Registries.TEMPLATE_POOL);
        Holder<StructureTemplatePool> $$5 = $$4.getOrThrow(Pools.EMPTY);
        Pools.register(bootstapContextStructureTemplatePool0, "bastion/hoglin_stable/starting_pieces", new StructureTemplatePool($$5, ImmutableList.of(Pair.of(StructurePoolElement.single("bastion/hoglin_stable/starting_pieces/starting_stairs_0", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/starting_pieces/starting_stairs_1", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/starting_pieces/starting_stairs_2", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/starting_pieces/starting_stairs_3", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/starting_pieces/starting_stairs_4", $$2), 1)), StructureTemplatePool.Projection.RIGID));
        Pools.register(bootstapContextStructureTemplatePool0, "bastion/hoglin_stable/mirrored_starting_pieces", new StructureTemplatePool($$5, ImmutableList.of(Pair.of(StructurePoolElement.single("bastion/hoglin_stable/starting_pieces/stairs_0_mirrored", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/starting_pieces/stairs_1_mirrored", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/starting_pieces/stairs_2_mirrored", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/starting_pieces/stairs_3_mirrored", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/starting_pieces/stairs_4_mirrored", $$2), 1)), StructureTemplatePool.Projection.RIGID));
        Pools.register(bootstapContextStructureTemplatePool0, "bastion/hoglin_stable/wall_bases", new StructureTemplatePool($$5, ImmutableList.of(Pair.of(StructurePoolElement.single("bastion/hoglin_stable/walls/wall_base", $$2), 1)), StructureTemplatePool.Projection.RIGID));
        Pools.register(bootstapContextStructureTemplatePool0, "bastion/hoglin_stable/walls", new StructureTemplatePool($$5, ImmutableList.of(Pair.of(StructurePoolElement.single("bastion/hoglin_stable/walls/side_wall_0", $$3), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/walls/side_wall_1", $$3), 1)), StructureTemplatePool.Projection.RIGID));
        Pools.register(bootstapContextStructureTemplatePool0, "bastion/hoglin_stable/stairs", new StructureTemplatePool($$5, ImmutableList.of(Pair.of(StructurePoolElement.single("bastion/hoglin_stable/stairs/stairs_1_0", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/stairs/stairs_1_1", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/stairs/stairs_1_2", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/stairs/stairs_1_3", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/stairs/stairs_1_4", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/stairs/stairs_2_0", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/stairs/stairs_2_1", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/stairs/stairs_2_2", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/stairs/stairs_2_3", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/stairs/stairs_2_4", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/stairs/stairs_3_0", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/stairs/stairs_3_1", $$2), 1), new Pair[] { Pair.of(StructurePoolElement.single("bastion/hoglin_stable/stairs/stairs_3_2", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/stairs/stairs_3_3", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/stairs/stairs_3_4", $$2), 1) }), StructureTemplatePool.Projection.RIGID));
        Pools.register(bootstapContextStructureTemplatePool0, "bastion/hoglin_stable/small_stables/inner", new StructureTemplatePool($$5, ImmutableList.of(Pair.of(StructurePoolElement.single("bastion/hoglin_stable/small_stables/inner_0", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/small_stables/inner_1", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/small_stables/inner_2", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/small_stables/inner_3", $$2), 1)), StructureTemplatePool.Projection.RIGID));
        Pools.register(bootstapContextStructureTemplatePool0, "bastion/hoglin_stable/small_stables/outer", new StructureTemplatePool($$5, ImmutableList.of(Pair.of(StructurePoolElement.single("bastion/hoglin_stable/small_stables/outer_0", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/small_stables/outer_1", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/small_stables/outer_2", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/small_stables/outer_3", $$2), 1)), StructureTemplatePool.Projection.RIGID));
        Pools.register(bootstapContextStructureTemplatePool0, "bastion/hoglin_stable/large_stables/inner", new StructureTemplatePool($$5, ImmutableList.of(Pair.of(StructurePoolElement.single("bastion/hoglin_stable/large_stables/inner_0", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/large_stables/inner_1", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/large_stables/inner_2", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/large_stables/inner_3", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/large_stables/inner_4", $$2), 1)), StructureTemplatePool.Projection.RIGID));
        Pools.register(bootstapContextStructureTemplatePool0, "bastion/hoglin_stable/large_stables/outer", new StructureTemplatePool($$5, ImmutableList.of(Pair.of(StructurePoolElement.single("bastion/hoglin_stable/large_stables/outer_0", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/large_stables/outer_1", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/large_stables/outer_2", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/large_stables/outer_3", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/large_stables/outer_4", $$2), 1)), StructureTemplatePool.Projection.RIGID));
        Pools.register(bootstapContextStructureTemplatePool0, "bastion/hoglin_stable/posts", new StructureTemplatePool($$5, ImmutableList.of(Pair.of(StructurePoolElement.single("bastion/hoglin_stable/posts/stair_post", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/posts/end_post", $$2), 1)), StructureTemplatePool.Projection.RIGID));
        Pools.register(bootstapContextStructureTemplatePool0, "bastion/hoglin_stable/ramparts", new StructureTemplatePool($$5, ImmutableList.of(Pair.of(StructurePoolElement.single("bastion/hoglin_stable/ramparts/ramparts_1", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/ramparts/ramparts_2", $$2), 1), Pair.of(StructurePoolElement.single("bastion/hoglin_stable/ramparts/ramparts_3", $$2), 1)), StructureTemplatePool.Projection.RIGID));
        Pools.register(bootstapContextStructureTemplatePool0, "bastion/hoglin_stable/rampart_plates", new StructureTemplatePool($$5, ImmutableList.of(Pair.of(StructurePoolElement.single("bastion/hoglin_stable/rampart_plates/rampart_plate_1", $$2), 1)), StructureTemplatePool.Projection.RIGID));
        Pools.register(bootstapContextStructureTemplatePool0, "bastion/hoglin_stable/connectors", new StructureTemplatePool($$5, ImmutableList.of(Pair.of(StructurePoolElement.single("bastion/hoglin_stable/connectors/end_post_connector", $$2), 1)), StructureTemplatePool.Projection.RIGID));
    }
}