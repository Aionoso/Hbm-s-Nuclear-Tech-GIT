package com.hbm.inventory.gui;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

import com.hbm.forgefluid.FFUtils;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.inventory.container.ContainerMachineUUCreator;
import com.hbm.lib.RefStrings;
import com.hbm.lib.Library;
import com.hbm.packet.AuxButtonPacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.tileentity.machine.TileEntityMachineUUCreator;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GUIMachineUUCreator extends GuiInfoContainer {

	private static ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":textures/gui/machine/gui_uu_creator.png");
	private TileEntityMachineUUCreator uu_creator;
	
	public GUIMachineUUCreator(InventoryPlayer invPlayer, TileEntityMachineUUCreator tedf) {
		super(new ContainerMachineUUCreator(invPlayer, tedf));
		uu_creator = tedf;
		
		this.xSize = 176;
		this.ySize = 186;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float f) {
		super.drawScreen(mouseX, mouseY, f);

		FFUtils.renderTankInfo(this, mouseX, mouseY, guiLeft + 142, guiTop + 22, 16, 60, uu_creator.tank, ModForgeFluids.uu_matter);
		this.drawElectricityInfo(this, mouseX, mouseY, guiLeft + 19, guiTop + 22, 16, 60, uu_creator.power, TileEntityMachineUUCreator.maxPower);
		super.renderHoveredToolTip(mouseX, mouseY);
	}

	protected void mouseClicked(int x, int y, int i) throws IOException {
    	super.mouseClicked(x, y, i);

    	if(guiLeft + 79 <= x && guiLeft + 79 + 18 > x && guiTop + 60 < y && guiTop + 60 + 18 >= y) {

    		//toggle the magnets
			mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
    		PacketDispatcher.wrapper.sendToServer(new AuxButtonPacket(uu_creator.getPos(), 0, 0));
    	}
    	super.renderHoveredToolTip(x, y);
    }

	@Override
	protected void drawGuiContainerForegroundLayer( int i, int j) {
		String name = this.uu_creator.hasCustomInventoryName() ? this.uu_creator.getInventoryName() : I18n.format(this.uu_creator.getInventoryName());
		
		this.fontRenderer.drawString("Produced UU", 56, 26, 0xE700FF);

		String producedmb = "";
		if(this.uu_creator.producedmb * 20 > 1000)
			producedmb = Library.getShortNumber((long)this.uu_creator.producedmb * 20) + " mb/s";
		else
			producedmb = this.uu_creator.producedmb * 20 + " mb/s";

		this.fontRenderer.drawString(producedmb, 123 - this.fontRenderer.getStringWidth(producedmb), 40, 0xE700FF);
		
		this.fontRenderer.drawString(name, this.xSize / 2 - this.fontRenderer.getStringWidth(name) / 2, 6, 4210752);
		this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		super.drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		if(uu_creator.isOn)
			drawTexturedModalRect(guiLeft + 79, guiTop + 60, 176, 60, 18, 18);
		int i = uu_creator.getPowerScaled(60);
		drawTexturedModalRect(guiLeft + 19, guiTop + 83 - i, 176, 60 - i, 16, i);
		FFUtils.drawLogLiquid(uu_creator.tank, guiLeft, guiTop, zLevel, 16, 60, 141, 111);
	}
}
