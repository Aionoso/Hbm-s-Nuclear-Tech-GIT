package com.hbm.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.hbm.main.ResourceManager;
import com.hbm.render.RenderHelper;
import com.hbm.tileentity.machine.TileEntityMachineCrystallizer;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RenderCrystallizer extends TileEntitySpecialRenderer<TileEntityMachineCrystallizer> {

	@Override
	public boolean isGlobalRenderer(TileEntityMachineCrystallizer te) {
		return true;
	}
	
	@Override
	public void render(TileEntityMachineCrystallizer crys, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y, z + 0.5D);
        GlStateManager.enableLighting();
        GlStateManager.disableCull();

		switch(crys.getBlockMetadata() - 10) {
		case 2: GL11.glRotatef(90, 0F, 1F, 0F); break;
		case 4: GL11.glRotatef(180, 0F, 1F, 0F); break;
		case 3: GL11.glRotatef(270, 0F, 1F, 0F); break;
		case 5: GL11.glRotatef(0, 0F, 1F, 0F); break;
		}

		GlStateManager.shadeModel(GL11.GL_SMOOTH);
        bindTexture(ResourceManager.crystallizer_tex);
        ResourceManager.crystallizer.renderPart("Body");

        GL11.glPushMatrix();
        GL11.glRotatef(crys.prevAngle + (crys.angle - crys.prevAngle) * partialTicks, 0, 1, 0);
        bindTexture(ResourceManager.crystallizer_spinner_tex);
        ResourceManager.crystallizer.renderPart("Spinner");
        GL11.glPopMatrix();

        renderFill(crys);
        bindTexture(ResourceManager.crystallizer_window_tex);
        ResourceManager.crystallizer.renderPart("Windows");
        
		GlStateManager.shadeModel(GL11.GL_FLAT);
		GlStateManager.enableCull();

        GL11.glPopMatrix();
	}

	public void renderFill(TileEntityMachineCrystallizer crys){
		if(crys.tankNew.getTankType() == null) return;
		GL11.glPushMatrix();
		GlStateManager.enableCull();
		GlStateManager.disableTexture2D();
       	GlStateManager.enableBlend();
		GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE);
		
		RenderHelper.setColor(crys.tankNew.getTankType().getColor());
		ResourceManager.crystallizer.renderPart("Windows");

		
		GL11.glColor4f(1F, 1F, 1F, 1F);
		GlStateManager.disableBlend();
		GlStateManager.enableTexture2D();
		GlStateManager.disableCull();
		GL11.glPopMatrix();
	}
}
