package com.leviathan143.ellipsis.common.capability;

import java.util.List;

import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;

public interface IMufflerMap
{
	List<BlockPos> findMufflers(ChunkPos centralChunkCoords);
	
	void removeMuffler(World world, BlockPos mufflerPos);

	void addMuffler(World world, BlockPos mufflerPos);
	
	BlockPos[] getAllMufflers();
	
	boolean doesChunkHaveMufflers(ChunkPos chunkPos);
	
	boolean isChunkDirty(ChunkPos chunkPos);
	
	void setChunkClean(ChunkPos chunkPos);

	void setChunkDirty(ChunkPos chunkPos);
	
	void deserializeChunkMufflers(ChunkPos chunkPos, NBTTagList mufflerListTag);
	
	NBTTagList serializeChunkMufflers(ChunkPos chunkPos);
	
	void setMufflersInChunk(ChunkPos chunkPos, List<BlockPos> mufflers);
	
	List<BlockPos> getMufflersInChunk(ChunkPos chunkPos);
}
