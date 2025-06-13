package com.miranoserqu.miras_expansion.init;

import com.miranoserqu.miras_expansion.MirasExpansion;
import com.miranoserqu.miras_expansion.block.AdvancedAnvilBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {

    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(Registries.BLOCK, MirasExpansion.MODID);

    public static final DeferredHolder<Block, Block> ADVANCED_ANVIL = REGISTRY.register("advanced_anvil", AdvancedAnvilBlock::new);

}
