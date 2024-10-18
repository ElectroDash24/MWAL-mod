package net.electrodash24.mojang_works_a_lot_mod.entity;

import net.electrodash24.mojang_works_a_lot_mod.MWALmod;
import net.electrodash24.mojang_works_a_lot_mod.entity.custom.BruteEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MWALmod.MODID);

    public static final RegistryObject<EntityType<BruteEntity>> BRUTE =
            ENTITY_TYPES.register("brute", () ->
                    EntityType.Builder.of(BruteEntity::new, MobCategory.MONSTER)
                            .sized(1.4F, 2.7F)
                            .build(MWALmod.MODID + "brute")
            );

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
