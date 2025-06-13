package com.miranoserqu.miras_expansion.GUI;

import com.miranoserqu.miras_expansion.block.AdvancedAnvilBlock;
import com.miranoserqu.miras_expansion.init.ModBlocks;
import com.miranoserqu.miras_expansion.init.ModItems;
import com.miranoserqu.miras_expansion.init.ModMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;


public class AdvancedAnvilMenu extends AbstractContainerMenu {

    private SimpleContainer container;
    private final ContainerLevelAccess access;
    private final BlockPos blockPos;
    private final Player player;

    public AdvancedAnvilMenu(int id, Inventory inv, FriendlyByteBuf data){
        this(id, inv, data.readBlockPos());
    }

    public AdvancedAnvilMenu(int id, Inventory inv, BlockPos pPos){
        super(ModMenus.ADVANCED_ANVIL_MENU.get(), id);
        this.container = new SimpleContainer(3);
        this.access = ContainerLevelAccess.create(inv.player.level(), pPos);
        this.blockPos = pPos;
        this.player = inv.player;

        this.addSlot(new Slot(container, 0, 79, 17) {
            @Override
            public void setChanged() {
                updateResult();
            }
        });

        this.addSlot(new Slot(container, 1, 34, 35) {
            @Override
            public void setChanged() {
                updateResult();
            }
        });

        this.addSlot(new Slot(container, 2, 79, 53){
            @Override
            public boolean mayPlace(ItemStack pStack) {
                return false;
            }
        });

        for (int si = 0; si < 3; si++) {
            for (int sj = 0; sj < 9; sj++) {
                this.addSlot(new Slot(inv, sj + (si + 1) * 9, 8 + sj * 18, 84 + si * 18));
            }
        }

        for (int si = 0; si < 9; si++) {
            this.addSlot(new Slot(inv, si, 8 + si * 18, 142));
        }

    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(this.access, pPlayer, ModBlocks.ADVANCED_ANVIL.get());
    }

    @Override
    public void removed(Player pPlayer) {
        this.clearContainer(pPlayer, this.container);
        super.removed(pPlayer);
    }

    private void updateResult(){
        Map<Item, String[]> upgrades = new HashMap<>();
        Map<Item, Double> amounts = new HashMap<>();

        upgrades.put(ModItems.REACH_UPGRADE.get(), new String[] {"MirasExpansion.RangeModifierChecker", "MirasExpansion.RangeModifierAmount"});
        amounts.put(ModItems.REACH_UPGRADE.get(), 3.0);
        upgrades.put(ModItems.HEALTH_UPGRADE.get(), new String[] {"MirasExpansion.HealthModifierChecker", "MirasExpansion.HealthModifierAmount"});
        amounts.put(ModItems.HEALTH_UPGRADE.get(), 5.0);

        for(Item item : upgrades.keySet()) {
            if (!container.getItem(0).isEmpty() && container.getItem(1).is(item)) {
                container.removeItem(1, 1);
                if (this.player.level().isClientSide) {
                    return;
                }
                ItemStack copy = container.getItem(0);
                CompoundTag nbtTag = copy.getOrCreateTag();
                nbtTag.putBoolean(upgrades.get(item)[0], true);
                nbtTag.putDouble(upgrades.get(item)[1], amounts.get(item));
                copy.setTag(nbtTag);
            }
        }
    }
}