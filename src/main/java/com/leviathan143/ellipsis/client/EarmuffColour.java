package com.leviathan143.ellipsis.client;

import com.leviathan143.ellipsis.common.helpers.StackNBTHelper;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;

public class EarmuffColour implements IItemColor
{
	@Override
	public int getColorFromItemstack(ItemStack stack, int tintIndex) 
	{
		return StackNBTHelper.getTag(stack).getInteger("Colour");
	}
}
