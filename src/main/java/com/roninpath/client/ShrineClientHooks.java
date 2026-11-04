package com.roninpath.client;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;

/** Client-only entry points, called from the network handlers by name so the server never loads Screen classes. */
public final class ShrineClientHooks {
    private ShrineClientHooks() {}

    public static void open(BlockPos pos) {
        Minecraft.getInstance().setScreen(new ShrineScreen(pos));
    }
}
