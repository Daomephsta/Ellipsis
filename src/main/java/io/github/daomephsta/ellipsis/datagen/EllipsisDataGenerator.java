package io.github.daomephsta.ellipsis.datagen;

import io.github.daomephsta.ellipsis.Ellipsis;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Ellipsis.MOD_ID, bus = Bus.MOD)
public class EllipsisDataGenerator 
{	
    @SubscribeEvent
	public static void generateData(GatherDataEvent event)
	{
        DataGenerator generator = event.getGenerator();
	    if (event.includeServer())
	    {
	        generator.addProvider(new EllipsisRecipeProvider(generator));
	        generator.addProvider(new EllipsisLootTableProvider(generator));
	    }
	    if (event.includeClient())
	    {
	        generator.addProvider(
	            new EllipsisItemModelProvider(generator, Ellipsis.MOD_ID, event.getExistingFileHelper()));
	        generator.addProvider(
	            new EllipsisBlockModelProvider(generator, Ellipsis.MOD_ID, event.getExistingFileHelper()));
	    }
	}
}
