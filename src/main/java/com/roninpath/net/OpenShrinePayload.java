package com.roninpath.net;

import com.roninpath.RoninPath;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/** Server -> client: open the shrine screen for the shrine at pos. */
public record OpenShrinePayload(BlockPos pos) implements CustomPacketPayload {
    public static final Type<OpenShrinePayload> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(RoninPath.MODID, "open_shrine"));
    public static final StreamCodec<io.netty.buffer.ByteBuf, OpenShrinePayload> STREAM_CODEC =
            BlockPos.STREAM_CODEC.map(OpenShrinePayload::new, OpenShrinePayload::pos);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
