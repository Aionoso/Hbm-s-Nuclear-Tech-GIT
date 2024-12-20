package com.hbm.tileentity.machine;

import api.hbm.energymk2.IEnergyProviderMK2;
import com.hbm.interfaces.IControlReceiver;
import com.hbm.forgefluid.FFUtils;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.interfaces.ITankPacketAcceptor;
import com.hbm.inventory.SAFERecipes;
import com.hbm.items.ModItems;
import com.hbm.items.machine.ItemFWatzCore;
import com.hbm.lib.Library;
import com.hbm.world.FWatz;
import com.hbm.tileentity.INBTPacketReceiver;
import com.hbm.tileentity.TileEntityLoadedBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityFWatzCore extends TileEntityLoadedBase implements IControlReceiver, ITickable, IEnergyProviderMK2, IFluidHandler, ITankPacketAcceptor, INBTPacketReceiver {

	public long power;
	public final static long maxPower = 1000000000000L;
	public boolean cooldown = false;

	public FluidTank tanks[];
	public Fluid[] tankTypes;
	public boolean needsUpdate;
	public boolean isOn = false;

	public ItemStackHandler inventory;

	private String customName;

	public TileEntityFWatzCore() {
		inventory = new ItemStackHandler(7) {
			@Override
			protected void onContentsChanged(int slot) {
				markDirty();
				super.onContentsChanged(slot);
			}
		};
		tanks = new FluidTank[3];
		tankTypes = new Fluid[3];
		tanks[0] = new FluidTank(128000);
		tankTypes[0] = ModForgeFluids.coolant;
		tanks[1] = new FluidTank(64000);
		tankTypes[1] = ModForgeFluids.amat;
		tanks[2] = new FluidTank(64000);
		tankTypes[2] = ModForgeFluids.aschrab;
		needsUpdate = false;
	}

	@Override
	public boolean hasPermission(EntityPlayer player){
		return true;
	}
	
	@Override
	public void receiveControl(NBTTagCompound data){
		this.isOn = !this.isOn;
		this.markDirty();
	}

	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.customName : "container.fusionaryWatzPlant";
	}

	public boolean hasCustomInventoryName() {
		return this.customName != null && this.customName.length() > 0;
	}

	public void setCustomName(String name) {
		this.customName = name;
	}

	public boolean isUseableByPlayer(EntityPlayer player) {
		if(world.getTileEntity(pos) != this) {
			return false;
		} else {
			return true;
		}
	}

	public int getSingularityType(){
		Item item = inventory.getStackInSlot(2).getItem();
		if(item instanceof ItemFWatzCore){
			return ((ItemFWatzCore)item).type;
		}
		return 0;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		power = compound.getLong("power");
		isOn = compound.getBoolean("isOn");
		tankTypes[0] = ModForgeFluids.coolant;
		tankTypes[1] = ModForgeFluids.amat;
		tankTypes[2] = ModForgeFluids.aschrab;
		if(compound.hasKey("tanks"))
			FFUtils.deserializeTankArray(compound.getTagList("tanks", 10), tanks);
		if(compound.hasKey("inventory"))
			inventory.deserializeNBT(compound.getCompoundTag("inventory"));
		super.readFromNBT(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setLong("power", power);
		compound.setBoolean("isOn", isOn);
		compound.setTag("inventory", inventory.serializeNBT());
		compound.setTag("tanks", FFUtils.serializeTankArray(tanks));
		return super.writeToNBT(compound);
	}

	@Override
	public void update() {
		if(!world.isRemote && this.isStructureValid(this.world)) {

			sendSAFEPower();

			if(this.isRunning()){
				ItemStack stack = inventory.getStackInSlot(2);
				if(stack.getItem() == ModItems.meteorite_sword_baleful){
					inventory.setStackInSlot(2, new ItemStack(ModItems.meteorite_sword_warped));
				} else if(stack.hasTagCompound()){
					NBTTagCompound nbt = stack.getTagCompound();
					if(nbt.getBoolean("ntmContagion")) nbt.removeTag("ntmContagion");
					if(nbt.hasNoTags()) stack.setTagCompound(null);
				}
			}

			if(this.isOn && inventory.getStackInSlot(2).getItem() instanceof ItemFWatzCore) {
				ItemFWatzCore itemCore = (ItemFWatzCore)inventory.getStackInSlot(2).getItem();
				if(cooldown) {
					
					tanks[0].fill(new FluidStack(tankTypes[0], itemCore.coolantRefill), true);

					if(tanks[0].getFluidAmount() >= tanks[0].getCapacity()) {
						cooldown = false;
					}

				} else {

					if(tanks[1].getFluidAmount() > itemCore.amatDrain && tanks[2].getFluidAmount() > itemCore.aschrabDrain) {
						tanks[0].drain(itemCore.coolantDrain, true);
						tanks[1].drain(itemCore.amatDrain, true);
						tanks[2].drain(itemCore.aschrabDrain, true);
						needsUpdate = true;
						power += itemCore.powerOutput;
					}

					if(power > maxPower)
						power = maxPower;

					if(tanks[0].getFluidAmount() <= 0) {
						cooldown = true;
					}

					if(world.rand.nextInt(4096) == 0)
						tryGrowCore();
				}
			}

			if(power > maxPower)
				power = maxPower;

			power = Library.chargeItemsFromTE(inventory, 0, power, maxPower);

			if(this.inputValidForTank(1, 3))
				if(FFUtils.fillFromFluidContainer(inventory, tanks[1], 3, 5))
					needsUpdate = true;
			if(this.inputValidForTank(2, 4))
				if(FFUtils.fillFromFluidContainer(inventory, tanks[2], 4, 6))
					needsUpdate = true;
			if(needsUpdate) {
				needsUpdate = false;
				this.markDirty();
			}

			if(this.isRunning() && (tanks[1].getFluidAmount() <= 0 || tanks[2].getFluidAmount() <= 0 || !isOn || !(inventory.getStackInSlot(2).getItem() instanceof ItemFWatzCore)) || cooldown || !this.isStructureValid(world))
				this.emptyPlasma();

			if(!this.isRunning() && tanks[1].getFluidAmount() >= 100 && tanks[2].getFluidAmount() >= 100 && isOn && inventory.getStackInSlot(2).getItem() instanceof ItemFWatzCore && !cooldown && this.isStructureValid(world))
				this.fillPlasma();

			NBTTagCompound data = new NBTTagCompound();
			data.setLong("power", power);
			data.setTag("tanks", FFUtils.serializeTankArray(tanks));
			data.setBoolean("isOn", isOn);
			INBTPacketReceiver.networkPack(this, data, 50);
		}
	}

	@Override
	public void networkUnpack(NBTTagCompound data) {
		this.power = data.getLong("power");
		this.isOn = data.getBoolean("isOn");
		if(data.hasKey("tanks"))
			FFUtils.deserializeTankArray(data.getTagList("tanks", 10), tanks);
	}

	private void sendSAFEPower(){
		this.tryProvide(world, pos.getX() + 7, pos.getX() -1, pos.getZ(), Library.POS_X);
		this.tryProvide(world, pos.getX() -7, pos.getX() -1, pos.getZ(), Library.NEG_X);
		this.tryProvide(world, pos.getX(), pos.getX() -1, pos.getZ() + 7, Library.POS_Z);
		this.tryProvide(world, pos.getX(), pos.getX() -1, pos.getZ() - 7, Library.NEG_Z);
	}

	private void tryGrowCore(){
		ItemStack output = SAFERecipes.getOutput(inventory.getStackInSlot(2));
		if(output != null){
			inventory.setStackInSlot(2, output.copy());
		}
	}

	public boolean isStructureValid(World world) {
		return FWatz.checkHull(world, pos);
	}

	public long getPowerScaled(long i) {
		return (power / 100 * i) / (maxPower / 100);
	}

	public void fillPlasma() {
		if(!this.world.isRemote)
			FWatz.fillPlasma(world, pos);
	}

	public void emptyPlasma() {
		if(!this.world.isRemote)
			FWatz.emptyPlasma(world, pos);
	}

	public boolean isRunning() {
		return FWatz.getPlasma(world, pos) && this.isStructureValid(world);
	}

	protected boolean inputValidForTank(int tank, int slot) {
		if(tanks[tank] != null) {
			if(inventory.getStackInSlot(slot).getItem() == ModItems.fluid_barrel_infinite || isValidFluidForTank(tank, FluidUtil.getFluidContained(inventory.getStackInSlot(slot)))) {
				return true;
			}
		}
		return false;
	}

	private boolean isValidFluidForTank(int tank, FluidStack stack) {
		if(stack == null || tanks[tank] == null)
			return false;
		return stack.getFluid() == tankTypes[tank];
	}

	@Override
	public IFluidTankProperties[] getTankProperties() {
		return new IFluidTankProperties[]{tanks[0].getTankProperties()[0], tanks[1].getTankProperties()[0], tanks[2].getTankProperties()[0]};
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		if(resource == null) {
			return 0;
		} else if(resource.getFluid() == tankTypes[0]) {
			needsUpdate = true;
			return tanks[0].fill(resource, doFill);
		} else if(resource.getFluid() == tankTypes[1]) {
			needsUpdate = true;
			return tanks[1].fill(resource, doFill);
		} else if(resource.getFluid() == tankTypes[2]) {
			needsUpdate = true;
			return tanks[2].fill(resource, doFill);
		} else {
			return 0;
		}
	}

	@Override
	public FluidStack drain(FluidStack resource, boolean doDrain) {
		return null;
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		return null;
	}

	@Override
	public void recievePacket(NBTTagCompound[] tags) {
		if(tags.length != 3) {
			return;
		} else {
			tanks[0].readFromNBT(tags[0]);
			tanks[1].readFromNBT(tags[1]);
			tanks[2].readFromNBT(tags[2]);
		}
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY ? CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this) : super.getCapability(capability, facing);
	}
	
	public long getPower() {
		return power;
	}

	public void setPower(long i) {
		power = i;
	}

	public long getMaxPower() {
		return maxPower;
	}
}
