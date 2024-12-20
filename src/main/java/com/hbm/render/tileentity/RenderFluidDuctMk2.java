package com.hbm.render.tileentity;

import com.hbm.blocks.ModBlocks;
import com.hbm.forgefluid.FFUtils;
import com.hbm.inventory.fluid.FluidType;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.lib.Library;
import com.hbm.main.ResourceManager;
import com.hbm.tileentity.network.TileEntityPipeBaseNT;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import org.lwjgl.opengl.GL11;

public class RenderFluidDuctMk2<T extends TileEntityPipeBaseNT> extends TileEntitySpecialRenderer<T> {

	@Override
	public void render(T te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		if(te.getBlockType() == ModBlocks.fluid_duct_solid)
			return;
		GL11.glPushMatrix();
		GlStateManager.enableLighting();
		GlStateManager.shadeModel(GL11.GL_SMOOTH);

		int color = 0xff00ff;
		FluidType type = Fluids.NONE;

		if(te instanceof TileEntityPipeBaseNT) {
			color = te.getType().getColor();
			type = te.getType();
		}
		if (type != Fluids.NONE) FFUtils.setRGBFromHex(color);

		boolean pX = Library.canConnectFluid(Minecraft.getMinecraft().world, te.getPos().getX() + 1, te.getPos().getY(), te.getPos().getZ(), Library.POS_X, type);
		boolean nX = Library.canConnectFluid(Minecraft.getMinecraft().world, te.getPos().getX() - 1, te.getPos().getY(), te.getPos().getZ(), Library.NEG_X, type);
		boolean pY = Library.canConnectFluid(Minecraft.getMinecraft().world, te.getPos().getX(), te.getPos().getY() + 1, te.getPos().getZ(), Library.POS_Y, type);
		boolean nY = Library.canConnectFluid(Minecraft.getMinecraft().world, te.getPos().getX(), te.getPos().getY() - 1, te.getPos().getZ(), Library.NEG_Y, type);
		boolean pZ = Library.canConnectFluid(Minecraft.getMinecraft().world, te.getPos().getX(), te.getPos().getY(), te.getPos().getZ() + 1, Library.POS_Z, type);
		boolean nZ = Library.canConnectFluid(Minecraft.getMinecraft().world, te.getPos().getX(), te.getPos().getY(), te.getPos().getZ() - 1, Library.NEG_Z, type);

		int mask = 0 + (pX ? 32 : 0) + (nX ? 16 : 0) + (pY ? 8 : 0) + (nY ? 4 : 0) + (pZ ? 2 : 0) + (nZ ? 1 : 0);

		GL11.glTranslated(x + 0.5F, y + 0.5F, z + 0.5F);

		bindTexture(ResourceManager.pipe_neo_tex);

		if(mask == 0) {
			ResourceManager.pipe_neo.renderPart("pX");
			ResourceManager.pipe_neo.renderPart("nX");
			ResourceManager.pipe_neo.renderPart("pY");
			ResourceManager.pipe_neo.renderPart("nY");
			ResourceManager.pipe_neo.renderPart("pZ");
			ResourceManager.pipe_neo.renderPart("nZ");
		} else if(mask == 0b100000 || mask == 0b010000) {
			ResourceManager.pipe_neo.renderPart("pX");
			ResourceManager.pipe_neo.renderPart("nX");
		} else if(mask == 0b001000 || mask == 0b000100) {
			ResourceManager.pipe_neo.renderPart("pY");
			ResourceManager.pipe_neo.renderPart("nY");
		} else if(mask == 0b000010 || mask == 0b000001) {
			ResourceManager.pipe_neo.renderPart("pZ");
			ResourceManager.pipe_neo.renderPart("nZ");
		} else {

			if(pX) ResourceManager.pipe_neo.renderPart("pX");
			if(nX) ResourceManager.pipe_neo.renderPart("nX");
			if(pY) ResourceManager.pipe_neo.renderPart("pY");
			if(nY) ResourceManager.pipe_neo.renderPart("nY");
			if(pZ) ResourceManager.pipe_neo.renderPart("nZ");
			if(nZ) ResourceManager.pipe_neo.renderPart("pZ");

			if(!pX && !pY && !pZ) ResourceManager.pipe_neo.renderPart("ppn");
			if(!pX && !pY && !nZ) ResourceManager.pipe_neo.renderPart("ppp");
			if(!nX && !pY && !pZ) ResourceManager.pipe_neo.renderPart("npn");
			if(!nX && !pY && !nZ) ResourceManager.pipe_neo.renderPart("npp");
			if(!pX && !nY && !pZ) ResourceManager.pipe_neo.renderPart("pnn");
			if(!pX && !nY && !nZ) ResourceManager.pipe_neo.renderPart("pnp");
			if(!nX && !nY && !pZ) ResourceManager.pipe_neo.renderPart("nnn");
			if(!nX && !nY && !nZ) ResourceManager.pipe_neo.renderPart("nnp");
		}
		GlStateManager.shadeModel(GL11.GL_FLAT);
		GlStateManager.color(1, 1, 1, 1);
		GL11.glTranslated(-x - 0.5F, -y - 0.5F, -z - 0.5F);
		GL11.glPopMatrix();
	}
}