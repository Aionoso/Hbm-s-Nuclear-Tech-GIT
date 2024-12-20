package com.hbm.tileentity.machine;

import java.util.HashMap;

import com.hbm.forgefluid.FFUtils;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.interfaces.ITankPacketAcceptor;
import com.hbm.inventory.RecipesCommon.ComparableStack;
import com.hbm.inventory.RecipesCommon.NbtComparableStack;
import com.hbm.inventory.SILEXRecipes;
import com.hbm.inventory.SILEXRecipes.SILEXRecipe;
import com.hbm.inventory.fluid.Fluids;
import com.hbm.items.machine.ItemFluidIcon;
import com.hbm.packet.FluidTankPacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.tileentity.TileEntityMachineBase;
import com.hbm.util.InventoryUtil;
import com.hbm.util.WeightedRandomObject;
import com.hbm.items.machine.ItemFELCrystal.EnumWavelengths;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntitySILEX extends TileEntityMachineBase implements ITickable, IFluidHandler, ITankPacketAcceptor {

	public EnumWavelengths mode = EnumWavelengths.NULL;
	public boolean hasLaser;
	public FluidTank tank;
	public ComparableStack current;
	public int currentFill;
	public static final int maxFill = 16000;
	public int progress;
	public final int processTime = 80;
	
	//0: Input
	//2-3: Fluid Containers
	//4: Output
	//5-10: Queue

	public TileEntitySILEX() {
		super(10);
		//acid
		tank = new FluidTank(16000);
	}

	public Fluid getTankType(){
		return tank.getFluid() == null ? null : tank.getFluid().getFluid();
	}
	
	@Override
	public String getName() {
		return "container.machineSILEX";
	}

	@Override
	public void update() {
		
		if(!world.isRemote) {

			FFUtils.fillFromFluidContainer(inventory, tank, 1, 2);
			
			loadFluid();
			
			if(!process()) {
				this.progress = 0;
			}
			
			dequeue();
			
			if(currentFill <= 0) {
				current = null;
			}
			
			NBTTagCompound data = new NBTTagCompound();
			data.setInteger("fill", currentFill);
			data.setInteger("progress", progress);
			data.setString("mode", mode.toString());
			
			if(this.current != null) {
				data.setInteger("item", Item.getIdFromItem(this.current.item));
				data.setInteger("meta", this.current.meta);
			}

			PacketDispatcher.wrapper.sendToAllAround(new FluidTankPacket(pos, tank), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 10));
			this.networkPack(data, 50);

			this.mode = EnumWavelengths.NULL;
		}
	}
	
	public void networkUnpack(NBTTagCompound nbt) {
		
		this.currentFill = nbt.getInteger("fill");
		this.progress = nbt.getInteger("progress");
		this.mode = EnumWavelengths.valueOf(nbt.getString("mode"));
		
		if(this.currentFill > 0) {
			this.current = new ComparableStack(Item.getItemById(nbt.getInteger("item")), 1, nbt.getInteger("meta"));
			
		} else {
			this.current = null;
		}
	}
	
	public void handleButtonPacket(int value, int meta) {
		
		this.currentFill = 0;
		this.current = null;
	}
	
	public int getProgressScaled(int i) {
		return (progress * i) / processTime;
	}
	
	public int getFluidScaled(int i) {
		return (tank.getFluidAmount() * i) / tank.getCapacity();
	}
	
	public int getFillScaled(int i) {
		return (currentFill * i) / maxFill;
	}
	
	public static final HashMap<Fluid, ComparableStack> fluidConversion = new HashMap<>();
	
	static {
		fluidConversion.put(ModForgeFluids.uf6, new NbtComparableStack(ItemFluidIcon.make(Fluids.UF6, 1)));
		fluidConversion.put(ModForgeFluids.puf6, new NbtComparableStack(ItemFluidIcon.make(Fluids.PUF6, 1)));
	}
	
	int loadDelay;
	
	public void loadFluid() {
		
		ComparableStack conv = fluidConversion.get(getTankType());
		
		if(conv != null) {
			
			if(currentFill == 0) {
				current = (ComparableStack) conv.copy();
			}
			
			if(current != null && current.equals(conv)) {
				
				int toFill = Math.min(10, Math.min(maxFill - currentFill, tank.getFluidAmount()));
				currentFill += toFill;
				tank.drain(toFill, true);
			}
		}
		
		loadDelay++;
		
		if(loadDelay > 20)
			loadDelay = 0;
		
		if(loadDelay == 0 && !inventory.getStackInSlot(0).isEmpty() && getTankType() == ModForgeFluids.acid && (this.current == null || this.current.equals(new ComparableStack(inventory.getStackInSlot(0)).makeSingular()))) {
			SILEXRecipe recipe = SILEXRecipes.getOutput(inventory.getStackInSlot(0));
			
			if(recipe == null)
				return;
			
			int load = recipe.fluidProduced;
			
			if(load <= maxFill - this.currentFill && load <= tank.getFluidAmount()) {
				this.currentFill += load;
				this.current = new ComparableStack(inventory.getStackInSlot(0)).makeSingular();
				tank.drain(load*3, true);
				inventory.getStackInSlot(0).shrink(1);
			}
		}
	}
	
	private boolean process() {
		
		if(current == null || currentFill <= 0)
			return false;
		
		SILEXRecipe recipe = SILEXRecipes.getOutput(current.toStack());
		
		if(recipe == null)
			return false;

		if(!(recipe.laserStrength.ordinal() <= this.mode.ordinal()))
			return false;
		
		if(currentFill < recipe.fluidConsumed)
			return false;
		
		if(!inventory.getStackInSlot(3).isEmpty())
			return false;

		progress += Math.pow(1.5, this.mode.ordinal()-recipe.laserStrength.ordinal());
		
		if(progress >= processTime) {
			
			currentFill -= recipe.fluidConsumed;
			
			ItemStack out = ((WeightedRandomObject)WeightedRandom.getRandomItem(world.rand, recipe.outputs)).asStack();
			inventory.setStackInSlot(3, out.copy());
			progress = 0;
			this.markDirty();
		}
		
		return true;
	}
	
	private void dequeue() {
		
		if(!inventory.getStackInSlot(3).isEmpty()) {
			
			for(int i = 4; i < 10; i++) {
				
				if(!inventory.getStackInSlot(i).isEmpty() && inventory.getStackInSlot(i).getCount() < inventory.getStackInSlot(i).getMaxStackSize() && InventoryUtil.doesStackDataMatch(inventory.getStackInSlot(3), inventory.getStackInSlot(i))) {
					inventory.getStackInSlot(i).grow(1);
					inventory.getStackInSlot(3).shrink(1);
					return;
				}
			}
			
			for(int i = 4; i < 10; i++) {
				
				if(inventory.getStackInSlot(i).isEmpty()) {
					inventory.setStackInSlot(i, inventory.getStackInSlot(3).copy());
					inventory.setStackInSlot(3, ItemStack.EMPTY);
					return;
				}
			}
		}
	}

	@Override
	public int[] getAccessibleSlotsFromSide(EnumFacing p_94128_1_) {
		return new int[] { 0, 4, 5, 6, 7, 8, 9 };
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemStack) {
		
		if(i == 0) return SILEXRecipes.getOutput(itemStack) != null;
		
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack itemStack, int side) {
		return slot >= 4;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.tank.readFromNBT(nbt.getCompoundTag("tank"));
		this.currentFill = nbt.getInteger("fill");
		
		if(this.currentFill > 0) {
			this.current = new ComparableStack(Item.getItemById(nbt.getInteger("item")), 1, nbt.getInteger("meta"));
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setTag("tank", this.tank.writeToNBT(new NBTTagCompound()));
		nbt.setInteger("fill", this.currentFill);
		nbt.setString("mode", mode.toString());
		
		if(this.current != null) {
			nbt.setInteger("item", Item.getIdFromItem(this.current.item));
			nbt.setInteger("meta", this.current.meta);
		}
		return nbt;
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(
				pos.getX() - 1,
				pos.getY(),
				pos.getZ() - 1,
				pos.getX() + 2,
				pos.getY() + 3,
				pos.getZ() + 2
			);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared()
	{
		return 65536.0D;
	}

	@Override
	public IFluidTankProperties[] getTankProperties(){
		return tank.getTankProperties();
	}

	@Override
	public int fill(FluidStack resource, boolean doFill){
		if(resource != null && (resource.getFluid() == ModForgeFluids.acid || fluidConversion.containsKey(resource.getFluid()))){
			return tank.fill(resource, doFill);
		}
		return 0;
	}

	@Override
	public FluidStack drain(FluidStack resource, boolean doDrain){
		return tank.drain(resource, doDrain);
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain){
		return tank.drain(maxDrain, doDrain);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing){
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing){
		if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY){
			return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this);
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public void recievePacket(NBTTagCompound[] tags){
		if(tags.length == 1){
			tank.readFromNBT(tags[0]);
		}
	}
}