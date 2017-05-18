package com.leviathan143.ellipsis.common.packets;

import java.util.ArrayList;
import java.util.List;

import com.leviathan143.ellipsis.Ellipsis;
import com.leviathan143.ellipsis.common.capability.RegionalMufflerMap;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraftforge.fml.common.network.simpleimpl.*;

public class PacketSyncChunkMufflers implements IMessage 
{
	private ChunkPos chunkPos;
	private List<BlockPos> mufflers;
	private boolean isDirty;

	public PacketSyncChunkMufflers() {}

	public PacketSyncChunkMufflers(ChunkPos chunkPos, List<BlockPos> mufflers, boolean isDirty) 
	{
		this.chunkPos = chunkPos;
		this.mufflers = mufflers;
		this.isDirty = isDirty;
	}

	public static class PacketSyncChunkMufflersHandler implements IMessageHandler<PacketSyncChunkMufflers, IMessage>
	{
		@Override
		public IMessage onMessage(final PacketSyncChunkMufflers message, final MessageContext ctx) 
		{
		    	Minecraft.getMinecraft().addScheduledTask(new Runnable() 
			{
				@Override
				public void run() 
				{
					processMessage(message, ctx);
				}
			});
			return null;
		}
		
		private void processMessage(PacketSyncChunkMufflers message, MessageContext ctx)
		{
			RegionalMufflerMap mufflerMap = Ellipsis.proxy.getMufflerMap();
			if(message.isDirty)
			{
				mufflerMap.setChunkDirty(message.chunkPos);
			}
			else
				mufflerMap.setMufflersInChunk(message.chunkPos, message.mufflers);
		}
	}

	@Override
	public void fromBytes(ByteBuf buf) 
	{
		this.chunkPos = new ChunkPos(buf.readInt(), buf.readInt());
		int mufflersSize = buf.readInt();
		mufflers = new ArrayList<BlockPos>(mufflersSize);
		for(int m = 0; m < mufflersSize; m++)
		{
			mufflers.add(BlockPos.fromLong(buf.readLong()));
		}
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(chunkPos.chunkXPos);
		buf.writeInt(chunkPos.chunkZPos);
		buf.writeInt(mufflers.size());
		for(BlockPos muffler : mufflers)
		{
			buf.writeLong(muffler.toLong());
		}
	}
}
