package com.hbm.entity.projectile;

import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ReportedException;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

abstract public class EntityDebrisBase extends Entity {
    public static final DataParameter<Integer> TYPE_ID = EntityDataManager.createKey(EntityDebrisBase.class, DataSerializers.VARINT);

    public float rot;
    public float lastRot;
    protected boolean hasSizeSet = false;

    public EntityDebrisBase(World world) {
        super(world);
    }

    @Override
    protected void entityInit() {
        this.dataManager.register(TYPE_ID, 0);
        this.rot = this.lastRot = this.rand.nextFloat() * 360;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    abstract public boolean interactFirst(EntityPlayer player);

    abstract protected int getLifetime();

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbt) {
        this.dataManager.set(TYPE_ID, nbt.getInteger("debtype"));
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbt) {
        nbt.setInteger("debtype", this.dataManager.get(TYPE_ID));
    }

    @Override
    public void move(MoverType type, double moX, double moY, double moZ) {

        this.world.profiler.startSection("move");
        this.height *= 0.4F;

        if(this.isInWeb) {
            this.isInWeb = false;
        }

        double initMoX = moX;
        double initMoY = moY;
        double initMoZ = moZ;
        AxisAlignedBB axisalignedbb = this.getEntityBoundingBox();

        List list = this.world.getCollisionBoxes(this, this.getEntityBoundingBox().expand(moX, moY, moZ));

        for(int i = 0; i < list.size(); ++i) {
            moY = ((AxisAlignedBB) list.get(i)).calculateYOffset(this.getEntityBoundingBox(), moY);
        }

        this.getEntityBoundingBox().offset(0.0D, moY, 0.0D);

        boolean isGoingDown = this.onGround || initMoY != moY && initMoY < 0.0D;
        int j;

        for(j = 0; j < list.size(); ++j) {
            moX = ((AxisAlignedBB) list.get(j)).calculateXOffset(this.getEntityBoundingBox(), moX);
        }

        this.getEntityBoundingBox().offset(moX, 0.0D, 0.0D);

        for(j = 0; j < list.size(); ++j) {
            moZ = ((AxisAlignedBB) list.get(j)).calculateZOffset(this.getEntityBoundingBox(), moZ);
        }

        this.getEntityBoundingBox().offset(0.0D, 0.0D, moZ);

        double d10;
        double d11;
        int k;
        double d12;

        if(this.stepHeight > 0.0F && isGoingDown && this.height < 0.05F && (initMoX != moX || initMoZ != moZ)) {
            d12 = moX;
            d10 = moY;
            d11 = moZ;
            moX = initMoX;
            moY = (double) this.stepHeight;
            moZ = initMoZ;
            AxisAlignedBB axisalignedbb1 = this.getEntityBoundingBox();
            this.setEntityBoundingBox(axisalignedbb);
            list = this.world.getCollisionBoxes(this, this.getEntityBoundingBox().expand(initMoX, moY, initMoZ));

            for(k = 0; k < list.size(); ++k) {
                moY = ((AxisAlignedBB) list.get(k)).calculateYOffset(this.getEntityBoundingBox(), moY);
            }

            this.getEntityBoundingBox().offset(0.0D, moY, 0.0D);

            for(k = 0; k < list.size(); ++k) {
                moX = ((AxisAlignedBB) list.get(k)).calculateXOffset(this.getEntityBoundingBox(), moX);
            }

            this.getEntityBoundingBox().offset(moX, 0.0D, 0.0D);

            for(k = 0; k < list.size(); ++k) {
                moZ = ((AxisAlignedBB) list.get(k)).calculateZOffset(this.getEntityBoundingBox(), moZ);
            }

            this.getEntityBoundingBox().offset(0.0D, 0.0D, moZ);
                moY = (double) (-this.stepHeight);

                for(k = 0; k < list.size(); ++k) {
                    moY = ((AxisAlignedBB) list.get(k)).calculateYOffset(this.getEntityBoundingBox(), moY);
                }

                this.getEntityBoundingBox().offset(0.0D, moY, 0.0D);

            if(d12 * d12 + d11 * d11 >= moX * moX + moZ * moZ) {
                moX = d12;
                moY = d10;
                moZ = d11;
                this.setEntityBoundingBox(axisalignedbb1);
            }
        }

        this.world.profiler.endSection();
        this.world.profiler.startSection("rest");
        this.posX = (this.getEntityBoundingBox().minX + this.getEntityBoundingBox().maxX) / 2.0D;
        this.posY = this.getEntityBoundingBox().minY + (double) this.getYOffset() - (double) this.height;
        this.posZ = (this.getEntityBoundingBox().minZ + this.getEntityBoundingBox().maxZ) / 2.0D;
        this.collidedHorizontally = initMoX != moX || initMoZ != moZ;
        this.collidedVertically = initMoY != moY;
        this.onGround = initMoY != moY && initMoY < 0.0D;
        this.collided = this.collidedHorizontally || this.collidedVertically;
        this.updateFallState(moY, this.onGround, world.getBlockState(new BlockPos(this.posX, this.posY, this.posZ)), new BlockPos(this.posX, this.posY, this.posZ));

        if(initMoX != moX) {
            //this.motionX = 0.0D;
            this.motionX *= -0.75D;

        }

        if(initMoY != moY) {
            this.motionY = 0.0D;
        }

        if(initMoZ != moZ) {
            //this.motionZ = 0.0D;
            this.motionZ *= -0.75D;
        }

        try {
            this.doBlockCollisions();
        } catch(Throwable throwable) {
            CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Checking entity block collision");
            CrashReportCategory crashreportcategory = crashreport.makeCategory("Entity being checked for collision");
            this.addEntityCrashInfo(crashreportcategory);
            throw new ReportedException(crashreport);
        }

        this.world.profiler.endSection();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(double dist) {

        int range = 128;
        return dist < range * range;
    }

}
