package com.hbm.tileentity.machine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import api.hbm.energymk2.*;
import com.hbm.blocks.machine.MachineBattery;
import com.hbm.lib.Library;
import com.hbm.lib.ForgeDirection;
import com.hbm.tileentity.TileEntityMachineBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.Optional;

import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.SimpleComponent;

@Optional.InterfaceList({@Optional.Interface(iface = "li.cil.oc.api.network.SimpleComponent", modid = "OpenComputers")})
public class TileEntityMachineBattery extends TileEntityMachineBase implements ITickable, IEnergyConductorMK2, IEnergyProviderMK2, IEnergyReceiverMK2, SimpleComponent {

	public long[] log = new long[20];
	public long delta = 0;
	public long power = 0;
	public long prevPowerState = 0;

	protected Nodespace.PowerNode node;

	//0: input only
	//1: buffer
	//2: output only
	//3: nothing
	public static final int mode_input = 0;
	public static final int mode_buffer = 1;
	public static final int mode_output = 2;
	public static final int mode_none = 3;
	public short redLow = 0;
	public short redHigh = 2;
	public ConnectionPriority priority = ConnectionPriority.NORMAL;

	public byte lastRedstone = 0;
	
	private String customName;
	
	public TileEntityMachineBattery() {
		super(4);
	}

	public static ForgeDirection[] getSendDirections(){
		return ForgeDirection.VALID_DIRECTIONS;
	}
	
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.customName : "container.battery";
	}

	public boolean hasCustomInventoryName() {
		return this.customName != null && this.customName.length() > 0;
	}
	
	public void setCustomName(String name) {
		this.customName = name;
	}
	
	@Override
	public String getName() {
		return "container.battery";
	}
	
	public boolean isUseableByPlayer(EntityPlayer player) {
		if(world.getTileEntity(pos) != this){
			return false;
		}else{
			return player.getDistanceSq(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D) <=64;
		}
	}
	
	public long getPowerRemainingScaled(long i) {
		return (power * i) / this.getMaxPower();
	}

	public byte getComparatorPower() {
		if(power == 0) return 0;
		double frac = (double) this.power / (double) this.getMaxPower() * 15D;
		return (byte) (MathHelper.clamp((int) frac + 1, 0, 15)); //to combat eventual rounding errors with the FEnSU's stupid maxPower
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setLong("power", power);
		compound.setShort("redLow", redLow);
		compound.setShort("redHigh", redHigh);
		compound.setByte("lastRedstone", lastRedstone);
		compound.setByte("priority", (byte)this.priority.ordinal());
		return super.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		this.power = compound.getLong("power");
		this.redLow = compound.getShort("redLow");
		this.redHigh = compound.getShort("redHigh");
		this.lastRedstone = compound.getByte("lastRedstone");
		this.priority = ConnectionPriority.values()[compound.getByte("priority")];
		super.readFromNBT(compound);
	}


	public void writeNBT(NBTTagCompound nbt) {
		NBTTagCompound data = new NBTTagCompound();
		data.setLong("power", power);
		data.setLong("prevPowerState", prevPowerState);
		data.setShort("redLow", redLow);
		data.setShort("redHigh", redHigh);
		data.setInteger("priority", this.priority.ordinal());
		nbt.setTag("NBT_PERSISTENT_KEY", data);
	}


	public void readNBT(NBTTagCompound nbt) {
		NBTTagCompound data = nbt.getCompoundTag("NBT_PERSISTENT_KEY");
		this.power = data.getLong("power");
		this.prevPowerState = data.getLong("prevPowerState");
		this.redLow = data.getShort("redLow");
		this.redHigh = data.getShort("redHigh");
		this.priority = ConnectionPriority.values()[data.getInteger("priority")];
	}

	@Override
	public int[] getAccessibleSlotsFromSide(EnumFacing p_94128_1_) {
        return new int[]{ 0, 1, 2, 3};
    }
	
	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		if(i == 0)
			if(stack.getItem() instanceof IBatteryItem){
				IBatteryItem batteryItem = ((IBatteryItem)stack.getItem());
				if(batteryItem.getCharge(stack) > 0 && batteryItem.getDischargeRate() > 0){
					return true;
				}
			}
		if(i == 2)
			if(stack.getItem() instanceof IBatteryItem){
				IBatteryItem batteryItem = ((IBatteryItem)stack.getItem());
				if(batteryItem.getCharge(stack) < batteryItem.getMaxCharge() && batteryItem.getChargeRate() > 0){
					return true;
				}
			}
		return false;
	}
	
	@Override
	public boolean canInsertItem(int i, ItemStack itemStack, int j) {
		return this.isItemValidForSlot(i, itemStack);
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemStack, int j) {
		return (i == 1 || i == 3);
	}

	public void tryMoveItems() {
		ItemStack itemStackDrain = inventory.getStackInSlot(0);
		if(itemStackDrain.getItem() instanceof IBatteryItem) {
			IBatteryItem itemDrain = ((IBatteryItem)itemStackDrain.getItem());
			if(itemDrain.getCharge(itemStackDrain) == 0) {
				if(inventory.getStackInSlot(1) == null || inventory.getStackInSlot(1).isEmpty()){
					inventory.setStackInSlot(1, itemStackDrain);
					inventory.setStackInSlot(0, ItemStack.EMPTY);
				}
			}
		}
		ItemStack itemStackFill = inventory.getStackInSlot(2);
		if(itemStackFill.getItem() instanceof IBatteryItem) {
			IBatteryItem itemFill = ((IBatteryItem)itemStackFill.getItem());
			if(itemFill.getCharge(itemStackFill) == itemFill.getMaxCharge()) {
				if(inventory.getStackInSlot(3) == null || inventory.getStackInSlot(3).isEmpty()){
					inventory.setStackInSlot(3, itemStackFill);
					inventory.setStackInSlot(2, ItemStack.EMPTY);
				}
			}
		}
	}
	
	@Override
	public void update() {
		if(!world.isRemote) {
			if(priority == null || priority.ordinal() == 0 || priority.ordinal() == 4) {
				priority = ConnectionPriority.LOW;
			}

			int mode = this.getRelevantMode(false);

			if(this.node == null || this.node.expired) {

				this.node = Nodespace.getNode(world, pos);

				if(this.node == null || this.node.expired) {
					this.node = this.createNode();
					Nodespace.createNode(world, this.node);
				}
			}

			long prevPower = this.power;

			power = Library.chargeItemsFromTE(inventory, 2, power, getMaxPower());

			if(mode == mode_output || mode == mode_buffer) {
				this.tryProvide(world, pos.getX(), pos.getY(), pos.getZ(), ForgeDirection.UNKNOWN);
			} else {
				if(node != null && node.hasValidNet()) node.net.removeProvider(this);
			}

			byte comp = this.getComparatorPower();
			tryMoveItems();
			if(comp != this.lastRedstone)
				this.markDirty();
			this.lastRedstone = comp;

			if(mode == mode_input || mode == mode_buffer) {
				if(node != null && node.hasValidNet()) node.net.addReceiver(this);
			} else {
				if(node != null && node.hasValidNet()) node.net.removeReceiver(this);
			}

			power = Library.chargeTEFromItems(inventory, 0, power, getMaxPower());

			long avg = (power + prevPower) / 2;
			this.delta = avg - this.log[0];

			for(int i = 1; i < this.log.length; i++) {
				this.log[i - 1] = this.log[i];
			}

			this.log[19] = avg;

			prevPowerState = power;

			this.networkPack(packNBT(), 20);
		}
	}

	public NBTTagCompound packNBT(){
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setLong("power", power);
		nbt.setLong("delta", delta);
		nbt.setShort("redLow", redLow);
		nbt.setShort("redHigh", redHigh);
		nbt.setByte("priority", (byte) this.priority.ordinal());
		return nbt;
	}

	public void onNodeDestroyedCallback() {
		this.node = null;
	}

	@Override
	public void invalidate() {
		super.invalidate();

		if(!world.isRemote) {
			if(this.node != null) {
				Nodespace.destroyNode(world, pos);
			}
		}
	}

	@Override public long getProviderSpeed() {
		int mode = this.getRelevantMode(true);
		return mode == mode_output || mode == mode_buffer ? this.getMaxPower() / 20 : 0;
	}

	@Override public long getReceiverSpeed() {
		int mode = this.getRelevantMode(true);
		return mode == mode_input || mode == mode_buffer ? this.getMaxPower() / 20 : 0;
	}

	@Override
	public void networkUnpack(NBTTagCompound nbt) {
		super.networkUnpack(nbt);

		this.power = nbt.getLong("power");
		this.delta = nbt.getLong("delta");
		this.redLow = nbt.getShort("redLow");
		this.redHigh = nbt.getShort("redHigh");
		this.priority = ConnectionPriority.values()[nbt.getByte("priority")];
	}

	@Override
	public long getPower() {
		return power;
	}

	private short modeCache = 0;
	public short getRelevantMode(boolean useCache) {
		if(useCache) return this.modeCache;
		boolean isPowered = world.isBlockPowered(this.pos) || world.isBlockIndirectlyGettingPowered(this.pos) > 0;
		this.modeCache = isPowered ? this.redHigh : this.redLow;
		return this.modeCache;
	}

	private long bufferedMax;

	@Override
	public long getMaxPower() {

		if(bufferedMax == 0) {
			bufferedMax = ((MachineBattery)world.getBlockState(pos).getBlock()).getMaxPower();
		}

		return bufferedMax;
	}

	@Override public boolean canConnect(ForgeDirection dir) { return true; }
	@Override public void setPower(long power) { this.power = power; }
	@Override public ConnectionPriority getPriority() { return this.priority; }


	// opencomputers interface

	@Override
	public String getComponentName() {
		return "battery";
	}

	@Callback(doc = "getPower(); returns the current power level - long")
	public Object[] getPower(Context context, Arguments args) {
		return new Object[] {power};
	}

	@Callback(doc = "getMaxPower(); returns the maximum power level - long")
	public Object[] getMaxPower(Context context, Arguments args) {
		return new Object[] {getMaxPower()};
	}

	@Callback(doc = "getChargePercent(); returns the charge in percent - double")
	public Object[] getChargePercent(Context context, Arguments args) {
		return new Object[] {100D * getPower()/(double)getMaxPower()};
	}

	@Callback(doc = "getPowerDelta(); returns the in/out power flow - long")
	public Object[] getPowerDelta(Context context, Arguments args) {
		return new Object[] {delta};
	}

	@Callback(doc = "getPriority(); returns the priority (1:low, 2:normal, 3:high) - int")
	public Object[] getPriority(Context context, Arguments args) {
		return new Object[] {1+getPriority().ordinal()};
	}

	@Callback(doc = "setPriority(int prio); sets the priority (1:low, 2:normal, 3:high)")
	public Object[] setPriority(Context context, Arguments args) {
		int prio = args.checkInteger(0);
		if(prio == 1) priority = ConnectionPriority.LOW;
		if(prio == 2) priority = ConnectionPriority.NORMAL;
		if(prio == 3) priority = ConnectionPriority.HIGH;
		return new Object[] {null};
	}
}
