package com.hbm.tileentity.machine;

import java.util.ArrayList;
import java.util.List;

import api.hbm.energymk2.IEnergyReceiverMK2;
import com.hbm.blocks.machine.MachineCharger;
import com.hbm.lib.ForgeDirection;
import com.hbm.tileentity.INBTPacketReceiver;
import com.hbm.tileentity.TileEntityLoadedBase;

import api.hbm.energymk2.IBatteryItem;
import net.minecraft.util.ITickable;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntityCharger extends TileEntityLoadedBase implements ITickable, IEnergyReceiverMK2, INBTPacketReceiver {
	
	public static final int range = 3;

	private List<EntityPlayer> players = new ArrayList();
	private long maxChargeRate;
	public long charge = 0;
	public long actualCharge = 0;
	public long totalCapacity = 0;
	public long totalEnergy = 0;
	private int lastOp = 0;

	public boolean isOn = false;
	public boolean pointingUp = true;

	@Override
	public void update() {
		ForgeDirection dir = ForgeDirection.getOrientation(this.getBlockMetadata()).getOpposite();
		
		if(!world.isRemote) {
			MachineCharger c = (MachineCharger)world.getBlockState(pos).getBlock();
			this.maxChargeRate = c.maxThroughput;
			this.pointingUp = c.pointingUp;

			this.trySubscribe(world, pos.getX() + dir.offsetX, pos.getY(), pos.getZ() + dir.offsetZ, dir);
			
			players = world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + (pointingUp ? range : -range), pos.getZ() + 1));
			
			totalCapacity = 0;
			totalEnergy = 0;
			charge = 0;
			
			for(EntityPlayer player : players) {
				InventoryPlayer inv = player.inventory;
				for(int i = 0; i < inv.getSizeInventory(); i ++){
					
					ItemStack stack = inv.getStackInSlot(i);
					if(stack != null && stack.getItem() instanceof IBatteryItem) {
						IBatteryItem battery = (IBatteryItem) stack.getItem();
						totalCapacity += battery.getMaxCharge();
						totalEnergy += battery.getCharge(stack);
						charge += Math.min(battery.getMaxCharge() - battery.getCharge(stack), battery.getChargeRate());
					}
				}
			}
			
			isOn = lastOp > 0;
			
			if(isOn) {
				lastOp--;
			}
			
			NBTTagCompound data = new NBTTagCompound();
			data.setBoolean("o", isOn);
			data.setBoolean("u", pointingUp);
			data.setLong("m", totalCapacity);
			data.setLong("v", totalEnergy);
			data.setLong("c", charge);
			data.setLong("a", actualCharge);
			INBTPacketReceiver.networkPack(this, data, 50);
			actualCharge = 0;
		}
	}

	@Override
	public void networkUnpack(NBTTagCompound nbt) {
		this.isOn = nbt.getBoolean("o");
		this.pointingUp = nbt.getBoolean("u");
		this.totalCapacity = nbt.getLong("m");
		this.totalEnergy = nbt.getLong("v");
		this.charge = nbt.getLong("c");
		this.actualCharge = nbt.getLong("a");
	}

	@Override
	public long getPower() {
		return 0;
	}

	@Override
	public long getMaxPower() {
		return Math.min(charge, maxChargeRate);
	}

	@Override
	public void setPower(long power) { }
	
	@Override
	public long transferPower(long power) {
		
		if(power == 0)
			return power;
		
		actualCharge = 0;
		long chargeBudget = maxChargeRate;
		for(EntityPlayer player : players) {
			InventoryPlayer inv = player.inventory;
			for(int i = 0; i < inv.getSizeInventory(); i ++){

				if(chargeBudget > 0 && power > 0){
					ItemStack stack = inv.getStackInSlot(i);
					
					if(stack != null && stack.getItem() instanceof IBatteryItem) {
						IBatteryItem battery = (IBatteryItem) stack.getItem();
						
						long toCharge = Math.min(battery.getMaxCharge() - battery.getCharge(stack), battery.getChargeRate());
						toCharge = Math.min(toCharge, chargeBudget);
						toCharge = Math.min(toCharge, power);
						battery.chargeBattery(stack, toCharge);
						power -= toCharge;
						actualCharge += toCharge;
						chargeBudget -= toCharge;
						
						lastOp = 4;
					}
				}
			}
		}
		
		return power;
	}
}
