package com.leviathan143.ellipsis.common.blocks;

import net.minecraft.client.audio.ISound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IMuffler 
{
	public boolean shouldMuffleSound(World world, BlockPos mufflerPos, ISound sound, SoundCategory category);
}
