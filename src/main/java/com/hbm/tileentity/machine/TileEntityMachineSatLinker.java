package com.hbm.tileentity.machine;

import com.hbm.items.ISatChip;
import com.hbm.items.machine.ItemSatChip;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityMachineSatLinker extends TileEntity implements ITickable {

	public ItemStackHandler inventory;
	
	//public static final int maxFill = 64 * 3;

	//private static final int[] slots_top = new int[] {0};
	//private static final int[] slots_bottom = new int[] {1};
	//private static final int[] slots_side = new int[] {2};
	
	private String customName;
	
	public TileEntityMachineSatLinker() {
		inventory = new ItemStackHandler(3){
			@Override
			protected void onContentsChanged(int slot) {
				super.onContentsChanged(slot);
				markDirty();
			}
		};
	}
	
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.customName : "container.satLinker";
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
	public void readFromNBT(NBTTagCompound compound) {
		if(compound.hasKey("inventory"))
			inventory.deserializeNBT(compound.getCompoundTag("inventory"));
		super.readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setTag("inventory", inventory.serializeNBT());
		return super.writeToNBT(compound);
	}
	
	@Override
	public void update() {
		if(!world.isRemote)
		{
			if(inventory.getStackInSlot(0).getItem() instanceof ItemSatChip && inventory.getStackInSlot(1).getItem() instanceof ItemSatChip) {
				ItemSatChip.setFreq(inventory.getStackInSlot(1), ItemSatChip.getFreq(inventory.getStackInSlot(0)));
			}
			if(inventory.getStackInSlot(0).getItem() instanceof ItemSatChip && inventory.getStackInSlot(1).getItem() instanceof ISatChip){
				ISatChip.setFreqS(inventory.getStackInSlot(1), ItemSatChip.getFreq(inventory.getStackInSlot(0)));
			}
			if(inventory.getStackInSlot(0).getItem() instanceof ISatChip && inventory.getStackInSlot(1).getItem() instanceof ItemSatChip){
				ItemSatChip.setFreq(inventory.getStackInSlot(1), ItemSatChip.getFreq(inventory.getStackInSlot(0)));
			}
			if(inventory.getStackInSlot(2).getItem() instanceof ItemSatChip) {
				ItemSatChip.setFreq(inventory.getStackInSlot(2), world.rand.nextInt(100000));
			}
			if(inventory.getStackInSlot(2).getItem() instanceof ISatChip){
				ISatChip.setFreqS(inventory.getStackInSlot(2), world.rand.nextInt(100000));
			}
		}
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory) : super.getCapability(capability, facing);
	}
}
