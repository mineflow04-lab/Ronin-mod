package com.roninpath.util;

import com.roninpath.RoninPath;
import com.roninpath.init.ModAttachments;
import java.util.Map;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

/** Ronin rank logic. Points live on the player (survive death). */
public final class Ranks {
    /** Points needed for rank 0..5. */
    public static final int[] THRESHOLDS = {0, 25, 75, 150, 300, 600};

    /** Extra points for killing known bosses (by entity id). Unknown ids are simply ignored. */
    public static final Map<String, Integer> BOSS_POINTS = Map.ofEntries(
            Map.entry("minecraft:warden", 40),
            Map.entry("minecraft:elder_guardian", 25),
            Map.entry("cataclysm:ender_golem", 40),
            Map.entry("cataclysm:ender_guardian", 40),
            Map.entry("cataclysm:netherite_monstrosity", 50),
            Map.entry("cataclysm:ignis", 50),
            Map.entry("cataclysm:the_harbinger", 60),
            Map.entry("cataclysm:the_leviathan", 70),
            Map.entry("cataclysm:ancient_remnant", 60),
            Map.entry("cataclysm:scylla", 70),
            Map.entry("cataclysm:maledictus", 50));

    private static final ResourceLocation HEALTH_ID =
            ResourceLocation.fromNamespaceAndPath(RoninPath.MODID, "rank_health");

    private Ranks() {}

    public static int getPoints(Player player) {
        return player.getData(ModAttachments.RONIN_POINTS);
    }

    public static int rankOf(int points) {
        int rank = 0;
        for (int i = 0; i < THRESHOLDS.length; i++) {
            if (points >= THRESHOLDS[i]) {
                rank = i;
            }
        }
        return rank;
    }

    public static Component rankName(int rank) {
        return Component.translatable("rank.roninpath." + rank);
    }

    public static void setPoints(ServerPlayer player, int value) {
        value = Math.max(0, value);
        int oldRank = rankOf(getPoints(player));
        player.setData(ModAttachments.RONIN_POINTS, value);
        int newRank = rankOf(value);

        grantRankAdvancements(player, newRank);
        applyPerks(player);

        if (newRank > oldRank) {
            player.displayClientMessage(
                    Component.translatable("message.roninpath.rank_up", rankName(newRank))
                            .withStyle(ChatFormatting.GOLD),
                    false);
        }
    }

    /** Perk: +1 heart (2 HP) per rank. Re-applied on login / respawn. */
    public static void applyPerks(ServerPlayer player) {
        AttributeInstance health = player.getAttribute(Attributes.MAX_HEALTH);
        if (health == null) {
            return;
        }
        health.removeModifier(HEALTH_ID);
        int rank = rankOf(getPoints(player));
        if (rank > 0) {
            health.addPermanentModifier(new AttributeModifier(
                    HEALTH_ID, rank * 2.0, AttributeModifier.Operation.ADD_VALUE));
        }
    }

    /** Silent "impossible" advancements rank_1..rank_5 so FTB Quests can use an Advancement task. */
    private static void grantRankAdvancements(ServerPlayer player, int rank) {
        MinecraftServer server = player.getServer();
        if (server == null) {
            return;
        }
        for (int i = 1; i <= rank; i++) {
            AdvancementHolder holder = server.getAdvancements()
                    .get(ResourceLocation.fromNamespaceAndPath(RoninPath.MODID, "rank_" + i));
            if (holder != null) {
                player.getAdvancements().award(holder, "reached");
            }
        }
    }
}
