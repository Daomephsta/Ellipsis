package io.github.daomephsta.ellipsis.datagen;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.mojang.datafixers.util.Pair;

import io.github.daomephsta.ellipsis.Ellipsis;
import io.github.daomephsta.ellipsis.blocks.EllipsisBlocks;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.loot.*;
import net.minecraft.loot.LootTable.Builder;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class EllipsisLootTableProvider extends LootTableProvider
{
    public EllipsisLootTableProvider(DataGenerator gen)
    {
        super(gen);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, Builder>>>, LootParameterSet>> getTables()
    {
        return ImmutableList.of(Pair.of(BlockTables::new, LootParameterSets.BLOCK));
    };
    
    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker)
    {
        for (Entry<ResourceLocation, LootTable> entry : map.entrySet())
            LootTableManager.validate(validationtracker, entry.getKey(), entry.getValue());
    }
    
    private static class BlockTables extends BlockLootTables
    {
        @Override
        protected void addTables()
        {
            dropSelf(EllipsisBlocks.DIRECTIONAL_MUFFLER);
            dropSelf(EllipsisBlocks.OMNIDIRECTIONAL_MUFFLER);
            dropSelf(EllipsisBlocks.REGIONAL_MUFFLER);
        }
        
        @Override
        protected Iterable<Block> getKnownBlocks()
        {
            return Iterables.filter(ForgeRegistries.BLOCKS, 
                block -> block.getRegistryName().getNamespace().equals(Ellipsis.MOD_ID));
        }
    }
}
