package io.github.daomephsta.ellipsis.blocks;

import net.minecraft.client.audio.ISound;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public interface IMuffler 
{
    public boolean shouldMuffleSound(World world, double mufflerX, double mufflerY, double mufflerZ, 
        ISound sound, SoundCategory category);
}
