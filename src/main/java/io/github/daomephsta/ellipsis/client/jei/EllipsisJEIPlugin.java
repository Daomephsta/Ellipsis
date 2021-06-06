package io.github.daomephsta.ellipsis.client.jei;

import java.util.stream.Stream;

import io.github.daomephsta.ellipsis.Ellipsis;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.registries.ForgeRegistries;

@JeiPlugin
public class EllipsisJEIPlugin implements IModPlugin
{	
	private static final ResourceLocation UID = Ellipsis.location("jei_plugin");

	@Override
	public void registerRecipes(IRecipeRegistration registration)
	{
		Stream.concat(ForgeRegistries.BLOCKS.getValues().stream(), ForgeRegistries.ITEMS.getValues().stream())
		    .filter(item -> item.getRegistryName().getNamespace().equals(Ellipsis.MOD_ID))
		    .forEach(item -> addJEIDescription(registration, item));
	}
	
	private void addJEIDescription(IRecipeRegistration registration, IItemProvider item)
	{
		registration.addIngredientInfo(new ItemStack(item), VanillaTypes.ITEM,
		    new TranslationTextComponent(item.asItem().getDescriptionId() + ".jeiDescription"));
	}

    @Override
    public ResourceLocation getPluginUid()
    {
        return UID;
    }
}
