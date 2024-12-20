package com.hbm.render.item;

import com.hbm.main.ResourceManager;
import com.hbm.render.amlfrom1710.Tessellator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.Random;

@SideOnly(Side.CLIENT)
public class ItemRendererDetonatorLaser extends TEISRBase {

    @Override
    public void renderByItem(ItemStack itemStackIn) {
        GL11.glPushMatrix();
        GlStateManager.enableCull();
        GlStateManager.shadeModel(GL11.GL_SMOOTH);
        Minecraft.getMinecraft().renderEngine.bindTexture(ResourceManager.detonator_laser_tex);

        switch(type) {

            case FIRST_PERSON_LEFT_HAND:
            case FIRST_PERSON_RIGHT_HAND:
                if(type == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND) {
                    GL11.glTranslated(0.3, 0.9, -0.3);
                    GL11.glRotated(205, 0, 0, 1);
                    GL11.glTranslated(-0.2, 1.1, 0.8);
                    GL11.glRotated(-25, 0, 0, 1);
                    GL11.glRotated(180, 1, 0, 0);
                } else {
                    GL11.glTranslated(0, 0, 0.9);
                    GL11.glRotated(0, 0, 0, 1);
                }
                double s0 = 0.25D;
                GL11.glScaled(s0, s0, s0);
                GL11.glRotatef(80F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-20F, 1.0F, 0.0F, 0.0F);
                GL11.glTranslatef(1.0F, 0.5F, 3.0F);
                if(type == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND) {
                    GL11.glRotated(205, 0, 0, 1);
                    GL11.glTranslated(0.2, 1.1, 0.8);
                    GL11.glRotated(-25, 0, 0, 1);
                }
                break;
            case THIRD_PERSON_LEFT_HAND:
            case THIRD_PERSON_RIGHT_HAND:
            case GROUND:
            case FIXED:
            case HEAD:
                GL11.glScalef(0.375F, 0.375F, 0.375F);
                GL11.glTranslated(1.75, -0.5, 0.4);
                GL11.glRotated(180, 1, 0, 1);
                break;
            case GUI:
                GL11.glTranslated(0.26, 0.23, 0);
                GL11.glRotated(90, 0, 1, 0);
                GL11.glRotated(45, 1, 0, 0);
                GL11.glScaled(0.2, 0.2, 0.2);
                break;
            default: break;
        }

        ResourceManager.detonator_laser.renderPart("Main");

        GL11.glPushMatrix();
        GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
        GL11.glDisable(GL11.GL_LIGHTING);
        GlStateManager.disableCull();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);

        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glColor3f(1F, 0F, 0F);
        ResourceManager.detonator_laser.renderPart("Lights");
        GL11.glColor3f(1F, 1F, 1F);

        GL11.glPushMatrix();

        float px = 0.0625F;
        GL11.glTranslatef(0.5626F, px * 18, -px * 14);

        Tessellator tess = Tessellator.instance;
        tess.startDrawing(GL11.GL_QUADS);

        int sub = 32;
        double width = px * 8;
        double len = width / sub;
        double time = System.currentTimeMillis() / -100D;
        double amplitude = 0.075;

        tess.setColorOpaque_I(0xffff00);

        for(int i = 0; i < sub; i++) {
            double h0 = Math.sin(i * 0.5 + time) * amplitude;
            double h1 = Math.sin((i + 1) * 0.5 + time) * amplitude;
            tess.addVertex(0, -px * 0.25 + h1, len * (i + 1));
            tess.addVertex(0, px * 0.25 + h1, len * (i + 1));
            tess.addVertex(0, px * 0.25 + h0, len * i);
            tess.addVertex(0, -px * 0.25 + h0, len * i);
        }

        tess.draw();

        GL11.glPopMatrix();

        GL11.glEnable(GL11.GL_TEXTURE_2D);

        GL11.glPushMatrix();
        String s;
        Random rand = new Random(System.currentTimeMillis() / 500);
        FontRenderer font = Minecraft.getMinecraft().fontRenderer;
        float f3 = 0.01F;
        GL11.glTranslatef(0.5625F, 1.3125F, 0.875F);
        GL11.glScalef(f3, -f3, f3);
        GL11.glRotatef(90, 0, 1, 0);
        GL11.glNormal3f(0.0F, 0.0F, -1.0F * f3);

        GL11.glTranslatef(3F, -2F, 0.2F);

        for(int i = 0; i < 3; i++) {
            s = (rand.nextInt(900000) + 100000) + "";
            font.drawString(s, 0, 0, 0xff0000);
            GL11.glTranslatef(0F, 12.5F, 0F);
        }
        GL11.glPopMatrix();

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
        GlStateManager.shadeModel(GL11.GL_FLAT);
        GL11.glPopMatrix();
    }
}
