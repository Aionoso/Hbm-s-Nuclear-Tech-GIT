package com.hbm.tileentity.machine;

import api.hbm.energymk2.IEnergyReceiverMK2;
import com.hbm.packet.PacketDispatcher;
import com.hbm.forgefluid.FFUtils;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.lib.Library;
import com.hbm.lib.ForgeDirection;
import com.hbm.packet.FluidTankPacket;
import com.hbm.interfaces.ITankPacketAcceptor;
import com.hbm.tileentity.TileEntityMachineBase;

import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.EnumFacing;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityMachineUUCreator extends TileEntityMachineBase implements IEnergyReceiverMK2, IFluidHandler, ITickable, ITankPacketAcceptor {
	
	public int[] log = new int[20];
	public static final long rfPerMbOfUU = 1_000_000L;
	public FluidTank tank;
	public long power;
	public static final long maxPower = 5_000_000_000_000_000L;
	public double producedmb = 0;
	public boolean isOn;

	public TileEntityMachineUUCreator() {
		super(4);
		this.tank = new FluidTank(2_000_000_000);
	}

	@Override
	public String getName() {
		return "container.uuCreator";
	}

	@Override
	public void update() {
		if(!world.isRemote) {
			if(world.getTotalWorldTime() % 10 == 0) {
				fillFluidInit(tank);
			}
			this.updateConnections();

			power = Library.chargeTEFromItems(inventory, 0, power, maxPower);
			FFUtils.fillFluidContainer(inventory, tank, 2, 3);
			int loggedProducedMB = 0;
			if(isOn){
				if(power >= rfPerMbOfUU && this.tank.getFluidAmount() < this.tank.getCapacity()){
					int producedUUmB = (int)Math.min(power / rfPerMbOfUU, this.tank.getCapacity()-this.tank.getFluidAmount());
					
					if(producedUUmB > 0){
						producedUUmB = tank.fill(new FluidStack(ModForgeFluids.uu_matter, producedUUmB), true);
						power -= producedUUmB * rfPerMbOfUU;
						this.markDirty();
						loggedProducedMB = producedUUmB;
					}
				}
			}
			
			for(int i = 1; i < this.log.length; i++) {
				this.log[i - 1] = this.log[i];
			}
			this.log[this.log.length-1] = loggedProducedMB;

			producedmb = getAvgUU();
			PacketDispatcher.wrapper.sendToAllAround(new FluidTankPacket(pos, new FluidTank[] { tank }), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 100));

			NBTTagCompound data = new NBTTagCompound();
			data.setBoolean("isOn", isOn);
			data.setLong("power", power);
			data.setDouble("uuMB", producedmb);
			this.networkPack(data, 250);
		}
	}

	public double getAvgUU(){
		long sum = 0;
		for(int i = 0; i < this.log.length; i++) {
			sum += this.log[i];
		}
		return (double)(sum / (double)this.log.length);
	}

	@Override
	public void networkUnpack(NBTTagCompound data) {
		this.isOn = data.getBoolean("isOn");
		this.power = data.getLong("power");
		this.producedmb = data.getDouble("uuMB");
	}

	@Override
	public void handleButtonPacket(int value, int meta) {
		if(meta == 0) {
			this.isOn = !this.isOn;
		}
	}

	public int getPowerScaled(int i) {
		double powerScaled = (double)power / (double)maxPower;
		return (int)(i * powerScaled);
	}
	
	private void updateConnections() {
		this.trySubscribe(world, pos.getX(), pos.getY() + 3, pos.getZ(), ForgeDirection.UP);
		this.trySubscribe(world, pos.getX() + 2, pos.getY() + 3, pos.getZ(), ForgeDirection.UP);
		this.trySubscribe(world, pos.getX() + -2, pos.getY() + 3, pos.getZ(), ForgeDirection.UP);
		this.trySubscribe(world, pos.getX(), pos.getY() + 3, pos.getZ() + 2, ForgeDirection.UP);
		this.trySubscribe(world, pos.getX(), pos.getY() + 3, pos.getZ() - 2, ForgeDirection.UP);

		this.trySubscribe(world, pos.getX(), pos.getY() - 1, pos.getZ(), ForgeDirection.DOWN);
		this.trySubscribe(world, pos.getX() + 2, pos.getY() - 1, pos.getZ(), ForgeDirection.DOWN);
		this.trySubscribe(world, pos.getX() + -2, pos.getY() - 1, pos.getZ(), ForgeDirection.DOWN);
		this.trySubscribe(world, pos.getX(), pos.getY() - 1, pos.getZ() + 2, ForgeDirection.DOWN);
		this.trySubscribe(world, pos.getX(), pos.getY() - 1, pos.getZ() - 2, ForgeDirection.DOWN);
	}

	private void fillFluidInit(FluidTank tank) {
		boolean update =  false;
		update = FFUtils.fillFluid(this, tank, world, pos.add(0, 3, 0), 1_000_000_000) || update;
		update = FFUtils.fillFluid(this, tank, world, pos.add(2, 3, 0), 1_000_000_000) || update;
		update = FFUtils.fillFluid(this, tank, world, pos.add(-2, 3, 0), 1_000_000_000) || update;
		update = FFUtils.fillFluid(this, tank, world, pos.add(0, 3, 2), 1_000_000_000) || update;
		update = FFUtils.fillFluid(this, tank, world, pos.add(0, 3, -2), 1_000_000_000) || update;

		update = FFUtils.fillFluid(this, tank, world, pos.add(0, -1, 0), 1_000_000_000) || update;
		update = FFUtils.fillFluid(this, tank, world, pos.add(2, -1, 0), 1_000_000_000) || update;
		update = FFUtils.fillFluid(this, tank, world, pos.add(-2, -1, 0), 1_000_000_000) || update;
		update = FFUtils.fillFluid(this, tank, world, pos.add(0, -1, 2), 1_000_000_000) || update;
		update = FFUtils.fillFluid(this, tank, world, pos.add(0, -1, -2), 1_000_000_000) || update;
		if(update)
			markDirty();
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(
			pos.getX() - 3,
			pos.getY(),
			pos.getZ() - 3,
			pos.getX() + 4,
			pos.getY() + 3,
			pos.getZ() + 4
		);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared() {
		return 65536.0D;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.isOn = nbt.getBoolean("isOn");
		this.power = nbt.getLong("power");
		this.tank.readFromNBT(nbt.getCompoundTag("tank"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setBoolean("isOn", isOn);
		nbt.setLong("power", power);
		nbt.setTag("tank", this.tank.writeToNBT(new NBTTagCompound()));
		return nbt;
	}

	@Override
	public void recievePacket(NBTTagCompound[] tags) {
		if(tags.length == 1)
			tank.readFromNBT(tags[0]);
	}

	//FF stuff
	@Override
	public IFluidTankProperties[] getTankProperties(){
		return tank.getTankProperties();
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY){
			return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this);
		}
		return super.getCapability(capability, facing);
	}

	@Override
		public int fill(FluidStack resource, boolean doFill) {
			return 0;
		}

	@Override
	public FluidStack drain(FluidStack resource, boolean doDrain) {
		if(resource == null)
			return null;
		if(resource.isFluidEqual(tank.getFluid())) {
			return tank.drain(resource.amount, doDrain);
		}
		return null;
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		if(tank.getFluid() != null) {
			return tank.drain(maxDrain, doDrain);
		}
		return null;
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY){
			return true;
		}
		return super.hasCapability(capability, facing);
	}
	
	//
	@Override
	public long getPower() {
		return power;
	}

	@Override
	public void setPower(long i) {
		power = i;
	}

	@Override
	public long getMaxPower() {
		return maxPower;
	}
}
