package com.leviathan143.ellipsis.common.capability;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;

public class RegionalMufflerMap
{		
	public static final String MUFFLER_LIST_TAG = "Mufflers";
	private static final String MUFFLER_POS_TAG = "Pos";

	private HashMap<ChunkPos, List<BlockPos>> regionalMufflerMap = new HashMap<ChunkPos, List<BlockPos>>();
	//Chunks been unloaded and need to be removed from the map TODO: Get this working with chunks that have lost all their mufflers
	private List<ChunkPos> dirtyChunks = new ArrayList<ChunkPos>();

	public List<BlockPos> findMufflers(ChunkPos centralChunkCoords)
	{
		ChunkPos chunkCoords = centralChunkCoords;
		List<BlockPos> mufflers = new ArrayList<BlockPos>();
		for(int x = -1; x < 2; x++)
		{
			for(int z = -1; z < 2; z++)
			{
				chunkCoords = new ChunkPos(centralChunkCoords.chunkXPos + x, centralChunkCoords.chunkZPos + z);
				if (regionalMufflerMap.containsKey(chunkCoords)) mufflers.addAll(regionalMufflerMap.get(chunkCoords));
			}
		}
		return mufflers;
	}

	public void setMufflersInChunk(ChunkPos chunkPos, List<BlockPos> mufflers) 
	{
		regionalMufflerMap.put(chunkPos, mufflers);
	}

	public List<BlockPos> getMufflersInChunk(ChunkPos chunkPos) 
	{
		return regionalMufflerMap.containsKey(chunkPos) ? regionalMufflerMap.get(chunkPos) : Collections.<BlockPos>emptyList();
	}

	public void addMuffler(World world, BlockPos mufflerPos)
	{
		ChunkPos chunkCoords = world.getChunkFromBlockCoords(mufflerPos).getPos();
		List<BlockPos> mufflers;
		if(regionalMufflerMap.containsKey(chunkCoords)) mufflers = regionalMufflerMap.get(chunkCoords);
		else
		{
			mufflers = new ArrayList<BlockPos>();
			world.getChunkFromBlockCoords(mufflerPos);
		}
		if(!this.isMufflerInList(mufflerPos, mufflers))
		{
			mufflers.add(mufflerPos);
			regionalMufflerMap.put(chunkCoords, mufflers);
		}
	}

	public void removeMuffler(World world, BlockPos mufflerPos)
	{
		ChunkPos chunkCoords = world.getChunkFromBlockCoords(mufflerPos).getPos();
		List<BlockPos> mufflers;
		if(regionalMufflerMap.containsKey(chunkCoords))
		{
			mufflers = regionalMufflerMap.get(chunkCoords);
			for(Iterator<BlockPos> iter = mufflers.iterator(); iter.hasNext();)
			{	
				BlockPos muffler = iter.next();
				if (mufflerPos.compareTo(muffler) == 0) iter.remove();
			}
			if(!mufflers.isEmpty()) regionalMufflerMap.put(chunkCoords, mufflers);
			else
			{
				regionalMufflerMap.remove(chunkCoords);
				//This ensures that it will be removed clientside as well
				setChunkDirty(chunkCoords);
			}
		}
	}
	
	public BlockPos[] getAllMufflers() 
	{
		List<BlockPos> allMufflers = new ArrayList<BlockPos>();
		for(List<BlockPos> chunkMufflers : regionalMufflerMap.values())
		{
			allMufflers.addAll(chunkMufflers);
		}
		return allMufflers.toArray(new BlockPos[0]);
	}

	private boolean isMufflerInList(BlockPos mufflerPos, List<BlockPos> mufflerList)
	{
		for(BlockPos muffler : mufflerList)
		{
			if (mufflerPos.compareTo(muffler) == 0) return true;;
		}
		return false;
	}
	
	public boolean doesChunkHaveMufflers(ChunkPos chunkPos) 
	{
		return regionalMufflerMap.containsKey(chunkPos);
	}
	
	public boolean isChunkDirty(ChunkPos chunkPos) 
	{
		return dirtyChunks.contains(chunkPos);
	}
	
	public void setChunkClean(ChunkPos chunkPos) 
	{
		dirtyChunks.remove(chunkPos);
	}
	
	public void setChunkDirty(ChunkPos chunkPos) 
	{
		dirtyChunks.add(chunkPos);
		regionalMufflerMap.remove(chunkPos);
	}

	public NBTTagList serializeChunkMufflers(ChunkPos chunkPos)
	{
		NBTTagList mufflerListTag = new NBTTagList();
		NBTTagCompound mufflerPosTag;
		//Create list of coords of mufflers in the chunk 
		for(BlockPos mufflerPos : regionalMufflerMap.get(chunkPos))
		{
			mufflerPosTag = new NBTTagCompound();
			mufflerPosTag.setIntArray(MUFFLER_POS_TAG, new int[] {mufflerPos.getX(), mufflerPos.getY(), mufflerPos.getZ()});
			mufflerListTag.appendTag(mufflerPosTag);
		}
		//Add list of coords of mufflers in the chunk to entry
		return mufflerListTag;
	}
	
	public void deserializeChunkMufflers(ChunkPos chunkPos, NBTTagList mufflerListTag)
	{
		List<BlockPos> mufflerList = new ArrayList<BlockPos>();
		for(int m = 0; m < mufflerListTag.tagCount(); m++)
		{
			NBTTagCompound	mufflerPosTag = mufflerListTag.getCompoundTagAt(m);
			int[] blockCoords = mufflerPosTag.getIntArray(MUFFLER_POS_TAG);
			mufflerList.add(new BlockPos(blockCoords[0], blockCoords[1], blockCoords[2]));
		}
		regionalMufflerMap.put(chunkPos, mufflerList);
	}
}
