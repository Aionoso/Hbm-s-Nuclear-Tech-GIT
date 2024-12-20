package com.hbm.render.tileentity;

import com.hbm.blocks.BlockDummyable;
import com.hbm.main.ResourceManager;
import com.hbm.tileentity.machine.TileEntitySawmill;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import org.lwjgl.opengl.GL11;

public class RenderSawmill extends TileEntitySpecialRenderer<TileEntitySawmill> {
    @Override
    public void render(TileEntitySawmill sawmill, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y, z + 0.5D);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_CULL_FACE);

        switch(sawmill.getBlockMetadata() - BlockDummyable.offset) {
            case 3: GL11.glRotatef(0, 0F, 1F, 0F); break;
            case 5: GL11.glRotatef(90, 0F, 1F, 0F); break;
            case 2: GL11.glRotatef(180, 0F, 1F, 0F); break;
            case 4: GL11.glRotatef(270, 0F, 1F, 0F); break;
        }

        float rot = sawmill.lastSpin + (sawmill.spin - sawmill.lastSpin) * partialTicks;
        bindTexture(ResourceManager.sawmill_tex);
        renderCommon(rot, sawmill.hasBlade);

        GL11.glPopMatrix();
    }

    public static void renderCommon(float rot, boolean hasBlade) {
        ResourceManager.sawmill.renderPart("Main");

        if(hasBlade) {
            GL11.glPushMatrix();
            GL11.glTranslated(0, 1.375, 0);
            GL11.glRotatef(-rot * 2, 0, 0, 1);
            GL11.glTranslated(0, -1.375, 0);
            ResourceManager.sawmill.renderPart("Blade");
            GL11.glPopMatrix();
        }

        GL11.glPushMatrix();
        GL11.glTranslated(0.5625, 1.375, 0);
        GL11.glRotatef(rot, 0, 0, 1);
        GL11.glTranslated(-0.5625, -1.375, 0);
        ResourceManager.sawmill.renderPart("GearLeft");
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glTranslated(-0.5625, 1.375, 0);
        GL11.glRotatef(-rot, 0, 0, 1);
        GL11.glTranslated(0.5625, -1.375, 0);
        ResourceManager.sawmill.renderPart("GearRight");
        GL11.glPopMatrix();
    }
}
