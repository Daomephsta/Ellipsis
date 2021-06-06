package io.github.daomephsta.ellipsis.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.audio.ISound;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;


public class OmnidirectionalMufflerBlock extends Block implements IMuffler 
{
	public OmnidirectionalMufflerBlock(Properties properties)
    {
        super(properties);
    }

    @Override
	public boolean shouldMuffleSound(World world, double mufflerX, double mufflerY, double mufflerZ, ISound sound, SoundCategory category)
	{
		return true;
	}
}
