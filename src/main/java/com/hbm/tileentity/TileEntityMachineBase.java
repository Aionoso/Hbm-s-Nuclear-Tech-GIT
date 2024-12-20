package com.hbm.tileentity;

import com.hbm.blocks.ModBlocks;
import com.hbm.interfaces.Spaghetti;
import com.hbm.lib.ItemStackHandlerWrapper;
import com.hbm.packet.BufPacket;
import com.hbm.packet.NBTPacket;
import com.hbm.packet.PacketDispatcher;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

@Spaghetti("Not spaghetti in itself, but for the love of god please use this base class for all machines")
public abstract class TileEntityMachineBase extends TileEntityLoadedBase implements INBTPacketReceiver, IBufPacketReceiver {

	public ItemStackHandler inventory;

	private String customName;

	public TileEntityMachineBase(int scount) {
		this(scount, 64);
	}

	public TileEntityMachineBase(int scount, int slotlimit) {
		inventory = getNewInventory(scount, slotlimit);
	}

	public ItemStackHandler getNewInventory(int scount, int slotlimit){
		return new ItemStackHandler(scount){
			@Override
			protected void onContentsChanged(int slot) {
				super.onContentsChanged(slot);
				markDirty();
			}
			
			@Override
			public int getSlotLimit(int slot) {
				return slotlimit;
			}
		};
	}

	// This is for cases like barrels - in 2.0.3 there are 6 slots instead of 4
	public void resizeInventory(int newSlotCount) {
		ItemStackHandler newInventory = getNewInventory(newSlotCount, inventory.getSlotLimit(0));
		for (int i = 0; i < Math.min(inventory.getSlots(), newSlotCount); i++) {
			newInventory.setStackInSlot(i, inventory.getStackInSlot(i));
		}
		this.inventory = newInventory;
		markDirty();
	}
	
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.customName : getName();
	}

	public abstract String getName();

	public boolean hasCustomInventoryName() {
		return this.customName != null && this.customName.length() > 0;
	}
	
	public void setCustomName(String name) {
		this.customName = name;
	}
	
	public boolean isUseableByPlayer(EntityPlayer player) {
		if(world.getTileEntity(pos) != this)
		{
			return false;
		}else{
			return player.getDistanceSq(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D) <=128;
		}
	}
	
	public int[] getAccessibleSlotsFromSide(EnumFacing e) {
		return new int[] {};
	}
	
	public int getGaugeScaled(int i, FluidTank tank) {
		return tank.getFluidAmount() * i / tank.getCapacity();
	}
	
	public void networkPack(NBTTagCompound nbt, int range) {
		nbt.setBoolean("muffled", muffled);
		if(!world.isRemote)
			PacketDispatcher.wrapper.sendToAllAround(new NBTPacket(nbt, pos), new TargetPoint(this.world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), range));
	}
	
	public void networkUnpack(NBTTagCompound nbt) {this.muffled = nbt.getBoolean("muffled");}

	/** Sends a sync packet that uses ByteBuf for efficient information-cramming */
	public void networkPackNT(int range) {
		if(!world.isRemote) PacketDispatcher.wrapper.sendToAllAround(new BufPacket(pos.getX(), pos.getY(), pos.getZ(), this), new TargetPoint(this.world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), range));
	}

	@Override public void serialize(ByteBuf buf) {
		buf.writeBoolean(muffled);
	}

	@Override public void deserialize(ByteBuf buf) {
		this.muffled = buf.readBoolean();
	}
	
	public void handleButtonPacket(int value, int meta) { }
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setTag("inventory", inventory.serializeNBT());
		return super.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		if(compound.hasKey("inventory"))
			inventory.deserializeNBT(compound.getCompoundTag("inventory"));
		super.readFromNBT(compound);
	}
	
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		return true;
	}
	
	public boolean canInsertItem(int slot, ItemStack itemStack, int amount) {
		return this.isItemValidForSlot(slot, itemStack);
	}

	public boolean canExtractItem(int slot, ItemStack itemStack, int amount) {
		return true;
	}
	
	public int countMufflers() {

		int count = 0;

		for(EnumFacing dir : EnumFacing.VALUES)
			if(world.getBlockState(pos.offset(dir)).getBlock() == ModBlocks.muffler)
				count++;

		return count;
	}

	public float getVolume(int toSilence) {

		float volume = 1 - (countMufflers() / (float)toSilence);

		return Math.max(volume, 0);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && inventory != null){
			if(facing == null)
				return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory);
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(new ItemStackHandlerWrapper(inventory, getAccessibleSlotsFromSide(facing)){
				@Override
				public ItemStack extractItem(int slot, int amount, boolean simulate) {
					if(canExtractItem(slot, inventory.getStackInSlot(slot), amount))
						return super.extractItem(slot, amount, simulate);
					return ItemStack.EMPTY;
				}
				
				@Override
				public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
					if(canInsertItem(slot, stack, stack.getCount()))
						return super.insertItem(slot, stack, simulate);
					return stack;
				}
			});
		}
		return super.getCapability(capability, facing);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && inventory != null) || super.hasCapability(capability, facing);
	}
}
