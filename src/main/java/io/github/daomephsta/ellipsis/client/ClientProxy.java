package io.github.daomephsta.ellipsis.client;

import io.github.daomephsta.ellipsis.client.model.armour.EarmuffsModel;
import io.github.daomephsta.ellipsis.items.EllipsisItems;
import net.minecraft.client.Minecraft;
import net.minecraft.item.IDyeableArmorItem;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientProxy
{
    public static final EarmuffsModel EARMUFFS_MODEL = new EarmuffsModel();

    public static void setup(FMLClientSetupEvent event)
    {
		Minecraft.getInstance().getItemColors().register((stack, tint) -> 
            tint == 0 ? ((IDyeableArmorItem) stack.getItem()).getColor(stack): 0xFFFFFF, 
            EllipsisItems.EARMUFFS);
	}
}