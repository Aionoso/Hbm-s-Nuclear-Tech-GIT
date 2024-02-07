package com.hbm.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotPattern extends SlotItemHandler {

    protected boolean canHover = true;

    public SlotPattern(IItemHandler inv, int index, int x, int y) {
        super(inv, index, x, y);
    }

    @Override
    public boolean canTakeStack(EntityPlayer player) {
        return false;
    }

    @Override
    public int getSlotStackLimit() {
        return 1;
    }

    public SlotPattern disableHover() {
        this.canHover = false;
        return this;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isEnabled() {
        return canHover;
    }
}
