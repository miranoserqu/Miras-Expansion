package com.miranoserqu.miras_expansion.mobEffect;

import com.miranoserqu.miras_expansion.init.ModMobEffects;
import com.miranoserqu.miras_expansion.MirasExpansion;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class ElectrifiedEffect extends MobEffect {

    public ElectrifiedEffect() {
        super(MobEffectCategory.HARMFUL, 0x1fadad);
    }

    @Override
    public void onEffectStarted(LivingEntity pLivingEntity, int pAmplifier) {

        int amplifier = pLivingEntity.getEffect(ModMobEffects.ELECTRIFIED.get()).getAmplifier();

        pLivingEntity.hurt(pLivingEntity.level().damageSources().magic(), amplifier);
        pLivingEntity.addEffect(new MobEffectInstance(
                MobEffects.MOVEMENT_SLOWDOWN,
                40 + 5 * amplifier,
                1
        ));

        AABB zone = new AABB(
                pLivingEntity.getX() - (2 + 0.2 * amplifier), pLivingEntity.getY() - (2 + 0.2 * amplifier), pLivingEntity.getZ() - (2 + 0.2 * amplifier),
                pLivingEntity.getX() + (2 + 0.2 * amplifier), pLivingEntity.getY() + (2 + 0.2 * amplifier), pLivingEntity.getZ() + (2 + 0.2 * amplifier)
                );

        List<LivingEntity> entities = pLivingEntity.level().getEntities(null, zone).stream().filter(entity -> entity instanceof LivingEntity).map(entity -> (LivingEntity) entity).toList();

        for(LivingEntity entity : entities){
            if(entity.getEffect(ModMobEffects.ELECTRIFIED.get()) == null){
                entity.addEffect(new MobEffectInstance(ModMobEffects.ELECTRIFIED.get(), 20, amplifier));
            }
        }

    }
}
