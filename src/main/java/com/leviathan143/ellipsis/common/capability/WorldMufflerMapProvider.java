package com.leviathan143.ellipsis.common.capability;

import com.leviathan143.ellipsis.Ellipsis.Constants;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class WorldMufflerMapProvider implements ICapabilityProvider
{
	public static final ResourceLocation CAP_KEY = new ResourceLocation(Constants.MODID, "_mufflerMap");

	private IMufflerMap mufflerMap;

	public WorldMufflerMapProvider()
	{
		mufflerMap = new RegionalMufflerMap();
		System.out.println(mufflerMap);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) 
	{
		if(capability == CapabilityMufflerMap.CAP_MUFFLER_MAP) 
		{
			return true;
		}
		return false;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) 
	{
		if(capability == CapabilityMufflerMap.CAP_MUFFLER_MAP) 
		{
			return CapabilityMufflerMap.CAP_MUFFLER_MAP.cast(mufflerMap);
		}
		return null;
	}
}
