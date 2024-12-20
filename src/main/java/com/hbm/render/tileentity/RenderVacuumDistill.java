package com.hbm.render.tileentity;

import com.hbm.main.ResourceManager;
import com.hbm.tileentity.machine.oil.TileEntityMachineVacuumDistill;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import org.lwjgl.opengl.GL11;

public class RenderVacuumDistill extends TileEntitySpecialRenderer<TileEntityMachineVacuumDistill> {

    @Override
    public void render(TileEntityMachineVacuumDistill tile, double x, double y, double z, float interp, int destroyStage, float alpha) {
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y, z + 0.5D);
        GL11.glEnable(GL11.GL_LIGHTING);

        GL11.glShadeModel(GL11.GL_SMOOTH);
        bindTexture(ResourceManager.vacuum_distill_tex);
        ResourceManager.vacuum_distill.renderAll();
        GL11.glShadeModel(GL11.GL_FLAT);

        GL11.glPopMatrix();
    }
}
