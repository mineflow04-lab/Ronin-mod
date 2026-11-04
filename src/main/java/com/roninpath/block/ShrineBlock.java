package com.roninpath.block;

import com.mojang.serialization.MapCodec;
import com.roninpath.init.ModAttachments;
import com.roninpath.init.ModItems;
import com.roninpath.net.OpenShrinePayload;
import com.roninpath.util.Ranks;
import com.roninpath.util.ShrineQuests;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.network.PacketDistributor;

/** Kitsune shrine: right-click opens the shrine screen; offerings give two random blessings. */
public class ShrineBlock extends Block {
    public static final MapCodec<ShrineBlock> CODEC = simpleCodec(ShrineBlock::new);

    private static final List<Holder<MobEffect>> BLESSINGS = List.of(
            MobEffects.REGENERATION,
            MobEffects.NIGHT_VISION,
            MobEffects.LUCK,
            MobEffects.FIRE_RESISTANCE,
            MobEffects.DAMAGE_RESISTANCE);

    /** Direction the player was looking when placing; the stepped front of the model faces the player. */
    public static final DirectionProperty FACING = net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING;

    // Three tiers of the model for facing=north (the model's low front step is on the south side).
    private static final double[][] TIERS = {
            {0, 0, 9, 16, 9, 16},
            {0, 0, 4, 16, 12.5, 9},
            {0, 0, 0, 16, 16, 4}
    };
    private static final VoxelShape[] SHAPES = new VoxelShape[4]; // indexed by number of 90-degree clockwise turns

    static {
        for (int turns = 0; turns < 4; turns++) {
            VoxelShape shape = Shapes.empty();
            for (double[] t : TIERS) {
                double x1 = t[0], z1 = t[2], x2 = t[3], z2 = t[5];
                for (int i = 0; i < turns; i++) {
                    double nx1 = 16 - z2, nx2 = 16 - z1, nz1 = x1, nz2 = x2;
                    x1 = nx1; x2 = nx2; z1 = nz1; z2 = nz2;
                }
                shape = Shapes.or(shape, Block.box(x1, t[1], z1, x2, t[4], z2));
            }
            SHAPES[turns] = shape;
        }
    }

    public ShrineBlock(Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection());
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        // north=0 turns, east=1, south=2, west=3 (get2DDataValue: south=0, west=1, north=2, east=3)
        return SHAPES[(state.getValue(FACING).get2DDataValue() + 2) % 4];
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
                                               BlockHitResult hit) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }
        if (player instanceof ServerPlayer sp) {
            ShrineQuests.check(sp);
            PacketDistributor.sendToPlayer(sp, new OpenShrinePayload(pos));
        }
        return InteractionResult.CONSUME;
    }

    /** First offering stack in the player's inventory, or EMPTY. Used by the server and by the screen. */
    public static ItemStack findOffering(Player player) {
        Inventory inv = player.getInventory();
        for (int i = 0; i < inv.getContainerSize(); i++) {
            ItemStack stack = inv.getItem(i);
            if (stack.is(ModItems.KITSUNE_OFFERING.get())) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }

    /** Makes an offering: two random blessings, +2 points, quest progress. Returns false if it was not possible. */
    public static boolean offer(ServerPlayer player, ServerLevel level, BlockPos pos) {
        ItemStack stack = findOffering(player);
        if (stack.isEmpty() || player.getCooldowns().isOnCooldown(stack.getItem())) {
            return false;
        }

        List<Holder<MobEffect>> pool = new ArrayList<>(BLESSINGS);
        Collections.shuffle(pool, new java.util.Random(level.getRandom().nextLong()));
        for (int i = 0; i < 2; i++) {
            player.addEffect(new MobEffectInstance(pool.get(i), 20 * 60 * 5, 0));
        }

        player.getCooldowns().addCooldown(stack.getItem(), 20 * 60);
        stack.consume(1, player);

        level.playSound(null, pos, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 1.0f, 1.0f);
        level.sendParticles(ParticleTypes.HAPPY_VILLAGER,
                pos.getX() + 0.5, pos.getY() + 1.1, pos.getZ() + 0.5, 12, 0.3, 0.3, 0.3, 0.0);

        player.setData(ModAttachments.OFFERINGS, player.getData(ModAttachments.OFFERINGS) + 1);
        Ranks.setPoints(player, Ranks.getPoints(player) + 2);
        ShrineQuests.check(player);
        return true;
    }
}
