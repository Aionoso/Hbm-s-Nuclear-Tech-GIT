package com.hbm.tileentity.machine;

import api.hbm.energymk2.IEnergyProviderMK2;
import com.hbm.blocks.ModBlocks;
import com.hbm.lib.ForgeDirection;
import com.hbm.tileentity.TileEntityLoadedBase;

import net.minecraft.util.ITickable;

public class TileEntityMachineMiniRTG extends TileEntityLoadedBase implements ITickable, IEnergyProviderMK2 {

	public long power;
	
	@Override
	public void update() {
		if(!world.isRemote) {
			for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
				this.tryProvide(world, pos.getX(), pos.getY(), pos.getZ(), dir);
			}
			if(this.getBlockType() == ModBlocks.machine_powerrtg)
				power += 2500;
			else
				power += 70;

			if(power > getMaxPower())
				power = getMaxPower();
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
		if(this.getBlockType() == ModBlocks.machine_powerrtg)
			return 50000;

		return 10000;
	}
}
