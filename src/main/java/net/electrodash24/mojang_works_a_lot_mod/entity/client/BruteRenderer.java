package net.electrodash24.mojang_works_a_lot_mod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.electrodash24.mojang_works_a_lot_mod.MWALmod;
import net.electrodash24.mojang_works_a_lot_mod.entity.custom.BruteEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class BruteRenderer extends MobRenderer<BruteEntity, BruteModel<BruteEntity>> {

    public BruteRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new BruteModel<>(pContext.bakeLayer(ModModelLayers.BRUTE_LAYER)),0.7f);
        this.addLayer(new BruteAxeLayer(this, Minecraft.getInstance().getItemRenderer()));
    }

    @Override
    public ResourceLocation getTextureLocation(BruteEntity bruteEntity) {
        return new ResourceLocation(MWALmod.MODID, "textures/entity/brute.png");
    }

    @Override
    public void render(BruteEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    protected void setupRotations(BruteEntity pEntityLiving, PoseStack pPoseStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks);

        if (!((double)pEntityLiving.walkAnimation.speed() < 0.01)) {
            float $$5 = 13.0F;
            float $$6 = pEntityLiving.walkAnimation.position(pPartialTicks) + 6.0F;
            float $$7 = (Math.abs($$6 % 13.0F - 6.5F) - 3.25F) / 3.25F;
            pPoseStack.mulPose(Axis.ZP.rotationDegrees(6.5F * $$7));
        }
    }
}
