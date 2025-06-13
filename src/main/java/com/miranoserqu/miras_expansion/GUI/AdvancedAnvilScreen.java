package com.miranoserqu.miras_expansion.GUI;

import com.miranoserqu.miras_expansion.MirasExpansion;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.checkerframework.checker.nullness.qual.NonNull;

public class AdvancedAnvilScreen extends AbstractContainerScreen<AdvancedAnvilMenu> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("miras_expansion:textures/screens/advanced_anvil_gui.png");

    @Override
    protected void renderBg(@NonNull GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        pGuiGraphics.blit(TEXTURE, this.getGuiLeft(), this.getGuiTop(), 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
    }

    public AdvancedAnvilScreen(AdvancedAnvilMenu pMenu, Inventory pInventory, Component pTitle){
        super(pMenu, pInventory, pTitle);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

}
