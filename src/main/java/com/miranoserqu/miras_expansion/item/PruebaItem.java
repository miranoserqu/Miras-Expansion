package com.miranoserqu.miras_expansion.item;

import com.miranoserqu.miras_expansion.MirasExpansion;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.fml.common.Mod;

public class PruebaItem extends Item {

    public PruebaItem(){
        super(new Item.Properties().rarity(Rarity.EPIC).stacksTo(20));
    }

}
