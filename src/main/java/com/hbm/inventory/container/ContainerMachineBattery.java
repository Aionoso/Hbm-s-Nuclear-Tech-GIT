package com.hbm.inventory.container;

import com.hbm.inventory.SlotMachineOutput;
import com.hbm.tileentity.machine.TileEntityMachineFENSU;
import com.hbm.tileentity.machine.TileEntityMachineBattery;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerMachineBattery extends Container {

	public TileEntityMachineBattery diFurnace;
	
	public ContainerMachineBattery(InventoryPlayer invPlayer, TileEntityMachineBattery tedf) {
		
		diFurnace = tedf;
		
		this.addSlotToContainer(new SlotItemHandler(tedf.inventory, 0, 53 - 18, 17));
		this.addSlotToContainer(new SlotMachineOutput(tedf.inventory, 1, 53 - 18, 53));
		this.addSlotToContainer(new SlotItemHandler(tedf.inventory, 2, 125, 17));
		this.addSlotToContainer(new SlotMachineOutput(tedf.inventory, 3, 125, 53));
		
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
	
	//Drillgon200: I have no idea how this method works.
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int par2) {
		ItemStack var3 = ItemStack.EMPTY;
		Slot var4 = (Slot) this.inventorySlots.get(par2);
		
		if (var4 != null && var4.getHasStack())
		{
			ItemStack var5 = var4.getStack();
			var3 = var5.copy();
			
            if (par2 <= 1) {
				if (!this.mergeItemStack(var5, 2, this.inventorySlots.size(), true))
				{
					return ItemStack.EMPTY;
				}
			}
			else if (!this.mergeItemStack(var5, 0, 1, false))
				if (!this.mergeItemStack(var5, 1, 2, false))
					return ItemStack.EMPTY;
			
			if (var5.getCount() == 0)
			{
				var4.putStack(ItemStack.EMPTY);
			}
			else
			{
				var4.onSlotChanged();
			}
		}
		
		return var3;
	}
	
	@Override
	public void detectAndSendChanges() {
		NBTTagCompound nbt = new NBTTagCompound();

		nbt.setLong("power", diFurnace.power);
		nbt.setLong("powerDelta", diFurnace.delta);
		nbt.setShort("redLow", diFurnace.redLow);
		nbt.setShort("redHigh", diFurnace.redHigh);
		nbt.setByte("priority", (byte)diFurnace.priority.ordinal());
		if(diFurnace instanceof TileEntityMachineFENSU)
			nbt.setByte("color", (byte) ((TileEntityMachineFENSU)diFurnace).color.getMetadata());
		
		diFurnace.networkPack(nbt, 10);
		super.detectAndSendChanges();
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return diFurnace.isUseableByPlayer(playerIn);
	}
}
