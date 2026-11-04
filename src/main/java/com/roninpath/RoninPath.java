package com.roninpath;

import com.roninpath.init.ModAttachments;
import com.roninpath.init.ModBlocks;
import com.roninpath.init.ModEntities;
import com.roninpath.init.ModEvents;
import com.roninpath.init.ModItems;
import com.roninpath.init.ModTabs;
import com.roninpath.net.ModNetwork;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;

@Mod(RoninPath.MODID)
public class RoninPath {
    public static final String MODID = "roninpath";

    public RoninPath(IEventBus modBus, ModContainer container) {
        ModBlocks.BLOCKS.register(modBus);
        ModItems.ITEMS.register(modBus);
        ModEntities.ENTITIES.register(modBus);
        ModAttachments.ATTACHMENTS.register(modBus);
        ModTabs.TABS.register(modBus);

        modBus.addListener(ModEvents::onAttributes);
        modBus.addListener(ModEvents::onSpawnPlacements);
        modBus.addListener(ModNetwork::register);

        NeoForge.EVENT_BUS.addListener(ModEvents::onRegisterCommands);
        NeoForge.EVENT_BUS.addListener(ModEvents::onLivingDeath);
        NeoForge.EVENT_BUS.addListener(ModEvents::onPlayerLogin);
        NeoForge.EVENT_BUS.addListener(ModEvents::onPlayerRespawn);
    }
}
