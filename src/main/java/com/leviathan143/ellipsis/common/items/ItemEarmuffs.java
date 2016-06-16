package com.leviathan143.ellipsis.common.items;

import java.awt.Color;
import java.util.List;

import net.minecraft.block.BlockCauldron;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.leviathan143.ellipsis.Ellipsis.Constants;
import com.leviathan143.ellipsis.client.model.Models;
import com.leviathan143.ellipsis.common.blocks.IMuffler;
import com.leviathan143.ellipsis.common.helpers.StackNBTHelper;

public class ItemEarmuffs extends ItemArmor implements IMuffler, IItemColor
{
	private static final String EARMUFFS_BASE_TEXTURE = Constants.MODID + ":textures/armour/earmuffs0.png";
	private static final String EARMUFFS_OVERLAY_TEXTURE = Constants.MODID + ":textures/armour/earmuffs1.png";
	private static final String TAG_COLOUR = "Colour";

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
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) 
	{
		if (advanced) tooltip.add("Colour: " + this.getColor(stack));
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) 
	{
		IBlockState blockstate = worldIn.getBlockState(pos);
		if(blockstate.getBlock() == Blocks.CAULDRON && blockstate.getValue(BlockCauldron.LEVEL) > 0)
		{
			((BlockCauldron) blockstate.getBlock()).setWaterLevel(worldIn, pos, blockstate, blockstate.getValue(BlockCauldron.LEVEL) - 1);
			this.setColourRGB(stack, 0, 0, 0);
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.PASS;
	}
	
	@Override
	public int getColorFromItemstack(ItemStack stack, int tintIndex) 
	{
		return StackNBTHelper.getTag(stack).getInteger(TAG_COLOUR);
	}
	
	@Override
	public int getColor(ItemStack stack) 
	{
		return StackNBTHelper.getTag(stack).getInteger(TAG_COLOUR);
	}

	public void addColourRGB(ItemStack stack, int r, int g, int b)
	{
		Color col = this.getColourRGB(stack);
		r = adjustColourComponent(col.getRed(), r);
		g = adjustColourComponent(col.getGreen(), g);
		b = adjustColourComponent(col.getBlue(), b);
		this.setColourRGB(stack, r, g, b);
	}

	private int adjustColourComponent(int colComponent, int adjustment)
	{
		if(adjustment > 0) colComponent += adjustment;
		if (colComponent > 255) colComponent = 255;
		return colComponent;
	}

	public void setColourRGB(ItemStack stack, int r, int g, int b)
	{
		StackNBTHelper.getTag(stack).setInteger(TAG_COLOUR, Integer.parseInt(this.convertDecToHex(r) + this.convertDecToHex(g) + this.convertDecToHex(b), 16));
	}

	public Color getColourRGB(ItemStack stack)
	{
		return new Color(StackNBTHelper.getTag(stack).getInteger(TAG_COLOUR));
	}

	public String convertDecToHex(int dec)
	{
		String hex = Integer.toString(dec, 16);
		if(hex.length() < 2) hex = hex + "0";
		return hex;
	}
}
