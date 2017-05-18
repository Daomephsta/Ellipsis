package com.leviathan143.ellipsis.client.jei;

import com.leviathan143.ellipsis.common.blocks.EllipsisBlocks;
import com.leviathan143.ellipsis.common.items.EllipsisItems;

import mezz.jei.api.*;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class EllipsisJEIPlugin extends BlankModPlugin 
{
	private IModRegistry registry;
	
	@Override
	public void register(IModRegistry registry) 
	{
		this.registry = registry;
		for(Block block : EllipsisBlocks.blockList) addJEIDescription(block);
		for(Item item : EllipsisItems.itemList) addJEIDescription(item);
	}
	
	private void addJEIDescription(Block block)
	{
		addJEIDescription(Item.getItemFromBlock(block));
	}
	
	private void addJEIDescription(Item item)
	{
		registry.addDescription(new ItemStack(item), item.getUnlocalizedName() + ".jeiDescription");
	}
}
