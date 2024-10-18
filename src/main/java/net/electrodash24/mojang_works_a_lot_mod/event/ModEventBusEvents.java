package net.electrodash24.mojang_works_a_lot_mod.event;

import net.electrodash24.mojang_works_a_lot_mod.MWALmod;
import net.electrodash24.mojang_works_a_lot_mod.entity.ModEntities;
import net.electrodash24.mojang_works_a_lot_mod.entity.custom.BruteEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MWALmod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.BRUTE.get(), BruteEntity.createAttributes().build());
    }
}