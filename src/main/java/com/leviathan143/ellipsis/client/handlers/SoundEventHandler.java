package com.leviathan143.ellipsis.client.handlers;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.items.wrapper.PlayerArmorInvWrapper;

import com.leviathan143.ellipsis.common.blocks.BlockRegionalMuffler;
import com.leviathan143.ellipsis.common.blocks.IMuffler;
import com.leviathan143.ellipsis.common.data.RegionalMufflerMap;

public class SoundEventHandler 
{	
	@SubscribeEvent
	public void onSoundPlayed(PlaySoundEvent event)
	{
		World world = Minecraft.getMinecraft().theWorld;
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;

		if(world == null || player == null) return;
		if(doesHeadgearMuffle(world, player, event.getSound()) || doBlocksMuffle(world, event))
		{
			event.setResultSound(null);
			return;
		}
	}

	@SubscribeEvent
	public void onEntitySoundPlayed(PlaySoundAtEntityEvent event)
	{ 

	}

	public boolean doesHeadgearMuffle(World world, EntityPlayer player, ISound iSound)
	{
		ItemStack headArmour = new PlayerArmorInvWrapper(player.inventory).getStackInSlot(3);

		if(headArmour != null && headArmour.getItem() instanceof IMuffler)
		{
			if(((IMuffler) headArmour.getItem()).shouldMuffleSound(world, player.getPosition(), iSound, iSound.getCategory()))
			{
				return true;
			}
		}
		return false;
	}

	public boolean doBlocksMuffle(World world, PlaySoundEvent soundEvent)
	{
		//The position of the block that emitted the sound
		BlockPos soundPos = new BlockPos(soundEvent.getSound().getXPosF(), soundEvent.getSound().getYPosF(), soundEvent.getSound().getZPosF());
		for(EnumFacing direction : EnumFacing.VALUES)
		{
			BlockPos pos = soundPos.offset(direction);
			Block block = world.getBlockState(pos).getBlock();
			if(block instanceof IMuffler )
			{
				if(((IMuffler) block).shouldMuffleSound(world, pos, soundEvent.getSound(), soundEvent.getSound().getCategory()))
				{
					return true;
				}
			}
		}
		return isRegionMuffled(world, soundPos, soundEvent);
	}

	public boolean isRegionMuffled(World world, BlockPos soundPos, PlaySoundEvent soundEvent)
	{
		RegionalMufflerMap mufflerMap = RegionalMufflerMap.get(world);
		List<BlockPos> mufflers = mufflerMap.findMufflers(world.getChunkFromBlockCoords(soundPos).getChunkCoordIntPair());
		if(mufflers != null)
		{
			for(BlockPos mufflerPos : mufflers)
			{
				Block block = world.getBlockState(mufflerPos).getBlock();
				if(block instanceof BlockRegionalMuffler)
				{
					if(((BlockRegionalMuffler) block).shouldMuffleSound(world, mufflerPos, soundEvent.getSound(), soundEvent.getSound().getCategory()))
					{
						return true;
					}
				}
			}
		}
		return false;
	}
}

