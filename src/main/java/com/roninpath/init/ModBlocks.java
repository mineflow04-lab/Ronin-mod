package com.roninpath.init;

import com.roninpath.RoninPath;
import com.roninpath.block.ShrineBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(RoninPath.MODID);

    public static final DeferredBlock<ShrineBlock> KITSUNE_SHRINE = BLOCKS.register(
            "kitsune_shrine",
            () -> new ShrineBlock(BlockBehaviour.Properties.of()
                    .strength(3.0f, 6.0f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
                    .noOcclusion()
                    .lightLevel(state -> 7)));

    private ModBlocks() {}
}
