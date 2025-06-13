package com.miranoserqu.miras_expansion.init;

import com.miranoserqu.miras_expansion.MirasExpansion;
import com.miranoserqu.miras_expansion.weapons.AcrobaticBow;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModWeapons {

    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(Registries.ITEM, MirasExpansion.MODID);

    public static final DeferredHolder<Item, Item> ACROBATIC_BOW = REGISTRY.register("acrobatic_bow", AcrobaticBow::new);

}
