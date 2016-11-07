package com.leviathan143.ellipsis.common.items;

import java.util.ArrayList;
import java.util.List;

import com.leviathan143.ellipsis.Ellipsis;
import com.leviathan143.ellipsis.Ellipsis.Constants;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class EllipsisItems 
{
	public static List<Item> itemList = new ArrayList<Item>();
	public static ItemEarmuffs earmuffs = new ItemEarmuffs();
	public static ItemEntitySilencer entitySilencer = new ItemEntitySilencer();
	
	public static void init()
	{
		registerItem(earmuffs, "earmuffs");
		registerItem(entitySilencer, "entitySilencer");
	}
	
	private static void registerItem(Item item, String name)
	{
		item.setUnlocalizedName(Constants.MODID + "." +  name);
		item.setRegistryName(name);
		item.setCreativeTab(Ellipsis.ellipsisTab);
		GameRegistry.register(item);
		itemList.add(item);
	}
}
