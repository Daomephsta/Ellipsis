package com.leviathan143.ellipsis.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;


public class BlockOmnidirectionalMuffler extends Block implements IMuffler 
{
	public BlockOmnidirectionalMuffler() 
	{
		super(Material.iron);
		setDefaultState(this.blockState.getBaseState());
	}

	@Override
	public boolean shouldMuffleSound(World world, BlockPos mufflerPos, ISound sound, SoundCategory category)
	{
		return true;
	}
}
