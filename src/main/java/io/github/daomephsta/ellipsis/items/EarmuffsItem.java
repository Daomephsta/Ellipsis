package io.github.daomephsta.ellipsis.items;

import io.github.daomephsta.ellipsis.Ellipsis;
import io.github.daomephsta.ellipsis.blocks.IMuffler;
import io.github.daomephsta.ellipsis.client.ClientProxy;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CauldronBlock;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.DyeableArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EarmuffsItem extends DyeableArmorItem implements IMuffler
{
	private static final String EARMUFFS_BASE_TEXTURE = Ellipsis.MOD_ID + ":textures/armour/earmuffs0.png";
	private static final String EARMUFFS_OVERLAY_TEXTURE = Ellipsis.MOD_ID + ":textures/armour/earmuffs1.png";

	public EarmuffsItem(Properties properties) 
	{
		super(ArmorMaterial.LEATHER, EquipmentSlotType.HEAD, properties);
	}

    @Override
	public boolean shouldMuffleSound(World world, double mufflerX, double mufflerY, double mufflerZ, 
	    ISound sound, SoundCategory category) 
	{
		return true;
	}

	@SuppressWarnings("unchecked")
    @OnlyIn(Dist.CLIENT)
	@Override
	public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, 
	    ItemStack itemStack, EquipmentSlotType armorSlot, A defaultModel) 
	{
		return armorSlot == EquipmentSlotType.HEAD 
		    ? (A) ClientProxy.EARMUFFS_MODEL 
		    : super.getArmorModel(entityLiving, itemStack, armorSlot, defaultModel);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType armorSlot, String type) 
	{
		return type != null && type.equals("overlay") ? EARMUFFS_OVERLAY_TEXTURE : EARMUFFS_BASE_TEXTURE;
	}

    private static final int WHITE_LEATHER_DELTA = 0xFFFFFF - 0xA06540;
	@Override
	public int getColor(ItemStack stack)
	{
	    return super.getColor(stack) + WHITE_LEATHER_DELTA;
	}
	
	@Override
	public ActionResultType useOn(ItemUseContext context)
	{   
		BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
		if(blockstate.getBlock() == Blocks.CAULDRON && blockstate.getValue(CauldronBlock.LEVEL) > 0)
		{
			((CauldronBlock) blockstate.getBlock()).setWaterLevel(context.getLevel(), context.getClickedPos(), 
			    blockstate, blockstate.getValue(CauldronBlock.LEVEL) - 1);
			this.setColor(context.getItemInHand(), 0xFFFFFF);
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.PASS;
	}
}
