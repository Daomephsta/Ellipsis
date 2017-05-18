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
	
	@CapabilityInject(RegionalMufflerMap.class)
	public static Capability<RegionalMufflerMap> CAP_MUFFLER_MAP = null;
	
	public static void register()
	{
		CapabilityManager.INSTANCE.register(RegionalMufflerMap.class, new Capability.IStorage<RegionalMufflerMap>() 
		{
			@Override
			public NBTBase writeNBT(Capability<RegionalMufflerMap> capability, RegionalMufflerMap instance, EnumFacing side) {return null;}

			@Override
			public void readNBT(Capability<RegionalMufflerMap> capability, RegionalMufflerMap instance, EnumFacing side, NBTBase nbt) {}
		}, 
		new Callable<RegionalMufflerMap>() 
		{
			@Override
			public RegionalMufflerMap call() throws Exception {return null;}
		});
	}
	
	public static RegionalMufflerMap get(World world)
	{
		return world.<RegionalMufflerMap>getCapability(CapabilityMufflerMap.CAP_MUFFLER_MAP, null);
	}
}
