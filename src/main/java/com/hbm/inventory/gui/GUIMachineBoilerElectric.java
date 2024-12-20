package com.hbm.inventory.gui;

import com.hbm.util.I18nUtil;
import org.lwjgl.opengl.GL11;

import com.hbm.inventory.container.ContainerMachineBoilerElectric;
import com.hbm.lib.RefStrings;
import com.hbm.tileentity.machine.TileEntityMachineBoilerElectric;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GUIMachineBoilerElectric extends GuiInfoContainer {

	public static ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":textures/gui/gui_boiler_electric.png");
	private TileEntityMachineBoilerElectric diFurnace;
	
	public GUIMachineBoilerElectric(InventoryPlayer invPlayer, TileEntityMachineBoilerElectric tedf) {
		super(new ContainerMachineBoilerElectric(invPlayer, tedf));
		diFurnace = tedf;

		this.xSize = 176;
		this.ySize = 168;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float f) {
		super.drawScreen(mouseX, mouseY, f);

		diFurnace.tanksNew[0].renderTankInfo(this, mouseX, mouseY, guiLeft + 62, guiTop + 69 - 52, 16, 52);
		diFurnace.tanksNew[1].renderTankInfo(this, mouseX, mouseY, guiLeft + 134, guiTop + 69 - 52, 16, 52);

		this.drawCustomInfoStat(mouseX, mouseY, guiLeft + 84, guiTop + 16, 8, 18, mouseX, mouseY, new String[] { String.valueOf((int)((double)diFurnace.heat / 100D)) + "°C"});
		
		String[] text = I18nUtil.resolveKeyArray("desc.guimachboilerel");
		this.drawCustomInfoStat(mouseX, mouseY, guiLeft - 16, guiTop + 36, 16, 16, guiLeft - 8, guiTop + 36 + 16, text);
		
		String[] text1 = I18nUtil.resolveKeyArray("desc.guimachboiler1");
		this.drawCustomInfoStat(mouseX, mouseY, guiLeft - 16, guiTop + 36 + 16, 16, 16, guiLeft - 8, guiTop + 36 + 16, text1);
		
	//	if(diFurnace.tanks[1].getTankType().name().equals(FluidType.NONE.name())) {
	//		
	//		String[] text2 = new String[] { "Error: Liquid can not be boiled!" };
	//		this.drawCustomInfoStat(mouseX, mouseY, guiLeft - 16, guiTop + 36 + 32, 16, 16, guiLeft - 8, guiTop + 36 + 16 + 32, text2);
	//	}
		
		this.drawElectricityInfo(this, mouseX, mouseY, guiLeft + 105, guiTop + 69 - 34, 7, 34, diFurnace.power, TileEntityMachineBoilerElectric.maxPower);
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

		//<insert witty line here>
		TileEntityMachineBoilerElectric dud = diFurnace;

		if(diFurnace.isInvalid() && diFurnace.getWorld().getTileEntity(diFurnace.getPos()) instanceof TileEntityMachineBoilerElectric)
			dud = (TileEntityMachineBoilerElectric) diFurnace.getWorld().getTileEntity(diFurnace.getPos());
		
		if(dud.power > 0)
			drawTexturedModalRect(guiLeft + 79, guiTop + 34, 176, 0, 18, 18);

		int j = (int)dud.getHeatScaled(17);
		drawTexturedModalRect(guiLeft + 85, guiTop + 33 - j, 194, 16 - j, 6, j);

		int i = (int)dud.getPowerScaled(34);
		drawTexturedModalRect(guiLeft + 105, guiTop + 69 - i, 200, 34 - i, 7, i);

		this.drawInfoPanel(guiLeft - 16, guiTop + 36, 16, 16, 2);
		this.drawInfoPanel(guiLeft - 16, guiTop + 36 + 16, 16, 16, 3);


		diFurnace.tanksNew[0].renderTank(guiLeft + 62, guiTop + 69, this.zLevel, 16, 52);
		diFurnace.tanksNew[1].renderTank(guiLeft + 134, guiTop + 69, this.zLevel, 16, 52);
	}
}
