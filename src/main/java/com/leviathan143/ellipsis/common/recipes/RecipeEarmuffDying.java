package com.leviathan143.ellipsis.common.recipes;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.oredict.OreDictionary;

import com.leviathan143.ellipsis.common.items.EllipsisItems;
import com.leviathan143.ellipsis.common.items.ItemEarmuffs;

public class RecipeEarmuffDying implements IRecipe 
{
	private static final int COLOURADJUSTMENT = 10;
	
	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) 
	{
		boolean earmuffsFound = false;
		boolean dyeFound = false;
		for(int s = 0; s < inv.getSizeInventory(); s++)
		{
			ItemStack slot = inv.getStackInSlot(s);
			if (slot == null) continue;
			if(slot.getItem() != EllipsisItems.earmuffs && !isRBGDye(slot))
				return false;
			if(slot.getItem() == EllipsisItems.earmuffs)
			{
				if(earmuffsFound) 
					return false;
				earmuffsFound = true;
			}
			else if(isRBGDye(slot)) dyeFound = true;
		}
		return earmuffsFound && dyeFound;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) 
	{
		ItemStack earmuffs = null;
		ItemStack dye = null;
		for(int s = 0; s < inv.getSizeInventory(); s++)
		{
			ItemStack slot = inv.getStackInSlot(s);
			if (slot == null) continue;
			if(slot.getItem() == EllipsisItems.earmuffs)
			{
				earmuffs = slot.copy();
			}
			if(isRBGDye(slot))
			{
				dye = slot.copy();
			}
		}
		int r = 0, g = 0, b = 0;
		if(dye.getItemDamage() == EnumDyeColor.RED.getDyeDamage()) r += COLOURADJUSTMENT;
		if(dye.getItemDamage() == EnumDyeColor.GREEN.getDyeDamage()) g += COLOURADJUSTMENT;
		if(dye.getItemDamage() == EnumDyeColor.BLUE.getDyeDamage()) b += COLOURADJUSTMENT;
		((ItemEarmuffs) earmuffs.getItem()).addColourRGB(earmuffs, r, g, b);;
		return earmuffs;
	}
	
	private boolean isRBGDye(ItemStack stack)
	{
		return OreDictionary.containsMatch(false, OreDictionary.getOres("dyeRed"), stack) 
				|| OreDictionary.containsMatch(false, OreDictionary.getOres("dyeBlue"), stack) 
				|| OreDictionary.containsMatch(false, OreDictionary.getOres("dyeGreen"), stack) || stack.getItem() == Items.WATER_BUCKET;
	}

	@Override
	public int getRecipeSize() 
	{
		return 10;
	}

	@Override
	public ItemStack getRecipeOutput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack[] getRemainingItems(InventoryCrafting inv) 
	{
		ItemStack[] consumedItems = new ItemStack[inv.getSizeInventory()];
		for(int slot = 0; slot < inv.getSizeInventory(); slot++)
		{
			ItemStack stack = inv.getStackInSlot(slot);
			if(stack != null && stack.getItem().hasContainerItem(stack))
			{
				consumedItems[slot] = ForgeHooks.getContainerItem(stack);
			}
		}
		return consumedItems;
	}
}
