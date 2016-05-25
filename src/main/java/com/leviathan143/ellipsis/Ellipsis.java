package com.leviathan143.ellipsis;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.leviathan143.ellipsis.Ellipsis.Constants;
import com.leviathan143.ellipsis.common.CommonProxy;
import com.leviathan143.ellipsis.common.blocks.EllipsisBlocks;

@Mod(modid = Constants.MODID, name = Constants.MODNAME, version = Constants.VERSION, acceptedMinecraftVersions = Constants.MCVERSION, canBeDeactivated = false)
public class Ellipsis 
{

	public class Constants
	{
		public static final String MODNAME = "...(aka Ellipsis)";
		public static final  String  MODID = "ellipsis";
		public static final  String  VERSION = "0.1";
		public static final  String  MCVERSION = "1.8.9";
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
		public Item getTabIconItem() {
			// TODO Auto-generated method stub
			return Item.getItemFromBlock(EllipsisBlocks.omnidirectionalMuffler);
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
}
