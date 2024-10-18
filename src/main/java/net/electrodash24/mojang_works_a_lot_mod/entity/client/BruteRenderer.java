package net.electrodash24.mojang_works_a_lot_mod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.electrodash24.mojang_works_a_lot_mod.MWALmod;
import net.electrodash24.mojang_works_a_lot_mod.entity.custom.BruteEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BruteRenderer extends MobRenderer<BruteEntity, BruteModel<BruteEntity>> {

    public BruteRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new BruteModel<>(pContext.bakeLayer(ModModelLayers.BRUTE_LAYER)),0.7f);
    }

    @Override
    public ResourceLocation getTextureLocation(BruteEntity bruteEntity) {
        return new ResourceLocation(MWALmod.MODID, "textures/entity/brute.png");
    }

    @Override
    public void render(BruteEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
