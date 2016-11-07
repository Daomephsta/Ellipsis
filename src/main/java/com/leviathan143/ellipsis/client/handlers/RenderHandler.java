package com.leviathan143.ellipsis.client.handlers;

import org.lwjgl.opengl.GL11;

import com.leviathan143.ellipsis.Ellipsis.Constants;
import com.leviathan143.ellipsis.common.items.EllipsisItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderHandler 
{
	private static final ResourceLocation SILENT_ICON = new ResourceLocation(Constants.MODID, "textures/blocks/omnidirectionalMuffler.png");

	private Minecraft mc = Minecraft.getMinecraft();

	@SubscribeEvent
	public void preRenderLiving(RenderLivingEvent.Pre<EntityLiving> event)
	{
		//Only render
		if(event.getEntity().isSilent())
		{
			if((mc.thePlayer.getHeldItem(EnumHand.MAIN_HAND) != null && mc.thePlayer.getHeldItem(EnumHand.MAIN_HAND).getItem() == EllipsisItems.entitySilencer) 
					|| (mc.thePlayer.getHeldItem(EnumHand.OFF_HAND) != null && mc.thePlayer.getHeldItem(EnumHand.OFF_HAND).getItem() == EllipsisItems.entitySilencer)) 
				renderSilentIndicator(event.getEntity(), event.getRenderer().getRenderManager(), mc.getRenderPartialTicks());
		}
	}

	public void renderSilentIndicator(EntityLivingBase entity, RenderManager renderManager, float partialTicks)
	{
		//Interpolate the entity and player coordinates
		double playerInterpX = mc.thePlayer.lastTickPosX + (mc.thePlayer.posX - mc.thePlayer.lastTickPosX) * partialTicks;
		double playerInterpY = mc.thePlayer.lastTickPosY + (mc.thePlayer.posY - mc.thePlayer.lastTickPosY) * partialTicks;
		double playerInterpZ = mc.thePlayer.lastTickPosZ + (mc.thePlayer.posZ - mc.thePlayer.lastTickPosZ) * partialTicks;

		double entityInterpX = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * partialTicks;
		double entityInterpY = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * partialTicks + entity.height + 0.25D;
		double entityInterpZ = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * partialTicks;

		double renderX = entityInterpX - playerInterpX; 
		double renderY = entityInterpY - playerInterpY;
		double renderZ = entityInterpZ - playerInterpZ;

		Tessellator tess = Tessellator.getInstance();
		VertexBuffer vtxBuf = tess.getBuffer();
		renderManager.renderEngine.bindTexture(SILENT_ICON);
		GlStateManager.pushMatrix();
		GlStateManager.translate(renderX, renderY, renderZ);
		GlStateManager.disableLighting();
		GlStateManager.scale(0.5, 0.5, 0.5);
		//Rotate the texture to face the camera
		GlStateManager.rotate(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate((float)(renderManager.options.thirdPersonView == 2 ? -1 : 1) * renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		vtxBuf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		vtxBuf.pos(-0.5, 0, 0).tex(0, 0).endVertex();
		vtxBuf.pos(-0.5, 1, 0).tex(0, 1).endVertex();
		vtxBuf.pos(0.5, 1, 0).tex(1, 1).endVertex();
		vtxBuf.pos(0.5, 0, 0).tex(1, 0).endVertex();
		tess.draw();
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
	}
}
