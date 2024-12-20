package com.hbm.render.util;

import com.hbm.lib.ForgeDirection;
import com.hbm.render.amlfrom1710.*;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.math.Vec3d;

public class ObjUtil {

    public static void renderWithIcon(WavefrontObject model, TextureAtlasSprite sprite, Tessellator tes, float rot, boolean shadow) {
        renderWithIcon(model, sprite, tes, rot, 0, 0, shadow);
    }

    public static void renderWithIcon(WavefrontObject model, TextureAtlasSprite sprite, Tessellator tes, float rot, float pitch, boolean shadow) {
        renderWithIcon(model, sprite, tes, rot, pitch, 0, shadow);
    }

    public static void renderWithIcon(WavefrontObject model, TextureAtlasSprite sprite, Tessellator tes, float rot, float pitch, float roll, boolean shadow) {
        for(GroupObject go : model.groupObjects) {

            for(Face f : go.faces) {

                Vertex n = f.faceNormal;

                Vec3 normal = Vec3.createVectorHelper(n.x, n.y, n.z);
                normal.rotateAroundZ(pitch);
                normal.rotateAroundY(rot);
                tes.setNormal((float)normal.xCoord, (float)normal.yCoord, (float)normal.zCoord);

                if(shadow) {
                    float brightness = ((float)normal.yCoord + 0.7F) * 0.9F - (float)Math.abs(normal.xCoord) * 0.1F + (float)Math.abs(normal.zCoord) * 0.1F;

                    if(brightness < 0.45F)
                        brightness = 0.45F;

                    tes.setColorOpaque_F(brightness, brightness, brightness);
                }

                for(int i = 0; i < f.vertices.length; i++) {

                    Vertex v = f.vertices[i];

                    Vec3 vec = Vec3.createVectorHelper(v.x, v.y, v.z);
                    vec.rotateAroundX(roll);
                    vec.rotateAroundZ(pitch);
                    vec.rotateAroundY(rot);

                    float x = (float) vec.xCoord;
                    float y = (float) vec.yCoord;
                    float z = (float) vec.zCoord;

                    TextureCoordinate t = f.textureCoordinates[i];
                    tes.addVertexWithUV(x, y, z, sprite.getInterpolatedU(t.u * 16), sprite.getInterpolatedV(t.v * 16));

                    // The shoddy way of rendering a tringulated model with a
                    // quad tessellator
                    if(i % 3 == 2)
                        tes.addVertexWithUV(x, y, z, sprite.getInterpolatedU(t.u * 16), sprite.getInterpolatedV(t.v * 16));
                }
            }
        }
    }

    public static void renderPartWithIcon(WavefrontObject model, String name, TextureAtlasSprite icon, BufferBuilder buffer, float rot, boolean shadow) {
        renderPartWithIcon(model, name, icon, buffer, rot, 0, 0, shadow);
    }

    public static void renderPartWithIcon(WavefrontObject model, String name, TextureAtlasSprite icon, BufferBuilder buffer, float rot, float pitch, boolean shadow) {
        renderPartWithIcon(model, name, icon, buffer, rot, pitch, 0, shadow);
    }

    public static void renderPartWithIcon(WavefrontObject model, String name, TextureAtlasSprite icon, BufferBuilder buffer, float rot, float pitch, float roll, boolean shadow) {

        GroupObject go = model.groupObjects.stream()
                .filter(obj -> obj.name.equals(name))
                .findFirst()
                .orElse(null);

        if(go == null)
            return;

        for(Face f : go.faces) {
            Vertex n = f.faceNormal;

            Vec3d normal = new Vec3d(n.x, n.y, n.z);
            normal = normal.rotatePitch(pitch).rotateYaw(rot);

            float brightness = 1.0F;
            if(shadow || hasColor) {
                if(shadow) {
                    brightness = ((float)normal.y * 0.3F + 0.7F) - (float)Math.abs(normal.x) * 0.1F + (float)Math.abs(normal.z) * 0.1F;
                    brightness = Math.max(brightness, 0.45F);
                }
            }

            for(int i = 0; i < f.vertices.length; i++) {
                Vertex ver = f.vertices[i];

                Vec3d vec = new Vec3d(ver.x, ver.y, ver.z);
                vec = vec.rotateYaw(rot)
                        .rotatePitch(pitch)
                        .add(vec.crossProduct(new Vec3d(0, 0, 1)).scale(Math.sin(roll)))
                        .add(vec.scale(Math.cos(roll)));

                float x = (float) vec.x;
                float y = (float) vec.y;
                float z = (float) vec.z;

                TextureCoordinate t = f.textureCoordinates[i];
                float u = icon.getInterpolatedU(t.u * 16D);
                float v = icon.getInterpolatedV(t.v * 16D);

                buffer.pos(x, y, z).tex(u, v);

                if(hasColor) {
                    buffer.color((int)(red * brightness), (int)(green * brightness), (int)(blue * brightness), 255);
                } else {
                    buffer.color(brightness, brightness, brightness, 1.0F);
                }

                buffer.normal((float)normal.x, (float)normal.y, (float)normal.z);
                buffer.endVertex();

                // The shoddy way of rendering a triangulated model with a quad buffer
                if(f.vertices.length == 3 && i % 3 == 2) {
                    buffer.pos(x, y, z).tex(u, v);
                    if(hasColor) {
                        buffer.color((int)(red * brightness), (int)(green * brightness), (int)(blue * brightness), 255);
                    } else {
                        buffer.color(brightness, brightness, brightness, 1.0F);
                    }
                    buffer.normal((float)normal.x, (float)normal.y, (float)normal.z);
                    buffer.endVertex();
                }
            }
        }
    }

    private static int red;
    private static int green;
    private static int blue;
    private static boolean hasColor = false;

    public static void setColor(int color) {
        red = (color & 0xff0000) >> 16;
        green = (color & 0x00ff00) >> 8;
        blue = color & 0x0000ff;
        hasColor = true;
    }

    public static void setColor(int r, int g, int b) {
        red = r;
        green = g;
        blue = b;
        hasColor = true;
    }

    public static void clearColor() {
        hasColor = false;
    }

    // Both methods assume model is facing towards +X (EAST)
    // Why not +Z (NORTH)? Pitch doesn't rotate as you would expect in that case using the (current) draw methods
    public static float getPitch(ForgeDirection dir) {
        if (dir == ForgeDirection.UP) return (float)Math.PI * -0.5F;
        if (dir == ForgeDirection.DOWN) return (float)Math.PI * 0.5F;
        return 0;
    }

    public static float getYaw(ForgeDirection dir) {
        if (dir == ForgeDirection.NORTH) return (float)Math.PI * 0.5f;;
        if (dir == ForgeDirection.SOUTH) return (float)Math.PI * -0.5f;
        if (dir == ForgeDirection.WEST) return (float)Math.PI;
        return 0;
    }

}
