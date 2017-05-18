package com.leviathan143.ellipsis.common.packets;

import com.leviathan143.ellipsis.common.capability.CapabilityMufflerMap;
import com.leviathan143.ellipsis.common.capability.RegionalMufflerMap;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.*;

public class PacketQueryServerMap implements IMessage 
{
	private World world;
	private ChunkPos chunkPos;
	
	public PacketQueryServerMap() {}

	public PacketQueryServerMap(World world, ChunkPos chunkPos) 
	{
		this.world = world;
		this.chunkPos = chunkPos;
	}

	public static class PacketQueryServerMapHandler implements IMessageHandler<PacketQueryServerMap, IMessage>
	{
		@Override
		public IMessage onMessage(final PacketQueryServerMap message, final MessageContext ctx) 
		{
			FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(new Runnable() 
			{
				@Override
				public void run() 
				{
					processMessage(message, ctx);
				}
			});
			return null;
		}		

		private void processMessage(PacketQueryServerMap message, MessageContext ctx)
		{
			RegionalMufflerMap mufflerMap = CapabilityMufflerMap.get(message.world);
			if(mufflerMap.doesChunkHaveMufflers(message.chunkPos))
			{
				PacketHandler.CHANNEL.sendTo(new PacketSyncChunkMufflers(message.chunkPos, mufflerMap.getMufflersInChunk(message.chunkPos)
						, mufflerMap.isChunkDirty(message.chunkPos)), ctx.getServerHandler().playerEntity);
			}
		}
	}

	@Override
	public void fromBytes(ByteBuf buf) 
	{
		this.world = DimensionManager.getWorld(buf.readInt());
		this.chunkPos = new ChunkPos(buf.readInt(), buf.readInt());
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(world.provider.getDimension());
		buf.writeInt(chunkPos.chunkXPos);
		buf.writeInt(chunkPos.chunkZPos);
	}
}
