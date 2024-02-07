package com.hbm.inventory.control_panel;

import java.util.ArrayList;
import java.util.List;

import com.hbm.tileentity.machine.TileEntityControlPanel;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerControlEdit extends Container {

	public TileEntityControlPanel control;
	public Slot input;
	public List<SlotDisableable> invSlots = new ArrayList<>();
	
	public ContainerControlEdit(InventoryPlayer invPlayer, TileEntityControlPanel te) {
		control = te;
		input = this.addSlotToContainer(new SlotItemHandlerDisableable(te.inventory, 0, 5, 51));
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				invSlots.add((SlotDisableable)this.addSlotToContainer(new SlotDisableable(invPlayer, j + i * 9 + 9, 48 + j * 18, 152 + i * 18)));
			}
		}
		
		for(int i = 0; i < 9; i++)
		{
			invSlots.add((SlotDisableable)this.addSlotToContainer(new SlotDisableable(invPlayer, i, 48 + i * 18, 210)));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index){
		ItemStack var3 = ItemStack.EMPTY;
		Slot var4 = this.inventorySlots.get(index);
		
		if (var4 != null && var4.getHasStack())
		{
			ItemStack var5 = var4.getStack();
			var3 = var5.copy();
			
            if (index == 0) {
				if (!this.mergeItemStack(var5, 1, this.inventorySlots.size(), true))
				{
					return ItemStack.EMPTY;
				}
			}
			else if (!this.mergeItemStack(var5, 0, 1, false))
			{
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
	public void onContainerClosed(EntityPlayer playerIn){
		for(int i = 0; i < control.inventory.getSlots(); i ++){
			ItemStack stack = control.inventory.getStackInSlot(i);
			if(!playerIn.addItemStackToInventory(stack))
				playerIn.dropItem(stack, false);
			control.inventory.setStackInSlot(i, ItemStack.EMPTY);
		}
		super.onContainerClosed(playerIn);
	}
	
	public static class SlotItemHandlerDisableable extends SlotItemHandler {
		public boolean isEnabled = false;
		public SlotItemHandlerDisableable(IItemHandler itemHandler, int index, int xPosition, int yPosition){
			super(itemHandler, index, xPosition, yPosition);
		}
		
		@Override
		@SideOnly(Side.CLIENT)
		public boolean isEnabled(){
			return isEnabled;
		}
		
		@Override
		public boolean canTakeStack(EntityPlayer playerIn){
			return super.canTakeStack(playerIn);
		}
		
	}
	
	public static class SlotDisableable extends Slot {
		public boolean isEnabled = false;
		public SlotDisableable(IInventory inventoryIn, int index, int xPosition, int yPosition){
			super(inventoryIn, index, xPosition, yPosition);
		}
		@Override
		@SideOnly(Side.CLIENT)
		public boolean isEnabled(){
			return isEnabled;
		}
		@Override
		public boolean canTakeStack(EntityPlayer playerIn){
			return super.canTakeStack(playerIn);
		}
	}

}
