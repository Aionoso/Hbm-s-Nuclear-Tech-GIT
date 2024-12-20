package com.hbm.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.hbm.blocks.BlockDummyable;
import com.hbm.forgefluid.FFUtils;
import com.hbm.main.ResourceManager;
import com.hbm.render.amlfrom1710.Vec3;
import com.hbm.render.misc.BeamPronter;
import com.hbm.render.misc.BeamPronter.EnumBeamType;
import com.hbm.render.misc.BeamPronter.EnumWaveType;
import com.hbm.tileentity.machine.TileEntityMachineOrbus;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class RenderOrbus extends TileEntitySpecialRenderer<TileEntityMachineOrbus> {

	@Override
	public boolean isGlobalRenderer(TileEntityMachineOrbus te){
		return true;
	}
	
	@Override
	public void render(TileEntityMachineOrbus orbus, double x, double y, double z, float partialTicks, int destroyStage, float alpha){
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		
		switch(orbus.getBlockMetadata() - BlockDummyable.offset) {
		case 2:
			GL11.glTranslated(1F, 0F, 1F); break;
		case 4:
			GL11.glTranslated(1F, 0F, 0F); break;
		case 3:
			GL11.glTranslated(0F, 0F, 0F); break;
		case 5:
			GL11.glTranslated(0F, 0F, 1F); break;
		}
		
		double scale = (double)orbus.tankNew.getFill() / (double)orbus.tankNew.getMaxFill();
		
		if(orbus.tankNew.getFill() > 0) {
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			int c = orbus.tankNew.getTankType().getColor();
			GL11.glColor3ub((byte)((c & 0xff0000) >> 16), (byte)((c & 0x00ff00) >> 8), (byte)((c & 0x0000ff)));
			GL11.glPushMatrix();
			GL11.glTranslated(0, 2.5D + Math.sin(((orbus.getWorld().getTotalWorldTime() + partialTicks) * 0.1D) % (Math.PI * 2D)) * 0.125 * scale, 0);
			GL11.glScaled(scale, scale, scale);
			ResourceManager.sphere_uv.renderAll();
			GL11.glPopMatrix();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
		}
		
		GlStateManager.enableLighting();
		GlStateManager.enableCull();

		bindTexture(ResourceManager.orbus_tex);

		GlStateManager.shadeModel(GL11.GL_SMOOTH);
		ResourceManager.orbus.renderAll();
		GlStateManager.shadeModel(GL11.GL_FLAT);
		
		if(orbus.tankNew.getFill() > 0) {
			GL11.glTranslated(0, 1, 0);
			BeamPronter.prontBeam(Vec3.createVectorHelper(0, 3, 0), EnumWaveType.SPIRAL, EnumBeamType.SOLID, 0x101020, 0x101020, 0, 1, 0F, 6, (float)scale * 0.5F);
			BeamPronter.prontBeam(Vec3.createVectorHelper(0, 3, 0), EnumWaveType.RANDOM, EnumBeamType.SOLID, 0x202060, 0x202060, (int)(orbus.getWorld().getTotalWorldTime() / 2) % 1000, 6, (float)scale, 2, 0.0625F * (float)scale);
			BeamPronter.prontBeam(Vec3.createVectorHelper(0, 3, 0), EnumWaveType.RANDOM, EnumBeamType.SOLID, 0x202060, 0x202060, (int)(orbus.getWorld().getTotalWorldTime() / 4) % 1000, 6, (float)scale, 2, 0.0625F * (float)scale);
		}
		
		GL11.glPopMatrix();
	}
}
