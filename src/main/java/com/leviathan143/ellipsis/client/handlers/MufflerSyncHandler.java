package com.leviathan143.ellipsis.client.handlers;

import com.leviathan143.ellipsis.common.capability.CapabilityMufflerMap;
import com.leviathan143.ellipsis.common.packets.PacketHandler;
import com.leviathan143.ellipsis.common.packets.PacketQueryServerMap;

import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MufflerSyncHandler
{
	@SubscribeEvent
	public static void onChunkLoad(ChunkEvent.Load event)
	{
		PacketHandler.CHANNEL.sendToServer(new PacketQueryServerMap(event.getWorld(), event.getChunk().getChunkCoordIntPair()));
	}
	
	//Remove unloaded chunks from the clientside muffler map
	@SubscribeEvent
	public static void onChunkUnload(ChunkEvent.Unload event)
	{
		CapabilityMufflerMap.get(event.getWorld()).setChunkDirty(event.getChunk().getChunkCoordIntPair());
	}
}
