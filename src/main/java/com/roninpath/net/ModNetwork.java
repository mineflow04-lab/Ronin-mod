package com.roninpath.net;

import com.roninpath.block.ShrineBlock;
import com.roninpath.client.ShrineClientHooks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public final class ModNetwork {
    private ModNetwork() {}

    public static void register(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");

        registrar.playToClient(OpenShrinePayload.TYPE, OpenShrinePayload.STREAM_CODEC,
                (payload, context) -> context.enqueueWork(() -> ShrineClientHooks.open(payload.pos())));

        registrar.playToServer(OfferPayload.TYPE, OfferPayload.STREAM_CODEC,
                (payload, context) -> context.enqueueWork(() -> {
                    if (!(context.player() instanceof ServerPlayer player)) {
                        return;
                    }
                    ServerLevel level = (ServerLevel) player.level();
                    BlockPos pos = payload.pos();
                    if (level.isLoaded(pos)
                            && player.distanceToSqr(Vec3.atCenterOf(pos)) <= 64.0
                            && level.getBlockState(pos).getBlock() instanceof ShrineBlock) {
                        ShrineBlock.offer(player, level, pos);
                    }
                }));
    }
}
