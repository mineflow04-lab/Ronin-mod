package com.roninpath.util;

import com.roninpath.init.ModAttachments;
import com.roninpath.init.ModItems;
import java.util.List;
import java.util.function.ToIntFunction;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/** The three quests shown on the shrine screen. Rewards are rank points, granted once. */
public final class ShrineQuests {
    public record Quest(String id, int goal, int reward, ToIntFunction<Player> progress) {}

    public static final List<Quest> QUESTS = List.of(
            new Quest("food", 5, 5, p -> count(p, ModItems.ONIGIRI.get())),
            new Quest("oni", 3, 15, p -> p.getData(ModAttachments.ONI_KILLS)),
            new Quest("blessing", 3, 10, p -> p.getData(ModAttachments.OFFERINGS)));

    private ShrineQuests() {}

    public static int count(Player player, Item item) {
        Inventory inv = player.getInventory();
        int total = 0;
        for (int i = 0; i < inv.getContainerSize(); i++) {
            ItemStack stack = inv.getItem(i);
            if (stack.is(item)) {
                total += stack.getCount();
            }
        }
        return total;
    }

    public static boolean isDone(Player player, int index) {
        return ((player.getData(ModAttachments.QUESTS_DONE) >> index) & 1) == 1;
    }

    public static int progress(Player player, int index) {
        Quest quest = QUESTS.get(index);
        return Math.min(quest.progress().applyAsInt(player), quest.goal());
    }

    /** Completes every quest whose goal is reached and pays out its reward. Server side only. */
    public static void check(ServerPlayer player) {
        for (int i = 0; i < QUESTS.size(); i++) {
            Quest quest = QUESTS.get(i);
            if (isDone(player, i) || quest.progress().applyAsInt(player) < quest.goal()) {
                continue;
            }
            player.setData(ModAttachments.QUESTS_DONE, player.getData(ModAttachments.QUESTS_DONE) | (1 << i));
            player.displayClientMessage(
                    Component.translatable("message.roninpath.quest_done",
                            Component.translatable("quest.roninpath." + quest.id() + ".title"), quest.reward())
                            .withStyle(ChatFormatting.GREEN),
                    false);
            Ranks.setPoints(player, Ranks.getPoints(player) + quest.reward());
        }
    }
}
