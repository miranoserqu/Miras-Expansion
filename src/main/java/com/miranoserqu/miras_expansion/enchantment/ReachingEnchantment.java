package com.miranoserqu.miras_expansion.enchantment;

import com.miranoserqu.miras_expansion.MirasExpansion;
import com.miranoserqu.miras_expansion.init.ModEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import java.util.Objects;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = MirasExpansion.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ReachingEnchantment extends Enchantment {

    private static final UUID REACH_DISTANCE_MODIFIER_UUID = UUID.fromString("d7e5f3c9-a1b2-4d5e-8f9c-1a2b3c4d5e6f");

    public ReachingEnchantment() {
        super(
                Rarity.VERY_RARE,
                EnchantmentCategory.DIGGER,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND}
        );
    }

    @Override
    public int getMaxLevel() {
        return 10;
    }

    @SubscribeEvent
    public static void onLivingEquipmentChange(LivingEquipmentChangeEvent event) {
        LivingEntity entity = event.getEntity();
        EquipmentSlot slot = event.getSlot();

        if (slot != EquipmentSlot.MAINHAND) return;

        ItemStack oldStack = event.getFrom();
        ItemStack newStack = event.getTo();

        int oldEnchantLevel = oldStack.getEnchantmentLevel(ModEnchantments.REACHING.get());
        int newEnchantLevel = newStack.getEnchantmentLevel(ModEnchantments.REACHING.get());

        if (newEnchantLevel > 0) {
            AttributeModifier newModifier = new AttributeModifier(
                    REACH_DISTANCE_MODIFIER_UUID,
                    "Enchantment Reaching",
                    newEnchantLevel,
                    AttributeModifier.Operation.ADDITION
            );

            Objects.requireNonNull(entity.getAttribute(NeoForgeMod.ENTITY_REACH.value())).removeModifier(REACH_DISTANCE_MODIFIER_UUID);
            Objects.requireNonNull(entity.getAttribute(NeoForgeMod.ENTITY_REACH.value())).addTransientModifier(newModifier);
        } else if (oldEnchantLevel > 0) {
            Objects.requireNonNull(entity.getAttribute(NeoForgeMod.ENTITY_REACH.value())).removeModifier(REACH_DISTANCE_MODIFIER_UUID);
        }
    }

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();

        int enchantLevel = stack.getEnchantmentLevel(ModEnchantments.REACHING.get());

        if (enchantLevel > 0) {
            event.getToolTip().add(net.minecraft.network.chat.Component.literal("§9+" + String.format("%.1f", enchantLevel) + " Distancia de minado (Reaching)"));
        }
    }

}
