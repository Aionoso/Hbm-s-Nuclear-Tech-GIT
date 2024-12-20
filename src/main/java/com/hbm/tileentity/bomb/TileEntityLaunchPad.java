package com.hbm.tileentity.bomb;

import api.hbm.energymk2.IEnergyReceiverMK2;
import com.hbm.lib.Library;
import com.hbm.lib.ForgeDirection;
import com.hbm.items.ModItems;
import com.hbm.interfaces.IBomb;
import com.hbm.packet.AuxGaugePacket;
import com.hbm.packet.AuxElectricityPacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.packet.TEMissilePacket;
import com.hbm.tileentity.TileEntityLoadedBase;
import net.minecraftforge.fml.common.Optional;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.SimpleComponent;

@Optional.InterfaceList({@Optional.Interface(iface = "li.cil.oc.api.network.SimpleComponent", modid = "OpenComputers")})
public class TileEntityLaunchPad extends TileEntityLoadedBase implements ITickable, IEnergyReceiverMK2, SimpleComponent {

	public ItemStackHandler inventory;

	public long power;
	public final long maxPower = 100000;

	// private static final int[] slots_top = new int[] {0};
	// private static final int[] slots_bottom = new int[] { 0, 1, 2};
	// private static final int[] slots_side = new int[] {0};
	public int state = 0;

	//Time missile needs to clear launchpad in ticks
	public static final int clearingDuraction = 100;
	public int clearingTimer = 0;

	private String customName;

	public TileEntityLaunchPad() {
		inventory = new ItemStackHandler(3);
	}

	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.customName : "container.launchPad";
	}

	public boolean hasCustomInventoryName() {
		return this.customName != null && this.customName.length() > 0;
	}

	public void setCustomName(String name) {
		this.customName = name;
	}

	public boolean isUseableByPlayer(EntityPlayer player) {
		if (world.getTileEntity(pos) != this) {
			return false;
		} else {
			return player.getDistanceSq(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D) <= 64;
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		power = compound.getLong("power");
		detectPower = power + 1;
		if (compound.hasKey("inventory"))
			inventory.deserializeNBT(compound.getCompoundTag("inventory"));

		super.readFromNBT(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setLong("power", power);

		compound.setTag("inventory", inventory.serializeNBT());

		return super.writeToNBT(compound);
	}

	public long getPowerScaled(long i) {
		return (power * i) / maxPower;
	}

	@Override
	public void update() {
		
		if (!world.isRemote) {
			if(clearingTimer > 0) clearingTimer--;
			this.updateConnections();
			power = Library.chargeTEFromItems(inventory, 2, power, maxPower);
			detectAndSendChanges();
		}
	}

	private void updateConnections() {
		this.trySubscribe(world, pos.getX() + 1, pos.getY(), pos.getZ(), ForgeDirection.EAST);
		this.trySubscribe(world, pos.getX() - 1, pos.getY(), pos.getZ(), ForgeDirection.WEST);
		this.trySubscribe(world, pos.getX(), pos.getY(), pos.getZ() + 1, ForgeDirection.SOUTH);
		this.trySubscribe(world, pos.getX(), pos.getY(), pos.getZ() - 1, ForgeDirection.NORTH);
		this.trySubscribe(world, pos.getX(), pos.getY() -1, pos.getZ(), ForgeDirection.DOWN);
	}

	private ItemStack detectStack = ItemStack.EMPTY;
	private long detectPower;
	
	private void detectAndSendChanges() {
		boolean mark = false;
		if(!(detectStack.isEmpty() && inventory.getStackInSlot(0).isEmpty()) && !detectStack.isItemEqualIgnoreDurability(inventory.getStackInSlot(0))){
			mark = true;
			detectStack = inventory.getStackInSlot(0).copy();
		}
		if(detectPower != power){
			mark = true;
			detectPower = power;
		}
		PacketDispatcher.wrapper.sendToAllAround(new AuxGaugePacket(pos, clearingTimer, 0), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 20));
		PacketDispatcher.wrapper.sendToAllTracking(new TEMissilePacket(pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(0)), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 1000));
		PacketDispatcher.wrapper.sendToAllAround(new AuxElectricityPacket(pos.getX(), pos.getY(), pos.getZ(), power), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 10));
		if(mark)
			markDirty();
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return INFINITE_EXTENT_AABB;
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

	@Override
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared() {
		return 65536.0D;
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory) : super.getCapability(capability, facing);
	}

	public boolean setCoords(int x, int z){
		if(!inventory.getStackInSlot(1).isEmpty() && (inventory.getStackInSlot(1).getItem() == ModItems.designator || inventory.getStackInSlot(1).getItem() == ModItems.designator_range || inventory.getStackInSlot(1).getItem() == ModItems.designator_manual)){
			NBTTagCompound nbt;
			if(inventory.getStackInSlot(1).hasTagCompound())
				nbt = inventory.getStackInSlot(1).getTagCompound();
			else
				nbt = new NBTTagCompound();
			nbt.setInteger("xCoord", x);
			nbt.setInteger("zCoord", z);
			inventory.getStackInSlot(1).setTagCompound(nbt);
			return true;
		}
		return false;
	}

	// opencomputers interface

	@Override
	public String getComponentName() {
		return "launchpad";
	}

	@Callback(doc = "setTarget(x:int, z:int); saves coords in target designator item - returns true if it worked")
	public Object[] setTarget(Context context, Arguments args) {
		int x = args.checkInteger(0);
		int z = args.checkInteger(1);
		
		return new Object[] {setCoords(x, z)};
	}

	@Callback(doc = "launch(); tries to launch the rocket")
	public Object[] launch(Context context, Arguments args) {
		Block b = world.getBlockState(pos).getBlock();
		if(b instanceof IBomb){
			((IBomb)b).explode(world, pos);
		}
		return new Object[] {null};
	}
}
