package com.miranoserqu.miras_expansion.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ReachUpgradeItem extends Item {

    public ReachUpgradeItem(){
        super(new Item.Properties().stacksTo(64));
    }

    @Override
    public Component getName(ItemStack pStack) {
        return Component.translatable("item.miras_expansion.reach_upgrade_item");
    }
}
