package com.leviathan143.ellipsis.common.items;

import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import com.leviathan143.ellipsis.Ellipsis.Constants;
import com.leviathan143.ellipsis.client.model.armour.ModelEarmuffs;
import com.leviathan143.ellipsis.common.blocks.IMuffler;

public class ItemEarmuffs extends ItemArmor implements IMuffler
{
	public ItemEarmuffs() 
	{
		super(ArmorMaterial.LEATHER, 0, 0);
	}
	
	private ModelEarmuffs modelEarmuffs = new ModelEarmuffs();

	private static final String EARMUFFS_TEXTURE = Constants.MODID + ":textures/armour/earmuffs.png";

	@Override
	public boolean shouldMuffleSound(World world, BlockPos mufflerPos,
			ISound sound, SoundCategory category) 
	{
		return true;
	}
	
	@Override
	public boolean isValidArmor(ItemStack stack, int armorType, Entity entity) 
	{
		if(armorType == 0) return true;
		return false;
	}

	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot, ModelBiped _default) 
	{
		if(armorSlot == 4)
		{
			return modelEarmuffs;
		}
		return super.getArmorModel(entityLiving, itemStack, armorSlot, _default);
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) 
	{
		return EARMUFFS_TEXTURE;
	}
}
