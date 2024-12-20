package com.hbm.inventory.gui;

import com.hbm.inventory.container.ContainerOilburner;
import com.hbm.inventory.fluid.trait.FT_Flammable;
import com.hbm.packet.NBTControlPacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.tileentity.machine.TileEntityHeaterOilburner;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.Locale;

public class GUIOilburner extends GuiInfoContainer {
    private final ResourceLocation texture;

    private TileEntityHeaterOilburner heater;

    public GUIOilburner(InventoryPlayer player, TileEntityHeaterOilburner heater, ResourceLocation texture) {
        super(new ContainerOilburner(player, heater));

        this.heater = heater;
        this.texture = texture;

        this.xSize = 176;
        this.ySize = 203;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        this.drawCustomInfoStat(mouseX, mouseY, guiLeft + 116, guiTop + 17, 16, 52, mouseX, mouseY, new String[]{String.format("%,d", Math.min(heater.heatEnergy, TileEntityHeaterOilburner.maxHeatEnergy)) + " / " + String.format("%,d", TileEntityHeaterOilburner.maxHeatEnergy) + " TU"});

        if(heater.tankNew.getTankType().hasTrait(FT_Flammable.class)) {
            this.drawCustomInfoStat(mouseX, mouseY, guiLeft + 79, guiTop + 34, 18, 18, mouseX, mouseY, new String[] { heater.setting + " mB/t", String.format(Locale.US, "%,d", (int)(heater.tankNew.getTankType().getTrait(FT_Flammable.class).getHeatEnergy() / 1000) * heater.setting) + " TU/t" });
        }

        super.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        if (guiLeft + 80 <= mouseX && guiLeft + 80 + 16 > mouseX && guiTop + 54 < mouseY && guiTop + 54 + 14 >= mouseY) {
            mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
            NBTTagCompound data = new NBTTagCompound();
            data.setBoolean("toggle", true);
            PacketDispatcher.wrapper.sendToServer(new NBTControlPacket(data, heater.getPos()));
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String name = this.heater.hasCustomInventoryName() ? this.heater.getInventoryName() : I18n.format(this.heater.getInventoryName());

        this.fontRenderer.drawString(name, this.xSize / 2 - this.fontRenderer.getStringWidth(name) / 2, 6, 4210752);
        this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        super.drawDefaultBackground();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        int i = heater.heatEnergy * 52 / TileEntityHeaterOilburner.maxHeatEnergy;
        drawTexturedModalRect(guiLeft + 116, guiTop + 69 - i, 194, 52 - i, 16, i);

        if (heater.isOn) {
            drawTexturedModalRect(guiLeft + 70, guiTop + 54, 210, 0, 35, 14);

            if(heater.tankNew.getFill() > 0 && heater.tankNew.getTankType().hasTrait(FT_Flammable.class)) {
                drawTexturedModalRect(guiLeft + 79, guiTop + 34, 176, 0, 18, 18);
            }
        }

        heater.tankNew.renderTank(guiLeft + 44, guiTop + 69, this.zLevel, 16, 52);
    }
}
