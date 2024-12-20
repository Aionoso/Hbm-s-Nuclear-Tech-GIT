package com.hbm.inventory.container;

import com.blamejared.ctgui.api.ContainerBase;
import com.hbm.inventory.SlotTakeOnly;
import com.hbm.inventory.SlotPattern;
import com.hbm.tileentity.machine.storage.TileEntityMassStorage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerMassStorage extends ContainerBase {
	
	private TileEntityMassStorage storage;

	public ContainerMassStorage(InventoryPlayer invPlayer, TileEntityMassStorage tedf) {

		this.storage = tedf;
		this.storage.openInventory();

		this.addSlotToContainer(new Slot(storage, 0, 61, 17));
		this.addSlotToContainer(new Slot(storage, 1, 61, 53));
		this.addSlotToContainer(new SlotTakeOnly(storage, 2, 61, 89));

		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for(int i = 0; i < 9; i++)
		{
			this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 142));
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int par2) {
		ItemStack var3 = ItemStack.EMPTY;
		Slot var4 = (Slot) this.inventorySlots.get(par2);

		if(var4 != null && var4.getHasStack()) {
			ItemStack var5 = var4.getStack();
			var3 = var5.copy();

			if (par2 <= 2) {
				if (!this.mergeItemStack(var5, 2, this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else {
				if (!this.mergeItemStack(var5, 0, 1, true))
					if (!this.mergeItemStack(var5, 2, 3, true))
						return ItemStack.EMPTY;
			}

			if (var5.isEmpty()) {
				var4.putStack(ItemStack.EMPTY);
			} else {
				var4.onSlotChanged();
			}
		}

		return var3;
	}

	@Override
	public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
		
		//L/R: 0
		//M3: 3
		//SHIFT: 1
		//DRAG: 5

		if(slotId != 1) {
			return super.slotClick(slotId, dragType, clickTypeIn, player);
		}

		Slot slot = this.getSlot(slotId);
		
		ItemStack ret = null;
		ItemStack held = player.inventory.getItemStack();
		
		if(slot.getHasStack())
			ret = slot.getStack().copy();
		
		//Don't allow for a type change when the thing isn't empty
		if(storage.getStockpile() > 0)
			return ret;

		slot.putStack(held != null ? held.copy() : null);
		
		if(slot.getHasStack()) {
			slot.getStack().setCount(1);
		}
		
		slot.onSlotChanged();
		
		return ret;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return storage.isUseableByPlayer(player);
	}
	
	@Override
	public void onContainerClosed(EntityPlayer p_75134_1_) {
		super.onContainerClosed(p_75134_1_);
		this.storage.closeInventory();
	}
}
