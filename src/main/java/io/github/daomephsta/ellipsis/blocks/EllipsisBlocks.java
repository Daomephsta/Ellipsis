package io.github.daomephsta.ellipsis.blocks;

import io.github.daomephsta.ellipsis.Ellipsis;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Ellipsis.MOD_ID)
@Mod.EventBusSubscriber(modid = Ellipsis.MOD_ID, bus = Bus.MOD)
public class EllipsisBlocks 
{	
	public static final Block OMNIDIRECTIONAL_MUFFLER = null;
	public static final Block DIRECTIONAL_MUFFLER = null;
	public static final Block REGIONAL_MUFFLER = null;
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) 
	{
		event.getRegistry().registerAll(
		    Ellipsis.withId("omnidirectional_muffler", new OmnidirectionalMufflerBlock(
		        Block.Properties.copy(Blocks.OAK_PLANKS))),
		    Ellipsis.withId("directional_muffler", new DirectionalMufflerBlock(
                Block.Properties.copy(Blocks.OAK_PLANKS))),
		    Ellipsis.withId("regional_muffler", new RegionalMufflerBlock(
                Block.Properties.copy(Blocks.OAK_PLANKS)))
		);
	}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) 
	{
	    event.getRegistry().registerAll(
            Ellipsis.withId("omnidirectional_muffler", new BlockItem(OMNIDIRECTIONAL_MUFFLER, 
                new Item.Properties().tab(Ellipsis.ITEM_GROUP))),
            Ellipsis.withId("directional_muffler", new BlockItem(DIRECTIONAL_MUFFLER, 
                new Item.Properties().tab(Ellipsis.ITEM_GROUP))),
            Ellipsis.withId("regional_muffler", new BlockItem(REGIONAL_MUFFLER, 
                new Item.Properties().tab(Ellipsis.ITEM_GROUP)))
        );
	}
}

