package com.roninpath.init;

import com.roninpath.RoninPath;
import java.util.function.Supplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModTabs {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RoninPath.MODID);

    public static final Supplier<CreativeModeTab> MAIN = TABS.register("main",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.roninpath"))
                    .icon(() -> new ItemStack(ModItems.ONI_HORN.get()))
                    .displayItems((params, output) -> {
                        output.accept(ModItems.ONI_HORN.get());
                        output.accept(ModItems.KITSUNE_OFFERING.get());
                        output.accept(ModItems.ONI_KANABO.get());
                        output.accept(ModItems.ONIGIRI.get());
                        output.accept(ModItems.ABURAAGE.get());
                        output.accept(ModItems.INARI_SUSHI.get());
                        output.accept(ModItems.RED_BEAN_MOCHI.get());
                        output.accept(ModItems.SAKE_BOTTLE.get());
                        output.accept(ModItems.KITSUNE_MASK.get());
                        output.accept(ModItems.KITSUNE_SHRINE.get());
                        output.accept(ModItems.ONI_SPAWN_EGG.get());
                    })
                    .build());

    private ModTabs() {}
}
