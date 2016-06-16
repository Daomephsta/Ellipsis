package com.leviathan143.ellipsis.common.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.leviathan143.ellipsis.common.blocks.EllipsisBlocks;
import com.leviathan143.ellipsis.common.items.EllipsisItems;


public class EllipsisCrafting 
{
	public static void register()
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(EllipsisItems.earmuffs)
			, " S ", "S S", "W W", 'S', "stickWood", 'W', new ItemStack(Blocks.WOOL, 1, OreDictionary.WILDCARD_VALUE)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(EllipsisBlocks.omnidirectionalMuffler)
			, "P P", " W ", "P P", 'P', "plankWood", 'W', new ItemStack(Blocks.WOOL, 1, OreDictionary.WILDCARD_VALUE)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(EllipsisBlocks.directionalMuffler)
			, "PPP", "PW ", "PPP", 'P', "plankWood", 'W', new ItemStack(Blocks.WOOL, 1, OreDictionary.WILDCARD_VALUE)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(EllipsisBlocks.regionalMuffler)
		, "I I", " W ", "I I", 'I', "ingotIron", 'W', new ItemStack(Blocks.WOOL, 1, OreDictionary.WILDCARD_VALUE)));
		GameRegistry.addRecipe(new RecipeEarmuffDying());
	}
}
