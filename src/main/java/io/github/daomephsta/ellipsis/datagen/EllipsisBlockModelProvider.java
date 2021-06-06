package io.github.daomephsta.ellipsis.datagen;

import static io.github.daomephsta.ellipsis.Ellipsis.location;
import static io.github.daomephsta.ellipsis.blocks.EllipsisBlocks.*;

import io.github.daomephsta.ellipsis.Ellipsis;
import io.github.daomephsta.ellipsis.blocks.RegionalMufflerBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class EllipsisBlockModelProvider extends BlockStateProvider
{
    public EllipsisBlockModelProvider(DataGenerator gen, String modid, ExistingFileHelper exFileHelper)
    {
        super(gen, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels()
    {
        directionalBlock(DIRECTIONAL_MUFFLER, models().cubeBottomTop(path(DIRECTIONAL_MUFFLER), 
            location("block/directional_muffler_arrow"), 
            location("block/smooth_oak_planks"), 
            location("block/directional_muffler_front")));
        simpleBlock(OMNIDIRECTIONAL_MUFFLER);
        getVariantBuilder(REGIONAL_MUFFLER).forAllStates(state ->
        {
            String s = path(REGIONAL_MUFFLER) + "/radius_" + 
                state.getValue(RegionalMufflerBlock.RADIUS);
            return ConfiguredModel.builder()
                .modelFile(models().cubeAll(ItemModelProvider.BLOCK_FOLDER + '/' + s, texture(s)))
                .build();
        });
        
        itemModels().withExistingParent(path(DIRECTIONAL_MUFFLER), location("block/directional_muffler"));
        itemModels().withExistingParent(path(OMNIDIRECTIONAL_MUFFLER), location("block/omnidirectional_muffler"));
        itemModels().getBuilder(path(REGIONAL_MUFFLER))
            .parent(getGeneratedModel("block/regional_muffler/radius_8"));
    }

    private ModelFile getGeneratedModel(String modelId)
    {
        return models().generatedModels.get(Ellipsis.location(modelId));
    }

    private static String path(IForgeRegistryEntry<?> entry)
    {
        return entry.getRegistryName().getPath();
    }

    private static ResourceLocation texture(String path)
    {
        return Ellipsis.location(ItemModelProvider.BLOCK_FOLDER + '/' + path);
    }
}
