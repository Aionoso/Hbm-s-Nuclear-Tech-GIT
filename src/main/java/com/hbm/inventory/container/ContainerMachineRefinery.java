package com.hbm.inventory.container;

import com.hbm.inventory.SlotMachineOutput;
import com.hbm.tileentity.machine.oil.TileEntityMachineRefinery;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerMachineRefinery extends Container {

private TileEntityMachineRefinery testNuke;
	
	public ContainerMachineRefinery(InventoryPlayer invPlayer, TileEntityMachineRefinery tedf) {
		
		testNuke = tedf;
		
		//Battery
		this.addSlotToContainer(new SlotItemHandler(tedf.inventory, 0, 8, 90));
		//Canister Input
		this.addSlotToContainer(new SlotItemHandler(tedf.inventory, 1, 35, 90));
		//Canister Output
		this.addSlotToContainer(new SlotMachineOutput(tedf.inventory, 2, 35, 108));
		//Heavy Oil Input
		this.addSlotToContainer(new SlotItemHandler(tedf.inventory, 3, 80, 90));
		//Heavy Oil Output
		this.addSlotToContainer(new SlotMachineOutput(tedf.inventory, 4, 80, 108));
		//Nahptha Input
		this.addSlotToContainer(new SlotItemHandler(tedf.inventory, 5, 98, 90));
		//Nahptha Output
		this.addSlotToContainer(new SlotMachineOutput(tedf.inventory, 6, 98, 108));
		//Light Oil Input
		this.addSlotToContainer(new SlotItemHandler(tedf.inventory, 7, 116, 90));
		//Light Oil Output
		this.addSlotToContainer(new SlotMachineOutput(tedf.inventory, 8, 116, 108));
		//Petroleum Input
		this.addSlotToContainer(new SlotItemHandler(tedf.inventory, 9, 134, 90));
		//Petroleum Output
		this.addSlotToContainer(new SlotMachineOutput(tedf.inventory, 10, 134, 108));
		//Sulfur Output
		this.addSlotToContainer(new SlotMachineOutput(tedf.inventory, 11, 152, 36));
		//Fluid ID
		this.addSlotToContainer(new SlotItemHandler(tedf.inventory, 12, 186, 106));
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18 + 56));
			}
		}
		
		for(int i = 0; i < 9; i++)
		{
			this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 142 + 56));
		}
	}
	
	@Override
    public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int par2)
    {
		ItemStack var3 = ItemStack.EMPTY;
		Slot var4 = (Slot) this.inventorySlots.get(par2);
		
		if (var4 != null && var4.getHasStack())
		{
			ItemStack var5 = var4.getStack();
			var3 = var5.copy();
			
            if (par2 <= 11) {
				if (!this.mergeItemStack(var5, 12, this.inventorySlots.size(), true))
				{
					return ItemStack.EMPTY;
				}
			}
			else if (!this.mergeItemStack(var5, 0, 1, false))
				if (!this.mergeItemStack(var5, 1, 2, false))
					if (!this.mergeItemStack(var5, 3, 4, false))
						if (!this.mergeItemStack(var5, 5, 6, false))
							if (!this.mergeItemStack(var5, 7, 8, false))
								if (!this.mergeItemStack(var5, 9, 10, false)) {
					return ItemStack.EMPTY;
			}
			
			if (var5.isEmpty())
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
	public boolean canInteractWith(EntityPlayer player) {
		return testNuke.isUseableByPlayer(player);
	}
}
