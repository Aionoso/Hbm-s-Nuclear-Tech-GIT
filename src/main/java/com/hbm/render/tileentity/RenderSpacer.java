package com.hbm.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ResourceManager;
import com.hbm.tileentity.machine.oil.TileEntitySpacer;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RenderSpacer extends TileEntitySpecialRenderer<TileEntitySpacer> {

	@Override
	public boolean isGlobalRenderer(TileEntitySpacer te){
		return true;
	}
	
	@Override
	public void render(TileEntitySpacer te, double x, double y, double z, float partialTicks, int destroyStage, float alpha){
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5D, y, z + 0.5D);
		GlStateManager.enableLighting();
		GlStateManager.enableCull();
		
		bindTexture(ResourceManager.fraction_spacer_tex);
		ResourceManager.fraction_spacer.renderAll();
		
		GL11.glPopMatrix();
	}
}
