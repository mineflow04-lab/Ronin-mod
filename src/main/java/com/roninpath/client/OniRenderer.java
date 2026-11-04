package com.roninpath.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.roninpath.RoninPath;
import com.roninpath.entity.OniEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class OniRenderer extends MobRenderer<OniEntity, OniModel> {
    private static final ResourceLocation TEX =
            ResourceLocation.fromNamespaceAndPath(RoninPath.MODID, "textures/entity/oni_model.png");

    public OniRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new OniModel(ctx.bakeLayer(OniModel.LAYER)), 0.8F);
    }

    @Override
    protected void scale(OniEntity e, PoseStack pose, float partialTick) {
        pose.scale(0.95F, 0.95F, 0.95F);
    }

    @Override
    public ResourceLocation getTextureLocation(OniEntity e) { return TEX; }
}
