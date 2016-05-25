package com.leviathan143.ellipsis.common;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class EllipsisConfig 
{
	private static Configuration config;
	
	private static final String CATEGORY_COMMON = "Client-side & server-side";
	private static final String CATEGORY_CLIENT = "Client-side only";
	private static final String CATEGORY_SERVER = "Server-side only";
	
	public static boolean doShennanigans = true;
	
	private static void commonSetup(File configFile)
	{
		config = new Configuration(configFile);
		config.load();
	}
	
	public static void clientSetup(File configFile)
	{
		commonSetup(configFile);
		doShennanigans = config.getBoolean("Shennanigans", CATEGORY_CLIENT, true, "Should special events happen on certain days of the year. Currently no special events are implemented");
		config.save();
	}
	
	public static void serverSetup(File configFile)
	{
		commonSetup(configFile);
		config.save();
	}
}
