package com.hbm.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ResourceManager;
import com.hbm.tileentity.machine.oil.TileEntityMachineRefinery;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RenderRefinery extends TileEntitySpecialRenderer<TileEntityMachineRefinery> {

	@Override
	public boolean isGlobalRenderer(TileEntityMachineRefinery te) {
		return true;
	}
	
	@Override
	public void render(TileEntityMachineRefinery te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y, z + 0.5D);
		GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glRotatef(180, 0F, 1F, 0F);

		bindTexture(ResourceManager.refinery_tex);

        GL11.glShadeModel(GL11.GL_SMOOTH);
        ResourceManager.refinery.renderAll();
        GL11.glShadeModel(GL11.GL_FLAT);

        GL11.glPopMatrix();
	}
}
