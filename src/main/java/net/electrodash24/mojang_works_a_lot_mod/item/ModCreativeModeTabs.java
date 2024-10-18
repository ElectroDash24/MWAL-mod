package net.electrodash24.mojang_works_a_lot_mod.item;


import net.electrodash24.mojang_works_a_lot_mod.MWALmod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import javax.crypto.spec.IvParameterSpec;
import java.util.List;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MWALmod.MODID);

    public static final RegistryObject<CreativeModeTab> MWAL_INGREDIENTS_TAB =
            CREATIVE_MODE_TABS.register("mwal_ingredients_tab", () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.RUBY.get()))
                    .title(Component.translatable("creativetab.mwal_ingredients_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        addItemsToCreativeTab("ingredients",output);
                    })
                    .build());

    private static void addItemsToCreativeTab(String category, CreativeModeTab.Output pOutput) {
        List<RegistryObject<Item>> items = ModItems.itemCategories.get(category);
        if (items != null) {
            for (RegistryObject<Item> item : items) {
                pOutput.accept(item.get());
            }
        }
    }

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
