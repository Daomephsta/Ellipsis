package com.leviathan143.ellipsis;

import com.leviathan143.ellipsis.Ellipsis.Constants;
import com.leviathan143.ellipsis.common.CommonProxy;
import com.leviathan143.ellipsis.common.blocks.EllipsisBlocks;
import com.leviathan143.ellipsis.common.items.EllipsisItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.event.FMLMissingMappingsEvent.MissingMapping;

@Mod(modid = Constants.MODID, name = Constants.MODNAME, version = Constants.VERSION, acceptedMinecraftVersions = Constants.MCVERSION, dependencies = Constants.DEPENDENCIES)
public class Ellipsis 
{

	public class Constants
	{
		public static final String MODNAME = "...(aka Ellipsis)";
		public static final  String  MODID = "ellipsis";
		public static final  String  VERSION = "0.1.2";
		public static final  String  MCVERSION = "1.11.2";
		public static final String DEPENDENCIES = "";
		public static final String COMMONPROXY_PATH="com.leviathan143.ellipsis.common.CommonProxy";
		public static final String CLIENTPROXY_PATH="com.leviathan143.ellipsis.client.ClientProxy";
	}

	@SidedProxy(clientSide = Constants.CLIENTPROXY_PATH,
			serverSide=Constants.COMMONPROXY_PATH)
	public static CommonProxy proxy;

	@Mod.Instance(Constants.MODID)
	public static Ellipsis instance;

	public static CreativeTabs ellipsisTab =  new CreativeTabs(Constants.MODID) 
	{	
		@Override
		public ItemStack getTabIconItem() 
		{
			return new ItemStack(EllipsisBlocks.omnidirectionalMuffler);
		}
	};

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		proxy.preInit(event);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init(event);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit(event);
	}
	
	@Mod.EventHandler
	public void remapIDs(FMLMissingMappingsEvent e)
	{
	    for(MissingMapping mapping : e.get())
	    {
		switch(mapping.type)
		{
		case BLOCK:
		    if(mapping.resourceLocation.getResourcePath().equals("directionalmuffler")) mapping.remap(EllipsisBlocks.directionalMuffler);
		    else if(mapping.resourceLocation.getResourcePath().equals("omnidirectionalmuffler")) mapping.remap(EllipsisBlocks.omnidirectionalMuffler);
		    else if(mapping.resourceLocation.getResourcePath().equals("regionalmuffler")) mapping.remap(EllipsisBlocks.regionalMuffler);
		    break;
		case ITEM:
		    if(mapping.resourceLocation.getResourcePath().equals("directionalmuffler")) mapping.remap(Item.getItemFromBlock(EllipsisBlocks.directionalMuffler));
		    else if(mapping.resourceLocation.getResourcePath().equals("omnidirectionalmuffler")) mapping.remap(Item.getItemFromBlock(EllipsisBlocks.omnidirectionalMuffler));
		    else if(mapping.resourceLocation.getResourcePath().equals("regionalmuffler")) mapping.remap(Item.getItemFromBlock(EllipsisBlocks.regionalMuffler));
		    else if(mapping.resourceLocation.getResourcePath().equals("entitysilencer")) mapping.remap(EllipsisItems.entitySilencer);
		    break;
		}
	    }
	}
}
