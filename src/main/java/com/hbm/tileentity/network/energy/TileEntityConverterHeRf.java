package com.hbm.tileentity.network.energy;

import java.lang.NoSuchMethodError;

import api.hbm.energymk2.IEnergyReceiverMK2;
import com.hbm.tileentity.TileEntityLoadedBase;
import com.hbm.config.GeneralConfig;
import com.hbm.lib.ForgeDirection;

import cofh.redstoneflux.api.IEnergyProvider;
import cofh.redstoneflux.api.IEnergyReceiver;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.common.Optional;

@Optional.InterfaceList({@Optional.Interface(iface = "cofh.redstoneflux.api.IEnergyProvider", modid = "redstoneflux")})
public class TileEntityConverterHeRf extends TileEntityLoadedBase implements ITickable, IEnergyReceiverMK2, IEnergyProvider, IEnergyStorage {

	//Thanks to the great people of Fusion Warfare for helping me with the original implementation of the RF energy API
	
	public TileEntityConverterHeRf() {
		super();
	}
	
	@Override
	public void update() {
		if (!world.isRemote) {
			for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
				this.trySubscribe(world, pos.getX() + dir.offsetX, pos.getY() + dir.offsetY, pos.getZ() + dir.offsetZ, dir);
		}
	}
	//RF
	@Override
	public boolean canConnectEnergy(EnumFacing from) {
		return true;
	}

	@Override
	public int extractEnergy(EnumFacing from, int maxExtract, boolean simulate) {
		return 0;
	}

	@Override
	public int getEnergyStored(EnumFacing from) {
		return 0;
	}

	@Override
	public int getMaxEnergyStored(EnumFacing from) {
		return 0;
	}

	private boolean recursionBrake = false;

	@Optional.Method(modid="redstoneflux")
	public int transferToRFMachine(TileEntity entity, int rf, EnumFacing dir){
		if(entity != null && entity instanceof IEnergyReceiver) {
				
			IEnergyReceiver receiver = (IEnergyReceiver) entity;
			return receiver.receiveEnergy(dir, rf, false);
		}
		return 0;
	}

	public int transferToFEMachine(TileEntity entity, int fe, EnumFacing dir){
		if(entity != null && entity.hasCapability(CapabilityEnergy.ENERGY, dir)) {
			
			IEnergyStorage storage = entity.getCapability(CapabilityEnergy.ENERGY, dir);
			if(storage.canReceive()){
				return storage.receiveEnergy(fe, false);
			}
		}
		return 0;
	}

	//NTM
	@Override
	public long transferPower(long power) {
		
		if(recursionBrake)
			return power;
		
		recursionBrake = true;
		
		// we have to limit the transfer amount because otherwise FEnSUs would overflow the RF output, twice
		int toRF = (int) Math.min(Integer.MAX_VALUE, power*GeneralConfig.conversionRateHeToRF);
		int transfer = 0;
		int totalTransferred = 0;
		boolean skipRF = false;
		for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {

			TileEntity entity = world.getTileEntity(pos.add(dir.offsetX, dir.offsetY, dir.offsetZ));
			if(!skipRF){
				try{
					transfer = transferToRFMachine(entity, toRF, dir.getOpposite().toEnumFacing());
					totalTransferred += transfer;
					toRF -= transfer; //to prevent energy duping
				} catch(NoSuchMethodError e){
					skipRF = true;
				} //RF not
			}

			transfer = transferToFEMachine(entity, toRF, dir.getOpposite().toEnumFacing());
			totalTransferred += transfer;
			toRF -= transfer; //to prevent energy duping
		}

		recursionBrake = false;
		lastTransfer = (long)(totalTransferred / GeneralConfig.conversionRateHeToRF);
		
		return power - (long)(totalTransferred / GeneralConfig.conversionRateHeToRF);
	}
	
	@Override
	public long getPower() {
		return 0;
	}
	@Override public void setPower(long power) { }

	@Override
	public long getMaxPower() {
		return (long)(Integer.MAX_VALUE / GeneralConfig.conversionRateHeToRF);
	}

	private long lastTransfer = 0;

	@Override
	public long getReceiverSpeed() {

		if(lastTransfer > 0) {
			return lastTransfer * 2;
		} else {
			return getMaxPower();
		}
	}

	//FE
	@Override
	public boolean canExtract(){
		return true;
	}

	@Override
	public boolean canReceive(){
		return false;
	}

	@Override
	public int getMaxEnergyStored(){
		return Integer.MAX_VALUE;
	}

	@Override
	public int getEnergyStored(){
		return 0;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate){
		return 0;
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate){
		return 0;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing){
		if(capability == CapabilityEnergy.ENERGY){
			return true;
		}
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing){
		if(capability == CapabilityEnergy.ENERGY){
			return (T) this;
		}
		return super.getCapability(capability, facing);
	}
}
