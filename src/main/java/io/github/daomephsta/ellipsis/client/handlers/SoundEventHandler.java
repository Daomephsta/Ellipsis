package io.github.daomephsta.ellipsis.client.handlers;

import io.github.daomephsta.ellipsis.Ellipsis;
import io.github.daomephsta.ellipsis.blocks.IMuffler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.wrapper.PlayerArmorInvWrapper;

@Mod.EventBusSubscriber(modid = Ellipsis.MOD_ID)
public class SoundEventHandler 
{	
	@SubscribeEvent
	public static void onSoundPlayed(PlaySoundEvent event)
	{
		World world = Minecraft.getInstance().level;
		PlayerEntity player = Minecraft.getInstance().player;

		if(world == null || player == null) return;
		if(doesHeadgearMuffle(world, player, event.getSound()) || doBlocksMuffle(world, event))
		{
			event.setResultSound(null);
			return;
		}
	}

	public static boolean doesHeadgearMuffle(World world, PlayerEntity player, ISound sound)
	{
		ItemStack headArmour = new PlayerArmorInvWrapper(player.inventory).getStackInSlot(3);

		if (headArmour.getItem() instanceof IMuffler && ((IMuffler) headArmour.getItem()).shouldMuffleSound(world,
				player.getX(), player.getY(), player.getZ(), sound, sound.getSource()))
			return true;
		return false;
	}

	public static boolean doBlocksMuffle(World world, PlaySoundEvent event)
	{
		//The position of the block that emitted the sound
		ISound sound = event.getSound();
        BlockPos soundPos = new BlockPos(sound.getX(), sound.getY(), sound.getZ());
		for(Direction direction : Direction.values())
		{
			BlockPos pos = soundPos.relative(direction);
			Block block = world.getBlockState(pos).getBlock();
			if (block instanceof IMuffler && ((IMuffler) block).shouldMuffleSound(world, 
			    pos.getX(), pos.getY(), pos.getZ(), sound, sound.getSource()))
            {
                return true;
            }
		}
		return isRegionMuffled(world, soundPos, event);
	}

	public static boolean isRegionMuffled(World world, BlockPos soundPos, PlaySoundEvent soundEvent)
	{
		// TODO reimplement
		return false;
	}
}

