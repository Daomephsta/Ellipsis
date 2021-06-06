package io.github.daomephsta.ellipsis.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.client.audio.ISound;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class DirectionalMufflerBlock extends DirectionalBlock implements IMuffler 
{
	public DirectionalMufflerBlock(Properties properties) 
	{
		super(properties);
		registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));
	}

	@Override
	public boolean shouldMuffleSound(World world, double mufflerX, double mufflerY, double mufflerZ, ISound sound, SoundCategory category)
	{
		BlockPos mufflerPos = new BlockPos(mufflerX, mufflerY, mufflerZ);
        Direction facing = world.getBlockState(mufflerPos).getValue(FACING);
        BlockPos muffled = mufflerPos.relative(facing);
        double dx = sound.getX() - muffled.getX();
        double dy = sound.getY() - muffled.getY();
        double dz = sound.getZ() - muffled.getZ();
        return 0.0 <= dx && dx <= 1.0 && 
               0.0 <= dy && dy <= 1.0 &&
               0.0 <= dz && dz <= 1.0;
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context)
	{
	    return defaultBlockState().setValue(FACING, context.getClickedFace().getOpposite());
	}
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder)
	{
	    builder.add(FACING);
	}
}
