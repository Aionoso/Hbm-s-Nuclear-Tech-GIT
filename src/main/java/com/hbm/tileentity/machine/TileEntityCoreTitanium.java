package com.hbm.tileentity.machine;

import api.hbm.energymk2.IEnergyReceiverMK2;
import com.hbm.blocks.ModBlocks;
import com.hbm.lib.ForgeDirection;
import com.hbm.lib.Library;
import com.hbm.items.ModItems;
import com.hbm.tileentity.INBTPacketReceiver;
import com.hbm.tileentity.TileEntityMachineBase;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;

public class TileEntityCoreTitanium extends TileEntityMachineBase implements ITickable, IEnergyReceiverMK2, INBTPacketReceiver {

	public int progress = 0;
	public int progressStep = 1;
	public long power = 0;
	public int soundCycle = 0;
	public boolean hasCluster = false;
	public final static long powerPerStep = 200L;
	public final static int processTime = 400;
	public final static long maxPower = 1000000L;
	
	public TileEntityCoreTitanium() {
		super(23);
	}
	
	@Override
	public String getName() {
		return "container.factoryTitanium";
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		this.progress = compound.getInteger("cookTime");
		this.progressStep = compound.getInteger("speed");
		power = compound.getLong("power");
		if(compound.hasKey("inventory"))
			inventory.deserializeNBT(compound.getCompoundTag("inventory"));
		super.readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setInteger("cookTime", progress);
		compound.setInteger("speed", this.progressStep);
		compound.setLong("power", power);
		compound.setTag("inventory", inventory.serializeNBT());
		return super.writeToNBT(compound);
	}

	public void moveToSlotIfProcessable(int slot){
		if(inventory.getStackInSlot(slot).isEmpty()) {
			for(int i = 0; i < 9; i++)
			{
				if(isProcessable(inventory.getStackInSlot(i)))
				{
					inventory.setStackInSlot(slot, inventory.getStackInSlot(i).copy());
					inventory.setStackInSlot(i, ItemStack.EMPTY);
					break;
				}
			}
		} else {
			for(int i = 0; i < 9; i++)
			{
				if(!inventory.getStackInSlot(i).isEmpty())
				{
					if(Library.areItemStacksEqualIgnoreCount(inventory.getStackInSlot(i), inventory.getStackInSlot(slot)))
					{
						if(inventory.getStackInSlot(slot).getCount() + inventory.getStackInSlot(i).getCount() <= inventory.getStackInSlot(i).getMaxStackSize())
						{
							inventory.getStackInSlot(slot).grow(inventory.getStackInSlot(i).getCount());
							inventory.setStackInSlot(i, ItemStack.EMPTY);
						} else {
							int j = inventory.getStackInSlot(slot).getMaxStackSize() - inventory.getStackInSlot(slot).getCount();
							inventory.getStackInSlot(slot).grow(j);
							inventory.getStackInSlot(i).shrink(j);
						}
						break;
					}
				}
			}
		}
	}

	public boolean hasSpace(int input, int output){
		boolean isEmpty = inventory.getStackInSlot(output).isEmpty();
		if(isEmpty) return true;
		ItemStack outputStack = FurnaceRecipes.instance().getSmeltingResult(inventory.getStackInSlot(input));
		return Library.areItemStacksEqualIgnoreCount(outputStack, inventory.getStackInSlot(output)) && inventory.getStackInSlot(output).getCount() < inventory.getStackInSlot(output).getMaxStackSize();
	}

	public boolean hasSpaceForAll(){
		return hasSpace(9, 11) &&
		hasSpace(10, 12);
	}

	public boolean hasSomethingToProcess(){
		return isProcessable(inventory.getStackInSlot(9)) ||
		isProcessable(inventory.getStackInSlot(10));
	}

	public void process(int input, int output){
		if(isProcessable(inventory.getStackInSlot(input))) {
			ItemStack itemStack = FurnaceRecipes.instance().getSmeltingResult(inventory.getStackInSlot(input));

			if(inventory.getStackInSlot(output).isEmpty()) {
				inventory.setStackInSlot(output, itemStack.copy());
			}else if(Library.areItemStacksEqualIgnoreCount(inventory.getStackInSlot(output), itemStack)) {
				inventory.getStackInSlot(output).grow(itemStack.getCount());
			}

			inventory.getStackInSlot(input).shrink(1);
			if(inventory.getStackInSlot(input).isEmpty()) {
				inventory.setStackInSlot(input, ItemStack.EMPTY);
			}
		}
	}

	public void moveToOuput(int slot){
		if(!inventory.getStackInSlot(slot).isEmpty())
		{
			for(int i = 0; i < 9; i++)
			{
				int j = i + 13;
				if(inventory.getStackInSlot(j).isEmpty()) {
					inventory.setStackInSlot(j, inventory.getStackInSlot(slot).copy());
					inventory.setStackInSlot(slot, ItemStack.EMPTY);
					return;
				} else if(Library.areItemStacksEqualIgnoreCount(inventory.getStackInSlot(j), inventory.getStackInSlot(slot))) {
					ItemStack stack = inventory.getStackInSlot(j);
					int k = stack.getMaxStackSize() - stack.getCount();
					if(k > 0) { //needs k items until stack is complete

						if(stack.getCount() + inventory.getStackInSlot(slot).getCount() <= inventory.getStackInSlot(slot).getMaxStackSize()) {
							inventory.getStackInSlot(j).grow(inventory.getStackInSlot(slot).getCount());
							inventory.setStackInSlot(slot, ItemStack.EMPTY);
							return;
						} else {
							
							if(k < 0) {
								inventory.getStackInSlot(j).grow(k);
								inventory.getStackInSlot(26).shrink(k);
								continue;
							}
						}
					}
				}
			}
		}
	}
	
	@Override
	public void update() {
		if(!world.isRemote && isStructureValid(world)) {
			this.trySubscribe(world, pos.getX(), pos.getY() + 2, pos.getZ(), ForgeDirection.UP);
			this.trySubscribe(world, pos.getX(), pos.getY() - 2, pos.getZ(), ForgeDirection.DOWN);
			hasCluster = inventory.getStackInSlot(22).getItem() == ModItems.factory_core_titanium;
			
			moveToSlotIfProcessable(9);
			moveToSlotIfProcessable(10);
			
			if(this.power > this.progressStep * powerPerStep && hasSomethingToProcess() && hasSpaceForAll())
			{
				this.progress += this.progressStep;
				this.power -= this.progressStep * powerPerStep;

				if(soundCycle == 0)
		        	this.world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_MINECART_RIDING, SoundCategory.BLOCKS, 0.75F, 0.5F);
				soundCycle++;
				
				if(soundCycle >= 50)
					soundCycle = 0;

			} else {
				if(hasCluster) {
					if(this.progressStep > 1 && world.rand.nextInt(10) == 0)
						this.progressStep -= 1;
				}
				this.progress = 0;
			}
			
			if(this.progress >= TileEntityCoreTitanium.processTime){

				process(9, 11);
				process(10, 12);
				this.progress = 0;
				if(hasCluster)
					this.progressStep = Math.min(100, this.progressStep+1);
			}
				
			moveToOuput(11);
			moveToOuput(12);

			NBTTagCompound data = new NBTTagCompound();
			data.setInteger("cookTime", progress);
			data.setInteger("speed", progressStep);
			data.setLong("power", power);
			this.networkPack(data, 250);
		}
	}

	@Override
	public void networkUnpack(NBTTagCompound nbt) {
		this.progress = nbt.getInteger("cookTime");
		this.progressStep = nbt.getInteger("speed");
		this.power = nbt.getLong("power");
	}

	public boolean isStructureValid(World world) {
		MutableBlockPos mPos = new BlockPos.MutableBlockPos();
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		if(world.getBlockState(mPos.setPos(x, y, z)).getBlock() == ModBlocks.factory_titanium_core &&
				world.getBlockState(mPos.setPos(x - 1, y - 1, z - 1)).getBlock() == ModBlocks.factory_titanium_hull &&
				world.getBlockState(mPos.setPos(x, y - 1, z - 1)).getBlock() == ModBlocks.factory_titanium_hull &&
				world.getBlockState(mPos.setPos(x + 1, y - 1, z - 1)).getBlock() == ModBlocks.factory_titanium_hull &&
				world.getBlockState(mPos.setPos(x - 1, y - 1, z)).getBlock() == ModBlocks.factory_titanium_hull &&
				(world.getBlockState(mPos.setPos(x, y - 1, z)).getBlock() == ModBlocks.factory_titanium_conductor || world.getBlockState(mPos.setPos(x, y - 1, z)).getBlock() == ModBlocks.factory_titanium_hull) &&
				world.getBlockState(mPos.setPos(x + 1, y - 1, z)).getBlock() == ModBlocks.factory_titanium_hull &&
				world.getBlockState(mPos.setPos(x - 1, y - 1, z + 1)).getBlock() == ModBlocks.factory_titanium_hull &&
				world.getBlockState(mPos.setPos(x, y - 1, z + 1)).getBlock() == ModBlocks.factory_titanium_hull &&
				world.getBlockState(mPos.setPos(x + 1, y - 1, z + 1)).getBlock() == ModBlocks.factory_titanium_hull &&
				world.getBlockState(mPos.setPos(x - 1, y, z - 1)).getBlock() == ModBlocks.factory_titanium_hull &&
				world.getBlockState(mPos.setPos(x, y, z - 1)).getBlock() == ModBlocks.factory_titanium_furnace &&
				world.getBlockState(mPos.setPos(x + 1, y, z - 1)).getBlock() == ModBlocks.factory_titanium_hull &&
				world.getBlockState(mPos.setPos(x - 1, y, z)).getBlock() == ModBlocks.factory_titanium_furnace &&
				world.getBlockState(mPos.setPos(x + 1, y, z)).getBlock() == ModBlocks.factory_titanium_furnace &&
				world.getBlockState(mPos.setPos(x - 1, y, z + 1)).getBlock() == ModBlocks.factory_titanium_hull &&
				world.getBlockState(mPos.setPos(x, y, z + 1)).getBlock() == ModBlocks.factory_titanium_furnace &&
				world.getBlockState(mPos.setPos(x + 1, y, z + 1)).getBlock() == ModBlocks.factory_titanium_hull &&
				world.getBlockState(mPos.setPos(x - 1, y + 1, z - 1)).getBlock() == ModBlocks.factory_titanium_hull &&
				world.getBlockState(mPos.setPos(x, y + 1, z - 1)).getBlock() == ModBlocks.factory_titanium_hull &&
				world.getBlockState(mPos.setPos(x + 1, y + 1, z - 1)).getBlock() == ModBlocks.factory_titanium_hull &&
				world.getBlockState(mPos.setPos(x - 1, y + 1, z)).getBlock() == ModBlocks.factory_titanium_hull &&
				(world.getBlockState(mPos.setPos(x, y + 1, z)).getBlock() == ModBlocks.factory_titanium_conductor || world.getBlockState(mPos.setPos(x, y + 1, z)).getBlock() == ModBlocks.factory_titanium_hull) &&
				world.getBlockState(mPos.setPos(x + 1, y + 1, z)).getBlock() == ModBlocks.factory_titanium_hull &&
				world.getBlockState(mPos.setPos(x - 1, y + 1, z + 1)).getBlock() == ModBlocks.factory_titanium_hull &&
				world.getBlockState(mPos.setPos(x, y + 1, z + 1)).getBlock() == ModBlocks.factory_titanium_hull &&
				world.getBlockState(mPos.setPos(x + 1, y + 1, z + 1)).getBlock() == ModBlocks.factory_titanium_hull)
		{
			return true;
		}
		return false;
	}

	public long getPowerScaled(long i) {
		return (power * i) / maxPower;
	}

	public int getProgressScaled(int i) {
		return (progress * i) / processTime;
	}

	public boolean isProcessable(ItemStack item) {
		if(item != null && !item.isEmpty())
		{
			return !FurnaceRecipes.instance().getSmeltingResult(item).isEmpty();
		} else {
			return false;
		}
	}

	@Override
	public void setPower(long i) {
		this.power = i;
	}

	@Override
	public long getPower() {
		return power;
	}

	@Override
	public long getMaxPower() {
		return maxPower;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack){
		return slot < 11 && isProcessable(stack);
	}
	
	@Override
	public int[] getAccessibleSlotsFromSide(EnumFacing e){
		return new int[]{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22 };
	}
	
	@Override
	public boolean canInsertItem(int slot, ItemStack itemStack, int amount){
		return this.isItemValidForSlot(slot, itemStack);
	}
	
	@Override
	public boolean canExtractItem(int slot, ItemStack itemStack, int amount){
		return slot > 10 && slot != 22;
	}
}
