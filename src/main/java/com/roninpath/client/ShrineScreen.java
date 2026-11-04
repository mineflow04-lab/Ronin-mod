package com.roninpath.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.roninpath.RoninPath;
import com.roninpath.block.ShrineBlock;
import com.roninpath.init.ModItems;
import com.roninpath.net.OfferPayload;
import com.roninpath.util.Ranks;
import com.roninpath.util.ShrineQuests;
import java.util.Locale;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;

/** The shrine GUI: rank on the left, shrine quests on the right, "Закрыть" / "Поднести" buttons. */
public class ShrineScreen extends Screen {
    private static final ResourceLocation BG =
            ResourceLocation.fromNamespaceAndPath(RoninPath.MODID, "textures/gui/shrine.png");
    private static final int TEX_W = 600, TEX_H = 306;
    /** Logical panel size; everything below is positioned in these units and scaled to fit the window. */
    private static final int PW = 480, PH = 245, TITLE_H = 36;

    private static final int[] BTN_CLOSE = {133, 217, 97, 24};
    private static final int[] BTN_OFFER = {246, 217, 98, 24};
    private static final int[] CARD_TOPS = {66, 105, 143};
    private static final String[] ROMAN = {"I", "II", "III", "IV", "V", "VI"};

    private final BlockPos pos;
    private float scale = 1f, originX, originY;

    public ShrineScreen(BlockPos pos) {
        super(Component.translatable("screen.roninpath.shrine.title"));
        this.pos = pos;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void tick() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.player.distanceToSqr(Vec3.atCenterOf(pos)) > 100.0) {
            onClose();
        }
    }

    private void layout() {
        scale = Math.min(1f, Math.min((width - 16f) / PW, (height - 16f) / (PH + TITLE_H)));
        originX = (width - PW * scale) / 2f;
        originY = (height - (PH + TITLE_H) * scale) / 2f + TITLE_H * scale;
    }

    private boolean hit(int[] r, double mx, double my) {
        double lx = (mx - originX) / scale, ly = (my - originY) / scale;
        return lx >= r[0] && lx < r[0] + r[2] && ly >= r[1] && ly < r[1] + r[3];
    }

    /** null = can offer, otherwise the reason shown in a tooltip. */
    private Component offerBlocker() {
        Player player = Minecraft.getInstance().player;
        if (player == null) {
            return Component.empty();
        }
        var stack = ShrineBlock.findOffering(player);
        if (stack.isEmpty()) {
            return Component.translatable("screen.roninpath.shrine.need_offering");
        }
        if (player.getCooldowns().isOnCooldown(ModItems.KITSUNE_OFFERING.get())) {
            return Component.translatable("screen.roninpath.shrine.cooldown");
        }
        return null;
    }

    @Override
    public boolean mouseClicked(double mx, double my, int button) {
        layout();
        if (button == 0) {
            if (hit(BTN_CLOSE, mx, my)) {
                click();
                onClose();
                return true;
            }
            if (hit(BTN_OFFER, mx, my) && offerBlocker() == null) {
                click();
                PacketDistributor.sendToServer(new OfferPayload(pos));
                return true;
            }
        }
        return super.mouseClicked(mx, my, button);
    }

    private void click() {
        Minecraft.getInstance().getSoundManager()
                .play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0f));
    }

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
        super.render(g, mouseX, mouseY, partialTick);
        Player player = Minecraft.getInstance().player;
        if (player == null) {
            return;
        }
        layout();
        PoseStack ps = g.pose();
        ps.pushPose();
        ps.translate(originX, originY, 0);
        ps.scale(scale, scale, 1f);

        g.blit(BG, 0, 0, PW, PH, 0f, 0f, TEX_W, TEX_H, TEX_W, TEX_H);

        // title above the frame, with a dark outline
        Component titleText = up("screen.roninpath.shrine.title");
        for (int[] d : new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {1, 1}, {-1, 1}, {1, -1}}) {
            text(g, titleText, PW / 2f + d[0] * 1.5f, -32 + d[1] * 1.5f, 3f, 0xFF140A0A, false, true);
        }
        text(g, titleText, PW / 2f, -32, 3f, 0xFFFFFFFF, false, true);

        text(g, up("screen.roninpath.shrine.rank"), 135, 17.5f, 1.5f, 0xFFFFFFFF, true, true);
        text(g, up("screen.roninpath.shrine.quests"), 340, 17.5f, 1.5f, 0xFFFFFFFF, true, true);

        // rank block
        int points = Ranks.getPoints(player);
        int rank = Ranks.rankOf(points);
        Component rankName = Component.literal(Ranks.rankName(rank).getString().toUpperCase(Locale.ROOT));
        text(g, Component.translatable("screen.roninpath.shrine.rank_line", rankName), 137, 132, 1.25f, 0xFFFFF2B0, true, true);
        text(g, Component.translatable("screen.roninpath.shrine.rank_num", ROMAN[Math.min(rank, ROMAN.length - 1)]),
                136, 153, 1.25f, 0xFF9FE8FF, true, true);

        int bx0 = 68, bx1 = 204, by0 = 173, by1 = 183;
        float fraction;
        Component barText;
        if (rank >= Ranks.THRESHOLDS.length - 1) {
            fraction = 1f;
            barText = Component.translatable("screen.roninpath.shrine.max", points);
        } else {
            int from = Ranks.THRESHOLDS[rank], to = Ranks.THRESHOLDS[rank + 1];
            fraction = Math.max(0f, Math.min(1f, (points - from) / (float) (to - from)));
            barText = Component.translatable("screen.roninpath.shrine.progress", points, to);
        }
        int fillX = bx0 + Math.round((bx1 - bx0) * fraction);
        if (fillX > bx0) {
            g.fillGradient(bx0, by0, fillX, by1, 0xFFC9F4FF, 0xFF3E86E0);
        }
        text(g, barText, (bx0 + bx1) / 2f, by0 + 1.6f, 0.85f, 0xFFFFFFFF, true, true);

        // quest cards
        for (int i = 0; i < ShrineQuests.QUESTS.size(); i++) {
            ShrineQuests.Quest quest = ShrineQuests.QUESTS.get(i);
            int top = CARD_TOPS[i];
            boolean done = ShrineQuests.isDone(player, i);
            int progress = done ? quest.goal() : ShrineQuests.progress(player, i);
            text(g, Component.translatable("quest.roninpath." + quest.id() + ".title"), 285, top + 5.5f, 1f, 0xFF4A2410, false, false);
            Component sub = done
                    ? Component.translatable("screen.roninpath.shrine.done")
                    : Component.translatable("quest.roninpath." + quest.id() + ".desc", progress, quest.goal());
            text(g, sub, 285, top + 18.5f, 0.85f, done ? 0xFF2E7D32 : 0xFF6B4A2B, false, false);
            text(g, Component.literal("+" + quest.reward()), 406, top + 9.5f, 1.25f, 0xFF7A4A00, false, true);
            text(g, Component.translatable("screen.roninpath.shrine.points"), 406, top + 21.5f, 0.7f, 0xFF7A4A00, false, true);
            if (done) {
                check(g, 262, top + 22);
            }
        }

        // buttons
        Component blocker = offerBlocker();
        drawButton(g, BTN_CLOSE, Component.translatable("screen.roninpath.shrine.close"), true, mouseX, mouseY);
        drawButton(g, BTN_OFFER, Component.translatable("screen.roninpath.shrine.offer"), blocker == null, mouseX, mouseY);
        ps.popPose();

        if (blocker != null && hit(BTN_OFFER, mouseX, mouseY)) {
            g.renderTooltip(font, blocker, mouseX, mouseY);
        }
    }

    private void drawButton(GuiGraphics g, int[] r, Component label, boolean enabled, int mx, int my) {
        if (!enabled) {
            g.fill(r[0], r[1], r[0] + r[2], r[1] + r[3], 0x90000000);
        } else if (hit(r, mx, my)) {
            g.fill(r[0], r[1], r[0] + r[2], r[1] + r[3], 0x30FFFFFF);
        }
        text(g, label, r[0] + r[2] / 2f, r[1] + 7, 1.25f, enabled ? 0xFFFFFFFF : 0xFF9A9A9A, true, true);
    }

    private void check(GuiGraphics g, int x, int y) {
        int[][] pts = {{0, 3}, {1, 4}, {2, 5}, {3, 4}, {4, 3}, {5, 2}, {6, 1}, {7, 0}};
        for (int[] d : new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}) {
            for (int[] p : pts) {
                g.fill(x + p[0] + d[0], y + p[1] + d[1], x + p[0] + d[0] + 1, y + p[1] + d[1] + 1, 0xFF20401F);
            }
        }
        for (int[] p : pts) {
            g.fill(x + p[0], y + p[1], x + p[0] + 1, y + p[1] + 1, 0xFF54E05B);
        }
    }

    private static Component up(String key) {
        return Component.literal(Component.translatable(key).getString().toUpperCase(Locale.ROOT));
    }

    private void text(GuiGraphics g, Component c, float x, float y, float s, int color, boolean shadow, boolean center) {
        PoseStack ps = g.pose();
        ps.pushPose();
        ps.translate(x, y, 0);
        ps.scale(s, s, 1f);
        g.drawString(font, c, center ? -font.width(c) / 2 : 0, 0, color, shadow);
        ps.popPose();
    }
}
