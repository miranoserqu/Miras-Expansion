package com.miranoserqu.miras_expansion.enchantment;

import com.miranoserqu.miras_expansion.MirasExpansion;
import com.miranoserqu.miras_expansion.init.ModEnchantments;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.level.BlockEvent;

import java.util.List;

@Mod.EventBusSubscriber(modid = MirasExpansion.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TelekinesisEnchantment extends Enchantment {

    public TelekinesisEnchantment(){
        super(
                Rarity.VERY_RARE,
                EnchantmentCategory.DIGGER,
                new EquipmentSlot[] {EquipmentSlot.MAINHAND}
        );
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (!(event.getLevel() instanceof ServerLevel serverLevel)) return;

        BlockState state = event.getState();
        Player player = event.getPlayer();
        BlockPos pos = event.getPos();

        if (player.getMainHandItem().getEnchantmentLevel(ModEnchantments.TELEKINESIS.get()) == 0) return;

        LootParams.Builder builder = new LootParams.Builder(serverLevel)
                .withParameter(LootContextParams.ORIGIN, pos.getCenter())
                .withParameter(LootContextParams.TOOL, player.getMainHandItem())
                .withParameter(LootContextParams.THIS_ENTITY, player);

        List<ItemStack> items = state.getDrops(builder);

        event.setCanceled(true);

        player.getMainHandItem().mineBlock(serverLevel, state, pos, player);
        serverLevel.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);

        for(ItemStack itemStack : items){
            SimpleContainer container = new SimpleContainer(itemStack);
            if(player.getMainHandItem().getEnchantmentLevel(ModEnchantments.BLASTING.get()) != 0){
                if(!player.addItem(serverLevel.getRecipeManager().getRecipeFor(
                        RecipeType.SMELTING, container, serverLevel).map(holder -> holder.value()
                        .getResultItem(serverLevel.registryAccess()).copyWithCount(itemStack.getCount())).orElse(itemStack.copy()))){
                    serverLevel.addFreshEntity(new ItemEntity(serverLevel, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, serverLevel.getRecipeManager().getRecipeFor(
                            RecipeType.SMELTING, container, serverLevel).map(holder -> holder.value().getResultItem(serverLevel.registryAccess()).copyWithCount(itemStack.getCount())).orElse(itemStack.copy())));
                }
                player.giveExperiencePoints(state.getExpDrop(
                        serverLevel,
                        RandomSource.create(),
                        pos,
                        player.getMainHandItem().getEnchantmentLevel(Enchantments.BLOCK_FORTUNE),
                        player.getMainHandItem().getEnchantmentLevel(Enchantments.SILK_TOUCH)
                ) + serverLevel.getRecipeManager().getRecipeFor(
                        RecipeType.SMELTING, container, serverLevel).map(holder -> holder.value().getExperience()).orElse(0.0f).intValue());
            } else{
                if(!player.addItem(itemStack)){
                    serverLevel.addFreshEntity(new ItemEntity(serverLevel, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, itemStack));
                }
                player.giveExperiencePoints(state.getExpDrop(
                        serverLevel,
                        RandomSource.create(),
                        pos,
                        player.getMainHandItem().getEnchantmentLevel(Enchantments.BLOCK_FORTUNE),
                        player.getMainHandItem().getEnchantmentLevel(Enchantments.SILK_TOUCH)
                ));
            }
        }
    }
}
