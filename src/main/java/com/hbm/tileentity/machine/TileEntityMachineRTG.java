package com.hbm.tileentity.machine;

import api.hbm.energymk2.IEnergyProviderMK2;
import com.hbm.lib.ForgeDirection;
import com.hbm.packet.AuxElectricityPacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.items.machine.ItemRTGPellet;
import com.hbm.tileentity.TileEntityLoadedBase;
import com.hbm.util.RTGUtil;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityMachineRTG extends TileEntityLoadedBase implements ITickable, IEnergyProviderMK2 {

	public ItemStackHandler inventory;
	
	public int heat;
	public final int heatMax = 6000;
	public long power;
	public final long maxPower = 1000000;
	
	//private static final int[] slots_top = new int[] { 0 };
	//private static final int[] slots_bottom = new int[] { 0 };
	//private static final int[] slots_side = new int[] { 0 };
	
	private String customName;	
	
	public TileEntityMachineRTG() {
		inventory = new ItemStackHandler(15){
			@Override
			protected void onContentsChanged(int slot) {
				markDirty();
				super.onContentsChanged(slot);
			}
			
			@Override
			public boolean isItemValid(int slot, ItemStack itemStack) {
				if(itemStack != null && (itemStack.getItem() instanceof ItemRTGPellet))
					return true;
				return false;
			}
			@Override
			public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
				if(isItemValid(slot, stack))
					return super.insertItem(slot, stack, simulate);
				return stack;
			}

			public boolean canExtractItem(int slot, ItemStack itemStack, int amount) {
				return !isItemValid(slot, itemStack);
			}

			@Override
			public ItemStack extractItem(int slot, int amount, boolean simulate) {
				if(canExtractItem(slot, inventory.getStackInSlot(slot), amount))
					return super.extractItem(slot, amount, simulate);
				return ItemStack.EMPTY;
			}
		};
	}
	
	@Override
	public void update() {
		if(!world.isRemote)
		{
			for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
				this.tryProvide(world, pos.getX() + dir.offsetX, pos.getY() + dir.offsetY, pos.getZ() + dir.offsetZ, dir);
			int[] slots = new int[inventory.getSlots()];
			for(int i = 0; i < inventory.getSlots();i++){
				slots[i] = i;
			}
			heat = RTGUtil.updateRTGs(inventory, slots);
			
			if(heat > heatMax)
				heat = heatMax;
			
			power += heat*5;
			if(power > maxPower)
				power = maxPower;
			
			
			detectAndSendChanges();
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		heat = compound.getInteger("heat");
		detectHeat = heat + 1;
		power = compound.getLong("power");
		detectPower = power + 1;
		if(compound.hasKey("inventory"))
			inventory.deserializeNBT(compound.getCompoundTag("inventory"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setInteger("heat", this.heat);
		compound.setLong("power", this.power);
		compound.setTag("inventory", inventory.serializeNBT());
		
		return super.writeToNBT(compound);
	}
	
	public long getPowerScaled(long i) {
		return (power * i) / maxPower;
	}
	
	public int getHeatScaled(int i) {
		return (heat * i) / heatMax;
	}
	
	public boolean hasPower() {
		return power > 0;
	}
	
	public boolean hasHeat() {
		return heat > 0;
	}

	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.customName : "container.rtg";
	}

	public boolean hasCustomInventoryName() {
		return this.customName != null && this.customName.length() > 0;
	}
	
	public void setCustomName(String name) {
		this.customName = name;
	}
	
	public boolean isUseableByPlayer(EntityPlayer player) {
		if(world.getTileEntity(pos) != this)
		{
			return false;
		}else{
			return player.getDistanceSq(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D) <=64;
		}
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory) : 
			super.getCapability(capability, facing);
	}
	
	private int detectHeat;
	private long detectPower;
	
	private void detectAndSendChanges() {
		
		boolean mark = false;
		if(detectHeat != heat){
			mark = true;
			detectHeat = heat;
		}
		if(detectPower != power){
			mark = true;
			detectPower = power;
		}
		PacketDispatcher.wrapper.sendToAllAround(new AuxElectricityPacket(pos.getX(), pos.getY(), pos.getZ(), power), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 10));
		if(mark)
			markDirty();
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
