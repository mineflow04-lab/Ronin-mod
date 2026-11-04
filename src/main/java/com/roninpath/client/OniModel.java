package com.roninpath.client;

import com.roninpath.RoninPath;
import com.roninpath.entity.OniEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

/** Подробная 3D-модель они (~320 кубов, ~260 частей). Текстура 1024x1024 = 256x256 единиц модели. */
public class OniModel extends EntityModel<OniEntity> {
    public static final ModelLayerLocation LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(RoninPath.MODID, "oni"), "main");

    private final ModelPart root, head, rightArm, leftArm, rightLeg, leftLeg;

    public OniModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.rightArm = root.getChild("right_arm");
        this.leftArm = root.getChild("left_arm");
        this.rightLeg = root.getChild("right_leg");
        this.leftLeg = root.getChild("left_leg");
    }

    public static LayerDefinition createLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        PartDefinition p_body = root.addOrReplaceChild("body", CubeListBuilder.create()
                
.texOffs(0, 0).addBox(-4.5F, 0F, -2.5F, 9F, 12F, 5F), PartPose.offset(0F, 0F, 0F));
        PartDefinition p_collar_l = p_body.addOrReplaceChild("collar_l", CubeListBuilder.create()
                
.texOffs(191, 17).addBox(0F, 0F, -0.6F, 1.6F, 7F, 1.2F), PartPose.offsetAndRotation(0F, 0.5F, -2.6F, 0F, 0F, 0.55F));
        PartDefinition p_collar_r = p_body.addOrReplaceChild("collar_r", CubeListBuilder.create()
                
.texOffs(197, 17).addBox(-1.6F, 0F, -0.6F, 1.6F, 7F, 1.2F), PartPose.offsetAndRotation(0F, 0.5F, -2.6F, 0F, 0F, -0.55F));
        PartDefinition p_chest_cord = p_body.addOrReplaceChild("chest_cord", CubeListBuilder.create()
                
.texOffs(25, 83).addBox(-3.5F, 0F, -0.3F, 7F, 0.8F, 0.6F), PartPose.offset(0F, 3F, -2.7F));
        PartDefinition p_belt = p_body.addOrReplaceChild("belt", CubeListBuilder.create()
                
.texOffs(141, 70).addBox(-5F, 8F, -3.6F, 10F, 2.4F, 1.4F)                .texOffs(164, 70).addBox(-5F, 8F, 2.2F, 10F, 2.4F, 1.4F)                .texOffs(48, 29).addBox(-5.4F, 8F, -2.4F, 1.4F, 2.4F, 4.8F)                .texOffs(61, 29).addBox(4F, 8F, -2.4F, 1.4F, 2.4F, 4.8F)                .texOffs(133, 46).addBox(-5.2F, 7.6F, -3.8F, 2.4F, 3.2F, 2F)                .texOffs(142, 46).addBox(2.8F, 7.6F, -3.8F, 2.4F, 3.2F, 2F)                .texOffs(116, 59).addBox(-5.2F, 7.6F, 2F, 2.4F, 3.2F, 1.8F)                .texOffs(125, 59).addBox(2.8F, 7.6F, 2F, 2.4F, 3.2F, 1.8F)                .texOffs(134, 59).addBox(-1.6F, 7.4F, -4.4F, 3.2F, 3.2F, 1.6F)                .texOffs(44, 79).addBox(-3.2F, 8.2F, -4.2F, 1.6F, 1.6F, 1F)                .texOffs(50, 79).addBox(1.6F, 8.2F, -4.2F, 1.6F, 1.6F, 1F), PartPose.offset(0F, 0F, 0F));
        PartDefinition p_belt_tail1 = p_body.addOrReplaceChild("belt_tail1", CubeListBuilder.create()
                
.texOffs(74, 29).addBox(-0.75F, -6.5F, -0.75F, 1.5F, 6.5F, 1.5F), PartPose.offsetAndRotation(2.4F, 10F, -4.2F, 0.0986F, 0F, 2.9927F));
        PartDefinition p_belt_tail1b = p_body.addOrReplaceChild("belt_tail1b", CubeListBuilder.create()
                
.texOffs(144, 59).addBox(-0.65F, -3.5F, -0.65F, 1.3F, 3.5F, 1.3F), PartPose.offsetAndRotation(3.3595F, 16.3969F, -4.8397F, -0.1427F, 0F, 2.8501F));
        PartDefinition p_belt_tail2 = p_body.addOrReplaceChild("belt_tail2", CubeListBuilder.create()
                
.texOffs(112, 38).addBox(-0.75F, -5F, -0.75F, 1.5F, 5F, 1.5F), PartPose.offsetAndRotation(3.6F, 10F, -4F, 0.146F, 0F, -2.9442F));
        PartDefinition p_belt_tail2b = p_body.addOrReplaceChild("belt_tail2b", CubeListBuilder.create()
                
.texOffs(150, 59).addBox(-0.65F, -3F, -0.65F, 1.3F, 3F, 1.3F), PartPose.offsetAndRotation(2.6299F, 14.8507F, -4.7276F, -0.0977F, 0F, -2.9442F));
        PartDefinition p_hip_mask = p_body.addOrReplaceChild("hip_mask", CubeListBuilder.create()
                
.texOffs(118, 38).addBox(-0.5F, 0F, -1.8F, 0.8F, 3.4F, 3.6F)                .texOffs(41, 83).addBox(-0.5F, -0.2F, -1.9F, 0.9F, 1F, 1F), PartPose.offsetAndRotation(-4.9F, 10.2F, -1F, 0F, 0.2F, 0F));
        PartDefinition p_sk_f0 = root.addOrReplaceChild("sk_f0", CubeListBuilder.create()
                
.texOffs(209, 0).addBox(-0.5F, 0F, -0.5F, 1F, 11F, 1F), PartPose.offsetAndRotation(-4F, 10.2F, -3.2F, -0.07F, 0F, 0F));
        PartDefinition p_sk_b0 = root.addOrReplaceChild("sk_b0", CubeListBuilder.create()
                
.texOffs(52, 17).addBox(-0.5F, 0F, -0.5F, 1F, 9.5F, 1F), PartPose.offsetAndRotation(-4F, 10.2F, 3.2F, 0.07F, 0F, 0F));
        PartDefinition p_sk_f1 = root.addOrReplaceChild("sk_f1", CubeListBuilder.create()
                
.texOffs(213, 0).addBox(-0.5F, 0F, -0.5F, 1F, 11F, 1F), PartPose.offsetAndRotation(-3F, 10.2F, -3.2F, -0.09F, 0F, 0F));
        PartDefinition p_sk_b1 = root.addOrReplaceChild("sk_b1", CubeListBuilder.create()
                
.texOffs(217, 0).addBox(-0.5F, 0F, -0.5F, 1F, 11F, 1F), PartPose.offsetAndRotation(-3F, 10.2F, 3.2F, 0.09F, 0F, 0F));
        PartDefinition p_sk_f2 = root.addOrReplaceChild("sk_f2", CubeListBuilder.create()
                
.texOffs(56, 17).addBox(-0.5F, 0F, -0.5F, 1F, 9.5F, 1F), PartPose.offsetAndRotation(-2F, 10.2F, -3.2F, -0.11F, 0F, 0F));
        PartDefinition p_sk_b2 = root.addOrReplaceChild("sk_b2", CubeListBuilder.create()
                
.texOffs(60, 17).addBox(-0.5F, 0F, -0.5F, 1F, 9.5F, 1F), PartPose.offsetAndRotation(-2F, 10.2F, 3.2F, 0.11F, 0F, 0F));
        PartDefinition p_sk_f3 = root.addOrReplaceChild("sk_f3", CubeListBuilder.create()
                
.texOffs(76, 17).addBox(-0.5F, 0F, -0.5F, 1F, 9F, 1F), PartPose.offsetAndRotation(-1F, 10.2F, -3.2F, -0.07F, 0F, 0F));
        PartDefinition p_sk_b3 = root.addOrReplaceChild("sk_b3", CubeListBuilder.create()
                
.texOffs(80, 17).addBox(-0.5F, 0F, -0.5F, 1F, 9F, 1F), PartPose.offsetAndRotation(-1F, 10.2F, 3.2F, 0.07F, 0F, 0F));
        PartDefinition p_sk_f4 = root.addOrReplaceChild("sk_f4", CubeListBuilder.create()
                
.texOffs(64, 17).addBox(-0.5F, 0F, -0.5F, 1F, 9.5F, 1F), PartPose.offsetAndRotation(0F, 10.2F, -3.2F, -0.09F, 0F, 0F));
        PartDefinition p_sk_b4 = root.addOrReplaceChild("sk_b4", CubeListBuilder.create()
                
.texOffs(221, 0).addBox(-0.5F, 0F, -0.5F, 1F, 11F, 1F), PartPose.offsetAndRotation(0F, 10.2F, 3.2F, 0.09F, 0F, 0F));
        PartDefinition p_sk_f5 = root.addOrReplaceChild("sk_f5", CubeListBuilder.create()
                
.texOffs(225, 0).addBox(-0.5F, 0F, -0.5F, 1F, 10.5F, 1F), PartPose.offsetAndRotation(1F, 10.2F, -3.2F, -0.11F, 0F, 0F));
        PartDefinition p_sk_b5 = root.addOrReplaceChild("sk_b5", CubeListBuilder.create()
                
.texOffs(68, 17).addBox(-0.5F, 0F, -0.5F, 1F, 9.5F, 1F), PartPose.offsetAndRotation(1F, 10.2F, 3.2F, 0.11F, 0F, 0F));
        PartDefinition p_sk_f6 = root.addOrReplaceChild("sk_f6", CubeListBuilder.create()
                
.texOffs(84, 17).addBox(-0.5F, 0F, -0.5F, 1F, 9F, 1F), PartPose.offsetAndRotation(2F, 10.2F, -3.2F, -0.07F, 0F, 0F));
        PartDefinition p_sk_b6 = root.addOrReplaceChild("sk_b6", CubeListBuilder.create()
                
.texOffs(203, 17).addBox(-0.5F, 0F, -0.5F, 1F, 8F, 1F), PartPose.offsetAndRotation(2F, 10.2F, 3.2F, 0.07F, 0F, 0F));
        PartDefinition p_sk_f7 = root.addOrReplaceChild("sk_f7", CubeListBuilder.create()
                
.texOffs(229, 0).addBox(-0.5F, 0F, -0.5F, 1F, 11F, 1F), PartPose.offsetAndRotation(3F, 10.2F, -3.2F, -0.09F, 0F, 0F));
        PartDefinition p_sk_b7 = root.addOrReplaceChild("sk_b7", CubeListBuilder.create()
                
.texOffs(72, 17).addBox(-0.5F, 0F, -0.5F, 1F, 10F, 1F), PartPose.offsetAndRotation(3F, 10.2F, 3.2F, 0.09F, 0F, 0F));
        PartDefinition p_sk_f8 = root.addOrReplaceChild("sk_f8", CubeListBuilder.create()
                
.texOffs(88, 17).addBox(-0.5F, 0F, -0.5F, 1F, 9F, 1F), PartPose.offsetAndRotation(4F, 10.2F, -3.2F, -0.11F, 0F, 0F));
        PartDefinition p_sk_b8 = root.addOrReplaceChild("sk_b8", CubeListBuilder.create()
                
.texOffs(207, 17).addBox(-0.5F, 0F, -0.5F, 1F, 8F, 1F), PartPose.offsetAndRotation(4F, 10.2F, 3.2F, 0.11F, 0F, 0F));
        PartDefinition p_sk_l0 = root.addOrReplaceChild("sk_l0", CubeListBuilder.create()
                
.texOffs(211, 17).addBox(-0.5F, 0F, -0.6F, 1F, 7F, 1.2F), PartPose.offsetAndRotation(5F, 10.2F, -1.8F, 0F, 0F, 0.08F));
        PartDefinition p_sk_r0 = root.addOrReplaceChild("sk_r0", CubeListBuilder.create()
                
.texOffs(233, 0).addBox(-0.5F, 0F, -0.6F, 1F, 10F, 1.2F), PartPose.offsetAndRotation(-5F, 10.2F, -1.8F, 0F, 0F, -0.08F));
        PartDefinition p_sk_l1 = root.addOrReplaceChild("sk_l1", CubeListBuilder.create()
                
.texOffs(238, 0).addBox(-0.5F, 0F, -0.6F, 1F, 10F, 1.2F), PartPose.offsetAndRotation(5F, 10.2F, -0.6F, 0F, 0F, 0.11F));
        PartDefinition p_sk_r1 = root.addOrReplaceChild("sk_r1", CubeListBuilder.create()
                
.texOffs(92, 17).addBox(-0.5F, 0F, -0.6F, 1F, 8F, 1.2F), PartPose.offsetAndRotation(-5F, 10.2F, -0.6F, 0F, 0F, -0.11F));
        PartDefinition p_sk_l2 = root.addOrReplaceChild("sk_l2", CubeListBuilder.create()
                
.texOffs(216, 17).addBox(-0.5F, 0F, -0.6F, 1F, 7F, 1.2F), PartPose.offsetAndRotation(5F, 10.2F, 0.6F, 0F, 0F, 0.08F));
        PartDefinition p_sk_r2 = root.addOrReplaceChild("sk_r2", CubeListBuilder.create()
                
.texOffs(221, 17).addBox(-0.5F, 0F, -0.6F, 1F, 7F, 1.2F), PartPose.offsetAndRotation(-5F, 10.2F, 0.6F, 0F, 0F, -0.08F));
        PartDefinition p_sk_l3 = root.addOrReplaceChild("sk_l3", CubeListBuilder.create()
                
.texOffs(226, 17).addBox(-0.5F, 0F, -0.6F, 1F, 7F, 1.2F), PartPose.offsetAndRotation(5F, 10.2F, 1.8F, 0F, 0F, 0.11F));
        PartDefinition p_sk_r3 = root.addOrReplaceChild("sk_r3", CubeListBuilder.create()
                
.texOffs(231, 17).addBox(-0.5F, 0F, -0.6F, 1F, 7F, 1.2F), PartPose.offsetAndRotation(-5F, 10.2F, 1.8F, 0F, 0F, -0.11F));
        PartDefinition p_sk_l4 = root.addOrReplaceChild("sk_l4", CubeListBuilder.create()
                
.texOffs(97, 17).addBox(-0.5F, 0F, -0.6F, 1F, 8F, 1.2F), PartPose.offsetAndRotation(5F, 10.2F, 3F, 0F, 0F, 0.08F));
        PartDefinition p_sk_r4 = root.addOrReplaceChild("sk_r4", CubeListBuilder.create()
                
.texOffs(102, 17).addBox(-0.5F, 0F, -0.6F, 1F, 8F, 1.2F), PartPose.offsetAndRotation(-5F, 10.2F, 3F, 0F, 0F, -0.08F));
        PartDefinition p_head = root.addOrReplaceChild("head", CubeListBuilder.create()
                
.texOffs(45, 0).addBox(-4F, -8F, -4F, 8F, 8F, 8F), PartPose.offset(0F, 0F, 0F));
        PartDefinition p_brow = p_head.addOrReplaceChild("brow", CubeListBuilder.create()
                
.texOffs(56, 79).addBox(-4.6F, -0.6F, -0.8F, 9.2F, 1.4F, 1.4F), PartPose.offset(0F, -5.6F, -4.2F));
        PartDefinition p_brow_l = p_head.addOrReplaceChild("brow_l", CubeListBuilder.create()
                
.texOffs(78, 79).addBox(-1.8F, -0.5F, -0.7F, 3.6F, 1.2F, 1.2F), PartPose.offsetAndRotation(2.4F, -5.8F, -4.4F, 0F, 0F, -0.35F));
        PartDefinition p_brow_r = p_head.addOrReplaceChild("brow_r", CubeListBuilder.create()
                
.texOffs(88, 79).addBox(-1.8F, -0.5F, -0.7F, 3.6F, 1.2F, 1.2F), PartPose.offsetAndRotation(-2.4F, -5.8F, -4.4F, 0F, 0F, 0.35F));
        PartDefinition p_muzzle = p_head.addOrReplaceChild("muzzle", CubeListBuilder.create()
                
.texOffs(151, 46).addBox(-2.4F, -1.6F, -2F, 4.8F, 3.2F, 2F)                .texOffs(98, 79).addBox(-1.3F, -2.4F, -2.5F, 2.6F, 1.4F, 1.2F)                .texOffs(106, 79).addBox(-1.6F, 1F, -1.8F, 0.9F, 1.8F, 0.9F)                .texOffs(110, 79).addBox(0.7F, 1F, -1.8F, 0.9F, 1.8F, 0.9F), PartPose.offset(0F, -2.6F, -4.2F));
        PartDefinition p_jaw = p_head.addOrReplaceChild("jaw", CubeListBuilder.create()
                
.texOffs(165, 46).addBox(-3F, 0F, -3.2F, 6F, 1.6F, 3.8F)                .texOffs(114, 79).addBox(-2.6F, -1.6F, -3F, 0.9F, 1.9F, 0.9F)                .texOffs(118, 79).addBox(1.7F, -1.6F, -3F, 0.9F, 1.9F, 0.9F)                .texOffs(45, 83).addBox(-1.5F, 0.1F, -3.5F, 3F, 0.9F, 0.5F), PartPose.offset(0F, -1.2F, -3F));
        PartDefinition p_ear_l = p_head.addOrReplaceChild("ear_l", CubeListBuilder.create()
                
.texOffs(185, 46).addBox(-0.6F, -1.8F, -1.2F, 1.2F, 3.2F, 2.4F), PartPose.offsetAndRotation(4.3F, -5F, 0.5F, 0F, 0F, 0.5F));
        PartDefinition p_ear_r = p_head.addOrReplaceChild("ear_r", CubeListBuilder.create()
                
.texOffs(193, 46).addBox(-0.6F, -1.8F, -1.2F, 1.2F, 3.2F, 2.4F), PartPose.offsetAndRotation(-4.3F, -5F, 0.5F, 0F, 0F, -0.5F));
        PartDefinition p_horn_l1 = p_head.addOrReplaceChild("horn_l1", CubeListBuilder.create()
                
.texOffs(201, 46).addBox(-1.4F, -2.8F, -1.4F, 2.8F, 2.8F, 2.8F), PartPose.offsetAndRotation(2.6F, -8F, -2.8F, 0.1406F, 0F, 0.3367F));
        PartDefinition p_horn_l2 = p_head.addOrReplaceChild("horn_l2", CubeListBuilder.create()
                
.texOffs(156, 59).addBox(-1.1F, -2.6F, -1.1F, 2.2F, 2.6F, 2.2F), PartPose.offsetAndRotation(3.5158F, -10.6167F, -3.1925F, 0.6631F, 0F, 0.5713F));
        PartDefinition p_horn_l3 = p_head.addOrReplaceChild("horn_l3", CubeListBuilder.create()
                
.texOffs(187, 70).addBox(-0.8F, -2.4F, -0.8F, 1.6F, 2.4F, 1.6F), PartPose.offsetAndRotation(4.6239F, -12.3403F, -4.793F, 1.1985F, 0F, 0.6947F));
        PartDefinition p_horn_l4 = p_head.addOrReplaceChild("horn_l4", CubeListBuilder.create()
                
.texOffs(122, 79).addBox(-0.5F, -1.8F, -0.5F, 1F, 1.8F, 1F), PartPose.offsetAndRotation(5.1828F, -13.011F, -7.0286F, 1.4303F, 0F, 2.3562F));
        PartDefinition p_horn_r1 = p_head.addOrReplaceChild("horn_r1", CubeListBuilder.create()
                
.texOffs(213, 46).addBox(-1.4F, -2.8F, -1.4F, 2.8F, 2.8F, 2.8F), PartPose.offsetAndRotation(-2.6F, -8F, -2.8F, 0.1406F, 0F, -0.3367F));
        PartDefinition p_horn_r2 = p_head.addOrReplaceChild("horn_r2", CubeListBuilder.create()
                
.texOffs(165, 59).addBox(-1.1F, -2.6F, -1.1F, 2.2F, 2.6F, 2.2F), PartPose.offsetAndRotation(-3.5158F, -10.6167F, -3.1925F, 0.6631F, 0F, -0.5713F));
        PartDefinition p_horn_r3 = p_head.addOrReplaceChild("horn_r3", CubeListBuilder.create()
                
.texOffs(194, 70).addBox(-0.8F, -2.4F, -0.8F, 1.6F, 2.4F, 1.6F), PartPose.offsetAndRotation(-4.6239F, -12.3403F, -4.793F, 1.1985F, 0F, -0.6947F));
        PartDefinition p_horn_r4 = p_head.addOrReplaceChild("horn_r4", CubeListBuilder.create()
                
.texOffs(126, 79).addBox(-0.5F, -1.8F, -0.5F, 1F, 1.8F, 1F), PartPose.offsetAndRotation(-5.1828F, -13.011F, -7.0286F, 1.4303F, 0F, -2.3562F));
        PartDefinition p_m0a = p_head.addOrReplaceChild("m0a", CubeListBuilder.create()
                
.texOffs(225, 46).addBox(-1.2967F, -3.0909F, -1.2967F, 2.5934F, 3.0909F, 2.5934F), PartPose.offsetAndRotation(2.8F, -8.2F, 0F, -0.1767F, 0F, 0.7753F));
        PartDefinition p_m0b = p_head.addOrReplaceChild("m0b", CubeListBuilder.create()
                
.texOffs(174, 59).addBox(-1.0374F, -2.404F, -1.0374F, 2.0747F, 2.404F, 2.0747F), PartPose.offsetAndRotation(4.9297F, -10.3732F, 0.5433F, -0.2278F, 0F, 1.1403F));
        PartDefinition p_m0c = p_head.addOrReplaceChild("m0c", CubeListBuilder.create()
                
.texOffs(201, 70).addBox(-0.778F, -2.0606F, -0.778F, 1.5561F, 2.0606F, 1.5561F), PartPose.offsetAndRotation(7.058F, -11.3505F, 1.0862F, -0.2442F, 0F, 1.786F));
        PartDefinition p_m1a = p_head.addOrReplaceChild("m1a", CubeListBuilder.create()
                
.texOffs(127, 38).addBox(-1.377F, -3.3235F, -1.377F, 2.754F, 3.3235F, 2.754F), PartPose.offsetAndRotation(2.1449F, -8.2F, 1.7998F, -0.6132F, 0F, 0.644F));
        PartDefinition p_m1b = p_head.addOrReplaceChild("m1b", CubeListBuilder.create()
                
.texOffs(183, 59).addBox(-1.1016F, -2.585F, -1.1016F, 2.2032F, 2.585F, 2.2032F), PartPose.offsetAndRotation(3.7767F, -10.3736F, 3.7125F, -0.7881F, 0F, 1.0308F));
        PartDefinition p_m1c = p_head.addOrReplaceChild("m1c", CubeListBuilder.create()
                
.texOffs(208, 70).addBox(-0.8262F, -2.2157F, -0.8262F, 1.6524F, 2.2157F, 1.6524F), PartPose.offsetAndRotation(5.3403F, -11.3109F, 5.5452F, -0.8289F, 0F, 1.9448F));
        PartDefinition p_m2a = p_head.addOrReplaceChild("m2a", CubeListBuilder.create()
                
.texOffs(236, 46).addBox(-1.3689F, -3.2489F, -1.3689F, 2.7379F, 3.2489F, 2.7379F), PartPose.offsetAndRotation(0.4862F, -8.2F, 2.7575F, -0.8752F, 0F, 0.1686F));
        PartDefinition p_m2b = p_head.addOrReplaceChild("m2b", CubeListBuilder.create()
                
.texOffs(192, 59).addBox(-1.0951F, -2.5269F, -1.0951F, 2.1903F, 2.5269F, 2.1903F), PartPose.offsetAndRotation(0.8355F, -10.2525F, 5.2515F, -1.1938F, 0F, 0.3615F));
        PartDefinition p_m2c = p_head.addOrReplaceChild("m2c", CubeListBuilder.create()
                
.texOffs(215, 70).addBox(-0.8214F, -2.1659F, -0.8214F, 1.6427F, 2.1659F, 1.6427F), PartPose.offsetAndRotation(1.1645F, -11.1226F, 7.6009F, -1.2715F, 0F, 2.6705F));
        PartDefinition p_m3a = p_head.addOrReplaceChild("m3a", CubeListBuilder.create()
                
.texOffs(201, 59).addBox(-1.1862F, -2.2892F, -1.1862F, 2.3725F, 2.2892F, 2.3725F), PartPose.offsetAndRotation(-1.4F, -8.2F, 2.4249F, -0.7787F, 0F, -0.4556F));
        PartDefinition p_m3b = p_head.addOrReplaceChild("m3b", CubeListBuilder.create()
                
.texOffs(222, 70).addBox(-0.949F, -1.7805F, -0.949F, 1.898F, 1.7805F, 1.898F), PartPose.offsetAndRotation(-2.117F, -9.6633F, 4.0326F, -1.0263F, 0F, -0.8279F));
        PartDefinition p_m3c = p_head.addOrReplaceChild("m3c", CubeListBuilder.create()
                
.texOffs(130, 79).addBox(-0.7117F, -1.5261F, -0.7117F, 1.4235F, 1.5261F, 1.4235F), PartPose.offsetAndRotation(-2.7963F, -10.2871F, 5.5556F, -1.0811F, 0F, -2.1503F));
        PartDefinition p_m4a = p_head.addOrReplaceChild("m4a", CubeListBuilder.create()
                
.texOffs(139, 38).addBox(-1.2596F, -3.5235F, -1.2596F, 2.5192F, 3.5235F, 2.5192F), PartPose.offsetAndRotation(-2.6311F, -8.2F, 0.9577F, -0.4065F, 0F, -0.7442F));
        PartDefinition p_m4b = p_head.addOrReplaceChild("m4b", CubeListBuilder.create()
                
.texOffs(211, 59).addBox(-1.0077F, -2.7405F, -1.0077F, 2.0153F, 2.7405F, 2.0153F), PartPose.offsetAndRotation(-4.8235F, -10.5807F, 2.3508F, -0.5188F, 0F, -1.1163F));
        PartDefinition p_m4c = p_head.addOrReplaceChild("m4c", CubeListBuilder.create()
                
.texOffs(230, 70).addBox(-0.7558F, -2.349F, -0.7558F, 1.5115F, 2.349F, 1.5115F), PartPose.offsetAndRotation(-6.9619F, -11.6256F, 3.7096F, -0.5491F, 0F, -1.8441F));
        PartDefinition p_m5a = p_head.addOrReplaceChild("m5a", CubeListBuilder.create()
                
.texOffs(247, 46).addBox(-1.0453F, -3.4662F, -1.0453F, 2.0906F, 3.4662F, 2.0906F), PartPose.offsetAndRotation(-2.6311F, -8.2F, -0.9577F, 0.0626F, 0F, -0.7442F));
        PartDefinition p_m5b = p_head.addOrReplaceChild("m5b", CubeListBuilder.create()
                
.texOffs(220, 59).addBox(-0.8362F, -2.6959F, -0.8362F, 1.6725F, 2.6959F, 1.6725F), PartPose.offsetAndRotation(-4.9746F, -10.7448F, -1.1744F, 0.0829F, 0F, -1.1163F));
        PartDefinition p_m5c = p_head.addOrReplaceChild("m5c", CubeListBuilder.create()
                
.texOffs(237, 70).addBox(-0.6272F, -2.3108F, -0.6272F, 1.2543F, 2.3108F, 1.2543F), PartPose.offsetAndRotation(-7.3885F, -11.9243F, -1.3977F, 0.0908F, 0F, -1.7503F));
        PartDefinition p_m6a = p_head.addOrReplaceChild("m6a", CubeListBuilder.create()
                
.texOffs(0, 53).addBox(-1.0986F, -2.8832F, -1.0986F, 2.1973F, 2.8832F, 2.1973F), PartPose.offsetAndRotation(-1.4F, -8.2F, -2.4249F, 0.4933F, 0F, -0.4556F));
        PartDefinition p_m6b = p_head.addOrReplaceChild("m6b", CubeListBuilder.create()
                
.texOffs(227, 59).addBox(-0.8789F, -2.2425F, -0.8789F, 1.7578F, 2.2425F, 1.7578F), PartPose.offsetAndRotation(-2.5174F, -10.4804F, -3.7902F, 0.7328F, 0F, -0.8279F));
        PartDefinition p_m6c = p_head.addOrReplaceChild("m6c", CubeListBuilder.create()
                
.texOffs(243, 70).addBox(-0.6592F, -1.9222F, -0.6592F, 1.3184F, 1.9222F, 1.3184F), PartPose.offsetAndRotation(-3.7451F, -11.6079F, -5.2903F, 0.8773F, 0F, -1.7465F));
        PartDefinition p_m7a = p_head.addOrReplaceChild("m7a", CubeListBuilder.create()
                
.texOffs(9, 53).addBox(-1.2296F, -2.9841F, -1.2296F, 2.4592F, 2.9841F, 2.4592F), PartPose.offsetAndRotation(0.4862F, -8.2F, -2.7575F, 0.6141F, 0F, 0.1686F));
        PartDefinition p_m7b = p_head.addOrReplaceChild("m7b", CubeListBuilder.create()
                
.texOffs(235, 59).addBox(-0.9837F, -2.3209F, -0.9837F, 1.9673F, 2.3209F, 1.9673F), PartPose.offsetAndRotation(0.8954F, -10.6044F, -4.4769F, 0.9786F, 0F, 0.3615F));
        PartDefinition p_m7c = p_head.addOrReplaceChild("m7c", CubeListBuilder.create()
                
.texOffs(249, 70).addBox(-0.7377F, -1.9894F, -0.7377F, 1.4755F, 1.9894F, 1.4755F), PartPose.offsetAndRotation(1.3536F, -11.8162F, -6.4026F, 1.3204F, 0F, 1.9465F));
        PartDefinition p_m8a = p_head.addOrReplaceChild("m8a", CubeListBuilder.create()
                
.texOffs(243, 59).addBox(-1.0867F, -2.2677F, -1.0867F, 2.1734F, 2.2677F, 2.1734F), PartPose.offsetAndRotation(2.1449F, -8.2F, -1.7998F, 0.295F, 0F, 0.644F));
        PartDefinition p_m8b = p_head.addOrReplaceChild("m8b", CubeListBuilder.create()
                
.texOffs(0, 75).addBox(-0.8694F, -1.7638F, -0.8694F, 1.7387F, 1.7638F, 1.7387F), PartPose.offsetAndRotation(3.4476F, -9.9352F, -2.4591F, 0.4095F, 0F, 1.0308F));
        PartDefinition p_m8c = p_head.addOrReplaceChild("m8c", CubeListBuilder.create()
                
.texOffs(136, 79).addBox(-0.652F, -1.5118F, -0.652F, 1.304F, 1.5118F, 1.304F), PartPose.offsetAndRotation(4.8353F, -10.767F, -3.1614F, 0.4632F, 0F, 1.7325F));
        PartDefinition p_m9a = p_head.addOrReplaceChild("m9a", CubeListBuilder.create()
                
.texOffs(150, 38).addBox(-1.4124F, -3.9046F, -1.4124F, 2.8247F, 3.9046F, 2.8247F), PartPose.offsetAndRotation(-3.8F, -7.6F, 3.9F, -1.134F, 0F, -1.3549F));
        PartDefinition p_m9b = p_head.addOrReplaceChild("m9b", CubeListBuilder.create()
                
.texOffs(19, 53).addBox(-1.1299F, -3.0369F, -1.1299F, 2.2598F, 3.0369F, 2.2598F), PartPose.offsetAndRotation(-5.4134F, -7.9538F, 7.4381F, -1.001F, 0F, -2.3496F));
        PartDefinition p_m9c = p_head.addOrReplaceChild("m9c", CubeListBuilder.create()
                
.texOffs(0, 65).addBox(-0.8474F, -2.6031F, -0.8474F, 1.6948F, 2.6031F, 1.6948F), PartPose.offsetAndRotation(-6.5794F, -6.8031F, 9.9952F, -0.6752F, 0F, -2.7678F));
        PartDefinition p_m10a = p_head.addOrReplaceChild("m10a", CubeListBuilder.create()
                
.texOffs(80, 29).addBox(-1.0718F, -5.2175F, -1.0718F, 2.1436F, 5.2175F, 2.1436F), PartPose.offsetAndRotation(-2.71F, -7.6F, 3.9F, -1.2429F, 0F, -1.2725F));
        PartDefinition p_m10b = p_head.addOrReplaceChild("m10b", CubeListBuilder.create()
                
.texOffs(29, 53).addBox(-0.8575F, -4.058F, -0.8575F, 1.7149F, 4.058F, 1.7149F), PartPose.offsetAndRotation(-4.3163F, -8.0939F, 8.8394F, -1.064F, 0F, -2.5158F));
        PartDefinition p_m10c = p_head.addOrReplaceChild("m10c", CubeListBuilder.create()
                
.texOffs(7, 65).addBox(-0.6431F, -3.4783F, -0.6431F, 1.2862F, 3.4783F, 1.2862F), PartPose.offsetAndRotation(-5.4701F, -6.4974F, 12.3873F, -0.7022F, 0F, -2.8628F));
        PartDefinition p_m11a = p_head.addOrReplaceChild("m11a", CubeListBuilder.create()
                
.texOffs(89, 29).addBox(-1.0624F, -5.3023F, -1.0624F, 2.1249F, 5.3023F, 2.1249F), PartPose.offsetAndRotation(-1.62F, -7.6F, 3.9F, -1.3556F, 0F, -1.0957F));
        PartDefinition p_m11b = p_head.addOrReplaceChild("m11b", CubeListBuilder.create()
                
.texOffs(36, 53).addBox(-0.85F, -4.124F, -0.85F, 1.6999F, 4.124F, 1.6999F), PartPose.offsetAndRotation(-2.627F, -8.118F, 9.08F, -1.115F, 0F, -2.7338F));
        PartDefinition p_m11c = p_head.addOrReplaceChild("m11c", CubeListBuilder.create()
                
.texOffs(13, 65).addBox(-0.6375F, -3.5349F, -0.6375F, 1.2749F, 3.5349F, 1.2749F), PartPose.offsetAndRotation(-3.3469F, -6.4516F, 12.783F, -0.7223F, 0F, -2.9695F));
        PartDefinition p_m12a = p_head.addOrReplaceChild("m12a", CubeListBuilder.create()
                
.texOffs(162, 38).addBox(-1.057F, -4.8171F, -1.057F, 2.114F, 4.8171F, 2.114F), PartPose.offsetAndRotation(-0.53F, -7.6F, 3.9F, -1.4528F, 0F, -0.5665F));
        PartDefinition p_m12b = p_head.addOrReplaceChild("m12b", CubeListBuilder.create()
                
.texOffs(43, 53).addBox(-0.8456F, -3.7467F, -0.8456F, 1.6912F, 3.7467F, 1.6912F), PartPose.offsetAndRotation(-0.8342F, -8.0784F, 8.6836F, -1.1442F, 0F, -3.0012F));
        PartDefinition p_m12c = p_head.addOrReplaceChild("m12c", CubeListBuilder.create()
                
.texOffs(19, 65).addBox(-0.6342F, -3.2114F, -0.6342F, 1.2684F, 3.2114F, 1.2684F), PartPose.offsetAndRotation(-1.0512F, -6.5435F, 12.0946F, -0.7329F, 0F, -3.0843F));
        PartDefinition p_m13a = p_head.addOrReplaceChild("m13a", CubeListBuilder.create()
                
.texOffs(50, 53).addBox(-1.3921F, -3.1548F, -1.3921F, 2.7843F, 3.1548F, 2.7843F), PartPose.offsetAndRotation(0.56F, -7.6F, 3.9F, -1.4509F, 0F, 0.5917F));
        PartDefinition p_m13b = p_head.addOrReplaceChild("m13b", CubeListBuilder.create()
                
.texOffs(25, 65).addBox(-1.1137F, -2.4537F, -1.1137F, 2.2274F, 2.4537F, 2.2274F), PartPose.offsetAndRotation(0.7705F, -7.9132F, 7.0321F, -1.1438F, 0F, 2.9934F));
        PartDefinition p_m13c = p_head.addOrReplaceChild("m13c", CubeListBuilder.create()
                
.texOffs(7, 75).addBox(-0.8353F, -2.1032F, -0.8353F, 1.6706F, 2.1032F, 1.6706F), PartPose.offsetAndRotation(0.9206F, -6.9082F, 9.2656F, -0.7328F, 0F, 3.0811F));
        PartDefinition p_m14a = p_head.addOrReplaceChild("m14a", CubeListBuilder.create()
                
.texOffs(62, 53).addBox(-1.097F, -3.7155F, -1.097F, 2.1939F, 3.7155F, 2.1939F), PartPose.offsetAndRotation(1.65F, -7.6F, 3.9F, -1.3525F, 0F, 1.1031F));
        PartDefinition p_m14b = p_head.addOrReplaceChild("m14b", CubeListBuilder.create()
                
.texOffs(34, 65).addBox(-0.8776F, -2.8899F, -0.8776F, 1.7551F, 2.8899F, 1.7551F), PartPose.offsetAndRotation(2.3682F, -7.9627F, 7.5274F, -1.1139F, 0F, 2.7271F));
        PartDefinition p_m14c = p_head.addOrReplaceChild("m14c", CubeListBuilder.create()
                
.texOffs(14, 75).addBox(-0.6582F, -2.477F, -0.6582F, 1.3164F, 2.477F, 1.3164F), PartPose.offsetAndRotation(2.8817F, -6.7957F, 10.1208F, -0.7218F, 0F, 2.9664F));
        PartDefinition p_m15a = p_head.addOrReplaceChild("m15a", CubeListBuilder.create()
                
.texOffs(236, 17).addBox(-1.3926F, -5.8025F, -1.3926F, 2.7852F, 5.8025F, 2.7852F), PartPose.offsetAndRotation(2.74F, -7.6F, 3.9F, -1.2398F, 0F, 1.2755F));
        PartDefinition p_m15b = p_head.addOrReplaceChild("m15b", CubeListBuilder.create()
                
.texOffs(171, 38).addBox(-1.1141F, -4.5131F, -1.1141F, 2.2281F, 4.5131F, 2.2281F), PartPose.offsetAndRotation(4.5443F, -8.1488F, 9.3875F, -1.0623F, 0F, 2.5106F));
        PartDefinition p_m15c = p_head.addOrReplaceChild("m15c", CubeListBuilder.create()
                
.texOffs(71, 53).addBox(-0.8356F, -3.8684F, -0.8356F, 1.6711F, 3.8684F, 1.6711F), PartPose.offsetAndRotation(5.8405F, -6.3748F, 13.3297F, -0.7016F, 0F, 2.8601F));
        PartDefinition p_m16a = p_head.addOrReplaceChild("m16a", CubeListBuilder.create()
                
.texOffs(180, 38).addBox(-1.4327F, -3.9311F, -1.4327F, 2.8653F, 3.9311F, 2.8653F), PartPose.offsetAndRotation(3.83F, -7.6F, 3.9F, -1.1311F, 0F, 1.3566F));
        PartDefinition p_m16b = p_head.addOrReplaceChild("m16b", CubeListBuilder.create()
                
.texOffs(78, 53).addBox(-1.1461F, -3.0575F, -1.1461F, 2.2923F, 3.0575F, 2.2923F), PartPose.offsetAndRotation(5.4649F, -7.9557F, 7.4573F, -0.9992F, 0F, 2.3456F));
        PartDefinition p_m16c = p_head.addOrReplaceChild("m16c", CubeListBuilder.create()
                
.texOffs(42, 65).addBox(-0.8596F, -2.6207F, -0.8596F, 1.7192F, 2.6207F, 1.7192F), PartPose.offsetAndRotation(6.6468F, -6.7985F, 10.0288F, -0.6744F, 0F, 2.7654F));
        PartDefinition p_m17a = p_head.addOrReplaceChild("m17a", CubeListBuilder.create()
                
.texOffs(192, 38).addBox(-1.305F, -4.3359F, -1.305F, 2.61F, 4.3359F, 2.61F), PartPose.offsetAndRotation(-3.8F, -5.8F, 3.9F, -1.134F, 0F, -1.3549F));
        PartDefinition p_m17b = p_head.addOrReplaceChild("m17b", CubeListBuilder.create()
                
.texOffs(88, 53).addBox(-1.044F, -3.3724F, -1.044F, 2.088F, 3.3724F, 2.088F), PartPose.offsetAndRotation(-5.5916F, -6.1929F, 7.8289F, -1.001F, 0F, -2.3496F));
        PartDefinition p_m17c = p_head.addOrReplaceChild("m17c", CubeListBuilder.create()
                
.texOffs(49, 65).addBox(-0.783F, -2.8906F, -0.783F, 1.566F, 2.8906F, 1.566F), PartPose.offsetAndRotation(-6.8864F, -4.9151F, 10.6685F, -0.6752F, 0F, -2.7678F));
        PartDefinition p_m18a = p_head.addOrReplaceChild("m18a", CubeListBuilder.create()
                
.texOffs(203, 38).addBox(-1.4234F, -3.4329F, -1.4234F, 2.8469F, 3.4329F, 2.8469F), PartPose.offsetAndRotation(-2.71F, -5.8F, 3.9F, -1.2429F, 0F, -1.2725F));
        PartDefinition p_m18b = p_head.addOrReplaceChild("m18b", CubeListBuilder.create()
                
.texOffs(56, 65).addBox(-1.1388F, -2.67F, -1.1388F, 2.2775F, 2.67F, 2.2775F), PartPose.offsetAndRotation(-3.7669F, -6.125F, 7.15F, -1.064F, 0F, -2.5158F));
        PartDefinition p_m18c = p_head.addOrReplaceChild("m18c", CubeListBuilder.create()
                
.texOffs(20, 75).addBox(-0.8541F, -2.2886F, -0.8541F, 1.7081F, 2.2886F, 1.7081F), PartPose.offsetAndRotation(-4.526F, -5.0745F, 9.4843F, -0.7022F, 0F, -2.8628F));
        PartDefinition p_m19a = p_head.addOrReplaceChild("m19a", CubeListBuilder.create()
                
.texOffs(98, 29).addBox(-1.435F, -4.7447F, -1.435F, 2.8699F, 4.7447F, 2.8699F), PartPose.offsetAndRotation(-1.62F, -5.8F, 3.9F, -1.3556F, 0F, -1.0957F));
        PartDefinition p_m19b = p_head.addOrReplaceChild("m19b", CubeListBuilder.create()
                
.texOffs(97, 53).addBox(-1.148F, -3.6903F, -1.148F, 2.2959F, 3.6903F, 2.2959F), PartPose.offsetAndRotation(-2.5211F, -6.2635F, 8.5353F, -1.115F, 0F, -2.7338F));
        PartDefinition p_m19c = p_head.addOrReplaceChild("m19c", CubeListBuilder.create()
                
.texOffs(66, 65).addBox(-0.861F, -3.1632F, -0.861F, 1.7219F, 3.1632F, 1.7219F), PartPose.offsetAndRotation(-3.1653F, -4.7724F, 11.8489F, -0.7223F, 0F, -2.9695F));
        PartDefinition p_m20a = p_head.addOrReplaceChild("m20a", CubeListBuilder.create()
                
.texOffs(110, 29).addBox(-1.1345F, -5.2931F, -1.1345F, 2.2689F, 5.2931F, 2.2689F), PartPose.offsetAndRotation(-0.53F, -5.8F, 3.9F, -1.4528F, 0F, -0.5665F));
        PartDefinition p_m20b = p_head.addOrReplaceChild("m20b", CubeListBuilder.create()
                
.texOffs(107, 53).addBox(-0.9076F, -4.1169F, -0.9076F, 1.8151F, 4.1169F, 1.8151F), PartPose.offsetAndRotation(-0.8643F, -6.3256F, 9.1563F, -1.1442F, 0F, -3.0012F));
        PartDefinition p_m20c = p_head.addOrReplaceChild("m20c", CubeListBuilder.create()
                
.texOffs(73, 65).addBox(-0.6807F, -3.5287F, -0.6807F, 1.3613F, 3.5287F, 1.3613F), PartPose.offsetAndRotation(-1.1027F, -4.6391F, 12.9043F, -0.7329F, 0F, -3.0843F));
        PartDefinition p_m21a = p_head.addOrReplaceChild("m21a", CubeListBuilder.create()
                
.texOffs(215, 38).addBox(-1.0747F, -3.8552F, -1.0747F, 2.1494F, 3.8552F, 2.1494F), PartPose.offsetAndRotation(0.56F, -5.8F, 3.9F, -1.4509F, 0F, 0.5917F));
        PartDefinition p_m21b = p_head.addOrReplaceChild("m21b", CubeListBuilder.create()
                
.texOffs(79, 65).addBox(-0.8597F, -2.9985F, -0.8597F, 1.7195F, 2.9985F, 1.7195F), PartPose.offsetAndRotation(0.8172F, -6.1828F, 7.7275F, -1.1438F, 0F, 2.9934F));
        PartDefinition p_m21c = p_head.addOrReplaceChild("m21c", CubeListBuilder.create()
                
.texOffs(27, 75).addBox(-0.6448F, -2.5701F, -0.6448F, 1.2896F, 2.5701F, 1.2896F), PartPose.offsetAndRotation(1.0006F, -4.9546F, 10.4568F, -0.7328F, 0F, 3.0811F));
        PartDefinition p_m22a = p_head.addOrReplaceChild("m22a", CubeListBuilder.create()
                
.texOffs(115, 53).addBox(-1.0293F, -3.2734F, -1.0293F, 2.0586F, 3.2734F, 2.0586F), PartPose.offsetAndRotation(1.65F, -5.8F, 3.9F, -1.3525F, 0F, 1.1031F));
        PartDefinition p_m22b = p_head.addOrReplaceChild("m22b", CubeListBuilder.create()
                
.texOffs(86, 65).addBox(-0.8235F, -2.546F, -0.8235F, 1.6469F, 2.546F, 1.6469F), PartPose.offsetAndRotation(2.2828F, -6.1196F, 7.0957F, -1.1139F, 0F, 2.7271F));
        PartDefinition p_m22c = p_head.addOrReplaceChild("m22c", CubeListBuilder.create()
                
.texOffs(33, 75).addBox(-0.6176F, -2.1823F, -0.6176F, 1.2352F, 2.1823F, 1.2352F), PartPose.offsetAndRotation(2.7351F, -5.0914F, 9.3805F, -0.7218F, 0F, 2.9664F));
        PartDefinition p_m23a = p_head.addOrReplaceChild("m23a", CubeListBuilder.create()
                
.texOffs(224, 38).addBox(-1.2714F, -3.6937F, -1.2714F, 2.5428F, 3.6937F, 2.5428F), PartPose.offsetAndRotation(2.74F, -5.8F, 3.9F, -1.2398F, 0F, 1.2755F));
        PartDefinition p_m23b = p_head.addOrReplaceChild("m23b", CubeListBuilder.create()
                
.texOffs(93, 65).addBox(-1.0171F, -2.8729F, -1.0171F, 2.0342F, 2.8729F, 2.0342F), PartPose.offsetAndRotation(3.8885F, -6.1493F, 7.3931F, -1.0623F, 0F, 2.5106F));
        PartDefinition p_m23c = p_head.addOrReplaceChild("m23c", CubeListBuilder.create()
                
.texOffs(38, 75).addBox(-0.7628F, -2.4624F, -0.7628F, 1.5257F, 2.4624F, 1.5257F), PartPose.offsetAndRotation(4.7136F, -5.0201F, 9.9026F, -0.7016F, 0F, 2.8601F));
        PartDefinition p_m24a = p_head.addOrReplaceChild("m24a", CubeListBuilder.create()
                
.texOffs(124, 53).addBox(-1.3051F, -2.8891F, -1.3051F, 2.6101F, 2.8891F, 2.6101F), PartPose.offsetAndRotation(3.83F, -5.8F, 3.9F, -1.1311F, 0F, 1.3566F));
        PartDefinition p_m24b = p_head.addOrReplaceChild("m24b", CubeListBuilder.create()
                
.texOffs(102, 65).addBox(-1.0441F, -2.2471F, -1.0441F, 2.0881F, 2.2471F, 2.0881F), PartPose.offsetAndRotation(5.0316F, -6.0614F, 6.5144F, -0.9992F, 0F, 2.3456F));
        PartDefinition p_m24c = p_head.addOrReplaceChild("m24c", CubeListBuilder.create()
                
.texOffs(45, 75).addBox(-0.783F, -1.9261F, -0.783F, 1.5661F, 1.9261F, 1.5661F), PartPose.offsetAndRotation(5.9002F, -5.211F, 8.4043F, -0.6744F, 0F, 2.7654F));
        PartDefinition p_m25a = p_head.addOrReplaceChild("m25a", CubeListBuilder.create()
                
.texOffs(135, 53).addBox(-1.1395F, -3.5223F, -1.1395F, 2.279F, 3.5223F, 2.279F), PartPose.offsetAndRotation(-3.8F, -4F, 3.9F, -1.134F, 0F, -1.3549F));
        PartDefinition p_m25b = p_head.addOrReplaceChild("m25b", CubeListBuilder.create()
                
.texOffs(111, 65).addBox(-0.9116F, -2.7396F, -0.9116F, 1.8232F, 2.7396F, 1.8232F), PartPose.offsetAndRotation(-5.2554F, -4.3192F, 7.0917F, -1.001F, 0F, -2.3496F));
        PartDefinition p_m25c = p_head.addOrReplaceChild("m25c", CubeListBuilder.create()
                
.texOffs(52, 75).addBox(-0.6837F, -2.3482F, -0.6837F, 1.3674F, 2.3482F, 1.3674F), PartPose.offsetAndRotation(-6.3073F, -3.2811F, 9.3984F, -0.6752F, 0F, -2.7678F));
        PartDefinition p_m26a = p_head.addOrReplaceChild("m26a", CubeListBuilder.create()
                
.texOffs(120, 29).addBox(-1.2163F, -4.82F, -1.2163F, 2.4327F, 4.82F, 2.4327F), PartPose.offsetAndRotation(-2.71F, -4F, 3.9F, -1.2429F, 0F, -1.2725F));
        PartDefinition p_m26b = p_head.addOrReplaceChild("m26b", CubeListBuilder.create()
                
.texOffs(145, 53).addBox(-0.9731F, -3.7489F, -0.9731F, 1.9461F, 3.7489F, 1.9461F), PartPose.offsetAndRotation(-4.1939F, -4.4563F, 8.4631F, -1.064F, 0F, -2.5158F));
        PartDefinition p_m26c = p_head.addOrReplaceChild("m26c", CubeListBuilder.create()
                
.texOffs(119, 65).addBox(-0.7298F, -3.2133F, -0.7298F, 1.4596F, 3.2133F, 1.4596F), PartPose.offsetAndRotation(-5.2598F, -2.9814F, 11.7407F, -0.7022F, 0F, -2.8628F));
        PartDefinition p_m27a = p_head.addOrReplaceChild("m27a", CubeListBuilder.create()
                
.texOffs(153, 53).addBox(-1.2165F, -3.4626F, -1.2165F, 2.4331F, 3.4626F, 2.4331F), PartPose.offsetAndRotation(-1.62F, -4F, 3.9F, -1.3556F, 0F, -1.0957F));
        PartDefinition p_m27b = p_head.addOrReplaceChild("m27b", CubeListBuilder.create()
                
.texOffs(125, 65).addBox(-0.9732F, -2.6932F, -0.9732F, 1.9465F, 2.6932F, 1.9465F), PartPose.offsetAndRotation(-2.2776F, -4.3383F, 7.2828F, -1.115F, 0F, -2.7338F));
        PartDefinition p_m27c = p_head.addOrReplaceChild("m27c", CubeListBuilder.create()
                
.texOffs(58, 75).addBox(-0.7299F, -2.3084F, -0.7299F, 1.4599F, 2.3084F, 1.4599F), PartPose.offsetAndRotation(-2.7477F, -3.2501F, 9.701F, -0.7223F, 0F, -2.9695F));
        PartDefinition p_m28a = p_head.addOrReplaceChild("m28a", CubeListBuilder.create()
                
.texOffs(235, 38).addBox(-1.0257F, -4.5126F, -1.0257F, 2.0513F, 4.5126F, 2.0513F), PartPose.offsetAndRotation(-0.53F, -4F, 3.9F, -1.4528F, 0F, -0.5665F));
        PartDefinition p_m28b = p_head.addOrReplaceChild("m28b", CubeListBuilder.create()
                
.texOffs(163, 53).addBox(-0.8205F, -3.5098F, -0.8205F, 1.641F, 3.5098F, 1.641F), PartPose.offsetAndRotation(-0.815F, -4.4481F, 8.3812F, -1.1442F, 0F, -3.0012F));
        PartDefinition p_m28c = p_head.addOrReplaceChild("m28c", CubeListBuilder.create()
                
.texOffs(133, 65).addBox(-0.6154F, -3.0084F, -0.6154F, 1.2308F, 3.0084F, 1.2308F), PartPose.offsetAndRotation(-1.0182F, -3.0102F, 11.5765F, -0.7329F, 0F, -3.0843F));
        PartDefinition p_m29a = p_head.addOrReplaceChild("m29a", CubeListBuilder.create()
                
.texOffs(130, 29).addBox(-1.0103F, -5.2428F, -1.0103F, 2.0206F, 5.2428F, 2.0206F), PartPose.offsetAndRotation(0.56F, -4F, 3.9F, -1.4509F, 0F, 0.5917F));
        PartDefinition p_m29b = p_head.addOrReplaceChild("m29b", CubeListBuilder.create()
                
.texOffs(170, 53).addBox(-0.8082F, -4.0777F, -0.8082F, 1.6165F, 4.0777F, 1.6165F), PartPose.offsetAndRotation(0.9098F, -4.5205F, 9.1051F, -1.1438F, 0F, 2.9934F));
        PartDefinition p_m29c = p_head.addOrReplaceChild("m29c", CubeListBuilder.create()
                
.texOffs(138, 65).addBox(-0.6062F, -3.4952F, -0.6062F, 1.2123F, 3.4952F, 1.2123F), PartPose.offsetAndRotation(1.1592F, -2.8503F, 12.8167F, -0.7328F, 0F, 3.0811F));
        PartDefinition p_m30a = p_head.addOrReplaceChild("m30a", CubeListBuilder.create()
                
.texOffs(139, 29).addBox(-1.3802F, -4.6344F, -1.3802F, 2.7604F, 4.6344F, 2.7604F), PartPose.offsetAndRotation(1.65F, -4F, 3.9F, -1.3525F, 0F, 1.1031F));
        PartDefinition p_m30b = p_head.addOrReplaceChild("m30b", CubeListBuilder.create()
                
.texOffs(177, 53).addBox(-1.1042F, -3.6046F, -1.1042F, 2.2083F, 3.6046F, 2.2083F), PartPose.offsetAndRotation(2.5458F, -4.4524F, 8.4245F, -1.1139F, 0F, 2.7271F));
        PartDefinition p_m30c = p_head.addOrReplaceChild("m30c", CubeListBuilder.create()
                
.texOffs(143, 65).addBox(-0.8281F, -3.0896F, -0.8281F, 1.6562F, 3.0896F, 1.6562F), PartPose.offsetAndRotation(3.1863F, -2.9968F, 11.6592F, -0.7218F, 0F, 2.9664F));
        PartDefinition p_m31a = p_head.addOrReplaceChild("m31a", CubeListBuilder.create()
                
.texOffs(186, 53).addBox(-1.3545F, -2.6588F, -1.3545F, 2.709F, 2.6588F, 2.709F), PartPose.offsetAndRotation(2.74F, -4F, 3.9F, -1.2398F, 0F, 1.2755F));
        PartDefinition p_m31b = p_head.addOrReplaceChild("m31b", CubeListBuilder.create()
                
.texOffs(150, 65).addBox(-1.0836F, -2.0679F, -1.0836F, 2.1672F, 2.0679F, 2.1672F), PartPose.offsetAndRotation(3.5667F, -4.2514F, 6.4144F, -1.0623F, 0F, 2.5106F));
        PartDefinition p_m31c = p_head.addOrReplaceChild("m31c", CubeListBuilder.create()
                
.texOffs(64, 75).addBox(-0.8127F, -1.7725F, -0.8127F, 1.6254F, 1.7725F, 1.6254F), PartPose.offsetAndRotation(4.1607F, -3.4386F, 8.2208F, -0.7016F, 0F, 2.8601F));
        PartDefinition p_m32a = p_head.addOrReplaceChild("m32a", CubeListBuilder.create()
                
.texOffs(244, 38).addBox(-1.2603F, -3.5987F, -1.2603F, 2.5207F, 3.5987F, 2.5207F), PartPose.offsetAndRotation(3.83F, -4F, 3.9F, -1.1311F, 0F, 1.3566F));
        PartDefinition p_m32b = p_head.addOrReplaceChild("m32b", CubeListBuilder.create()
                
.texOffs(159, 65).addBox(-1.0083F, -2.799F, -1.0083F, 2.0165F, 2.799F, 2.0165F), PartPose.offsetAndRotation(5.3267F, -4.3256F, 7.1565F, -0.9992F, 0F, 2.3456F));
        PartDefinition p_m32c = p_head.addOrReplaceChild("m32c", CubeListBuilder.create()
                
.texOffs(71, 75).addBox(-0.7562F, -2.3991F, -0.7562F, 1.5124F, 2.3991F, 1.5124F), PartPose.offsetAndRotation(6.4086F, -3.2663F, 9.5105F, -0.6744F, 0F, 2.7654F));
        PartDefinition p_m33a = p_head.addOrReplaceChild("m33a", CubeListBuilder.create()
                
.texOffs(168, 65).addBox(-1.021F, -2.3645F, -1.021F, 2.0421F, 2.3645F, 2.0421F), PartPose.offsetAndRotation(-3.8F, -2.2F, 3.9F, -1.134F, 0F, -1.3549F));
        PartDefinition p_m33b = p_head.addOrReplaceChild("m33b", CubeListBuilder.create()
                
.texOffs(78, 75).addBox(-0.8168F, -1.8391F, -0.8168F, 1.6336F, 1.8391F, 1.6336F), PartPose.offsetAndRotation(-4.777F, -2.4143F, 6.0425F, -1.001F, 0F, -2.3496F));
        PartDefinition p_m33c = p_head.addOrReplaceChild("m33c", CubeListBuilder.create()
                
.texOffs(142, 79).addBox(-0.6126F, -1.5763F, -0.6126F, 1.2252F, 1.5763F, 1.2252F), PartPose.offsetAndRotation(-5.4831F, -1.7174F, 7.5911F, -0.6752F, 0F, -2.7678F));
        PartDefinition p_m34a = p_head.addOrReplaceChild("m34a", CubeListBuilder.create()
                
.texOffs(197, 53).addBox(-1.4298F, -2.8285F, -1.4298F, 2.8597F, 2.8285F, 2.8597F), PartPose.offsetAndRotation(-2.71F, -2.2F, 3.9F, -1.2429F, 0F, -1.2725F));
        PartDefinition p_m34b = p_head.addOrReplaceChild("m34b", CubeListBuilder.create()
                
.texOffs(177, 65).addBox(-1.1439F, -2.1999F, -1.1439F, 2.2877F, 2.1999F, 2.2877F), PartPose.offsetAndRotation(-3.5808F, -2.4678F, 6.5777F, -1.064F, 0F, -2.5158F));
        PartDefinition p_m34c = p_head.addOrReplaceChild("m34c", CubeListBuilder.create()
                
.texOffs(85, 75).addBox(-0.8579F, -1.8857F, -0.8579F, 1.7158F, 1.8857F, 1.7158F), PartPose.offsetAndRotation(-4.2063F, -1.6023F, 8.5011F, -0.7022F, 0F, -2.8628F));
        PartDefinition p_m35a = p_head.addOrReplaceChild("m35a", CubeListBuilder.create()
                
.texOffs(209, 53).addBox(-1.3401F, -2.8706F, -1.3401F, 2.6802F, 2.8706F, 2.6802F), PartPose.offsetAndRotation(-1.62F, -2.2F, 3.9F, -1.3556F, 0F, -1.0957F));
        PartDefinition p_m35b = p_head.addOrReplaceChild("m35b", CubeListBuilder.create()
                
.texOffs(187, 65).addBox(-1.0721F, -2.2327F, -1.0721F, 2.1441F, 2.2327F, 2.1441F), PartPose.offsetAndRotation(-2.1652F, -2.4804F, 6.7044F, -1.115F, 0F, -2.7338F));
        PartDefinition p_m35c = p_head.addOrReplaceChild("m35c", CubeListBuilder.create()
                
.texOffs(92, 75).addBox(-0.804F, -1.9137F, -0.804F, 1.6081F, 1.9137F, 1.6081F), PartPose.offsetAndRotation(-2.5549F, -1.5783F, 8.7092F, -0.7223F, 0F, -2.9695F));
        PartDefinition p_m36a = p_head.addOrReplaceChild("m36a", CubeListBuilder.create()
                
.texOffs(151, 29).addBox(-1.4239F, -4.8501F, -1.4239F, 2.8478F, 4.8501F, 2.8478F), PartPose.offsetAndRotation(-0.53F, -2.2F, 3.9F, -1.4528F, 0F, -0.5665F));
        PartDefinition p_m36b = p_head.addOrReplaceChild("m36b", CubeListBuilder.create()
                
.texOffs(0, 46).addBox(-1.1391F, -3.7723F, -1.1391F, 2.2783F, 3.7723F, 2.2783F), PartPose.offsetAndRotation(-0.8363F, -2.6816F, 8.7164F, -1.1442F, 0F, -3.0012F));
        PartDefinition p_m36c = p_head.addOrReplaceChild("m36c", CubeListBuilder.create()
                
.texOffs(196, 65).addBox(-0.8544F, -3.2334F, -0.8544F, 1.7087F, 3.2334F, 1.7087F), PartPose.offsetAndRotation(-1.0547F, -1.1362F, 12.1506F, -0.7329F, 0F, -3.0843F));
        PartDefinition p_m37a = p_head.addOrReplaceChild("m37a", CubeListBuilder.create()
                
.texOffs(220, 53).addBox(-1.1597F, -3.2698F, -1.1597F, 2.3193F, 3.2698F, 2.3193F), PartPose.offsetAndRotation(0.56F, -2.2F, 3.9F, -1.4509F, 0F, 0.5917F));
        PartDefinition p_m37b = p_head.addOrReplaceChild("m37b", CubeListBuilder.create()
                
.texOffs(203, 65).addBox(-0.9277F, -2.5432F, -0.9277F, 1.8555F, 2.5432F, 1.8555F), PartPose.offsetAndRotation(0.7782F, -2.5246F, 7.1464F, -1.1438F, 0F, 2.9934F));
        PartDefinition p_m37c = p_head.addOrReplaceChild("m37c", CubeListBuilder.create()
                
.texOffs(99, 75).addBox(-0.6958F, -2.1799F, -0.6958F, 1.3916F, 2.1799F, 1.3916F), PartPose.offsetAndRotation(0.9337F, -1.4829F, 9.4612F, -0.7328F, 0F, 3.0811F));
        PartDefinition p_m38a = p_head.addOrReplaceChild("m38a", CubeListBuilder.create()
                
.texOffs(10, 46).addBox(-1.349F, -3.7567F, -1.349F, 2.698F, 3.7567F, 2.698F), PartPose.offsetAndRotation(1.65F, -2.2F, 3.9F, -1.3525F, 0F, 1.1031F));
        PartDefinition p_m38b = p_head.addOrReplaceChild("m38b", CubeListBuilder.create()
                
.texOffs(230, 53).addBox(-1.0792F, -2.9219F, -1.0792F, 2.1584F, 2.9219F, 2.1584F), PartPose.offsetAndRotation(2.3762F, -2.5668F, 7.5675F, -1.1139F, 0F, 2.7271F));
        PartDefinition p_m38c = p_head.addOrReplaceChild("m38c", CubeListBuilder.create()
                
.texOffs(211, 65).addBox(-0.8094F, -2.5045F, -0.8094F, 1.6188F, 2.5045F, 1.6188F), PartPose.offsetAndRotation(2.8954F, -1.3868F, 10.1897F, -0.7218F, 0F, 2.9664F));
        PartDefinition p_m39a = p_head.addOrReplaceChild("m39a", CubeListBuilder.create()
                
.texOffs(239, 53).addBox(-1.3368F, -2.6317F, -1.3368F, 2.6736F, 2.6317F, 2.6736F), PartPose.offsetAndRotation(2.74F, -2.2F, 3.9F, -1.2398F, 0F, 1.2755F));
        PartDefinition p_m39b = p_head.addOrReplaceChild("m39b", CubeListBuilder.create()
                
.texOffs(218, 65).addBox(-1.0694F, -2.0469F, -1.0694F, 2.1388F, 2.0469F, 2.1388F), PartPose.offsetAndRotation(3.5583F, -2.4489F, 6.3889F, -1.0623F, 0F, 2.5106F));
        PartDefinition p_m39c = p_head.addOrReplaceChild("m39c", CubeListBuilder.create()
                
.texOffs(105, 75).addBox(-0.8021F, -1.7545F, -0.8021F, 1.6041F, 1.7545F, 1.6041F), PartPose.offsetAndRotation(4.1462F, -1.6443F, 8.1768F, -0.7016F, 0F, 2.8601F));
        PartDefinition p_m40a = p_head.addOrReplaceChild("m40a", CubeListBuilder.create()
                
.texOffs(163, 29).addBox(-1.3869F, -4.4925F, -1.3869F, 2.7737F, 4.4925F, 2.7737F), PartPose.offsetAndRotation(3.83F, -2.2F, 3.9F, -1.1311F, 0F, 1.3566F));
        PartDefinition p_m40b = p_head.addOrReplaceChild("m40b", CubeListBuilder.create()
                
.texOffs(0, 59).addBox(-1.1095F, -3.4942F, -1.1095F, 2.219F, 3.4942F, 2.219F), PartPose.offsetAndRotation(5.6984F, -2.6065F, 7.9653F, -0.9992F, 0F, 2.3456F));
        PartDefinition p_m40c = p_head.addOrReplaceChild("m40c", CubeListBuilder.create()
                
.texOffs(227, 65).addBox(-0.8321F, -2.995F, -0.8321F, 1.6642F, 2.995F, 1.6642F), PartPose.offsetAndRotation(7.049F, -1.2841F, 10.904F, -0.6744F, 0F, 2.7654F));
        PartDefinition p_m41a = p_head.addOrReplaceChild("m41a", CubeListBuilder.create()
                
.texOffs(234, 65).addBox(-1.331F, -2.3159F, -1.331F, 2.6621F, 2.3159F, 2.6621F), PartPose.offsetAndRotation(4F, -7.4F, -2F, -0.4902F, 0F, 1.6598F));
        PartDefinition p_m41b = p_head.addOrReplaceChild("m41b", CubeListBuilder.create()
                
.texOffs(112, 75).addBox(-1.0648F, -1.8013F, -1.0648F, 2.1296F, 1.8013F, 2.1296F), PartPose.offsetAndRotation(6.0352F, -7.2183F, -0.9097F, -0.4339F, 0F, 2.0966F));
        PartDefinition p_m41c = p_head.addOrReplaceChild("m41c", CubeListBuilder.create()
                
.texOffs(121, 75).addBox(-0.7986F, -1.544F, -0.7986F, 1.5972F, 1.544F, 1.5972F), PartPose.offsetAndRotation(7.4487F, -6.3979F, -0.1525F, -0.3094F, 0F, 2.5022F));
        PartDefinition p_m42a = p_head.addOrReplaceChild("m42a", CubeListBuilder.create()
                
.texOffs(245, 65).addBox(-1.1193F, -2.4141F, -1.1193F, 2.2385F, 2.4141F, 2.2385F), PartPose.offsetAndRotation(4F, -7.4F, 0.5F, -0.4902F, 0F, 1.6598F));
        PartDefinition p_m42b = p_head.addOrReplaceChild("m42b", CubeListBuilder.create()
                
.texOffs(128, 75).addBox(-0.8954F, -1.8777F, -0.8954F, 1.7908F, 1.8777F, 1.7908F), PartPose.offsetAndRotation(6.1214F, -7.2106F, 1.6365F, -0.4339F, 0F, 2.0966F));
        PartDefinition p_m42c = p_head.addOrReplaceChild("m42c", CubeListBuilder.create()
                
.texOffs(147, 79).addBox(-0.6716F, -1.6094F, -0.6716F, 1.3431F, 1.6094F, 1.3431F), PartPose.offsetAndRotation(7.5949F, -6.3554F, 2.4259F, -0.3094F, 0F, 2.5022F));
        PartDefinition p_m43a = p_head.addOrReplaceChild("m43a", CubeListBuilder.create()
                
.texOffs(9, 59).addBox(-1.3213F, -3.3495F, -1.3213F, 2.6427F, 3.3495F, 2.6427F), PartPose.offsetAndRotation(4F, -7.2F, 2.8F, -0.4902F, 0F, 1.6598F));
        PartDefinition p_m43b = p_head.addOrReplaceChild("m43b", CubeListBuilder.create()
                
.texOffs(0, 70).addBox(-1.0571F, -2.6052F, -1.0571F, 2.1141F, 2.6052F, 2.1141F), PartPose.offsetAndRotation(6.9434F, -6.9372F, 4.3768F, -0.4339F, 0F, 2.0966F));
        PartDefinition p_m43c = p_head.addOrReplaceChild("m43c", CubeListBuilder.create()
                
.texOffs(136, 75).addBox(-0.7928F, -2.233F, -0.7928F, 1.5856F, 2.233F, 1.5856F), PartPose.offsetAndRotation(8.9878F, -5.7507F, 5.472F, -0.3094F, 0F, 2.5022F));
        PartDefinition p_m44a = p_head.addOrReplaceChild("m44a", CubeListBuilder.create()
                
.texOffs(20, 59).addBox(-1.3235F, -2.8619F, -1.3235F, 2.6469F, 2.8619F, 2.6469F), PartPose.offsetAndRotation(4F, -5.4F, -1.5F, -0.4902F, 0F, 1.6598F));
        PartDefinition p_m44b = p_head.addOrReplaceChild("m44b", CubeListBuilder.create()
                
.texOffs(9, 70).addBox(-1.0588F, -2.2259F, -1.0588F, 2.1176F, 2.2259F, 2.1176F), PartPose.offsetAndRotation(6.515F, -5.1755F, -0.1527F, -0.4339F, 0F, 2.0966F));
        PartDefinition p_m44c = p_head.addOrReplaceChild("m44c", CubeListBuilder.create()
                
.texOffs(143, 75).addBox(-0.7941F, -1.908F, -0.7941F, 1.5882F, 1.908F, 1.5882F), PartPose.offsetAndRotation(8.2618F, -4.1617F, 0.7831F, -0.3094F, 0F, 2.5022F));
        PartDefinition p_m45a = p_head.addOrReplaceChild("m45a", CubeListBuilder.create()
                
.texOffs(31, 59).addBox(-1.1094F, -3.2313F, -1.1094F, 2.2187F, 3.2313F, 2.2187F), PartPose.offsetAndRotation(4F, -5.4F, 1.2F, -0.4902F, 0F, 1.6598F));
        PartDefinition p_m45b = p_head.addOrReplaceChild("m45b", CubeListBuilder.create()
                
.texOffs(18, 70).addBox(-0.8875F, -2.5132F, -0.8875F, 1.775F, 2.5132F, 1.775F), PartPose.offsetAndRotation(6.8395F, -5.1465F, 2.7212F, -0.4339F, 0F, 2.0966F));
        PartDefinition p_m45c = p_head.addOrReplaceChild("m45c", CubeListBuilder.create()
                
.texOffs(150, 75).addBox(-0.6656F, -2.1542F, -0.6656F, 1.3312F, 2.1542F, 1.3312F), PartPose.offsetAndRotation(8.8118F, -4.0019F, 3.7777F, -0.3094F, 0F, 2.5022F));
        PartDefinition p_m46a = p_head.addOrReplaceChild("m46a", CubeListBuilder.create()
                
.texOffs(26, 70).addBox(-1.0621F, -2.8202F, -1.0621F, 2.1242F, 2.8202F, 2.1242F), PartPose.offsetAndRotation(4F, -3.6F, 0.5F, -0.4902F, 0F, 1.6598F));
        PartDefinition p_m46b = p_head.addOrReplaceChild("m46b", CubeListBuilder.create()
                
.texOffs(156, 75).addBox(-0.8497F, -2.1935F, -0.8497F, 1.6994F, 2.1935F, 1.6994F), PartPose.offsetAndRotation(6.4783F, -3.3787F, 1.8277F, -0.4339F, 0F, 2.0966F));
        PartDefinition p_m46c = p_head.addOrReplaceChild("m46c", CubeListBuilder.create()
                
.texOffs(163, 75).addBox(-0.6373F, -1.8802F, -0.6373F, 1.2745F, 1.8802F, 1.2745F), PartPose.offsetAndRotation(8.1997F, -2.3797F, 2.7498F, -0.3094F, 0F, 2.5022F));
        PartDefinition p_m47a = p_head.addOrReplaceChild("m47a", CubeListBuilder.create()
                
.texOffs(35, 70).addBox(-1.0521F, -2.3908F, -1.0521F, 2.1042F, 2.3908F, 2.1042F), PartPose.offsetAndRotation(4F, -3.4F, -2F, -0.4902F, 0F, 1.6598F));
        PartDefinition p_m47b = p_head.addOrReplaceChild("m47b", CubeListBuilder.create()
                
.texOffs(169, 75).addBox(-0.8417F, -1.8595F, -0.8417F, 1.6834F, 1.8595F, 1.6834F), PartPose.offsetAndRotation(6.1009F, -3.2124F, -0.8745F, -0.4339F, 0F, 2.0966F));
        PartDefinition p_m47c = p_head.addOrReplaceChild("m47c", CubeListBuilder.create()
                
.texOffs(153, 79).addBox(-0.6313F, -1.5938F, -0.6313F, 1.2625F, 1.5938F, 1.2625F), PartPose.offsetAndRotation(7.5601F, -2.3655F, -0.0928F, -0.3094F, 0F, 2.5022F));
        PartDefinition p_m48a = p_head.addOrReplaceChild("m48a", CubeListBuilder.create()
                
.texOffs(21, 46).addBox(-1.3489F, -3.4905F, -1.3489F, 2.6977F, 3.4905F, 2.6977F), PartPose.offsetAndRotation(-4F, -7.4F, -2F, -0.4902F, 0F, -1.6598F));
        PartDefinition p_m48b = p_head.addOrReplaceChild("m48b", CubeListBuilder.create()
                
.texOffs(44, 70).addBox(-1.0791F, -2.7148F, -1.0791F, 2.1582F, 2.7148F, 2.1582F), PartPose.offsetAndRotation(-7.0673F, -7.1261F, -0.3568F, -0.4339F, 0F, -2.0966F));
        PartDefinition p_m48c = p_head.addOrReplaceChild("m48c", CubeListBuilder.create()
                
.texOffs(176, 75).addBox(-0.8093F, -2.327F, -0.8093F, 1.6186F, 2.327F, 1.6186F), PartPose.offsetAndRotation(-9.1978F, -5.8897F, 0.7845F, -0.3094F, 0F, -2.5022F));
        PartDefinition p_m49a = p_head.addOrReplaceChild("m49a", CubeListBuilder.create()
                
.texOffs(53, 70).addBox(-1.017F, -2.5408F, -1.017F, 2.034F, 2.5408F, 2.034F), PartPose.offsetAndRotation(-4F, -7.4F, 0.5F, -0.4902F, 0F, -1.6598F));
        PartDefinition p_m49b = p_head.addOrReplaceChild("m49b", CubeListBuilder.create()
                
.texOffs(183, 75).addBox(-0.8136F, -1.9761F, -0.8136F, 1.6272F, 1.9761F, 1.6272F), PartPose.offsetAndRotation(-6.2327F, -7.2007F, 1.6961F, -0.4339F, 0F, -2.0966F));
        PartDefinition p_m49c = p_head.addOrReplaceChild("m49c", CubeListBuilder.create()
                
.texOffs(159, 79).addBox(-0.6102F, -1.6938F, -0.6102F, 1.2204F, 1.6938F, 1.2204F), PartPose.offsetAndRotation(-7.7835F, -6.3006F, 2.5269F, -0.3094F, 0F, -2.5022F));
        PartDefinition p_m50a = p_head.addOrReplaceChild("m50a", CubeListBuilder.create()
                
.texOffs(32, 46).addBox(-1.1867F, -4.0261F, -1.1867F, 2.3735F, 4.0261F, 2.3735F), PartPose.offsetAndRotation(-4F, -7.2F, 2.8F, -0.4902F, 0F, -1.6598F));
        PartDefinition p_m50b = p_head.addOrReplaceChild("m50b", CubeListBuilder.create()
                
.texOffs(40, 59).addBox(-0.9494F, -3.1314F, -0.9494F, 1.8988F, 3.1314F, 1.8988F), PartPose.offsetAndRotation(-7.5379F, -6.8841F, 4.6953F, -0.4339F, 0F, -2.0966F));
        PartDefinition p_m50c = p_head.addOrReplaceChild("m50c", CubeListBuilder.create()
                
.texOffs(62, 70).addBox(-0.712F, -2.684F, -0.712F, 1.4241F, 2.684F, 1.4241F), PartPose.offsetAndRotation(-9.9953F, -5.458F, 6.0118F, -0.3094F, 0F, -2.5022F));
        PartDefinition p_m51a = p_head.addOrReplaceChild("m51a", CubeListBuilder.create()
                
.texOffs(48, 59).addBox(-1.0831F, -2.9806F, -1.0831F, 2.1661F, 2.9806F, 2.1661F), PartPose.offsetAndRotation(-4F, -5.4F, -1.5F, -0.4902F, 0F, -1.6598F));
        PartDefinition p_m51b = p_head.addOrReplaceChild("m51b", CubeListBuilder.create()
                
.texOffs(68, 70).addBox(-0.8665F, -2.3182F, -0.8665F, 1.7329F, 2.3182F, 1.7329F), PartPose.offsetAndRotation(-6.6192F, -5.1661F, -0.0968F, -0.4339F, 0F, -2.0966F));
        PartDefinition p_m51c = p_head.addOrReplaceChild("m51c", CubeListBuilder.create()
                
.texOffs(190, 75).addBox(-0.6498F, -1.9871F, -0.6498F, 1.2997F, 1.9871F, 1.2997F), PartPose.offsetAndRotation(-8.4385F, -4.1103F, 0.8778F, -0.3094F, 0F, -2.5022F));
        PartDefinition p_m52a = p_head.addOrReplaceChild("m52a", CubeListBuilder.create()
                
.texOffs(57, 59).addBox(-1.2892F, -3.3191F, -1.2892F, 2.5784F, 3.3191F, 2.5784F), PartPose.offsetAndRotation(-4F, -5.4F, 1.2F, -0.4902F, 0F, -1.6598F));
        PartDefinition p_m52b = p_head.addOrReplaceChild("m52b", CubeListBuilder.create()
                
.texOffs(75, 70).addBox(-1.0314F, -2.5815F, -1.0314F, 2.0627F, 2.5815F, 2.0627F), PartPose.offsetAndRotation(-6.9167F, -5.1396F, 2.7625F, -0.4339F, 0F, -2.0966F));
        PartDefinition p_m52c = p_head.addOrReplaceChild("m52c", CubeListBuilder.create()
                
.texOffs(196, 75).addBox(-0.7735F, -2.2128F, -0.7735F, 1.547F, 2.2128F, 1.547F), PartPose.offsetAndRotation(-8.9426F, -3.9638F, 3.8478F, -0.3094F, 0F, -2.5022F));
        PartDefinition p_m53a = p_head.addOrReplaceChild("m53a", CubeListBuilder.create()
                
.texOffs(68, 59).addBox(-1.1476F, -3.0702F, -1.1476F, 2.2952F, 3.0702F, 2.2952F), PartPose.offsetAndRotation(-4F, -3.6F, 0.5F, -0.4902F, 0F, -1.6598F));
        PartDefinition p_m53b = p_head.addOrReplaceChild("m53b", CubeListBuilder.create()
                
.texOffs(84, 70).addBox(-0.9181F, -2.3879F, -0.9181F, 1.8362F, 2.3879F, 1.8362F), PartPose.offsetAndRotation(-6.698F, -3.3591F, 1.9453F, -0.4339F, 0F, -2.0966F));
        PartDefinition p_m53c = p_head.addOrReplaceChild("m53c", CubeListBuilder.create()
                
.texOffs(203, 75).addBox(-0.6886F, -2.0468F, -0.6886F, 1.3771F, 2.0468F, 1.3771F), PartPose.offsetAndRotation(-8.5719F, -2.2716F, 2.9492F, -0.3094F, 0F, -2.5022F));
        PartDefinition p_m54a = p_head.addOrReplaceChild("m54a", CubeListBuilder.create()
                
.texOffs(92, 70).addBox(-1.3206F, -2.3503F, -1.3206F, 2.6412F, 2.3503F, 2.6412F), PartPose.offsetAndRotation(-4F, -3.4F, -2F, -0.4902F, 0F, -1.6598F));
        PartDefinition p_m54b = p_head.addOrReplaceChild("m54b", CubeListBuilder.create()
                
.texOffs(209, 75).addBox(-1.0565F, -1.828F, -1.0565F, 2.113F, 1.828F, 2.113F), PartPose.offsetAndRotation(-6.0653F, -3.2156F, -0.8936F, -0.4339F, 0F, -2.0966F));
        PartDefinition p_m54c = p_head.addOrReplaceChild("m54c", CubeListBuilder.create()
                
.texOffs(218, 75).addBox(-0.7924F, -1.5668F, -0.7924F, 1.5847F, 1.5668F, 1.5847F), PartPose.offsetAndRotation(-7.4999F, -2.3831F, -0.1251F, -0.3094F, 0F, -2.5022F));
        PartDefinition p_m55a = p_head.addOrReplaceChild("m55a", CubeListBuilder.create()
                
.texOffs(225, 75).addBox(-0.9F, -1.8294F, -0.9F, 1.8F, 1.8294F, 1.8F), PartPose.offsetAndRotation(-3F, -8F, -3.8F, -0.6662F, 0F, -0.5404F));
        PartDefinition p_m55b = p_head.addOrReplaceChild("m55b", CubeListBuilder.create()
                
.texOffs(164, 79).addBox(-0.72F, -1.4229F, -0.72F, 1.44F, 1.4229F, 1.44F), PartPose.offsetAndRotation(-3.74F, -9.2333F, -2.6694F, -0.9868F, 0F, -1.4328F));
        PartDefinition p_m55c = p_head.addOrReplaceChild("m55c", CubeListBuilder.create()
                
.texOffs(170, 79).addBox(-0.54F, -1.2196F, -0.54F, 1.08F, 1.2196F, 1.08F), PartPose.offsetAndRotation(-4.517F, -9.3413F, -1.4823F, -0.834F, 0F, -2.3357F));
        PartDefinition p_m56a = p_head.addOrReplaceChild("m56a", CubeListBuilder.create()
                
.texOffs(103, 70).addBox(-0.9F, -2.2442F, -0.9F, 1.8F, 2.2442F, 1.8F), PartPose.offsetAndRotation(-1.5F, -8F, -3.8F, -0.7205F, 0F, -0.2915F));
        PartDefinition p_m56b = p_head.addOrReplaceChild("m56b", CubeListBuilder.create()
                
.texOffs(233, 75).addBox(-0.72F, -1.7455F, -0.72F, 1.44F, 1.7455F, 1.44F), PartPose.offsetAndRotation(-1.9846F, -9.6153F, -2.3193F, -1.2434F, 0F, -1.2998F));
        PartDefinition p_m56c = p_head.addOrReplaceChild("m56c", CubeListBuilder.create()
                
.texOffs(175, 79).addBox(-0.54F, -1.4961F, -0.54F, 1.08F, 1.4961F, 1.08F), PartPose.offsetAndRotation(-2.5255F, -9.7656F, -0.6666F, -1.0059F, 0F, -2.599F));
        PartDefinition p_m57a = p_head.addOrReplaceChild("m57a", CubeListBuilder.create()
                
.texOffs(111, 70).addBox(-0.9F, -2.5546F, -0.9F, 1.8F, 2.5546F, 1.8F), PartPose.offsetAndRotation(0F, -8F, -3.8F, -0.7419F, 0F, 0F));
        PartDefinition p_m57b = p_head.addOrReplaceChild("m57b", CubeListBuilder.create()
                
.texOffs(239, 75).addBox(-0.72F, -1.9869F, -0.72F, 1.44F, 1.9869F, 1.44F), PartPose.offsetAndRotation(0F, -9.8831F, -2.0738F, -1.4801F, 0F, 0F));
        PartDefinition p_m57c = p_head.addOrReplaceChild("m57c", CubeListBuilder.create()
                
.texOffs(180, 79).addBox(-0.54F, -1.7031F, -0.54F, 1.08F, 1.7031F, 1.08F), PartPose.offsetAndRotation(0F, -10.063F, -0.0951F, -1.0979F, 0F, 3.1416F));
        PartDefinition p_m58a = p_head.addOrReplaceChild("m58a", CubeListBuilder.create()
                
.texOffs(245, 75).addBox(-0.9F, -1.9175F, -0.9F, 1.8F, 1.9175F, 1.8F), PartPose.offsetAndRotation(1.5F, -8F, -3.8F, -0.7205F, 0F, 0.2915F));
        PartDefinition p_m58b = p_head.addOrReplaceChild("m58b", CubeListBuilder.create()
                
.texOffs(185, 79).addBox(-0.72F, -1.4914F, -0.72F, 1.44F, 1.4914F, 1.44F), PartPose.offsetAndRotation(1.914F, -9.3802F, -2.5349F, -1.2434F, 0F, 1.2998F));
        PartDefinition p_m58c = p_head.addOrReplaceChild("m58c", CubeListBuilder.create()
                
.texOffs(191, 79).addBox(-0.54F, -1.2783F, -0.54F, 1.08F, 1.2783F, 1.08F), PartPose.offsetAndRotation(2.3762F, -9.5085F, -1.1227F, -1.0059F, 0F, 2.599F));
        PartDefinition p_m59a = p_head.addOrReplaceChild("m59a", CubeListBuilder.create()
                
.texOffs(119, 70).addBox(-0.9F, -2.4585F, -0.9F, 1.8F, 2.4585F, 1.8F), PartPose.offsetAndRotation(3F, -8F, -3.8F, -0.6662F, 0F, 0.5404F));
        PartDefinition p_m59b = p_head.addOrReplaceChild("m59b", CubeListBuilder.create()
                
.texOffs(0, 79).addBox(-0.72F, -1.9122F, -0.72F, 1.44F, 1.9122F, 1.44F), PartPose.offsetAndRotation(3.9944F, -9.6574F, -2.2807F, -0.9868F, 0F, 1.4328F));
        PartDefinition p_m59c = p_head.addOrReplaceChild("m59c", CubeListBuilder.create()
                
.texOffs(196, 79).addBox(-0.54F, -1.639F, -0.54F, 1.08F, 1.639F, 1.08F), PartPose.offsetAndRotation(5.0386F, -9.8024F, -0.6854F, -0.834F, 0F, 2.3357F));
        PartDefinition p_pauld_l0 = root.addOrReplaceChild("pauld_l0", CubeListBuilder.create()
                
.texOffs(42, 46).addBox(-2.5F, -2.4F, -2.8F, 5F, 1.3F, 5.6F), PartPose.offsetAndRotation(5.6F, 0.6F, 0F, 0F, 0F, 0.3F));
        PartDefinition p_pauld_l1 = root.addOrReplaceChild("pauld_l1", CubeListBuilder.create()
                
.texOffs(175, 29).addBox(-2.9F, -1.2F, -3F, 5.8F, 1.3F, 6F), PartPose.offsetAndRotation(5.6F, 0.6F, 0F, 0F, 0F, 0.35F));
        PartDefinition p_pauld_l2 = root.addOrReplaceChild("pauld_l2", CubeListBuilder.create()
                
.texOffs(199, 29).addBox(-3.3F, 0F, -3.2F, 6.6F, 1.3F, 6.4F), PartPose.offsetAndRotation(5.6F, 0.6F, 0F, 0F, 0F, 0.4F));
        PartDefinition p_pauld_r0 = root.addOrReplaceChild("pauld_r0", CubeListBuilder.create()
                
.texOffs(64, 46).addBox(-2.5F, -2.4F, -2.8F, 5F, 1.3F, 5.6F), PartPose.offsetAndRotation(-5.6F, 0.6F, 0F, 0F, 0F, -0.3F));
        PartDefinition p_pauld_r1 = root.addOrReplaceChild("pauld_r1", CubeListBuilder.create()
                
.texOffs(225, 29).addBox(-2.9F, -1.2F, -3F, 5.8F, 1.3F, 6F), PartPose.offsetAndRotation(-5.6F, 0.6F, 0F, 0F, 0F, -0.35F));
        PartDefinition p_pauld_r2 = root.addOrReplaceChild("pauld_r2", CubeListBuilder.create()
                
.texOffs(0, 38).addBox(-3.3F, 0F, -3.2F, 6.6F, 1.3F, 6.4F), PartPose.offsetAndRotation(-5.6F, 0.6F, 0F, 0F, 0F, -0.4F));
        PartDefinition p_right_arm = root.addOrReplaceChild("right_arm", CubeListBuilder.create()
                
.texOffs(117, 0).addBox(-2.5F, -2.5F, -2.5F, 5F, 8.5F, 5F), PartPose.offset(-6.5F, 2.5F, 0F));
        PartDefinition p_right_arm_sleeve = p_right_arm.addOrReplaceChild("right_arm_sleeve", CubeListBuilder.create()
                
.texOffs(0, 17).addBox(-3.2F, -1.2F, -3.2F, 6.4F, 5.5F, 6.4F), PartPose.offset(0F, 0F, 0F));
        PartDefinition p_right_arm_fore = p_right_arm.addOrReplaceChild("right_arm_fore", CubeListBuilder.create()
                
.texOffs(107, 17).addBox(-2.25F, 0F, -2.25F, 4.5F, 5.5F, 4.5F)                .texOffs(125, 17).addBox(-3F, 2.8F, -3F, 6F, 3.2F, 6F), PartPose.offsetAndRotation(0F, 6F, 0F, -0.9F, 0F, 0F));
        PartDefinition p_right_arm_hand = p_right_arm_fore.addOrReplaceChild("right_arm_hand", CubeListBuilder.create()
                
.texOffs(26, 38).addBox(-2.1F, 0F, -2.1F, 4.2F, 3.4F, 4.2F)                .texOffs(78, 59).addBox(-2.3F, 0.2F, -2.3F, 4.6F, 1F, 4.6F), PartPose.offset(0F, 5.5F, 0F));
        PartDefinition p_left_arm = root.addOrReplaceChild("left_arm", CubeListBuilder.create()
                
.texOffs(137, 0).addBox(-2.5F, -2.5F, -2.5F, 5F, 8.5F, 5F), PartPose.offset(6.5F, 2.5F, 0F));
        PartDefinition p_left_arm_sleeve = p_left_arm.addOrReplaceChild("left_arm_sleeve", CubeListBuilder.create()
                
.texOffs(26, 17).addBox(-3.2F, -1.2F, -3.2F, 6.4F, 5.5F, 6.4F), PartPose.offset(0F, 0F, 0F));
        PartDefinition p_left_arm_fore = p_left_arm.addOrReplaceChild("left_arm_fore", CubeListBuilder.create()
                
.texOffs(149, 17).addBox(-2.25F, 0F, -2.25F, 4.5F, 5.5F, 4.5F)                .texOffs(167, 17).addBox(-3F, 2.8F, -3F, 6F, 3.2F, 6F), PartPose.offsetAndRotation(0F, 6F, 0F, -0.45F, 0F, 0F));
        PartDefinition p_left_arm_hand = p_left_arm_fore.addOrReplaceChild("left_arm_hand", CubeListBuilder.create()
                
.texOffs(43, 38).addBox(-2.1F, 0F, -2.1F, 4.2F, 3.4F, 4.2F)                .texOffs(97, 59).addBox(-2.3F, 0.2F, -2.3F, 4.6F, 1F, 4.6F), PartPose.offset(0F, 5.5F, 0F));
        PartDefinition p_club = p_right_arm_hand.addOrReplaceChild("club", CubeListBuilder.create()
                
.texOffs(86, 46).addBox(-0.8F, -3F, -0.8F, 1.6F, 5F, 1.6F)                .texOffs(6, 79).addBox(-1.2F, 0.4F, -1.2F, 2.4F, 0.9F, 2.4F)                .texOffs(28, 0).addBox(-2.1F, -15F, -2.1F, 4.2F, 12F, 4.2F)                .texOffs(93, 46).addBox(-2.5F, -15.6F, -2.5F, 5F, 1.3F, 5F)                .texOffs(113, 46).addBox(-2.5F, -3.9F, -2.5F, 5F, 1.3F, 5F)                .texOffs(201, 79).addBox(-3F, -13.5F, -0.55F, 1.1F, 1.1F, 1.1F)                .texOffs(52, 83).addBox(-3.23F, -13.2F, -0.28F, 0.56F, 0.56F, 0.56F)                .texOffs(206, 79).addBox(1.9F, -13.5F, -0.55F, 1.1F, 1.1F, 1.1F)                .texOffs(55, 83).addBox(2.67F, -13.2F, -0.28F, 0.56F, 0.56F, 0.56F)                .texOffs(211, 79).addBox(-0.55F, -13.5F, -3F, 1.1F, 1.1F, 1.1F)                .texOffs(58, 83).addBox(-0.28F, -13.2F, -3.23F, 0.56F, 0.56F, 0.56F)                .texOffs(216, 79).addBox(-0.55F, -13.5F, 1.9F, 1.1F, 1.1F, 1.1F)                .texOffs(61, 83).addBox(-0.28F, -13.2F, 2.67F, 0.56F, 0.56F, 0.56F)                .texOffs(221, 79).addBox(-3F, -10.7F, -0.55F, 1.1F, 1.1F, 1.1F)                .texOffs(64, 83).addBox(-3.23F, -10.4F, -0.28F, 0.56F, 0.56F, 0.56F)                .texOffs(226, 79).addBox(1.9F, -10.7F, -0.55F, 1.1F, 1.1F, 1.1F)                .texOffs(67, 83).addBox(2.67F, -10.4F, -0.28F, 0.56F, 0.56F, 0.56F)                .texOffs(231, 79).addBox(-0.55F, -10.7F, -3F, 1.1F, 1.1F, 1.1F)                .texOffs(70, 83).addBox(-0.28F, -10.4F, -3.23F, 0.56F, 0.56F, 0.56F)                .texOffs(236, 79).addBox(-0.55F, -10.7F, 1.9F, 1.1F, 1.1F, 1.1F)                .texOffs(73, 83).addBox(-0.28F, -10.4F, 2.67F, 0.56F, 0.56F, 0.56F)                .texOffs(241, 79).addBox(-3F, -7.9F, -0.55F, 1.1F, 1.1F, 1.1F)                .texOffs(76, 83).addBox(-3.23F, -7.6F, -0.28F, 0.56F, 0.56F, 0.56F)                .texOffs(246, 79).addBox(1.9F, -7.9F, -0.55F, 1.1F, 1.1F, 1.1F)                .texOffs(79, 83).addBox(2.67F, -7.6F, -0.28F, 0.56F, 0.56F, 0.56F)                .texOffs(251, 79).addBox(-0.55F, -7.9F, -3F, 1.1F, 1.1F, 1.1F)                .texOffs(82, 83).addBox(-0.28F, -7.6F, -3.23F, 0.56F, 0.56F, 0.56F)                .texOffs(0, 83).addBox(-0.55F, -7.9F, 1.9F, 1.1F, 1.1F, 1.1F)                .texOffs(85, 83).addBox(-0.28F, -7.6F, 2.67F, 0.56F, 0.56F, 0.56F)                .texOffs(5, 83).addBox(-3F, -5.1F, -0.55F, 1.1F, 1.1F, 1.1F)                .texOffs(88, 83).addBox(-3.23F, -4.8F, -0.28F, 0.56F, 0.56F, 0.56F)                .texOffs(10, 83).addBox(1.9F, -5.1F, -0.55F, 1.1F, 1.1F, 1.1F)                .texOffs(91, 83).addBox(2.67F, -4.8F, -0.28F, 0.56F, 0.56F, 0.56F)                .texOffs(15, 83).addBox(-0.55F, -5.1F, -3F, 1.1F, 1.1F, 1.1F)                .texOffs(94, 83).addBox(-0.28F, -4.8F, -3.23F, 0.56F, 0.56F, 0.56F)                .texOffs(20, 83).addBox(-0.55F, -5.1F, 1.9F, 1.1F, 1.1F, 1.1F)                .texOffs(97, 83).addBox(-0.28F, -4.8F, 2.67F, 0.56F, 0.56F, 0.56F), PartPose.offsetAndRotation(0F, 1.8F, -1F, 3.4F, 0F, 0F));
        PartDefinition p_right_leg = root.addOrReplaceChild("right_leg", CubeListBuilder.create()
                
.texOffs(77, 0).addBox(-2.5F, 0F, -2.5F, 5F, 9.5F, 5F)                .texOffs(157, 0).addBox(-3.2F, -0.4F, -3.2F, 6.4F, 6.5F, 6.4F), PartPose.offset(-2.5F, 12F, 0F));
        PartDefinition p_right_leg_hem0 = p_right_leg.addOrReplaceChild("right_leg_hem0", CubeListBuilder.create()
                
.texOffs(16, 79).addBox(-1.2F, 0F, -0.5F, 2.4F, 2.8F, 1F), PartPose.offsetAndRotation(-2.4F, 6F, -3.2F, 0.1F, 0F, 0F));
        PartDefinition p_right_leg_hem1 = p_right_leg.addOrReplaceChild("right_leg_hem1", CubeListBuilder.create()
                
.texOffs(127, 70).addBox(-1.2F, 0F, -0.5F, 2.4F, 3.8F, 1F), PartPose.offsetAndRotation(0F, 6F, 3F, -0.1F, 0F, 0F));
        PartDefinition p_right_leg_hem2 = p_right_leg.addOrReplaceChild("right_leg_hem2", CubeListBuilder.create()
                
.texOffs(23, 79).addBox(-1.2F, 0F, -0.5F, 2.4F, 2.8F, 1F), PartPose.offsetAndRotation(2.4F, 6F, -3.2F, 0.1F, 0F, 0F));
        PartDefinition p_right_leg_boot = p_right_leg.addOrReplaceChild("right_leg_boot", CubeListBuilder.create()
                
.texOffs(0, 29).addBox(-2.8F, 0F, -3.4F, 5.6F, 2.5F, 6.4F)                .texOffs(60, 38).addBox(-2.9F, 1.9F, -3.6F, 5.8F, 0.7F, 6.8F), PartPose.offset(0F, 9.5F, 0F));
        PartDefinition p_left_leg = root.addOrReplaceChild("left_leg", CubeListBuilder.create()
                
.texOffs(97, 0).addBox(-2.5F, 0F, -2.5F, 5F, 9.5F, 5F)                .texOffs(183, 0).addBox(-3.2F, -0.4F, -3.2F, 6.4F, 6.5F, 6.4F), PartPose.offset(2.5F, 12F, 0F));
        PartDefinition p_left_leg_hem0 = p_left_leg.addOrReplaceChild("left_leg_hem0", CubeListBuilder.create()
                
.texOffs(30, 79).addBox(-1.2F, 0F, -0.5F, 2.4F, 2.8F, 1F), PartPose.offsetAndRotation(-2.4F, 6F, -3.2F, 0.1F, 0F, 0F));
        PartDefinition p_left_leg_hem1 = p_left_leg.addOrReplaceChild("left_leg_hem1", CubeListBuilder.create()
                
.texOffs(134, 70).addBox(-1.2F, 0F, -0.5F, 2.4F, 3.8F, 1F), PartPose.offsetAndRotation(0F, 6F, 3F, -0.1F, 0F, 0F));
        PartDefinition p_left_leg_hem2 = p_left_leg.addOrReplaceChild("left_leg_hem2", CubeListBuilder.create()
                
.texOffs(37, 79).addBox(-1.2F, 0F, -0.5F, 2.4F, 2.8F, 1F), PartPose.offsetAndRotation(2.4F, 6F, -3.2F, 0.1F, 0F, 0F));
        PartDefinition p_left_leg_boot = p_left_leg.addOrReplaceChild("left_leg_boot", CubeListBuilder.create()
                
.texOffs(24, 29).addBox(-2.8F, 0F, -3.4F, 5.6F, 2.5F, 6.4F)                .texOffs(86, 38).addBox(-2.9F, 1.9F, -3.6F, 5.8F, 0.7F, 6.8F), PartPose.offset(0F, 9.5F, 0F));
        return LayerDefinition.create(mesh, 256, 256);
    }

    @Override
    public void setupAnim(OniEntity e, float limbSwing, float limbSwingAmount, float age, float netHeadYaw, float headPitch) {
        head.yRot = netHeadYaw * Mth.DEG_TO_RAD;
        head.xRot = headPitch * Mth.DEG_TO_RAD;
        float s = 0.6662F;
        rightLeg.xRot = Mth.cos(limbSwing * s) * 1.2F * limbSwingAmount;
        leftLeg.xRot = Mth.cos(limbSwing * s + Mth.PI) * 1.2F * limbSwingAmount;
        rightArm.xRot = Mth.cos(limbSwing * s + Mth.PI) * 0.6F * limbSwingAmount - 0.3F;
        leftArm.xRot = Mth.cos(limbSwing * s) * 0.9F * limbSwingAmount;
        rightArm.zRot = 0.05F; leftArm.zRot = -0.05F;
        // лёгкое дыхание
        float b = Mth.cos(age * 0.09F) * 0.03F;
        rightArm.zRot += b; leftArm.zRot -= b;
        // удар кастетом: замах и опускание
        if (attackTime > 0.0F) {
            float t = attackTime;
            float swing = Mth.sin(t * Mth.PI);
            rightArm.xRot = -2.2F + Mth.sin(Mth.sqrt(t) * Mth.PI) * 2.0F;
            rightArm.yRot = -swing * 0.4F;
            head.xRot += swing * 0.2F;
        }
    }

    @Override
    public void renderToBuffer(PoseStack pose, VertexConsumer buf, int light, int overlay, int color) {
        root.render(pose, buf, light, overlay, color);
    }
}
