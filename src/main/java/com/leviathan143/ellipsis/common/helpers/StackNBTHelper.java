package com.leviathan143.ellipsis.common.helpers;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class StackNBTHelper 
{
	public static NBTTagCompound getTag(ItemStack stack)
	{
		NBTTagCompound tag = stack.getTagCompound();
		if (tag == null) 
		{
			tag = new NBTTagCompound();
			stack.setTagCompound(tag);
		}
		return tag;
	}
}
