package com.hbm.render.tileentity;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.forgefluid.FFUtils;
import com.hbm.lib.RefStrings;
import com.hbm.main.ResourceManager;
import com.hbm.tileentity.machine.TileEntityMachineFluidTank;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class RenderFluidTank extends TileEntitySpecialRenderer<TileEntityMachineFluidTank> {

	@Override
	public void render(TileEntityMachineFluidTank te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5D, y, z + 0.5D);
		GlStateManager.enableLighting();
		GlStateManager.disableCull();
		GlStateManager.shadeModel(GL11.GL_SMOOTH);
		switch(te.getBlockMetadata())
		{
			case 2:
				GL11.glRotatef(270, 0F, 1F, 0F); break;
			case 4:
				GL11.glRotatef(0, 0F, 1F, 0F); break;
			case 3:
				GL11.glRotatef(90, 0F, 1F, 0F); break;
			case 5:
				GL11.glRotatef(180, 0F, 1F, 0F); break;
		}

		bindTexture(ResourceManager.tank_tex);
		ResourceManager.fluidtank.renderPart("Frame");

		GlStateManager.shadeModel(GL11.GL_FLAT);
		GL11.glPopMatrix();
		renderTileEntityAt2(te, x, y, z, partialTicks);
	}

	public void renderTileEntityAt2(TileEntity tileEntity, double x, double y, double z, float f)
	{
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5D, y, z + 0.5D);
		GlStateManager.enableLighting();
		GlStateManager.disableCull();
		switch(tileEntity.getBlockMetadata())
		{
			case 2:
				GL11.glRotatef(270, 0F, 1F, 0F); break;
			case 4:
				GL11.glRotatef(0, 0F, 1F, 0F); break;
			case 3:
				GL11.glRotatef(90, 0F, 1F, 0F); break;
			case 5:
				GL11.glRotatef(180, 0F, 1F, 0F); break;
		}

		String s = "NONE";
		Fluid type = null;
		if(tileEntity instanceof TileEntityMachineFluidTank){
			TileEntityMachineFluidTank tankTileEntity = (TileEntityMachineFluidTank)tileEntity;
			Fluid lastType = tankTileEntity.lastType;
			if(lastType != null){
				type = lastType;
			}
			if(tankTileEntity.tank.getFluid() != null){
				type = tankTileEntity.tank.getFluid().getFluid();
				tankTileEntity.lastType=type;
			}
			if(type != null){
				s = FluidRegistry.getFluidName(type).toUpperCase();
				if(s.substring(0, 3).equals("HBM")){
					s = s.substring(3);
				}
			}
		}

		ResourceLocation rotTexture = new ResourceLocation(RefStrings.MODID, "textures/models/tank/tank_" + s + ".png");

		try {
			Minecraft.getMinecraft().getResourceManager().getResource(rotTexture);
		} catch (IOException e) {//unreachable code:
			e.printStackTrace();
			//Drillgon200: Set to my really ugly unknown texture
			//Alcater: found a way to textract the color from the fluids texture
			rotTexture = new ResourceLocation(RefStrings.MODID, "textures/models/tank/tank_generic.png");
			if(type != null){
				FFUtils.setRGBFromHex(ModForgeFluids.getFluidColor(type));
			}
		}

		GlStateManager.shadeModel(GL11.GL_SMOOTH);
		bindTexture(rotTexture);
		ResourceManager.fluidtank.renderPart("Tank");
		GlStateManager.shadeModel(GL11.GL_FLAT);
		GlStateManager.color(1, 1, 1, 1);
		GL11.glPopMatrix();
	}
}