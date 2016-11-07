package com.leviathan143.ellipsis.common.capability;

import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WorldCapAttacher 
{
	@SubscribeEvent
	public static void attachCaps(AttachCapabilitiesEvent<World> event)
	{
			event.addCapability(WorldMufflerMapProvider.CAP_KEY, new WorldMufflerMapProvider());
	}
}
