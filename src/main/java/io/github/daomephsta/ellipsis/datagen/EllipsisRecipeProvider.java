package io.github.daomephsta.ellipsis.datagen;

import java.util.function.Consumer;

import io.github.daomephsta.ellipsis.blocks.EllipsisBlocks;
import io.github.daomephsta.ellipsis.items.EllipsisItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;

class EllipsisRecipeProvider extends RecipeProvider
{
    public EllipsisRecipeProvider(DataGenerator generator)
    {
        super(generator);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> output)
    {
        ShapedRecipeBuilder.shaped(EllipsisItems.EARMUFFS)
            .unlockedBy("has_wool", has(ItemTags.WOOL))
            .define('W', ItemTags.WOOL)
            .define('S', Items.STICK)
            .pattern(" S ")
            .pattern("S S")
            .pattern("W W")
            .save(output);
        muffler(EllipsisBlocks.OMNIDIRECTIONAL_MUFFLER)
            .pattern("P P")
            .pattern(" W ")
            .pattern("P P")
            .save(output);
        muffler(EllipsisBlocks.DIRECTIONAL_MUFFLER)
            .pattern("PPP")
            .pattern("PW ")
            .pattern("PPP")
            .save(output);
        muffler(EllipsisBlocks.REGIONAL_MUFFLER)
            .pattern("PWP")
            .pattern("W W")
            .pattern("PWP")
            .save(output);
    }

    private ShapedRecipeBuilder muffler(IItemProvider muffler)
    {
        return ShapedRecipeBuilder.shaped(muffler)
            .unlockedBy("has_wool", has(ItemTags.WOOL))
            .define('P', ItemTags.PLANKS)
            .define('W', ItemTags.WOOL);
    }
}