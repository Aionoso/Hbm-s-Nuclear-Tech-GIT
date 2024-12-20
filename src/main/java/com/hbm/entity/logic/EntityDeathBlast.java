package com.hbm.entity.logic;

import com.hbm.config.CompatibilityConfig;
import com.hbm.entity.projectile.EntityBulletBase;
import com.hbm.handler.BulletConfigSyncingUtil;
import com.hbm.interfaces.IConstantRenderer;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.packet.AuxParticlePacketNT;
import com.hbm.packet.PacketDispatcher;
import com.hbm.render.amlfrom1710.Vec3;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityDeathBlast extends Entity implements IConstantRenderer {

	public static final int maxAge = 60;
	
	public EntityDeathBlast(World worldIn) {
		super(worldIn);
		this.ignoreFrustumCheck = true;
	}

	@Override
	public void onUpdate() {
		if(this.ticksExisted >= maxAge && !world.isRemote) {
			this.setDead();

			if(CompatibilityConfig.isWarDim(world)){
				world.spawnEntity(EntityNukeExplosionMK5.statFacNoRad(world, 40, posX, posY, posZ));
				
				int count = 100;
				for(int i = 0; i < count; i++) {
					
					Vec3 vec = Vec3.createVectorHelper(0.2, 0, 0);
					vec.rotateAroundY((float)(2 * Math.PI * i / (float)count));
					
					EntityBulletBase laser = new EntityBulletBase(world, BulletConfigSyncingUtil.MASKMAN_BOLT);
					laser.setPosition(posX, posY + 2, posZ);
					laser.motionX = vec.xCoord;
					laser.motionZ = vec.zCoord;
					laser.motionY = -0.01;
					world.spawnEntity(laser);
				}
			}
			
			NBTTagCompound data = new NBTTagCompound();
			data.setString("type", "muke");
			PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(data, posX, posY + 0.5, posZ), new TargetPoint(world.provider.getDimension(), posX, posY, posZ, 250));
			world.playSound(null, posX, posY, posZ, HBMSoundHandler.mukeExplosion, SoundCategory.HOSTILE, 25.0F, 0.9F);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public int getBrightnessForRender()
    {
        return 15728880;
    }

    @Override
	public float getBrightness()
    {
        return 1.0F;
    }
	
    @Override
	@SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(double distance)
    {
        return distance < 25000;
    }
	
	@Override
	protected void entityInit() {
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
	}

}
