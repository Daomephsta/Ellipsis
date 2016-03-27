package com.leviathan143.ellipsis.common;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.leviathan143.ellipsis.common.blocks.EllipsisBlocks;
import com.leviathan143.ellipsis.common.items.EllipsisItems;
import com.leviathan143.ellipsis.common.recipes.EllipsisCrafting;

public class CommonProxy 
{
	public void preInit(FMLPreInitializationEvent event) 
	{	
		EllipsisBlocks.init();
		EllipsisItems.init();
		registerModels();
	}

	public void init(FMLInitializationEvent event) 
	{
		EllipsisCrafting.register();
	}

	public void postInit(FMLPostInitializationEvent event) 
	{
	}

	public void registerModels() 
	{
		
	}
}
