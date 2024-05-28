package com.weever.rotp_cm.client.render.entity.model;

import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.client.render.entity.model.stand.HumanoidStandModel;
import com.github.standobyte.jojo.client.render.entity.pose.ModelPose;
import com.github.standobyte.jojo.client.render.entity.pose.RotationAngle;
import com.github.standobyte.jojo.client.render.entity.pose.anim.PosedActionAnimation;
import com.github.standobyte.jojo.entity.stand.StandPose;

import com.weever.rotp_cm.entity.CMoonEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 4.8.1
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


public class CMoonModel extends HumanoidStandModel<CMoonEntity> {

    public CMoonModel() {
        super();

        addHumanoidBaseBoxes(null);
        texWidth = 256;
        texHeight = 256;

        final ModelRenderer arrow_horizontal3;
        final ModelRenderer arrow;
        final ModelRenderer arrow2;
        final ModelRenderer arrow_horizontal;
        final ModelRenderer arrow_horizontal2;
        final ModelRenderer arrow_horizontal4;
        final ModelRenderer arrow3;
        final ModelRenderer arrow4;
        final ModelRenderer arrow_horizontal5;
        final ModelRenderer arrow_horizontal6;
        final ModelRenderer cube_r1;
        final ModelRenderer cube_r2;
        final ModelRenderer cube_r3;
        final ModelRenderer cube_r4;
        final ModelRenderer cube_r5;
        final ModelRenderer cube_r6;
        final ModelRenderer cube_r7;
        final ModelRenderer cube_r8;
        final ModelRenderer cube_r9;
        final ModelRenderer cube_r10;

        head.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
        head.texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.1F, false);
        head.texOffs(24, 5).addBox(-1.0F, -10.0F, -4.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        head.texOffs(34, 5).addBox(-1.0F, -10.0F, 3.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        head.texOffs(71, 1).addBox(-4.35F, -4.85F, -4.15F, 3.0F, 2.0F, 1.0F, 0.0F, false);
        head.texOffs(71, 4).addBox(1.35F, -4.85F, -4.15F, 3.0F, 2.0F, 1.0F, 0.0F, false);


        torso.texOffs(4, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);
        torso.texOffs(29, 35).addBox(-4.0F, 1.0F, -2.5F, 8.0F, 4.0F, 1.0F, -0.1F, false);
        torso.texOffs(3, 32).addBox(-4.0F, 0.2F, -2.5F, 8.0F, 2.0F, 5.0F, 0.1F, false);
        torso.texOffs(28, 18).addBox(-2.0F, 3.95F, -2.25F, 4.0F, 6.0F, 2.0F, -0.1F, false);
        torso.texOffs(24, 28).addBox(-4.0F, 10.0F, -2.0F, 8.0F, 2.0F, 4.0F, 0.1F, false);

        leftArm.texOffs(24, 94).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);
        leftArm.texOffs(24, 85).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.1F, false);

        leftArmJoint.texOffs(2, 104).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, -0.1F, false);

        leftForeArm.texOffs(24, 118).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);
        leftForeArm.texOffs(24, 110).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.1F, false);
        leftForeArm.texOffs(12, 87).addBox(1.25F, 4.5F, -2.0F, 1.0F, 1.0F, 1.0F, -0.1F, false);
        leftForeArm.texOffs(12, 87).addBox(1.25F, 4.5F, -1.0F, 1.0F, 1.0F, 1.0F, -0.1F, false);
        leftForeArm.texOffs(12, 87).addBox(1.25F, 4.5F, 0.0F, 1.0F, 1.0F, 1.0F, -0.1F, false);
        leftForeArm.texOffs(12, 87).addBox(1.25F, 4.5F, 1.0F, 1.0F, 1.0F, 1.0F, -0.1F, false);

        arrow_horizontal3 = new ModelRenderer(this);
        arrow_horizontal3.setPos(-0.5F, -0.5F, -1.5F);
        leftForeArm.addChild(arrow_horizontal3);
        arrow_horizontal3.texOffs(5, 82).addBox(0.0F, 2.0F, 3.0F, 1.0F, 1.0F, 2.0F, -0.1F, false);

        cube_r1 = new ModelRenderer(this);
        cube_r1.setPos(0.0F, 2.5F, 4.0F);
        arrow_horizontal3.addChild(cube_r1);
        setRotationAngle(cube_r1, -0.7854F, 0.0F, 0.0F);
        cube_r1.texOffs(6, 80).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        arrow = new ModelRenderer(this);
        arrow.setPos(-0.5F, -1.5F, 0.0F);
        leftArm.addChild(arrow);
        arrow.texOffs(0, 86).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, -0.1F, false);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setPos(0.0F, -1.0F, -0.5F);
        arrow.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.0F, 0.0F, 0.7854F);
        cube_r2.texOffs(0, 84).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        arrow2 = new ModelRenderer(this);
        arrow2.setPos(1.0F, -1.5F, 0.0F);
        leftArm.addChild(arrow2);
        setRotationAngle(arrow2, 0.0F, 0.0F, 0.3927F);
        arrow2.texOffs(0, 86).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, -0.1F, false);

        cube_r3 = new ModelRenderer(this);
        cube_r3.setPos(0.0F, -1.0F, -0.5F);
        arrow2.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.0F, 0.0F, 0.7854F);
        cube_r3.texOffs(0, 84).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        arrow_horizontal = new ModelRenderer(this);
        arrow_horizontal.setPos(-0.5F, -2.5F, -1.5F);
        leftArm.addChild(arrow_horizontal);
        arrow_horizontal.texOffs(5, 82).addBox(0.0F, 2.0F, 3.0F, 1.0F, 1.0F, 2.0F, -0.1F, false);

        cube_r4 = new ModelRenderer(this);
        cube_r4.setPos(0.0F, 2.5F, 4.0F);
        arrow_horizontal.addChild(cube_r4);
        setRotationAngle(cube_r4, -0.7854F, 0.0F, 0.0F);
        cube_r4.texOffs(6, 80).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        arrow_horizontal2 = new ModelRenderer(this);
        arrow_horizontal2.setPos(-0.5F, 0.5F, -1.5F);
        leftArm.addChild(arrow_horizontal2);
        arrow_horizontal2.texOffs(5, 82).addBox(0.0F, 2.0F, 3.0F, 1.0F, 1.0F, 2.0F, -0.1F, false);

        cube_r5 = new ModelRenderer(this);
        cube_r5.setPos(0.0F, 2.5F, 4.0F);
        arrow_horizontal2.addChild(cube_r5);
        setRotationAngle(cube_r5, -0.7854F, 0.0F, 0.0F);
        cube_r5.texOffs(6, 80).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        rightArm.texOffs(0, 94).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);
        rightArm.texOffs(0, 85).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.1F, false);

        rightArmJoint.texOffs(26, 104).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, -0.1F, false);

        rightForeArm.texOffs(0, 118).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);
        rightForeArm.texOffs(0, 110).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.1F, false);
        rightForeArm.texOffs(36, 87).addBox(-2.25F, 4.5F, -2.0F, 1.0F, 1.0F, 1.0F, -0.1F, false);
        rightForeArm.texOffs(36, 87).addBox(-2.25F, 4.5F, -1.0F, 1.0F, 1.0F, 1.0F, -0.1F, false);
        rightForeArm.texOffs(36, 87).addBox(-2.25F, 4.5F, 0.0F, 1.0F, 1.0F, 1.0F, -0.1F, false);
        rightForeArm.texOffs(36, 87).addBox(-2.25F, 4.5F, 1.0F, 1.0F, 1.0F, 1.0F, -0.1F, false);

        arrow_horizontal4 = new ModelRenderer(this);
        arrow_horizontal4.setPos(-0.5F, -0.5F, -1.5F);
        rightForeArm.addChild(arrow_horizontal4);
        arrow_horizontal4.texOffs(29, 82).addBox(0.0F, 2.0F, 3.0F, 1.0F, 1.0F, 2.0F, -0.1F, false);

        cube_r6 = new ModelRenderer(this);
        cube_r6.setPos(0.0F, 2.5F, 4.0F);
        arrow_horizontal4.addChild(cube_r6);
        setRotationAngle(cube_r6, -0.7854F, 0.0F, 0.0F);
        cube_r6.texOffs(30, 80).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        arrow3 = new ModelRenderer(this);
        arrow3.setPos(0.5F, -1.5F, 0.0F);
        rightArm.addChild(arrow3);
        arrow3.texOffs(24, 86).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, -0.1F, false);

        cube_r7 = new ModelRenderer(this);
        cube_r7.setPos(0.0F, -1.0F, -0.5F);
        arrow3.addChild(cube_r7);
        setRotationAngle(cube_r7, 0.0F, 0.0F, 0.7854F);
        cube_r7.texOffs(24, 84).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        arrow4 = new ModelRenderer(this);
        arrow4.setPos(-1.0F, -1.5F, 0.0F);
        rightArm.addChild(arrow4);
        setRotationAngle(arrow4, 0.0F, 0.0F, -0.3927F);
        arrow4.texOffs(24, 86).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, -0.1F, false);

        cube_r8 = new ModelRenderer(this);
        cube_r8.setPos(0.0F, -1.0F, -0.5F);
        arrow4.addChild(cube_r8);
        setRotationAngle(cube_r8, 0.0F, 0.0F, 0.7854F);
        cube_r8.texOffs(24, 84).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        arrow_horizontal5 = new ModelRenderer(this);
        arrow_horizontal5.setPos(-0.5F, 0.5F, -1.5F);
        rightArm.addChild(arrow_horizontal5);
        arrow_horizontal5.texOffs(29, 82).addBox(0.0F, 2.0F, 3.0F, 1.0F, 1.0F, 2.0F, -0.1F, false);

        cube_r9 = new ModelRenderer(this);
        cube_r9.setPos(0.0F, 2.5F, 4.0F);
        arrow_horizontal5.addChild(cube_r9);
        setRotationAngle(cube_r9, -0.7854F, 0.0F, 0.0F);
        cube_r9.texOffs(30, 80).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        arrow_horizontal6 = new ModelRenderer(this);
        arrow_horizontal6.setPos(-0.5F, -2.5F, -1.5F);
        rightArm.addChild(arrow_horizontal6);
        arrow_horizontal6.texOffs(29, 82).addBox(0.0F, 2.0F, 3.0F, 1.0F, 1.0F, 2.0F, -0.1F, false);

        cube_r10 = new ModelRenderer(this);
        cube_r10.setPos(0.0F, 2.5F, 4.0F);
        arrow_horizontal6.addChild(cube_r10);
        setRotationAngle(cube_r10, -0.7854F, 0.0F, 0.0F);
        cube_r10.texOffs(30, 80).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

        leftLeg.texOffs(48, 102).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);
        leftLeg.texOffs(48, 95).addBox(-1.9F, 0.2F, -2.0F, 4.0F, 3.0F, 4.0F, 0.1F, false);

        leftLegJoint.texOffs(50, 112).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, -0.1F, false);

        leftLowerLeg.texOffs(48, 118).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

        rightLeg.texOffs(72, 102).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);
        rightLeg.texOffs(72, 95).addBox(-2.0F, 0.2F, -2.0F, 4.0F, 3.0F, 4.0F, 0.1F, false);

        rightLegJoint.texOffs(74, 112).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, -0.1F, false);

        rightLowerLeg.texOffs(72, 118).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);
    }

    @Override
    protected RotationAngle[][] initSummonPoseRotations() {
        return new RotationAngle[][] {
                new RotationAngle[] {
                        new RotationAngle(body, 0.0F, 0.7854F, 0.0F),
                        new RotationAngle(leftArm, 0.0F, 0.0F, -0.9599F),
                        new RotationAngle(leftForeArm, 0.0F, 1.5708F, 0.7418F),
                        new RotationAngle(rightArm, -1.5708F, 0.7854F, 0.0F),
                        new RotationAngle(rightForeArm, 0.0F, 0.0F, -1.5708F),
                        new RotationAngle(leftLeg, -0.3054F, 0.0F, 0.0F),
                        new RotationAngle(leftLowerLeg, 0.48F, 0.0F, 0.0F),
                        new RotationAngle(rightLeg, 0.3927F, 0.0F, 0.0F),
                        new RotationAngle(rightLowerLeg, 0.3054F, 0.0F, 0.0F),
                },
                new RotationAngle[] {
                        new RotationAngle(head, -0.2618F, 0.0F, 0.0F),
                        new RotationAngle(leftArm, -2.3562F, 1.2217F, -1.5708F),
                        new RotationAngle(leftForeArm, -1.9635F, -0.3747F, 0.2291F),
                        new RotationAngle(rightArm, 0.0F, 0.2182F, 0.7854F),
                        new RotationAngle(rightForeArm, 0.2618F, 0.0F, -1.5708F),
                        new RotationAngle(leftLeg, -1.6581F, 0.0F, 0.0F),
                        new RotationAngle(leftLegJoint, 0.7418F, 0.0F, 0.0F),
                        new RotationAngle(leftLowerLeg, 1.789F, 0.0F, 0.0F),
                        new RotationAngle(rightLeg, -0.1745F, 0.0F, 0.0F),
                        new RotationAngle(rightLowerLeg, 0.3491F, 0.0F, 0.0F),
                }
        };
    }



    @Override
    protected ModelPose<CMoonEntity> initIdlePose() {
        return new ModelPose<>(new RotationAngle[] {
                new RotationAngle(leftArm, -0.0425F, 0.7769F, -0.1841F),
                new RotationAngle(leftForeArm, 0.0F, 0.0F, 0.0F),
                new RotationAngle(rightArm, 0.0003F, -0.7816F, 0.1231F),
                new RotationAngle(rightForeArm, 0.0F, 0.0F, 0.0F),
                new RotationAngle(leftLeg, -0.2182F, 0.0F, -0.0873F),
                new RotationAngle(leftLowerLeg, 0.7854F, 0.0F, 0.0F),
                new RotationAngle(rightLeg, -0.1309F, 0.0F, 0.0873F),
                new RotationAngle(rightLowerLeg, 0.3054F, 0.0F, 0.0F),
        });
    }

    @Override
    protected ModelPose<CMoonEntity> initIdlePose2Loop() {
        return new ModelPose<>(new RotationAngle[] {
                new RotationAngle(leftArm, -0.0852F, 0.7741F, -0.2451F),
                new RotationAngle(leftForeArm, 0.0F, 0.0F, 0.0F),
                new RotationAngle(rightArm, -0.0429F, -0.7807F, 0.1845F),
                new RotationAngle(rightForeArm, 0.0F, 0.0F, 0.0F)
        });

    }
}