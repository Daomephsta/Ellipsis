package com.leviathan143.ellipsis.common.handlers;

import com.leviathan143.ellipsis.common.capability.CapabilityMufflerMap;
import com.leviathan143.ellipsis.common.capability.IMufflerMap;
import com.leviathan143.ellipsis.common.capability.RegionalMufflerMap;

import net.minecraft.util.math.ChunkPos;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChunkDataHandler 
{
	@SubscribeEvent
	public static void onChunkDataLoad(ChunkDataEvent.Load event)
	{
		if(event.getData().hasKey(RegionalMufflerMap.MUFFLER_LIST_TAG))
		{
			CapabilityMufflerMap.get(event.getWorld()).deserializeChunkMufflers(event.getChunk().getChunkCoordIntPair()
					, event.getData().getTagList(RegionalMufflerMap.MUFFLER_LIST_TAG, NBT.TAG_COMPOUND));
		}
	}

	@SubscribeEvent
	public static void onChunkDataSave(ChunkDataEvent.Save event)
	{
		IMufflerMap mufflerMap = CapabilityMufflerMap.get(event.getWorld());
		ChunkPos chunkPos = event.getChunk().getChunkCoordIntPair();
		if(mufflerMap.doesChunkHaveMufflers(chunkPos))
		{
			event.getData().setTag(RegionalMufflerMap.MUFFLER_LIST_TAG, mufflerMap.serializeChunkMufflers(event.getChunk().getChunkCoordIntPair()));
		}
		//Check for dirty chunks
		else if(mufflerMap.isChunkDirty(chunkPos))
		{
			event.getData().removeTag(RegionalMufflerMap.MUFFLER_LIST_TAG);
			mufflerMap.setChunkClean(chunkPos);
		}
	}
}
