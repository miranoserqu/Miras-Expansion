package com.miranoserqu.miras_expansion.event;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.miranoserqu.miras_expansion.MirasExpansion;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = MirasExpansion.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandler {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event){
        if(event.phase == TickEvent.Phase.END && !event.player.level().isClientSide){
            handleAttributes(
                    event.player,
                    event.player.getMainHandItem(),
                    "MirasExpansion.RangeModifierChecker",
                    "MirasExpansion.RangeModifierAmount",
                    NeoForgeMod.BLOCK_REACH.value(),
                    UUID.fromString("ef03c971-6d1f-48a5-8e95-d6a797276ed7"),
                    "Block Reach Distance Boost",
                    AttributeModifier.Operation.ADDITION
                    );
            handleAttributes(
                    event.player,
                    event.player.getMainHandItem(),
                    "MirasExpansion.RangeModifierChecker",
                    "MirasExpansion.RangeModifierAmount",
                    NeoForgeMod.ENTITY_REACH.value(),
                    UUID.fromString("038c4525-c21a-47f0-962b-b1e26886de51"),
                    "Entity Reach Distance Boost",
                    AttributeModifier.Operation.ADDITION
            );
            handleAttributes(
                    event.player,
                    event.player.getItemBySlot(EquipmentSlot.HEAD),
                    "MirasExpansion.HealthModifierChecker",
                    "MirasExpansion.HealthModifierAmount",
                    Attributes.MAX_HEALTH,
                    UUID.fromString("935ea483-c120-454a-8f87-b06329dba001"),
                    "Health Boost",
                    AttributeModifier.Operation.ADDITION
            );
            handleAttributes(
                    event.player,
                    event.player.getItemBySlot(EquipmentSlot.CHEST),
                    "MirasExpansion.HealthModifierChecker",
                    "MirasExpansion.HealthModifierAmount",
                    Attributes.MAX_HEALTH,
                    UUID.fromString("0bf9c866-224b-4305-b583-5181b4f18efe"),
                    "Health Boost",
                    AttributeModifier.Operation.ADDITION
            );
            handleAttributes(
                    event.player,
                    event.player.getItemBySlot(EquipmentSlot.LEGS),
                    "MirasExpansion.HealthModifierChecker",
                    "MirasExpansion.HealthModifierAmount",
                    Attributes.MAX_HEALTH,
                    UUID.fromString("40625b6c-2a14-4ce0-89a2-6d13af996b3e"),
                    "Health Boost",
                    AttributeModifier.Operation.ADDITION
            );
            handleAttributes(
                    event.player,
                    event.player.getItemBySlot(EquipmentSlot.FEET),
                    "MirasExpansion.HealthModifierChecker",
                    "MirasExpansion.HealthModifierAmount",
                    Attributes.MAX_HEALTH,
                    UUID.fromString("9e46e6c2-a6b8-4eb2-8274-c102206df237"),
                    "Health Boost",
                    AttributeModifier.Operation.ADDITION
            );
        }
    }

    private static void handleAttributes(Player player, ItemStack itemStack, String modifier, String amount, Attribute attribute, UUID uuid, String attributeDefinition, AttributeModifier.Operation operation){

        boolean marker = itemStack.hasTag() && (itemStack.getTag() != null && itemStack.getTag().getBoolean(modifier));
        double modifierAmount;
        if (itemStack.hasTag()) {
            assert itemStack.getTag() != null;
            modifierAmount = itemStack.getTag().getDouble(amount);
        } else {
            modifierAmount = 0.0;
        }

        if(marker){
            Multimap<Attribute, AttributeModifier> attributesMap = HashMultimap.create();
            attributesMap.put(attribute, new AttributeModifier(
                    uuid,
                    attributeDefinition,
                    modifierAmount,
                    operation
            ));
            player.getAttributes().addTransientAttributeModifiers(attributesMap);
        } else{
            Objects.requireNonNull(player.getAttribute(attribute)).removeModifier(uuid);
        }

    }

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event){

        ItemStack itemStack = event.getItemStack();
        List<Component> tooltip = event.getToolTip();
        List<String[]> modifiers = new ArrayList<>();
        modifiers.add(new String[] {"MirasExpansion.RangeModifierChecker", "MirasExpansion.RangeModifierAmount", "tooltip.miras_expansion.reach_boost_condition", "tooltip.miras_expansion.reach_boost"});
        modifiers.add(new String[] {"MirasExpansion.HealthModifierChecker", "MirasExpansion.HealthModifierAmount", "tooltip.miras_expansion.health_boost_condition_head", "tooltip.miras_expansion.heath_boost"});
        modifiers.add(new String[] {"MirasExpansion.HealthModifierChecker", "MirasExpansion.HealthModifierAmount", "tooltip.miras_expansion.health_boost_condition_chest", "tooltip.miras_expansion.heath_boost"});
        modifiers.add(new String[] {"MirasExpansion.HealthModifierChecker", "MirasExpansion.HealthModifierAmount", "tooltip.miras_expansion.health_boost_condition_legs", "tooltip.miras_expansion.heath_boost"});
        modifiers.add(new String[] {"MirasExpansion.HealthModifierChecker", "MirasExpansion.HealthModifierAmount", "tooltip.miras_expansion.health_boost_condition_feet", "tooltip.miras_expansion.heath_boost"});

        for(String[] modifier : modifiers) {

            boolean marker = getModifierMarker(itemStack, modifier[0]);
            double amount = getModifierAmount(itemStack, modifier[1]);

            if (marker) {
                if(tooltip.contains(Component.translatable(modifier[2]).withStyle(ChatFormatting.GRAY))){
                    tooltip.add(tooltip.indexOf(Component.translatable(modifier[2]).withStyle(ChatFormatting.GRAY)) + 1, Component.literal("+" + amount + " ").append(Component.translatable(modifier[3]).withStyle(ChatFormatting.BLUE)).withStyle(ChatFormatting.BLUE));
                } else{
                    tooltip.add(Component.literal(""));
                    tooltip.add(Component.translatable(modifier[2]).withStyle(ChatFormatting.GRAY));
                    tooltip.add(Component.literal("+" + amount + " ").append(Component.translatable(modifier[3]).withStyle(ChatFormatting.BLUE)).withStyle(ChatFormatting.BLUE));
                }
            }
        }
    }

    private static boolean getModifierMarker(ItemStack itemStack, String tag){
        return itemStack.hasTag() && (itemStack.getTag() != null && itemStack.getTag().getBoolean(tag));
    }

    private static double getModifierAmount(ItemStack itemStack, String tag){
        if (itemStack.hasTag()) {
            assert itemStack.getTag() != null;
            return itemStack.getTag().getDouble(tag);
        } else {
            return 0.0;
        }
    }

}
