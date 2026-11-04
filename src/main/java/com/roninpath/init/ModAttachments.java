package com.roninpath.init;

import com.mojang.serialization.Codec;
import com.roninpath.RoninPath;
import java.util.function.Supplier;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public final class ModAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENTS =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, RoninPath.MODID);

    /** Rank points. Synced to the client so the shrine screen can show them. */
    public static final Supplier<AttachmentType<Integer>> RONIN_POINTS = intAttachment("ronin_points");
    /** Offerings made at the shrine. */
    public static final Supplier<AttachmentType<Integer>> OFFERINGS = intAttachment("offerings");
    /** Oni killed by the player. */
    public static final Supplier<AttachmentType<Integer>> ONI_KILLS = intAttachment("oni_kills");
    /** Bit mask of completed shrine quests (bit i = quest i). */
    public static final Supplier<AttachmentType<Integer>> QUESTS_DONE = intAttachment("quests_done");

    private static Supplier<AttachmentType<Integer>> intAttachment(String name) {
        return ATTACHMENTS.register(name, () -> AttachmentType.builder(() -> 0)
                .serialize(Codec.INT)
                .sync(ByteBufCodecs.VAR_INT)
                .copyOnDeath()
                .build());
    }

    private ModAttachments() {}
}
