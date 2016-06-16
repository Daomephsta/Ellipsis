package com.leviathan143.ellipsis.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.audio.ISound;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class BlockDirectionalMuffler extends Block implements IMuffler 
{
	private static final PropertyDirection FACING = PropertyDirection.create("facing");

	public BlockDirectionalMuffler() 
	{
		super(Material.WOOD, MapColor.GRAY);
		setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}

	@Override
	public boolean shouldMuffleSound(World world, BlockPos mufflerPos, ISound sound, SoundCategory category) 
	{
		if(mufflerPos.offset(world.getBlockState(mufflerPos).getValue(FACING)).compareTo(new BlockPos(sound.getXPosF(), sound.getYPosF(), sound.getZPosF())) == 0)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ
			, int meta, EntityLivingBase placer) 
	{
		return this.getDefaultState().withProperty(FACING, BlockPistonBase.getFacingFromEntity(pos, placer));
	}

	@Override
	protected BlockStateContainer createBlockState() 
	{
		return new BlockStateContainer(this, new IProperty[]{FACING});
	}

	@Override
	public IBlockState getStateFromMeta(int meta) 
	{
		EnumFacing facing;
		switch(meta)
		{
		case 0:
			facing = EnumFacing.EAST;
			break;
		case 1:
			facing = EnumFacing.WEST;
			break;
		case 2:
			facing = EnumFacing.SOUTH;
			break;
		case 3:
			facing = EnumFacing.NORTH;
			break;
		case 4:
			facing = EnumFacing.UP;
			break;
		case 5:
			facing = EnumFacing.DOWN;
			break;
		default:
			facing = EnumFacing.NORTH;
			break;
		}
		return this.getDefaultState().withProperty(FACING, facing);
	}

	@Override
	public int getMetaFromState(IBlockState state) 
	{

		switch(state.getValue(FACING))
		{
		case EAST:
			return 0;
		case WEST:
			return 1;
		case SOUTH:
			return 2;
		case NORTH:
			return 3;
		case UP:
			return 4;
		case DOWN:
			return 5;
		default:
			return 3;
		}
	}
}
