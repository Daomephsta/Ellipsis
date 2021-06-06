package io.github.daomephsta.ellipsis.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.audio.ISound;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

//TODO Reimplement using rtree-3i-lite
public class RegionalMufflerBlock extends Block implements IMuffler
{	
	public static final IntegerProperty RADIUS = IntegerProperty.create("radius", 1, 8); 
	
	public RegionalMufflerBlock(Properties properties) 
	{
		super(properties);
		registerDefaultState(defaultBlockState().setValue(RADIUS, 1));
	}
	
//  TODO
//	@Override
//	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) 
//	{
//		RegionalMufflerMap.addMuffler(world, pos);
//	}
	
	@Override
	public void destroy(IWorld world, BlockPos pos, BlockState state)
	{
	    // TODO
	}
	
	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, 
	    PlayerEntity player, Hand hand, BlockRayTraceResult clickPos)
	{
        if(!player.getItemInHand(hand).isEmpty() || (hand == Hand.OFF_HAND && player.getMainHandItem().isEmpty())) 
            return ActionResultType.PASS;
        int nextRadius = state.getValue(RADIUS) + (player.isCrouching() ? -1 : 1);
        if (nextRadius > 8)
            nextRadius = 1;
        else if (nextRadius < 1)
            nextRadius = 8;
        world.setBlock(pos, state.setValue(RADIUS, nextRadius), 7);
	    return ActionResultType.sidedSuccess(world.isClientSide());
	}
    
	@Override
	public boolean shouldMuffleSound(World world, double mufflerX, double mufflerY, double mufflerZ, 
	    ISound sound, SoundCategory category)
	{
	    if(category.equals(SoundCategory.RECORDS)) 
	        return false;
        // TODO
	    return false;
	}
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder)
	{
	    builder.add(RADIUS);
	}
}
