package com.hbm.tileentity.machine;

import api.hbm.energymk2.IEnergyProviderMK2;
import com.hbm.blocks.ModBlocks;
import com.hbm.lib.ForgeDirection;
import com.hbm.saveddata.RadiationSavedData;
import com.hbm.tileentity.TileEntityLoadedBase;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

public class TileEntityMachineAmgen extends TileEntityLoadedBase implements ITickable, IEnergyProviderMK2 {

	public long power;
	public long maxPower = 500;
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		power = compound.getLong("power");
		super.readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setLong("power", power);
		return super.writeToNBT(compound);
	}
	
	@Override
	public void update() {
		if(!world.isRemote) {
			long prevPower = power;

			if(this.getBlockType() == ModBlocks.machine_amgen) {
				power += RadiationSavedData.getData(world).getRadNumFromCoord(pos);
				
				RadiationSavedData.decrementRad(world, pos, 5F);
				
			} else {
				
				Block b = world.getBlockState(pos.down()).getBlock();
				if(b == ModBlocks.geysir_water) {
					power += 75;
				} else if(b == ModBlocks.geysir_chlorine) {
					power += 100;
				} else if(b == ModBlocks.geysir_vapor) {
					power += 50;
				} else if(b == ModBlocks.geysir_nether) {
					power += 500;
				} else if(b == Blocks.LAVA) {
					power += 100;
				} else if(b == Blocks.FLOWING_LAVA) {
					power += 25;
				}
				
				b = world.getBlockState(pos.up()).getBlock();
				
				if(b == Blocks.LAVA) {
					power += 100;
					
				} else if(b == Blocks.FLOWING_LAVA) {
					power += 25;
				}
			}
			
			if(power > maxPower)
				power = maxPower;

			for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
				this.tryProvide(world, pos.getX() + dir.offsetX, pos.getY() + dir.offsetY, pos.getZ() + dir.offsetZ, dir);
			if(prevPower != power)
				markDirty();
		}
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
		return this.maxPower;
	}
}
