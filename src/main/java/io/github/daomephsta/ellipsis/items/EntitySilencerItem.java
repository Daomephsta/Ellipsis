package io.github.daomephsta.ellipsis.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;

public class EntitySilencerItem extends Item 
{
	public EntitySilencerItem(Properties properties)
    {
        super(properties);
    }
	
	@Override
	public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand)
	{
	    entity.setSilent(!entity.isSilent());
		return ActionResultType.sidedSuccess(entity.level.isClientSide);
	}
}
