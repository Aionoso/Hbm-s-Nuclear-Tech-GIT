package com.hbm.tileentity.machine;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import api.hbm.energymk2.IEnergyReceiverMK2;
import com.hbm.blocks.ModBlocks;
import com.hbm.handler.MultiblockHandler;
import com.hbm.inventory.AssemblerRecipes;
import com.hbm.inventory.RecipesCommon.AStack;
import com.hbm.items.ModItems;
import com.hbm.items.machine.ItemAssemblyTemplate;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.lib.Library;
import com.hbm.main.MainRegistry;
import com.hbm.sound.AudioWrapper;
import com.hbm.tileentity.TileEntityMachineBase;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.oredict.OreDictionary;

public class TileEntityMachineAssembler extends TileEntityMachineBase implements ITickable, IEnergyReceiverMK2 {

	public long power;
	public static final long maxPower = 2000000;
	public int progress;
	public boolean needsProcess = true;
	public int maxProgress = 100;
	public boolean isProgressing;
	int age = 0;
	int consumption = 100;
	int speed = 100;

	@SideOnly(Side.CLIENT)
	public int recipe;

	private AudioWrapper audio;
	
	public TileEntityMachineAssembler() {

		super(18);
		inventory = new ItemStackHandler(18){
			@Override
			protected void onContentsChanged(int slot){
				markDirty();
				OnContentsChanged(slot);
				super.onContentsChanged(slot);
			}
		};
	}

	public void OnContentsChanged(int slot){
		this.needsProcess = true;
	}


	@Override
	public String getName() {
		return "container.assembler";
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.power = nbt.getLong("powerTime");
		this.isProgressing = nbt.getBoolean("progressing");
		this.progress = nbt.getInteger("progress");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setBoolean("progressing", this.isProgressing);
		nbt.setLong("powerTime", power);
		nbt.setInteger("progress", progress);
		return nbt;
	}

	public long getPowerScaled(long i) {
		return (power * i) / maxPower;
	}

	public int getProgressScaled(int i) {
		return (progress * i) / Math.max(10, maxProgress);
	}

	@Override
	public void update() {
		if(!world.isRemote) {

			this.updateConnections();

			this.consumption = 100;
			this.speed = 100;

			double c = 100;
			double s = 100;

			for(int i = 1; i < 4; i++) {
				ItemStack stack = inventory.getStackInSlot(i);

				if(!stack.isEmpty()) {
					if(stack.getItem() == ModItems.upgrade_speed_1) {
						s *= 0.75;
						c *= 3;
					}
					if(stack.getItem() == ModItems.upgrade_speed_2) {
						s *= 0.65;
						c *= 6;
					}
					if(stack.getItem() == ModItems.upgrade_speed_3) {
						s *= 0.5;
						c *= 9;
					}
					if(stack.getItem() == ModItems.upgrade_power_1) {
						c *= 0.8;
						s *= 1.25;
					}
					if(stack.getItem() == ModItems.upgrade_power_2) {
						c *= 0.4;
						s *= 1.5;
					}
					if(stack.getItem() == ModItems.upgrade_power_3) {
						c *= 0.2;
						s *= 2;
					}
				}
			}
		this.speed = (int) s;
		this.consumption = (int) c;

		if(speed < 2)
			speed = 2;
		if(consumption < 2)
			consumption = 2;
			isProgressing = false;
			power = Library.chargeTEFromItems(inventory, 0, power, maxPower);
			if(needsProcess && (AssemblerRecipes.getOutputFromTempate(inventory.getStackInSlot(4)) != ItemStack.EMPTY && AssemblerRecipes.getRecipeFromTempate(inventory.getStackInSlot(4)) != null)) {
				this.maxProgress = (ItemAssemblyTemplate.getProcessTime(inventory.getStackInSlot(4)) * speed) / 100;
				if(removeItems(AssemblerRecipes.getRecipeFromTempate(inventory.getStackInSlot(4)), cloneItemStackProper(inventory))) {
					if(power >= consumption ){
					if(inventory.getStackInSlot(5).isEmpty() || (!inventory.getStackInSlot(5).isEmpty() && inventory.getStackInSlot(5).getItem() == AssemblerRecipes.getOutputFromTempate(inventory.getStackInSlot(4)).copy().getItem()) && inventory.getStackInSlot(5).getCount() + AssemblerRecipes.getOutputFromTempate(inventory.getStackInSlot(4)).copy().getCount() <= inventory.getStackInSlot(5).getMaxStackSize()) {
						progress++;
						isProgressing = true;

						if(progress >= maxProgress) {
							progress = 0;
							if(inventory.getStackInSlot(5).isEmpty()) {
								inventory.setStackInSlot(5, AssemblerRecipes.getOutputFromTempate(inventory.getStackInSlot(4)).copy());
							} else {
								inventory.getStackInSlot(5).grow(AssemblerRecipes.getOutputFromTempate(inventory.getStackInSlot(4)).copy().getCount());
							}

							removeItems(AssemblerRecipes.getRecipeFromTempate(inventory.getStackInSlot(4)), inventory);
							if(inventory.getStackInSlot(0).getItem() == ModItems.meteorite_sword_alloyed)
								inventory.setStackInSlot(0, new ItemStack(ModItems.meteorite_sword_machined));
						}

						power -= consumption;
					}}
				} else{
					progress = 0;
					needsProcess = false;
				}
			} else{
				progress = 0;
			}


			int meta = this.getBlockMetadata();
			TileEntity te = null;
			TileEntity te2 = null;
			boolean canFill;
			if(meta == 2) {
				te2 = world.getTileEntity(pos.add(-2, 0, 0));
				te = world.getTileEntity(pos.add(3, 0, -1));
			}
			if(meta == 3) {
				te2 = world.getTileEntity(pos.add(2, 0, 0));
				te = world.getTileEntity(pos.add(-3, 0, 1));
			}
			if(meta == 4) {
				te2 = world.getTileEntity(pos.add(0, 0, 2));
				te = world.getTileEntity(pos.add(-1, 0, -3));
			}
			if(meta == 5) {
				te2 = world.getTileEntity(pos.add(0, 0, -2));
				te = world.getTileEntity(pos.add(1, 0, 3));
			}
			canFill = !(te2 instanceof TileEntityDummyPort);

			if(!isProgressing){
				tryExchangeTemplates(te, te2);
			}

			if(te != null) {
				ICapabilityProvider capte = te;
				if(capte.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, MultiblockHandler.intToEnumFacing(meta).rotateY())) {
					IItemHandler cap = capte.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, MultiblockHandler.intToEnumFacing(meta).rotateY());
					if (cap != null){
						tryFillContainerCap(cap, 5);
					}
				}
			}

			if(te2 != null) {
				ICapabilityProvider capte = te2;
				if(capte.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, MultiblockHandler.intToEnumFacing(meta).rotateY())) {
					IItemHandler cap = capte.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, MultiblockHandler.intToEnumFacing(meta).rotateY());
					int[] slots;
					if(te2 instanceof TileEntityMachineBase){
						slots = ((TileEntityMachineBase)te2).getAccessibleSlotsFromSide(MultiblockHandler.intToEnumFacing(meta).rotateY());
						tryFillAssemblerCap(cap, slots, (TileEntityMachineBase)te2);
					}
					else if(cap != null){
						if(canFill) {
							slots = new int[cap.getSlots()];
							for (int i = 0; i < slots.length; i++)
								slots[i] = i;
							tryFillAssemblerCap(cap, slots, null);
						}
					}
					
				}
			}

			NBTTagCompound data = new NBTTagCompound();
			data.setLong("power", power);
			data.setInteger("progress", progress);
			data.setInteger("maxProgress", maxProgress);
			data.setBoolean("isProgressing", isProgressing);
			data.setInteger("recipe", !inventory.getStackInSlot(4).isEmpty() ? ItemAssemblyTemplate.getRecipeIndex(inventory.getStackInSlot(4)) : -1);
			this.networkPack(data, 150);
		} else {

			float volume = this.getVolume(2);

			if(isProgressing && volume > 0) {

				if(audio == null) {
					audio = MainRegistry.proxy.getLoopedSound(HBMSoundHandler.assemblerOperate, SoundCategory.BLOCKS, pos.getX(), pos.getY(), pos.getZ(), volume, 1.0F);
					audio.startSound();
				}

			} else {

				if(audio != null) {
					audio.stopSound();
					audio = null;
				}
			}

		}
	}

	private void updateConnections() {
		int meta = this.getBlockMetadata();
		
		if(meta == 5) {
			this.trySubscribe(world, pos.getX() - 2, pos.getY(), pos.getZ(), Library.NEG_X);
			this.trySubscribe(world, pos.getX() - 2, pos.getY(), pos.getZ() + 1, Library.NEG_X);
			this.trySubscribe(world, pos.getX() + 3, pos.getY(), pos.getZ(), Library.POS_X);
			this.trySubscribe(world, pos.getX() + 3, pos.getY(), pos.getZ() + 1, Library.POS_X);
			
		} else if(meta == 3) {
			this.trySubscribe(world, pos.getX(), pos.getY(), pos.getZ() - 2, Library.NEG_Z);
			this.trySubscribe(world, pos.getX() - 1, pos.getY(), pos.getZ() - 2, Library.NEG_Z);
			this.trySubscribe(world, pos.getX(), pos.getY(), pos.getZ() + 3, Library.POS_Z);
			this.trySubscribe(world, pos.getX() - 1, pos.getY(), pos.getZ() + 3, Library.POS_Z);
			
		} else if(meta == 4) {
			this.trySubscribe(world, pos.getX() + 2, pos.getY(), pos.getZ(), Library.POS_X);
			this.trySubscribe(world, pos.getX() + 2, pos.getY(), pos.getZ() - 1, Library.POS_X);
			this.trySubscribe(world, pos.getX() - 3, pos.getY(), pos.getZ(), Library.NEG_X);
			this.trySubscribe(world, pos.getX() - 3, pos.getY(), pos.getZ() - 1, Library.NEG_X);
			
		} else if(meta == 2) {
			this.trySubscribe(world, pos.getX(), pos.getY(),  pos.getZ() + 2, Library.POS_Z);
			this.trySubscribe(world, pos.getX() + 1, pos.getY(), pos.getZ() + 2, Library.POS_Z);
			this.trySubscribe(world, pos.getX(), pos.getY(), pos.getZ() - 3, Library.NEG_Z);
			this.trySubscribe(world, pos.getX() + 1, pos.getY(), pos.getZ() - 3, Library.NEG_Z);
		}
	}

	@Override
	public void onChunkUnload() {
		if(audio != null) {
			audio.stopSound();
			audio = null;
    	}
	}
	
	@Override
	public void invalidate() {
		super.invalidate();
    	if(audio != null) {
			audio.stopSound();
			audio = null;
    	}
	}
	
	@Override
	public void networkUnpack(NBTTagCompound nbt) {
		this.power = nbt.getLong("power");
		this.progress = nbt.getInteger("progress");
		this.maxProgress = nbt.getInteger("maxProgress");
		this.isProgressing = nbt.getBoolean("isProgressing");
		this.recipe = nbt.getInteger("recipe");
	}

	public boolean tryExchangeTemplates(TileEntity te1, TileEntity te2) {
		//validateTe sees if it's a valid inventory tile entity
		boolean te1Valid = validateTe(te1);
		boolean te2Valid = validateTe(te2);

		if(te1Valid && te2Valid) {
			IItemHandlerModifiable iTe1 = (IItemHandlerModifiable) te1.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
			IItemHandlerModifiable iTe2 = (IItemHandlerModifiable) te2.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
			boolean openSlot = false;
			boolean existingTemplate = false;
			boolean filledContainer = false;
			//Check if there's an existing template and an open slot
			for(int i = 0; i < iTe1.getSlots(); i++) {
				if(iTe1.getStackInSlot(i).isEmpty()) {
					openSlot = true;
					break;
				}

			}
			if(!this.inventory.getStackInSlot(4).isEmpty()) {
				existingTemplate = true;
			}
			//Check if there's a template in input
			for(int i = 0; i < iTe2.getSlots(); i++) {
				if(iTe2.getStackInSlot(i).getItem() instanceof ItemAssemblyTemplate) {
					if(openSlot && existingTemplate) {
						filledContainer = tryFillContainerCap(iTe1, 4);
					}
					if(filledContainer || !existingTemplate) {
						ItemStack copy = iTe2.getStackInSlot(i).copy();
						iTe2.setStackInSlot(i, ItemStack.EMPTY);
						this.inventory.setStackInSlot(4, copy);
						return false;
					}
				}

			}

		}
		return false;

	}

	private boolean validateTe(TileEntity te) {
		if(te != null && te.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null) && te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null) instanceof IItemHandlerModifiable)
			return true;
		return false;
	}

	//I can't believe that worked.
	public ItemStackHandler cloneItemStackProper(IItemHandlerModifiable array) {
		ItemStackHandler stack = new ItemStackHandler(array.getSlots());

		for(int i = 0; i < array.getSlots(); i++)
			if(array.getStackInSlot(i).getItem() != Items.AIR)
				stack.setStackInSlot(i, array.getStackInSlot(i).copy());
			else
				stack.setStackInSlot(i, ItemStack.EMPTY);
		;

		return stack;
	}

	//Unloads output into chests
	public boolean tryFillContainer(IInventory inv, int slot) {

		int size = inv.getSizeInventory();

		for(int i = 0; i < size; i++) {
			if(inv.getStackInSlot(i) != null) {

				if(inventory.getStackInSlot(slot).getItem() == Items.AIR)
					return false;

				ItemStack sta1 = inv.getStackInSlot(i).copy();
				ItemStack sta2 = inventory.getStackInSlot(slot).copy();
				if(sta1 != null && sta2 != null) {
					sta1.setCount(1);
					sta2.setCount(1);

					if(isItemAcceptable(sta1, sta2) && inventory.getStackInSlot(i).getCount() < inventory.getStackInSlot(i).getMaxStackSize()) {
						inventory.getStackInSlot(slot).shrink(1);

						if(inventory.getStackInSlot(slot).isEmpty())
							inventory.setStackInSlot(slot, ItemStack.EMPTY);

						ItemStack sta3 = inventory.getStackInSlot(i).copy();
						sta3.grow(1);
						inv.setInventorySlotContents(i, sta3);

						return true;
					}
				}
			}
		}
		for(int i = 0; i < size; i++) {

			if(inventory.getStackInSlot(slot).getItem() == Items.AIR)
				return false;

			ItemStack sta2 = inventory.getStackInSlot(slot).copy();
			if(inv.getStackInSlot(i) == null && sta2 != null) {
				sta2.setCount(1);
				inventory.getStackInSlot(slot).shrink(1);
				;

				if(inventory.getStackInSlot(slot).isEmpty())
					inventory.setStackInSlot(slot, ItemStack.EMPTY);

				inv.setInventorySlotContents(i, sta2);

				return true;
			}
		}

		return false;
	}

	//Unloads output into chests. Capability version.
	public boolean tryFillContainerCap(IItemHandler chest, int slot) {
		//Check if we have something to output
		if(inventory.getStackInSlot(slot).isEmpty())
			return false;

		for(int i = 0; i < chest.getSlots(); i++) {
			
			ItemStack outputStack = inventory.getStackInSlot(slot).copy();
			if(outputStack.isEmpty())
				return false;

			ItemStack chestItem = chest.getStackInSlot(i).copy();
			if(chestItem.isEmpty() || (Library.areItemStacksCompatible(outputStack, chestItem, false) && chestItem.getCount() < chestItem.getMaxStackSize())) {
				inventory.getStackInSlot(slot).shrink(1);

				outputStack.setCount(1);
				chest.insertItem(i, outputStack, false);

				return true;
			}
		}

		return false;
	}

	private int getValidSlot(AStack nextIngredient){
		int firstFreeSlot = -1;
		float maxStackSize = nextIngredient.getStack().getMaxStackSize();
		int stackCount = (int)Math.ceil(nextIngredient.count() / maxStackSize);
		int stacksFound = 0;

		nextIngredient = nextIngredient.singulize();
		
		for(int k = 6; k < 18; k++) { //scaning inventory if some of the ingredients allready exist
			if(stacksFound < stackCount){
				ItemStack assStack = inventory.getStackInSlot(k).copy();
				if(assStack.isEmpty()){
					if(firstFreeSlot < 6){
						firstFreeSlot = k;
					}
				} else { // check if there are already enough filled stacks is full
				
					assStack.setCount(1);
					if(nextIngredient.isApplicable(assStack)){ // check if it is the right item
						if(inventory.getStackInSlot(k).getCount() < assStack.getMaxStackSize()) {
							// is that stack full?
							return k; // found a not full slot where we already have that ingredient
						}
						else
							stacksFound++;
					}
				}
			}else {
				return -1; // All required stacks are full
			}
		}
		if(firstFreeSlot < 6) // nothing free in assembler inventory anymore
			return -2;
		return firstFreeSlot;
	}

	//private int extractIngredient(IItemHandler container)


	public boolean tryFillAssemblerCap(IItemHandler container, int[] allowedSlots, TileEntityMachineBase te) {
		if(allowedSlots.length < 1)
			return false;
		if(AssemblerRecipes.getOutputFromTempate(inventory.getStackInSlot(4)) == ItemStack.EMPTY || AssemblerRecipes.getRecipeFromTempate(inventory.getStackInSlot(4)) == null) //No recipe template found
			return false;
		else {
			List<AStack> recipeIngredients = new ArrayList<>(AssemblerRecipes.getRecipeFromTempate(inventory.getStackInSlot(4))); //Loading Ingredients
			Map<Integer, ItemStack> itemStackMap = new HashMap<Integer, ItemStack>();

			for(int slot : allowedSlots) {
				container.getStackInSlot(slot);
				if (container.getStackInSlot(slot).isEmpty()) { // check next slot in chest if it is empty
					continue;
				} else { // found an item in chest
					itemStackMap.put(slot, container.getStackInSlot(slot).copy());
				}
			}
			if(itemStackMap.size() == 0){
				return true;
			}

			for(int ig = 0; ig < recipeIngredients.size(); ig++) {

				AStack nextIngredient = recipeIngredients.get(ig).copy(); // getting new ingredient
				
				int ingredientSlot = getValidSlot(nextIngredient);


				if(ingredientSlot < 6)
					continue; // Ingredient filled or Assembler is full

				int possibleAmount = inventory.getStackInSlot(ingredientSlot).getMaxStackSize() - inventory.getStackInSlot(ingredientSlot).getCount(); // how many items do we need to fill the stack?
				
				if(possibleAmount == 0){ // full
					System.out.println("This should never happen method getValidSlot broke");
					continue;
				}
				// Ok now we know what we are looking for (nexIngredient) and where to put it (ingredientSlot) - So lets see if we find some of it in containers
				for (Map.Entry<Integer,ItemStack> set :
						itemStackMap.entrySet()) {
					    ItemStack stack = set.getValue();
						int slot = set.getKey();
						ItemStack compareStack = stack.copy();
						compareStack.setCount(1);

						if(nextIngredient.isApplicable(compareStack)){ // bingo found something

							int foundCount = Math.min(stack.getCount(), possibleAmount);
							if(te != null && !te.canExtractItem(slot, stack, foundCount))
								continue;
							if(foundCount > 0){
								possibleAmount -= foundCount;
								container.extractItem(slot, foundCount, false);
								inventory.getStackInSlot(ingredientSlot);
								if(inventory.getStackInSlot(ingredientSlot).isEmpty()){

									stack.setCount(foundCount);
									inventory.setStackInSlot(ingredientSlot, stack);

								}else{
									inventory.getStackInSlot(ingredientSlot).grow(foundCount); // transfer complete
								}
								needsProcess = true;
							}else{
								break; // ingredientSlot filled
							}
						}
					}
				}
			}
			return true;
	}

	//boolean true: remove items, boolean false: simulation mode
	public boolean removeItems(List<AStack> stack, IItemHandlerModifiable array) {
		if(stack == null)
			return false;

		for(int i = 0; i < stack.size(); i++) {
			for(int j = 0; j < stack.get(i).count(); j++) {
				AStack sta = stack.get(i).copy();
				sta.singulize();
				if(!canRemoveItemFromArray(sta, array)){
					return false;
				}
			}
		}

		return true;

	}

	public boolean canRemoveItemFromArray(AStack stack, IItemHandlerModifiable array) {
		AStack st = stack.copy();

		if(st == null)
			return true;

		for(int i = 6; i < 18; i++) {

			if(!array.getStackInSlot(i).isEmpty()) {

				ItemStack sta = array.getStackInSlot(i).copy();
				sta.setCount(1);

				if(st.isApplicable(sta) && array.getStackInSlot(i).getCount() > 0) {
					array.getStackInSlot(i).shrink(1);

					if(array.getStackInSlot(i).isEmpty())
						array.setStackInSlot(i, ItemStack.EMPTY);

					return true;
				}
			}
		}

		return false;
	}

	public boolean isItemAcceptable(ItemStack stack1, ItemStack stack2) {

		if(stack1 != null && stack2 != null && !stack1.isEmpty() && !stack2.isEmpty()) {
			if(Library.areItemStacksCompatible(stack1, stack2))
				return true;

			int[] ids1 = OreDictionary.getOreIDs(stack1);
			int[] ids2 = OreDictionary.getOreIDs(stack2);

			if(ids1.length > 0 && ids2.length > 0) {
				for(int i = 0; i < ids1.length; i++)
					for(int j = 0; j < ids2.length; j++)
						if(ids1[i] == ids2[j])
							return true;
			}
		}

		return false;
	}

	//Drillgon200: Method so I can check stuff like containing a fluid without checking if the compound tags are exactly equal, that way
	//it's more compatible with capabilities.
	//private boolean areStacksEqual(ItemStack sta1, ItemStack sta2){
	//	return Library.areItemStacksCompatible(sta2, sta1);
	//return ItemStack.areItemStacksEqual(sta1, sta2);
	//	}

	@Override
	public void setPower(long i) {
		power = i;

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
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1).grow(2, 1, 2).grow(10);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared() {
		return 65536.0D;
	}
	
	@Override
	public int countMufflers() {

		int count = 0;

		for(int x = pos.getX() - 1; x <= pos.getX() + 1; x++)
			for(int z = pos.getZ() - 1; z <= pos.getZ() + 1; z++)
				if(world.getBlockState(new BlockPos(x, pos.getY() - 1, z)).getBlock() == ModBlocks.muffler)
					count++;

		return count;
	}
}
