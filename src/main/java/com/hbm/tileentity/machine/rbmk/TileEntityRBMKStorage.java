package com.hbm.tileentity.machine.rbmk;

import com.hbm.items.machine.ItemRBMKRod;
import com.hbm.tileentity.machine.rbmk.TileEntityRBMKConsole.ColumnType;

import net.minecraft.util.EnumFacing;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileEntityRBMKStorage extends TileEntityRBMKSlottedBase implements IRBMKLoadable {


	public TileEntityRBMKStorage() {
		super(12);
	}

	@Override
	public String getName() {
		return "container.rbmkStorage";
	}
	
	@Override
	public void update() {
		if(!world.isRemote) {
			int freeSlot = 0;
			for(int i = 0; i < 12; i++){
				if(inventory.getStackInSlot(i).isEmpty()){
					continue;
				}else{
					if(inventory.getStackInSlot(freeSlot).isEmpty()){
						moveItem(i, freeSlot);
					}
					freeSlot++;
				}
			}
		}
		super.update();
	}

	public void moveItem(int fromSlot, int toSlot){
		inventory.setStackInSlot(toSlot, inventory.getStackInSlot(fromSlot).copy());
		inventory.setStackInSlot(fromSlot, ItemStack.EMPTY);
	}

	@Override
	public ColumnType getConsoleType() {
		return ColumnType.STORAGE;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemStack) {
		return true;
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemStack, int j) {
		return true;
	}

	@Override
	public boolean canLoad(ItemStack toLoad) {
		return toLoad != null && inventory.getStackInSlot(11).isEmpty();
	}

	@Override
	public void load(ItemStack toLoad) {
		inventory.setStackInSlot(11, toLoad.copy());
		this.markDirty();
	}

	@Override
	public boolean canUnload() {
		return !inventory.getStackInSlot(0).isEmpty();
	}

	@Override
	public ItemStack provideNext() {
		return inventory.getStackInSlot(0);
	}

	@Override
	public void unload() {
		inventory.setStackInSlot(0, ItemStack.EMPTY);
		this.markDirty();
	}
}
