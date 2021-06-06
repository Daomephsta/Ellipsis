package io.github.daomephsta.ellipsis.items;

import io.github.daomephsta.ellipsis.Ellipsis;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Ellipsis.MOD_ID)
@Mod.EventBusSubscriber(modid = Ellipsis.MOD_ID, bus = Bus.MOD)
public class EllipsisItems 
{
	public static final EarmuffsItem EARMUFFS = null;
	public static final EntitySilencerItem ENTITY_SILENCER = null;
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) 
	{
        event.getRegistry().registerAll(
            Ellipsis.withId("earmuffs", new EarmuffsItem(
                new Item.Properties().tab(Ellipsis.ITEM_GROUP))),
            Ellipsis.withId("entity_silencer", new EntitySilencerItem(
                new Item.Properties().tab(Ellipsis.ITEM_GROUP)))
        );
	}
}
