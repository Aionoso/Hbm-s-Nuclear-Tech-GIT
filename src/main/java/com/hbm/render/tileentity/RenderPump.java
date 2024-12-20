package com.hbm.render.tileentity;

import com.hbm.blocks.BlockDummyable;
import com.hbm.blocks.ModBlocks;
import com.hbm.main.ResourceManager;
import com.hbm.render.item.ItemRenderBase;
import com.hbm.tileentity.machine.TileEntityMachinePumpBase;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import org.lwjgl.opengl.GL11;

public class RenderPump extends TileEntitySpecialRenderer<TileEntityMachinePumpBase> {

    @Override
    public void render(TileEntityMachinePumpBase tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {

        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y, z + 0.5D);
        GL11.glEnable(GL11.GL_LIGHTING);

        switch(tile.getBlockMetadata() - BlockDummyable.offset) {
            case 3: GL11.glRotatef(90, 0F, 1F, 0F); break;
            case 5: GL11.glRotatef(180, 0F, 1F, 0F); break;
            case 2: GL11.glRotatef(270, 0F, 1F, 0F); break;
            case 4: GL11.glRotatef(0, 0F, 1F, 0F); break;
        }
        float angle = tile.lastRotor + (tile.rotor - tile.lastRotor) * partialTicks;
        renderCommon(angle, tile.getBlockType() == ModBlocks.pump_steam ? 0 : 1);

        GL11.glPopMatrix();
    }

    private void renderCommon(double rot, int type) {
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glShadeModel(GL11.GL_SMOOTH);

        if(type == 0) bindTexture(ResourceManager.pump_steam_tex);
        else bindTexture(ResourceManager.pump_electric_tex);
        ResourceManager.pump.renderPart("Base");

        GL11.glPushMatrix();
        GL11.glTranslated(0, 2.25, 0);
        GL11.glRotated(rot - 90, 0, 0, 1);
        GL11.glTranslated(0, -2.25, 0);
        ResourceManager.pump.renderPart("Rotor");
        GL11.glPopMatrix();

        double sin = Math.sin(rot * Math.PI / 180D) * 0.5D - 0.5D;
        double cos = Math.cos(rot * Math.PI / 180D) * 0.5D;
        double ang = Math.acos(cos / 2D);
        double cath = Math.sqrt(1 + (cos * cos) / 2);

        GL11.glPushMatrix();
        GL11.glTranslated(0, 1 - cath + sin, 0);
        GL11.glTranslated(0, 4.75, 0);
        GL11.glRotated(ang * 180D / Math.PI - 90D, 0, 0, -1);
        GL11.glTranslated(0, -4.75, 0);
        ResourceManager.pump.renderPart("Arms");
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glTranslated(0, 1 - cath + sin, 0);
        ResourceManager.pump.renderPart("Piston");
        GL11.glPopMatrix();

        GL11.glShadeModel(GL11.GL_FLAT);
        GL11.glEnable(GL11.GL_CULL_FACE);
    }
}
