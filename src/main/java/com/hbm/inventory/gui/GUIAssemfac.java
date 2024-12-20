package com.hbm.inventory.gui;

import com.hbm.forgefluid.FFUtils;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.inventory.container.ContainerAssemfac;
import com.hbm.lib.RefStrings;
import com.hbm.tileentity.machine.TileEntityMachineAssemfac;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GUIAssemfac extends GuiInfoContainer {

    private static ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":textures/gui/processing/gui_assemfac.png");
    private static ResourceLocation chemfac = new ResourceLocation(RefStrings.MODID + ":textures/gui/processing/gui_chemfac.png");
    private TileEntityMachineAssemfac assemfac;

    public GUIAssemfac(InventoryPlayer invPlayer, TileEntityMachineAssemfac tedf) {
        super(new ContainerAssemfac(invPlayer, tedf));
        assemfac = tedf;

        this.xSize = 256;
        this.ySize = 256;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float f) {
        super.drawScreen(mouseX, mouseY, f);
        super.renderHoveredToolTip(mouseX, mouseY);

        this.drawElectricityInfo(this, mouseX, mouseY, guiLeft + 234, guiTop + 164, 16, 52, assemfac.power, assemfac.getMaxPower());

        FFUtils.renderTankInfo(this, mouseX, mouseY, guiLeft + 209, guiTop + 181, 9, 54, assemfac.water.getTank(), FluidRegistry.WATER);
        FFUtils.renderTankInfo(this, mouseX, mouseY, guiLeft + 218, guiTop + 181, 9, 54, assemfac.steam.getTank(), ModForgeFluids.spentsteam);

        for(int i = 0; i < 8; i++) {

            if(assemfac.maxProgress[i] > 0) {
                int progress = assemfac.progress[i] * 16 / assemfac.maxProgress[i];

                if(progress > 0) {
                    GL11.glDisable(GL11.GL_LIGHTING);
                    GL11.glDisable(GL11.GL_DEPTH_TEST);
                    int x = guiLeft + 234;
                    int y = guiTop + 13 + 16 * i;
                    GL11.glColorMask(true, true, true, false);
                    this.drawGradientRect(x, y, x + progress + 1, y + 16, -2130706433, -2130706433);
                    GL11.glColorMask(true, true, true, true);
                    GL11.glEnable(GL11.GL_LIGHTING);
                    GL11.glEnable(GL11.GL_DEPTH_TEST);
                }
            }
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int i, int j) { }

    @Override
    protected void drawGuiContainerBackgroundLayer(float interp, int mX, int mY) {
        super.drawDefaultBackground();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        Minecraft.getMinecraft().getTextureManager().bindTexture(chemfac);

        int p = (int) (assemfac.power * 52 / assemfac.getMaxPower());
        drawTexturedModalRect(guiLeft + 234, guiTop + 216 - p, 0, 219 - p, 16, p);

        if(assemfac.power > 0)
            drawTexturedModalRect(guiLeft + 238, guiTop + 150, 0, 219, 9, 12);

        FFUtils.drawLiquid(assemfac.water.getTank(), guiLeft, guiTop, this.zLevel, 7, 52, 210, 256);
        FFUtils.drawLiquid(assemfac.steam.getTank(), guiLeft, guiTop, this.zLevel, 7, 52, 219, 256);

        if(Keyboard.isKeyDown(Keyboard.KEY_LMENU))
            for(int i = 0; i < this.inventorySlots.inventorySlots.size(); i++) {
                Slot s = this.inventorySlots.getSlot(i);

                this.fontRenderer.drawStringWithShadow(i + "", guiLeft + s.xPos + 2, guiTop + s.yPos, 0xffffff);
                this.fontRenderer.drawStringWithShadow(s.getSlotIndex() + "", guiLeft + s.xPos + 2, guiTop + s.yPos + 8, 0xff8080);
            }
    }
}
