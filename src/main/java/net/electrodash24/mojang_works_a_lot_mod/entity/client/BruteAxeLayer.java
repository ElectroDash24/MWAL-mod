package net.electrodash24.mojang_works_a_lot_mod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.electrodash24.mojang_works_a_lot_mod.entity.custom.BruteEntity;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BruteAxeLayer extends RenderLayer<BruteEntity, BruteModel<BruteEntity>> {
    private final ItemRenderer itemRenderer;

    public BruteAxeLayer(RenderLayerParent<BruteEntity, BruteModel<BruteEntity>> parentRenderer, ItemRenderer itemRenderer) {
        super(parentRenderer);
        this.itemRenderer = itemRenderer;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, BruteEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {

        poseStack.pushPose();

        ModelPart arm = ((BruteModel)this.getParentModel()).getArmHoldingAxe();
        arm.translateAndRotate(poseStack);

        poseStack.translate(-0.65F, 1.02F, -0.3F);
        poseStack.scale(1.0F, 1.0F, 1.0F);

        poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(0.0F));

        itemRenderer.renderStatic(
                new ItemStack(Items.IRON_AXE),
                ItemDisplayContext.THIRD_PERSON_RIGHT_HAND,
                packedLight,
                OverlayTexture.NO_OVERLAY,
                poseStack,
                buffer,
                entity.level(),
                0
        );

        poseStack.popPose();

    }
}
