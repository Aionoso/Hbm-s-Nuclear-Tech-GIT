package com.hbm.tileentity.machine;

import java.util.List;

import api.hbm.energymk2.IEnergyReceiverMK2;
import com.hbm.lib.ForgeDirection;
import com.hbm.lib.ModDamageSource;
import com.hbm.packet.AuxElectricityPacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.tileentity.INBTPacketReceiver;
import com.hbm.tileentity.TileEntityLoadedBase;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

public class TileEntityMachineTeleporter extends TileEntityLoadedBase implements ITickable, IEnergyReceiverMK2, INBTPacketReceiver {

	public long power = 0;
	public BlockPos target = null;
	public boolean linked = false;
	public boolean prevLinked = false;
	public byte packageTimer = 0;
	public static final int consumption = 100000000;
	public static final int maxPower = 1000000000;

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		power = compound.getLong("power");
		if(compound.getBoolean("hastarget")) {
			int x = compound.getInteger("x1");
			int y = compound.getInteger("y1");
			int z = compound.getInteger("z1");
			target = new BlockPos(x, y, z);
		}
		linked = compound.getBoolean("linked");
		super.readFromNBT(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setLong("power", power);
		if(target != null) {
			compound.setBoolean("hastarget", true);
			compound.setInteger("x1", target.getX());
			compound.setInteger("y1", target.getY());
			compound.setInteger("z1", target.getZ());
		} else {
			compound.setBoolean("hastarget", false);
		}
		compound.setBoolean("linked", linked);
		return super.writeToNBT(compound);
	}

	@Override
	public void update() {
		boolean b0 = false;
		packageTimer++;
		if(!this.world.isRemote) {
			for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
				this.trySubscribe(world, pos.getX() + dir.offsetX, pos.getY() + dir.offsetY, pos.getZ() + dir.offsetZ, dir);
			List<Entity> entities = this.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(pos.getX() - 0.25, pos.getY(), pos.getZ() - 0.25, pos.getX() + 0.75, pos.getY() + 2, pos.getZ() + 0.75));
			if(!entities.isEmpty())
				for(Entity e : entities) {
					if(e.ticksExisted >= 10) {
						teleport(e);
						b0 = true;
					}
				}

			PacketDispatcher.wrapper.sendToAllAround(new AuxElectricityPacket(pos, power), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 10));
			networkPack();
			prevLinked = linked;
		}

		if(b0)
			world.spawnParticle(EnumParticleTypes.PORTAL, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, 0.0D, 0.1D, 0.5D);
	}

	public void networkPack() {
		if(linked != prevLinked || packageTimer == 0){
			NBTTagCompound data = new NBTTagCompound();
			if(this.target != null){
				data.setInteger("targetX", this.target.getX());
				data.setInteger("targetY", this.target.getY());
				data.setInteger("targetZ", this.target.getZ());
			}
			data.setBoolean("linked", this.linked);
			INBTPacketReceiver.networkPack(this, data, 150);
			packageTimer = 40;
		}
	}

	@Override
	public void networkUnpack(NBTTagCompound data) {
		if(data.hasKey("targetX")){
			this.target = new BlockPos(data.getInteger("targetX"), data.getInteger("targetY"), data.getInteger("targetZ"));
		}
		if(data.hasKey("linked")){
			this.linked = data.getBoolean("linked");
		}
	}
	
	public void teleport(Entity entity) {

		if (this.power < consumption)
			return;

		world.playSound(null, entity.getPosition(), SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.BLOCKS,  1.0F, 1.0F);
		if (target == null && !this.linked) {
			entity.attackEntityFrom(ModDamageSource.teleporter, 10000);
		} else {
			if ((entity instanceof EntityPlayerMP)) {
				((EntityPlayerMP) entity).setPositionAndUpdate(target.getX() + 0.5D, target.getY() + 1.6D + entity.getYOffset(), target.getZ() + 0.5D);
			} else {
				entity.setPositionAndRotation(target.getX() + 0.5D, target.getY() + 1.6D + entity.getYOffset(), target.getZ() + 0.5D, entity.rotationYaw, entity.rotationPitch);
			}
			world.playSound(null, target, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.BLOCKS,  1.0F, 1.0F);
		}
		
		this.power -= consumption;
	}

	@Override
	public void setPower(long i) {
		power = i;
	}

	@Override
	public long getPower() {
		return power;
	}

	@Override
	public long getMaxPower() {
		return maxPower;
	}
}
