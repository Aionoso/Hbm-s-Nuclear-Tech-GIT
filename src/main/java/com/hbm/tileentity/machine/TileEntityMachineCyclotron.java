package com.hbm.tileentity.machine;

import api.hbm.energymk2.IEnergyReceiverMK2;
import com.hbm.config.BombConfig;
import com.hbm.handler.MultiblockHandler;
import com.hbm.inventory.RecipesCommon.AStack;
import com.hbm.inventory.RecipesCommon.NbtComparableStack;
import com.hbm.entity.effect.EntityBlackHole;
import com.hbm.entity.logic.EntityBalefire;
import com.hbm.entity.logic.EntityNukeExplosionMK5;
import com.hbm.explosion.ExplosionLarge;
import com.hbm.explosion.ExplosionThermo;
import com.hbm.forgefluid.FFUtils;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.interfaces.ITankPacketAcceptor;
import com.hbm.inventory.CyclotronRecipes;
import com.hbm.items.ModItems;
import com.hbm.items.machine.ItemMachineUpgrade;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.lib.Library;
import com.hbm.packet.AuxParticlePacketNT;
import com.hbm.packet.FluidTankPacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.tileentity.TileEntityMachineBase;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.item.Item;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.oredict.OreDictionary;

public class TileEntityMachineCyclotron extends TileEntityMachineBase implements ITickable, IEnergyReceiverMK2, IFluidHandler, ITankPacketAcceptor {

	public long power;
	public static final long maxPower = 100000000;
	public int consumption = 1000000;

	public boolean isOn;

	private int age;
	private int countdown;
	private byte plugs; 
	public int progress;
	public static final int duration = 690;

	public FluidTank coolant;
	public FluidTank amat;


	private TileEntity teIn1 = null;
	private TileEntity teIn2 = null;
	private TileEntity teIn3 = null;

	private TileEntity teOut1 = null;
	private TileEntity teOut2 = null;
	private TileEntity teOut3 = null;

	public TileEntityMachineCyclotron() {
		super(0);
		this.inventory = new ItemStackHandler(16){
			@Override
			protected void onContentsChanged(int slot) {
				super.onContentsChanged(slot);
				markDirty();
			}
			@Override
			public void setStackInSlot(int slot, ItemStack stack) {
				if(stack != null && slot >= 14 && slot <= 15 && stack.getItem() instanceof ItemMachineUpgrade)
					world.playSound(null, pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5, HBMSoundHandler.upgradePlug, SoundCategory.BLOCKS, 1.5F, 1.0F);
				super.setStackInSlot(slot, stack);
			}
		};
		coolant = new FluidTank(32000);
		amat = new FluidTank(8000);
	}
	
	@Override
	public String getName() {
		return "container.cyclotron";
	}

	@Override
	public int[] getAccessibleSlotsFromSide(EnumFacing e) {
		int side = e.getIndex();
		if(side == 2) // North
			return new int[] { 6, 7, 8 }; // C
		if(side == 3) // South
			return new int[] { 9, 10, 11, 12 }; // Fluids
		if(side == 4) // West
			return new int[] { 0, 1, 2 }; // A
		if(side == 5) // East
			return new int[] { 3, 4, 5 }; // B
		return new int[] { };
	}

	private void findContainers(){
		int meta = this.getBlockMetadata();
		if(meta == 14) { // East
			teIn1 = world.getTileEntity(pos.add(-3, 0, -1));
			teIn2 = world.getTileEntity(pos.add(-3, 0, 0));
			teIn3 = world.getTileEntity(pos.add(-3, 0, 1));
			teOut1 = world.getTileEntity(pos.add(3, 0, -1));
			teOut2 = world.getTileEntity(pos.add(3, 0, 0));
			teOut3 = world.getTileEntity(pos.add(3, 0, 1));
		}
		if(meta == 12) { // South
			teIn1 = world.getTileEntity(pos.add(1, 0, -3));
			teIn2 = world.getTileEntity(pos.add(0, 0, -3));
			teIn3 = world.getTileEntity(pos.add(-1, 0, -3));
			teOut1 = world.getTileEntity(pos.add(1, 0, 3));
			teOut2 = world.getTileEntity(pos.add(0, 0, 3));
			teOut3 = world.getTileEntity(pos.add(-1, 0, 3));
		}
		if(meta == 15) { // West
			teIn1 = world.getTileEntity(pos.add(3, 0, 1));
			teIn2 = world.getTileEntity(pos.add(3, 0, 0));
			teIn3 = world.getTileEntity(pos.add(3, 0, -1));
			teOut1 = world.getTileEntity(pos.add(-3, 0, 1));
			teOut2 = world.getTileEntity(pos.add(-3, 0, 0));
			teOut3 = world.getTileEntity(pos.add(-3, 0, -1));
		}
		if(meta == 13) { // North
			teIn1 = world.getTileEntity(pos.add(-1, 0, 3));
			teIn2 = world.getTileEntity(pos.add(0, 0, 3));
			teIn3 = world.getTileEntity(pos.add(1, 0, 3));
			teOut1 = world.getTileEntity(pos.add(-1, 0, -3));
			teOut2 = world.getTileEntity(pos.add(0, 0, -3));
			teOut3 = world.getTileEntity(pos.add(1, 0, -3));
		}
	}

	private void fillFromContainers(TileEntity tile, int inputSlot, int tagetSlot){
		int meta = this.getBlockMetadata();
		if(tile != null && tile instanceof ICapabilityProvider) {
			ICapabilityProvider capte = (ICapabilityProvider) tile;
			if(capte.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, MultiblockHandler.intToEnumFacing(meta).rotateY())) {
				IItemHandler cap = capte.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, MultiblockHandler.intToEnumFacing(meta).rotateY());
				int[] slots;
				if(tile instanceof TileEntityMachineBase){
					slots = ((TileEntityMachineBase)tile).getAccessibleSlotsFromSide(MultiblockHandler.intToEnumFacing(meta).rotateY());
					tryFillAssemblerCap(cap, slots, (TileEntityMachineBase)tile, inputSlot, tagetSlot);
				}
				else{
					slots = new int[cap.getSlots()];
					for(int i = 0; i< slots.length; i++)
						slots[i] = i;
					tryFillAssemblerCap(cap, slots, null, inputSlot, tagetSlot);
				}
			}
		}
	}

	public static boolean isItemATarget(Item item){
		return item == ModItems.part_lithium || item == ModItems.part_beryllium || item == ModItems.part_carbon || item == ModItems.part_copper || item == ModItems.part_plutonium; 
	}

	public boolean tryFillAssemblerCap(IItemHandler container, int[] allowedSlots, TileEntityMachineBase te, int inputSlot, int targetSlot) {
		if(allowedSlots.length < 1)
			return false;

		int inputAmount = inventory.getStackInSlot(inputSlot).getMaxStackSize() - inventory.getStackInSlot(inputSlot).getCount(); // how many items do we need to fill the stack?
		int targetAmount = inventory.getStackInSlot(targetSlot).getMaxStackSize() - inventory.getStackInSlot(targetSlot).getCount(); // how many items do we need to fill the stack?
		AStack inputItem = new NbtComparableStack(inventory.getStackInSlot(inputSlot).copy()).singulize();
		AStack targetItem = new NbtComparableStack(inventory.getStackInSlot(targetSlot).copy()).singulize();
		
		int inputDelta = inputAmount;
		int targetDelta = targetAmount;
		for(int slot : allowedSlots) {
			if(container.getStackInSlot(slot) == null || container.getStackInSlot(slot).isEmpty()){ // check next slot in chest if it is empty
				continue;
			}else{ // found an item in chest
				ItemStack stack = container.getStackInSlot(slot).copy();
				// check input
				if(inventory.getStackInSlot(inputSlot) == null || inventory.getStackInSlot(inputSlot).isEmpty()){
					if(isItemATarget(stack.getItem())){
						inputDelta = this.moveItem(container, inputSlot, slot, inputDelta, te);
						continue;
					}
				} else {
					ItemStack compareStack = stack.copy();
					compareStack.setCount(1);
					if(inputDelta > 0 && inputItem.isApplicable(compareStack)){ // bingo found something
						inputDelta = this.moveItem(container, inputSlot, slot, inputDelta, te);
						continue;
					}
				}
				// check target
				if(inventory.getStackInSlot(targetSlot) == null || inventory.getStackInSlot(targetSlot).isEmpty()){
					if(!isItemATarget(stack.getItem())){
						targetDelta = this.moveItem(container, targetSlot, slot, targetDelta, te);
						continue;
					}
				} else {
					ItemStack compareStack = stack.copy();
					compareStack.setCount(1);
					if(targetDelta > 0 && targetItem.isApplicable(compareStack)){ // bingo found something
						targetDelta = this.moveItem(container, targetSlot, slot, targetDelta, te);
						continue;
					}
				}	
			}
		}
		return true;
	}

	private int moveItem(IItemHandler container, int inventorySlot, int containerSlot, int amount, TileEntityMachineBase te){
		ItemStack stack = container.getStackInSlot(containerSlot).copy();
		int foundCount = Math.min(stack.getCount(), amount);
		if(te != null && !te.canExtractItem(containerSlot, stack, foundCount)){
			return amount;
		} else if(foundCount > 0){

			container.extractItem(containerSlot, foundCount, false);

			if(inventory.getStackInSlot(inventorySlot) == null || inventory.getStackInSlot(inventorySlot).isEmpty()){

				stack.setCount(foundCount);
				inventory.setStackInSlot(inventorySlot, stack);

			}else{
				inventory.getStackInSlot(inventorySlot).grow(foundCount); // transfer complete
			}
			amount -= foundCount;
		}
		return amount;
	}


	private void exportIntoContainers(TileEntity tile, int slot){
		int meta = this.getBlockMetadata();
		if(tile != null && tile instanceof ICapabilityProvider) {
			ICapabilityProvider capte = (ICapabilityProvider) tile;
			if(capte.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, MultiblockHandler.intToEnumFacing(meta).rotateY())) {
				IItemHandler cap = capte.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, MultiblockHandler.intToEnumFacing(meta).rotateY());
				tryFillContainerCap(cap, slot);
			}
		}
	}

	//Unloads output into chests. Capability version.
	public boolean tryFillContainerCap(IItemHandler inv, int slot) {

		int size = inv.getSlots();

		for(int i = 0; i < size; i++) {
			if(inv.getStackInSlot(i) != null) {

				if(inventory.getStackInSlot(slot).getItem() == Items.AIR)
					return false;

				ItemStack sta1 = inv.getStackInSlot(i).copy();
				ItemStack sta2 = inventory.getStackInSlot(slot).copy();
				if(sta1 != null && sta2 != null) {
					sta1.setCount(1);
					sta2.setCount(1);

					if(isItemAcceptable(sta1, sta2) && inv.getStackInSlot(i).getCount() < inv.getStackInSlot(i).getMaxStackSize()) {
						inventory.getStackInSlot(slot).shrink(1);

						if(inventory.getStackInSlot(slot).isEmpty())
							inventory.setStackInSlot(slot, ItemStack.EMPTY);

						ItemStack sta3 = inv.getStackInSlot(i).copy();
						sta3.setCount(1);
						inv.insertItem(i, sta3, false);

						return true;
					}
				}
			}
		}
		for(int i = 0; i < size; i++) {

			if(inventory.getStackInSlot(slot).getItem() == Items.AIR)
				return false;

			ItemStack sta2 = inventory.getStackInSlot(slot).copy();
			if(inv.getStackInSlot(i).getItem() == Items.AIR && sta2 != null) {
				sta2.setCount(1);
				inventory.getStackInSlot(slot).shrink(1);

				if(inventory.getStackInSlot(slot).isEmpty())
					inventory.setStackInSlot(slot, ItemStack.EMPTY);

				inv.insertItem(i, sta2, false);

				return true;
			}
		}

		return false;
	}
	
	@Override
	public void update() {
		if (!world.isRemote) {
			age++;
			if(age >= 20)
			{
				age = 0;
			}
			this.updateConnections();
			if(age == 9 || age == 19)
				fillFluidInit(amat);

			this.power = Library.chargeTEFromItems(inventory, 13, power, maxPower);
			FFUtils.fillFromFluidContainer(inventory, coolant, 11, 12);
			if(coolant.getFluid() != null && coolant.getFluid().getFluid() != ModForgeFluids.coolant){
				coolant.setFluid(null);
			}
			FFUtils.fillFluidContainer(inventory, amat, 9, 10);

			this.findContainers();
			this.fillFromContainers(this.teIn1, 0, 3);
			this.fillFromContainers(this.teIn2, 1, 4);
			this.fillFromContainers(this.teIn3, 2, 5);
			this.exportIntoContainers(this.teOut1, 6);
			this.exportIntoContainers(this.teOut2, 7);
			this.exportIntoContainers(this.teOut3, 8);
			
			if(isOn) {

				int defConsumption = consumption - 100000 * getConsumption();

				if(canProcess() && power >= defConsumption) {
					progress += this.getSpeed();
					power -= defConsumption;
					
					if(progress >= duration) {
						process();
						progress = 0;
						this.markDirty();
					}
					if(coolant.getFluidAmount() > 0) {
						countdown = 0;

						if(world.rand.nextInt(3) == 0)
							coolant.drain(1, true);

					} else if(world.rand.nextInt(this.getSafety()) == 0) {

						countdown++;

						int chance = 7 - Math.min((int) Math.ceil(countdown / 200D), 6);

						if(world.rand.nextInt(chance) == 0)
							ExplosionLarge.spawnTracers(world, pos.getX() + 0.5, pos.getY() + 3.25, pos.getZ() + 0.5, 1);

						if(countdown > 1000) {
							ExplosionThermo.setEntitiesOnFire(world, pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5, 25);
							ExplosionThermo.scorchLight(world, pos.getX(), pos.getY(), pos.getZ(), 7);

							if(countdown % 4 == 0)
								ExplosionLarge.spawnBurst(world, pos.getX() + 0.5, pos.getY() + 3.25, pos.getZ() + 0.5, 18, 1);

						} else if(countdown > 600) {
							ExplosionThermo.setEntitiesOnFire(world, pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5, 10);
						}

						if(countdown == 1140)
							world.playSound(null, pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5, HBMSoundHandler.shutdown, SoundCategory.BLOCKS, 10.0F, 1.0F);

						if(countdown > 1200)
							explode();
					}
				} else {
					progress = 0;
				}
				
			} else {
				progress = 0;
			}
			
			NBTTagCompound data = new NBTTagCompound();
			data.setLong("power", power);
			data.setInteger("progress", progress);
			data.setBoolean("isOn", isOn);
			data.setByte("plugs", plugs);
			this.networkPack(data, 25);

			PacketDispatcher.wrapper.sendToAllAround(new FluidTankPacket(pos, coolant, amat), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 15));
		}
	}
	
	private void updateConnections()  {
		
		this.trySubscribe(world, pos.getX() + 3, pos.getY(), pos.getZ() + 1, Library.POS_X);
		this.trySubscribe(world, pos.getX() + 3, pos.getY(), pos.getZ() - 1, Library.POS_X);
		this.trySubscribe(world, pos.getX() - 3, pos.getY(), pos.getZ() + 1, Library.NEG_X);
		this.trySubscribe(world, pos.getX() - 3, pos.getY(), pos.getZ() - 1, Library.NEG_X);
		this.trySubscribe(world, pos.getX() + 1, pos.getY(), pos.getZ() + 3, Library.POS_Z);
		this.trySubscribe(world, pos.getX() - 1, pos.getY(), pos.getZ() + 3, Library.POS_Z);
		this.trySubscribe(world, pos.getX() + 1, pos.getY(), pos.getZ() - 3, Library.NEG_Z);
		this.trySubscribe(world, pos.getX() - 1, pos.getY(), pos.getZ() - 3, Library.NEG_Z);
	}

	@Override
	public void networkUnpack(NBTTagCompound data) {
		this.isOn = data.getBoolean("isOn");
		this.power = data.getLong("power");
		this.plugs = data.getByte("plugs");
		this.progress = data.getInteger("progress");
	}
	
	@Override
	public void handleButtonPacket(int value, int meta) {
		isOn = !isOn;
	}

	public boolean isItemAcceptable(ItemStack stack1, ItemStack stack2) {

		if(stack1 != null && stack2 != null && !stack1.isEmpty() && !stack2.isEmpty()) {
			if(Library.areItemStacksCompatible(stack1, stack2))
				return true;

			int[] ids1 = OreDictionary.getOreIDs(stack1);
			int[] ids2 = OreDictionary.getOreIDs(stack2);

			if(ids1 != null && ids2 != null && ids1.length > 0 && ids2.length > 0) {
				for(int i = 0; i < ids1.length; i++)
					for(int j = 0; j < ids2.length; j++)
						if(ids1[i] == ids2[j])
							return true;
			}
		}

		return false;
	}
	
	private void explode() {

		ExplosionLarge.explodeFire(world, pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5, 25, true, false, true);
		int rand = world.rand.nextInt(10);

		if(rand < 2) {
			world.spawnEntity(EntityNukeExplosionMK5.statFac(world, (int)(BombConfig.fatmanRadius * 1.5), pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5));
			
			NBTTagCompound data = new NBTTagCompound();
			data.setString("type", "muke");
			PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(data, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5), new TargetPoint(world.provider.getDimension(), pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 250));
			world.playSound(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, HBMSoundHandler.mukeExplosion, SoundCategory.BLOCKS, 15.0F, 1.0F);
		} else if(rand < 4) {
			EntityBalefire bf = new EntityBalefire(world);
			bf.posX = pos.getX() + 0.5;
			bf.posY = pos.getY() + 1.5;
			bf.posZ = pos.getZ() + 0.5;
			bf.destructionRange = (int)(BombConfig.fatmanRadius * 1.5);
			world.spawnEntity(bf);
			
			NBTTagCompound data = new NBTTagCompound();
			data.setString("type", "muke");
			data.setBoolean("balefire", true);
			PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(data, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5), new TargetPoint(world.provider.getDimension(), pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 250));
			world.playSound(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, HBMSoundHandler.mukeExplosion, SoundCategory.BLOCKS, 15.0F, 1.0F);
		} else if(rand < 5) {
			EntityBlackHole bl = new EntityBlackHole(world, 1.5F + world.rand.nextFloat());
			bl.posX = pos.getX() + 0.5F;
			bl.posY = pos.getY() + 1.5F;
			bl.posZ = pos.getZ() + 0.5F;
			world.spawnEntity(bl);
		}
	}
	
	public boolean canProcess(){

		for(int i = 0; i < 3; i++) {
			Object[] res = CyclotronRecipes.getOutput(inventory.getStackInSlot(i+3), inventory.getStackInSlot(i));

			if(res == null)
				continue;

			ItemStack out = (ItemStack)res[0];

			if(out == null)
				continue;

			if(inventory.getStackInSlot(i+6).isEmpty())
				return true;
			if(Library.areItemStacksCompatible(out, inventory.getStackInSlot(i+6), false) && inventory.getStackInSlot(i+6).getCount() < out.getMaxStackSize())
				return true;
		}

		return false;
	}
	
	public void process() {

		for(int i = 0; i < 3; i++) {

			Object[] res = CyclotronRecipes.getOutput(inventory.getStackInSlot(i+3), inventory.getStackInSlot(i));

			if(res == null)
				continue;

			ItemStack out = (ItemStack)res[0];

			if(out == null)
				continue;

			if(inventory.getStackInSlot(i+6).isEmpty()) {
				amat.fill(new FluidStack(ModForgeFluids.amat, (Integer)res[1]), true);
				inventory.getStackInSlot(i).shrink(1);
				inventory.getStackInSlot(i+3).shrink(1);
				inventory.setStackInSlot(i+6, out);
				continue;
			}

			if(inventory.getStackInSlot(i+6).getItem() == out.getItem() && inventory.getStackInSlot(i+6).getItemDamage() == out.getItemDamage() && inventory.getStackInSlot(i+6).getCount() < out.getMaxStackSize()) {

				amat.fill(new FluidStack(ModForgeFluids.amat, (Integer)res[1]), true);
				inventory.getStackInSlot(i).shrink(1);
				inventory.getStackInSlot(i+3).shrink(1);
				inventory.getStackInSlot(i+6).grow(1);
			}
		}
	}
	
	public int getSpeed() {
		
		int speed = 1;
		
		for(int i = 14; i < 16; i++) {

			if(!inventory.getStackInSlot(i).isEmpty()) {

				if(inventory.getStackInSlot(i).getItem() == ModItems.upgrade_speed_1)
					speed += 1;
				else if(inventory.getStackInSlot(i).getItem() == ModItems.upgrade_speed_2)
					speed += 2;
				else if(inventory.getStackInSlot(i).getItem() == ModItems.upgrade_speed_3)
					speed += 3;
			}
		}

		return Math.min(speed, 4);
	}

	public int getConsumption() {

		int speed = 0;

		for(int i = 14; i < 16; i++) {

			if(!inventory.getStackInSlot(i).isEmpty()) {

				if(inventory.getStackInSlot(i).getItem() == ModItems.upgrade_power_1)
					speed += 1;
				else if(inventory.getStackInSlot(i).getItem() == ModItems.upgrade_power_2)
					speed += 2;
				else if(inventory.getStackInSlot(i).getItem() == ModItems.upgrade_power_3)
					speed += 3;
			}
		}

		return Math.min(speed, 3);
	}

	public int getSafety() {

		int speed = 1;

		for(int i = 14; i < 16; i++) {

			if(!inventory.getStackInSlot(i).isEmpty()) {

				if(inventory.getStackInSlot(i).getItem() == ModItems.upgrade_effect_1)
					speed += 1;
				else if(inventory.getStackInSlot(i).getItem() == ModItems.upgrade_effect_2)
					speed += 2;
				else if(inventory.getStackInSlot(i).getItem() == ModItems.upgrade_effect_3)
					speed += 3;
			}
		}

		return Math.min(speed, 4);
	}
	
	public long getPowerScaled(long i) {
		return (power * i) / maxPower;
	}
	
	public int getProgressScaled(int i) {
		return (progress * i) / duration;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);

		coolant.readFromNBT(compound.getCompoundTag("coolant"));
		amat.readFromNBT(compound.getCompoundTag("amat"));

		this.isOn = compound.getBoolean("isOn");
		this.countdown = compound.getInteger("countdown");
		this.progress = compound.getInteger("progress");
		this.plugs = compound.getByte("plugs");
		this.power = compound.getLong("power");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setTag("coolant", coolant.writeToNBT(new NBTTagCompound()));
		compound.setTag("amat", amat.writeToNBT(new NBTTagCompound()));

		compound.setBoolean("isOn", isOn);
		compound.setInteger("countdown", countdown);
		compound.setByte("plugs", plugs);
		compound.setInteger("progress", progress);
		compound.setLong("power", power);
		return super.writeToNBT(compound);
	}
	
	public void setPlug(int index) {
		this.plugs |= (1 << index);
		this.markDirty();
	}

	public boolean getPlug(int index) {
		return (this.plugs & (1 << index)) > 0;
	}

	public static Item getItemForPlug(int i) {

		switch(i) {
		case 0: return ModItems.powder_balefire;
		case 1: return ModItems.book_of_;
		case 2: return ModItems.diamond_gavel;
		case 3: return ModItems.coin_maskman;
		}

		return null;
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(pos.getX() - 2, pos.getY(), pos.getZ() - 2, pos.getX() + 3, pos.getY() + 4, pos.getZ() + 3);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared() {
		return 65536.0D;
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY){
			return true;
		}
		return super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY){
			return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this);
		}
		return super.getCapability(capability, facing);
	}
	
	public void fillFluidInit(FluidTank tank) {

		fillFluid(pos.getX() + 3, pos.getY(), pos.getZ() + 1, tank);
		fillFluid(pos.getX() + 3, pos.getY(), pos.getZ() - 1, tank);
		fillFluid(pos.getX() - 3, pos.getY(), pos.getZ() + 1, tank);
		fillFluid(pos.getX() - 3, pos.getY(), pos.getZ() - 1, tank);

		fillFluid(pos.getX() + 1, pos.getY(), pos.getZ() + 3, tank);
		fillFluid(pos.getX() - 1, pos.getY(), pos.getZ() + 3, tank);
		fillFluid(pos.getX() + 1, pos.getY(), pos.getZ() - 3, tank);
		fillFluid(pos.getX() - 1, pos.getY(), pos.getZ() - 3, tank);
	}

	public void fillFluid(int x, int y, int z, FluidTank tank) {
		FFUtils.fillFluid(this, tank, world, new BlockPos(x, y, z), 2000);
	}
	
	@Override
	public IFluidTankProperties[] getTankProperties() {
		return new IFluidTankProperties[]{coolant.getTankProperties()[0], amat.getTankProperties()[0]};
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		if(resource != null && resource.getFluid() == ModForgeFluids.coolant){
			return coolant.fill(resource, doFill);
		}
		return 0;
	}

	@Override
	public FluidStack drain(FluidStack resource, boolean doDrain) {
		return amat.drain(resource, doDrain);
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		return amat.drain(maxDrain, doDrain);
	}
	
	@Override
	public void recievePacket(NBTTagCompound[] tags) {
		if(tags.length == 2){
			coolant.readFromNBT(tags[0]);
			amat.readFromNBT(tags[1]);
		}
	}

	@Override
	public void setPower(long i) {
		this.power = i;
	}

	@Override
	public long getPower() {
		return this.power;
	}

	@Override
	public long getMaxPower() {
		return maxPower;
	}
	

}
