package com.leviathan143.ellipsis.common.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.leviathan143.ellipsis.Ellipsis;
import com.leviathan143.ellipsis.Ellipsis.Constants;

public class EllipsisBlocks 
{
	public static List<Block> blockList = new ArrayList<Block>();
	public static BlockOmnidirectionalMuffler omnidirectionalMuffler = new BlockOmnidirectionalMuffler();
	public static BlockDirectionalMuffler directionalMuffler = new BlockDirectionalMuffler();
	
	public static void init()
	{
		registerBlock(omnidirectionalMuffler, "omnidirectionalMuffler");
		registerBlock(directionalMuffler, "directionalMuffler");
	}
	
	private static void registerBlock(Block block, String name)
	{
		block.setUnlocalizedName(Constants.MODID + "." +  name);
		block.setCreativeTab(Ellipsis.ellipsisTab);
		GameRegistry.registerBlock(block, name);
		blockList.add(block);
	}
}
