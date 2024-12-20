package com.hbm.tileentity.machine;

import api.hbm.energymk2.IEnergyProviderMK2;
import com.hbm.blocks.BlockDummyable;

import com.hbm.util.ContaminationUtil;
import com.hbm.lib.Library;
import com.hbm.lib.ForgeDirection;
import com.hbm.packet.AuxElectricityPacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.tileentity.TileEntityLoadedBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityMachineRadGen extends TileEntityLoadedBase implements ITickable, IEnergyProviderMK2 {

	public ItemStackHandler inventory;

	public long power;
	public int fuel;
	public int strength;
	public int mode;
	public int soundCycle = 0;
	public float rotation;
	public static final long maxPower = 1000000;
	public static final int maxFuel = 1000;
	public static final int maxStrength = 10000;

	///private static final int[] slots_top = new int[] { 0 };
	///private static final int[] slots_bottom = new int[] { 0, 0 };
	///private static final int[] slots_side = new int[] { 0 };

	private String customName;
	
	public TileEntityMachineRadGen() {
		inventory = new ItemStackHandler(3){
			@Override
			protected void onContentsChanged(int slot) {
				markDirty();
				super.onContentsChanged(slot);
			}
		};
	}

	private static int[] accessibleSlots = new int[]{0};

	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.customName : "container.radGen";
	}

	public boolean hasCustomInventoryName() {
		return this.customName != null && this.customName.length() > 0;
	}


	public void setCustomName(String name) {
		this.customName = name;
	}
	
	public boolean isUseableByPlayer(EntityPlayer player) {
		if (world.getTileEntity(pos) != this) {
			return false;
		} else {
			return player.getDistanceSq(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D) <= 64;
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		this.power = compound.getLong("power");
		this.fuel = compound.getInteger("fuel");
		this.strength = compound.getInteger("strength");
		if(compound.hasKey("inventory"))
			inventory.deserializeNBT(compound.getCompoundTag("inventory"));
		super.readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setLong("power", power);
		compound.setInteger("fuel", fuel);
		compound.setInteger("strength", strength);
		compound.setTag("inventory", inventory.serializeNBT());
		return super.writeToNBT(compound);
	}

	public int[] getAccessibleSlotsFromSide(EnumFacing e) {
		return accessibleSlots;
	}

	public boolean isItemValidForSlot(int i, ItemStack stack) {
		return i == 0 && ContaminationUtil.getStackRads(stack) > 0;
	}
	
	@Override
	public void update() {
		if (!world.isRemote) {
			power = Library.chargeItemsFromTE(inventory, 2, power, maxPower);
			ForgeDirection dir = ForgeDirection.getOrientation(this.getBlockMetadata() - BlockDummyable.offset);
			this.tryProvide(world, this.pos.getX() - dir.offsetX * 4, this.pos.getY(), this.pos.getZ() - dir.offsetZ * 4, dir.getOpposite());
			int r = (int)Math.sqrt(ContaminationUtil.getStackRads(inventory.getStackInSlot(0)));
			if(r > 0) {
				if(inventory.getStackInSlot(0).getItem().hasContainerItem(inventory.getStackInSlot(0))) {
					if(inventory.getStackInSlot(1).isEmpty()) {
						if(fuel + r <= maxFuel) {
							
							inventory.setStackInSlot(1, new ItemStack(inventory.getStackInSlot(0).getItem().getContainerItem()));
							
							inventory.getStackInSlot(0).shrink(1);
							if(inventory.getStackInSlot(0).isEmpty())
								inventory.setStackInSlot(0, ItemStack.EMPTY);
							fuel += r;
						}
					} else if(inventory.getStackInSlot(0).getItem().getContainerItem() == inventory.getStackInSlot(1).getItem() && inventory.getStackInSlot(1).getCount() < inventory.getStackInSlot(1).getMaxStackSize()) {
						if(fuel + r <= maxFuel) {
							
							inventory.getStackInSlot(1).grow(1);
							
							inventory.getStackInSlot(0).shrink(1);
							if(inventory.getStackInSlot(0).isEmpty())
								inventory.setStackInSlot(0, ItemStack.EMPTY);
							fuel += r;
						}
					}
				} else {
					if(fuel + r <= maxFuel) {
						inventory.getStackInSlot(0).shrink(1);
						fuel += r;
					}
				}
			}

			if(fuel > maxFuel)
				fuel = maxFuel;
			
			if(fuel > 0) {
				fuel--;
				if(strength < maxStrength){
					strength = (int)(maxStrength * fuel / maxFuel);
				} else{
					strength = maxStrength;
				}
			} else {
				if(strength > 0)
					strength -= (int)(strength * 0.1);
			}

			if(strength > maxStrength)
				strength = maxStrength;

			if(strength < 0)
				strength = 0;
			
			power += strength;
			
			if(power > maxPower)
				power = maxPower;
			
			mode = 0;
			if(strength > 0)
				mode = 1;
			if(strength > 1000)
				mode = 2;
			
			PacketDispatcher.wrapper.sendToAllAround(new AuxElectricityPacket(pos.getX(), pos.getY(), pos.getZ(), power), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 20));
		}
	}
	
	public int getFuelScaled(int i) {
		return (fuel * i) / maxFuel;
	}
	
	public long getPowerScaled(long i) {
		return (power * i) / maxPower;
	}
	
	public int getStrengthScaled(int i) {
		return (int)(strength * i / maxStrength);
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return TileEntity.INFINITE_EXTENT_AABB;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared()
	{
		return 65536.0D;
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inventory);
		} else {
			return super.getCapability(capability, facing);
		}
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
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
