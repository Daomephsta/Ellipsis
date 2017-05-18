package com.leviathan143.ellipsis.common;

import com.leviathan143.ellipsis.common.blocks.EllipsisBlocks;
import com.leviathan143.ellipsis.common.capability.*;
import com.leviathan143.ellipsis.common.handlers.ChunkDataHandler;
import com.leviathan143.ellipsis.common.items.EllipsisItems;
import com.leviathan143.ellipsis.common.packets.PacketHandler;
import com.leviathan143.ellipsis.common.recipes.EllipsisCrafting;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.*;

public class CommonProxy 
{
	public void preInit(FMLPreInitializationEvent event) 
	{	
		EllipsisBlocks.init();
		EllipsisItems.init();
		PacketHandler.registerPackets();
		CapabilityMufflerMap.register();
		registerRenders();
	}

	public void init(FMLInitializationEvent event) 
	{
		EllipsisCrafting.register();
	}

	public void postInit(FMLPostInitializationEvent event) 
	{
		MinecraftForge.EVENT_BUS.register(WorldCapAttacher.class);
		MinecraftForge.EVENT_BUS.register(ChunkDataHandler.class);
	}

	public void registerRenders() 
	{
		
	}
	
	public RegionalMufflerMap getMufflerMap()
	{
	    return CapabilityMufflerMap.get(FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld());
	}
}
