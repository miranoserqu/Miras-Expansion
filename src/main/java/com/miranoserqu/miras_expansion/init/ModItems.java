package com.miranoserqu.miras_expansion.init;

import com.miranoserqu.miras_expansion.MirasExpansion;
import com.miranoserqu.miras_expansion.item.ForgingSword;
import com.miranoserqu.miras_expansion.item.HealthUpgradeItem;
import com.miranoserqu.miras_expansion.item.PruebaItem;
import com.miranoserqu.miras_expansion.item.ReachUpgradeItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(Registries.ITEM, MirasExpansion.MODID);

    public  static final DeferredHolder<Item, Item> FORGING_SWORD = REGISTRY.register("forging_sword", ForgingSword::new);

    public static final DeferredHolder<Item, Item> PRUEBAITEM = REGISTRY.register("prueba_item", PruebaItem::new);
    public static final DeferredHolder<Item, Item> REACH_UPGRADE = REGISTRY.register("reach_upgrade", ReachUpgradeItem::new);
    public static final DeferredHolder<Item, Item> HEALTH_UPGRADE = REGISTRY.register("health_upgrade", HealthUpgradeItem::new);

    public static final DeferredHolder<Item, Item> ADVANCED_ANVIL_ITEM = REGISTRY.register("advanced_anvil_item", () -> new BlockItem(ModBlocks.ADVANCED_ANVIL.get(), new Item.Properties().stacksTo(64)){
        @Override
        public Component getName(ItemStack pStack) {
            return Component.translatable("item.miras_expansion.advanced_anvil_item");
        }
    });

}
