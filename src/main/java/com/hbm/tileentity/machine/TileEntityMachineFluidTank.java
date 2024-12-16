package com.hbm.tileentity.machine;

import com.hbm.forgefluid.FFUtils;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.interfaces.ITankPacketAcceptor;
import com.hbm.inventory.control_panel.*;
import com.hbm.main.MainRegistry;
import com.hbm.packet.FluidTankPacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.tileentity.TileEntityMachineBase;


import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Level;


import java.util.*;


public class TileEntityMachineFluidTank extends TileEntityMachineBase implements ITickable, IFluidHandler, ITankPacketAcceptor, IControllable {

	public FluidTank tank;

	public short mode = 0;
	public static final short modes = 4;
	public int age = 0;
	public static int[] slots = { 2 };
	public Fluid lastType = null;
	
	public TileEntityMachineFluidTank() {
		super(6);
		tank = new FluidTank(256000);
	}
	
	public String getName() {
		return "container.fluidtank";
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		tank.readFromNBT(compound);
		mode = compound.getShort("mode");
		super.readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		tank.writeToNBT(compound);
		compound.setShort("mode", mode);
		return super.writeToNBT(compound);
	}
	
	@Override
	public int[] getAccessibleSlotsFromSide(EnumFacing e){
		return slots;
	}
	
	@Override
	public void update() {
		if (!world.isRemote) {
			age++;
			if (age >= 20) {
				age = 0;
			}

			if ((mode == 1 || mode == 2) && (age == 9 || age == 19))
				fillFluidInit();
			
			FFUtils.fillFromFluidContainer(inventory, tank, 2, 3);
			FFUtils.fillFluidContainer(inventory, tank, 4, 5);
			
			if(tank.getFluid() != null && (tank.getFluid().getFluid() == ModForgeFluids.amat || tank.getFluid().getFluid() == ModForgeFluids.aschrab)) {
				world.destroyBlock(pos, false);
				world.newExplosion(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 5, true, true);
			}
			
			PacketDispatcher.wrapper.sendToAllTracking(new FluidTankPacket(pos.getX(), pos.getY(), pos.getZ(), new FluidTank[] {tank}), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 10));
			NBTTagCompound data = new NBTTagCompound();
			data.setShort("mode", mode);
			this.networkPack(data, 50);
			
			detectAndSendChanges();
		}
	}
	
	@Override
	public void networkUnpack(NBTTagCompound nbt) {
		mode = nbt.getShort("mode");
	}
	
	@Override
	public void handleButtonPacket(int value, int meta) {
		mode = (short) ((mode + 1) % modes);
		if (!world.isRemote) {
			broadcastControlEvt();
		}
		markDirty();
	}

	private void fillFluidInit() {
		if (tank.getFluid() != null) {
			FFUtils.fillFluid(this, tank, world, pos.add(2, 0, -1), 64000);
			FFUtils.fillFluid(this, tank, world, pos.add(2, 0, 1), 64000);
			FFUtils.fillFluid(this, tank, world, pos.add(-2, 0, -1), 64000);
			FFUtils.fillFluid(this, tank, world, pos.add(-2, 0, 1), 64000);
			FFUtils.fillFluid(this, tank, world, pos.add(-1, 0, 2), 64000);
			FFUtils.fillFluid(this, tank, world, pos.add(1, 0, 2), 64000);
			FFUtils.fillFluid(this, tank, world, pos.add(-1, 0, -2), 64000);
			FFUtils.fillFluid(this, tank, world, pos.add(1, 0, -2), 64000);
		}
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return TileEntity.INFINITE_EXTENT_AABB;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared() {
		return 65536.0D;
	}
	
	private FluidTank detectTank;
	
	private void detectAndSendChanges() {
		boolean mark = false;
		if(!FFUtils.areTanksEqual(tank, detectTank)){
			mark = true;
			detectTank = FFUtils.copyTank(tank);
		}
		if(mark)
			markDirty();
	}

	@Override
	public IFluidTankProperties[] getTankProperties() {
		return new IFluidTankProperties[]{tank.getTankProperties()[0]};
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		if (this.canFill(resource.getFluid())) {
			return tank.fill(resource, doFill);
		}
		return 0;
	}

	@Override
	public FluidStack drain(FluidStack resource, boolean doDrain) {
		if (resource == null || !resource.isFluidEqual(tank.getFluid())) {
			return null;
		}
		if (this.canDrain(resource.getFluid())) {
			return tank.drain(resource.amount, doDrain);
		}
		return null;
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		if (this.canDrain(null)) {
			return tank.drain(maxDrain, doDrain);
		}
		return null;
	}
	
	public boolean canFill(Fluid fluid) {
		if (!this.world.isRemote) {
			if(mode == 2 || mode == 3 || (tank.getFluid() != null && tank.getFluid().getFluid() != fluid))
				return false;
			else
				return true;
		}
		return false;
	}

	public boolean canDrain(Fluid fluid) {
		if (!this.world.isRemote) {
			return tank.getFluid() != null;
		}
		return false;
	}

	@Override
	public void recievePacket(NBTTagCompound[] tags) {
		if(tags.length != 1) {
			return;
		} else {
			tank.readFromNBT(tags[0]);
		}
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY){
			return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this);
		} else {
			return super.getCapability(capability, facing);
		}
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY){
			return true;
		} else {
			return super.hasCapability(capability, facing);
		}
	}

	// control panel

	@Override
	public Map<String, DataValue> getQueryData() {
		Map<String, DataValue> data = new HashMap<>();

		if (tank.getFluid() != null) {
			data.put("t0_fluidType", new DataValueString(tank.getFluid().getLocalizedName()));
		}
		data.put("t0_fluidAmount", new DataValueFloat(tank.getFluidAmount()));
		data.put("mode", new DataValueFloat(mode));

		return data;
	}

	@Override
	public void receiveEvent(BlockPos from, ControlEvent e) {
		if (e.name.equals("tank_set_mode")) {
			mode = (short) (e.vars.get("mode").getNumber() % modes);
			broadcastControlEvt();
		}
	}

	public void broadcastControlEvt() {
		ControlEventSystem.get(world).broadcastToSubscribed(this, ControlEvent.newEvent("tank_set_mode").setVar("mode", new DataValueFloat(mode)));
	}

	@Override
	public List<String> getInEvents() {
		return Collections.singletonList("tank_set_mode");
	}

	@Override
	public List<String> getOutEvents() {
		return Collections.singletonList("tank_set_mode");
	}

	@Override
	public void validate() {
		super.validate();
		ControlEventSystem.get(world).addControllable(this);
	}

	@Override
	public void invalidate() {
		super.invalidate();
		ControlEventSystem.get(world).removeControllable(this);
	}

	@Override
	public BlockPos getControlPos() {
		return getPos();
	}

	@Override
	public World getControlWorld() {
		return getWorld();
	}

}
