package com.roninpath.net;

import com.roninpath.RoninPath;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/** Client -> server: the "Поднести" button was pressed at the shrine at pos. */
public record OfferPayload(BlockPos pos) implements CustomPacketPayload {
    public static final Type<OfferPayload> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(RoninPath.MODID, "offer"));
    public static final StreamCodec<io.netty.buffer.ByteBuf, OfferPayload> STREAM_CODEC =
            BlockPos.STREAM_CODEC.map(OfferPayload::new, OfferPayload::pos);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
