package com.hbm.tileentity.machine.rbmk;

import com.hbm.lib.Library;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.interfaces.ITankPacketAcceptor;
import com.hbm.tileentity.machine.rbmk.TileEntityRBMKConsole.ColumnType;

import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.common.capabilities.Capability;

import java.util.List;

public class TileEntityRBMKCooler extends TileEntityRBMKBase implements IFluidHandler, ITankPacketAcceptor {

	public FluidTank tank;
	public int lastCooled;

	public TileEntityRBMKCooler() {
		super();
		this.tank = new FluidTank(ModForgeFluids.cryogel, 0, 16000);
	}

	public void getDiagData(NBTTagCompound nbt) {
		this.writeToNBT(nbt);
		nbt.removeTag("jumpheight");
	}

	@Override
	public void update() {

		if(!world.isRemote) {

			if((int) (this.heat) > 750) {

				int heatProvided = (int) (this.heat - 750D);
				int cooling = Math.min(heatProvided, tank.getFluidAmount());

				this.heat -= cooling;
				this.tank.drain(cooling, true);

				this.lastCooled = cooling;

				if(lastCooled > 0) {
					List<Entity> entities = world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(pos.getX(), pos.getY()+rbmkHeight, pos.getZ(), pos.getX()+1, pos.getY()+rbmkHeight+6, pos.getZ()+1));

					for(Entity e : entities) {
						e.setFire(5);
						e.attackEntityFrom(DamageSource.IN_FIRE, 10);
					}
				}
			} else {
				this.lastCooled = 0;
			}

			if(this.lastCooled > 100) {
				world.playSound(null, pos.getX() + 0.5, pos.getY() + rbmkHeight + 0.5, pos.getZ() + 0.5, HBMSoundHandler.flamethrowerShoot, SoundCategory.BLOCKS, 1.0F, 1.25F + world.rand.nextFloat());
				world.playSound(null, pos.getX() + 0.5, pos.getY() + rbmkHeight + 0.5, pos.getZ() + 0.5, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, 1F + world.rand.nextFloat() * 0.5F);
			} else if(this.lastCooled > 50){
				world.playSound(null, pos.getX() + 0.5, pos.getY() + rbmkHeight + 0.5, pos.getZ() + 0.5, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, 0.75F + world.rand.nextFloat() * 0.5F);
			} else if(this.lastCooled > 5){
				if(world.rand.nextInt(20) == 0) {
					world.playSound(null, pos.getX() + 0.5, pos.getY() + rbmkHeight + 0.5, pos.getZ() + 0.5, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, 0.25F + world.rand.nextFloat() * 0.5F);
				}
			}

		} else {

			if(this.lastCooled > 100) {

				for(int i = 0; i < 2; i++) {
					world.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + 0.25 + world.rand.nextDouble() * 0.5, pos.getY() + rbmkHeight + 0.5, pos.getZ() + 0.25 + world.rand.nextDouble() * 0.5, 0, 1, 0);
					world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.getX() + 0.25 + world.rand.nextDouble() * 0.5, pos.getY() + rbmkHeight + 0.5, pos.getZ() + 0.25 + world.rand.nextDouble() * 0.5, 0, 1, 0);
				}

				if(world.rand.nextInt(20) == 0){
					world.spawnParticle(EnumParticleTypes.LAVA, pos.getX() + 0.25 + world.rand.nextDouble() * 0.5, pos.getY() + rbmkHeight + 0.5, pos.getZ() + 0.25 + world.rand.nextDouble() * 0.5, 0, 0, 0);
				}

			} else if(this.lastCooled > 75) {

				for(int i = 0; i < 2; i++) {
					world.spawnParticle(EnumParticleTypes.CLOUD, pos.getX() + 0.25 + world.rand.nextDouble() * 0.5, pos.getY() + rbmkHeight + 0.5, pos.getZ() + 0.25 + world.rand.nextDouble() * 0.5, world.rand.nextGaussian() * 0.05, 0.5, world.rand.nextGaussian() * 0.05);
				}

				if(world.rand.nextInt(20) == 0){
					world.spawnParticle(EnumParticleTypes.LAVA, pos.getX() + 0.25 + world.rand.nextDouble() * 0.5, pos.getY() + rbmkHeight + 0.5, pos.getZ() + 0.25 + world.rand.nextDouble() * 0.5, 0, 0.0, 0);
				}

			} else if(this.lastCooled > 50) {

				for(int i = 0; i < 2; i++) {
					world.spawnParticle(EnumParticleTypes.CLOUD, pos.getX() + 0.25 + world.rand.nextDouble() * 0.5, pos.getY() + rbmkHeight + 0.5, pos.getZ() + 0.25 + world.rand.nextDouble() * 0.5, world.rand.nextGaussian() * 0.05, 0.3, world.rand.nextGaussian() * 0.05);
				}

			} else if(this.lastCooled > 5) {

				if(world.getTotalWorldTime() % 2 == 0){
					world.spawnParticle(EnumParticleTypes.CLOUD, pos.getX() + 0.25 + world.rand.nextDouble() * 0.5, pos.getY() + rbmkHeight + 0.5, pos.getZ() + 0.25 + world.rand.nextDouble() * 0.5, 0, 0.2, 0);
				}
			}
		}

		super.update();
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		tank.readFromNBT(nbt.getCompoundTag("cryo"));
		this.lastCooled = nbt.getInteger("cooled");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);

		nbt.setTag("cryo", tank.writeToNBT(new NBTTagCompound()));
		nbt.setInteger("cooled", this.lastCooled);
		return nbt;
	}

	@Override
    public void recievePacket(NBTTagCompound[] tags) {
        if (tags.length != 1) {
            return;
        } else {
            tank.readFromNBT(tags[0]);
        }
    }

	@Override
	public ColumnType getConsoleType() {
		return ColumnType.COOLER;
	}

	@Override
	public NBTTagCompound getNBTForConsole() {
		NBTTagCompound data = new NBTTagCompound();
		data.setInteger("cryo", this.tank.getFluidAmount());
		data.setInteger("cooled", this.lastCooled);
		return data;
	}

	@Override
	public IFluidTankProperties[] getTankProperties(){
		return new IFluidTankProperties[]{tank.getTankProperties()[0]};
	}

	@Override
	public int fill(FluidStack resource, boolean doFill){
		if(resource != null && resource.getFluid() == ModForgeFluids.cryogel){
			return tank.fill(resource, doFill);
		}
		return 0;
	}

	@Override
	public FluidStack drain(FluidStack resource, boolean doDrain){
		return null;
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain){
		return null;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing){
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing){
		if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this);
		return super.getCapability(capability, facing);
	}
}
