package com.hbm.render.tileentity;

import java.awt.Color;

import com.hbm.inventory.fluid.Fluids;
import com.hbm.inventory.fluid.tank.FluidTankNTM;
import org.lwjgl.opengl.GL11;

import com.hbm.main.ResourceManager;
import com.hbm.tileentity.machine.TileEntityMachineMixer;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RenderMixer extends TileEntitySpecialRenderer<TileEntityMachineMixer> {

	@Override
	public void render(TileEntityMachineMixer mixer, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5D, y, z + 0.5D);
		GlStateManager.enableLighting();
		GL11.glDisable(GL11.GL_CULL_FACE);
		
		GlStateManager.shadeModel(GL11.GL_SMOOTH);
		bindTexture(ResourceManager.mixer_tex);
		ResourceManager.mixer.renderPart("Main");
		
		GL11.glPushMatrix();
		GL11.glRotatef(mixer.prevRotation + (mixer.rotation - mixer.prevRotation) * partialTicks, 0, -1, 0);
		ResourceManager.mixer.renderPart("Mixer");
		GL11.glPopMatrix();

		int totalFill = 0;
		int totalMax = 0;

		for(FluidTankNTM tank : mixer.tanksNew) {
			if(tank.getTankType() != Fluids.NONE) {
				totalFill += tank.getFill();
				totalMax += tank.getMaxFill();
			}
		}
		
		if(totalFill > 0) {
			GL11.glDepthMask(false);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glAlphaFunc(GL11.GL_GREATER, 0);
			OpenGlHelper.glBlendFunc(770, 771, 1, 0);

			Color color = new Color(mixer.tanksNew[2].getTankType().getColor());
			GL11.glColor4f(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, 0.75F);
			GL11.glTranslated(0, 1, 0);
			GL11.glScaled(1, (double) totalFill / (double) totalMax * 0.99, 1);
			GL11.glTranslated(0, -1, 0);
			ResourceManager.mixer.renderPart("Fluid");

			GL11.glColor4f(1F, 1F, 1F, 1F);
			GL11.glDepthMask(true);
			GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
		}
		GL11.glShadeModel(GL11.GL_FLAT);
		
		GL11.glEnable(GL11.GL_CULL_FACE);
		
		GL11.glPopMatrix();
	}
}
