package com.miranoserqu.miras_expansion.init;

import com.miranoserqu.miras_expansion.MirasExpansion;
import com.miranoserqu.miras_expansion.enchantment.BlastingEnchantment;
import com.miranoserqu.miras_expansion.enchantment.TelekinesisEnchantment;
import com.miranoserqu.miras_expansion.enchantment.ReachingEnchantment;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEnchantments {

    public static final DeferredRegister<Enchantment> REGISRTY = DeferredRegister.create(Registries.ENCHANTMENT, MirasExpansion.MODID);

    public static final DeferredHolder<Enchantment, Enchantment> BLASTING = REGISRTY.register("blasting", BlastingEnchantment::new);
    public static final DeferredHolder<Enchantment, Enchantment> TELEKINESIS = REGISRTY.register("telekinesis", TelekinesisEnchantment::new);
    public static final DeferredHolder<Enchantment, Enchantment> REACHING = REGISRTY.register("reaching", ReachingEnchantment::new);

}
