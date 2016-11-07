package com.leviathan143.ellipsis.common.capability;

import java.util.concurrent.Callable;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityMufflerMap
{
	
	@CapabilityInject(IMufflerMap.class)
	public static Capability<IMufflerMap> CAP_MUFFLER_MAP = null;
	
	public static void register()
	{
		CapabilityManager.INSTANCE.register(IMufflerMap.class, new Capability.IStorage<IMufflerMap>() 
		{
			@Override
			public NBTBase writeNBT(Capability<IMufflerMap> capability, IMufflerMap instance, EnumFacing side) {return null;}

			@Override
			public void readNBT(Capability<IMufflerMap> capability, IMufflerMap instance, EnumFacing side, NBTBase nbt) {}
		}, 
		new Callable<IMufflerMap>() 
		{
			@Override
			public IMufflerMap call() throws Exception {return null;}
		});
	}
	
	public static IMufflerMap get(World world)
	{
		return world.<IMufflerMap>getCapability(CapabilityMufflerMap.CAP_MUFFLER_MAP, null);
	}
}
