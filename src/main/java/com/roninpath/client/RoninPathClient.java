package com.roninpath.client;

import com.roninpath.RoninPath;
import com.roninpath.init.ModEntities;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(value = RoninPath.MODID, dist = Dist.CLIENT)
public class RoninPathClient {
    public RoninPathClient(IEventBus modBus, ModContainer container) {
        modBus.addListener(RoninPathClient::registerRenderers);
        modBus.addListener(RoninPathClient::registerLayers);
    }

    private static void registerLayers(net.neoforged.neoforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(OniModel.LAYER, OniModel::createLayer);
    }

    private static void registerRenderers(net.neoforged.neoforge.client.event.EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.ONI.get(), OniRenderer::new);
    }
}
