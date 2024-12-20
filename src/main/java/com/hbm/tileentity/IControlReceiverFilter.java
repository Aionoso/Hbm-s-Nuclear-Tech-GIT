package com.hbm.tileentity;

import com.hbm.interfaces.IControlReceiver;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import static com.hbm.render.tileentity.RenderPress.pos;

public interface IControlReceiverFilter extends IControlReceiver {
	
	void nextMode(int i);

	@Override
	public default void receiveControl(NBTTagCompound data) {
		if(data.hasKey("slot")) {
			setFilterContents(data);
		}
	}

	/**
	 * Expects the implementor to be a tile entity and an IInventory
	 * @param nbt
	 */
	public default void setFilterContents(NBTTagCompound nbt) {
		TileEntity tile = (TileEntity) this;
		IInventory inv = (IInventory) this;
		int slot = nbt.getInteger("slot");
		inv.setInventorySlotContents(slot, new ItemStack(Item.getItemById(nbt.getInteger("id")), 1, nbt.getInteger("meta")));
		nextMode(slot);
		tile.getWorld().markChunkDirty(tile.getPos(), tile);
	}
}
