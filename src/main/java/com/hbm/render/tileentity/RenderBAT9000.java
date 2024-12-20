package com.hbm.render.tileentity;

import com.hbm.inventory.fluid.FluidType;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.render.amlfrom1710.Tessellator;
import org.lwjgl.opengl.GL11;

import com.hbm.main.ResourceManager;
import com.hbm.render.misc.DiamondPronter;
import com.hbm.render.misc.EnumSymbol;
import com.hbm.tileentity.machine.TileEntityMachineBAT9000;

import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RenderBAT9000 extends TileEntitySpecialRenderer<TileEntityMachineBAT9000> {

	@Override
	public boolean isGlobalRenderer(TileEntityMachineBAT9000 te){
		return true;
	}

	@Override
	public void render(TileEntityMachineBAT9000 bat, double x, double y, double z, float partialTicks, int destroyStage, float alpha){

		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5D, y, z + 0.5D);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_CULL_FACE);

		bindTexture(ResourceManager.bat9000_tex);

		GL11.glShadeModel(GL11.GL_SMOOTH);
		ResourceManager.bat9000.renderAll();
		GL11.glShadeModel(GL11.GL_FLAT);

		FluidType type = bat.tankNew.getTankType();

		if(type != null && type != Fluids.NONE) {

			RenderHelper.disableStandardItemLighting();
			GL11.glPushMatrix();
			int poison = type.poison;
			int flammability = type.flammability;
			int reactivity = type.reactivity;
			EnumSymbol symbol = type.symbol;

			GL11.glRotatef(45, 0, 1, 0);

			for(int j = 0; j < 4; j++) {

				GL11.glPushMatrix();
				GL11.glTranslated(2.5, 2.25, 0);
				GL11.glScalef(1.0F, 0.75F, 0.75F);
				DiamondPronter.pront(poison, flammability, reactivity, symbol);
				GL11.glPopMatrix();
				GL11.glRotatef(90, 0, 1, 0);
			}
			GL11.glPopMatrix();
			RenderHelper.enableStandardItemLighting();
		}

		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glColor3f(1F, 1F, 1F);
		Tessellator tess = Tessellator.instance;

		double height = bat.tankNew.getFill() * 1.5D / bat.tankNew.getMaxFill();
		double off = 2.2;

		tess.startDrawingQuads();
		tess.setColorOpaque_I(type.getColor());

		tess.addVertex(-off, 1.5, -0.5);
		tess.addVertex(-off, 1.5 + height, -0.5);
		tess.addVertex(-off, 1.5 + height, 0.5);
		tess.addVertex(-off, 1.5, 0.5);

		tess.addVertex(off, 1.5, -0.5);
		tess.addVertex(off, 1.5 + height, -0.5);
		tess.addVertex(off, 1.5 + height, 0.5);
		tess.addVertex(off, 1.5, 0.5);

		tess.addVertex(-0.5, 1.5, -off);
		tess.addVertex(-0.5, 1.5 + height, -off);
		tess.addVertex(0.5, 1.5 + height, -off);
		tess.addVertex(0.5, 1.5, -off);

		tess.addVertex(-0.5, 1.5, off);
		tess.addVertex(-0.5, 1.5 + height, off);
		tess.addVertex(0.5, 1.5 + height, off);
		tess.addVertex(0.5, 1.5, off);

		tess.draw();

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_TEXTURE_2D);

		GL11.glPopMatrix();
	}
}
