package com.hbm.render.tileentity;

import com.hbm.wiaj.WorldInAJar;
import com.hbm.wiaj.actors.ITileActorRenderer;
import net.minecraft.nbt.NBTTagCompound;
import org.lwjgl.opengl.GL11;

import com.hbm.blocks.BlockDummyable;
import com.hbm.main.ResourceManager;
import com.hbm.tileentity.machine.TileEntityMachineFENSU;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RenderFENSU extends TileEntitySpecialRenderer<TileEntityMachineFENSU> implements ITileActorRenderer {

	@Override
	public void render(TileEntityMachineFENSU te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GL11.glPushMatrix();

		GL11.glTranslatef((float)x + 0.5F, (float)y, (float)z + 0.5F);

		GlStateManager.enableCull();
		GlStateManager.enableLighting();
		GlStateManager.shadeModel(GL11.GL_SMOOTH);

		switch(te.getBlockMetadata() - BlockDummyable.offset) {
		case 2: GL11.glRotatef(90, 0F, 1F, 0F); break;
		case 4: GL11.glRotatef(180, 0F, 1F, 0F); break;
		case 3: GL11.glRotatef(270, 0F, 1F, 0F); break;
		case 5: GL11.glRotatef(0, 0F, 1F, 0F); break;
		}

        

        TileEntityMachineFENSU fensu = (TileEntityMachineFENSU)te;
        bindTexture(ResourceManager.fensu_tex[fensu.color.getMetadata()]);
        ResourceManager.fensu.renderPart("Base");
        float rot = fensu.prevRotation + (fensu.rotation - fensu.prevRotation) * partialTicks;

        GL11.glTranslated(0, 2.5, 0);
        GL11.glRotated(rot, 1, 0, 0);
        GL11.glTranslated(0, -2.5, 0);
        ResourceManager.fensu.renderPart("Disc");

        GL11.glPushMatrix();

	        GlStateManager.disableLighting();
	        GlStateManager.disableCull();
	        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
	        ResourceManager.fensu.renderPart("Lights");
	        GlStateManager.enableCull();
	        GlStateManager.enableLighting();

        GL11.glPopMatrix();

		GlStateManager.shadeModel(GL11.GL_FLAT);

		GL11.glPopMatrix();
	}

	@Override
	public void renderActor(WorldInAJar world, int ticks, float interp, NBTTagCompound data) {
		double x = data.getDouble("x");
		double y = data.getDouble("y");
		double z = data.getDouble("z");
		int rotation = data.getInteger("rotation");
		float lastSpin = data.getFloat("lastSpin");
		float spin = data.getFloat("spin");

		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5D, y, z + 0.5D);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glShadeModel(GL11.GL_SMOOTH);

		switch(rotation) {
			case 3: GL11.glRotatef(0, 0F, 1F, 0F); break;
			case 5: GL11.glRotatef(90, 0F, 1F, 0F); break;
			case 2: GL11.glRotatef(180, 0F, 1F, 0F); break;
			case 4: GL11.glRotatef(270, 0F, 1F, 0F); break;
		}

		ITileActorRenderer.bindTexture(ResourceManager.fensu_tex[0]);
		ResourceManager.fensu.renderPart("Base");

		float rot = lastSpin + (spin - lastSpin) * interp;

		GL11.glTranslated(0, 2.5, 0);
		GL11.glRotated(rot, 1, 0, 0);
		GL11.glTranslated(0, -2.5, 0);
		ResourceManager.fensu.renderPart("Disc");
		ResourceManager.fensu.renderPart("Lights");
		GL11.glShadeModel(GL11.GL_FLAT);

		GL11.glPopMatrix();
	}

	@Override
	public void updateActor(int ticks, NBTTagCompound data) {

		float lastSpin = 0;
		float spin = data.getFloat("spin");
		float speed = data.getFloat("speed");

		lastSpin = spin;
		spin += speed;

		if(spin >= 360) {
			lastSpin -= 360;
			spin -= 360;
		}

		data.setFloat("lastSpin", lastSpin);
		data.setFloat("spin", spin);
	}
}
