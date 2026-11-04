package com.roninpath.init;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.roninpath.entity.OniEntity;
import com.roninpath.util.Ranks;
import com.roninpath.util.ShrineQuests;
import java.util.Collection;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public final class ModEvents {
    private ModEvents() {}

    public static void onAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.ONI.get(), OniEntity.createAttributes().build());
    }

    public static void onSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(ModEntities.ONI.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);
    }

    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            Ranks.applyPerks(player);
        }
    }

    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            Ranks.applyPerks(player);
        }
    }

    public static void onLivingDeath(LivingDeathEvent event) {
        if (!(event.getSource().getEntity() instanceof ServerPlayer player)) {
            return;
        }
        Entity victim = event.getEntity();
        int points;
        if (victim instanceof OniEntity) {
            points = 20;
        } else if (victim instanceof WitherBoss) {
            points = 40;
        } else if (victim instanceof EnderDragon) {
            points = 60;
        } else {
            String id = BuiltInRegistries.ENTITY_TYPE.getKey(victim.getType()).toString();
            points = Ranks.BOSS_POINTS.getOrDefault(id, 0);
        }
        if (victim instanceof OniEntity) {
            player.setData(ModAttachments.ONI_KILLS, player.getData(ModAttachments.ONI_KILLS) + 1);
        }
        if (points > 0) {
            Ranks.setPoints(player, Ranks.getPoints(player) + points);
        }
        if (victim instanceof OniEntity) {
            ShrineQuests.check(player);
        }
    }

    public static void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("ronin")
                .then(Commands.literal("rank").executes(ctx -> {
                    ServerPlayer player = ctx.getSource().getPlayerOrException();
                    int points = Ranks.getPoints(player);
                    int rank = Ranks.rankOf(points);
                    ctx.getSource().sendSuccess(
                            () -> Component.translatable("command.roninpath.rank", Ranks.rankName(rank), points),
                            false);
                    return points;
                }))
                .then(Commands.literal("points")
                        .requires(source -> source.hasPermission(2))
                        .then(Commands.literal("add")
                                .then(Commands.argument("targets", EntityArgument.players())
                                        .then(Commands.argument("amount", IntegerArgumentType.integer())
                                                .executes(ctx -> modify(ctx, false)))))
                        .then(Commands.literal("set")
                                .then(Commands.argument("targets", EntityArgument.players())
                                        .then(Commands.argument("amount", IntegerArgumentType.integer(0))
                                                .executes(ctx -> modify(ctx, true)))))
                        .then(Commands.literal("get")
                                .then(Commands.argument("target", EntityArgument.player())
                                        .executes(ctx -> {
                                            ServerPlayer target = EntityArgument.getPlayer(ctx, "target");
                                            int points = Ranks.getPoints(target);
                                            ctx.getSource().sendSuccess(
                                                    () -> Component.translatable("command.roninpath.get",
                                                            target.getDisplayName(), points),
                                                    false);
                                            return points;
                                        })))));
    }

    private static int modify(CommandContext<CommandSourceStack> ctx, boolean set) throws CommandSyntaxException {
        int amount = IntegerArgumentType.getInteger(ctx, "amount");
        Collection<ServerPlayer> targets = EntityArgument.getPlayers(ctx, "targets");
        for (ServerPlayer player : targets) {
            Ranks.setPoints(player, set ? amount : Ranks.getPoints(player) + amount);
        }
        int count = targets.size();
        ctx.getSource().sendSuccess(() -> Component.translatable("command.roninpath.updated", count), true);
        return count;
    }
}
