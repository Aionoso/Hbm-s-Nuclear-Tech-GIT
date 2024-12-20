package com.hbm.tileentity.machine;

import api.hbm.energymk2.IEnergyReceiverMK2;
import com.hbm.interfaces.ITankPacketAcceptor;
import com.hbm.forgefluid.FFUtils;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.lib.ForgeDirection;
import com.hbm.tileentity.INBTPacketReceiver;
import com.hbm.tileentity.TileEntityLoadedBase;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

public class TileEntityDeuteriumExtractor extends TileEntityLoadedBase implements ITickable, IFluidHandler, IEnergyReceiverMK2, ITankPacketAcceptor, INBTPacketReceiver {
	
	public int age = 0;
	public long power = 0;
	public FluidTank[] tanks;

	public TileEntityDeuteriumExtractor() {
		tanks = new FluidTank[2];
		tanks[0] = new FluidTank(FluidRegistry.WATER, 0, 1000);
		tanks[1] = new FluidTank(ModForgeFluids.heavywater, 0, 100);
	}

	@Override
	public void update() {
		
		if(!world.isRemote) {
			
			updateConnections();

			age++;
			if(age >= 20) {
				age = 0;
			}

			if(age == 9 || age == 19)
				fillFluidInit(tanks[1]);
			
			if(hasPower() && hasEnoughWater() && tanks[1].getCapacity() > tanks[1].getFluidAmount()) {
				int convert = Math.min(tanks[1].getCapacity(), tanks[0].getFluidAmount()) / 50;
				convert = Math.min(convert, tanks[1].getCapacity() - tanks[1].getFluidAmount());
				
				tanks[0].drain(convert * 50, true); //dividing first, then multiplying, will remove any rounding issues
				tanks[1].fill(new FluidStack(ModForgeFluids.heavywater, convert), true);
				power -= this.getMaxPower() / 20;
				this.markDirty();
			}

			NBTTagCompound data = new NBTTagCompound();
			data.setLong("power", power);
			data.setTag("tanks", FFUtils.serializeTankArray(tanks));
			
			INBTPacketReceiver.networkPack(this, data, 50);
		}
	}
	
	protected void updateConnections() {
		for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) this.trySubscribe(world, pos.getX() + dir.offsetX, pos.getY() + dir.offsetY, pos.getZ() + dir.offsetZ, dir);
	}

	public void fillFluidInit(FluidTank tank) {
		FFUtils.fillFluid(this, tank, world, pos.west(), 100);
		FFUtils.fillFluid(this, tank, world, pos.east(), 100);
		FFUtils.fillFluid(this, tank, world, pos.down(), 100);
		FFUtils.fillFluid(this, tank, world, pos.up(), 100);
		FFUtils.fillFluid(this, tank, world, pos.north(), 100);
		FFUtils.fillFluid(this, tank, world, pos.south(), 100);
	}

	@Override
	public void networkUnpack(NBTTagCompound data) {
		this.power = data.getLong("power");
		if(data.hasKey("tanks"))
			FFUtils.deserializeTankArray(data.getTagList("tanks", 10), tanks);
	}

	public boolean hasPower() {
		return power >= this.getMaxPower() / 20;
	}

	public boolean hasEnoughWater() {
		return tanks[0].getFluidAmount() >= 100;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.power = nbt.getLong("power");
		if(nbt.hasKey("tanks"))
			FFUtils.deserializeTankArray(nbt.getTagList("tanks", 10), tanks);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setLong("power", power);
		nbt.setTag("tanks", FFUtils.serializeTankArray(tanks));
		return super.writeToNBT(nbt);
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
		return 10_000;
	}

	@Override
	public void recievePacket(NBTTagCompound[] tags) {
		if(tags.length != 2) {
			return;
		} else {
			tanks[0].readFromNBT(tags[0]);
			tanks[1].readFromNBT(tags[1]);
		}
	}

	@Override
	public IFluidTankProperties[] getTankProperties() {
		return new IFluidTankProperties[] { tanks[0].getTankProperties()[0], tanks[1].getTankProperties()[0] };
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		if(resource.getFluid() == FluidRegistry.WATER) {
			return tanks[0].fill(resource, doFill);
		}
		return 0;
	}

	@Override
	public FluidStack drain(FluidStack resource, boolean doDrain) {
		if(resource == null || !resource.isFluidEqual(tanks[1].getFluid())) {
			return null;
		}
		return tanks[1].drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		return tanks[1].drain(maxDrain, doDrain);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this);
		} else {
			return super.getCapability(capability, facing);
		}
	}
}