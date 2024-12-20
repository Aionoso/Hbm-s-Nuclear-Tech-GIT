package com.hbm.render.tileentity;

import com.hbm.main.ResourceManager;
import com.hbm.tileentity.turret.TileEntityTurretArty;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

public class RenderTurretArty extends TileEntitySpecialRenderer<TileEntityTurretArty> {

    @Override
    public void render(TileEntityTurretArty turret, double x, double y, double z, float interp, int destroyStage, float alpha) {
        Vec3d pos = turret.getHorizontalOffset();

        GL11.glPushMatrix();
        GL11.glTranslated(x + pos.x, y, z + pos.z);
        GlStateManager.enableLighting();
        GlStateManager.enableCull();
        GlStateManager.shadeModel(GL11.GL_SMOOTH);

        bindTexture(ResourceManager.turret_arty_tex);
        ResourceManager.turret_arty.renderPart("Base");
        double yaw = -Math.toDegrees(turret.lastRotationYaw + (turret.rotationYaw - turret.lastRotationYaw) * interp) - 90D;
        double pitch = Math.toDegrees(turret.lastRotationPitch + (turret.rotationPitch - turret.lastRotationPitch) * interp);

        GL11.glRotated(yaw - 90, 0, 1, 0);
        ResourceManager.turret_arty.renderPart("Carriage");

        GL11.glTranslated(0, 3, 0);
        GL11.glRotated(pitch, 1, 0, 0);
        GL11.glTranslated(0, -3, 0);
        ResourceManager.turret_arty.renderPart("Cannon");
        double barrel = turret.lastBarrelPos + (turret.barrelPos - turret.lastBarrelPos) * interp;
        double length = 2.5;
        GL11.glTranslated(0, 0, barrel * length);
        ResourceManager.turret_arty.renderPart("Barrel");

        GL11.glShadeModel(GL11.GL_FLAT);
        GL11.glPopMatrix();
    }
}
