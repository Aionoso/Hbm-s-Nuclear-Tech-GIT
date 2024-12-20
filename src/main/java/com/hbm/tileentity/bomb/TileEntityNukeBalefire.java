package com.hbm.tileentity.bomb;

import com.hbm.config.BombConfig;
import com.hbm.entity.effect.EntityNukeTorex;
import com.hbm.entity.logic.EntityBalefire;
import com.hbm.items.ModItems;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.tileentity.TileEntityMachineBase;

import api.hbm.energymk2.IBatteryItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityNukeBalefire extends TileEntityMachineBase implements ITickable {

	public boolean loaded;
	public boolean started;
	public int timer;
	
	public TileEntityNukeBalefire() {
		super(2);
		timer = 18000;
	}

	@Override
	public String getName() {
		return "container.nukeFstbmb";
	}

	@Override
	public void update() {
		if(!world.isRemote) {

			if(!this.isLoaded()) {
				started = false;
			}

			if(started) {
				timer--;

				if(timer % 20 == 0)
					world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), HBMSoundHandler.fstbmbPing, SoundCategory.BLOCKS, 5.0F, 1.0F);
			}

			if(timer <= 0) {
				explode();
			}

			NBTTagCompound data = new NBTTagCompound();
			data.setInteger("timer", timer);
			data.setBoolean("loaded", this.isLoaded());
			data.setBoolean("started", started);
			networkPack(data, 250);
		}
	}
	
	public void handleButtonPacket(int value, int meta) {

		if(meta == 0 && this.isLoaded()) {
			world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), HBMSoundHandler.fstbmbStart, SoundCategory.BLOCKS, 5.0F, 1.0F);
			started = true;
		}

		if(meta == 1)
			timer = value * 20;
	}
	
	@Override
	public void networkUnpack(NBTTagCompound data) {
		timer = data.getInteger("timer");
		started = data.getBoolean("started");
		loaded = data.getBoolean("loaded");
	}
	
	public boolean isLoaded() {

		return hasEgg() && hasBattery();
	}

	public boolean hasEgg() {

		if(inventory.getStackInSlot(0).getItem() == ModItems.egg_balefire) {
			return true;
		}

		return false;
	}

	public boolean hasBattery() {

		return getBattery() > 0;
	}

	public int getBattery() {
		
		if(inventory.getStackInSlot(1).getItem() == ModItems.battery_spark &&
				((IBatteryItem)ModItems.battery_spark).getCharge(inventory.getStackInSlot(1)) == ((IBatteryItem)ModItems.battery_spark).getMaxCharge()) {
			return 1;
		}
		if(inventory.getStackInSlot(1).getItem() == ModItems.battery_trixite &&
				((IBatteryItem)ModItems.battery_trixite).getCharge(inventory.getStackInSlot(1)) == ((IBatteryItem)ModItems.battery_trixite).getMaxCharge()) {
			return 2;
		}

		return 0;
	}

	public void explode() {
		for(int i = 0; i < inventory.getSlots(); i++)
			inventory.setStackInSlot(i, ItemStack.EMPTY);

		world.destroyBlock(pos, false);

		EntityBalefire bf = new EntityBalefire(world);
		bf.posX = pos.getX() + 0.5;
		bf.posY = pos.getY() + 0.5;
		bf.posZ = pos.getZ() + 0.5;
		bf.destructionRange = (int) 250;
		world.spawnEntity(bf);
		if(BombConfig.enableNukeClouds) {
			EntityNukeTorex.statFacBale(world, pos.getX() + 0.5, pos.getY() + 5, pos.getZ() + 0.5, 250F);
		}
	}

	public String getMinutes() {

		String mins = "" + (timer / 1200);

		if(mins.length() == 1)
			mins = "0" + mins;

		return mins;
	}

	public String getSeconds() {

		String mins = "" + ((timer / 20) % 60);

		if(mins.length() == 1)
			mins = "0" + mins;

		return mins;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		started = compound.getBoolean("started");
		timer = compound.getInteger("timer");
		super.readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setBoolean("started", started);
		compound.setInteger("timer", timer);
		return super.writeToNBT(compound);
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
}