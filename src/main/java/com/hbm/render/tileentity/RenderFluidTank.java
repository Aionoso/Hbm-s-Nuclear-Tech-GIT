package com.hbm.render.tileentity;

import com.hbm.inventory.fluid.FluidType;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.inventory.fluid.trait.FT_Corrosive;
import com.hbm.render.misc.DiamondPronter;
import net.minecraft.client.renderer.RenderHelper;
import org.lwjgl.opengl.GL11;

import com.hbm.lib.RefStrings;
import com.hbm.main.ResourceManager;
import com.hbm.tileentity.machine.TileEntityMachineFluidTank;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderFluidTank extends TileEntitySpecialRenderer<TileEntityMachineFluidTank> {
	
	@Override
	public void render(TileEntityMachineFluidTank tank, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5D, y, z + 0.5D);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_CULL_FACE);

		switch(tank.getBlockMetadata() - 10) {
			case 2: GL11.glRotatef(180, 0F, 1F, 0F); break;
			case 4: GL11.glRotatef(270, 0F, 1F, 0F); break;
			case 3: GL11.glRotatef(0, 0F, 1F, 0F); break;
			case 5: GL11.glRotatef(90, 0F, 1F, 0F); break;
		}
		FluidType type = tank.tankNew.getTankType();

		GL11.glShadeModel(GL11.GL_SMOOTH);
		bindTexture(ResourceManager.tank_tex);

		ResourceManager.fluidtank.renderPart("Frame");
		bindTexture(new ResourceLocation(RefStrings.MODID, getTextureFromType(tank.tankNew.getTankType())));
		ResourceManager.fluidtank.renderPart("Tank");

		GL11.glColor3d(1D, 1D, 1D);
		GL11.glShadeModel(GL11.GL_FLAT);

		if(type != null && type != Fluids.NONE) {

			RenderHelper.disableStandardItemLighting();
			GL11.glPushMatrix();
			GL11.glTranslated(-0.25, 0.5, -1.501);
			GL11.glRotated(90, 0, 1, 0);
			GL11.glScalef(1.0F, 0.375F, 0.375F);
			DiamondPronter.pront(type.poison, type.flammability, type.reactivity, type.symbol);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glTranslated(0.25, 0.5, 1.501);
			GL11.glRotated(-90, 0, 1, 0);
			GL11.glScalef(1.0F, 0.375F, 0.375F);
			DiamondPronter.pront(type.poison, type.flammability, type.reactivity, type.symbol);
			GL11.glPopMatrix();
		}

		GL11.glPopMatrix();
		RenderHelper.enableStandardItemLighting();
	}

	public String getTextureFromType(FluidType type) {

		if(type.customFluid) {
			int color = type.getTint();
			double r = ((color & 0xff0000) >> 16) / 255D;
			double g = ((color & 0x00ff00) >> 8) / 255D;
			double b = ((color & 0x0000ff) >> 0) / 255D;
			GL11.glColor3d(r, g, b);
			return "textures/models/tank/tank_NONE.png";
		}

		String s = type.getName();

		if(type.isAntimatter() || (type.hasTrait(FT_Corrosive.class) && type.getTrait(FT_Corrosive.class).isHighlyCorrosive()))
			s = "DANGER";

		return "textures/models/tank/tank_" + s + ".png";
	}
}
