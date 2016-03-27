package com.leviathan143.ellipsis.client;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.items.wrapper.PlayerArmorInvWrapper;

import com.leviathan143.ellipsis.common.blocks.IMuffler;

public class SoundEventHandler 
{	
	@SubscribeEvent
	public void onSoundPlayed(PlaySoundEvent event)
	{
		World world = Minecraft.getMinecraft().theWorld;
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;

		if(world == null || player == null) return;
		if(doesHeadgearMuffle(world, player, event) || doBlocksMuffle(world, event))
		{
			event.result = null;
			return;
		}
	}

	public boolean doesHeadgearMuffle(World world, EntityPlayer player, PlaySoundEvent soundEvent)
	{
		ItemStack headArmour = new PlayerArmorInvWrapper(player.inventory).getStackInSlot(3);
		
		if(headArmour != null && headArmour.getItem() instanceof IMuffler)
		{
			if(((IMuffler) headArmour.getItem()).shouldMuffleSound(world, player.getPosition(), soundEvent.sound, soundEvent.category))
			{
				return true;
			}
		}
		return false;
	}

	public boolean doBlocksMuffle(World world, PlaySoundEvent soundEvent)
	{
		//The position of the block that emitted the sound
		BlockPos soundPos = new BlockPos(soundEvent.sound.getXPosF(), soundEvent.sound.getYPosF(), soundEvent.sound.getZPosF());
		for(EnumFacing direction : EnumFacing.VALUES)
		{
			BlockPos pos = soundPos.offset(direction);
			Block block = world.getBlockState(pos).getBlock();
			if(block instanceof IMuffler )
			{
				if(((IMuffler) block).shouldMuffleSound(world, pos, soundEvent.sound, soundEvent.category))
				{
					soundEvent.result = null;
					return true;
				}
			}
		}
		return false;
	}
}

