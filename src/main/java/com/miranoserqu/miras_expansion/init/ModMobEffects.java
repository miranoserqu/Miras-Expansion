package com.miranoserqu.miras_expansion.init;

import com.miranoserqu.miras_expansion.MirasExpansion;
import com.miranoserqu.miras_expansion.mobEffect.ElectrifiedEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMobEffects {

    public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(Registries.MOB_EFFECT, MirasExpansion.MODID);

    public static final DeferredHolder<MobEffect, MobEffect> ELECTRIFIED = REGISTRY.register("electrified", ElectrifiedEffect::new);

}
