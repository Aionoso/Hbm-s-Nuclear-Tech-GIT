package com.hbm.tileentity.machine;

import java.util.HashMap;

import api.hbm.energymk2.IEnergyProviderMK2;
import com.hbm.forgefluid.FFUtils;
import com.hbm.interfaces.ITankPacketAcceptor;
import com.hbm.lib.ForgeDirection;
import com.hbm.lib.Library;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.inventory.EngineRecipes;
import com.hbm.inventory.EngineRecipes.FuelGrade;
import com.hbm.packet.FluidTankPacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.tileentity.TileEntityMachineBase;

import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileEntityMachineDiesel extends TileEntityMachineBase implements ITickable, IEnergyProviderMK2, IFluidHandler, ITankPacketAcceptor {

	public long power;
	public int soundCycle = 0;
	public static final long maxPower = 50000;
	public long powerCap = 50000;
	public int age = 0;
	public FluidTank tank;
	public Fluid tankType;
	public boolean needsUpdate;

	private static final int[] slots_top = new int[] { 0 };
	private static final int[] slots_bottom = new int[] { 1, 2 };
	private static final int[] slots_side = new int[] { 2 };

	public static HashMap<FuelGrade, Double> fuelEfficiency = new HashMap();
	static {
		fuelEfficiency.put(FuelGrade.MEDIUM,	0.5D);
		fuelEfficiency.put(FuelGrade.HIGH,		0.75D);
		fuelEfficiency.put(FuelGrade.AERO,		0.1D);
	}

	public TileEntityMachineDiesel() {
		super(3);
		tank = new FluidTank(16000);
	}
	
	@Override
	public String getName() {
		return "container.machineDiesel";
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setLong("powerTime", power);
		compound.setLong("powerCap", powerCap);
		tank.writeToNBT(compound);
		return super.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		this.power = compound.getLong("powerTime");
		this.powerCap = compound.getLong("powerCap");
		tank.readFromNBT(compound);
		super.readFromNBT(compound);
	}
	
	@Override
	public int[] getAccessibleSlotsFromSide(EnumFacing e) {
		int p_94128_1_ = e.ordinal();
		return p_94128_1_ == 0 ? slots_bottom : (p_94128_1_ == 1 ? slots_top : slots_side);
	}
	
	public long getPowerScaled(long i) {
		return (power * i) / powerCap;
	}
	
	@Override
	public void update() {
		if(tank.getFluid() != null)
			tankType = tank.getFluid().getFluid();
		if (!world.isRemote) {
			if (needsUpdate) {
				needsUpdate = false;
			}
			for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
				this.tryProvide(world, pos.getX() + dir.offsetX, pos.getY() + dir.offsetY, pos.getZ() + dir.offsetZ, dir);
			}


			//Tank Management
			if(this.inputValidForTank(-1, 0))
				if(FFUtils.fillFromFluidContainer(inventory, tank, 0, 1))
					needsUpdate = true;

			Fluid type = tank.getFluid() == null ? null : tank.getFluid().getFluid();
			if(type != null && type == ModForgeFluids.nitan)
				powerCap = maxPower * 10;
			else
				powerCap = maxPower;
			
			// Battery Item
			power = Library.chargeItemsFromTE(inventory, 2, power, powerCap);

			generate();

			NBTTagCompound data = new NBTTagCompound();
			data.setInteger("power", (int) power);
			data.setInteger("powerCap", (int) powerCap);
			this.networkPack(data, 50);
			
			PacketDispatcher.wrapper.sendToAllAround(new FluidTankPacket(pos, new FluidTank[] {tank}), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 10));
		}
	}
	
	@Override
	public void networkUnpack(NBTTagCompound data) {
		power = data.getInteger("power");
		powerCap = data.getInteger("powerCap");
	}
	
	public boolean hasAcceptableFuel() {
		return getHEFromFuel() > 0;
	}
	
	public long getHEFromFuel() {
		if(tank.getFluid() == null) return 0;
		return getHEFromFuel(tank.getFluid().getFluid());
	}
	
	public static long getHEFromFuel(Fluid type) {
		if(EngineRecipes.hasFuelRecipe(type)) {
			FuelGrade grade = EngineRecipes.getFuelGrade(type);
			double efficiency = fuelEfficiency.containsKey(grade) ? fuelEfficiency.get(grade) : 0;
			return (long) (EngineRecipes.getEnergy(type) / 1000L * efficiency);
		}
		
		return 0;
	}

	public void generate() {
		if (hasAcceptableFuel()) {
			if (tank.getFluidAmount() > 0) {
				if (soundCycle == 0) {
					this.world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_FIREWORK_BLAST, SoundCategory.BLOCKS, 0.5F, -100.0F);
				}
				soundCycle++;

				if (soundCycle >= 5)
					soundCycle = 0;

				tank.drain(1, true);
				needsUpdate = true;
				if (power + getHEFromFuel() <= powerCap) {
					power += getHEFromFuel();
				} else {
					power = powerCap;
				}
			}
		}
	}
	protected boolean inputValidForTank(int tank, int slot){
		if(!inventory.getStackInSlot(slot).isEmpty()){
			if(isValidFluid(FluidUtil.getFluidContained(inventory.getStackInSlot(slot)))){
				return true;	
			}
		}
		return false;
	}

	private boolean isValidFluid(FluidStack stack) {
		if(stack == null)
			return false;
		return getHEFromFuel(stack.getFluid()) > 0;
	}

	@Override
	public IFluidTankProperties[] getTankProperties() {
		return new IFluidTankProperties[]{tank.getTankProperties()[0]};
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		if (isValidFluid(resource)) {
			return tank.fill(resource, doFill);
		}
		return 0;
	}

	@Override
	public FluidStack drain(FluidStack resource, boolean doDrain) {
		return null;
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		return null;
	}

	@Override
	public void recievePacket(NBTTagCompound[] tags) {
		if(tags.length != 1){
			return;
		} else {
			tank.readFromNBT(tags[0]);
		}
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
			return true;
		} else if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY){
			return true;
		} else {
			return super.hasCapability(capability, facing);
		}
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory);
		} else if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY){
			return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this);
		} else {
			return super.getCapability(capability, facing);
		}
	}

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
