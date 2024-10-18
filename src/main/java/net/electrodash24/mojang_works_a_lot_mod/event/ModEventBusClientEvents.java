package net.electrodash24.mojang_works_a_lot_mod.event;

import net.electrodash24.mojang_works_a_lot_mod.MWALmod;
import net.electrodash24.mojang_works_a_lot_mod.entity.client.BruteModel;
import net.electrodash24.mojang_works_a_lot_mod.entity.client.ModModelLayers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MWALmod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.BRUTE_LAYER, BruteModel::createBodyLayer);
    }
}
