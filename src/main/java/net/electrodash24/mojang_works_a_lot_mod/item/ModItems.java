package net.electrodash24.mojang_works_a_lot_mod.item;

import net.electrodash24.mojang_works_a_lot_mod.MWALmod;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.electrodash24.mojang_works_a_lot_mod.entity.ModEntities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MWALmod.MODID);

    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BRUTE_SPAWN_EGG = ITEMS.register("illager_brute_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.BRUTE, 0x959b9b, 0x383431, new Item.Properties()));

    public static final Map<String, List<RegistryObject<Item>>> itemCategories = new HashMap<>();

    static {
        addItemToCategory("ingredients", RUBY);
        addItemToCategory("spawn_eggs", BRUTE_SPAWN_EGG);
    }

    private static void addItemToCategory(String category, RegistryObject<Item> item) {
        itemCategories.computeIfAbsent(category, k -> new ArrayList<>()).add(item);
    }
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
