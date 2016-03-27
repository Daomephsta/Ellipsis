package com.leviathan143.ellipsis.common.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.leviathan143.ellipsis.Ellipsis;
import com.leviathan143.ellipsis.Ellipsis.Constants;
import com.leviathan143.ellipsis.common.blocks.BlockDirectionalMuffler;
import com.leviathan143.ellipsis.common.blocks.BlockOmnidirectionalMuffler;

public class EllipsisItems 
{
	public static List<Item> itemList = new ArrayList<Item>();
	public static ItemEarmuffs earmuffs = new ItemEarmuffs();
	
	public static void init()
	{
		registerItem(earmuffs, "earmuffs");
	}
	
	private static void registerItem(Item item, String name)
	{
		item.setUnlocalizedName(Constants.MODID + "." +  name);
		item.setCreativeTab(Ellipsis.ellipsisTab);
		GameRegistry.registerItem(item, name);
		itemList.add(item);
	}
}
