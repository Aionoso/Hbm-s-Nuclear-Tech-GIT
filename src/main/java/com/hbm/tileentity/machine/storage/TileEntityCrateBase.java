package com.hbm.tileentity.machine.storage;

import com.hbm.lib.HBMSoundHandler;
import com.hbm.tileentity.IGUIProvider;
import com.hbm.tileentity.machine.TileEntityLockableBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.SoundCategory;

public abstract class TileEntityCrateBase extends TileEntityLockableBase implements ISidedInventory, IGUIProvider {

	protected ItemStack slots[];
	public String customName;

	public TileEntityCrateBase(int count) {
		slots = new ItemStack[count];
	}

	@Override
	public int getSizeInventory() {
		return slots.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return slots[i];
	}


	public ItemStack getStackInSlotOnClosing(int i) {
		if (slots[i] != null) {
			ItemStack itemStack = slots[i];
			slots[i] = null;
			return itemStack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemStack) {
		slots[i] = itemStack;
		if (itemStack != null && itemStack.getCount() > getInventoryStackLimit()) {
			itemStack.setCount(getInventoryStackLimit());
		}
	}


	public boolean hasCustomInventoryName() {
		return this.customName != null && this.customName.length() > 0;
	}

	public void setCustomName(String name) {
		this.customName = name;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	public boolean isUseableByPlayer(EntityPlayer player) {
		if (world.getTileEntity(pos) != this) {
			return false;
		} else {
			return player.getDistanceSq(pos.getX(), pos.getY(), pos.getZ()) <= 64;
		}
	}


	public void openInventory() {
		this.world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), HBMSoundHandler.crateOpen, SoundCategory.BLOCKS, 1.0F, 1.0F);
	}


	public void closeInventory() {
		this.world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), HBMSoundHandler.crateClose, SoundCategory.BLOCKS, 1.0F, 1.0F);
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		return true;
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (slots[i] != null) {
			if (slots[i].getCount() <= j) {
				ItemStack itemStack = slots[i];
				slots[i] = null;
				return itemStack;
			}
			ItemStack itemStack1 = slots[i].splitStack(j);
			if (slots[i].getCount() == 0) {
				slots[i] = null;
			}

			return itemStack1;
		} else {
			return null;
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		lock = compound.getInteger("lock");
		isLocked = compound.getBoolean("isLocked");
		lockMod = compound.getDouble("lockMod");
		super.readFromNBT(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setInteger("lock", lock);
		compound.setBoolean("isLocked", isLocked);
		compound.setDouble("lockMod", lockMod);
		return super.writeToNBT(compound);
	}


	public int[] getAccessibleSlotsFromSide(int side) {
		int[] slots = new int[this.slots.length];
		for(int i = 0; i < slots.length; i++) slots[i] = i;
		return slots;
	}


	public boolean canInsertItem(int i, ItemStack itemStack, int j) {
		return this.isItemValidForSlot(i, itemStack) && !this.isLocked();
	}


	public boolean canExtractItem(int i, ItemStack itemStack, int j) {
		return !this.isLocked();
	}
}
