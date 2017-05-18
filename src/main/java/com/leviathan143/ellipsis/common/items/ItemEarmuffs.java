package com.leviathan143.ellipsis.common.items;

import com.leviathan143.ellipsis.Ellipsis.Constants;
import com.leviathan143.ellipsis.client.model.Models;
import com.leviathan143.ellipsis.common.blocks.IMuffler;

import net.minecraft.block.BlockCauldron;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemEarmuffs extends ItemArmor implements IMuffler
{
	private static final String EARMUFFS_BASE_TEXTURE = Constants.MODID + ":textures/armour/earmuffs0.png";
	private static final String EARMUFFS_OVERLAY_TEXTURE = Constants.MODID + ":textures/armour/earmuffs1.png";

	public ItemEarmuffs() 
	{
		super(ArmorMaterial.LEATHER, 0, EntityEquipmentSlot.HEAD);
	}

	@Override
	public boolean shouldMuffleSound(World world, BlockPos mufflerPos,
			ISound sound, SoundCategory category) 
	{
		return true;
	}

	@Override
	public boolean isValidArmor(ItemStack stack, EntityEquipmentSlot armorType, Entity entity) 
	{
		return armorType == EntityEquipmentSlot.HEAD;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, net.minecraft.client.model.ModelBiped _default) 
	{
		return armorSlot == EntityEquipmentSlot.HEAD ? Models.MODEL_EARMUFFS : super.getArmorModel(entityLiving, itemStack, armorSlot, _default);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot armorSlot, String type) 
	{
		return type != null && type.equals("overlay") ? EARMUFFS_OVERLAY_TEXTURE : EARMUFFS_BASE_TEXTURE;
	}

	@Override
	public int getColor(ItemStack stack) 
	{
		if(stack.getTagCompound() != null)
		{
			NBTTagCompound displayTag = stack.getTagCompound().getCompoundTag("display");
			if (displayTag != null && displayTag.hasKey("color", 3))
				return super.getColor(stack);
		}
		return 16777215; //16777215 = white
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		IBlockState blockstate = worldIn.getBlockState(pos);
		if(blockstate.getBlock() == Blocks.CAULDRON && blockstate.getValue(BlockCauldron.LEVEL) > 0)
		{
			((BlockCauldron) blockstate.getBlock()).setWaterLevel(worldIn, pos, blockstate, blockstate.getValue(BlockCauldron.LEVEL) - 1);
			this.setColor(player.getHeldItem(hand), 16777215); //16777215 = white
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.PASS;
	}
}
