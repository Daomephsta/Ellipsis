package io.github.daomephsta.ellipsis;

import io.github.daomephsta.ellipsis.blocks.EllipsisBlocks;
import io.github.daomephsta.ellipsis.client.ClientProxy;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistryEntry;

@Mod(Ellipsis.MOD_ID)
public class Ellipsis
{
	public static final String MOD_ID = "ellipsis";
	public static final ItemGroup ITEM_GROUP = new ItemGroup(Ellipsis.MOD_ID)
	{
		@Override
		public ItemStack makeIcon()
		{
			return new ItemStack(EllipsisBlocks.OMNIDIRECTIONAL_MUFFLER);
		}
	};
	
	public Ellipsis()
    {
	    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
	    FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientProxy::setup);
	    MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(FMLCommonSetupEvent event)
    {
	}
    
    public static <T extends IForgeRegistryEntry<T>> T withId(String id, T entry)
    {
        entry.setRegistryName(location(id));
        return entry;
    }

    public static ResourceLocation location(String id)
    {
        return new ResourceLocation(MOD_ID, id);
    }
}
