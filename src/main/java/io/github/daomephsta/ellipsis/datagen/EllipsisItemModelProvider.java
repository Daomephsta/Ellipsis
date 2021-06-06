package io.github.daomephsta.ellipsis.datagen;

import static io.github.daomephsta.ellipsis.items.EllipsisItems.EARMUFFS;
import static io.github.daomephsta.ellipsis.items.EllipsisItems.ENTITY_SILENCER;

import io.github.daomephsta.ellipsis.Ellipsis;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelBuilder.Perspective;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class EllipsisItemModelProvider extends ItemModelProvider
{
    private static final ResourceLocation GENERATED = new ResourceLocation("item/generated");

    public EllipsisItemModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper)
    {
        super(generator, modid, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {
        singleTexture(path(EARMUFFS), GENERATED, "layer0", texture(path(EARMUFFS)))
            .transforms()
            .transform(Perspective.THIRDPERSON_RIGHT)
                .rotation(90, 90, 0)
                .translation(0.0F, -2.0F, -0.5F)
                .scale(0.85F)
                .end()
            .transform(Perspective.THIRDPERSON_LEFT)
                .rotation(90, 90, 0)
                .translation(0.0F, -2.0F, -0.5F)
                .scale(0.85F)
                .end();
        singleTexture(path(ENTITY_SILENCER), GENERATED, "layer0", texture(path(ENTITY_SILENCER)));
    }

    private static String path(IForgeRegistryEntry<?> entry)
    {
        return entry.getRegistryName().getPath();
    }

    private static ResourceLocation texture(String path)
    {
        return Ellipsis.location(ITEM_FOLDER + '/' + path);
    }
}
