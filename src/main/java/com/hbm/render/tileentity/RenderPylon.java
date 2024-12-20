package com.hbm.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.hbm.lib.RefStrings;
import com.hbm.render.amlfrom1710.Vec3;
import com.hbm.render.model.ModelPylon;
import com.hbm.tileentity.network.energy.TileEntityPylon;
import com.hbm.tileentity.network.energy.TileEntityPylonBase;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;

public class RenderPylon extends TileEntitySpecialRenderer<TileEntityPylon> {

	private static final ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":" + "textures/models/network/ModelPylon.png");

	private static final float cableColorR = 0.16F;
	private static final float cableColorG = 0.16F;
	private static final float cableColorB = 0.16F;

	private ModelPylon pylon;

	public RenderPylon() {
		this.pylon = new ModelPylon();
	}

	@Override
	public boolean isGlobalRenderer(TileEntityPylon te) {
		return true;
	}

	@Override
	public void render(TileEntityPylon pyl, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GL11.glPushMatrix();
			GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F - ((1F / 16F) * 14F), (float) z + 0.5F);
			GL11.glRotatef(180, 0F, 0F, 1F);
			bindTexture(texture);
			this.pylon.renderAll(0.0625F);
		GL11.glPopMatrix();

		this.renderPowerLines(pyl, x, y, z);
	}

	public static void renderPowerLines(TileEntityPylonBase pyl, double x, double y, double z) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y, (float) z);
		for (int i = 0; i < pyl.connected.size(); i++) {

			int[] otherPylon = pyl.connected.get(i);
			TileEntity tile = pyl.getWorld().getTileEntity(new BlockPos(otherPylon[0], otherPylon[1], otherPylon[2]));

			if(tile instanceof TileEntityPylonBase) {
				TileEntityPylonBase pylon = (TileEntityPylonBase) tile;
				Vec3[] m1 = pyl.getMountPos();
				Vec3[] m2 = pylon.getMountPos();

				int lineCount = Math.max(pyl.getConnectionType() == TileEntityPylonBase.ConnectionType.QUAD ? 4 : 1, pylon.getConnectionType() == TileEntityPylonBase.ConnectionType.QUAD ? 4 : 1);
				
				for(int line = 0; line < lineCount; line++) {

					int secondIndex = line % m2.length;

					if(lineCount == 4 && ((pyl.getBlockMetadata() - 10 == 5 && pylon.getBlockMetadata() - 10 == 2) || (pyl.getBlockMetadata() - 10 == 2 && pylon.getBlockMetadata() - 10 == 5))) {
						secondIndex += 2;
						secondIndex %= m2.length;
					}
					Vec3 first = m1[line % m1.length];
					Vec3 second = m2[secondIndex];

					Vec3 mid = new Vec3(new BlockPos(otherPylon[0], otherPylon[1], otherPylon[2])).add(second).subtract(new Vec3(pyl.getPos()).add(first));
					drawLine(first, first.add(new Vec3(mid.xCoord*0.5, mid.yCoord*0.5, mid.zCoord*0.5)), lineCount == 1 ? 0.03125F : 0.055F, mid.lengthVector()*0.045);
				}
			}
		}
		GL11.glPopMatrix();
	}

	public static void drawLine(Vec3 firstPylonMountPos, Vec3 secoundPylonMountPos, float girth, double hang) {
		float count = 10;
		Vec3 deltaVector = secoundPylonMountPos.subtract(firstPylonMountPos); // vector from pylon1 mount to pylon2 mount

		for(float j = 0; j < count; j++) {
			float k = j + 1;
			
			drawLineSegment(
				firstPylonMountPos.xCoord + (deltaVector.xCoord * j / count),
				firstPylonMountPos.yCoord + (deltaVector.yCoord * j / count) - hang * Math.sin(j / count * Math.PI * 0.5),
				firstPylonMountPos.zCoord + (deltaVector.zCoord * j / count),
				firstPylonMountPos.xCoord + (deltaVector.xCoord * k / count),
				firstPylonMountPos.yCoord + (deltaVector.yCoord * k / count) - hang * Math.sin(k / count * Math.PI * 0.5),
				firstPylonMountPos.zCoord + (deltaVector.zCoord * k / count), girth);
		}
	}

	public static void drawLineSegment(double x, double y, double z, double a, double b, double c, float girth) {
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GlStateManager.disableLighting();
		GL11.glDisable(GL11.GL_CULL_FACE);
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder buf = tessellator.getBuffer();
		buf.begin(GL11.GL_TRIANGLE_STRIP, DefaultVertexFormats.POSITION_COLOR);
		// tessellator.setColorRGBA_F(0.683F, 0.089F, 0.0F, 1.0F);
		buf.pos(x, y + girth, z).color(cableColorR, cableColorG, cableColorB, 1.0F).endVertex();
		buf.pos(x, y - girth, z).color(cableColorR, cableColorG, cableColorB, 1.0F).endVertex();
		buf.pos(a, b + girth, c).color(cableColorR, cableColorG, cableColorB, 1.0F).endVertex();
		buf.pos(a, b - girth, c).color(cableColorR, cableColorG, cableColorB, 1.0F).endVertex();
		tessellator.draw();
		buf.begin(GL11.GL_TRIANGLE_STRIP, DefaultVertexFormats.POSITION_COLOR);
		// tessellator.setColorRGBA_F(0.683F, 0.089F, 0.0F, 1.0F);
		buf.pos(x + girth, y, z).color(cableColorR, cableColorG, cableColorB, 1.0F).endVertex();
		buf.pos(x - girth, y, z).color(cableColorR, cableColorG, cableColorB, 1.0F).endVertex();
		buf.pos(a + girth, b, c).color(cableColorR, cableColorG, cableColorB, 1.0F).endVertex();
		buf.pos(a - girth, b, c).color(cableColorR, cableColorG, cableColorB, 1.0F).endVertex();
		tessellator.draw();
		buf.begin(GL11.GL_TRIANGLE_STRIP, DefaultVertexFormats.POSITION_COLOR);
		// tessellator.setColorRGBA_F(0.683F, 0.089F, 0.0F, 1.0F);
		buf.pos(x, y, z + girth).color(cableColorR, cableColorG, cableColorB, 1.0F).endVertex();
		buf.pos(x, y, z - girth).color(cableColorR, cableColorG, cableColorB, 1.0F).endVertex();
		buf.pos(a, b, c + girth).color(cableColorR, cableColorG, cableColorB, 1.0F).endVertex();
		buf.pos(a, b, c - girth).color(cableColorR, cableColorG, cableColorB, 1.0F).endVertex();
		tessellator.draw();
		GlStateManager.enableLighting();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_CULL_FACE);
	}
}
