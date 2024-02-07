package com.hbm.tileentity.machine.rbmk;

import java.util.HashMap;

import com.hbm.blocks.ModBlocks;
import com.hbm.entity.projectile.EntityRBMKDebris.DebrisType;
import com.hbm.forgefluid.FFUtils;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.interfaces.ITankPacketAcceptor;
import com.hbm.inventory.RecipesCommon.ComparableStack;
import com.hbm.inventory.RBMKOutgasserRecipes;
import com.hbm.util.ContaminationUtil;
import com.hbm.items.ModItems;
import com.hbm.items.machine.ItemFluidIcon;
import com.hbm.packet.FluidTankPacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.tileentity.machine.rbmk.TileEntityRBMKConsole.ColumnType;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

public class TileEntityRBMKOutgasser extends TileEntityRBMKSlottedBase implements IRBMKFluxReceiver, IFluidHandler, ITankPacketAcceptor, IRBMKLoadable {

	public FluidTank gas;
	public Fluid gasType;
	public double progress = 0;
	public double usedFlux = 0;
	public int duration = 10000;

	public TileEntityRBMKOutgasser() {
		super(2);
		gas = new FluidTank(64000);
		gasType = ModForgeFluids.tritium;
	}

	@Override
	public String getName() {
		return "container.rbmkOutgasser";
	}
	
	@Override
	public void update() {
		
		if(!world.isRemote) {
			PacketDispatcher.wrapper.sendToAllAround(new FluidTankPacket(pos, gas), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 50));
			NBTTagCompound type = new NBTTagCompound();
			type.setString("gasType", gasType.getName());
			networkPack(type, 50);
			
			if(world.getTotalWorldTime() % 10 == 0)
				fillFluidInit(gas);
			
			if(!canProcess()) {
				this.progress = 0;
			}
		}
		
		super.update();
	}
	
	@Override
	public void networkUnpack(NBTTagCompound nbt){
		if(nbt.hasKey("steamType")){
			this.gasType = FluidRegistry.getFluid(nbt.getString("gasType"));
		} else {
			super.networkUnpack(nbt);
		}
	}

	@Override
	public void receiveFlux(NType type, double flux) {
		
		if(canProcess()) {
			
			if(type == NType.FAST)
				flux *= 0.2D;
			
			progress += flux * RBMKDials.getOutgasserMod(world);
			
			if(progress > duration) {
				process();
				this.markDirty();
			}
		} else if(!inventory.getStackInSlot(0).isEmpty()){
			if(type == NType.FAST)
				flux *= 0.2D;
			ContaminationUtil.neutronActivateItem(inventory.getStackInSlot(0), (float)(flux * 0.001), 1F);
			this.markDirty();
		}
		this.usedFlux = flux;
	}
	
	
	
	private boolean canProcess() {
		
		if(inventory.getStackInSlot(0).isEmpty())
			return false;
		
		int requiredFlux = RBMKOutgasserRecipes.getRequiredFlux(inventory.getStackInSlot(0));
		if (requiredFlux == -1)
			return false;
		duration = requiredFlux;

		ItemStack output = RBMKOutgasserRecipes.getOutput(inventory.getStackInSlot(0));
		if(output.getItem() == ModItems.fluid_icon) {
			return ItemFluidIcon.getFluid(output) == gasType && gas.getFluidAmount() + ItemFluidIcon.getQuantity(output) <= gas.getCapacity();
		}
		
		if(inventory.getStackInSlot(1).isEmpty())
			return true;
		
		return inventory.getStackInSlot(1).getItem() == output.getItem() && inventory.getStackInSlot(1).getItemDamage() == output.getItemDamage() && inventory.getStackInSlot(1).getCount() + output.getCount() <= inventory.getStackInSlot(1).getMaxStackSize();
	}

	
	private void process() {
		
		ItemStack output = RBMKOutgasserRecipes.getOutput(inventory.getStackInSlot(0));
		inventory.getStackInSlot(0).shrink(1);
		this.progress = 0;
		
		if(output.getItem() == ModItems.fluid_icon) {
			gas.fill(new FluidStack(gasType, ItemFluidIcon.getQuantity(output)), true);
			return;
		}
		
		if(inventory.getStackInSlot(1).isEmpty()) {
			inventory.setStackInSlot(1, output.copy());
		} else {
			inventory.getStackInSlot(1).grow(output.getCount());
		}
	}

	public void fillFluidInit(FluidTank tank) {
		fillFluid(this.pos.getX(), this.pos.getY() + RBMKDials.getColumnHeight(world) + 1, this.pos.getZ(), tank);
		
		if(world.getBlockState(pos.down()) == ModBlocks.rbmk_loader) {

			fillFluid(this.pos.getX() + 1, this.pos.getY() - 1, this.pos.getZ(), tank);
			fillFluid(this.pos.getX() - 1, this.pos.getY() - 1, this.pos.getZ(), tank);
			fillFluid(this.pos.getX(), this.pos.getY() - 1, this.pos.getZ() + 1, tank);
			fillFluid(this.pos.getX(), this.pos.getY() - 1, this.pos.getZ() - 1, tank);
			fillFluid(this.pos.getX(), this.pos.getY() - 2, this.pos.getZ(), tank);
		}
		
		if(world.getBlockState(pos.down(2)) == ModBlocks.rbmk_loader) {

			fillFluid(this.pos.getX() + 1, this.pos.getY() - 2, this.pos.getZ(), tank);
			fillFluid(this.pos.getX() - 1, this.pos.getY() - 2, this.pos.getZ(), tank);
			fillFluid(this.pos.getX(), this.pos.getY() - 2, this.pos.getZ() + 1, tank);
			fillFluid(this.pos.getX(), this.pos.getY() - 2, this.pos.getZ() - 1, tank);
			fillFluid(this.pos.getX(), this.pos.getY() - 1, this.pos.getZ(), tank);
			fillFluid(this.pos.getX(), this.pos.getY() - 3, this.pos.getZ(), tank);
		}
	}

	public void fillFluid(int x, int y, int z, FluidTank tank) {
		FFUtils.fillFluid(this, tank, world, new BlockPos(x, y, z), tank.getCapacity());
	}
	
	
	@Override
	public void onMelt(int reduce) {
		
		int count = 4 + world.rand.nextInt(2);
		
		for(int i = 0; i < count; i++) {
			spawnDebris(DebrisType.BLANK);
		}
		
		super.onMelt(reduce);
	}

	@Override
	public ColumnType getConsoleType() {
		return ColumnType.OUTGASSER;
	}

	@Override
	public NBTTagCompound getNBTForConsole() {
		NBTTagCompound data = new NBTTagCompound();
		data.setInteger("gas", this.gas.getFluidAmount());
		data.setInteger("maxGas", this.gas.getCapacity());
		data.setDouble("usedFlux", this.usedFlux);
		data.setDouble("progress", this.progress);
		data.setDouble("maxProgress", this.duration);
		return data;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		
		this.progress = nbt.getDouble("progress");
		this.duration = nbt.getInteger("duration");
		this.gas.readFromNBT(nbt.getCompoundTag("gas"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		
		nbt.setDouble("progress", this.progress);
		nbt.setInteger("duration", this.duration);
		nbt.setTag("gas", gas.writeToNBT(new NBTTagCompound()));
		
		return nbt;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemStack) {
		return RBMKOutgasserRecipes.getOutput(itemStack) != null && i == 0;
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemStack, int j) {
		return i == 1;
	}

	@Override
	public void recievePacket(NBTTagCompound[] tags){
		if(tags.length == 1){
			gas.readFromNBT(tags[0]);
		}
	}

	@Override
	public IFluidTankProperties[] getTankProperties(){
		return gas.getTankProperties();
	}

	@Override
	public int fill(FluidStack resource, boolean doFill){
		return 0;
	}

	@Override
	public FluidStack drain(FluidStack resource, boolean doDrain){
		return gas.drain(resource, doDrain);
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain){
		return gas.drain(maxDrain, doDrain);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing){
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing){
		if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this);
		return super.getCapability(capability, facing);
	}

	@Override
	public boolean canLoad(ItemStack toLoad) {
		return toLoad != null && inventory.getStackInSlot(0).isEmpty();
	}

	@Override
	public void load(ItemStack toLoad) {
		inventory.setStackInSlot(0, toLoad.copy());
		this.markDirty();
	}

	@Override
	public boolean canUnload() {
		return !inventory.getStackInSlot(1).isEmpty();
	}

	@Override
	public ItemStack provideNext() {
		return inventory.getStackInSlot(1);
	}

	@Override
	public void unload() {
		inventory.setStackInSlot(1, ItemStack.EMPTY);
		this.markDirty();
	}
}