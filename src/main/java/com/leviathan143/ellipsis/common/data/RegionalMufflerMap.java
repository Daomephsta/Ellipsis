package com.leviathan143.ellipsis.common.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;
import net.minecraftforge.common.util.Constants.NBT;

import com.leviathan143.ellipsis.Ellipsis.Constants;

public class RegionalMufflerMap extends WorldSavedData 
{	
	private static final String DATANAME = Constants.MODID + "_regionalMufflerMap";
	private static final String MUFFLER_MAP_TAG = "MufflerMap";
	private static final String CHUNK_COORD_TAG = "ChunkCoords";
	private static final String MUFFLER_POS_TAG = "Pos";
	private static final String MUFFLER_LIST_TAG = "Mufflers";

	private HashMap<ChunkCoordIntPair, List<BlockPos>> regionalMufflerMap = new HashMap<ChunkCoordIntPair, List<BlockPos>>();

	public RegionalMufflerMap() 
	{
		super(DATANAME);
	}

	public RegionalMufflerMap(String dataName) 
	{
		super(dataName);
	}

	public static RegionalMufflerMap get(World world)
	{
		MapStorage worldDataStorage = world.getPerWorldStorage();
		RegionalMufflerMap regionalMufflerMap = (RegionalMufflerMap) worldDataStorage.loadData(RegionalMufflerMap.class, DATANAME);
		if(regionalMufflerMap == null)
		{
			regionalMufflerMap = new RegionalMufflerMap();
			worldDataStorage.setData(DATANAME, regionalMufflerMap);
		}
		return regionalMufflerMap;
	}

	public List<BlockPos> getMufflers(ChunkCoordIntPair chunkCoords)
	{
		return regionalMufflerMap.get(chunkCoords);
	}

	public List<BlockPos> findMufflers(ChunkCoordIntPair centralChunkCoords)
	{
		ChunkCoordIntPair chunkCoords = centralChunkCoords;
		List<BlockPos> mufflers = new ArrayList<BlockPos>();
		for(int x = -1; x < 2; x++)
		{
			for(int z = -1; z < 2; z++)
			{
				chunkCoords = new ChunkCoordIntPair(centralChunkCoords.chunkXPos + x, centralChunkCoords.chunkZPos + z);
				if (regionalMufflerMap.containsKey(chunkCoords)) mufflers.addAll(regionalMufflerMap.get(chunkCoords));
			}
		}
		return mufflers;
	}

	public void addMuffler(World world, BlockPos mufflerPos)
	{
		ChunkCoordIntPair chunkCoords = world.getChunkFromBlockCoords(mufflerPos).getChunkCoordIntPair();
		List<BlockPos> mufflers;
		if(regionalMufflerMap.containsKey(chunkCoords)) mufflers = regionalMufflerMap.get(chunkCoords);
		else mufflers = new ArrayList<BlockPos>();
		if(!this.isMufflerInList(mufflerPos, mufflers))
		{
			mufflers.add(mufflerPos);
			regionalMufflerMap.put(chunkCoords, mufflers);
			this.markDirty();
		}
	}

	public void removeMuffler(World world, BlockPos mufflerPos)
	{
		ChunkCoordIntPair chunkCoords = world.getChunkFromBlockCoords(mufflerPos).getChunkCoordIntPair();
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
			else regionalMufflerMap.remove(chunkCoords);
			this.markDirty();
		}
	}

	private boolean isMufflerInList(BlockPos mufflerPos, List<BlockPos> mufflerList)
	{
		for(BlockPos muffler : mufflerList)
		{
			if (mufflerPos.compareTo(muffler) == 0) return true;;
		}
		return false;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) 
	{
		if(!nbt.hasKey(MUFFLER_MAP_TAG)) return;
		NBTTagList regionalMufflerMapTag = nbt.getTagList(MUFFLER_MAP_TAG, NBT.TAG_COMPOUND);
		for(int t = 0; t < regionalMufflerMapTag.tagCount(); t++)
		{

			NBTTagCompound entryTag = regionalMufflerMapTag.getCompoundTagAt(t);
			int[] chunkCoords = entryTag.getIntArray(CHUNK_COORD_TAG);
			NBTTagList mufflerListTag = entryTag.getTagList(MUFFLER_LIST_TAG, NBT.TAG_COMPOUND);
			List<BlockPos> mufflerList = new ArrayList<BlockPos>();
			for(int m = 0; m < mufflerListTag.tagCount(); m++)
			{
				NBTTagCompound	mufflerPosTag = mufflerListTag.getCompoundTagAt(m);
				int[] blockCoords = mufflerPosTag.getIntArray(MUFFLER_POS_TAG);
				mufflerList.add(new BlockPos(blockCoords[0], blockCoords[1], blockCoords[2]));
			}
			regionalMufflerMap.put(new ChunkCoordIntPair(chunkCoords[0], chunkCoords[1]), mufflerList);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) 
	{
		NBTTagList regionalMufflerMapTag = new NBTTagList();
		for(Iterator<Entry<ChunkCoordIntPair, List<BlockPos>>> iter = this.regionalMufflerMap.entrySet().iterator(); iter.hasNext();)
		{
			NBTTagCompound entryTag = new NBTTagCompound();
			NBTTagList mufflerListTag = new NBTTagList();
			NBTTagCompound mufflerPosTag;
			Entry<ChunkCoordIntPair, List<BlockPos>> mufflersInChunk = iter.next();
			//Add chunk coords to entry tag
			entryTag.setIntArray(CHUNK_COORD_TAG, new int[] {mufflersInChunk.getKey().chunkXPos, mufflersInChunk.getKey().chunkZPos});
			//Create list of coords of mufflers in the chunk 
			for(BlockPos mufflerPos : mufflersInChunk.getValue())
			{
				mufflerPosTag = new NBTTagCompound();
				mufflerPosTag.setIntArray(MUFFLER_POS_TAG, new int[] {mufflerPos.getX(), mufflerPos.getY(), mufflerPos.getZ()});
				mufflerListTag.appendTag(mufflerPosTag);
			}
			//Add list of coords of mufflers in the chunk to entry
			entryTag.setTag(MUFFLER_LIST_TAG, mufflerListTag);
			//Add entry to map
			regionalMufflerMapTag.appendTag(entryTag);
		}
		//Write map to NBT
		nbt.setTag(MUFFLER_MAP_TAG, regionalMufflerMapTag);
	}
}
