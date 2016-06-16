package com.leviathan143.ellipsis.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.audio.ISound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class BlockOmnidirectionalMuffler extends Block implements IMuffler 
{
	public BlockOmnidirectionalMuffler() 
	{
		super(Material.WOOD, MapColor.GRAY);
		setDefaultState(this.blockState.getBaseState());
	}

	@Override
	public boolean shouldMuffleSound(World world, BlockPos mufflerPos, ISound sound, SoundCategory category)
	{
		return true;
	}
}
