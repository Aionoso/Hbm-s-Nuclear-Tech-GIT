package com.hbm.inventory.gui;


import com.hbm.render.amlfrom1710.Tessellator;
import com.hbm.util.I18nUtil;
import com.mojang.realmsclient.gui.ChatFormatting;
import org.lwjgl.opengl.GL11;

import com.hbm.inventory.container.ContainerMachineGasCent;
import com.hbm.lib.RefStrings;
import com.hbm.tileentity.machine.TileEntityMachineGasCent;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GUIMachineGasCent extends GuiInfoContainer {

	public static ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":textures/gui/processing/gui_centrifuge_gas.png");
	private TileEntityMachineGasCent diFurnace;
	
	public GUIMachineGasCent(InventoryPlayer invPlayer, TileEntityMachineGasCent tedf) {
		super(new ContainerMachineGasCent(invPlayer, tedf));
		diFurnace = tedf;

		this.xSize = 176;
		this.ySize = 168;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float f) {
		super.drawScreen(mouseX, mouseY, f);

		String[] inTankInfo = new String[] {diFurnace.inputTank.getTankType().getName(), diFurnace.inputTank.getFill() + " / " + diFurnace.inputTank.getMaxFill() + " mB"};
		if(diFurnace.inputTank.getTankType().getIfHighSpeed()) {
			if(diFurnace.processingSpeed > diFurnace.processingSpeed - 70)
				inTankInfo[0] = ChatFormatting.DARK_RED + inTankInfo[0];
			else
				inTankInfo[0] = ChatFormatting.GOLD + inTankInfo[0];
		}
		String[] outTankInfo = new String[] {diFurnace.outputTank.getTankType().getName(), diFurnace.outputTank.getFill() + " / " + diFurnace.outputTank.getMaxFill() + " mB"};
		if(diFurnace.outputTank.getTankType().getIfHighSpeed())
			outTankInfo[0] = ChatFormatting.GOLD + outTankInfo[0];

		this.drawCustomInfoStat(mouseX, mouseY, guiLeft + 15, guiTop + 15, 24, 55, mouseX, mouseY, inTankInfo);
		this.drawCustomInfoStat(mouseX, mouseY, guiLeft + 137, guiTop + 15, 25, 55, mouseX, mouseY, outTankInfo);

		this.drawElectricityInfo(this, mouseX, mouseY, guiLeft + 182, guiTop + 69 - 52, 16, 52, diFurnace.power, diFurnace.maxPower);

		String[] enrichmentText = I18nUtil.resolveKeyArray("desc.gui.gasCent.enrichment");
		this.drawCustomInfoStat(mouseX, mouseY, guiLeft - 12, guiTop + 16, 16, 16, guiLeft - 8, guiTop + 16 + 16, enrichmentText);

		String[] transferText = I18nUtil.resolveKeyArray("desc.gui.gasCent.output");
		this.drawCustomInfoStat(mouseX, mouseY, guiLeft - 12, guiTop + 32, 16, 16, guiLeft - 8, guiTop + 32 + 16, transferText);
		super.renderHoveredToolTip(mouseX, mouseY);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int i, int j) {
		String name = this.diFurnace.hasCustomInventoryName() ? this.diFurnace.getInventoryName() : I18n.format(this.diFurnace.getInventoryName());
		
		this.fontRenderer.drawString(name, this.xSize / 2 - this.fontRenderer.getStringWidth(name) / 2, 6, 4210752);
		this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		super.drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		int i = (int)diFurnace.getPowerRemainingScaled(34);
		drawTexturedModalRect(guiLeft + 8, guiTop + 51 - i, 176, 34 - i, 16, i);

		int j = (int)diFurnace.getCentrifugeProgressScaled(37);
		drawTexturedModalRect(guiLeft + 95, guiTop + 55 - j, 192, 37 - j, 22, j);

		this.renderTank(guiLeft + 16, guiTop + 16, this.zLevel, 6, 52, diFurnace.inputTank.getFill(), diFurnace.inputTank.getMaxFill());
		this.renderTank(guiLeft + 32, guiTop + 16, this.zLevel, 6, 52, diFurnace.inputTank.getFill(), diFurnace.inputTank.getMaxFill());

		this.renderTank(guiLeft + 138, guiTop + 16, this.zLevel, 6, 52, diFurnace.outputTank.getFill(), diFurnace.outputTank.getMaxFill());
		this.renderTank(guiLeft + 154, guiTop + 16, this.zLevel, 6, 52, diFurnace.outputTank.getFill(), diFurnace.outputTank.getMaxFill());

		this.drawInfoPanel(guiLeft - 12, guiTop + 16, 16, 16, 3);
		this.drawInfoPanel(guiLeft - 12, guiTop + 32, 16, 16, 2);
	}

	public void renderTank(int x, int y, double z, int width, int height, int fluid, int maxFluid) {

		GL11.glEnable(GL11.GL_BLEND);

		y += height;

		Minecraft.getMinecraft().getTextureManager().bindTexture(diFurnace.tankNew.getTankType().getTexture());

		int i = (fluid * height) / maxFluid;

		double minX = x;
		double maxX = x + width;
		double minY = y - height;
		double maxY = y - (height - i);

		double minV = 1D;
		double maxV = 1D - i / 16D;
		double minU = 0D;
		double maxU = width / 16D;

		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(minX, maxY, z, minU, maxV);
		tessellator.addVertexWithUV(maxX, maxY, z, maxU, maxV);
		tessellator.addVertexWithUV(maxX, minY, z, maxU, minV);
		tessellator.addVertexWithUV(minX, minY, z, minU, minV);
		tessellator.draw();

		GL11.glDisable(GL11.GL_BLEND);
	}
}
