package com.miranoserqu.miras_expansion.init;

import com.miranoserqu.miras_expansion.GUI.AdvancedAnvilMenu;
import com.miranoserqu.miras_expansion.GUI.AdvancedAnvilScreen;
import com.miranoserqu.miras_expansion.MirasExpansion;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModMenus {

    public static final DeferredRegister<MenuType<?>> REGISTER = DeferredRegister.create(
            Registries.MENU,
            MirasExpansion.MODID
    );

    public static final Supplier<MenuType<AdvancedAnvilMenu>> ADVANCED_ANVIL_MENU = REGISTER.register(
            "advanced_anvil_menu",
            () -> IMenuTypeExtension.create((id, inv, data) -> new AdvancedAnvilMenu(id, inv, data))
    );

}
