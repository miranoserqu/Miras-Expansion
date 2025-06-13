package com.miranoserqu.miras_expansion.block;

import com.miranoserqu.miras_expansion.GUI.AdvancedAnvilMenu;
import com.miranoserqu.miras_expansion.MirasExpansion;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class AdvancedAnvilBlock extends FallingBlock {

    public AdvancedAnvilBlock(){
        super(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).sound(SoundType.METAL).strength(50f, 1200f).requiresCorrectToolForDrops());
    }

    MapCodec<AdvancedAnvilBlock> CODEC = simpleCodec(properties -> new AdvancedAnvilBlock());

    @Override
    protected @NotNull MapCodec<? extends FallingBlock> codec() {
        return CODEC;
    }

    @Override
    @SuppressWarnings("deprecation")
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide) {
            if (pPlayer instanceof ServerPlayer serverPlayer) {
                MenuProvider containerProvider = new SimpleMenuProvider(
                        (id, inventory, player) -> new AdvancedAnvilMenu(id, inventory, pPos),
                        Component.translatable("container.miras_expansion.advanced_anvil")
                );

                serverPlayer.openMenu(containerProvider, (buf) -> {
                    buf.writeBlockPos(pPos);
                });
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide);
    }
}
