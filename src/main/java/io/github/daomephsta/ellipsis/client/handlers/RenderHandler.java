package io.github.daomephsta.ellipsis.client.handlers;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.matrix.MatrixStack;

import io.github.daomephsta.ellipsis.Ellipsis;
import io.github.daomephsta.ellipsis.items.EllipsisItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Ellipsis.MOD_ID)
public class RenderHandler 
{
	private static final ResourceLocation SILENT_ICON = Ellipsis.location("textures/misc/silent_indicator.png");
	@SubscribeEvent
	public static void preRenderLiving(RenderLivingEvent.Pre<?, ?> event)
	{
		if (event.getEntity().isSilent() && Minecraft.getInstance().player.isHolding(EllipsisItems.ENTITY_SILENCER))
        {
            //Interpolate the entity and player coordinates for smooth rendering when moving
            float deltaFrameTime = Minecraft.getInstance().getDeltaFrameTime();
            Vector3d playerLerpPos = Minecraft.getInstance().player.getPosition(deltaFrameTime);
            Vector3d entityLerpPos = event.getEntity().getPosition(deltaFrameTime);
            double renderX = entityLerpPos.x - playerLerpPos.x; 
            double renderY = entityLerpPos.y - playerLerpPos.y;
            double renderZ = entityLerpPos.z - playerLerpPos.z;
            
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder builder = tessellator.getBuilder();
            MatrixStack transforms = event.getMatrixStack();
            EntityRendererManager rendererManager = event.getRenderer().getDispatcher();
            rendererManager.textureManager.bind(SILENT_ICON);
            transforms.pushPose();
            transforms.translate(renderX, renderY, renderZ);
            //Rotate the texture to face the camera
            transforms.mulPose(rendererManager.cameraOrientation());
            transforms.scale(0.5F, 0.5F, 0.5F);
            builder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
            builder.vertex(-0.5, 0, 0).uv(1, 1).endVertex();
            builder.vertex(-0.5, 1, 0).uv(1, 0).endVertex();
            builder.vertex(0.5, 1, 0).uv(0, 0).endVertex();
            builder.vertex(0.5, 0, 0).uv(0, 1).endVertex();
            tessellator.end();
            transforms.popPose();
        }
	}
}
