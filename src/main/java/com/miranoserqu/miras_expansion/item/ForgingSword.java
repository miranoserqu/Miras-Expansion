package com.miranoserqu.miras_expansion.item;

import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class ForgingSword extends SwordItem {

    SimpleContainer container = new SimpleContainer(1);

    public ForgingSword(){
        super(Tiers.STONE, 2, 1.6f, new Item.Properties().stacksTo(1));
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
        if(pStack.getEnchantmentLevel(Enchantments.FIRE_ASPECT) == 0){
            pStack.enchant(Enchantments.FIRE_ASPECT, 2);
        }
    }
}
