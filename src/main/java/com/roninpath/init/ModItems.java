package com.roninpath.init;

import com.roninpath.RoninPath;
import com.roninpath.block.ShrineBlock;
import com.roninpath.item.KitsuneMaskItem;
import com.roninpath.item.SakeItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(RoninPath.MODID);

    public static final DeferredItem<Item> ONI_HORN = ITEMS.registerSimpleItem(
            "oni_horn", new Item.Properties().rarity(Rarity.UNCOMMON));

    public static final DeferredItem<Item> KITSUNE_OFFERING = ITEMS.registerSimpleItem(
            "kitsune_offering", new Item.Properties().stacksTo(16));

    public static final DeferredItem<Item> ONIGIRI = ITEMS.registerSimpleItem(
            "onigiri", new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(6).saturationModifier(0.8f).build()));

    public static final DeferredItem<Item> ABURAAGE = ITEMS.registerSimpleItem(
            "aburaage", new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(5).saturationModifier(0.6f).build()));

    public static final DeferredItem<Item> INARI_SUSHI = ITEMS.registerSimpleItem(
            "inari_sushi", new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(8).saturationModifier(0.8f).build()));

    public static final DeferredItem<Item> RED_BEAN_MOCHI = ITEMS.registerSimpleItem(
            "red_bean_mochi", new Item.Properties().food(
                    new FoodProperties.Builder().nutrition(4).saturationModifier(0.5f).fast().build()));

    public static final DeferredItem<SakeItem> SAKE_BOTTLE = ITEMS.register(
            "sake_bottle",
            () -> new SakeItem(new Item.Properties().stacksTo(16).food(
                    new FoodProperties.Builder().nutrition(2).saturationModifier(0.3f).alwaysEdible()
                            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20 * 30), 1.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 20 * 8), 1.0f)
                            .build())));

    public static final DeferredItem<KitsuneMaskItem> KITSUNE_MASK = ITEMS.register(
            "kitsune_mask",
            () -> new KitsuneMaskItem(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));

    public static final DeferredItem<SwordItem> ONI_KANABO = ITEMS.register(
            "oni_kanabo",
            () -> new SwordItem(Tiers.DIAMOND, new Item.Properties()
                    .rarity(Rarity.RARE)
                    .attributes(SwordItem.createAttributes(Tiers.DIAMOND, 7, -3.2f))));

    public static final DeferredItem<BlockItem> KITSUNE_SHRINE = ITEMS.registerSimpleBlockItem(
            ModBlocks.KITSUNE_SHRINE);

    public static final DeferredItem<DeferredSpawnEggItem> ONI_SPAWN_EGG = ITEMS.register(
            "oni_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.ONI, 0x9B1B1B, 0x1A1A1A, new Item.Properties()));

    private ModItems() {}
}
