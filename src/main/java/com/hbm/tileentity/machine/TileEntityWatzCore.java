package com.hbm.tileentity.machine;

import java.util.Random;

import api.hbm.energymk2.IEnergyProviderMK2;
import com.hbm.blocks.ModBlocks;
import com.hbm.config.BombConfig;
import com.hbm.entity.logic.EntityNukeExplosionMK3;
import com.hbm.forgefluid.FFUtils;
import com.hbm.forgefluid.ModForgeFluids;
import com.hbm.interfaces.ITankPacketAcceptor;
import com.hbm.items.ModItems;
import com.hbm.items.machine.ItemCapacitor;
import com.hbm.items.special.WatzFuel;
import com.hbm.lib.Library;
import com.hbm.lib.ForgeDirection;
import com.hbm.world.Watz;
import com.hbm.packet.AuxElectricityPacket;
import com.hbm.packet.FluidTankPacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.tileentity.TileEntityLoadedBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityWatzCore extends TileEntityLoadedBase implements ITickable, IEnergyProviderMK2, IFluidHandler, ITankPacketAcceptor {

	public long power;
	public final static long maxPower = 1000000000;
	public int heat;
	
	public int heatMultiplier;
	public int powerMultiplier;
	public int decayMultiplier;
	
	public int heatList;
	public int wasteList;
	public int powerList;
	public int age = 0;
	Random rand = new Random();
	
	public ItemStackHandler inventory;
	public FluidTank tank;
	public Fluid tankType;
	public boolean needsUpdate;
	
	private String customName;
	
	public TileEntityWatzCore() {
		inventory = new ItemStackHandler(40){
			@Override
			protected void onContentsChanged(int slot) {
				markDirty();
				super.onContentsChanged(slot);
			}
		};
		tank = new FluidTank(64000);
		tankType = ModForgeFluids.watz;
		needsUpdate = false;
	}
	
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.customName : "container.watzPowerplant";
	}

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
			return true;
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		power = compound.getLong("power");
		tank.readFromNBT(compound);
		tankType = ModForgeFluids.watz;
		if(compound.hasKey("inventory"))
			inventory.deserializeNBT(compound.getCompoundTag("inventory"));
		super.readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setLong("power", power);
		tank.writeToNBT(compound);
		compound.setTag("inventory", inventory.serializeNBT());
		return super.writeToNBT(compound);
	}
	
	@Override
	public void update() {
		if (!world.isRemote && this.isStructureValid(this.world)) {

			age++;
			if (age >= 20) {
				age = 0;
			}

			if (age == 9 || age == 19) {
				fillFluidInit(tank);
			}
			this.sendWatzPower();

			powerMultiplier = 100;
			heatMultiplier = 100;
			decayMultiplier = 100;
			powerList = 0;
			heatList = 0;
			heat = 0;

			if (hasFuse()) {
				
				//Adds power and heat
				for (int i = 0; i < 36; i++) {
					surveyPellet(inventory.getStackInSlot(i));
				}
				//Calculates modifiers
				for (int i = 0; i < 36; i++) {
					surveyPelletAgain(inventory.getStackInSlot(i));
				}
				//Decays pellet by (DECAYMULTIPLIER * DEFAULTDECAY=100)/100 ticks
				for (int i = 0; i < 36; i++) {
					decayPellet(i);
				}
			}

			//Only damages filter when heat is present (thus waste being created)
			if (heatList > 0) {
				ItemCapacitor.setDura(inventory.getStackInSlot(38), ItemCapacitor.getDura(inventory.getStackInSlot(38)) - 1);
				markDirty();
			}

			heatList *= heatMultiplier;
			heatList /= 100;
			heat = heatList;

			powerList *= powerMultiplier;
			powerList /= 100;
			power += powerList;

			tank.fill(new FluidStack(tankType, ((decayMultiplier * heat) / 100) / 100), true);
			needsUpdate = true;
			
			if(power > maxPower)
				power = maxPower;
			
			//Gets rid of 1/4 of the total waste, if at least one access hatch is not occupied
			if(tank.getFluidAmount() >= tank.getCapacity())
				emptyWaste();
			
			power = Library.chargeItemsFromTE(inventory, 37, power, maxPower);
			
			if(FFUtils.fillFluidContainer(inventory, tank, 36, 39))
				needsUpdate = true;

			if(needsUpdate){
				//Removed you because selectively sending packets is buggy. Really this should only be sending packets when a gui is open, but whatever.
				needsUpdate = false;
			}
			
			PacketDispatcher.wrapper.sendToAllAround(new FluidTankPacket(pos, new FluidTank[]{tank}), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 20));
			PacketDispatcher.wrapper.sendToAllAround(new AuxElectricityPacket(pos, power), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 20));
		}
	}

	public boolean isStructureValid(World world) {
		return Watz.checkHull(world, pos);
	}

	public boolean hasFuse() {
		return inventory.getStackInSlot(38).getItem() == ModItems.titanium_filter && ItemCapacitor.getDura(inventory.getStackInSlot(38)) > 0;
	}

	public long getPowerScaled(long i) {
		return (power/100 * i) / (maxPower/100);
	}
	
	public void surveyPellet(ItemStack stack) {
		if(stack != null && stack.getItem() instanceof WatzFuel)
		{
			WatzFuel fuel = (WatzFuel)stack.getItem();
			this.powerList += fuel.power;
			this.heatList += fuel.heat;
		}
	}
	
	public void surveyPelletAgain(ItemStack stack) {
		if(stack.getItem() instanceof WatzFuel)
		{
			WatzFuel fuel = (WatzFuel)stack.getItem();
			this.powerMultiplier *= fuel.powerMultiplier;
			this.heatMultiplier *= fuel.heatMultiplier;
			this.decayMultiplier *= fuel.decayMultiplier;
		}
	}
	
	public void decayPellet(int i) {
		if(inventory.getStackInSlot(i).getItem() instanceof WatzFuel)
		{
			WatzFuel fuel = (WatzFuel)inventory.getStackInSlot(i).getItem();
			WatzFuel.setLifeTime(inventory.getStackInSlot(i), WatzFuel.getLifeTime(inventory.getStackInSlot(i)) + this.decayMultiplier);
			WatzFuel.updateDamage(inventory.getStackInSlot(i));
			if(WatzFuel.getLifeTime(inventory.getStackInSlot(i)) >= fuel.lifeTime)
			{
				if(inventory.getStackInSlot(i).getItem() == ModItems.pellet_lead)
					inventory.setStackInSlot(i, new ItemStack(ModItems.powder_lead));
				else
					inventory.setStackInSlot(i, new ItemStack(ModItems.pellet_lead));
			}
		}
	}
	
	public void emptyWaste() {
		MutableBlockPos mPos = new BlockPos.MutableBlockPos();
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		tank.drain(tank.getFluidAmount() / 4, true);
		needsUpdate = true;
		if (!world.isRemote) {
			if (this.world.getBlockState(mPos.setPos(x + 4, y, z)).getBlock() == Blocks.AIR)
			{
				this.world.setBlockState(mPos.setPos(x + 4, y, z), ModBlocks.mud_block.getDefaultState());
				this.world.playSound(null, x, y, z, SoundEvents.ENTITY_GENERIC_SPLASH, SoundCategory.BLOCKS, 3.0F, 0.5F);
			}
			else if (this.world.getBlockState(mPos.setPos(x - 4, y, z)).getBlock() == Blocks.AIR)
			{
				this.world.setBlockState(mPos.setPos(x - 4, y, z), ModBlocks.mud_block.getDefaultState());
				this.world.playSound(null, x, y, z, SoundEvents.ENTITY_GENERIC_SPLASH, SoundCategory.BLOCKS, 3.0F, 0.5F);
			}
			else if (this.world.getBlockState(mPos.setPos(x, y, z + 4)).getBlock() == Blocks.AIR)
			{
				this.world.setBlockState(mPos.setPos(x, y, z + 4), ModBlocks.mud_block.getDefaultState());
				this.world.playSound(null, x, y, z, SoundEvents.ENTITY_GENERIC_SPLASH, SoundCategory.BLOCKS, 3.0F, 0.5F);
			}
			else if (this.world.getBlockState(mPos.setPos(x, y, z - 4)).getBlock() == Blocks.AIR)
			{
				this.world.setBlockState(mPos.setPos(x, y, z - 4), ModBlocks.mud_block.getDefaultState());
				this.world.playSound(null, x, y, z, SoundEvents.ENTITY_GENERIC_SPLASH, SoundCategory.BLOCKS, 3.0F, 0.5F);
			}
			else {
				if (rand.nextInt(10) != 0) {
					for (int i = -3; i <= 3; i++)
						for (int j = -5; j <= 5; j++)
							for (int k = -3; k <= 3; k++)
								if (rand.nextInt(2) == 0)
									this.world.setBlockState(mPos.setPos(x + i, y + j, z + k), ModBlocks.mud_block.getDefaultState());
					this.world.setBlockState(pos, ModBlocks.mud_block.getDefaultState());
					this.world.playSound(null, x, y, z, SoundEvents.ENTITY_GENERIC_SPLASH, SoundCategory.BLOCKS, 3.0F, 0.5F);
					this.world.playSound(null, x, y, z, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 3.0F, 0.75F);
				} else {
					EntityNukeExplosionMK3 entity = new EntityNukeExplosionMK3(world);
					entity.posX = x;
					entity.posY = y;
					entity.posZ = z;
					entity.destructionRange = BombConfig.fleijaRadius;
					entity.speed = 25;
					entity.coefficient = 1.0F;
					entity.waste = false;
	    	
					world.spawnEntity(entity);
				}
			}
		}
	}

	public void sendWatzPower() {
		this.tryProvide(world, pos.getX(), pos.getY() + 7, pos.getZ(), ForgeDirection.UP);
		this.tryProvide(world, pos.getX(), pos.getY() - 7, pos.getZ(), ForgeDirection.DOWN);
	}

	public void fillFluidInit(FluidTank tank) {
		needsUpdate = FFUtils.fillFluid(this, tank, world, pos.add(4, 0, 0), 16000) || needsUpdate;
		needsUpdate = FFUtils.fillFluid(this, tank, world, pos.add(-4, 0, 0), 16000) || needsUpdate;
		needsUpdate = FFUtils.fillFluid(this, tank, world, pos.add(0, 0, 4), 16000) || needsUpdate;
		needsUpdate = FFUtils.fillFluid(this, tank, world, pos.add(0, 0, -4), 16000) || needsUpdate;
	}

	@Override
	public IFluidTankProperties[] getTankProperties() {
		return new IFluidTankProperties[]{tank.getTankProperties()[0]};
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		return 0;
	}

	@Override
	public FluidStack drain(FluidStack resource, boolean doDrain) {
		if(resource != null && resource.getFluid() == tankType){
			needsUpdate = true;
			return tank.drain(resource.amount, doDrain);
		} else {
			return null;
		}
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		if(tank.getFluidAmount() > 0){
			needsUpdate = true;
			return tank.drain(maxDrain, doDrain);
		} else {
			return null;
		}
	}

	@Override
	public void recievePacket(NBTTagCompound[] tags) {
		if(tags.length != 1){
			return;
		} else {
			tank.readFromNBT(tags[0]);
		}
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY ? CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this) : super.getCapability(capability, facing);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}

	@Override
	public long getPower() {
		return power;
	}

	@Override
	public void setPower(long i) {
		power = i;
	}

	@Override
	public long getMaxPower() {
		return maxPower;
	}
}
