package com.hbm.render.tileentity;

import com.hbm.main.ResourceManager;
import com.hbm.tileentity.machine.TileEntityMachineRadarLarge;
import com.hbm.tileentity.machine.TileEntityMachineRadarNT;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import org.lwjgl.opengl.GL11;

public class RenderRadarLarge extends TileEntitySpecialRenderer<TileEntityMachineRadarLarge> {
    @Override
    public boolean isGlobalRenderer(TileEntityMachineRadarLarge te) {
        return true;
    }
    @Override
    public void render(TileEntityMachineRadarLarge tileEntity, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y, z + 0.5D);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glRotatef(180, 0F, 1F, 0F);

        bindTexture(ResourceManager.radar_large_tex);
        ResourceManager.radar_large.renderPart("Radar");

        TileEntityMachineRadarNT radar = (TileEntityMachineRadarNT) tileEntity;
        GL11.glRotatef(radar.prevRotation + (radar.rotation - radar.prevRotation) * partialTicks, 0F, -1F, 0F);

        ResourceManager.radar_large.renderPart("Dish");

        GL11.glPopMatrix();
    }

}
