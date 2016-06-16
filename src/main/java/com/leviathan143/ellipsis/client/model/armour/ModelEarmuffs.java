package com.leviathan143.ellipsis.client.model.armour;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;


/**
 * ModelEarmuffs - Leviathan143
 * Created using Tabula 5.1.0
 */
public class ModelEarmuffs extends ModelBiped {
	public ModelRenderer headband;
	public ModelRenderer rightMuff;
	public ModelRenderer leftMuff;

	public ModelEarmuffs() 
	{
		this.textureWidth = 32;
		this.textureHeight = 16;
		this.rightMuff = new ModelRenderer(this, 0, 6);
		this.rightMuff.mirror = true;
		this.rightMuff.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.rightMuff.addBox(3.5F, -5.0F, -2.0F, 2, 4, 3, 0.0F);
		this.setRotateAngle(rightMuff, 0.0F, 3.141592653589793F, 0.0F);
		this.leftMuff = new ModelRenderer(this, 0, 6);
		this.leftMuff.mirror = true;
		this.leftMuff.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.leftMuff.addBox(3.5F, -5.0F, -1.0F, 2, 4, 3, 0.0F);
		this.headband = new ModelRenderer(this, 0, 0);
		this.headband.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.headband.addBox(-5.0F, -9.0F, 0.0F, 10, 4, 1, 0.0F);
		this.headband.addChild(this.rightMuff);
		this.headband.addChild(this.leftMuff);    
	}

	@Override
	public void render(Entity entity, float x, float y, float z, float yaw, float partialTicks, float scale) 
	{
		if (entity instanceof EntityArmorStand)
		{
			 yaw = ((EntityArmorStand) entity).getHeadRotation().getZ();
		}
		GlStateManager.pushMatrix();
		this.bipedHead = headband;
		super.setRotationAngles(x, y, z, yaw, partialTicks, scale, entity);
		if(entity.isSneaking()) GlStateManager.translate(0.0F, 0.275F, 0.0F);
		headband.render(scale);
		GlStateManager.popMatrix();
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}