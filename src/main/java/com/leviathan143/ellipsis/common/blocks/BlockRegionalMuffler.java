package com.leviathan143.ellipsis.common.blocks;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.audio.ISound;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.leviathan143.ellipsis.common.data.RegionalMufflerMap;

public class BlockRegionalMuffler extends Block
{	
	private static final PropertyInteger REGION_RADIUS = PropertyInteger.create("radius", 1, 8); 
	
	public BlockRegionalMuffler() 
	{
		super(Material.WOOD, MapColor.GRAY);
		setDefaultState(this.blockState.getBaseState().withProperty(REGION_RADIUS, 1));
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state,
			EntityLivingBase placer, ItemStack stack) 
	{
		RegionalMufflerMap.get(worldIn).addMuffler(worldIn, pos);
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) 
	{
		RegionalMufflerMap.get(worldIn).removeMuffler(worldIn, pos);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) 
	{
		if(heldItem != null) return false;
		int radius = state.getValue(REGION_RADIUS);
		if(playerIn.isSneaking()) radius = radius > 1 ? radius - 1 : 8;
		else radius = radius < 8 ? radius + 1 : 1;
		worldIn.setBlockState(pos, this.getDefaultState().withProperty(REGION_RADIUS, radius));
		return true;
	}
	
	@Override
	protected BlockStateContainer createBlockState() 
	{
		return new BlockStateContainer(this, new IProperty[]{REGION_RADIUS});
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) 
	{
		return this.getDefaultState().withProperty(REGION_RADIUS, meta + 1);
	}

	@Override
	public int getMetaFromState(IBlockState state) 
	{
		int radius = state.getValue(REGION_RADIUS);
		return radius - 1;
	}
	
	public boolean shouldMuffleSound(World world, BlockPos mufflerPos,
			ISound sound, SoundCategory category) 
	{
		if(category.equals(SoundCategory.RECORDS)) return false;
		return Math.sqrt(mufflerPos.distanceSqToCenter(sound.getXPosF(), sound.getYPosF(), sound.getZPosF())) <= world.getBlockState(mufflerPos).getValue(REGION_RADIUS);
	}
}
