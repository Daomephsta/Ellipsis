package com.leviathan143.ellipsis.client;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.leviathan143.ellipsis.client.handlers.MufflerSyncHandler;
import com.leviathan143.ellipsis.client.handlers.RenderHandler;
import com.leviathan143.ellipsis.client.handlers.SoundEventHandler;
import com.leviathan143.ellipsis.common.CommonProxy;
import com.leviathan143.ellipsis.common.EllipsisConfig;
import com.leviathan143.ellipsis.common.Shennanigans;
import com.leviathan143.ellipsis.common.blocks.EllipsisBlocks;
import com.leviathan143.ellipsis.common.items.EllipsisItems;

public class ClientProxy extends CommonProxy
{
	SoundEventHandler soundEventHandler = new SoundEventHandler();
	RenderHandler renderHandler = new RenderHandler();
	EarmuffColour earmuffColourHandler = new EarmuffColour();
	
	@Override
	public void preInit(FMLPreInitializationEvent event) 
	{	
		super.preInit(event);
		EllipsisConfig.clientSetup(event.getSuggestedConfigurationFile());
		Shennanigans.checkDate();
	}

	@Override
	public void init(FMLInitializationEvent event) 
	{
		super.init(event);
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(earmuffColourHandler, EllipsisItems.earmuffs);
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) 
	{
		super.postInit(event);
		MinecraftForge.EVENT_BUS.register(soundEventHandler);
		MinecraftForge.EVENT_BUS.register(renderHandler);
		MinecraftForge.EVENT_BUS.register(MufflerSyncHandler.class);
	}
	
	@Override
	public void registerRenders()
	{
		for(Block block : EllipsisBlocks.blockList)
		{
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
		}
		for(Item item : EllipsisItems.itemList)
		{
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}
	}
}