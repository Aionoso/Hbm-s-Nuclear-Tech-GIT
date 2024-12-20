package com.hbm.inventory.container;

import com.hbm.forgefluid.FFUtils;
import com.hbm.packet.AuxLongPacket;
import com.hbm.packet.FluidTankPacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.tileentity.machine.TileEntityCoreReceiver;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidTank;

public class ContainerCoreReceiver extends Container {

	private TileEntityCoreReceiver te;
	private EntityPlayerMP player;

	public ContainerCoreReceiver(EntityPlayer player, TileEntityCoreReceiver te) {
		InventoryPlayer invPlayer = player.inventory;
		if(player instanceof EntityPlayerMP)
			this.player = (EntityPlayerMP) player;
		this.te = te;

		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for(int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 142));
		}
	}

	@Override
	public void addListener(IContainerListener listener) {
		super.addListener(listener);
		PacketDispatcher.sendTo(new AuxLongPacket(te.getPos(), te.syncJoules, 0), player);
		PacketDispatcher.sendTo(new FluidTankPacket(te.getPos(), new FluidTank[] { tank }), player);
	}

	int joules;
	FluidTank tank;

	@Override
	public void detectAndSendChanges() {
		if(joules != te.syncJoules) {
			joules = (int) te.syncJoules;
			PacketDispatcher.sendTo(new AuxLongPacket(te.getPos(), te.syncJoules, 0), player);
		}
		super.detectAndSendChanges();
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return te.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int par2) {
		ItemStack var3 = ItemStack.EMPTY;
		Slot var4 = (Slot) this.inventorySlots.get(par2);

		if(var4 != null && var4.getHasStack()) {
			return ItemStack.EMPTY;
		}

		return var3;
	}
}
