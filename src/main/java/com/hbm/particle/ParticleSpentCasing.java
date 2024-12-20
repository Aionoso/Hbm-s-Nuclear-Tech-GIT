package com.hbm.particle;

import com.hbm.main.ResourceManager;
import com.hbm.render.amlfrom1710.Vec3;
import com.hbm.util.Tuple;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SideOnly(Side.CLIENT)
public class ParticleSpentCasing extends Particle {

    public static final Random rand = new Random();
    public static float dScale = 0.05F, smokeJitter = 0.001F;

    private int maxSmokeGen = 120;
    private double smokeLift = 0.5D;
    private int nodeLife = 30;

    private final List<Tuple.Pair<Vec3, Double>> smokeNodes = new ArrayList();

    private final TextureManager textureManager;

    private final SpentCasing config;
    public boolean isSmoking;

    public float momentumPitch, momentumYaw;

    public float rotationPitch, rotationYaw;
    public float prevRotationPitch, prevRotationYaw;

    private boolean isInWater() {
        return this.world.getBlockState(new BlockPos(this.posX, this.posY, this.posZ)).getMaterial() == Material.WATER;
    }

    public ParticleSpentCasing(TextureManager textureManager, World world, double x, double y, double z, double mx, double my, double mz, float momentumPitch, float momentumYaw, SpentCasing config, boolean smoking, int smokeLife, double smokeLift, int nodeLife) {
        super(world, x, y, z, 0, 0, 0);
        this.textureManager = textureManager;
        this.momentumPitch = momentumPitch;
        this.momentumYaw = momentumYaw;
        this.config = config;

        this.particleMaxAge = config.getMaxAge();

        this.isSmoking = smoking;
        this.maxSmokeGen = smokeLife;
        this.smokeLift = smokeLift;
        this.nodeLife = nodeLife;

        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;

        this.motionX = mx;
        this.motionY = my;
        this.motionZ = mz;

        particleGravity = 1F;
    }

    @Override
    public int getFXLayer() {
        return 3;
    }

    @Override
    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if(this.particleAge++ >= this.particleMaxAge) {
            this.setExpired();
        }

        this.motionY -= 0.04D * (double) this.particleGravity;
        double prevMotionY = this.motionY;
        this.move(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.98D;
        this.motionY *= 0.98D;
        this.motionZ *= 0.98D;

        if(this.onGround) {
            this.motionX *= 0.7D;
            this.motionZ *= 0.7D;
        }

        if(onGround) {
            this.onGround = false;
            motionY = prevMotionY * -0.5;
            this.rotationPitch = 0;
            //momentumPitch = (float) rand.nextGaussian() * config.getBouncePitch();
            //momentumYaw = (float) rand.nextGaussian() * config.getBounceYaw();

        }

        if(particleAge > maxSmokeGen && !smokeNodes.isEmpty())
            smokeNodes.clear();

        if(isSmoking && particleAge <= maxSmokeGen) {

            for(Tuple.Pair<Vec3, Double> pair : smokeNodes) {
                Vec3 node = pair.getKey();

                node.xCoord += rand.nextGaussian() * smokeJitter;
                node.zCoord += rand.nextGaussian() * smokeJitter;
                node.yCoord += smokeLift * dScale;

                pair.value = Math.max(0, pair.value - (1D / (double) nodeLife));
            }

            if(particleAge < maxSmokeGen || isInWater()) {
                smokeNodes.add(new Tuple.Pair<Vec3, Double>(Vec3.createVectorHelper(0, 0, 0), smokeNodes.isEmpty() ? 0.0D : 1D));
            }
        }

        prevRotationPitch = rotationPitch;
        prevRotationYaw = rotationYaw;

        if(onGround) {
            rotationPitch = 0;
        } else {
            rotationPitch += momentumPitch;
            rotationYaw += momentumYaw;
        }
    }

    /** Used for frame-perfect translation of smoke */
    private boolean setupDeltas = false;
    private double prevRenderX;
    private double prevRenderY;
    private double prevRenderZ;

    @Override
    public void renderParticle(BufferBuilder buffer, Entity entityIn, float interp, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {

        GL11.glPushMatrix();
        RenderHelper.enableStandardItemLighting();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glDepthMask(true);

        double pX = prevPosX + (posX - prevPosX) * interp;
        double pY = prevPosY + (posY - prevPosY) * interp;
        double pZ = prevPosZ + (posZ - prevPosZ) * interp;

        if(!setupDeltas) {
            prevRenderX = pX;
            prevRenderY = pY;
            prevRenderZ = pZ;
            setupDeltas = true;
        }
        BlockPos blockPos = new BlockPos(pX, pY, pZ);
        int brightness = world.getCombinedLight(blockPos, 0);
        int lX = brightness % 65536;
        int lY = brightness / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)lX / 1.0F, (float)lY / 1.0F);

        textureManager.bindTexture(ResourceManager.casings_tex);

        EntityPlayer player = Minecraft.getMinecraft().player;
        double dX = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double)interp;
        double dY = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double)interp;
        double dZ = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double)interp;

        GL11.glTranslated(pX - dX, pY - dY - this.height / 4 + config.getScaleY() * 0.01, pZ - dZ);

        GL11.glScalef(dScale, dScale, dScale);

        GL11.glRotatef(180 - momentumYaw, 0, 1, 0);
        GL11.glRotatef(-momentumPitch, 1, 0, 0);

        GL11.glScalef(config.getScaleX(), config.getScaleY(), config.getScaleZ());

        int index = 0;
        for(String name : config.getType().partNames) {
            int col = this.config.getColors()[index]; //unsafe on purpose, set your colors properly or else...!
            Color color = new Color(col);
            GL11.glColor3f(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F);
            ResourceManager.casings.renderPart(name);
            index++;
        }

        GL11.glColor3f(1F, 1F, 1F);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glTranslated(pX - dX, pY - dY - this.height / 4, pZ - dZ);
        //GL11.glScalef(dScale, dScale, dScale);
        //GL11.glScalef(config.getScaleX(), config.getScaleY(), config.getScaleZ());

        if(!smokeNodes.isEmpty()) {
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferbuilder = tessellator.getBuffer();
            bufferbuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);

            float scale = config.getScaleX() * 0.5F * dScale;
            Vec3 vec = Vec3.createVectorHelper(scale, 0, 0);
            float yaw = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * interp;
            vec.rotateAroundY((float) Math.toRadians(-yaw));

            double deltaX = prevRenderX - pX;
            double deltaY = prevRenderY - pY;
            double deltaZ = prevRenderZ - pZ;

            for(Tuple.Pair<Vec3, Double> pair : smokeNodes) {
                Vec3 pos = pair.getKey();
                double mult = 1D;
                pos.xCoord += deltaX * mult;
                pos.yCoord += deltaY * mult;
                pos.zCoord += deltaZ * mult;

            }

            for(int i = 0; i < smokeNodes.size() - 1; i++) {
                final Tuple.Pair<Vec3, Double> node = smokeNodes.get(i), past = smokeNodes.get(i + 1);
                final Vec3 nodeLoc = node.getKey(), pastLoc = past.getKey();
                float nodeAlpha = node.getValue().floatValue();
                float pastAlpha = past.getValue().floatValue();

                double timeAlpha = 1D - (double) particleAge / (double) maxSmokeGen;
                nodeAlpha *= timeAlpha;
                pastAlpha *= timeAlpha;

                bufferbuilder.pos(nodeLoc.xCoord, nodeLoc.yCoord, nodeLoc.zCoord).color(1F, 1F, 1F, nodeAlpha).endVertex();
                bufferbuilder.pos(nodeLoc.xCoord + vec.xCoord, nodeLoc.yCoord, nodeLoc.zCoord + vec.zCoord).color(1F, 1F, 1F, 0F).endVertex();
                bufferbuilder.pos(pastLoc.xCoord + vec.xCoord, pastLoc.yCoord, pastLoc.zCoord + vec.zCoord).color(1F, 1F, 1F, 0F).endVertex();
                bufferbuilder.pos(pastLoc.xCoord, pastLoc.yCoord, pastLoc.zCoord).color(1F, 1F, 1F, pastAlpha).endVertex();

                bufferbuilder.pos(nodeLoc.xCoord, nodeLoc.yCoord, nodeLoc.zCoord).color(1F, 1F, 1F, nodeAlpha).endVertex();
                bufferbuilder.pos(nodeLoc.xCoord - vec.xCoord, nodeLoc.yCoord, nodeLoc.zCoord - vec.zCoord).color(1F, 1F, 1F, 0F).endVertex();
                bufferbuilder.pos(pastLoc.xCoord - vec.xCoord, pastLoc.yCoord, pastLoc.zCoord - vec.zCoord).color(1F, 1F, 1F, 0F).endVertex();
                bufferbuilder.pos(pastLoc.xCoord, pastLoc.yCoord, pastLoc.zCoord).color(1F, 1F, 1F, pastAlpha).endVertex();
            }

            GL11.glAlphaFunc(GL11.GL_GREATER, 0F);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_CULL_FACE);
            tessellator.draw();
            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glAlphaFunc(GL11.GL_GEQUAL, 0.1F);
        }

        GL11.glShadeModel(GL11.GL_FLAT);
        GL11.glPopMatrix();

        RenderHelper.disableStandardItemLighting();

        prevRenderX = pX;
        prevRenderY = pY;
        prevRenderZ = pZ;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float partialTicks) {
        int x = MathHelper.floor(this.posX);
        int z = MathHelper.floor(this.posZ);

        if(this.world.isBlockLoaded(new BlockPos(x, 0, z))) {
            double d0 = (this.getBoundingBox().maxY - this.getBoundingBox().minY) * 0.66D;
            int y = MathHelper.floor(this.posY + d0);
            return this.world.getCombinedLight(new BlockPos(x, y, z), 0);
        } else {
            return 0;
        }
    }

    private void tryPlayBounceSound() {

        SoundEvent sound = config.getSound();

        if(sound != null) {
            world.playSound(null, this.posX, this.posY, this.posZ, sound, SoundCategory.NEUTRAL, 2.0F, 1.0F);
        }
    }

    public double getPosX() {
        return this.posX;
    }

    public double getPosY() {
        return this.posY;
    }

    public double getPosZ() {
        return this.posZ;
    }
}
