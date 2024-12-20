package com.hbm.inventory.container;

import com.hbm.inventory.SlotMachineOutput;
import com.hbm.tileentity.machine.TileEntityAshpit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerAshpit extends Container {

    protected TileEntityAshpit ashpit;

    public ContainerAshpit(InventoryPlayer invPlayer, TileEntityAshpit ashpit) {
        this.ashpit = ashpit;

        for(int i = 0; i < 5; i++) this.addSlotToContainer(new SlotMachineOutput(ashpit.inventory, i, 44 + i * 18, 27));

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 9; j++) {
                this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 86 + i * 18));
            }
        }

        for(int i = 0; i < 9; i++) {
            this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 144));
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = (Slot) this.inventorySlots.get(index);

        if(slot != null && slot.getHasStack()) {
            ItemStack originalStack = slot.getStack();
            stack = originalStack.copy();

            if(index <= 4) {
                if(!this.mergeItemStack(originalStack, 5, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(originalStack, stack);

            } else {
                return ItemStack.EMPTY;
            }

            if(originalStack.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }

        return stack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return ashpit.isUseableByPlayer(player);
    }
}
