package com.leviathan143.ellipsis.common;

import java.util.Calendar;

public class Shennanigans 
{
	private static Calendar calendar = Calendar.getInstance();
	
	public static boolean isAprilFoolsDay = false;
	
	public static void checkDate()
	{	
		if(calendar.get(Calendar.DAY_OF_YEAR) == 92 && EllipsisConfig.doShennanigans) isAprilFoolsDay = true;
	}
}
