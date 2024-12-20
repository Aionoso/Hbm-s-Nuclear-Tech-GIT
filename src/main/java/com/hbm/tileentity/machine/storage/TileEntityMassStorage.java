package com.hbm.tileentity.machine.storage;

import com.hbm.inventory.container.ContainerMassStorage;
import com.hbm.inventory.gui.GUIMassStorage;
import com.hbm.items.ModItems;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.render.amlfrom1710.Vec3;
import com.hbm.tileentity.IControlReceiverFilter;
import com.hbm.tileentity.INBTPacketReceiver;

import net.minecraft.inventory.ISidedInventory;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;


public class TileEntityMassStorage extends TileEntityCrateBase implements ITickable, INBTPacketReceiver, IControlReceiverFilter {
	
	private int stack = 0;
	public boolean output = false;
	private int capacity;
	public int redstone = 0;
	
	@SideOnly(Side.CLIENT) public ItemStack type;
	
	public TileEntityMassStorage() {
		super(3);
	}
	
	public TileEntityMassStorage(int capacity) {
		this();
		this.capacity = capacity;
	}

	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.customName : "container.massStorage";
	}


	@Override
	public void update() {
		
		if(!world.isRemote) {
			
			int newRed = this.getStockpile() * 15 / this.capacity;
			
			if(newRed != this.redstone) {
				this.redstone = newRed;
				this.markDirty();
			}
			
			if(slots[0] != null && slots[0].getItem() == ModItems.fluid_barrel_infinite) {
				this.stack = this.getCapacity();
			}
			
			if(this.getType() == null)
				this.stack = 0;
			
			if(getType() != null && getStockpile() < getCapacity() && slots[0] != null && slots[0].isItemEqual(getType()) && ItemStack.areItemStackTagsEqual(slots[0], getType())) {
				
				int remaining = getCapacity() - getStockpile();
				int toRemove = Math.min(remaining, slots[0].getCount());
				this.decrStackSize(0, toRemove);
				this.stack += toRemove;
				this.world.markChunkDirty(pos, this);
			}
			
			if(output && getType() != null) {
				
				if(slots[2] != null && !(slots[2].isItemEqual(getType()) && ItemStack.areItemStackTagsEqual(slots[2], getType()))) {
					return;
				}
				
				int amount = Math.min(getStockpile(), getType().getMaxStackSize());
				
				if(amount > 0) {
					if(slots[2] == null) {
						slots[2] = slots[1].copy();
						slots[2].setCount(amount);
						this.stack -= amount;
					} else {
						amount = Math.min(amount, slots[2].getMaxStackSize() - slots[2].getCount());
						slots[2].setCount(amount);
						this.stack -= amount;
					}
				}
			}
			
			NBTTagCompound data = new NBTTagCompound();
			data.setInteger("stack", getStockpile());
			data.setBoolean("output", output);
			if(slots[1] != null) slots[1].writeToNBT(data);
			INBTPacketReceiver.networkPack(this, data, 15);
		}
	}

	@Override
	public void networkUnpack(NBTTagCompound nbt) {
		this.stack = nbt.getInteger("stack");
		this.output = nbt.getBoolean("output");
		this.type = new ItemStack(nbt);
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public ItemStack getType() {
		return slots[1] == null ? null : slots[1].copy();
	}
	
	public int getStockpile() {
		return stack;
	}
	
	public void setStockpile(int stack) {
		this.stack = stack;
	}

	@Override
	public boolean hasPermission(EntityPlayer player) {
		return Vec3.createVectorHelper(pos.getX(), pos.getY(), pos.getZ()).lengthVector() < 20;
	}

	@Override
	public void openInventory() {
		this.world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), HBMSoundHandler.storageOpen, SoundCategory.BLOCKS, 1.0F, 1.0F);
	}

	@Override
	public void closeInventory() {
		this.world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), HBMSoundHandler.storageClose, SoundCategory.BLOCKS, 1.0F, 1.0F);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.stack = nbt.getInteger("stack");
		this.output = nbt.getBoolean("output");
		this.capacity = nbt.getInteger("capacity");
		this.redstone = nbt.getByte("redstone");

		if(this.capacity <= 0) {
			this.capacity = 10_000;
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("stack", stack);
		nbt.setBoolean("output", output);
		nbt.setInteger("capacity", capacity);
		nbt.setByte("redstone", (byte) redstone);
		return super.writeToNBT(nbt);
	}

	@Override
	public void nextMode(int i) {
	}

	@Override
	public void receiveControl(NBTTagCompound data) {
		
		if(data.hasKey("provide") && slots[1] != null) {
			
			if(this.getStockpile() == 0) {
				return;
			}
			
			int amount = data.getBoolean("provide") ? slots[1].getMaxStackSize() : 1;
			amount = Math.min(amount, getStockpile());
			
			if(slots[2] != null && !(slots[2].isItemEqual(getType()) && ItemStack.areItemStackTagsEqual(slots[2], getType()))) {
				return;
			}
			
			if(slots[2] == null) {
				slots[2] = slots[1].copy();
				slots[2].setCount(amount);
				this.stack -= amount;
			} else {
				amount = Math.min(amount, slots[2].getMaxStackSize() - slots[2].getCount());
				slots[2].setCount(amount);
				this.stack -= amount;
			}
		}
		
		if(data.hasKey("toggle")) {
			this.output = !output;
		}
		if(data.hasKey("slot")){
			setFilterContents(data);
		}
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return new int[0];
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemStackIn, EnumFacing direction) {
		return !this.isLocked() && i == 0 && (this.getType() == null || (getType().isItemEqual(itemStackIn) && ItemStack.areItemStackTagsEqual(itemStackIn, getType())));
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemStack, EnumFacing direction) {
		return !this.isLocked() && i == 2;
	}


	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return new int[] { 0, 2 };
	}

	@Override
	public Container provideContainer(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new ContainerMassStorage(player.inventory, this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public GuiScreen provideGUI(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new GUIMassStorage(player.inventory, this);
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return null;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return false;
	}

	@Override
	public void openInventory(EntityPlayer player) {

	}

	@Override
	public void closeInventory(EntityPlayer player) {

	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {

	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {

	}

	@Override
	public String getName() {
		return "";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}
}
