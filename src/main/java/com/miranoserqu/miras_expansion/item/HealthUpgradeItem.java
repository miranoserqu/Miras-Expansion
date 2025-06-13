package com.miranoserqu.miras_expansion.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class HealthUpgradeItem extends Item {

    public HealthUpgradeItem(){
        super(new Properties().stacksTo(64));
    }

    @Override
    public Component getName(ItemStack pStack) {
        return Component.translatable("item.miras_expansion.health_upgrade_item");
    }
}
