package com.hbm.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ClientProxy;
import com.hbm.main.ResourceManager;
import com.hbm.tileentity.machine.TileEntityControlPanel;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RenderControlPanel extends TileEntitySpecialRenderer<TileEntityControlPanel> {

	@Override
	public void render(TileEntityControlPanel te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GL11.glPushMatrix();
		GL11.glTranslated(x+0.5, y, z+0.5);
		GL11.glPushMatrix();
		switch(te.getBlockMetadata()){
		case 4:
			GL11.glRotatef(180, 0F, 1F, 0F); break;
		case 2:
			GL11.glRotatef(90, 0F, 1F, 0F); break;
		case 5:
			GL11.glRotatef(0, 0F, 1F, 0F); break;
		case 3:
			GL11.glRotatef(270, 0F, 1F, 0F); break;
		}
		GlStateManager.enableRescaleNormal();
		bindTexture(ResourceManager.control_panel0_tex);
		ResourceManager.control_panel0.renderAll();
		GL11.glPopMatrix();
		GL11.glTranslated(-0.5, 0, -0.5);
		te.panel.transform.store(ClientProxy.AUX_GL_BUFFER);
		ClientProxy.AUX_GL_BUFFER.rewind();
		GL11.glMultMatrix(ClientProxy.AUX_GL_BUFFER);
		te.panel.render();
		GlStateManager.disableRescaleNormal();
		GL11.glPopMatrix();
	}
}
