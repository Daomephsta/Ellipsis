package com.leviathan143.ellipsis.common.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.google.common.collect.BiMap;
import com.leviathan143.ellipsis.Ellipsis;
import com.leviathan143.ellipsis.Ellipsis.Constants;
import com.leviathan143.ellipsis.common.items.EllipsisItems;

public class EllipsisBlocks 
{
	public static List<Block> blockList = new ArrayList<Block>();
	public static BlockOmnidirectionalMuffler omnidirectionalMuffler = new BlockOmnidirectionalMuffler();
	public static BlockDirectionalMuffler directionalMuffler = new BlockDirectionalMuffler();
	public static BlockRegionalMuffler regionalMuffler = new BlockRegionalMuffler();
	
	public static void init()
	{
		registerBlockWithItemBlock(omnidirectionalMuffler, "omnidirectionalMuffler");
		registerBlockWithItemBlock(directionalMuffler, "directionalMuffler");
		registerBlockWithItemBlock(regionalMuffler, "regionalMuffler");
	}
	
	private static void registerBlock(Block block, String name)
	{
		block.setUnlocalizedName(Constants.MODID + "." +  name);
		block.setRegistryName(name);
		block.setCreativeTab(Ellipsis.ellipsisTab);
		GameRegistry.register(block);
		blockList.add(block);
	}
	
	private static void registerBlockWithItemBlock(Block block, String name)
	{
		block.setUnlocalizedName(Constants.MODID + "." +  name);
		block.setRegistryName(name);
		block.setCreativeTab(Ellipsis.ellipsisTab);
		GameRegistry.register(block);
		blockList.add(block);
		
		ItemBlock itemBlock = new ItemBlock(block);
		itemBlock.setRegistryName(name);
		itemBlock.setCreativeTab(Ellipsis.ellipsisTab);
		GameRegistry.register(itemBlock);
		EllipsisItems.itemList.add(itemBlock);
	}
}

