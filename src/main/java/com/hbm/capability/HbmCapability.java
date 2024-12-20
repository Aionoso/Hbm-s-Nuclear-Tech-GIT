package com.hbm.capability;

import java.util.concurrent.Callable;

import com.hbm.handler.HbmKeybinds.EnumKeybind;
import com.hbm.main.MainRegistry;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class HbmCapability {

	public interface IHBMData {
		public boolean getKeyPressed(EnumKeybind key);
		public void setKeyPressed(EnumKeybind key, boolean pressed);
		public boolean getEnableBackpack();
		public boolean getEnableHUD();
		public boolean getOnLadder();
		public void setEnableBackpack(boolean b);
		public void setEnableHUD(boolean b);
		public void setOnLadder(boolean b);
		
		public default boolean isJetpackActive() {
			return getEnableBackpack() && getKeyPressed(EnumKeybind.JETPACK);
		}
	}
	
	public static class HBMData implements IHBMData {

		public static final Callable<IHBMData> FACTORY = () -> {return new HBMData();};
		
		private boolean[] keysPressed = new boolean[EnumKeybind.values().length];
		
		public boolean enableBackpack = true;
		public boolean enableHUD = true;
		public boolean isOnLadder = false;
		
		@Override
		public boolean getKeyPressed(EnumKeybind key) {
			return keysPressed[key.ordinal()];
		}

		@Override
		public void setKeyPressed(EnumKeybind key, boolean pressed) {
			if(!getKeyPressed(key) && pressed) {
				
				if(key == EnumKeybind.TOGGLE_JETPACK) {
					this.enableBackpack = !this.enableBackpack;
					
					if(this.enableBackpack)
						MainRegistry.proxy.displayTooltip(TextFormatting.GREEN + "Jetpack ON");
					else
						MainRegistry.proxy.displayTooltip(TextFormatting.RED + "Jetpack OFF");
				}
				if(key == EnumKeybind.TOGGLE_HEAD) {
					this.enableHUD = !this.enableHUD;
					
					if(this.enableHUD)
						MainRegistry.proxy.displayTooltip(TextFormatting.GREEN + "HUD ON");
					else
						MainRegistry.proxy.displayTooltip(TextFormatting.RED + "HUD OFF");
				}
			}
			keysPressed[key.ordinal()] = pressed;
		}
		
		@Override
		public boolean getEnableBackpack(){
			return enableBackpack;
		}

		@Override
		public boolean getEnableHUD(){
			return enableHUD;
		}

		@Override
		public void setEnableBackpack(boolean b){
			enableBackpack = b;
		}

		@Override
		public void setEnableHUD(boolean b){
			enableHUD = b;
		}

		@Override
		public boolean getOnLadder() {return isOnLadder;}

		@Override
		public void setOnLadder(boolean b){isOnLadder = b;}
		
	}
	
	public static class HBMDataStorage implements IStorage<IHBMData>{

		@Override
		public NBTBase writeNBT(Capability<IHBMData> capability, IHBMData instance, EnumFacing side) {
			NBTTagCompound tag = new NBTTagCompound();
			for(EnumKeybind key : EnumKeybind.values()){
				tag.setBoolean(key.name(), instance.getKeyPressed(key));
			}
			tag.setBoolean("enableBackpack", instance.getEnableBackpack());
			tag.setBoolean("enableHUD", instance.getEnableHUD());
			tag.setBoolean("isOnLadder", instance.getOnLadder());
			return tag;
		}

		@Override
		public void readNBT(Capability<IHBMData> capability, IHBMData instance, EnumFacing side, NBTBase nbt) {
			if(nbt instanceof NBTTagCompound){
				NBTTagCompound tag = (NBTTagCompound)nbt;
				for(EnumKeybind key : EnumKeybind.values()){
					instance.setKeyPressed(key, tag.getBoolean(key.name()));
				}
				instance.setEnableBackpack(tag.getBoolean("enableBackpack"));
				instance.setEnableHUD(tag.getBoolean("enableHUD"));
				instance.setOnLadder(tag.getBoolean("isOnLadder"));
			}
		}
		
	}
	
	public static class HBMDataProvider implements ICapabilitySerializable<NBTBase> {

		public static final IHBMData DUMMY = new IHBMData(){

			@Override
			public boolean getKeyPressed(EnumKeybind key) {
				return false;
			}

			@Override
			public void setKeyPressed(EnumKeybind key, boolean pressed) {
			}

			@Override
			public boolean getEnableBackpack(){
				return false;
			}

			@Override
			public boolean getEnableHUD(){
				return false;
			}

			@Override
			public void setEnableBackpack(boolean b){
			}

			@Override
			public void setEnableHUD(boolean b){
			}

			@Override
			public boolean getOnLadder() {
				return false;
			}

			@Override
			public void setOnLadder(boolean b){
			}
		};
		
		@CapabilityInject(IHBMData.class)
		public static final Capability<IHBMData> HBM_CAP = null;
		
		private IHBMData instance = HBM_CAP.getDefaultInstance();
		
		@Override
		public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
			return capability == HBM_CAP;
		}

		@Override
		public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
			return capability == HBM_CAP ? HBM_CAP.<T>cast(this.instance) : null;
		}

		@Override
		public NBTBase serializeNBT() {
			return HBM_CAP.getStorage().writeNBT(HBM_CAP, instance, null);
		}

		@Override
		public void deserializeNBT(NBTBase nbt) {
			HBM_CAP.getStorage().readNBT(HBM_CAP, instance, null, nbt);
		}
		
	}
	
	public static IHBMData getData(Entity e){
		if(e.hasCapability(HBMDataProvider.HBM_CAP, null))
			return e.getCapability(HBMDataProvider.HBM_CAP, null);
		return HBMDataProvider.DUMMY;
	}
}
