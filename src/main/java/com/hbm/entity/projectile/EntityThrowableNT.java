package com.hbm.entity.projectile;

import com.hbm.render.amlfrom1710.Vec3;
import com.hbm.util.TrackerUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
/**
 * Near-identical copy of EntityThrowable but deobfuscated & untangled
 * @author hbm
 *
 */
public abstract class EntityThrowableNT extends Entity implements IProjectile {
    private int stuckBlockX = -1;
    private int stuckBlockY = -1;
    private int stuckBlockZ = -1;
    private Block stuckBlock;
    protected boolean inGround;
    public int throwableShake;
    protected EntityLivingBase thrower;
    private String throwerName;
    private int ticksInGround;
    private int ticksInAir;
    private static final DataParameter<Byte> STUCK_IN = EntityDataManager.createKey(EntityThrowableNT.class, DataSerializers.BYTE);

    public EntityThrowableNT(World worldIn)
    {
        super(worldIn);
        this.setSize(0.25F, 0.25F);
    }

    public EntityThrowableNT(World worldIn, double x, double y, double z)
    {
        this(worldIn);
        this.ticksInGround = 0;
        this.setSize(0.25F, 0.25F);
        this.setPosition(x, y, z);
    }

    protected void entityInit()
    {
        this.dataManager.register(STUCK_IN, Byte.valueOf((byte)0));
    }

    public void setStuckIn(int side) {
        this.dataManager.set(STUCK_IN, (byte) side);
    }

    public int getStuckIn() {
        return this.dataManager.get(STUCK_IN);
    }

    protected float throwForce() {
        return 1.5F;
    }

    protected double headingForceMult() {
        return 0.0075D;
    }

    protected float throwAngle() {
        return 0.0F;
    }

    protected double motionMult() {
        return 1.0D;
    }

    @SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(double distance)
    {
        double d0 = this.getEntityBoundingBox().getAverageEdgeLength() * 4.0D;

        if (Double.isNaN(d0))
        {
            d0 = 4.0D;
        }

        d0 = d0 * 64.0D;
        return distance < d0 * d0;
    }

    public EntityThrowableNT(World world, EntityLivingBase thrower) {
        super(world);
        this.thrower = thrower;
        this.setSize(0.25F, 0.25F);
        this.setLocationAndAngles(thrower.posX, thrower.posY + (double) thrower.getEyeHeight(), thrower.posZ, thrower.rotationYaw, thrower.rotationPitch);
        this.posX -= (double) (MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
        this.posY -= 0.1D;
        this.posZ -= (double) (MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
        this.setPosition(this.posX, this.posY, this.posZ);
        float velocity = 0.4F;
        this.motionX = (double) (-MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI) * velocity);
        this.motionZ = (double) (MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI) * velocity);
        this.motionY = (double) (-MathHelper.sin((this.rotationPitch + this.throwAngle()) / 180.0F * (float) Math.PI) * velocity);
        this.shoot(this.motionX, this.motionY, this.motionZ, this.throwForce(), 1.0F);
    }

    @Override
    public void shoot(double motionX, double motionY, double motionZ, float velocity, float inaccuracy) {
        float throwLen = MathHelper.sqrt(motionX * motionX + motionY * motionY + motionZ * motionZ);
        motionX /= (double) throwLen;
        motionY /= (double) throwLen;
        motionZ /= (double) throwLen;
        motionX += this.rand.nextGaussian() * headingForceMult() * (double) inaccuracy;
        motionY += this.rand.nextGaussian() * headingForceMult() * (double) inaccuracy;
        motionZ += this.rand.nextGaussian() * headingForceMult() * (double) inaccuracy;
        motionX *= (double) velocity;
        motionY *= (double) velocity;
        motionZ *= (double) velocity;
        this.motionX = motionX;
        this.motionY = motionY;
        this.motionZ = motionZ;
        float hyp = MathHelper.sqrt(motionX * motionX + motionZ * motionZ);
        this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(motionX, motionZ) * 180.0D / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(motionY, (double) hyp) * 180.0D / Math.PI);
        this.ticksInGround = 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setVelocity(double x, double y, double z) {
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;

        if(this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
            float hyp = MathHelper.sqrt(x * x + z * z);
            this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(x, z) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(y, (double) hyp) * 180.0D / Math.PI);
        }
    }
    @Override
    public void onUpdate() {
        super.onUpdate();

        if(this.throwableShake > 0) {
            --this.throwableShake;
        }

        if(this.inGround) {
            if(this.world.getBlockState(new BlockPos(this.stuckBlockX, this.stuckBlockY, this.stuckBlockZ)) == this.stuckBlock) {
                ++this.ticksInGround;

                if(this.groundDespawn() > 0 && this.ticksInGround == this.groundDespawn()) {
                    this.setDead();
                }

                return;

            } else {

                this.inGround = false;
                this.motionX *= (double) (this.rand.nextFloat() * 0.2F);
                this.motionY *= (double) (this.rand.nextFloat() * 0.2F);
                this.motionZ *= (double) (this.rand.nextFloat() * 0.2F);
                this.ticksInGround = 0;
                this.ticksInAir = 0;
            }

        } else {
            ++this.ticksInAir;

            Vec3d pos = new Vec3d(this.posX, this.posY, this.posZ);
            Vec3d nextPos = new Vec3d(this.posX + this.motionX * motionMult(), this.posY + this.motionY * motionMult(), this.posZ + this.motionZ * motionMult());
            RayTraceResult mop = null;
            if(!this.isSpectral()) mop = this.world.rayTraceBlocks(pos, nextPos, false, true, false);
            pos = new Vec3d(this.posX, this.posY, this.posZ);
            nextPos = new Vec3d(this.posX + this.motionX * motionMult(), this.posY + this.motionY * motionMult(), this.posZ + this.motionZ * motionMult());

            if(mop != null) {
                nextPos = new Vec3d(mop.hitVec.x, mop.hitVec.y, mop.hitVec.z);
            }

            if(!this.world.isRemote && this.doesImpactEntities()) {

                Entity hitEntity = null;
                List list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(this.motionX * motionMult(), this.motionY * motionMult(), this.motionZ * motionMult()).expand(1.0D, 1.0D, 1.0D));
                double nearest = 0.0D;
                EntityLivingBase thrower = this.getThrower();
                RayTraceResult nonPenImpact = null;

                for(int j = 0; j < list.size(); ++j) {
                    Entity entity = (Entity) list.get(j);

                    if(entity.canBeCollidedWith() && (entity != thrower || this.ticksInAir >= this.selfDamageDelay())) {
                        double hitbox = 0.3F;
                        AxisAlignedBB aabb = entity.getEntityBoundingBox().expand(hitbox, hitbox, hitbox);
                        RayTraceResult hitMop = aabb.calculateIntercept(pos, nextPos);

                        if(hitMop != null) {

                            // if penetration is enabled, run impact for all intersecting entities
                            if(this.doesPenetrate()) {
                                this.onImpact(new RayTraceResult(entity, hitMop.hitVec));
                            } else {

                                double dist = pos.distanceTo(hitMop.hitVec);

                                if(dist < nearest || nearest == 0.0D) {
                                    hitEntity = entity;
                                    nearest = dist;
                                    nonPenImpact = hitMop;
                                }
                            }
                        }
                    }
                }

                // if not, only run it for the closest MOP
                if(!this.doesPenetrate() && hitEntity != null) {
                    mop = new RayTraceResult(hitEntity, nonPenImpact.hitVec);
                }
            }

            if(mop != null) {
                if(mop.typeOfHit == RayTraceResult.Type.BLOCK && this.world.getBlockState(mop.getBlockPos()) == Blocks.PORTAL) {
                    this.setPortal(mop.getBlockPos());
                } else {
                    this.onImpact(mop);
                }
            }

            if(!this.onGround) {
                float hyp = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
                this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

                for(this.rotationPitch = (float) (Math.atan2(this.motionY, (double) hyp) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F);

                while(this.rotationPitch - this.prevRotationPitch >= 180.0F) this.prevRotationPitch += 360.0F;
                while(this.rotationYaw - this.prevRotationYaw < -180.0F) this.prevRotationYaw -= 360.0F;
                while(this.rotationYaw - this.prevRotationYaw >= 180.0F) this.prevRotationYaw += 360.0F;

                this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
                this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
            }

            float drag = this.getAirDrag();
            double gravity = this.getGravityVelocity();

            this.posX += this.motionX * motionMult();
            this.posY += this.motionY * motionMult();
            this.posZ += this.motionZ * motionMult();

            if(this.isInWater()) {
                for(int i = 0; i < 4; ++i) {
                    float f = 0.25F;
                    this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * (double) f, this.posY - this.motionY * (double) f, this.posZ - this.motionZ * (double) f, this.motionX, this.motionY, this.motionZ);
                }

                drag = this.getWaterDrag();
            }

            this.motionX *= (double) drag;
            this.motionY *= (double) drag;
            this.motionZ *= (double) drag;
            this.motionY -= gravity;
            this.setPosition(this.posX, this.posY, this.posZ);
        }
    }

    public boolean doesImpactEntities() {
        return true;
    }

    public boolean doesPenetrate() {
        return false;
    }

    public boolean isSpectral() {
        return false;
    }

    public int selfDamageDelay() {
        return 5;
    }

    public void getStuck(BlockPos pos, int side) {
        this.stuckBlockX = pos.getX();
        this.stuckBlockY = pos.getY();
        this.stuckBlockZ = pos.getZ();
        this.stuckBlock = world.getBlockState(pos).getBlock();
        this.inGround = true;
        this.motionX = 0;
        this.motionY = 0;
        this.motionZ = 0;
        this.setStuckIn(side);
        TrackerUtil.sendTeleport(world, this);
    }

    public double getGravityVelocity() {
        return 0.03D;
    }

    protected abstract void onImpact(RayTraceResult result);

    public void writeEntityToNBT(NBTTagCompound compound)
    {
        compound.setInteger("xTile", this.stuckBlockX);
        compound.setInteger("yTile", this.stuckBlockY);
        compound.setInteger("zTile", this.stuckBlockZ);
        compound.setByte("inTile", (byte) Block.getIdFromBlock(this.stuckBlock));
        compound.setByte("shake", (byte)this.throwableShake);
        compound.setByte("inGround", (byte)(this.inGround ? 1 : 0));

        if ((this.throwerName == null || this.throwerName.isEmpty()) && this.thrower instanceof EntityPlayer)
        {
            this.throwerName = this.thrower.getCommandSenderEntity().getName();
        }

        compound.setString("ownerName", this.throwerName == null ? "" : this.throwerName);
    }

    public void readEntityFromNBT(NBTTagCompound compound)
    {
        this.stuckBlockX = compound.getShort("xTile");
        this.stuckBlockY = compound.getShort("yTile");
        this.stuckBlockZ = compound.getShort("zTile");
        this.stuckBlock = Block.getBlockById(compound.getByte("inTile") & 255);
        this.throwableShake = compound.getByte("shake") & 255;
        this.inGround = compound.getByte("inGround") == 1;
        this.throwerName = compound.getString("ownerName");

        if (this.throwerName != null && this.throwerName.isEmpty()) this.throwerName = null;
    }

    public void setThrower(EntityLivingBase thrower) {
        this.thrower = thrower;
    }

    public EntityLivingBase getThrower() {
        if(this.thrower == null && this.throwerName != null && this.throwerName.length() > 0) {
            this.thrower = this.world.getPlayerEntityByName(this.throwerName);
        }
        return this.thrower;
    }

    /* ================================== Additional Getters =====================================*/

    protected float getAirDrag() { return 0.99F; }

    protected float getWaterDrag() { return 0.8F; }

    protected int groundDespawn() { return 1200; }
}
