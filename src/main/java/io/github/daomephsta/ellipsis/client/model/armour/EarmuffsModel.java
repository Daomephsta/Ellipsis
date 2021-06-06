package io.github.daomephsta.ellipsis.client.model.armour;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;


/**
 * ModelEarmuffs - Daomephsta
 * Created using Tabula 5.1.0
 */
public class EarmuffsModel extends BipedModel<LivingEntity> {
	public ModelRenderer headband;
	public ModelRenderer rightMuff;
	public ModelRenderer leftMuff;

	public EarmuffsModel() 
	{
	    super(1.0F);
		this.texWidth = 32;
		this.texHeight = 16;
		this.rightMuff = new ModelRenderer(this, 0, 6);
		this.rightMuff.mirror = true;
		this.rightMuff.setPos(0.0F, 0.0F, 0.0F);
		this.rightMuff.addBox(3.5F, -5.0F, -2.0F, 2, 4, 3, 0.0F);
		this.setRotateAngle(rightMuff, 0.0F, 3.141592653589793F, 0.0F);
		this.leftMuff = new ModelRenderer(this, 0, 6);
		this.leftMuff.mirror = true;
		this.leftMuff.setPos(0.0F, 0.0F, 0.0F);
		this.leftMuff.addBox(3.5F, -5.0F, -1.0F, 2, 4, 3, 0.0F);
		this.head = this.headband = new ModelRenderer(this, 0, 0);
		this.headband.setPos(0.0F, 0.0F, 0.0F);
		this.headband.addBox(-5.0F, -9.0F, 0.0F, 10, 4, 1, 0.0F);
		this.headband.addChild(this.rightMuff);
		this.headband.addChild(this.leftMuff);    
	}

	@Override
	public void renderToBuffer(MatrixStack transforms, IVertexBuilder builder, 
	    int unknown, int unknown2, float x, float y, float z, float yaw)
	{
		headband.render(transforms, builder, unknown, unknown2, x, y, z, yaw);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}